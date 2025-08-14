package com.uxcam.internals;

import android.content.SharedPreferences;
import android.util.Base64;
import com.uxcam.screenaction.utils.Util;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes6.dex */
public final class fl {

    /* renamed from: a, reason: collision with root package name */
    public static SharedPreferences f153a;
    public static bx b;

    public static void a(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher;
        if (f153a == null && Util.getCurrentApplicationContext() != null) {
            f153a = Util.getCurrentApplicationContext().getSharedPreferences("UXCamPreferences", 0);
        }
        SharedPreferences sharedPreferences = f153a;
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        if (b == null) {
            b = new bx();
        }
        bx bxVar = b;
        if (bxVar.f104a) {
            try {
                Cipher cipher2 = Cipher.getInstance("AES/GCM/NoPadding");
                bxVar.b = cipher2;
                cipher2.init(1, bxVar.a());
                str = Base64.encodeToString(bxVar.b.doFinal(str.getBytes("UTF-8")), 0);
            } catch (Exception unused) {
            }
        }
        SharedPreferences.Editor editorPutString = editorEdit.putString("UXCam_AppKeys", str);
        if (b == null) {
            b = new bx();
        }
        bx bxVar2 = b;
        editorPutString.putString("UXCam_AppKeys_iv", (!bxVar2.f104a || (cipher = bxVar2.b) == null) ? "" : Base64.encodeToString(cipher.getIV(), 2)).apply();
    }
}
