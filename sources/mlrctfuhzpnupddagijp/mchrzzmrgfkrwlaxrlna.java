package mlrctfuhzpnupddagijp;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class mchrzzmrgfkrwlaxrlna implements ppvnkbmzfphuuihfhotp {
    private Double dbuymyhwehsdoxafsfpy;
    private transient JSONObject uusbetktgiikylwfbevs;
    private Double yvrzbryuycempgkdhpvj;

    public mchrzzmrgfkrwlaxrlna(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "risk_score");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "funds_remaining");
        JSONObject jSONObjectDanumarvmgpbarrzqyrz = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.danumarvmgpbarrzqyrz(jSONObject, "maxmind_risk_json");
        this.uusbetktgiikylwfbevs = jSONObjectDanumarvmgpbarrzqyrz;
        if (jSONObjectDanumarvmgpbarrzqyrz == null) {
            this.uusbetktgiikylwfbevs = jSONObject;
        }
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("risk_score", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("funds_remaining", this.dbuymyhwehsdoxafsfpy);
            JSONObject jSONObject2 = this.uusbetktgiikylwfbevs;
            if (jSONObject2 != null) {
                jSONObject.put("maxmind_risk_json", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
