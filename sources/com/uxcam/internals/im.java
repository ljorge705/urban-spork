package com.uxcam.internals;

import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;
import com.uxcam.screenaction.utils.ReflectionUtil;
import com.uxcam.screenshot.state.ScreenshotStateHolder;

/* loaded from: classes6.dex */
public final class im implements ik {

    /* renamed from: a, reason: collision with root package name */
    public final ScreenshotStateHolder f212a;

    public im(ScreenshotStateHolder screenshotStateHolder) {
        this.f212a = screenshotStateHolder;
    }

    @Override // com.uxcam.internals.ik
    public final void a(View view, he heVar) {
        try {
            Window window = !this.f212a.isFlutter() ? (Window) ReflectionUtil.getFieldValue("mWindow", view) : (Window) ReflectionUtil.getFieldValueFlutter("mWindow", view);
            if (window != null) {
                Window.Callback callback = window.getCallback();
                if ((callback instanceof hu) || callback == null) {
                    return;
                }
                window.setCallback(new hu(callback, heVar));
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
            try {
                PopupWindow popupWindow = !this.f212a.isFlutter() ? (PopupWindow) ReflectionUtil.getFieldValue("mWindow", view) : (PopupWindow) ReflectionUtil.getFieldValueFlutter("mWindow", view);
                if (popupWindow != null) {
                    popupWindow.setTouchInterceptor(new il());
                }
            } catch (Exception unused) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
