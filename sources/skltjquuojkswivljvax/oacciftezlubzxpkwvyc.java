package skltjquuojkswivljvax;

import io.sentry.TraceContext;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class oacciftezlubzxpkwvyc extends rvhplcmttaqkggggovhx implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private ahpfbhknxzgojyggofxj.oacciftezlubzxpkwvyc efmnkxwvqeqnaehsyess;
    private String mxodkqpwhcryvsgsvabl;
    private String uusbetktgiikylwfbevs;
    private Double vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public oacciftezlubzxpkwvyc() {
    }

    public oacciftezlubzxpkwvyc(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, TraceContext.JsonKeys.USER_ID);
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "device_id");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "checkpoint");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "requestID");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "amount");
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "curType");
        if (jSONObject.has("actionType")) {
            try {
                this.efmnkxwvqeqnaehsyess = ahpfbhknxzgojyggofxj.oacciftezlubzxpkwvyc.valueOf(jSONObject.getString("actionType"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
