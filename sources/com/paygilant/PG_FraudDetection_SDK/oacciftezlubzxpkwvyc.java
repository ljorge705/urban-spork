package com.paygilant.PG_FraudDetection_SDK;

import android.content.Context;
import android.content.SharedPreferences;
import com.clevertap.android.sdk.db.Column;
import cyxdnekglwjxeogqvedd.mchrzzmrgfkrwlaxrlna;
import hbmifujbkwcjpgteyixs.kaqespyeeawijtnikoai;
import hbmifujbkwcjpgteyixs.pzqcxkrstpkgvuxxlors;

/* loaded from: classes6.dex */
class oacciftezlubzxpkwvyc {
    private static oacciftezlubzxpkwvyc danumarvmgpbarrzqyrz;
    private mchrzzmrgfkrwlaxrlna ctfsaqlysacfjtqixtmr;
    private String dbuymyhwehsdoxafsfpy;
    private Boolean dyrapphjndqarxdhyvgv;
    private Boolean efmnkxwvqeqnaehsyess;
    private Boolean mxodkqpwhcryvsgsvabl;
    private Boolean ooztjhejjvpgrdhjnyju;
    private String uusbetktgiikylwfbevs;
    private String vjgujdxqyzpnlimdrvvt;
    private pzqcxkrstpkgvuxxlors yvrzbryuycempgkdhpvj;

    private oacciftezlubzxpkwvyc(Context context, String str) {
        this.dbuymyhwehsdoxafsfpy = "no_device_id";
        this.dbuymyhwehsdoxafsfpy = zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, Column.DEVICE_ID, "no_device_id");
        Boolean bool = Boolean.FALSE;
        this.efmnkxwvqeqnaehsyess = zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "approvePolicy", bool);
        this.mxodkqpwhcryvsgsvabl = zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "isExperimental", bool);
        this.vjgujdxqyzpnlimdrvvt = str;
        this.uusbetktgiikylwfbevs = zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "serverUrl", (String) null);
        this.ctfsaqlysacfjtqixtmr = new mchrzzmrgfkrwlaxrlna();
        this.yvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj();
        this.dyrapphjndqarxdhyvgv = bool;
        this.ooztjhejjvpgrdhjnyju = bool;
    }

    public static oacciftezlubzxpkwvyc mxodkqpwhcryvsgsvabl() {
        if (danumarvmgpbarrzqyrz == null) {
            synchronized (oacciftezlubzxpkwvyc.class) {
                if (danumarvmgpbarrzqyrz == null) {
                    danumarvmgpbarrzqyrz = new oacciftezlubzxpkwvyc(null, null);
                }
            }
        }
        return danumarvmgpbarrzqyrz;
    }

    public String ctfsaqlysacfjtqixtmr() {
        return this.dbuymyhwehsdoxafsfpy;
    }

    public String danumarvmgpbarrzqyrz() {
        pzqcxkrstpkgvuxxlors pzqcxkrstpkgvuxxlorsVar = this.yvrzbryuycempgkdhpvj;
        if (pzqcxkrstpkgvuxxlorsVar == null || pzqcxkrstpkgvuxxlorsVar.ctfsaqlysacfjtqixtmr() == null) {
            return null;
        }
        return this.yvrzbryuycempgkdhpvj.ctfsaqlysacfjtqixtmr();
    }

    public String dbuymyhwehsdoxafsfpy() {
        return this.vjgujdxqyzpnlimdrvvt;
    }

    public void dbuymyhwehsdoxafsfpy(Boolean bool) {
        this.ooztjhejjvpgrdhjnyju = bool;
    }

    public String dyrapphjndqarxdhyvgv() {
        return this.uusbetktgiikylwfbevs;
    }

    public mchrzzmrgfkrwlaxrlna efmnkxwvqeqnaehsyess() {
        mchrzzmrgfkrwlaxrlna mchrzzmrgfkrwlaxrlnaVar = this.ctfsaqlysacfjtqixtmr;
        return mchrzzmrgfkrwlaxrlnaVar != null ? mchrzzmrgfkrwlaxrlnaVar : new mchrzzmrgfkrwlaxrlna();
    }

    public Boolean ldeiitcdqlqzkidvrbjy() {
        return this.ooztjhejjvpgrdhjnyju;
    }

    public pzqcxkrstpkgvuxxlors ooztjhejjvpgrdhjnyju() {
        pzqcxkrstpkgvuxxlors pzqcxkrstpkgvuxxlorsVar = this.yvrzbryuycempgkdhpvj;
        return pzqcxkrstpkgvuxxlorsVar != null ? pzqcxkrstpkgvuxxlorsVar : new pzqcxkrstpkgvuxxlors(null, null);
    }

    public Boolean uusbetktgiikylwfbevs() {
        return this.efmnkxwvqeqnaehsyess;
    }

    public Boolean vikftlgmuszlvyjnlikz() {
        return this.dyrapphjndqarxdhyvgv;
    }

    public Boolean vjgujdxqyzpnlimdrvvt() {
        return this.mxodkqpwhcryvsgsvabl;
    }

    public pzqcxkrstpkgvuxxlors yvrzbryuycempgkdhpvj() {
        if (this.vjgujdxqyzpnlimdrvvt == null) {
            return null;
        }
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        pzqcxkrstpkgvuxxlors pzqcxkrstpkgvuxxlorsVar = new pzqcxkrstpkgvuxxlors(this.vjgujdxqyzpnlimdrvvt + "_" + lValueOf, lValueOf);
        pzqcxkrstpkgvuxxlorsVar.yvrzbryuycempgkdhpvj(kaqespyeeawijtnikoai.MOBILE);
        pzqcxkrstpkgvuxxlorsVar.yvrzbryuycempgkdhpvj((Boolean) null);
        return pzqcxkrstpkgvuxxlorsVar;
    }

    public void yvrzbryuycempgkdhpvj(Boolean bool) {
        this.dyrapphjndqarxdhyvgv = bool;
    }

    protected static void yvrzbryuycempgkdhpvj(Context context, String str) {
        danumarvmgpbarrzqyrz = new oacciftezlubzxpkwvyc(context, str);
    }

    public void dbuymyhwehsdoxafsfpy(Context context, String str) {
        zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(context, Column.DEVICE_ID, str);
        this.dbuymyhwehsdoxafsfpy = str;
    }

    public void uusbetktgiikylwfbevs(Context context, String str) {
        zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(context, "serverUrl", str);
        this.uusbetktgiikylwfbevs = str;
    }

    public void dbuymyhwehsdoxafsfpy(Context context, Boolean bool) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("Paygilant", 0).edit();
        editorEdit.putBoolean("isExperimental", bool.booleanValue());
        editorEdit.apply();
        this.mxodkqpwhcryvsgsvabl = bool;
    }

    public void yvrzbryuycempgkdhpvj(Context context, Boolean bool) {
        zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(context, "approvePolicy", bool);
        this.efmnkxwvqeqnaehsyess = bool;
    }

    public void yvrzbryuycempgkdhpvj(Context context, mchrzzmrgfkrwlaxrlna mchrzzmrgfkrwlaxrlnaVar) {
        if (context != null) {
            dbuymyhwehsdoxafsfpy(context, mchrzzmrgfkrwlaxrlnaVar.mxodkqpwhcryvsgsvabl());
        }
        this.ctfsaqlysacfjtqixtmr = mchrzzmrgfkrwlaxrlnaVar;
    }
}
