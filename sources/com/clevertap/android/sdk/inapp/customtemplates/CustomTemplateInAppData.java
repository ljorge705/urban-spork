package com.clevertap.android.sdk.inapp.customtemplates;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.inapp.CTInAppType;
import com.clevertap.android.sdk.utils.JsonUtilsKt;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CustomTemplateInAppData.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\b\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0011\b\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0015\u001a\u00020\u0000J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0013\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u001c\u0010\u001c\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\"J\b\u0010#\u001a\u00020\u0017H\u0016J\u0010\u0010$\u001a\u00020 2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010%\u001a\u00020 2\u0006\u0010\u0002\u001a\u00020\u0003J\u0018\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0017H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006*"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateInAppData;", "Landroid/os/Parcelable;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "args", CustomTemplateInAppData.KEY_IS_ACTION, "", "()Z", "setAction", "(Z)V", CustomTemplateInAppData.KEY_TEMPLATE_DESCRIPTION, "", CustomTemplateInAppData.KEY_TEMPLATE_ID, "<set-?>", CustomTemplateInAppData.KEY_TEMPLATE_NAME, "getTemplateName", "()Ljava/lang/String;", Constants.COPY_TYPE, "describeContents", "", "equals", "other", "", "getArguments", "getFileArgsUrls", "", "templatesManager", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatesManager;", "", "filesList", "", "hashCode", "setFieldsFromJson", "writeFieldsToJson", "writeToParcel", "dest", "flags", "CREATOR", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CustomTemplateInAppData implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_IS_ACTION = "isAction";
    private static final String KEY_TEMPLATE_DESCRIPTION = "templateDescription";
    private static final String KEY_TEMPLATE_ID = "templateId";
    private static final String KEY_TEMPLATE_NAME = "templateName";
    private static final String KEY_VARS = "vars";
    private JSONObject args;
    private boolean isAction;
    private String templateDescription;
    private String templateId;
    private String templateName;

    /* compiled from: CustomTemplateInAppData.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TemplateArgumentType.values().length];
            try {
                iArr[TemplateArgumentType.FILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TemplateArgumentType.ACTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ CustomTemplateInAppData(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public /* synthetic */ CustomTemplateInAppData(JSONObject jSONObject, DefaultConstructorMarker defaultConstructorMarker) {
        this(jSONObject);
    }

    @JvmStatic
    public static final CustomTemplateInAppData createFromJson(JSONObject jSONObject) {
        return INSTANCE.createFromJson(jSONObject);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final String getTemplateName() {
        return this.templateName;
    }

    /* renamed from: isAction, reason: from getter */
    public final boolean getIsAction() {
        return this.isAction;
    }

    public final void setAction(boolean z) {
        this.isAction = z;
    }

    private CustomTemplateInAppData(Parcel parcel) {
        this.templateName = parcel != null ? parcel.readString() : null;
        boolean z = false;
        if (parcel != null && parcel.readByte() == 0) {
            z = true;
        }
        this.isAction = true ^ z;
        this.templateId = parcel != null ? parcel.readString() : null;
        this.templateDescription = parcel != null ? parcel.readString() : null;
        this.args = parcel != null ? JsonUtilsKt.readJson(parcel) : null;
    }

    private CustomTemplateInAppData(JSONObject jSONObject) {
        this((Parcel) null);
        setFieldsFromJson(jSONObject);
    }

    public final JSONObject getArguments() {
        JSONObject jSONObject = this.args;
        if (jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        CTXtensions.copyFrom(jSONObject2, jSONObject);
        return jSONObject2;
    }

    public final List<String> getFileArgsUrls(TemplatesManager templatesManager) {
        Intrinsics.checkNotNullParameter(templatesManager, "templatesManager");
        ArrayList arrayList = new ArrayList();
        getFileArgsUrls(templatesManager, arrayList);
        return arrayList;
    }

    public final void getFileArgsUrls(TemplatesManager templatesManager, List<String> filesList) {
        CustomTemplate template;
        JSONObject jSONObject;
        JSONObject jSONObjectOptJSONObject;
        CustomTemplateInAppData customTemplateInAppDataCreateFromJson;
        Intrinsics.checkNotNullParameter(templatesManager, "templatesManager");
        Intrinsics.checkNotNullParameter(filesList, "filesList");
        String str = this.templateName;
        if (str == null || (template = templatesManager.getTemplate(str)) == null || (jSONObject = this.args) == null) {
            return;
        }
        for (TemplateArgument templateArgument : template.getArgs$clevertap_core_release()) {
            int i = WhenMappings.$EnumSwitchMapping$0[templateArgument.getType().ordinal()];
            if (i == 1) {
                String stringOrNull = JsonUtilsKt.getStringOrNull(jSONObject, templateArgument.getName());
                if (stringOrNull != null) {
                    filesList.add(stringOrNull);
                }
            } else if (i == 2 && (jSONObjectOptJSONObject = jSONObject.optJSONObject(templateArgument.getName())) != null && (customTemplateInAppDataCreateFromJson = INSTANCE.createFromJson(jSONObjectOptJSONObject)) != null) {
                customTemplateInAppDataCreateFromJson.getFileArgsUrls(templatesManager, filesList);
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.templateName);
        dest.writeByte(this.isAction ? (byte) 1 : (byte) 0);
        dest.writeString(this.templateId);
        dest.writeString(this.templateDescription);
        JsonUtilsKt.writeJson(dest, this.args);
    }

    public final void writeFieldsToJson(JSONObject json) throws JSONException {
        Intrinsics.checkNotNullParameter(json, "json");
        json.put(KEY_TEMPLATE_NAME, this.templateName);
        json.put(KEY_IS_ACTION, this.isAction);
        json.put(KEY_TEMPLATE_ID, this.templateId);
        json.put(KEY_TEMPLATE_DESCRIPTION, this.templateDescription);
        json.put("vars", this.args);
    }

    public final CustomTemplateInAppData copy() {
        CustomTemplateInAppData customTemplateInAppData = new CustomTemplateInAppData((Parcel) null);
        customTemplateInAppData.templateName = this.templateName;
        customTemplateInAppData.isAction = this.isAction;
        customTemplateInAppData.templateId = this.templateId;
        customTemplateInAppData.templateDescription = this.templateDescription;
        JSONObject jSONObject = this.args;
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            CTXtensions.copyFrom(jSONObject2, jSONObject);
            customTemplateInAppData.args = jSONObject2;
        }
        return customTemplateInAppData;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateInAppData");
        CustomTemplateInAppData customTemplateInAppData = (CustomTemplateInAppData) other;
        if (!Intrinsics.areEqual(this.templateName, customTemplateInAppData.templateName) || this.isAction != customTemplateInAppData.isAction || !Intrinsics.areEqual(this.templateId, customTemplateInAppData.templateId) || !Intrinsics.areEqual(this.templateDescription, customTemplateInAppData.templateDescription)) {
            return false;
        }
        JSONObject jSONObject = this.args;
        String string = jSONObject != null ? jSONObject.toString() : null;
        JSONObject jSONObject2 = customTemplateInAppData.args;
        return Intrinsics.areEqual(string, jSONObject2 != null ? jSONObject2.toString() : null);
    }

    public int hashCode() {
        String string;
        String str = this.templateName;
        int iHashCode = 0;
        int iHashCode2 = (((str != null ? str.hashCode() : 0) * 31) + Boolean.hashCode(this.isAction)) * 31;
        String str2 = this.templateId;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.templateDescription;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        JSONObject jSONObject = this.args;
        if (jSONObject != null && (string = jSONObject.toString()) != null) {
            iHashCode = string.hashCode();
        }
        return iHashCode4 + iHashCode;
    }

    private final void setFieldsFromJson(JSONObject json) {
        this.templateName = JsonUtilsKt.getStringOrNull(json, KEY_TEMPLATE_NAME);
        this.isAction = json.optBoolean(KEY_IS_ACTION);
        this.templateId = JsonUtilsKt.getStringOrNull(json, KEY_TEMPLATE_ID);
        this.templateDescription = JsonUtilsKt.getStringOrNull(json, KEY_TEMPLATE_DESCRIPTION);
        this.args = json.optJSONObject("vars");
    }

    /* compiled from: CustomTemplateInAppData.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0014\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001d\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateInAppData$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateInAppData;", "()V", "KEY_IS_ACTION", "", "KEY_TEMPLATE_DESCRIPTION", "KEY_TEMPLATE_ID", "KEY_TEMPLATE_NAME", "KEY_VARS", "createFromJson", Constants.INAPP_KEY, "Lorg/json/JSONObject;", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", RRWebVideoEvent.JsonKeys.SIZE, "", "(I)[Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateInAppData;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateInAppData$CREATOR, reason: from kotlin metadata */
    public static final class Companion implements Parcelable.Creator<CustomTemplateInAppData> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomTemplateInAppData createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomTemplateInAppData(parcel, (DefaultConstructorMarker) null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomTemplateInAppData[] newArray(int size) {
            return new CustomTemplateInAppData[size];
        }

        @JvmStatic
        public final CustomTemplateInAppData createFromJson(JSONObject inApp) {
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (inApp == null) {
                return null;
            }
            if (CTInAppType.CTInAppTypeCustomCodeTemplate == CTInAppType.fromString(inApp.optString("type"))) {
                return new CustomTemplateInAppData(inApp, defaultConstructorMarker);
            }
            return null;
        }
    }
}
