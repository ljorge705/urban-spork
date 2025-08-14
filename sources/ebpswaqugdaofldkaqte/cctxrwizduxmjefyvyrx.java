package ebpswaqugdaofldkaqte;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class cctxrwizduxmjefyvyrx extends oacciftezlubzxpkwvyc {
    protected fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai ctfsaqlysacfjtqixtmr;
    protected fsewsnhlwjlpcrfwmizu.zwlltpaufqribmleigux vjgujdxqyzpnlimdrvvt;

    protected cctxrwizduxmjefyvyrx() {
        super(zwlltpaufqribmleigux.EXTERNAL_CARD_TRANSACTION);
        this.ctfsaqlysacfjtqixtmr = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai();
        this.vjgujdxqyzpnlimdrvvt = new fsewsnhlwjlpcrfwmizu.zwlltpaufqribmleigux();
    }

    public cctxrwizduxmjefyvyrx(JSONObject jSONObject) {
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
        this.vjgujdxqyzpnlimdrvvt = new fsewsnhlwjlpcrfwmizu.zwlltpaufqribmleigux(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "cardTransaction"));
    }
}
