package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Size;
import androidx.camera.core.impl.Quirk;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler;

/* loaded from: classes.dex */
public class ExtraSupportedOutputSizeQuirk implements Quirk {
    static boolean load() {
        return isMotoE5Play();
    }

    private static boolean isMotoE5Play() {
        return "motorola".equalsIgnoreCase(Build.BRAND) && "moto e5 play".equalsIgnoreCase(Build.MODEL);
    }

    public Size[] getExtraSupportedResolutions(int i) {
        return (i == 34 && isMotoE5Play()) ? getMotoE5PlayExtraSupportedResolutions() : new Size[0];
    }

    public <T> Size[] getExtraSupportedResolutions(Class<T> cls) {
        return (StreamConfigurationMap.isOutputSupportedFor(cls) && isMotoE5Play()) ? getMotoE5PlayExtraSupportedResolutions() : new Size[0];
    }

    private Size[] getMotoE5PlayExtraSupportedResolutions() {
        return new Size[]{new Size(1920, PhotoshopDirectory.TAG_COUNT_INFORMATION), new Size(1440, PhotoshopDirectory.TAG_COUNT_INFORMATION), new Size(1280, 720), new Size(960, 720), new Size(864, DefaultFrameSampler.DESIRED_FRAME_WIDTH), new Size(720, DefaultFrameSampler.DESIRED_FRAME_WIDTH)};
    }
}
