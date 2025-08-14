package com.uxcam.internals;

import com.uxcam.internals.bg;
import com.uxcam.internals.ev;
import com.uxcam.internals.gl;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class cl extends ab {
    public int A;
    public boolean B;

    /* renamed from: n, reason: collision with root package name */
    public final ArrayList f109n;
    public final dl o;
    public final db p;
    public final db q;
    public final ArrayList r;
    public final cd s;
    public long t;
    public long u;
    public int v;
    public int w;
    public long x;
    public int y;
    public long z;

    public cl(cd cdVar, int i) {
        super(i);
        this.f109n = new ArrayList();
        this.o = new dl();
        this.p = new db();
        this.q = new db();
        this.r = new ArrayList();
        this.t = 0L;
        this.u = -1L;
        this.v = 0;
        this.w = 0;
        this.x = 0L;
        this.y = -1;
        this.B = true;
        this.s = cdVar;
        a(new em(1, 1));
    }

    public final void a(int i) throws IOException {
        if (this.g.size() == 0) {
            return;
        }
        dl dlVar = this.o;
        long jPosition = this.s.f107a.position();
        int i2 = dlVar.b;
        long[] jArr = dlVar.f123a;
        if (i2 >= jArr.length) {
            long[] jArr2 = new long[jArr.length + 128];
            System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
            dlVar.f123a = jArr2;
        }
        long[] jArr3 = dlVar.f123a;
        int i3 = dlVar.b;
        dlVar.b = i3 + 1;
        jArr3[i3] = jPosition;
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            ByteBuffer byteBuffer = (ByteBuffer) it.next();
            this.p.a(byteBuffer.remaining());
            this.s.f107a.write(byteBuffer);
        }
        int i4 = this.i;
        if (i4 == -1 || i4 != this.g.size()) {
            this.h.add(new ev.aa(this.j + 1, this.g.size(), i));
        }
        this.i = this.g.size();
        this.j++;
        this.f = 0L;
        this.g.clear();
    }

    @Override // com.uxcam.internals.ab
    public final long b() {
        return this.z;
    }

    @Override // com.uxcam.internals.ab
    public final ha a(dw dwVar) throws IOException {
        if (!this.k) {
            a(this.y);
            long j = this.t;
            if (j > 0) {
                this.f109n.add(new gl.aa((int) j, (int) this.u));
            }
            this.k = true;
            ha haVar = new ha();
            gf gfVarA = a();
            gy gyVar = new gy(this.f74a, (dwVar.c * this.z) / this.c, gfVarA.f172a, gfVarA.b, new Date().getTime(), new Date().getTime(), new int[]{65536, 0, 0, 0, 65536, 0, 0, 0, 1073741824});
            gyVar.b = 15;
            haVar.b.add(gyVar);
            gf gfVarA2 = a();
            if (this.b == 1) {
                dz dzVar = new dz(new cy("tapt"));
                dzVar.b.add(new ay(gfVarA2.f172a, gfVarA2.b));
                dzVar.b.add(new ei(gfVarA2.f172a, gfVarA2.b));
                dzVar.b.add(new bu(gfVarA2.f172a, gfVarA2.b));
                haVar.b.add(dzVar);
            }
            dq dqVar = new dq();
            haVar.b.add(dqVar);
            dqVar.b.add(new dr(this.c, this.z, new Date().getTime(), new Date().getTime()));
            dqVar.b.add(new cx("mhlr", gz.a(this.b)));
            ds dsVar = new ds();
            dqVar.b.add(dsVar);
            int i = this.b;
            int iA = fk.a(i);
            if (iA == 0) {
                ii iiVar = new ii(0);
                iiVar.b = 1;
                dsVar.b.add(iiVar);
            } else if (iA == 1) {
                gh ghVar = new gh();
                ghVar.b = 1;
                dsVar.b.add(ghVar);
            } else if (iA == 2) {
                dz dzVar2 = new dz(new cy("gmhd"));
                dzVar2.b.add(new cq());
                dz dzVar3 = new dz(new cy("tmcd"));
                dzVar2.b.add(dzVar3);
                dzVar3.b.add(new gn(new short[]{0, 0, 0}, new short[]{255, 255, 255}));
                dsVar.b.add(dzVar2);
            } else {
                throw new IllegalStateException("Handler " + gz.a(i) + " not supported");
            }
            dsVar.b.add(new cx("dhlr", "url "));
            bm bmVar = new bm();
            dsVar.b.add(bmVar);
            bn bnVar = new bn();
            bmVar.b.add(bnVar);
            bnVar.b.add(new dg(new cy(0L, "alis"), ByteBuffer.wrap(new byte[]{0, 0, 0, 1})));
            dz dzVar4 = new dz(new cy("stbl"));
            dsVar.b.add(dzVar4);
            if (this.r.size() > 0) {
                this.r.add(new bg.aa(this.w, this.v));
                Iterator it = this.r.iterator();
                int i2 = Integer.MAX_VALUE;
                while (it.hasNext()) {
                    int i3 = ((bg.aa) it.next()).b;
                    if (i3 < i2) {
                        i2 = i3;
                    }
                }
                if (i2 > 0) {
                    Iterator it2 = this.r.iterator();
                    while (it2.hasNext()) {
                        ((bg.aa) it2.next()).b -= i2;
                    }
                }
                if (((bg.aa) this.r.get(0)).b > 0) {
                    ArrayList arrayList = this.m;
                    if (arrayList == null) {
                        ArrayList arrayList2 = new ArrayList();
                        this.m = arrayList2;
                        arrayList2.add(new br(this.z, r3.b));
                    } else {
                        Iterator it3 = arrayList.iterator();
                        while (it3.hasNext()) {
                            ((br) it3.next()).c += r3.b;
                        }
                    }
                }
                dzVar4.b.add(new bg((bg.aa[]) this.r.toArray(new bg.aa[0])));
            }
            if (this.m != null) {
                dz dzVar5 = new dz(new cy("edts"));
                dzVar5.b.add(new bs(this.m));
                haVar.b.add(dzVar5);
            }
            et[] etVarArr = (et[]) this.l.toArray(new et[0]);
            es esVar = new es();
            for (et etVar : etVarArr) {
                esVar.b.add(etVar);
            }
            dzVar4.b.add(esVar);
            dzVar4.b.add(new ev((ev.aa[]) this.h.toArray(new ev.aa[0])));
            dzVar4.b.add(new eu(this.p.a()));
            dzVar4.b.add(new gl((gl.aa[]) this.f109n.toArray(new gl.aa[0])));
            dl dlVar = this.o;
            int i4 = dlVar.b;
            long[] jArr = new long[i4];
            System.arraycopy(dlVar.f123a, 0, jArr, 0, i4);
            dzVar4.b.add(new av(jArr));
            if (!this.B) {
                db dbVar = this.q;
                if (dbVar.b > 0) {
                    dzVar4.b.add(new gj(dbVar.a()));
                }
            }
            return haVar;
        }
        throw new IllegalStateException("The muxer track has finished muxing");
    }
}
