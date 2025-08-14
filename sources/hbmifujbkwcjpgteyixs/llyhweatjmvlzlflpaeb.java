package hbmifujbkwcjpgteyixs;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class llyhweatjmvlzlflpaeb implements Serializable, tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Double ctfsaqlysacfjtqixtmr;
    private Boolean dbuymyhwehsdoxafsfpy;
    private Boolean dyrapphjndqarxdhyvgv;
    private Double efmnkxwvqeqnaehsyess;
    private Double mxodkqpwhcryvsgsvabl;
    private Double uusbetktgiikylwfbevs;
    private Double vjgujdxqyzpnlimdrvvt;
    private Double yvrzbryuycempgkdhpvj;

    public llyhweatjmvlzlflpaeb(JSONObject jSONObject) {
        this.yvrzbryuycempgkdhpvj = null;
        this.dbuymyhwehsdoxafsfpy = Boolean.FALSE;
        this.uusbetktgiikylwfbevs = null;
        this.ctfsaqlysacfjtqixtmr = null;
        this.vjgujdxqyzpnlimdrvvt = null;
        this.mxodkqpwhcryvsgsvabl = null;
        this.efmnkxwvqeqnaehsyess = null;
        this.dyrapphjndqarxdhyvgv = null;
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "altitude");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isMock", false);
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "latitude");
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "accuracy");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "longitude");
        this.dyrapphjndqarxdhyvgv = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "fullAccuracy");
        this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "maxmindLongitude");
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "maxmindLatitude");
    }

    public String toString() {
        return "Location [altitude=" + this.yvrzbryuycempgkdhpvj + ", isMock=" + this.dbuymyhwehsdoxafsfpy + ", latitude=" + this.uusbetktgiikylwfbevs + ", accuracy=" + this.ctfsaqlysacfjtqixtmr + ", longitude=" + this.vjgujdxqyzpnlimdrvvt + ", fullAccuracy=" + this.dyrapphjndqarxdhyvgv + ", ipLatitude=" + this.mxodkqpwhcryvsgsvabl + ", ipLongitude=" + this.efmnkxwvqeqnaehsyess + "]";
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("altitude", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("isMock", this.dbuymyhwehsdoxafsfpy);
            jSONObject.put("latitude", this.uusbetktgiikylwfbevs);
            jSONObject.put("accuracy", this.ctfsaqlysacfjtqixtmr);
            jSONObject.put("longitude", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("fullAccuracy", this.dyrapphjndqarxdhyvgv);
            jSONObject.put("maxmindLongitude", this.efmnkxwvqeqnaehsyess);
            jSONObject.put("maxmindLatitude", this.mxodkqpwhcryvsgsvabl);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
