package com.clevertap.android.sdk.pushnotification.work;

import android.content.Context;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CTWorkManager.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\fH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/clevertap/android/sdk/pushnotification/work/CTWorkManager;", "", "context", "Landroid/content/Context;", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "(Landroid/content/Context;Lcom/clevertap/android/sdk/CleverTapInstanceConfig;)V", Constants.KEY_ACCOUNT_ID, "", "logger", "Lcom/clevertap/android/sdk/Logger;", Session.JsonKeys.INIT, "", "schedulePushImpressionsFlushWork", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CTWorkManager {
    private final String accountId;
    private final Context context;
    private final Logger logger;

    public CTWorkManager(Context context, CleverTapInstanceConfig config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.context = context;
        String accountId = config.getAccountId();
        Intrinsics.checkNotNullExpressionValue(accountId, "config.accountId");
        this.accountId = accountId;
        Logger logger = config.getLogger();
        Intrinsics.checkNotNullExpressionValue(logger, "config.logger");
        this.logger = logger;
    }

    private final void schedulePushImpressionsFlushWork() {
        this.logger.verbose(this.accountId, "scheduling one time work request to flush push impressions...");
        try {
            Constraints constraintsBuild = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresCharging(true).build();
            Intrinsics.checkNotNullExpressionValue(constraintsBuild, "Builder()\n              …\n                .build()");
            OneTimeWorkRequest oneTimeWorkRequestBuild = new OneTimeWorkRequest.Builder(CTFlushPushImpressionsWork.class).setConstraints(constraintsBuild).build();
            Intrinsics.checkNotNullExpressionValue(oneTimeWorkRequestBuild, "Builder(CTFlushPushImpre…\n                .build()");
            WorkManager.getInstance(this.context).enqueueUniqueWork(Constants.FLUSH_PUSH_IMPRESSIONS_ONE_TIME_WORKER_NAME, ExistingWorkPolicy.KEEP, oneTimeWorkRequestBuild);
            this.logger.verbose(this.accountId, "Finished scheduling one time work request to flush push impressions...");
        } catch (Throwable th) {
            this.logger.verbose(this.accountId, "Failed to schedule one time work request to flush push impressions.", th);
            th.printStackTrace();
        }
    }

    public final void init() {
        if (CTXtensions.isPackageAndOsTargetsAbove(this.context, 26)) {
            Context context = this.context;
            if (Utils.isMainProcess(context, context.getPackageName())) {
                schedulePushImpressionsFlushWork();
            }
        }
    }
}
