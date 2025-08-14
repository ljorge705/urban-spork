package ebpswaqugdaofldkaqte;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class llyhweatjmvlzlflpaeb implements Serializable {
    protected String dbuymyhwehsdoxafsfpy;
    protected rvhplcmttaqkggggovhx uusbetktgiikylwfbevs;
    protected String yvrzbryuycempgkdhpvj;

    public llyhweatjmvlzlflpaeb() {
    }

    public llyhweatjmvlzlflpaeb(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "requestID");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "transactionID");
        if (jSONObject.has("status")) {
            try {
                this.uusbetktgiikylwfbevs = rvhplcmttaqkggggovhx.valueOf(jSONObject.getString("status"));
            } catch (JSONException unused) {
            }
        }
    }
}
