package com.clevertap.android.sdk.inbox;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.clevertap.android.sdk.R;

/* loaded from: classes5.dex */
class CTSimpleMessageViewHolder extends CTInboxBaseMessageViewHolder {
    private final Button cta1;
    private final Button cta2;
    private final Button cta3;
    private final TextView message;
    private final TextView timestamp;
    private final TextView title;

    CTSimpleMessageViewHolder(View view) {
        super(view);
        view.setTag(this);
        this.title = (TextView) view.findViewById(R.id.messageTitle);
        this.message = (TextView) view.findViewById(R.id.messageText);
        this.timestamp = (TextView) view.findViewById(R.id.timestamp);
        this.cta1 = (Button) view.findViewById(R.id.cta_button_1);
        this.cta2 = (Button) view.findViewById(R.id.cta_button_2);
        this.cta3 = (Button) view.findViewById(R.id.cta_button_3);
        this.mediaImage = (ImageView) view.findViewById(R.id.media_image);
        this.relativeLayout = (RelativeLayout) view.findViewById(R.id.simple_message_relative_layout);
        this.frameLayout = (FrameLayout) view.findViewById(R.id.simple_message_frame_layout);
        this.squareImage = (ImageView) view.findViewById(R.id.square_media_image);
        this.clickLayout = (RelativeLayout) view.findViewById(R.id.click_relative_layout);
        this.ctaLinearLayout = (LinearLayout) view.findViewById(R.id.cta_linear_layout);
        this.bodyRelativeLayout = (LinearLayout) view.findViewById(R.id.body_linear_layout);
        this.progressBarFrameLayout = (FrameLayout) view.findViewById(R.id.simple_progress_frame_layout);
        this.mediaLayout = (RelativeLayout) view.findViewById(R.id.media_layout);
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x06c1  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x06ee  */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x04dd A[Catch: NoClassDefFoundError -> 0x06a3, TryCatch #0 {NoClassDefFoundError -> 0x06a3, blocks: (B:65:0x03f9, B:67:0x0408, B:69:0x0440, B:66:0x0401, B:70:0x045c, B:72:0x046b, B:74:0x047a, B:76:0x0482, B:73:0x0473, B:77:0x049b, B:79:0x04a2, B:81:0x04c4, B:82:0x04dd, B:84:0x04e4, B:85:0x04f5, B:87:0x052d, B:88:0x0549, B:90:0x054f, B:91:0x0560, B:93:0x059c, B:94:0x05bc, B:96:0x05c2, B:98:0x05cc, B:99:0x05dd, B:101:0x0615, B:102:0x0631, B:104:0x064a, B:105:0x0662, B:107:0x0668, B:109:0x068a), top: B:125:0x02f6, inners: #2, #5, #7, #8 }] */
    @Override // com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void configureWithMessage(com.clevertap.android.sdk.inbox.CTInboxMessage r21, com.clevertap.android.sdk.inbox.CTInboxListViewFragment r22, int r23) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 1795
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inbox.CTSimpleMessageViewHolder.configureWithMessage(com.clevertap.android.sdk.inbox.CTInboxMessage, com.clevertap.android.sdk.inbox.CTInboxListViewFragment, int):void");
    }
}
