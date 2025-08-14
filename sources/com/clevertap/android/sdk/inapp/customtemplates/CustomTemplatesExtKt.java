package com.clevertap.android.sdk.inapp.customtemplates;

import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate;
import io.sentry.protocol.SentryStackFrame;
import java.util.Arrays;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomTemplatesExt.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b\u001a\u001f\u0010\t\u001a\u00020\u00012\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b\u001a%\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\u0012\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000e\"\u00020\u0001¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {SentryStackFrame.JsonKeys.FUNCTION, "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "isVisual", "", "buildBlock", "Lkotlin/Function1;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$FunctionBuilder;", "", "Lkotlin/ExtensionFunctionType;", "template", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate$TemplateBuilder;", "templatesSet", "", "templates", "", "([Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;)Ljava/util/Set;", "clevertap-core_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CustomTemplatesExtKt {
    public static final Set<CustomTemplate> templatesSet(CustomTemplate... templates) {
        Intrinsics.checkNotNullParameter(templates, "templates");
        return SetsKt.setOf(Arrays.copyOf(templates, templates.length));
    }

    public static final CustomTemplate template(Function1<? super CustomTemplate.TemplateBuilder, Unit> buildBlock) {
        Intrinsics.checkNotNullParameter(buildBlock, "buildBlock");
        CustomTemplate.TemplateBuilder templateBuilder = new CustomTemplate.TemplateBuilder();
        buildBlock.invoke(templateBuilder);
        return templateBuilder.build();
    }

    public static final CustomTemplate function(boolean z, Function1<? super CustomTemplate.FunctionBuilder, Unit> buildBlock) {
        Intrinsics.checkNotNullParameter(buildBlock, "buildBlock");
        CustomTemplate.FunctionBuilder functionBuilder = new CustomTemplate.FunctionBuilder(z);
        buildBlock.invoke(functionBuilder);
        return functionBuilder.build();
    }
}
