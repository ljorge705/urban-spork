package com.clevertap.android.sdk.inapp;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.inapp.CTLocalInApp;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.OperatingSystem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CTLocalInApp.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00042\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp;", "", "()V", "Builder", "Companion", "InAppType", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CTLocalInApp {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String FALLBACK_TO_NOTIFICATION_SETTINGS = "fallbackToNotificationSettings";
    public static final String IS_LOCAL_INAPP = "isLocalInApp";

    @JvmStatic
    public static final Builder builder() {
        return INSTANCE.builder();
    }

    private CTLocalInApp() {
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ALERT' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* compiled from: CTLocalInApp.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$InAppType;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "ALERT", "HALF_INTERSTITIAL", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class InAppType {
        private static final /* synthetic */ InAppType[] $VALUES;
        public static final InAppType ALERT;
        public static final InAppType HALF_INTERSTITIAL;
        private final String type;

        private static final /* synthetic */ InAppType[] $values() {
            return new InAppType[]{ALERT, HALF_INTERSTITIAL};
        }

        public static InAppType valueOf(String str) {
            return (InAppType) Enum.valueOf(InAppType.class, str);
        }

        public static InAppType[] values() {
            return (InAppType[]) $VALUES.clone();
        }

        public final String getType() {
            return this.type;
        }

        private InAppType(String str, int i, String str2) {
            this.type = str2;
        }

        static {
            String string = CTInAppType.CTInAppTypeAlert.toString();
            Intrinsics.checkNotNullExpressionValue(string, "CTInAppTypeAlert.toString()");
            ALERT = new InAppType("ALERT", 0, string);
            String string2 = CTInAppType.CTInAppTypeHalfInterstitial.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "CTInAppTypeHalfInterstitial.toString()");
            HALF_INTERSTITIAL = new InAppType("HALF_INTERSTITIAL", 1, string2);
            $VALUES = $values();
        }
    }

    /* compiled from: CTLocalInApp.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Companion;", "", "()V", "FALLBACK_TO_NOTIFICATION_SETTINGS", "", "IS_LOCAL_INAPP", "builder", "Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder builder() {
            return new Builder();
        }
    }

    /* compiled from: CTLocalInApp.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0006\t\n\u000b\f\r\u000eB\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder;", "", "()V", "jsonObject", "Lorg/json/JSONObject;", "setInAppType", "Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder1;", "inAppType", "Lcom/clevertap/android/sdk/inapp/CTLocalInApp$InAppType;", "Builder1", "Builder2", "Builder3", "Builder4", "Builder5", "Builder6", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Builder {
        private JSONObject jsonObject = new JSONObject();

        public final Builder1 setInAppType(InAppType inAppType) throws JSONException {
            Intrinsics.checkNotNullParameter(inAppType, "inAppType");
            JSONObject jSONObject = this.jsonObject;
            jSONObject.put("type", inAppType.getType());
            jSONObject.put(CTLocalInApp.IS_LOCAL_INAPP, true);
            jSONObject.put(Constants.KEY_HIDE_CLOSE, true);
            return new Builder1(jSONObject);
        }

        /* compiled from: CTLocalInApp.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder1;", "", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "setTitleText", "Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder2;", "titleText", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Builder1 {
            private JSONObject jsonObject;

            public Builder1(JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.jsonObject = jsonObject;
            }

            public final Builder2 setTitleText(String titleText) throws JSONException {
                Intrinsics.checkNotNullParameter(titleText, "titleText");
                JSONObject jSONObject = this.jsonObject;
                jSONObject.put("title", new JSONObject().put("text", titleText));
                return new Builder2(jSONObject);
            }
        }

        /* compiled from: CTLocalInApp.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder2;", "", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "setMessageText", "Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder3;", "messageText", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Builder2 {
            private JSONObject jsonObject;

            public Builder2(JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.jsonObject = jsonObject;
            }

            public final Builder3 setMessageText(String messageText) throws JSONException {
                Intrinsics.checkNotNullParameter(messageText, "messageText");
                JSONObject jSONObject = this.jsonObject;
                jSONObject.put("message", new JSONObject().put("text", messageText));
                return new Builder3(jSONObject);
            }
        }

        /* compiled from: CTLocalInApp.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder3;", "", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "followDeviceOrientation", "Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder4;", "deviceOrientation", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Builder3 {
            private JSONObject jsonObject;

            public Builder3(JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.jsonObject = jsonObject;
            }

            public final Builder4 followDeviceOrientation(boolean deviceOrientation) throws JSONException {
                JSONObject jSONObject = this.jsonObject;
                jSONObject.put(Constants.KEY_PORTRAIT, true);
                jSONObject.put(Constants.KEY_LANDSCAPE, deviceOrientation);
                return new Builder4(jSONObject);
            }
        }

        /* compiled from: CTLocalInApp.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder4;", "", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "setPositiveBtnText", "Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder5;", "positiveBtnText", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Builder4 {
            private JSONObject jsonObject;

            public Builder4(JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.jsonObject = jsonObject;
            }

            public final Builder5 setPositiveBtnText(String positiveBtnText) throws JSONException {
                Intrinsics.checkNotNullParameter(positiveBtnText, "positiveBtnText");
                JSONObject jSONObject = this.jsonObject;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("text", positiveBtnText);
                jSONObject2.put(Constants.KEY_RADIUS, ExifInterface.GPS_MEASUREMENT_2D);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("type", InAppActionType.CLOSE);
                Unit unit = Unit.INSTANCE;
                jSONObject2.put(Constants.KEY_ACTIONS, jSONObject3);
                jSONObject.put(Constants.KEY_BUTTONS, new JSONArray().put(0, jSONObject2));
                return new Builder5(jSONObject);
            }
        }

        /* compiled from: CTLocalInApp.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder5;", "", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "setNegativeBtnText", "Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder6;", "negativeBtnText", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Builder5 {
            private JSONObject jsonObject;

            public Builder5(JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.jsonObject = jsonObject;
            }

            public final Builder6 setNegativeBtnText(String negativeBtnText) throws JSONException {
                Intrinsics.checkNotNullParameter(negativeBtnText, "negativeBtnText");
                JSONObject jSONObject = this.jsonObject;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("text", negativeBtnText);
                jSONObject2.put(Constants.KEY_RADIUS, ExifInterface.GPS_MEASUREMENT_2D);
                jSONObject.getJSONArray(Constants.KEY_BUTTONS).put(1, jSONObject2);
                return new Builder6(jSONObject);
            }
        }

        /* compiled from: CTLocalInApp.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\u0003J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0007J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0007J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0007J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTLocalInApp$Builder$Builder6;", "", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "updateActionButtonArray", "Lkotlin/Function2;", "", "", OperatingSystem.JsonKeys.BUILD, "setBackgroundColor", ViewProps.BACKGROUND_COLOR, "setBtnBackgroundColor", "btnBackgroundColor", "setBtnBorderColor", "btnBorderColor", "setBtnBorderRadius", "btnBorderRadius", "setBtnTextColor", "btnTextColor", "setFallbackToSettings", "fallbackToSettings", "", "setImageUrl", "imageUrl", "setMessageTextColor", "messageTextColor", "setTitleTextColor", "titleTextColor", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Builder6 {
            private JSONObject jsonObject;
            private final Function2<String, String, Unit> updateActionButtonArray;

            /* renamed from: build, reason: from getter */
            public final JSONObject getJsonObject() {
                return this.jsonObject;
            }

            public Builder6(JSONObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                this.jsonObject = jsonObject;
                this.updateActionButtonArray = new Function2<String, String, Unit>() { // from class: com.clevertap.android.sdk.inapp.CTLocalInApp$Builder$Builder6$updateActionButtonArray$1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) throws JSONException {
                        invoke2(str, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String key, String value) throws JSONException {
                        Intrinsics.checkNotNullParameter(key, "key");
                        Intrinsics.checkNotNullParameter(value, "value");
                        Integer[] numArr = {0, 1};
                        CTLocalInApp.Builder.Builder6 builder6 = this.this$0;
                        for (int i = 0; i < 2; i++) {
                            builder6.jsonObject.getJSONArray(Constants.KEY_BUTTONS).getJSONObject(numArr[i].intValue()).put(key, value);
                        }
                    }
                };
            }

            public final Builder6 setFallbackToSettings(boolean fallbackToSettings) {
                this.jsonObject.put(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS, fallbackToSettings);
                return this;
            }

            public final Builder6 setBackgroundColor(String backgroundColor) {
                Intrinsics.checkNotNullParameter(backgroundColor, "backgroundColor");
                this.jsonObject.put(Constants.KEY_BG, backgroundColor);
                return this;
            }

            public final Builder6 setImageUrl(String imageUrl) {
                Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("url", imageUrl);
                jSONObject.put("content_type", MimeTypes.BASE_TYPE_IMAGE);
                JSONObject jSONObject2 = this.jsonObject;
                jSONObject2.put(Constants.KEY_MEDIA, jSONObject);
                if (jSONObject2.getBoolean(Constants.KEY_LANDSCAPE)) {
                    jSONObject2.put(Constants.KEY_MEDIA_LANDSCAPE, jSONObject);
                }
                return this;
            }

            public final Builder6 setTitleTextColor(String titleTextColor) {
                Intrinsics.checkNotNullParameter(titleTextColor, "titleTextColor");
                this.jsonObject.getJSONObject("title").put("color", titleTextColor);
                return this;
            }

            public final Builder6 setMessageTextColor(String messageTextColor) {
                Intrinsics.checkNotNullParameter(messageTextColor, "messageTextColor");
                this.jsonObject.getJSONObject("message").put("color", messageTextColor);
                return this;
            }

            public final Builder6 setBtnTextColor(String btnTextColor) {
                Intrinsics.checkNotNullParameter(btnTextColor, "btnTextColor");
                this.updateActionButtonArray.invoke("color", btnTextColor);
                return this;
            }

            public final Builder6 setBtnBackgroundColor(String btnBackgroundColor) {
                Intrinsics.checkNotNullParameter(btnBackgroundColor, "btnBackgroundColor");
                this.updateActionButtonArray.invoke(Constants.KEY_BG, btnBackgroundColor);
                return this;
            }

            public final Builder6 setBtnBorderColor(String btnBorderColor) {
                Intrinsics.checkNotNullParameter(btnBorderColor, "btnBorderColor");
                this.updateActionButtonArray.invoke(Constants.KEY_BORDER, btnBorderColor);
                return this;
            }

            public final Builder6 setBtnBorderRadius(String btnBorderRadius) {
                Intrinsics.checkNotNullParameter(btnBorderRadius, "btnBorderRadius");
                this.updateActionButtonArray.invoke(Constants.KEY_RADIUS, btnBorderRadius);
                return this;
            }
        }
    }
}
