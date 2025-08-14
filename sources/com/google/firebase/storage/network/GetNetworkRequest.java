package com.google.firebase.storage.network;

import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes2.dex */
public class GetNetworkRequest extends NetworkRequest {
    private static final String TAG = "GetNetworkRequest";

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "GET";
    }

    public GetNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, long j) {
        super(storageReferenceUri, firebaseApp);
        if (j != 0) {
            super.setCustomHeader(HttpHeaders.RANGE, "bytes=" + j + "-");
        }
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Map<String, String> getQueryParameters() {
        return Collections.singletonMap("alt", Constants.KEY_MEDIA);
    }
}
