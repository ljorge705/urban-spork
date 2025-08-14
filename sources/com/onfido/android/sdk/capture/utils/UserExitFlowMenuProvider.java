package com.onfido.android.sdk.capture.utils;

import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.core.view.MenuProvider;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.LifecycleAwareDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012#\b\u0002\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b0\n\u0012#\b\u0002\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/UserExitFlowMenuProvider;", "Landroidx/core/view/MenuProvider;", "lifecycleAwareDialog", "Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;", "menuResId", "", "onExitClicked", "Lkotlin/Function0;", "", "positiveButtonAction", "Lkotlin/Function1;", "Landroid/content/DialogInterface;", "Lkotlin/ParameterName;", "name", "dialog", "negativeButtonAction", "(Lcom/onfido/android/sdk/capture/utils/LifecycleAwareDialog;ILkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onCreateMenu", "menu", "Landroid/view/Menu;", "menuInflater", "Landroid/view/MenuInflater;", "onMenuItemSelected", "", "menuItem", "Landroid/view/MenuItem;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UserExitFlowMenuProvider implements MenuProvider {
    private final LifecycleAwareDialog lifecycleAwareDialog;
    private final int menuResId;
    private final Function1<DialogInterface, Unit> negativeButtonAction;
    private final Function0<Unit> onExitClicked;
    private final Function1<DialogInterface, Unit> positiveButtonAction;

    /* JADX WARN: Multi-variable type inference failed */
    public UserExitFlowMenuProvider(LifecycleAwareDialog lifecycleAwareDialog, int i, Function0<Unit> onExitClicked, Function1<? super DialogInterface, Unit> positiveButtonAction, Function1<? super DialogInterface, Unit> negativeButtonAction) {
        Intrinsics.checkNotNullParameter(lifecycleAwareDialog, "lifecycleAwareDialog");
        Intrinsics.checkNotNullParameter(onExitClicked, "onExitClicked");
        Intrinsics.checkNotNullParameter(positiveButtonAction, "positiveButtonAction");
        Intrinsics.checkNotNullParameter(negativeButtonAction, "negativeButtonAction");
        this.lifecycleAwareDialog = lifecycleAwareDialog;
        this.menuResId = i;
        this.onExitClicked = onExitClicked;
        this.positiveButtonAction = positiveButtonAction;
        this.negativeButtonAction = negativeButtonAction;
    }

    @Override // androidx.core.view.MenuProvider
    public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(menuInflater, "menuInflater");
        menuInflater.inflate(this.menuResId, menu);
    }

    @Override // androidx.core.view.MenuProvider
    public boolean onMenuItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        if (menuItem.getItemId() != R.id.action_exit_flow) {
            return false;
        }
        LifecycleAwareDialog lifecycleAwareDialog = this.lifecycleAwareDialog;
        int i = R.string.onfido_generic_close_sdk_title;
        lifecycleAwareDialog.show((56 & 1) != 0 ? null : Integer.valueOf(i), R.string.onfido_generic_close_sdk_subtitle, (56 & 4) != 0 ? R.string.onfido_ok : R.string.onfido_generic_close_sdk_exit, (56 & 8) != 0 ? null : Integer.valueOf(R.string.onfido_generic_close_sdk_cancel), (56 & 16) != 0 ? false : false, (Function1<? super DialogInterface, Unit>) ((56 & 32) != 0 ? null : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.utils.UserExitFlowMenuProvider.onMenuItemSelected.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserExitFlowMenuProvider.this.negativeButtonAction.invoke(it);
                UserExitFlowMenuProvider.this.lifecycleAwareDialog.dismiss();
            }
        }), (Function1<? super DialogInterface, Unit>) ((56 & 64) != 0 ? LifecycleAwareDialog.C07234.INSTANCE : new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.utils.UserExitFlowMenuProvider.onMenuItemSelected.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface it) {
                Intrinsics.checkNotNullParameter(it, "it");
                UserExitFlowMenuProvider.this.positiveButtonAction.invoke(it);
            }
        }));
        this.onExitClicked.invoke();
        return true;
    }

    public /* synthetic */ UserExitFlowMenuProvider(LifecycleAwareDialog lifecycleAwareDialog, int i, Function0 function0, Function1 function1, Function1 function12, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycleAwareDialog, (i2 & 2) != 0 ? R.menu.onfido_activity_workflow_toolbar_menu : i, (i2 & 4) != 0 ? new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.utils.UserExitFlowMenuProvider.1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }
        } : function0, (i2 & 8) != 0 ? new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.utils.UserExitFlowMenuProvider.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface dialog) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                dialog.dismiss();
            }
        } : function1, (i2 & 16) != 0 ? new Function1<DialogInterface, Unit>() { // from class: com.onfido.android.sdk.capture.utils.UserExitFlowMenuProvider.3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                invoke2(dialogInterface);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DialogInterface dialog) {
                Intrinsics.checkNotNullParameter(dialog, "dialog");
                dialog.dismiss();
            }
        } : function12);
    }
}
