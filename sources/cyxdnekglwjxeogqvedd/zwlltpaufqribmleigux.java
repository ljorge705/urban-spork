package cyxdnekglwjxeogqvedd;

import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class zwlltpaufqribmleigux implements Serializable {
    public static Integer ctfsaqlysacfjtqixtmr = 604800000;
    public static Integer vjgujdxqyzpnlimdrvvt = 300000;
    private Integer dbuymyhwehsdoxafsfpy;
    private Integer uusbetktgiikylwfbevs;
    private Integer yvrzbryuycempgkdhpvj;

    public zwlltpaufqribmleigux() {
        this.yvrzbryuycempgkdhpvj = ctfsaqlysacfjtqixtmr;
        Integer num = vjgujdxqyzpnlimdrvvt;
        this.dbuymyhwehsdoxafsfpy = num;
        this.uusbetktgiikylwfbevs = num;
    }

    public zwlltpaufqribmleigux(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        Integer numMxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "timeDelayDevice");
        if (numMxodkqpwhcryvsgsvabl != null) {
            this.yvrzbryuycempgkdhpvj = numMxodkqpwhcryvsgsvabl;
        }
        Integer numMxodkqpwhcryvsgsvabl2 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "timeDelayLocation");
        if (numMxodkqpwhcryvsgsvabl2 != null) {
            this.dbuymyhwehsdoxafsfpy = numMxodkqpwhcryvsgsvabl2;
        }
        Integer numMxodkqpwhcryvsgsvabl3 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "timeDelaySignal");
        if (numMxodkqpwhcryvsgsvabl3 != null) {
            this.uusbetktgiikylwfbevs = numMxodkqpwhcryvsgsvabl3;
        }
    }
}
