package net.time4j.tz;

import com.facebook.imageutils.JfifUtil;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcMLKit;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.StreamCorruptedException;

/* loaded from: classes4.dex */
final class SPX implements Externalizable {
    static final int FALLBACK_TIMEZONE_TYPE = 12;
    static final int HISTORIZED_TIMEZONE_TYPE = 14;
    static final int TRANSITION_RESOLVER_TYPE = 13;
    static final int ZONAL_OFFSET_TYPE = 15;
    private static final long serialVersionUID = -1000776907354520172L;
    private transient Object obj;
    private transient int type;

    private Object readResolve() throws ObjectStreamException {
        return this.obj;
    }

    public SPX() {
    }

    SPX(Object obj, int i) {
        this.obj = obj;
        this.type = i;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        switch (this.type) {
            case 12:
                writeFallback(objectOutput);
                return;
            case 13:
                writeStrategy(objectOutput);
                return;
            case 14:
                writeZone(objectOutput);
                return;
            case 15:
                writeOffset(objectOutput);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type.");
        }
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        byte b = objectInput.readByte();
        switch ((b & 255) >> 4) {
            case 12:
                this.obj = readFallback(objectInput, b);
                return;
            case 13:
                this.obj = readStrategy(b);
                return;
            case 14:
                this.obj = readZone(objectInput, b);
                return;
            case 15:
                this.obj = readOffset(objectInput, b);
                return;
            default:
                throw new StreamCorruptedException("Unknown serialized type.");
        }
    }

    private void writeFallback(ObjectOutput objectOutput) throws IOException {
        FallbackTimezone fallbackTimezone = (FallbackTimezone) this.obj;
        objectOutput.writeByte(192);
        objectOutput.writeObject(fallbackTimezone.getID());
        objectOutput.writeObject(fallbackTimezone.getFallback());
    }

    private Object readFallback(ObjectInput objectInput, byte b) throws IOException, ClassNotFoundException {
        return new FallbackTimezone((TZID) objectInput.readObject(), (Timezone) objectInput.readObject());
    }

    private void writeStrategy(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeByte(((TransitionResolver) this.obj).getKey() | 208);
    }

    private Object readStrategy(byte b) {
        int i = b & 15;
        return TransitionResolver.of(GapResolver.values()[i / 2], OverlapResolver.values()[i % 2]);
    }

    private void writeZone(ObjectOutput objectOutput) throws IOException {
        HistorizedTimezone historizedTimezone = (HistorizedTimezone) this.obj;
        boolean z = historizedTimezone.getStrategy() != Timezone.DEFAULT_CONFLICT_STRATEGY;
        objectOutput.writeByte(z ? JfifUtil.MARKER_APP1 : 224);
        objectOutput.writeObject(historizedTimezone.getID());
        objectOutput.writeObject(historizedTimezone.getHistory());
        if (z) {
            objectOutput.writeObject(historizedTimezone.getStrategy());
        }
    }

    private Object readZone(ObjectInput objectInput, byte b) throws IOException, ClassNotFoundException {
        TZID tzid = (TZID) objectInput.readObject();
        TransitionHistory transitionHistory = (TransitionHistory) objectInput.readObject();
        TransitionStrategy transitionStrategy = Timezone.DEFAULT_CONFLICT_STRATEGY;
        if ((b & 15) == 1) {
            transitionStrategy = (TransitionStrategy) objectInput.readObject();
        }
        return new HistorizedTimezone(tzid, transitionHistory, transitionStrategy);
    }

    private void writeOffset(ObjectOutput objectOutput) throws IOException {
        ZonalOffset zonalOffset = (ZonalOffset) this.obj;
        boolean z = zonalOffset.getFractionalAmount() != 0;
        objectOutput.writeByte(z ? 241 : FaceDetectorAvcMLKit.FACE_DETECTION_FRAME_WIDTH);
        objectOutput.writeInt(zonalOffset.getIntegralAmount());
        if (z) {
            objectOutput.writeInt(zonalOffset.getFractionalAmount());
        }
    }

    private Object readOffset(ObjectInput objectInput, byte b) throws IOException {
        return ZonalOffset.ofTotalSeconds(objectInput.readInt(), (b & 15) == 1 ? objectInput.readInt() : 0);
    }
}
