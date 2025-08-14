package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes5.dex */
class DivisionAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public DivisionAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray array = readableMap.getArray("input");
        this.mInputNodes = new int[array.size()];
        int i = 0;
        while (true) {
            int[] iArr = this.mInputNodes;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = array.getInt(i);
            i++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x005a, code lost:
    
        throw new com.facebook.react.bridge.JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.divide node with Animated ID " + r5.mTag);
     */
    @Override // com.facebook.react.animated.AnimatedNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update() {
        /*
            r5 = this;
            r0 = 0
        L1:
            int[] r1 = r5.mInputNodes
            int r2 = r1.length
            if (r0 >= r2) goto L5b
            com.facebook.react.animated.NativeAnimatedNodesManager r2 = r5.mNativeAnimatedNodesManager
            r1 = r1[r0]
            com.facebook.react.animated.AnimatedNode r1 = r2.getNodeById(r1)
            if (r1 == 0) goto L44
            boolean r2 = r1 instanceof com.facebook.react.animated.ValueAnimatedNode
            if (r2 == 0) goto L44
            com.facebook.react.animated.ValueAnimatedNode r1 = (com.facebook.react.animated.ValueAnimatedNode) r1
            double r1 = r1.getValue()
            if (r0 != 0) goto L1f
            r5.mValue = r1
            goto L2a
        L1f:
            r3 = 0
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L2d
            double r3 = r5.mValue
            double r3 = r3 / r1
            r5.mValue = r3
        L2a:
            int r0 = r0 + 1
            goto L1
        L2d:
            com.facebook.react.bridge.JSApplicationCausedNativeException r0 = new com.facebook.react.bridge.JSApplicationCausedNativeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Detected a division by zero in Animated.divide node with Animated ID "
            r1.<init>(r2)
            int r2 = r5.mTag
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L44:
            com.facebook.react.bridge.JSApplicationCausedNativeException r0 = new com.facebook.react.bridge.JSApplicationCausedNativeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Illegal node ID set as an input for Animated.divide node with Animated ID "
            r1.<init>(r2)
            int r2 = r5.mTag
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.DivisionAnimatedNode.update():void");
    }

    @Override // com.facebook.react.animated.ValueAnimatedNode, com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder sbAppend = new StringBuilder("DivisionAnimatedNode[").append(this.mTag).append("]: input nodes: ");
        int[] iArr = this.mInputNodes;
        return sbAppend.append(iArr != null ? iArr.toString() : "null").append(" - super: ").append(super.prettyPrint()).toString();
    }
}
