package dialoobnhfjfdhmrftlq;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class rvhplcmttaqkggggovhx implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private ymdxlfsorgvechaxkrll ctfsaqlysacfjtqixtmr;
    private rzpyqrjkvhgcoquvutoh.oacciftezlubzxpkwvyc dbuymyhwehsdoxafsfpy;
    private Boolean uusbetktgiikylwfbevs;
    private cctxrwizduxmjefyvyrx vjgujdxqyzpnlimdrvvt;
    private Long yvrzbryuycempgkdhpvj;

    public rvhplcmttaqkggggovhx() {
        this.ctfsaqlysacfjtqixtmr = new ymdxlfsorgvechaxkrll();
        this.dbuymyhwehsdoxafsfpy = new rzpyqrjkvhgcoquvutoh.oacciftezlubzxpkwvyc();
        this.uusbetktgiikylwfbevs = Boolean.TRUE;
    }

    public rvhplcmttaqkggggovhx(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has("device_users")) {
            try {
                this.ctfsaqlysacfjtqixtmr = new ymdxlfsorgvechaxkrll(jSONObject.getJSONObject("device_users"));
            } catch (JSONException unused) {
            }
        }
        if (jSONObject.has("current_user")) {
            try {
                this.vjgujdxqyzpnlimdrvvt = new cctxrwizduxmjefyvyrx(jSONObject.getJSONObject("current_user"));
            } catch (JSONException unused2) {
            }
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "device_millisecond_in_system");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "is_new_device");
    }
}
