package com.onfido.android.sdk.capture.component.active.video.capture.data.repository.mapper;

import android.content.res.Resources;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.result.FailureReason;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.mapper.Mapper;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/data/repository/mapper/ThrowableToErrorMessageMapper;", "Lcom/onfido/android/sdk/capture/utils/mapper/Mapper;", "", "Lcom/onfido/android/sdk/capture/common/result/FailureReason;", "jsonParser", "Lkotlinx/serialization/json/Json;", "resources", "Landroid/content/res/Resources;", "(Lkotlinx/serialization/json/Json;Landroid/content/res/Resources;)V", "getErrorMessage", "", "throwable", "getJsonError", "httpException", "Lretrofit2/HttpException;", "getMessage", "input", "handleHttpException", "map", "parseNetworkError", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ThrowableToErrorMessageMapper implements Mapper<Throwable, FailureReason> {
    private final Json jsonParser;
    private final Resources resources;

    @Inject
    public ThrowableToErrorMessageMapper(Json jsonParser, Resources resources) {
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        Intrinsics.checkNotNullParameter(resources, "resources");
        this.jsonParser = jsonParser;
        this.resources = resources;
    }

    private final String getErrorMessage(Throwable throwable) throws Resources.NotFoundException {
        String message = throwable.getMessage();
        if (message != null) {
            return message;
        }
        String string = this.resources.getString(R.string.onfido_generic_error_detail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    private final String getJsonError(HttpException httpException) {
        ResponseBody responseBodyErrorBody;
        Response<?> response = httpException.response();
        if (response == null || (responseBodyErrorBody = response.errorBody()) == null) {
            return null;
        }
        return responseBodyErrorBody.string();
    }

    private final String getMessage(Throwable input) {
        return input instanceof HttpException ? handleHttpException((HttpException) input) : getErrorMessage(input);
    }

    private final String handleHttpException(HttpException httpException) {
        try {
            return parseNetworkError(httpException);
        } catch (Exception e) {
            Timber.INSTANCE.e(e, "JSON parse exception", new Object[0]);
            return getErrorMessage(httpException);
        }
    }

    private final String parseNetworkError(HttpException httpException) {
        String jsonError = getJsonError(httpException);
        if (jsonError != null) {
            Json json = this.jsonParser;
            String message = ((NetworkError) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(NetworkError.class)), jsonError)).getMessage();
            if (message != null) {
                return message;
            }
        }
        return "";
    }

    @Override // com.onfido.android.sdk.capture.utils.mapper.Mapper
    public FailureReason map(Throwable input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return new FailureReason(input, getMessage(input));
    }
}
