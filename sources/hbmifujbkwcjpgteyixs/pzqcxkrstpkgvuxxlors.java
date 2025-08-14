package hbmifujbkwcjpgteyixs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pzqcxkrstpkgvuxxlors implements tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    kaqespyeeawijtnikoai ctfsaqlysacfjtqixtmr;
    Long dbuymyhwehsdoxafsfpy;
    private Set dyrapphjndqarxdhyvgv;
    private boolean efmnkxwvqeqnaehsyess;
    private long mxodkqpwhcryvsgsvabl;
    Boolean uusbetktgiikylwfbevs;
    boolean vjgujdxqyzpnlimdrvvt;
    String yvrzbryuycempgkdhpvj;

    public pzqcxkrstpkgvuxxlors() {
        this.vjgujdxqyzpnlimdrvvt = false;
        this.mxodkqpwhcryvsgsvabl = 0L;
        this.efmnkxwvqeqnaehsyess = false;
        this.uusbetktgiikylwfbevs = Boolean.FALSE;
        this.dyrapphjndqarxdhyvgv = new HashSet();
    }

    private JSONArray uusbetktgiikylwfbevs() {
        JSONArray jSONArray = new JSONArray();
        Set set = this.dyrapphjndqarxdhyvgv;
        if (set != null && !set.isEmpty()) {
            Iterator it = this.dyrapphjndqarxdhyvgv.iterator();
            while (it.hasNext()) {
                jSONArray.put(((cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx) it.next()).toString());
            }
        }
        return jSONArray;
    }

    private Set yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArray;
        try {
            jSONArray = jSONObject.getJSONArray(str);
        } catch (JSONException unused) {
            jSONArray = null;
        }
        HashSet hashSet = new HashSet();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    hashSet.add(cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.valueOf(jSONArray.getString(i)));
                } catch (IllegalArgumentException | JSONException unused2) {
                }
            }
        }
        return hashSet;
    }

    public String ctfsaqlysacfjtqixtmr() {
        return this.yvrzbryuycempgkdhpvj;
    }

    public Set dbuymyhwehsdoxafsfpy() {
        return this.dyrapphjndqarxdhyvgv;
    }

    public void yvrzbryuycempgkdhpvj(kaqespyeeawijtnikoai kaqespyeeawijtnikoaiVar) {
        this.ctfsaqlysacfjtqixtmr = kaqespyeeawijtnikoaiVar;
    }

    public void yvrzbryuycempgkdhpvj(Boolean bool) {
        this.uusbetktgiikylwfbevs = bool;
    }

    public void yvrzbryuycempgkdhpvj(boolean z) {
        this.efmnkxwvqeqnaehsyess = z;
    }

    public pzqcxkrstpkgvuxxlors(String str, Long l) {
        this();
        this.yvrzbryuycempgkdhpvj = str;
        this.dbuymyhwehsdoxafsfpy = l;
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("idSession", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("timeSession", this.dbuymyhwehsdoxafsfpy);
            jSONObject.put("new_session", this.uusbetktgiikylwfbevs);
            jSONObject.put("failover", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("isSessionModify", this.efmnkxwvqeqnaehsyess);
            jSONObject.put("compareDataTypeList", uusbetktgiikylwfbevs());
            jSONObject.put("dormantUserSessionTimePassedInDays", this.mxodkqpwhcryvsgsvabl);
            kaqespyeeawijtnikoai kaqespyeeawijtnikoaiVar = this.ctfsaqlysacfjtqixtmr;
            if (kaqespyeeawijtnikoaiVar != null) {
                jSONObject.put("channel", kaqespyeeawijtnikoaiVar.uusbetktgiikylwfbevs());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public pzqcxkrstpkgvuxxlors(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "idSession");
        this.dbuymyhwehsdoxafsfpy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.vikftlgmuszlvyjnlikz(jSONObject, "timeSession");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(jSONObject, "new_session");
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "channel");
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "failover", false).booleanValue();
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "dormantUserSessionTimePassedInDays", (Long) 0L).longValue();
        this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isSessionModify", false).booleanValue();
        this.dyrapphjndqarxdhyvgv = yvrzbryuycempgkdhpvj(jSONObject, "compareDataTypeList");
        this.ctfsaqlysacfjtqixtmr = strLdeiitcdqlqzkidvrbjy != null ? kaqespyeeawijtnikoai.valueOf(strLdeiitcdqlqzkidvrbjy) : kaqespyeeawijtnikoai.ctfsaqlysacfjtqixtmr();
    }
}
