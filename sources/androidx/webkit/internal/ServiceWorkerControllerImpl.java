package androidx.webkit.internal;

import android.webkit.ServiceWorkerController;
import androidx.webkit.ServiceWorkerClientCompat;
import androidx.webkit.ServiceWorkerControllerCompat;
import androidx.webkit.ServiceWorkerWebSettingsCompat;
import org.chromium.support_lib_boundary.ServiceWorkerControllerBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes5.dex */
public class ServiceWorkerControllerImpl extends ServiceWorkerControllerCompat {
    private ServiceWorkerControllerBoundaryInterface mBoundaryInterface;
    private ServiceWorkerController mFrameworksImpl;
    private final ServiceWorkerWebSettingsCompat mWebSettings;

    @Override // androidx.webkit.ServiceWorkerControllerCompat
    public ServiceWorkerWebSettingsCompat getServiceWorkerWebSettings() {
        return this.mWebSettings;
    }

    public ServiceWorkerControllerImpl() {
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.SERVICE_WORKER_BASIC_USAGE;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            this.mFrameworksImpl = ServiceWorkerController.getInstance();
            this.mBoundaryInterface = null;
            this.mWebSettings = new ServiceWorkerWebSettingsImpl(this.mFrameworksImpl.getServiceWorkerWebSettings());
        } else {
            if (webViewFeatureInternal.isSupportedByWebView()) {
                this.mFrameworksImpl = null;
                this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getServiceWorkerController();
                this.mWebSettings = new ServiceWorkerWebSettingsImpl(this.mBoundaryInterface.getServiceWorkerWebSettings());
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    private ServiceWorkerController getFrameworksImpl() {
        if (this.mFrameworksImpl == null) {
            this.mFrameworksImpl = ServiceWorkerController.getInstance();
        }
        return this.mFrameworksImpl;
    }

    private ServiceWorkerControllerBoundaryInterface getBoundaryInterface() {
        if (this.mBoundaryInterface == null) {
            this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getServiceWorkerController();
        }
        return this.mBoundaryInterface;
    }

    @Override // androidx.webkit.ServiceWorkerControllerCompat
    public void setServiceWorkerClient(ServiceWorkerClientCompat serviceWorkerClientCompat) {
        WebViewFeatureInternal webViewFeatureInternal = WebViewFeatureInternal.SERVICE_WORKER_BASIC_USAGE;
        if (webViewFeatureInternal.isSupportedByFramework()) {
            getFrameworksImpl().setServiceWorkerClient(new FrameworkServiceWorkerClient(serviceWorkerClientCompat));
        } else {
            if (webViewFeatureInternal.isSupportedByWebView()) {
                getBoundaryInterface().setServiceWorkerClient(BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new ServiceWorkerClientAdapter(serviceWorkerClientCompat)));
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }
}
