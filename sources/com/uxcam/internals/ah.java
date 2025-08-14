package com.uxcam.internals;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.clevertap.android.sdk.Constants;
import com.uxcam.screenaction.utils.Util;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes6.dex */
public final class ah {
    public static void a(String str) {
        try {
            ArrayList arrayListA = a();
            if (!arrayListA.contains(str)) {
                arrayListA.add(str);
            }
            fl.a(TextUtils.join(Constants.SEPARATOR_COMMA, arrayListA));
        } catch (Exception e) {
            fj fjVarB = new fj().b("AppKeyStorage::saveAppKey()");
            fjVarB.a("reason", e.getMessage());
            fjVarB.a(2);
        }
    }

    public static ArrayList a() throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (fl.f153a == null && Util.getCurrentApplicationContext() != null) {
            fl.f153a = Util.getCurrentApplicationContext().getSharedPreferences("UXCamPreferences", 0);
        }
        SharedPreferences sharedPreferences = fl.f153a;
        String strA = "";
        if (sharedPreferences != null && sharedPreferences.contains("UXCam_AppKeys")) {
            if (fl.b == null) {
                fl.b = new bx();
            }
            bx bxVar = fl.b;
            String string = sharedPreferences.getString("UXCam_AppKeys", "");
            if (fl.f153a == null && Util.getCurrentApplicationContext() != null) {
                fl.f153a = Util.getCurrentApplicationContext().getSharedPreferences("UXCamPreferences", 0);
            }
            String string2 = fl.f153a.getString("UXCam_AppKeys_iv", "");
            strA = bxVar.a(string, (string2 == null || string2.isEmpty()) ? new byte[0] : Base64.decode(string2, 2));
        }
        if (strA != null && !strA.isEmpty()) {
            return new ArrayList(Arrays.asList(strA.split(Constants.SEPARATOR_COMMA)));
        }
        return new ArrayList();
    }
}
