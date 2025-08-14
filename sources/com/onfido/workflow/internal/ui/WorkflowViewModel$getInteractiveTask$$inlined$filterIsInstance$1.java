package com.onfido.workflow.internal.ui;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.workflow.internal.ui.model.FlowTask;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RxExtensions.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "", "it", "test", "com/onfido/android/sdk/capture/utils/RxExtensionsKt$filterIsInstance$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1<T> implements Predicate {
    public static final WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1<T> INSTANCE = new WorkflowViewModel$getInteractiveTask$$inlined$filterIsInstance$1<>();

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it instanceof FlowTask.InteractiveTask;
    }
}
