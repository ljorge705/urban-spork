package io.sentry.android.replay.util;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

/* loaded from: classes6.dex */
public class FixedWindowCallback implements Window.Callback {
    public final Window.Callback delegate;

    public FixedWindowCallback(Window.Callback callback) {
        this.delegate = callback;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int i) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return null;
        }
        return callback.onCreatePanelView(i);
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.onCreatePanelMenu(i, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.onPreparePanel(i, view, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.onMenuOpened(i, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.onMenuItemSelected(i, menuItem);
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    public void onContentChanged() {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onContentChanged();
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onWindowFocusChanged(z);
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onPanelClosed(i, menu);
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent searchEvent) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return false;
        }
        return callback.onSearchRequested(searchEvent);
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        Window.Callback callback2 = this.delegate;
        if (callback2 == null) {
            return null;
        }
        return callback2.onWindowStartingActionMode(callback);
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        Window.Callback callback2 = this.delegate;
        if (callback2 == null) {
            return null;
        }
        return callback2.onWindowStartingActionMode(callback, i);
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onProvideKeyboardShortcuts(list, menu, i);
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
        Window.Callback callback = this.delegate;
        if (callback == null) {
            return;
        }
        callback.onPointerCaptureChanged(z);
    }
}
