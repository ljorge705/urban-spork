package io.sentry;

import io.sentry.protocol.SentryId;

/* loaded from: classes6.dex */
public interface ReplayController {
    void captureReplay(Boolean bool);

    ReplayBreadcrumbConverter getBreadcrumbConverter();

    SentryId getReplayId();

    boolean isRecording();

    void pause();

    void resume();

    void setBreadcrumbConverter(ReplayBreadcrumbConverter replayBreadcrumbConverter);

    void start();

    void stop();
}
