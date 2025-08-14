package com.drew.imaging.riff;

import com.drew.lang.SequentialReader;
import com.drew.metadata.avi.AviDirectory;
import java.io.IOException;

/* loaded from: classes5.dex */
public class RiffReader {
    public void processRiff(SequentialReader sequentialReader, RiffHandler riffHandler) throws RiffProcessingException, IOException {
        sequentialReader.setMotorolaByteOrder(false);
        String string = sequentialReader.getString(4);
        if (!string.equals("RIFF")) {
            throw new RiffProcessingException("Invalid RIFF header: " + string);
        }
        int int32 = sequentialReader.getInt32() - 4;
        if (riffHandler.shouldAcceptRiffIdentifier(sequentialReader.getString(4))) {
            processChunks(sequentialReader, int32, riffHandler);
        }
    }

    public void processChunks(SequentialReader sequentialReader, int i, RiffHandler riffHandler) throws IOException {
        while (sequentialReader.getPosition() < i) {
            String str = new String(sequentialReader.getBytes(4));
            int int32 = sequentialReader.getInt32();
            if (str.equals("LIST") || str.equals("RIFF")) {
                if (riffHandler.shouldAcceptList(new String(sequentialReader.getBytes(4)))) {
                    processChunks(sequentialReader, int32 - 4, riffHandler);
                } else {
                    sequentialReader.skip(int32 - 4);
                }
            } else if (str.equals(AviDirectory.CHUNK_DATETIME_ORIGINAL)) {
                riffHandler.processChunk(str, sequentialReader.getBytes(int32 - 2));
                sequentialReader.skip(2L);
            } else {
                if (riffHandler.shouldAcceptChunk(str)) {
                    riffHandler.processChunk(str, sequentialReader.getBytes(int32));
                } else {
                    sequentialReader.skip(int32);
                }
                if (int32 % 2 == 1) {
                    sequentialReader.skip(1L);
                }
            }
        }
    }
}
