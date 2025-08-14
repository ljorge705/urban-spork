package com.onfido.android.sdk.capture.internal.util;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/ImageResult;", "", "imageContent", "", "dimension", "Lcom/onfido/android/sdk/capture/internal/util/Dimension;", "([BLcom/onfido/android/sdk/capture/internal/util/Dimension;)V", "getDimension", "()Lcom/onfido/android/sdk/capture/internal/util/Dimension;", "getImageContent", "()[B", "equals", "", "other", "hashCode", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageResult {
    private final Dimension dimension;
    private final byte[] imageContent;

    public ImageResult(byte[] imageContent, Dimension dimension) {
        Intrinsics.checkNotNullParameter(imageContent, "imageContent");
        Intrinsics.checkNotNullParameter(dimension, "dimension");
        this.imageContent = imageContent;
        this.dimension = dimension;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ImageResult.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.onfido.android.sdk.capture.internal.util.ImageResult");
        ImageResult imageResult = (ImageResult) other;
        return Arrays.equals(this.imageContent, imageResult.imageContent) && Intrinsics.areEqual(this.dimension, imageResult.dimension);
    }

    public final Dimension getDimension() {
        return this.dimension;
    }

    public final byte[] getImageContent() {
        return this.imageContent;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.imageContent) * 31) + this.dimension.hashCode();
    }
}
