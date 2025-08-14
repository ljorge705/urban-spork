package com.uxcam.internals;

import java.nio.ByteBuffer;
import java.util.HashMap;

/* loaded from: classes6.dex */
public final class es extends dz {
    public static final /* synthetic */ int c = 0;

    public static class aa extends ao {
        public aa() {
            HashMap map = new HashMap();
            map.put("ap4h", ij.class);
            map.put("apch", ij.class);
            map.put("apcn", ij.class);
            map.put("apcs", ij.class);
            map.put("apco", ij.class);
            map.put("avc1", ij.class);
            map.put("cvid", ij.class);
            map.put("jpeg", ij.class);
            map.put("smc ", ij.class);
            map.put("rle ", ij.class);
            map.put("rpza", ij.class);
            map.put("kpcd", ij.class);
            map.put("png ", ij.class);
            map.put("mjpa", ij.class);
            map.put("mjpb", ij.class);
            map.put("SVQ1", ij.class);
            map.put("SVQ3", ij.class);
            map.put("mp4v", ij.class);
            map.put("dvc ", ij.class);
            map.put("dvcp", ij.class);
            map.put("gif ", ij.class);
            map.put("h263", ij.class);
            map.put("tiff", ij.class);
            map.put("raw ", ij.class);
            map.put("2vuY", ij.class);
            map.put("yuv2", ij.class);
            map.put("v308", ij.class);
            map.put("v408", ij.class);
            map.put("v216", ij.class);
            map.put("v410", ij.class);
            map.put("v210", ij.class);
            map.put("m2v1", ij.class);
            map.put("m1v1", ij.class);
            map.put("xd5b", ij.class);
            map.put("dv5n", ij.class);
            map.put("jp2h", ij.class);
            map.put("mjp2", ij.class);
            map.put("ac-3", ai.class);
            map.put("cac3", ai.class);
            map.put("ima4", ai.class);
            map.put("aac ", ai.class);
            map.put("celp", ai.class);
            map.put("hvxc", ai.class);
            map.put("twvq", ai.class);
            map.put(".mp1", ai.class);
            map.put(".mp2", ai.class);
            map.put("midi", ai.class);
            map.put("apvs", ai.class);
            map.put("alac", ai.class);
            map.put("aach", ai.class);
            map.put("aacl", ai.class);
            map.put("aace", ai.class);
            map.put("aacf", ai.class);
            map.put("aacp", ai.class);
            map.put("aacs", ai.class);
            map.put("samr", ai.class);
            map.put("AUDB", ai.class);
            map.put("ilbc", ai.class);
            map.put("ms\u0000\u0011", ai.class);
            map.put("ms\u00001", ai.class);
            map.put("aes3", ai.class);
            map.put("NONE", ai.class);
            map.put("raw ", ai.class);
            map.put("twos", ai.class);
            map.put("sowt", ai.class);
            map.put("MAC3 ", ai.class);
            map.put("MAC6 ", ai.class);
            map.put("ima4", ai.class);
            map.put("fl32", ai.class);
            map.put("fl64", ai.class);
            map.put("in24", ai.class);
            map.put("in32", ai.class);
            map.put("ulaw", ai.class);
            map.put("alaw", ai.class);
            map.put("dvca", ai.class);
            map.put("QDMC", ai.class);
            map.put("QDM2", ai.class);
            map.put("Qclp", ai.class);
            map.put(".mp3", ai.class);
            map.put("mp4a", ai.class);
            map.put("lpcm", ai.class);
            map.put("tmcd", go.class);
            map.put("time", go.class);
            map.put("c608", et.class);
            map.put("c708", et.class);
            map.put("text", et.class);
        }
    }

    static {
        new aa();
    }

    public es(cy cyVar) {
        super(cyVar);
    }

    @Override // com.uxcam.internals.dz, com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        byteBuffer.putInt(0);
        byteBuffer.putInt(this.b.size());
        super.a(byteBuffer);
    }

    public es() {
        this(new cy("stsd"));
    }
}
