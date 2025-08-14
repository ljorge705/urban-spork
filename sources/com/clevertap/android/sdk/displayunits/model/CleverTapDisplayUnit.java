package com.clevertap.android.sdk.displayunits.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.displayunits.CTDisplayUnitType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CleverTapDisplayUnit implements Parcelable {
    public static final Parcelable.Creator<CleverTapDisplayUnit> CREATOR = new Parcelable.Creator<CleverTapDisplayUnit>() { // from class: com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleverTapDisplayUnit createFromParcel(Parcel parcel) {
            return new CleverTapDisplayUnit(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CleverTapDisplayUnit[] newArray(int i) {
            return new CleverTapDisplayUnit[i];
        }
    };
    private String bgColor;
    private ArrayList<CleverTapDisplayUnitContent> contents;
    private HashMap<String, String> customExtras;
    private String error;
    private JSONObject jsonObject;
    private CTDisplayUnitType type;
    private String unitID;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBgColor() {
        return this.bgColor;
    }

    public ArrayList<CleverTapDisplayUnitContent> getContents() {
        return this.contents;
    }

    public HashMap<String, String> getCustomExtras() {
        return this.customExtras;
    }

    public String getError() {
        return this.error;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    public CTDisplayUnitType getType() {
        return this.type;
    }

    public String getUnitID() {
        return this.unitID;
    }

    public static CleverTapDisplayUnit toDisplayUnit(JSONObject jSONObject) {
        try {
            String string = jSONObject.has(Constants.NOTIFICATION_ID_TAG) ? jSONObject.getString(Constants.NOTIFICATION_ID_TAG) : Constants.TEST_IDENTIFIER;
            CTDisplayUnitType cTDisplayUnitTypeType = jSONObject.has("type") ? CTDisplayUnitType.type(jSONObject.getString("type")) : null;
            String string2 = jSONObject.has(Constants.KEY_BG) ? jSONObject.getString(Constants.KEY_BG) : "";
            JSONArray jSONArray = jSONObject.has("content") ? jSONObject.getJSONArray("content") : null;
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    CleverTapDisplayUnitContent content = CleverTapDisplayUnitContent.toContent(jSONArray.getJSONObject(i));
                    if (TextUtils.isEmpty(content.getError())) {
                        arrayList.add(content);
                    }
                }
            }
            return new CleverTapDisplayUnit(jSONObject, string, cTDisplayUnitTypeType, string2, arrayList, jSONObject.has(Constants.KEY_CUSTOM_KV) ? jSONObject.getJSONObject(Constants.KEY_CUSTOM_KV) : null, null);
        } catch (Exception e) {
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Unable to init CleverTapDisplayUnit with JSON - " + e.getLocalizedMessage());
            return new CleverTapDisplayUnit(null, "", null, null, null, null, "Error Creating Display Unit from JSON : " + e.getLocalizedMessage());
        }
    }

    private CleverTapDisplayUnit(JSONObject jSONObject, String str, CTDisplayUnitType cTDisplayUnitType, String str2, ArrayList<CleverTapDisplayUnitContent> arrayList, JSONObject jSONObject2, String str3) {
        this.jsonObject = jSONObject;
        this.unitID = str;
        this.type = cTDisplayUnitType;
        this.bgColor = str2;
        this.contents = arrayList;
        this.customExtras = getKeyValues(jSONObject2);
        this.error = str3;
    }

    private CleverTapDisplayUnit(Parcel parcel) {
        try {
            this.unitID = parcel.readString();
            this.type = (CTDisplayUnitType) parcel.readValue(CTDisplayUnitType.class.getClassLoader());
            this.bgColor = parcel.readString();
            JSONObject jSONObject = null;
            if (parcel.readByte() == 1) {
                ArrayList<CleverTapDisplayUnitContent> arrayList = new ArrayList<>();
                this.contents = arrayList;
                parcel.readList(arrayList, CleverTapDisplayUnitContent.class.getClassLoader());
            } else {
                this.contents = null;
            }
            this.customExtras = parcel.readHashMap(null);
            if (parcel.readByte() != 0) {
                jSONObject = new JSONObject(parcel.readString());
            }
            this.jsonObject = jSONObject;
            this.error = parcel.readString();
        } catch (Exception e) {
            String str = "Error Creating Display Unit from parcel : " + e.getLocalizedMessage();
            this.error = str;
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, str);
        }
    }

    public JSONObject getWZRKFields() throws JSONException {
        try {
            JSONObject jSONObject = this.jsonObject;
            if (jSONObject == null) {
                return null;
            }
            Iterator<String> itKeys = jSONObject.keys();
            JSONObject jSONObject2 = new JSONObject();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next.startsWith(Constants.WZRK_PREFIX)) {
                    jSONObject2.put(next, this.jsonObject.get(next));
                }
            }
            return jSONObject2;
        } catch (Exception e) {
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Error in getting WiZRK fields " + e.getLocalizedMessage());
            return null;
        }
    }

    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(" Unit id- ").append(this.unitID);
            StringBuilder sbAppend = sb.append(", Type- ");
            CTDisplayUnitType cTDisplayUnitType = this.type;
            sbAppend.append(cTDisplayUnitType != null ? cTDisplayUnitType.toString() : null);
            sb.append(", bgColor- ").append(this.bgColor);
            ArrayList<CleverTapDisplayUnitContent> arrayList = this.contents;
            if (arrayList != null && !arrayList.isEmpty()) {
                for (int i = 0; i < this.contents.size(); i++) {
                    CleverTapDisplayUnitContent cleverTapDisplayUnitContent = this.contents.get(i);
                    if (cleverTapDisplayUnitContent != null) {
                        sb.append(", Content Item:").append(i).append(StringUtils.SPACE).append(cleverTapDisplayUnitContent.toString());
                        sb.append("\n");
                    }
                }
            }
            if (this.customExtras != null) {
                sb.append(", Custom KV:").append(this.customExtras);
            }
            sb.append(", JSON -").append(this.jsonObject);
            sb.append(", Error-").append(this.error);
            sb.append(" ]");
            return sb.toString();
        } catch (Exception e) {
            Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Exception in toString:" + e);
            return super.toString();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.unitID);
        parcel.writeValue(this.type);
        parcel.writeString(this.bgColor);
        if (this.contents == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.contents);
        }
        parcel.writeMap(this.customExtras);
        if (this.jsonObject == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.jsonObject.toString());
        }
        parcel.writeString(this.error);
    }

    HashMap<String, String> getKeyValues(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            try {
                Iterator<String> itKeys = jSONObject.keys();
                if (itKeys != null) {
                    HashMap<String, String> map = null;
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        String string = jSONObject.getString(next);
                        if (!TextUtils.isEmpty(next)) {
                            if (map == null) {
                                map = new HashMap<>();
                            }
                            map.put(next, string);
                        }
                    }
                    return map;
                }
            } catch (Exception e) {
                Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Error in getting Key Value Pairs " + e.getLocalizedMessage());
            }
        }
        return null;
    }
}
