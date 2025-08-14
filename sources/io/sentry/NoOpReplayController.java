package io.sentry;

import io.sentry.protocol.SentryId;

/* loaded from: classes6.dex */
public final class NoOpReplayController implements ReplayController {
    private static final NoOpReplayController instance = new NoOpReplayController();

    public static NoOpReplayController getInstance() {
        return instance;
    }

    @Override // io.sentry.ReplayController
    public void captureReplay(Boolean bool) {
    }

    @Override // io.sentry.ReplayController
    public boolean isRecording() {
        return false;
    }

    @Override // io.sentry.ReplayController
    public void pause() {
    }

    @Override // io.sentry.ReplayController
    public void resume() {
    }

    @Override // io.sentry.ReplayController
    public void setBreadcrumbConverter(ReplayBreadcrumbConverter replayBreadcrumbConverter) {
    }

    @Override // io.sentry.ReplayController
    public void start() {
    }

    @Override // io.sentry.ReplayController
    public void stop() {
    }

    private NoOpReplayController() {
    }

    @Override // io.sentry.ReplayController
    public SentryId getReplayId() {
        return SentryId.EMPTY_ID;
    }

    @Override // io.sentry.ReplayController
    public ReplayBreadcrumbConverter getBreadcrumbConverter() {
        return NoOpReplayBreadcrumbConverter.getInstance();
    }
}
