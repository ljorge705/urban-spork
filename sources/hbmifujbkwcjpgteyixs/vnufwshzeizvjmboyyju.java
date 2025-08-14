package hbmifujbkwcjpgteyixs;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class vnufwshzeizvjmboyyju implements Serializable {
    String dbuymyhwehsdoxafsfpy;
    String yvrzbryuycempgkdhpvj;

    public vnufwshzeizvjmboyyju(String str, String str2) {
        this.yvrzbryuycempgkdhpvj = str;
        this.dbuymyhwehsdoxafsfpy = str2;
    }

    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.dbuymyhwehsdoxafsfpy);
            jSONObject.put("hashCode", this.yvrzbryuycempgkdhpvj);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public vnufwshzeizvjmboyyju(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "hashCode");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "id");
    }
}
