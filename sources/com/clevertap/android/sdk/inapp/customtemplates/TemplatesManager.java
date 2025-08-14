package com.clevertap.android.sdk.inapp.customtemplates;

import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.inapp.CTInAppNotification;
import com.clevertap.android.sdk.inapp.InAppListener;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateContext;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: TemplatesManager.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\"\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u00020\nJ\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0018\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\nJ\u0010\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000bH\u0016J\u001e\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00040\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatesManager;", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext$ContextDismissListener;", "templates", "", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "logger", "Lcom/clevertap/android/sdk/Logger;", "(Ljava/util/Collection;Lcom/clevertap/android/sdk/Logger;)V", "activeContexts", "", "", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplateContext;", "customTemplates", "", "closeTemplate", "", "notification", "Lcom/clevertap/android/sdk/inapp/CTInAppNotification;", "createContextFromInApp", "inAppListener", "Lcom/clevertap/android/sdk/inapp/InAppListener;", "resourceProvider", "Lcom/clevertap/android/sdk/inapp/images/FileResourceProvider;", "getActiveContextForTemplate", "templateName", "getAllRegisteredTemplates", "getTemplate", "isTemplateRegistered", "", "onDismissContext", "context", "presentTemplate", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class TemplatesManager implements CustomTemplateContext.ContextDismissListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<TemplateProducer> templateProducers = new ArrayList();
    private final Map<String, CustomTemplateContext> activeContexts;
    private final Map<String, CustomTemplate> customTemplates;
    private final Logger logger;

    @JvmStatic
    public static final TemplatesManager createInstance(CleverTapInstanceConfig cleverTapInstanceConfig) {
        return INSTANCE.createInstance(cleverTapInstanceConfig);
    }

    @JvmStatic
    public static final void register(TemplateProducer templateProducer) {
        INSTANCE.register(templateProducer);
    }

    public TemplatesManager(Collection<CustomTemplate> templates, Logger logger) {
        Intrinsics.checkNotNullParameter(templates, "templates");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        Collection<CustomTemplate> collection = templates;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection, 10)), 16));
        for (Object obj : collection) {
            linkedHashMap.put(((CustomTemplate) obj).getName(), obj);
        }
        this.customTemplates = linkedHashMap;
        this.activeContexts = new LinkedHashMap();
    }

    /* compiled from: TemplatesManager.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0005H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatesManager$Companion;", "", "()V", "templateProducers", "", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplateProducer;", "clearRegisteredProducers", "", "createInstance", "Lcom/clevertap/android/sdk/inapp/customtemplates/TemplatesManager;", "ctInstanceConfig", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "register", "templateProducer", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void register(TemplateProducer templateProducer) {
            Intrinsics.checkNotNullParameter(templateProducer, "templateProducer");
            TemplatesManager.templateProducers.add(templateProducer);
        }

        @JvmStatic
        public final TemplatesManager createInstance(CleverTapInstanceConfig ctInstanceConfig) {
            Intrinsics.checkNotNullParameter(ctInstanceConfig, "ctInstanceConfig");
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = TemplatesManager.templateProducers.iterator();
            while (it.hasNext()) {
                for (CustomTemplate customTemplate : ((TemplateProducer) it.next()).defineTemplates(ctInstanceConfig)) {
                    if (linkedHashSet.contains(customTemplate)) {
                        throw new CustomTemplateException("CustomTemplate with a name \"" + customTemplate.getName() + "\" is already registered", null, 2, null);
                    }
                    linkedHashSet.add(customTemplate);
                }
            }
            Logger logger = ctInstanceConfig.getLogger();
            Intrinsics.checkNotNullExpressionValue(logger, "ctInstanceConfig.logger");
            return new TemplatesManager(linkedHashSet, logger);
        }

        public final void clearRegisteredProducers() {
            TemplatesManager.templateProducers.clear();
        }
    }

    public final boolean isTemplateRegistered(String templateName) {
        Intrinsics.checkNotNullParameter(templateName, "templateName");
        return this.customTemplates.containsKey(templateName);
    }

    public final Collection<CustomTemplate> getAllRegisteredTemplates() {
        return this.customTemplates.values();
    }

    public final CustomTemplate getTemplate(String templateName) {
        Intrinsics.checkNotNullParameter(templateName, "templateName");
        return this.customTemplates.get(templateName);
    }

    public final CustomTemplateContext getActiveContextForTemplate(String templateName) {
        Intrinsics.checkNotNullParameter(templateName, "templateName");
        return this.activeContexts.get(templateName);
    }

    public final void presentTemplate(CTInAppNotification notification, InAppListener inAppListener, FileResourceProvider resourceProvider) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        Intrinsics.checkNotNullParameter(inAppListener, "inAppListener");
        Intrinsics.checkNotNullParameter(resourceProvider, "resourceProvider");
        CustomTemplateContext customTemplateContextCreateContextFromInApp = createContextFromInApp(notification, inAppListener, resourceProvider);
        if (customTemplateContextCreateContextFromInApp == null) {
            return;
        }
        CustomTemplate customTemplate = this.customTemplates.get(customTemplateContextCreateContextFromInApp.getTemplateName());
        if (customTemplate == null) {
            this.logger.info("CustomTemplates", "Cannot find template with name " + customTemplateContextCreateContextFromInApp.getTemplateName());
            return;
        }
        CustomTemplatePresenter<?> presenter = customTemplate.getPresenter();
        if (presenter instanceof TemplatePresenter) {
            if (customTemplateContextCreateContextFromInApp instanceof CustomTemplateContext.TemplateContext) {
                this.activeContexts.put(customTemplate.getName(), customTemplateContextCreateContextFromInApp);
                ((TemplatePresenter) presenter).onPresent(customTemplateContextCreateContextFromInApp);
                return;
            }
            return;
        }
        if ((presenter instanceof FunctionPresenter) && (customTemplateContextCreateContextFromInApp instanceof CustomTemplateContext.FunctionContext)) {
            this.activeContexts.put(customTemplate.getName(), customTemplateContextCreateContextFromInApp);
            ((FunctionPresenter) presenter).onPresent(customTemplateContextCreateContextFromInApp);
        }
    }

    public final void closeTemplate(CTInAppNotification notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        CustomTemplateInAppData customTemplateData = notification.getCustomTemplateData();
        String templateName = customTemplateData != null ? customTemplateData.getTemplateName() : null;
        if (templateName == null) {
            this.logger.debug("CustomTemplates", "Cannot close custom template from notification without template name");
            return;
        }
        CustomTemplateContext customTemplateContext = this.activeContexts.get(templateName);
        if (customTemplateContext == null) {
            this.logger.debug("CustomTemplates", "Cannot close custom template without active context");
            return;
        }
        CustomTemplate customTemplate = this.customTemplates.get(templateName);
        if (customTemplate == null) {
            this.logger.info("CustomTemplates", "Cannot find template with name " + templateName);
            return;
        }
        CustomTemplatePresenter<?> presenter = customTemplate.getPresenter();
        if ((presenter instanceof TemplatePresenter) && (customTemplateContext instanceof CustomTemplateContext.TemplateContext)) {
            ((TemplatePresenter) presenter).onClose((CustomTemplateContext.TemplateContext) customTemplateContext);
        }
    }

    @Override // com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateContext.ContextDismissListener
    public void onDismissContext(CustomTemplateContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.activeContexts.remove(context.getTemplateName());
    }

    private final CustomTemplateContext createContextFromInApp(CTInAppNotification notification, InAppListener inAppListener, FileResourceProvider resourceProvider) {
        CustomTemplateInAppData customTemplateData = notification.getCustomTemplateData();
        String templateName = customTemplateData != null ? customTemplateData.getTemplateName() : null;
        if (templateName == null) {
            this.logger.debug("CustomTemplates", "Cannot create TemplateContext from notification without template name");
            return null;
        }
        CustomTemplate customTemplate = this.customTemplates.get(templateName);
        if (customTemplate == null) {
            this.logger.debug("CustomTemplates", "Cannot create TemplateContext for non-registered template: " + templateName);
            return null;
        }
        return CustomTemplateContext.INSTANCE.createContext$clevertap_core_release(customTemplate, notification, inAppListener, resourceProvider, this, this.logger);
    }
}
