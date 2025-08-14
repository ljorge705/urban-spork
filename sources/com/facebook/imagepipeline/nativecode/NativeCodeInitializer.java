package com.facebook.imagepipeline.nativecode;

import android.content.Context;
import com.facebook.soloader.SoLoader;
import java.io.IOException;

/* loaded from: classes5.dex */
public class NativeCodeInitializer {
    public static void init(Context context) throws IOException {
        SoLoader.init(context, 0);
    }
}
