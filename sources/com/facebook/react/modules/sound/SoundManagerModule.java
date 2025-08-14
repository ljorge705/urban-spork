package com.facebook.react.modules.sound;

import android.media.AudioManager;
import androidx.media3.common.MimeTypes;
import com.facebook.fbreact.specs.NativeSoundManagerSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = NativeSoundManagerSpec.NAME)
/* loaded from: classes5.dex */
public class SoundManagerModule extends NativeSoundManagerSpec {
    public SoundManagerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.fbreact.specs.NativeSoundManagerSpec
    public void playTouchSound() {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager != null) {
            audioManager.playSoundEffect(0);
        }
    }
}
