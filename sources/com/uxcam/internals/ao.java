package com.uxcam.internals;

import com.drew.metadata.heif.HeifBoxTypes;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.drew.metadata.mp4.Mp4BoxTypes;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ao {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f84a = 0;

    static {
        new ao();
    }

    public ao() {
        HashMap map = new HashMap();
        map.put("vmhd", ii.class);
        map.put("ftyp", ce.class);
        map.put("moov", dv.class);
        map.put("mvhd", dw.class);
        map.put("trak", ha.class);
        map.put(Mp4BoxTypes.BOX_TRACK_HEADER, gy.class);
        map.put("edts", dz.class);
        map.put("elst", bs.class);
        map.put("mdia", dq.class);
        map.put("mdhd", dr.class);
        map.put("minf", ds.class);
        map.put("hdlr", cx.class);
        map.put("dinf", bm.class);
        map.put("stbl", dz.class);
        int i = es.c;
        map.put("stsd", es.class);
        map.put("stts", gl.class);
        map.put("stss", gj.class);
        map.put("stps", ec.class);
        map.put("stsc", ev.class);
        map.put("stsz", eu.class);
        map.put("stco", aw.class);
        map.put("mvex", dz.class);
        map.put("moof", dz.class);
        map.put("traf", dz.class);
        map.put("mfra", dz.class);
        map.put("skip", dz.class);
        map.put("meta", dg.class);
        int i2 = bn.c;
        map.put("dref", bn.class);
        map.put(HeifBoxTypes.BOX_ITEM_PROTECTION, dz.class);
        map.put("sinf", dz.class);
        map.put("co64", av.class);
        map.put("smhd", gh.class);
        map.put("clip", dz.class);
        map.put("crgn", az.class);
        map.put("load", dh.class);
        map.put("tapt", dz.class);
        map.put("gmhd", dz.class);
        map.put("tmcd", dg.class);
        map.put("tref", dz.class);
        map.put("clef", ay.class);
        map.put("prof", ei.class);
        map.put("enof", bu.class);
        map.put("gmin", cq.class);
        map.put(QuickTimeAtomTypes.ATOM_TIMECODE_MEDIA_INFO, gn.class);
        map.put("udta", dz.class);
        map.put("ctts", bg.class);
        map.put("name", dy.class);
    }
}
