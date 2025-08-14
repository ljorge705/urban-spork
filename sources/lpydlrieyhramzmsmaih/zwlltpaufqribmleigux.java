package lpydlrieyhramzmsmaih;

import android.content.Context;
import android.os.Build;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import hbmifujbkwcjpgteyixs.jfrjraouuicuqboknnmi;
import hbmifujbkwcjpgteyixs.vnufwshzeizvjmboyyju;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
abstract class zwlltpaufqribmleigux {
    private static List yvrzbryuycempgkdhpvj(Context context) {
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_PHONE_STATE").booleanValue()) {
            ArrayList arrayList = new ArrayList();
            try {
                TelecomManager telecomManager = (TelecomManager) context.getSystemService("telecom");
                if (telecomManager == null) {
                    return null;
                }
                for (PhoneAccountHandle phoneAccountHandle : telecomManager.getCallCapablePhoneAccounts()) {
                    arrayList.add(new vnufwshzeizvjmboyyju(String.valueOf(phoneAccountHandle.hashCode()), phoneAccountHandle.getId()));
                }
                return arrayList;
            } catch (Exception e) {
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            }
        }
        return null;
    }

    public static jfrjraouuicuqboknnmi yvrzbryuycempgkdhpvj(Context context, jfrjraouuicuqboknnmi jfrjraouuicuqboknnmiVar, TelephonyManager telephonyManager) {
        String strValueOf;
        String strValueOf2;
        String simSerialNumber;
        String subscriberId;
        if (jfrjraouuicuqboknnmiVar == null) {
            jfrjraouuicuqboknnmiVar = new jfrjraouuicuqboknnmi();
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_PHONE_STATE").booleanValue()) {
            String serial = null;
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    strValueOf = String.valueOf(telephonyManager.isConcurrentVoiceAndDataSupported());
                } catch (SecurityException e) {
                    com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
                    strValueOf = null;
                }
            } else {
                strValueOf = "not available";
            }
            String strValueOf3 = String.valueOf(telephonyManager.isVoiceCapable());
            String strValueOf4 = String.valueOf(telephonyManager.getPhoneCount());
            String strValueOf5 = String.valueOf(telephonyManager.isWorldPhone());
            String strValueOf6 = String.valueOf(telephonyManager.isTtyModeSupported());
            try {
                strValueOf2 = String.valueOf(telephonyManager.getDataNetworkType());
            } catch (SecurityException e2) {
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e2);
                strValueOf2 = null;
            }
            if (Build.VERSION.SDK_INT < 29) {
                try {
                    simSerialNumber = telephonyManager.getSimSerialNumber();
                } catch (SecurityException e3) {
                    com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e3);
                    simSerialNumber = null;
                }
                try {
                    subscriberId = telephonyManager.getSubscriberId();
                } catch (SecurityException e4) {
                    com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e4);
                    subscriberId = null;
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    try {
                        serial = Build.getSerial();
                    } catch (SecurityException e5) {
                        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e5);
                    }
                }
            } else {
                simSerialNumber = null;
                subscriberId = null;
            }
            String groupIdLevel1 = telephonyManager.getGroupIdLevel1();
            jfrjraouuicuqboknnmiVar.uwanvjsqbzasnsnlxnqi(simSerialNumber);
            jfrjraouuicuqboknnmiVar.ldeiitcdqlqzkidvrbjy(strValueOf2);
            jfrjraouuicuqboknnmiVar.yvrzbryuycempgkdhpvj(groupIdLevel1);
            jfrjraouuicuqboknnmiVar.uusbetktgiikylwfbevs(subscriberId);
            jfrjraouuicuqboknnmiVar.gtykjqtliutrjfvjtiex(serial);
            jfrjraouuicuqboknnmiVar.cwzojhoqdsccekmlpbcq(strValueOf4);
            jfrjraouuicuqboknnmiVar.ctfsaqlysacfjtqixtmr(strValueOf);
            jfrjraouuicuqboknnmiVar.mxodkqpwhcryvsgsvabl(strValueOf6);
            jfrjraouuicuqboknnmiVar.efmnkxwvqeqnaehsyess(strValueOf3);
            jfrjraouuicuqboknnmiVar.dyrapphjndqarxdhyvgv(strValueOf5);
            if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.ctfsaqlysacfjtqixtmr()) {
                jfrjraouuicuqboknnmiVar.dbuymyhwehsdoxafsfpy(yvrzbryuycempgkdhpvj(context));
            }
        }
        return jfrjraouuicuqboknnmiVar;
    }
}
