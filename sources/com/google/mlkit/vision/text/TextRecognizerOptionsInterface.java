package com.google.mlkit.vision.text;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public interface TextRecognizerOptionsInterface {
    public static final int LATIN = 1;
    public static final int LATIN_AND_CHINESE = 2;
    public static final int LATIN_AND_DEVANAGARI = 3;
    public static final int LATIN_AND_JAPANESE = 4;
    public static final int LATIN_AND_KOREAN = 5;

    /* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
    @Retention(RetentionPolicy.CLASS)
    public @interface LanguageOption {
    }

    String getCreatorClass();

    Executor getExecutor();

    boolean getIsThickClient();

    int getLoggingEventId();

    int getLoggingLanguageOption();

    String getLoggingLibraryName();

    String getModuleId();
}
