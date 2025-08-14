package com.onfido.api.client;

import com.onfido.api.client.data.ErrorData;
import retrofit2.Response;

/* loaded from: classes6.dex */
public interface ErrorParser {
    ErrorData parseErrorFrom(Response response);
}
