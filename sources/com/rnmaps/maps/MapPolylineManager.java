package com.rnmaps.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.core.internal.view.SupportMenu;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.gms.maps.model.SquareCap;
import java.util.Map;

/* loaded from: classes6.dex */
public class MapPolylineManager extends ViewGroupManager<MapPolyline> {
    private final DisplayMetrics metrics;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapPolyline";
    }

    public MapPolylineManager(ReactApplicationContext reactApplicationContext) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.metrics = displayMetrics;
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public MapPolyline createViewInstance(ThemedReactContext themedReactContext) {
        return new MapPolyline(themedReactContext);
    }

    @ReactProp(name = "coordinates")
    public void setCoordinate(MapPolyline mapPolyline, ReadableArray readableArray) {
        mapPolyline.setCoordinates(readableArray);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(MapPolyline mapPolyline, float f) {
        mapPolyline.setWidth(this.metrics.density * f);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "strokeColor")
    public void setStrokeColor(MapPolyline mapPolyline, int i) {
        mapPolyline.setColor(i);
    }

    @ReactProp(defaultBoolean = false, name = "tappable")
    public void setTappable(MapPolyline mapPolyline, boolean z) {
        mapPolyline.setTappable(z);
    }

    @ReactProp(defaultBoolean = false, name = "geodesic")
    public void setGeodesic(MapPolyline mapPolyline, boolean z) {
        mapPolyline.setGeodesic(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.Z_INDEX)
    public void setZIndex(MapPolyline mapPolyline, float f) {
        mapPolyline.setZIndex(f);
    }

    @ReactProp(name = "lineCap")
    public void setlineCap(MapPolyline mapPolyline, String str) {
        Cap squareCap;
        str.hashCode();
        switch (str) {
            case "square":
                squareCap = new SquareCap();
                break;
            case "butt":
                squareCap = new ButtCap();
                break;
            case "round":
                squareCap = new RoundCap();
                break;
            default:
                squareCap = new RoundCap();
                break;
        }
        mapPolyline.setLineCap(squareCap);
    }

    @ReactProp(name = "lineDashPattern")
    public void setLineDashPattern(MapPolyline mapPolyline, ReadableArray readableArray) {
        mapPolyline.setLineDashPattern(readableArray);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("onPress", MapBuilder.of("registrationName", "onPress"));
    }
}
