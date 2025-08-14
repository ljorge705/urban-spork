package com.onfido.android.sdk.capture.utils.yearclass;

import android.app.ActivityManager;
import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes2.dex */
public class DeviceInfo {
    private static final FileFilter CPU_FILTER = new FileFilter() { // from class: com.onfido.android.sdk.capture.utils.yearclass.DeviceInfo.1
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith("cpu")) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (!Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    };
    public static final int DEVICEINFO_UNKNOWN = -1;

    private static int extractValue(byte[] bArr, int i) {
        byte b;
        while (i < bArr.length && (b = bArr[i]) != 10) {
            if (Character.isDigit(b)) {
                int i2 = i + 1;
                while (i2 < bArr.length && Character.isDigit(bArr[i2])) {
                    i2++;
                }
                return Integer.parseInt(new String(bArr, 0, i, i2 - i));
            }
            i++;
        }
        return -1;
    }

    public static int getCPUMaxFreqKHz() throws IOException {
        int i = -1;
        for (int i2 = 0; i2 < getNumberOfCPUCores(); i2++) {
            try {
                File file = new File("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_max_freq");
                if (file.exists() && file.canRead()) {
                    byte[] bArr = new byte[128];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileInputStream.read(bArr);
                        int i3 = 0;
                        while (Character.isDigit(bArr[i3]) && i3 < 128) {
                            i3++;
                        }
                        int i4 = Integer.parseInt(new String(bArr, 0, i3));
                        if (i4 > i) {
                            i = i4;
                        }
                    } catch (NumberFormatException unused) {
                    } catch (Throwable th) {
                        fileInputStream.close();
                        throw th;
                    }
                    fileInputStream.close();
                }
            } catch (IOException unused2) {
                return -1;
            }
        }
        if (i == -1) {
            FileInputStream fileInputStream2 = new FileInputStream("/proc/cpuinfo");
            try {
                int fileForValue = parseFileForValue("cpu MHz", fileInputStream2) * 1000;
                if (fileForValue > i) {
                    i = fileForValue;
                }
                fileInputStream2.close();
            } catch (Throwable th2) {
                fileInputStream2.close();
                throw th2;
            }
        }
        return i;
    }

    private static int getCoresFromCPUFileList() {
        return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
    }

    public static int getCoresFromFileInfo(String str) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = bufferedReader.readLine();
                bufferedReader.close();
                int coresFromFileString = getCoresFromFileString(line);
                fileInputStream.close();
                return coresFromFileString;
            } finally {
            }
        } catch (IOException unused) {
            return -1;
        }
    }

    static int getCoresFromFileString(String str) {
        if (str == null || !str.matches("0-[\\d]+$")) {
            return -1;
        }
        return Integer.valueOf(str.substring(2)).intValue() + 1;
    }

    public static int getNumberOfCPUCores() {
        try {
            int coresFromFileInfo = getCoresFromFileInfo("/sys/devices/system/cpu/possible");
            if (coresFromFileInfo == -1) {
                coresFromFileInfo = getCoresFromFileInfo("/sys/devices/system/cpu/present");
            }
            return coresFromFileInfo == -1 ? getCoresFromCPUFileList() : coresFromFileInfo;
        } catch (NullPointerException | SecurityException unused) {
            return -1;
        }
    }

    public static long getTotalMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    private static int parseFileForValue(String str, FileInputStream fileInputStream) throws IOException {
        byte[] bArr = new byte[1024];
        try {
            int i = fileInputStream.read(bArr);
            int i2 = 0;
            while (i2 < i) {
                byte b = bArr[i2];
                if (b == 10 || i2 == 0) {
                    if (b == 10) {
                        i2++;
                    }
                    for (int i3 = i2; i3 < i; i3++) {
                        int i4 = i3 - i2;
                        if (bArr[i3] != str.charAt(i4)) {
                            break;
                        }
                        if (i4 == str.length() - 1) {
                            return extractValue(bArr, i3);
                        }
                    }
                }
                i2++;
            }
            return -1;
        } catch (IOException | NumberFormatException unused) {
            return -1;
        }
    }
}
