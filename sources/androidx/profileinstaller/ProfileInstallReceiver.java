package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.profileinstaller.ProfileInstaller;

/* loaded from: classes5.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {
    public static final String ACTION_INSTALL_PROFILE = "androidx.profileinstaller.action.INSTALL_PROFILE";
    public static final String ACTION_SKIP_FILE = "androidx.profileinstaller.action.SKIP_FILE";
    private static final String EXTRA_SKIP_FILE_OPERATION = "EXTRA_SKIP_FILE_OPERATION";
    private static final String EXTRA_SKIP_FILE_OPERATION_DELETE = "DELETE_SKIP_FILE";
    private static final String EXTRA_SKIP_FILE_OPERATION_WRITE = "WRITE_SKIP_FILE";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws PackageManager.NameNotFoundException {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (ACTION_INSTALL_PROFILE.equals(action)) {
            ProfileInstaller.writeProfile(context, new ProfileInstallReceiver$$ExternalSyntheticLambda0(), new ResultDiagnostics(), true);
            return;
        }
        if (ACTION_SKIP_FILE.equals(action)) {
            String string = intent.getExtras().getString(EXTRA_SKIP_FILE_OPERATION);
            if (EXTRA_SKIP_FILE_OPERATION_WRITE.equals(string)) {
                ProfileInstaller.writeSkipFile(context, new ProfileInstallReceiver$$ExternalSyntheticLambda0(), new ResultDiagnostics());
            } else if (EXTRA_SKIP_FILE_OPERATION_DELETE.equals(string)) {
                ProfileInstaller.deleteSkipFile(context, new ProfileInstallReceiver$$ExternalSyntheticLambda0(), new ResultDiagnostics());
            }
        }
    }

    class ResultDiagnostics implements ProfileInstaller.DiagnosticsCallback {
        ResultDiagnostics() {
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onDiagnosticReceived(int i, Object obj) {
            ProfileInstaller.LOG_DIAGNOSTICS.onDiagnosticReceived(i, obj);
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public void onResultReceived(int i, Object obj) {
            ProfileInstaller.LOG_DIAGNOSTICS.onResultReceived(i, obj);
            ProfileInstallReceiver.this.setResultCode(i);
        }
    }
}
