package fwqklbhgchkohvxputfv;

import com.clevertap.android.sdk.db.Column;
import io.sentry.protocol.Response;
import iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc;
import org.json.JSONException;
import org.json.JSONObject;
import tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx;

/* loaded from: classes6.dex */
public class fdwipeifdmvqsbqrrpyp implements rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private Integer dbuymyhwehsdoxafsfpy;
    private transient JSONObject dyrapphjndqarxdhyvgv;
    private String efmnkxwvqeqnaehsyess;
    private String mxodkqpwhcryvsgsvabl;
    private Double uusbetktgiikylwfbevs;
    private String vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public fdwipeifdmvqsbqrrpyp() {
        this.mxodkqpwhcryvsgsvabl = "OK";
    }

    public String toString() {
        return yvrzbryuycempgkdhpvj().toString();
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("risk_score", this.uusbetktgiikylwfbevs);
            jSONObject.put("requestID", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("signedRisk", this.ctfsaqlysacfjtqixtmr);
            jSONObject.put(Response.TYPE, this.mxodkqpwhcryvsgsvabl);
            jSONObject.put(Column.DEVICE_ID, this.efmnkxwvqeqnaehsyess);
            jSONObject.put("risk_level", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("risk", this.dbuymyhwehsdoxafsfpy);
            jSONObject.put("checkpoint_data", this.dyrapphjndqarxdhyvgv);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public fdwipeifdmvqsbqrrpyp(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.uusbetktgiikylwfbevs = oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "risk_score");
        this.ctfsaqlysacfjtqixtmr = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "signedRisk");
        this.yvrzbryuycempgkdhpvj = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "risk_level");
        this.dbuymyhwehsdoxafsfpy = oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "risk");
        this.vjgujdxqyzpnlimdrvvt = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "requestID");
        this.efmnkxwvqeqnaehsyess = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, Column.DEVICE_ID);
        this.mxodkqpwhcryvsgsvabl = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, Response.TYPE);
        this.dyrapphjndqarxdhyvgv = oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "checkpoint_data");
    }
}
