package com.onfido.workflow.internal.ui.backstack;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.onfido.android.sdk.capture.internal.navigation.Screen;
import com.onfido.android.sdk.capture.internal.navigation.navigator.FragmentNavigationManager;
import com.onfido.workflow.internal.ui.LoadingScreen;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowBackstackManager.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0011\u0012BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\u0010\rJ\b\u0010\u0004\u001a\u00020\bH\u0016R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/workflow/internal/ui/backstack/WorkflowBackstackManager;", "Landroidx/fragment/app/FragmentManager$OnBackStackChangedListener;", "navigationManager", "Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;", "onBackStackChanged", "Lkotlin/Function1;", "", "Lcom/onfido/android/sdk/capture/internal/navigation/Screen;", "", "parentFragmentManager", "Landroidx/fragment/app/FragmentManager;", "onScreenBackStackChanged", "", "(Lcom/onfido/android/sdk/capture/internal/navigation/navigator/FragmentNavigationManager;Lkotlin/jvm/functions/Function1;Landroidx/fragment/app/FragmentManager;Lkotlin/jvm/functions/Function1;)V", "backstackListeners", "", "Lcom/onfido/workflow/internal/ui/backstack/WorkflowBackstackManager$FragmentManagerBackStackListenerPair;", "FragmentManagerBackStackListenerPair", "ScreenBackStackListener", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowBackstackManager implements FragmentManager.OnBackStackChangedListener {
    private final List<FragmentManagerBackStackListenerPair> backstackListeners;
    private final FragmentNavigationManager navigationManager;
    private final Function1<List<? extends Screen>, Unit> onBackStackChanged;
    private final Function1<Integer, Unit> onScreenBackStackChanged;
    private final FragmentManager parentFragmentManager;

    /* JADX WARN: Multi-variable type inference failed */
    public WorkflowBackstackManager(FragmentNavigationManager navigationManager, Function1<? super List<? extends Screen>, Unit> onBackStackChanged, FragmentManager parentFragmentManager, Function1<? super Integer, Unit> onScreenBackStackChanged) {
        Intrinsics.checkNotNullParameter(navigationManager, "navigationManager");
        Intrinsics.checkNotNullParameter(onBackStackChanged, "onBackStackChanged");
        Intrinsics.checkNotNullParameter(parentFragmentManager, "parentFragmentManager");
        Intrinsics.checkNotNullParameter(onScreenBackStackChanged, "onScreenBackStackChanged");
        this.navigationManager = navigationManager;
        this.onBackStackChanged = onBackStackChanged;
        this.parentFragmentManager = parentFragmentManager;
        this.onScreenBackStackChanged = onScreenBackStackChanged;
        this.backstackListeners = new ArrayList();
    }

    @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
    public void onBackStackChanged() {
        FragmentManager childFragmentManager;
        List<Screen> currentScreensSnapshot = this.navigationManager.getCurrentScreensSnapshot();
        this.onBackStackChanged.invoke(currentScreensSnapshot);
        ArrayList arrayList = new ArrayList();
        for (Object obj : currentScreensSnapshot) {
            if (!(((Screen) obj) instanceof LoadingScreen)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        Screen screen = (Screen) CollectionsKt.lastOrNull((List) arrayList2);
        if (arrayList2.size() == 1 && screen != null) {
            Fragment fragmentFindFragmentByTag = this.parentFragmentManager.findFragmentByTag(screen.screenKey());
            if (fragmentFindFragmentByTag == null || (childFragmentManager = fragmentFindFragmentByTag.getChildFragmentManager()) == null) {
                return;
            }
            FragmentManagerBackStackListenerPair fragmentManagerBackStackListenerPair = new FragmentManagerBackStackListenerPair(childFragmentManager, new ScreenBackStackListener(this.onScreenBackStackChanged, childFragmentManager));
            this.backstackListeners.add(fragmentManagerBackStackListenerPair);
            childFragmentManager.addOnBackStackChangedListener(fragmentManagerBackStackListenerPair.getBackstackListener());
            return;
        }
        for (FragmentManagerBackStackListenerPair fragmentManagerBackStackListenerPair2 : this.backstackListeners) {
            fragmentManagerBackStackListenerPair2.getFragmentManager().removeOnBackStackChangedListener(fragmentManagerBackStackListenerPair2.getBackstackListener());
        }
        this.backstackListeners.clear();
    }

    /* compiled from: WorkflowBackstackManager.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\u0005H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/workflow/internal/ui/backstack/WorkflowBackstackManager$ScreenBackStackListener;", "Landroidx/fragment/app/FragmentManager$OnBackStackChangedListener;", "screenBackStackChanged", "Lkotlin/Function1;", "", "", "childFragmentManager", "Landroidx/fragment/app/FragmentManager;", "(Lkotlin/jvm/functions/Function1;Landroidx/fragment/app/FragmentManager;)V", "onBackStackChanged", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class ScreenBackStackListener implements FragmentManager.OnBackStackChangedListener {
        private final FragmentManager childFragmentManager;
        private final Function1<Integer, Unit> screenBackStackChanged;

        /* JADX WARN: Multi-variable type inference failed */
        public ScreenBackStackListener(Function1<? super Integer, Unit> screenBackStackChanged, FragmentManager childFragmentManager) {
            Intrinsics.checkNotNullParameter(screenBackStackChanged, "screenBackStackChanged");
            Intrinsics.checkNotNullParameter(childFragmentManager, "childFragmentManager");
            this.screenBackStackChanged = screenBackStackChanged;
            this.childFragmentManager = childFragmentManager;
        }

        @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
        public void onBackStackChanged() {
            this.screenBackStackChanged.invoke(Integer.valueOf(this.childFragmentManager.getBackStackEntryCount()));
        }
    }

    /* compiled from: WorkflowBackstackManager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/workflow/internal/ui/backstack/WorkflowBackstackManager$FragmentManagerBackStackListenerPair;", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "backstackListener", "Landroidx/fragment/app/FragmentManager$OnBackStackChangedListener;", "(Landroidx/fragment/app/FragmentManager;Landroidx/fragment/app/FragmentManager$OnBackStackChangedListener;)V", "getBackstackListener", "()Landroidx/fragment/app/FragmentManager$OnBackStackChangedListener;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class FragmentManagerBackStackListenerPair {
        private final FragmentManager.OnBackStackChangedListener backstackListener;
        private final FragmentManager fragmentManager;

        public final FragmentManager.OnBackStackChangedListener getBackstackListener() {
            return this.backstackListener;
        }

        public final FragmentManager getFragmentManager() {
            return this.fragmentManager;
        }

        public FragmentManagerBackStackListenerPair(FragmentManager fragmentManager, FragmentManager.OnBackStackChangedListener backstackListener) {
            Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
            Intrinsics.checkNotNullParameter(backstackListener, "backstackListener");
            this.fragmentManager = fragmentManager;
            this.backstackListener = backstackListener;
        }
    }
}
