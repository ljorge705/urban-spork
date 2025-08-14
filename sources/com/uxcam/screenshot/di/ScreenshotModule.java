package com.uxcam.screenshot.di;

import android.graphics.Paint;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.BitmapCreator;
import com.uxcam.screenshot.BitmapCreatorImpl;
import com.uxcam.screenshot.flutterviewfinder.FlutterViewFinder;
import com.uxcam.screenshot.flutterviewfinder.FlutterViewFinderImpl;
import com.uxcam.screenshot.fullscreenocclusion.FullScreenOcclusionDrawer;
import com.uxcam.screenshot.fullscreenocclusion.FullScreenOcclusionDrawerImpl;
import com.uxcam.screenshot.helper.BitmapSource;
import com.uxcam.screenshot.helper.ScreenShotHelperImpl;
import com.uxcam.screenshot.helper.ScreenshotHelper;
import com.uxcam.screenshot.keyboardoverlay.KeyboardOverlayDrawer;
import com.uxcam.screenshot.keyboardoverlay.KeyboardOverlayDrawerImpl;
import com.uxcam.screenshot.legacyscreenshot.LegacyScreenshot;
import com.uxcam.screenshot.legacyscreenshot.LegacyScreenshotImpl;
import com.uxcam.screenshot.pixelcopyscreenshot.PixelCopyScreenshot;
import com.uxcam.screenshot.pixelcopyscreenshot.PixelCopyScreenshotImpl;
import com.uxcam.screenshot.repository.ComposeOcclusionRepository;
import com.uxcam.screenshot.repository.ComposeOcclusionRepositoryImpl;
import com.uxcam.screenshot.repository.OcclusionRepository;
import com.uxcam.screenshot.repository.OcclusionRepositoryImpl;
import com.uxcam.screenshot.screenshotTaker.BlackScreenDrawerImpl;
import com.uxcam.screenshot.screenshotTaker.LargestViewRootFilter;
import com.uxcam.screenshot.screenshotTaker.LargestViewRootFilterImpl;
import com.uxcam.screenshot.screenshotTaker.ScreenshotTaker;
import com.uxcam.screenshot.screenshotTaker.ScreenshotTakerImpl;
import com.uxcam.screenshot.state.ScreenshotStateHolder;
import com.uxcam.screenshot.state.ScreenshotStateHolderImpl;
import com.uxcam.screenshot.utils.ScreenShotBitmapUtil;
import com.uxcam.screenshot.viewocclusion.SensitiveComposableOcclusion;
import com.uxcam.screenshot.viewocclusion.SensitiveComposableOcclusionImpl;
import com.uxcam.screenshot.viewocclusion.SensitiveViewsFinder;
import com.uxcam.screenshot.viewocclusion.SensitiveViewsFinderImpl;
import com.uxcam.screenshot.viewocclusion.SensitiveViewsOcclusion;
import com.uxcam.screenshot.viewocclusion.SensitiveViewsOcclusionImpl;
import com.uxcam.screenshot.viewocclusion.WebViewOcclusion;
import com.uxcam.screenshot.viewocclusion.WebViewOcclusionImpl;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 22\u00020\u0001:\u00012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010-\u001a\u00020\u0004H\u0002J\u0006\u0010.\u001a\u00020\u0006J\u0006\u0010/\u001a\u00020\nJ\u0006\u00100\u001a\u00020\u001cJ\b\u00101\u001a\u00020\"H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/uxcam/screenshot/di/ScreenshotModule;", "", "()V", "bitmapCreator", "Lcom/uxcam/screenshot/BitmapCreator;", "bitmapSource", "Lcom/uxcam/screenshot/helper/BitmapSource;", "blackScreenDrawer", "Lcom/uxcam/screenshot/screenshotTaker/BlackScreenDrawerImpl;", "composeOcclusionRepository", "Lcom/uxcam/screenshot/repository/ComposeOcclusionRepository;", "flutterViewFinder", "Lcom/uxcam/screenshot/flutterviewfinder/FlutterViewFinder;", "fullScreenOcclusion", "Lcom/uxcam/screenshot/fullscreenocclusion/FullScreenOcclusionDrawer;", "keyboardOverlayDrawer", "Lcom/uxcam/screenshot/keyboardoverlay/KeyboardOverlayDrawer;", "largestViewRootFilter", "Lcom/uxcam/screenshot/screenshotTaker/LargestViewRootFilter;", "legacyScreenshot", "Lcom/uxcam/screenshot/legacyscreenshot/LegacyScreenshot;", "occlusionRepository", "Lcom/uxcam/screenshot/repository/OcclusionRepository;", "getOcclusionRepository", "()Lcom/uxcam/screenshot/repository/OcclusionRepository;", "pixelCopyScreenshot", "Lcom/uxcam/screenshot/pixelcopyscreenshot/PixelCopyScreenshot;", "screenShotHelper", "Lcom/uxcam/screenshot/helper/ScreenshotHelper;", "screenshotStateHolder", "Lcom/uxcam/screenshot/state/ScreenshotStateHolder;", "getScreenshotStateHolder", "()Lcom/uxcam/screenshot/state/ScreenshotStateHolder;", "screenshotTaker", "Lcom/uxcam/screenshot/screenshotTaker/ScreenshotTaker;", "sensitiveComposableOcclusion", "Lcom/uxcam/screenshot/viewocclusion/SensitiveComposableOcclusion;", "sensitiveViewsFinder", "Lcom/uxcam/screenshot/viewocclusion/SensitiveViewsFinder;", "getSensitiveViewsFinder", "()Lcom/uxcam/screenshot/viewocclusion/SensitiveViewsFinder;", "sensitiveViewsOcclusion", "Lcom/uxcam/screenshot/viewocclusion/SensitiveViewsOcclusion;", "webViewOcclusion", "Lcom/uxcam/screenshot/viewocclusion/WebViewOcclusion;", "getBitmapCreator", "getBitmapSource", "getComposeOcclusionRepository", "getScreenshotHelper", "getScreenshotTaker", "Companion", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ScreenshotModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static ScreenshotModule screenshotModule;
    private BitmapCreator bitmapCreator;
    private BitmapSource bitmapSource;
    private ComposeOcclusionRepository composeOcclusionRepository;
    private ScreenshotHelper screenShotHelper;
    private ScreenshotTaker screenshotTaker;
    private final LegacyScreenshot legacyScreenshot = new LegacyScreenshotImpl();
    private final PixelCopyScreenshot pixelCopyScreenshot = new PixelCopyScreenshotImpl();
    private final FullScreenOcclusionDrawer fullScreenOcclusion = new FullScreenOcclusionDrawerImpl();
    private final WebViewOcclusion webViewOcclusion = new WebViewOcclusionImpl();
    private final SensitiveViewsOcclusion sensitiveViewsOcclusion = new SensitiveViewsOcclusionImpl(new Paint());
    private final SensitiveViewsFinder sensitiveViewsFinder = new SensitiveViewsFinderImpl();
    private final KeyboardOverlayDrawer keyboardOverlayDrawer = new KeyboardOverlayDrawerImpl();
    private final FlutterViewFinder flutterViewFinder = new FlutterViewFinderImpl();
    private final LargestViewRootFilter largestViewRootFilter = new LargestViewRootFilterImpl();
    private final ScreenshotStateHolder screenshotStateHolder = new ScreenshotStateHolderImpl();
    private final SensitiveComposableOcclusion sensitiveComposableOcclusion = new SensitiveComposableOcclusionImpl();
    private final OcclusionRepository occlusionRepository = new OcclusionRepositoryImpl();
    private final BlackScreenDrawerImpl blackScreenDrawer = new BlackScreenDrawerImpl();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/uxcam/screenshot/di/ScreenshotModule$Companion;", "", "()V", "screenshotModule", "Lcom/uxcam/screenshot/di/ScreenshotModule;", "getInstance", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ScreenshotModule getInstance() {
            if (ScreenshotModule.screenshotModule == null) {
                ScreenshotModule.screenshotModule = new ScreenshotModule();
            }
            ScreenshotModule screenshotModule = ScreenshotModule.screenshotModule;
            Intrinsics.checkNotNull(screenshotModule);
            return screenshotModule;
        }
    }

    @JvmStatic
    public static final ScreenshotModule getInstance() {
        return INSTANCE.getInstance();
    }

    public final OcclusionRepository getOcclusionRepository() {
        return this.occlusionRepository;
    }

    public final ScreenshotStateHolder getScreenshotStateHolder() {
        return this.screenshotStateHolder;
    }

    public final SensitiveViewsFinder getSensitiveViewsFinder() {
        return this.sensitiveViewsFinder;
    }

    private final BitmapCreator getBitmapCreator() {
        if (this.bitmapCreator == null) {
            ScreenShotBitmapUtil screenShotBitmapUtil = ScreenShotBitmapUtil.getInstance();
            Intrinsics.checkNotNullExpressionValue(screenShotBitmapUtil, "getInstance()");
            this.bitmapCreator = new BitmapCreatorImpl(screenShotBitmapUtil);
        }
        BitmapCreator bitmapCreator = this.bitmapCreator;
        Intrinsics.checkNotNull(bitmapCreator);
        return bitmapCreator;
    }

    public final BitmapSource getBitmapSource() {
        if (this.bitmapSource == null) {
            this.bitmapSource = new BitmapSource();
        }
        BitmapSource bitmapSource = this.bitmapSource;
        Intrinsics.checkNotNull(bitmapSource);
        return bitmapSource;
    }

    public final ComposeOcclusionRepository getComposeOcclusionRepository() {
        if (this.composeOcclusionRepository == null) {
            this.composeOcclusionRepository = new ComposeOcclusionRepositoryImpl();
        }
        ComposeOcclusionRepository composeOcclusionRepository = this.composeOcclusionRepository;
        Intrinsics.checkNotNull(composeOcclusionRepository);
        return composeOcclusionRepository;
    }

    private final ScreenshotTaker getScreenshotTaker() {
        ScreenshotTaker screenshotTaker = this.screenshotTaker;
        if (screenshotTaker != null) {
            return screenshotTaker;
        }
        ScreenshotTakerImpl screenshotTakerImpl = new ScreenshotTakerImpl(this.pixelCopyScreenshot, this.legacyScreenshot, this.largestViewRootFilter, this.screenshotStateHolder, this.blackScreenDrawer);
        this.screenshotTaker = screenshotTakerImpl;
        Intrinsics.checkNotNull(screenshotTakerImpl);
        return screenshotTakerImpl;
    }

    public final ScreenshotHelper getScreenshotHelper() {
        ScreenshotHelper screenshotHelper = this.screenShotHelper;
        if (screenshotHelper != null) {
            return screenshotHelper;
        }
        ScreenshotStateHolder screenshotStateHolder = this.screenshotStateHolder;
        ScreenshotTaker screenshotTaker = getScreenshotTaker();
        SensitiveViewsFinder sensitiveViewsFinder = this.sensitiveViewsFinder;
        KeyboardOverlayDrawer keyboardOverlayDrawer = this.keyboardOverlayDrawer;
        FlutterViewFinder flutterViewFinder = this.flutterViewFinder;
        FullScreenOcclusionDrawer fullScreenOcclusionDrawer = this.fullScreenOcclusion;
        SensitiveViewsOcclusion sensitiveViewsOcclusion = this.sensitiveViewsOcclusion;
        WebViewOcclusion webViewOcclusion = this.webViewOcclusion;
        SensitiveComposableOcclusion sensitiveComposableOcclusion = this.sensitiveComposableOcclusion;
        ScreenShotBitmapUtil screenShotBitmapUtil = ScreenShotBitmapUtil.getInstance();
        Intrinsics.checkNotNullExpressionValue(screenShotBitmapUtil, "getInstance()");
        ScreenShotHelperImpl screenShotHelperImpl = new ScreenShotHelperImpl(screenshotStateHolder, screenshotTaker, sensitiveViewsFinder, keyboardOverlayDrawer, flutterViewFinder, fullScreenOcclusionDrawer, sensitiveViewsOcclusion, webViewOcclusion, sensitiveComposableOcclusion, screenShotBitmapUtil, getComposeOcclusionRepository(), this.occlusionRepository, getBitmapCreator(), Util.isClass("io.flutter.app.FlutterApplication") || Util.isClass("io.flutter.embedding.android.FlutterView") || Util.isClass("io.flutter.embedding.engine.FlutterJNI"), getBitmapSource());
        this.screenShotHelper = screenShotHelperImpl;
        Intrinsics.checkNotNull(screenShotHelperImpl);
        return screenShotHelperImpl;
    }
}
