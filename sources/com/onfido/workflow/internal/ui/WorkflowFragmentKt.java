package com.onfido.workflow.internal.ui;

import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.internal.navigation.navigator.FragmentNavigationManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: WorkflowFragment.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"hasOnlyLoadingScreen", "", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "onfido-workflow_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowFragmentKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasOnlyLoadingScreen(FragmentNavigationManager fragmentNavigationManager) {
        List<Screen> currentScreensSnapshot = fragmentNavigationManager.getCurrentScreensSnapshot();
        ArrayList arrayList = new ArrayList();
        for (Object obj : currentScreensSnapshot) {
            if (!(((Screen) obj) instanceof LoadingScreen)) {
                arrayList.add(obj);
            }
        }
        return arrayList.isEmpty();
    }
}
