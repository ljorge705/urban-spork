package com.microsoft.codepush.react;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.uimanager.ViewManager;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CodePush implements ReactPackage {
    private static CodePush mCurrentInstance = null;
    private static String mPublicKey = null;
    private static ReactInstanceHolder mReactInstanceHolder = null;
    private static String mServerUrl = "https://codepush.appcenter.ms/";
    private static String sAppVersion = null;
    private static boolean sIsRunningBinaryVersion = false;
    private static boolean sNeedToReportRollback = false;
    private static boolean sTestConfigurationFlag = false;
    private String mAssetsBundleFileName;
    private Context mContext;
    private String mDeploymentKey;
    private boolean mDidUpdate;
    private final boolean mIsDebugMode;
    private SettingsManager mSettingsManager;
    private CodePushTelemetryManager mTelemetryManager;
    private CodePushUpdateManager mUpdateManager;

    public static String getServiceUrl() {
        return mServerUrl;
    }

    public static boolean isUsingTestConfiguration() {
        return sTestConfigurationFlag;
    }

    public static void overrideAppVersion(String str) {
        sAppVersion = str;
    }

    public static void setReactInstanceHolder(ReactInstanceHolder reactInstanceHolder) {
        mReactInstanceHolder = reactInstanceHolder;
    }

    public static void setUsingTestConfiguration(boolean z) {
        sTestConfigurationFlag = z;
    }

    public boolean didUpdate() {
        return this.mDidUpdate;
    }

    public String getAppVersion() {
        return sAppVersion;
    }

    public String getAssetsBundleFileName() {
        return this.mAssetsBundleFileName;
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getDeploymentKey() {
        return this.mDeploymentKey;
    }

    public String getPublicKey() {
        return mPublicKey;
    }

    public String getServerUrl() {
        return mServerUrl;
    }

    void invalidateCurrentInstance() {
        mCurrentInstance = null;
    }

    boolean isDebugMode() {
        return this.mIsDebugMode;
    }

    boolean isRunningBinaryVersion() {
        return sIsRunningBinaryVersion;
    }

    boolean needToReportRollback() {
        return sNeedToReportRollback;
    }

    public void setDeploymentKey(String str) {
        this.mDeploymentKey = str;
    }

    public void setNeedToReportRollback(boolean z) {
        sNeedToReportRollback = z;
    }

    public CodePush(String str, Context context) {
        this(str, context, false);
    }

    public CodePush(String str, Context context, boolean z) {
        this.mDidUpdate = false;
        this.mContext = context.getApplicationContext();
        this.mUpdateManager = new CodePushUpdateManager(context.getFilesDir().getAbsolutePath());
        this.mTelemetryManager = new CodePushTelemetryManager(this.mContext);
        this.mDeploymentKey = str;
        this.mIsDebugMode = z;
        this.mSettingsManager = new SettingsManager(this.mContext);
        if (sAppVersion == null) {
            try {
                sAppVersion = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                throw new CodePushUnknownException("Unable to get package info for " + this.mContext.getPackageName(), e);
            }
        }
        mCurrentInstance = this;
        String customPropertyFromStringsIfExist = getCustomPropertyFromStringsIfExist("PublicKey");
        if (customPropertyFromStringsIfExist != null) {
            mPublicKey = customPropertyFromStringsIfExist;
        }
        String customPropertyFromStringsIfExist2 = getCustomPropertyFromStringsIfExist("ServerUrl");
        if (customPropertyFromStringsIfExist2 != null) {
            mServerUrl = customPropertyFromStringsIfExist2;
        }
        clearDebugCacheIfNeeded(null);
        initializeUpdateAfterRestart();
    }

    public CodePush(String str, Context context, boolean z, String str2) {
        this(str, context, z);
        mServerUrl = str2;
    }

    public CodePush(String str, Context context, boolean z, int i) {
        this(str, context, z);
        mPublicKey = getPublicKeyByResourceDescriptor(i);
    }

    public CodePush(String str, Context context, boolean z, String str2, Integer num) {
        this(str, context, z);
        if (num != null) {
            mPublicKey = getPublicKeyByResourceDescriptor(num.intValue());
        }
        mServerUrl = str2;
    }

    private String getPublicKeyByResourceDescriptor(int i) {
        try {
            String string = this.mContext.getString(i);
            if (string.isEmpty()) {
                throw new CodePushInvalidPublicKeyException("Specified public key is empty");
            }
            return string;
        } catch (Resources.NotFoundException e) {
            throw new CodePushInvalidPublicKeyException("Unable to get public key, related resource descriptor " + i + " can not be found", e);
        }
    }

    private String getCustomPropertyFromStringsIfExist(String str) {
        int identifier = this.mContext.getResources().getIdentifier("CodePush" + str, CTVariableUtils.STRING, this.mContext.getPackageName());
        if (identifier == 0) {
            return null;
        }
        String string = this.mContext.getString(identifier);
        if (!string.isEmpty()) {
            return string;
        }
        CodePushUtils.log("Specified " + str + " is empty");
        return null;
    }

    private boolean isLiveReloadEnabled(ReactInstanceManager reactInstanceManager) throws SecurityException {
        DevSupportManager devSupportManager;
        if (reactInstanceManager != null && (devSupportManager = reactInstanceManager.getDevSupportManager()) != null) {
            DeveloperSettings devSettings = devSupportManager.getDevSettings();
            for (Method method : devSettings.getClass().getMethods()) {
                if (method.getName().equals("isReloadOnJSChangeEnabled")) {
                    try {
                        return ((Boolean) method.invoke(devSettings, new Object[0])).booleanValue();
                    } catch (Exception unused) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void clearDebugCacheIfNeeded(ReactInstanceManager reactInstanceManager) {
        if (this.mIsDebugMode && this.mSettingsManager.isPendingUpdate(null) && !isLiveReloadEnabled(reactInstanceManager)) {
            File file = new File(this.mContext.getFilesDir(), "ReactNativeDevBundle.js");
            if (file.exists()) {
                file.delete();
            }
        }
    }

    long getBinaryResourcesModifiedTime() {
        try {
            return Long.parseLong(this.mContext.getResources().getString(this.mContext.getResources().getIdentifier(CodePushConstants.CODE_PUSH_APK_BUILD_TIME_KEY, CTVariableUtils.STRING, this.mContext.getPackageName())).replaceAll("\"", ""));
        } catch (Exception e) {
            throw new CodePushUnknownException("Error in getting binary resources modified time", e);
        }
    }

    public String getPackageFolder() {
        JSONObject currentPackage = this.mUpdateManager.getCurrentPackage();
        if (currentPackage == null) {
            return null;
        }
        return this.mUpdateManager.getPackageFolderPath(currentPackage.optString("packageHash"));
    }

    @Deprecated
    public static String getBundleUrl() {
        return getJSBundleFile();
    }

    @Deprecated
    public static String getBundleUrl(String str) {
        return getJSBundleFile(str);
    }

    public static String getJSBundleFile() {
        return getJSBundleFile(CodePushConstants.DEFAULT_JS_BUNDLE_NAME);
    }

    public static String getJSBundleFile(String str) {
        CodePush codePush = mCurrentInstance;
        if (codePush == null) {
            throw new CodePushNotInitializedException("A CodePush instance has not been created yet. Have you added it to your app's list of ReactPackages?");
        }
        return codePush.getJSBundleFileInternal(str);
    }

    public String getJSBundleFileInternal(String str) {
        String currentPackageBundlePath;
        this.mAssetsBundleFileName = str;
        String str2 = CodePushConstants.ASSETS_BUNDLE_PREFIX + str;
        try {
            currentPackageBundlePath = this.mUpdateManager.getCurrentPackageBundlePath(this.mAssetsBundleFileName);
        } catch (CodePushMalformedDataException e) {
            CodePushUtils.log(e.getMessage());
            clearUpdates();
            currentPackageBundlePath = null;
        }
        if (currentPackageBundlePath == null) {
            CodePushUtils.logBundleUrl(str2);
            sIsRunningBinaryVersion = true;
            return str2;
        }
        JSONObject currentPackage = this.mUpdateManager.getCurrentPackage();
        if (isPackageBundleLatest(currentPackage)) {
            CodePushUtils.logBundleUrl(currentPackageBundlePath);
            sIsRunningBinaryVersion = false;
            return currentPackageBundlePath;
        }
        this.mDidUpdate = false;
        if (!this.mIsDebugMode || hasBinaryVersionChanged(currentPackage)) {
            clearUpdates();
        }
        CodePushUtils.logBundleUrl(str2);
        sIsRunningBinaryVersion = true;
        return str2;
    }

    void initializeUpdateAfterRestart() {
        this.mDidUpdate = false;
        JSONObject pendingUpdate = this.mSettingsManager.getPendingUpdate();
        if (pendingUpdate != null) {
            try {
                JSONObject currentPackage = this.mUpdateManager.getCurrentPackage();
                if (currentPackage == null || (!isPackageBundleLatest(currentPackage) && hasBinaryVersionChanged(currentPackage))) {
                    CodePushUtils.log("Skipping initializeUpdateAfterRestart(), binary version is newer");
                    return;
                }
                try {
                    if (pendingUpdate.getBoolean(CodePushConstants.PENDING_UPDATE_IS_LOADING_KEY)) {
                        CodePushUtils.log("Update did not finish loading the last time, rolling back to a previous version.");
                        sNeedToReportRollback = true;
                        rollbackPackage();
                    } else {
                        this.mDidUpdate = true;
                        this.mSettingsManager.savePendingUpdate(pendingUpdate.getString(CodePushConstants.PENDING_UPDATE_HASH_KEY), true);
                    }
                } catch (JSONException e) {
                    throw new CodePushUnknownException("Unable to read pending update metadata stored in SharedPreferences", e);
                }
            } catch (CodePushMalformedDataException e2) {
                CodePushUtils.log(e2);
                clearUpdates();
            }
        }
    }

    private boolean isPackageBundleLatest(JSONObject jSONObject) {
        try {
            String strOptString = jSONObject.optString(CodePushConstants.BINARY_MODIFIED_TIME_KEY, null);
            Long lValueOf = strOptString != null ? Long.valueOf(Long.parseLong(strOptString)) : null;
            String strOptString2 = jSONObject.optString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, null);
            long binaryResourcesModifiedTime = getBinaryResourcesModifiedTime();
            if (lValueOf != null && lValueOf.longValue() == binaryResourcesModifiedTime) {
                if (!isUsingTestConfiguration()) {
                    if (sAppVersion.equals(strOptString2)) {
                    }
                }
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            throw new CodePushUnknownException("Error in reading binary modified date from package metadata", e);
        }
    }

    private boolean hasBinaryVersionChanged(JSONObject jSONObject) {
        return !sAppVersion.equals(jSONObject.optString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, null));
    }

    private void rollbackPackage() throws Throwable {
        this.mSettingsManager.saveFailedUpdate(this.mUpdateManager.getCurrentPackage());
        this.mUpdateManager.rollbackPackage();
        this.mSettingsManager.removePendingUpdate();
    }

    public void clearUpdates() {
        this.mUpdateManager.clearUpdates();
        this.mSettingsManager.removePendingUpdate();
        this.mSettingsManager.removeFailedUpdates();
    }

    static ReactInstanceManager getReactInstanceManager() {
        ReactInstanceHolder reactInstanceHolder = mReactInstanceHolder;
        if (reactInstanceHolder == null) {
            return null;
        }
        return reactInstanceHolder.getReactInstanceManager();
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        CodePushNativeModule codePushNativeModule = new CodePushNativeModule(reactApplicationContext, this, this.mUpdateManager, this.mTelemetryManager, this.mSettingsManager);
        CodePushDialog codePushDialog = new CodePushDialog(reactApplicationContext);
        ArrayList arrayList = new ArrayList();
        arrayList.add(codePushNativeModule);
        arrayList.add(codePushDialog);
        return arrayList;
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return new ArrayList();
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList();
    }
}
