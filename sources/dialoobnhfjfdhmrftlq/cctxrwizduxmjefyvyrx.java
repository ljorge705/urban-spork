package dialoobnhfjfdhmrftlq;

import io.sentry.TraceContext;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class cctxrwizduxmjefyvyrx implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Long ctfsaqlysacfjtqixtmr;
    private Integer dbuymyhwehsdoxafsfpy;
    private Long uusbetktgiikylwfbevs;
    private String yvrzbryuycempgkdhpvj;

    public cctxrwizduxmjefyvyrx() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.yvrzbryuycempgkdhpvj.equals(((cctxrwizduxmjefyvyrx) obj).yvrzbryuycempgkdhpvj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "{\n\t \"userId\": \"" + this.yvrzbryuycempgkdhpvj + "\",\n\t\"firstTimestamp\": " + this.ctfsaqlysacfjtqixtmr + ",\n\t\"lastTimestamp\": \"" + this.uusbetktgiikylwfbevs + "\"\n}";
    }

    public cctxrwizduxmjefyvyrx(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, TraceContext.JsonKeys.USER_ID);
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "channel_id");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "last_timestamp");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "first_timestamp");
    }
}
