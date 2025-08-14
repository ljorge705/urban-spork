package com.google.android.play.core.splitinstall.testing;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzaa {
    private static final com.google.android.play.core.splitinstall.internal.zzu zza = new com.google.android.play.core.splitinstall.internal.zzu("LocalTestingConfigParser");
    private final XmlPullParser zzb;
    private final zzu zzc = zzv.zzc();

    zzaa(XmlPullParser xmlPullParser) {
        this.zzb = xmlPullParser;
    }

    public static zzv zza(File file) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        File file2 = new File(file, "local_testing_config.xml");
        if (!file2.exists()) {
            return zzv.zza;
        }
        try {
            FileReader fileReader = new FileReader(file2);
            try {
                XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
                xmlPullParserNewPullParser.setInput(fileReader);
                final zzaa zzaaVar = new zzaa(xmlPullParserNewPullParser);
                zzaaVar.zze("local-testing-config", new zzz() { // from class: com.google.android.play.core.splitinstall.testing.zzy
                    @Override // com.google.android.play.core.splitinstall.testing.zzz
                    public final void zza() throws XmlPullParserException, IOException {
                        this.zza.zzd();
                    }
                });
                zzv zzvVarZze = zzaaVar.zzc.zze();
                fileReader.close();
                return zzvVarZze;
            } catch (Throwable th) {
                try {
                    fileReader.close();
                } catch (Throwable th2) {
                    try {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                    } catch (Exception unused) {
                    }
                }
                throw th;
            }
        } catch (IOException | RuntimeException | XmlPullParserException e) {
            zza.zze("%s can not be parsed, using default. Error: %s", "local_testing_config.xml", e.getMessage());
            return zzv.zza;
        }
    }

    public static /* synthetic */ void zzb(final zzaa zzaaVar) throws XmlPullParserException, IOException {
        for (int i = 0; i < zzaaVar.zzb.getAttributeCount(); i++) {
            if ("defaultErrorCode".equals(zzaaVar.zzb.getAttributeName(i))) {
                zzaaVar.zzc.zza(com.google.android.play.core.splitinstall.model.zza.zza(zzaaVar.zzb.getAttributeValue(i)));
            }
        }
        zzaaVar.zze("split-install-error", new zzz() { // from class: com.google.android.play.core.splitinstall.testing.zzw
            @Override // com.google.android.play.core.splitinstall.testing.zzz
            public final void zza() throws XmlPullParserException {
                zzaa.zzc(this.zza);
            }
        });
    }

    public static /* synthetic */ void zzc(zzaa zzaaVar) throws XmlPullParserException {
        String attributeValue = null;
        String attributeValue2 = null;
        for (int i = 0; i < zzaaVar.zzb.getAttributeCount(); i++) {
            if ("module".equals(zzaaVar.zzb.getAttributeName(i))) {
                attributeValue = zzaaVar.zzb.getAttributeValue(i);
            }
            if ("errorCode".equals(zzaaVar.zzb.getAttributeName(i))) {
                attributeValue2 = zzaaVar.zzb.getAttributeValue(i);
            }
        }
        if (attributeValue == null || attributeValue2 == null) {
            throw new XmlPullParserException(String.format("'%s' element does not contain 'module'/'errorCode' attributes.", "split-install-error"), zzaaVar.zzb, null);
        }
        zzaaVar.zzc.zzd().put(attributeValue, Integer.valueOf(com.google.android.play.core.splitinstall.model.zza.zza(attributeValue2)));
        while (zzaaVar.zzb.next() != 3) {
        }
    }

    private final void zze(String str, zzz zzzVar) throws XmlPullParserException, IOException {
        while (true) {
            int next = this.zzb.next();
            if (next == 3 || next == 1) {
                return;
            }
            if (this.zzb.getEventType() == 2) {
                if (!this.zzb.getName().equals(str)) {
                    throw new XmlPullParserException(String.format("Expected '%s' tag but found '%s'.", str, this.zzb.getName()), this.zzb, null);
                }
                zzzVar.zza();
            }
        }
    }

    final /* synthetic */ void zzd() throws XmlPullParserException, IOException {
        zze("split-install-errors", new zzz() { // from class: com.google.android.play.core.splitinstall.testing.zzx
            @Override // com.google.android.play.core.splitinstall.testing.zzz
            public final void zza() throws XmlPullParserException, IOException {
                zzaa.zzb(this.zza);
            }
        });
    }
}
