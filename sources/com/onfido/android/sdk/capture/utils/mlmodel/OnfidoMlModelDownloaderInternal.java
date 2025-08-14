package com.onfido.android.sdk.capture.utils.mlmodel;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.google.android.gms.actions.SearchIntents;
import com.onfido.android.sdk.capture.BuildConfig;
import io.sentry.protocol.Device;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016JO\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0018\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\u001aJ;\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u001cJ\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0002¨\u0006 "}, d2 = {"Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModelDownloaderInternal;", "Landroid/content/ContentProvider;", "()V", "delete", "", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "downloadModel", "", Device.JsonKeys.MODEL, "Lcom/onfido/android/sdk/capture/utils/mlmodel/OnfidoMlModels;", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", SearchIntents.EXTRA_QUERY, "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "getWorkManager", "Landroidx/work/WorkManager;", "Landroid/content/Context;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class OnfidoMlModelDownloaderInternal extends ContentProvider {
    private final void downloadModel(OnfidoMlModels model) throws Throwable {
        Context context = getContext();
        if (context == null) {
            return;
        }
        WorkManager workManager = getWorkManager(context);
        Constraints constraintsBuild = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        String strName = model.name();
        Pair[] pairArr = {TuplesKt.to(OnfidoMlModelDownloaderWorker.MODEL_ASSET_NAME_KEY, model.getAssetName()), TuplesKt.to(OnfidoMlModelDownloaderWorker.MODEL_ASSET_URL_KEY, BuildConfig.ONFIDO_ASSETS_URL)};
        Data.Builder builder = new Data.Builder();
        for (int i = 0; i < 2; i++) {
            Pair pair = pairArr[i];
            builder.put((String) pair.getFirst(), pair.getSecond());
        }
        Data dataBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(dataBuild, "dataBuilder.build()");
        workManager.enqueueUniqueWork(strName, ExistingWorkPolicy.KEEP, new OneTimeWorkRequest.Builder(OnfidoMlModelDownloaderWorker.class).setInputData(dataBuild).setConstraints(constraintsBuild).build());
    }

    private final WorkManager getWorkManager(Context context) {
        if (!WorkManager.isInitialized()) {
            WorkManager.initialize(context, new Configuration.Builder().setMinimumLoggingLevel(6).build());
        }
        WorkManager workManager = WorkManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(workManager, "getInstance(...)");
        return workManager;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() throws Throwable {
        Iterator<OnfidoMlModels> it = OnfidoMlModels.getEntries().iterator();
        while (it.hasNext()) {
            downloadModel(it.next());
        }
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) throws Throwable {
        OnfidoMlModels next;
        Intrinsics.checkNotNullParameter(uri, "uri");
        Iterator<OnfidoMlModels> it = OnfidoMlModels.getEntries().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(next.name(), selection)) {
                break;
            }
        }
        OnfidoMlModels onfidoMlModels = next;
        if (onfidoMlModels != null) {
            downloadModel(onfidoMlModels);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }
}
