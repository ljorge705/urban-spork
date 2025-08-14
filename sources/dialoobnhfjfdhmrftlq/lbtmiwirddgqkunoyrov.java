package dialoobnhfjfdhmrftlq;

import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class lbtmiwirddgqkunoyrov implements Serializable {
    private Double ctfsaqlysacfjtqixtmr;
    private Long dbuymyhwehsdoxafsfpy;
    private Long uusbetktgiikylwfbevs;
    private Double vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public lbtmiwirddgqkunoyrov() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.yvrzbryuycempgkdhpvj.equals(((lbtmiwirddgqkunoyrov) obj).yvrzbryuycempgkdhpvj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "{\n\t \"deviceId\": \"" + this.yvrzbryuycempgkdhpvj + "\",\n\t\"firstTimestamp\": " + this.uusbetktgiikylwfbevs + ",\n\t\"lastTimestamp\": \"" + this.dbuymyhwehsdoxafsfpy + "\"\n\"latitude\": \"" + this.ctfsaqlysacfjtqixtmr + "\"\n\"longitude\": \"" + this.vjgujdxqyzpnlimdrvvt + "\"\n}";
    }

    public lbtmiwirddgqkunoyrov(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "device_id");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "last_timestamp");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "first_timestamp");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "latitude");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "longitude");
    }
}
