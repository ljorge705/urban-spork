package com.onfido.android.sdk.capture.component.active.video.capture.data.remote;

import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import android.view.accessibility.AccessibilityManager;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.javax.inject.Inject;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/remote/MotionMetaDataHelper;", "", "context", "Landroid/content/Context;", "metadata", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Landroid/content/Context;Lcom/onfido/api/client/data/SdkUploadMetaData;Lkotlinx/serialization/json/Json;)V", "getCurrentLanguageCode", "", "getMetaData", "isAudioEnabled", "", "isScreenReaderEnabled", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionMetaDataHelper {
    private final Context context;
    private final Json jsonParser;
    private final SdkUploadMetaData metadata;

    @Inject
    public MotionMetaDataHelper(Context context, SdkUploadMetaData metadata, Json jsonParser) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.context = context;
        this.metadata = metadata;
        this.jsonParser = jsonParser;
    }

    private final String getCurrentLanguageCode() {
        Configuration configuration = this.context.getResources().getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        LocaleList locales = configuration.getLocales();
        Intrinsics.checkNotNullExpressionValue(locales, "getLocales(...)");
        String languageTag = !locales.isEmpty() ? locales.get(0).toLanguageTag() : Locale.getDefault().getLanguage();
        Intrinsics.checkNotNull(languageTag);
        return languageTag;
    }

    private final boolean isScreenReaderEnabled() {
        Object systemService = this.context.getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        AccessibilityManager accessibilityManager = (AccessibilityManager) systemService;
        return accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled();
    }

    public final String getMetaData(boolean isAudioEnabled) {
        Json json = this.jsonParser;
        Map mutableMap = MapsKt.toMutableMap(JsonElementKt.getJsonObject(this.jsonParser.parseToJsonElement(json.encodeToString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(SdkUploadMetaData.class)), this.metadata))));
        mutableMap.put("record_motion_audio", JsonElementKt.JsonPrimitive(Boolean.valueOf(isAudioEnabled)));
        mutableMap.put("language_code", JsonElementKt.JsonPrimitive(getCurrentLanguageCode()));
        mutableMap.put("screen_reader_enabled", JsonElementKt.JsonPrimitive(Boolean.valueOf(isScreenReaderEnabled())));
        return new JsonObject(mutableMap).toString();
    }
}
