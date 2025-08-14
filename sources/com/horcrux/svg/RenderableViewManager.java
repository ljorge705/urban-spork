package com.horcrux.svg;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNSVGCircleManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGCircleManagerInterface;
import com.facebook.react.viewmanagers.RNSVGClipPathManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface;
import com.facebook.react.viewmanagers.RNSVGDefsManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGDefsManagerInterface;
import com.facebook.react.viewmanagers.RNSVGEllipseManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface;
import com.facebook.react.viewmanagers.RNSVGForeignObjectManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface;
import com.facebook.react.viewmanagers.RNSVGGroupManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGGroupManagerInterface;
import com.facebook.react.viewmanagers.RNSVGImageManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGImageManagerInterface;
import com.facebook.react.viewmanagers.RNSVGLineManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGLineManagerInterface;
import com.facebook.react.viewmanagers.RNSVGLinearGradientManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface;
import com.facebook.react.viewmanagers.RNSVGMarkerManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface;
import com.facebook.react.viewmanagers.RNSVGMaskManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGMaskManagerInterface;
import com.facebook.react.viewmanagers.RNSVGPathManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGPathManagerInterface;
import com.facebook.react.viewmanagers.RNSVGPatternManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGPatternManagerInterface;
import com.facebook.react.viewmanagers.RNSVGRadialGradientManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface;
import com.facebook.react.viewmanagers.RNSVGRectManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGRectManagerInterface;
import com.facebook.react.viewmanagers.RNSVGSymbolManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface;
import com.facebook.react.viewmanagers.RNSVGTSpanManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface;
import com.facebook.react.viewmanagers.RNSVGTextManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGTextManagerInterface;
import com.facebook.react.viewmanagers.RNSVGTextPathManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface;
import com.facebook.react.viewmanagers.RNSVGUseManagerDelegate;
import com.facebook.react.viewmanagers.RNSVGUseManagerInterface;
import com.horcrux.svg.RenderableView;
import com.horcrux.svg.VirtualViewManager;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.sentry.rrweb.RRWebMetaEvent;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
class RenderableViewManager<T extends RenderableView> extends VirtualViewManager<T> {
    RenderableViewManager(VirtualViewManager.SVGClass sVGClass) {
        super(sVGClass);
    }

    static class GroupViewManagerAbstract<U extends GroupView> extends RenderableViewManager<U> {
        GroupViewManagerAbstract(VirtualViewManager.SVGClass sVGClass) {
            super(sVGClass);
        }

        @ReactProp(name = "font")
        public void setFont(U u, @Nullable ReadableMap readableMap) {
            u.setFont(readableMap);
        }

        @ReactProp(name = ViewProps.FONT_SIZE)
        public void setFontSize(U u, Dynamic dynamic) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            int i = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
            if (i == 1) {
                javaOnlyMap.putDouble(ViewProps.FONT_SIZE, dynamic.asDouble());
            } else if (i != 2) {
                return;
            } else {
                javaOnlyMap.putString(ViewProps.FONT_SIZE, dynamic.asString());
            }
            u.setFont(javaOnlyMap);
        }

