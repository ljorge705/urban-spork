package dialoobnhfjfdhmrftlq;

import io.sentry.TraceContext;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nlkeiuewguxtleppgqdg implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private boolean ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private String uusbetktgiikylwfbevs;
    private String yvrzbryuycempgkdhpvj;

    public nlkeiuewguxtleppgqdg() {
        this.ctfsaqlysacfjtqixtmr = false;
    }

    public String toString() {
        return yvrzbryuycempgkdhpvj().toString();
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestID", this.yvrzbryuycempgkdhpvj);
            jSONObject.put(TraceContext.JsonKeys.USER_ID, this.dbuymyhwehsdoxafsfpy);
            jSONObject.put("device_id", this.uusbetktgiikylwfbevs);
            jSONObject.put("is_whitelisted", this.ctfsaqlysacfjtqixtmr);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public nlkeiuewguxtleppgqdg(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "requestID");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, TraceContext.JsonKeys.USER_ID);
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "device_id");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_whitelisted").booleanValue();
    }
}
