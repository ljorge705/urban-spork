package hbmifujbkwcjpgteyixs;

import io.sentry.protocol.Device;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class fdwipeifdmvqsbqrrpyp implements Serializable, tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private String uusbetktgiikylwfbevs;
    private String vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public fdwipeifdmvqsbqrrpyp(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, Device.JsonKeys.LANGUAGE);
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "systemLocale");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "defaultTimeZone");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "networkCountryIso");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "networkOperatorName");
    }

    public String ctfsaqlysacfjtqixtmr() {
        return this.ctfsaqlysacfjtqixtmr;
    }

    public String dbuymyhwehsdoxafsfpy() {
        return this.uusbetktgiikylwfbevs;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fdwipeifdmvqsbqrrpyp)) {
            return false;
        }
        fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar = (fdwipeifdmvqsbqrrpyp) obj;
        return uusbetktgiikylwfbevs().equals(fdwipeifdmvqsbqrrpypVar.uusbetktgiikylwfbevs()) && dbuymyhwehsdoxafsfpy().equals(fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy()) && ctfsaqlysacfjtqixtmr().equals(fdwipeifdmvqsbqrrpypVar.ctfsaqlysacfjtqixtmr()) && mxodkqpwhcryvsgsvabl().equals(fdwipeifdmvqsbqrrpypVar.mxodkqpwhcryvsgsvabl()) && vjgujdxqyzpnlimdrvvt().equals(fdwipeifdmvqsbqrrpypVar.vjgujdxqyzpnlimdrvvt());
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String mxodkqpwhcryvsgsvabl() {
        return this.dbuymyhwehsdoxafsfpy;
    }

    public String uusbetktgiikylwfbevs() {
        return this.yvrzbryuycempgkdhpvj;
    }

    public String vjgujdxqyzpnlimdrvvt() {
        return this.vjgujdxqyzpnlimdrvvt;
    }
}
