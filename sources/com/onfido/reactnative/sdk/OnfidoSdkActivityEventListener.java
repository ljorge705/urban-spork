package com.onfido.reactnative.sdk;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.reactnative.sdk.Response;
import com.onfido.workflow.OnfidoWorkflow;

/* loaded from: classes6.dex */
class OnfidoSdkActivityEventListener extends BaseActivityEventListener {
    static final int checksActivityCode = 102040;
    static final int workflowActivityCode = 102030;
    final Onfido client;
    private Promise currentPromise = null;

    public void setCurrentPromise(Promise promise) {
        this.currentPromise = promise;
    }

    public OnfidoSdkActivityEventListener(Onfido onfido) {
        this.client = onfido;
    }

    @Override // com.facebook.react.bridge.BaseActivityEventListener, com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        super.onActivityResult(activity, i, i2, intent);
        if (i == workflowActivityCode) {
            handleOnfidoWorkflow(OnfidoWorkflow.create(activity), i2, intent);
        }
        if (i == checksActivityCode) {
            handleOnfidoChecks(i2, intent);
        }
    }

    private void handleOnfidoWorkflow(OnfidoWorkflow onfidoWorkflow, int i, Intent intent) {
        onfidoWorkflow.handleActivityResult(i, intent, new OnfidoWorkflow.ResultListener() { // from class: com.onfido.reactnative.sdk.OnfidoSdkActivityEventListener.1
            @Override // com.onfido.workflow.OnfidoWorkflow.ResultListener
            public void onUserCompleted() {
                if (OnfidoSdkActivityEventListener.this.currentPromise != null) {
                    OnfidoSdkActivityEventListener.this.currentPromise.resolve(Arguments.createMap());
                }
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.ResultListener
            public void onUserExited(ExitCode exitCode) {
                if (OnfidoSdkActivityEventListener.this.currentPromise != null) {
                    OnfidoSdkActivityEventListener.this.currentPromise.reject(exitCode.toString(), new Exception("User exited by manual action."));
                    OnfidoSdkActivityEventListener.this.currentPromise = null;
                }
            }

            @Override // com.onfido.workflow.OnfidoWorkflow.ResultListener
            public void onException(OnfidoWorkflow.WorkflowException workflowException) {
                if (OnfidoSdkActivityEventListener.this.currentPromise != null) {
                    OnfidoSdkActivityEventListener.this.currentPromise.reject("error", workflowException);
                    OnfidoSdkActivityEventListener.this.currentPromise = null;
                }
            }
        });
    }

    private void handleOnfidoChecks(int i, Intent intent) {
        this.client.handleActivityResult(i, intent, new Onfido.OnfidoResultListener() { // from class: com.onfido.reactnative.sdk.OnfidoSdkActivityEventListener.2
            @Override // com.onfido.android.sdk.capture.Onfido.OnfidoResultListener
            public void userCompleted(Captures captures) {
                String str;
                String str2;
                String nfcMediaUUID;
                String str3;
                String string;
                Response.ProofOfAddress proofOfAddress;
                if (OnfidoSdkActivityEventListener.this.currentPromise != null) {
                    if (captures.getDocument() != null) {
                        String id = captures.getDocument().getFront() != null ? captures.getDocument().getFront().getId() : null;
                        String id2 = captures.getDocument().getBack() != null ? captures.getDocument().getBack().getId() : null;
                        if (captures.getDocument().getNfcMediaUUID() != null) {
                            str = id;
                            str2 = id2;
                            nfcMediaUUID = captures.getDocument().getNfcMediaUUID();
                        } else {
                            str = id;
                            nfcMediaUUID = null;
                            str2 = id2;
                        }
                    } else {
                        str = null;
                        str2 = null;
                        nfcMediaUUID = null;
                    }
                    if (captures.getFace() != null) {
                        String id3 = captures.getFace().getId();
                        captures.getFace().getVariant();
                        str3 = id3;
                        string = captures.getFace().getVariant().toString();
                    } else {
                        str3 = null;
                        string = null;
                    }
                    if (captures.getPoa() != null) {
                        proofOfAddress = new Response.ProofOfAddress(captures.getPoa().getType(), new Response.ProofOfAddress.ProofOfAddressSide(captures.getPoa().getFront().getId(), captures.getPoa().getFront().getType()), captures.getPoa().getBack() != null ? new Response.ProofOfAddress.ProofOfAddressSide(captures.getPoa().getBack().getId(), captures.getPoa().getBack().getType()) : null);
                    } else {
                        proofOfAddress = null;
                    }
                    try {
                        OnfidoSdkActivityEventListener.this.currentPromise.resolve(ReactNativeBridgeUtiles.convertPublicFieldsToWritableMap(new Response(str, str2, str3, string, nfcMediaUUID, proofOfAddress)));
                        OnfidoSdkActivityEventListener.this.currentPromise = null;
                    } catch (Exception e) {
                        OnfidoSdkActivityEventListener.this.currentPromise.reject("error", "Error serializing response", e);
                        OnfidoSdkActivityEventListener.this.currentPromise = null;
                    }
                }
            }

            @Override // com.onfido.android.sdk.capture.Onfido.OnfidoResultListener
            public void userExited(ExitCode exitCode) {
                if (OnfidoSdkActivityEventListener.this.currentPromise != null) {
                    OnfidoSdkActivityEventListener.this.currentPromise.reject(exitCode.toString(), new Exception("User exited by manual action."));
                    OnfidoSdkActivityEventListener.this.currentPromise = null;
                }
            }

            @Override // com.onfido.android.sdk.capture.Onfido.OnfidoResultListener
            public void onError(OnfidoException onfidoException) {
                if (OnfidoSdkActivityEventListener.this.currentPromise != null) {
                    OnfidoSdkActivityEventListener.this.currentPromise.reject("error", onfidoException);
                    OnfidoSdkActivityEventListener.this.currentPromise = null;
                }
            }
        });
    }
}
