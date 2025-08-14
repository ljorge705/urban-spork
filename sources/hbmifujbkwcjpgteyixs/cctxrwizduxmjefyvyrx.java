package hbmifujbkwcjpgteyixs;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class cctxrwizduxmjefyvyrx implements Serializable {
    private String dbuymyhwehsdoxafsfpy;
    private String yvrzbryuycempgkdhpvj;

    public cctxrwizduxmjefyvyrx(String str, String str2) {
        this.yvrzbryuycempgkdhpvj = str;
        this.dbuymyhwehsdoxafsfpy = str2;
    }

    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("btName", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("btAddress", this.dbuymyhwehsdoxafsfpy);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public cctxrwizduxmjefyvyrx(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "btName");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "btAddress");
    }
}
