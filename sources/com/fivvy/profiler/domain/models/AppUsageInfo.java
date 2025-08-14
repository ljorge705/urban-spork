package com.fivvy.profiler.domain.models;

/* loaded from: classes5.dex */
public class AppUsageInfo {
    private final String appName;
    private final String packageName;
    private final double usage;

    public String getAppName() {
        return this.appName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public double getUsage() {
        return this.usage;
    }

    public AppUsageInfo(String str, String str2, double d) {
        this.appName = str;
        this.packageName = str2;
        this.usage = Math.round(d * 100.0d) / 100.0d;
    }

    public String toString() {
        return "\nAppName: " + this.appName + "\nPackageName: " + this.packageName + "\nUsage: " + this.usage + " minutes \n";
    }
}
