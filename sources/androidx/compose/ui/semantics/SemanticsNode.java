package androidx.compose.ui.semantics;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import io.sentry.protocol.ViewHierarchyNode;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SemanticsNode.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010B\u001a\u00020C2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00000EH\u0002J6\u0010F\u001a\u00020\u00002\b\u0010G\u001a\u0004\u0018\u00010H2\u0017\u0010I\u001a\u0013\u0012\u0004\u0012\u00020K\u0012\u0004\u0012\u00020C0J¢\u0006\u0002\bLH\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\bMJ\r\u0010N\u001a\u00020OH\u0000¢\u0006\u0002\bPJ\u001e\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00000\u00102\u000e\b\u0002\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00000EH\u0002J\u000e\u0010S\u001a\u00020\u00192\u0006\u0010T\u001a\u00020UJ\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00000\u00102\u0006\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u0005H\u0002J\u0010\u0010X\u001a\u00020C2\u0006\u0010Y\u001a\u00020\u0014H\u0002J\u001d\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00000\u00102\b\b\u0002\u0010W\u001a\u00020\u0005H\u0000¢\u0006\u0002\bZR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00000\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001eR\u0011\u0010\"\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001eR\u0011\u0010#\u001a\u00020$8F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0013\u0010,\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b-\u0010.R\u001a\u0010/\u001a\u0002008Fø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b1\u00102R\u001a\u00103\u001a\u0002008Fø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b4\u00102R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\u00000\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u0012R\u0013\u00107\u001a\u0004\u0018\u0001088F¢\u0006\u0006\u001a\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<8Fø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b=\u00102R\u0011\u0010>\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b?\u0010\fR\u0014\u0010@\u001a\u00020\u0014X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0016\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006["}, d2 = {"Landroidx/compose/ui/semantics/SemanticsNode;", "", "outerSemanticsNode", "Landroidx/compose/ui/node/SemanticsModifierNode;", "mergingEnabled", "", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/SemanticsModifierNode;ZLandroidx/compose/ui/node/LayoutNode;)V", "boundsInRoot", "Landroidx/compose/ui/geometry/Rect;", "getBoundsInRoot", "()Landroidx/compose/ui/geometry/Rect;", "boundsInWindow", "getBoundsInWindow", ViewHierarchyNode.JsonKeys.CHILDREN, "", "getChildren", "()Ljava/util/List;", "config", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getConfig", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "fakeNodeParent", "id", "", "getId", "()I", "isFake", "isFake$ui_release", "()Z", "setFake$ui_release", "(Z)V", "isMergingSemanticsOfDescendants", "isRoot", "layoutInfo", "Landroidx/compose/ui/layout/LayoutInfo;", "getLayoutInfo", "()Landroidx/compose/ui/layout/LayoutInfo;", "getLayoutNode$ui_release", "()Landroidx/compose/ui/node/LayoutNode;", "getMergingEnabled", "getOuterSemanticsNode$ui_release", "()Landroidx/compose/ui/node/SemanticsModifierNode;", "parent", "getParent", "()Landroidx/compose/ui/semantics/SemanticsNode;", "positionInRoot", "Landroidx/compose/ui/geometry/Offset;", "getPositionInRoot-F1C5BW0", "()J", "positionInWindow", "getPositionInWindow-F1C5BW0", "replacedChildren", "getReplacedChildren$ui_release", "root", "Landroidx/compose/ui/node/RootForTest;", "getRoot", "()Landroidx/compose/ui/node/RootForTest;", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "touchBoundsInRoot", "getTouchBoundsInRoot", "unmergedConfig", "getUnmergedConfig$ui_release", "emitFakeNodes", "", "unmergedChildren", "", "fakeSemanticsNode", ViewProps.ROLE, "Landroidx/compose/ui/semantics/Role;", OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, "Lkotlin/Function1;", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lkotlin/ExtensionFunctionType;", "fakeSemanticsNode-ypyhhiA", "findCoordinatorToGetBounds", "Landroidx/compose/ui/node/NodeCoordinator;", "findCoordinatorToGetBounds$ui_release", "findOneLayerOfMergingSemanticsNodes", "list", "getAlignmentLinePosition", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "includeReplacedSemantics", "includeFakeNodes", "mergeConfig", "mergedConfig", "unmergedChildren$ui_release", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SemanticsNode {
    public static final int $stable = 8;
    private SemanticsNode fakeNodeParent;
    private final int id;
    private boolean isFake;
    private final LayoutNode layoutNode;
    private final boolean mergingEnabled;
    private final SemanticsModifierNode outerSemanticsNode;
    private final SemanticsConfiguration unmergedConfig;

    public final int getId() {
        return this.id;
    }

    /* renamed from: getLayoutNode$ui_release, reason: from getter */
    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public final boolean getMergingEnabled() {
        return this.mergingEnabled;
    }

    /* renamed from: getOuterSemanticsNode$ui_release, reason: from getter */
    public final SemanticsModifierNode getOuterSemanticsNode() {
        return this.outerSemanticsNode;
    }

    /* renamed from: getUnmergedConfig$ui_release, reason: from getter */
    public final SemanticsConfiguration getUnmergedConfig() {
        return this.unmergedConfig;
    }

    /* renamed from: isFake$ui_release, reason: from getter */
    public final boolean getIsFake() {
        return this.isFake;
    }

    public final void setFake$ui_release(boolean z) {
        this.isFake = z;
    }

    public SemanticsNode(SemanticsModifierNode outerSemanticsNode, boolean z, LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(outerSemanticsNode, "outerSemanticsNode");
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.outerSemanticsNode = outerSemanticsNode;
        this.mergingEnabled = z;
        this.layoutNode = layoutNode;
        this.unmergedConfig = SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemanticsNode);
        this.id = layoutNode.getSemanticsId();
    }

    public /* synthetic */ SemanticsNode(SemanticsModifierNode semanticsModifierNode, boolean z, LayoutNode layoutNode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(semanticsModifierNode, z, (i & 4) != 0 ? DelegatableNodeKt.requireLayoutNode(semanticsModifierNode) : layoutNode);
    }

    public final LayoutInfo getLayoutInfo() {
        return this.layoutNode;
    }

    public final RootForTest getRoot() {
        Owner owner = this.layoutNode.getOwner();
        if (owner != null) {
            return owner.getRootForTest();
        }
        return null;
    }

    public final Rect getTouchBoundsInRoot() {
        SemanticsModifierNode outerMergingSemantics;
        if (!this.unmergedConfig.getIsMergingSemanticsOfDescendants() || (outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(this.layoutNode)) == null) {
            outerMergingSemantics = this.outerSemanticsNode;
        }
        return SemanticsModifierNodeKt.touchBoundsInRoot(outerMergingSemantics);
    }

    /* renamed from: getSize-YbymL2g, reason: not valid java name */
    public final long m3762getSizeYbymL2g() {
        return findCoordinatorToGetBounds$ui_release().mo3401getSizeYbymL2g();
    }

    public final Rect getBoundsInRoot() {
        return !this.layoutNode.isAttached() ? Rect.INSTANCE.getZero() : LayoutCoordinatesKt.boundsInRoot(findCoordinatorToGetBounds$ui_release());
    }

    /* renamed from: getPositionInRoot-F1C5BW0, reason: not valid java name */
    public final long m3760getPositionInRootF1C5BW0() {
        return !this.layoutNode.isAttached() ? Offset.INSTANCE.m1655getZeroF1C5BW0() : LayoutCoordinatesKt.positionInRoot(findCoordinatorToGetBounds$ui_release());
    }

    public final Rect getBoundsInWindow() {
        return !this.layoutNode.isAttached() ? Rect.INSTANCE.getZero() : LayoutCoordinatesKt.boundsInWindow(findCoordinatorToGetBounds$ui_release());
    }

    /* renamed from: getPositionInWindow-F1C5BW0, reason: not valid java name */
    public final long m3761getPositionInWindowF1C5BW0() {
        return !this.layoutNode.isAttached() ? Offset.INSTANCE.m1655getZeroF1C5BW0() : LayoutCoordinatesKt.positionInWindow(findCoordinatorToGetBounds$ui_release());
    }

    public final int getAlignmentLinePosition(AlignmentLine alignmentLine) {
        Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
        return findCoordinatorToGetBounds$ui_release().get(alignmentLine);
    }

    public final SemanticsConfiguration getConfig() {
        if (!isMergingSemanticsOfDescendants()) {
            return this.unmergedConfig;
        }
        SemanticsConfiguration semanticsConfigurationCopy = this.unmergedConfig.copy();
        mergeConfig(semanticsConfigurationCopy);
        return semanticsConfigurationCopy;
    }

    private final void mergeConfig(SemanticsConfiguration mergedConfig) {
        if (this.unmergedConfig.getIsClearingSemantics()) {
            return;
        }
        List listUnmergedChildren$ui_release$default = unmergedChildren$ui_release$default(this, false, 1, null);
        int size = listUnmergedChildren$ui_release$default.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode = (SemanticsNode) listUnmergedChildren$ui_release$default.get(i);
            if (!semanticsNode.isMergingSemanticsOfDescendants()) {
                mergedConfig.mergeChild$ui_release(semanticsNode.unmergedConfig);
                semanticsNode.mergeConfig(mergedConfig);
            }
        }
    }

    private final boolean isMergingSemanticsOfDescendants() {
        return this.mergingEnabled && this.unmergedConfig.getIsMergingSemanticsOfDescendants();
    }

    public static /* synthetic */ List unmergedChildren$ui_release$default(SemanticsNode semanticsNode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return semanticsNode.unmergedChildren$ui_release(z);
    }

    public final List<SemanticsNode> unmergedChildren$ui_release(boolean includeFakeNodes) {
        if (this.isFake) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        List listFindOneLayerOfSemanticsWrappers$default = SemanticsNodeKt.findOneLayerOfSemanticsWrappers$default(this.layoutNode, null, 1, null);
        int size = listFindOneLayerOfSemanticsWrappers$default.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new SemanticsNode((SemanticsModifierNode) listFindOneLayerOfSemanticsWrappers$default.get(i), this.mergingEnabled, null, 4, null));
        }
        if (includeFakeNodes) {
            emitFakeNodes(arrayList);
        }
        return arrayList;
    }

    public final List<SemanticsNode> getChildren() {
        return getChildren(!this.mergingEnabled, false);
    }

    public final List<SemanticsNode> getReplacedChildren$ui_release() {
        return getChildren(false, true);
    }

    private final List<SemanticsNode> getChildren(boolean includeReplacedSemantics, boolean includeFakeNodes) {
        if (!includeReplacedSemantics && this.unmergedConfig.getIsClearingSemantics()) {
            return CollectionsKt.emptyList();
        }
        if (isMergingSemanticsOfDescendants()) {
            return findOneLayerOfMergingSemanticsNodes$default(this, null, 1, null);
        }
        return unmergedChildren$ui_release(includeFakeNodes);
    }

    public final boolean isRoot() {
        return getParent() == null;
    }

    public final SemanticsNode getParent() {
        SemanticsNode semanticsNode = this.fakeNodeParent;
        if (semanticsNode != null) {
            return semanticsNode;
        }
        LayoutNode layoutNodeFindClosestParentNode = this.mergingEnabled ? SemanticsNodeKt.findClosestParentNode(this.layoutNode, new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.semantics.SemanticsNode$parent$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(LayoutNode it) {
                SemanticsConfiguration semanticsConfigurationCollapsedSemanticsConfiguration;
                Intrinsics.checkNotNullParameter(it, "it");
                SemanticsModifierNode outerSemantics = SemanticsNodeKt.getOuterSemantics(it);
                boolean z = false;
                if (outerSemantics != null && (semanticsConfigurationCollapsedSemanticsConfiguration = SemanticsModifierNodeKt.collapsedSemanticsConfiguration(outerSemantics)) != null && semanticsConfigurationCollapsedSemanticsConfiguration.getIsMergingSemanticsOfDescendants()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }) : null;
        if (layoutNodeFindClosestParentNode == null) {
            layoutNodeFindClosestParentNode = SemanticsNodeKt.findClosestParentNode(this.layoutNode, new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.semantics.SemanticsNode$parent$2
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(LayoutNode it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(SemanticsNodeKt.getOuterSemantics(it) != null);
                }
            });
        }
        SemanticsModifierNode outerSemantics = layoutNodeFindClosestParentNode != null ? SemanticsNodeKt.getOuterSemantics(layoutNodeFindClosestParentNode) : null;
        if (outerSemantics == null) {
            return null;
        }
        return new SemanticsNode(outerSemantics, this.mergingEnabled, null, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ List findOneLayerOfMergingSemanticsNodes$default(SemanticsNode semanticsNode, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        return semanticsNode.findOneLayerOfMergingSemanticsNodes(list);
    }

    private final List<SemanticsNode> findOneLayerOfMergingSemanticsNodes(List<SemanticsNode> list) {
        List listUnmergedChildren$ui_release$default = unmergedChildren$ui_release$default(this, false, 1, null);
        int size = listUnmergedChildren$ui_release$default.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode = (SemanticsNode) listUnmergedChildren$ui_release$default.get(i);
            if (semanticsNode.isMergingSemanticsOfDescendants()) {
                list.add(semanticsNode);
            } else if (!semanticsNode.unmergedConfig.getIsClearingSemantics()) {
                semanticsNode.findOneLayerOfMergingSemanticsNodes(list);
            }
        }
        return list;
    }

    public final NodeCoordinator findCoordinatorToGetBounds$ui_release() {
        if (this.unmergedConfig.getIsMergingSemanticsOfDescendants()) {
            SemanticsModifierNode outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(this.layoutNode);
            if (outerMergingSemantics == null) {
                outerMergingSemantics = this.outerSemanticsNode;
            }
            return DelegatableNodeKt.m3502requireCoordinator64DMado(outerMergingSemantics, NodeKind.m3598constructorimpl(8));
        }
        return DelegatableNodeKt.m3502requireCoordinator64DMado(this.outerSemanticsNode, NodeKind.m3598constructorimpl(8));
    }

    private final void emitFakeNodes(List<SemanticsNode> unmergedChildren) {
        final Role role = SemanticsNodeKt.getRole(this);
        if (role != null && this.unmergedConfig.getIsMergingSemanticsOfDescendants() && (!unmergedChildren.isEmpty())) {
            unmergedChildren.add(m3759fakeSemanticsNodeypyhhiA(role, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.semantics.SemanticsNode$emitFakeNodes$fakeNode$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver fakeSemanticsNode) {
                    Intrinsics.checkNotNullParameter(fakeSemanticsNode, "$this$fakeSemanticsNode");
                    SemanticsPropertiesKt.m3766setRolekuIjeqM(fakeSemanticsNode, role.getValue());
                }
            }));
        }
        if (this.unmergedConfig.contains(SemanticsProperties.INSTANCE.getContentDescription()) && (!unmergedChildren.isEmpty()) && this.unmergedConfig.getIsMergingSemanticsOfDescendants()) {
            List list = (List) SemanticsConfigurationKt.getOrNull(this.unmergedConfig, SemanticsProperties.INSTANCE.getContentDescription());
            final String str = list != null ? (String) CollectionsKt.firstOrNull(list) : null;
            if (str != null) {
                unmergedChildren.add(0, m3759fakeSemanticsNodeypyhhiA(null, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.ui.semantics.SemanticsNode$emitFakeNodes$fakeNode$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver fakeSemanticsNode) {
                        Intrinsics.checkNotNullParameter(fakeSemanticsNode, "$this$fakeSemanticsNode");
                        SemanticsPropertiesKt.setContentDescription(fakeSemanticsNode, str);
                    }
                }));
            }
        }
    }

    /* renamed from: fakeSemanticsNode-ypyhhiA, reason: not valid java name */
    private final SemanticsNode m3759fakeSemanticsNodeypyhhiA(Role role, Function1<? super SemanticsPropertyReceiver, Unit> properties) {
        SemanticsNode semanticsNode = new SemanticsNode(new SemanticsNode$fakeSemanticsNode$fakeNode$1(properties), false, new LayoutNode(true, role != null ? SemanticsNodeKt.roleFakeNodeId(this) : SemanticsNodeKt.contentDescriptionFakeNodeId(this)));
        semanticsNode.isFake = true;
        semanticsNode.fakeNodeParent = this;
        return semanticsNode;
    }
}
