package hbmifujbkwcjpgteyixs;

import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.App;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class lmqztflyazmtuunswyyl implements Serializable, tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private String ctfsaqlysacfjtqixtmr;
    private List danumarvmgpbarrzqyrz;
    private llyhweatjmvlzlflpaeb dbuymyhwehsdoxafsfpy;
    private List dyrapphjndqarxdhyvgv;
    private Boolean efmnkxwvqeqnaehsyess;
    private Boolean mxodkqpwhcryvsgsvabl;
    private List ooztjhejjvpgrdhjnyju;
    private Boolean uusbetktgiikylwfbevs;
    private Boolean vjgujdxqyzpnlimdrvvt;
    private String yvrzbryuycempgkdhpvj;

    public lmqztflyazmtuunswyyl() {
        Boolean bool = Boolean.FALSE;
        this.uusbetktgiikylwfbevs = bool;
        this.ctfsaqlysacfjtqixtmr = "0";
        this.vjgujdxqyzpnlimdrvvt = bool;
        this.mxodkqpwhcryvsgsvabl = bool;
        this.efmnkxwvqeqnaehsyess = bool;
        this.dyrapphjndqarxdhyvgv = new ArrayList();
        this.ooztjhejjvpgrdhjnyju = new ArrayList();
        this.danumarvmgpbarrzqyrz = new ArrayList();
    }

    public Boolean ctfsaqlysacfjtqixtmr() {
        return this.mxodkqpwhcryvsgsvabl;
    }

    public boolean dbuymyhwehsdoxafsfpy() {
        return this.ctfsaqlysacfjtqixtmr.equals("1");
    }

    public List efmnkxwvqeqnaehsyess() {
        return this.dyrapphjndqarxdhyvgv;
    }

    public Boolean mxodkqpwhcryvsgsvabl() {
        return this.vjgujdxqyzpnlimdrvvt;
    }

    public String uusbetktgiikylwfbevs() {
        return this.yvrzbryuycempgkdhpvj;
    }

    public Boolean vjgujdxqyzpnlimdrvvt() {
        return this.efmnkxwvqeqnaehsyess;
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        List list = this.dyrapphjndqarxdhyvgv;
        if (list != null && !list.isEmpty()) {
            Iterator it = this.dyrapphjndqarxdhyvgv.iterator();
            while (it.hasNext()) {
                arrayList.add(((lbtmiwirddgqkunoyrov) it.next()).toString());
            }
        }
        List list2 = this.ooztjhejjvpgrdhjnyju;
        if (list2 != null && !list2.isEmpty()) {
            Iterator it2 = this.ooztjhejjvpgrdhjnyju.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((lbtmiwirddgqkunoyrov) it2.next()).toString());
            }
        }
        List list3 = this.danumarvmgpbarrzqyrz;
        if (list3 != null && !list3.isEmpty()) {
            Iterator it3 = this.danumarvmgpbarrzqyrz.iterator();
            while (it3.hasNext()) {
                arrayList3.add(((lbtmiwirddgqkunoyrov) it3.next()).toString());
            }
        }
        JSONArray jSONArray = new JSONArray((Collection) arrayList);
        JSONArray jSONArray2 = new JSONArray((Collection) arrayList2);
        JSONArray jSONArray3 = new JSONArray((Collection) arrayList3);
        llyhweatjmvlzlflpaeb llyhweatjmvlzlflpaebVar = this.dbuymyhwehsdoxafsfpy;
        if (llyhweatjmvlzlflpaebVar != null) {
            try {
                jSONObject.put(FirebaseAnalytics.Param.LOCATION, llyhweatjmvlzlflpaebVar.yvrzbryuycempgkdhpvj());
            } catch (JSONException unused) {
            }
        }
        try {
            jSONObject.put("currentSSID", this.yvrzbryuycempgkdhpvj);
        } catch (JSONException unused2) {
        }
        try {
            jSONObject.put("isRooted", this.uusbetktgiikylwfbevs);
        } catch (JSONException unused3) {
        }
        try {
            jSONObject.put("adbEnabled", this.ctfsaqlysacfjtqixtmr);
        } catch (JSONException unused4) {
        }
        try {
            jSONObject.put("isVpnActive", this.vjgujdxqyzpnlimdrvvt);
        } catch (JSONException unused5) {
        }
        try {
            jSONObject.put("isSuspiciousMouseActivity", this.mxodkqpwhcryvsgsvabl);
        } catch (JSONException unused6) {
        }
        try {
            jSONObject.put("isSuspiciousUnknownActivity", this.efmnkxwvqeqnaehsyess);
        } catch (JSONException unused7) {
        }
        List list4 = this.dyrapphjndqarxdhyvgv;
        if (list4 != null && !list4.isEmpty()) {
            try {
                jSONObject.put("permissionListSize", this.dyrapphjndqarxdhyvgv.size());
            } catch (JSONException unused8) {
            }
        }
        try {
            jSONObject.put(App.JsonKeys.APP_PERMISSIONS, jSONArray);
        } catch (JSONException unused9) {
        }
        try {
            jSONObject.put("ignorePermissions", jSONArray2);
        } catch (JSONException unused10) {
        }
        try {
            jSONObject.put("askedPermissions", jSONArray3);
        } catch (JSONException unused11) {
        }
        return jSONObject;
    }

    public lmqztflyazmtuunswyyl(String str, llyhweatjmvlzlflpaeb llyhweatjmvlzlflpaebVar, Boolean bool, String str2, Boolean bool2, Boolean bool3, Boolean bool4, List list, List list2, List list3) {
        this();
        this.yvrzbryuycempgkdhpvj = str;
        this.dbuymyhwehsdoxafsfpy = llyhweatjmvlzlflpaebVar;
        this.uusbetktgiikylwfbevs = bool;
        this.ctfsaqlysacfjtqixtmr = str2;
        this.dyrapphjndqarxdhyvgv = list;
        this.ooztjhejjvpgrdhjnyju = list2;
        this.danumarvmgpbarrzqyrz = list3;
        this.vjgujdxqyzpnlimdrvvt = bool2;
        this.mxodkqpwhcryvsgsvabl = Boolean.valueOf(bool3 != null ? bool3.booleanValue() : false);
        this.efmnkxwvqeqnaehsyess = Boolean.valueOf(bool4 != null ? bool4.booleanValue() : false);
    }

    private List yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArray;
        try {
            jSONArray = jSONObject.getJSONArray(str);
        } catch (JSONException unused) {
            jSONArray = null;
        }
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(lbtmiwirddgqkunoyrov.valueOf(jSONArray.getString(i)));
                } catch (IllegalArgumentException | JSONException unused2) {
                }
            }
        }
        return arrayList;
    }

    public lmqztflyazmtuunswyyl(JSONObject jSONObject) {
        this();
        if (jSONObject == null) {
            return;
        }
        this.yvrzbryuycempgkdhpvj = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "currentSSID");
        this.uusbetktgiikylwfbevs = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isRooted", false);
        this.vjgujdxqyzpnlimdrvvt = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isVpnActive", false);
        this.efmnkxwvqeqnaehsyess = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isSuspiciousUnknownActivity", false);
        this.mxodkqpwhcryvsgsvabl = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "isSuspiciousMouseActivity", false);
        String strLdeiitcdqlqzkidvrbjy = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.ldeiitcdqlqzkidvrbjy(jSONObject, "adbEnabled");
        this.ctfsaqlysacfjtqixtmr = strLdeiitcdqlqzkidvrbjy;
        if (strLdeiitcdqlqzkidvrbjy == null) {
            this.ctfsaqlysacfjtqixtmr = iujcgqoygfjchslhmonl.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(jSONObject, "ADB_ENABLED", "0");
        }
        this.danumarvmgpbarrzqyrz = yvrzbryuycempgkdhpvj(jSONObject, "askedPermissions");
        this.dyrapphjndqarxdhyvgv = yvrzbryuycempgkdhpvj(jSONObject, App.JsonKeys.APP_PERMISSIONS);
        this.ooztjhejjvpgrdhjnyju = yvrzbryuycempgkdhpvj(jSONObject, "ignorePermissions");
        if (jSONObject.has(FirebaseAnalytics.Param.LOCATION)) {
            try {
                this.dbuymyhwehsdoxafsfpy = new llyhweatjmvlzlflpaeb(new JSONObject(String.valueOf(jSONObject.get(FirebaseAnalytics.Param.LOCATION))));
            } catch (JSONException unused) {
            }
        }
    }
}
