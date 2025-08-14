package org.junit.rules;

import org.junit.runner.Description;

/* loaded from: classes4.dex */
public class TestName extends TestWatcher {
    private String name;

    public String getMethodName() {
        return this.name;
    }

    @Override // org.junit.rules.TestWatcher
    protected void starting(Description description) {
        this.name = description.getMethodName();
    }
}
