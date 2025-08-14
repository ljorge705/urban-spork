package com.uxcam.screenaction.models;

/* loaded from: classes6.dex */
public class KeyConstant {
    public static final int DOUBLE_TAP = 1;
    public static final String KEY_ACTIVITY_NAME = "an";
    public static final String KEY_APPEARED_TIME = "at";
    public static final String KEY_APP_STATUS = "status";
    public static final String KEY_CHILD_FRAGMENTS = "cfl";
    public static final String KEY_COORDINATES = "cor";
    public static final String KEY_EVENT = "name";
    public static final String KEY_FRAGMENT_ACTIVITY_NAME = "oan";
    public static final String KEY_FRAGMENT_DATA = "fd";
    public static final String KEY_FRAGMENT_NAME = "ofn";
    public static final String KEY_SCREEN = "screen";
    public static final String KEY_TIME = "time";
    public static final String KEY_VIEW_APPEARED = "va";
    public static final String KEY_VIEW_TIME = "vt";
    public static final int LONG_PRESS = 6;
    public static final int ORIENTATION_CHANGE = 10;
    public static final int ORIENTATION_LANDSCAPE_RIGHT = 0;
    public static final int ORIENTATION_PORTRAIT = 1;
    public static final int SCROLL = 12;
    public static final int SINGLE_TAP = 0;
    public static final int SWIPE_DOWN = 3;
    public static final int SWIPE_LEFT = 4;
    public static final int SWIPE_RIGHT = 5;
    public static final int SWIPE_UP = 2;
    public static final int TRAIL = 11;

    public static String getGestureName(int i) {
        switch (i) {
            case 0:
                return "SINGLE_TAP";
            case 1:
                return "DOUBLE_TAP";
            case 2:
                return "SWIPE_UP";
            case 3:
                return "SWIPE_DOWN";
            case 4:
                return "SWIPE_LEFT";
            case 5:
                return "SWIPE_RIGHT";
            case 6:
                return "LONG_PRESS";
            case 7:
            case 8:
            case 9:
            default:
                return Integer.toString(i);
            case 10:
                return "ORIENTATION_CHANGE";
            case 11:
                return "TRAIL";
            case 12:
                return "SCROLL";
        }
    }
}
