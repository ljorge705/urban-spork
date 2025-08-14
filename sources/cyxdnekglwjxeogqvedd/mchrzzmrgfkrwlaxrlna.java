package cyxdnekglwjxeogqvedd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mchrzzmrgfkrwlaxrlna implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private Long ctfsaqlysacfjtqixtmr;
    private List cwzojhoqdsccekmlpbcq;
    private Integer danumarvmgpbarrzqyrz;
    private fdwipeifdmvqsbqrrpyp dbuymyhwehsdoxafsfpy;
    private cdabktqulpsnjrlnlnii dyrapphjndqarxdhyvgv;
    private Boolean efmnkxwvqeqnaehsyess;
    private Long gsokbpxrpyefzacjsrbi;
    private List gtykjqtliutrjfvjtiex;
    private Boolean hbycjaoesutelfxwaoca;
    private zwlltpaufqribmleigux hekbabbvryaiwbpvazlo;
    private Boolean ldeiitcdqlqzkidvrbjy;
    private String lleeevhiydcwptuwzadc;
    private Boolean mxodkqpwhcryvsgsvabl;
    private cctxrwizduxmjefyvyrx ooztjhejjvpgrdhjnyju;
    private Boolean pyxmijmxbomdjegczksl;
    private List utxtilfqoxdwcqlqpjoj;
    private Boolean uusbetktgiikylwfbevs;
    private Boolean uwanvjsqbzasnsnlxnqi;
    private Integer vikftlgmuszlvyjnlikz;
    private Long vjgujdxqyzpnlimdrvvt;
    private Integer yvrzbryuycempgkdhpvj;

    public mchrzzmrgfkrwlaxrlna() {
        this.dbuymyhwehsdoxafsfpy = fdwipeifdmvqsbqrrpyp.PRODUCTION;
        this.yvrzbryuycempgkdhpvj = -1;
        Boolean bool = Boolean.FALSE;
        this.uusbetktgiikylwfbevs = bool;
        this.ctfsaqlysacfjtqixtmr = 5000L;
        this.mxodkqpwhcryvsgsvabl = bool;
        this.dyrapphjndqarxdhyvgv = new cdabktqulpsnjrlnlnii();
        this.ooztjhejjvpgrdhjnyju = new cctxrwizduxmjefyvyrx();
        this.ldeiitcdqlqzkidvrbjy = bool;
        this.efmnkxwvqeqnaehsyess = bool;
        this.danumarvmgpbarrzqyrz = 10000;
        this.vikftlgmuszlvyjnlikz = 2;
        this.cwzojhoqdsccekmlpbcq = new ArrayList();
        this.gtykjqtliutrjfvjtiex = new ArrayList();
        this.utxtilfqoxdwcqlqpjoj = ctfsaqlysacfjtqixtmr();
        this.hekbabbvryaiwbpvazlo = new zwlltpaufqribmleigux();
        this.hbycjaoesutelfxwaoca = bool;
        this.uwanvjsqbzasnsnlxnqi = bool;
        this.pyxmijmxbomdjegczksl = bool;
        this.lleeevhiydcwptuwzadc = null;
        this.vjgujdxqyzpnlimdrvvt = 30000L;
        this.gsokbpxrpyefzacjsrbi = Long.valueOf(DateUtils.MILLIS_PER_HOUR);
    }

    private static ArrayList ctfsaqlysacfjtqixtmr() {
        rvhplcmttaqkggggovhx rvhplcmttaqkggggovhxVar = rvhplcmttaqkggggovhx.ADB_ENABLE;
        Boolean bool = Boolean.FALSE;
        return new ArrayList(Arrays.asList(new oacciftezlubzxpkwvyc(rvhplcmttaqkggggovhxVar, bool), new oacciftezlubzxpkwvyc(rvhplcmttaqkggggovhx.CURRENT_SSID, bool), new oacciftezlubzxpkwvyc(rvhplcmttaqkggggovhx.PERMISSION, bool), new oacciftezlubzxpkwvyc(rvhplcmttaqkggggovhx.IS_VPN_ACTIVE, bool), new oacciftezlubzxpkwvyc(rvhplcmttaqkggggovhx.IS_SUSPICIOUS_UNKNOWN_ACTIVITY, bool), new oacciftezlubzxpkwvyc(rvhplcmttaqkggggovhx.IS_SUSPICIOUS_MOUSE_ACTIVITY, bool)));
    }

    public Long danumarvmgpbarrzqyrz() {
        return this.gsokbpxrpyefzacjsrbi;
    }

    public fdwipeifdmvqsbqrrpyp dbuymyhwehsdoxafsfpy() {
        return this.dbuymyhwehsdoxafsfpy;
    }

    public Integer dyrapphjndqarxdhyvgv() {
        return this.vikftlgmuszlvyjnlikz;
    }

    public Long efmnkxwvqeqnaehsyess() {
        return this.vjgujdxqyzpnlimdrvvt;
    }

    public cdabktqulpsnjrlnlnii ldeiitcdqlqzkidvrbjy() {
        return this.dyrapphjndqarxdhyvgv;
    }

    public Boolean mxodkqpwhcryvsgsvabl() {
        return this.hbycjaoesutelfxwaoca;
    }

    public Integer ooztjhejjvpgrdhjnyju() {
        return this.danumarvmgpbarrzqyrz;
    }

    public List uusbetktgiikylwfbevs() {
        return this.utxtilfqoxdwcqlqpjoj;
    }

    public Long vikftlgmuszlvyjnlikz() {
        return this.ctfsaqlysacfjtqixtmr;
    }

    public Boolean vjgujdxqyzpnlimdrvvt() {
        return this.uwanvjsqbzasnsnlxnqi;
    }

    public mchrzzmrgfkrwlaxrlna(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has("app_run_mode")) {
            try {
                this.dbuymyhwehsdoxafsfpy = fdwipeifdmvqsbqrrpyp.valueOf(jSONObject.get("app_run_mode").toString());
            } catch (Exception unused) {
            }
        }
        Integer numMxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "face_verification_delay");
        if (numMxodkqpwhcryvsgsvabl != null) {
            this.yvrzbryuycempgkdhpvj = numMxodkqpwhcryvsgsvabl;
        }
        Integer numMxodkqpwhcryvsgsvabl2 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "restart_Time_Ms_thread_sdk");
        if (numMxodkqpwhcryvsgsvabl2 != null) {
            this.danumarvmgpbarrzqyrz = numMxodkqpwhcryvsgsvabl2;
        }
        Integer numMxodkqpwhcryvsgsvabl3 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(jSONObject, "restart_coefficient_thread_sdk");
        if (numMxodkqpwhcryvsgsvabl3 != null) {
            this.vikftlgmuszlvyjnlikz = numMxodkqpwhcryvsgsvabl3;
        }
        Long lVikftlgmuszlvyjnlikz = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "timeout_connection");
        if (lVikftlgmuszlvyjnlikz != null) {
            this.ctfsaqlysacfjtqixtmr = lVikftlgmuszlvyjnlikz;
        }
        Long lVikftlgmuszlvyjnlikz2 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "intervalTimeToCompare");
        if (lVikftlgmuszlvyjnlikz2 != null) {
            this.vjgujdxqyzpnlimdrvvt = lVikftlgmuszlvyjnlikz2;
        }
        Long lVikftlgmuszlvyjnlikz3 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "sessionTimeout");
        if (lVikftlgmuszlvyjnlikz3 != null) {
            this.gsokbpxrpyefzacjsrbi = lVikftlgmuszlvyjnlikz3;
        }
        Boolean boolUusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "scan_credit_card");
        if (boolUusbetktgiikylwfbevs != null) {
            this.uusbetktgiikylwfbevs = boolUusbetktgiikylwfbevs;
        }
        Boolean boolUusbetktgiikylwfbevs2 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "enable_bio_collection");
        if (boolUusbetktgiikylwfbevs2 != null) {
            this.mxodkqpwhcryvsgsvabl = boolUusbetktgiikylwfbevs2;
        }
        Boolean boolUusbetktgiikylwfbevs3 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "activity_map");
        if (boolUusbetktgiikylwfbevs3 != null) {
            this.ldeiitcdqlqzkidvrbjy = boolUusbetktgiikylwfbevs3;
        }
        Boolean boolUusbetktgiikylwfbevs4 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "enable_crash_eye");
        if (boolUusbetktgiikylwfbevs4 != null) {
            this.efmnkxwvqeqnaehsyess = boolUusbetktgiikylwfbevs4;
        }
        if (jSONObject.has("tracing_manager")) {
            try {
                this.dyrapphjndqarxdhyvgv = new cdabktqulpsnjrlnlnii(jSONObject.getJSONObject("tracing_manager"));
            } catch (JSONException unused2) {
            }
        }
        if (jSONObject.has("EnrichmentConfig")) {
            try {
                this.hekbabbvryaiwbpvazlo = new zwlltpaufqribmleigux(jSONObject.getJSONObject("EnrichmentConfig"));
            } catch (JSONException unused3) {
            }
        }
        if (jSONObject.has("face_configuration")) {
            try {
                this.ooztjhejjvpgrdhjnyju = new cctxrwizduxmjefyvyrx(jSONObject.getJSONObject("face_configuration"));
            } catch (JSONException unused4) {
            }
        }
        if (jSONObject.has("screen_listeners_list")) {
            this.cwzojhoqdsccekmlpbcq = new ymdxlfsorgvechaxkrll().yvrzbryuycempgkdhpvj(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "screen_listeners_list"));
        }
        if (jSONObject.has("checkpoint_screens_list")) {
            this.gtykjqtliutrjfvjtiex = new ymdxlfsorgvechaxkrll().yvrzbryuycempgkdhpvj(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "checkpoint_screens_list"));
        }
        this.utxtilfqoxdwcqlqpjoj = jSONObject.has("compare_list") ? new oacciftezlubzxpkwvyc().yvrzbryuycempgkdhpvj(iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "compare_list")) : ctfsaqlysacfjtqixtmr();
        Boolean boolUusbetktgiikylwfbevs5 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "experimentalAttributesCollection");
        if (boolUusbetktgiikylwfbevs5 != null) {
            this.hbycjaoesutelfxwaoca = boolUusbetktgiikylwfbevs5;
        }
        Boolean boolUusbetktgiikylwfbevs6 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "enableCrashLogging");
        if (boolUusbetktgiikylwfbevs6 != null) {
            this.uwanvjsqbzasnsnlxnqi = boolUusbetktgiikylwfbevs6;
        }
        Boolean boolUusbetktgiikylwfbevs7 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "enableErrorLogging");
        if (boolUusbetktgiikylwfbevs7 != null) {
            this.pyxmijmxbomdjegczksl = boolUusbetktgiikylwfbevs7;
        }
        Boolean boolUusbetktgiikylwfbevs8 = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "sessionTimeout");
        if (boolUusbetktgiikylwfbevs8 != null) {
            this.pyxmijmxbomdjegczksl = boolUusbetktgiikylwfbevs8;
        }
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "bearer");
        if (strLdeiitcdqlqzkidvrbjy != null) {
            this.lleeevhiydcwptuwzadc = strLdeiitcdqlqzkidvrbjy;
        }
    }
}
