package com.uxcam.screenaction;

import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.uxcam.screenaction.models.ScreenAction;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/uxcam/screenaction/OnScreenActionResult;", "", OnfidoLauncher.KEY_RESULT, "", "screenAction", "Lcom/uxcam/screenaction/models/ScreenAction;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface OnScreenActionResult {
    void result(ScreenAction screenAction);
}
