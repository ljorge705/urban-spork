package com.fivvy.profiler.presenter;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.fivvy.profiler.R;
import com.fivvy.profiler.domain.enums.Language;

/* loaded from: classes5.dex */
public class TransparentActivity extends AppCompatActivity {
    TextView textAppDescription;
    TextView textModalInstruction;
    TextView textViewAppName;
    private WebView webViewSwitch;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_transparent);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.bottom_sheet);
        this.webViewSwitch = (WebView) findViewById(R.id.webViewSwitch);
        this.textViewAppName = (TextView) findViewById(R.id.textViewAppName);
        this.textAppDescription = (TextView) findViewById(R.id.textAppDescription);
        this.textModalInstruction = (TextView) findViewById(R.id.textViewInstructions);
        this.webViewSwitch.getSettings().setJavaScriptEnabled(true);
        this.webViewSwitch.getSettings().setLoadWithOverviewMode(true);
        this.webViewSwitch.getSettings().setUseWideViewPort(true);
        this.webViewSwitch.setWebViewClient(new WebViewClient());
        this.webViewSwitch.setScrollContainer(false);
        this.webViewSwitch.setHorizontalScrollBarEnabled(false);
        this.webViewSwitch.setVerticalScrollBarEnabled(false);
        this.webViewSwitch.setHorizontalScrollbarOverlay(false);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        byte[] byteArrayExtra = getIntent().getByteArrayExtra("IMAGE_PATH");
        if (byteArrayExtra != null) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length));
        } else {
            Log.e("TransparentActivity", "No image bytes received");
        }
        this.webViewSwitch.loadDataWithBaseURL(null, "<html><head><style>img{position: fixed; width:213px; height: 110px;}</style></head><body><img src=\"file:///android_asset/preview.gif\"></body></html>", "text/html", "utf-8", null);
        getIntent().getStringExtra("EXTRA_PACKAGE_NAME");
        setCustomModalTexts(getIntent().getStringExtra("APP_NAME"), getIntent().getStringExtra("APP_DESCRIPTION"), getIntent().getStringExtra("MODAL_TEXT"), getIntent().getStringExtra("LN"));
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fivvy.profiler.presenter.TransparentActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m5129x8e0bf5b(view);
            }
        });
    }

    /* renamed from: lambda$onCreate$0$com-fivvy-profiler-presenter-TransparentActivity, reason: not valid java name */
    /* synthetic */ void m5129x8e0bf5b(View view) {
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.webViewSwitch.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.webViewSwitch.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        WebView webView = this.webViewSwitch;
        if (webView != null) {
            webView.removeAllViews();
            this.webViewSwitch.destroy();
        }
        super.onDestroy();
    }

    private void setCustomModalTexts(String str, String str2, String str3, String str4) {
        if (str4.toUpperCase().equals(Language.ES.name())) {
            if (str != null) {
                this.textViewAppName.setText(str.equals("") ? getString(R.string.es_app_name) : str);
            }
            if (str2 != null) {
                this.textAppDescription.setText(str2.equals("") ? getString(R.string.es_permisos_otorgados) : str2);
            }
            if (str3 != null) {
                this.textModalInstruction.setText(str3.equals("") ? getString(R.string.es_modal_text) : str3);
            }
        }
        if (str4.toUpperCase().equals(Language.PT.name())) {
            if (str != null) {
                this.textViewAppName.setText(str.equals("") ? getString(R.string.pt_app_name) : str);
            }
            if (str2 != null) {
                this.textAppDescription.setText(str2.equals("") ? getString(R.string.pt_permisos_otorgados) : str2);
            }
            if (str3 != null) {
                this.textModalInstruction.setText(str3.equals("") ? getString(R.string.pt_modal_text) : str3);
            }
        }
        if (str4.toUpperCase().equals(Language.EN.name())) {
            if (str != null) {
                TextView textView = this.textViewAppName;
                if (str.equals("")) {
                    str = getString(R.string.en_app_name);
                }
                textView.setText(str);
            }
            if (str2 != null) {
                TextView textView2 = this.textAppDescription;
                if (str2.equals("")) {
                    str2 = getString(R.string.en_permisos_otorgados);
                }
                textView2.setText(str2);
            }
            if (str3 != null) {
                TextView textView3 = this.textModalInstruction;
                if (str3.equals("")) {
                    str3 = getString(R.string.en_modal_text);
                }
                textView3.setText(str3);
            }
        }
    }
}
