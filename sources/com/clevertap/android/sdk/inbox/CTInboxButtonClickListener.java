package com.clevertap.android.sdk.inbox;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import com.clevertap.android.sdk.Constants;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
class CTInboxButtonClickListener implements View.OnClickListener {
    private final int buttonIndex;
    private JSONObject buttonObject;
    private final String buttonText;
    private final CTInboxListViewFragment fragment;
    private final CTInboxMessage inboxMessage;
    private final boolean isBodyClick;
    private final int position;
    private ViewPager viewPager;

    CTInboxButtonClickListener(int i, CTInboxMessage cTInboxMessage, String str, JSONObject jSONObject, CTInboxListViewFragment cTInboxListViewFragment, boolean z, int i2) {
        this.position = i;
        this.inboxMessage = cTInboxMessage;
        this.buttonText = str;
        this.fragment = cTInboxListViewFragment;
        this.buttonObject = jSONObject;
        this.isBodyClick = z;
        this.buttonIndex = i2;
    }

    CTInboxButtonClickListener(int i, CTInboxMessage cTInboxMessage, String str, CTInboxListViewFragment cTInboxListViewFragment, ViewPager viewPager, boolean z, int i2) {
        this.position = i;
        this.inboxMessage = cTInboxMessage;
        this.buttonText = str;
        this.fragment = cTInboxListViewFragment;
        this.viewPager = viewPager;
        this.isBodyClick = z;
        this.buttonIndex = i2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            CTInboxListViewFragment cTInboxListViewFragment = this.fragment;
            if (cTInboxListViewFragment != null) {
                cTInboxListViewFragment.handleViewPagerClick(this.position, viewPager.getCurrentItem());
                return;
            }
            return;
        }
        if (this.buttonText == null || this.buttonObject == null) {
            CTInboxListViewFragment cTInboxListViewFragment2 = this.fragment;
            if (cTInboxListViewFragment2 != null) {
                cTInboxListViewFragment2.handleClick(this.position, 0, null, null, null, this.buttonIndex);
                return;
            }
            return;
        }
        if (this.fragment != null) {
            if (this.inboxMessage.getInboxMessageContents().get(0).getLinktype(this.buttonObject).equalsIgnoreCase(Constants.COPY_TYPE) && this.fragment.getActivity() != null) {
                copyToClipboard(this.fragment.getActivity());
            }
            this.fragment.handleClick(this.position, 0, this.buttonText, this.buttonObject, getKeyValues(this.inboxMessage), this.buttonIndex);
        }
    }

    private void copyToClipboard(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        ClipData clipDataNewPlainText = ClipData.newPlainText(this.buttonText, this.inboxMessage.getInboxMessageContents().get(0).getLinkCopyText(this.buttonObject));
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipDataNewPlainText);
            Toast.makeText(context, "Text Copied to Clipboard", 0).show();
        }
    }

    private HashMap<String, String> getKeyValues(CTInboxMessage cTInboxMessage) {
        if (cTInboxMessage == null || cTInboxMessage.getInboxMessageContents() == null || cTInboxMessage.getInboxMessageContents().get(0) == null || !Constants.KEY_KV.equalsIgnoreCase(cTInboxMessage.getInboxMessageContents().get(0).getLinktype(this.buttonObject))) {
            return null;
        }
        return cTInboxMessage.getInboxMessageContents().get(0).getLinkKeyValue(this.buttonObject);
    }
}
