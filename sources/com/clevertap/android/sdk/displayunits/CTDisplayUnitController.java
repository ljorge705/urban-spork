package com.clevertap.android.sdk.displayunits;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTDisplayUnitController {
    final HashMap<String, CleverTapDisplayUnit> items = new HashMap<>();

    public synchronized ArrayList<CleverTapDisplayUnit> getAllDisplayUnits() {
        if (!this.items.isEmpty()) {
            return new ArrayList<>(this.items.values());
        }
        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Failed to return Display Units, nothing found in the cache");
        return null;
    }

    public synchronized CleverTapDisplayUnit getDisplayUnitForID(String str) {
        if (!TextUtils.isEmpty(str)) {
            return this.items.get(str);
        }
        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Can't return Display Unit, id was null");
        return null;
    }

    public synchronized void reset() {
        this.items.clear();
        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Cleared Display Units Cache");
    }

    public synchronized ArrayList<CleverTapDisplayUnit> updateDisplayUnits(JSONArray jSONArray) {
        reset();
        if (jSONArray != null && jSONArray.length() > 0) {
            ArrayList<CleverTapDisplayUnit> arrayList = new ArrayList<>();
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    CleverTapDisplayUnit displayUnit = CleverTapDisplayUnit.toDisplayUnit((JSONObject) jSONArray.get(i));
                    if (TextUtils.isEmpty(displayUnit.getError())) {
                        this.items.put(displayUnit.getUnitID(), displayUnit);
                        arrayList.add(displayUnit);
                    } else {
                        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Failed to convert JsonArray item at index:" + i + " to Display Unit");
                    }
                } catch (Exception e) {
                    Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Failed while parsing Display Unit:" + e.getLocalizedMessage());
                    return null;
                }
            }
            return arrayList.isEmpty() ? null : arrayList;
        }
        Logger.d(Constants.FEATURE_DISPLAY_UNIT, "Null json array response can't parse Display Units ");
        return null;
    }
}
