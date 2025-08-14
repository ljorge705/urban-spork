package fsewsnhlwjlpcrfwmizu;

import com.onfido.android.sdk.capture.config.MediaCallbackResultReceiver;
import ebpswaqugdaofldkaqte.vubdyxzvpnvcymakzopn;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class zwlltpaufqribmleigux implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    protected String ctfsaqlysacfjtqixtmr;
    protected ebpswaqugdaofldkaqte.wfdoaqfvfyoijpgclxfu dbuymyhwehsdoxafsfpy;
    protected vubdyxzvpnvcymakzopn efmnkxwvqeqnaehsyess;
    protected Double mxodkqpwhcryvsgsvabl;
    protected String uusbetktgiikylwfbevs;
    protected String vjgujdxqyzpnlimdrvvt;
    protected String yvrzbryuycempgkdhpvj;

    public zwlltpaufqribmleigux() {
    }

    public zwlltpaufqribmleigux(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "cardId");
        if (jSONObject.has("curType")) {
            try {
                this.dbuymyhwehsdoxafsfpy = ebpswaqugdaofldkaqte.wfdoaqfvfyoijpgclxfu.valueOf(jSONObject.getString("curType"));
            } catch (Exception unused) {
            }
        }
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "merchant_code");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "merchant_name");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, MediaCallbackResultReceiver.KEY_COUNTRY);
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "amount");
        if (jSONObject.has("transactionType")) {
            try {
                this.efmnkxwvqeqnaehsyess = vubdyxzvpnvcymakzopn.valueOf(jSONObject.getString("transactionType"));
            } catch (Exception unused2) {
            }
        }
    }
}
