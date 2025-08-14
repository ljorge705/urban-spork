package com.uxcam.internals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes6.dex */
public final class di implements eh {
    @Override // com.uxcam.internals.eh
    public final BufferedReader b() {
        return new BufferedReader(new InputStreamReader(new ProcessBuilder("logcat", "-d", "-v", "epoch").start().getInputStream()));
    }

    @Override // com.uxcam.internals.eh
    public final void a() throws IOException {
        try {
            new ProcessBuilder("logcat", "-c").start();
        } catch (IOException unused) {
        }
    }
}
