package com.onfido.api.client.exception;

/* loaded from: classes6.dex */
public class EnterpriseFeaturesNotAuthorizedException extends RuntimeException {
    public EnterpriseFeaturesNotAuthorizedException() {
        super("Enterprise features are not available on this account. Please talk to your Onfido Account Representative if you think you are receiving this message in error.");
    }
}
