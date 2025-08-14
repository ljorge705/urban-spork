package mlrctfuhzpnupddagijp;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class kaqespyeeawijtnikoai implements ppvnkbmzfphuuihfhotp {
    private transient JSONObject dbuymyhwehsdoxafsfpy;
    private Double yvrzbryuycempgkdhpvj;

    public kaqespyeeawijtnikoai(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "telesign_score");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "telesign_json");
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("telesign_score", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("telesign_json", this.dbuymyhwehsdoxafsfpy);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
