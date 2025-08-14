package dialoobnhfjfdhmrftlq;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class lmqztflyazmtuunswyyl implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private wfdoaqfvfyoijpgclxfu dbuymyhwehsdoxafsfpy;
    private String yvrzbryuycempgkdhpvj;

    public lmqztflyazmtuunswyyl() {
        this.yvrzbryuycempgkdhpvj = null;
        this.dbuymyhwehsdoxafsfpy = new wfdoaqfvfyoijpgclxfu();
    }

    private wfdoaqfvfyoijpgclxfu yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
        return new wfdoaqfvfyoijpgclxfu(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "ipAddress"), iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "userAgent"), iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "acceptLanguage"), iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "sessionAge"), iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "cookie"), iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "session_id"));
    }

    public lmqztflyazmtuunswyyl(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "ip");
        if (jSONObject.has("device_protocol")) {
            try {
                this.dbuymyhwehsdoxafsfpy = yvrzbryuycempgkdhpvj(jSONObject.getJSONObject("device_protocol"));
            } catch (JSONException unused) {
            }
        }
    }
}
