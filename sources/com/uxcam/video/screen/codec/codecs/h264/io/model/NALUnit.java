package com.uxcam.video.screen.codec.codecs.h264.io.model;

import com.google.common.base.Ascii;
import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public class NALUnit {
    public int nal_ref_idc;
    public NALUnitType type;

    public NALUnit(NALUnitType nALUnitType, int i) {
        this.type = nALUnitType;
        this.nal_ref_idc = i;
    }

    public static NALUnit read(ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        return new NALUnit(NALUnitType.fromValue(b & Ascii.US), ((b & 255) >> 5) & 3);
    }

    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) (this.type.getValue() | (this.nal_ref_idc << 5)));
    }
}
