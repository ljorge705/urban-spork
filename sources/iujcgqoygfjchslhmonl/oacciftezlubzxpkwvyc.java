package iujcgqoygfjchslhmonl;

import ahpfbhknxzgojyggofxj.wfdoaqfvfyoijpgclxfu;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class oacciftezlubzxpkwvyc {
    public static DecimalFormat yvrzbryuycempgkdhpvj = new DecimalFormat("#.##");

    public static HashMap ctfsaqlysacfjtqixtmr(JSONObject jSONObject, String str) throws JSONException {
        JSONObject jSONObject2;
        HashMap map = new HashMap();
        if (jSONObject.has(str)) {
            try {
                jSONObject2 = jSONObject.getJSONObject(str);
            } catch (JSONException unused) {
                jSONObject2 = null;
            }
            if (jSONObject2 != null) {
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    try {
                        jSONObject2.getJSONArray(next);
                    } catch (JSONException unused2) {
                    }
                    map.put(next, new HashSet(yvrzbryuycempgkdhpvj(jSONObject2, next)));
                }
            }
        }
        return map;
    }

    public static JSONObject danumarvmgpbarrzqyrz(JSONObject jSONObject, String str) {
        if (jSONObject.has(str)) {
            try {
                return jSONObject.getJSONObject(str);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public static Set dbuymyhwehsdoxafsfpy(JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArray;
        HashSet hashSet = new HashSet();
        if (jSONObject.has(str)) {
            try {
                jSONArray = jSONObject.getJSONArray(str);
            } catch (JSONException unused) {
                jSONArray = null;
            }
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        hashSet.add(jSONArray.get(i));
                    } catch (JSONException unused2) {
                    }
                }
            }
        }
        return hashSet;
    }

    public static JSONArray dyrapphjndqarxdhyvgv(JSONObject jSONObject, String str) {
        return yvrzbryuycempgkdhpvj(jSONObject, str, (JSONArray) null);
    }

    public static ArrayList efmnkxwvqeqnaehsyess(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                ArrayList arrayList = new ArrayList();
                Iterator it = jSONArray.iterator();
                while (it.hasNext()) {
                    arrayList.add((Integer) it.next());
                }
                return arrayList;
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public static String ldeiitcdqlqzkidvrbjy(JSONObject jSONObject, String str) {
        return yvrzbryuycempgkdhpvj(jSONObject, str, (String) null);
    }

    public static Integer mxodkqpwhcryvsgsvabl(JSONObject jSONObject, String str) {
        return yvrzbryuycempgkdhpvj(jSONObject, str, (Integer) null);
    }

    public static JSONObject ooztjhejjvpgrdhjnyju(JSONObject jSONObject, String str) {
        if (jSONObject.has(str)) {
            try {
                return jSONObject.getJSONObject(str);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public static Boolean uusbetktgiikylwfbevs(JSONObject jSONObject, String str) {
        if (jSONObject.has(str)) {
            try {
                return Boolean.valueOf(jSONObject.getBoolean(str));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public static Long vikftlgmuszlvyjnlikz(JSONObject jSONObject, String str) {
        return yvrzbryuycempgkdhpvj(jSONObject, str, (Long) null);
    }

    public static Double vjgujdxqyzpnlimdrvvt(JSONObject jSONObject, String str) {
        return yvrzbryuycempgkdhpvj(jSONObject, str, (Double) null);
    }

    public static List yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has(str)) {
            try {
                jSONArray = jSONObject.getJSONArray(str);
            } catch (JSONException unused) {
                jSONArray = null;
            }
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        arrayList.add(jSONArray.get(i));
                    } catch (JSONException unused2) {
                    }
                }
            }
        }
        return arrayList;
    }

    public static Boolean yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str, boolean z) {
        Boolean boolUusbetktgiikylwfbevs = uusbetktgiikylwfbevs(jSONObject, str);
        return boolUusbetktgiikylwfbevs == null ? Boolean.valueOf(z) : boolUusbetktgiikylwfbevs;
    }

    public static Double yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str, Double d) {
        if (!jSONObject.has(str)) {
            return d;
        }
        try {
            return Double.valueOf(jSONObject.getDouble(str));
        } catch (JSONException unused) {
            return d;
        }
    }

    public static Object yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str, Class cls, Object obj) {
        String strLdeiitcdqlqzkidvrbjy = ldeiitcdqlqzkidvrbjy(jSONObject, str);
        if (strLdeiitcdqlqzkidvrbjy == null) {
            return obj;
        }
        Object obj2 = null;
        for (Object obj3 : cls.getEnumConstants()) {
            if (obj3.toString().equalsIgnoreCase(strLdeiitcdqlqzkidvrbjy)) {
                obj2 = obj3;
            }
        }
        return obj2 == null ? obj : obj2;
    }

    public static Integer yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str, Integer num) {
        if (!jSONObject.has(str)) {
            return num;
        }
        try {
            return Integer.valueOf(jSONObject.getInt(str));
        } catch (JSONException unused) {
            return num;
        }
    }

    public static JSONArray yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str, JSONArray jSONArray) {
        if (!jSONObject.has(str)) {
            return jSONArray;
        }
        try {
            return jSONObject.getJSONArray(str);
        } catch (JSONException unused) {
            return jSONArray;
        }
    }

    public static Long yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str, Long l) {
        if (!jSONObject.has(str)) {
            return l;
        }
        try {
            return Long.valueOf(jSONObject.getLong(str));
        } catch (JSONException unused) {
            return l;
        }
    }

    public static wfdoaqfvfyoijpgclxfu yvrzbryuycempgkdhpvj(JSONObject jSONObject, wfdoaqfvfyoijpgclxfu wfdoaqfvfyoijpgclxfuVar) {
        if (!jSONObject.has("risk_level")) {
            return wfdoaqfvfyoijpgclxfuVar;
        }
        try {
            return wfdoaqfvfyoijpgclxfu.valueOf(jSONObject.getString("risk_level"));
        } catch (JSONException unused) {
            return wfdoaqfvfyoijpgclxfuVar;
        }
    }

    public static String yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str, String str2) {
        if (!jSONObject.has(str)) {
            return str2;
        }
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return str2;
        }
    }

    public static Object yvrzbryuycempgkdhpvj(String str, Class cls) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return objectMapper.readValue(str, cls);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object yvrzbryuycempgkdhpvj(JSONObject jSONObject, Class cls) {
        return yvrzbryuycempgkdhpvj(jSONObject.toString(), cls);
    }
}
