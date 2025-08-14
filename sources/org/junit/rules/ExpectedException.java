package org.junit.rules;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Assert;
import org.junit.internal.matchers.ThrowableCauseMatcher;
import org.junit.internal.matchers.ThrowableMessageMatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* loaded from: classes4.dex */
public class ExpectedException implements TestRule {
    private final ExpectedExceptionMatcherBuilder matcherBuilder = new ExpectedExceptionMatcherBuilder();
    private String missingExceptionMessage = "Expected test to throw %s";

    @Deprecated
    public ExpectedException handleAssertionErrors() {
        return this;
    }

    @Deprecated
    public ExpectedException handleAssumptionViolatedExceptions() {
        return this;
    }

    public ExpectedException reportMissingExceptionWithMessage(String str) {
        this.missingExceptionMessage = str;
        return this;
    }

    public static ExpectedException none() {
        return new ExpectedException();
    }

    private ExpectedException() {
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(Statement statement, Description description) {
        return new ExpectedExceptionStatement(statement);
    }

    public void expect(Matcher<?> matcher) {
        this.matcherBuilder.add(matcher);
    }

    public void expect(Class<? extends Throwable> cls) {
        expect(CoreMatchers.instanceOf(cls));
    }

    public void expectMessage(String str) {
        expectMessage(CoreMatchers.containsString(str));
    }

    public void expectMessage(Matcher<String> matcher) {
        expect(ThrowableMessageMatcher.hasMessage(matcher));
    }

    public void expectCause(Matcher<? extends Throwable> matcher) {
        expect(ThrowableCauseMatcher.hasCause(matcher));
    }

    private class ExpectedExceptionStatement extends Statement {
        private final Statement next;

        public ExpectedExceptionStatement(Statement statement) {
            this.next = statement;
        }

        @Override // org.junit.runners.model.Statement
        public void evaluate() throws Throwable {
            try {
                this.next.evaluate();
                if (ExpectedException.this.isAnyExceptionExpected()) {
                    ExpectedException.this.failDueToMissingException();
                }
            } catch (Throwable th) {
                ExpectedException.this.handleException(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleException(Throwable th) throws Throwable {
        if (isAnyExceptionExpected()) {
            Assert.assertThat(th, this.matcherBuilder.build());
            return;
        }
        throw th;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAnyExceptionExpected() {
        return this.matcherBuilder.expectsThrowable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void failDueToMissingException() throws AssertionError {
        Assert.fail(missingExceptionMessage());
    }

    private String missingExceptionMessage() {
        return String.format(this.missingExceptionMessage, StringDescription.toString(this.matcherBuilder.build()));
    }
}
