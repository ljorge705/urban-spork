package mlrctfuhzpnupddagijp;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class lmqztflyazmtuunswyyl implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private ppvnkbmzfphuuihfhotp ctfsaqlysacfjtqixtmr;
    private ppvnkbmzfphuuihfhotp dbuymyhwehsdoxafsfpy;
    private ppvnkbmzfphuuihfhotp dyrapphjndqarxdhyvgv;
    private ppvnkbmzfphuuihfhotp efmnkxwvqeqnaehsyess;
    private ppvnkbmzfphuuihfhotp mxodkqpwhcryvsgsvabl;
    private ppvnkbmzfphuuihfhotp uusbetktgiikylwfbevs;
    private ppvnkbmzfphuuihfhotp vjgujdxqyzpnlimdrvvt;
    private ppvnkbmzfphuuihfhotp yvrzbryuycempgkdhpvj;

    public lmqztflyazmtuunswyyl(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has("maxmind")) {
            try {
                this.yvrzbryuycempgkdhpvj = new mchrzzmrgfkrwlaxrlna(jSONObject.getJSONObject("maxmind"));
            } catch (JSONException unused) {
            }
        }
        if (jSONObject.has("telesign")) {
            try {
                this.dbuymyhwehsdoxafsfpy = new kaqespyeeawijtnikoai(jSONObject.getJSONObject("telesign"));
            } catch (JSONException unused2) {
            }
        }
        if (jSONObject.has("ekata")) {
            try {
                this.uusbetktgiikylwfbevs = new oacciftezlubzxpkwvyc(jSONObject.getJSONObject("ekata"));
            } catch (JSONException unused3) {
            }
        }
        if (jSONObject.has("maxmindip")) {
            try {
                this.ctfsaqlysacfjtqixtmr = new ymdxlfsorgvechaxkrll(jSONObject.getJSONObject("maxmindip"));
            } catch (JSONException unused4) {
            }
        }
        if (jSONObject.has("geocide_billing")) {
            try {
                this.vjgujdxqyzpnlimdrvvt = new rvhplcmttaqkggggovhx(jSONObject.getJSONObject("geocide_billing"));
            } catch (JSONException unused5) {
            }
        }
        if (jSONObject.has("geocide_shipping")) {
            try {
                this.mxodkqpwhcryvsgsvabl = new cctxrwizduxmjefyvyrx(jSONObject.getJSONObject("geocide_shipping"));
            } catch (JSONException unused6) {
            }
        }
        if (jSONObject.has("geocide_merchant")) {
            try {
                this.efmnkxwvqeqnaehsyess = new zwlltpaufqribmleigux(jSONObject.getJSONObject("geocide_merchant"));
            } catch (JSONException unused7) {
            }
        }
        if (jSONObject.has("sanctions_screening")) {
            try {
                cdabktqulpsnjrlnlnii cdabktqulpsnjrlnlniiVar = new cdabktqulpsnjrlnlnii();
                cdabktqulpsnjrlnlniiVar.yvrzbryuycempgkdhpvj(jSONObject.getJSONObject("geocide_merchant"));
                this.dyrapphjndqarxdhyvgv = cdabktqulpsnjrlnlniiVar;
            } catch (JSONException unused8) {
            }
        }
    }

    public String toString() {
        return yvrzbryuycempgkdhpvj().toString();
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar = this.yvrzbryuycempgkdhpvj;
            if (ppvnkbmzfphuuihfhotpVar != null) {
                jSONObject.put("maxmind", ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj());
            }
            ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar2 = this.dbuymyhwehsdoxafsfpy;
            if (ppvnkbmzfphuuihfhotpVar2 != null) {
                jSONObject.put("telesign", ppvnkbmzfphuuihfhotpVar2.yvrzbryuycempgkdhpvj());
            }
            ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar3 = this.uusbetktgiikylwfbevs;
            if (ppvnkbmzfphuuihfhotpVar3 != null) {
                jSONObject.put("ekata", ppvnkbmzfphuuihfhotpVar3.yvrzbryuycempgkdhpvj());
            }
            ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar4 = this.ctfsaqlysacfjtqixtmr;
            if (ppvnkbmzfphuuihfhotpVar4 != null) {
                jSONObject.put("maxmindip", ppvnkbmzfphuuihfhotpVar4.yvrzbryuycempgkdhpvj());
            }
            ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar5 = this.vjgujdxqyzpnlimdrvvt;
            if (ppvnkbmzfphuuihfhotpVar5 != null) {
                jSONObject.put("geocide_billing", ppvnkbmzfphuuihfhotpVar5.yvrzbryuycempgkdhpvj());
            }
            ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar6 = this.mxodkqpwhcryvsgsvabl;
            if (ppvnkbmzfphuuihfhotpVar6 != null) {
                jSONObject.put("geocide_shipping", ppvnkbmzfphuuihfhotpVar6.yvrzbryuycempgkdhpvj());
            }
            ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar7 = this.efmnkxwvqeqnaehsyess;
            if (ppvnkbmzfphuuihfhotpVar7 != null) {
                jSONObject.put("geocide_merchant", ppvnkbmzfphuuihfhotpVar7.yvrzbryuycempgkdhpvj());
            }
            ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar8 = this.dyrapphjndqarxdhyvgv;
            if (ppvnkbmzfphuuihfhotpVar8 != null) {
                jSONObject.put("sanctions_screening", ppvnkbmzfphuuihfhotpVar8.yvrzbryuycempgkdhpvj());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
