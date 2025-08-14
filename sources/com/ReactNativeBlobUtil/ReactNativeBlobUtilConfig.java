package com.ReactNativeBlobUtil;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import io.sentry.ProfilingTraceData;
import java.util.Locale;
import org.spongycastle.cms.CMSAttributeTableGenerator;

/* loaded from: classes5.dex */
class ReactNativeBlobUtilConfig {
    public ReadableMap addAndroidDownloads;
    public String appendExt;
    public Boolean auto;
    public ReadableArray binaryContentTypes;
    public Boolean fileCache;
    public Boolean followRedirect;
    public Boolean increment;
    public String key;
    public String mime;
    public Boolean overwrite;
    public String path;
    public long timeout;
    public Boolean transformFile;
    public Boolean trusty;
    public Boolean wifiOnly;

    ReactNativeBlobUtilConfig(ReadableMap readableMap) {
        boolean z = false;
        this.wifiOnly = false;
        this.overwrite = true;
        this.timeout = 60000L;
        this.increment = false;
        this.followRedirect = true;
        this.binaryContentTypes = null;
        if (readableMap == null) {
            return;
        }
        this.fileCache = Boolean.valueOf(readableMap.hasKey("fileCache") && readableMap.getBoolean("fileCache"));
        this.transformFile = Boolean.valueOf(readableMap.hasKey("transformFile") ? readableMap.getBoolean("transformFile") : false);
        this.path = readableMap.hasKey(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH) ? readableMap.getString(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH) : null;
        this.appendExt = readableMap.hasKey("appendExt") ? readableMap.getString("appendExt") : "";
        this.trusty = Boolean.valueOf(readableMap.hasKey("trusty") && readableMap.getBoolean("trusty"));
        this.wifiOnly = Boolean.valueOf(readableMap.hasKey("wifiOnly") && readableMap.getBoolean("wifiOnly"));
        if (readableMap.hasKey("addAndroidDownloads")) {
            this.addAndroidDownloads = readableMap.getMap("addAndroidDownloads");
        }
        if (readableMap.hasKey("binaryContentTypes")) {
            this.binaryContentTypes = readableMap.getArray("binaryContentTypes");
        }
        String str = this.path;
        if (str != null && str.toLowerCase(Locale.ROOT).contains("?append=true")) {
            this.overwrite = false;
        }
        if (readableMap.hasKey("overwrite")) {
            this.overwrite = Boolean.valueOf(readableMap.getBoolean("overwrite"));
        }
        if (readableMap.hasKey("followRedirect")) {
            this.followRedirect = Boolean.valueOf(readableMap.getBoolean("followRedirect"));
        }
        this.key = readableMap.hasKey(Constants.KEY_KEY) ? readableMap.getString(Constants.KEY_KEY) : null;
        this.mime = readableMap.hasKey(CMSAttributeTableGenerator.CONTENT_TYPE) ? readableMap.getString(CMSAttributeTableGenerator.CONTENT_TYPE) : null;
        this.increment = Boolean.valueOf(readableMap.hasKey("increment") && readableMap.getBoolean("increment"));
        if (readableMap.hasKey("auto") && readableMap.getBoolean("auto")) {
            z = true;
        }
        this.auto = Boolean.valueOf(z);
        if (readableMap.hasKey(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT)) {
            this.timeout = readableMap.getInt(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT);
        }
    }
}
