package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.oblador.keychain.KeychainModule;
import io.grpc.internal.GrpcUtil;

/* loaded from: classes2.dex */
public class ResumableUploadCancelRequest extends ResumableNetworkRequest {
    public static boolean cancelCalled = false;
    private final Uri uploadURL;

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return GrpcUtil.HTTP_METHOD;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    public Uri getURL() {
        return this.uploadURL;
    }

    public ResumableUploadCancelRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, Uri uri) {
        super(storageReferenceUri, firebaseApp);
        cancelCalled = true;
        this.uploadURL = uri;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", KeychainModule.AuthPromptOptions.CANCEL);
    }
}
