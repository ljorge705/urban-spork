package com.google.mlkit.common.sdkinternal.model;

import com.google.mlkit.common.MlKitException;
import java.io.File;

/* compiled from: com.google.mlkit:common@@18.5.0 */
/* loaded from: classes2.dex */
public interface RemoteModelFileMover {
    File getModelFileDestination() throws MlKitException;

    File moveAllFilesFromPrivateTempToPrivateDestination(File file) throws MlKitException;
}
