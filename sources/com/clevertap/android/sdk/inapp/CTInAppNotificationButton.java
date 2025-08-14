package com.clevertap.android.sdk.inapp;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTInAppNotificationButton implements Parcelable {
    public static final Parcelable.Creator<CTInAppNotificationButton> CREATOR = new Parcelable.Creator<CTInAppNotificationButton>() { // from class: com.clevertap.android.sdk.inapp.CTInAppNotificationButton.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInAppNotificationButton createFromParcel(Parcel parcel) {
            return new CTInAppNotificationButton(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInAppNotificationButton[] newArray(int i) {
            return new CTInAppNotificationButton[i];
        }
    };
    private CTInAppAction action;
    private String backgroundColor;
    private String borderColor;
    private String borderRadius;
    private String error;
    private JSONObject jsonDescription;
    private String text;
    private String textColor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CTInAppAction getAction() {
        return this.action;
    }

    String getBackgroundColor() {
        return this.backgroundColor;
    }

    String getBorderColor() {
        return this.borderColor;
    }

    String getBorderRadius() {
        return this.borderRadius;
    }

    String getError() {
        return this.error;
    }

    public String getText() {
        return this.text;
    }

    String getTextColor() {
        return this.textColor;
    }

    CTInAppNotificationButton() {
    }

    protected CTInAppNotificationButton(Parcel parcel) {
        this.text = parcel.readString();
        this.textColor = parcel.readString();
        this.backgroundColor = parcel.readString();
        this.borderColor = parcel.readString();
        this.borderRadius = parcel.readString();
        try {
            this.jsonDescription = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.error = parcel.readString();
        this.action = (CTInAppAction) parcel.readParcelable(CTInAppAction.class.getClassLoader());
    }

    public HashMap<String, String> getKeyValues() {
        CTInAppAction cTInAppAction = this.action;
        if (cTInAppAction != null) {
            return cTInAppAction.getKeyValues();
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
        parcel.writeString(this.textColor);
        parcel.writeString(this.backgroundColor);
        parcel.writeString(this.borderColor);
        parcel.writeString(this.borderRadius);
        if (this.jsonDescription == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.jsonDescription.toString());
        }
        parcel.writeString(this.error);
        parcel.writeParcelable(this.action, i);
    }

    CTInAppNotificationButton initWithJSON(JSONObject jSONObject) {
        this.jsonDescription = jSONObject;
        this.text = jSONObject.optString("text");
        this.textColor = jSONObject.optString("color", Constants.BLUE);
        this.backgroundColor = jSONObject.optString(Constants.KEY_BG, Constants.WHITE);
        this.borderColor = jSONObject.optString(Constants.KEY_BORDER, Constants.WHITE);
        this.borderRadius = jSONObject.optString(Constants.KEY_RADIUS);
        this.action = CTInAppAction.createFromJson(jSONObject.optJSONObject(Constants.KEY_ACTIONS));
        return this;
    }
}
