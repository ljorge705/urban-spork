package com.onfido.android.sdk.capture.internal.util;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"toSingle", "Lio/reactivex/rxjava3/core/Single;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lcom/google/android/gms/tasks/Task;", "executor", "Ljava/util/concurrent/Executor;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TaskExtensionKt {
    public static final <T> Single<T> toSingle(final Task<T> task, final Executor executor) {
        Intrinsics.checkNotNullParameter(task, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Single<T> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.internal.util.TaskExtensionKt$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                TaskExtensionKt.toSingle$lambda$2(task, executor, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toSingle$lambda$2(Task this_toSingle, Executor executor, final SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this_toSingle, "$this_toSingle");
        Intrinsics.checkNotNullParameter(executor, "$executor");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        final Function1<T, Unit> function1 = new Function1<T, Unit>() { // from class: com.onfido.android.sdk.capture.internal.util.TaskExtensionKt$toSingle$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke2((TaskExtensionKt$toSingle$1$1<T>) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(T t) {
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onSuccess(t);
            }
        };
        this_toSingle.addOnSuccessListener(executor, new OnSuccessListener() { // from class: com.onfido.android.sdk.capture.internal.util.TaskExtensionKt$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                TaskExtensionKt.toSingle$lambda$2$lambda$0(function1, obj);
            }
        });
        this_toSingle.addOnFailureListener(executor, new OnFailureListener() { // from class: com.onfido.android.sdk.capture.internal.util.TaskExtensionKt$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                TaskExtensionKt.toSingle$lambda$2$lambda$1(emitter, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toSingle$lambda$2$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void toSingle$lambda$2$lambda$1(SingleEmitter emitter, Exception exception) {
        Intrinsics.checkNotNullParameter(emitter, "$emitter");
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (emitter.isDisposed()) {
            return;
        }
        emitter.onError(exception);
    }
}
