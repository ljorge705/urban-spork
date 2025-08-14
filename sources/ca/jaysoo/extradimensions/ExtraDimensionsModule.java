package ca.jaysoo.extradimensions;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ExtraDimensionsModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private ReactContext mReactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "ExtraDimensions";
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    public ExtraDimensionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mReactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HashMap map = new HashMap();
        DisplayMetrics displayMetrics = getReactApplicationContext().getResources().getDisplayMetrics();
        try {
            Display.class.getMethod("getRealMetrics", DisplayMetrics.class).invoke(((WindowManager) this.mReactContext.getSystemService("window")).getDefaultDisplay(), displayMetrics);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        map.put("REAL_WINDOW_HEIGHT", Float.valueOf(getRealHeight(displayMetrics)));
        map.put("REAL_WINDOW_WIDTH", Float.valueOf(getRealWidth(displayMetrics)));
        map.put("STATUS_BAR_HEIGHT", Float.valueOf(getStatusBarHeight(displayMetrics)));
        map.put("SOFT_MENU_BAR_HEIGHT", Float.valueOf(getSoftMenuBarHeight(displayMetrics)));
        map.put("SMART_BAR_HEIGHT", Float.valueOf(getSmartBarHeight(displayMetrics)));
        map.put("SOFT_MENU_BAR_ENABLED", Boolean.valueOf(hasPermanentMenuKey()));
        return map;
    }

    private boolean hasPermanentMenuKey() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        int identifier = reactApplicationContext.getResources().getIdentifier("config_showNavigationBar", "bool", Constants.KEY_ANDROID);
        return identifier <= 0 || !reactApplicationContext.getResources().getBoolean(identifier);
    }

    private float getStatusBarHeight(DisplayMetrics displayMetrics) {
        if (getReactApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", Constants.KEY_ANDROID) > 0) {
            return r0.getResources().getDimensionPixelSize(r1) / displayMetrics.density;
        }
        return 0.0f;
    }

    private float getSoftMenuBarHeight(DisplayMetrics displayMetrics) {
        if (hasPermanentMenuKey()) {
            return 0.0f;
        }
        if (getReactApplicationContext().getResources().getIdentifier("navigation_bar_height", "dimen", Constants.KEY_ANDROID) > 0) {
            return r0.getResources().getDimensionPixelSize(r2) / displayMetrics.density;
        }
        return 0.0f;
    }

    private float getRealHeight(DisplayMetrics displayMetrics) {
        return displayMetrics.heightPixels / displayMetrics.density;
    }

    private float getRealWidth(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels / displayMetrics.density;
    }

    private float getSmartBarHeight(DisplayMetrics displayMetrics) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        boolean zEquals = Build.MANUFACTURER.equals("Meizu");
        boolean z = Settings.System.getInt(reactApplicationContext.getContentResolver(), "mz_smartbar_auto_hide", 0) == 1;
        if (!zEquals || z) {
            return 0.0f;
        }
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return reactApplicationContext.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("mz_action_button_min_height").get(cls.newInstance()).toString())) / displayMetrics.density;
        } catch (Throwable unused) {
            return getNormalNavigationBarHeight(reactApplicationContext) / displayMetrics.density;
        }
    }

    protected static float getNormalNavigationBarHeight(Context context) {
        int identifier;
        try {
            Resources resources = context.getResources();
            int identifier2 = resources.getIdentifier("config_showNavigationBar", "bool", Constants.KEY_ANDROID);
            if (identifier2 > 0 && resources.getBoolean(identifier2) && (identifier = resources.getIdentifier("navigation_bar_height", "dimen", Constants.KEY_ANDROID)) > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
        } catch (Throwable unused) {
        }
        return 0.0f;
    }
}
