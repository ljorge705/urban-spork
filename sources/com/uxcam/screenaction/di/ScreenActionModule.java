package com.uxcam.screenaction.di;

import com.uxcam.screenaction.ScreenActionProvider;
import com.uxcam.screenaction.ScreenActionProviderImpl;
import com.uxcam.screenaction.compose.ComposeRootsProvider;
import com.uxcam.screenaction.compose.ComposeRootsProviderImpl;
import com.uxcam.screenaction.compose.ComposeScreenActionProvider;
import com.uxcam.screenaction.compose.RadiographyFork;
import com.uxcam.screenaction.repository.ScreenActionViewsRepository;
import com.uxcam.screenaction.repository.ScreenActionViewsRepositoryImpl;
import com.uxcam.screenaction.views.ViewSystemScreenActionProvider;
import com.uxcam.screenaction.views.ViewSystemScreenActionProviderImpl;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u0004H\u0002J\b\u0010\u000e\u001a\u00020\u0006H\u0002J\u0006\u0010\u000f\u001a\u00020\bJ\u0006\u0010\u0010\u001a\u00020\nJ\b\u0010\u0011\u001a\u00020\fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/uxcam/screenaction/di/ScreenActionModule;", "", "()V", "composeRootsProvider", "Lcom/uxcam/screenaction/compose/ComposeRootsProvider;", "composeScreenActionProvider", "Lcom/uxcam/screenaction/compose/ComposeScreenActionProvider;", "screenActionProvider", "Lcom/uxcam/screenaction/ScreenActionProvider;", "screenActionViewsRepository", "Lcom/uxcam/screenaction/repository/ScreenActionViewsRepository;", "viewSystemScreenActionProvider", "Lcom/uxcam/screenaction/views/ViewSystemScreenActionProvider;", "getComposeRootsProvider", "getComposeScreenActionProvider", "getScreenActionProvider", "getScreenActionViewsRepository", "getViewSystemScreenActionProvider", "Companion", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ScreenActionModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static ScreenActionModule screenActionModule;
    private ComposeRootsProvider composeRootsProvider;
    private ComposeScreenActionProvider composeScreenActionProvider;
    private ScreenActionProvider screenActionProvider;
    private ScreenActionViewsRepository screenActionViewsRepository;
    private ViewSystemScreenActionProvider viewSystemScreenActionProvider;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/uxcam/screenaction/di/ScreenActionModule$Companion;", "", "()V", "screenActionModule", "Lcom/uxcam/screenaction/di/ScreenActionModule;", "getInstance", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ScreenActionModule getInstance() {
            if (ScreenActionModule.screenActionModule == null) {
                ScreenActionModule.screenActionModule = new ScreenActionModule();
            }
            ScreenActionModule screenActionModule = ScreenActionModule.screenActionModule;
            Intrinsics.checkNotNull(screenActionModule);
            return screenActionModule;
        }
    }

    @JvmStatic
    public static final ScreenActionModule getInstance() {
        return INSTANCE.getInstance();
    }

    private final ComposeRootsProvider getComposeRootsProvider() {
        if (this.composeRootsProvider == null) {
            this.composeRootsProvider = new ComposeRootsProviderImpl();
        }
        ComposeRootsProvider composeRootsProvider = this.composeRootsProvider;
        Intrinsics.checkNotNull(composeRootsProvider);
        return composeRootsProvider;
    }

    private final ComposeScreenActionProvider getComposeScreenActionProvider() {
        if (this.composeScreenActionProvider == null) {
            this.composeScreenActionProvider = new RadiographyFork(Dispatchers.getIO());
        }
        ComposeScreenActionProvider composeScreenActionProvider = this.composeScreenActionProvider;
        Intrinsics.checkNotNull(composeScreenActionProvider);
        return composeScreenActionProvider;
    }

    private final ViewSystemScreenActionProvider getViewSystemScreenActionProvider() {
        if (this.viewSystemScreenActionProvider == null) {
            this.viewSystemScreenActionProvider = new ViewSystemScreenActionProviderImpl();
        }
        ViewSystemScreenActionProvider viewSystemScreenActionProvider = this.viewSystemScreenActionProvider;
        Intrinsics.checkNotNull(viewSystemScreenActionProvider);
        return viewSystemScreenActionProvider;
    }

    public final ScreenActionProvider getScreenActionProvider() {
        if (this.screenActionProvider == null) {
            this.screenActionProvider = new ScreenActionProviderImpl(getViewSystemScreenActionProvider(), getComposeScreenActionProvider(), getComposeRootsProvider());
        }
        ScreenActionProvider screenActionProvider = this.screenActionProvider;
        Intrinsics.checkNotNull(screenActionProvider);
        return screenActionProvider;
    }

    public final ScreenActionViewsRepository getScreenActionViewsRepository() {
        if (this.screenActionViewsRepository == null) {
            this.screenActionViewsRepository = new ScreenActionViewsRepositoryImpl();
        }
        ScreenActionViewsRepository screenActionViewsRepository = this.screenActionViewsRepository;
        Intrinsics.checkNotNull(screenActionViewsRepository);
        return screenActionViewsRepository;
    }
}
