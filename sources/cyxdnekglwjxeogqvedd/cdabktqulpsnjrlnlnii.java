package cyxdnekglwjxeogqvedd;

import org.json.JSONObject;

/* loaded from: classes6.dex */
public class cdabktqulpsnjrlnlnii implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Boolean ctfsaqlysacfjtqixtmr;
    private Boolean dbuymyhwehsdoxafsfpy;
    private Integer mxodkqpwhcryvsgsvabl;
    private Boolean uusbetktgiikylwfbevs;
    private Boolean vjgujdxqyzpnlimdrvvt;
    private Boolean yvrzbryuycempgkdhpvj;

    public cdabktqulpsnjrlnlnii() {
        Boolean bool = Boolean.FALSE;
        this.yvrzbryuycempgkdhpvj = bool;
        this.dbuymyhwehsdoxafsfpy = bool;
        this.uusbetktgiikylwfbevs = bool;
        this.ctfsaqlysacfjtqixtmr = bool;
        this.vjgujdxqyzpnlimdrvvt = bool;
        this.mxodkqpwhcryvsgsvabl = 1000;
    }

    public Boolean ctfsaqlysacfjtqixtmr() {
        return this.vjgujdxqyzpnlimdrvvt;
    }

    public Boolean dbuymyhwehsdoxafsfpy() {
        return this.dbuymyhwehsdoxafsfpy;
    }

    public Integer efmnkxwvqeqnaehsyess() {
        return this.mxodkqpwhcryvsgsvabl;
    }

    public Boolean mxodkqpwhcryvsgsvabl() {
        return this.ctfsaqlysacfjtqixtmr;
    }

    public Boolean uusbetktgiikylwfbevs() {
        return this.yvrzbryuycempgkdhpvj;
    }

    public Boolean vjgujdxqyzpnlimdrvvt() {
        return this.uusbetktgiikylwfbevs;
    }

    public cdabktqulpsnjrlnlnii(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "level_exception", false);
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "level_connection_exception", false);
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "level_pg_manager", false);
        this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "level_sending_data", false);
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "level_face_verification_data", false);
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "max_trace_data", (Integer) 1000);
    }
}
