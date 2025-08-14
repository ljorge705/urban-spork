package com.reactnativecommunity.picker;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.LayoutShadowNode;

/* loaded from: classes6.dex */
public class ReactPickerShadowNode extends LayoutShadowNode {
    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object obj) {
        Assertions.assertCondition(obj instanceof ReactPickerLocalData);
        setStyleMinHeight(((ReactPickerLocalData) obj).getHeight());
    }
}
