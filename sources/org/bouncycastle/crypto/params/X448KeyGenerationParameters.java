package org.bouncycastle.crypto.params;

import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

/* loaded from: classes4.dex */
public class X448KeyGenerationParameters extends KeyGenerationParameters {
    public X448KeyGenerationParameters(SecureRandom secureRandom) {
        super(secureRandom, CaptureActivity.RESULT_EXIT_USER_FLOW);
    }
}
