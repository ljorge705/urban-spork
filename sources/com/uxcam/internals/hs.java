package com.uxcam.internals;

import com.uxcam.datamodel.UXConfig;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class hs implements hr {

    /* renamed from: a, reason: collision with root package name */
    public UXConfig f197a;

    @Override // com.uxcam.internals.hr
    public final boolean b() {
        return this.f197a != null;
    }

    @Override // com.uxcam.internals.hr
    public final UXConfig a() {
        if (this.f197a == null) {
            this.f197a = new UXConfig.Builder("").build();
        }
        UXConfig uXConfig = this.f197a;
        Intrinsics.checkNotNull(uXConfig);
        return uXConfig;
    }

    @Override // com.uxcam.internals.hr
    public final void c(boolean z) {
        if (this.f197a == null) {
            this.f197a = new UXConfig.Builder("").build();
        }
        UXConfig uXConfig = this.f197a;
        Intrinsics.checkNotNull(uXConfig);
        uXConfig.e = !z;
    }

    @Override // com.uxcam.internals.hr
    public final void d(boolean z) {
        if (this.f197a == null) {
            this.f197a = new UXConfig.Builder("").build();
        }
        UXConfig uXConfig = this.f197a;
        Intrinsics.checkNotNull(uXConfig);
        uXConfig.f = z;
    }

    @Override // com.uxcam.internals.hr
    public final void b(boolean z) {
        if (this.f197a == null) {
            this.f197a = new UXConfig.Builder("").build();
        }
        UXConfig uXConfig = this.f197a;
        Intrinsics.checkNotNull(uXConfig);
        uXConfig.getClass();
        uXConfig.d = z ? UXConfig.MultiSessionRecordStatus.ENABLED : UXConfig.MultiSessionRecordStatus.DISABLED_BUT_NOT_STARTED;
    }

    @Override // com.uxcam.internals.hr
    public final void a(UXConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        if (this.f197a == null) {
            this.f197a = new UXConfig.Builder("").build();
        }
        UXConfig uXConfig = this.f197a;
        Intrinsics.checkNotNull(uXConfig);
        uXConfig.getClass();
        uXConfig.b = config.b;
        uXConfig.c = config.c;
        uXConfig.d = config.d;
        uXConfig.e = config.e;
        uXConfig.f = config.f;
        uXConfig.g = config.g;
    }

    @Override // com.uxcam.internals.hr
    public final void a(String str) {
        if (this.f197a == null) {
            this.f197a = new UXConfig.Builder("").build();
        }
        UXConfig uXConfig = this.f197a;
        Intrinsics.checkNotNull(uXConfig);
        uXConfig.b = str;
    }

    @Override // com.uxcam.internals.hr
    public final void a(boolean z) {
        if (this.f197a == null) {
            this.f197a = new UXConfig.Builder("").build();
        }
        UXConfig uXConfig = this.f197a;
        Intrinsics.checkNotNull(uXConfig);
        uXConfig.c = z;
    }
}
