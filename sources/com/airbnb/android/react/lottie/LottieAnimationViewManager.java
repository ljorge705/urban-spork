package com.airbnb.android.react.lottie;

import android.animation.Animator;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.RenderMode;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes5.dex */
class LottieAnimationViewManager extends SimpleViewManager<LottieAnimationView> {
    private static final int COMMAND_PAUSE = 3;
    private static final int COMMAND_PLAY = 1;
    private static final int COMMAND_RESET = 2;
    private static final int COMMAND_RESUME = 4;
    private static final String REACT_CLASS = "LottieAnimationView";
    private static final String TAG = "LottieAnimationViewManager";
    private static final int VERSION = 1;
    private Map<LottieAnimationView, LottieAnimationViewPropertyManager> propManagersMap = new WeakHashMap();

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    LottieAnimationViewManager() {
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.builder().put("VERSION", 1).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public LottieAnimationView createViewInstance(ThemedReactContext themedReactContext) {
        final LottieAnimationView lottieAnimationView = new LottieAnimationView(themedReactContext);
        lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                LottieAnimationViewManager.this.sendOnAnimationFinishEvent(lottieAnimationView, false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                LottieAnimationViewManager.this.sendOnAnimationFinishEvent(lottieAnimationView, true);
            }
        });
        return lottieAnimationView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOnAnimationFinishEvent(LottieAnimationView lottieAnimationView, boolean z) {
        ReactContext reactContext;
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean("isCancelled", z);
        Context context = lottieAnimationView.getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                reactContext = null;
                break;
            } else {
                if (context instanceof ReactContext) {
                    reactContext = (ReactContext) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        if (reactContext != null) {
            ((RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(lottieAnimationView.getId(), "animationFinish", writableMapCreateMap);
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("animationFinish", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onAnimationFinish"))).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("play", 1, "reset", 2, "pause", 3, "resume", 4);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(final LottieAnimationView lottieAnimationView, int i, final ReadableArray readableArray) {
        if (i == 1) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager.2
                @Override // java.lang.Runnable
                public void run() {
                    int i2 = readableArray.getInt(0);
                    int i3 = readableArray.getInt(1);
                    if (i2 != -1 && i3 != -1) {
                        if (i2 > i3) {
                            lottieAnimationView.setMinAndMaxFrame(i3, i2);
                            if (lottieAnimationView.getSpeed() > 0.0f) {
                                lottieAnimationView.reverseAnimationSpeed();
                            }
                        } else {
                            lottieAnimationView.setMinAndMaxFrame(i2, i3);
                            if (lottieAnimationView.getSpeed() < 0.0f) {
                                lottieAnimationView.reverseAnimationSpeed();
                            }
                        }
                    }
                    if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                        lottieAnimationView.setProgress(0.0f);
                        lottieAnimationView.playAnimation();
                    } else {
                        lottieAnimationView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager.2.1
                            @Override // android.view.View.OnAttachStateChangeListener
                            public void onViewAttachedToWindow(View view) {
                                LottieAnimationView lottieAnimationView2 = (LottieAnimationView) view;
                                lottieAnimationView2.setProgress(0.0f);
                                lottieAnimationView2.playAnimation();
                                lottieAnimationView2.removeOnAttachStateChangeListener(this);
                            }

                            @Override // android.view.View.OnAttachStateChangeListener
                            public void onViewDetachedFromWindow(View view) {
                                lottieAnimationView.removeOnAttachStateChangeListener(this);
                            }
                        });
                    }
                }
            });
            return;
        }
        if (i == 2) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager.3
                @Override // java.lang.Runnable
                public void run() {
                    if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                        lottieAnimationView.cancelAnimation();
                        lottieAnimationView.setProgress(0.0f);
                    }
                }
            });
        } else if (i == 3) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager.4
                @Override // java.lang.Runnable
                public void run() {
                    if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                        lottieAnimationView.pauseAnimation();
                    }
                }
            });
        } else {
            if (i != 4) {
                return;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager.5
                @Override // java.lang.Runnable
                public void run() {
                    if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                        lottieAnimationView.resumeAnimation();
                    }
                }
            });
        }
    }

    @ReactProp(name = "sourceName")
    public void setSourceName(LottieAnimationView lottieAnimationView, String str) {
        if (!str.contains(".")) {
            str = str + ".json";
        }
        getOrCreatePropertyManager(lottieAnimationView).setAnimationName(str);
    }

    @ReactProp(name = "sourceJson")
    public void setSourceJson(LottieAnimationView lottieAnimationView, String str) {
        getOrCreatePropertyManager(lottieAnimationView).setAnimationJson(str);
    }

    @ReactProp(name = "sourceURL")
    public void setSourceURL(final LottieAnimationView lottieAnimationView, final String str) {
        new Thread(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager.6
            @Override // java.lang.Runnable
            public void run() throws IOException {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(str).openStream()));
                    final String str2 = "";
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            str2 = str2 + line;
                        } else {
                            bufferedReader.close();
                            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManager.6.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    LottieAnimationViewManager.this.getOrCreatePropertyManager(lottieAnimationView).setAnimationJson(str2);
                                    LottieAnimationViewManager.this.getOrCreatePropertyManager(lottieAnimationView).commitChanges();
                                }
                            });
                            return;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error loading animation from URL: " + e);
                }
            }
        }).start();
    }

    @ReactProp(name = "cacheComposition")
    public void setCacheComposition(LottieAnimationView lottieAnimationView, boolean z) {
        lottieAnimationView.setCacheComposition(z);
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(LottieAnimationView lottieAnimationView, String str) {
        ImageView.ScaleType scaleType;
        if ("cover".equals(str)) {
            scaleType = ImageView.ScaleType.CENTER_CROP;
        } else if ("contain".equals(str)) {
            scaleType = ImageView.ScaleType.CENTER_INSIDE;
        } else {
            scaleType = "center".equals(str) ? ImageView.ScaleType.CENTER : null;
        }
        getOrCreatePropertyManager(lottieAnimationView).setScaleType(scaleType);
    }

    @ReactProp(name = "renderMode")
    public void setRenderMode(LottieAnimationView lottieAnimationView, String str) {
        RenderMode renderMode;
        if ("AUTOMATIC".equals(str)) {
            renderMode = RenderMode.AUTOMATIC;
        } else if ("HARDWARE".equals(str)) {
            renderMode = RenderMode.HARDWARE;
        } else {
            renderMode = "SOFTWARE".equals(str) ? RenderMode.SOFTWARE : null;
        }
        getOrCreatePropertyManager(lottieAnimationView).setRenderMode(renderMode);
    }

    @ReactProp(name = "progress")
    public void setProgress(LottieAnimationView lottieAnimationView, float f) {
        getOrCreatePropertyManager(lottieAnimationView).setProgress(Float.valueOf(f));
    }

    @ReactProp(name = "speed")
    public void setSpeed(LottieAnimationView lottieAnimationView, double d) {
        getOrCreatePropertyManager(lottieAnimationView).setSpeed((float) d);
    }

    @ReactProp(name = "loop")
    public void setLoop(LottieAnimationView lottieAnimationView, boolean z) {
        getOrCreatePropertyManager(lottieAnimationView).setLoop(z);
    }

    @ReactProp(name = "imageAssetsFolder")
    public void setImageAssetsFolder(LottieAnimationView lottieAnimationView, String str) {
        getOrCreatePropertyManager(lottieAnimationView).setImageAssetsFolder(str);
    }

    @ReactProp(name = "enableMergePathsAndroidForKitKatAndAbove")
    public void setEnableMergePaths(LottieAnimationView lottieAnimationView, boolean z) {
        getOrCreatePropertyManager(lottieAnimationView).setEnableMergePaths(z);
    }

    @ReactProp(name = "colorFilters")
    public void setColorFilters(LottieAnimationView lottieAnimationView, ReadableArray readableArray) {
        getOrCreatePropertyManager(lottieAnimationView).setColorFilters(readableArray);
    }

    @ReactProp(name = "textFiltersAndroid")
    public void setTextFilters(LottieAnimationView lottieAnimationView, ReadableArray readableArray) {
        getOrCreatePropertyManager(lottieAnimationView).setTextFilters(readableArray);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(LottieAnimationView lottieAnimationView) {
        super.onAfterUpdateTransaction((LottieAnimationViewManager) lottieAnimationView);
        getOrCreatePropertyManager(lottieAnimationView).commitChanges();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LottieAnimationViewPropertyManager getOrCreatePropertyManager(LottieAnimationView lottieAnimationView) {
        LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager = this.propManagersMap.get(lottieAnimationView);
        if (lottieAnimationViewPropertyManager != null) {
            return lottieAnimationViewPropertyManager;
        }
        LottieAnimationViewPropertyManager lottieAnimationViewPropertyManager2 = new LottieAnimationViewPropertyManager(lottieAnimationView);
        this.propManagersMap.put(lottieAnimationView, lottieAnimationViewPropertyManager2);
        return lottieAnimationViewPropertyManager2;
    }
}
