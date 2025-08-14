package com.google.maps.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: StreetViewUtil.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcom/google/maps/android/Status;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.google.maps.android.StreetViewUtils$Companion$fetchStreetViewData$2", f = "StreetViewUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class StreetViewUtils$Companion$fetchStreetViewData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Status>, Object> {
    final /* synthetic */ String $urlString;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StreetViewUtils$Companion$fetchStreetViewData$2(String str, Continuation<? super StreetViewUtils$Companion$fetchStreetViewData$2> continuation) {
        super(2, continuation);
        this.$urlString = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StreetViewUtils$Companion$fetchStreetViewData$2(this.$urlString, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Status> continuation) {
        return ((StreetViewUtils$Companion$fetchStreetViewData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws IOException {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                URLConnection uRLConnectionOpenConnection = new URL(this.$urlString).openConnection();
                Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                httpURLConnection.setRequestMethod("GET");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    BufferedReader bufferedReader2 = bufferedReader;
                    try {
                        String text = TextStreamsKt.readText(bufferedReader2);
                        CloseableKt.closeFinally(bufferedReader2, null);
                        bufferedReader.close();
                        inputStream.close();
                        return StreetViewUtils.INSTANCE.deserializeResponse(text).getStatus();
                    } finally {
                    }
                } else {
                    throw new IOException("HTTP Error: " + responseCode);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException("Network error: " + e.getMessage());
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
