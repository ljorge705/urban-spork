package fsewsnhlwjlpcrfwmizu;

import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ymdxlfsorgvechaxkrll implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    protected String ctfsaqlysacfjtqixtmr;
    protected String dbuymyhwehsdoxafsfpy;
    protected cdabktqulpsnjrlnlnii efmnkxwvqeqnaehsyess;
    protected rvhplcmttaqkggggovhx mxodkqpwhcryvsgsvabl;
    protected String uusbetktgiikylwfbevs;
    protected wfdoaqfvfyoijpgclxfu vjgujdxqyzpnlimdrvvt;
    protected mchrzzmrgfkrwlaxrlna yvrzbryuycempgkdhpvj;

    public ymdxlfsorgvechaxkrll() {
    }

    public ymdxlfsorgvechaxkrll(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "paymentMethod");
        if (strLdeiitcdqlqzkidvrbjy != null) {
            try {
                this.yvrzbryuycempgkdhpvj = mchrzzmrgfkrwlaxrlna.valueOf(strLdeiitcdqlqzkidvrbjy);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "paymentMethodId");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "processor");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "fullNameOnCard");
        this.vjgujdxqyzpnlimdrvvt = new wfdoaqfvfyoijpgclxfu(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "creditCardDetail"));
        this.mxodkqpwhcryvsgsvabl = new rvhplcmttaqkggggovhx(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "bankAccountDetails"));
        this.efmnkxwvqeqnaehsyess = new cdabktqulpsnjrlnlnii(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "POSDetails"));
    }
}
