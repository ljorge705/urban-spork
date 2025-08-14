package hbmifujbkwcjpgteyixs;

import org.json.JSONObject;

/* loaded from: classes6.dex */
public class asvpglyfnwnhyiooalpb implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private cyxdnekglwjxeogqvedd.wfdoaqfvfyoijpgclxfu ctfsaqlysacfjtqixtmr;
    private Double dbuymyhwehsdoxafsfpy;
    private Boolean mxodkqpwhcryvsgsvabl;
    private Integer uusbetktgiikylwfbevs;
    private Boolean vjgujdxqyzpnlimdrvvt;
    private Double yvrzbryuycempgkdhpvj;

    public asvpglyfnwnhyiooalpb() {
        Boolean bool = Boolean.FALSE;
        this.vjgujdxqyzpnlimdrvvt = bool;
        this.mxodkqpwhcryvsgsvabl = bool;
        this.ctfsaqlysacfjtqixtmr = cyxdnekglwjxeogqvedd.wfdoaqfvfyoijpgclxfu.AWS_RECKOGNITION;
    }

    public asvpglyfnwnhyiooalpb(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "verificationScore");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vjgujdxqyzpnlimdrvvt(jSONObject, "threshold");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "faceDetails");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "is_in_fraud_collection", false);
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "is_enrolment", false);
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "verificationVendor");
        if (strLdeiitcdqlqzkidvrbjy != null) {
            try {
                this.ctfsaqlysacfjtqixtmr = cyxdnekglwjxeogqvedd.wfdoaqfvfyoijpgclxfu.valueOf(strLdeiitcdqlqzkidvrbjy);
            } catch (Exception unused) {
            }
        }
    }
}
