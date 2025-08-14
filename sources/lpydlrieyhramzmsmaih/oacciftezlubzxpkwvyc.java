package lpydlrieyhramzmsmaih;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.InstallSourceInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.autofill.HintConstants;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.google.firebase.analytics.FirebaseAnalytics;
import dialoobnhfjfdhmrftlq.asvpglyfnwnhyiooalpb;
import hbmifujbkwcjpgteyixs.cctxrwizduxmjefyvyrx;
import hbmifujbkwcjpgteyixs.cdabktqulpsnjrlnlnii;
import hbmifujbkwcjpgteyixs.jfrjraouuicuqboknnmi;
import hbmifujbkwcjpgteyixs.lbtmiwirddgqkunoyrov;
import hbmifujbkwcjpgteyixs.llyhweatjmvlzlflpaeb;
import hbmifujbkwcjpgteyixs.lmqztflyazmtuunswyyl;
import hbmifujbkwcjpgteyixs.nlkeiuewguxtleppgqdg;
import hbmifujbkwcjpgteyixs.ppvnkbmzfphuuihfhotp;
import hbmifujbkwcjpgteyixs.pzqcxkrstpkgvuxxlors;
import hbmifujbkwcjpgteyixs.wgeshaitmpzrwttiokth;
import hbmifujbkwcjpgteyixs.yktdemhqxtjzzjfxodtk;
import io.sentry.cache.EnvelopeCache;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class oacciftezlubzxpkwvyc implements Runnable {
    private final Context dbuymyhwehsdoxafsfpy;
    private final Handler yvrzbryuycempgkdhpvj;

    public oacciftezlubzxpkwvyc(Handler handler, Context context) {
        this.dbuymyhwehsdoxafsfpy = context.getApplicationContext();
        this.yvrzbryuycempgkdhpvj = handler;
    }

    public static List cwzojhoqdsccekmlpbcq(Context context) {
        File[] fileArrListFiles;
        ArrayList arrayList = new ArrayList();
        if (!com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_EXTERNAL_STORAGE").booleanValue() || !Environment.getExternalStorageDirectory().exists() || (fileArrListFiles = new File(Environment.getExternalStorageDirectory().getAbsoluteFile().getPath()).listFiles()) == null) {
            return arrayList;
        }
        for (File file : fileArrListFiles) {
            arrayList.add(new yktdemhqxtjzzjfxodtk(Long.valueOf(file.lastModified()), file.getName()));
        }
        return arrayList;
    }

    public static String danumarvmgpbarrzqyrz(Context context) {
        WifiManager wifiManagerDbuymyhwehsdoxafsfpy;
        WifiInfo connectionInfo;
        if (!vodttdxrgsufbynbtbbg(context).booleanValue() || !com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_FINE_LOCATION").booleanValue() || (wifiManagerDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy(context)) == null || (connectionInfo = wifiManagerDbuymyhwehsdoxafsfpy.getConnectionInfo()) == null || connectionInfo.getSSID().equals("") || connectionInfo.getSSID().startsWith("0x") || connectionInfo.getSSID() == null) {
            return null;
        }
        return com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(connectionInfo.getSSID());
    }

    private static WifiManager dbuymyhwehsdoxafsfpy(Context context) {
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_WIFI_STATE").booleanValue()) {
            return (WifiManager) context.getApplicationContext().getSystemService("wifi");
        }
        return null;
    }

    public static Boolean dtzqkpwicmyznzcqlscc(Context context) {
        try {
            String installingPackageName = context.getPackageManager().getInstallSourceInfo(context.getPackageName()).getInstallingPackageName();
            if (installingPackageName == null) {
                return null;
            }
            return Boolean.valueOf("com.android.vending".equals(installingPackageName));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private static Integer dyrapphjndqarxdhyvgv(Context context) {
        Integer.valueOf(0);
        try {
            Integer numValueOf = Integer.valueOf(Settings.Global.getInt(context.getContentResolver(), "boot_count"));
            return Integer.valueOf(numValueOf != null ? numValueOf.intValue() : 0);
        } catch (Settings.SettingNotFoundException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return -1;
        }
    }

    protected static List efmnkxwvqeqnaehsyess(Context context) {
        BluetoothAdapter defaultAdapter;
        if (((com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.BLUETOOTH").booleanValue() && Build.VERSION.SDK_INT < 31) || (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.BLUETOOTH_CONNECT").booleanValue() && Build.VERSION.SDK_INT >= 31)) && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
            try {
                Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
                if (bondedDevices.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (BluetoothDevice bluetoothDevice : bondedDevices) {
                        String name = bluetoothDevice.getName();
                        if (name != null) {
                            arrayList.add(new cctxrwizduxmjefyvyrx(com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(name), com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(bluetoothDevice.getAddress())));
                        }
                    }
                    return arrayList;
                }
            } catch (Exception e) {
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            }
        }
        return null;
    }

    private static String fniextxrjqlolhwhqggv(Context context) {
        WifiManager wifiManagerDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy(context);
        if (wifiManagerDbuymyhwehsdoxafsfpy != null) {
            return String.valueOf(wifiManagerDbuymyhwehsdoxafsfpy.getConnectionInfo().getNetworkId());
        }
        return null;
    }

    private static JSONObject gsokbpxrpyefzacjsrbi(Context context) throws JSONException {
        WifiManager wifiManagerDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy(context);
        if (wifiManagerDbuymyhwehsdoxafsfpy == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            Boolean boolValueOf = Boolean.valueOf(wifiManagerDbuymyhwehsdoxafsfpy.is5GHzBandSupported());
            Boolean boolValueOf2 = Boolean.valueOf(wifiManagerDbuymyhwehsdoxafsfpy.isDeviceToApRttSupported());
            Boolean boolValueOf3 = Boolean.valueOf(wifiManagerDbuymyhwehsdoxafsfpy.isEnhancedPowerReportingSupported());
            Boolean boolValueOf4 = Boolean.valueOf(wifiManagerDbuymyhwehsdoxafsfpy.isP2pSupported());
            Boolean boolValueOf5 = Boolean.valueOf(wifiManagerDbuymyhwehsdoxafsfpy.isPreferredNetworkOffloadSupported());
            Boolean boolValueOf6 = Boolean.valueOf(wifiManagerDbuymyhwehsdoxafsfpy.isTdlsSupported());
            Boolean boolValueOf7 = Boolean.valueOf(wifiManagerDbuymyhwehsdoxafsfpy.isScanAlwaysAvailable());
            jSONObject.put("is5GHzBandSupported", boolValueOf);
            jSONObject.put("isDeviceToApRttSupported", boolValueOf2);
            jSONObject.put("isEnhancedPowerReportingSupported", boolValueOf3);
            jSONObject.put("isP2pSupported", boolValueOf4);
            jSONObject.put("isPreferredNetworkOffloadSupported", boolValueOf5);
            jSONObject.put("isTdlsSupported", boolValueOf6);
            jSONObject.put("isScanAlwaysAvailable", boolValueOf7);
            return jSONObject;
        } catch (JSONException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return null;
        }
    }

    private static List hbycjaoesutelfxwaoca(Context context) {
        RingtoneManager ringtoneManager = new RingtoneManager(context);
        ringtoneManager.setType(1);
        Cursor cursor = ringtoneManager.getCursor();
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(cursor.getString(1));
        }
        return arrayList;
    }

    public static JSONObject hekbabbvryaiwbpvazlo(Context context) throws JSONException {
        JSONObject jSONObjectYvrzbryuycempgkdhpvj = yvrzbryuycempgkdhpvj("fused", context);
        if (jSONObjectYvrzbryuycempgkdhpvj != null) {
            return jSONObjectYvrzbryuycempgkdhpvj;
        }
        JSONObject jSONObjectYvrzbryuycempgkdhpvj2 = yvrzbryuycempgkdhpvj("gps", context);
        if (jSONObjectYvrzbryuycempgkdhpvj2 != null) {
            return jSONObjectYvrzbryuycempgkdhpvj2;
        }
        JSONObject jSONObjectYvrzbryuycempgkdhpvj3 = yvrzbryuycempgkdhpvj("network", context);
        return jSONObjectYvrzbryuycempgkdhpvj3 != null ? jSONObjectYvrzbryuycempgkdhpvj3 : yvrzbryuycempgkdhpvj("passive", context);
    }

    public static Boolean kqybgnwtttwkvdwxaqhw(Context context) {
        return Build.VERSION.SDK_INT >= 30 ? dtzqkpwicmyznzcqlscc(context) : vpjqwyqiclwncdgbkkkg(context);
    }

    public static String mxodkqpwhcryvsgsvabl(Context context) {
        Intent intentRegisterReceiver;
        String string = Settings.Global.getString(context.getContentResolver(), "adb_enabled");
        if (context.getPackageManager().hasSystemFeature("android.hardware.usb.accessory") && (intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.hardware.usb.action.USB_STATE"))) != null && intentRegisterReceiver.getExtras() != null) {
            boolean z = intentRegisterReceiver.getExtras().getBoolean("connected");
            if (string == null) {
                return "-99";
            }
            if (string.equals("1") && z) {
                return "1";
            }
        }
        return "0";
    }

    private static List nicafiaansnuaopzwdwm(Context context) {
        List<WifiConfiguration> configuredNetworks;
        if (Build.VERSION.SDK_INT < 29) {
            ArrayList arrayList = new ArrayList();
            WifiManager wifiManagerDbuymyhwehsdoxafsfpy = dbuymyhwehsdoxafsfpy(context);
            if (wifiManagerDbuymyhwehsdoxafsfpy != null && (configuredNetworks = wifiManagerDbuymyhwehsdoxafsfpy.getConfiguredNetworks()) != null) {
                Iterator<WifiConfiguration> it = configuredNetworks.iterator();
                while (it.hasNext()) {
                    arrayList.add(com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(it.next().SSID));
                }
                return arrayList;
            }
        }
        return null;
    }

    public static ArrayList ooztjhejjvpgrdhjnyju(Context context) {
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_CALL_LOG").booleanValue()) {
            try {
                Cursor cursorQuery = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
                if (cursorQuery != null) {
                    int columnIndex = cursorQuery.getColumnIndex(CTVariableUtils.NUMBER);
                    int columnIndex2 = cursorQuery.getColumnIndex("type");
                    int columnIndex3 = cursorQuery.getColumnIndex("date");
                    int columnIndex4 = cursorQuery.getColumnIndex("duration");
                    int columnIndex5 = cursorQuery.getColumnIndex("name");
                    ArrayList arrayList = new ArrayList();
                    if (cursorQuery.getCount() > 0) {
                        while (cursorQuery.moveToNext() && arrayList.size() < 500) {
                            String string = cursorQuery.getString(columnIndex);
                            String string2 = cursorQuery.getString(columnIndex2);
                            Long lValueOf = Long.valueOf(cursorQuery.getString(columnIndex3));
                            String string3 = cursorQuery.getString(columnIndex5);
                            String string4 = cursorQuery.getString(columnIndex4);
                            String strReplaceAll = string.replaceAll("[^0-9]", "");
                            if (strReplaceAll.length() > 5) {
                                strReplaceAll = strReplaceAll.substring(strReplaceAll.length() - 5);
                            }
                            arrayList.add(new cdabktqulpsnjrlnlnii(strReplaceAll, Boolean.valueOf(string3 != null), String.valueOf(lValueOf), string4, string2));
                        }
                        cursorQuery.close();
                        return arrayList;
                    }
                }
            } catch (Exception e) {
                com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            }
        }
        return null;
    }

    public static boolean ppdfopkmksgbbjoukavl(Context context) {
        ConnectivityManager connectivityManager;
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_NETWORK_STATE").booleanValue() && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
            for (Network network : connectivityManager.getAllNetworks()) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                if (networkCapabilities != null && networkCapabilities.hasTransport(4) && networkCapabilities.hasCapability(13)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String utxtilfqoxdwcqlqpjoj(Context context) {
        InstallSourceInfo installSourceInfo;
        try {
            String packageName = context.getPackageName();
            if (packageName != null) {
                return packageName;
            }
            PackageManager packageManager = context.getPackageManager();
            return (Build.VERSION.SDK_INT < 30 || (installSourceInfo = packageManager.getInstallSourceInfo(packageName)) == null) ? packageManager.getInstallerPackageName(packageName) : installSourceInfo.getInstallingPackageName();
        } catch (PackageManager.NameNotFoundException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return "";
        }
    }

    public static JSONObject uusbetktgiikylwfbevs(Context context) throws JSONException {
        String strDanumarvmgpbarrzqyrz = danumarvmgpbarrzqyrz(context);
        JSONObject jSONObjectHekbabbvryaiwbpvazlo = hekbabbvryaiwbpvazlo(context);
        llyhweatjmvlzlflpaeb llyhweatjmvlzlflpaebVar = jSONObjectHekbabbvryaiwbpvazlo != null ? new llyhweatjmvlzlflpaeb(jSONObjectHekbabbvryaiwbpvazlo) : null;
        boolean zYvrzbryuycempgkdhpvj = wfdoaqfvfyoijpgclxfu.yvrzbryuycempgkdhpvj(context);
        boolean zPpdfopkmksgbbjoukavl = ppdfopkmksgbbjoukavl(context);
        String strMxodkqpwhcryvsgsvabl = mxodkqpwhcryvsgsvabl(context);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        yvrzbryuycempgkdhpvj(context, arrayList, arrayList2);
        return new lmqztflyazmtuunswyyl(strDanumarvmgpbarrzqyrz, llyhweatjmvlzlflpaebVar, Boolean.valueOf(zYvrzbryuycempgkdhpvj), strMxodkqpwhcryvsgsvabl, Boolean.valueOf(zPpdfopkmksgbbjoukavl), com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(), com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(), arrayList, arrayList2, new ArrayList(rvhplcmttaqkggggovhx.uusbetktgiikylwfbevs().dbuymyhwehsdoxafsfpy())).yvrzbryuycempgkdhpvj();
    }

    private static int uwanvjsqbzasnsnlxnqi(Context context) {
        BluetoothAdapter defaultAdapter;
        try {
            if (((!com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.BLUETOOTH").booleanValue() || Build.VERSION.SDK_INT >= 31) && (!com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.BLUETOOTH_SCAN").booleanValue() || Build.VERSION.SDK_INT < 31)) || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
                return 0;
            }
            return defaultAdapter.getScanMode();
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return 0;
        }
    }

    public static fdwipeifdmvqsbqrrpyp vikftlgmuszlvyjnlikz(Context context) {
        Cursor cursorQuery;
        HashMap map = new HashMap();
        String str = "SIM_" + Settings.Secure.getString(context.getContentResolver(), "android_id");
        try {
            System.currentTimeMillis();
            boolean z = false;
            if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_CONTACTS").booleanValue() && (cursorQuery = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"display_name", "data1", "account_name"}, String.format("%s > %s", "has_phone_number", "0"), null, String.format("%s , %s", "account_name", "data1"))) != null && cursorQuery.getCount() > 0) {
                while (cursorQuery.moveToNext()) {
                    try {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("data1"));
                        String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("account_name"));
                        if (string != null) {
                            if (string2 != null) {
                                if (string2.contains("@")) {
                                    yvrzbryuycempgkdhpvj(map, com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(string2), string);
                                } else if (string2.toLowerCase().contains(HintConstants.AUTOFILL_HINT_PHONE) || string2.toLowerCase().contains("sim")) {
                                }
                            } else if (!z) {
                                z = true;
                            }
                            yvrzbryuycempgkdhpvj(map, str, string);
                        }
                    } catch (IllegalArgumentException e) {
                        com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
                        return null;
                    }
                }
                cursorQuery.close();
            }
            System.currentTimeMillis();
            return new fdwipeifdmvqsbqrrpyp(map, Boolean.valueOf(z));
        } catch (SecurityException e2) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e2);
            return null;
        }
    }

    private static Boolean vodttdxrgsufbynbtbbg(Context context) {
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        try {
            if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_WIFI_STATE").booleanValue() && com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_NETWORK_STATE").booleanValue()) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                return (connectivityManager == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) ? Boolean.FALSE : Boolean.valueOf(networkCapabilities.hasTransport(1));
            }
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
        return Boolean.FALSE;
    }

    public static Boolean vpjqwyqiclwncdgbkkkg(Context context) {
        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        if (installerPackageName == null) {
            return null;
        }
        return Boolean.valueOf("com.android.vending".equals(installerPackageName));
    }

    private static void yvrzbryuycempgkdhpvj(HashMap map, String str, String str2) {
        String strReplaceAll = str2.replaceAll("[^0-9]", "");
        if (strReplaceAll.length() > 5) {
            if (!map.containsKey(str)) {
                map.put(str, new HashSet());
            }
            ((HashSet) map.get(str)).add(strReplaceAll.substring(strReplaceAll.length() - 5));
        }
    }

    public JSONObject ctfsaqlysacfjtqixtmr(Context context) throws PackageManager.NameNotFoundException, Settings.SettingNotFoundException {
        int i = 0;
        int i2 = Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", 0);
        PackageManager packageManager = context.getPackageManager();
        String strValueOf = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 4096);
            String[] strArr = packageInfo != null ? packageInfo.requestedPermissions : null;
            if (strArr != null && strArr.length > 0 && Arrays.asList(strArr).contains("android.permission.REQUEST_INSTALL_PACKAGES") && Build.VERSION.SDK_INT >= 26) {
                strValueOf = String.valueOf(packageManager.canRequestPackageInstalls());
            }
        } catch (PackageManager.NameNotFoundException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
        System.currentTimeMillis();
        boolean zYvrzbryuycempgkdhpvj = wfdoaqfvfyoijpgclxfu.yvrzbryuycempgkdhpvj(context);
        System.currentTimeMillis();
        try {
            i = Settings.Secure.getInt(context.getContentResolver(), "location_mode");
        } catch (Settings.SettingNotFoundException e2) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e2);
        }
        Boolean boolKqybgnwtttwkvdwxaqhw = kqybgnwtttwkvdwxaqhw(context);
        String localizedPattern = ((SimpleDateFormat) DateFormat.getDateFormat(context)).toLocalizedPattern();
        nlkeiuewguxtleppgqdg nlkeiuewguxtleppgqdgVar = new nlkeiuewguxtleppgqdg();
        nlkeiuewguxtleppgqdgVar.yvrzbryuycempgkdhpvj(Integer.valueOf(i2));
        nlkeiuewguxtleppgqdgVar.ooztjhejjvpgrdhjnyju(strValueOf);
        nlkeiuewguxtleppgqdgVar.uusbetktgiikylwfbevs(Boolean.valueOf(zYvrzbryuycempgkdhpvj));
        nlkeiuewguxtleppgqdgVar.yvrzbryuycempgkdhpvj(Boolean.valueOf(wfdoaqfvfyoijpgclxfu.yvrzbryuycempgkdhpvj()));
        nlkeiuewguxtleppgqdgVar.dbuymyhwehsdoxafsfpy(boolKqybgnwtttwkvdwxaqhw);
        nlkeiuewguxtleppgqdgVar.dbuymyhwehsdoxafsfpy(Settings.Secure.getString(context.getContentResolver(), "accessibility_enabled"));
        nlkeiuewguxtleppgqdgVar.uusbetktgiikylwfbevs(Settings.Secure.getString(context.getContentResolver(), "speak_password"));
        nlkeiuewguxtleppgqdgVar.dbuymyhwehsdoxafsfpy(Integer.valueOf(i));
        nlkeiuewguxtleppgqdgVar.ctfsaqlysacfjtqixtmr(Settings.Global.getString(context.getContentResolver(), "auto_time"));
        nlkeiuewguxtleppgqdgVar.vjgujdxqyzpnlimdrvvt(Settings.Global.getString(context.getContentResolver(), "auto_time_zone"));
        try {
            nlkeiuewguxtleppgqdgVar.uusbetktgiikylwfbevs(Integer.valueOf(Settings.Global.getInt(context.getContentResolver(), "stay_on_while_plugged_in")));
            nlkeiuewguxtleppgqdgVar.ctfsaqlysacfjtqixtmr(Integer.valueOf(Settings.Global.getInt(context.getContentResolver(), "usb_mass_storage_enabled")));
            nlkeiuewguxtleppgqdgVar.vjgujdxqyzpnlimdrvvt(Integer.valueOf(Settings.Global.getInt(context.getContentResolver(), "wifi_sleep_policy")));
        } catch (Settings.SettingNotFoundException e3) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e3);
        }
        nlkeiuewguxtleppgqdgVar.nicafiaansnuaopzwdwm(Settings.Global.getString(context.getContentResolver(), "wifi_networks_available_notification_on"));
        nlkeiuewguxtleppgqdgVar.yvrzbryuycempgkdhpvj(Settings.System.getString(context.getContentResolver(), "accelerometer_rotation"));
        nlkeiuewguxtleppgqdgVar.mxodkqpwhcryvsgsvabl(localizedPattern);
        nlkeiuewguxtleppgqdgVar.efmnkxwvqeqnaehsyess(Settings.System.getString(context.getContentResolver(), "dtmf_tone_type"));
        nlkeiuewguxtleppgqdgVar.dyrapphjndqarxdhyvgv(Settings.System.getString(context.getContentResolver(), "dtmf_tone"));
        nlkeiuewguxtleppgqdgVar.danumarvmgpbarrzqyrz(Settings.System.getString(context.getContentResolver(), "mode_ringer_streams_affected"));
        nlkeiuewguxtleppgqdgVar.ldeiitcdqlqzkidvrbjy(Settings.System.getString(context.getContentResolver(), "notification_sound"));
        nlkeiuewguxtleppgqdgVar.vikftlgmuszlvyjnlikz(Settings.System.getString(context.getContentResolver(), "mute_streams_affected"));
        nlkeiuewguxtleppgqdgVar.cwzojhoqdsccekmlpbcq(Settings.System.getString(context.getContentResolver(), "screen_brightness"));
        nlkeiuewguxtleppgqdgVar.gtykjqtliutrjfvjtiex(Settings.System.getString(context.getContentResolver(), "screen_brightness_mode"));
        nlkeiuewguxtleppgqdgVar.utxtilfqoxdwcqlqpjoj(Settings.System.getString(context.getContentResolver(), "screen_off_timeout"));
        nlkeiuewguxtleppgqdgVar.hekbabbvryaiwbpvazlo(Settings.System.getString(context.getContentResolver(), "sound_effects_enabled"));
        nlkeiuewguxtleppgqdgVar.uwanvjsqbzasnsnlxnqi(Settings.System.getString(context.getContentResolver(), "show_password"));
        nlkeiuewguxtleppgqdgVar.pyxmijmxbomdjegczksl(Settings.System.getString(context.getContentResolver(), "time_12_24"));
        nlkeiuewguxtleppgqdgVar.lleeevhiydcwptuwzadc(Settings.System.getString(context.getContentResolver(), "user_rotation"));
        nlkeiuewguxtleppgqdgVar.gsokbpxrpyefzacjsrbi(Settings.System.getString(context.getContentResolver(), "vibrate_when_ringing"));
        nlkeiuewguxtleppgqdgVar.hbycjaoesutelfxwaoca(utxtilfqoxdwcqlqpjoj(context));
        nlkeiuewguxtleppgqdgVar.yvrzbryuycempgkdhpvj(Long.valueOf(System.currentTimeMillis()));
        return nlkeiuewguxtleppgqdgVar.yvrzbryuycempgkdhpvj();
    }

    public Integer gtykjqtliutrjfvjtiex(Context context) {
        if (!com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_EXTERNAL_STORAGE").booleanValue() && !com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_MEDIA_IMAGES").booleanValue()) {
            return null;
        }
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursorQuery == null) {
            return 0;
        }
        Integer numValueOf = Integer.valueOf(cursorQuery.getCount());
        cursorQuery.close();
        return numValueOf;
    }

    public JSONObject ldeiitcdqlqzkidvrbjy(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        String strYvrzbryuycempgkdhpvj = com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context);
        String str = Build.VERSION.RELEASE;
        String externalStorageState = Environment.getExternalStorageState();
        int i = context.getResources().getConfiguration().screenLayout;
        DisplayMetrics displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
        }
        long j = memoryInfo.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSizeLong = (statFs.getBlockSizeLong() * statFs.getBlockCountLong()) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        Uri validRingtoneUri = RingtoneManager.getValidRingtoneUri(context);
        String string2 = validRingtoneUri != null ? validRingtoneUri.toString() : null;
        ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar = new ppvnkbmzfphuuihfhotp();
        ppvnkbmzfphuuihfhotpVar.uhujfisxsgsrhmusmdur(string);
        ppvnkbmzfphuuihfhotpVar.kqybgnwtttwkvdwxaqhw(strYvrzbryuycempgkdhpvj);
        ppvnkbmzfphuuihfhotpVar.dbuymyhwehsdoxafsfpy(str);
        ppvnkbmzfphuuihfhotpVar.hbycjaoesutelfxwaoca(Build.DEVICE);
        ppvnkbmzfphuuihfhotpVar.efmnkxwvqeqnaehsyess(Build.BOOTLOADER);
        ppvnkbmzfphuuihfhotpVar.pyxmijmxbomdjegczksl(Build.FINGERPRINT);
        ppvnkbmzfphuuihfhotpVar.nicafiaansnuaopzwdwm(Build.HARDWARE);
        ppvnkbmzfphuuihfhotpVar.vikftlgmuszlvyjnlikz(Build.ID);
        List listAsList = Arrays.asList(Build.SUPPORTED_32_BIT_ABIS);
        List listAsList2 = Arrays.asList(Build.SUPPORTED_64_BIT_ABIS);
        JSONArray jSONArray = new JSONArray((Collection) listAsList);
        JSONArray jSONArray2 = new JSONArray((Collection) listAsList2);
        ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj(jSONArray);
        ppvnkbmzfphuuihfhotpVar.dbuymyhwehsdoxafsfpy(jSONArray2);
        ppvnkbmzfphuuihfhotpVar.hekbabbvryaiwbpvazlo(TimeZone.getDefault().getID());
        ppvnkbmzfphuuihfhotpVar.zwobhtbpnlqafneiqjbw(context.getResources().getConfiguration().getLocales().get(0).toString());
        ppvnkbmzfphuuihfhotpVar.vodttdxrgsufbynbtbbg(Locale.getDefault().getLanguage());
        ppvnkbmzfphuuihfhotpVar.hzboqnueafthurvgzlhd(Build.TAGS);
        ppvnkbmzfphuuihfhotpVar.mxodkqpwhcryvsgsvabl(Long.valueOf(Build.TIME));
        ppvnkbmzfphuuihfhotpVar.hohwaucsiylhmibnvokv(Build.TYPE);
        ppvnkbmzfphuuihfhotpVar.flxvypzwnsemgoacejis(Build.USER);
        ppvnkbmzfphuuihfhotpVar.cwzojhoqdsccekmlpbcq(Build.VERSION.CODENAME);
        ppvnkbmzfphuuihfhotpVar.ppdfopkmksgbbjoukavl(Build.VERSION.INCREMENTAL);
        ppvnkbmzfphuuihfhotpVar.uusbetktgiikylwfbevs(Settings.Global.getString(context.getContentResolver(), "animator_duration_scale"));
        ppvnkbmzfphuuihfhotpVar.lleeevhiydcwptuwzadc(Settings.System.getString(context.getContentResolver(), "font_scale"));
        ppvnkbmzfphuuihfhotpVar.gsokbpxrpyefzacjsrbi(Settings.System.getString(context.getContentResolver(), "haptic_feedback_enabled"));
        ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj(Integer.valueOf(Locale.getAvailableLocales().length));
        ppvnkbmzfphuuihfhotpVar.ctfsaqlysacfjtqixtmr(Locale.getDefault().toString());
        ppvnkbmzfphuuihfhotpVar.ctfsaqlysacfjtqixtmr(Integer.valueOf(Float.valueOf(displayMetrics.density).intValue()));
        ppvnkbmzfphuuihfhotpVar.vjgujdxqyzpnlimdrvvt(Integer.valueOf(displayMetrics.densityDpi));
        ppvnkbmzfphuuihfhotpVar.efmnkxwvqeqnaehsyess(Integer.valueOf(Float.valueOf(displayMetrics.scaledDensity).intValue()));
        ppvnkbmzfphuuihfhotpVar.ldeiitcdqlqzkidvrbjy(Integer.valueOf(Float.valueOf(displayMetrics.xdpi).intValue()));
        ppvnkbmzfphuuihfhotpVar.cwzojhoqdsccekmlpbcq(Integer.valueOf(Float.valueOf(displayMetrics.ydpi).intValue()));
        if (Environment.getExternalStorageDirectory().exists()) {
            ppvnkbmzfphuuihfhotpVar.uusbetktgiikylwfbevs(Long.valueOf(new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath()).getTotalBytes()));
        }
        ppvnkbmzfphuuihfhotpVar.dtzqkpwicmyznzcqlscc(Build.MANUFACTURER);
        ppvnkbmzfphuuihfhotpVar.dyrapphjndqarxdhyvgv(Build.BRAND);
        ppvnkbmzfphuuihfhotpVar.fniextxrjqlolhwhqggv(Build.PRODUCT);
        ppvnkbmzfphuuihfhotpVar.ooztjhejjvpgrdhjnyju(Build.DISPLAY);
        ppvnkbmzfphuuihfhotpVar.mxodkqpwhcryvsgsvabl(Build.BOARD);
        ppvnkbmzfphuuihfhotpVar.vpjqwyqiclwncdgbkkkg(Build.MODEL);
        ppvnkbmzfphuuihfhotpVar.ctfsaqlysacfjtqixtmr(Long.valueOf(j));
        ppvnkbmzfphuuihfhotpVar.vjgujdxqyzpnlimdrvvt(Long.valueOf(blockSizeLong));
        ppvnkbmzfphuuihfhotpVar.ooztjhejjvpgrdhjnyju(Integer.valueOf(i));
        ppvnkbmzfphuuihfhotpVar.uwanvjsqbzasnsnlxnqi(externalStorageState);
        ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj(gsokbpxrpyefzacjsrbi(context));
        ppvnkbmzfphuuihfhotpVar.mqwpnefpqywpyryhimya(string2);
        ppvnkbmzfphuuihfhotpVar.uusbetktgiikylwfbevs(hbycjaoesutelfxwaoca(context));
        ppvnkbmzfphuuihfhotpVar.dbuymyhwehsdoxafsfpy(Double.valueOf(yvrzbryuycempgkdhpvj()));
        ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj(yvrzbryuycempgkdhpvj(context));
        ppvnkbmzfphuuihfhotpVar.uusbetktgiikylwfbevs(dyrapphjndqarxdhyvgv(context));
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.ctfsaqlysacfjtqixtmr()) {
            String str2 = Build.VERSION.BASE_OS;
            if (!str2.isEmpty()) {
                ppvnkbmzfphuuihfhotpVar.vjgujdxqyzpnlimdrvvt(str2);
            }
            ppvnkbmzfphuuihfhotpVar.libyhucgefaitkehplch(Build.VERSION.SECURITY_PATCH);
            ppvnkbmzfphuuihfhotpVar.mxodkqpwhcryvsgsvabl(Integer.valueOf(Build.VERSION.PREVIEW_SDK_INT));
            ppvnkbmzfphuuihfhotpVar.ldeiitcdqlqzkidvrbjy("unknown");
            String str3 = Build.CPU_ABI;
            if (!str3.isEmpty()) {
                ppvnkbmzfphuuihfhotpVar.gtykjqtliutrjfvjtiex(str3);
            }
            String str4 = Build.CPU_ABI2;
            if (!str4.isEmpty()) {
                ppvnkbmzfphuuihfhotpVar.utxtilfqoxdwcqlqpjoj(str4);
            }
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            Point point = new Point();
            windowManager.getDefaultDisplay().getRealSize(point);
            int i2 = point.x;
            ppvnkbmzfphuuihfhotpVar.dyrapphjndqarxdhyvgv(Integer.valueOf(point.y));
            ppvnkbmzfphuuihfhotpVar.danumarvmgpbarrzqyrz(Integer.valueOf(i2));
            ppvnkbmzfphuuihfhotpVar.dbuymyhwehsdoxafsfpy(Integer.valueOf(uwanvjsqbzasnsnlxnqi(context)));
            ppvnkbmzfphuuihfhotpVar.danumarvmgpbarrzqyrz(Build.HOST);
            ppvnkbmzfphuuihfhotpVar.vqslvxgmgnhrmgypwmhq(Build.getRadioVersion());
            ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj(Settings.Secure.getString(context.getContentResolver(), "allowed_geolocation_origins"));
            ppvnkbmzfphuuihfhotpVar.vikftlgmuszlvyjnlikz(Integer.valueOf(context.getPackageManager().getSystemAvailableFeatures().length));
            ppvnkbmzfphuuihfhotpVar.dbuymyhwehsdoxafsfpy(cwzojhoqdsccekmlpbcq(context));
        }
        ppvnkbmzfphuuihfhotpVar.dbuymyhwehsdoxafsfpy(uusbetktgiikylwfbevs());
        return ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj();
    }

    public Integer lleeevhiydcwptuwzadc(Context context) {
        if (!com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_EXTERNAL_STORAGE").booleanValue() && !com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_MEDIA_VIDEO").booleanValue()) {
            return null;
        }
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursorQuery == null) {
            return 0;
        }
        Integer numValueOf = Integer.valueOf(cursorQuery.getCount());
        cursorQuery.close();
        return numValueOf;
    }

    public JSONObject pyxmijmxbomdjegczksl(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        jfrjraouuicuqboknnmi jfrjraouuicuqboknnmiVar = new jfrjraouuicuqboknnmi();
        if (telephonyManager != null) {
            int simState = telephonyManager.getSimState();
            String simCountryIso = telephonyManager.getSimCountryIso();
            String simOperator = telephonyManager.getSimOperator();
            String networkCountryIso = telephonyManager.getNetworkCountryIso();
            String networkOperator = telephonyManager.getNetworkOperator();
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            String simOperatorName = telephonyManager.getSimOperatorName();
            String strValueOf = String.valueOf(telephonyManager.hasIccCard());
            String strValueOf2 = String.valueOf(telephonyManager.isNetworkRoaming());
            jfrjraouuicuqboknnmiVar = zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, jfrjraouuicuqboknnmiVar, telephonyManager);
            jfrjraouuicuqboknnmiVar.yvrzbryuycempgkdhpvj(Integer.valueOf(simState));
            jfrjraouuicuqboknnmiVar.utxtilfqoxdwcqlqpjoj(simCountryIso);
            jfrjraouuicuqboknnmiVar.hekbabbvryaiwbpvazlo(simOperator);
            jfrjraouuicuqboknnmiVar.ooztjhejjvpgrdhjnyju(networkCountryIso);
            jfrjraouuicuqboknnmiVar.danumarvmgpbarrzqyrz(networkOperator);
            jfrjraouuicuqboknnmiVar.vikftlgmuszlvyjnlikz(networkOperatorName);
            jfrjraouuicuqboknnmiVar.hbycjaoesutelfxwaoca(simOperatorName);
            jfrjraouuicuqboknnmiVar.dbuymyhwehsdoxafsfpy(strValueOf);
            jfrjraouuicuqboknnmiVar.vjgujdxqyzpnlimdrvvt(strValueOf2);
            jfrjraouuicuqboknnmiVar.yvrzbryuycempgkdhpvj(Long.valueOf(System.currentTimeMillis()));
        }
        return jfrjraouuicuqboknnmiVar.yvrzbryuycempgkdhpvj();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            System.currentTimeMillis();
            JSONObject jSONObjectLdeiitcdqlqzkidvrbjy = ldeiitcdqlqzkidvrbjy(this.dbuymyhwehsdoxafsfpy);
            Message message = new Message();
            message.what = 0;
            message.obj = jSONObjectLdeiitcdqlqzkidvrbjy;
            this.yvrzbryuycempgkdhpvj.sendMessage(message);
            JSONObject jSONObjectVjgujdxqyzpnlimdrvvt = vjgujdxqyzpnlimdrvvt(this.dbuymyhwehsdoxafsfpy);
            Message message2 = new Message();
            message2.what = 1;
            message2.obj = jSONObjectVjgujdxqyzpnlimdrvvt;
            this.yvrzbryuycempgkdhpvj.sendMessage(message2);
            JSONObject jSONObjectCtfsaqlysacfjtqixtmr = ctfsaqlysacfjtqixtmr(this.dbuymyhwehsdoxafsfpy);
            Message message3 = new Message();
            message3.what = 2;
            message3.obj = jSONObjectCtfsaqlysacfjtqixtmr;
            this.yvrzbryuycempgkdhpvj.sendMessage(message3);
            JSONObject jSONObjectPyxmijmxbomdjegczksl = pyxmijmxbomdjegczksl(this.dbuymyhwehsdoxafsfpy);
            Message message4 = new Message();
            message4.what = 3;
            message4.obj = jSONObjectPyxmijmxbomdjegczksl;
            this.yvrzbryuycempgkdhpvj.sendMessage(message4);
            System.currentTimeMillis();
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
    }

    public JSONObject vjgujdxqyzpnlimdrvvt(Context context) {
        wgeshaitmpzrwttiokth wgeshaitmpzrwttiokthVar = new wgeshaitmpzrwttiokth();
        Context applicationContext = context.getApplicationContext();
        fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarVikftlgmuszlvyjnlikz = vikftlgmuszlvyjnlikz(context);
        if (fdwipeifdmvqsbqrrpypVarVikftlgmuszlvyjnlikz != null) {
            HashMap mapYvrzbryuycempgkdhpvj = fdwipeifdmvqsbqrrpypVarVikftlgmuszlvyjnlikz.yvrzbryuycempgkdhpvj();
            if (mapYvrzbryuycempgkdhpvj != null) {
                wgeshaitmpzrwttiokthVar.mxodkqpwhcryvsgsvabl(wgeshaitmpzrwttiokth.yvrzbryuycempgkdhpvj(mapYvrzbryuycempgkdhpvj));
                wgeshaitmpzrwttiokthVar.yvrzbryuycempgkdhpvj(fdwipeifdmvqsbqrrpypVarVikftlgmuszlvyjnlikz.dbuymyhwehsdoxafsfpy());
            }
        } else {
            wgeshaitmpzrwttiokthVar.dbuymyhwehsdoxafsfpy((Integer) (-1));
        }
        wgeshaitmpzrwttiokthVar.uusbetktgiikylwfbevs(gtykjqtliutrjfvjtiex(applicationContext));
        wgeshaitmpzrwttiokthVar.vjgujdxqyzpnlimdrvvt(lleeevhiydcwptuwzadc(applicationContext));
        List listNicafiaansnuaopzwdwm = nicafiaansnuaopzwdwm(applicationContext);
        if (listNicafiaansnuaopzwdwm != null) {
            Integer numValueOf = Integer.valueOf(listNicafiaansnuaopzwdwm.size());
            wgeshaitmpzrwttiokthVar.ooztjhejjvpgrdhjnyju(listNicafiaansnuaopzwdwm);
            wgeshaitmpzrwttiokthVar.ctfsaqlysacfjtqixtmr(numValueOf);
        }
        wgeshaitmpzrwttiokthVar.efmnkxwvqeqnaehsyess(efmnkxwvqeqnaehsyess(applicationContext));
        wgeshaitmpzrwttiokthVar.yvrzbryuycempgkdhpvj(ctfsaqlysacfjtqixtmr());
        wgeshaitmpzrwttiokthVar.dbuymyhwehsdoxafsfpy(fniextxrjqlolhwhqggv(applicationContext));
        ArrayList arrayListOoztjhejjvpgrdhjnyju = ooztjhejjvpgrdhjnyju(applicationContext);
        if (arrayListOoztjhejjvpgrdhjnyju != null) {
            wgeshaitmpzrwttiokthVar.yvrzbryuycempgkdhpvj(Integer.valueOf(arrayListOoztjhejjvpgrdhjnyju.size()));
            wgeshaitmpzrwttiokthVar.dyrapphjndqarxdhyvgv(arrayListOoztjhejjvpgrdhjnyju);
        }
        return wgeshaitmpzrwttiokthVar.yvrzbryuycempgkdhpvj();
    }

    private static String ctfsaqlysacfjtqixtmr() throws SocketException {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(sb.toString());
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return "02:00:00:00:00:00";
        }
    }

    public static JSONObject dbuymyhwehsdoxafsfpy() {
        ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar = new ppvnkbmzfphuuihfhotp();
        ppvnkbmzfphuuihfhotpVar.vpjqwyqiclwncdgbkkkg(Build.MODEL);
        ppvnkbmzfphuuihfhotpVar.dtzqkpwicmyznzcqlscc(Build.MANUFACTURER);
        ppvnkbmzfphuuihfhotpVar.dbuymyhwehsdoxafsfpy(Build.VERSION.RELEASE);
        ppvnkbmzfphuuihfhotpVar.ppdfopkmksgbbjoukavl(Build.VERSION.INCREMENTAL);
        ppvnkbmzfphuuihfhotpVar.vikftlgmuszlvyjnlikz(Build.ID);
        return ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj();
    }

    public static Long uusbetktgiikylwfbevs() {
        try {
            File file = new File("/data/data/", "com.android.vending");
            if (file.exists()) {
                return Long.valueOf(file.lastModified());
            }
            return null;
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return null;
        }
    }

    private static Double yvrzbryuycempgkdhpvj(Context context) {
        if (context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")) != null) {
            return Double.valueOf(r4.getIntExtra("temperature", 0) / 10.0d);
        }
        return null;
    }

    private static float yvrzbryuycempgkdhpvj() throws InterruptedException, IOException {
        try {
            Process processExec = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp");
            processExec.waitFor();
            String line = new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine();
            if (line != null) {
                return Float.parseFloat(line) / 1000.0f;
            }
            return 51.0f;
        } catch (Exception e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
            return 0.0f;
        }
    }

    public static JSONObject yvrzbryuycempgkdhpvj(Context context, Boolean bool, String str, String str2, pzqcxkrstpkgvuxxlors pzqcxkrstpkgvuxxlorsVar) {
        JSONObject jSONObject = new JSONObject();
        wgeshaitmpzrwttiokth wgeshaitmpzrwttiokthVar = new wgeshaitmpzrwttiokth();
        wgeshaitmpzrwttiokthVar.yvrzbryuycempgkdhpvj(ctfsaqlysacfjtqixtmr());
        fdwipeifdmvqsbqrrpyp fdwipeifdmvqsbqrrpypVarVikftlgmuszlvyjnlikz = vikftlgmuszlvyjnlikz(context);
        if (fdwipeifdmvqsbqrrpypVarVikftlgmuszlvyjnlikz != null) {
            HashMap mapYvrzbryuycempgkdhpvj = fdwipeifdmvqsbqrrpypVarVikftlgmuszlvyjnlikz.yvrzbryuycempgkdhpvj();
            if (mapYvrzbryuycempgkdhpvj != null) {
                wgeshaitmpzrwttiokthVar.mxodkqpwhcryvsgsvabl(wgeshaitmpzrwttiokth.yvrzbryuycempgkdhpvj(mapYvrzbryuycempgkdhpvj));
                wgeshaitmpzrwttiokthVar.yvrzbryuycempgkdhpvj(fdwipeifdmvqsbqrrpypVarVikftlgmuszlvyjnlikz.dbuymyhwehsdoxafsfpy());
            }
        } else {
            wgeshaitmpzrwttiokthVar.dbuymyhwehsdoxafsfpy((Integer) (-1));
        }
        ppvnkbmzfphuuihfhotp ppvnkbmzfphuuihfhotpVar = new ppvnkbmzfphuuihfhotp();
        ppvnkbmzfphuuihfhotpVar.kqybgnwtttwkvdwxaqhw(com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context));
        ppvnkbmzfphuuihfhotpVar.uhujfisxsgsrhmusmdur(str);
        ppvnkbmzfphuuihfhotpVar.vpjqwyqiclwncdgbkkkg(Build.MODEL);
        asvpglyfnwnhyiooalpb asvpglyfnwnhyiooalpbVar = new asvpglyfnwnhyiooalpb();
        String string = UUID.randomUUID().toString();
        asvpglyfnwnhyiooalpbVar.uusbetktgiikylwfbevs(str2);
        asvpglyfnwnhyiooalpbVar.ctfsaqlysacfjtqixtmr(string);
        if (bool.booleanValue()) {
            asvpglyfnwnhyiooalpbVar.yvrzbryuycempgkdhpvj(ahpfbhknxzgojyggofxj.zwlltpaufqribmleigux.DEVICE_HEURISTICS_EVENT);
        }
        try {
            jSONObject.put("device_data", ppvnkbmzfphuuihfhotpVar.yvrzbryuycempgkdhpvj());
            jSONObject.put("user_data", wgeshaitmpzrwttiokthVar.yvrzbryuycempgkdhpvj());
            jSONObject.put(QuickTimeAtomTypes.ATOM_KEYS, asvpglyfnwnhyiooalpbVar.yvrzbryuycempgkdhpvj());
            jSONObject.put(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE, pzqcxkrstpkgvuxxlorsVar.yvrzbryuycempgkdhpvj());
        } catch (JSONException e) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
        }
        return jSONObject;
    }

    private static JSONObject yvrzbryuycempgkdhpvj(String str, Context context) throws JSONException {
        Location lastKnownLocation;
        if (!com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_FINE_LOCATION").booleanValue() && !com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_COARSE_LOCATION").booleanValue()) {
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
            if (locationManager.isProviderEnabled(str) && (lastKnownLocation = locationManager.getLastKnownLocation(str)) != null) {
                double altitude = lastKnownLocation.getAltitude();
                double latitude = lastKnownLocation.getLatitude();
                double longitude = lastKnownLocation.getLongitude();
                float accuracy = lastKnownLocation.getAccuracy();
                boolean zIsFromMockProvider = lastKnownLocation.isFromMockProvider();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("altitude", altitude);
                    jSONObject.put("latitude", latitude);
                    jSONObject.put("longitude", longitude);
                    jSONObject.put("accuracy", accuracy);
                    jSONObject.put("isMock", zIsFromMockProvider);
                    jSONObject.put("fullAccuracy", str.equals("fused") || str.equals("gps"));
                    return jSONObject;
                } catch (JSONException e) {
                    com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e);
                }
            }
            return null;
        } catch (Exception e2) {
            com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.dbuymyhwehsdoxafsfpy(e2);
            return null;
        }
    }

    private static void yvrzbryuycempgkdhpvj(Context context, List list, List list2) {
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_PHONE_STATE").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.READ_PHONE_STATE);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.READ_PHONE_STATE);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_MEDIA_IMAGES").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.READ_MEDIA_IMAGES);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.READ_MEDIA_IMAGES);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_MEDIA_VIDEO").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.READ_MEDIA_VIDEO);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.READ_MEDIA_VIDEO);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_EXTERNAL_STORAGE").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.READ_EXTERNAL_STORAGE);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.READ_EXTERNAL_STORAGE);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_CONTACTS").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.READ_CONTACTS);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.READ_CONTACTS);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.WRITE_EXTERNAL_STORAGE").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.WRITE_EXTERNAL_STORAGE);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.WRITE_EXTERNAL_STORAGE);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_FINE_LOCATION").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.ACCESS_FINE_LOCATION);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.ACCESS_FINE_LOCATION);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_COARSE_LOCATION").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.ACCESS_COARSE_LOCATION);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.ACCESS_COARSE_LOCATION);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.ACCESS_WIFI_STATE").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.ACCESS_WIFI_STATE);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.ACCESS_WIFI_STATE);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.READ_CALL_LOG").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.READ_CALL_LOG);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.READ_CALL_LOG);
        }
        if (Build.VERSION.SDK_INT < 31 ? !com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.BLUETOOTH").booleanValue() : !com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.BLUETOOTH_CONNECT").booleanValue()) {
            list2.add(lbtmiwirddgqkunoyrov.BLUETOOTH);
        } else {
            list.add(lbtmiwirddgqkunoyrov.BLUETOOTH);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.GET_ACCOUNTS").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.GET_ACCOUNTS);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.GET_ACCOUNTS);
        }
        if (com.paygilant.PG_FraudDetection_SDK.zwlltpaufqribmleigux.yvrzbryuycempgkdhpvj(context, "android.permission.CAMERA").booleanValue()) {
            list.add(lbtmiwirddgqkunoyrov.CAMERA);
        } else {
            list2.add(lbtmiwirddgqkunoyrov.CAMERA);
        }
    }
}
