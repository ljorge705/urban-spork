package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaRecorder;
import android.util.Size;
import android.view.SurfaceHolder;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007\b\u0007¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J3\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0002\u0010\u0019J!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u001b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0002\u0010\u001c¨\u0006\u001e"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;", "", "()V", "findPreferredSize", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SmartSize;", TouchesHelper.TARGET_KEY, "", "sizes", "", "Landroid/util/Size;", "preferredSize", "(Ljava/lang/String;[Landroid/util/Size;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SmartSize;)Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SmartSize;", "getCameraStreamConfigMap", "Landroid/hardware/camera2/params/StreamConfigurationMap;", "characteristics", "Landroid/hardware/camera2/CameraCharacteristics;", "getPreviewSize", "getVideoSize", "hasTheSameAspectRatio", "", "smartSize", "isWidthBiggerThanHeight", "logSizes", "", "selectedSize", "(Ljava/lang/String;Landroid/util/Size;Landroid/util/Size;[Landroid/util/Size;)V", "sortByArea", "", "([Landroid/util/Size;)Ljava/util/List;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SurfaceSizeProvider {
    private static final SmartSize PREFERRED_PREVIEW_SIZE = new SmartSize(1600, 1200);
    private static final SmartSize PREFERRED_VIDEO_SIZE = new SmartSize(OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE, DefaultFrameSampler.DESIRED_FRAME_WIDTH);

    @Inject
    public SurfaceSizeProvider() {
    }

    private final SmartSize findPreferredSize(String target, Size[] sizes, SmartSize preferredSize) {
        for (SmartSize smartSize : sortByArea(sizes)) {
            if (smartSize.getLong() <= preferredSize.getLong() && smartSize.getShort() <= preferredSize.getShort() && isWidthBiggerThanHeight(smartSize) && hasTheSameAspectRatio(preferredSize, smartSize)) {
                logSizes(target, preferredSize.getSize(), smartSize.getSize(), sizes);
                if (!Intrinsics.areEqual(smartSize.getSize(), preferredSize.getSize())) {
                    Timber.INSTANCE.e("Couldn't find preferredSize (" + preferredSize.getSize() + ") for " + target + ". selectedSize: " + smartSize + ". Supported sizes: " + ArraysKt.joinToString$default(sizes, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), new Object[0]);
                }
                return smartSize;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final StreamConfigurationMap getCameraStreamConfigMap(CameraCharacteristics characteristics) {
        Object obj = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        Intrinsics.checkNotNull(obj);
        return (StreamConfigurationMap) obj;
    }

    private final boolean hasTheSameAspectRatio(SmartSize preferredSize, SmartSize smartSize) {
        return preferredSize.getShort() * smartSize.getLong() == preferredSize.getLong() * smartSize.getShort();
    }

    private final boolean isWidthBiggerThanHeight(SmartSize smartSize) {
        return smartSize.getSize().getWidth() > smartSize.getSize().getHeight();
    }

    private final void logSizes(String target, Size preferredSize, Size selectedSize, Size[] sizes) {
        Timber.Companion companion = Timber.INSTANCE;
        companion.i(target, new Object[0]);
        companion.i("preferredSize: " + preferredSize, new Object[0]);
        companion.i("selectedSize: " + selectedSize, new Object[0]);
        companion.i("supported sizes:\n" + ArraysKt.joinToString$default(sizes, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), new Object[0]);
    }

    private final List<SmartSize> sortByArea(Size[] sizes) {
        List<Size> listSortedWith = ArraysKt.sortedWith(sizes, new Comparator() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider$sortByArea$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Size size = (Size) t;
                Size size2 = (Size) t2;
                return ComparisonsKt.compareValues(Integer.valueOf(size.getHeight() * size.getWidth()), Integer.valueOf(size2.getHeight() * size2.getWidth()));
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10));
        for (Size size : listSortedWith) {
            arrayList.add(new SmartSize(size.getWidth(), size.getHeight()));
        }
        return CollectionsKt.reversed(arrayList);
    }

    public final SmartSize getPreviewSize(CameraCharacteristics characteristics) {
        Intrinsics.checkNotNullParameter(characteristics, "characteristics");
        Size[] outputSizes = getCameraStreamConfigMap(characteristics).getOutputSizes(SurfaceHolder.class);
        Intrinsics.checkNotNull(outputSizes);
        return findPreferredSize("SurfaceHolder", outputSizes, PREFERRED_PREVIEW_SIZE);
    }

    public final SmartSize getVideoSize(CameraCharacteristics characteristics) {
        Intrinsics.checkNotNullParameter(characteristics, "characteristics");
        Size[] outputSizes = getCameraStreamConfigMap(characteristics).getOutputSizes(MediaRecorder.class);
        Intrinsics.checkNotNull(outputSizes);
        return findPreferredSize("MediaRecorder", outputSizes, PREFERRED_VIDEO_SIZE);
    }
}
