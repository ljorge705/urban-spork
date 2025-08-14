package com.uxcam.internals;

import android.content.Context;
import com.uxcam.screenaction.utils.FilePath;
import java.io.File;

/* loaded from: classes6.dex */
public final class da {

    /* renamed from: a, reason: collision with root package name */
    public final Context f116a;

    public da(Context context) {
        this.f116a = context;
    }

    public final void a() {
        try {
            File[] fileArrListFiles = new File(FilePath.getScreenVideoImageUrl(ga.b, Boolean.TRUE)).listFiles(new cz());
            File file = (fileArrListFiles == null || fileArrListFiles.length <= 0) ? null : fileArrListFiles[0];
            if (file != null) {
                new ag().b(this.f116a, file);
            }
        } catch (Exception unused) {
            gk.a("da").getClass();
        }
    }
}
