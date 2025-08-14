package ebpswaqugdaofldkaqte;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class cdabktqulpsnjrlnlnii extends oacciftezlubzxpkwvyc {
    protected fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai ctfsaqlysacfjtqixtmr;
    private String efmnkxwvqeqnaehsyess;
    private String mxodkqpwhcryvsgsvabl;
    protected String vjgujdxqyzpnlimdrvvt;

    protected cdabktqulpsnjrlnlnii() {
        super(zwlltpaufqribmleigux.LOGIN);
        this.ctfsaqlysacfjtqixtmr = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai();
    }

    public cdabktqulpsnjrlnlnii(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has("additionalData")) {
            try {
                this.dbuymyhwehsdoxafsfpy = jSONObject.getJSONObject("additionalData");
            } catch (JSONException unused) {
            }
        }
        this.uusbetktgiikylwfbevs = fsewsnhlwjlpcrfwmizu.lmqztflyazmtuunswyyl.valueOf(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isDeviceVerified", fsewsnhlwjlpcrfwmizu.lmqztflyazmtuunswyyl.UNKNOWN.toString()));
        this.ctfsaqlysacfjtqixtmr = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "user"));
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "authenticationMethod");
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "status");
        this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "status_reason");
    }
}
