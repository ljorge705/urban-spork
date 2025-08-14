package com.uxcam.internals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes6.dex */
public final class dj implements eh {
    @Override // com.uxcam.internals.eh
    public final BufferedReader b() {
        return new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"logcat", "-d", "-v epoch"}).getInputStream()));
    }

    @Override // com.uxcam.internals.eh
    public final void a() throws IOException {
        try {
            Runtime.getRuntime().exec(new String[]{"logcat", "-c"});
        } catch (IOException unused) {
        }
    }
}
