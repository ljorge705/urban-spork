package com.drew.metadata.heif;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class HeifBoxTypes {
    public static final String BOX_AUXILIARY_TYPE_PROPERTY = "auxC";
    public static final String BOX_COLOUR_INFO = "colr";
    public static final String BOX_FILE_TYPE = "ftyp";
    public static final String BOX_HANDLER = "hdlr";
    public static final String BOX_HVC1 = "hvc1";
    public static final String BOX_IMAGE_ROTATION = "irot";
    public static final String BOX_IMAGE_SPATIAL_EXTENTS = "ispe";
    public static final String BOX_ITEM_INFO = "iinf";
    public static final String BOX_ITEM_LOCATION = "iloc";
    public static final String BOX_ITEM_PROTECTION = "ipro";
    public static final String BOX_PIXEL_INFORMATION = "pixi";
    public static final String BOX_PRIMARY_ITEM = "pitm";
    public static ArrayList<String> _boxList;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        _boxList = arrayList;
        arrayList.add("ftyp");
        _boxList.add(BOX_ITEM_PROTECTION);
        _boxList.add(BOX_PRIMARY_ITEM);
        _boxList.add(BOX_ITEM_INFO);
        _boxList.add(BOX_ITEM_LOCATION);
        _boxList.add("hdlr");
        _boxList.add(BOX_HVC1);
        _boxList.add(BOX_IMAGE_SPATIAL_EXTENTS);
        _boxList.add(BOX_AUXILIARY_TYPE_PROPERTY);
        _boxList.add(BOX_IMAGE_ROTATION);
        _boxList.add(BOX_COLOUR_INFO);
        _boxList.add(BOX_PIXEL_INFORMATION);
    }
}
