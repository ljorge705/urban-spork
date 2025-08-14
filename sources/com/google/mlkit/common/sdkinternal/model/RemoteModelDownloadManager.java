package com.google.mlkit.common.sdkinternal.model;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.LongSparseArray;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zziy;
import com.google.android.gms.internal.mlkit_common.zzje;
import com.google.android.gms.internal.mlkit_common.zzmh;
import com.google.android.gms.internal.mlkit_common.zzmq;
import com.google.android.gms.internal.mlkit_common.zzmt;
import com.google.android.gms.internal.mlkit_common.zznb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes2.dex */
public class RemoteModelDownloadManager {
    private static final GmsLogger zza = new GmsLogger("ModelDownloadManager", "");
    private static final Map zzb = new HashMap();
    private final LongSparseArray zzc = new LongSparseArray();
    private final LongSparseArray zzd = new LongSparseArray();
    private final MlKitContext zze;
    private final DownloadManager zzf;
    private final RemoteModel zzg;
    private final ModelType zzh;
    private final zzmq zzi;
    private final SharedPrefManager zzj;
    private final ModelFileHelper zzk;
    private final ModelInfoRetrieverInterop zzl;
    private final RemoteModelFileManager zzm;
    private DownloadConditions zzn;

    RemoteModelDownloadManager(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop modelInfoRetrieverInterop, zzmq zzmqVar) {
        this.zze = mlKitContext;
        this.zzh = remoteModel.getModelType();
        this.zzg = remoteModel;
        DownloadManager downloadManager = (DownloadManager) mlKitContext.getApplicationContext().getSystemService("download");
        this.zzf = downloadManager;
        this.zzi = zzmqVar;
        if (downloadManager == null) {
            zza.d("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.zzk = modelFileHelper;
        this.zzj = SharedPrefManager.getInstance(mlKitContext);
        this.zzl = modelInfoRetrieverInterop;
        this.zzm = remoteModelFileManager;
    }

    public static synchronized RemoteModelDownloadManager getInstance(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop modelInfoRetrieverInterop) {
        Map map;
        map = zzb;
        if (!map.containsKey(remoteModel)) {
            map.put(remoteModel, new RemoteModelDownloadManager(mlKitContext, remoteModel, modelFileHelper, remoteModelFileManager, modelInfoRetrieverInterop, zznb.zzb("common")));
        }
        return (RemoteModelDownloadManager) map.get(remoteModel);
    }

    private final Task zzj(long j) {
        this.zze.getApplicationContext().registerReceiver(zzm(j), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), null, MLTaskExecutor.getInstance().getHandler());
        return zzk(j).getTask();
    }

    private final synchronized TaskCompletionSource zzk(long j) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zzd.get(j);
        if (taskCompletionSource != null) {
            return taskCompletionSource;
        }
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        this.zzd.put(j, taskCompletionSource2);
        return taskCompletionSource2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MlKitException zzl(Long l) {
        DownloadManager downloadManager = this.zzf;
        Cursor cursorQuery = null;
        if (downloadManager != null && l != null) {
            cursorQuery = downloadManager.query(new DownloadManager.Query().setFilterById(l.longValue()));
        }
        int i = 13;
        String str = "Model downloading failed";
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("reason"));
            if (i2 == 1006) {
                str = "Model downloading failed due to insufficient space on the device.";
                i = 101;
            } else {
                str = "Model downloading failed due to error code: " + i2 + " from Android DownloadManager";
            }
        }
        return new MlKitException(str, i);
    }

    private final synchronized zzd zzm(long j) {
        zzd zzdVar = (zzd) this.zzc.get(j);
        if (zzdVar != null) {
            return zzdVar;
        }
        zzd zzdVar2 = new zzd(this, j, zzk(j), null);
        this.zzc.put(j, zzdVar2);
        return zzdVar2;
    }

    private final synchronized Long zzn(DownloadManager.Request request, ModelInfo modelInfo) {
        DownloadManager downloadManager = this.zzf;
        if (downloadManager == null) {
            return null;
        }
        long jEnqueue = downloadManager.enqueue(request);
        zza.d("ModelDownloadManager", "Schedule a new downloading task: " + jEnqueue);
        this.zzj.setDownloadingModelInfo(jEnqueue, modelInfo);
        this.zzi.zzf(zzmt.zzg(), this.zzg, zziy.NO_ERROR, false, modelInfo.getModelType(), zzje.SCHEDULED);
        return Long.valueOf(jEnqueue);
    }

    private final synchronized Long zzo(ModelInfo modelInfo, DownloadConditions downloadConditions) throws MlKitException {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        String downloadingModelHash = this.zzj.getDownloadingModelHash(this.zzg);
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        if (downloadingModelHash != null && downloadingModelHash.equals(modelInfo.getModelHash()) && downloadingModelStatusCode != null) {
            Integer downloadingModelStatusCode2 = getDownloadingModelStatusCode();
            if (downloadingModelStatusCode2 == null || (downloadingModelStatusCode2.intValue() != 8 && downloadingModelStatusCode2.intValue() != 16)) {
                this.zzi.zzf(zzmt.zzg(), this.zzg, zziy.NO_ERROR, false, this.zzg.getModelType(), zzje.DOWNLOADING);
            }
            zza.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
            return null;
        }
        GmsLogger gmsLogger = zza;
        gmsLogger.d("ModelDownloadManager", "Need to download a new model.");
        removeOrCancelDownload();
        DownloadManager.Request request = new DownloadManager.Request(modelInfo.getModelUri());
        if (this.zzk.modelExistsLocally(modelInfo.getModelNameForPersist(), modelInfo.getModelType())) {
            gmsLogger.d("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
            this.zzi.zzf(zzmt.zzg(), this.zzg, zziy.NO_ERROR, false, modelInfo.getModelType(), zzje.UPDATE_AVAILABLE);
        }
        request.setRequiresCharging(downloadConditions.isChargingRequired());
        if (downloadConditions.isWifiRequired()) {
            request.setAllowedNetworkTypes(2);
        }
        return zzn(request, modelInfo);
    }

    public Task<Void> ensureModelDownloaded() {
        MlKitException mlKitException;
        ModelInfo modelInfoZzg;
        this.zzi.zzf(zzmt.zzg(), this.zzg, zziy.NO_ERROR, false, ModelType.UNKNOWN, zzje.EXPLICITLY_REQUESTED);
        Long lZzo = null;
        try {
            modelInfoZzg = zzg();
            mlKitException = null;
        } catch (MlKitException e) {
            mlKitException = e;
            modelInfoZzg = null;
        }
        try {
            Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
            Long downloadingId = getDownloadingId();
            if (!modelExistsLocally() && (downloadingModelStatusCode == null || downloadingModelStatusCode.intValue() != 8)) {
                if (downloadingModelStatusCode != null && downloadingModelStatusCode.intValue() == 16) {
                    MlKitException mlKitExceptionZzl = zzl(downloadingId);
                    removeOrCancelDownload();
                    return Tasks.forException(mlKitExceptionZzl);
                }
                if (downloadingModelStatusCode == null || (!(downloadingModelStatusCode.intValue() == 4 || downloadingModelStatusCode.intValue() == 2 || downloadingModelStatusCode.intValue() == 1) || downloadingId == null || getDownloadingModelHash() == null)) {
                    if (modelInfoZzg != null) {
                        lZzo = zzo(modelInfoZzg, this.zzn);
                    }
                    return lZzo == null ? Tasks.forException(new MlKitException("Failed to schedule the download task", 13, mlKitException)) : zzj(lZzo.longValue());
                }
                zzmq zzmqVar = this.zzi;
                zzmh zzmhVarZzg = zzmt.zzg();
                RemoteModel remoteModel = this.zzg;
                zzmqVar.zzf(zzmhVarZzg, remoteModel, zziy.NO_ERROR, false, remoteModel.getModelType(), zzje.DOWNLOADING);
                return zzj(downloadingId.longValue());
            }
            if (modelInfoZzg != null) {
                Long lZzo2 = zzo(modelInfoZzg, this.zzn);
                if (lZzo2 != null) {
                    return zzj(lZzo2.longValue());
                }
                zza.i("ModelDownloadManager", "Didn't schedule download for the updated model");
            }
            return Tasks.forResult(null);
        } catch (MlKitException e2) {
            return Tasks.forException(new MlKitException("Failed to ensure the model is downloaded.", 13, e2));
        }
    }

    public synchronized ParcelFileDescriptor getDownloadedFile() {
        Long downloadingId = getDownloadingId();
        DownloadManager downloadManager = this.zzf;
        ParcelFileDescriptor parcelFileDescriptorOpenDownloadedFile = null;
        if (downloadManager == null || downloadingId == null) {
            return null;
        }
        try {
            parcelFileDescriptorOpenDownloadedFile = downloadManager.openDownloadedFile(downloadingId.longValue());
        } catch (FileNotFoundException unused) {
            zza.e("ModelDownloadManager", "Downloaded file is not found");
        }
        return parcelFileDescriptorOpenDownloadedFile;
    }

    public synchronized Long getDownloadingId() {
        return this.zzj.getDownloadingModelId(this.zzg);
    }

    public synchronized String getDownloadingModelHash() {
        return this.zzj.getDownloadingModelHash(this.zzg);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.lang.Integer getDownloadingModelStatusCode() {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.Long r0 = r8.getDownloadingId()     // Catch: java.lang.Throwable -> L90
            android.app.DownloadManager r1 = r8.zzf     // Catch: java.lang.Throwable -> L90
            r2 = 0
            if (r1 == 0) goto L8e
            if (r0 != 0) goto Le
            goto L8e
        Le:
            android.app.DownloadManager$Query r3 = new android.app.DownloadManager$Query     // Catch: java.lang.Throwable -> L90
            r3.<init>()     // Catch: java.lang.Throwable -> L90
            r4 = 1
            long[] r5 = new long[r4]     // Catch: java.lang.Throwable -> L90
            long r6 = r0.longValue()     // Catch: java.lang.Throwable -> L90
            r0 = 0
            r5[r0] = r6     // Catch: java.lang.Throwable -> L90
            android.app.DownloadManager$Query r3 = r3.setFilterById(r5)     // Catch: java.lang.Throwable -> L90
            android.database.Cursor r1 = r1.query(r3)     // Catch: java.lang.Throwable -> L90
            if (r1 == 0) goto L3e
            boolean r3 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L3c
            if (r3 == 0) goto L3e
            java.lang.String r3 = "status"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L3c
            int r3 = r1.getInt(r3)     // Catch: java.lang.Throwable -> L3c
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L3c
            goto L3f
        L3c:
            r2 = move-exception
            goto L73
        L3e:
            r3 = r2
        L3f:
            if (r3 != 0) goto L48
            if (r1 == 0) goto L46
            r1.close()     // Catch: java.lang.Throwable -> L90
        L46:
            monitor-exit(r8)
            return r2
        L48:
            int r5 = r3.intValue()     // Catch: java.lang.Throwable -> L3c
            r6 = 2
            if (r5 == r6) goto L6d
            int r5 = r3.intValue()     // Catch: java.lang.Throwable -> L3c
            r6 = 4
            if (r5 == r6) goto L6d
            int r5 = r3.intValue()     // Catch: java.lang.Throwable -> L3c
            if (r5 == r4) goto L6d
            int r5 = r3.intValue()     // Catch: java.lang.Throwable -> L3c
            r6 = 8
            if (r5 == r6) goto L6d
            int r0 = r3.intValue()     // Catch: java.lang.Throwable -> L3c
            r4 = 16
            if (r0 == r4) goto L6d
            goto L6e
        L6d:
            r2 = r3
        L6e:
            r1.close()     // Catch: java.lang.Throwable -> L90
            monitor-exit(r8)
            return r2
        L73:
            r1.close()     // Catch: java.lang.Throwable -> L77
            goto L8d
        L77:
            r1 = move-exception
            java.lang.Class[] r3 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> L90
            java.lang.Class<java.lang.Throwable> r4 = java.lang.Throwable.class
            r3[r0] = r4     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> L90
            java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
            java.lang.String r4 = "addSuppressed"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r4, r3)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> L90
            java.lang.Object[] r1 = new java.lang.Object[]{r1}     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> L90
            r0.invoke(r2, r1)     // Catch: java.lang.Exception -> L8d java.lang.Throwable -> L90
        L8d:
            throw r2     // Catch: java.lang.Throwable -> L90
        L8e:
            monitor-exit(r8)
            return r2
        L90:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.getDownloadingModelStatusCode():java.lang.Integer");
    }

    public int getFailureReason(Long l) {
        int columnIndex;
        DownloadManager downloadManager = this.zzf;
        Cursor cursorQuery = null;
        if (downloadManager != null && l != null) {
            cursorQuery = downloadManager.query(new DownloadManager.Query().setFilterById(l.longValue()));
        }
        if (cursorQuery == null || !cursorQuery.moveToFirst() || (columnIndex = cursorQuery.getColumnIndex("reason")) == -1) {
            return 0;
        }
        return cursorQuery.getInt(columnIndex);
    }

    public boolean isModelDownloadedAndValid() throws MlKitException {
        try {
            if (modelExistsLocally()) {
                return true;
            }
        } catch (MlKitException unused) {
            zza.d("ModelDownloadManager", "Failed to check if the model exist locally.");
        }
        Long downloadingId = getDownloadingId();
        String downloadingModelHash = getDownloadingModelHash();
        if (downloadingId == null || downloadingModelHash == null) {
            zza.d("ModelDownloadManager", "No new model is downloading.");
            removeOrCancelDownload();
            return false;
        }
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        GmsLogger gmsLogger = zza;
        new StringBuilder("Download Status code: ").append(downloadingModelStatusCode);
        gmsLogger.d("ModelDownloadManager", "Download Status code: ".concat(String.valueOf(downloadingModelStatusCode)));
        if (downloadingModelStatusCode != null) {
            return Objects.equal(downloadingModelStatusCode, 8) && zzi(downloadingModelHash) != null;
        }
        removeOrCancelDownload();
        return false;
    }

    public boolean modelExistsLocally() throws MlKitException {
        return this.zzk.modelExistsLocally(this.zzg.getUniqueModelNameForPersist(), this.zzh);
    }

    public synchronized void removeOrCancelDownload() throws MlKitException {
        Long downloadingId = getDownloadingId();
        if (this.zzf != null && downloadingId != null) {
            GmsLogger gmsLogger = zza;
            new StringBuilder("Cancel or remove existing downloading task: ").append(downloadingId);
            gmsLogger.d("ModelDownloadManager", "Cancel or remove existing downloading task: ".concat(downloadingId.toString()));
            if (this.zzf.remove(downloadingId.longValue()) > 0 || getDownloadingModelStatusCode() == null) {
                this.zzk.deleteTempFilesInPrivateFolder(this.zzg.getUniqueModelNameForPersist(), this.zzg.getModelType());
                this.zzj.clearDownloadingModelInfo(this.zzg);
            }
        }
    }

    public void setDownloadConditions(DownloadConditions downloadConditions) {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        this.zzn = downloadConditions;
    }

    public synchronized void updateLatestModelHashAndType(String str) throws MlKitException {
        this.zzj.setLatestModelHash(this.zzg, str);
        removeOrCancelDownload();
    }

    final synchronized ModelInfo zzg() throws MlKitException {
        boolean z;
        boolean zModelExistsLocally = modelExistsLocally();
        if (zModelExistsLocally) {
            this.zzi.zzf(zzmt.zzg(), this.zzg, zziy.NO_ERROR, false, this.zzg.getModelType(), zzje.LIVE);
        }
        ModelInfoRetrieverInterop modelInfoRetrieverInterop = this.zzl;
        if (modelInfoRetrieverInterop == null) {
            throw new MlKitException("Please include com.google.mlkit:linkfirebase sdk as your dependency when you try to download from Firebase.", 14);
        }
        ModelInfo modelInfoRetrieveRemoteModelInfo = modelInfoRetrieverInterop.retrieveRemoteModelInfo(this.zzg);
        if (modelInfoRetrieveRemoteModelInfo == null) {
            return null;
        }
        MlKitContext mlKitContext = this.zze;
        RemoteModel remoteModel = this.zzg;
        String modelHash = modelInfoRetrieveRemoteModelInfo.getModelHash();
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(mlKitContext);
        boolean zEquals = modelHash.equals(sharedPrefManager.getIncompatibleModelHash(remoteModel));
        boolean z2 = false;
        if (zEquals && CommonUtils.getAppVersion(mlKitContext.getApplicationContext()).equals(sharedPrefManager.getPreviousAppVersion())) {
            zza.e("ModelDownloadManager", "The model is incompatible with TFLite and the app is not upgraded, do not download");
            z = false;
        } else {
            z = true;
        }
        if (!zModelExistsLocally) {
            this.zzj.clearLatestModelHash(this.zzg);
        }
        boolean z3 = !modelInfoRetrieveRemoteModelInfo.getModelHash().equals(SharedPrefManager.getInstance(this.zze).getLatestModelHash(this.zzg));
        if (!z) {
            z2 = z3;
        } else if (!zModelExistsLocally || z3) {
            return modelInfoRetrieveRemoteModelInfo;
        }
        if (zModelExistsLocally && (z2 ^ z)) {
            return null;
        }
        throw new MlKitException("The model " + this.zzg.getModelName() + " is incompatible with TFLite runtime", 100);
    }

    public final File zzi(String str) throws MlKitException {
        GmsLogger gmsLogger = zza;
        gmsLogger.d("ModelDownloadManager", "Model downloaded successfully");
        this.zzi.zzf(zzmt.zzg(), this.zzg, zziy.NO_ERROR, true, this.zzh, zzje.SUCCEEDED);
        ParcelFileDescriptor downloadedFile = getDownloadedFile();
        if (downloadedFile == null) {
            removeOrCancelDownload();
            return null;
        }
        gmsLogger.d("ModelDownloadManager", "moving downloaded model from external storage to private folder.");
        try {
            return this.zzm.moveModelToPrivateFolder(downloadedFile, str, this.zzg);
        } finally {
            removeOrCancelDownload();
        }
    }
}
