package hbmifujbkwcjpgteyixs;

import androidx.autofill.HintConstants;
import io.sentry.protocol.App;
import io.sentry.protocol.DebugMeta;
import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class wgeshaitmpzrwttiokth implements Serializable, tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx {
    private List ctfsaqlysacfjtqixtmr;
    private Integer cwzojhoqdsccekmlpbcq;
    private List danumarvmgpbarrzqyrz;
    private List dbuymyhwehsdoxafsfpy;
    private int dtzqkpwicmyznzcqlscc;
    private Integer dyrapphjndqarxdhyvgv;
    private Integer efmnkxwvqeqnaehsyess;
    private List fniextxrjqlolhwhqggv;
    private List gsokbpxrpyefzacjsrbi;
    private List gtykjqtliutrjfvjtiex;
    private String hbycjaoesutelfxwaoca;
    private String hekbabbvryaiwbpvazlo;
    private List kqybgnwtttwkvdwxaqhw;
    private List ldeiitcdqlqzkidvrbjy;
    private String libyhucgefaitkehplch;
    private List lleeevhiydcwptuwzadc;
    private String mqwpnefpqywpyryhimya;
    private List mxodkqpwhcryvsgsvabl;
    private List nicafiaansnuaopzwdwm;
    private List ooztjhejjvpgrdhjnyju;
    private List ppdfopkmksgbbjoukavl;
    private Integer pyxmijmxbomdjegczksl;
    private String utxtilfqoxdwcqlqpjoj;
    private Integer uusbetktgiikylwfbevs;
    private String uwanvjsqbzasnsnlxnqi;
    private Integer vikftlgmuszlvyjnlikz;
    private Integer vjgujdxqyzpnlimdrvvt;
    private Boolean vodttdxrgsufbynbtbbg;
    private final List vpjqwyqiclwncdgbkkkg;
    private String vqslvxgmgnhrmgypwmhq;
    private Long yvrzbryuycempgkdhpvj;
    private String zwobhtbpnlqafneiqjbw;

    public wgeshaitmpzrwttiokth() {
        this.danumarvmgpbarrzqyrz = new ArrayList();
        this.vodttdxrgsufbynbtbbg = Boolean.FALSE;
        this.vpjqwyqiclwncdgbkkkg = new ArrayList();
        this.fniextxrjqlolhwhqggv = new LinkedList();
    }

    private List ctfsaqlysacfjtqixtmr(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.has(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new cptipfepeuixrxtabhzr(jSONArray.getJSONObject(i)));
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    private List dbuymyhwehsdoxafsfpy(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.has(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new cctxrwizduxmjefyvyrx(jSONArray.getJSONObject(i)));
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    private List uusbetktgiikylwfbevs(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.has(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new mchrzzmrgfkrwlaxrlna(jSONArray.getJSONObject(i)));
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    private List vjgujdxqyzpnlimdrvvt(JSONObject jSONObject, String str) throws JSONException {
        if (!jSONObject.has(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new xufavitsdcqtqeuvulja(jSONArray.getJSONObject(i)));
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    public static List yvrzbryuycempgkdhpvj(HashMap map) {
        ArrayList arrayList = new ArrayList();
        if (map != null && map.size() != 0) {
            for (String str : map.keySet()) {
                oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar = new oacciftezlubzxpkwvyc();
                oacciftezlubzxpkwvycVar.yvrzbryuycempgkdhpvj(str);
                oacciftezlubzxpkwvycVar.yvrzbryuycempgkdhpvj(new ArrayList((Collection) map.get(str)));
                arrayList.add(oacciftezlubzxpkwvycVar);
            }
        }
        return arrayList;
    }

    public void ctfsaqlysacfjtqixtmr(Integer num) {
        this.vikftlgmuszlvyjnlikz = num;
    }

    public Long dbuymyhwehsdoxafsfpy() {
        return this.yvrzbryuycempgkdhpvj;
    }

    public void dbuymyhwehsdoxafsfpy(Integer num) {
        this.uusbetktgiikylwfbevs = num;
    }

    public void dbuymyhwehsdoxafsfpy(String str) {
        this.hekbabbvryaiwbpvazlo = str;
    }

    public void dyrapphjndqarxdhyvgv(List list) {
        this.ctfsaqlysacfjtqixtmr = list;
    }

    public void efmnkxwvqeqnaehsyess(List list) {
        this.ppdfopkmksgbbjoukavl = list;
    }

    public void mxodkqpwhcryvsgsvabl(List list) {
        this.kqybgnwtttwkvdwxaqhw = list;
    }

    public void ooztjhejjvpgrdhjnyju(List list) {
        this.ooztjhejjvpgrdhjnyju = list;
    }

    public void uusbetktgiikylwfbevs(Integer num) {
        this.efmnkxwvqeqnaehsyess = num;
    }

    public void vjgujdxqyzpnlimdrvvt(Integer num) {
        this.dyrapphjndqarxdhyvgv = num;
    }

    public void yvrzbryuycempgkdhpvj(Boolean bool) {
        this.vodttdxrgsufbynbtbbg = bool;
    }

    public void yvrzbryuycempgkdhpvj(Integer num) {
        this.vjgujdxqyzpnlimdrvvt = num;
    }

    public void yvrzbryuycempgkdhpvj(String str) {
        this.utxtilfqoxdwcqlqpjoj = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0172  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public wgeshaitmpzrwttiokth(org.json.JSONObject r6) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: hbmifujbkwcjpgteyixs.wgeshaitmpzrwttiokth.<init>(org.json.JSONObject):void");
    }

    private ArrayList ctfsaqlysacfjtqixtmr(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((cptipfepeuixrxtabhzr) it.next()).yvrzbryuycempgkdhpvj());
        }
        return arrayList;
    }

    private ArrayList dbuymyhwehsdoxafsfpy(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((mchrzzmrgfkrwlaxrlna) it.next()).yvrzbryuycempgkdhpvj());
        }
        return arrayList;
    }

    private List uusbetktgiikylwfbevs(List list) throws JSONException {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            cdabktqulpsnjrlnlnii cdabktqulpsnjrlnlniiVar = (cdabktqulpsnjrlnlnii) it.next();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, cdabktqulpsnjrlnlniiVar.mxodkqpwhcryvsgsvabl());
                jSONObject.put("isInContact", cdabktqulpsnjrlnlniiVar.vjgujdxqyzpnlimdrvvt());
                jSONObject.put("callDate", cdabktqulpsnjrlnlniiVar.dbuymyhwehsdoxafsfpy());
                jSONObject.put("callDuration", cdabktqulpsnjrlnlniiVar.uusbetktgiikylwfbevs());
                jSONObject.put("callType", cdabktqulpsnjrlnlniiVar.ctfsaqlysacfjtqixtmr());
                arrayList.add(jSONObject);
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    private ArrayList vjgujdxqyzpnlimdrvvt(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((xufavitsdcqtqeuvulja) it.next()).yvrzbryuycempgkdhpvj());
        }
        return arrayList;
    }

    private List yvrzbryuycempgkdhpvj(JSONObject jSONObject, String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has(str)) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(new oacciftezlubzxpkwvyc(jSONArray.getJSONObject(i)));
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private JSONArray uusbetktgiikylwfbevs() {
        if (this.kqybgnwtttwkvdwxaqhw == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.kqybgnwtttwkvdwxaqhw.size(); i++) {
            jSONArray.put(((oacciftezlubzxpkwvyc) this.kqybgnwtttwkvdwxaqhw.get(i)).yvrzbryuycempgkdhpvj());
        }
        return jSONArray;
    }

    private ArrayList yvrzbryuycempgkdhpvj(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((cctxrwizduxmjefyvyrx) it.next()).yvrzbryuycempgkdhpvj());
        }
        return arrayList;
    }

    private List yvrzbryuycempgkdhpvj(JSONObject jSONObject) throws JSONException {
        String string;
        Boolean boolValueOf;
        String strValueOf;
        String string2;
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("callLogs");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                String string3 = null;
                if (jSONObject2.has(HintConstants.AUTOFILL_HINT_PHONE_NUMBER)) {
                    try {
                        string = jSONObject2.getString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER);
                    } catch (JSONException unused) {
                    }
                } else {
                    string = null;
                }
                if (jSONObject2.has("isInContact")) {
                    try {
                        boolValueOf = Boolean.valueOf(jSONObject2.getBoolean("isInContact"));
                    } catch (JSONException unused2) {
                    }
                } else {
                    boolValueOf = null;
                }
                if (jSONObject2.has("callDate")) {
                    try {
                        strValueOf = String.valueOf(jSONObject2.get("callDate"));
                    } catch (JSONException unused3) {
                    }
                } else {
                    strValueOf = null;
                }
                if (jSONObject2.has("callDuration")) {
                    try {
                        string2 = jSONObject2.getString("callDuration");
                    } catch (JSONException unused4) {
                    }
                } else {
                    string2 = null;
                }
                if (jSONObject2.has("callType")) {
                    try {
                        string3 = jSONObject2.getString("callType");
                    } catch (JSONException unused5) {
                    }
                }
                arrayList.add(new cdabktqulpsnjrlnlnii(string, boolValueOf, strValueOf, string2, string3));
            }
        } catch (JSONException unused6) {
        }
        return arrayList;
    }

    @Override // tjobugwlaqmndtgupgkk.rvhplcmttaqkggggovhx
    public JSONObject yvrzbryuycempgkdhpvj() throws JSONException {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONArray jSONArray3;
        JSONObject jSONObject = new JSONObject();
        List listUusbetktgiikylwfbevs = uusbetktgiikylwfbevs(this.ctfsaqlysacfjtqixtmr);
        ArrayList arrayListCtfsaqlysacfjtqixtmr = ctfsaqlysacfjtqixtmr(this.gtykjqtliutrjfvjtiex);
        ArrayList arrayListYvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj(this.ppdfopkmksgbbjoukavl);
        ArrayList arrayListDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy(this.gsokbpxrpyefzacjsrbi);
        ArrayList arrayListDbuymyhwehsdoxafsfpy2 = dbuymyhwehsdoxafsfpy(this.nicafiaansnuaopzwdwm);
        ArrayList arrayListVjgujdxqyzpnlimdrvvt = vjgujdxqyzpnlimdrvvt(this.danumarvmgpbarrzqyrz);
        JSONArray jSONArray4 = this.dbuymyhwehsdoxafsfpy != null ? new JSONArray((Collection) this.dbuymyhwehsdoxafsfpy) : null;
        JSONArray jSONArray5 = listUusbetktgiikylwfbevs != null ? new JSONArray((Collection) listUusbetktgiikylwfbevs) : null;
        JSONArray jSONArray6 = this.mxodkqpwhcryvsgsvabl != null ? new JSONArray((Collection) this.mxodkqpwhcryvsgsvabl) : null;
        JSONArray jSONArray7 = this.ooztjhejjvpgrdhjnyju != null ? new JSONArray((Collection) this.ooztjhejjvpgrdhjnyju) : null;
        JSONArray jSONArray8 = this.ldeiitcdqlqzkidvrbjy != null ? new JSONArray((Collection) this.ldeiitcdqlqzkidvrbjy) : null;
        JSONArray jSONArray9 = this.ppdfopkmksgbbjoukavl != null ? new JSONArray((Collection) arrayListYvrzbryuycempgkdhpvj) : null;
        JSONArray jSONArray10 = this.danumarvmgpbarrzqyrz != null ? new JSONArray((Collection) arrayListVjgujdxqyzpnlimdrvvt) : null;
        JSONArray jSONArray11 = this.lleeevhiydcwptuwzadc != null ? new JSONArray((Collection) this.lleeevhiydcwptuwzadc) : null;
        if (this.gsokbpxrpyefzacjsrbi != null) {
            jSONArray = jSONArray8;
            jSONArray2 = new JSONArray((Collection) this.gsokbpxrpyefzacjsrbi);
        } else {
            jSONArray = jSONArray8;
            jSONArray2 = null;
        }
        JSONArray jSONArray12 = arrayListDbuymyhwehsdoxafsfpy2 != null ? new JSONArray((Collection) arrayListDbuymyhwehsdoxafsfpy2) : null;
        JSONArray jSONArray13 = arrayListCtfsaqlysacfjtqixtmr != null ? new JSONArray((Collection) arrayListCtfsaqlysacfjtqixtmr) : null;
        JSONArray jSONArray14 = arrayListDbuymyhwehsdoxafsfpy != null ? new JSONArray((Collection) arrayListDbuymyhwehsdoxafsfpy) : null;
        JSONArray jSONArray15 = arrayListDbuymyhwehsdoxafsfpy2 != null ? new JSONArray((Collection) arrayListDbuymyhwehsdoxafsfpy2) : null;
        try {
            if (this.kqybgnwtttwkvdwxaqhw != null) {
                jSONArray3 = jSONArray15;
                jSONObject.put("account_contacts_list", uusbetktgiikylwfbevs());
            } else {
                jSONArray3 = jSONArray15;
            }
            jSONObject.put("ssids", jSONArray7);
            jSONObject.put("ssidsCount", this.vikftlgmuszlvyjnlikz);
            jSONObject.put("macAddr", this.utxtilfqoxdwcqlqpjoj);
            jSONObject.put("wifiNetworkID", this.hekbabbvryaiwbpvazlo);
            jSONObject.put("contacts", jSONArray4);
            jSONObject.put("contactsCount", this.uusbetktgiikylwfbevs);
            jSONObject.put(DebugMeta.JsonKeys.IMAGES, jSONArray6);
            jSONObject.put("imagesCount", this.efmnkxwvqeqnaehsyess);
            jSONObject.put("videoCount", this.dyrapphjndqarxdhyvgv);
            jSONObject.put("callLogs", jSONArray5);
            jSONObject.put("callLogCount", this.vjgujdxqyzpnlimdrvvt);
            jSONObject.put("accountsCount", this.pyxmijmxbomdjegczksl);
            jSONObject.put("bluetoothNewList", jSONArray9);
            jSONObject.put("wifi_networks", jSONArray10);
            jSONObject.put("preferredLanguages", jSONArray11);
            jSONObject.put("calendarDetails", jSONArray2);
            jSONObject.put("reminderDetails", jSONArray12);
            jSONObject.put("bluetoothAddress", this.hbycjaoesutelfxwaoca);
            jSONObject.put("apps", jSONArray);
            jSONObject.put("appsCount", this.cwzojhoqdsccekmlpbcq);
            jSONObject.put("collectTime", this.yvrzbryuycempgkdhpvj);
            jSONObject.put("serviceAppInfo", jSONArray13);
            jSONObject.put("calendarDetails", jSONArray14);
            jSONObject.put("reminderDetails", jSONArray3);
            jSONObject.put("userEmail", this.uwanvjsqbzasnsnlxnqi);
            jSONObject.put("isNullAccountName", this.vodttdxrgsufbynbtbbg);
            jSONObject.put("count_login_fails", this.dtzqkpwicmyznzcqlscc);
            jSONObject.put("device_id", this.vqslvxgmgnhrmgypwmhq);
            jSONObject.put(OperatingSystem.TYPE, this.mqwpnefpqywpyryhimya);
            jSONObject.put(Device.JsonKeys.BRAND, this.libyhucgefaitkehplch);
            jSONObject.put(Device.JsonKeys.MODEL, this.zwobhtbpnlqafneiqjbw);
            jSONObject.put(App.JsonKeys.APP_PERMISSIONS, new JSONArray((Collection) this.fniextxrjqlolhwhqggv));
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
