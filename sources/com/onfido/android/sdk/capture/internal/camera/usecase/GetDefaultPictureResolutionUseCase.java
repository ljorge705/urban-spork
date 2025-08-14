package com.onfido.android.sdk.capture.internal.camera.usecase;

import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.onfido.android.sdk.capture.internal.util.Dimension;
import com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\b\u0010\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0016J\u0016\u0010\u0010\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/usecase/GetDefaultPictureResolutionUseCase;", "Lcom/onfido/android/sdk/capture/ui/camera/CameraResolutionProvider;", "screenHeightToFrameHeightRatio", "", "previewDimension", "Lcom/onfido/android/sdk/capture/internal/util/Dimension;", "(DLcom/onfido/android/sdk/capture/internal/util/Dimension;)V", "getPreviewDimension", "()Lcom/onfido/android/sdk/capture/internal/util/Dimension;", "providesStrictAspectRatio", "", "getProvidesStrictAspectRatio", "()Z", "findOptimalPictureResolution", "supportedDimensionList", "", "findOptimalPreviewResolution", "supportedPreviewDimensionList", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class GetDefaultPictureResolutionUseCase implements CameraResolutionProvider {
    private final Dimension previewDimension;
    private final boolean providesStrictAspectRatio;
    private final double screenHeightToFrameHeightRatio;
    private static final int TARGET_PICTURE_HEIGHT = 720;
    private static final Dimension RESOLUTION_HD = new Dimension(TARGET_PICTURE_HEIGHT, 1280);
    private static final Dimension RESOLUTION_FULL_HD = new Dimension(PhotoshopDirectory.TAG_COUNT_INFORMATION, 1920);

    /* JADX WARN: Removed duplicated region for block: B:6:0x0020 A[PHI: r3
      0x0020: PHI (r3v2 com.onfido.android.sdk.capture.internal.util.Dimension) = 
      (r3v1 com.onfido.android.sdk.capture.internal.util.Dimension)
      (r3v0 com.onfido.android.sdk.capture.internal.util.Dimension)
     binds: [B:10:0x0036, B:5:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GetDefaultPictureResolutionUseCase(double r2, com.onfido.android.sdk.capture.internal.util.Dimension r4) {
        /*
            r1 = this;
            java.lang.String r0 = "previewDimension"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r1.<init>()
            r1.screenHeightToFrameHeightRatio = r2
            int r2 = r4.getWidth()
            com.onfido.android.sdk.capture.internal.util.Dimension r3 = com.onfido.android.sdk.capture.internal.camera.usecase.GetDefaultPictureResolutionUseCase.RESOLUTION_HD
            int r0 = r3.getWidth()
            if (r2 != r0) goto L22
            int r2 = r4.getHeight()
            int r0 = r3.getHeight()
            if (r2 < r0) goto L22
        L20:
            r4 = r3
            goto L39
        L22:
            int r2 = r4.getWidth()
            com.onfido.android.sdk.capture.internal.util.Dimension r3 = com.onfido.android.sdk.capture.internal.camera.usecase.GetDefaultPictureResolutionUseCase.RESOLUTION_FULL_HD
            int r0 = r3.getWidth()
            if (r2 != r0) goto L39
            int r2 = r4.getHeight()
            int r0 = r3.getHeight()
            if (r2 < r0) goto L39
            goto L20
        L39:
            r1.previewDimension = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.camera.usecase.GetDefaultPictureResolutionUseCase.<init>(double, com.onfido.android.sdk.capture.internal.util.Dimension):void");
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider
    public Dimension findOptimalPictureResolution(List<Dimension> supportedDimensionList) {
        Intrinsics.checkNotNullParameter(supportedDimensionList, "supportedDimensionList");
        if (Intrinsics.areEqual(this.previewDimension, RESOLUTION_HD) || Intrinsics.areEqual(this.previewDimension, RESOLUTION_FULL_HD)) {
            return this.previewDimension;
        }
        double width = this.previewDimension.getWidth() / this.previewDimension.getHeight();
        int i = (int) (TARGET_PICTURE_HEIGHT / this.screenHeightToFrameHeightRatio);
        return new Dimension((int) (i * width), i);
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider
    public Dimension findOptimalPreviewResolution(List<Dimension> supportedPreviewDimensionList) {
        Intrinsics.checkNotNullParameter(supportedPreviewDimensionList, "supportedPreviewDimensionList");
        return this.previewDimension;
    }

    protected final Dimension getPreviewDimension() {
        return this.previewDimension;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider
    public boolean getProvidesStrictAspectRatio() {
        return this.providesStrictAspectRatio;
    }
}
