package com.onfido.hosted.web.module.model;

import com.clevertap.android.sdk.Constants;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 %2\u00020\u0001:\u0002$%B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J&\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"HÁ\u0001¢\u0006\u0002\b#R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKJsConfig;", "", "seen1", "", "bootstrap", "Lcom/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig;", "context", "Lcom/onfido/hosted/web/module/model/CaptureSDKContext;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig;Lcom/onfido/hosted/web/module/model/CaptureSDKContext;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig;Lcom/onfido/hosted/web/module/model/CaptureSDKContext;)V", "getBootstrap$annotations", "()V", "getBootstrap", "()Lcom/onfido/hosted/web/module/model/CaptureSDKBootstrapConfig;", "getContext$annotations", "getContext", "()Lcom/onfido/hosted/web/module/model/CaptureSDKContext;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CaptureSDKJsConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CaptureSDKBootstrapConfig bootstrap;
    private final CaptureSDKContext context;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKJsConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKJsConfig;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<CaptureSDKJsConfig> serializer() {
            return CaptureSDKJsConfig$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKJsConfig(int i, @SerialName("bootstrap") CaptureSDKBootstrapConfig captureSDKBootstrapConfig, @SerialName("context") CaptureSDKContext captureSDKContext, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, CaptureSDKJsConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.bootstrap = captureSDKBootstrapConfig;
        this.context = captureSDKContext;
    }

    public static /* synthetic */ CaptureSDKJsConfig copy$default(CaptureSDKJsConfig captureSDKJsConfig, CaptureSDKBootstrapConfig captureSDKBootstrapConfig, CaptureSDKContext captureSDKContext, int i, Object obj) {
        if ((i & 1) != 0) {
            captureSDKBootstrapConfig = captureSDKJsConfig.bootstrap;
        }
        if ((i & 2) != 0) {
            captureSDKContext = captureSDKJsConfig.context;
        }
        return captureSDKJsConfig.copy(captureSDKBootstrapConfig, captureSDKContext);
    }

    @SerialName("bootstrap")
    public static /* synthetic */ void getBootstrap$annotations() {
    }

    @SerialName("context")
    public static /* synthetic */ void getContext$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(CaptureSDKJsConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, CaptureSDKBootstrapConfig$$serializer.INSTANCE, self.bootstrap);
        output.encodeSerializableElement(serialDesc, 1, CaptureSDKContext$$serializer.INSTANCE, self.context);
    }

    /* renamed from: component1, reason: from getter */
    public final CaptureSDKBootstrapConfig getBootstrap() {
        return this.bootstrap;
    }

    /* renamed from: component2, reason: from getter */
    public final CaptureSDKContext getContext() {
        return this.context;
    }

    public final CaptureSDKJsConfig copy(CaptureSDKBootstrapConfig bootstrap, CaptureSDKContext context) {
        Intrinsics.checkNotNullParameter(bootstrap, "bootstrap");
        Intrinsics.checkNotNullParameter(context, "context");
        return new CaptureSDKJsConfig(bootstrap, context);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureSDKJsConfig)) {
            return false;
        }
        CaptureSDKJsConfig captureSDKJsConfig = (CaptureSDKJsConfig) other;
        return Intrinsics.areEqual(this.bootstrap, captureSDKJsConfig.bootstrap) && Intrinsics.areEqual(this.context, captureSDKJsConfig.context);
    }

    public final CaptureSDKBootstrapConfig getBootstrap() {
        return this.bootstrap;
    }

    public final CaptureSDKContext getContext() {
        return this.context;
    }

    public int hashCode() {
        return (this.bootstrap.hashCode() * 31) + this.context.hashCode();
    }

    public String toString() {
        return "CaptureSDKJsConfig(bootstrap=" + this.bootstrap + ", context=" + this.context + ')';
    }

    public CaptureSDKJsConfig(CaptureSDKBootstrapConfig bootstrap, CaptureSDKContext context) {
        Intrinsics.checkNotNullParameter(bootstrap, "bootstrap");
        Intrinsics.checkNotNullParameter(context, "context");
        this.bootstrap = bootstrap;
        this.context = context;
    }
}
