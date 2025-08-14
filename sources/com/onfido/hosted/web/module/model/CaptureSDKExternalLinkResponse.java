package com.onfido.hosted.web.module.model;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.events.TouchesHelper;
import io.sentry.protocol.Browser;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0081\b\u0018\u0000 $2\u00020\u0001:\u0003#$%B1\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u001f\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J&\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!HÁ\u0001¢\u0006\u0002\b\"R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse;", "", "seen1", "", "url", "", TouchesHelper.TARGET_KEY, "Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;)V", "getTarget$annotations", "()V", "getTarget", "()Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;", "getUrl$annotations", "getUrl", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "LinkTarget", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class CaptureSDKExternalLinkResponse {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final LinkTarget target;
    private final String url;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<CaptureSDKExternalLinkResponse> serializer() {
            return CaptureSDKExternalLinkResponse$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0087\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\t\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\u000b"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "BROWSER", "OVERLAY", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @Serializable
    public static final class LinkTarget {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ LinkTarget[] $VALUES;
        private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        private final String value;

        @SerialName(Browser.TYPE)
        public static final LinkTarget BROWSER = new LinkTarget("BROWSER", 0, Browser.TYPE);

        @SerialName("overlay")
        public static final LinkTarget OVERLAY = new LinkTarget("OVERLAY", 1, "overlay");

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/CaptureSDKExternalLinkResponse$LinkTarget;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            private final /* synthetic */ KSerializer get$cachedSerializer() {
                return (KSerializer) LinkTarget.$cachedSerializer$delegate.getValue();
            }

            public final KSerializer<LinkTarget> serializer() {
                return get$cachedSerializer();
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        private static final /* synthetic */ LinkTarget[] $values() {
            return new LinkTarget[]{BROWSER, OVERLAY};
        }

        static {
            LinkTarget[] linkTargetArr$values = $values();
            $VALUES = linkTargetArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(linkTargetArr$values);
            INSTANCE = new Companion(null);
            $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: com.onfido.hosted.web.module.model.CaptureSDKExternalLinkResponse.LinkTarget.Companion.1
                @Override // kotlin.jvm.functions.Function0
                public final KSerializer<Object> invoke() {
                    return CaptureSDKExternalLinkResponse$LinkTarget$$serializer.INSTANCE;
                }
            });
        }

        private LinkTarget(String str, int i, String str2) {
            this.value = str2;
        }

        public static EnumEntries<LinkTarget> getEntries() {
            return $ENTRIES;
        }

        public static LinkTarget valueOf(String str) {
            return (LinkTarget) Enum.valueOf(LinkTarget.class, str);
        }

        public static LinkTarget[] values() {
            return (LinkTarget[]) $VALUES.clone();
        }

        public final String getValue() {
            return this.value;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CaptureSDKExternalLinkResponse(int i, @SerialName("url") String str, @SerialName(TouchesHelper.TARGET_KEY) LinkTarget linkTarget, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, CaptureSDKExternalLinkResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.url = str;
        this.target = linkTarget;
    }

    public static /* synthetic */ CaptureSDKExternalLinkResponse copy$default(CaptureSDKExternalLinkResponse captureSDKExternalLinkResponse, String str, LinkTarget linkTarget, int i, Object obj) {
        if ((i & 1) != 0) {
            str = captureSDKExternalLinkResponse.url;
        }
        if ((i & 2) != 0) {
            linkTarget = captureSDKExternalLinkResponse.target;
        }
        return captureSDKExternalLinkResponse.copy(str, linkTarget);
    }

    @SerialName(TouchesHelper.TARGET_KEY)
    public static /* synthetic */ void getTarget$annotations() {
    }

    @SerialName("url")
    public static /* synthetic */ void getUrl$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(CaptureSDKExternalLinkResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.url);
        output.encodeNullableSerializableElement(serialDesc, 1, CaptureSDKExternalLinkResponse$LinkTarget$$serializer.INSTANCE, self.target);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final LinkTarget getTarget() {
        return this.target;
    }

    public final CaptureSDKExternalLinkResponse copy(String url, LinkTarget target) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new CaptureSDKExternalLinkResponse(url, target);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptureSDKExternalLinkResponse)) {
            return false;
        }
        CaptureSDKExternalLinkResponse captureSDKExternalLinkResponse = (CaptureSDKExternalLinkResponse) other;
        return Intrinsics.areEqual(this.url, captureSDKExternalLinkResponse.url) && this.target == captureSDKExternalLinkResponse.target;
    }

    public final LinkTarget getTarget() {
        return this.target;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iHashCode = this.url.hashCode() * 31;
        LinkTarget linkTarget = this.target;
        return iHashCode + (linkTarget == null ? 0 : linkTarget.hashCode());
    }

    public String toString() {
        return "CaptureSDKExternalLinkResponse(url=" + this.url + ", target=" + this.target + ')';
    }

    public CaptureSDKExternalLinkResponse(String url, LinkTarget linkTarget) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        this.target = linkTarget;
    }
}
