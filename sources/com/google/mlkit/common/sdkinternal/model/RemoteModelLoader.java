package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzmq;
import com.google.android.gms.internal.mlkit_common.zznb;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes2.dex */
public class RemoteModelLoader {
    private static final GmsLogger zza = new GmsLogger("RemoteModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final RemoteModel zzd;
    private final RemoteModelDownloadManager zze;
    private final RemoteModelFileManager zzf;
    private final RemoteModelLoaderHelper zzg;
    private final zzmq zzh;
    private boolean zzi;

    private RemoteModelLoader(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator modelValidator, RemoteModelLoaderHelper remoteModelLoaderHelper, RemoteModelFileMover remoteModelFileMover) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, remoteModel, modelValidator, new ModelFileHelper(mlKitContext), remoteModelFileMover);
        this.zzf = remoteModelFileManager;
        this.zzi = true;
        this.zze = RemoteModelDownloadManager.getInstance(mlKitContext, remoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
        this.zzg = remoteModelLoaderHelper;
        this.zzc = mlKitContext;
        this.zzd = remoteModel;
        this.zzh = zznb.zzb("common");
    }

    public static synchronized RemoteModelLoader getInstance(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator modelValidator, RemoteModelLoaderHelper remoteModelLoaderHelper, RemoteModelFileMover remoteModelFileMover) {
        String uniqueModelNameForPersist;
        Map map;
        uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        map = zzb;
        if (!map.containsKey(uniqueModelNameForPersist)) {
            map.put(uniqueModelNameForPersist, new RemoteModelLoader(mlKitContext, remoteModel, modelValidator, remoteModelLoaderHelper, remoteModelFileMover));
        }
        return (RemoteModelLoader) map.get(uniqueModelNameForPersist);
    }

    private final MappedByteBuffer zza(String str) throws MlKitException {
        return this.zzg.loadModelAtPath(str);
    }

    private final MappedByteBuffer zzb(File file) throws MlKitException {
        try {
            return zza(file.getAbsolutePath());
        } catch (Exception e) {
            this.zzf.zzc(file);
            throw new MlKitException("Failed to load newly downloaded model.", 14, e);
        }
    }

    public RemoteModel getRemoteModel() {
        return this.zzd;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00bf A[Catch: all -> 0x0100, TryCatch #0 {, blocks: (B:4:0x0003, B:8:0x0022, B:10:0x002a, B:27:0x00bf, B:29:0x00ce, B:31:0x00d6, B:34:0x00dc, B:35:0x00fa, B:36:0x00fb, B:11:0x0031, B:13:0x0050, B:16:0x0059, B:18:0x0077, B:20:0x007f, B:21:0x0091, B:23:0x0099, B:24:0x00b0), top: B:43:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00fb A[Catch: all -> 0x0100, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:8:0x0022, B:10:0x002a, B:27:0x00bf, B:29:0x00ce, B:31:0x00d6, B:34:0x00dc, B:35:0x00fa, B:36:0x00fb, B:11:0x0031, B:13:0x0050, B:16:0x0059, B:18:0x0077, B:20:0x007f, B:21:0x0091, B:23:0x0099, B:24:0x00b0), top: B:43:0x0003, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized java.nio.MappedByteBuffer load() throws com.google.mlkit.common.MlKitException {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelLoader.load():java.nio.MappedByteBuffer");
    }
}
