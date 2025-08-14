package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import io.sentry.protocol.Request;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bB%\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\u000bB3\b\u0002\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0015\u001a\u00020\u0016J\r\u0010\u0017\u001a\u00020\u0018H\u0001¢\u0006\u0002\b\u0019J\u008d\u0001\u0010\u001a\u001a\u00020\u00162\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0001\u0010\u001d\u001a\u00020\u001c2\b\b\u0003\u0010\u001e\u001a\u00020\u001c2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010 \u001a\u00020\u00182%\b\u0002\u0010!\u001a\u001f\u0012\u0013\u0012\u00110\"¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00052#\b\u0002\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00160\u0005¢\u0006\u0002\u0010'J\u008b\u0001\u0010\u001a\u001a\u00020\u00162\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020)2\b\b\u0003\u0010\u001e\u001a\u00020\u001c2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010 \u001a\u00020\u00182%\b\u0002\u0010!\u001a\u001f\u0012\u0013\u0012\u00110\"¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00052#\b\u0002\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00160\u0005¢\u0006\u0002\u0010+R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "Landroidx/lifecycle/LifecycleObserver;", Request.JsonKeys.FRAGMENT, "Landroidx/fragment/app/Fragment;", "dialogBuilder", "Lkotlin/Function1;", "Landroid/content/Context;", "Landroidx/appcompat/app/AlertDialog$Builder;", "(Landroidx/fragment/app/Fragment;Lkotlin/jvm/functions/Function1;)V", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "(Landroidx/appcompat/app/AppCompatActivity;Lkotlin/jvm/functions/Function1;)V", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "context", "Lkotlin/Lazy;", "(Lkotlin/jvm/functions/Function1;Landroidx/lifecycle/LifecycleOwner;Lkotlin/Lazy;)V", "dialog", "Landroidx/appcompat/app/AlertDialog;", "observer", "Landroidx/lifecycle/LifecycleEventObserver;", "dismiss", "", "isShowing", "", "isShowing$onfido_capture_sdk_core_release", "show", "titleResId", "", "messageResId", "positiveButtonResId", "negativeButtonResId", "cancelable", "negativeButtonAction", "Landroid/content/DialogInterface;", "Lkotlin/ParameterName;", "name", "positiveButtonAction", "diadlog", "(Ljava/lang/Integer;IILjava/lang/Integer;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "title", "", "message", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LifecycleAwareDialog implements LifecycleObserver {
    private final Lazy<Context> context;
    private AlertDialog dialog;
    private final Function1<Context, AlertDialog.Builder> dialogBuilder;
    private final LifecycleOwner lifecycleOwner;
    private final LifecycleEventObserver observer;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LifecycleAwareDialog(final AppCompatActivity activity, Function1<? super Context, ? extends AlertDialog.Builder> dialogBuilder) {
        this(dialogBuilder, activity, LazyKt.lazy(new Function0<AppCompatActivity>() { // from class: com.onfido.android.sdk.capture.utils.LifecycleAwareDialog.5
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AppCompatActivity invoke() {
                return activity;
            }
        }));
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(dialogBuilder, "dialogBuilder");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void observer$lambda$0(LifecycleAwareDialog this$0, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        AlertDialog alertDialog;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            AlertDialog alertDialog2 = this$0.dialog;
            if (alertDialog2 != null) {
                alertDialog2.show();
                return;
            }
            return;
        }
        if ((i == 2 || i == 3 || i == 4) && (alertDialog = this$0.dialog) != null) {
            alertDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$1(Function1 positiveButtonAction, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(positiveButtonAction, "$positiveButtonAction");
        Intrinsics.checkNotNull(dialogInterface);
        positiveButtonAction.invoke(dialogInterface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void show$lambda$3$lambda$2(Function1 function1, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNull(dialogInterface);
        function1.invoke(dialogInterface);
    }

    public final void dismiss() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.dialog = null;
    }

    public final boolean isShowing$onfido_capture_sdk_core_release() {
        AlertDialog alertDialog = this.dialog;
        return alertDialog != null && alertDialog.isShowing();
    }

    public final void show(Integer titleResId, int messageResId, int positiveButtonResId, Integer negativeButtonResId, boolean cancelable, Function1<? super DialogInterface, Unit> negativeButtonAction, Function1<? super DialogInterface, Unit> positiveButtonAction) {
        Intrinsics.checkNotNullParameter(positiveButtonAction, "positiveButtonAction");
        String string = titleResId != null ? this.context.getValue().getString(titleResId.intValue()) : null;
        String string2 = this.context.getValue().getString(messageResId);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        show(string, string2, positiveButtonResId, negativeButtonResId, cancelable, negativeButtonAction, positiveButtonAction);
    }

    public /* synthetic */ LifecycleAwareDialog(AppCompatActivity appCompatActivity, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(appCompatActivity, (Function1<? super Context, ? extends AlertDialog.Builder>) ((i & 2) != 0 ? new Function1<Context, AlertDialog.Builder>() { // from class: com.onfido.android.sdk.capture.utils.LifecycleAwareDialog.4
            @Override // kotlin.jvm.functions.Function1
            public final AlertDialog.Builder invoke(Context it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new AlertDialog.Builder(it);
            }
        } : function1));
    }

    public final void show(String title, String message, int positiveButtonResId, Integer negativeButtonResId, boolean cancelable, final Function1<? super DialogInterface, Unit> negativeButtonAction, final Function1<? super DialogInterface, Unit> positiveButtonAction) {
        AlertDialog alertDialog;
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(positiveButtonAction, "positiveButtonAction");
        AlertDialog.Builder positiveButton = this.dialogBuilder.invoke(this.context.getValue()).setTitle(title).setCancelable(cancelable).setMessage(message).setPositiveButton(positiveButtonResId, new DialogInterface.OnClickListener() { // from class: com.onfido.android.sdk.capture.utils.LifecycleAwareDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                LifecycleAwareDialog.show$lambda$1(positiveButtonAction, dialogInterface, i);
            }
        });
        if (negativeButtonResId != null && negativeButtonAction != null) {
            positiveButton.setNegativeButton(negativeButtonResId.intValue(), new DialogInterface.OnClickListener() { // from class: com.onfido.android.sdk.capture.utils.LifecycleAwareDialog$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    LifecycleAwareDialog.show$lambda$3$lambda$2(negativeButtonAction, dialogInterface, i);
                }
            });
        }
        this.dialog = positiveButton.create();
        if (this.lifecycleOwner.getLifecycle().getState() != Lifecycle.State.RESUMED || (alertDialog = this.dialog) == null) {
            return;
        }
        alertDialog.show();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LifecycleAwareDialog(final Fragment fragment, Function1<? super Context, ? extends AlertDialog.Builder> dialogBuilder) {
        this(dialogBuilder, fragment, LazyKt.lazy(new Function0<Context>() { // from class: com.onfido.android.sdk.capture.utils.LifecycleAwareDialog.3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Context invoke() {
                Context contextRequireContext = fragment.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                return contextRequireContext;
            }
        }));
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(dialogBuilder, "dialogBuilder");
    }

    public /* synthetic */ LifecycleAwareDialog(Fragment fragment, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragment, (Function1<? super Context, ? extends AlertDialog.Builder>) ((i & 2) != 0 ? new Function1<Context, AlertDialog.Builder>() { // from class: com.onfido.android.sdk.capture.utils.LifecycleAwareDialog.2
            @Override // kotlin.jvm.functions.Function1
            public final AlertDialog.Builder invoke(Context it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new AlertDialog.Builder(it);
            }
        } : function1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LifecycleAwareDialog(Function1<? super Context, ? extends AlertDialog.Builder> function1, LifecycleOwner lifecycleOwner, Lazy<? extends Context> lazy) {
        this.dialogBuilder = function1;
        this.lifecycleOwner = lifecycleOwner;
        this.context = lazy;
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: com.onfido.android.sdk.capture.utils.LifecycleAwareDialog$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                LifecycleAwareDialog.observer$lambda$0(this.f$0, lifecycleOwner2, event);
            }
        };
        this.observer = lifecycleEventObserver;
        lifecycleOwner.getLifecycle().addObserver(lifecycleEventObserver);
    }

    /* synthetic */ LifecycleAwareDialog(Function1 function1, LifecycleOwner lifecycleOwner, Lazy lazy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function1<Context, AlertDialog.Builder>() { // from class: com.onfido.android.sdk.capture.utils.LifecycleAwareDialog.1
            @Override // kotlin.jvm.functions.Function1
            public final AlertDialog.Builder invoke(Context it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return new AlertDialog.Builder(it);
            }
        } : function1, lifecycleOwner, lazy);
    }
}
