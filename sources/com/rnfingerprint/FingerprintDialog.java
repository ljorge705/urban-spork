package com.rnfingerprint;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.react.bridge.ReadableMap;
import com.rnfingerprint.FingerprintHandler;

/* loaded from: classes6.dex */
public class FingerprintDialog extends DialogFragment implements FingerprintHandler.Callback {
    private String authReason;
    private DialogResultListener dialogCallback;
    private boolean isAuthInProgress;
    private FingerprintManager.CryptoObject mCryptoObject;
    private TextView mFingerprintError;
    private FingerprintHandler mFingerprintHandler;
    private ImageView mFingerprintImage;
    private TextView mFingerprintSensorDescription;
    private int imageColor = 0;
    private int imageErrorColor = 0;
    private String dialogTitle = "";
    private String cancelText = "";
    private String sensorDescription = "";
    private String sensorErrorDescription = "";
    private String errorText = "";

    public interface DialogResultListener {
        void onAuthenticated();

        void onCancelled();

        void onError(String str, int i);
    }

    public void setCryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        this.mCryptoObject = cryptoObject;
    }

    public void setDialogCallback(DialogResultListener dialogResultListener) {
        this.dialogCallback = dialogResultListener;
    }

    public void setReasonForAuthentication(String str) {
        this.authReason = str;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mFingerprintHandler = new FingerprintHandler(context, this);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, android.R.style.Theme.Material.Light.Dialog);
        setCancelable(false);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fingerprint_dialog, viewGroup, false);
        ((TextView) viewInflate.findViewById(R.id.fingerprint_description)).setText(this.authReason);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.fingerprint_icon);
        this.mFingerprintImage = imageView;
        int i = this.imageColor;
        if (i != 0) {
            imageView.setColorFilter(i);
        }
        TextView textView = (TextView) viewInflate.findViewById(R.id.fingerprint_sensor_description);
        this.mFingerprintSensorDescription = textView;
        textView.setText(this.sensorDescription);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.fingerprint_error);
        this.mFingerprintError = textView2;
        textView2.setText(this.errorText);
        Button button = (Button) viewInflate.findViewById(R.id.cancel_button);
        button.setText(this.cancelText);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.rnfingerprint.FingerprintDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FingerprintDialog.this.onCancelled();
            }
        });
        getDialog().setTitle(this.dialogTitle);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.rnfingerprint.FingerprintDialog.2
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                if (i2 != 4 || FingerprintDialog.this.mFingerprintHandler == null) {
                    return false;
                }
                FingerprintDialog.this.onCancelled();
                return true;
            }
        });
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isAuthInProgress) {
            return;
        }
        this.isAuthInProgress = true;
        this.mFingerprintHandler.startAuth(this.mCryptoObject);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.isAuthInProgress) {
            this.mFingerprintHandler.endAuth();
            this.isAuthInProgress = false;
        }
    }

    public void setAuthConfig(ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        if (readableMap.hasKey("title")) {
            this.dialogTitle = readableMap.getString("title");
        }
        if (readableMap.hasKey("cancelText")) {
            this.cancelText = readableMap.getString("cancelText");
        }
        if (readableMap.hasKey("sensorDescription")) {
            this.sensorDescription = readableMap.getString("sensorDescription");
        }
        if (readableMap.hasKey("sensorErrorDescription")) {
            this.sensorErrorDescription = readableMap.getString("sensorErrorDescription");
        }
        if (readableMap.hasKey("imageColor")) {
            this.imageColor = readableMap.getInt("imageColor");
        }
        if (readableMap.hasKey("imageErrorColor")) {
            this.imageErrorColor = readableMap.getInt("imageErrorColor");
        }
    }

    @Override // com.rnfingerprint.FingerprintHandler.Callback
    public void onAuthenticated() {
        this.isAuthInProgress = false;
        this.dialogCallback.onAuthenticated();
        dismiss();
    }

    @Override // com.rnfingerprint.FingerprintHandler.Callback
    public void onError(String str, int i) {
        this.mFingerprintError.setText(str);
        this.mFingerprintImage.setColorFilter(this.imageErrorColor);
        this.mFingerprintSensorDescription.setText(this.sensorErrorDescription);
    }

    @Override // com.rnfingerprint.FingerprintHandler.Callback
    public void onCancelled() {
        this.isAuthInProgress = false;
        this.mFingerprintHandler.endAuth();
        this.dialogCallback.onCancelled();
        dismiss();
    }
}
