package com.uxcam.screenshot.repository;

import com.uxcam.screenshot.model.UXCamBlur;
import com.uxcam.screenshot.model.UXCamOccludeAllTextFields;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.model.UXCamOverlay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/uxcam/screenshot/repository/OcclusionRepositoryImpl;", "Lcom/uxcam/screenshot/repository/OcclusionRepository;", "<init>", "()V", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class OcclusionRepositoryImpl implements OcclusionRepository {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap f277a = new HashMap();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public final HashMap d = new HashMap();
    public final HashMap e = new HashMap();
    public final HashMap f = new HashMap();
    public final ArrayList g = new ArrayList();
    public UXCamOcclusion h;
    public UXCamOcclusion i;
    public UXCamOverlay j;
    public UXCamBlur k;
    public UXCamOccludeAllTextFields l;
    public UXCamOccludeAllTextFields m;

    /* renamed from: n, reason: collision with root package name */
    public UXCamOccludeAllTextFields f278n;

    @Override // com.uxcam.screenshot.repository.OcclusionRepository
    public final void applyOcclusionFromBackend(UXCamOcclusion uXCamOcclusion) {
        if (uXCamOcclusion == null) {
            return;
        }
        if (uXCamOcclusion.getExcludeMentionedScreens()) {
            if (Intrinsics.areEqual(uXCamOcclusion.getClass(), UXCamOccludeAllTextFields.class)) {
                ArrayList arrayList = this.g;
                List<String> screens = uXCamOcclusion.getScreens();
                Intrinsics.checkNotNullExpressionValue(screens, "occlusion.screens");
                arrayList.addAll(screens);
                this.m = (UXCamOccludeAllTextFields) uXCamOcclusion;
                return;
            }
            for (String screen : uXCamOcclusion.getScreens()) {
                HashMap map = this.f;
                Intrinsics.checkNotNullExpressionValue(screen, "screen");
                map.put(screen, uXCamOcclusion);
            }
            this.i = uXCamOcclusion;
            return;
        }
        if (uXCamOcclusion.getScreens() == null) {
            if (Intrinsics.areEqual(uXCamOcclusion.getClass(), UXCamOccludeAllTextFields.class)) {
                this.m = (UXCamOccludeAllTextFields) uXCamOcclusion;
                return;
            } else {
                this.i = uXCamOcclusion;
                return;
            }
        }
        for (String screen2 : uXCamOcclusion.getScreens()) {
            Class<?> cls = uXCamOcclusion.getClass();
            if (Intrinsics.areEqual(cls, UXCamOverlay.class)) {
                HashMap map2 = this.c;
                Intrinsics.checkNotNullExpressionValue(screen2, "screen");
                map2.put(screen2, (UXCamOverlay) uXCamOcclusion);
            } else if (Intrinsics.areEqual(cls, UXCamBlur.class)) {
                HashMap map3 = this.d;
                Intrinsics.checkNotNullExpressionValue(screen2, "screen");
                map3.put(screen2, (UXCamBlur) uXCamOcclusion);
            } else if (Intrinsics.areEqual(cls, UXCamOccludeAllTextFields.class)) {
                HashMap map4 = this.e;
                Intrinsics.checkNotNullExpressionValue(screen2, "screen");
                map4.put(screen2, (UXCamOccludeAllTextFields) uXCamOcclusion);
            }
        }
    }

    @Override // com.uxcam.screenshot.repository.OcclusionRepository
    public final void applyOcclusionFromSDK(UXCamOcclusion uXCamOcclusion) {
        if (uXCamOcclusion == null) {
            return;
        }
        if (uXCamOcclusion.getScreens() == null) {
            if (Intrinsics.areEqual(uXCamOcclusion.getClass(), UXCamOccludeAllTextFields.class)) {
                this.l = (UXCamOccludeAllTextFields) uXCamOcclusion;
                return;
            } else {
                this.h = uXCamOcclusion;
                return;
            }
        }
        if (uXCamOcclusion.getExcludeMentionedScreens()) {
            Class<?> cls = uXCamOcclusion.getClass();
            if (Intrinsics.areEqual(cls, UXCamOverlay.class)) {
                this.j = (UXCamOverlay) uXCamOcclusion;
                return;
            } else if (Intrinsics.areEqual(cls, UXCamBlur.class)) {
                this.k = (UXCamBlur) uXCamOcclusion;
                return;
            } else {
                if (Intrinsics.areEqual(cls, UXCamOccludeAllTextFields.class)) {
                    this.f278n = (UXCamOccludeAllTextFields) uXCamOcclusion;
                    return;
                }
                return;
            }
        }
        for (String screen : uXCamOcclusion.getScreens()) {
            Class<?> cls2 = uXCamOcclusion.getClass();
            if (Intrinsics.areEqual(cls2, UXCamOverlay.class) || Intrinsics.areEqual(cls2, UXCamBlur.class)) {
                HashMap map = this.f277a;
                Intrinsics.checkNotNullExpressionValue(screen, "screen");
                map.put(screen, uXCamOcclusion);
            } else if (Intrinsics.areEqual(cls2, UXCamOccludeAllTextFields.class)) {
                HashMap map2 = this.b;
                Intrinsics.checkNotNullExpressionValue(screen, "screen");
                map2.put(screen, (UXCamOccludeAllTextFields) uXCamOcclusion);
            }
        }
    }

    @Override // com.uxcam.screenshot.repository.OcclusionRepository
    public final UXCamOccludeAllTextFields getOccludeAllTextFields(String str) {
        if (str == null) {
            return null;
        }
        if (this.m != null && !this.g.contains(str)) {
            return this.m;
        }
        if (this.e.containsKey(str)) {
            return (UXCamOccludeAllTextFields) this.e.get(str);
        }
        UXCamOccludeAllTextFields uXCamOccludeAllTextFields = this.l;
        if (uXCamOccludeAllTextFields != null) {
            return uXCamOccludeAllTextFields;
        }
        UXCamOccludeAllTextFields uXCamOccludeAllTextFields2 = this.f278n;
        if (uXCamOccludeAllTextFields2 != null) {
            Intrinsics.checkNotNull(uXCamOccludeAllTextFields2);
            if (!uXCamOccludeAllTextFields2.getScreens().contains(str)) {
                return this.f278n;
            }
        }
        if (this.b.containsKey(str)) {
            return (UXCamOccludeAllTextFields) this.b.get(str);
        }
        return null;
    }

    @Override // com.uxcam.screenshot.repository.OcclusionRepository
    public final UXCamOcclusion getOcclusion(String str) {
        if (str != null && shouldOcclude(str)) {
            if (this.c.containsKey(str)) {
                return (UXCamOcclusion) this.c.get(str);
            }
            if (this.d.containsKey(str)) {
                return (UXCamOcclusion) this.d.get(str);
            }
            if (this.i != null && !this.f.containsKey(str)) {
                return this.i;
            }
            if (this.f277a.containsKey(str)) {
                return (UXCamOcclusion) this.f277a.get(str);
            }
            UXCamOcclusion uXCamOcclusion = this.h;
            if (uXCamOcclusion != null) {
                return uXCamOcclusion;
            }
            UXCamOverlay uXCamOverlay = this.j;
            if (uXCamOverlay != null) {
                Intrinsics.checkNotNull(uXCamOverlay);
                if (!uXCamOverlay.getScreens().contains(str)) {
                    return this.j;
                }
            }
            UXCamBlur uXCamBlur = this.k;
            if (uXCamBlur != null) {
                Intrinsics.checkNotNull(uXCamBlur);
                if (!uXCamBlur.getScreens().contains(str)) {
                    return this.k;
                }
            }
        }
        return null;
    }

    @Override // com.uxcam.screenshot.repository.OcclusionRepository
    public final void removeAllOcclusionsFromBackend() {
        this.g.clear();
        this.m = null;
        this.f.clear();
        this.i = null;
        this.e.clear();
        this.d.clear();
        this.c.clear();
    }

    @Override // com.uxcam.screenshot.repository.OcclusionRepository
    public final void removeOcclusionFromBackend(UXCamOcclusion uXCamOcclusion) {
        if (uXCamOcclusion == null) {
            return;
        }
        if (uXCamOcclusion.getScreens() == null) {
            if (Intrinsics.areEqual(uXCamOcclusion.getClass(), UXCamOccludeAllTextFields.class)) {
                this.m = null;
            }
            this.i = null;
        } else {
            if (!uXCamOcclusion.getExcludeMentionedScreens()) {
                if (Intrinsics.areEqual(uXCamOcclusion.getClass(), UXCamOccludeAllTextFields.class)) {
                    this.m = null;
                    this.e.clear();
                }
                this.d.clear();
                this.c.clear();
                return;
            }
            if (Intrinsics.areEqual(uXCamOcclusion.getClass(), UXCamOccludeAllTextFields.class)) {
                this.g.clear();
                this.m = null;
            } else {
                this.f.clear();
                this.i = null;
            }
        }
    }

    @Override // com.uxcam.screenshot.repository.OcclusionRepository
    public final void removeOcclusionFromSDK(UXCamOcclusion uXCamOcclusion) {
        if (uXCamOcclusion == null) {
            return;
        }
        if (uXCamOcclusion.getScreens() == null) {
            if (Intrinsics.areEqual(uXCamOcclusion.getClass(), UXCamOccludeAllTextFields.class)) {
                this.l = null;
                return;
            } else {
                this.h = null;
                return;
            }
        }
        if (!uXCamOcclusion.getExcludeMentionedScreens()) {
            for (String str : uXCamOcclusion.getScreens()) {
                Class<?> cls = uXCamOcclusion.getClass();
                if (Intrinsics.areEqual(cls, UXCamOverlay.class) || Intrinsics.areEqual(cls, UXCamBlur.class)) {
                    this.f277a.remove(str);
                } else if (Intrinsics.areEqual(cls, UXCamOccludeAllTextFields.class)) {
                    this.b.remove(str);
                }
            }
            return;
        }
        Class<?> cls2 = uXCamOcclusion.getClass();
        if (Intrinsics.areEqual(cls2, UXCamOverlay.class)) {
            this.j = null;
        } else if (Intrinsics.areEqual(cls2, UXCamBlur.class)) {
            this.k = null;
        } else if (Intrinsics.areEqual(cls2, UXCamOccludeAllTextFields.class)) {
            this.f278n = null;
        }
    }

    @Override // com.uxcam.screenshot.repository.OcclusionRepository
    public final boolean shouldOcclude(String str) {
        if (str == null) {
            return false;
        }
        if (this.h != null) {
            return true;
        }
        UXCamOverlay uXCamOverlay = this.j;
        if (uXCamOverlay != null) {
            Intrinsics.checkNotNull(uXCamOverlay);
            if (!uXCamOverlay.getScreens().contains(str)) {
                return true;
            }
        }
        UXCamBlur uXCamBlur = this.k;
        if (uXCamBlur != null) {
            Intrinsics.checkNotNull(uXCamBlur);
            if (!uXCamBlur.getScreens().contains(str)) {
                return true;
            }
        }
        if (this.f277a.containsKey(str)) {
            return true;
        }
        if ((this.i == null || this.f.containsKey(str)) && !this.c.containsKey(str)) {
            return this.d.containsKey(str);
        }
        return true;
    }
}
