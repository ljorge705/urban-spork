package com.clevertap.android.sdk.product_config;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Log;
import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
class DefaultXmlParser {
    private static final String XML_TAG_ENTRY = "entry";
    private static final String XML_TAG_KEY = "key";
    private static final int XML_TAG_TYPE_KEY = 0;
    private static final int XML_TAG_TYPE_VALUE = 1;
    private static final String XML_TAG_VALUE = "value";

    DefaultXmlParser() {
    }

    HashMap<String, String> getDefaultsFromXml(Context context, int i) {
        HashMap<String, String> map = new HashMap<>();
        getDefaultsFromXml(context.getResources(), i, map);
        return map;
    }

    void getDefaultsFromXml(Resources resources, int i, HashMap<String, String> map) {
        if (resources == null) {
            Log.e("ProductConfig", "Could not find the resources of the current context while trying to set defaults from an XML.");
            return;
        }
        try {
            getDefaultsFromXmlParser(resources.getXml(i), map);
        } catch (Exception e) {
            Log.e("ProductConfig", "Encountered an error while parsing the defaults XML file.", e);
        }
    }

    void getDefaultsFromXmlParser(XmlResourceParser xmlResourceParser, HashMap<String, String> map) throws XmlPullParserException, IOException {
        int eventType = xmlResourceParser.getEventType();
        String text = null;
        String name = null;
        String text2 = null;
        while (eventType != 1) {
            if (eventType == 2) {
                name = xmlResourceParser.getName();
            } else if (eventType == 3) {
                if (xmlResourceParser.getName().equals(XML_TAG_ENTRY)) {
                    if (text != null && text2 != null) {
                        map.put(text, text2);
                    } else {
                        Log.w(Constants.LOG_TAG_PRODUCT_CONFIG, "An entry in the defaults XML has an invalid key and/or value tag.");
                    }
                    text = null;
                    text2 = null;
                }
                name = null;
            } else if (eventType == 4 && name != null) {
                name.hashCode();
                char c = !name.equals("key") ? !name.equals("value") ? (char) 65535 : (char) 1 : (char) 0;
                if (c == 0) {
                    text = xmlResourceParser.getText();
                } else if (c == 1) {
                    text2 = xmlResourceParser.getText();
                } else {
                    Log.w(Constants.LOG_TAG_PRODUCT_CONFIG, "Encountered an unexpected tag while parsing the defaults XML.");
                }
            }
            eventType = xmlResourceParser.next();
        }
    }
}
