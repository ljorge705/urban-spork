package com.paygilant.PG_FraudDetection_SDK;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Base64;
import androidx.autofill.HintConstants;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class zwlltpaufqribmleigux {
    public static boolean ctfsaqlysacfjtqixtmr() {
        if (oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl() != null) {
            return oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().vjgujdxqyzpnlimdrvvt().booleanValue();
        }
        return false;
    }

    public static void dbuymyhwehsdoxafsfpy(Throwable th) {
        yvrzbryuycempgkdhpvj(oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().uusbetktgiikylwfbevs(), th);
    }

    public static boolean uusbetktgiikylwfbevs() {
        return oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().dbuymyhwehsdoxafsfpy() == cyxdnekglwjxeogqvedd.fdwipeifdmvqsbqrrpyp.DEVELOPMENT;
    }

    public static void yvrzbryuycempgkdhpvj(Boolean bool, Throwable th) {
        if (bool.booleanValue()) {
            yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj(th, oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().efmnkxwvqeqnaehsyess().intValue());
        }
    }

    public static Boolean dbuymyhwehsdoxafsfpy() {
        return oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().ldeiitcdqlqzkidvrbjy();
    }

    public static void yvrzbryuycempgkdhpvj(Boolean bool, yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar, String str) {
        if (!bool.booleanValue() || fdwipeifdmvqsbqrrpypVar == null) {
            return;
        }
        yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj(fdwipeifdmvqsbqrrpypVar, oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().efmnkxwvqeqnaehsyess().intValue());
        fdwipeifdmvqsbqrrpypVar.dbuymyhwehsdoxafsfpy().add(str);
    }

    protected static void dbuymyhwehsdoxafsfpy(Context context, String str, String str2) {
        if (context != null) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("Paygilant", 0).edit();
            editorEdit.putString(str, str2);
            editorEdit.apply();
        }
    }

    public static void yvrzbryuycempgkdhpvj(Throwable th) {
        yvrzbryuycempgkdhpvj(oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().dbuymyhwehsdoxafsfpy(), th);
    }

    protected static void dbuymyhwehsdoxafsfpy(Context context, String str, Boolean bool) {
        if (context != null) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("Paygilant", 0).edit();
            editorEdit.putBoolean(str, bool.booleanValue());
            editorEdit.apply();
        }
    }

    public static Boolean yvrzbryuycempgkdhpvj(Context context, String str) {
        if (context != null) {
            return Boolean.valueOf(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
        }
        return Boolean.FALSE;
    }

    public static String yvrzbryuycempgkdhpvj(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (str != null && !str.equals("") && !str.startsWith("0x")) {
                messageDigest.update(str.replace("\"", "").getBytes());
                String strEncodeToString = Base64.encodeToString(messageDigest.digest(), 2);
                messageDigest.reset();
                return strEncodeToString;
            }
            return "";
        } catch (NoSuchAlgorithmException e) {
            dbuymyhwehsdoxafsfpy(e);
            return "";
        }
    }

    public static void yvrzbryuycempgkdhpvj(Boolean bool, yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar) {
        if (!bool.booleanValue() || fdwipeifdmvqsbqrrpypVar == null) {
            return;
        }
        fdwipeifdmvqsbqrrpypVar.yvrzbryuycempgkdhpvj(yylccyxskpljpfqfhxlx.oacciftezlubzxpkwvyc.End);
        yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj(fdwipeifdmvqsbqrrpypVar, oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().efmnkxwvqeqnaehsyess().intValue());
    }

    public static Boolean yvrzbryuycempgkdhpvj(Context context, int i, String str) {
        File file = new File(context.getFilesDir() + File.separator + str);
        return Boolean.valueOf(file.exists() && file.list().length >= i);
    }

    public static Boolean yvrzbryuycempgkdhpvj(Context context, String str, Boolean bool) {
        return context != null ? Boolean.valueOf(context.getSharedPreferences("Paygilant", 0).getBoolean(str, bool.booleanValue())) : bool;
    }

    public static String yvrzbryuycempgkdhpvj(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29 || context == null || !yvrzbryuycempgkdhpvj(context, "android.permission.READ_PHONE_STATE").booleanValue()) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (i < 26) {
            return telephonyManager.getDeviceId();
        }
        try {
            return telephonyManager.getImei();
        } catch (SecurityException e) {
            dbuymyhwehsdoxafsfpy(e);
            return null;
        }
    }

    public static String yvrzbryuycempgkdhpvj(Context context, String str, String str2) {
        return context != null ? context.getSharedPreferences("Paygilant", 0).getString(str, str2) : str2;
    }

    public static Boolean yvrzbryuycempgkdhpvj() {
        return oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().vikftlgmuszlvyjnlikz();
    }

    public static Boolean yvrzbryuycempgkdhpvj(Context context, omamhfdoazbdavfkujac.oacciftezlubzxpkwvyc oacciftezlubzxpkwvycVar) {
        if (context == null) {
            return null;
        }
        File file = new File(context.getFilesDir() + File.separator + oacciftezlubzxpkwvycVar.toString());
        if (file.isDirectory()) {
            return Boolean.valueOf(file.list().length <= 0);
        }
        return Boolean.TRUE;
    }

    public static yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp yvrzbryuycempgkdhpvj(Boolean bool, String str, List list) {
        if (!bool.booleanValue()) {
            return null;
        }
        yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVar = new yylccyxskpljpfqfhxlx.fdwipeifdmvqsbqrrpyp(Thread.currentThread().getName(), str, list, yylccyxskpljpfqfhxlx.oacciftezlubzxpkwvyc.Start);
        yylccyxskpljpfqfhxlx.rvhplcmttaqkggggovhx.dbuymyhwehsdoxafsfpy().yvrzbryuycempgkdhpvj(fdwipeifdmvqsbqrrpypVar, oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().efmnkxwvqeqnaehsyess().ldeiitcdqlqzkidvrbjy().efmnkxwvqeqnaehsyess().intValue());
        return fdwipeifdmvqsbqrrpypVar;
    }
}
