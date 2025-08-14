package com.learnium.RNDeviceInfo;

/* loaded from: classes2.dex */
public enum DeviceType {
    HANDSET("Handset"),
    TABLET("Tablet"),
    TV("Tv"),
    UNKNOWN("unknown");

    private final String value;

    public String getValue() {
        return this.value;
    }

    DeviceType(String str) {
        this.value = str;
    }
}
