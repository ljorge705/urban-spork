package com.paygilant.PG_FraudDetection_SDK;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes6.dex */
class fdwipeifdmvqsbqrrpyp implements Window.Callback {
    static int ctfsaqlysacfjtqixtmr;
    private boolean dbuymyhwehsdoxafsfpy;
    private boolean uusbetktgiikylwfbevs;
    private Window.Callback yvrzbryuycempgkdhpvj;

    public fdwipeifdmvqsbqrrpyp(Window.Callback callback, boolean z, boolean z2) {
        this.yvrzbryuycempgkdhpvj = callback;
        this.dbuymyhwehsdoxafsfpy = z;
        this.uusbetktgiikylwfbevs = z2;
        ctfsaqlysacfjtqixtmr++;
    }

    private void dbuymyhwehsdoxafsfpy() {
        oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().yvrzbryuycempgkdhpvj(Boolean.TRUE);
        this.dbuymyhwehsdoxafsfpy = true;
    }

    private void uusbetktgiikylwfbevs() {
        oacciftezlubzxpkwvyc.mxodkqpwhcryvsgsvabl().dbuymyhwehsdoxafsfpy(Boolean.TRUE);
        this.uusbetktgiikylwfbevs = true;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.yvrzbryuycempgkdhpvj.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.yvrzbryuycempgkdhpvj.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return this.yvrzbryuycempgkdhpvj.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.yvrzbryuycempgkdhpvj.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        yvrzbryuycempgkdhpvj(motionEvent);
        return this.yvrzbryuycempgkdhpvj.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        this.yvrzbryuycempgkdhpvj.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        this.yvrzbryuycempgkdhpvj.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
        this.yvrzbryuycempgkdhpvj.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public void onContentChanged() {
        this.yvrzbryuycempgkdhpvj.onContentChanged();
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        return this.yvrzbryuycempgkdhpvj.onCreatePanelMenu(i, menu);
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int i) {
        return this.yvrzbryuycempgkdhpvj.onCreatePanelView(i);
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.yvrzbryuycempgkdhpvj.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.yvrzbryuycempgkdhpvj.onMenuItemSelected(i, menuItem);
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return this.yvrzbryuycempgkdhpvj.onMenuOpened(i, menu);
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        this.yvrzbryuycempgkdhpvj.onPanelClosed(i, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        return this.yvrzbryuycempgkdhpvj.onPreparePanel(i, view, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        return this.yvrzbryuycempgkdhpvj.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.yvrzbryuycempgkdhpvj.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        this.yvrzbryuycempgkdhpvj.onWindowFocusChanged(z);
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return onWindowStartingActionMode(callback);
    }

    public Window.Callback yvrzbryuycempgkdhpvj() {
        return this.yvrzbryuycempgkdhpvj;
    }

    private void yvrzbryuycempgkdhpvj(MotionEvent motionEvent) {
        if (this.dbuymyhwehsdoxafsfpy && this.uusbetktgiikylwfbevs) {
            return;
        }
        int actionMasked = motionEvent.getActionMasked();
        int pointerCount = motionEvent.getPointerCount();
        if (actionMasked == 0 || actionMasked == 5) {
            for (int i = 0; i < pointerCount; i++) {
                int toolType = motionEvent.getToolType(i);
                if (toolType == 3 && !this.dbuymyhwehsdoxafsfpy) {
                    dbuymyhwehsdoxafsfpy();
                } else if (toolType == 0 && !this.uusbetktgiikylwfbevs) {
                    uusbetktgiikylwfbevs();
                }
            }
        }
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.yvrzbryuycempgkdhpvj.onSearchRequested(searchEvent);
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return this.yvrzbryuycempgkdhpvj.onWindowStartingActionMode(callback, i);
    }
}
