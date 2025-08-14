package ebpswaqugdaofldkaqte;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class ppvnkbmzfphuuihfhotp extends oacciftezlubzxpkwvyc {
    protected fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai ctfsaqlysacfjtqixtmr;

    public ppvnkbmzfphuuihfhotp() {
        super(zwlltpaufqribmleigux.REGISTER);
        this.ctfsaqlysacfjtqixtmr = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai();
    }

    public ppvnkbmzfphuuihfhotp(JSONObject jSONObject) {
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
    }
}
