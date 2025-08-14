package com.clevertap.android.sdk.gif;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
class GifHeader {
    int bgColor;
    int bgIndex;
    GifFrame currentFrame;
    boolean gctFlag;
    int gctSize;
    int height;
    int pixelAspect;
    int width;
    int frameCount = 0;
    List<GifFrame> frames = new ArrayList();
    int[] gct = null;
    int loopCount = 0;
    int status = 0;

    public int getHeight() {
        return this.height;
    }

    public int getNumFrames() {
        return this.frameCount;
    }

    public int getStatus() {
        return this.status;
    }

    public int getWidth() {
        return this.width;
    }

    GifHeader() {
    }
}
