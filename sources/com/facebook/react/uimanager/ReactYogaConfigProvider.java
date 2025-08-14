package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConfig;
import com.facebook.yoga.YogaConfigFactory;
import com.facebook.yoga.YogaErrata;

/* loaded from: classes5.dex */
public class ReactYogaConfigProvider {
    private static YogaConfig YOGA_CONFIG;

    public static YogaConfig get() {
        if (YOGA_CONFIG == null) {
            YogaConfig yogaConfigCreate = YogaConfigFactory.create();
            YOGA_CONFIG = yogaConfigCreate;
            yogaConfigCreate.setPointScaleFactor(0.0f);
            YOGA_CONFIG.setErrata(YogaErrata.ALL);
        }
        return YOGA_CONFIG;
    }
}