        public void setFontSize(U u, @Nullable String str) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            javaOnlyMap.putString(ViewProps.FONT_SIZE, str);
            u.setFont(javaOnlyMap);
        }

        public void setFontSize(U u, @Nullable Double d) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            javaOnlyMap.putDouble(ViewProps.FONT_SIZE, d.doubleValue());
            u.setFont(javaOnlyMap);
        }

        @ReactProp(name = ViewProps.FONT_WEIGHT)
        public void setFontWeight(U u, Dynamic dynamic) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            int i = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
            if (i == 1) {
                javaOnlyMap.putDouble(ViewProps.FONT_WEIGHT, dynamic.asDouble());
            } else if (i != 2) {
                return;
            } else {
                javaOnlyMap.putString(ViewProps.FONT_WEIGHT, dynamic.asString());
            }
            u.setFont(javaOnlyMap);
        }

        public void setFontWeight(U u, @Nullable String str) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            javaOnlyMap.putString(ViewProps.FONT_WEIGHT, str);
            u.setFont(javaOnlyMap);
        }

        public void setFontWeight(U u, @Nullable Double d) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            javaOnlyMap.putDouble(ViewProps.FONT_WEIGHT, d.doubleValue());
            u.setFont(javaOnlyMap);
        }
    }

    /* renamed from: com.horcrux.svg.RenderableViewManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static class GroupViewManager extends GroupViewManagerAbstract<GroupView> implements RNSVGGroupManagerInterface<GroupView> {
        public static final String REACT_CLASS = "RNSVGGroup";

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((GroupViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((GroupViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((GroupViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((GroupViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((GroupViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((GroupViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((GroupViewManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((GroupViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((GroupViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((GroupViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((GroupViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((GroupViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((GroupViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((GroupViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((GroupViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((GroupViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((GroupViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((GroupViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((GroupViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((GroupViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((GroupViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((GroupViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((GroupViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((GroupViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((GroupViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((GroupViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((GroupViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((GroupViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((GroupViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((GroupViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((GroupViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGGroupManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((GroupViewManager) view, i);
        }

        GroupViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGGroup);
            this.mDelegate = new RNSVGGroupManagerDelegate(this);
        }
    }

    static class PathViewManager extends RenderableViewManager<PathView> implements RNSVGPathManagerInterface<PathView> {
        public static final String REACT_CLASS = "RNSVGPath";

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((PathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((PathViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((PathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((PathViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((PathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((PathViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((PathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((PathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((PathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((PathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((PathViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((PathViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((PathViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((PathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((PathViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((PathViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((PathViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((PathViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((PathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((PathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((PathViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((PathViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((PathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((PathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((PathViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((PathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((PathViewManager) view, i);
        }

        PathViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGPath);
            this.mDelegate = new RNSVGPathManagerDelegate(this);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPathManagerInterface
        @ReactProp(name = "d")
        public void setD(PathView pathView, String str) {
            pathView.setD(str);
        }
    }

    static class TextViewManagerAbstract<K extends TextView> extends GroupViewManagerAbstract<K> {
        TextViewManagerAbstract(VirtualViewManager.SVGClass sVGClass) {
            super(sVGClass);
        }

        @ReactProp(name = "inlineSize")
        public void setInlineSize(K k, Dynamic dynamic) {
            k.setInlineSize(dynamic);
        }

        @ReactProp(name = "textLength")
        public void setTextLength(K k, Dynamic dynamic) {
            k.setTextLength(dynamic);
        }

        @ReactProp(name = "lengthAdjust")
        public void setLengthAdjust(K k, @Nullable String str) {
            k.setLengthAdjust(str);
        }

        @ReactProp(name = "alignmentBaseline")
        public void setMethod(K k, @Nullable String str) {
            k.setMethod(str);
        }

        @ReactProp(name = "baselineShift")
        public void setBaselineShift(K k, Dynamic dynamic) {
            k.setBaselineShift(dynamic);
        }

        @ReactProp(name = "verticalAlign")
        public void setVerticalAlign(K k, @Nullable String str) {
            k.setVerticalAlign(str);
        }

        @ReactProp(name = "rotate")
        public void setRotate(K k, Dynamic dynamic) {
            k.setRotate(dynamic);
        }

        @ReactProp(name = "dx")
        public void setDeltaX(K k, Dynamic dynamic) {
            k.setDeltaX(dynamic);
        }

        @ReactProp(name = "dy")
        public void setDeltaY(K k, Dynamic dynamic) {
            k.setDeltaY(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(K k, Dynamic dynamic) {
            k.setPositionX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(K k, Dynamic dynamic) {
            k.setPositionY(dynamic);
        }

        @Override // com.horcrux.svg.RenderableViewManager.GroupViewManagerAbstract
        @ReactProp(name = "font")
        public void setFont(K k, @Nullable ReadableMap readableMap) {
            k.setFont(readableMap);
        }

        public void setAlignmentBaseline(K k, @Nullable String str) {
            k.setMethod(str);
        }

        public void setDx(K k, @Nullable ReadableArray readableArray) {
            k.setDeltaX(readableArray);
        }

        public void setDy(K k, @Nullable ReadableArray readableArray) {
            k.setDeltaY(readableArray);
        }

        public void setX(K k, @Nullable ReadableArray readableArray) {
            k.setPositionX(readableArray);
        }

        public void setY(K k, @Nullable ReadableArray readableArray) {
            k.setPositionY(readableArray);
        }

        public void setRotate(K k, @Nullable ReadableArray readableArray) {
            k.setRotate(readableArray);
        }

        public void setInlineSize(K k, @Nullable String str) {
            k.setInlineSize(str);
        }

        public void setTextLength(K k, @Nullable String str) {
            k.setTextLength(str);
        }

        public void setBaselineShift(K k, @Nullable String str) {
            k.setBaselineShift(str);
        }

        public void setInlineSize(K k, @Nullable Double d) {
            k.setInlineSize(d);
        }

        public void setTextLength(K k, @Nullable Double d) {
            k.setTextLength(d);
        }

        public void setBaselineShift(K k, @Nullable Double d) {
            k.setBaselineShift(d);
        }
    }

    static class TextViewManager extends TextViewManagerAbstract<TextView> implements RNSVGTextManagerInterface<TextView> {
        public static final String REACT_CLASS = "RNSVGText";

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setAlignmentBaseline(View view, @Nullable String str) {
            super.setAlignmentBaseline((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setBaselineShift(View view, @Nullable Double d) {
            super.setBaselineShift((TextViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setBaselineShift(View view, @Nullable String str) {
            super.setBaselineShift((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((TextViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setDx(View view, @Nullable ReadableArray readableArray) {
            super.setDx((TextViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setDy(View view, @Nullable ReadableArray readableArray) {
            super.setDy((TextViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((TextViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((TextViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((TextViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((TextViewManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((TextViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((TextViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setInlineSize(View view, @Nullable Double d) {
            super.setInlineSize((TextViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setInlineSize(View view, @Nullable String str) {
            super.setInlineSize((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "lengthAdjust")
        public /* bridge */ /* synthetic */ void setLengthAdjust(View view, @Nullable String str) {
            super.setLengthAdjust((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((TextViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((TextViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((TextViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((TextViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((TextViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((TextViewManager) view, z);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setRotate(View view, @Nullable ReadableArray readableArray) {
            super.setRotate((TextViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((TextViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((TextViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((TextViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((TextViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((TextViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((TextViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((TextViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((TextViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((TextViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setTextLength(View view, @Nullable Double d) {
            super.setTextLength((TextViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setTextLength(View view, @Nullable String str) {
            super.setTextLength((TextViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((TextViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        @ReactProp(name = "verticalAlign")
        public /* bridge */ /* synthetic */ void setVerticalAlign(View view, @Nullable String str) {
            super.setVerticalAlign((TextViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setX(View view, @Nullable ReadableArray readableArray) {
            super.setX((TextViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextManagerInterface
        public /* bridge */ /* synthetic */ void setY(View view, @Nullable ReadableArray readableArray) {
            super.setY((TextViewManager) view, readableArray);
        }

        TextViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGText);
            this.mDelegate = new RNSVGTextManagerDelegate(this);
        }

        TextViewManager(VirtualViewManager.SVGClass sVGClass) {
            super(sVGClass);
            this.mDelegate = new RNSVGTextManagerDelegate(this);
        }
    }

    static class TSpanViewManager extends TextViewManagerAbstract<TSpanView> implements RNSVGTSpanManagerInterface<TSpanView> {
        public static final String REACT_CLASS = "RNSVGTSpan";

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setAlignmentBaseline(View view, @Nullable String str) {
            super.setAlignmentBaseline((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setBaselineShift(View view, @Nullable Double d) {
            super.setBaselineShift((TSpanViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setBaselineShift(View view, @Nullable String str) {
            super.setBaselineShift((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((TSpanViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setDx(View view, @Nullable ReadableArray readableArray) {
            super.setDx((TSpanViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setDy(View view, @Nullable ReadableArray readableArray) {
            super.setDy((TSpanViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((TSpanViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((TSpanViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((TSpanViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((TSpanViewManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((TSpanViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((TSpanViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setInlineSize(View view, @Nullable Double d) {
            super.setInlineSize((TSpanViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setInlineSize(View view, @Nullable String str) {
            super.setInlineSize((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "lengthAdjust")
        public /* bridge */ /* synthetic */ void setLengthAdjust(View view, @Nullable String str) {
            super.setLengthAdjust((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((TSpanViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((TSpanViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((TSpanViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((TSpanViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((TSpanViewManager) view, z);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setRotate(View view, @Nullable ReadableArray readableArray) {
            super.setRotate((TSpanViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((TSpanViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((TSpanViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((TSpanViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((TSpanViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((TSpanViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((TSpanViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((TSpanViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((TSpanViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((TSpanViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setTextLength(View view, @Nullable Double d) {
            super.setTextLength((TSpanViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setTextLength(View view, @Nullable String str) {
            super.setTextLength((TSpanViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((TSpanViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "verticalAlign")
        public /* bridge */ /* synthetic */ void setVerticalAlign(View view, @Nullable String str) {
            super.setVerticalAlign((TSpanViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setX(View view, @Nullable ReadableArray readableArray) {
            super.setX((TSpanViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        public /* bridge */ /* synthetic */ void setY(View view, @Nullable ReadableArray readableArray) {
            super.setY((TSpanViewManager) view, readableArray);
        }

        TSpanViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGTSpan);
            this.mDelegate = new RNSVGTSpanManagerDelegate(this);
        }

        TSpanViewManager(VirtualViewManager.SVGClass sVGClass) {
            super(sVGClass);
            this.mDelegate = new RNSVGTSpanManagerDelegate(this);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTSpanManagerInterface
        @ReactProp(name = "content")
        public void setContent(TSpanView tSpanView, @Nullable String str) {
            tSpanView.setContent(str);
        }
    }

    static class TextPathViewManager extends TextViewManagerAbstract<TextPathView> implements RNSVGTextPathManagerInterface<TextPathView> {
        public static final String REACT_CLASS = "RNSVGTextPath";

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setAlignmentBaseline(View view, @Nullable String str) {
            super.setAlignmentBaseline((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setBaselineShift(View view, @Nullable Double d) {
            super.setBaselineShift((TextPathViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setBaselineShift(View view, @Nullable String str) {
            super.setBaselineShift((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((TextPathViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setDx(View view, @Nullable ReadableArray readableArray) {
            super.setDx((TextPathViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setDy(View view, @Nullable ReadableArray readableArray) {
            super.setDy((TextPathViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((TextPathViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((TextPathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((TextPathViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((TextPathViewManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((TextPathViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((TextPathViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setInlineSize(View view, @Nullable Double d) {
            super.setInlineSize((TextPathViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setInlineSize(View view, @Nullable String str) {
            super.setInlineSize((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "lengthAdjust")
        public /* bridge */ /* synthetic */ void setLengthAdjust(View view, @Nullable String str) {
            super.setLengthAdjust((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((TextPathViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((TextPathViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((TextPathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((TextPathViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((TextPathViewManager) view, z);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setRotate(View view, @Nullable ReadableArray readableArray) {
            super.setRotate((TextPathViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((TextPathViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((TextPathViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((TextPathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((TextPathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((TextPathViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((TextPathViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((TextPathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((TextPathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((TextPathViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setTextLength(View view, @Nullable Double d) {
            super.setTextLength((TextPathViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setTextLength(View view, @Nullable String str) {
            super.setTextLength((TextPathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((TextPathViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "verticalAlign")
        public /* bridge */ /* synthetic */ void setVerticalAlign(View view, @Nullable String str) {
            super.setVerticalAlign((TextPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setX(View view, @Nullable ReadableArray readableArray) {
            super.setX((TextPathViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public /* bridge */ /* synthetic */ void setY(View view, @Nullable ReadableArray readableArray) {
            super.setY((TextPathViewManager) view, readableArray);
        }

        TextPathViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGTextPath);
            this.mDelegate = new RNSVGTextPathManagerDelegate(this);
        }

        TextPathViewManager(VirtualViewManager.SVGClass sVGClass) {
            super(sVGClass);
            this.mDelegate = new RNSVGTextPathManagerDelegate(this);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = RRWebMetaEvent.JsonKeys.HREF)
        public void setHref(TextPathView textPathView, String str) {
            textPathView.setHref(str);
        }

        @ReactProp(name = "startOffset")
        public void setStartOffset(TextPathView textPathView, Dynamic dynamic) {
            textPathView.setStartOffset(dynamic);
        }

        @Override // com.horcrux.svg.RenderableViewManager.TextViewManagerAbstract
        @ReactProp(name = "method")
        public void setMethod(TextPathView textPathView, @Nullable String str) {
            textPathView.setMethod(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public void setMidLine(TextPathView textPathView, @Nullable String str) {
            textPathView.setSharp(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = "spacing")
        public void setSpacing(TextPathView textPathView, @Nullable String str) {
            textPathView.setSpacing(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public void setStartOffset(TextPathView textPathView, @Nullable String str) {
            textPathView.setStartOffset(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        public void setStartOffset(TextPathView textPathView, @Nullable Double d) {
            textPathView.setStartOffset(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGTextPathManagerInterface
        @ReactProp(name = ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE)
        public void setSide(TextPathView textPathView, @Nullable String str) {
            textPathView.setSide(str);
        }

        @ReactProp(name = "midLine")
        public void setSharp(TextPathView textPathView, @Nullable String str) {
            textPathView.setSharp(str);
        }
    }

    static class ImageViewManager extends RenderableViewManager<ImageView> implements RNSVGImageManagerInterface<ImageView> {
        public static final String REACT_CLASS = "RNSVGImage";

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((ImageViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((ImageViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((ImageViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((ImageViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((ImageViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((ImageViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((ImageViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((ImageViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((ImageViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((ImageViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((ImageViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((ImageViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((ImageViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((ImageViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((ImageViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((ImageViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((ImageViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((ImageViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((ImageViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((ImageViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((ImageViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((ImageViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((ImageViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((ImageViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((ImageViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((ImageViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((ImageViewManager) view, i);
        }

        ImageViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGImage);
            this.mDelegate = new RNSVGImageManagerDelegate(this);
        }

        @ReactProp(name = "x")
        public void setX(ImageView imageView, Dynamic dynamic) {
            imageView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(ImageView imageView, Dynamic dynamic) {
            imageView.setY(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(ImageView imageView, Dynamic dynamic) {
            imageView.setWidth(dynamic);
        }

        @ReactProp(name = "height")
        public void setHeight(ImageView imageView, Dynamic dynamic) {
            imageView.setHeight(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public void setX(ImageView imageView, @Nullable String str) {
            imageView.setX(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public void setY(ImageView imageView, @Nullable String str) {
            imageView.setY(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public void setWidth(ImageView imageView, @Nullable String str) {
            imageView.setWidth(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public void setHeight(ImageView imageView, @Nullable String str) {
            imageView.setHeight(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public void setX(ImageView imageView, @Nullable Double d) {
            imageView.setX(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public void setY(ImageView imageView, @Nullable Double d) {
            imageView.setY(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public void setWidth(ImageView imageView, @Nullable Double d) {
            imageView.setWidth(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        public void setHeight(ImageView imageView, @Nullable Double d) {
            imageView.setHeight(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(customType = "ImageSource", name = "src")
        public void setSrc(ImageView imageView, @Nullable ReadableMap readableMap) {
            imageView.setSrc(readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "align")
        public void setAlign(ImageView imageView, String str) {
            imageView.setAlign(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGImageManagerInterface
        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(ImageView imageView, int i) {
            imageView.setMeetOrSlice(i);
        }
    }

    static class CircleViewManager extends RenderableViewManager<CircleView> implements RNSVGCircleManagerInterface<CircleView> {
        public static final String REACT_CLASS = "RNSVGCircle";

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((CircleViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((CircleViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((CircleViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((CircleViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((CircleViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((CircleViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((CircleViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((CircleViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((CircleViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((CircleViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((CircleViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((CircleViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((CircleViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((CircleViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((CircleViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((CircleViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((CircleViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((CircleViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((CircleViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((CircleViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((CircleViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((CircleViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((CircleViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((CircleViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((CircleViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((CircleViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((CircleViewManager) view, i);
        }

        CircleViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGCircle);
            this.mDelegate = new RNSVGCircleManagerDelegate(this);
        }

        @ReactProp(name = "cx")
        public void setCx(CircleView circleView, Dynamic dynamic) {
            circleView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(CircleView circleView, Dynamic dynamic) {
            circleView.setCy(dynamic);
        }

        @ReactProp(name = "r")
        public void setR(CircleView circleView, Dynamic dynamic) {
            circleView.setR(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public void setCx(CircleView circleView, String str) {
            circleView.setCx(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public void setCx(CircleView circleView, Double d) {
            circleView.setCx(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public void setCy(CircleView circleView, String str) {
            circleView.setCy(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public void setCy(CircleView circleView, Double d) {
            circleView.setCy(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public void setR(CircleView circleView, String str) {
            circleView.setR(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGCircleManagerInterface
        public void setR(CircleView circleView, Double d) {
            circleView.setR(d);
        }
    }

    static class EllipseViewManager extends RenderableViewManager<EllipseView> implements RNSVGEllipseManagerInterface<EllipseView> {
        public static final String REACT_CLASS = "RNSVGEllipse";

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((EllipseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((EllipseViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((EllipseViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((EllipseViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((EllipseViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((EllipseViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((EllipseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((EllipseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((EllipseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((EllipseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((EllipseViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((EllipseViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((EllipseViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((EllipseViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((EllipseViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((EllipseViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((EllipseViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((EllipseViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((EllipseViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((EllipseViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((EllipseViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((EllipseViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((EllipseViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((EllipseViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((EllipseViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((EllipseViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((EllipseViewManager) view, i);
        }

        EllipseViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGEllipse);
            this.mDelegate = new RNSVGEllipseManagerDelegate(this);
        }

        @ReactProp(name = "cx")
        public void setCx(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setCy(dynamic);
        }

        @ReactProp(name = "rx")
        public void setRx(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setRy(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public void setCx(EllipseView ellipseView, @Nullable String str) {
            ellipseView.setCx(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public void setCy(EllipseView ellipseView, @Nullable String str) {
            ellipseView.setCy(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public void setRx(EllipseView ellipseView, @Nullable String str) {
            ellipseView.setRx(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public void setRy(EllipseView ellipseView, @Nullable String str) {
            ellipseView.setRy(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public void setCx(EllipseView ellipseView, @Nullable Double d) {
            ellipseView.setCx(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public void setCy(EllipseView ellipseView, @Nullable Double d) {
            ellipseView.setCy(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public void setRx(EllipseView ellipseView, @Nullable Double d) {
            ellipseView.setRx(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGEllipseManagerInterface
        public void setRy(EllipseView ellipseView, @Nullable Double d) {
            ellipseView.setRy(d);
        }
    }

    static class LineViewManager extends RenderableViewManager<LineView> implements RNSVGLineManagerInterface<LineView> {
        public static final String REACT_CLASS = "RNSVGLine";

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((LineViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((LineViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((LineViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((LineViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((LineViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((LineViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((LineViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((LineViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((LineViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((LineViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((LineViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((LineViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((LineViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((LineViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((LineViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((LineViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((LineViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((LineViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((LineViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((LineViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((LineViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((LineViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((LineViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((LineViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((LineViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((LineViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((LineViewManager) view, i);
        }

        LineViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGLine);
            this.mDelegate = new RNSVGLineManagerDelegate(this);
        }

        @ReactProp(name = "x1")
        public void setX1(LineView lineView, Dynamic dynamic) {
            lineView.setX1(dynamic);
        }

        @ReactProp(name = "y1")
        public void setY1(LineView lineView, Dynamic dynamic) {
            lineView.setY1(dynamic);
        }

        @ReactProp(name = "x2")
        public void setX2(LineView lineView, Dynamic dynamic) {
            lineView.setX2(dynamic);
        }

        @ReactProp(name = "y2")
        public void setY2(LineView lineView, Dynamic dynamic) {
            lineView.setY2(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public void setX1(LineView lineView, @Nullable String str) {
            lineView.setX1(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public void setY1(LineView lineView, @Nullable String str) {
            lineView.setY1(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public void setX2(LineView lineView, @Nullable String str) {
            lineView.setX2(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public void setY2(LineView lineView, @Nullable String str) {
            lineView.setY2(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public void setX1(LineView lineView, @Nullable Double d) {
            lineView.setX1(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public void setY1(LineView lineView, @Nullable Double d) {
            lineView.setY1(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public void setX2(LineView lineView, @Nullable Double d) {
            lineView.setX2(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLineManagerInterface
        public void setY2(LineView lineView, @Nullable Double d) {
            lineView.setY2(d);
        }
    }

    static class RectViewManager extends RenderableViewManager<RectView> implements RNSVGRectManagerInterface<RectView> {
        public static final String REACT_CLASS = "RNSVGRect";

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((RectViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((RectViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((RectViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((RectViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((RectViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((RectViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((RectViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((RectViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((RectViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((RectViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((RectViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((RectViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((RectViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((RectViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((RectViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((RectViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((RectViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((RectViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((RectViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((RectViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((RectViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((RectViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((RectViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((RectViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((RectViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((RectViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((RectViewManager) view, i);
        }

        RectViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGRect);
            this.mDelegate = new RNSVGRectManagerDelegate(this);
        }

        @ReactProp(name = "x")
        public void setX(RectView rectView, Dynamic dynamic) {
            rectView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(RectView rectView, Dynamic dynamic) {
            rectView.setY(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(RectView rectView, Dynamic dynamic) {
            rectView.setWidth(dynamic);
        }

        @ReactProp(name = "height")
        public void setHeight(RectView rectView, Dynamic dynamic) {
            rectView.setHeight(dynamic);
        }

        @ReactProp(name = "rx")
        public void setRx(RectView rectView, Dynamic dynamic) {
            rectView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(RectView rectView, Dynamic dynamic) {
            rectView.setRy(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setX(RectView rectView, @Nullable String str) {
            rectView.setX(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setY(RectView rectView, @Nullable String str) {
            rectView.setY(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setHeight(RectView rectView, @Nullable String str) {
            rectView.setHeight(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setWidth(RectView rectView, @Nullable String str) {
            rectView.setWidth(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setRx(RectView rectView, @Nullable String str) {
            rectView.setRx(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setRy(RectView rectView, @Nullable String str) {
            rectView.setRy(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setX(RectView rectView, @Nullable Double d) {
            rectView.setX(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setY(RectView rectView, @Nullable Double d) {
            rectView.setY(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setHeight(RectView rectView, @Nullable Double d) {
            rectView.setHeight(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setWidth(RectView rectView, @Nullable Double d) {
            rectView.setWidth(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setRx(RectView rectView, @Nullable Double d) {
            rectView.setRx(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRectManagerInterface
        public void setRy(RectView rectView, @Nullable Double d) {
            rectView.setRy(d);
        }
    }

    static class ClipPathViewManager extends GroupViewManagerAbstract<ClipPathView> implements RNSVGClipPathManagerInterface<ClipPathView> {
        public static final String REACT_CLASS = "RNSVGClipPath";

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((ClipPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((ClipPathViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((ClipPathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((ClipPathViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((ClipPathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((ClipPathViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((ClipPathViewManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((ClipPathViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((ClipPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((ClipPathViewManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((ClipPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((ClipPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((ClipPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((ClipPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((ClipPathViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((ClipPathViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((ClipPathViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((ClipPathViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((ClipPathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((ClipPathViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((ClipPathViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((ClipPathViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((ClipPathViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((ClipPathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((ClipPathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((ClipPathViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((ClipPathViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((ClipPathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((ClipPathViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((ClipPathViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((ClipPathViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGClipPathManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((ClipPathViewManager) view, i);
        }

        ClipPathViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGClipPath);
            this.mDelegate = new RNSVGClipPathManagerDelegate(this);
        }
    }

    static class DefsViewManager extends VirtualViewManager<DefsView> implements RNSVGDefsManagerInterface<DefsView> {
        public static final String REACT_CLASS = "RNSVGDefs";

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((DefsViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((DefsViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((DefsViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((DefsViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((DefsViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((DefsViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((DefsViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((DefsViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((DefsViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((DefsViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((DefsViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGDefsManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((DefsViewManager) view, z);
        }

        DefsViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGDefs);
            this.mDelegate = new RNSVGDefsManagerDelegate(this);
        }
    }

    static class UseViewManager extends RenderableViewManager<UseView> implements RNSVGUseManagerInterface<UseView> {
        public static final String REACT_CLASS = "RNSVGUse";

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((UseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((UseViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((UseViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((UseViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((UseViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((UseViewManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((UseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((UseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((UseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((UseViewManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((UseViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((UseViewManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((UseViewManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((UseViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((UseViewManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((UseViewManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((UseViewManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((UseViewManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((UseViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((UseViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((UseViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((UseViewManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((UseViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((UseViewManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((UseViewManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((UseViewManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((UseViewManager) view, i);
        }

        UseViewManager() {
            super(VirtualViewManager.SVGClass.RNSVGUse);
            this.mDelegate = new RNSVGUseManagerDelegate(this);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        @ReactProp(name = RRWebMetaEvent.JsonKeys.HREF)
        public void setHref(UseView useView, String str) {
            useView.setHref(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public void setX(UseView useView, @Nullable String str) {
            useView.setX(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public void setY(UseView useView, @Nullable String str) {
            useView.setY(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public void setHeight(UseView useView, @Nullable String str) {
            useView.setHeight(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public void setWidth(UseView useView, @Nullable Double d) {
            useView.setWidth(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public void setX(UseView useView, @Nullable Double d) {
            useView.setX(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public void setY(UseView useView, @Nullable Double d) {
            useView.setY(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public void setHeight(UseView useView, @Nullable Double d) {
            useView.setHeight(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGUseManagerInterface
        public void setWidth(UseView useView, @Nullable String str) {
            useView.setWidth(str);
        }

        @ReactProp(name = "x")
        public void setX(UseView useView, Dynamic dynamic) {
            useView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(UseView useView, Dynamic dynamic) {
            useView.setY(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(UseView useView, Dynamic dynamic) {
            useView.setWidth(dynamic);
        }

        @ReactProp(name = "height")
        public void setHeight(UseView useView, Dynamic dynamic) {
            useView.setHeight(dynamic);
        }
    }

    static class SymbolManager extends GroupViewManagerAbstract<SymbolView> implements RNSVGSymbolManagerInterface<SymbolView> {
        public static final String REACT_CLASS = "RNSVGSymbol";

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((SymbolManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((SymbolManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((SymbolManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((SymbolManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((SymbolManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((SymbolManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((SymbolManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((SymbolManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((SymbolManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((SymbolManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((SymbolManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((SymbolManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((SymbolManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((SymbolManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((SymbolManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((SymbolManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((SymbolManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((SymbolManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((SymbolManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((SymbolManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((SymbolManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((SymbolManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((SymbolManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((SymbolManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((SymbolManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((SymbolManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((SymbolManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((SymbolManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((SymbolManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((SymbolManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((SymbolManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((SymbolManager) view, i);
        }

        SymbolManager() {
            super(VirtualViewManager.SVGClass.RNSVGSymbol);
            this.mDelegate = new RNSVGSymbolManagerDelegate(this);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "minX")
        public void setMinX(SymbolView symbolView, float f) {
            symbolView.setMinX(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "minY")
        public void setMinY(SymbolView symbolView, float f) {
            symbolView.setMinY(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "vbWidth")
        public void setVbWidth(SymbolView symbolView, float f) {
            symbolView.setVbWidth(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "vbHeight")
        public void setVbHeight(SymbolView symbolView, float f) {
            symbolView.setVbHeight(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "align")
        public void setAlign(SymbolView symbolView, String str) {
            symbolView.setAlign(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGSymbolManagerInterface
        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(SymbolView symbolView, int i) {
            symbolView.setMeetOrSlice(i);
        }
    }

    static class PatternManager extends GroupViewManagerAbstract<PatternView> implements RNSVGPatternManagerInterface<PatternView> {
        public static final String REACT_CLASS = "RNSVGPattern";

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((PatternManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((PatternManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((PatternManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((PatternManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((PatternManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((PatternManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((PatternManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((PatternManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((PatternManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((PatternManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((PatternManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((PatternManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((PatternManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((PatternManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((PatternManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((PatternManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((PatternManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((PatternManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((PatternManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((PatternManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((PatternManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((PatternManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((PatternManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((PatternManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((PatternManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((PatternManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((PatternManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((PatternManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((PatternManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((PatternManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((PatternManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((PatternManager) view, i);
        }

        PatternManager() {
            super(VirtualViewManager.SVGClass.RNSVGPattern);
            this.mDelegate = new RNSVGPatternManagerDelegate(this);
        }

        @ReactProp(name = "x")
        public void setX(PatternView patternView, Dynamic dynamic) {
            patternView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(PatternView patternView, Dynamic dynamic) {
            patternView.setY(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(PatternView patternView, Dynamic dynamic) {
            patternView.setWidth(dynamic);
        }

        @ReactProp(name = "height")
        public void setHeight(PatternView patternView, Dynamic dynamic) {
            patternView.setHeight(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public void setX(PatternView patternView, @Nullable String str) {
            patternView.setX(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public void setY(PatternView patternView, @Nullable String str) {
            patternView.setY(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public void setHeight(PatternView patternView, @Nullable String str) {
            patternView.setHeight(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public void setWidth(PatternView patternView, @Nullable String str) {
            patternView.setWidth(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public void setX(PatternView patternView, @Nullable Double d) {
            patternView.setX(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public void setY(PatternView patternView, @Nullable Double d) {
            patternView.setY(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public void setHeight(PatternView patternView, @Nullable Double d) {
            patternView.setHeight(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        public void setWidth(PatternView patternView, @Nullable Double d) {
            patternView.setWidth(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "patternUnits")
        public void setPatternUnits(PatternView patternView, int i) {
            patternView.setPatternUnits(i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "patternContentUnits")
        public void setPatternContentUnits(PatternView patternView, int i) {
            patternView.setPatternContentUnits(i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "patternTransform")
        public void setPatternTransform(PatternView patternView, @Nullable ReadableArray readableArray) {
            patternView.setPatternTransform(readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "minX")
        public void setMinX(PatternView patternView, float f) {
            patternView.setMinX(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "minY")
        public void setMinY(PatternView patternView, float f) {
            patternView.setMinY(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "vbWidth")
        public void setVbWidth(PatternView patternView, float f) {
            patternView.setVbWidth(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "vbHeight")
        public void setVbHeight(PatternView patternView, float f) {
            patternView.setVbHeight(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "align")
        public void setAlign(PatternView patternView, String str) {
            patternView.setAlign(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGPatternManagerInterface
        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(PatternView patternView, int i) {
            patternView.setMeetOrSlice(i);
        }
    }

    static class MaskManager extends GroupViewManagerAbstract<MaskView> implements RNSVGMaskManagerInterface<MaskView> {
        public static final String REACT_CLASS = "RNSVGMask";

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((MaskManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((MaskManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((MaskManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((MaskManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((MaskManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((MaskManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((MaskManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((MaskManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((MaskManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((MaskManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((MaskManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((MaskManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((MaskManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((MaskManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((MaskManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((MaskManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((MaskManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((MaskManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((MaskManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((MaskManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((MaskManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((MaskManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((MaskManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((MaskManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((MaskManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((MaskManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((MaskManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((MaskManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((MaskManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((MaskManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((MaskManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((MaskManager) view, i);
        }

        MaskManager() {
            super(VirtualViewManager.SVGClass.RNSVGMask);
            this.mDelegate = new RNSVGMaskManagerDelegate(this);
        }

        @ReactProp(name = "x")
        public void setX(MaskView maskView, Dynamic dynamic) {
            maskView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(MaskView maskView, Dynamic dynamic) {
            maskView.setY(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(MaskView maskView, Dynamic dynamic) {
            maskView.setWidth(dynamic);
        }

        @ReactProp(name = "height")
        public void setHeight(MaskView maskView, Dynamic dynamic) {
            maskView.setHeight(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public void setX(MaskView maskView, @Nullable String str) {
            maskView.setX(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public void setY(MaskView maskView, @Nullable String str) {
            maskView.setY(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public void setHeight(MaskView maskView, @Nullable String str) {
            maskView.setHeight(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public void setWidth(MaskView maskView, @Nullable String str) {
            maskView.setWidth(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public void setX(MaskView maskView, @Nullable Double d) {
            maskView.setX(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public void setY(MaskView maskView, @Nullable Double d) {
            maskView.setY(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public void setHeight(MaskView maskView, @Nullable Double d) {
            maskView.setHeight(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        public void setWidth(MaskView maskView, @Nullable Double d) {
            maskView.setWidth(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "maskUnits")
        public void setMaskUnits(MaskView maskView, int i) {
            maskView.setMaskUnits(i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMaskManagerInterface
        @ReactProp(name = "maskContentUnits")
        public void setMaskContentUnits(MaskView maskView, int i) {
            maskView.setMaskContentUnits(i);
        }
    }

    static class ForeignObjectManager extends GroupViewManagerAbstract<ForeignObjectView> implements RNSVGForeignObjectManagerInterface<ForeignObjectView> {
        public static final String REACT_CLASS = "RNSVGForeignObject";

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((ForeignObjectManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((ForeignObjectManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((ForeignObjectManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((ForeignObjectManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((ForeignObjectManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((ForeignObjectManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((ForeignObjectManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((ForeignObjectManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((ForeignObjectManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((ForeignObjectManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((ForeignObjectManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((ForeignObjectManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((ForeignObjectManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((ForeignObjectManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((ForeignObjectManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((ForeignObjectManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((ForeignObjectManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((ForeignObjectManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((ForeignObjectManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((ForeignObjectManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((ForeignObjectManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((ForeignObjectManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((ForeignObjectManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((ForeignObjectManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((ForeignObjectManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((ForeignObjectManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((ForeignObjectManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((ForeignObjectManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((ForeignObjectManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((ForeignObjectManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((ForeignObjectManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((ForeignObjectManager) view, i);
        }

        ForeignObjectManager() {
            super(VirtualViewManager.SVGClass.RNSVGForeignObject);
            this.mDelegate = new RNSVGForeignObjectManagerDelegate(this);
        }

        @ReactProp(name = "x")
        public void setX(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setY(dynamic);
        }

        @ReactProp(name = "width")
        public void setWidth(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setWidth(dynamic);
        }

        @ReactProp(name = "height")
        public void setHeight(ForeignObjectView foreignObjectView, Dynamic dynamic) {
            foreignObjectView.setHeight(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public void setX(ForeignObjectView foreignObjectView, @Nullable String str) {
            foreignObjectView.setX(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public void setY(ForeignObjectView foreignObjectView, @Nullable String str) {
            foreignObjectView.setY(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public void setHeight(ForeignObjectView foreignObjectView, @Nullable String str) {
            foreignObjectView.setHeight(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public void setWidth(ForeignObjectView foreignObjectView, @Nullable String str) {
            foreignObjectView.setWidth(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public void setX(ForeignObjectView foreignObjectView, @Nullable Double d) {
            foreignObjectView.setX(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public void setY(ForeignObjectView foreignObjectView, @Nullable Double d) {
            foreignObjectView.setY(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public void setHeight(ForeignObjectView foreignObjectView, @Nullable Double d) {
            foreignObjectView.setHeight(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface
        public void setWidth(ForeignObjectView foreignObjectView, @Nullable Double d) {
            foreignObjectView.setWidth(d);
        }
    }

    static class MarkerManager extends GroupViewManagerAbstract<MarkerView> implements RNSVGMarkerManagerInterface<MarkerView> {
        public static final String REACT_CLASS = "RNSVGMarker";

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((MarkerManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((MarkerManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((MarkerManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setFill(View view, @Nullable ReadableMap readableMap) {
            super.setFill((MarkerManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
        public /* bridge */ /* synthetic */ void setFillOpacity(View view, float f) {
            super.setFillOpacity((MarkerManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(defaultInt = 1, name = "fillRule")
        public /* bridge */ /* synthetic */ void setFillRule(View view, int i) {
            super.setFillRule((MarkerManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "font")
        public /* bridge */ /* synthetic */ void setFont(View view, @Nullable ReadableMap readableMap) {
            super.setFont((MarkerManager) view, readableMap);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable Double d) {
            super.setFontSize((MarkerManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setFontSize(View view, @Nullable String str) {
            super.setFontSize((MarkerManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable Double d) {
            super.setFontWeight((MarkerManager) view, d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setFontWeight(View view, @Nullable String str) {
            super.setFontWeight((MarkerManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((MarkerManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((MarkerManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((MarkerManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((MarkerManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((MarkerManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((MarkerManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((MarkerManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((MarkerManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "propList")
        public /* bridge */ /* synthetic */ void setPropList(View view, @Nullable ReadableArray readableArray) {
            super.setPropList((MarkerManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((MarkerManager) view, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setStroke(View view, @Nullable ReadableMap readableMap) {
            super.setStroke((MarkerManager) view, readableMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable ReadableArray readableArray) {
            super.setStrokeDasharray((MarkerManager) view, readableArray);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "strokeDasharray")
        public /* bridge */ /* synthetic */ void setStrokeDasharray(View view, @Nullable String str) {
            super.setStrokeDasharray((MarkerManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "strokeDashoffset")
        public /* bridge */ /* synthetic */ void setStrokeDashoffset(View view, float f) {
            super.setStrokeDashoffset((MarkerManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinecap")
        public /* bridge */ /* synthetic */ void setStrokeLinecap(View view, int i) {
            super.setStrokeLinecap((MarkerManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(defaultInt = 1, name = "strokeLinejoin")
        public /* bridge */ /* synthetic */ void setStrokeLinejoin(View view, int i) {
            super.setStrokeLinejoin((MarkerManager) view, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
        public /* bridge */ /* synthetic */ void setStrokeMiterlimit(View view, float f) {
            super.setStrokeMiterlimit((MarkerManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
        public /* bridge */ /* synthetic */ void setStrokeOpacity(View view, float f) {
            super.setStrokeOpacity((MarkerManager) view, f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable Double d) {
            super.setStrokeWidth((MarkerManager) view, d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public /* bridge */ /* synthetic */ void setStrokeWidth(View view, @Nullable String str) {
            super.setStrokeWidth((MarkerManager) view, str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "vectorEffect")
        public /* bridge */ /* synthetic */ void setVectorEffect(View view, int i) {
            super.setVectorEffect((MarkerManager) view, i);
        }

        MarkerManager() {
            super(VirtualViewManager.SVGClass.RNSVGMarker);
            this.mDelegate = new RNSVGMarkerManagerDelegate(this);
        }

        @ReactProp(name = "refX")
        public void setRefX(MarkerView markerView, Dynamic dynamic) {
            markerView.setRefX(dynamic);
        }

        @ReactProp(name = "refY")
        public void setRefY(MarkerView markerView, Dynamic dynamic) {
            markerView.setRefY(dynamic);
        }

        @ReactProp(name = "markerWidth")
        public void setMarkerWidth(MarkerView markerView, Dynamic dynamic) {
            markerView.setMarkerWidth(dynamic);
        }

        @ReactProp(name = "markerHeight")
        public void setMarkerHeight(MarkerView markerView, Dynamic dynamic) {
            markerView.setMarkerHeight(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public void setRefX(MarkerView markerView, @Nullable String str) {
            markerView.setRefX(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public void setRefY(MarkerView markerView, @Nullable String str) {
            markerView.setRefY(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public void setMarkerHeight(MarkerView markerView, @Nullable String str) {
            markerView.setMarkerHeight(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public void setMarkerWidth(MarkerView markerView, @Nullable String str) {
            markerView.setMarkerWidth(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public void setRefX(MarkerView markerView, @Nullable Double d) {
            markerView.setRefX(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public void setRefY(MarkerView markerView, @Nullable Double d) {
            markerView.setRefY(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public void setMarkerHeight(MarkerView markerView, @Nullable Double d) {
            markerView.setMarkerHeight(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        public void setMarkerWidth(MarkerView markerView, @Nullable Double d) {
            markerView.setMarkerWidth(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "markerUnits")
        public void setMarkerUnits(MarkerView markerView, String str) {
            markerView.setMarkerUnits(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "orient")
        public void setOrient(MarkerView markerView, String str) {
            markerView.setOrient(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "minX")
        public void setMinX(MarkerView markerView, float f) {
            markerView.setMinX(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "minY")
        public void setMinY(MarkerView markerView, float f) {
            markerView.setMinY(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "vbWidth")
        public void setVbWidth(MarkerView markerView, float f) {
            markerView.setVbWidth(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "vbHeight")
        public void setVbHeight(MarkerView markerView, float f) {
            markerView.setVbHeight(f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "align")
        public void setAlign(MarkerView markerView, String str) {
            markerView.setAlign(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGMarkerManagerInterface
        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(MarkerView markerView, int i) {
            markerView.setMeetOrSlice(i);
        }
    }

    static class LinearGradientManager extends VirtualViewManager<LinearGradientView> implements RNSVGLinearGradientManagerInterface<LinearGradientView> {
        public static final String REACT_CLASS = "RNSVGLinearGradient";

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((LinearGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((LinearGradientManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((LinearGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((LinearGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((LinearGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((LinearGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((LinearGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((LinearGradientManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((LinearGradientManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((LinearGradientManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((LinearGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((LinearGradientManager) view, z);
        }

        LinearGradientManager() {
            super(VirtualViewManager.SVGClass.RNSVGLinearGradient);
            this.mDelegate = new RNSVGLinearGradientManagerDelegate(this);
        }

        @ReactProp(name = "x1")
        public void setX1(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setX1(dynamic);
        }

        @ReactProp(name = "y1")
        public void setY1(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setY1(dynamic);
        }

        @ReactProp(name = "x2")
        public void setX2(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setX2(dynamic);
        }

        @ReactProp(name = "y2")
        public void setY2(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setY2(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public void setX1(LinearGradientView linearGradientView, @Nullable String str) {
            linearGradientView.setX1(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public void setY1(LinearGradientView linearGradientView, @Nullable String str) {
            linearGradientView.setY1(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public void setX2(LinearGradientView linearGradientView, @Nullable String str) {
            linearGradientView.setX2(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public void setY2(LinearGradientView linearGradientView, @Nullable String str) {
            linearGradientView.setY2(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public void setX1(LinearGradientView linearGradientView, @Nullable Double d) {
            linearGradientView.setX1(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public void setY1(LinearGradientView linearGradientView, @Nullable Double d) {
            linearGradientView.setY1(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public void setX2(LinearGradientView linearGradientView, @Nullable Double d) {
            linearGradientView.setX2(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        public void setY2(LinearGradientView linearGradientView, @Nullable Double d) {
            linearGradientView.setY2(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "gradient")
        public void setGradient(LinearGradientView linearGradientView, ReadableArray readableArray) {
            linearGradientView.setGradient(readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "gradientUnits")
        public void setGradientUnits(LinearGradientView linearGradientView, int i) {
            linearGradientView.setGradientUnits(i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface
        @ReactProp(name = "gradientTransform")
        public void setGradientTransform(LinearGradientView linearGradientView, @Nullable ReadableArray readableArray) {
            linearGradientView.setGradientTransform(readableArray);
        }
    }

    static class RadialGradientManager extends VirtualViewManager<RadialGradientView> implements RNSVGRadialGradientManagerInterface<RadialGradientView> {
        public static final String REACT_CLASS = "RNSVGRadialGradient";

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "clipPath")
        public /* bridge */ /* synthetic */ void setClipPath(View view, String str) {
            super.setClipPath((RadialGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "clipRule")
        public /* bridge */ /* synthetic */ void setClipRule(View view, int i) {
            super.setClipRule((RadialGradientManager) view, i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "display")
        public /* bridge */ /* synthetic */ void setDisplay(View view, String str) {
            super.setDisplay((RadialGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "markerEnd")
        public /* bridge */ /* synthetic */ void setMarkerEnd(View view, String str) {
            super.setMarkerEnd((RadialGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "markerMid")
        public /* bridge */ /* synthetic */ void setMarkerMid(View view, String str) {
            super.setMarkerMid((RadialGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "markerStart")
        public /* bridge */ /* synthetic */ void setMarkerStart(View view, String str) {
            super.setMarkerStart((RadialGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "mask")
        public /* bridge */ /* synthetic */ void setMask(View view, String str) {
            super.setMask((RadialGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public /* bridge */ /* synthetic */ void setMatrix(View view, @Nullable ReadableArray readableArray) {
            super.setMatrix((RadialGradientManager) view, readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "name")
        public /* bridge */ /* synthetic */ void setName(View view, String str) {
            super.setName((RadialGradientManager) view, str);
        }

        @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
        @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((RadialGradientManager) view, f);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = ViewProps.POINTER_EVENTS)
        public /* bridge */ /* synthetic */ void setPointerEvents(View view, @Nullable String str) {
            super.setPointerEvents((RadialGradientManager) view, str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "responsible")
        public /* bridge */ /* synthetic */ void setResponsible(View view, boolean z) {
            super.setResponsible((RadialGradientManager) view, z);
        }

        RadialGradientManager() {
            super(VirtualViewManager.SVGClass.RNSVGRadialGradient);
            this.mDelegate = new RNSVGRadialGradientManagerDelegate(this);
        }

        @ReactProp(name = "fx")
        public void setFx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setFx(dynamic);
        }

        @ReactProp(name = "fy")
        public void setFy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setFy(dynamic);
        }

        @ReactProp(name = "rx")
        public void setRx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setRy(dynamic);
        }

        @ReactProp(name = "cx")
        public void setCx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setCy(dynamic);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setFx(RadialGradientView radialGradientView, @Nullable String str) {
            radialGradientView.setFx(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setFy(RadialGradientView radialGradientView, @Nullable String str) {
            radialGradientView.setFy(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setCx(RadialGradientView radialGradientView, @Nullable String str) {
            radialGradientView.setCx(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setCy(RadialGradientView radialGradientView, @Nullable String str) {
            radialGradientView.setCy(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setRx(RadialGradientView radialGradientView, @Nullable String str) {
            radialGradientView.setRx(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setRy(RadialGradientView radialGradientView, @Nullable String str) {
            radialGradientView.setRy(str);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setFx(RadialGradientView radialGradientView, @Nullable Double d) {
            radialGradientView.setFx(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setFy(RadialGradientView radialGradientView, @Nullable Double d) {
            radialGradientView.setFy(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setCx(RadialGradientView radialGradientView, @Nullable Double d) {
            radialGradientView.setCx(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setCy(RadialGradientView radialGradientView, @Nullable Double d) {
            radialGradientView.setCy(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setRx(RadialGradientView radialGradientView, @Nullable Double d) {
            radialGradientView.setRx(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        public void setRy(RadialGradientView radialGradientView, @Nullable Double d) {
            radialGradientView.setRy(d);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "gradient")
        public void setGradient(RadialGradientView radialGradientView, ReadableArray readableArray) {
            radialGradientView.setGradient(readableArray);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "gradientUnits")
        public void setGradientUnits(RadialGradientView radialGradientView, int i) {
            radialGradientView.setGradientUnits(i);
        }

        @Override // com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface
        @ReactProp(name = "gradientTransform")
        public void setGradientTransform(RadialGradientView radialGradientView, @Nullable ReadableArray readableArray) {
            radialGradientView.setGradientTransform(readableArray);
        }
    }

    @ReactProp(name = "fill")
    public void setFill(T t, @Nullable Dynamic dynamic) throws NumberFormatException {
        t.setFill(dynamic);
    }

    public void setFill(T t, @Nullable ReadableMap readableMap) {
        t.setFill(readableMap);
    }

    @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
    public void setFillOpacity(T t, float f) {
        t.setFillOpacity(f);
    }

    @ReactProp(defaultInt = 1, name = "fillRule")
    public void setFillRule(T t, int i) {
        t.setFillRule(i);
    }

    @ReactProp(name = "stroke")
    public void setStroke(T t, @Nullable Dynamic dynamic) throws NumberFormatException {
        t.setStroke(dynamic);
    }

    public void setStroke(T t, @Nullable ReadableMap readableMap) {
        t.setStroke(readableMap);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
    public void setStrokeOpacity(T t, float f) {
        t.setStrokeOpacity(f);
    }

    @ReactProp(name = "strokeDasharray")
    public void setStrokeDasharray(T t, @Nullable ReadableArray readableArray) {
        t.setStrokeDasharray(readableArray);
    }

    @ReactProp(name = "strokeDasharray")
    public void setStrokeDasharray(T t, @Nullable String str) {
        t.setStrokeDasharray(str);
    }

    @ReactProp(name = "strokeDashoffset")
    public void setStrokeDashoffset(T t, float f) {
        t.setStrokeDashoffset(f);
    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWidth(T t, Dynamic dynamic) {
        t.setStrokeWidth(dynamic);
    }

    public void setStrokeWidth(T t, @Nullable String str) {
        t.setStrokeWidth(str);
    }

    public void setStrokeWidth(T t, @Nullable Double d) {
        t.setStrokeWidth(d);
    }

    @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
    public void setStrokeMiterlimit(T t, float f) {
        t.setStrokeMiterlimit(f);
    }

    @ReactProp(defaultInt = 1, name = "strokeLinecap")
    public void setStrokeLinecap(T t, int i) {
        t.setStrokeLinecap(i);
    }

    @ReactProp(defaultInt = 1, name = "strokeLinejoin")
    public void setStrokeLinejoin(T t, int i) {
        t.setStrokeLinejoin(i);
    }

    @ReactProp(name = "vectorEffect")
    public void setVectorEffect(T t, int i) {
        t.setVectorEffect(i);
    }

    @ReactProp(name = "propList")
    public void setPropList(T t, @Nullable ReadableArray readableArray) {
        t.setPropList(readableArray);
    }
}
