package mlrctfuhzpnupddagijp;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ymdxlfsorgvechaxkrll implements ppvnkbmzfphuuihfhotp {
    private transient JSONObject dbuymyhwehsdoxafsfpy;
    private Double yvrzbryuycempgkdhpvj;

    public ymdxlfsorgvechaxkrll(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "maxmindip_score");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "maxmindip_json");
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("maxmindip_score", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("maxmindip_json", this.dbuymyhwehsdoxafsfpy);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
