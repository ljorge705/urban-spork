package com.ReactNativeBlobUtil.Response;

import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilProgressConfig;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilReq;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

/* loaded from: classes5.dex */
public class ReactNativeBlobUtilFileResp extends ResponseBody {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    long bytesDownloaded;
    boolean isEndMarkerReceived;
    String mPath;
    String mTaskId;
    FileOutputStream ofStream;
    ResponseBody originalBody;
    ReactApplicationContext rctContext;

    public ReactNativeBlobUtilFileResp(ResponseBody responseBody) {
        this.bytesDownloaded = 0L;
        this.originalBody = responseBody;
    }

    public ReactNativeBlobUtilFileResp(ReactApplicationContext reactApplicationContext, String str, ResponseBody responseBody, String str2, boolean z) throws IOException {
        this.bytesDownloaded = 0L;
        this.rctContext = reactApplicationContext;
        this.mTaskId = str;
        this.originalBody = responseBody;
        this.mPath = str2;
        this.isEndMarkerReceived = false;
        if (str2 != null) {
            boolean z2 = !z;
            String strReplace = str2.replace("?append=true", "");
            this.mPath = strReplace;
            File file = new File(strReplace);
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parentFile);
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            this.ofStream = new FileOutputStream(new File(strReplace), z2);
        }
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentType */
    public MediaType get$contentType() {
        return this.originalBody.get$contentType();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentLength */
    public long getContentLength() {
        if (this.originalBody.getContentLength() > 2147483647L) {
            return 2147483647L;
        }
        return this.originalBody.getContentLength();
    }

    public boolean isDownloadComplete() {
        return this.bytesDownloaded == getContentLength() || (getContentLength() == -1 && this.isEndMarkerReceived);
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: source */
    public BufferedSource getSource() {
        return Okio.buffer(new ProgressReportingSource());
    }

    private class ProgressReportingSource implements Source {
        @Override // okio.Source
        /* renamed from: timeout */
        public Timeout getTimeout() {
            return null;
        }

        private ProgressReportingSource() {
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            int i = (int) j;
            try {
                byte[] bArr = new byte[i];
                long j2 = ReactNativeBlobUtilFileResp.this.originalBody.byteStream().read(bArr, 0, i);
                ReactNativeBlobUtilFileResp.this.bytesDownloaded += j2 > 0 ? j2 : 0L;
                if (j2 > 0) {
                    ReactNativeBlobUtilFileResp.this.ofStream.write(bArr, 0, (int) j2);
                } else if (ReactNativeBlobUtilFileResp.this.getContentLength() == -1 && j2 == -1) {
                    ReactNativeBlobUtilFileResp.this.isEndMarkerReceived = true;
                }
                ReactNativeBlobUtilProgressConfig reportProgress = ReactNativeBlobUtilReq.getReportProgress(ReactNativeBlobUtilFileResp.this.mTaskId);
                if (ReactNativeBlobUtilFileResp.this.getContentLength() != 0) {
                    float contentLength = ReactNativeBlobUtilFileResp.this.getContentLength() != -1 ? ReactNativeBlobUtilFileResp.this.bytesDownloaded / ReactNativeBlobUtilFileResp.this.getContentLength() : ReactNativeBlobUtilFileResp.this.isEndMarkerReceived ? 1.0f : 0.0f;
                    if (reportProgress != null && reportProgress.shouldReport(contentLength)) {
                        if (ReactNativeBlobUtilFileResp.this.getContentLength() != -1) {
                            reportProgress(ReactNativeBlobUtilFileResp.this.mTaskId, ReactNativeBlobUtilFileResp.this.bytesDownloaded, ReactNativeBlobUtilFileResp.this.getContentLength());
                        } else if (!ReactNativeBlobUtilFileResp.this.isEndMarkerReceived) {
                            reportProgress(ReactNativeBlobUtilFileResp.this.mTaskId, 0L, ReactNativeBlobUtilFileResp.this.getContentLength());
                        } else {
                            reportProgress(ReactNativeBlobUtilFileResp.this.mTaskId, ReactNativeBlobUtilFileResp.this.bytesDownloaded, ReactNativeBlobUtilFileResp.this.bytesDownloaded);
                        }
                    }
                }
                return j2;
            } catch (Exception unused) {
                return -1L;
            }
        }

        private void reportProgress(String str, long j, long j2) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("taskId", str);
            writableMapCreateMap.putString("written", String.valueOf(j));
            writableMapCreateMap.putString("total", String.valueOf(j2));
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) ReactNativeBlobUtilFileResp.this.rctContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ReactNativeBlobUtilConst.EVENT_PROGRESS, writableMapCreateMap);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            ReactNativeBlobUtilFileResp.this.ofStream.close();
        }
    }
}
