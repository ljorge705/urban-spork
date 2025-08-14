package ebpswaqugdaofldkaqte;

import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class fdwipeifdmvqsbqrrpyp extends oacciftezlubzxpkwvyc {
    protected fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai ctfsaqlysacfjtqixtmr;
    protected Long efmnkxwvqeqnaehsyess;
    protected fsewsnhlwjlpcrfwmizu.fdwipeifdmvqsbqrrpyp mxodkqpwhcryvsgsvabl;
    protected fsewsnhlwjlpcrfwmizu.ymdxlfsorgvechaxkrll vjgujdxqyzpnlimdrvvt;

    public fdwipeifdmvqsbqrrpyp() {
        super(zwlltpaufqribmleigux.ADD_PAYMENT_METHOD);
        this.ctfsaqlysacfjtqixtmr = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai();
        this.vjgujdxqyzpnlimdrvvt = new fsewsnhlwjlpcrfwmizu.ymdxlfsorgvechaxkrll();
        this.mxodkqpwhcryvsgsvabl = new fsewsnhlwjlpcrfwmizu.fdwipeifdmvqsbqrrpyp();
    }

    public fdwipeifdmvqsbqrrpyp(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "additionalData");
        this.ctfsaqlysacfjtqixtmr = new fsewsnhlwjlpcrfwmizu.kaqespyeeawijtnikoai(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "user"));
        this.mxodkqpwhcryvsgsvabl = new fsewsnhlwjlpcrfwmizu.fdwipeifdmvqsbqrrpyp(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "billingAddress"));
        this.vjgujdxqyzpnlimdrvvt = new fsewsnhlwjlpcrfwmizu.ymdxlfsorgvechaxkrll(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "payment"));
        this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "server_timestamp");
        this.uusbetktgiikylwfbevs = fsewsnhlwjlpcrfwmizu.lmqztflyazmtuunswyyl.valueOf(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isDeviceVerified", fsewsnhlwjlpcrfwmizu.lmqztflyazmtuunswyyl.UNKNOWN.toString()));
    }
}
