package lpydlrieyhramzmsmaih;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class rvhplcmttaqkggggovhx {
    private static rvhplcmttaqkggggovhx ooztjhejjvpgrdhjnyju;
    final Handler dyrapphjndqarxdhyvgv;
    private oacciftezlubzxpkwvyc mxodkqpwhcryvsgsvabl;
    private Context vjgujdxqyzpnlimdrvvt;
    private JSONObject dbuymyhwehsdoxafsfpy = new JSONObject();
    private JSONObject yvrzbryuycempgkdhpvj = new JSONObject();
    private JSONObject uusbetktgiikylwfbevs = new JSONObject();
    private JSONObject ctfsaqlysacfjtqixtmr = new JSONObject();
    private HashSet efmnkxwvqeqnaehsyess = new HashSet();

    class fdwipeifdmvqsbqrrpyp extends Handler {
        fdwipeifdmvqsbqrrpyp(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message != null) {
                int i = message.what;
                if (i == 0) {
                    rvhplcmttaqkggggovhx.this.dbuymyhwehsdoxafsfpy = (JSONObject) message.obj;
                    return;
                }
                if (i == 1) {
                    rvhplcmttaqkggggovhx.this.yvrzbryuycempgkdhpvj = (JSONObject) message.obj;
                } else if (i == 2) {
                    rvhplcmttaqkggggovhx.this.uusbetktgiikylwfbevs = (JSONObject) message.obj;
                } else {
                    if (i != 3) {
                        return;
                    }
                    rvhplcmttaqkggggovhx.this.ctfsaqlysacfjtqixtmr = (JSONObject) message.obj;
                }
            }
        }
    }

    private rvhplcmttaqkggggovhx(Context context) {
        this.vjgujdxqyzpnlimdrvvt = context;
        Handler handlerCtfsaqlysacfjtqixtmr = ctfsaqlysacfjtqixtmr();
        this.dyrapphjndqarxdhyvgv = handlerCtfsaqlysacfjtqixtmr;
        this.mxodkqpwhcryvsgsvabl = new oacciftezlubzxpkwvyc(handlerCtfsaqlysacfjtqixtmr, context);
    }

    public static rvhplcmttaqkggggovhx uusbetktgiikylwfbevs() {
        return ooztjhejjvpgrdhjnyju;
    }

    public HashSet dbuymyhwehsdoxafsfpy() {
        return this.efmnkxwvqeqnaehsyess;
    }

    private Handler ctfsaqlysacfjtqixtmr() {
        return new fdwipeifdmvqsbqrrpyp(Looper.getMainLooper());
    }

    public void yvrzbryuycempgkdhpvj(List list) {
        lbtmiwirddgqkunoyrov[] lbtmiwirddgqkunoyrovVarArr = (lbtmiwirddgqkunoyrov[]) lbtmiwirddgqkunoyrov.class.getEnumConstants();
        if (lbtmiwirddgqkunoyrovVarArr != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                for (lbtmiwirddgqkunoyrov lbtmiwirddgqkunoyrovVar : lbtmiwirddgqkunoyrovVarArr) {
                    if (str.contains(lbtmiwirddgqkunoyrovVar.toString())) {
                        this.efmnkxwvqeqnaehsyess.add(lbtmiwirddgqkunoyrovVar);
                    }
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void dbuymyhwehsdoxafsfpy(java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: lpydlrieyhramzmsmaih.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy(java.lang.String):void");
    }

    public void yvrzbryuycempgkdhpvj() {
        System.currentTimeMillis();
        try {
            this.dbuymyhwehsdoxafsfpy = this.mxodkqpwhcryvsgsvabl.ldeiitcdqlqzkidvrbjy(this.vjgujdxqyzpnlimdrvvt);
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
        try {
            this.ctfsaqlysacfjtqixtmr = this.mxodkqpwhcryvsgsvabl.pyxmijmxbomdjegczksl(this.vjgujdxqyzpnlimdrvvt);
        } catch (Exception e2) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e2);
        }
        try {
            this.yvrzbryuycempgkdhpvj = this.mxodkqpwhcryvsgsvabl.vjgujdxqyzpnlimdrvvt(this.vjgujdxqyzpnlimdrvvt);
        } catch (Exception e3) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e3);
        }
        try {
            this.uusbetktgiikylwfbevs = this.mxodkqpwhcryvsgsvabl.ctfsaqlysacfjtqixtmr(this.vjgujdxqyzpnlimdrvvt);
        } catch (Exception e4) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e4);
        }
    }

    public JSONObject yvrzbryuycempgkdhpvj(Boolean bool) {
        JSONObject jSONObjectUusbetktgiikylwfbevs;
        JSONObject jSONObject = new JSONObject();
        try {
            try {
                jSONObjectUusbetktgiikylwfbevs = oacciftezlubzxpkwvyc.uusbetktgiikylwfbevs(this.vjgujdxqyzpnlimdrvvt);
            } catch (Exception e) {
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
                jSONObjectUusbetktgiikylwfbevs = null;
            }
            jSONObject.put("checkpoint_additional_data", jSONObjectUusbetktgiikylwfbevs);
            if (bool.booleanValue()) {
                yvrzbryuycempgkdhpvj();
            }
            JSONObject jSONObject2 = this.dbuymyhwehsdoxafsfpy;
            if (jSONObject2 != null && jSONObject2.length() != 0) {
                jSONObject.put("device_data", this.dbuymyhwehsdoxafsfpy);
            }
            JSONObject jSONObject3 = this.ctfsaqlysacfjtqixtmr;
            if (jSONObject3 != null && jSONObject3.length() != 0) {
                jSONObject.put("sim_data", this.ctfsaqlysacfjtqixtmr);
            }
            JSONObject jSONObject4 = this.yvrzbryuycempgkdhpvj;
            if (jSONObject4 != null && jSONObject4.length() != 0) {
                jSONObject.put("user_data", this.yvrzbryuycempgkdhpvj);
            }
            JSONObject jSONObject5 = this.uusbetktgiikylwfbevs;
            if (jSONObject5 != null && jSONObject5.length() != 0) {
                jSONObject.put("settings_data", this.uusbetktgiikylwfbevs);
            }
        } catch (JSONException unused) {
        }
        this.dbuymyhwehsdoxafsfpy = new JSONObject();
        this.yvrzbryuycempgkdhpvj = new JSONObject();
        this.uusbetktgiikylwfbevs = new JSONObject();
        this.ctfsaqlysacfjtqixtmr = new JSONObject();
        return jSONObject;
    }

    public static void yvrzbryuycempgkdhpvj(Context context) {
        if (ooztjhejjvpgrdhjnyju != null || context == null) {
            return;
        }
        ooztjhejjvpgrdhjnyju = new rvhplcmttaqkggggovhx(context.getApplicationContext());
    }

    public void yvrzbryuycempgkdhpvj(String str) {
        try {
            dbuymyhwehsdoxafsfpy(str);
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
    }
}
