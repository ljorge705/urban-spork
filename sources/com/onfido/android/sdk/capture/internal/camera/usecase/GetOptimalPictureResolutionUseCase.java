package com.onfido.android.sdk.capture.internal.camera.usecase;

import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.internal.camera.usecase.GetOptimalPictureResolutionUseCase;
import com.onfido.android.sdk.capture.internal.util.Dimension;
import com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider;
import com.onfido.dagger.assisted.AssistedInject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000b\u001a\u00020\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/usecase/GetOptimalPictureResolutionUseCase;", "Lcom/onfido/android/sdk/capture/internal/camera/usecase/GetDefaultPictureResolutionUseCase;", "screenHeightToFrameHeightRatio", "", "previewDimension", "Lcom/onfido/android/sdk/capture/internal/util/Dimension;", "(DLcom/onfido/android/sdk/capture/internal/util/Dimension;)V", "providesStrictAspectRatio", "", "getProvidesStrictAspectRatio", "()Z", "findOptimalPictureResolution", "supportedDimensionList", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GetOptimalPictureResolutionUseCase extends GetDefaultPictureResolutionUseCase {
    private static final double MAX_IMAGE_RESOLUTION_IN_MP = 12.0d;
    private static final double ONE_MP_PIXEL_SIZE = 1048576.0d;
    private static final int RESOLUTION_MAX_ENHANCED_IN_PX = 2199;
    private static final int RESOLUTION_MAX_IN_PX = 1999;
    private static final int RESOLUTION_MIN_IN_PX = 1201;
    private final boolean providesStrictAspectRatio;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ClosedFloatingPointRange<Double> SIXTEEN_BY_NINE_ASPECT_RATIO_RANGE = RangesKt.rangeTo(1.75d, 1.8d);

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0018\u001a\u00020\r*\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u00020\u0004*\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0018\u0010\u0013\u001a\u00020\u0004*\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000fR\u0018\u0010\u0015\u001a\u00020\u0007*\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/usecase/GetOptimalPictureResolutionUseCase$Companion;", "", "()V", "MAX_IMAGE_RESOLUTION_IN_MP", "", "ONE_MP_PIXEL_SIZE", "RESOLUTION_MAX_ENHANCED_IN_PX", "", "RESOLUTION_MAX_IN_PX", "RESOLUTION_MIN_IN_PX", "SIXTEEN_BY_NINE_ASPECT_RATIO_RANGE", "Lkotlin/ranges/ClosedFloatingPointRange;", ViewProps.ASPECT_RATIO, "Lcom/onfido/android/sdk/capture/internal/util/Dimension;", "getAspectRatio", "(Lcom/onfido/android/sdk/capture/internal/util/Dimension;)D", "isSixteenByNine", "", "(Lcom/onfido/android/sdk/capture/internal/util/Dimension;)Z", "megapixel", "getMegapixel", "pixelSize", "getPixelSize", "(Lcom/onfido/android/sdk/capture/internal/util/Dimension;)I", "rotate", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/usecase/GetOptimalPictureResolutionUseCase$Companion$Factory;", "Lcom/onfido/android/sdk/capture/ui/camera/CameraResolutionProvider$Companion$Factory;", "()V", "create", "Lcom/onfido/android/sdk/capture/ui/camera/CameraResolutionProvider;", "screenHeightToFrameHeightRatio", "", "previewDimension", "Lcom/onfido/android/sdk/capture/internal/util/Dimension;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Factory implements CameraResolutionProvider.Companion.Factory {
            @Override // com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider.Companion.Factory
            public CameraResolutionProvider create(double screenHeightToFrameHeightRatio, Dimension previewDimension) {
                Intrinsics.checkNotNullParameter(previewDimension, "previewDimension");
                return new GetOptimalPictureResolutionUseCase(screenHeightToFrameHeightRatio, previewDimension);
            }
        }

        private Companion() {
        }

        private final double getAspectRatio(Dimension dimension) {
            return dimension.getWidth() / dimension.getHeight();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final double getMegapixel(Dimension dimension) {
            return getPixelSize(dimension) / GetOptimalPictureResolutionUseCase.ONE_MP_PIXEL_SIZE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getPixelSize(Dimension dimension) {
            return dimension.getWidth() * dimension.getHeight();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSixteenByNine(Dimension dimension) {
            return GetOptimalPictureResolutionUseCase.SIXTEEN_BY_NINE_ASPECT_RATIO_RANGE.contains(Double.valueOf(getAspectRatio(dimension)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Dimension rotate(Dimension dimension) {
            return new Dimension(dimension.getHeight(), dimension.getWidth());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @AssistedInject
    public GetOptimalPictureResolutionUseCase(double d, Dimension previewDimension) {
        super(d, previewDimension);
        Intrinsics.checkNotNullParameter(previewDimension, "previewDimension");
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.usecase.GetDefaultPictureResolutionUseCase, com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider
    public Dimension findOptimalPictureResolution(List<Dimension> supportedDimensionList) {
        Object obj;
        Object next;
        Intrinsics.checkNotNullParameter(supportedDimensionList, "supportedDimensionList");
        List<Dimension> listSortedWith = CollectionsKt.sortedWith(supportedDimensionList, new Comparator() { // from class: com.onfido.android.sdk.capture.internal.camera.usecase.GetOptimalPictureResolutionUseCase$findOptimalPictureResolution$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                GetOptimalPictureResolutionUseCase.Companion companion = GetOptimalPictureResolutionUseCase.INSTANCE;
                return ComparisonsKt.compareValues(Integer.valueOf(companion.getPixelSize((Dimension) t2)), Integer.valueOf(companion.getPixelSize((Dimension) t)));
            }
        });
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : listSortedWith) {
            if (INSTANCE.isSixteenByNine((Dimension) obj2)) {
                arrayList.add(obj2);
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            int height = ((Dimension) next).getHeight();
            if (RESOLUTION_MIN_IN_PX <= height && height < 2000) {
                break;
            }
        }
        dimension = (Dimension) next;
        if (dimension == null) {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next2 = it2.next();
                if (((Dimension) next2).getHeight() <= RESOLUTION_MAX_ENHANCED_IN_PX) {
                    obj = next2;
                    break;
                }
            }
            dimension = (Dimension) obj;
            if (dimension == null) {
                for (Dimension dimension : listSortedWith) {
                    if (INSTANCE.getMegapixel(dimension) <= MAX_IMAGE_RESOLUTION_IN_MP) {
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
        }
        return INSTANCE.rotate(dimension);
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.usecase.GetDefaultPictureResolutionUseCase, com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider
    public boolean getProvidesStrictAspectRatio() {
        return this.providesStrictAspectRatio;
    }
}
