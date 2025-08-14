package com.onfido.android.sdk.capture.network.error;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.api.client.ErrorParser;
import com.onfido.api.client.data.ErrorData;
import com.onfido.api.client.exception.TokenExpiredException;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.CompletableTransformer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.HttpException;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b\u0000\u0010\b*\u00020\u0001J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/network/error/ErrorHandler;", "", "errorParser", "Lcom/onfido/api/client/ErrorParser;", "(Lcom/onfido/api/client/ErrorParser;)V", "handleError", "Lio/reactivex/rxjava3/core/CompletableTransformer;", "Lio/reactivex/rxjava3/core/SingleTransformer;", ExifInterface.GPS_DIRECTION_TRUE, "identifyError", "", "throwable", "isTokenExpirationError", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ErrorHandler {
    private static final String TOKEN_EXPIRED = "expired_token";
    private final ErrorParser errorParser;

    public ErrorHandler(ErrorParser errorParser) {
        Intrinsics.checkNotNullParameter(errorParser, "errorParser");
        this.errorParser = errorParser;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SingleSource handleError$lambda$0(final ErrorHandler this$0, Single source) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(source, "source");
        return source.onErrorResumeNext(new Function() { // from class: com.onfido.android.sdk.capture.network.error.ErrorHandler$handleError$1$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends T> apply(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                return Single.error(this.this$0.identifyError(throwable));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CompletableSource handleError$lambda$1(final ErrorHandler this$0, Completable source) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(source, "source");
        return source.onErrorResumeNext(new Function() { // from class: com.onfido.android.sdk.capture.network.error.ErrorHandler$handleError$2$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final CompletableSource apply(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                return Completable.error(this.this$0.identifyError(throwable));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable identifyError(Throwable throwable) {
        return isTokenExpirationError(throwable) ? new TokenExpiredException() : throwable;
    }

    private final boolean isTokenExpirationError(Throwable throwable) {
        ErrorData.Error error;
        if (!(throwable instanceof HttpException)) {
            return false;
        }
        ErrorData errorFrom = this.errorParser.parseErrorFrom(((HttpException) throwable).response());
        return Intrinsics.areEqual((errorFrom == null || (error = errorFrom.getError()) == null) ? null : error.getType(), TOKEN_EXPIRED);
    }

    public final CompletableTransformer handleError() {
        return new CompletableTransformer() { // from class: com.onfido.android.sdk.capture.network.error.ErrorHandler$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.CompletableTransformer
            public final CompletableSource apply(Completable completable) {
                return ErrorHandler.handleError$lambda$1(this.f$0, completable);
            }
        };
    }

    /* renamed from: handleError, reason: collision with other method in class */
    public final <T> SingleTransformer<T, T> m5615handleError() {
        return new SingleTransformer() { // from class: com.onfido.android.sdk.capture.network.error.ErrorHandler$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.SingleTransformer
            public final SingleSource apply(Single single) {
                return ErrorHandler.handleError$lambda$0(this.f$0, single);
            }
        };
    }
}
