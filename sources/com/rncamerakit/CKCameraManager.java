package com.rncamerakit;

import android.util.Log;
import androidx.core.internal.view.SupportMenu;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.henninghall.date_picker.props.ModeProp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CKCameraManager.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0014\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\tH\u0007J\u001a\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0007J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\tH\u0007J\u001a\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0001\u0010\u0018\u001a\u00020\u0019H\u0007J\u001a\u0010\u001a\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\b\b\u0001\u0010\u0018\u001a\u00020\u0019H\u0007J\u0018\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\tH\u0007J\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0018\u0010 \u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0019H\u0007J\u001a\u0010#\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0007J\u0018\u0010$\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\tH\u0007¨\u0006%"}, d2 = {"Lcom/rncamerakit/CKCameraManager;", "Lcom/facebook/react/uimanager/SimpleViewManager;", "Lcom/rncamerakit/CKCamera;", "()V", "createViewInstance", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "receiveCommand", "", "view", "commandId", "args", "Lcom/facebook/react/bridge/ReadableArray;", "setCameraType", "type", "setFlashMode", ModeProp.name, "setFocusMode", "setFrameColor", "color", "", "setLaserColor", "setOutputPath", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "setScanBarcode", ViewProps.ENABLED, "", "setShowFrame", "setShutterAnimationDuration", "duration", "setTorchMode", "setZoomMode", "react-native-camera-kit_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class CKCameraManager extends SimpleViewManager<CKCamera> {

    /* compiled from: CKCameraManager.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReadableType.values().length];
            try {
                iArr[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReadableType.Array.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReadableType.Boolean.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReadableType.Map.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReadableType.Number.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ReadableType.String.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CKCameraManager";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public CKCamera createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new CKCamera(context);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(CKCamera view, String commandId, ReadableArray args) {
        String str;
        Intrinsics.checkNotNullParameter(view, "view");
        String str2 = "CameraManager received command " + commandId + "(";
        int size = args != null ? args.size() : 0;
        if (size >= 0) {
            int i = 0;
            while (true) {
                if (i > 0) {
                    str2 = str2 + ", ";
                }
                ReadableType type = args != null ? args.getType(0) : null;
                switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                    case 1:
                        str = "Null";
                        break;
                    case 2:
                        str = "Array";
                        break;
                    case 3:
                        str = "Boolean";
                        break;
                    case 4:
                        str = "Map";
                        break;
                    case 5:
                        str = "Number";
                        break;
                    case 6:
                        str = "String";
                        break;
                    default:
                        str = "";
                        break;
                }
                str2 = str2 + str;
                if (i != size) {
                    i++;
                }
            }
        }
        Log.d("ReactNative", str2 + ")");
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> mapOf = MapBuilder.of("onOrientationChange", MapBuilder.of("registrationName", "onOrientationChange"), "onReadCode", MapBuilder.of("registrationName", "onReadCode"), "onPictureTaken", MapBuilder.of("registrationName", "onPictureTaken"));
        Intrinsics.checkNotNullExpressionValue(mapOf, "of(...)");
        return mapOf;
    }

    @ReactProp(name = "cameraType")
    public final void setCameraType(CKCamera view, String type) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(type, "type");
        view.setCameraType(type);
    }

    @ReactProp(name = "flashMode")
    public final void setFlashMode(CKCamera view, String mode) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setFlashMode(mode);
    }

    @ReactProp(name = "torchMode")
    public final void setTorchMode(CKCamera view, String mode) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTorchMode(mode);
    }

    @ReactProp(name = "focusMode")
    public final void setFocusMode(CKCamera view, String mode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(mode, "mode");
        view.setAutoFocus(mode);
    }

    @ReactProp(name = "zoomMode")
    public final void setZoomMode(CKCamera view, String mode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(mode, "mode");
        view.setZoomMode(mode);
    }

    @ReactProp(name = "scanBarcode")
    public final void setScanBarcode(CKCamera view, boolean enabled) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setScanBarcode(enabled);
    }

    @ReactProp(name = "showFrame")
    public final void setShowFrame(CKCamera view, boolean enabled) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setShowFrame(enabled);
    }

    @ReactProp(defaultInt = SupportMenu.CATEGORY_MASK, name = "laserColor")
    public final void setLaserColor(CKCamera view, int color) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setLaserColor(color);
    }

    @ReactProp(defaultInt = -16711936, name = "frameColor")
    public final void setFrameColor(CKCamera view, int color) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setFrameColor(color);
    }

    @ReactProp(name = "outputPath")
    public final void setOutputPath(CKCamera view, String path) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(path, "path");
        view.setOutputPath(path);
    }

    @ReactProp(name = "shutterAnimationDuration")
    public final void setShutterAnimationDuration(CKCamera view, int duration) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setShutterAnimationDuration(duration);
    }
}
