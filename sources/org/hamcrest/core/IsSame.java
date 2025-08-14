package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/* loaded from: classes4.dex */
public class IsSame<T> extends BaseMatcher<T> {
    private final T object;

    @Override // org.hamcrest.Matcher
    public boolean matches(Object obj) {
        return obj == this.object;
    }

    public IsSame(T t) {
        this.object = t;
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("sameInstance(").appendValue(this.object).appendText(")");
    }

    @Factory
    public static <T> Matcher<T> sameInstance(T t) {
        return new IsSame(t);
    }

    @Factory
    public static <T> Matcher<T> theInstance(T t) {
        return new IsSame(t);
    }
}
