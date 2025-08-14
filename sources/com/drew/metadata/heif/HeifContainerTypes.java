package com.drew.metadata.heif;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class HeifContainerTypes {
    public static final String BOX_IMAGE_PROPERTY = "iprp";
    public static final String BOX_ITEM_PROPERTY = "ipco";
    public static final String BOX_METADATA = "meta";
    public static ArrayList<String> _containerList;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        _containerList = arrayList;
        arrayList.add("meta");
        _containerList.add(BOX_IMAGE_PROPERTY);
        _containerList.add(BOX_ITEM_PROPERTY);
    }
}
