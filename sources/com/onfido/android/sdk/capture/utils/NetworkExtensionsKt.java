package com.onfido.android.sdk.capture.utils;

import io.reactivex.rxjava3.core.Single;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Okio__JvmOkioKt;
import retrofit2.HttpException;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007\u001a\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"}, d2 = {"httpErrorBodyString", "", "", "saveFile", "Lio/reactivex/rxjava3/core/Single;", "Ljava/io/File;", "Lokhttp3/ResponseBody;", "file", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkExtensionsKt {
    public static final String httpErrorBodyString(Throwable th) {
        Response<?> response;
        ResponseBody responseBodyErrorBody;
        Intrinsics.checkNotNullParameter(th, "<this>");
        if (!(th instanceof HttpException) || (response = ((HttpException) th).response()) == null || (responseBodyErrorBody = response.errorBody()) == null) {
            return null;
        }
        return responseBodyErrorBody.string();
    }

    public static final Single<File> saveFile(ResponseBody responseBody, File file) {
        Single<File> singleError;
        Intrinsics.checkNotNullParameter(responseBody, "<this>");
        Intrinsics.checkNotNullParameter(file, "file");
        BufferedSink bufferedSinkBuffer = Okio.buffer(Okio__JvmOkioKt.sink$default(file, false, 1, null));
        try {
            try {
                bufferedSinkBuffer.writeAll(responseBody.getSource());
                bufferedSinkBuffer.flush();
                singleError = Single.just(file);
                Intrinsics.checkNotNull(singleError);
            } catch (IOException e) {
                singleError = Single.error(e);
                Intrinsics.checkNotNull(singleError);
            }
            return singleError;
        } finally {
            bufferedSinkBuffer.close();
        }
    }
}
