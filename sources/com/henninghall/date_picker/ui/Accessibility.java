package com.henninghall.date_picker.ui;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.henninghall.date_picker.DatePickerPackage;
import com.henninghall.date_picker.Utils;
import com.henninghall.date_picker.pickers.Picker;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class Accessibility {
    private static final AccessibilityManager systemManager = (AccessibilityManager) DatePickerPackage.context.getApplicationContext().getSystemService("accessibility");
    private static Locale mLocale = Locale.getDefault();

    public static Locale getLocale() {
        return mLocale;
    }

    public static void setLocale(Locale locale) {
        mLocale = locale;
    }

    public static boolean shouldAllowScroll(View view) {
        if (systemManager.isTouchExplorationEnabled()) {
            return view.isAccessibilityFocused();
        }
        return true;
    }

    public static void startAccessibilityDelegate(final Picker picker) {
        picker.getView().setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.henninghall.date_picker.ui.Accessibility.1
            @Override // android.view.View.AccessibilityDelegate
            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                int value = picker.getValue();
                if (i == 4096) {
                    if (!picker.isSpinning()) {
                        picker.smoothScrollToValue(value - 1);
                    }
                } else if (i == 8192 && !picker.isSpinning()) {
                    picker.smoothScrollToValue(value + 1);
                }
                return super.performAccessibilityAction(view, i, bundle);
            }
        });
    }

    public static boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = systemManager;
        if (accessibilityManager == null) {
            return false;
        }
        return accessibilityManager.isEnabled();
    }

    public static boolean isSpokenFeedbackEnabled() {
        return hasAccessibilityFeatureTypeEnabled(1);
    }

    private static boolean hasAccessibilityFeatureTypeEnabled(int i) {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = systemManager.getEnabledAccessibilityServiceList(i);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.size() > 0;
    }

    public static void announce(String str, View view) {
        if (isAccessibilityEnabled() && isSpokenFeedbackEnabled()) {
            view.announceForAccessibility(str);
        }
    }

    private static String pickerValueToDisplayedValue(Picker picker, int i) {
        String str = picker.getDisplayedValues()[i];
        return str != null ? str : String.valueOf(i);
    }

    public static void sendValueChangedEvent(Picker picker, int i) {
        AccessibilityEvent accessibilityEventBuildEvent = buildEvent(picker.getView(), 32);
        accessibilityEventBuildEvent.getText().add(pickerValueToDisplayedValue(picker, i));
        sendEvent(accessibilityEventBuildEvent);
    }

    private static String getContentDescriptionLabel(String str) {
        return Utils.getLocalisedStringFromResources(getLocale(), str + "_description");
    }

    public static String getContentDescription(Picker picker) {
        String string = picker.getView().getTag().toString();
        return pickerValueToDisplayedValue(picker, picker.getValue()) + ", " + getContentDescriptionLabel(string);
    }

    public static AccessibilityEvent buildEvent(View view, int i) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i);
        accessibilityEventObtain.setClassName(view.getClass().getName());
        accessibilityEventObtain.setPackageName(view.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    public static void sendEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityManager accessibilityManager = systemManager;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        accessibilityManager.sendAccessibilityEvent(accessibilityEvent);
    }

    public static void setRoleToSlider(Picker picker, AccessibilityNodeInfo accessibilityNodeInfo) {
        accessibilityNodeInfo.setClassName("android.widget.SeekBar");
        accessibilityNodeInfo.setScrollable(true);
        accessibilityNodeInfo.setContentDescription(getContentDescription(picker));
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction = new AccessibilityNodeInfo.AccessibilityAction(4096, "Increase value");
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction2 = new AccessibilityNodeInfo.AccessibilityAction(8192, "Decrease value");
        accessibilityNodeInfo.addAction(accessibilityAction);
        accessibilityNodeInfo.addAction(accessibilityAction2);
    }
}
