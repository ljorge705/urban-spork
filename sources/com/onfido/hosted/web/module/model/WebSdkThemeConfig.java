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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0081\b\u0018\u0000 -2\u00020\u0001:\u0002,-BI\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB5\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J>\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001J&\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*HÁ\u0001¢\u0006\u0002\b+R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0016\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0016\u0012\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0015¨\u0006."}, d2 = {"Lcom/onfido/hosted/web/module/model/WebSdkThemeConfig;", "", "seen1", "", "fontFamilyTitle", "", "fontWeightTitle", "fontFamilySubtitle", "fontWeightSubtitle", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)V", "getFontFamilySubtitle$annotations", "()V", "getFontFamilySubtitle", "()Ljava/lang/String;", "getFontFamilyTitle$annotations", "getFontFamilyTitle", "getFontWeightSubtitle$annotations", "getFontWeightSubtitle", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getFontWeightTitle$annotations", "getFontWeightTitle", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)Lcom/onfido/hosted/web/module/model/WebSdkThemeConfig;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$onfido_capture_sdk_core_release", "$serializer", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Serializable
/* loaded from: classes6.dex */
public final /* data */ class WebSdkThemeConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String fontFamilySubtitle;
    private final String fontFamilyTitle;
    private final Integer fontWeightSubtitle;
    private final Integer fontWeightTitle;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/onfido/hosted/web/module/model/WebSdkThemeConfig$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/onfido/hosted/web/module/model/WebSdkThemeConfig;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final KSerializer<WebSdkThemeConfig> serializer() {
            return WebSdkThemeConfig$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WebSdkThemeConfig() {
        this((String) null, (Integer) null, (String) null, (Integer) null, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ WebSdkThemeConfig copy$default(WebSdkThemeConfig webSdkThemeConfig, String str, Integer num, String str2, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = webSdkThemeConfig.fontFamilyTitle;
        }
        if ((i & 2) != 0) {
            num = webSdkThemeConfig.fontWeightTitle;
        }
        if ((i & 4) != 0) {
            str2 = webSdkThemeConfig.fontFamilySubtitle;
        }
        if ((i & 8) != 0) {
            num2 = webSdkThemeConfig.fontWeightSubtitle;
        }
        return webSdkThemeConfig.copy(str, num, str2, num2);
    }

    @SerialName("fontFamilySubtitle")
    public static /* synthetic */ void getFontFamilySubtitle$annotations() {
    }

    @SerialName("fontFamilyTitle")
    public static /* synthetic */ void getFontFamilyTitle$annotations() {
    }

    @SerialName("fontWeightSubtitle")
    public static /* synthetic */ void getFontWeightSubtitle$annotations() {
    }

    @SerialName("fontWeightTitle")
    public static /* synthetic */ void getFontWeightTitle$annotations() {
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$onfido_capture_sdk_core_release(WebSdkThemeConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.fontFamilyTitle != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.fontFamilyTitle);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.fontWeightTitle != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.fontWeightTitle);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.fontFamilySubtitle != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.fontFamilySubtitle);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.fontWeightSubtitle == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.fontWeightSubtitle);
    }

    /* renamed from: component1, reason: from getter */
    public final String getFontFamilyTitle() {
        return this.fontFamilyTitle;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getFontWeightTitle() {
        return this.fontWeightTitle;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFontFamilySubtitle() {
        return this.fontFamilySubtitle;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getFontWeightSubtitle() {
        return this.fontWeightSubtitle;
    }

    public final WebSdkThemeConfig copy(String fontFamilyTitle, Integer fontWeightTitle, String fontFamilySubtitle, Integer fontWeightSubtitle) {
        return new WebSdkThemeConfig(fontFamilyTitle, fontWeightTitle, fontFamilySubtitle, fontWeightSubtitle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WebSdkThemeConfig)) {
            return false;
        }
        WebSdkThemeConfig webSdkThemeConfig = (WebSdkThemeConfig) other;
        return Intrinsics.areEqual(this.fontFamilyTitle, webSdkThemeConfig.fontFamilyTitle) && Intrinsics.areEqual(this.fontWeightTitle, webSdkThemeConfig.fontWeightTitle) && Intrinsics.areEqual(this.fontFamilySubtitle, webSdkThemeConfig.fontFamilySubtitle) && Intrinsics.areEqual(this.fontWeightSubtitle, webSdkThemeConfig.fontWeightSubtitle);
    }

    public final String getFontFamilySubtitle() {
        return this.fontFamilySubtitle;
    }

    public final String getFontFamilyTitle() {
        return this.fontFamilyTitle;
    }

    public final Integer getFontWeightSubtitle() {
        return this.fontWeightSubtitle;
    }

    public final Integer getFontWeightTitle() {
        return this.fontWeightTitle;
    }

    public int hashCode() {
        String str = this.fontFamilyTitle;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.fontWeightTitle;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.fontFamilySubtitle;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num2 = this.fontWeightSubtitle;
        return iHashCode3 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "WebSdkThemeConfig(fontFamilyTitle=" + this.fontFamilyTitle + ", fontWeightTitle=" + this.fontWeightTitle + ", fontFamilySubtitle=" + this.fontFamilySubtitle + ", fontWeightSubtitle=" + this.fontWeightSubtitle + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ WebSdkThemeConfig(int i, @SerialName("fontFamilyTitle") String str, @SerialName("fontWeightTitle") Integer num, @SerialName("fontFamilySubtitle") String str2, @SerialName("fontWeightSubtitle") Integer num2, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.fontFamilyTitle = null;
        } else {
            this.fontFamilyTitle = str;
        }
        if ((i & 2) == 0) {
            this.fontWeightTitle = null;
        } else {
            this.fontWeightTitle = num;
        }
        if ((i & 4) == 0) {
            this.fontFamilySubtitle = null;
        } else {
            this.fontFamilySubtitle = str2;
        }
        if ((i & 8) == 0) {
            this.fontWeightSubtitle = null;
        } else {
            this.fontWeightSubtitle = num2;
        }
    }

    public WebSdkThemeConfig(String str, Integer num, String str2, Integer num2) {
        this.fontFamilyTitle = str;
        this.fontWeightTitle = num;
        this.fontFamilySubtitle = str2;
        this.fontWeightSubtitle = num2;
    }

    public /* synthetic */ WebSdkThemeConfig(String str, Integer num, String str2, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num2);
    }
}
