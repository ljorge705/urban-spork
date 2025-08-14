package com.clevertap.android.sdk.displayunits.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CleverTapDisplayUnitContent implements Parcelable {
    public static final Parcelable.Creator<CleverTapDisplayUnitContent> CREATOR = new Parcelable.Creator<CleverTapDisplayUnitContent>() { // from class: com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleverTapDisplayUnitContent createFromParcel(Parcel parcel) {
            return new CleverTapDisplayUnitContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleverTapDisplayUnitContent[] newArray(int i) {
            return new CleverTapDisplayUnitContent[i];
        }
    };
    private String actionUrl;
    private String contentType;
    private String error;
    private String icon;
    private String media;
    private String message;
    private String messageColor;
    private String posterUrl;
    private String title;
    private String titleColor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getActionUrl() {
        return this.actionUrl;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getError() {
        return this.error;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getMedia() {
        return this.media;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMessageColor() {
        return this.messageColor;
    }

    public String getPosterUrl() {
        return this.posterUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleColor() {
        return this.titleColor;
    }

    void setContentType(String str) {
        this.contentType = str;
    }

    private CleverTapDisplayUnitContent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.title = str;
        this.titleColor = str2;
        this.message = str3;
        this.messageColor = str4;
        this.icon = str5;
        this.media = str6;
        this.contentType = str7;
        this.posterUrl = str8;
        this.actionUrl = str9;
        this.error = str10;
    }

    private CleverTapDisplayUnitContent(Parcel parcel) {
        this.title = parcel.readString();
        this.titleColor = parcel.readString();
        this.message = parcel.readString();
        this.messageColor = parcel.readString();
        this.icon = parcel.readString();
        this.media = parcel.readString();
        this.contentType = parcel.readString();
        this.posterUrl = parcel.readString();
        this.actionUrl = parcel.readString();
        this.error = parcel.readString();
    }

    public boolean mediaIsAudio() {
        String str = this.contentType;
        return (str == null || this.media == null || !str.startsWith(MimeTypes.BASE_TYPE_AUDIO)) ? false : true;
    }

    public boolean mediaIsGIF() {
        String str = this.contentType;
        return (str == null || this.media == null || !str.equals("image/gif")) ? false : true;
    }

    public boolean mediaIsImage() {
        String str = this.contentType;
        return (str == null || this.media == null || !str.startsWith(MimeTypes.BASE_TYPE_IMAGE) || this.contentType.equals("image/gif")) ? false : true;
    }

    public boolean mediaIsVideo() {
        String str = this.contentType;
        return (str == null || this.media == null || !str.startsWith("video")) ? false : true;
    }

    public String toString() {
        return "[ title:" + this.title + ", titleColor:" + this.titleColor + " message:" + this.message + ", messageColor:" + this.messageColor + ", media:" + this.media + ", contentType:" + this.contentType + ", posterUrl:" + this.posterUrl + ", actionUrl:" + this.actionUrl + ", icon:" + this.icon + ", error:" + this.error + " ]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.titleColor);
        parcel.writeString(this.message);
        parcel.writeString(this.messageColor);
        parcel.writeString(this.icon);
        parcel.writeString(this.media);
        parcel.writeString(this.contentType);
        parcel.writeString(this.posterUrl);
        parcel.writeString(this.actionUrl);
        parcel.writeString(this.error);
    }

    static CleverTapDisplayUnitContent toContent(JSONObject jSONObject) throws JSONException {
        String str;
        String string;
        String str2;
        String string2;
        String string3;
        String str3;
        String str4;
        String string4;
        try {
            JSONObject jSONObject2 = jSONObject.has("title") ? jSONObject.getJSONObject("title") : null;
            String string5 = "";
            if (jSONObject2 != null) {
                String string6 = jSONObject2.has("text") ? jSONObject2.getString("text") : "";
                string = jSONObject2.has("color") ? jSONObject2.getString("color") : "";
                str = string6;
            } else {
                str = "";
                string = str;
            }
            JSONObject jSONObject3 = jSONObject.has("message") ? jSONObject.getJSONObject("message") : null;
            if (jSONObject3 != null) {
                String string7 = jSONObject3.has("text") ? jSONObject3.getString("text") : "";
                string2 = jSONObject3.has("color") ? jSONObject3.getString("color") : "";
                str2 = string7;
            } else {
                str2 = "";
                string2 = str2;
            }
            JSONObject jSONObject4 = jSONObject.has(Constants.KEY_ICON) ? jSONObject.getJSONObject(Constants.KEY_ICON) : null;
            if (jSONObject4 != null) {
                string3 = jSONObject4.has("url") ? jSONObject4.getString("url") : "";
            } else {
                string3 = "";
            }
            JSONObject jSONObject5 = jSONObject.has(Constants.KEY_MEDIA) ? jSONObject.getJSONObject(Constants.KEY_MEDIA) : null;
            if (jSONObject5 != null) {
                String string8 = jSONObject5.has("url") ? jSONObject5.getString("url") : "";
                String string9 = jSONObject5.has("content_type") ? jSONObject5.getString("content_type") : "";
                string4 = jSONObject5.has(Constants.KEY_POSTER_URL) ? jSONObject5.getString(Constants.KEY_POSTER_URL) : "";
                str4 = string9;
                str3 = string8;
            } else {
                str3 = "";
                str4 = str3;
                string4 = str4;
            }
            JSONObject jSONObject6 = jSONObject.has(Constants.KEY_ACTION) ? jSONObject.getJSONObject(Constants.KEY_ACTION) : null;
            if (jSONObject6 != null) {
                JSONObject jSONObject7 = jSONObject6.has("url") ? jSONObject6.getJSONObject("url") : null;
                if (jSONObject7 != null) {
                    JSONObject jSONObject8 = jSONObject7.has(Constants.KEY_ANDROID) ? jSONObject7.getJSONObject(Constants.KEY_ANDROID) : null;
                    if (jSONObject8 != null && jSONObject8.has("text")) {
                        string5 = jSONObject8.getString("text");
                    }
                }
            }
            return new CleverTapDisplayUnitContent(str, string, str2, string2, string3, str3, str4, string4, string5, null);
        } catch (Exception e) {
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Unable to init CleverTapDisplayUnitContent with JSON - " + e.getLocalizedMessage());
            return new CleverTapDisplayUnitContent("", "", "", "", "", "", "", "", "", "Error Creating DisplayUnit Content from JSON : " + e.getLocalizedMessage());
        }
    }
}
