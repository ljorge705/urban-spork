package com.rnmaps.maps;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class MapsPackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MapModule(reactApplicationContext));
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        MapManager mapManager = new MapManager(reactApplicationContext);
        MapMarkerManager mapMarkerManager = new MapMarkerManager();
        mapManager.setMarkerManager(mapMarkerManager);
        ArrayList arrayList = new ArrayList();
        arrayList.add(mapManager);
        arrayList.add(mapMarkerManager);
        arrayList.add(new MapCalloutManager());
        arrayList.add(new MapPolylineManager(reactApplicationContext));
        arrayList.add(new MapGradientPolylineManager(reactApplicationContext));
        arrayList.add(new MapPolygonManager(reactApplicationContext));
        arrayList.add(new MapCircleManager(reactApplicationContext));
        arrayList.add(new MapUrlTileManager(reactApplicationContext));
        arrayList.add(new MapWMSTileManager(reactApplicationContext));
        arrayList.add(new MapLocalTileManager(reactApplicationContext));
        arrayList.add(new MapOverlayManager(reactApplicationContext));
        arrayList.add(new MapHeatmapManager());
        return arrayList;
    }
}
