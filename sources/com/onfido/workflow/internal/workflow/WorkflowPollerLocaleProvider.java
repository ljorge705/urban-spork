package com.onfido.workflow.internal.workflow;

import android.content.Context;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.onfido.javax.inject.Inject;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkflowPollerLocaleProvider.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/workflow/WorkflowPollerLocaleProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getWorkflowLocale", "Ljava/util/Locale;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WorkflowPollerLocaleProvider {
    private final Context context;

    @Inject
    public WorkflowPollerLocaleProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Locale getWorkflowLocale() {
        LocaleListCompat locales = ConfigurationCompat.getLocales(this.context.getResources().getConfiguration());
        Intrinsics.checkNotNullExpressionValue(locales, "getLocales(...)");
        if (locales.size() > 0) {
            Locale locale = locales.get(0);
            if (locale == null) {
                locale = Locale.ENGLISH;
            }
            Intrinsics.checkNotNull(locale);
            return locale;
        }
        Locale locale2 = Locale.ENGLISH;
        Intrinsics.checkNotNull(locale2);
        return locale2;
    }
}
