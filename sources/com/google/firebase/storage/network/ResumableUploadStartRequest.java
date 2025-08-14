package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import io.grpc.internal.GrpcUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class ResumableUploadStartRequest extends ResumableNetworkRequest {
    private final String contentType;
    private final JSONObject metadata;

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return GrpcUtil.HTTP_METHOD;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected JSONObject getOutputJSON() {
        return this.metadata;
    }

    public ResumableUploadStartRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, JSONObject jSONObject, String str) {
        super(storageReferenceUri, firebaseApp);
        this.metadata = jSONObject;
        this.contentType = str;
        if (TextUtils.isEmpty(str)) {
            this.mException = new IllegalArgumentException("mContentType is null or empty");
        }
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", ViewProps.START);
        super.setCustomHeader("X-Goog-Upload-Header-Content-Type", str);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    public Uri getURL() {
        String authority = getStorageReferenceUri().getGsUri().getAuthority();
        Uri.Builder builderBuildUpon = getStorageReferenceUri().getHttpBaseUri().buildUpon();
        builderBuildUpon.appendPath("b");
        builderBuildUpon.appendPath(authority);
        builderBuildUpon.appendPath("o");
        return builderBuildUpon.build();
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Map<String, String> getQueryParameters() {
        HashMap map = new HashMap();
        map.put("name", getPathWithoutBucket());
        map.put("uploadType", "resumable");
        return map;
    }
}
