package com.uxcam.internals;

import com.uxcam.video.screen.codec.codecs.h264.io.model.SeqParameterSet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes6.dex */
public final class fn {

    /* renamed from: a, reason: collision with root package name */
    public final cd f155a;
    public final hb b;
    public final cv c;
    public final ArrayList d;
    public final ArrayList e;
    public final cl f;
    public final ByteBuffer g;
    public final dn h;
    public ed i;

    public fn(File file) {
        cd cdVar = new cd(new FileOutputStream(file).getChannel());
        this.f155a = cdVar;
        int i = fp.o;
        dn dnVar = new dn(cdVar, ap.MP4.a());
        this.h = dnVar;
        cd cdVar2 = dnVar.c;
        int i2 = dnVar.d;
        dnVar.d = i2 + 1;
        cl clVar = new cl(cdVar2, i2);
        dnVar.f124a.add(clVar);
        this.f = clVar;
        this.g = ByteBuffer.allocate(4194304);
        this.c = new cv(new bq());
        bd bdVar = bd.RGB;
        bd bdVar2 = cv.a()[0];
        Map map = (Map) be.f95a.get(bdVar);
        this.b = map == null ? null : (hb) map.get(bdVar2);
        this.d = new ArrayList();
        this.e = new ArrayList();
    }

    /* JADX WARN: Removed duplicated region for block: B:228:0x06ee  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x070d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(int r37, android.graphics.Bitmap r38) {
        /*
            Method dump skipped, instructions count: 1840
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.internals.fn.a(int, android.graphics.Bitmap):void");
    }

    public final void a() {
        ab abVar;
        cl clVar = this.f;
        ArrayList arrayList = this.d;
        ArrayList arrayList2 = this.e;
        ByteBuffer byteBuffer = (ByteBuffer) arrayList.get(0);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
        byteBufferAllocate.put(byteBuffer.duplicate());
        byteBufferAllocate.flip();
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(byteBufferAllocate.remaining());
        byteBufferAllocate2.put(byteBufferAllocate.duplicate());
        byteBufferAllocate2.flip();
        if (byteBufferAllocate2.remaining() >= 2) {
            ByteBuffer byteBufferDuplicate = byteBufferAllocate2.duplicate();
            ByteBuffer byteBufferDuplicate2 = byteBufferAllocate2.duplicate();
            byte b = byteBufferDuplicate.get();
            byteBufferDuplicate2.put(b);
            byte b2 = byteBufferDuplicate.get();
            byteBufferDuplicate2.put(b2);
            while (byteBufferDuplicate.hasRemaining()) {
                byte b3 = byteBufferDuplicate.get();
                if (b != 0 || b2 != 0 || b3 != 3) {
                    byteBufferDuplicate2.put(b3);
                }
                b = b2;
                b2 = b3;
            }
            byteBufferAllocate2.limit(byteBufferDuplicate2.position());
        }
        SeqParameterSet seqParameterSet = SeqParameterSet.read(byteBufferAllocate2);
        aj ajVar = new aj(seqParameterSet.profile_idc, seqParameterSet.level_idc, arrayList, arrayList2);
        int i = (seqParameterSet.pic_width_in_mbs_minus1 + 1) << 4;
        int i2 = ((seqParameterSet.pic_height_in_map_units_minus1 + 1) << (!seqParameterSet.frame_mbs_only_flag ? 1 : 0)) << 4;
        boolean z = seqParameterSet.frame_cropping_flag;
        if (z) {
            i -= (seqParameterSet.frame_crop_right_offset + seqParameterSet.frame_crop_left_offset) << seqParameterSet.chroma_format_idc.c[1];
        }
        if (z) {
            i2 -= (seqParameterSet.frame_crop_bottom_offset + seqParameterSet.frame_crop_top_offset) << seqParameterSet.chroma_format_idc.d[1];
        }
        ij ijVar = new ij(new cy("avc1"), (short) i, (short) i2, "JCodec");
        ijVar.b.add(ajVar);
        if (!clVar.k) {
            clVar.l.add(ijVar);
            dn dnVar = this.h;
            dnVar.getClass();
            dv dvVar = new dv();
            int i3 = ((ab) dnVar.f124a.get(0)).c;
            long jB = ((ab) dnVar.f124a.get(0)).b();
            Iterator it = dnVar.f124a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    abVar = null;
                    break;
                } else {
                    abVar = (ab) it.next();
                    if (abVar.b == 1) {
                        break;
                    }
                }
            }
            if (abVar != null) {
                i3 = abVar.c;
                jB = abVar.b();
            }
            dw dwVar = new dw(i3, jB, new Date().getTime(), new Date().getTime(), new int[]{65536, 0, 0, 0, 65536, 0, 0, 0, 1073741824}, dnVar.d);
            dvVar.b.add(0, dwVar);
            Iterator it2 = dnVar.f124a.iterator();
            while (it2.hasNext()) {
                ha haVarA = ((ab) it2.next()).a(dwVar);
                if (haVarA != null) {
                    dvVar.b.add(haVarA);
                }
            }
            long jPosition = (dnVar.c.f107a.position() - dnVar.b) + 8;
            cd cdVar = dnVar.c;
            int i4 = Cdo.f125a;
            ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate(4194304);
            dvVar.b(byteBufferAllocate3);
            byteBufferAllocate3.flip();
            cdVar.f107a.write(byteBufferAllocate3);
            cd cdVar2 = dnVar.c;
            cdVar2.f107a.position(dnVar.b);
            cd cdVar3 = dnVar.c;
            cdVar3.f107a.write((ByteBuffer) ByteBuffer.allocate(8).putLong(jPosition).flip());
            cd cdVar4 = this.f155a;
            if (cdVar4 == null) {
                return;
            }
            try {
                cdVar4.f107a.close();
                return;
            } catch (IOException unused) {
                return;
            }
        }
        throw new IllegalStateException("The muxer track has finished muxing");
    }
}
