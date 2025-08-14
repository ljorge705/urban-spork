package com.reactnativecommunity.art;

import android.view.Surface;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;

@ReactModule(name = ARTSurfaceViewManager.REACT_CLASS)
/* loaded from: classes6.dex */
public class ARTSurfaceViewManager extends BaseViewManager<ARTSurfaceView, ARTSurfaceViewShadowNode> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = new YogaMeasureFunction() { // from class: com.reactnativecommunity.art.ARTSurfaceViewManager.1
        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    };
    public static final String REACT_CLASS = "ARTSurfaceView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBackgroundColor(ARTSurfaceView aRTSurfaceView, int i) {
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ARTSurfaceViewShadowNode createShadowNodeInstance() {
        ARTSurfaceViewShadowNode aRTSurfaceViewShadowNode = new ARTSurfaceViewShadowNode();
        aRTSurfaceViewShadowNode.setMeasureFunction(MEASURE_FUNCTION);
        return aRTSurfaceViewShadowNode;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<ARTSurfaceViewShadowNode> getShadowNodeClass() {
        return ARTSurfaceViewShadowNode.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ARTSurfaceView createViewInstance(ThemedReactContext themedReactContext) {
        return new ARTSurfaceView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(ARTSurfaceView aRTSurfaceView, Object obj) throws Surface.OutOfResourcesException, IllegalArgumentException {
        ((ARTSurfaceViewShadowNode) obj).setupSurfaceTextureListener(aRTSurfaceView);
    }
}
