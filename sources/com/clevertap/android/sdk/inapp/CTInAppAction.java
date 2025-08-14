package com.clevertap.android.sdk.inapp;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateInAppData;
import com.clevertap.android.sdk.utils.JsonUtilsKt;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: CTInAppAction.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0011\b\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u001eH\u0016R\"\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\"\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\b\u001a\u0004\u0018\u00010\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R^\u0010\u0013\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\u00122&\u0010\b\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\u0011j\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R \u0010\u0017\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00168G@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\b\u001a\u0004\u0018\u00010\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006%"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTInAppAction;", "Landroid/os/Parcelable;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "<set-?>", "", "actionUrl", "getActionUrl", "()Ljava/lang/String;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateInAppData;", "customTemplateInAppData", "getCustomTemplateInAppData", "()Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateInAppData;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "keyValues", "getKeyValues", "()Ljava/util/HashMap;", "", "shouldFallbackToSettings", "()Z", "Lcom/clevertap/android/sdk/inapp/InAppActionType;", "type", "getType", "()Lcom/clevertap/android/sdk/inapp/InAppActionType;", "describeContents", "", "setFieldsFromJson", "", "writeToParcel", "dest", "flags", "CREATOR", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CTInAppAction implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String actionUrl;
    private CustomTemplateInAppData customTemplateInAppData;
    private HashMap<String, String> keyValues;
    private boolean shouldFallbackToSettings;
    private InAppActionType type;

    public /* synthetic */ CTInAppAction(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public /* synthetic */ CTInAppAction(JSONObject jSONObject, DefaultConstructorMarker defaultConstructorMarker) {
        this(jSONObject);
    }

    @JvmStatic
    public static final CTInAppAction createCloseAction() {
        return INSTANCE.createCloseAction();
    }

    @JvmStatic
    public static final CTInAppAction createFromJson(JSONObject jSONObject) {
        return INSTANCE.createFromJson(jSONObject);
    }

    @JvmStatic
    public static final CTInAppAction createOpenUrlAction(String str) {
        return INSTANCE.createOpenUrlAction(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final String getActionUrl() {
        return this.actionUrl;
    }

    public final CustomTemplateInAppData getCustomTemplateInAppData() {
        return this.customTemplateInAppData;
    }

    public final HashMap<String, String> getKeyValues() {
        return this.keyValues;
    }

    public final InAppActionType getType() {
        return this.type;
    }

    /* renamed from: shouldFallbackToSettings, reason: from getter */
    public final boolean getShouldFallbackToSettings() {
        return this.shouldFallbackToSettings;
    }

    private CTInAppAction(Parcel parcel) {
        String string;
        this.type = (parcel == null || (string = parcel.readString()) == null) ? null : InAppActionType.INSTANCE.fromString(string);
        this.actionUrl = parcel != null ? parcel.readString() : null;
        HashMap<String, String> hashMap = parcel != null ? parcel.readHashMap(null) : null;
        this.keyValues = hashMap instanceof HashMap ? hashMap : null;
        this.customTemplateInAppData = parcel != null ? (CustomTemplateInAppData) parcel.readParcelable(CustomTemplateInAppData.class.getClassLoader()) : null;
    }

    private CTInAppAction(JSONObject jSONObject) {
        this((Parcel) null);
        setFieldsFromJson(jSONObject);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        InAppActionType inAppActionType = this.type;
        dest.writeString(inAppActionType != null ? inAppActionType.toString() : null);
        dest.writeString(this.actionUrl);
        dest.writeMap(this.keyValues);
        dest.writeParcelable(this.customTemplateInAppData, flags);
    }

    private final void setFieldsFromJson(JSONObject json) {
        String stringOrNull = JsonUtilsKt.getStringOrNull(json, "type");
        this.type = stringOrNull != null ? InAppActionType.INSTANCE.fromString(stringOrNull) : null;
        this.actionUrl = JsonUtilsKt.getStringOrNull(json, Constants.KEY_ANDROID);
        this.customTemplateInAppData = CustomTemplateInAppData.INSTANCE.createFromJson(json);
        this.shouldFallbackToSettings = json.optBoolean(Constants.KEY_FALLBACK_NOTIFICATION_SETTINGS);
        if (StringsKt.equals(Constants.KEY_KV, json.optString("type"), true) && json.has(Constants.KEY_KV)) {
            JSONObject jSONObjectOptJSONObject = json.optJSONObject(Constants.KEY_KV);
            HashMap<String, String> map = this.keyValues;
            if (map == null) {
                map = new HashMap<>();
            }
            if (jSONObjectOptJSONObject != null) {
                Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                Intrinsics.checkNotNullExpressionValue(itKeys, "keyValuesJson.keys()");
                while (itKeys.hasNext()) {
                    String key = itKeys.next();
                    String strOptString = jSONObjectOptJSONObject.optString(key);
                    if (strOptString.length() > 0) {
                        Intrinsics.checkNotNullExpressionValue(key, "key");
                        map.put(key, strOptString);
                    }
                }
                this.keyValues = map;
            }
        }
    }

    /* compiled from: CTInAppAction.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0007J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0007J\u001d\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/clevertap/android/sdk/inapp/CTInAppAction$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/clevertap/android/sdk/inapp/CTInAppAction;", "()V", "createCloseAction", "createFromJson", "json", "Lorg/json/JSONObject;", "createFromParcel", "parcel", "Landroid/os/Parcel;", "createOpenUrlAction", "url", "", "newArray", "", RRWebVideoEvent.JsonKeys.SIZE, "", "(I)[Lcom/clevertap/android/sdk/inapp/CTInAppAction;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.inapp.CTInAppAction$CREATOR, reason: from kotlin metadata */
    public static final class Companion implements Parcelable.Creator<CTInAppAction> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInAppAction createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CTInAppAction(parcel, (DefaultConstructorMarker) null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInAppAction[] newArray(int size) {
            return new CTInAppAction[size];
        }

        @JvmStatic
        public final CTInAppAction createFromJson(JSONObject json) {
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (json == null) {
                return null;
            }
            return new CTInAppAction(json, defaultConstructorMarker);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final CTInAppAction createOpenUrlAction(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            CTInAppAction cTInAppAction = new CTInAppAction((Parcel) null, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            cTInAppAction.type = InAppActionType.OPEN_URL;
            cTInAppAction.actionUrl = url;
            return cTInAppAction;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final CTInAppAction createCloseAction() {
            CTInAppAction cTInAppAction = new CTInAppAction((Parcel) null, (DefaultConstructorMarker) (0 == true ? 1 : 0));
            cTInAppAction.type = InAppActionType.CLOSE;
            return cTInAppAction;
        }
    }
}
