package com.onfido.api.client;

import com.onfido.api.client.data.LiveVideoChallenges;
import io.reactivex.rxjava3.core.Single;

/* loaded from: classes6.dex */
public class LiveVideoChallengeAPI {
    private final OnfidoService onfidoService;

    LiveVideoChallengeAPI(OnfidoService onfidoService) {
        this.onfidoService = onfidoService;
    }

    Single<LiveVideoChallenges> get() {
        return this.onfidoService.getLiveVideoChallenges();
    }
}
