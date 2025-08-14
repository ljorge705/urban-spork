package com.singular.sdk.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.licensing.ILicenseResultListener;
import com.android.vending.licensing.ILicensingService;
import com.singular.sdk.internal.LicenseApiHelper;
import java.security.SecureRandom;

/* loaded from: classes6.dex */
public class LicenseChecker implements ServiceConnection {
    private static final int ERROR_RESPONSE_CODE = -1;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final SingularLog logger = SingularLog.getLogger("LicenseApiHelper");
    private final LicenseApiHelper.LicenseResultHandler handler;
    private final Context mContext;
    private Handler mHandler;
    private final String mPackageName;
    private ILicensingService mService;

    public LicenseChecker(Context context, LicenseApiHelper.LicenseResultHandler licenseResultHandler) {
        HandlerThread handlerThread = new HandlerThread("license_checker");
        handlerThread.start();
        this.mContext = context;
        this.mPackageName = context.getPackageName();
        this.mHandler = new Handler(handlerThread.getLooper());
        this.handler = licenseResultHandler;
    }

    public synchronized void checkAccess() {
        ILicensingService iLicensingService = this.mService;
        if (iLicensingService == null) {
            SingularLog singularLog = logger;
            singularLog.info("Binding to licensing service.");
            try {
                if (!this.mContext.bindService(new Intent("com.android.vending.licensing.ILicensingService").setPackage("com.android.vending"), this, 1)) {
                    singularLog.error("Could not bind to service.");
                    this.handler.handle(-1, "Binding failed", "");
                }
            } catch (SecurityException e) {
                logger.error("SecurityException", e);
                this.handler.handle(-1, String.format("Exception: %s, Message: %s", e.toString(), e.getMessage()), "");
            }
            logger.info("Binding done.");
        } else {
            try {
                iLicensingService.checkLicense(generateNonce(), this.mPackageName, new ResultListener());
            } catch (RemoteException e2) {
                logger.error("RemoteException in checkLicense call.", e2);
                this.handler.handle(-1, String.format("Exception: %s, Message: %s", e2.toString(), e2.getMessage()), "");
            }
        }
    }

    public synchronized void onDestroy() {
        cleanupService();
        this.mHandler.getLooper().quit();
    }

    @Override // android.content.ServiceConnection
    public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        SingularLog singularLog = logger;
        singularLog.info("onServiceConnected.");
        ILicensingService iLicensingServiceAsInterface = ILicensingService.Stub.asInterface(iBinder);
        this.mService = iLicensingServiceAsInterface;
        try {
            iLicensingServiceAsInterface.checkLicense(generateNonce(), this.mPackageName, new ResultListener());
            singularLog.info("checkLicense call done.");
        } catch (RemoteException e) {
            logger.error("RemoteException in checkLicense call.", e);
            this.handler.handle(-1, e.toString(), "");
        }
    }

    @Override // android.content.ServiceConnection
    public synchronized void onServiceDisconnected(ComponentName componentName) {
        logger.info("Service unexpectedly disconnected.");
        this.mService = null;
    }

    private void cleanupService() {
        if (this.mService != null) {
            try {
                this.mContext.unbindService(this);
            } catch (IllegalArgumentException unused) {
                logger.error("Unable to unbind from licensing service (already unbound)");
            }
            this.mService = null;
        }
    }

    private class ResultListener extends ILicenseResultListener.Stub {
        private ResultListener() {
        }

        @Override // com.android.vending.licensing.ILicenseResultListener
        public void verifyLicense(int i, String str, String str2) {
            LicenseChecker.this.handler.handle(i, str, str2);
        }
    }

    private int generateNonce() {
        return RANDOM.nextInt();
    }
}
