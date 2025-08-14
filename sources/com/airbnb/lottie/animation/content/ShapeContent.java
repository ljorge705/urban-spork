package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapePath;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.List;

/* loaded from: classes5.dex */
public class ShapeContent implements PathContent, BaseKeyframeAnimation.AnimationListener {
    private final boolean hidden;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final ShapeKeyframeAnimation shapeAnimation;
    private List<ShapeModifierContent> shapeModifierContents;
    private final Path path = new Path();
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    public ShapeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapePath shapePath) {
        this.name = shapePath.getName();
        this.hidden = shapePath.isHidden();
        this.lottieDrawable = lottieDrawable;
        ShapeKeyframeAnimation shapeKeyframeAnimationCreateAnimation = shapePath.getShapePath().createAnimation();
        this.shapeAnimation = shapeKeyframeAnimationCreateAnimation;
        baseLayer.addAnimation(shapeKeyframeAnimationCreateAnimation);
        shapeKeyframeAnimationCreateAnimation.addUpdateListener(this);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    @Override // com.airbnb.lottie.animation.content.Content
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setContents(java.util.List<com.airbnb.lottie.animation.content.Content> r6, java.util.List<com.airbnb.lottie.animation.content.Content> r7) {
        /*
            r5 = this;
            r7 = 0
            r0 = 0
        L2:
            int r1 = r6.size()
            if (r0 >= r1) goto L39
            java.lang.Object r1 = r6.get(r0)
            com.airbnb.lottie.animation.content.Content r1 = (com.airbnb.lottie.animation.content.Content) r1
            boolean r2 = r1 instanceof com.airbnb.lottie.animation.content.TrimPathContent
            if (r2 == 0) goto L26
            r2 = r1
            com.airbnb.lottie.animation.content.TrimPathContent r2 = (com.airbnb.lottie.animation.content.TrimPathContent) r2
            com.airbnb.lottie.model.content.ShapeTrimPath$Type r3 = r2.getType()
            com.airbnb.lottie.model.content.ShapeTrimPath$Type r4 = com.airbnb.lottie.model.content.ShapeTrimPath.Type.SIMULTANEOUSLY
            if (r3 != r4) goto L26
            com.airbnb.lottie.animation.content.CompoundTrimPathContent r1 = r5.trimPaths
            r1.addTrimPath(r2)
            r2.addListener(r5)
            goto L36
        L26:
            boolean r2 = r1 instanceof com.airbnb.lottie.animation.content.ShapeModifierContent
            if (r2 == 0) goto L36
            if (r7 != 0) goto L31
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L31:
            com.airbnb.lottie.animation.content.ShapeModifierContent r1 = (com.airbnb.lottie.animation.content.ShapeModifierContent) r1
            r7.add(r1)
        L36:
            int r0 = r0 + 1
            goto L2
        L39:
            com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation r6 = r5.shapeAnimation
            r6.setShapeModifiers(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.ShapeContent.setContents(java.util.List, java.util.List):void");
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        Path value = this.shapeAnimation.getValue();
        if (value == null) {
            return this.path;
        }
        this.path.set(value);
        this.path.setFillType(Path.FillType.EVEN_ODD);
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }
}
