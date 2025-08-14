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
public class MapUrlTileManager extends ViewGroupManager<MapUrlTile> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapUrlTile";
    }

    public MapUrlTileManager(ReactApplicationContext reactApplicationContext) {
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(new DisplayMetrics());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public MapUrlTile createViewInstance(ThemedReactContext themedReactContext) {
        return new MapUrlTile(themedReactContext);
    }

    @ReactProp(name = "urlTemplate")
    public void setUrlTemplate(MapUrlTile mapUrlTile, String str) {
        mapUrlTile.setUrlTemplate(str);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = GroundOverlayOptions.NO_DIMENSION, name = ViewProps.Z_INDEX)
    public void setZIndex(MapUrlTile mapUrlTile, float f) {
        mapUrlTile.setZIndex(f);
    }

    @ReactProp(defaultFloat = 0.0f, name = "minimumZ")
    public void setMinimumZ(MapUrlTile mapUrlTile, float f) {
        mapUrlTile.setMinimumZ(f);
    }

    @ReactProp(defaultFloat = FaceAlignmentChecks.FACE_AND_OVAL_CENTER_X_ALIGNMENT_DELTA, name = "maximumZ")
    public void setMaximumZ(MapUrlTile mapUrlTile, float f) {
        mapUrlTile.setMaximumZ(f);
    }

    @ReactProp(defaultFloat = FaceAlignmentChecks.FACE_AND_OVAL_CENTER_X_ALIGNMENT_DELTA, name = "maximumNativeZ")
    public void setMaximumNativeZ(MapUrlTile mapUrlTile, float f) {
        mapUrlTile.setMaximumNativeZ(f);
    }

    @ReactProp(defaultBoolean = false, name = "flipY")
    public void setFlipY(MapUrlTile mapUrlTile, boolean z) {
        mapUrlTile.setFlipY(z);
    }

    @ReactProp(defaultFloat = 256.0f, name = "tileSize")
    public void setTileSize(MapUrlTile mapUrlTile, float f) {
        mapUrlTile.setTileSize(f);
    }

    @ReactProp(defaultBoolean = false, name = "doubleTileSize")
    public void setDoubleTileSize(MapUrlTile mapUrlTile, boolean z) {
        mapUrlTile.setDoubleTileSize(z);
    }

    @ReactProp(name = "tileCachePath")
    public void setTileCachePath(MapUrlTile mapUrlTile, String str) {
        mapUrlTile.setTileCachePath(str);
    }

    @ReactProp(defaultFloat = 0.0f, name = "tileCacheMaxAge")
    public void setTileCacheMaxAge(MapUrlTile mapUrlTile, float f) {
        mapUrlTile.setTileCacheMaxAge(f);
    }

    @ReactProp(defaultBoolean = false, name = "offlineMode")
    public void setOfflineMode(MapUrlTile mapUrlTile, boolean z) {
        mapUrlTile.setOfflineMode(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(MapUrlTile mapUrlTile, float f) {
        mapUrlTile.setOpacity(f);
    }
}
