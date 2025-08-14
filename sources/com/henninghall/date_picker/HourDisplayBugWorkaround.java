package com.henninghall.date_picker;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class HourDisplayBugWorkaround {
    private final State state;

    public HourDisplayBugWorkaround(State state) {
        this.state = state;
    }

    private boolean shouldApply(String str) {
        return str.length() == 1;
    }

    private String adjust(String str) {
        return StringUtils.SPACE + str + StringUtils.SPACE;
    }

    public String adjustValueIfNecessary(String str) {
        return !shouldApply(str) ? str : adjust(str);
    }
}
