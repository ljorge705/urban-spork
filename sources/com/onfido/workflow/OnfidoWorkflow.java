package com.onfido.workflow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.workflow.internal.OnfidoWorkflowImpl;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnfidoWorkflow.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000 \u00112\u00020\u0001:\u0003\u0011\u0012\u0013J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\fH&J \u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0014"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow;", "", "createIntent", "Landroid/content/Intent;", "workflowConfig", "Lcom/onfido/workflow/WorkflowConfig;", "handleActivityResult", "", "resultCode", "", "intent", "resultListener", "Lcom/onfido/workflow/OnfidoWorkflow$ResultListener;", "startActivityForResult", "activity", "Landroid/app/Activity;", "requestCode", "Companion", "ResultListener", "WorkflowException", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface OnfidoWorkflow {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* compiled from: OnfidoWorkflow.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$ResultListener;", "", "onException", "", SentryEvent.JsonKeys.EXCEPTION, "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "onUserCompleted", "onUserExited", "exitCode", "Lcom/onfido/android/sdk/capture/ExitCode;", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ResultListener {
        void onException(WorkflowException exception);

        void onUserCompleted();

        void onUserExited(ExitCode exitCode);
    }

    @JvmStatic
    static OnfidoWorkflow create(Context context) {
        return INSTANCE.create(context);
    }

    Intent createIntent(WorkflowConfig workflowConfig);

    void handleActivityResult(int resultCode, Intent intent, ResultListener resultListener);

    void startActivityForResult(Activity activity, int requestCode, WorkflowConfig workflowConfig);

    /* compiled from: OnfidoWorkflow.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002:\f\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017B\u001b\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0001\f\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#¨\u0006$"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getMessage", "()Ljava/lang/String;", "WorkflowAbandonedException", "WorkflowBiometricTokenRetrievalException", "WorkflowBiometricTokenStorageException", "WorkflowCaptureCancelledException", "WorkflowHttpException", "WorkflowInsufficientVersionException", "WorkflowInvalidSSLCertificateException", "WorkflowTokenExpiredException", "WorkflowUnknownCameraException", "WorkflowUnknownException", "WorkflowUnknownResultException", "WorkflowUnsupportedTaskException", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowAbandonedException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowBiometricTokenRetrievalException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowBiometricTokenStorageException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowCaptureCancelledException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowHttpException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowInsufficientVersionException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowInvalidSSLCertificateException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowTokenExpiredException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowUnknownCameraException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowUnknownException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowUnknownResultException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowUnsupportedTaskException;", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class WorkflowException extends Exception {
        private final Throwable cause;
        private final String message;

        public /* synthetic */ WorkflowException(String str, Throwable th, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, th);
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        public /* synthetic */ WorkflowException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : th, null);
        }

        private WorkflowException(String str, Throwable th) {
            super(str, th);
            this.message = str;
            this.cause = th;
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowUnknownCameraException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowUnknownCameraException extends WorkflowException {
            private final String message;

            public static /* synthetic */ WorkflowUnknownCameraException copy$default(WorkflowUnknownCameraException workflowUnknownCameraException, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowUnknownCameraException.message;
                }
                return workflowUnknownCameraException.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final WorkflowUnknownCameraException copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowUnknownCameraException(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof WorkflowUnknownCameraException) && Intrinsics.areEqual(this.message, ((WorkflowUnknownCameraException) other).message);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowUnknownCameraException(message=" + this.message + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowUnknownCameraException(String message) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowInvalidSSLCertificateException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowInvalidSSLCertificateException extends WorkflowException {
            private final String message;

            public static /* synthetic */ WorkflowInvalidSSLCertificateException copy$default(WorkflowInvalidSSLCertificateException workflowInvalidSSLCertificateException, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowInvalidSSLCertificateException.message;
                }
                return workflowInvalidSSLCertificateException.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final WorkflowInvalidSSLCertificateException copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowInvalidSSLCertificateException(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof WorkflowInvalidSSLCertificateException) && Intrinsics.areEqual(this.message, ((WorkflowInvalidSSLCertificateException) other).message);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowInvalidSSLCertificateException(message=" + this.message + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowInvalidSSLCertificateException(String message) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowTokenExpiredException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowTokenExpiredException extends WorkflowException {
            private final String message;

            public static /* synthetic */ WorkflowTokenExpiredException copy$default(WorkflowTokenExpiredException workflowTokenExpiredException, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowTokenExpiredException.message;
                }
                return workflowTokenExpiredException.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final WorkflowTokenExpiredException copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowTokenExpiredException(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof WorkflowTokenExpiredException) && Intrinsics.areEqual(this.message, ((WorkflowTokenExpiredException) other).message);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowTokenExpiredException(message=" + this.message + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowTokenExpiredException(String message) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowUnknownResultException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowUnknownResultException extends WorkflowException {
            private final String message;

            public static /* synthetic */ WorkflowUnknownResultException copy$default(WorkflowUnknownResultException workflowUnknownResultException, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowUnknownResultException.message;
                }
                return workflowUnknownResultException.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final WorkflowUnknownResultException copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowUnknownResultException(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof WorkflowUnknownResultException) && Intrinsics.areEqual(this.message, ((WorkflowUnknownResultException) other).message);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowUnknownResultException(message=" + this.message + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowUnknownResultException(String message) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowInsufficientVersionException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowInsufficientVersionException extends WorkflowException {
            private final String message;

            public static /* synthetic */ WorkflowInsufficientVersionException copy$default(WorkflowInsufficientVersionException workflowInsufficientVersionException, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowInsufficientVersionException.message;
                }
                return workflowInsufficientVersionException.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final WorkflowInsufficientVersionException copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowInsufficientVersionException(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof WorkflowInsufficientVersionException) && Intrinsics.areEqual(this.message, ((WorkflowInsufficientVersionException) other).message);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowInsufficientVersionException(message=" + this.message + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowInsufficientVersionException(String message) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowUnsupportedTaskException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getMessage", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowUnsupportedTaskException extends WorkflowException {
            private final Throwable cause;
            private final String message;

            public static /* synthetic */ WorkflowUnsupportedTaskException copy$default(WorkflowUnsupportedTaskException workflowUnsupportedTaskException, String str, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowUnsupportedTaskException.message;
                }
                if ((i & 2) != 0) {
                    th = workflowUnsupportedTaskException.cause;
                }
                return workflowUnsupportedTaskException.copy(str, th);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* renamed from: component2, reason: from getter */
            public final Throwable getCause() {
                return this.cause;
            }

            public final WorkflowUnsupportedTaskException copy(String message, Throwable cause) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowUnsupportedTaskException(message, cause);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof WorkflowUnsupportedTaskException)) {
                    return false;
                }
                WorkflowUnsupportedTaskException workflowUnsupportedTaskException = (WorkflowUnsupportedTaskException) other;
                return Intrinsics.areEqual(this.message, workflowUnsupportedTaskException.message) && Intrinsics.areEqual(this.cause, workflowUnsupportedTaskException.cause);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public Throwable getCause() {
                return this.cause;
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                int iHashCode = this.message.hashCode() * 31;
                Throwable th = this.cause;
                return iHashCode + (th == null ? 0 : th.hashCode());
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowUnsupportedTaskException(message=" + this.message + ", cause=" + this.cause + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public WorkflowUnsupportedTaskException(String message, Throwable th) {
                super(message, th, null);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.cause = th;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowHttpException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getMessage", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowHttpException extends WorkflowException {
            private final Throwable cause;
            private final String message;

            public static /* synthetic */ WorkflowHttpException copy$default(WorkflowHttpException workflowHttpException, String str, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowHttpException.message;
                }
                if ((i & 2) != 0) {
                    th = workflowHttpException.cause;
                }
                return workflowHttpException.copy(str, th);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* renamed from: component2, reason: from getter */
            public final Throwable getCause() {
                return this.cause;
            }

            public final WorkflowHttpException copy(String message, Throwable cause) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowHttpException(message, cause);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof WorkflowHttpException)) {
                    return false;
                }
                WorkflowHttpException workflowHttpException = (WorkflowHttpException) other;
                return Intrinsics.areEqual(this.message, workflowHttpException.message) && Intrinsics.areEqual(this.cause, workflowHttpException.cause);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public Throwable getCause() {
                return this.cause;
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                int iHashCode = this.message.hashCode() * 31;
                Throwable th = this.cause;
                return iHashCode + (th == null ? 0 : th.hashCode());
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowHttpException(message=" + this.message + ", cause=" + this.cause + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public WorkflowHttpException(String message, Throwable th) {
                super(message, th, null);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.cause = th;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowCaptureCancelledException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowCaptureCancelledException extends WorkflowException {
            private final String message;

            public static /* synthetic */ WorkflowCaptureCancelledException copy$default(WorkflowCaptureCancelledException workflowCaptureCancelledException, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowCaptureCancelledException.message;
                }
                return workflowCaptureCancelledException.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final WorkflowCaptureCancelledException copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowCaptureCancelledException(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof WorkflowCaptureCancelledException) && Intrinsics.areEqual(this.message, ((WorkflowCaptureCancelledException) other).message);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowCaptureCancelledException(message=" + this.message + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowCaptureCancelledException(String message) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowUnknownException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getMessage", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowUnknownException extends WorkflowException {
            private final Throwable cause;
            private final String message;

            public static /* synthetic */ WorkflowUnknownException copy$default(WorkflowUnknownException workflowUnknownException, String str, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowUnknownException.message;
                }
                if ((i & 2) != 0) {
                    th = workflowUnknownException.cause;
                }
                return workflowUnknownException.copy(str, th);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* renamed from: component2, reason: from getter */
            public final Throwable getCause() {
                return this.cause;
            }

            public final WorkflowUnknownException copy(String message, Throwable cause) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowUnknownException(message, cause);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof WorkflowUnknownException)) {
                    return false;
                }
                WorkflowUnknownException workflowUnknownException = (WorkflowUnknownException) other;
                return Intrinsics.areEqual(this.message, workflowUnknownException.message) && Intrinsics.areEqual(this.cause, workflowUnknownException.cause);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public Throwable getCause() {
                return this.cause;
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                int iHashCode = this.message.hashCode() * 31;
                Throwable th = this.cause;
                return iHashCode + (th == null ? 0 : th.hashCode());
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowUnknownException(message=" + this.message + ", cause=" + this.cause + ")";
            }

            public /* synthetic */ WorkflowUnknownException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? null : th);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public WorkflowUnknownException(String message, Throwable th) {
                super(message, th, null);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.cause = th;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowBiometricTokenRetrievalException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getMessage", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowBiometricTokenRetrievalException extends WorkflowException {
            private final Throwable cause;
            private final String message;

            public static /* synthetic */ WorkflowBiometricTokenRetrievalException copy$default(WorkflowBiometricTokenRetrievalException workflowBiometricTokenRetrievalException, String str, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowBiometricTokenRetrievalException.message;
                }
                if ((i & 2) != 0) {
                    th = workflowBiometricTokenRetrievalException.cause;
                }
                return workflowBiometricTokenRetrievalException.copy(str, th);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* renamed from: component2, reason: from getter */
            public final Throwable getCause() {
                return this.cause;
            }

            public final WorkflowBiometricTokenRetrievalException copy(String message, Throwable cause) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowBiometricTokenRetrievalException(message, cause);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof WorkflowBiometricTokenRetrievalException)) {
                    return false;
                }
                WorkflowBiometricTokenRetrievalException workflowBiometricTokenRetrievalException = (WorkflowBiometricTokenRetrievalException) other;
                return Intrinsics.areEqual(this.message, workflowBiometricTokenRetrievalException.message) && Intrinsics.areEqual(this.cause, workflowBiometricTokenRetrievalException.cause);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public Throwable getCause() {
                return this.cause;
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                int iHashCode = this.message.hashCode() * 31;
                Throwable th = this.cause;
                return iHashCode + (th == null ? 0 : th.hashCode());
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowBiometricTokenRetrievalException(message=" + this.message + ", cause=" + this.cause + ")";
            }

            public /* synthetic */ WorkflowBiometricTokenRetrievalException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? null : th);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowBiometricTokenRetrievalException(String message, Throwable th) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.cause = th;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowBiometricTokenStorageException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getMessage", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowBiometricTokenStorageException extends WorkflowException {
            private final Throwable cause;
            private final String message;

            public static /* synthetic */ WorkflowBiometricTokenStorageException copy$default(WorkflowBiometricTokenStorageException workflowBiometricTokenStorageException, String str, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowBiometricTokenStorageException.message;
                }
                if ((i & 2) != 0) {
                    th = workflowBiometricTokenStorageException.cause;
                }
                return workflowBiometricTokenStorageException.copy(str, th);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* renamed from: component2, reason: from getter */
            public final Throwable getCause() {
                return this.cause;
            }

            public final WorkflowBiometricTokenStorageException copy(String message, Throwable cause) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowBiometricTokenStorageException(message, cause);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof WorkflowBiometricTokenStorageException)) {
                    return false;
                }
                WorkflowBiometricTokenStorageException workflowBiometricTokenStorageException = (WorkflowBiometricTokenStorageException) other;
                return Intrinsics.areEqual(this.message, workflowBiometricTokenStorageException.message) && Intrinsics.areEqual(this.cause, workflowBiometricTokenStorageException.cause);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public Throwable getCause() {
                return this.cause;
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                int iHashCode = this.message.hashCode() * 31;
                Throwable th = this.cause;
                return iHashCode + (th == null ? 0 : th.hashCode());
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowBiometricTokenStorageException(message=" + this.message + ", cause=" + this.cause + ")";
            }

            public /* synthetic */ WorkflowBiometricTokenStorageException(String str, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? null : th);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowBiometricTokenStorageException(String message, Throwable th) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.cause = th;
            }
        }

        /* compiled from: OnfidoWorkflow.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException$WorkflowAbandonedException;", "Lcom/onfido/workflow/OnfidoWorkflow$WorkflowException;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class WorkflowAbandonedException extends WorkflowException {
            private final String message;

            public static /* synthetic */ WorkflowAbandonedException copy$default(WorkflowAbandonedException workflowAbandonedException, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = workflowAbandonedException.message;
                }
                return workflowAbandonedException.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            public final WorkflowAbandonedException copy(String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new WorkflowAbandonedException(message);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof WorkflowAbandonedException) && Intrinsics.areEqual(this.message, ((WorkflowAbandonedException) other).message);
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.WorkflowException, java.lang.Throwable
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @Override // java.lang.Throwable
            public String toString() {
                return "WorkflowAbandonedException(message=" + this.message + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public WorkflowAbandonedException(String message) {
                super(message, null, 2, 0 == true ? 1 : 0);
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }
        }
    }

    /* compiled from: OnfidoWorkflow.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/OnfidoWorkflow$Companion;", "", "()V", "create", "Lcom/onfido/workflow/OnfidoWorkflow;", "context", "Landroid/content/Context;", "onfido-workflow-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @JvmStatic
        public final OnfidoWorkflow create(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return new OnfidoWorkflowImpl(applicationContext);
        }
    }
}
