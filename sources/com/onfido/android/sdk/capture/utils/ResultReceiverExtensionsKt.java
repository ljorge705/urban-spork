package com.onfido.android.sdk.capture.utils;

import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.core.os.BundleKt;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"INTERNAL_BETWEEN_RESULT_RECEIVER_MS", "", "MAX_CHUNKED_MEDIA_SIZE_IN_BYTE", "", "sendMedia", "Lio/reactivex/rxjava3/disposables/Disposable;", "Landroid/os/ResultReceiver;", "documentData", "", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "bundleOfData", "Landroid/os/Bundle;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ResultReceiverExtensionsKt {
    public static final long INTERNAL_BETWEEN_RESULT_RECEIVER_MS = 10;
    public static final int MAX_CHUNKED_MEDIA_SIZE_IN_BYTE = 262144;

    public static final Disposable sendMedia(final ResultReceiver resultReceiver, byte[] documentData, Scheduler scheduler, final Bundle bundleOfData) {
        Intrinsics.checkNotNullParameter(resultReceiver, "<this>");
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        Intrinsics.checkNotNullParameter(bundleOfData, "bundleOfData");
        final List<byte[]> listChuncked = ByteArrayExtensionsKt.chuncked(documentData, 262144);
        return Observable.zip(Observable.fromIterable(CollectionsKt.withIndex(listChuncked)).observeOn(scheduler).subscribeOn(scheduler), Observable.interval(10L, TimeUnit.MILLISECONDS, scheduler), new BiFunction() { // from class: com.onfido.android.sdk.capture.utils.ResultReceiverExtensionsKt$sendMedia$1$1
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public /* bridge */ /* synthetic */ Object apply(Object obj, Object obj2) {
                return apply((IndexedValue<byte[]>) obj, ((Number) obj2).longValue());
            }

            public final IndexedValue<byte[]> apply(IndexedValue<byte[]> item, long j) {
                Intrinsics.checkNotNullParameter(item, "item");
                return item;
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.utils.ResultReceiverExtensionsKt.sendMedia.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(IndexedValue<byte[]> indexedValue) {
                Intrinsics.checkNotNullParameter(indexedValue, "<name for destructuring parameter 0>");
                int index = indexedValue.getIndex();
                byte[] bArrComponent2 = indexedValue.component2();
                ResultReceiver resultReceiver2 = resultReceiver;
                Bundle bundle = bundleOfData;
                bundle.putBundle("file", BundleKt.bundleOf(TuplesKt.to("data", bArrComponent2), TuplesKt.to("index", Integer.valueOf(index)), TuplesKt.to("count", Integer.valueOf(listChuncked.size()))));
                Unit unit = Unit.INSTANCE;
                resultReceiver2.send(-1, bundle);
            }
        });
    }

    public static /* synthetic */ Disposable sendMedia$default(ResultReceiver resultReceiver, byte[] bArr, Scheduler scheduler, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            scheduler = Schedulers.io();
            Intrinsics.checkNotNullExpressionValue(scheduler, "io(...)");
        }
        return sendMedia(resultReceiver, bArr, scheduler, bundle);
    }
}
