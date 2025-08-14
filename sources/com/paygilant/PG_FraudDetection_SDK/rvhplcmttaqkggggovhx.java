package com.paygilant.PG_FraudDetection_SDK;

import android.content.Context;
import android.os.Build;
import java.lang.Thread;
import java.util.UUID;

/* loaded from: classes6.dex */
class rvhplcmttaqkggggovhx implements Thread.UncaughtExceptionHandler {
    private Context dbuymyhwehsdoxafsfpy;
    private Thread.UncaughtExceptionHandler yvrzbryuycempgkdhpvj = Thread.getDefaultUncaughtExceptionHandler();
    private static final Integer uusbetktgiikylwfbevs = 50;
    private static final String ctfsaqlysacfjtqixtmr = "rvhplcmttaqkggggovhx";

    public rvhplcmttaqkggggovhx(Context context) {
        this.dbuymyhwehsdoxafsfpy = null;
        this.dbuymyhwehsdoxafsfpy = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void yvrzbryuycempgkdhpvj(Boolean bool) {
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().vjgujdxqyzpnlimdrvvt().booleanValue()) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            StringBuilder sb = new StringBuilder(th.toString() + "\n\n");
            sb.append("--------- Stack trace ---------\n\n");
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append("    ").append(stackTraceElement.toString()).append("\n");
            }
            sb.append("-------------------------------\n\n");
            StringBuilder sb2 = new StringBuilder("--------- Cause ---------\n\n");
            Throwable cause = th.getCause();
            if (cause != null) {
                sb2.append(cause.toString()).append("\n\n");
                for (StackTraceElement stackTraceElement2 : cause.getStackTrace()) {
                    sb2.append("    ").append(stackTraceElement2.toString()).append("\n");
                }
            }
            sb2.append("-------------------------------\n\n");
            uqgffrurkpkmcepvnfkx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar = new uqgffrurkpkmcepvnfkx.fdwipeifdmvqsbqrrpyp();
            fdwipeifdmvqsbqrrpypVar.ctfsaqlysacfjtqixtmr(th.toString());
            fdwipeifdmvqsbqrrpypVar.uusbetktgiikylwfbevs(sb.toString());
            fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(sb2.toString());
            fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(0);
            fdwipeifdmvqsbqrrpypVar.vjgujdxqyzpnlimdrvvt("3.0.6");
            fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy(Build.MODEL);
            fdwipeifdmvqsbqrrpypVar.mxodkqpwhcryvsgsvabl(Build.VERSION.RELEASE);
            fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(Long.valueOf(System.currentTimeMillis()));
            new omamhfdoazbdavfkujac.zwlltpaufqribmleigux(fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(), this.dbuymyhwehsdoxafsfpy, omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc.Paygilant_crash.toString(), UUID.randomUUID().toString(), new ckoaurzatqnuxckmholu.rvhplcmttaqkggggovhx() { // from class: com.paygilant.PG_FraudDetection_SDK.rvhplcmttaqkggggovhx$$ExternalSyntheticLambda0
                @Override // ckoaurzatqnuxckmholu.rvhplcmttaqkggggovhx
                public final void yvrzbryuycempgkdhpvj(Boolean bool) {
                    rvhplcmttaqkggggovhx.yvrzbryuycempgkdhpvj(bool);
                }
            }).yvrzbryuycempgkdhpvj();
        }
        this.yvrzbryuycempgkdhpvj.uncaughtException(thread, th);
    }
}
