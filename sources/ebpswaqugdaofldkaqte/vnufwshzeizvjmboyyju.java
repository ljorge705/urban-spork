package ebpswaqugdaofldkaqte;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class vnufwshzeizvjmboyyju extends oacciftezlubzxpkwvyc {
    protected fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai ctfsaqlysacfjtqixtmr;
    protected fsewsnhlwjlpcrfwmizu.wfdoaqfvfyoijpgclxfu vjgujdxqyzpnlimdrvvt;

    protected vnufwshzeizvjmboyyju() {
        super(zwlltpaufqribmleigux.VIEW_CARD_DETAILS);
        this.ctfsaqlysacfjtqixtmr = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai();
        this.vjgujdxqyzpnlimdrvvt = new fsewsnhlwjlpcrfwmizu.wfdoaqfvfyoijpgclxfu();
    }

    public vnufwshzeizvjmboyyju(JSONObject jSONObject) {
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
        this.vjgujdxqyzpnlimdrvvt = new fsewsnhlwjlpcrfwmizu.wfdoaqfvfyoijpgclxfu(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "creditCardDetail"));
    }
}
