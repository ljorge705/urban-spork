package com.uxcam.internals;

import com.uxcam.internals.ff;
import com.uxcam.screenshot.di.ScreenshotModule;
import java.util.HashMap;
import java.util.Timer;

/* loaded from: classes6.dex */
public final class fg implements bv {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ff f149a;

    public fg(ff ffVar) {
        this.f149a = ffVar;
    }

    @Override // com.uxcam.internals.bv
    public final void a() {
        Timer timer = ff.d;
        gk.a("ff").getClass();
        ff ffVar = this.f149a;
        ff.ab abVar = ffVar.c;
        if (abVar != null) {
            abVar.a(ffVar.b);
            ffVar.c = null;
        }
        ff ffVar2 = this.f149a;
        ffVar2.getClass();
        ScreenshotModule.getInstance().getScreenshotStateHolder().resetImageCount();
        ff.g = false;
        ff.k = null;
        ff.h = null;
        dd<ew> ddVar = ff.f;
        if (ddVar != null) {
            ddVar.clear();
            ff.f = null;
        }
        ScreenshotModule.getInstance().getScreenshotStateHolder().resetImageCount();
        if (!ga.K) {
            ffVar2.g();
        }
        HashMap map = new HashMap();
        String strReplace = "[ #event# ]".replace("#event#", "ScreenVideoHandler::finishEncodingAndSendHttp() -> encoderSucceeded()");
        map.put("reason", "Encoding complete with media codec.");
        map.put("invokes_next", "encodingComplete() && finishEncodingAndSendHttpForKitkat()");
        ht.b(strReplace, map);
    }

    @Override // com.uxcam.internals.bv
    public final void b() {
        Timer timer = ff.d;
        gk.a("ff").getClass();
        this.f149a.getClass();
        ff.e();
        fj fjVarB = new fj().b("ScreenVideoHandler::initializeMediaCodec() -> encoderFailed()");
        fjVarB.a("reason", "exception was not thrown ...");
        fjVarB.a("invokes_next", "initializeJCodec()");
        fjVarB.a("reason1", "Encoding failed with media codec.").a(2);
    }
}
