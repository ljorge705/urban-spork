package net.time4j.format;

import net.time4j.engine.DisplayStyle;

/* loaded from: classes4.dex */
public enum DisplayMode implements DisplayStyle {
    FULL(0),
    LONG(1),
    MEDIUM(2),
    SHORT(3);

    private static DisplayMode[] ENUMS = values();
    private final transient int style;

    @Override // net.time4j.engine.DisplayStyle
    public int getStyleValue() {
        return this.style;
    }

    DisplayMode(int i) {
        this.style = i;
    }

    public static DisplayMode ofStyle(int i) {
        for (DisplayMode displayMode : ENUMS) {
            if (displayMode.getStyleValue() == i) {
                return displayMode;
            }
        }
        throw new UnsupportedOperationException("Unknown format style: " + i);
    }
}
