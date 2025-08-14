package uqgffrurkpkmcepvnfkx;

import io.sentry.ProfilingTraceData;
import iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx;

/* loaded from: classes7.dex */
public class fdwipeifdmvqsbqrrpyp implements Serializable, rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private Long dyrapphjndqarxdhyvgv;
    private Integer efmnkxwvqeqnaehsyess;
    private String mxodkqpwhcryvsgsvabl;
    private String uusbetktgiikylwfbevs;
    private String vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public fdwipeifdmvqsbqrrpyp() {
    }

    public void ctfsaqlysacfjtqixtmr(String str) {
        this.mxodkqpwhcryvsgsvabl = str;
    }

    public void dbuymyhwehsdoxafsfpy(String str) {
        this.uusbetktgiikylwfbevs = str;
    }

    public void mxodkqpwhcryvsgsvabl(String str) {
        this.dbuymyhwehsdoxafsfpy = str;
    }

    public void uusbetktgiikylwfbevs(String str) {
        this.yvrzbryuycempgkdhpvj = str;
    }

    public void vjgujdxqyzpnlimdrvvt(String str) {
        this.ctfsaqlysacfjtqixtmr = str;
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error_stack", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("version", this.dbuymyhwehsdoxafsfpy);
            jSONObject.put(ProfilingTraceData.JsonKeys.DEVICE_MODEL, this.uusbetktgiikylwfbevs);
            jSONObject.put("sdk_version", this.ctfsaqlysacfjtqixtmr);
            jSONObject.put("cause", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("error_code", this.efmnkxwvqeqnaehsyess);
            jSONObject.put("error_type", this.mxodkqpwhcryvsgsvabl);
            jSONObject.put("error_timestamp", this.dyrapphjndqarxdhyvgv);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void yvrzbryuycempgkdhpvj(Long l) {
        this.dyrapphjndqarxdhyvgv = l;
    }

    public void yvrzbryuycempgkdhpvj(String str) {
        this.vjgujdxqyzpnlimdrvvt = str;
    }

    public fdwipeifdmvqsbqrrpyp(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "error_stack");
        this.dbuymyhwehsdoxafsfpy = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "version");
        this.uusbetktgiikylwfbevs = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, ProfilingTraceData.JsonKeys.DEVICE_MODEL);
        this.ctfsaqlysacfjtqixtmr = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "sdk_version");
        this.vjgujdxqyzpnlimdrvvt = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "cause");
        this.mxodkqpwhcryvsgsvabl = oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "error_type");
        this.efmnkxwvqeqnaehsyess = oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "error_code");
        this.dyrapphjndqarxdhyvgv = oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "error_timestamp");
    }

    public void yvrzbryuycempgkdhpvj(int i) {
        this.efmnkxwvqeqnaehsyess = Integer.valueOf(i);
    }
}
