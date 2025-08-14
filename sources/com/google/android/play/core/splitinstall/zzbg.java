package com.google.android.play.core.splitinstall;

import android.util.Log;
import com.clevertap.android.sdk.Constants;
import io.sentry.protocol.Device;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzbg {
    static final zzk zza(XmlPullParser xmlPullParser, zzi zziVar) {
        while (xmlPullParser.next() != 1) {
            try {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("splits")) {
                        while (xmlPullParser.next() != 3) {
                            if (xmlPullParser.getEventType() == 2) {
                                if (xmlPullParser.getName().equals("module")) {
                                    String strZzb = zzb("name", xmlPullParser, zziVar);
                                    if (strZzb != null) {
                                        while (xmlPullParser.next() != 3) {
                                            if (xmlPullParser.getEventType() == 2) {
                                                if (xmlPullParser.getName().equals(Device.JsonKeys.LANGUAGE)) {
                                                    while (xmlPullParser.next() != 3) {
                                                        if (xmlPullParser.getEventType() == 2) {
                                                            if (xmlPullParser.getName().equals("entry")) {
                                                                String strZzb2 = zzb(Constants.KEY_KEY, xmlPullParser, zziVar);
                                                                String strZzb3 = zzb("split", xmlPullParser, zziVar);
                                                                zzc(xmlPullParser, zziVar);
                                                                if (strZzb2 != null && strZzb3 != null) {
                                                                    zziVar.zza(strZzb, strZzb2, strZzb3);
                                                                }
                                                            } else {
                                                                zzc(xmlPullParser, zziVar);
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    zzc(xmlPullParser, zziVar);
                                                }
                                            }
                                        }
                                    } else {
                                        zzc(xmlPullParser, zziVar);
                                    }
                                } else {
                                    zzc(xmlPullParser, zziVar);
                                }
                            }
                        }
                    } else {
                        zzc(xmlPullParser, zziVar);
                    }
                }
            } catch (IOException | IllegalStateException | XmlPullParserException e) {
                Log.e("SplitInstall", "Error while parsing splits.xml", e);
                return null;
            }
        }
        return zziVar.zzb();
    }

    private static final String zzb(String str, XmlPullParser xmlPullParser, zzi zziVar) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals(str)) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }

    private static final void zzc(XmlPullParser xmlPullParser, zzi zziVar) throws XmlPullParserException, IOException {
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }
}
