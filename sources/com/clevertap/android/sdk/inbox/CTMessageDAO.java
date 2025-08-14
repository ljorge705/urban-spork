package com.clevertap.android.sdk.inbox;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.db.Column;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTMessageDAO {
    private String campaignId;
    private long date;
    private long expires;
    private String id;
    private JSONObject jsonData;
    private boolean read;
    private List<String> tags;
    private String userId;
    private JSONObject wzrkParams;

    public String getCampaignId() {
        return this.campaignId;
    }

    public long getDate() {
        return this.date;
    }

    public long getExpires() {
        return this.expires;
    }

    public String getId() {
        return this.id;
    }

    public JSONObject getJsonData() {
        return this.jsonData;
    }

    public String getUserId() {
        return this.userId;
    }

    public JSONObject getWzrkParams() {
        return this.wzrkParams;
    }

    public int isRead() {
        return this.read ? 1 : 0;
    }

    public void setCampaignId(String str) {
        this.campaignId = str;
    }

    public void setDate(long j) {
        this.date = j;
    }

    public void setExpires(long j) {
        this.expires = j;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setJsonData(JSONObject jSONObject) {
        this.jsonData = jSONObject;
    }

    public void setRead(int i) {
        this.read = i == 1;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setWzrkParams(JSONObject jSONObject) {
        this.wzrkParams = jSONObject;
    }

    public CTMessageDAO() {
        this.tags = new ArrayList();
    }

    private CTMessageDAO(String str, JSONObject jSONObject, boolean z, long j, long j2, String str2, List<String> list, String str3, JSONObject jSONObject2) {
        new ArrayList();
        this.id = str;
        this.jsonData = jSONObject;
        this.read = z;
        this.date = j;
        this.expires = j2;
        this.userId = str2;
        this.tags = list;
        this.campaignId = str3;
        this.wzrkParams = jSONObject2;
    }

    boolean containsVideoOrAudio() {
        Logger.d("CTMessageDAO:containsVideoOrAudio() called");
        CTInboxMessageContent cTInboxMessageContent = new CTInboxMessage(toJSON()).getInboxMessageContents().get(0);
        return cTInboxMessageContent.mediaIsVideo() || cTInboxMessageContent.mediaIsAudio();
    }

    public String getTags() {
        return TextUtils.join(Constants.SEPARATOR_COMMA, this.tags);
    }

    public void setTags(String str) {
        this.tags.addAll(Arrays.asList(str.split(Constants.SEPARATOR_COMMA)));
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("msg", this.jsonData);
            jSONObject.put("isRead", this.read);
            jSONObject.put("date", this.date);
            jSONObject.put("wzrk_ttl", this.expires);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.tags.size(); i++) {
                jSONArray.put(this.tags.get(i));
            }
            jSONObject.put("tags", jSONArray);
            jSONObject.put(Constants.NOTIFICATION_ID_TAG, this.campaignId);
            jSONObject.put("wzrkParams", this.wzrkParams);
            return jSONObject;
        } catch (JSONException e) {
            Logger.v("Unable to convert CTMessageDao to JSON - " + e.getLocalizedMessage());
            return jSONObject;
        }
    }

    static CTMessageDAO initWithJSON(JSONObject jSONObject, String str) throws JSONException {
        try {
            String string = jSONObject.has(Column.ID) ? jSONObject.getString(Column.ID) : null;
            long j = jSONObject.has("date") ? jSONObject.getInt("date") : System.currentTimeMillis() / 1000;
            long j2 = jSONObject.has("wzrk_ttl") ? jSONObject.getInt("wzrk_ttl") : (System.currentTimeMillis() + 86400000) / 1000;
            JSONObject jSONObject2 = jSONObject.has("msg") ? jSONObject.getJSONObject("msg") : null;
            ArrayList arrayList = new ArrayList();
            if (jSONObject2 != null) {
                JSONArray jSONArray = jSONObject2.has("tags") ? jSONObject2.getJSONArray("tags") : null;
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                }
            }
            String string2 = jSONObject.has(Constants.NOTIFICATION_ID_TAG) ? jSONObject.getString(Constants.NOTIFICATION_ID_TAG) : Constants.TEST_IDENTIFIER;
            if (string2.equalsIgnoreCase(Constants.TEST_IDENTIFIER)) {
                jSONObject.put(Constants.NOTIFICATION_ID_TAG, string2);
            }
            JSONObject wzrkFields = getWzrkFields(jSONObject);
            if (string == null) {
                return null;
            }
            return new CTMessageDAO(string, jSONObject2, false, j, j2, str, arrayList, string2, wzrkFields);
        } catch (JSONException e) {
            Logger.d("Unable to parse Notification inbox message to CTMessageDao - " + e.getLocalizedMessage());
            return null;
        }
    }

    private static JSONObject getWzrkFields(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next.startsWith(Constants.WZRK_PREFIX)) {
                jSONObject2.put(next, jSONObject.get(next));
            }
        }
        return jSONObject2;
    }
}
