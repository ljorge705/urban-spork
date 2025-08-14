package com.rnmaps.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.FaceAlignmentChecks;

/* loaded from: classes6.dex */
public class MapWMSTileManager extends ViewGroupManager<MapWMSTile> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapWMSTile";
    }

    public MapWMSTileManager(ReactApplicationContext reactApplicationContext) {
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(new DisplayMetrics());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public MapWMSTile createViewInstance(ThemedReactContext themedReactContext) {
        return new MapWMSTile(themedReactContext);
    }

    @ReactProp(name = "urlTemplate")
    public void setUrlTemplate(MapWMSTile mapWMSTile, String str) {
        mapWMSTile.setUrlTemplate(str);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = GroundOverlayOptions.NO_DIMENSION, name = ViewProps.Z_INDEX)
    public void setZIndex(MapWMSTile mapWMSTile, float f) {
        mapWMSTile.setZIndex(f);
    }

    @ReactProp(defaultFloat = 0.0f, name = "minimumZ")
    public void setMinimumZ(MapWMSTile mapWMSTile, float f) {
        mapWMSTile.setMinimumZ(f);
    }

    @ReactProp(defaultFloat = FaceAlignmentChecks.FACE_AND_OVAL_CENTER_X_ALIGNMENT_DELTA, name = "maximumZ")
    public void setMaximumZ(MapWMSTile mapWMSTile, float f) {
        mapWMSTile.setMaximumZ(f);
    }

    @ReactProp(defaultFloat = FaceAlignmentChecks.FACE_AND_OVAL_CENTER_X_ALIGNMENT_DELTA, name = "maximumNativeZ")
    public void setMaximumNativeZ(MapWMSTile mapWMSTile, float f) {
        mapWMSTile.setMaximumNativeZ(f);
    }

    @ReactProp(defaultFloat = 256.0f, name = "tileSize")
    public void setTileSize(MapWMSTile mapWMSTile, float f) {
        mapWMSTile.setTileSize(f);
    }

    @ReactProp(name = "tileCachePath")
    public void setTileCachePath(MapWMSTile mapWMSTile, String str) {
        mapWMSTile.setTileCachePath(str);
    }

    @ReactProp(defaultFloat = 0.0f, name = "tileCacheMaxAge")
    public void setTileCacheMaxAge(MapWMSTile mapWMSTile, float f) {
        mapWMSTile.setTileCacheMaxAge(f);
    }

    @ReactProp(defaultBoolean = false, name = "offlineMode")
    public void setOfflineMode(MapWMSTile mapWMSTile, boolean z) {
        mapWMSTile.setOfflineMode(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(MapWMSTile mapWMSTile, float f) {
        mapWMSTile.setOpacity(f);
    }
}
