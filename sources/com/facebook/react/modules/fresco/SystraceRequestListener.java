package com.facebook.react.modules.fresco;

import android.util.Pair;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.systrace.Systrace;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes5.dex */
public class SystraceRequestListener extends BaseRequestListener {
    int mCurrentID = 0;
    Map<String, Pair<Integer, String>> mProducerID = new HashMap();
    Map<String, Pair<Integer, String>> mRequestsID = new HashMap();

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public boolean requiresExtraMap(String str) {
        return false;
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerStart(String str, String str2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Systrace.isTracing(0L)) {
            Pair<Integer, String> pairCreate = Pair.create(Integer.valueOf(this.mCurrentID), "FRESCO_PRODUCER_" + str2.replace(AbstractJsonLexerKt.COLON, '_'));
            Systrace.beginAsyncSection(0L, (String) pairCreate.second, this.mCurrentID);
            this.mProducerID.put(str, pairCreate);
            this.mCurrentID++;
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithSuccess(String str, String str2, Map<String, String> map) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Systrace.isTracing(0L) && this.mProducerID.containsKey(str)) {
            Pair<Integer, String> pair = this.mProducerID.get(str);
            Systrace.endAsyncSection(0L, (String) pair.second, ((Integer) pair.first).intValue());
            this.mProducerID.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithFailure(String str, String str2, Throwable th, Map<String, String> map) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Systrace.isTracing(0L) && this.mProducerID.containsKey(str)) {
            Pair<Integer, String> pair = this.mProducerID.get(str);
            Systrace.endAsyncSection(0L, (String) pair.second, ((Integer) pair.first).intValue());
            this.mProducerID.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithCancellation(String str, String str2, Map<String, String> map) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Systrace.isTracing(0L) && this.mProducerID.containsKey(str)) {
            Pair<Integer, String> pair = this.mProducerID.get(str);
            Systrace.endAsyncSection(0L, (String) pair.second, ((Integer) pair.first).intValue());
            this.mProducerID.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerEvent(String str, String str2, String str3) {
        if (Systrace.isTracing(0L)) {
            Systrace.traceInstant(0L, "FRESCO_PRODUCER_EVENT_" + str.replace(AbstractJsonLexerKt.COLON, '_') + "_" + str2.replace(AbstractJsonLexerKt.COLON, '_') + "_" + str3.replace(AbstractJsonLexerKt.COLON, '_'), Systrace.EventScope.THREAD);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Systrace.isTracing(0L)) {
            Pair<Integer, String> pairCreate = Pair.create(Integer.valueOf(this.mCurrentID), "FRESCO_REQUEST_" + imageRequest.getSourceUri().toString().replace(AbstractJsonLexerKt.COLON, '_'));
            Systrace.beginAsyncSection(0L, (String) pairCreate.second, this.mCurrentID);
            this.mRequestsID.put(str, pairCreate);
            this.mCurrentID++;
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Systrace.isTracing(0L) && this.mRequestsID.containsKey(str)) {
            Pair<Integer, String> pair = this.mRequestsID.get(str);
            Systrace.endAsyncSection(0L, (String) pair.second, ((Integer) pair.first).intValue());
            this.mRequestsID.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Systrace.isTracing(0L) && this.mRequestsID.containsKey(str)) {
            Pair<Integer, String> pair = this.mRequestsID.get(str);
            Systrace.endAsyncSection(0L, (String) pair.second, ((Integer) pair.first).intValue());
            this.mRequestsID.remove(str);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestCancellation(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Systrace.isTracing(0L) && this.mRequestsID.containsKey(str)) {
            Pair<Integer, String> pair = this.mRequestsID.get(str);
            Systrace.endAsyncSection(0L, (String) pair.second, ((Integer) pair.first).intValue());
            this.mRequestsID.remove(str);
        }
    }
}
