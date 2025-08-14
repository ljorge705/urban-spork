package ebpswaqugdaofldkaqte;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class lmqztflyazmtuunswyyl extends oacciftezlubzxpkwvyc {
    Long ctfsaqlysacfjtqixtmr;
    protected fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai vjgujdxqyzpnlimdrvvt;

    public lmqztflyazmtuunswyyl() {
        super(zwlltpaufqribmleigux.PASSWORD_CHANGE);
        this.vjgujdxqyzpnlimdrvvt = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai();
    }

    public lmqztflyazmtuunswyyl(JSONObject jSONObject) {
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
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "lastChangePasswordTime");
        this.uusbetktgiikylwfbevs = fsewsnhlwjlpcrfwmizu.lmqztflyazmtuunswyyl.valueOf(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isDeviceVerified", fsewsnhlwjlpcrfwmizu.lmqztflyazmtuunswyyl.UNKNOWN.toString()));
        this.vjgujdxqyzpnlimdrvvt = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "user"));
    }
}
