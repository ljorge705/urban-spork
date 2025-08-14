package buermssrvkevhtyundwb;

import ebpswaqugdaofldkaqte.vubdyxzvpnvcymakzopn;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kaqespyeeawijtnikoai implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private lmqztflyazmtuunswyyl ctfsaqlysacfjtqixtmr;
    private lmqztflyazmtuunswyyl cwzojhoqdsccekmlpbcq;
    private lmqztflyazmtuunswyyl danumarvmgpbarrzqyrz;
    private lmqztflyazmtuunswyyl dbuymyhwehsdoxafsfpy;
    private lmqztflyazmtuunswyyl dyrapphjndqarxdhyvgv;
    private lmqztflyazmtuunswyyl efmnkxwvqeqnaehsyess;
    private lmqztflyazmtuunswyyl ldeiitcdqlqzkidvrbjy;
    private lmqztflyazmtuunswyyl mxodkqpwhcryvsgsvabl;
    private lmqztflyazmtuunswyyl ooztjhejjvpgrdhjnyju;
    private lmqztflyazmtuunswyyl uusbetktgiikylwfbevs;
    private lmqztflyazmtuunswyyl vikftlgmuszlvyjnlikz;
    private lmqztflyazmtuunswyyl vjgujdxqyzpnlimdrvvt;
    private vubdyxzvpnvcymakzopn yvrzbryuycempgkdhpvj;

    public kaqespyeeawijtnikoai() {
        this.dbuymyhwehsdoxafsfpy = new lmqztflyazmtuunswyyl();
        this.uusbetktgiikylwfbevs = new lmqztflyazmtuunswyyl();
        this.ctfsaqlysacfjtqixtmr = new lmqztflyazmtuunswyyl();
        this.vjgujdxqyzpnlimdrvvt = new lmqztflyazmtuunswyyl();
        this.mxodkqpwhcryvsgsvabl = new lmqztflyazmtuunswyyl();
        this.efmnkxwvqeqnaehsyess = new lmqztflyazmtuunswyyl();
        this.dyrapphjndqarxdhyvgv = new lmqztflyazmtuunswyyl();
        this.ooztjhejjvpgrdhjnyju = new lmqztflyazmtuunswyyl();
        this.danumarvmgpbarrzqyrz = new lmqztflyazmtuunswyyl();
        this.vikftlgmuszlvyjnlikz = new lmqztflyazmtuunswyyl();
        this.ldeiitcdqlqzkidvrbjy = new lmqztflyazmtuunswyyl();
        this.cwzojhoqdsccekmlpbcq = new lmqztflyazmtuunswyyl();
    }

    public kaqespyeeawijtnikoai(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "hourly_transaction_aggregation");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "window_transaction_aggregation");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "weekly_transaction_aggregation");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "monthly_transaction_aggregation");
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "incoming_hourly_transaction_aggregation");
        this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "incoming_window_transaction_aggregation");
        this.dyrapphjndqarxdhyvgv = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "incoming_weekly_transaction_aggregation");
        this.ooztjhejjvpgrdhjnyju = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "incoming_monthly_transaction_aggregation");
        this.danumarvmgpbarrzqyrz = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "outgoing_hourly_transaction_aggregation");
        this.vikftlgmuszlvyjnlikz = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "outgoing_window_transaction_aggregation");
        this.ldeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "outgoing_weekly_transaction_aggregation");
        this.cwzojhoqdsccekmlpbcq = iujcgqoygfjchslhmonl.fdwipeifdmvqsbqrrpyp.dbuymyhwehsdoxafsfpy(jSONObject, "outgoing_monthly_transaction_aggregation");
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "transactionType");
        if (strLdeiitcdqlqzkidvrbjy != null) {
            try {
                this.yvrzbryuycempgkdhpvj = vubdyxzvpnvcymakzopn.valueOf(strLdeiitcdqlqzkidvrbjy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
