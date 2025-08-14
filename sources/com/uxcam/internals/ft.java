package com.uxcam.internals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ft {

    /* renamed from: a, reason: collision with root package name */
    public final hd f162a;
    public final File b;

    public ft(File file, hd hdVar) {
        this.b = new File(file, "metadata.json");
        this.f162a = hdVar;
    }

    public final File a() {
        return this.b;
    }

    public final void b() throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("decryptKey", this.f162a.c());
        jSONObject.put("decryptiv", this.f162a.b());
        FileOutputStream fileOutputStream = new FileOutputStream(this.b);
        fileOutputStream.write(jSONObject.toString().getBytes());
        fileOutputStream.close();
    }
}
