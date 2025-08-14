package com.clevertap.android.sdk.response;

import android.content.Context;
import android.content.SharedPreferences;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.ControllerManager;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.network.NetworkManager;
import com.clevertap.android.sdk.product_config.CTProductConfigController;
import com.clevertap.android.sdk.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ARPResponse extends CleverTapResponseDecorator {
    private final CleverTapInstanceConfig config;
    private final CTProductConfigController ctProductConfigController;
    private final Logger logger;
    private final NetworkManager networkManager;
    private final Validator validator;

    public ARPResponse(CleverTapInstanceConfig cleverTapInstanceConfig, NetworkManager networkManager, Validator validator, ControllerManager controllerManager) {
        this.config = cleverTapInstanceConfig;
        this.ctProductConfigController = controllerManager.getCTProductConfigController();
        this.logger = cleverTapInstanceConfig.getLogger();
        this.networkManager = networkManager;
        this.validator = validator;
    }

    @Override // com.clevertap.android.sdk.response.CleverTapResponseDecorator, com.clevertap.android.sdk.response.CleverTapResponse
    public void processResponse(JSONObject jSONObject, String str, Context context) {
        try {
            if (jSONObject.has("arp")) {
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("arp");
                if (jSONObject2.length() > 0) {
                    CTProductConfigController cTProductConfigController = this.ctProductConfigController;
                    if (cTProductConfigController != null) {
                        cTProductConfigController.setArpValue(jSONObject2);
                    }
                    try {
                        processDiscardedEventsList(jSONObject2);
                    } catch (Throwable th) {
                        this.logger.verbose("Error handling discarded events response: " + th.getLocalizedMessage());
                    }
                    handleARPUpdate(context, jSONObject2);
                }
            }
        } catch (Throwable th2) {
            this.logger.verbose(this.config.getAccountId(), "Failed to process ARP", th2);
        }
    }

    private void handleARPUpdate(Context context, JSONObject jSONObject) throws JSONException {
        String newNamespaceARPKey;
        if (jSONObject == null || jSONObject.length() == 0 || (newNamespaceARPKey = this.networkManager.getNewNamespaceARPKey()) == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = StorageHelper.getPreferences(context, newNamespaceARPKey).edit();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof Number) {
                    editorEdit.putInt(next, ((Number) obj).intValue());
                } else if (obj instanceof String) {
                    editorEdit.putString(next, (String) obj);
                } else if (obj instanceof Boolean) {
                    editorEdit.putBoolean(next, ((Boolean) obj).booleanValue());
                } else {
                    this.logger.verbose(this.config.getAccountId(), "ARP update for key " + next + " rejected (invalid data type)");
                }
            } catch (JSONException unused) {
            }
        }
        this.logger.verbose(this.config.getAccountId(), "Stored ARP for namespace key: " + newNamespaceARPKey + " values: " + jSONObject.toString());
        StorageHelper.persist(editorEdit);
    }

    private void processDiscardedEventsList(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has(Constants.DISCARDED_EVENT_JSON_KEY)) {
            this.logger.verbose(this.config.getAccountId(), "ARP doesn't contain the Discarded Events key");
            return;
        }
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.DISCARDED_EVENT_JSON_KEY);
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
            }
            Validator validator = this.validator;
            if (validator != null) {
                validator.setDiscardedEvents(arrayList);
            } else {
                this.logger.verbose(this.config.getAccountId(), "Validator object is NULL");
            }
        } catch (JSONException e) {
            this.logger.verbose(this.config.getAccountId(), "Error parsing discarded events list" + e.getLocalizedMessage());
        }
    }
}
