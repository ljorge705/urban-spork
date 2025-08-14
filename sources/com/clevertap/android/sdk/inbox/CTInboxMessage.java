package com.clevertap.android.sdk.inbox;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTInboxMessage implements Parcelable {
    public static final Parcelable.Creator<CTInboxMessage> CREATOR = new Parcelable.Creator<CTInboxMessage>() { // from class: com.clevertap.android.sdk.inbox.CTInboxMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInboxMessage createFromParcel(Parcel parcel) {
            return new CTInboxMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInboxMessage[] newArray(int i) {
            return new CTInboxMessage[i];
        }
    };
    private String actionUrl;
    private String bgColor;
    private String body;
    private String campaignId;
    private JSONObject customData;
    private JSONObject data;
    private long date;
    private long expires;
    private String imageUrl;
    private ArrayList<CTInboxMessageContent> inboxMessageContents;
    private boolean isRead;
    private String messageId;
    private String orientation;
    private List<String> tags;
    private String title;
    private CTInboxMessageType type;
    private JSONObject wzrkParams;

    public static Parcelable.Creator<CTInboxMessage> getCREATOR() {
        return CREATOR;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getActionUrl() {
        return this.actionUrl;
    }

    public String getBgColor() {
        return this.bgColor;
    }

    public String getBody() {
        return this.body;
    }

    public String getCampaignId() {
        return this.campaignId;
    }

    public JSONObject getCustomData() {
        return this.customData;
    }

    public JSONObject getData() {
        return this.data;
    }

    public long getDate() {
        return this.date;
    }

    public long getExpires() {
        return this.expires;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public ArrayList<CTInboxMessageContent> getInboxMessageContents() {
        return this.inboxMessageContents;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String getTitle() {
        return this.title;
    }

    public CTInboxMessageType getType() {
        return this.type;
    }

    public boolean isRead() {
        return this.isRead;
    }

    void setRead(boolean z) {
        this.isRead = z;
    }

    public CTInboxMessage(JSONObject jSONObject) throws JSONException {
        this.customData = new JSONObject();
        this.inboxMessageContents = new ArrayList<>();
        this.tags = new ArrayList();
        this.data = jSONObject;
        try {
            this.messageId = jSONObject.has("id") ? jSONObject.getString("id") : "0";
            this.campaignId = jSONObject.has(Constants.NOTIFICATION_ID_TAG) ? jSONObject.getString(Constants.NOTIFICATION_ID_TAG) : Constants.TEST_IDENTIFIER;
            this.date = jSONObject.has("date") ? jSONObject.getLong("date") : System.currentTimeMillis() / 1000;
            this.expires = jSONObject.has("wzrk_ttl") ? jSONObject.getLong("wzrk_ttl") : System.currentTimeMillis() + 86400000;
            this.isRead = jSONObject.has("isRead") && jSONObject.getBoolean("isRead");
            JSONArray jSONArray = jSONObject.has("tags") ? jSONObject.getJSONArray("tags") : null;
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.tags.add(jSONArray.getString(i));
                }
            }
            JSONObject jSONObject2 = jSONObject.has("msg") ? jSONObject.getJSONObject("msg") : null;
            if (jSONObject2 != null) {
                this.type = jSONObject2.has("type") ? CTInboxMessageType.fromString(jSONObject2.getString("type")) : CTInboxMessageType.fromString("");
                this.bgColor = jSONObject2.has(Constants.KEY_BG) ? jSONObject2.getString(Constants.KEY_BG) : "";
                JSONArray jSONArray2 = jSONObject2.has("content") ? jSONObject2.getJSONArray("content") : null;
                if (jSONArray2 != null) {
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        this.inboxMessageContents.add(new CTInboxMessageContent().initWithJSON(jSONArray2.getJSONObject(i2)));
                    }
                }
                JSONArray jSONArray3 = jSONObject2.has(Constants.KEY_CUSTOM_KV) ? jSONObject2.getJSONArray(Constants.KEY_CUSTOM_KV) : null;
                if (jSONArray3 != null) {
                    for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                        JSONObject jSONObject3 = jSONArray3.getJSONObject(i3);
                        if (jSONObject3.has(Constants.KEY_KEY)) {
                            String string = jSONObject3.getString(Constants.KEY_KEY);
                            if (jSONObject3.has("value")) {
                                this.customData.put(string, jSONObject3.getJSONObject("value").getString("text"));
                            }
                        }
                    }
                }
                this.orientation = jSONObject2.has("orientation") ? jSONObject2.getString("orientation") : "";
            }
            this.wzrkParams = jSONObject.has("wzrkParams") ? jSONObject.getJSONObject("wzrkParams") : null;
        } catch (JSONException e) {
            Logger.v("Unable to init CTInboxMessage with JSON - " + e.getLocalizedMessage());
        }
    }

    private CTInboxMessage(Parcel parcel) {
        this.customData = new JSONObject();
        this.inboxMessageContents = new ArrayList<>();
        this.tags = new ArrayList();
        try {
            this.title = parcel.readString();
            this.body = parcel.readString();
            this.imageUrl = parcel.readString();
            this.actionUrl = parcel.readString();
            this.date = parcel.readLong();
            this.expires = parcel.readLong();
            this.messageId = parcel.readString();
            JSONObject jSONObject = null;
            this.data = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
            this.customData = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
            this.isRead = parcel.readByte() != 0;
            this.type = (CTInboxMessageType) parcel.readValue(CTInboxMessageType.class.getClassLoader());
            if (parcel.readByte() == 1) {
                List arrayList = new ArrayList();
                this.tags = arrayList;
                parcel.readList(arrayList, String.class.getClassLoader());
            } else {
                this.tags = null;
            }
            this.bgColor = parcel.readString();
            if (parcel.readByte() == 1) {
                ArrayList<CTInboxMessageContent> arrayList2 = new ArrayList<>();
                this.inboxMessageContents = arrayList2;
                parcel.readList(arrayList2, CTInboxMessageContent.class.getClassLoader());
            } else {
                this.inboxMessageContents = null;
            }
            this.orientation = parcel.readString();
            this.campaignId = parcel.readString();
            if (parcel.readByte() != 0) {
                jSONObject = new JSONObject(parcel.readString());
            }
            this.wzrkParams = jSONObject;
        } catch (JSONException e) {
            Logger.v("Unable to parse CTInboxMessage from parcel - " + e.getLocalizedMessage());
        }
    }

    public ArrayList<String> getCarouselImages() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<CTInboxMessageContent> it = getInboxMessageContents().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getMedia());
        }
        return arrayList;
    }

    public JSONObject getWzrkParams() {
        JSONObject jSONObject = this.wzrkParams;
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.body);
        parcel.writeString(this.imageUrl);
        parcel.writeString(this.actionUrl);
        parcel.writeLong(this.date);
        parcel.writeLong(this.expires);
        parcel.writeString(this.messageId);
        if (this.data == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.data.toString());
        }
        if (this.customData == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.customData.toString());
        }
        parcel.writeByte(this.isRead ? (byte) 1 : (byte) 0);
        parcel.writeValue(this.type);
        if (this.tags == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.tags);
        }
        parcel.writeString(this.bgColor);
        if (this.inboxMessageContents == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.inboxMessageContents);
        }
        parcel.writeString(this.orientation);
        parcel.writeString(this.campaignId);
        if (this.wzrkParams == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.wzrkParams.toString());
        }
    }
}
