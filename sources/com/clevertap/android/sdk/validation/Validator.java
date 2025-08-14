package com.clevertap.android.sdk.validation;

import androidx.core.app.FrameMetricsAggregator;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class Validator {
    public static final String ADD_VALUES_OPERATION = "multiValuePropertyAddValues";
    public static final String REMOVE_VALUES_OPERATION = "multiValuePropertyRemoveValues";
    private static final String[] eventNameCharsNotAllowed = {".", ":", "$", "'", "\"", "\\"};
    private static final String[] objectKeyCharsNotAllowed = {".", ":", "$", "'", "\"", "\\"};
    private static final String[] objectValueCharsNotAllowed = {"'", "\"", "\\"};
    private static final String[] restrictedNames = {"Stayed", Constants.NOTIFICATION_CLICKED_EVENT_NAME, Constants.NOTIFICATION_VIEWED_EVENT_NAME, "UTM Visited", "Notification Sent", Constants.APP_LAUNCHED_EVENT, "wzrk_d", "App Uninstalled", "Notification Bounced", Constants.GEOFENCE_ENTERED_EVENT_NAME, Constants.GEOFENCE_EXITED_EVENT_NAME, Constants.SC_OUTGOING_EVENT_NAME, Constants.SC_INCOMING_EVENT_NAME, Constants.SC_END_EVENT_NAME, Constants.SC_CAMPAIGN_OPT_OUT_EVENT_NAME};
    private ArrayList<String> discardedEvents;

    private enum RestrictedMultiValueFields {
        Name,
        Email,
        Education,
        Married,
        DOB,
        Gender,
        Phone,
        Age,
        FBID,
        GPID,
        Birthday
    }

    public enum ValidationContext {
        Profile,
        Event
    }

    private ArrayList<String> getDiscardedEvents() {
        return this.discardedEvents;
    }

    public void setDiscardedEvents(ArrayList<String> arrayList) {
        this.discardedEvents = arrayList;
    }

    public ValidationResult cleanEventName(String str) {
        ValidationResult validationResult = new ValidationResult();
        String strTrim = str.trim();
        for (String str2 : eventNameCharsNotAllowed) {
            strTrim = strTrim.replace(str2, "");
        }
        if (strTrim.length() > 512) {
            strTrim = strTrim.substring(0, FrameMetricsAggregator.EVERY_DURATION);
            ValidationResult validationResultCreate = ValidationResultFactory.create(510, 11, strTrim.trim(), "512");
            validationResult.setErrorDesc(validationResultCreate.getErrorDesc());
            validationResult.setErrorCode(validationResultCreate.getErrorCode());
        }
        validationResult.setObject(strTrim.trim());
        return validationResult;
    }

    public ValidationResult cleanMultiValuePropertyKey(String str) {
        ValidationResult validationResultCleanObjectKey = cleanObjectKey(str);
        String str2 = (String) validationResultCleanObjectKey.getObject();
        try {
            if (RestrictedMultiValueFields.valueOf(str2) != null) {
                ValidationResult validationResultCreate = ValidationResultFactory.create(523, 24, str2);
                validationResultCleanObjectKey.setErrorDesc(validationResultCreate.getErrorDesc());
                validationResultCleanObjectKey.setErrorCode(validationResultCreate.getErrorCode());
                validationResultCleanObjectKey.setObject(null);
            }
        } catch (Throwable unused) {
        }
        return validationResultCleanObjectKey;
    }

    public ValidationResult cleanMultiValuePropertyValue(String str) {
        ValidationResult validationResult = new ValidationResult();
        String lowerCase = str.trim().toLowerCase();
        for (String str2 : objectValueCharsNotAllowed) {
            lowerCase = lowerCase.replace(str2, "");
        }
        try {
            if (lowerCase.length() > 512) {
                lowerCase = lowerCase.substring(0, FrameMetricsAggregator.EVERY_DURATION);
                ValidationResult validationResultCreate = ValidationResultFactory.create(521, 11, lowerCase, "512");
                validationResult.setErrorDesc(validationResultCreate.getErrorDesc());
                validationResult.setErrorCode(validationResultCreate.getErrorCode());
            }
        } catch (Exception unused) {
        }
        validationResult.setObject(lowerCase);
        return validationResult;
    }

    public ValidationResult cleanObjectKey(String str) {
        ValidationResult validationResult = new ValidationResult();
        String strTrim = str.trim();
        for (String str2 : objectKeyCharsNotAllowed) {
            strTrim = strTrim.replace(str2, "");
        }
        if (strTrim.length() > 120) {
            strTrim = strTrim.substring(0, 119);
            ValidationResult validationResultCreate = ValidationResultFactory.create(520, 11, strTrim.trim(), "120");
            validationResult.setErrorDesc(validationResultCreate.getErrorDesc());
            validationResult.setErrorCode(validationResultCreate.getErrorCode());
        }
        validationResult.setObject(strTrim.trim());
        return validationResult;
    }

    public ValidationResult cleanObjectValue(Object obj, ValidationContext validationContext) throws JSONException, IllegalArgumentException {
        String strValueOf;
        ValidationResult validationResult = new ValidationResult();
        if ((obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Boolean) || (obj instanceof Double) || (obj instanceof Long)) {
            validationResult.setObject(obj);
            return validationResult;
        }
        if ((obj instanceof String) || (obj instanceof Character)) {
            if (obj instanceof Character) {
                strValueOf = String.valueOf(obj);
            } else {
                strValueOf = (String) obj;
            }
            String strTrim = strValueOf.trim();
            for (String str : objectValueCharsNotAllowed) {
                strTrim = strTrim.replace(str, "");
            }
            try {
                if (strTrim.length() > 512) {
                    strTrim = strTrim.substring(0, FrameMetricsAggregator.EVERY_DURATION);
                    ValidationResult validationResultCreate = ValidationResultFactory.create(521, 11, strTrim.trim(), "512");
                    validationResult.setErrorDesc(validationResultCreate.getErrorDesc());
                    validationResult.setErrorCode(validationResultCreate.getErrorCode());
                }
            } catch (Exception unused) {
            }
            validationResult.setObject(strTrim.trim());
            return validationResult;
        }
        if (obj instanceof Date) {
            validationResult.setObject(Constants.DATE_PREFIX + (((Date) obj).getTime() / 1000));
            return validationResult;
        }
        boolean z = obj instanceof String[];
        if ((z || (obj instanceof ArrayList)) && validationContext.equals(ValidationContext.Profile)) {
            ArrayList arrayList = obj instanceof ArrayList ? (ArrayList) obj : null;
            String[] strArr = z ? (String[]) obj : null;
            ArrayList arrayList2 = new ArrayList();
            if (strArr != null) {
                for (String str2 : strArr) {
                    try {
                        arrayList2.add(str2);
                    } catch (Exception unused2) {
                    }
                }
            } else {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    try {
                        arrayList2.add((String) it.next());
                    } catch (Exception unused3) {
                    }
                }
            }
            String[] strArr2 = (String[]) arrayList2.toArray(new String[0]);
            if (strArr2.length > 0 && strArr2.length <= 100) {
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                for (String str3 : strArr2) {
                    jSONArray.put(str3);
                }
                try {
                    jSONObject.put(Constants.COMMAND_SET, jSONArray);
                } catch (JSONException unused4) {
                }
                validationResult.setObject(jSONObject);
            } else {
                ValidationResult validationResultCreate2 = ValidationResultFactory.create(521, 13, strArr2.length + "", "100");
                validationResult.setErrorDesc(validationResultCreate2.getErrorDesc());
                validationResult.setErrorCode(validationResultCreate2.getErrorCode());
            }
            return validationResult;
        }
        throw new IllegalArgumentException("Not a String, Boolean, Long, Integer, Float, Double, or Date");
    }

    public ValidationResult isEventDiscarded(String str) {
        ValidationResult validationResult = new ValidationResult();
        if (str == null) {
            ValidationResult validationResultCreate = ValidationResultFactory.create(510, 14, new String[0]);
            validationResult.setErrorCode(validationResultCreate.getErrorCode());
            validationResult.setErrorDesc(validationResultCreate.getErrorDesc());
            return validationResult;
        }
        if (getDiscardedEvents() != null) {
            Iterator<String> it = getDiscardedEvents().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (Utils.areNamesNormalizedEqual(str, it.next())) {
                    ValidationResult validationResultCreate2 = ValidationResultFactory.create(513, 17, str);
                    validationResult.setErrorCode(validationResultCreate2.getErrorCode());
                    validationResult.setErrorDesc(validationResultCreate2.getErrorDesc());
                    Logger.d(str + " s a discarded event name as per CleverTap. Dropping event at SDK level. Check discarded events in CleverTap Dashboard settings.");
                    break;
                }
            }
        }
        return validationResult;
    }

    public ValidationResult isRestrictedEventName(String str) {
        ValidationResult validationResult = new ValidationResult();
        if (str == null) {
            ValidationResult validationResultCreate = ValidationResultFactory.create(510, 14, new String[0]);
            validationResult.setErrorCode(validationResultCreate.getErrorCode());
            validationResult.setErrorDesc(validationResultCreate.getErrorDesc());
            return validationResult;
        }
        for (String str2 : restrictedNames) {
            if (Utils.areNamesNormalizedEqual(str, str2)) {
                ValidationResult validationResultCreate2 = ValidationResultFactory.create(513, 16, str);
                validationResult.setErrorCode(validationResultCreate2.getErrorCode());
                validationResult.setErrorDesc(validationResultCreate2.getErrorDesc());
                Logger.v(validationResultCreate2.getErrorDesc());
                return validationResult;
            }
        }
        return validationResult;
    }

    public ValidationResult mergeMultiValuePropertyForKey(JSONArray jSONArray, JSONArray jSONArray2, String str, String str2) {
        return _mergeListInternalForKey(str2, jSONArray, jSONArray2, REMOVE_VALUES_OPERATION.equals(str), new ValidationResult());
    }

    private ValidationResult _mergeListInternalForKey(String str, JSONArray jSONArray, JSONArray jSONArray2, boolean z, ValidationResult validationResult) {
        if (jSONArray == null) {
            validationResult.setObject(null);
            return validationResult;
        }
        if (jSONArray2 == null) {
            validationResult.setObject(jSONArray);
            return validationResult;
        }
        JSONArray jSONArray3 = new JSONArray();
        HashSet hashSet = new HashSet();
        int length = jSONArray.length();
        int length2 = jSONArray2.length();
        BitSet bitSet = z ? null : new BitSet(length + length2);
        int iScan = scan(jSONArray2, hashSet, bitSet, length);
        int iScan2 = 0;
        if (!z && hashSet.size() < 100) {
            iScan2 = scan(jSONArray, hashSet, bitSet, 0);
        }
        for (int i = iScan2; i < length; i++) {
            if (z) {
                try {
                    String str2 = (String) jSONArray.get(i);
                    if (!hashSet.contains(str2)) {
                        jSONArray3.put(str2);
                    }
                } catch (Throwable unused) {
                }
            } else if (!bitSet.get(i)) {
                jSONArray3.put(jSONArray.get(i));
            }
        }
        if (!z && jSONArray3.length() < 100) {
            for (int i2 = iScan; i2 < length2; i2++) {
                try {
                    if (!bitSet.get(i2 + length)) {
                        jSONArray3.put(jSONArray2.get(i2));
                    }
                } catch (Throwable unused2) {
                }
            }
        }
        if (iScan > 0 || iScan2 > 0) {
            ValidationResult validationResultCreate = ValidationResultFactory.create(521, 12, str, "100");
            validationResult.setErrorCode(validationResultCreate.getErrorCode());
            validationResult.setErrorDesc(validationResultCreate.getErrorDesc());
        }
        validationResult.setObject(jSONArray3);
        return validationResult;
    }

    private int scan(JSONArray jSONArray, Set<String> set, BitSet bitSet, int i) {
        if (jSONArray == null) {
            return 0;
        }
        for (int length = jSONArray.length() - 1; length >= 0; length--) {
            try {
                Object obj = jSONArray.get(length);
                String string = obj != null ? obj.toString() : null;
                if (bitSet == null) {
                    if (string != null) {
                        set.add(string);
                    }
                } else if (string == null || set.contains(string)) {
                    bitSet.set(length + i, true);
                } else {
                    set.add(string);
                    if (set.size() == 100) {
                        return length;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return 0;
    }
}
