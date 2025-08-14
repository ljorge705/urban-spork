package io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import javax.annotation.Nullable;
import okio.Buffer;

/* loaded from: classes6.dex */
class OutboundFlowController {
    private final FrameWriter frameWriter;
    private final Transport transport;
    private int initialWindowSize = 65535;
    private final StreamState connectionState = new StreamState(0, 65535, null);

    public interface Stream {
        void onSentBytes(int i);
    }

    public interface Transport {
        StreamState[] getActiveStreams();
    }

    public OutboundFlowController(Transport transport, FrameWriter frameWriter) {
        this.transport = (Transport) Preconditions.checkNotNull(transport, NotificationCompat.CATEGORY_TRANSPORT);
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter, "frameWriter");
    }

    public boolean initialOutboundWindowSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Invalid initial window size: " + i);
        }
        int i2 = i - this.initialWindowSize;
        this.initialWindowSize = i;
        for (StreamState streamState : this.transport.getActiveStreams()) {
            streamState.incrementStreamWindow(i2);
        }
        return i2 > 0;
    }

    public int windowUpdate(@Nullable StreamState streamState, int i) {
        if (streamState == null) {
            int iIncrementStreamWindow = this.connectionState.incrementStreamWindow(i);
            writeStreams();
            return iIncrementStreamWindow;
        }
        int iIncrementStreamWindow2 = streamState.incrementStreamWindow(i);
        WriteStatus writeStatus = new WriteStatus();
        streamState.writeBytes(streamState.writableWindow(), writeStatus);
        if (writeStatus.hasWritten()) {
            flush();
        }
        return iIncrementStreamWindow2;
    }

    public void data(boolean z, StreamState streamState, Buffer buffer, boolean z2) {
        Preconditions.checkNotNull(buffer, "source");
        int iWritableWindow = streamState.writableWindow();
        boolean zHasPendingData = streamState.hasPendingData();
        int size = (int) buffer.size();
        if (!zHasPendingData && iWritableWindow >= size) {
            streamState.write(buffer, size, z);
        } else {
            if (!zHasPendingData && iWritableWindow > 0) {
                streamState.write(buffer, iWritableWindow, false);
            }
            streamState.enqueueData(buffer, (int) buffer.size(), z);
        }
        if (z2) {
            flush();
        }
    }

    public void notifyWhenNoPendingData(StreamState streamState, Runnable runnable) {
        Preconditions.checkNotNull(runnable, "noPendingDataRunnable");
        if (streamState.hasPendingData()) {
            streamState.notifyWhenNoPendingData(runnable);
        } else {
            runnable.run();
        }
    }

    public void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StreamState createState(Stream stream, int i) {
        return new StreamState(i, this.initialWindowSize, (Stream) Preconditions.checkNotNull(stream, "stream"));
    }

    public void writeStreams() {
        int i;
        StreamState[] activeStreams = this.transport.getActiveStreams();
        Collections.shuffle(Arrays.asList(activeStreams));
        int iWindow = this.connectionState.window();
        int length = activeStreams.length;
        while (true) {
            i = 0;
            if (length <= 0 || iWindow <= 0) {
                break;
            }
            int iCeil = (int) Math.ceil(iWindow / length);
            for (int i2 = 0; i2 < length && iWindow > 0; i2++) {
                StreamState streamState = activeStreams[i2];
                int iMin = Math.min(iWindow, Math.min(streamState.unallocatedBytes(), iCeil));
                if (iMin > 0) {
                    streamState.allocateBytes(iMin);
                    iWindow -= iMin;
                }
                if (streamState.unallocatedBytes() > 0) {
                    activeStreams[i] = streamState;
                    i++;
                }
            }
            length = i;
        }
        WriteStatus writeStatus = new WriteStatus();
        StreamState[] activeStreams2 = this.transport.getActiveStreams();
        int length2 = activeStreams2.length;
        while (i < length2) {
            StreamState streamState2 = activeStreams2[i];
            streamState2.writeBytes(streamState2.allocatedBytes(), writeStatus);
            streamState2.clearAllocatedBytes();
            i++;
        }
        if (writeStatus.hasWritten()) {
            flush();
        }
    }

    private static final class WriteStatus {
        int numWrites;

        boolean hasWritten() {
            return this.numWrites > 0;
        }

        void incrementNumWrites() {
            this.numWrites++;
        }

        private WriteStatus() {
        }
    }

    public final class StreamState {
        private int allocatedBytes;
        private Runnable noPendingDataRunnable;
        private final Stream stream;
        private final int streamId;
        private int window;
        private final Buffer pendingWriteBuffer = new Buffer();
        private boolean pendingBufferHasEndOfStream = false;

        void allocateBytes(int i) {
            this.allocatedBytes += i;
        }

        int allocatedBytes() {
            return this.allocatedBytes;
        }

        void clearAllocatedBytes() {
            this.allocatedBytes = 0;
        }

        int window() {
            return this.window;
        }

        StreamState(int i, int i2, Stream stream) {
            this.streamId = i;
            this.window = i2;
            this.stream = stream;
        }

        int unallocatedBytes() {
            return streamableBytes() - this.allocatedBytes;
        }

        int incrementStreamWindow(int i) {
            if (i > 0 && Integer.MAX_VALUE - i < this.window) {
                throw new IllegalArgumentException("Window size overflow for stream: " + this.streamId);
            }
            int i2 = this.window + i;
            this.window = i2;
            return i2;
        }

        int writableWindow() {
            return Math.min(this.window, OutboundFlowController.this.connectionState.window());
        }

        int streamableBytes() {
            return Math.max(0, Math.min(this.window, (int) this.pendingWriteBuffer.size()));
        }

        boolean hasPendingData() {
            return this.pendingWriteBuffer.size() > 0;
        }

        int writeBytes(int i, WriteStatus writeStatus) {
            Runnable runnable;
            int iMin = Math.min(i, writableWindow());
            int size = 0;
            while (hasPendingData() && iMin > 0) {
                if (iMin >= this.pendingWriteBuffer.size()) {
                    size += (int) this.pendingWriteBuffer.size();
                    Buffer buffer = this.pendingWriteBuffer;
                    write(buffer, (int) buffer.size(), this.pendingBufferHasEndOfStream);
                } else {
                    size += iMin;
                    write(this.pendingWriteBuffer, iMin, false);
                }
                writeStatus.incrementNumWrites();
                iMin = Math.min(i - size, writableWindow());
            }
            if (!hasPendingData() && (runnable = this.noPendingDataRunnable) != null) {
                runnable.run();
                this.noPendingDataRunnable = null;
            }
            return size;
        }

        void write(Buffer buffer, int i, boolean z) {
            do {
                int iMin = Math.min(i, OutboundFlowController.this.frameWriter.maxDataLength());
                int i2 = -iMin;
                OutboundFlowController.this.connectionState.incrementStreamWindow(i2);
                incrementStreamWindow(i2);
                try {
                    OutboundFlowController.this.frameWriter.data(buffer.size() == ((long) iMin) && z, this.streamId, buffer, iMin);
                    this.stream.onSentBytes(iMin);
                    i -= iMin;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (i > 0);
        }

        void enqueueData(Buffer buffer, int i, boolean z) {
            this.pendingWriteBuffer.write(buffer, i);
            this.pendingBufferHasEndOfStream |= z;
        }

        void notifyWhenNoPendingData(Runnable runnable) {
            Preconditions.checkState(this.noPendingDataRunnable == null, "pending data notification already requested");
            this.noPendingDataRunnable = runnable;
        }
    }
}
