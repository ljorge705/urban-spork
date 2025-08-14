package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ToolbarHost;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&J\b\u0010\t\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J\b\u0010\u000b\u001a\u00020\u0007H&J&\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000eH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/ToolbarHost;", "", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "getToolbar", "()Landroidx/appcompat/widget/Toolbar;", "disableImmersiveFragment", "", "enableImmersiveFragment", "resetToolbar", "resetToolbarColors", "restoreToolbar", "setToolbarColor", ViewProps.BACKGROUND_COLOR, "", "titleColor", "subtitleColor", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ToolbarHost {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void resetToolbar(ToolbarHost toolbarHost) {
            toolbarHost.restoreToolbar();
            toolbarHost.resetToolbarColors();
        }

        public static void resetToolbarColors(ToolbarHost toolbarHost) {
            toolbarHost.setToolbarColor(R.attr.onfidoColorToolbarBackground, R.attr.onfidoColorContentToolbarTitle, R.attr.onfidoColorContentToolbarSubtitle);
        }

        public static void setToolbarColor(final ToolbarHost toolbarHost, int i, final int i2, int i3) {
            toolbarHost.getToolbar().setBackgroundColor(setToolbarColor$getColorInt(toolbarHost, i));
            toolbarHost.getToolbar().setTitleTextColor(setToolbarColor$getColorInt(toolbarHost, i2));
            Drawable navigationIcon = toolbarHost.getToolbar().getNavigationIcon();
            if (navigationIcon != null) {
                navigationIcon.setColorFilter(setToolbarColor$getColorInt(toolbarHost, i2), PorterDuff.Mode.SRC_ATOP);
            }
            toolbarHost.getToolbar().setSubtitleTextColor(setToolbarColor$getColorInt(toolbarHost, i3));
            toolbarHost.getToolbar().post(new Runnable() { // from class: com.onfido.android.sdk.capture.utils.ToolbarHost$DefaultImpls$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ToolbarHost.DefaultImpls.setToolbarColor$lambda$0(toolbarHost, i2);
                }
            });
        }

        private static int setToolbarColor$getColorInt(ToolbarHost toolbarHost, int i) {
            Context context = toolbarHost.getToolbar().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            return ContextUtilsKt.colorFromAttr(context, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void setToolbarColor$lambda$0(ToolbarHost this$0, int i) {
            Drawable icon;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            MenuItem menuItemFindItem = this$0.getToolbar().getMenu().findItem(R.id.action_exit_flow);
            if (menuItemFindItem == null || (icon = menuItemFindItem.getIcon()) == null) {
                return;
            }
            icon.setColorFilter(setToolbarColor$getColorInt(this$0, i), PorterDuff.Mode.SRC_ATOP);
        }
    }

    void disableImmersiveFragment();

    void enableImmersiveFragment();

    Toolbar getToolbar();

    void resetToolbar();

    void resetToolbarColors();

    void restoreToolbar();

    void setToolbarColor(int backgroundColor, int titleColor, int subtitleColor);
}
