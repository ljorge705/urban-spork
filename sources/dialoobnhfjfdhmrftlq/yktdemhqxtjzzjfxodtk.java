package dialoobnhfjfdhmrftlq;

import io.sentry.TraceContext;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class yktdemhqxtjzzjfxodtk implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private boolean danumarvmgpbarrzqyrz;
    private String dbuymyhwehsdoxafsfpy;
    private boolean dyrapphjndqarxdhyvgv;
    private List efmnkxwvqeqnaehsyess;
    private boolean ldeiitcdqlqzkidvrbjy;
    private String mxodkqpwhcryvsgsvabl;
    private boolean ooztjhejjvpgrdhjnyju;
    private String uusbetktgiikylwfbevs;
    private boolean vikftlgmuszlvyjnlikz;
    private String vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public yktdemhqxtjzzjfxodtk() {
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
            jSONObject.put("ip", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("paymentMethodId", this.ctfsaqlysacfjtqixtmr);
            jSONObject.put("destinationId", this.mxodkqpwhcryvsgsvabl);
            jSONObject.put("fraud_type_ids", (Collection) this.efmnkxwvqeqnaehsyess);
            jSONObject.put("is_ip_safe", this.dyrapphjndqarxdhyvgv);
            jSONObject.put("is_user_safe", this.ooztjhejjvpgrdhjnyju);
            jSONObject.put("is_device_safe", this.danumarvmgpbarrzqyrz);
            jSONObject.put("is_payment_method_safe", this.vikftlgmuszlvyjnlikz);
            jSONObject.put("is_destination_safe", this.ldeiitcdqlqzkidvrbjy);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public yktdemhqxtjzzjfxodtk(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, TraceContext.JsonKeys.USER_ID);
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "device_id");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "paymentMethodId");
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "requestID");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "ip");
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "destinationId");
        this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.efmnkxwvqeqnaehsyess(jSONObject, "fraud_type_ids");
    }
}
