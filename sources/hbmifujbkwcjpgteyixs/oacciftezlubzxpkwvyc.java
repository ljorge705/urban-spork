package hbmifujbkwcjpgteyixs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class oacciftezlubzxpkwvyc implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private List dbuymyhwehsdoxafsfpy;
    private String yvrzbryuycempgkdhpvj;

    public oacciftezlubzxpkwvyc() {
        this.dbuymyhwehsdoxafsfpy = new ArrayList();
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("account", this.yvrzbryuycempgkdhpvj);
            if (this.dbuymyhwehsdoxafsfpy != null) {
                jSONObject.put("contacts", new JSONArray((Collection) this.dbuymyhwehsdoxafsfpy));
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public void yvrzbryuycempgkdhpvj(String str) {
        this.yvrzbryuycempgkdhpvj = str;
    }

    public void yvrzbryuycempgkdhpvj(List list) {
        this.dbuymyhwehsdoxafsfpy = list;
    }

    public oacciftezlubzxpkwvyc(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "account");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "contacts");
    }
}
