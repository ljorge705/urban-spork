package hbmifujbkwcjpgteyixs;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class qazlvjxyyxbchcvecwjp implements Serializable {
    private boolean dbuymyhwehsdoxafsfpy;
    private int yvrzbryuycempgkdhpvj;

    public qazlvjxyyxbchcvecwjp(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null || !jSONObject.has("email_verification_info")) {
            return;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("email_verification_info");
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject2, "listVersion", (Integer) 0).intValue();
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject2, "lastDomainVerificationResult", false).booleanValue();
    }
}
