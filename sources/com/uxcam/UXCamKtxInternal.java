package com.uxcam;

import com.uxcam.screenaction.models.OccludeComposable;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.repository.ComposeOcclusionRepository;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public class UXCamKtxInternal {
    public static void occludeComposable(OccludeComposable occludeComposable) {
        boolean z = aa.h;
        try {
            ComposeOcclusionRepository composeOcclusionRepository = ScreenshotModule.INSTANCE.getInstance().getComposeOcclusionRepository();
            Intrinsics.checkNotNull(occludeComposable);
            composeOcclusionRepository.addComposable(occludeComposable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
