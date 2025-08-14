package dialoobnhfjfdhmrftlq;

import io.sentry.TraceContext;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pzqcxkrstpkgvuxxlors implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private boolean ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private boolean mxodkqpwhcryvsgsvabl;
    private String uusbetktgiikylwfbevs;
    private boolean vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public pzqcxkrstpkgvuxxlors() {
        this.mxodkqpwhcryvsgsvabl = false;
        this.ctfsaqlysacfjtqixtmr = false;
        this.vjgujdxqyzpnlimdrvvt = false;
    }

    public String toString() {
        return yvrzbryuycempgkdhpvj().toString();
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TraceContext.JsonKeys.USER_ID, this.yvrzbryuycempgkdhpvj);
            jSONObject.put("device_id", this.dbuymyhwehsdoxafsfpy);
            jSONObject.put("paymentMethodId", this.uusbetktgiikylwfbevs);
            jSONObject.put("is_user_verified_by_time", this.ctfsaqlysacfjtqixtmr);
            jSONObject.put("is_device_verified_by_time", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("is_payment_method_verified_by_time", this.mxodkqpwhcryvsgsvabl);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public pzqcxkrstpkgvuxxlors(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, TraceContext.JsonKeys.USER_ID);
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "device_id");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "paymentMethodId");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_user_verified_by_time").booleanValue();
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_device_verified_by_time").booleanValue();
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_payment_method_verified_by_time").booleanValue();
    }
}
