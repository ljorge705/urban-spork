package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ib extends ia {
    public final /* synthetic */ ic c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ib(ic icVar, int[] iArr, int[] iArr2) {
        super(iArr, iArr2);
        this.c = icVar;
    }

    @Override // com.uxcam.internals.ia
    public final void a(am amVar, int i) {
        int[] iArr = this.c.f205a.f118a;
        int iIntValue = (i >= iArr.length ? null : Integer.valueOf(iArr[i])).intValue();
        int i2 = this.f204a[iIntValue];
        int i3 = this.b[iIntValue];
        amVar.a(i2 >>> (32 - i3), i3);
    }
}
