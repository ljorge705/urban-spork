package com.clevertap.android.sdk.inapp;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTInAppNotificationMedia implements Parcelable {
    public static final Parcelable.Creator<CTInAppNotificationMedia> CREATOR = new Parcelable.Creator<CTInAppNotificationMedia>() { // from class: com.clevertap.android.sdk.inapp.CTInAppNotificationMedia.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInAppNotificationMedia createFromParcel(Parcel parcel) {
            return new CTInAppNotificationMedia(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInAppNotificationMedia[] newArray(int i) {
            return new CTInAppNotificationMedia[i];
        }
    };
    private String cacheKey;
    private String contentType;
    private String mediaUrl;
    int orientation;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    String getCacheKey() {
        return this.cacheKey;
    }

    String getContentType() {
        return this.contentType;
    }

    public String getMediaUrl() {
        return this.mediaUrl;
    }

    public int getOrientation() {
        return this.orientation;
    }

    void setMediaUrl(String str) {
        this.mediaUrl = str;
    }

    public CTInAppNotificationMedia() {
    }

    private CTInAppNotificationMedia(Parcel parcel) {
        this.mediaUrl = parcel.readString();
        this.contentType = parcel.readString();
        this.cacheKey = parcel.readString();
        this.orientation = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mediaUrl);
        parcel.writeString(this.contentType);
        parcel.writeString(this.cacheKey);
        parcel.writeInt(this.orientation);
    }

    public CTInAppNotificationMedia initWithJSON(JSONObject jSONObject, int i) {
        this.orientation = i;
        try {
            this.contentType = jSONObject.has("content_type") ? jSONObject.getString("content_type") : "";
            String string = jSONObject.has("url") ? jSONObject.getString("url") : "";
            if (!string.isEmpty()) {
                if (this.contentType.startsWith(MimeTypes.BASE_TYPE_IMAGE)) {
                    this.mediaUrl = string;
                    if (jSONObject.has(Constants.KEY_KEY)) {
                        this.cacheKey = UUID.randomUUID().toString() + jSONObject.getString(Constants.KEY_KEY);
                    } else {
                        this.cacheKey = UUID.randomUUID().toString();
                    }
                } else {
                    this.mediaUrl = string;
                }
            }
        } catch (JSONException e) {
            Logger.v("Error parsing Media JSONObject - " + e.getLocalizedMessage());
        }
        if (this.contentType.isEmpty()) {
            return null;
        }
        return this;
    }

    public boolean isAudio() {
        String contentType = getContentType();
        return (contentType == null || this.mediaUrl == null || !contentType.startsWith(MimeTypes.BASE_TYPE_AUDIO)) ? false : true;
    }

    public boolean isGIF() {
        String contentType = getContentType();
        return (contentType == null || this.mediaUrl == null || !contentType.equals("image/gif")) ? false : true;
    }

    public boolean isImage() {
        String contentType = getContentType();
        return (contentType == null || this.mediaUrl == null || !contentType.startsWith(MimeTypes.BASE_TYPE_IMAGE) || contentType.equals("image/gif")) ? false : true;
    }

    public boolean isVideo() {
        String contentType = getContentType();
        return (contentType == null || this.mediaUrl == null || !contentType.startsWith("video")) ? false : true;
    }

    public boolean isMediaStreamable() {
        return isVideo() || isAudio();
    }
}
