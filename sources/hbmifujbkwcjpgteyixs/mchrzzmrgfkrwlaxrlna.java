package hbmifujbkwcjpgteyixs;

import io.sentry.protocol.ViewHierarchyNode;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mchrzzmrgfkrwlaxrlna implements Serializable {
    private String dbuymyhwehsdoxafsfpy;
    private String uusbetktgiikylwfbevs;
    private String yvrzbryuycempgkdhpvj;

    public mchrzzmrgfkrwlaxrlna(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "account");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, ViewHierarchyNode.JsonKeys.IDENTIFIER);
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "title");
    }

    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("account", this.yvrzbryuycempgkdhpvj);
            jSONObject.put(ViewHierarchyNode.JsonKeys.IDENTIFIER, this.dbuymyhwehsdoxafsfpy);
            jSONObject.put("title", this.uusbetktgiikylwfbevs);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
