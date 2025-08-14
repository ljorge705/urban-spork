package com.onfido.api.client.exception;

/* loaded from: classes6.dex */
public class InternalEnterpriseFeaturesNotAuthorizedException extends RuntimeException {
    public InternalEnterpriseFeaturesNotAuthorizedException() {
        super("Enterprise features are not available on this account. Please talk to your Onfido Account Representative if you think you are receiving this message in error.");
    }
}
