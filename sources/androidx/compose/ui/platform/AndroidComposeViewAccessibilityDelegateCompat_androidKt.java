package androidx.compose.ui.platform;

import android.graphics.Region;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\r\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0012\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\b\u0012\u0004\u0012\u00020\u00140\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000\u001a\"\u0010\u0018\u001a\u0004\u0018\u00010\u0019*\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u001bH\u0002\u001a\u0018\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001e0\u001d*\u00020\u001fH\u0000\u001a\f\u0010 \u001a\u00020\u0001*\u00020\u0002H\u0002\u001a \u0010!\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020#0\"2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0002\u001a\u0014\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010&\u001a\u00020'H\u0002\u001a\u001b\u0010(\u001a\b\u0012\u0004\u0012\u00020#0\"*\u00020#2\u0006\u0010)\u001a\u00020#H\u0080\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0004\"\u0018\u0010\b\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004\"\u0018\u0010\t\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0004\"\u0018\u0010\n\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0004\"\u0018\u0010\u000b\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0004¨\u0006*"}, d2 = {"hasCollectionInfo", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "getHasCollectionInfo", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "isContainer", "(Landroidx/compose/ui/semantics/SemanticsNode;)Ljava/lang/Boolean;", "isPassword", "isRtl", "isScrollable", "isTextField", "semanticsNodeIsStructurallySignificant", "getSemanticsNodeIsStructurallySignificant", "accessibilityEquals", "Landroidx/compose/ui/semantics/AccessibilityAction;", "other", "", ViewProps.ENABLED, "excludeLineAndPageGranularities", "findById", "Landroidx/compose/ui/platform/ScrollObservationScope;", "", "id", "", "findClosestParentNode", "Landroidx/compose/ui/node/LayoutNode;", "selector", "Lkotlin/Function1;", "getAllUncoveredSemanticsNodesToMap", "", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "Landroidx/compose/ui/semantics/SemanticsOwner;", "hasPaneTitle", "overlaps", "Landroidx/compose/ui/platform/OpenEndRange;", "", "it", "propertiesDeleted", "oldNode", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;", "rangeUntil", "that", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat_androidKt {
    public static final OpenEndRange<Float> rangeUntil(float f, float f2) {
        return new OpenEndFloatRange(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean overlaps(OpenEndRange<Float> openEndRange, OpenEndRange<Float> openEndRange2) {
        return (openEndRange.isEmpty() || openEndRange2.isEmpty() || Math.max(((Number) openEndRange.getStart()).floatValue(), ((Number) openEndRange2.getStart()).floatValue()) >= Math.min(((Number) openEndRange.getEndExclusive()).floatValue(), ((Number) openEndRange2.getEndExclusive()).floatValue())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutNode findClosestParentNode(LayoutNode layoutNode, Function1<? super LayoutNode, Boolean> function1) {
        for (LayoutNode parent$ui_release = layoutNode.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            if (function1.invoke(parent$ui_release).booleanValue()) {
                return parent$ui_release;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean enabled(SemanticsNode semanticsNode) {
        return SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), SemanticsProperties.INSTANCE.getDisabled()) == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean propertiesDeleted(SemanticsNode semanticsNode, AndroidComposeViewAccessibilityDelegateCompat.SemanticsNodeCopy semanticsNodeCopy) {
        Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> it = semanticsNodeCopy.getUnmergedConfig().iterator();
        while (it.hasNext()) {
            if (!semanticsNode.getConfig().contains(it.next().getKey())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasPaneTitle(SemanticsNode semanticsNode) {
        return semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getPaneTitle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isPassword(SemanticsNode semanticsNode) {
        return semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getPassword());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isTextField(SemanticsNode semanticsNode) {
        return semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetText());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isRtl(SemanticsNode semanticsNode) {
        return semanticsNode.getLayoutInfo().getLayoutDirection() == LayoutDirection.Rtl;
    }

    private static final Boolean isContainer(SemanticsNode semanticsNode) {
        return (Boolean) SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), SemanticsProperties.INSTANCE.getIsContainer());
    }

    private static final boolean getHasCollectionInfo(SemanticsNode semanticsNode) {
        return semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getCollectionInfo());
    }

    private static final boolean isScrollable(SemanticsNode semanticsNode) {
        return semanticsNode.getConfig().contains(SemanticsActions.INSTANCE.getScrollBy());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getSemanticsNodeIsStructurallySignificant(SemanticsNode semanticsNode) {
        if (Intrinsics.areEqual((Object) isContainer(semanticsNode), (Object) false)) {
            return false;
        }
        return Intrinsics.areEqual((Object) isContainer(semanticsNode), (Object) true) || getHasCollectionInfo(semanticsNode) || isScrollable(semanticsNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean excludeLineAndPageGranularities(SemanticsNode semanticsNode) {
        SemanticsModifierNode outerSemantics;
        SemanticsConfiguration semanticsConfigurationCollapsedSemanticsConfiguration;
        if (isTextField(semanticsNode) && !Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getFocused()), (Object) true)) {
            return true;
        }
        LayoutNode layoutNodeFindClosestParentNode = findClosestParentNode(semanticsNode.getLayoutNode(), new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt$excludeLineAndPageGranularities$ancestor$1
            /* JADX WARN: Removed duplicated region for block: B:12:0x0027  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Boolean invoke(androidx.compose.ui.node.LayoutNode r3) {
                /*
                    r2 = this;
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                    androidx.compose.ui.node.SemanticsModifierNode r3 = androidx.compose.ui.semantics.SemanticsNodeKt.getOuterSemantics(r3)
                    if (r3 == 0) goto L10
                    androidx.compose.ui.semantics.SemanticsConfiguration r3 = androidx.compose.ui.node.SemanticsModifierNodeKt.collapsedSemanticsConfiguration(r3)
                    goto L11
                L10:
                    r3 = 0
                L11:
                    if (r3 == 0) goto L27
                    boolean r0 = r3.getIsMergingSemanticsOfDescendants()
                    r1 = 1
                    if (r0 != r1) goto L27
                    androidx.compose.ui.semantics.SemanticsActions r0 = androidx.compose.ui.semantics.SemanticsActions.INSTANCE
                    androidx.compose.ui.semantics.SemanticsPropertyKey r0 = r0.getSetText()
                    boolean r3 = r3.contains(r0)
                    if (r3 == 0) goto L27
                    goto L28
                L27:
                    r1 = 0
                L28:
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt$excludeLineAndPageGranularities$ancestor$1.invoke(androidx.compose.ui.node.LayoutNode):java.lang.Boolean");
            }
        });
        return layoutNodeFindClosestParentNode != null && ((outerSemantics = SemanticsNodeKt.getOuterSemantics(layoutNodeFindClosestParentNode)) == null || (semanticsConfigurationCollapsedSemanticsConfiguration = SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemantics)) == null || !Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsConfigurationCollapsedSemanticsConfiguration, SemanticsProperties.INSTANCE.getFocused()), (Object) true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean accessibilityEquals(AccessibilityAction<?> accessibilityAction, Object obj) {
        if (accessibilityAction == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityAction)) {
            return false;
        }
        AccessibilityAction accessibilityAction2 = (AccessibilityAction) obj;
        if (!Intrinsics.areEqual(accessibilityAction.getLabel(), accessibilityAction2.getLabel())) {
            return false;
        }
        if (accessibilityAction.getAction() != null || accessibilityAction2.getAction() == null) {
            return accessibilityAction.getAction() == null || accessibilityAction2.getAction() != null;
        }
        return false;
    }

    public static final Map<Integer, SemanticsNodeWithAdjustedBounds> getAllUncoveredSemanticsNodesToMap(SemanticsOwner semanticsOwner) {
        Intrinsics.checkNotNullParameter(semanticsOwner, "<this>");
        SemanticsNode unmergedRootSemanticsNode = semanticsOwner.getUnmergedRootSemanticsNode();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (unmergedRootSemanticsNode.getLayoutNode().getIsPlaced() && unmergedRootSemanticsNode.getLayoutNode().isAttached()) {
            Region region = new Region();
            Rect boundsInRoot = unmergedRootSemanticsNode.getBoundsInRoot();
            region.set(new android.graphics.Rect(MathKt.roundToInt(boundsInRoot.getLeft()), MathKt.roundToInt(boundsInRoot.getTop()), MathKt.roundToInt(boundsInRoot.getRight()), MathKt.roundToInt(boundsInRoot.getBottom())));
            getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(region, unmergedRootSemanticsNode, linkedHashMap, unmergedRootSemanticsNode);
        }
        return linkedHashMap;
    }

    private static final void getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(Region region, SemanticsNode semanticsNode, Map<Integer, SemanticsNodeWithAdjustedBounds> map, SemanticsNode semanticsNode2) {
        Rect rect;
        LayoutInfo layoutInfo;
        boolean z = (semanticsNode2.getLayoutNode().getIsPlaced() && semanticsNode2.getLayoutNode().isAttached()) ? false : true;
        if (!region.isEmpty() || semanticsNode2.getId() == semanticsNode.getId()) {
            if (!z || semanticsNode2.getIsFake()) {
                android.graphics.Rect rect2 = new android.graphics.Rect(MathKt.roundToInt(semanticsNode2.getTouchBoundsInRoot().getLeft()), MathKt.roundToInt(semanticsNode2.getTouchBoundsInRoot().getTop()), MathKt.roundToInt(semanticsNode2.getTouchBoundsInRoot().getRight()), MathKt.roundToInt(semanticsNode2.getTouchBoundsInRoot().getBottom()));
                Region region2 = new Region();
                region2.set(rect2);
                int id = semanticsNode2.getId() == semanticsNode.getId() ? -1 : semanticsNode2.getId();
                if (region2.op(region, region2, Region.Op.INTERSECT)) {
                    Integer numValueOf = Integer.valueOf(id);
                    android.graphics.Rect bounds = region2.getBounds();
                    Intrinsics.checkNotNullExpressionValue(bounds, "region.bounds");
                    map.put(numValueOf, new SemanticsNodeWithAdjustedBounds(semanticsNode2, bounds));
                    List<SemanticsNode> replacedChildren$ui_release = semanticsNode2.getReplacedChildren$ui_release();
                    for (int size = replacedChildren$ui_release.size() - 1; -1 < size; size--) {
                        getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(region, semanticsNode, map, replacedChildren$ui_release.get(size));
                    }
                    region.op(rect2, region, Region.Op.REVERSE_DIFFERENCE);
                    return;
                }
                if (!semanticsNode2.getIsFake()) {
                    if (id == -1) {
                        Integer numValueOf2 = Integer.valueOf(id);
                        android.graphics.Rect bounds2 = region2.getBounds();
                        Intrinsics.checkNotNullExpressionValue(bounds2, "region.bounds");
                        map.put(numValueOf2, new SemanticsNodeWithAdjustedBounds(semanticsNode2, bounds2));
                        return;
                    }
                    return;
                }
                SemanticsNode parent = semanticsNode2.getParent();
                if (parent != null && (layoutInfo = parent.getLayoutInfo()) != null && layoutInfo.getIsPlaced()) {
                    rect = parent.getBoundsInRoot();
                } else {
                    rect = new Rect(0.0f, 0.0f, 10.0f, 10.0f);
                }
                map.put(Integer.valueOf(id), new SemanticsNodeWithAdjustedBounds(semanticsNode2, new android.graphics.Rect(MathKt.roundToInt(rect.getLeft()), MathKt.roundToInt(rect.getTop()), MathKt.roundToInt(rect.getRight()), MathKt.roundToInt(rect.getBottom()))));
            }
        }
    }

    public static final ScrollObservationScope findById(List<ScrollObservationScope> list, int i) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (list.get(i2).getSemanticsNodeId() == i) {
                return list.get(i2);
            }
        }
        return null;
    }
}
