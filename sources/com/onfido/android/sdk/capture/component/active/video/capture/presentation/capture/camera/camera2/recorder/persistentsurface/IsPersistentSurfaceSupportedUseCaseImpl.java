package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.ApiLevel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.ApiLevelUtil;
import com.onfido.android.sdk.capture.utils.DeviceUtils;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\f\u001a\u00020\nH\u0096\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/IsPersistentSurfaceSupportedUseCaseImpl;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/IsPersistentSurfaceSupportedUseCase;", "apiLevelUtil", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/ApiLevelUtil;", "deviceUtils", "Lcom/onfido/android/sdk/capture/utils/DeviceUtils;", "repository", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/PersistentRecorderSurfaceRepository;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/ApiLevelUtil;Lcom/onfido/android/sdk/capture/utils/DeviceUtils;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/PersistentRecorderSurfaceRepository;)V", "isPersistentSurfaceSupported", "", "Ljava/lang/Boolean;", "invoke", "isInUnsupportedDeviceList", "isSupported", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class IsPersistentSurfaceSupportedUseCaseImpl implements IsPersistentSurfaceSupportedUseCase {
    private final ApiLevelUtil apiLevelUtil;
    private final DeviceUtils deviceUtils;
    private Boolean isPersistentSurfaceSupported;
    private final PersistentRecorderSurfaceRepository repository;

    @Inject
    public IsPersistentSurfaceSupportedUseCaseImpl(ApiLevelUtil apiLevelUtil, DeviceUtils deviceUtils, PersistentRecorderSurfaceRepository repository) {
        Intrinsics.checkNotNullParameter(apiLevelUtil, "apiLevelUtil");
        Intrinsics.checkNotNullParameter(deviceUtils, "deviceUtils");
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.apiLevelUtil = apiLevelUtil;
        this.deviceUtils = deviceUtils;
        this.repository = repository;
    }

    private final boolean isInUnsupportedDeviceList() {
        List<String> unsupportedDeviceList = this.repository.getUnsupportedDeviceList();
        ArrayList arrayList = new ArrayList();
        for (Object obj : unsupportedDeviceList) {
            if (true ^ StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String lowerCase = ((String) it.next()).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            arrayList2.add(lowerCase);
        }
        ArrayList<String> arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(StringsKt.trim((CharSequence) it2.next()).toString());
        }
        if (!arrayList3.isEmpty()) {
            for (String str : arrayList3) {
                String lowerCase2 = this.deviceUtils.getDeviceModel$onfido_capture_sdk_core_release().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                if (StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) str, false, 2, (Object) null)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isSupported() {
        return this.apiLevelUtil.isApiAtLeast(ApiLevel.MARSHMALLOW) && !isInUnsupportedDeviceList();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCase
    public boolean invoke() {
        Boolean bool = this.isPersistentSurfaceSupported;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zIsSupported = isSupported();
        this.isPersistentSurfaceSupported = Boolean.valueOf(zIsSupported);
        return zIsSupported;
    }
}
