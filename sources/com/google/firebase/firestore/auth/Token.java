package com.google.firebase.firestore.auth;

/* loaded from: classes2.dex */
public final class Token {
    private final User user;
    private final String value;

    public User getUser() {
        return this.user;
    }

    public String getValue() {
        return this.value;
    }

    public Token(String str, User user) {
        this.value = str;
        this.user = user;
    }
}
