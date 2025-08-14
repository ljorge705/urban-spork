package com.scottyab.rootbeer;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.clevertap.android.sdk.Constants;
import com.scottyab.rootbeer.util.QLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes6.dex */
public class RootBeer {
    private boolean loggingEnabled = true;
    private final Context mContext;

    public RootBeer(Context context) {
        this.mContext = context;
    }

    public boolean isRooted() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary("su") || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative() || checkForMagiskBinary();
    }

    @Deprecated
    public boolean isRootedWithoutBusyBoxCheck() {
        return isRooted();
    }

    public boolean isRootedWithBusyBoxCheck() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary("su") || checkForBinary("busybox") || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForRootNative() || checkForMagiskBinary();
    }

    public boolean detectTestKeys() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public boolean detectRootManagementApps() {
        return detectRootManagementApps(null);
    }

    public boolean detectRootManagementApps(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(Const.knownRootAppsPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectPotentiallyDangerousApps() {
        return detectPotentiallyDangerousApps(null);
    }

    public boolean detectPotentiallyDangerousApps(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(Const.knownDangerousAppsPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectRootCloakingApps() {
        return detectRootCloakingApps(null) || (canLoadNativeLibrary() && !checkForNativeLibraryReadAccess());
    }

    public boolean detectRootCloakingApps(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(Const.knownRootCloakingPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean checkForSuBinary() {
        return checkForBinary("su");
    }

    public boolean checkForMagiskBinary() {
        return checkForBinary("magisk");
    }

    public boolean checkForBusyBoxBinary() {
        return checkForBinary("busybox");
    }

    public boolean checkForBinary(String str) {
        boolean z = false;
        for (String str2 : Const.getPaths()) {
            String str3 = str2 + str;
            if (new File(str2, str).exists()) {
                QLog.v(str3 + " binary detected!");
                z = true;
            }
        }
        return z;
    }

    public void setLogging(boolean z) {
        this.loggingEnabled = z;
        QLog.LOGGING_LEVEL = z ? 5 : 0;
    }

    private String[] propsReader() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("getprop").getInputStream();
            if (inputStream == null) {
                return null;
            }
            return new Scanner(inputStream).useDelimiter("\\A").next().split("\n");
        } catch (IOException | NoSuchElementException e) {
            QLog.e(e);
            return null;
        }
    }

    private String[] mountReader() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("mount").getInputStream();
            if (inputStream == null) {
                return null;
            }
            return new Scanner(inputStream).useDelimiter("\\A").next().split("\n");
        } catch (IOException | NoSuchElementException e) {
            QLog.e(e);
            return null;
        }
    }

    private boolean isAnyPackageFromListInstalled(List<String> list) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = this.mContext.getPackageManager();
        boolean z = false;
        for (String str : list) {
            try {
                packageManager.getPackageInfo(str, 0);
                QLog.e(str + " ROOT management app detected!");
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    public boolean checkForDangerousProps() {
        HashMap map = new HashMap();
        map.put("ro.debuggable", "1");
        map.put("ro.secure", "0");
        String[] strArrPropsReader = propsReader();
        if (strArrPropsReader == null) {
            return false;
        }
        boolean z = false;
        for (String str : strArrPropsReader) {
            for (String str2 : map.keySet()) {
                if (str.contains(str2)) {
                    String str3 = "[" + ((String) map.get(str2)) + "]";
                    if (str.contains(str3)) {
                        QLog.v(str2 + " = " + str3 + " detected!");
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public boolean checkForRWPaths() {
        String[] strArrMountReader = mountReader();
        int i = 0;
        if (strArrMountReader == null) {
            return false;
        }
        int length = strArrMountReader.length;
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            String str = strArrMountReader[i2];
            String[] strArrSplit = str.split(StringUtils.SPACE);
            if (strArrSplit.length < 6) {
                QLog.e("Error formatting mount line: " + str);
            } else {
                String str2 = strArrSplit[2];
                String strReplace = strArrSplit[5];
                String[] strArr = Const.pathsThatShouldNotBeWritable;
                int length2 = strArr.length;
                int i3 = i;
                while (i3 < length2) {
                    String str3 = strArr[i3];
                    if (str2.equalsIgnoreCase(str3)) {
                        strReplace = strReplace.replace("(", "").replace(")", "");
                        String[] strArrSplit2 = strReplace.split(Constants.SEPARATOR_COMMA);
                        int length3 = strArrSplit2.length;
                        int i4 = i;
                        while (true) {
                            if (i4 >= length3) {
                                break;
                            }
                            if (strArrSplit2[i4].equalsIgnoreCase("rw")) {
                                QLog.v(str3 + " path is mounted with rw permissions! " + str);
                                z = true;
                                break;
                            }
                            i4++;
                        }
                    }
                    i3++;
                    i = 0;
                }
            }
            i2++;
            i = 0;
        }
        return z;
    }

    public boolean checkSuExists() {
        Process processExec = null;
        try {
            processExec = Runtime.getRuntime().exec(new String[]{"which", "su"});
            boolean z = new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine() != null;
            if (processExec != null) {
                processExec.destroy();
            }
            return z;
        } catch (Throwable unused) {
            if (processExec != null) {
                processExec.destroy();
            }
            return false;
        }
    }

    public boolean checkForNativeLibraryReadAccess() {
        try {
            new RootBeerNative().setLogDebugMessages(this.loggingEnabled);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            return false;
        }
    }

    public boolean canLoadNativeLibrary() {
        return new RootBeerNative().wasNativeLibraryLoaded();
    }

    public boolean checkForRootNative() {
        if (!canLoadNativeLibrary()) {
            QLog.e("We could not load the native library to test for root");
            return false;
        }
        String[] paths = Const.getPaths();
        int length = paths.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = paths[i] + "su";
        }
        RootBeerNative rootBeerNative = new RootBeerNative();
        try {
            rootBeerNative.setLogDebugMessages(this.loggingEnabled);
            return rootBeerNative.checkForRoot(strArr) > 0;
        } catch (UnsatisfiedLinkError unused) {
            return false;
        }
    }
}
