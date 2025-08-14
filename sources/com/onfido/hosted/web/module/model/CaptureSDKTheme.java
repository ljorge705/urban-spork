package com.onfido.hosted.web.module.model;

import com.clevertap.android.sdk.Constants;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 *2\u00020\u0001:\u0002)*BI\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0001\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\tHÆ\u0003J5\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001J&\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'HÁ\u0001¢\u0006\u0002\b(R\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R(\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0017¨\u0006+"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKTheme;", "", "seen1", "", "name", "", "legacyConfig", "", "config", "Lcom/onfido/hosted/web/module/model/WebSdkThemeConfig;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/util/Map;Lcom/onfido/hosted/web/module/model/WebSdkThemeConfig;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/util/Map;Lcom/onfido/hosted/web/module/model/WebSdkThemeConfig;)V", "getConfig$annotations", "()V", "getConfig", "()Lcom/onfido/hosted/web/module/model/WebSdkThemeConfig;", "getLegacyConfig$annotations", "getLegacyConfig", "()Ljava/util/Map;", "getName$annotations", "getName", "()Ljava/lang/String;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CaptureSDKTheme {
    private static final KSerializer<Object>[] $childSerializers;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final WebSdkThemeConfig config;
    private final Map<String, String> legacyConfig;
    private final String name;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKTheme$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKTheme;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<CaptureSDKTheme> serializer() {
            return CaptureSDKTheme$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{null, new LinkedHashMapSerializer(stringSerializer, stringSerializer), null};
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKTheme(int i, @SerialName("name") String str, @SerialName("legacyConfig") Map map, @SerialName("config") WebSdkThemeConfig webSdkThemeConfig, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, CaptureSDKTheme$$serializer.INSTANCE.getDescriptor());
        }
        this.name = str;
        this.legacyConfig = map;
        if ((i & 4) == 0) {
            this.config = null;
        } else {
            this.config = webSdkThemeConfig;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CaptureSDKTheme copy$default(CaptureSDKTheme captureSDKTheme, String str, Map map, WebSdkThemeConfig webSdkThemeConfig, int i, Object obj) {
        if ((i & 1) != 0) {
            str = captureSDKTheme.name;
        }
        if ((i & 2) != 0) {
            map = captureSDKTheme.legacyConfig;
        }
        if ((i & 4) != 0) {
            webSdkThemeConfig = captureSDKTheme.config;
        }
        return captureSDKTheme.copy(str, map, webSdkThemeConfig);
    }

    @SerialName("config")
    public static /* synthetic */ void getConfig$annotations() {
    }

    @SerialName("legacyConfig")
    public static /* synthetic */ void getLegacyConfig$annotations() {
    }

    @SerialName("name")
    public static /* synthetic */ void getName$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(CaptureSDKTheme self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.name);
        output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.legacyConfig);
        if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.config == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 2, WebSdkThemeConfig$$serializer.INSTANCE, self.config);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final Map<String, String> component2() {
        return this.legacyConfig;
    }

    /* renamed from: component3, reason: from getter */
    public final WebSdkThemeConfig getConfig() {
        return this.config;
    }

    public final CaptureSDKTheme copy(String name, Map<String, String> legacyConfig, WebSdkThemeConfig config) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(legacyConfig, "legacyConfig");
        return new CaptureSDKTheme(name, legacyConfig, config);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureSDKTheme)) {
            return false;
        }
        CaptureSDKTheme captureSDKTheme = (CaptureSDKTheme) other;
        return Intrinsics.areEqual(this.name, captureSDKTheme.name) && Intrinsics.areEqual(this.legacyConfig, captureSDKTheme.legacyConfig) && Intrinsics.areEqual(this.config, captureSDKTheme.config);
    }

    public final WebSdkThemeConfig getConfig() {
        return this.config;
    }

    public final Map<String, String> getLegacyConfig() {
        return this.legacyConfig;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + this.legacyConfig.hashCode()) * 31;
        WebSdkThemeConfig webSdkThemeConfig = this.config;
        return iHashCode + (webSdkThemeConfig == null ? 0 : webSdkThemeConfig.hashCode());
    }

    public String toString() {
        return "CaptureSDKTheme(name=" + this.name + ", legacyConfig=" + this.legacyConfig + ", config=" + this.config + ')';
    }

    public CaptureSDKTheme(String name, Map<String, String> legacyConfig, WebSdkThemeConfig webSdkThemeConfig) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(legacyConfig, "legacyConfig");
        this.name = name;
        this.legacyConfig = legacyConfig;
        this.config = webSdkThemeConfig;
    }

    public /* synthetic */ CaptureSDKTheme(String str, Map map, WebSdkThemeConfig webSdkThemeConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, map, (i & 4) != 0 ? null : webSdkThemeConfig);
    }
}
