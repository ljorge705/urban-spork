package com.onfido.android.sdk.capture.internal.camera;

import android.content.Context;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import io.sentry.protocol.OperatingSystem;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/VideoFileBuilder;", "", "()V", "<set-?>", "", "prefix", "getPrefix", "()Ljava/lang/String;", OperatingSystem.JsonKeys.BUILD, "Ljava/io/File;", "context", "Landroid/content/Context;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VideoFileBuilder {
    private static final String VIDEO_FILE_DATE_FORMAT = "yyyy_MM_dd_HH_mm_ss_SSS";
    private static final String VIDEO_FILE_EXTENSION = "mp4";
    private String prefix;

    public final File build(Context context) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        File fileCreateTempFile = File.createTempFile(this.prefix + '-' + new SimpleDateFormat(VIDEO_FILE_DATE_FORMAT, Locale.UK).format(new Date()) + '-', LivenessConstants.VIDEO_RECORDING_FILE_FORMAT, context.getCacheDir());
        Intrinsics.checkNotNullExpressionValue(fileCreateTempFile, "createTempFile(...)");
        return fileCreateTempFile;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    public final VideoFileBuilder prefix(String prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        this.prefix = prefix;
        return this;
    }
}
