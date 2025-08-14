package com.clevertap.android.sdk.login;

import com.clevertap.android.sdk.CleverTapInstanceConfig;

/* loaded from: classes5.dex */
public class LegacyIdentityRepo implements IdentityRepo {
    private static final String TAG = "LegacyIdentityRepo";
    private final CleverTapInstanceConfig config;
    private IdentitySet identities;

    @Override // com.clevertap.android.sdk.login.IdentityRepo
    public IdentitySet getIdentitySet() {
        return this.identities;
    }

    public LegacyIdentityRepo(CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.config = cleverTapInstanceConfig;
        loadIdentitySet();
    }

    @Override // com.clevertap.android.sdk.login.IdentityRepo
    public boolean hasIdentity(String str) {
        boolean zContains = this.identities.contains(str);
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "isIdentity [Key: " + str + " , Value: " + zContains + "]");
        return zContains;
    }

    private void loadIdentitySet() {
        this.identities = IdentitySet.getDefault();
        this.config.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "LegacyIdentityRepo Setting the default IdentitySet[" + this.identities + "]");
    }
}
