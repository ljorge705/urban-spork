package mlrctfuhzpnupddagijp;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class wfdoaqfvfyoijpgclxfu implements ppvnkbmzfphuuihfhotp {
    private transient JSONObject dbuymyhwehsdoxafsfpy;
    private Double yvrzbryuycempgkdhpvj;

    public wfdoaqfvfyoijpgclxfu(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "geoip_score");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ooztjhejjvpgrdhjnyju(jSONObject, "geoip_json");
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("geoip_score", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("geoip_json", this.dbuymyhwehsdoxafsfpy);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
