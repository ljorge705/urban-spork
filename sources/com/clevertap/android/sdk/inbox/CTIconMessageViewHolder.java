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
class CTIconMessageViewHolder extends CTInboxBaseMessageViewHolder {
    private final RelativeLayout clickLayout;
    private final Button cta1;
    private final Button cta2;
    private final Button cta3;
    private final LinearLayout ctaLinearLayout;
    private final ImageView iconImage;
    private final TextView message;
    private final TextView timestamp;
    private final TextView title;

    CTIconMessageViewHolder(View view) {
        super(view);
        view.setTag(this);
        this.title = (TextView) view.findViewById(R.id.messageTitle);
        this.message = (TextView) view.findViewById(R.id.messageText);
        this.mediaImage = (ImageView) view.findViewById(R.id.media_image);
        this.iconImage = (ImageView) view.findViewById(R.id.image_icon);
        this.timestamp = (TextView) view.findViewById(R.id.timestamp);
        this.cta1 = (Button) view.findViewById(R.id.cta_button_1);
        this.cta2 = (Button) view.findViewById(R.id.cta_button_2);
        this.cta3 = (Button) view.findViewById(R.id.cta_button_3);
        this.frameLayout = (FrameLayout) view.findViewById(R.id.icon_message_frame_layout);
        this.squareImage = (ImageView) view.findViewById(R.id.square_media_image);
        this.clickLayout = (RelativeLayout) view.findViewById(R.id.click_relative_layout);
        this.ctaLinearLayout = (LinearLayout) view.findViewById(R.id.cta_linear_layout);
        this.progressBarFrameLayout = (FrameLayout) view.findViewById(R.id.icon_progress_frame_layout);
        this.mediaLayout = (RelativeLayout) view.findViewById(R.id.media_layout);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:0|2|(1:4)(1:5)|6|(5:8|166|(1:(4:11|(3:142|14|(6:16|17|151|18|19|20))|13|34)(2:25|(1:27)))(2:28|(1:30))|165|34)(1:35)|36|(3:158|37|(5:39|(1:44)(1:48)|49|157|(2:51|(3:54|(3:56|155|57)(2:60|(3:62|146|63)(2:66|(2:68|(5:70|71|(1:73)(1:74)|163|75)(4:78|(1:80)(1:81)|82|(1:84)))(2:85|(2:87|(1:89)))))|53)(1:53))(2:90|(3:92|144|93)(2:96|(3:98|153|99)(2:102|(2:104|(3:106|161|107)(2:110|(1:112)))(2:113|(2:115|(1:117)))))))(5:45|(1:47)(0)|49|157|(0)(0)))|121|(1:123)(2:124|(1:126)(1:127))|128|149|129|(3:131|159|132)(1:135)|148|(2:139|140)(1:168)) */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x079f, code lost:
    
        com.clevertap.android.sdk.Logger.d(r20);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:123:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x070a  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x073f A[Catch: NoClassDefFoundError -> 0x079f, TRY_LEAVE, TryCatch #3 {NoClassDefFoundError -> 0x079f, blocks: (B:129:0x0735, B:131:0x073f, B:132:0x0745, B:134:0x077c, B:135:0x0797), top: B:149:0x0735, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0797 A[Catch: NoClassDefFoundError -> 0x079f, TRY_LEAVE, TryCatch #3 {NoClassDefFoundError -> 0x079f, blocks: (B:129:0x0735, B:131:0x073f, B:132:0x0745, B:134:0x077c, B:135:0x0797), top: B:149:0x0735, inners: #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x07a4  */
    /* JADX WARN: Removed duplicated region for block: B:168:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0321 A[Catch: NoClassDefFoundError -> 0x06ed, TRY_LEAVE, TryCatch #8 {NoClassDefFoundError -> 0x06ed, blocks: (B:37:0x0304, B:54:0x0336, B:56:0x033c, B:57:0x034e, B:59:0x0385, B:60:0x03a0, B:62:0x03a6, B:63:0x03b8, B:65:0x03f4, B:66:0x0414, B:68:0x041a, B:70:0x042a, B:42:0x0316, B:45:0x0321), top: B:158:0x0304, inners: #2, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0523 A[Catch: NoClassDefFoundError -> 0x06ee, TryCatch #7 {NoClassDefFoundError -> 0x06ee, blocks: (B:73:0x0434, B:75:0x0443, B:77:0x047e, B:74:0x043c, B:78:0x0497, B:80:0x04a7, B:82:0x04b6, B:84:0x04c7, B:81:0x04af, B:85:0x04e0, B:87:0x04e7, B:89:0x050a, B:90:0x0523, B:92:0x052a, B:93:0x053c, B:95:0x0574, B:96:0x0590, B:98:0x0596, B:99:0x05a8, B:101:0x05e4, B:102:0x0604, B:104:0x060a, B:106:0x061a, B:107:0x0626, B:109:0x065e, B:110:0x067a, B:112:0x0694, B:113:0x06ac, B:115:0x06b2, B:117:0x06d5), top: B:157:0x032e, inners: #1, #5, #10, #11 }] */
    /* JADX WARN: Type inference failed for: r13v1, types: [com.clevertap.android.sdk.inbox.CTInboxMessageContent] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10, types: [com.clevertap.android.sdk.inbox.CTInboxMessageContent] */
    /* JADX WARN: Type inference failed for: r3v12, types: [com.clevertap.android.sdk.inbox.CTInboxMessageContent] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.clevertap.android.sdk.inbox.CTInboxMessageContent] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v46, types: [android.widget.Button] */
    /* JADX WARN: Type inference failed for: r7v47, types: [int] */
    /* JADX WARN: Type inference failed for: r7v50 */
    /* JADX WARN: Type inference failed for: r7v55 */
    /* JADX WARN: Type inference failed for: r7v58 */
    /* JADX WARN: Type inference failed for: r7v59 */
    /* JADX WARN: Type inference failed for: r7v62 */
    /* JADX WARN: Type inference failed for: r7v63 */
    /* JADX WARN: Type inference failed for: r7v66 */
    /* JADX WARN: Type inference failed for: r7v72 */
    /* JADX WARN: Type inference failed for: r7v73 */
    /* JADX WARN: Type inference failed for: r7v74 */
    /* JADX WARN: Type inference failed for: r7v76 */
    /* JADX WARN: Type inference failed for: r7v77 */
    /* JADX WARN: Type inference failed for: r7v78 */
    /* JADX WARN: Type inference failed for: r7v79 */
    /* JADX WARN: Type inference failed for: r7v8 */
    @Override // com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void configureWithMessage(com.clevertap.android.sdk.inbox.CTInboxMessage r22, com.clevertap.android.sdk.inbox.CTInboxListViewFragment r23, int r24) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 1977
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inbox.CTIconMessageViewHolder.configureWithMessage(com.clevertap.android.sdk.inbox.CTInboxMessage, com.clevertap.android.sdk.inbox.CTInboxListViewFragment, int):void");
    }
}
