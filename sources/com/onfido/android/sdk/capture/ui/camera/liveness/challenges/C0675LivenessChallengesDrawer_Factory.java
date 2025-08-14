package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import android.content.Context;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesDrawer_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0675LivenessChallengesDrawer_Factory {
    private final Provider<AnnouncementService> announcementServiceProvider;

    public C0675LivenessChallengesDrawer_Factory(Provider<AnnouncementService> provider) {
        this.announcementServiceProvider = provider;
    }

    public static C0675LivenessChallengesDrawer_Factory create(Provider<AnnouncementService> provider) {
        return new C0675LivenessChallengesDrawer_Factory(provider);
    }

    public static LivenessChallengesDrawer newInstance(Context context, AnnouncementService announcementService) {
        return new LivenessChallengesDrawer(context, announcementService);
    }

    public LivenessChallengesDrawer get(Context context) {
        return newInstance(context, this.announcementServiceProvider.get());
    }
}
