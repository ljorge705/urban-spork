package com.paygilant.PG_FraudDetection_SDK;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.Window;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.db.Column;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.paygilant.PG_FraudDetection_SDK.Biometric.PaygilantScreenListener;
import com.paygilant.pgdata.CheckPoint.ScreenListenerType;
import io.sentry.cache.EnvelopeCache;
import io.sentry.protocol.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class PaygilantManager {
    private static PaygilantManager rdxarqqumxakclvwzpqr = null;
    private static final String uhujfisxsgsrhmusmdur = "PaygilantManagerThread";
    private Handler ctfsaqlysacfjtqixtmr;
    private int cwzojhoqdsccekmlpbcq;
    private Boolean danumarvmgpbarrzqyrz;
    private final ckoaurzatqnuxckmholu.wfdoaqfvfyoijpgclxfu dbuymyhwehsdoxafsfpy;
    private com.paygilant.PG_FraudDetection_SDK.fdwipeifdmvqsbqrrpyp dtzqkpwicmyznzcqlscc;
    private Handler dyrapphjndqarxdhyvgv;
    private Handler efmnkxwvqeqnaehsyess;
    private long flxvypzwnsemgoacejis;
    private yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fniextxrjqlolhwhqggv;
    private boolean gsokbpxrpyefzacjsrbi;
    private int gtykjqtliutrjfvjtiex;
    private int hbycjaoesutelfxwaoca;
    private int hekbabbvryaiwbpvazlo;
    private boolean hohwaucsiylhmibnvokv;
    private final Object hzboqnueafthurvgzlhd;
    private boolean kqybgnwtttwkvdwxaqhw;
    private int ldeiitcdqlqzkidvrbjy;
    private omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu libyhucgefaitkehplch;
    private final Runnable lleeevhiydcwptuwzadc;
    private Activity mqwpnefpqywpyryhimya;
    private Handler mxodkqpwhcryvsgsvabl;
    private boolean nicafiaansnuaopzwdwm;
    private Boolean ooztjhejjvpgrdhjnyju;
    private tjobugwlaqmndtgupgkk.fdwipeifdmvqsbqrrpyp ppdfopkmksgbbjoukavl;
    private final Runnable pyxmijmxbomdjegczksl;
    private int utxtilfqoxdwcqlqpjoj;
    private String uusbetktgiikylwfbevs;
    private final Runnable uwanvjsqbzasnsnlxnqi;
    private Boolean vikftlgmuszlvyjnlikz;
    private Handler vjgujdxqyzpnlimdrvvt;
    private boolean vodttdxrgsufbynbtbbg;
    private boolean vpjqwyqiclwncdgbkkkg;
    private yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp vqslvxgmgnhrmgypwmhq;
    private final Context yvrzbryuycempgkdhpvj;
    private String zwobhtbpnlqafneiqjbw;

    class asvpglyfnwnhyiooalpb extends ArrayList {
        final /* synthetic */ JSONObject yvrzbryuycempgkdhpvj;

        asvpglyfnwnhyiooalpb(JSONObject jSONObject) {
            this.yvrzbryuycempgkdhpvj = jSONObject;
            if (jSONObject != null) {
                add(jSONObject.toString());
            }
        }
    }

    static /* synthetic */ class ccbenmmkjpnsjembpxcv {
        static final /* synthetic */ int[] yvrzbryuycempgkdhpvj;

        static {
            int[] iArr = new int[cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.values().length];
            yvrzbryuycempgkdhpvj = iArr;
            try {
                iArr[cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.ADB_ENABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                yvrzbryuycempgkdhpvj[cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.CURRENT_SSID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                yvrzbryuycempgkdhpvj[cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.IS_VPN_ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                yvrzbryuycempgkdhpvj[cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.PERMISSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                yvrzbryuycempgkdhpvj[cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.IS_SUSPICIOUS_MOUSE_ACTIVITY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                yvrzbryuycempgkdhpvj[cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.IS_SUSPICIOUS_UNKNOWN_ACTIVITY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    class cctxrwizduxmjefyvyrx implements Runnable {
        final /* synthetic */ Boolean yvrzbryuycempgkdhpvj;

        cctxrwizduxmjefyvyrx(Boolean bool) {
            this.yvrzbryuycempgkdhpvj = bool;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PaygilantManager.this.danumarvmgpbarrzqyrz.booleanValue()) {
                PaygilantManager.this.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj);
                PaygilantManager.this.mxodkqpwhcryvsgsvabl.postDelayed(this, PaygilantManager.this.yvrzbryuycempgkdhpvj(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ooztjhejjvpgrdhjnyju().intValue(), PaygilantManager.this.ldeiitcdqlqzkidvrbjy));
            }
        }
    }

    class cdabktqulpsnjrlnlnii extends ArrayList {
        final /* synthetic */ String dbuymyhwehsdoxafsfpy;
        final /* synthetic */ omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc yvrzbryuycempgkdhpvj;

        cdabktqulpsnjrlnlnii(omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar, String str) {
            this.yvrzbryuycempgkdhpvj = oacciftezlubzxpkwvycVar;
            this.dbuymyhwehsdoxafsfpy = str;
            add(oacciftezlubzxpkwvycVar.toString());
            add(str);
        }
    }

    class cptipfepeuixrxtabhzr extends ArrayList {
        cptipfepeuixrxtabhzr() {
        }
    }

    class fdwipeifdmvqsbqrrpyp extends ArrayList {
        final /* synthetic */ JSONObject yvrzbryuycempgkdhpvj;

        fdwipeifdmvqsbqrrpyp(JSONObject jSONObject) {
            this.yvrzbryuycempgkdhpvj = jSONObject;
            if (jSONObject != null) {
                add(jSONObject.toString());
            }
        }
    }

    class hecekghzkgawkedymcnw extends ArrayList {
        hecekghzkgawkedymcnw() {
        }
    }

    class jfrjraouuicuqboknnmi extends ArrayList {
        jfrjraouuicuqboknnmi() {
            add(String.valueOf(PaygilantManager.this.vpjqwyqiclwncdgbkkkg));
        }
    }

    class kaqespyeeawijtnikoai extends ArrayList {
        final /* synthetic */ File yvrzbryuycempgkdhpvj;

        kaqespyeeawijtnikoai(File file) {
            this.yvrzbryuycempgkdhpvj = file;
            if (file != null) {
                add(file.getPath());
            }
            add(PaygilantManager.this.uusbetktgiikylwfbevs);
        }
    }

    class lbtmiwirddgqkunoyrov extends ArrayList {
        lbtmiwirddgqkunoyrov() {
        }
    }

    class lhsfeadmwwqkbzscxaex extends ArrayList {
        lhsfeadmwwqkbzscxaex() {
        }
    }

    class llyhweatjmvlzlflpaeb extends ArrayList {
        final /* synthetic */ String yvrzbryuycempgkdhpvj;

        llyhweatjmvlzlflpaeb(String str) {
            this.yvrzbryuycempgkdhpvj = str;
            add(str);
        }
    }

    class lmqztflyazmtuunswyyl extends ArrayList {
        final /* synthetic */ String yvrzbryuycempgkdhpvj;

        lmqztflyazmtuunswyyl(String str) {
            this.yvrzbryuycempgkdhpvj = str;
            add(String.valueOf(str));
        }
    }

    class mchrzzmrgfkrwlaxrlna extends ArrayList {
        final /* synthetic */ String dbuymyhwehsdoxafsfpy;
        final /* synthetic */ omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc yvrzbryuycempgkdhpvj;

        mchrzzmrgfkrwlaxrlna(omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar, String str) {
            this.yvrzbryuycempgkdhpvj = oacciftezlubzxpkwvycVar;
            this.dbuymyhwehsdoxafsfpy = str;
            add(oacciftezlubzxpkwvycVar.toString());
            add(str);
        }
    }

    class nlkeiuewguxtleppgqdg extends ArrayList {
        nlkeiuewguxtleppgqdg() {
            add(String.valueOf(PaygilantManager.this.vpjqwyqiclwncdgbkkkg));
        }
    }

    class oacciftezlubzxpkwvyc extends ArrayList {
        oacciftezlubzxpkwvyc() {
        }
    }

    class ppvnkbmzfphuuihfhotp extends ArrayList {
        final /* synthetic */ Boolean yvrzbryuycempgkdhpvj;

        ppvnkbmzfphuuihfhotp(Boolean bool) {
            this.yvrzbryuycempgkdhpvj = bool;
            if (bool != null) {
                add(String.valueOf(bool));
            }
        }
    }

    class pzqcxkrstpkgvuxxlors implements Application.ActivityLifecycleCallbacks {
        pzqcxkrstpkgvuxxlors() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            PaygilantManager.this.uusbetktgiikylwfbevs();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            PaygilantManager.this.kqybgnwtttwkvdwxaqhw = true;
            PaygilantManager.this.mqwpnefpqywpyryhimya = activity;
            PaygilantManager paygilantManager = PaygilantManager.this;
            paygilantManager.dbuymyhwehsdoxafsfpy(paygilantManager.mqwpnefpqywpyryhimya);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            PaygilantManager.this.kqybgnwtttwkvdwxaqhw = false;
            PaygilantManager.this.mqwpnefpqywpyryhimya = activity;
            PaygilantManager paygilantManager = PaygilantManager.this;
            paygilantManager.yvrzbryuycempgkdhpvj(paygilantManager.mqwpnefpqywpyryhimya);
            long jCurrentTimeMillis = System.currentTimeMillis() - PaygilantManager.this.flxvypzwnsemgoacejis;
            long jLongValue = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().danumarvmgpbarrzqyrz().longValue();
            if (com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr() == "no_device_id" || !PaygilantManager.this.ooztjhejjvpgrdhjnyju.booleanValue() || !com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().uusbetktgiikylwfbevs().booleanValue() || jCurrentTimeMillis <= jLongValue) {
                return;
            }
            PaygilantManager.this.gtykjqtliutrjfvjtiex();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    class qazlvjxyyxbchcvecwjp extends ArrayList {
        final /* synthetic */ Boolean yvrzbryuycempgkdhpvj;

        qazlvjxyyxbchcvecwjp(Boolean bool) {
            this.yvrzbryuycempgkdhpvj = bool;
            if (bool != null) {
                add(String.valueOf(bool));
            }
        }
    }

    class rvhplcmttaqkggggovhx implements Runnable {
        rvhplcmttaqkggggovhx() {
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            PaygilantManager.this.cwzojhoqdsccekmlpbcq();
            PaygilantManager.this.ctfsaqlysacfjtqixtmr.postDelayed(this, omamhfdoazbdavfkujac.rvhplcmttaqkggggovhx.yvrzbryuycempgkdhpvj);
        }
    }

    class vnufwshzeizvjmboyyju extends ArrayList {
        final /* synthetic */ boolean yvrzbryuycempgkdhpvj;

        vnufwshzeizvjmboyyju(boolean z) {
            this.yvrzbryuycempgkdhpvj = z;
            add(String.valueOf(z));
        }
    }

    class vubdyxzvpnvcymakzopn extends ArrayList {
        vubdyxzvpnvcymakzopn() {
        }
    }

    class wfdoaqfvfyoijpgclxfu extends ArrayList {
        final /* synthetic */ Boolean yvrzbryuycempgkdhpvj;

        wfdoaqfvfyoijpgclxfu(Boolean bool) {
            this.yvrzbryuycempgkdhpvj = bool;
            if (bool != null) {
                add(String.valueOf(bool));
            }
        }
    }

    class wgeshaitmpzrwttiokth implements Runnable {
        wgeshaitmpzrwttiokth() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PaygilantManager.this.ooztjhejjvpgrdhjnyju.booleanValue()) {
                return;
            }
            PaygilantManager.this.danumarvmgpbarrzqyrz();
            PaygilantManager.this.dyrapphjndqarxdhyvgv.postDelayed(this, PaygilantManager.this.yvrzbryuycempgkdhpvj(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ooztjhejjvpgrdhjnyju().intValue(), PaygilantManager.this.gtykjqtliutrjfvjtiex));
        }
    }

    class xufavitsdcqtqeuvulja extends ArrayList {
        xufavitsdcqtqeuvulja() {
        }
    }

    class yktdemhqxtjzzjfxodtk extends ArrayList {
        yktdemhqxtjzzjfxodtk() {
        }
    }

    class ymdxlfsorgvechaxkrll implements Runnable {
        ymdxlfsorgvechaxkrll() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PaygilantManager.this.vikftlgmuszlvyjnlikz.booleanValue()) {
                PaygilantManager.this.ctfsaqlysacfjtqixtmr();
                PaygilantManager.this.efmnkxwvqeqnaehsyess.postDelayed(this, PaygilantManager.this.yvrzbryuycempgkdhpvj(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ooztjhejjvpgrdhjnyju().intValue(), PaygilantManager.this.cwzojhoqdsccekmlpbcq));
            }
        }
    }

    class yzlqycvnjgiuljrpdgrd implements ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc {
        yzlqycvnjgiuljrpdgrd() {
        }

        @Override // ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc
        public void yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
        }
    }

    class zwlltpaufqribmleigux implements Runnable {
        final /* synthetic */ Long dbuymyhwehsdoxafsfpy;
        final /* synthetic */ List yvrzbryuycempgkdhpvj;

        zwlltpaufqribmleigux(List list, Long l) {
            this.yvrzbryuycempgkdhpvj = list;
            this.dbuymyhwehsdoxafsfpy = l;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PaygilantManager.this.ooztjhejjvpgrdhjnyju.booleanValue() && !PaygilantManager.this.kqybgnwtttwkvdwxaqhw) {
                PaygilantManager.this.uusbetktgiikylwfbevs(this.yvrzbryuycempgkdhpvj);
            }
            PaygilantManager.this.vjgujdxqyzpnlimdrvvt.postDelayed(this, this.dbuymyhwehsdoxafsfpy.longValue());
        }
    }

    private PaygilantManager(Context context, Activity activity, String str) {
        Boolean bool = Boolean.FALSE;
        this.uwanvjsqbzasnsnlxnqi = vjgujdxqyzpnlimdrvvt(bool);
        Boolean bool2 = Boolean.TRUE;
        this.pyxmijmxbomdjegczksl = vjgujdxqyzpnlimdrvvt(bool2);
        this.lleeevhiydcwptuwzadc = ooztjhejjvpgrdhjnyju();
        this.gsokbpxrpyefzacjsrbi = false;
        this.nicafiaansnuaopzwdwm = true;
        this.kqybgnwtttwkvdwxaqhw = false;
        this.vodttdxrgsufbynbtbbg = true;
        this.vpjqwyqiclwncdgbkkkg = false;
        omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu wfdoaqfvfyoijpgclxfuVar = omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.NOT_STARTED;
        this.libyhucgefaitkehplch = wfdoaqfvfyoijpgclxfuVar;
        this.zwobhtbpnlqafneiqjbw = "no_session_id";
        this.hzboqnueafthurvgzlhd = new Object();
        this.hohwaucsiylhmibnvokv = false;
        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(context, Settings.Secure.getString(context.getContentResolver(), "android_id"));
        this.fniextxrjqlolhwhqggv = new yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp(Thread.currentThread().getName(), "PaygilantManager", new lmqztflyazmtuunswyyl(str), yylccyxskpljpfqfhxlx.oacciftezlubzxpkwvyc.Start);
        System.currentTimeMillis();
        this.uusbetktgiikylwfbevs = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr();
        this.gsokbpxrpyefzacjsrbi = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().uusbetktgiikylwfbevs().booleanValue();
        this.mxodkqpwhcryvsgsvabl = null;
        this.efmnkxwvqeqnaehsyess = null;
        this.dyrapphjndqarxdhyvgv = null;
        this.ooztjhejjvpgrdhjnyju = bool;
        this.vikftlgmuszlvyjnlikz = bool2;
        this.danumarvmgpbarrzqyrz = bool2;
        this.ldeiitcdqlqzkidvrbjy = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ooztjhejjvpgrdhjnyju().intValue();
        this.cwzojhoqdsccekmlpbcq = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ooztjhejjvpgrdhjnyju().intValue();
        this.gtykjqtliutrjfvjtiex = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ooztjhejjvpgrdhjnyju().intValue();
        this.utxtilfqoxdwcqlqpjoj = 0;
        this.hekbabbvryaiwbpvazlo = 0;
        this.hbycjaoesutelfxwaoca = 0;
        this.nicafiaansnuaopzwdwm = true;
        this.vpjqwyqiclwncdgbkkkg = false;
        this.libyhucgefaitkehplch = wfdoaqfvfyoijpgclxfuVar;
        this.zwobhtbpnlqafneiqjbw = "no_session_id";
        this.hohwaucsiylhmibnvokv = false;
        this.flxvypzwnsemgoacejis = 0L;
        Thread.setDefaultUncaughtExceptionHandler(new com.paygilant.PG_FraudDetection_SDK.rvhplcmttaqkggggovhx(context));
        lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.yvrzbryuycempgkdhpvj(context);
        this.yvrzbryuycempgkdhpvj = context;
        this.dbuymyhwehsdoxafsfpy = new ckoaurzatqnuxckmholu.wfdoaqfvfyoijpgclxfu(context, str);
        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().uusbetktgiikylwfbevs(context, str);
        this.mqwpnefpqywpyryhimya = activity;
        if (activity != null && !activity.isDestroyed()) {
            uusbetktgiikylwfbevs();
        }
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(dbuymyhwehsdoxafsfpy());
        System.currentTimeMillis();
        this.vqslvxgmgnhrmgypwmhq = new yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp(Thread.currentThread().getName(), "PaygilantManager", new cptipfepeuixrxtabhzr(), yylccyxskpljpfqfhxlx.oacciftezlubzxpkwvyc.End);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void ctfsaqlysacfjtqixtmr(Boolean bool) {
    }

    public static PaygilantManager getInstance(Context context) {
        PaygilantManager paygilantManager = rdxarqqumxakclvwzpqr;
        if (paygilantManager != null) {
            return paygilantManager;
        }
        if (dbuymyhwehsdoxafsfpy(context)) {
            return null;
        }
        return rdxarqqumxakclvwzpqr;
    }

    @Deprecated
    public static void init(Context context, Activity activity, String str) throws PaygilantException {
        if (str == null || str.isEmpty()) {
            throw new PaygilantException("Server URL cannot be null or empty");
        }
        if (context == null) {
            throw new PaygilantException("PaygilantManager Context can't be Null");
        }
        rdxarqqumxakclvwzpqr = new PaygilantManager(context.getApplicationContext(), activity, str);
    }

    private boolean mxodkqpwhcryvsgsvabl() {
        return this.nicafiaansnuaopzwdwm;
    }

    private void yvrzbryuycempgkdhpvj(boolean z) {
        this.nicafiaansnuaopzwdwm = z;
    }

    public String getDeviceId() {
        return this.uusbetktgiikylwfbevs;
    }

    public String getServerUrl() {
        return com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().dyrapphjndqarxdhyvgv();
    }

    @Deprecated
    public String getSessionId() {
        return com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().danumarvmgpbarrzqyrz();
    }

    @Deprecated
    public void initializeDeviceId() {
    }

    public boolean isApprovePolicy() {
        return this.gsokbpxrpyefzacjsrbi;
    }

    @Deprecated
    public void logout() {
    }

    @Deprecated
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        ArrayList arrayList = new ArrayList();
        if (boolVjgujdxqyzpnlimdrvvt.booleanValue()) {
            arrayList.add(Arrays.toString(strArr));
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "onRequestPermissionsResult Deprecated", arrayList));
    }

    public void setApprovePolicy(boolean z) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "setApprovePolicy", new vnufwshzeizvjmboyyju(z));
        if (mxodkqpwhcryvsgsvabl() && z) {
            yvrzbryuycempgkdhpvj(false);
            vjgujdxqyzpnlimdrvvt();
            yvrzbryuycempgkdhpvj();
        }
        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj, Boolean.valueOf(z));
        this.gsokbpxrpyefzacjsrbi = z;
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    @Deprecated
    public void setUserId(String str) {
    }

    @Deprecated
    public PaygilantScreenListener startNewScreenListener(ScreenListenerType screenListenerType, int i, Activity activity) {
        if (this.vodttdxrgsufbynbtbbg) {
            setApprovePolicy(true);
            this.vodttdxrgsufbynbtbbg = false;
        }
        return new PaygilantScreenListener(this.yvrzbryuycempgkdhpvj, new yzlqycvnjgiuljrpdgrd(), screenListenerType, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ctfsaqlysacfjtqixtmr() {
        JSONObject jSONObjectDbuymyhwehsdoxafsfpy;
        try {
            jSONObjectDbuymyhwehsdoxafsfpy = lpydlrieyhramzmsmaih.oacciftezlubzxpkwvyc.dbuymyhwehsdoxafsfpy();
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            jSONObjectDbuymyhwehsdoxafsfpy = null;
        }
        this.dbuymyhwehsdoxafsfpy.dbuymyhwehsdoxafsfpy(new omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr(), yvrzbryuycempgkdhpvj(jSONObjectDbuymyhwehsdoxafsfpy, null, null, this.yvrzbryuycempgkdhpvj.getPackageName()), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().mxodkqpwhcryvsgsvabl(), Integer.valueOf(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().vikftlgmuszlvyjnlikz().intValue())), new ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda1
            @Override // ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc
            public final void yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
                this.f$0.ctfsaqlysacfjtqixtmr(jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cwzojhoqdsccekmlpbcq() throws JSONException {
        if (yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj().length() <= 0 || com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr().equals("no_device_id")) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tracingData", yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.dbuymyhwehsdoxafsfpy.vjgujdxqyzpnlimdrvvt(new omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr(), yvrzbryuycempgkdhpvj(jSONObject, null, null, this.yvrzbryuycempgkdhpvj.getPackageName()), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().mxodkqpwhcryvsgsvabl(), 15000), new ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda9
            @Override // ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc
            public final void yvrzbryuycempgkdhpvj(JSONObject jSONObject2) {
                this.f$0.efmnkxwvqeqnaehsyess(jSONObject2);
            }
        });
    }

    private void danumarvmgpbarrzqyrz(JSONObject jSONObject) {
        if (!dbuymyhwehsdoxafsfpy(jSONObject).booleanValue()) {
            int i = this.cwzojhoqdsccekmlpbcq;
            int i2 = omamhfdoazbdavfkujac.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy;
            if (i >= i2) {
                this.cwzojhoqdsccekmlpbcq = i2;
                if (this.hbycjaoesutelfxwaoca >= 5) {
                    this.vikftlgmuszlvyjnlikz = Boolean.FALSE;
                }
            } else {
                this.cwzojhoqdsccekmlpbcq = i * com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().dyrapphjndqarxdhyvgv().intValue();
            }
            this.hbycjaoesutelfxwaoca++;
            return;
        }
        cyxdnekglwjxeogqvedd.mchrzzmrgfkrwlaxrlna mchrzzmrgfkrwlaxrlnaVar = new cyxdnekglwjxeogqvedd.mchrzzmrgfkrwlaxrlna(jSONObject.getJSONObject("output"));
        this.vikftlgmuszlvyjnlikz = Boolean.FALSE;
        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj, mchrzzmrgfkrwlaxrlnaVar);
        if (yvrzbryuycempgkdhpvj(mchrzzmrgfkrwlaxrlnaVar)) {
            if (mchrzzmrgfkrwlaxrlnaVar.ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt().booleanValue()) {
                yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj(this.fniextxrjqlolhwhqggv, com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().efmnkxwvqeqnaehsyess().intValue());
                yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj(this.vqslvxgmgnhrmgypwmhq, com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().efmnkxwvqeqnaehsyess().intValue());
            }
            ldeiitcdqlqzkidvrbjy();
        }
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "manipulateSdkConfiguration", new fdwipeifdmvqsbqrrpyp(jSONObject));
        if (!com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().vjgujdxqyzpnlimdrvvt().booleanValue() || com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr().equals("no_device_id")) {
            yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj, omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc.Paygilant_crash, com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr());
        } else {
            dbuymyhwehsdoxafsfpy(this.yvrzbryuycempgkdhpvj, omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc.Paygilant_crash, com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr());
        }
        if (mchrzzmrgfkrwlaxrlnaVar.uusbetktgiikylwfbevs() != null && !mchrzzmrgfkrwlaxrlnaVar.uusbetktgiikylwfbevs().isEmpty()) {
            this.vpjqwyqiclwncdgbkkkg = yvrzbryuycempgkdhpvj(mchrzzmrgfkrwlaxrlnaVar.uusbetktgiikylwfbevs());
            yvrzbryuycempgkdhpvj(this.mqwpnefpqywpyryhimya);
            dbuymyhwehsdoxafsfpy(mchrzzmrgfkrwlaxrlnaVar);
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void dyrapphjndqarxdhyvgv(JSONObject jSONObject) {
        synchronized (this.hzboqnueafthurvgzlhd) {
            if (dbuymyhwehsdoxafsfpy(jSONObject).booleanValue()) {
                this.libyhucgefaitkehplch = omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.SUCCESS;
                this.flxvypzwnsemgoacejis = System.currentTimeMillis();
            } else {
                this.libyhucgefaitkehplch = omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.FAILED;
            }
            this.hzboqnueafthurvgzlhd.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void efmnkxwvqeqnaehsyess(JSONObject jSONObject) {
        if (dbuymyhwehsdoxafsfpy(jSONObject).booleanValue()) {
            yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj(new JSONArray());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gtykjqtliutrjfvjtiex() {
        synchronized (this.hzboqnueafthurvgzlhd) {
            this.libyhucgefaitkehplch = omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.IN_PROGRESS;
        }
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "sentSessionTimeOut", new oacciftezlubzxpkwvyc());
        if (lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs() == null) {
            lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj);
        }
        JSONObject jSONObjectYvrzbryuycempgkdhpvj = lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs().yvrzbryuycempgkdhpvj(Boolean.TRUE);
        this.ppdfopkmksgbbjoukavl = new tjobugwlaqmndtgupgkk.fdwipeifdmvqsbqrrpyp(jSONObjectYvrzbryuycempgkdhpvj);
        this.dbuymyhwehsdoxafsfpy.ctfsaqlysacfjtqixtmr(new omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp(null, yvrzbryuycempgkdhpvj(jSONObjectYvrzbryuycempgkdhpvj, null, null, this.yvrzbryuycempgkdhpvj.getPackageName()), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().mxodkqpwhcryvsgsvabl(), Integer.valueOf(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().vikftlgmuszlvyjnlikz().intValue())), new ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda7
            @Override // ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc
            public final void yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
                this.f$0.dyrapphjndqarxdhyvgv(jSONObject);
            }
        });
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    public static void init(Context context, String str) throws PaygilantException {
        if (str == null || str.isEmpty()) {
            throw new PaygilantException("Server URL cannot be null or empty");
        }
        if (context == null) {
            throw new PaygilantException("PaygilantManager Context can't be Null");
        }
        rdxarqqumxakclvwzpqr = new PaygilantManager(context.getApplicationContext(), null, str);
    }

    private void ldeiitcdqlqzkidvrbjy() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.ctfsaqlysacfjtqixtmr = handler;
        handler.postDelayed(new rvhplcmttaqkggggovhx(), omamhfdoazbdavfkujac.rvhplcmttaqkggggovhx.yvrzbryuycempgkdhpvj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ooztjhejjvpgrdhjnyju, reason: merged with bridge method [inline-methods] */
    public void uusbetktgiikylwfbevs(JSONObject jSONObject) throws JSONException {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "manageResponseDeviceId", new asvpglyfnwnhyiooalpb(jSONObject));
        try {
            if (jSONObject.has("output")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("output");
                if (jSONObject2.has(Column.DEVICE_ID)) {
                    String string = jSONObject2.getString(Column.DEVICE_ID);
                    if (!string.equals("no_device_id")) {
                        if (!com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr().equals(string)) {
                            com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().dbuymyhwehsdoxafsfpy(this.yvrzbryuycempgkdhpvj, string);
                            yvrzbryuycempgkdhpvj(string);
                            dyrapphjndqarxdhyvgv();
                            vikftlgmuszlvyjnlikz();
                        }
                        this.danumarvmgpbarrzqyrz = Boolean.FALSE;
                    }
                }
            } else {
                int i = this.ldeiitcdqlqzkidvrbjy;
                int i2 = omamhfdoazbdavfkujac.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy;
                if (i >= i2) {
                    this.ldeiitcdqlqzkidvrbjy = i2;
                    if (this.utxtilfqoxdwcqlqpjoj >= 5) {
                        this.danumarvmgpbarrzqyrz = Boolean.FALSE;
                    }
                } else {
                    this.ldeiitcdqlqzkidvrbjy = i * com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().dyrapphjndqarxdhyvgv().intValue();
                }
                this.utxtilfqoxdwcqlqpjoj++;
            }
        } catch (JSONException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private void vikftlgmuszlvyjnlikz() {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "sendDataWithNewSessionThread", new lhsfeadmwwqkbzscxaex());
        this.ooztjhejjvpgrdhjnyju = Boolean.FALSE;
        Handler handler = new Handler(Looper.getMainLooper());
        this.dyrapphjndqarxdhyvgv = handler;
        handler.post(this.lleeevhiydcwptuwzadc);
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private void vjgujdxqyzpnlimdrvvt() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.efmnkxwvqeqnaehsyess = handler;
        handler.post(new ymdxlfsorgvechaxkrll());
    }

    public void getSessionId(final SessionIdCallback sessionIdCallback) {
        new Thread(new Runnable() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.yvrzbryuycempgkdhpvj(sessionIdCallback);
            }
        }).start();
    }

    public void onRequestPermissionsResult(final String[] strArr) {
        if (strArr == null) {
            return;
        }
        new Thread(new Runnable() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.yvrzbryuycempgkdhpvj(strArr);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ctfsaqlysacfjtqixtmr(JSONObject jSONObject) {
        try {
            danumarvmgpbarrzqyrz(jSONObject);
        } catch (JSONException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void danumarvmgpbarrzqyrz() {
        synchronized (this.hzboqnueafthurvgzlhd) {
            this.libyhucgefaitkehplch = omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.IN_PROGRESS;
        }
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "sendDataWithNewSession", new hecekghzkgawkedymcnw());
        if (lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs() == null) {
            lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj);
        }
        JSONObject jSONObjectYvrzbryuycempgkdhpvj = lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs().yvrzbryuycempgkdhpvj(Boolean.TRUE);
        this.ppdfopkmksgbbjoukavl = new tjobugwlaqmndtgupgkk.fdwipeifdmvqsbqrrpyp(jSONObjectYvrzbryuycempgkdhpvj);
        this.dbuymyhwehsdoxafsfpy.ctfsaqlysacfjtqixtmr(new omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp(null, yvrzbryuycempgkdhpvj(jSONObjectYvrzbryuycempgkdhpvj, null, null, this.yvrzbryuycempgkdhpvj.getPackageName()), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().mxodkqpwhcryvsgsvabl(), Integer.valueOf(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().vikftlgmuszlvyjnlikz().intValue())), new ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda6
            @Override // ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc
            public final void yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
                this.f$0.vjgujdxqyzpnlimdrvvt(jSONObject);
            }
        });
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private void dbuymyhwehsdoxafsfpy(cyxdnekglwjxeogqvedd.mchrzzmrgfkrwlaxrlna mchrzzmrgfkrwlaxrlnaVar) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (cyxdnekglwjxeogqvedd.oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar : mchrzzmrgfkrwlaxrlnaVar.uusbetktgiikylwfbevs()) {
            if (oacciftezlubzxpkwvycVar.dbuymyhwehsdoxafsfpy().booleanValue()) {
                arrayList.add(oacciftezlubzxpkwvycVar.uusbetktgiikylwfbevs());
                z = true;
            }
        }
        if (z) {
            yvrzbryuycempgkdhpvj(arrayList, mchrzzmrgfkrwlaxrlnaVar.efmnkxwvqeqnaehsyess());
        }
    }

    private void dyrapphjndqarxdhyvgv() {
        Runnable runnable;
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "restartCallingSessionThread", new yktdemhqxtjzzjfxodtk());
        Handler handler = this.dyrapphjndqarxdhyvgv;
        if (handler != null && (runnable = this.lleeevhiydcwptuwzadc) != null) {
            handler.removeCallbacks(runnable);
        }
        this.ooztjhejjvpgrdhjnyju = Boolean.FALSE;
        this.gtykjqtliutrjfvjtiex = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ooztjhejjvpgrdhjnyju().intValue();
        this.hekbabbvryaiwbpvazlo = 0;
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private void efmnkxwvqeqnaehsyess() {
        Runnable runnable;
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "restartCallingDeviceIdThread", new vubdyxzvpnvcymakzopn());
        Handler handler = this.mxodkqpwhcryvsgsvabl;
        if (handler != null && (runnable = this.uwanvjsqbzasnsnlxnqi) != null) {
            handler.removeCallbacks(runnable);
        }
        this.danumarvmgpbarrzqyrz = Boolean.FALSE;
        this.ldeiitcdqlqzkidvrbjy = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ooztjhejjvpgrdhjnyju().intValue();
        this.utxtilfqoxdwcqlqpjoj = 0;
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    @Deprecated
    public static void init(Context context, String str, String str2) throws PaygilantException {
        if (str == null || str.isEmpty()) {
            throw new PaygilantException("Server URL cannot be null or empty");
        }
        if (context == null) {
            throw new PaygilantException("PaygilantManager Context can't be Null");
        }
        rdxarqqumxakclvwzpqr = new PaygilantManager(context.getApplicationContext(), null, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void mxodkqpwhcryvsgsvabl(JSONObject jSONObject) {
        if (dbuymyhwehsdoxafsfpy(jSONObject).booleanValue()) {
            this.flxvypzwnsemgoacejis = System.currentTimeMillis();
        }
    }

    private Runnable ooztjhejjvpgrdhjnyju() {
        return new wgeshaitmpzrwttiokth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uusbetktgiikylwfbevs() {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "getAndSentInitData", new xufavitsdcqtqeuvulja());
        if (mxodkqpwhcryvsgsvabl() && isApprovePolicy()) {
            yvrzbryuycempgkdhpvj(false);
            vjgujdxqyzpnlimdrvvt();
            yvrzbryuycempgkdhpvj();
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void vjgujdxqyzpnlimdrvvt(JSONObject jSONObject) {
        synchronized (this.hzboqnueafthurvgzlhd) {
            if (dbuymyhwehsdoxafsfpy(jSONObject).booleanValue()) {
                this.libyhucgefaitkehplch = omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.SUCCESS;
                this.zwobhtbpnlqafneiqjbw = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().danumarvmgpbarrzqyrz();
                this.flxvypzwnsemgoacejis = System.currentTimeMillis();
                this.ooztjhejjvpgrdhjnyju = Boolean.TRUE;
            } else {
                this.libyhucgefaitkehplch = omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.FAILED;
                int i = this.gtykjqtliutrjfvjtiex;
                int i2 = omamhfdoazbdavfkujac.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy;
                if (i >= i2) {
                    this.gtykjqtliutrjfvjtiex = i2;
                    if (this.hekbabbvryaiwbpvazlo >= 5) {
                        this.ooztjhejjvpgrdhjnyju = Boolean.TRUE;
                    }
                } else {
                    this.gtykjqtliutrjfvjtiex = i * com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().dyrapphjndqarxdhyvgv().intValue();
                }
                this.hekbabbvryaiwbpvazlo++;
            }
            this.hzboqnueafthurvgzlhd.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dbuymyhwehsdoxafsfpy(Activity activity) {
        Window window;
        com.paygilant.PG_FraudDetection_SDK.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar;
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "disableBioListener", new nlkeiuewguxtleppgqdg());
        if (this.vpjqwyqiclwncdgbkkkg && activity != null && (window = activity.getWindow()) != null && (fdwipeifdmvqsbqrrpypVar = this.dtzqkpwicmyznzcqlscc) != null) {
            window.setCallback(fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj());
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private Boolean uusbetktgiikylwfbevs(Boolean bool) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "ifNeedActiveHeuristicsEvent", new ppvnkbmzfphuuihfhotp(bool));
        if (!bool.booleanValue()) {
            String strYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj, "PERMISSION_LIST", "");
            if (!strYvrzbryuycempgkdhpvj.contains("android.permission.READ_CONTACTS") && com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj, "android.permission.READ_CONTACTS").booleanValue()) {
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(this.yvrzbryuycempgkdhpvj, "PERMISSION_LIST", strYvrzbryuycempgkdhpvj + ",android.permission.READ_CONTACTS");
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj, String.valueOf(true));
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
                return Boolean.TRUE;
            }
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
        return bool;
    }

    private Runnable vjgujdxqyzpnlimdrvvt(Boolean bool) {
        return new cctxrwizduxmjefyvyrx(bool);
    }

    private Application.ActivityLifecycleCallbacks dbuymyhwehsdoxafsfpy() {
        return new pzqcxkrstpkgvuxxlors();
    }

    private void dbuymyhwehsdoxafsfpy(Boolean bool) {
        Handler handler;
        Runnable runnable;
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "getDeviceIdThread", new wfdoaqfvfyoijpgclxfu(bool));
        this.mxodkqpwhcryvsgsvabl = new Handler(Looper.getMainLooper());
        if (bool.booleanValue()) {
            handler = this.mxodkqpwhcryvsgsvabl;
            runnable = this.pyxmijmxbomdjegczksl;
        } else {
            handler = this.mxodkqpwhcryvsgsvabl;
            runnable = this.uwanvjsqbzasnsnlxnqi;
        }
        handler.post(runnable);
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uusbetktgiikylwfbevs(List list) {
        tjobugwlaqmndtgupgkk.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar = this.ppdfopkmksgbbjoukavl;
        if (fdwipeifdmvqsbqrrpypVar == null || fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy() == null || !yvrzbryuycempgkdhpvj(list, this.ppdfopkmksgbbjoukavl.dbuymyhwehsdoxafsfpy())) {
            return;
        }
        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju().yvrzbryuycempgkdhpvj(true);
        JSONObject jSONObjectYvrzbryuycempgkdhpvj = lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs().yvrzbryuycempgkdhpvj(Boolean.TRUE);
        this.ppdfopkmksgbbjoukavl = new tjobugwlaqmndtgupgkk.fdwipeifdmvqsbqrrpyp(jSONObjectYvrzbryuycempgkdhpvj);
        this.dbuymyhwehsdoxafsfpy.ctfsaqlysacfjtqixtmr(new omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp(null, yvrzbryuycempgkdhpvj(jSONObjectYvrzbryuycempgkdhpvj, null, null, this.yvrzbryuycempgkdhpvj.getPackageName()), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().mxodkqpwhcryvsgsvabl(), Integer.valueOf(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().vikftlgmuszlvyjnlikz().intValue())), new ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda5
            @Override // ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc
            public final void yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
                this.f$0.mxodkqpwhcryvsgsvabl(jSONObject);
            }
        });
    }

    private Boolean dbuymyhwehsdoxafsfpy(JSONObject jSONObject) {
        if (jSONObject == null) {
            return Boolean.FALSE;
        }
        try {
            return Boolean.valueOf(jSONObject.getInt(Response.JsonKeys.STATUS_CODE) == 200);
        } catch (JSONException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void yvrzbryuycempgkdhpvj(Activity activity) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "activeBioListener", new jfrjraouuicuqboknnmi());
        if (this.vpjqwyqiclwncdgbkkkg && activity != null && !this.kqybgnwtttwkvdwxaqhw) {
            Window window = activity.getWindow();
            boolean zBooleanValue = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().vikftlgmuszlvyjnlikz().booleanValue();
            boolean zBooleanValue2 = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ldeiitcdqlqzkidvrbjy().booleanValue();
            if (window != null && (!zBooleanValue || !zBooleanValue2)) {
                com.paygilant.PG_FraudDetection_SDK.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar = new com.paygilant.PG_FraudDetection_SDK.fdwipeifdmvqsbqrrpyp(window.getCallback(), zBooleanValue, zBooleanValue2);
                this.dtzqkpwicmyznzcqlscc = fdwipeifdmvqsbqrrpypVar;
                window.setCallback(fdwipeifdmvqsbqrrpypVar);
            }
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private static String[] dbuymyhwehsdoxafsfpy(List list) {
        return (String[]) list.toArray(new String[0]);
    }

    private boolean yvrzbryuycempgkdhpvj(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            cyxdnekglwjxeogqvedd.oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar = (cyxdnekglwjxeogqvedd.oacciftezlubzxpkwvyc) it.next();
            if (oacciftezlubzxpkwvycVar.uusbetktgiikylwfbevs() == cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.IS_SUSPICIOUS_MOUSE_ACTIVITY && oacciftezlubzxpkwvycVar.dbuymyhwehsdoxafsfpy().booleanValue()) {
                return true;
            }
            if (oacciftezlubzxpkwvycVar.uusbetktgiikylwfbevs() == cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx.IS_SUSPICIOUS_UNKNOWN_ACTIVITY && oacciftezlubzxpkwvycVar.dbuymyhwehsdoxafsfpy().booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private static boolean dbuymyhwehsdoxafsfpy(Context context) {
        uqgffrurkpkmcepvnfkx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar = new uqgffrurkpkmcepvnfkx.fdwipeifdmvqsbqrrpyp();
        fdwipeifdmvqsbqrrpypVar.ctfsaqlysacfjtqixtmr("PaygilantManager obj is null");
        fdwipeifdmvqsbqrrpypVar.uusbetktgiikylwfbevs("PaygilantManager obj is null");
        fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj("PaygilantManager obj is null");
        fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(0);
        fdwipeifdmvqsbqrrpypVar.vjgujdxqyzpnlimdrvvt("3.0.6");
        fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy(Build.MODEL);
        fdwipeifdmvqsbqrrpypVar.mxodkqpwhcryvsgsvabl(Build.VERSION.RELEASE);
        fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(Long.valueOf(System.currentTimeMillis()));
        new omamhfdoazbdavfkujac.zwlltpaufqribmleigux(fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(), context, omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc.Paygilant_crash.toString(), UUID.randomUUID().toString(), new ckoaurzatqnuxckmholu.rvhplcmttaqkggggovhx() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda8
            @Override // ckoaurzatqnuxckmholu.rvhplcmttaqkggggovhx
            public final void yvrzbryuycempgkdhpvj(Boolean bool) {
                PaygilantManager.ctfsaqlysacfjtqixtmr(bool);
            }
        }).yvrzbryuycempgkdhpvj();
        String strYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "serverUrl", (String) null);
        if (context == null) {
            throw new PaygilantRunTimeException("PaygilantManager Context can't be Null");
        }
        if (strYvrzbryuycempgkdhpvj == null) {
            throw new PaygilantRunTimeException("PaygilantManager need to Initialize first");
        }
        try {
            init(context, strYvrzbryuycempgkdhpvj);
        } catch (PaygilantException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
        return false;
    }

    private void yvrzbryuycempgkdhpvj() {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "callDeviceIdScenarios", new lbtmiwirddgqkunoyrov());
        if (com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr().equals("no_device_id")) {
            dbuymyhwehsdoxafsfpy(Boolean.FALSE);
        } else {
            vikftlgmuszlvyjnlikz();
            yvrzbryuycempgkdhpvj(Boolean.FALSE);
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private void dbuymyhwehsdoxafsfpy(Context context, omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar, String str) {
        JSONObject jSONObject;
        String strYvrzbryuycempgkdhpvj;
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, oacciftezlubzxpkwvycVar).booleanValue()) {
            return;
        }
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "sendingFileToServer", new mchrzzmrgfkrwlaxrlna(oacciftezlubzxpkwvycVar, str));
        for (File file : new File(this.yvrzbryuycempgkdhpvj.getFilesDir() + File.separator + oacciftezlubzxpkwvycVar.toString()).listFiles()) {
            try {
                strYvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj(file);
            } catch (IOException | JSONException e) {
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
                jSONObject = null;
            }
            if (strYvrzbryuycempgkdhpvj.equals("")) {
                file.delete();
            } else {
                jSONObject = new JSONObject(strYvrzbryuycempgkdhpvj);
                yvrzbryuycempgkdhpvj(oacciftezlubzxpkwvycVar, file, jSONObject);
            }
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private boolean yvrzbryuycempgkdhpvj(cyxdnekglwjxeogqvedd.mchrzzmrgfkrwlaxrlna mchrzzmrgfkrwlaxrlnaVar) {
        return mchrzzmrgfkrwlaxrlnaVar.ldeiitcdqlqzkidvrbjy().uusbetktgiikylwfbevs().booleanValue() || mchrzzmrgfkrwlaxrlnaVar.ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt().booleanValue() || mchrzzmrgfkrwlaxrlnaVar.ldeiitcdqlqzkidvrbjy().mxodkqpwhcryvsgsvabl().booleanValue() || mchrzzmrgfkrwlaxrlnaVar.ldeiitcdqlqzkidvrbjy().ctfsaqlysacfjtqixtmr().booleanValue() || mchrzzmrgfkrwlaxrlnaVar.ldeiitcdqlqzkidvrbjy().dbuymyhwehsdoxafsfpy().booleanValue();
    }

    private List yvrzbryuycempgkdhpvj(List list, List list2) {
        HashSet hashSet = new HashSet(list);
        ArrayList arrayList = new ArrayList();
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov lbtmiwirddgqkunoyrovVar = (hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov) it.next();
            if (!hashSet.contains(lbtmiwirddgqkunoyrovVar)) {
                arrayList.add("android.permission." + lbtmiwirddgqkunoyrovVar.toString());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void yvrzbryuycempgkdhpvj(Boolean bool) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "getDeviceId", new qazlvjxyyxbchcvecwjp(bool));
        this.dbuymyhwehsdoxafsfpy.yvrzbryuycempgkdhpvj(new omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr(), yvrzbryuycempgkdhpvj(lpydlrieyhramzmsmaih.oacciftezlubzxpkwvyc.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj, uusbetktgiikylwfbevs(bool), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().dbuymyhwehsdoxafsfpy(), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr(), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju()), null, null, this.yvrzbryuycempgkdhpvj.getPackageName()), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().mxodkqpwhcryvsgsvabl(), Integer.valueOf(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().vikftlgmuszlvyjnlikz().intValue())), new ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda3
            @Override // ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc
            public final void yvrzbryuycempgkdhpvj(JSONObject jSONObject) throws JSONException {
                this.f$0.uusbetktgiikylwfbevs(jSONObject);
            }
        });
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private dialoobnhfjfdhmrftlq.asvpglyfnwnhyiooalpb yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
        dialoobnhfjfdhmrftlq.asvpglyfnwnhyiooalpb asvpglyfnwnhyiooalpbVar = new dialoobnhfjfdhmrftlq.asvpglyfnwnhyiooalpb();
        if (!jSONObject.has(QuickTimeAtomTypes.ATOM_KEYS)) {
            return asvpglyfnwnhyiooalpbVar;
        }
        try {
            return new dialoobnhfjfdhmrftlq.asvpglyfnwnhyiooalpb(jSONObject.getJSONObject(QuickTimeAtomTypes.ATOM_KEYS));
        } catch (JSONException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return asvpglyfnwnhyiooalpbVar;
        }
    }

    private JSONObject yvrzbryuycempgkdhpvj(JSONObject jSONObject, ebpswaqugdaofldkaqte.zwlltpaufqribmleigux zwlltpaufqribmleiguxVar, String str, String str2) throws JSONException {
        dialoobnhfjfdhmrftlq.asvpglyfnwnhyiooalpb asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj(jSONObject);
        String string = UUID.randomUUID().toString();
        asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj.uusbetktgiikylwfbevs(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr());
        if (str2 != null) {
            asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj.yvrzbryuycempgkdhpvj(str2);
        }
        asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj.ctfsaqlysacfjtqixtmr(string);
        if (zwlltpaufqribmleiguxVar != null) {
            asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj.yvrzbryuycempgkdhpvj(zwlltpaufqribmleiguxVar);
        }
        asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj.dbuymyhwehsdoxafsfpy(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().dbuymyhwehsdoxafsfpy());
        if (str != null) {
            asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj.vjgujdxqyzpnlimdrvvt(str);
        }
        try {
            JSONObject jSONObjectYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju().yvrzbryuycempgkdhpvj();
            jSONObjectYvrzbryuycempgkdhpvj.remove("failover");
            jSONObject.put(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, jSONObjectYvrzbryuycempgkdhpvj);
            asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj.yvrzbryuycempgkdhpvj((Long) null);
            jSONObject.put(QuickTimeAtomTypes.ATOM_KEYS, asvpglyfnwnhyiooalpbVarYvrzbryuycempgkdhpvj.yvrzbryuycempgkdhpvj());
        } catch (JSONException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
        return jSONObject;
    }

    private static List yvrzbryuycempgkdhpvj(Context context) {
        ArrayList arrayList = new ArrayList();
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_PHONE_STATE").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.READ_PHONE_STATE);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_MEDIA_IMAGES").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.READ_MEDIA_IMAGES);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_MEDIA_VIDEO").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.READ_MEDIA_VIDEO);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_EXTERNAL_STORAGE").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.READ_EXTERNAL_STORAGE);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_CONTACTS").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.READ_CONTACTS);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.WRITE_EXTERNAL_STORAGE").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.WRITE_EXTERNAL_STORAGE);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_FINE_LOCATION").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.ACCESS_FINE_LOCATION);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_COARSE_LOCATION").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.ACCESS_COARSE_LOCATION);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_WIFI_STATE").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.ACCESS_WIFI_STATE);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_CALL_LOG").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.READ_CALL_LOG);
        }
        if (Build.VERSION.SDK_INT < 31 ? com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.BLUETOOTH").booleanValue() : com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.BLUETOOTH_CONNECT").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.BLUETOOTH);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.GET_ACCOUNTS").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.GET_ACCOUNTS);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.CAMERA").booleanValue()) {
            arrayList.add(hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov.CAMERA);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int yvrzbryuycempgkdhpvj(int i, int i2) {
        if (i <= i2) {
            if (i < i2) {
                i2 = i;
                i = i2;
            } else {
                i2 = 0;
            }
        }
        return new Random().nextInt((i - i2) + 1) + i2;
    }

    private boolean yvrzbryuycempgkdhpvj(List list, hbmifujbkwcjpgteyixs.lmqztflyazmtuunswyyl lmqztflyazmtuunswyylVar) {
        Iterator it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx rvhplcmttaqkggggovhxVar = (cyxdnekglwjxeogqvedd.rvhplcmttaqkggggovhx) it.next();
            switch (ccbenmmkjpnsjembpxcv.yvrzbryuycempgkdhpvj[rvhplcmttaqkggggovhxVar.ordinal()]) {
                case 1:
                    boolean zDbuymyhwehsdoxafsfpy = lmqztflyazmtuunswyylVar.dbuymyhwehsdoxafsfpy();
                    if (!this.hohwaucsiylhmibnvokv) {
                        if (zDbuymyhwehsdoxafsfpy) {
                            this.hohwaucsiylhmibnvokv = true;
                        }
                        String strMxodkqpwhcryvsgsvabl = lpydlrieyhramzmsmaih.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl(this.yvrzbryuycempgkdhpvj);
                        String str = zDbuymyhwehsdoxafsfpy ? "1" : "0";
                        if (strMxodkqpwhcryvsgsvabl != null && !str.equals(strMxodkqpwhcryvsgsvabl)) {
                            com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju().dbuymyhwehsdoxafsfpy().add(rvhplcmttaqkggggovhxVar);
                            z = true;
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                    break;
                case 2:
                    String strUusbetktgiikylwfbevs = lmqztflyazmtuunswyylVar.uusbetktgiikylwfbevs();
                    String strDanumarvmgpbarrzqyrz = lpydlrieyhramzmsmaih.oacciftezlubzxpkwvyc.danumarvmgpbarrzqyrz(this.yvrzbryuycempgkdhpvj);
                    if (strDanumarvmgpbarrzqyrz != null && (strUusbetktgiikylwfbevs == null || (strUusbetktgiikylwfbevs != null && !strUusbetktgiikylwfbevs.equals(strDanumarvmgpbarrzqyrz)))) {
                        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju().dbuymyhwehsdoxafsfpy().add(rvhplcmttaqkggggovhxVar);
                        z = true;
                        break;
                    } else {
                        break;
                    }
                    break;
                case 3:
                    Boolean boolMxodkqpwhcryvsgsvabl = lmqztflyazmtuunswyylVar.mxodkqpwhcryvsgsvabl();
                    Boolean boolValueOf = Boolean.valueOf(lpydlrieyhramzmsmaih.oacciftezlubzxpkwvyc.ppdfopkmksgbbjoukavl(this.yvrzbryuycempgkdhpvj));
                    if (boolMxodkqpwhcryvsgsvabl != null && boolMxodkqpwhcryvsgsvabl.equals(boolValueOf)) {
                        break;
                    } else {
                        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju().dbuymyhwehsdoxafsfpy().add(rvhplcmttaqkggggovhxVar);
                        z = true;
                        break;
                    }
                    break;
                case 4:
                    List listEfmnkxwvqeqnaehsyess = lmqztflyazmtuunswyylVar.efmnkxwvqeqnaehsyess();
                    List listYvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj);
                    if (listEfmnkxwvqeqnaehsyess != null && listYvrzbryuycempgkdhpvj != null && !listEfmnkxwvqeqnaehsyess.equals(listYvrzbryuycempgkdhpvj)) {
                        List listYvrzbryuycempgkdhpvj2 = yvrzbryuycempgkdhpvj(listEfmnkxwvqeqnaehsyess, listYvrzbryuycempgkdhpvj);
                        if (!listYvrzbryuycempgkdhpvj2.isEmpty()) {
                            onRequestPermissionsResult(dbuymyhwehsdoxafsfpy(listYvrzbryuycempgkdhpvj2));
                        }
                        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju().dbuymyhwehsdoxafsfpy().add(rvhplcmttaqkggggovhxVar);
                        z = true;
                        break;
                    } else {
                        break;
                    }
                    break;
                case 5:
                    Boolean boolCtfsaqlysacfjtqixtmr = lmqztflyazmtuunswyylVar.ctfsaqlysacfjtqixtmr();
                    Boolean boolVikftlgmuszlvyjnlikz = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().vikftlgmuszlvyjnlikz();
                    if (boolCtfsaqlysacfjtqixtmr != null && boolVikftlgmuszlvyjnlikz != null && !boolCtfsaqlysacfjtqixtmr.booleanValue() && boolVikftlgmuszlvyjnlikz.booleanValue()) {
                        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju().dbuymyhwehsdoxafsfpy().add(rvhplcmttaqkggggovhxVar);
                        z = true;
                        break;
                    } else {
                        break;
                    }
                    break;
                case 6:
                    Boolean boolVjgujdxqyzpnlimdrvvt = lmqztflyazmtuunswyylVar.vjgujdxqyzpnlimdrvvt();
                    Boolean boolLdeiitcdqlqzkidvrbjy = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ldeiitcdqlqzkidvrbjy();
                    if (boolVjgujdxqyzpnlimdrvvt != null && boolLdeiitcdqlqzkidvrbjy != null && !boolVjgujdxqyzpnlimdrvvt.booleanValue() && boolLdeiitcdqlqzkidvrbjy.booleanValue()) {
                        com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ooztjhejjvpgrdhjnyju().dbuymyhwehsdoxafsfpy().add(rvhplcmttaqkggggovhxVar);
                        z = true;
                        break;
                    } else {
                        break;
                    }
                    break;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void yvrzbryuycempgkdhpvj(SessionIdCallback sessionIdCallback) {
        omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu wfdoaqfvfyoijpgclxfuVar;
        omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu wfdoaqfvfyoijpgclxfuVar2;
        String str;
        synchronized (this.hzboqnueafthurvgzlhd) {
            try {
                omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu wfdoaqfvfyoijpgclxfuVar3 = this.libyhucgefaitkehplch;
                wfdoaqfvfyoijpgclxfuVar = omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.IN_PROGRESS;
                if (wfdoaqfvfyoijpgclxfuVar3 == wfdoaqfvfyoijpgclxfuVar) {
                    this.hzboqnueafthurvgzlhd.wait();
                } else if (wfdoaqfvfyoijpgclxfuVar3 == omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.NOT_STARTED) {
                    this.hzboqnueafthurvgzlhd.wait(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().vikftlgmuszlvyjnlikz().longValue());
                }
                wfdoaqfvfyoijpgclxfuVar2 = this.libyhucgefaitkehplch;
            } catch (InterruptedException unused) {
                sessionIdCallback.onReceiver("no_session_id");
            }
            if (wfdoaqfvfyoijpgclxfuVar2 == omamhfdoazbdavfkujac.wfdoaqfvfyoijpgclxfu.SUCCESS) {
                str = this.zwobhtbpnlqafneiqjbw;
                sessionIdCallback.onReceiver(str);
            } else if (wfdoaqfvfyoijpgclxfuVar2 == wfdoaqfvfyoijpgclxfuVar) {
                this.hzboqnueafthurvgzlhd.wait();
                str = this.zwobhtbpnlqafneiqjbw;
                sessionIdCallback.onReceiver(str);
            } else {
                str = "no_session_id";
                sessionIdCallback.onReceiver(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void yvrzbryuycempgkdhpvj(String[] strArr) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        try {
            ArrayList arrayList = new ArrayList();
            if (boolVjgujdxqyzpnlimdrvvt.booleanValue()) {
                arrayList.add(Arrays.toString(strArr));
            }
            yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "onRequestPermissionsResult", arrayList);
            if (lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs() == null) {
                lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj);
            }
            lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs().yvrzbryuycempgkdhpvj(Arrays.asList(strArr));
            String strYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj, "PERMISSION_LIST", "");
            StringBuilder sb = new StringBuilder(strYvrzbryuycempgkdhpvj);
            for (String str : strArr) {
                if (!strYvrzbryuycempgkdhpvj.contains(str) && com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(this.yvrzbryuycempgkdhpvj, str).booleanValue()) {
                    if (str.equals("android.permission.READ_CONTACTS")) {
                        efmnkxwvqeqnaehsyess();
                        Boolean bool = Boolean.TRUE;
                        this.danumarvmgpbarrzqyrz = bool;
                        dbuymyhwehsdoxafsfpy(bool);
                    }
                    lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs().yvrzbryuycempgkdhpvj(str);
                    sb.append(Constants.SEPARATOR_COMMA).append(str);
                }
            }
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(this.yvrzbryuycempgkdhpvj, "PERMISSION_LIST", sb.toString());
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void yvrzbryuycempgkdhpvj(File file, String str, JSONObject jSONObject) {
        if (dbuymyhwehsdoxafsfpy(jSONObject).booleanValue()) {
            file.delete();
        }
    }

    private ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc yvrzbryuycempgkdhpvj(final File file, final String str) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "receiveWithDeleteFile", new kaqespyeeawijtnikoai(file)));
        return new ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc() { // from class: com.paygilant.PG_FraudDetection_SDK.PaygilantManager$$ExternalSyntheticLambda4
            @Override // ckoaurzatqnuxckmholu.oacciftezlubzxpkwvyc
            public final void yvrzbryuycempgkdhpvj(JSONObject jSONObject) {
                this.f$0.yvrzbryuycempgkdhpvj(file, str, jSONObject);
            }
        };
    }

    private void yvrzbryuycempgkdhpvj(Context context, omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar, String str) {
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, oacciftezlubzxpkwvycVar).booleanValue()) {
            return;
        }
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "removeFiles", new cdabktqulpsnjrlnlnii(oacciftezlubzxpkwvycVar, str));
        for (File file : new File(this.yvrzbryuycempgkdhpvj.getFilesDir() + File.separator + oacciftezlubzxpkwvycVar.toString()).listFiles()) {
            file.delete();
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
        }
    }

    private void yvrzbryuycempgkdhpvj(List list, Long l) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.vjgujdxqyzpnlimdrvvt = handler;
        handler.postDelayed(new zwlltpaufqribmleigux(list, l), l.longValue());
    }

    private void yvrzbryuycempgkdhpvj(omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar, File file, JSONObject jSONObject) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "sendingPathFile", new ArrayList());
        omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar = new omamhfdoazbdavfkujac.fdwipeifdmvqsbqrrpyp(com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ctfsaqlysacfjtqixtmr(), yvrzbryuycempgkdhpvj(jSONObject, null, null, this.yvrzbryuycempgkdhpvj.getPackageName()), com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().mxodkqpwhcryvsgsvabl(), 15000);
        if (oacciftezlubzxpkwvycVar == omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc.Paygilant_crash) {
            this.dbuymyhwehsdoxafsfpy.uusbetktgiikylwfbevs(fdwipeifdmvqsbqrrpypVar, yvrzbryuycempgkdhpvj(file, "send file crash response: "));
        }
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private void yvrzbryuycempgkdhpvj(String str) {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "setDeviceId", new llyhweatjmvlzlflpaeb(str));
        this.uusbetktgiikylwfbevs = str;
        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
    }

    private String yvrzbryuycempgkdhpvj(File file) throws IOException {
        Boolean boolVjgujdxqyzpnlimdrvvt = com.paygilant.PG_FraudDetection_SDK.oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().vjgujdxqyzpnlimdrvvt();
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, "stringFromFile", new ArrayList());
        byte[] bArr = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            fileInputStream.read(bArr);
            fileInputStream.close();
            String str = new String(bArr);
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(boolVjgujdxqyzpnlimdrvvt, fdwipeifdmvqsbqrrpypVarYvrzbryuycempgkdhpvj);
            return str;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }
}
