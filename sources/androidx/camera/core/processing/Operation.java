package androidx.camera.core.processing;

import androidx.camera.core.ImageCaptureException;

/* loaded from: classes.dex */
public interface Operation<I, O> {
    O apply(I i) throws ImageCaptureException;
}
