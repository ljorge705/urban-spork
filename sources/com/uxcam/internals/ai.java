package com.uxcam.internals;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes6.dex */
public final class ai extends et {
    static {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashSet hashSet = new HashSet();
        hashSet.add("raw ");
        hashSet.add("twos");
        hashSet.add("sowt");
        hashSet.add("fl32");
        hashSet.add("fl64");
        hashSet.add("in24");
        hashSet.add("in32");
        hashSet.add("lpcm");
        df dfVar = df.Left;
        au auVar = au.STEREO_LEFT;
        map.put(dfVar, auVar);
        df dfVar2 = df.Right;
        au auVar2 = au.STEREO_RIGHT;
        map.put(dfVar2, auVar2);
        map.put(df.HeadphonesLeft, auVar);
        map.put(df.HeadphonesRight, auVar2);
        df dfVar3 = df.LeftTotal;
        map.put(dfVar3, auVar);
        df dfVar4 = df.RightTotal;
        map.put(dfVar4, auVar2);
        df dfVar5 = df.LeftWide;
        map.put(dfVar5, auVar);
        df dfVar6 = df.RightWide;
        map.put(dfVar6, auVar2);
        map2.put(dfVar, au.FRONT_LEFT);
        map2.put(dfVar2, au.FRONT_RIGHT);
        map2.put(df.LeftCenter, au.FRONT_CENTER_LEFT);
        map2.put(df.RightCenter, au.FRONT_CENTER_RIGHT);
        map2.put(df.Center, au.CENTER);
        df dfVar7 = df.CenterSurround;
        au auVar3 = au.REAR_CENTER;
        map2.put(dfVar7, auVar3);
        map2.put(df.CenterSurroundDirect, auVar3);
        df dfVar8 = df.LeftSurround;
        au auVar4 = au.REAR_LEFT;
        map2.put(dfVar8, auVar4);
        map2.put(df.LeftSurroundDirect, auVar4);
        df dfVar9 = df.RightSurround;
        au auVar5 = au.REAR_RIGHT;
        map2.put(dfVar9, auVar5);
        map2.put(df.RightSurroundDirect, auVar5);
        map2.put(df.RearSurroundLeft, au.SIDE_LEFT);
        map2.put(df.RearSurroundRight, au.SIDE_RIGHT);
        df dfVar10 = df.LFE2;
        au auVar6 = au.LFE;
        map2.put(dfVar10, auVar6);
        map2.put(df.LFEScreen, auVar6);
        map2.put(dfVar3, auVar);
        map2.put(dfVar4, auVar2);
        map2.put(dfVar5, auVar);
        map2.put(dfVar6, auVar2);
    }

    @Override // com.uxcam.internals.et, com.uxcam.internals.dz, com.uxcam.internals.an
    public final void a(ByteBuffer byteBuffer) {
        super.a(byteBuffer);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
        byteBuffer.putInt(0);
        byteBuffer.putShort((short) 0);
        byteBuffer.putShort((short) 0);
        short s = (short) 0;
        byteBuffer.putShort(s);
        byteBuffer.putShort(s);
        byteBuffer.putInt((int) Math.round(0.0f * 65536.0d));
        c(byteBuffer);
    }

    @Override // com.uxcam.internals.dz, com.uxcam.internals.an
    public final void a(StringBuilder sb) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        sb.append(this.f83a.f115a + ": {\n");
        sb.append("entry: ");
        gx.a(this, sb, "channelCount", "sampleSize", "sampleRat", "revision", "vendor", "compressionId", "pktSize", "samplesPerPkt", "bytesPerPkt", "bytesPerFrame", "bytesPerSample", "version", "lpcmFlags");
        sb.append(",\nexts: [\n");
        b(sb);
        sb.append("\n]\n");
        sb.append("}\n");
    }
}
