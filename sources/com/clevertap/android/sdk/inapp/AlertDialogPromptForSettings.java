package com.clevertap.android.sdk.inapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.clevertap.android.sdk.CTStringResources;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.inapp.AlertDialogPromptForSettings;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlertDialogPromptForSettings.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/clevertap/android/sdk/inapp/AlertDialogPromptForSettings;", "", "()V", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class AlertDialogPromptForSettings {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final void show(Activity activity, Function0<Unit> function0, Function0<Unit> function02) {
        INSTANCE.show(activity, function0, function02);
    }

    private AlertDialogPromptForSettings() {
    }

    /* compiled from: AlertDialogPromptForSettings.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0007¨\u0006\n"}, d2 = {"Lcom/clevertap/android/sdk/inapp/AlertDialogPromptForSettings$Companion;", "", "()V", "show", "", "activity", "Landroid/app/Activity;", "onAccept", "Lkotlin/Function0;", "onDecline", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void show(Activity activity, final Function0<Unit> onAccept, final Function0<Unit> onDecline) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(onAccept, "onAccept");
            Intrinsics.checkNotNullParameter(onDecline, "onDecline");
            Context applicationContext = activity.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "activity.applicationContext");
            CTStringResources cTStringResources = new CTStringResources(applicationContext, R.string.ct_permission_not_available_title, R.string.ct_permission_not_available_message, R.string.ct_permission_not_available_open_settings_option, R.string.ct_txt_cancel);
            String strComponent1 = cTStringResources.component1();
            String strComponent2 = cTStringResources.component2();
            String strComponent3 = cTStringResources.component3();
            new AlertDialog.Builder(activity, android.R.style.Theme.Material.Light.Dialog.Alert).setTitle(strComponent1).setCancelable(false).setMessage(strComponent2).setPositiveButton(strComponent3, new DialogInterface.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.AlertDialogPromptForSettings$Companion$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialogPromptForSettings.Companion.show$lambda$0(onAccept, dialogInterface, i);
                }
            }).setNegativeButton(cTStringResources.component4(), new DialogInterface.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.AlertDialogPromptForSettings$Companion$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialogPromptForSettings.Companion.show$lambda$1(onDecline, dialogInterface, i);
                }
            }).show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void show$lambda$0(Function0 onAccept, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(onAccept, "$onAccept");
            onAccept.invoke();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void show$lambda$1(Function0 onDecline, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(onDecline, "$onDecline");
            onDecline.invoke();
        }
    }
}
