package com.onfido.android.sdk.capture.utils;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0004*\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"FOCUS_ATTEMPT_INTERVAL_IN_MILLIS", "", "MAX_FOCUS_ATTEMPT", "sendAccessibilityFocusEvent", "", "Landroid/view/View;", "setDefaultAccessibilityFocus", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AccessibilityExtensionsKt {
    private static final long FOCUS_ATTEMPT_INTERVAL_IN_MILLIS = 100;
    private static final long MAX_FOCUS_ATTEMPT = 20;

    public static final void sendAccessibilityFocusEvent(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.performAccessibilityAction(64, null);
        view.sendAccessibilityEvent(8);
    }

    public static final void setDefaultAccessibilityFocus(final View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: com.onfido.android.sdk.capture.utils.AccessibilityExtensionsKt.setDefaultAccessibilityFocus.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View host, int action, Bundle args) {
                Intrinsics.checkNotNullParameter(host, "host");
                booleanRef.element = super.performAccessibilityAction(host, action, args);
                return booleanRef.element;
            }
        });
        Observable.interval(0L, 100L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).takeUntil(new Predicate() { // from class: com.onfido.android.sdk.capture.utils.AccessibilityExtensionsKt.setDefaultAccessibilityFocus.2
            public final boolean test(long j) {
                return booleanRef.element;
            }

            @Override // io.reactivex.rxjava3.functions.Predicate
            public /* bridge */ /* synthetic */ boolean test(Object obj) {
                return test(((Number) obj).longValue());
            }
        }).take(20L).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.utils.AccessibilityExtensionsKt.setDefaultAccessibilityFocus.3
            public final void accept(long j) {
                AccessibilityExtensionsKt.sendAccessibilityFocusEvent(view);
                view.requestFocus();
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.utils.AccessibilityExtensionsKt.setDefaultAccessibilityFocus.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }
        }, new Action() { // from class: com.onfido.android.sdk.capture.utils.AccessibilityExtensionsKt$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AccessibilityExtensionsKt.setDefaultAccessibilityFocus$lambda$0(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDefaultAccessibilityFocus$lambda$0(View this_setDefaultAccessibilityFocus) {
        Intrinsics.checkNotNullParameter(this_setDefaultAccessibilityFocus, "$this_setDefaultAccessibilityFocus");
        ViewCompat.setAccessibilityDelegate(this_setDefaultAccessibilityFocus, null);
    }
}
