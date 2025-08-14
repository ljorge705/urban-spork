package hbmifujbkwcjpgteyixs;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class vubdyxzvpnvcymakzopn implements Serializable {
    Double dbuymyhwehsdoxafsfpy;
    Boolean uusbetktgiikylwfbevs = Boolean.FALSE;
    Long yvrzbryuycempgkdhpvj;

    public vubdyxzvpnvcymakzopn(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("fuzzy_match")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("fuzzy_match");
            this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject2, "fuzzy_id");
            this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject2, "email_score");
        }
    }
}
