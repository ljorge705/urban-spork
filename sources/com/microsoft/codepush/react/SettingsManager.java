package com.microsoft.codepush.react;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class SettingsManager {
    private SharedPreferences mSettings;

    public SettingsManager(Context context) {
        this.mSettings = context.getSharedPreferences("CodePush", 0);
    }

    public JSONArray getFailedUpdates() {
        String string = this.mSettings.getString(CodePushConstants.FAILED_UPDATES_KEY, null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(string);
        } catch (JSONException unused) {
            JSONArray jSONArray = new JSONArray();
            this.mSettings.edit().putString(CodePushConstants.FAILED_UPDATES_KEY, jSONArray.toString()).commit();
            return jSONArray;
        }
    }

    public JSONObject getPendingUpdate() {
        String string = this.mSettings.getString(CodePushConstants.PENDING_UPDATE_KEY, null);
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            CodePushUtils.log("Unable to parse pending update metadata " + string + " stored in SharedPreferences");
            return null;
        }
    }

    public boolean isFailedHash(String str) {
        JSONArray failedUpdates = getFailedUpdates();
        if (str != null) {
            for (int i = 0; i < failedUpdates.length(); i++) {
                try {
                    if (str.equals(failedUpdates.getJSONObject(i).getString("packageHash"))) {
                        return true;
                    }
                } catch (JSONException e) {
                    throw new CodePushUnknownException("Unable to read failedUpdates data stored in SharedPreferences.", e);
                }
            }
        }
        return false;
    }

    public boolean isPendingUpdate(String str) {
        JSONObject pendingUpdate = getPendingUpdate();
        if (pendingUpdate != null) {
            try {
                if (!pendingUpdate.getBoolean(CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY)) {
                    if (str != null) {
                        if (pendingUpdate.getString(CodePushConstants.PENDING_UPDATE_HASH_KEY).equals(str)) {
                        }
                    }
                    return true;
                }
            } catch (JSONException e) {
                throw new CodePushUnknownException("Unable to read pending update metadata in isPendingUpdate.", e);
            }
        }
        return false;
    }

    public void removeFailedUpdates() {
        this.mSettings.edit().remove(CodePushConstants.FAILED_UPDATES_KEY).commit();
    }

    public void removePendingUpdate() {
        this.mSettings.edit().remove(CodePushConstants.PENDING_UPDATE_KEY).commit();
    }

    public void saveFailedUpdate(JSONObject jSONObject) {
        JSONArray jSONArray;
        try {
            if (isFailedHash(jSONObject.getString("packageHash"))) {
                return;
            }
            String string = this.mSettings.getString(CodePushConstants.FAILED_UPDATES_KEY, null);
            if (string == null) {
                jSONArray = new JSONArray();
            } else {
                try {
                    jSONArray = new JSONArray(string);
                } catch (JSONException e) {
                    throw new CodePushMalformedDataException("Unable to parse failed updates information " + string + " stored in SharedPreferences", e);
                }
            }
            jSONArray.put(jSONObject);
            this.mSettings.edit().putString(CodePushConstants.FAILED_UPDATES_KEY, jSONArray.toString()).commit();
        } catch (JSONException e2) {
            throw new CodePushUnknownException("Unable to read package hash from package.", e2);
        }
    }

    public JSONObject getLatestRollbackInfo() {
        String string = this.mSettings.getString(CodePushConstants.LATEST_ROLLBACK_INFO_KEY, null);
        if (string == null) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            CodePushUtils.log("Unable to parse latest rollback metadata " + string + " stored in SharedPreferences");
            return null;
        }
    }

    public void setLatestRollbackInfo(String str) throws JSONException {
        JSONObject latestRollbackInfo = getLatestRollbackInfo();
        int i = 0;
        if (latestRollbackInfo != null) {
            try {
                if (latestRollbackInfo.getString("packageHash").equals(str)) {
                    i = latestRollbackInfo.getInt("count");
                }
            } catch (JSONException unused) {
                CodePushUtils.log("Unable to parse latest rollback info.");
            }
        } else {
            latestRollbackInfo = new JSONObject();
        }
        try {
            latestRollbackInfo.put("packageHash", str);
            latestRollbackInfo.put("time", System.currentTimeMillis());
            latestRollbackInfo.put("count", i + 1);
            this.mSettings.edit().putString(CodePushConstants.LATEST_ROLLBACK_INFO_KEY, latestRollbackInfo.toString()).commit();
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to save latest rollback info.", e);
        }
    }

    public void savePendingUpdate(String str, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CodePushConstants.PENDING_UPDATE_HASH_KEY, str);
            jSONObject.put(CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY, z);
            this.mSettings.edit().putString(CodePushConstants.PENDING_UPDATE_KEY, jSONObject.toString()).commit();
        } catch (JSONException e) {
            throw new CodePushUnknownException("Unable to save pending update.", e);
        }
    }
}
