package iujcgqoygfjchslhmonl;

import buermssrvkevhtyundwb.lmqztflyazmtuunswyyl;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class fdwipeifdmvqsbqrrpyp {
    public static lmqztflyazmtuunswyyl dbuymyhwehsdoxafsfpy(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        try {
            try {
                return new lmqztflyazmtuunswyyl(jSONObject.getJSONObject(str));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IllegalArgumentException e2) {
            System.out.println("from GeneralSdkDataUtils: " + e2.getMessage());
            return null;
        }
    }

    public static buermssrvkevhtyundwb.oacciftezlubzxpkwvyc yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        try {
            try {
                return new buermssrvkevhtyundwb.oacciftezlubzxpkwvyc(jSONObject.getJSONObject(str));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IllegalArgumentException e2) {
            System.out.println("from GeneralSdkDataUtils: " + e2.getMessage());
            return null;
        }
    }
}
