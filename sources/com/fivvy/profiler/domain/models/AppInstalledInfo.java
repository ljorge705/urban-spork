package com.fivvy.profiler.domain.models;

/* loaded from: classes5.dex */
public class AppInstalledInfo {
    public final String appName;
    public final String category;
    public final String icon;
    public final String installTime;
    public final String lastUpdateTime;
    public final String packageName;
    public final String versionCode;
    public final String versionName;

    public AppInstalledInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.appName = str;
        this.packageName = str2;
        this.category = str8;
        this.icon = str6;
        this.installTime = str4;
        this.lastUpdateTime = str7;
        this.versionCode = str3;
        this.versionName = str5;
    }

    public String toString() {
        return "\nName: " + this.appName + ", PackageName: " + this.packageName + ", VersionCode: " + this.versionCode + ", InstallTime: " + this.installTime + ", VersionName: " + this.versionName + ", Icon: " + this.icon + ", LastUpdateTime: " + this.lastUpdateTime + ", Category: " + this.category + "\n";
    }
}
