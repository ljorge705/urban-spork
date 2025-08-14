package com.uxcam.screenshot.model;

import java.util.List;

/* loaded from: classes6.dex */
public interface UXCamOcclusion {
    boolean getExcludeMentionedScreens();

    List<String> getScreens();

    boolean isWithoutGesture();
}
