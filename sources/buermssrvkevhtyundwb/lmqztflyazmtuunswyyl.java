package buermssrvkevhtyundwb;

import ebpswaqugdaofldkaqte.vubdyxzvpnvcymakzopn;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lmqztflyazmtuunswyyl implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Double ctfsaqlysacfjtqixtmr;
    private Double dbuymyhwehsdoxafsfpy;
    private Integer dyrapphjndqarxdhyvgv;
    private vubdyxzvpnvcymakzopn efmnkxwvqeqnaehsyess;
    private String mxodkqpwhcryvsgsvabl;
    private Double uusbetktgiikylwfbevs;
    private Double vjgujdxqyzpnlimdrvvt;
    private Integer yvrzbryuycempgkdhpvj;

    public lmqztflyazmtuunswyyl() {
        this.yvrzbryuycempgkdhpvj = 0;
        Double dValueOf = Double.valueOf(0.0d);
        this.dbuymyhwehsdoxafsfpy = dValueOf;
        this.uusbetktgiikylwfbevs = dValueOf;
        this.ctfsaqlysacfjtqixtmr = dValueOf;
        this.vjgujdxqyzpnlimdrvvt = dValueOf;
        this.dyrapphjndqarxdhyvgv = 0;
    }

    public lmqztflyazmtuunswyyl(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "transactions_count", (Integer) 0);
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "sum_amount", Double.valueOf(0.0d));
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "min_amount", Double.valueOf(0.0d));
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "max_amount", Double.valueOf(0.0d));
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "avg_amount", Double.valueOf(0.0d));
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "id");
        this.dyrapphjndqarxdhyvgv = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "distinct_payees", (Integer) 0);
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "transactionType");
        if (strLdeiitcdqlqzkidvrbjy != null) {
            try {
                this.efmnkxwvqeqnaehsyess = vubdyxzvpnvcymakzopn.valueOf(strLdeiitcdqlqzkidvrbjy);
            } catch (Exception unused) {
            }
        }
    }
}
