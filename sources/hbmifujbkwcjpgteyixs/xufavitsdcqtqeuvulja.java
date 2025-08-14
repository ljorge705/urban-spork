package hbmifujbkwcjpgteyixs;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class xufavitsdcqtqeuvulja implements Serializable {
    private Long dbuymyhwehsdoxafsfpy;
    private String yvrzbryuycempgkdhpvj;

    public xufavitsdcqtqeuvulja(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "id");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "first_timestamp");
    }

    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("first_timestamp", this.dbuymyhwehsdoxafsfpy);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
