package com.microsoft.codepush.react;

import android.content.Context;

/* loaded from: classes2.dex */
public class CodePushBuilder {
    private Context mContext;
    private String mDeploymentKey;
    private boolean mIsDebugMode;
    private Integer mPublicKeyResourceDescriptor;
    private String mServerUrl = CodePush.getServiceUrl();

    public CodePushBuilder setIsDebugMode(boolean z) {
        this.mIsDebugMode = z;
        return this;
    }

    public CodePushBuilder setServerUrl(String str) {
        this.mServerUrl = str;
        return this;
    }

    public CodePushBuilder(String str, Context context) {
        this.mDeploymentKey = str;
        this.mContext = context;
    }

    public CodePushBuilder setPublicKeyResourceDescriptor(int i) {
        this.mPublicKeyResourceDescriptor = Integer.valueOf(i);
        return this;
    }

    public CodePush build() {
        return new CodePush(this.mDeploymentKey, this.mContext, this.mIsDebugMode, this.mServerUrl, this.mPublicKeyResourceDescriptor);
    }
}
