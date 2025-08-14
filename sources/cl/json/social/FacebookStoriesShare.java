package cl.json.social;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import cl.json.ShareFile;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.reactnativecommunity.clipboard.ClipboardModule;

/* loaded from: classes5.dex */
public class FacebookStoriesShare extends SingleShareIntent {
    private static final String PACKAGE = "com.facebook.katana";
    private static final String PLAY_STORE_LINK = "market://details?id=com.facebook.katana";

    @Override // cl.json.social.ShareIntent
    protected String getDefaultWebLink() {
        return null;
    }

    @Override // cl.json.social.ShareIntent
    protected String getPackage() {
        return PACKAGE;
    }

    @Override // cl.json.social.ShareIntent
    protected String getPlayStoreLink() {
        return PLAY_STORE_LINK;
    }

    public FacebookStoriesShare(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        setIntent(new Intent("com.facebook.stories.ADD_TO_STORY"));
    }

    @Override // cl.json.social.SingleShareIntent, cl.json.social.ShareIntent
    public void open(ReadableMap readableMap) throws ActivityNotFoundException, IllegalArgumentException {
        super.open(readableMap);
        shareStory(readableMap);
        openIntentChooser(readableMap);
    }

    private void shareStory(ReadableMap readableMap) {
        String string;
        if (!hasValidKey(RemoteConfigConstants.RequestFieldKey.APP_ID, readableMap)) {
            throw new IllegalArgumentException("appId was not provided.");
        }
        if (!hasValidKey("backgroundImage", readableMap) && !hasValidKey("backgroundVideo", readableMap) && !hasValidKey("stickerImage", readableMap)) {
            throw new IllegalArgumentException("Invalid background or sticker assets provided.");
        }
        Activity currentActivity = this.reactContext.getCurrentActivity();
        if (currentActivity == null) {
            TargetChosenReceiver.callbackReject("Something went wrong");
            return;
        }
        this.intent.putExtra("com.facebook.platform.extra.APPLICATION_ID", readableMap.getString(RemoteConfigConstants.RequestFieldKey.APP_ID));
        this.intent.putExtra("bottom_background_color", "#906df4");
        this.intent.putExtra("top_background_color", "#837DF4");
        if (hasValidKey("attributionURL", readableMap)) {
            this.intent.putExtra("content_url", readableMap.getString("attributionURL"));
        }
        if (hasValidKey("backgroundTopColor", readableMap)) {
            this.intent.putExtra("top_background_color", readableMap.getString("backgroundTopColor"));
        }
        if (hasValidKey("backgroundBottomColor", readableMap)) {
            this.intent.putExtra("bottom_background_color", readableMap.getString("backgroundBottomColor"));
        }
        boolean zValueOf = false;
        if (hasValidKey("useInternalStorage", readableMap)) {
            zValueOf = Boolean.valueOf(readableMap.getBoolean("useInternalStorage"));
        }
        Boolean boolValueOf = Boolean.valueOf(hasValidKey("backgroundImage", readableMap) || hasValidKey("backgroundVideo", readableMap));
        if (boolValueOf.booleanValue()) {
            if (hasValidKey("backgroundImage", readableMap)) {
                string = readableMap.getString("backgroundImage");
            } else {
                string = hasValidKey("backgroundVideo", readableMap) ? readableMap.getString("backgroundVideo") : "";
            }
            ShareFile shareFile = new ShareFile(string, "image/jpeg", AppStateModule.APP_STATE_BACKGROUND, zValueOf, this.reactContext);
            this.intent.setDataAndType(shareFile.getURI(), shareFile.getType());
            this.intent.setFlags(1);
        }
        if (hasValidKey("stickerImage", readableMap)) {
            ShareFile shareFile2 = new ShareFile(readableMap.getString("stickerImage"), ClipboardModule.MIMETYPE_PNG, "sticker", zValueOf, this.reactContext);
            if (!boolValueOf.booleanValue()) {
                this.intent.setType("image/*");
            }
            this.intent.putExtra("interactive_asset_uri", shareFile2.getURI());
            currentActivity.grantUriPermission(PACKAGE, shareFile2.getURI(), 1);
        }
    }
}
