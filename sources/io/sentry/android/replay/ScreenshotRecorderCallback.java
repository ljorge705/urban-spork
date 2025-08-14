package io.sentry.android.replay;

import android.graphics.Bitmap;
import java.io.File;
import kotlin.Metadata;

/* compiled from: ScreenshotRecorder.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lio/sentry/android/replay/ScreenshotRecorderCallback;", "", "onScreenshotRecorded", "", "bitmap", "Landroid/graphics/Bitmap;", "screenshot", "Ljava/io/File;", "frameTimestamp", "", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface ScreenshotRecorderCallback {
    void onScreenshotRecorded(Bitmap bitmap);

    void onScreenshotRecorded(File screenshot, long frameTimestamp);
}
