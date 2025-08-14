package org.junit.runner;

/* loaded from: classes4.dex */
public final class FilterFactoryParams {
    private final String args;
    private final Description topLevelDescription;

    public String getArgs() {
        return this.args;
    }

    public Description getTopLevelDescription() {
        return this.topLevelDescription;
    }

    public FilterFactoryParams(Description description, String str) {
        if (str == null || description == null) {
            throw null;
        }
        this.topLevelDescription = description;
        this.args = str;
    }
}
