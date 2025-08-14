package com.microsoft.codepush.react;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.protocol.SentryStackFrame;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CodePushTelemetryManager {
    private final String APP_VERSION_KEY = RemoteConfigConstants.RequestFieldKey.APP_VERSION;
    private final String DEPLOYMENT_FAILED_STATUS = "DeploymentFailed";
    private final String DEPLOYMENT_KEY_KEY = "deploymentKey";
    private final String DEPLOYMENT_SUCCEEDED_STATUS = "DeploymentSucceeded";
    private final String LABEL_KEY = "label";
    private final String LAST_DEPLOYMENT_REPORT_KEY = "CODE_PUSH_LAST_DEPLOYMENT_REPORT";
    private final String PACKAGE_KEY = SentryStackFrame.JsonKeys.PACKAGE;
    private final String PREVIOUS_DEPLOYMENT_KEY_KEY = "previousDeploymentKey";
    private final String PREVIOUS_LABEL_OR_APP_VERSION_KEY = "previousLabelOrAppVersion";
    private final String RETRY_DEPLOYMENT_REPORT_KEY = "CODE_PUSH_RETRY_DEPLOYMENT_REPORT";
    private final String STATUS_KEY = "status";
    private SharedPreferences mSettings;

    public CodePushTelemetryManager(Context context) {
        this.mSettings = context.getSharedPreferences("CodePush", 0);
    }

    public WritableMap getBinaryUpdateReport(String str) {
        String previousStatusReportIdentifier = getPreviousStatusReportIdentifier();
        if (previousStatusReportIdentifier == null) {
            clearRetryStatusReport();
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str);
            return writableMapCreateMap;
        }
        if (previousStatusReportIdentifier.equals(str)) {
            return null;
        }
        clearRetryStatusReport();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        if (isStatusReportIdentifierCodePushLabel(previousStatusReportIdentifier)) {
            String deploymentKeyFromStatusReportIdentifier = getDeploymentKeyFromStatusReportIdentifier(previousStatusReportIdentifier);
            String versionLabelFromStatusReportIdentifier = getVersionLabelFromStatusReportIdentifier(previousStatusReportIdentifier);
            writableMapCreateMap2.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str);
            writableMapCreateMap2.putString("previousDeploymentKey", deploymentKeyFromStatusReportIdentifier);
            writableMapCreateMap2.putString("previousLabelOrAppVersion", versionLabelFromStatusReportIdentifier);
        } else {
            writableMapCreateMap2.putString(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str);
            writableMapCreateMap2.putString("previousLabelOrAppVersion", previousStatusReportIdentifier);
        }
        return writableMapCreateMap2;
    }

    public WritableMap getRetryStatusReport() {
        String string = this.mSettings.getString("CODE_PUSH_RETRY_DEPLOYMENT_REPORT", null);
        if (string != null) {
            clearRetryStatusReport();
            try {
                return CodePushUtils.convertJsonObjectToWritable(new JSONObject(string));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public WritableMap getRollbackReport(WritableMap writableMap) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putMap(SentryStackFrame.JsonKeys.PACKAGE, writableMap);
        writableMapCreateMap.putString("status", "DeploymentFailed");
        return writableMapCreateMap;
    }

    public WritableMap getUpdateReport(WritableMap writableMap) {
        String packageStatusReportIdentifier = getPackageStatusReportIdentifier(writableMap);
        String previousStatusReportIdentifier = getPreviousStatusReportIdentifier();
        if (packageStatusReportIdentifier != null) {
            if (previousStatusReportIdentifier == null) {
                clearRetryStatusReport();
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putMap(SentryStackFrame.JsonKeys.PACKAGE, writableMap);
                writableMapCreateMap.putString("status", "DeploymentSucceeded");
                return writableMapCreateMap;
            }
            if (!previousStatusReportIdentifier.equals(packageStatusReportIdentifier)) {
                clearRetryStatusReport();
                WritableMap writableMapCreateMap2 = Arguments.createMap();
                if (isStatusReportIdentifierCodePushLabel(previousStatusReportIdentifier)) {
                    String deploymentKeyFromStatusReportIdentifier = getDeploymentKeyFromStatusReportIdentifier(previousStatusReportIdentifier);
                    String versionLabelFromStatusReportIdentifier = getVersionLabelFromStatusReportIdentifier(previousStatusReportIdentifier);
                    writableMapCreateMap2.putMap(SentryStackFrame.JsonKeys.PACKAGE, writableMap);
                    writableMapCreateMap2.putString("status", "DeploymentSucceeded");
                    writableMapCreateMap2.putString("previousDeploymentKey", deploymentKeyFromStatusReportIdentifier);
                    writableMapCreateMap2.putString("previousLabelOrAppVersion", versionLabelFromStatusReportIdentifier);
                    return writableMapCreateMap2;
                }
                writableMapCreateMap2.putMap(SentryStackFrame.JsonKeys.PACKAGE, writableMap);
                writableMapCreateMap2.putString("status", "DeploymentSucceeded");
                writableMapCreateMap2.putString("previousLabelOrAppVersion", previousStatusReportIdentifier);
                return writableMapCreateMap2;
            }
        }
        return null;
    }

    public void recordStatusReported(ReadableMap readableMap) {
        if (readableMap.hasKey("status") && "DeploymentFailed".equals(readableMap.getString("status"))) {
            return;
        }
        if (readableMap.hasKey(RemoteConfigConstants.RequestFieldKey.APP_VERSION)) {
            saveStatusReportedForIdentifier(readableMap.getString(RemoteConfigConstants.RequestFieldKey.APP_VERSION));
        } else if (readableMap.hasKey(SentryStackFrame.JsonKeys.PACKAGE)) {
            saveStatusReportedForIdentifier(getPackageStatusReportIdentifier(readableMap.getMap(SentryStackFrame.JsonKeys.PACKAGE)));
        }
    }

    public void saveStatusReportForRetry(ReadableMap readableMap) {
        this.mSettings.edit().putString("CODE_PUSH_RETRY_DEPLOYMENT_REPORT", CodePushUtils.convertReadableToJsonObject(readableMap).toString()).commit();
    }

    private void clearRetryStatusReport() {
        this.mSettings.edit().remove("CODE_PUSH_RETRY_DEPLOYMENT_REPORT").commit();
    }

    private String getDeploymentKeyFromStatusReportIdentifier(String str) {
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length > 0) {
            return strArrSplit[0];
        }
        return null;
    }

    private String getPackageStatusReportIdentifier(ReadableMap readableMap) {
        String strTryGetString = CodePushUtils.tryGetString(readableMap, "deploymentKey");
        String strTryGetString2 = CodePushUtils.tryGetString(readableMap, "label");
        if (strTryGetString == null || strTryGetString2 == null) {
            return null;
        }
        return strTryGetString + ":" + strTryGetString2;
    }

    private String getPreviousStatusReportIdentifier() {
        return this.mSettings.getString("CODE_PUSH_LAST_DEPLOYMENT_REPORT", null);
    }

    private String getVersionLabelFromStatusReportIdentifier(String str) {
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length > 1) {
            return strArrSplit[1];
        }
        return null;
    }

    private boolean isStatusReportIdentifierCodePushLabel(String str) {
        return str != null && str.contains(":");
    }

    private void saveStatusReportedForIdentifier(String str) {
        this.mSettings.edit().putString("CODE_PUSH_LAST_DEPLOYMENT_REPORT", str).commit();
    }
}
