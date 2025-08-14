package dialoobnhfjfdhmrftlq;

import io.sentry.TraceContext;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class fdwipeifdmvqsbqrrpyp implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private boolean cwzojhoqdsccekmlpbcq;
    private boolean danumarvmgpbarrzqyrz;
    private String dbuymyhwehsdoxafsfpy;
    private List dyrapphjndqarxdhyvgv;
    private String efmnkxwvqeqnaehsyess;
    private boolean ldeiitcdqlqzkidvrbjy;
    private String mxodkqpwhcryvsgsvabl;
    private boolean ooztjhejjvpgrdhjnyju;
    private Integer uusbetktgiikylwfbevs;
    private boolean vikftlgmuszlvyjnlikz;
    private String vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public fdwipeifdmvqsbqrrpyp() {
        this.ooztjhejjvpgrdhjnyju = false;
        this.danumarvmgpbarrzqyrz = false;
        this.vikftlgmuszlvyjnlikz = false;
        this.ldeiitcdqlqzkidvrbjy = false;
        this.cwzojhoqdsccekmlpbcq = false;
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
            jSONObject.put("device_id", this.ctfsaqlysacfjtqixtmr);
            jSONObject.put("ip", this.mxodkqpwhcryvsgsvabl);
            jSONObject.put("paymentMethodId", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("destinationId", this.efmnkxwvqeqnaehsyess);
            jSONObject.put("fraud_type_ids", (Collection) this.dyrapphjndqarxdhyvgv);
            jSONObject.put("is_ip_chargeback", this.ooztjhejjvpgrdhjnyju);
            jSONObject.put("is_user_chargeback", this.danumarvmgpbarrzqyrz);
            jSONObject.put("is_device_chargeback", this.vikftlgmuszlvyjnlikz);
            jSONObject.put("is_payment_method_chargeback", this.ldeiitcdqlqzkidvrbjy);
            jSONObject.put("is_destination_chargeback", this.cwzojhoqdsccekmlpbcq);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public fdwipeifdmvqsbqrrpyp(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, TraceContext.JsonKeys.USER_ID);
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "channel_id");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "device_id");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "paymentMethodId");
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "requestID");
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "ip");
        this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "destinationId");
        this.dyrapphjndqarxdhyvgv = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.efmnkxwvqeqnaehsyess(jSONObject, "fraud_type_ids");
        this.ooztjhejjvpgrdhjnyju = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_ip_chargeback").booleanValue();
        this.danumarvmgpbarrzqyrz = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_user_chargeback").booleanValue();
        this.vikftlgmuszlvyjnlikz = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_device_chargeback").booleanValue();
        this.ldeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_payment_method_chargeback").booleanValue();
        this.cwzojhoqdsccekmlpbcq = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "is_destination_chargeback", false).booleanValue();
    }
}
