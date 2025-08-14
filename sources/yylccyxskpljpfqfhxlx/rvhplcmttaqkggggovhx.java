package yylccyxskpljpfqfhxlx;

import android.os.Build;
import org.json.JSONArray;

/* loaded from: classes7.dex */
public class rvhplcmttaqkggggovhx {
    private static volatile rvhplcmttaqkggggovhx dbuymyhwehsdoxafsfpy;
    private JSONArray yvrzbryuycempgkdhpvj = new JSONArray();

    private rvhplcmttaqkggggovhx() {
    }

    public static rvhplcmttaqkggggovhx dbuymyhwehsdoxafsfpy() {
        if (dbuymyhwehsdoxafsfpy == null) {
            synchronized (rvhplcmttaqkggggovhx.class) {
                if (dbuymyhwehsdoxafsfpy == null) {
                    dbuymyhwehsdoxafsfpy = new rvhplcmttaqkggggovhx();
                }
            }
        }
        return dbuymyhwehsdoxafsfpy;
    }

    public void yvrzbryuycempgkdhpvj(Throwable th, int i) {
        synchronized (this) {
            if (this.yvrzbryuycempgkdhpvj.length() <= i) {
                this.yvrzbryuycempgkdhpvj.put(yvrzbryuycempgkdhpvj(th).yvrzbryuycempgkdhpvj());
            }
        }
    }

    public void yvrzbryuycempgkdhpvj(fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar, int i) {
        synchronized (this) {
            if (this.yvrzbryuycempgkdhpvj.length() <= i) {
                fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(System.currentTimeMillis());
                this.yvrzbryuycempgkdhpvj.put(fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj());
            }
        }
    }

    public JSONArray yvrzbryuycempgkdhpvj() {
        JSONArray jSONArray;
        synchronized (this) {
            jSONArray = this.yvrzbryuycempgkdhpvj;
        }
        return jSONArray;
    }

    private static uqgffrurkpkmcepvnfkx.fdwipeifdmvqsbqrrpyp yvrzbryuycempgkdhpvj(Throwable th) {
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
        return fdwipeifdmvqsbqrrpypVar;
    }

    public void yvrzbryuycempgkdhpvj(JSONArray jSONArray) {
        synchronized (this) {
            this.yvrzbryuycempgkdhpvj = jSONArray;
        }
    }
}
