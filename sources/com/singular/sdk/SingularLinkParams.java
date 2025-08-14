package com.singular.sdk;

import android.net.Uri;
import com.singular.sdk.internal.utils.UriUtils;
import java.util.HashMap;
import java.util.Set;

/* loaded from: classes6.dex */
public class SingularLinkParams {
    private String deeplink;
    private boolean isDeferred;
    private String passthrough;
    private Uri url;
    private HashMap<String, String> urlParameters;

    public String getDeeplink() {
        return this.deeplink;
    }

    public String getPassthrough() {
        return this.passthrough;
    }

    public boolean isDeferred() {
        return this.isDeferred;
    }

    public SingularLinkParams(String str, String str2, boolean z, Uri uri) {
        this.deeplink = str;
        this.passthrough = str2;
        this.isDeferred = z;
        this.url = uri;
    }

    public HashMap<String, String> getUrlParameters() {
        if (this.urlParameters == null && UriUtils.isValid(this.url)) {
            Set<String> queryParameterNames = this.url.getQueryParameterNames();
            this.urlParameters = new HashMap<>(queryParameterNames.size());
            for (String str : queryParameterNames) {
                this.urlParameters.put(str, this.url.getQueryParameter(str));
            }
        }
        HashMap<String, String> map = this.urlParameters;
        return map != null ? map : new HashMap<>();
    }
}
