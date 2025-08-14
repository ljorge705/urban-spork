package com.onfido.android.sdk.capture.internal.navigation;

import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "Landroid/os/Parcelable;", "createFragment", "Landroidx/fragment/app/Fragment;", "screenKey", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface Screen extends Parcelable {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static String screenKey(Screen screen) {
            String name = screen.getClass().getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            return name;
        }
    }

    Fragment createFragment();

    String screenKey();
}
