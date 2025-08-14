package com.onfido.android.sdk.capture.internal.service;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.CompletableTransformer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J+\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0017¢\u0006\u0002\u0010\u0010J\u001e\u0010\n\u001a\u00020\u000b2\n\u0010\u0011\u001a\u00020\u0012\"\u00020\u00132\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0017J+\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\r\"\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\u0016J\u001e\u0010\u0014\u001a\u00020\u00152\n\u0010\u0011\u001a\u00020\u0012\"\u00020\u00132\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0012J\b\u0010\u0019\u001a\u00020\bH\u0012J\b\u0010\u001a\u001a\u00020\u000bH\u0017J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\bH\u0012J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001dH\u0012J\u0010\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 H\u0017R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\t¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "isEnabled", "", "()Z", "announceString", "Lio/reactivex/rxjava3/core/Completable;", "announcementText", "", "", "shouldInterrupt", "([Ljava/lang/String;Z)Lio/reactivex/rxjava3/core/Completable;", "resIds", "", "", "announceStringAsync", "", "([Ljava/lang/String;Z)V", "executeWhenEnabled", "Lio/reactivex/rxjava3/core/CompletableTransformer;", "hasEnabledSpokenAccessibilityService", "interruptAnnouncement", "interruptBeforeAnnouncement", "isAccessibilityEnabledSingle", "Lio/reactivex/rxjava3/core/Single;", "sendEvent", "event", "Landroid/view/accessibility/AccessibilityEvent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class AnnouncementService {
    private final AccessibilityManager accessibilityManager;
    private final Context context;

    @Inject
    public AnnouncementService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.accessibilityManager = (AccessibilityManager) ContextCompat.getSystemService(context, AccessibilityManager.class);
    }

    public static /* synthetic */ Completable announceString$default(AnnouncementService announcementService, int[] iArr, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: announceString");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return announcementService.announceString(iArr, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void announceString$lambda$3(AnnouncementService this$0, String[] announcementText) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(announcementText, "$announcementText");
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
        accessibilityEventObtain.setEventType(16384);
        for (String str : announcementText) {
            accessibilityEventObtain.getText().add(str);
        }
        AccessibilityManager accessibilityManager = this$0.accessibilityManager;
        if (accessibilityManager != null) {
            accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
        }
    }

    public static /* synthetic */ void announceStringAsync$default(AnnouncementService announcementService, int[] iArr, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: announceStringAsync");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        announcementService.announceStringAsync(iArr, z);
    }

    private CompletableTransformer executeWhenEnabled() {
        return new CompletableTransformer() { // from class: com.onfido.android.sdk.capture.internal.service.AnnouncementService$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.CompletableTransformer
            public final CompletableSource apply(Completable completable) {
                return AnnouncementService.executeWhenEnabled$lambda$6(this.f$0, completable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CompletableSource executeWhenEnabled$lambda$6(AnnouncementService this$0, final Completable upstream) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        return this$0.isAccessibilityEnabledSingle().filter(new Predicate() { // from class: com.onfido.android.sdk.capture.internal.service.AnnouncementService$executeWhenEnabled$1$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public /* bridge */ /* synthetic */ boolean test(Object obj) {
                return test(((Boolean) obj).booleanValue());
            }

            public final boolean test(boolean z) {
                return z;
            }
        }).flatMapCompletable(new Function() { // from class: com.onfido.android.sdk.capture.internal.service.AnnouncementService$executeWhenEnabled$1$2
            public final CompletableSource apply(boolean z) {
                return upstream;
            }

            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Boolean) obj).booleanValue());
            }
        });
    }

    private boolean hasEnabledSpokenAccessibilityService() {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || (enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1)) == null) {
            return false;
        }
        return !enabledAccessibilityServiceList.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void interruptAnnouncement$lambda$5(AnnouncementService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AccessibilityManager accessibilityManager = this$0.accessibilityManager;
        if (accessibilityManager != null) {
            accessibilityManager.interrupt();
        }
    }

    private CompletableTransformer interruptBeforeAnnouncement(final boolean shouldInterrupt) {
        return new CompletableTransformer() { // from class: com.onfido.android.sdk.capture.internal.service.AnnouncementService$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.core.CompletableTransformer
            public final CompletableSource apply(Completable completable) {
                return AnnouncementService.interruptBeforeAnnouncement$lambda$8(shouldInterrupt, this, completable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CompletableSource interruptBeforeAnnouncement$lambda$8(boolean z, AnnouncementService this$0, Completable upstream) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        return z ? this$0.interruptAnnouncement().andThen(upstream) : upstream;
    }

    private Single<Boolean> isAccessibilityEnabledSingle() {
        Single<Boolean> singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.internal.service.AnnouncementService$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AnnouncementService.isAccessibilityEnabledSingle$lambda$7(this.f$0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFromCallable, "fromCallable(...)");
        return singleFromCallable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean isAccessibilityEnabledSingle$lambda$7(AnnouncementService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return Boolean.valueOf(this$0.hasEnabledSpokenAccessibilityService());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendEvent$lambda$4(AnnouncementService this$0, AccessibilityEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(event, "$event");
        AccessibilityManager accessibilityManager = this$0.accessibilityManager;
        if (accessibilityManager != null) {
            accessibilityManager.sendAccessibilityEvent(event);
        }
    }

    public Completable announceString(int[] resIds, boolean shouldInterrupt) {
        Intrinsics.checkNotNullParameter(resIds, "resIds");
        ArrayList arrayList = new ArrayList(resIds.length);
        for (int i : resIds) {
            arrayList.add(this.context.getString(i));
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        return announceString((String[]) Arrays.copyOf(strArr, strArr.length), shouldInterrupt);
    }

    public void announceStringAsync(int[] resIds, boolean shouldInterrupt) {
        Intrinsics.checkNotNullParameter(resIds, "resIds");
        announceString(Arrays.copyOf(resIds, resIds.length), shouldInterrupt).blockingAwait();
    }

    public Completable interruptAnnouncement() {
        Completable completableCompose = Completable.fromAction(new Action() { // from class: com.onfido.android.sdk.capture.internal.service.AnnouncementService$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AnnouncementService.interruptAnnouncement$lambda$5(this.f$0);
            }
        }).compose(executeWhenEnabled());
        Intrinsics.checkNotNullExpressionValue(completableCompose, "compose(...)");
        return completableCompose;
    }

    public boolean isEnabled() {
        return hasEnabledSpokenAccessibilityService();
    }

    public Completable sendEvent(final AccessibilityEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Completable completableCompose = Completable.fromAction(new Action() { // from class: com.onfido.android.sdk.capture.internal.service.AnnouncementService$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AnnouncementService.sendEvent$lambda$4(this.f$0, event);
            }
        }).compose(executeWhenEnabled());
        Intrinsics.checkNotNullExpressionValue(completableCompose, "compose(...)");
        return completableCompose;
    }

    public static /* synthetic */ Completable announceString$default(AnnouncementService announcementService, String[] strArr, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: announceString");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return announcementService.announceString(strArr, z);
    }

    public static /* synthetic */ void announceStringAsync$default(AnnouncementService announcementService, String[] strArr, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: announceStringAsync");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        announcementService.announceStringAsync(strArr, z);
    }

    public Completable announceString(final String[] announcementText, boolean shouldInterrupt) {
        Intrinsics.checkNotNullParameter(announcementText, "announcementText");
        Completable completableCompose = Completable.fromAction(new Action() { // from class: com.onfido.android.sdk.capture.internal.service.AnnouncementService$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AnnouncementService.announceString$lambda$3(this.f$0, announcementText);
            }
        }).compose(executeWhenEnabled()).compose(interruptBeforeAnnouncement(shouldInterrupt));
        Intrinsics.checkNotNullExpressionValue(completableCompose, "compose(...)");
        return completableCompose;
    }

    public void announceStringAsync(String[] resIds, boolean shouldInterrupt) {
        Intrinsics.checkNotNullParameter(resIds, "resIds");
        announceString((String[]) Arrays.copyOf(resIds, resIds.length), shouldInterrupt).blockingAwait();
    }
}
