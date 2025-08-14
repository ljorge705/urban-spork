package com.drew.metadata.gif;

import com.drew.metadata.TagDescriptor;

/* loaded from: classes5.dex */
public class GifAnimationDescriptor extends TagDescriptor<GifAnimationDirectory> {
    public GifAnimationDescriptor(GifAnimationDirectory gifAnimationDirectory) {
        super(gifAnimationDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getIterationCountDescription();
        }
        return super.getDescription(i);
    }

    public String getIterationCountDescription() {
        Integer integer = ((GifAnimationDirectory) this._directory).getInteger(1);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 0 ? "Infinite" : integer.intValue() == 1 ? "Once" : integer.intValue() == 2 ? "Twice" : integer.toString() + " times";
    }
}
