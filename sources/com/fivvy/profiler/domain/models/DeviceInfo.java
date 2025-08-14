package com.fivvy.profiler.domain.models;

/* loaded from: classes5.dex */
public class DeviceInfo {
    private final String apiLevel;
    private final String base;
    private final String baseOs;
    private final int batteryStatus;
    private final String brand;
    private final String device;
    private final String deviceId;
    private final String display;
    private final String fingerprint;
    private final String hardware;
    private final String host;
    private final String id;
    private final String incrementalVersion;
    private final String manufacturer;
    private final String model;
    private final String product;
    private String publicIP;
    private final String releaseVersion;
    private final String tags;
    private final String type;

    public String getApiLevel() {
        return this.apiLevel;
    }

    public String getBase() {
        return this.base;
    }

    public String getBaseOs() {
        return this.baseOs;
    }

    public int getBatteryStatus() {
        return this.batteryStatus;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getDevice() {
        return this.device;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDisplay() {
        return this.display;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public String getHardware() {
        return this.hardware;
    }

    public String getHost() {
        return this.host;
    }

    public String getId() {
        return this.id;
    }

    public String getIncrementalVersion() {
        return this.incrementalVersion;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public String getProduct() {
        return this.product;
    }

    public String getPublicIP() {
        return this.publicIP;
    }

    public String getReleaseVersion() {
        return this.releaseVersion;
    }

    public String getTags() {
        return this.tags;
    }

    public String getType() {
        return this.type;
    }

    private DeviceInfo(Builder builder) {
        this.apiLevel = builder.apiLevel;
        this.deviceId = builder.deviceId;
        this.device = builder.device;
        this.hardware = builder.hardware;
        this.brand = builder.brand;
        this.manufacturer = builder.manufacturer;
        this.model = builder.model;
        this.product = builder.product;
        this.tags = builder.tags;
        this.type = builder.type;
        this.base = builder.base;
        this.id = builder.id;
        this.host = builder.host;
        this.fingerprint = builder.fingerprint;
        this.incrementalVersion = builder.incrementalVersion;
        this.releaseVersion = builder.releaseVersion;
        this.baseOs = builder.baseOs;
        this.display = builder.display;
        this.batteryStatus = builder.batteryStatus;
        this.publicIP = builder.publicIP;
    }

    public static class Builder {
        private String apiLevel;
        private String base;
        private String baseOs;
        private int batteryStatus;
        private String brand;
        private String device;
        private String deviceId;
        private String display;
        private String fingerprint;
        private String hardware;
        private String host;
        private String id;
        private String incrementalVersion;
        private String manufacturer;
        private String model;
        private String product;
        private String publicIP;
        private String releaseVersion;
        private String tags;
        private String type;

        public Builder apiLevel(String str) {
            this.apiLevel = str;
            return this;
        }

        public Builder base(String str) {
            this.base = str;
            return this;
        }

        public Builder baseOs(String str) {
            this.baseOs = str;
            return this;
        }

        public Builder batteryStatus(int i) {
            this.batteryStatus = i;
            return this;
        }

        public Builder brand(String str) {
            this.brand = str;
            return this;
        }

        public Builder device(String str) {
            this.device = str;
            return this;
        }

        public Builder deviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder display(String str) {
            this.display = str;
            return this;
        }

        public Builder fingerprint(String str) {
            this.fingerprint = str;
            return this;
        }

        public Builder hardware(String str) {
            this.hardware = str;
            return this;
        }

        public Builder host(String str) {
            this.host = str;
            return this;
        }

        public Builder id(String str) {
            this.id = str;
            return this;
        }

        public Builder incrementalVersion(String str) {
            this.incrementalVersion = str;
            return this;
        }

        public Builder manufacturer(String str) {
            this.manufacturer = str;
            return this;
        }

        public Builder model(String str) {
            this.model = str;
            return this;
        }

        public Builder product(String str) {
            this.product = str;
            return this;
        }

        public Builder publicIP(String str) {
            this.publicIP = str;
            return this;
        }

        public Builder releaseVersion(String str) {
            this.releaseVersion = str;
            return this;
        }

        public Builder tags(String str) {
            this.tags = str;
            return this;
        }

        public Builder type(String str) {
            this.type = str;
            return this;
        }

        public DeviceInfo build() {
            return new DeviceInfo(this);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("API Level: ");
        String str = this.apiLevel;
        if (str == null) {
            str = "N/A";
        }
        StringBuilder sbAppend = sb.append(str).append("\nDevice ID: ");
        String str2 = this.deviceId;
        if (str2 == null) {
            str2 = "N/A";
        }
        StringBuilder sbAppend2 = sbAppend.append(str2).append("\nDevice: ");
        String str3 = this.device;
        if (str3 == null) {
            str3 = "N/A";
        }
        StringBuilder sbAppend3 = sbAppend2.append(str3).append("\nHardware: ");
        String str4 = this.hardware;
        if (str4 == null) {
            str4 = "N/A";
        }
        StringBuilder sbAppend4 = sbAppend3.append(str4).append("\nBrand: ");
        String str5 = this.brand;
        if (str5 == null) {
            str5 = "N/A";
        }
        StringBuilder sbAppend5 = sbAppend4.append(str5).append("\nManufacturer: ");
        String str6 = this.manufacturer;
        if (str6 == null) {
            str6 = "N/A";
        }
        StringBuilder sbAppend6 = sbAppend5.append(str6).append("\nModel: ");
        String str7 = this.model;
        if (str7 == null) {
            str7 = "N/A";
        }
        StringBuilder sbAppend7 = sbAppend6.append(str7).append("\nProduct: ");
        String str8 = this.product;
        if (str8 == null) {
            str8 = "N/A";
        }
        StringBuilder sbAppend8 = sbAppend7.append(str8).append("\nTags: ");
        String str9 = this.tags;
        if (str9 == null) {
            str9 = "N/A";
        }
        StringBuilder sbAppend9 = sbAppend8.append(str9).append("\nType: ");
        String str10 = this.type;
        if (str10 == null) {
            str10 = "N/A";
        }
        StringBuilder sbAppend10 = sbAppend9.append(str10).append("\nBase: ");
        String str11 = this.base;
        if (str11 == null) {
            str11 = "N/A";
        }
        StringBuilder sbAppend11 = sbAppend10.append(str11).append("\nID: ");
        String str12 = this.id;
        if (str12 == null) {
            str12 = "N/A";
        }
        StringBuilder sbAppend12 = sbAppend11.append(str12).append("\nHost: ");
        String str13 = this.host;
        if (str13 == null) {
            str13 = "N/A";
        }
        StringBuilder sbAppend13 = sbAppend12.append(str13).append("\nFingerprint: ");
        String str14 = this.fingerprint;
        if (str14 == null) {
            str14 = "N/A";
        }
        StringBuilder sbAppend14 = sbAppend13.append(str14).append("\nIncremental Version: ");
        String str15 = this.incrementalVersion;
        if (str15 == null) {
            str15 = "N/A";
        }
        StringBuilder sbAppend15 = sbAppend14.append(str15).append("\nRelease Version: ");
        String str16 = this.releaseVersion;
        if (str16 == null) {
            str16 = "N/A";
        }
        StringBuilder sbAppend16 = sbAppend15.append(str16).append("\nBase OS: ");
        String str17 = this.baseOs;
        if (str17 == null) {
            str17 = "N/A";
        }
        StringBuilder sbAppend17 = sbAppend16.append(str17).append("\nDisplay: ");
        String str18 = this.display;
        return sbAppend17.append(str18 != null ? str18 : "N/A").append("\nBattery Status: ").append(this.batteryStatus).append("\npublic IP: ").append(this.publicIP).toString();
    }
}
