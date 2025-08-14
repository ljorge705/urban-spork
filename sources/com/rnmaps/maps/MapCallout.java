package com.rnmaps.maps;

import android.content.Context;
import com.facebook.react.views.view.ReactViewGroup;

/* loaded from: classes6.dex */
public class MapCallout extends ReactViewGroup {
    public int height;
    private boolean tooltip;
    public int width;

    public boolean getTooltip() {
        return this.tooltip;
    }

    public void setTooltip(boolean z) {
        this.tooltip = z;
    }

    public MapCallout(Context context) {
        super(context);
        this.tooltip = false;
    }
}
