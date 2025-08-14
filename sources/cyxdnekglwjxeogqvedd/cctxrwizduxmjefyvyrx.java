package cyxdnekglwjxeogqvedd;

import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class cctxrwizduxmjefyvyrx implements Serializable {
    private String ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private String uusbetktgiikylwfbevs;
    private wfdoaqfvfyoijpgclxfu vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public cctxrwizduxmjefyvyrx() {
        this.yvrzbryuycempgkdhpvj = null;
        this.dbuymyhwehsdoxafsfpy = null;
        this.uusbetktgiikylwfbevs = null;
        this.ctfsaqlysacfjtqixtmr = null;
        this.vjgujdxqyzpnlimdrvvt = wfdoaqfvfyoijpgclxfu.AWS_RECKOGNITION;
    }

    public cctxrwizduxmjefyvyrx(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "awsAccessKey");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "awsSecretKey");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "scanovateURL");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "regions");
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "verificationVendor");
        if (strLdeiitcdqlqzkidvrbjy != null) {
            this.vjgujdxqyzpnlimdrvvt = wfdoaqfvfyoijpgclxfu.valueOf(strLdeiitcdqlqzkidvrbjy);
        }
    }
}
