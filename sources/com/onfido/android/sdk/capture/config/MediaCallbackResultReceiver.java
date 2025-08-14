package com.onfido.android.sdk.capture.config;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.config.MediaResult;
import com.onfido.android.sdk.capture.ui.CaptureType;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaCallbackResultReceiver.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0012J\b\u0010\u0011\u001a\u00020\u0012H\u0012J\u001a\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0014\u0010\u0015\u001a\u00020\u0016*\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0007H\u0012R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0092\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0092\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/onfido/android/sdk/capture/config/MediaCallbackResultReceiver;", "Landroid/os/ResultReceiver;", "mediaCallback", "Lcom/onfido/android/sdk/capture/config/MediaCallback;", "(Lcom/onfido/android/sdk/capture/config/MediaCallback;)V", "arrayOfChunks", "", "", "[[B", "getMediaCallback", "()Lcom/onfido/android/sdk/capture/config/MediaCallback;", "receivedChunkCount", "", "collectChunks", "", "resultData", "Landroid/os/Bundle;", "hasChunkLeft", "", "onReceiveResult", "resultCode", "getMediaResult", "Lcom/onfido/android/sdk/capture/config/MediaResult;", "file", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class MediaCallbackResultReceiver extends ResultReceiver {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String KEY_CAPTURE_TYPE = "isDocument";
    public static final String KEY_COUNT = "count";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_DATA = "data";
    public static final String KEY_DOC_SIDE = "doc_side";
    public static final String KEY_DOC_TYPE = "doc_type";
    public static final String KEY_FILE = "file";
    public static final String KEY_FILE_NAME = "file_name";
    public static final String KEY_FILE_TYPE = "file_type";
    public static final String KEY_INDEX = "index";
    private byte[][] arrayOfChunks;
    private final MediaCallback mediaCallback;
    private int receivedChunkCount;

    /* compiled from: MediaCallbackResultReceiver.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CaptureType.values().length];
            try {
                iArr[CaptureType.FACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CaptureType.DOCUMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CaptureType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public MediaCallback getMediaCallback() {
        return this.mediaCallback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaCallbackResultReceiver(MediaCallback mediaCallback) {
        super(new Handler(Looper.getMainLooper()));
        Intrinsics.checkNotNullParameter(mediaCallback, "mediaCallback");
        this.mediaCallback = mediaCallback;
        this.arrayOfChunks = new byte[0][];
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (resultData != null) {
            collectChunks(resultData);
            if (hasChunkLeft()) {
                return;
            }
            getMediaCallback().onMediaCaptured(getMediaResult(resultData, MediaCallbackResultReceiverKt.allocateAll(this.arrayOfChunks)));
            this.receivedChunkCount = 0;
        }
    }

    private void collectChunks(Bundle resultData) {
        byte[] byteArray;
        Bundle bundle = resultData.getBundle("file");
        if (bundle == null || (byteArray = bundle.getByteArray("data")) == null) {
            byteArray = new byte[0];
        }
        int i = bundle != null ? bundle.getInt("index") : 0;
        int i2 = bundle != null ? bundle.getInt("count") : 0;
        if (this.receivedChunkCount == 0) {
            byte[][] bArr = new byte[i2][];
            for (int i3 = 0; i3 < i2; i3++) {
                bArr[i3] = new byte[0];
            }
            this.arrayOfChunks = bArr;
        }
        this.arrayOfChunks[i] = byteArray;
        this.receivedChunkCount++;
    }

    private boolean hasChunkLeft() {
        return this.receivedChunkCount != this.arrayOfChunks.length;
    }

    private MediaResult getMediaResult(Bundle bundle, byte[] bArr) {
        String string = bundle.getString(KEY_FILE_NAME);
        if (string == null) {
            string = "";
        }
        String string2 = bundle.getString(KEY_FILE_TYPE);
        if (string2 == null) {
            string2 = "";
        }
        String string3 = bundle.getString(KEY_DOC_SIDE);
        if (string3 == null) {
            string3 = "";
        }
        String string4 = bundle.getString("doc_type");
        if (string4 == null) {
            string4 = "";
        }
        String string5 = bundle.getString(KEY_COUNTRY);
        String str = string5 != null ? string5 : "";
        Serializable serializable = bundle.getSerializable(KEY_CAPTURE_TYPE);
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.CaptureType");
        MediaFile mediaFile = new MediaFile(bArr, string2, string);
        int i = WhenMappings.$EnumSwitchMapping$0[((CaptureType) serializable).ordinal()];
        if (i == 1) {
            return new MediaResult.SelfieResult(mediaFile);
        }
        if (i == 2) {
            return new MediaResult.DocumentResult(mediaFile, new DocumentMetadata(string3, string4, str));
        }
        if (i == 3) {
            return new MediaResult.LivenessResult(mediaFile);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* compiled from: MediaCallbackResultReceiver.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u0016\u0010\n\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002R\u0016\u0010\f\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u0016\u0010\u000e\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0002R\u0016\u0010\u0010\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0002R\u0016\u0010\u0012\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0002R\u0016\u0010\u0014\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0002R\u0016\u0010\u0016\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0002¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/config/MediaCallbackResultReceiver$Companion;", "", "()V", "KEY_CAPTURE_TYPE", "", "getKEY_CAPTURE_TYPE$annotations", "KEY_COUNT", "getKEY_COUNT$annotations", "KEY_COUNTRY", "getKEY_COUNTRY$annotations", "KEY_DATA", "getKEY_DATA$annotations", "KEY_DOC_SIDE", "getKEY_DOC_SIDE$annotations", "KEY_DOC_TYPE", "getKEY_DOC_TYPE$annotations", "KEY_FILE", "getKEY_FILE$annotations", "KEY_FILE_NAME", "getKEY_FILE_NAME$annotations", "KEY_FILE_TYPE", "getKEY_FILE_TYPE$annotations", "KEY_INDEX", "getKEY_INDEX$annotations", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getKEY_CAPTURE_TYPE$annotations() {
        }

        public static /* synthetic */ void getKEY_COUNT$annotations() {
        }

        public static /* synthetic */ void getKEY_COUNTRY$annotations() {
        }

        public static /* synthetic */ void getKEY_DATA$annotations() {
        }

        public static /* synthetic */ void getKEY_DOC_SIDE$annotations() {
        }

        public static /* synthetic */ void getKEY_DOC_TYPE$annotations() {
        }

        public static /* synthetic */ void getKEY_FILE$annotations() {
        }

        public static /* synthetic */ void getKEY_FILE_NAME$annotations() {
        }

        public static /* synthetic */ void getKEY_FILE_TYPE$annotations() {
        }

        public static /* synthetic */ void getKEY_INDEX$annotations() {
        }

        private Companion() {
        }
    }
}
