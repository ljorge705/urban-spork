package com.uxcam.datamodel;

import com.uxcam.env.Environment;
import com.uxcam.internals.bi;
import com.uxcam.internals.hr;
import com.uxcam.screenshot.model.UXCamOcclusion;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public final class UXConfig {

    /* renamed from: a, reason: collision with root package name */
    public final List<UXCamOcclusion> f71a;
    public String b;
    public boolean c;
    public MultiSessionRecordStatus d;
    public boolean e;
    public boolean f;
    public boolean g;
    public final Environment h;

    public static class Builder {
        private final String appKey;
        private boolean isAutomaticScreenNameTaggingEnabled;
        private boolean isCrashHandlingEnabled;
        private boolean isImprovedScreenCaptureEnabled;
        private MultiSessionRecordStatus multiSessionRecordStatus;
        private boolean honorFlagSecure = false;
        private List<UXCamOcclusion> occlusions = new ArrayList();
        private Environment environment = null;

        public Builder(String str) {
            this.isAutomaticScreenNameTaggingEnabled = true;
            this.multiSessionRecordStatus = MultiSessionRecordStatus.ENABLED;
            this.isCrashHandlingEnabled = true;
            this.isImprovedScreenCaptureEnabled = false;
            this.appKey = str;
            hr hrVarL = bi.b().l();
            if (hrVarL.b()) {
                UXConfig uXConfigA = hrVarL.a();
                this.isAutomaticScreenNameTaggingEnabled = uXConfigA.c;
                this.multiSessionRecordStatus = uXConfigA.d;
                this.isCrashHandlingEnabled = uXConfigA.e;
                this.isImprovedScreenCaptureEnabled = uXConfigA.f;
            }
        }

        public UXConfig build() {
            return new UXConfig(this);
        }

        public Builder enableAutomaticScreenNameTagging(boolean z) {
            this.isAutomaticScreenNameTaggingEnabled = z;
            return this;
        }

        public Builder enableCrashHandling(boolean z) {
            this.isCrashHandlingEnabled = z;
            return this;
        }

        public Builder enableImprovedScreenCapture(boolean z) {
            this.isImprovedScreenCaptureEnabled = z;
            return this;
        }

        public Builder enableMultiSessionRecord(boolean z) {
            this.multiSessionRecordStatus = z ? MultiSessionRecordStatus.ENABLED : MultiSessionRecordStatus.DISABLED_BUT_NOT_STARTED;
            return this;
        }

        public Builder environment(Environment environment) {
            this.environment = environment;
            return this;
        }

        public Builder honorFlagSecure() {
            this.honorFlagSecure = true;
            return this;
        }

        public Builder occlusions(List<UXCamOcclusion> list) {
            this.occlusions = list;
            return this;
        }
    }

    public enum MultiSessionRecordStatus {
        ENABLED,
        DISABLED_BUT_NOT_STARTED,
        DISABLED
    }

    public UXConfig(Builder builder) {
        this.b = builder.appKey;
        this.c = builder.isAutomaticScreenNameTaggingEnabled;
        this.d = builder.multiSessionRecordStatus;
        this.e = builder.isCrashHandlingEnabled;
        this.f = builder.isImprovedScreenCaptureEnabled;
        this.f71a = builder.occlusions;
        this.g = builder.honorFlagSecure;
        this.h = builder.environment;
    }
}
