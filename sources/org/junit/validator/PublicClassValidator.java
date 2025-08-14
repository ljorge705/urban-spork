package org.junit.validator;

import java.util.Collections;
import java.util.List;
import org.junit.runners.model.TestClass;

/* loaded from: classes4.dex */
public class PublicClassValidator implements TestClassValidator {
    private static final List<Exception> NO_VALIDATION_ERRORS = Collections.emptyList();

    @Override // org.junit.validator.TestClassValidator
    public List<Exception> validateTestClass(TestClass testClass) {
        return testClass.isPublic() ? NO_VALIDATION_ERRORS : Collections.singletonList(new Exception("The class " + testClass.getName() + " is not public."));
    }
}
