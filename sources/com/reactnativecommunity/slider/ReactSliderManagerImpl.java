package com.reactnativecommunity.slider;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class ReactSliderManagerImpl {
    public static final String REACT_CLASS = "RNCSlider";

    public static ReactSlider createViewInstance(ThemedReactContext themedReactContext) {
        ReactSlider reactSlider = new ReactSlider(themedReactContext, null);
        reactSlider.setSplitTrack(false);
        return reactSlider;
    }

    public static void setValue(ReactSlider reactSlider, double d) {
        if (reactSlider.isSliding()) {
            return;
        }
        reactSlider.setValue(d);
        if (!reactSlider.isAccessibilityFocused() || Build.VERSION.SDK_INT <= 29) {
            return;
        }
        reactSlider.setupAccessibility((int) d);
    }

    public static void setMinimumValue(ReactSlider reactSlider, float f) {
        reactSlider.setMinValue(f);
    }

    public static void setMaximumValue(ReactSlider reactSlider, float f) {
        reactSlider.setMaxValue(f);
    }

    public static void setLowerLimit(ReactSlider reactSlider, double d) {
        reactSlider.setLowerLimit(d);
    }

    public static void setUpperLimit(ReactSlider reactSlider, double d) {
        reactSlider.setUpperLimit(d);
    }

    public static void setStep(ReactSlider reactSlider, float f) {
        reactSlider.setStep(f);
    }

    public static void setDisabled(ReactSlider reactSlider, boolean z) {
        reactSlider.setEnabled(!z);
    }

    public static void setThumbTintColor(ReactSlider reactSlider, Integer num) {
        if (reactSlider.getThumb() != null) {
            if (num == null) {
                reactSlider.getThumb().clearColorFilter();
            } else {
                reactSlider.getThumb().setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    public static void setMinimumTrackTintColor(ReactSlider reactSlider, Integer num) {
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(android.R.id.progress);
        if (num == null) {
            drawableFindDrawableByLayerId.clearColorFilter();
        } else if (Build.VERSION.SDK_INT > 28) {
            drawableFindDrawableByLayerId.setColorFilter(new PorterDuffColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN));
        } else {
            drawableFindDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    public static void setThumbImage(ReactSlider reactSlider, @Nullable ReadableMap readableMap) {
        reactSlider.setThumbImage(readableMap != null ? readableMap.getString(ReactNativeBlobUtilConst.DATA_ENCODE_URI) : null);
    }

    public static void setMaximumTrackTintColor(ReactSlider reactSlider, Integer num) {
        Drawable drawableFindDrawableByLayerId = ((LayerDrawable) reactSlider.getProgressDrawable().getCurrent()).findDrawableByLayerId(android.R.id.background);
        if (num == null) {
            drawableFindDrawableByLayerId.clearColorFilter();
        } else if (Build.VERSION.SDK_INT > 28) {
            drawableFindDrawableByLayerId.setColorFilter(new PorterDuffColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN));
        } else {
            drawableFindDrawableByLayerId.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    public static void setInverted(ReactSlider reactSlider, boolean z) {
        if (z) {
            reactSlider.setScaleX(-1.0f);
        } else {
            reactSlider.setScaleX(1.0f);
        }
    }

    public static void setAccessibilityUnits(ReactSlider reactSlider, String str) {
        reactSlider.setAccessibilityUnits(str);
    }

    public static void setAccessibilityIncrements(ReactSlider reactSlider, ReadableArray readableArray) {
        ArrayList<Object> arrayList = readableArray.toArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Object> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((String) it.next());
        }
        reactSlider.setAccessibilityIncrements(arrayList2);
    }

    public static Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(ReactSlidingCompleteEvent.EVENT_NAME, MapBuilder.of("registrationName", "onRNCSliderSlidingComplete"), ReactSlidingStartEvent.EVENT_NAME, MapBuilder.of("registrationName", "onRNCSliderSlidingStart"));
    }
}
