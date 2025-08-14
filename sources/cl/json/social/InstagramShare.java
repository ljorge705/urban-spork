package cl.json.social;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.media3.common.MimeTypes;
import androidx.webkit.internal.AssetHelper;
import cl.json.RNShareImpl;
import cl.json.ShareFile;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: classes5.dex */
public class InstagramShare extends SingleShareIntent {
    private static final String PACKAGE = "com.instagram.android";
    private static final String PLAY_STORE_LINK = "https://play.google.com/store/apps/details?id=com.instagram.android";

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

    public InstagramShare(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // cl.json.social.SingleShareIntent, cl.json.social.ShareIntent
    public void open(ReadableMap readableMap) throws ActivityNotFoundException {
        super.open(readableMap);
        if (!ShareIntent.hasValidKey("type", readableMap)) {
            Log.e(RNShareImpl.NAME, "No type provided");
            return;
        }
        String string = readableMap.getString("type");
        if (string.startsWith("text")) {
            openInstagramIntentChooserForText(this.chooserTitle);
            return;
        }
        if (!ShareIntent.hasValidKey("url", readableMap)) {
            Log.e(RNShareImpl.NAME, "No url provided");
            return;
        }
        String string2 = readableMap.getString("url");
        if (Boolean.valueOf(string2.startsWith("instagram://")).booleanValue()) {
            openInstagramUrlScheme(string2);
            return;
        }
        String extension = getExtension(string);
        openInstagramIntentChooserForMedia(string2, this.chooserTitle, Boolean.valueOf(string.startsWith(MimeTypes.BASE_TYPE_IMAGE)), extension);
    }

    protected void openInstagramUrlScheme(String str) throws ActivityNotFoundException {
        Uri uri = Uri.parse(str);
        getIntent().setAction("android.intent.action.VIEW");
        getIntent().setData(uri);
        super.openIntentChooser();
    }

    private String getExtension(String str) {
        return str.split("/")[r2.length - 1];
    }

    protected void openInstagramIntentChooserForText(String str) throws ActivityNotFoundException {
        getIntent().setPackage(PACKAGE);
        getIntent().setType(AssetHelper.DEFAULT_MIME_TYPE);
        getIntent().setAction("android.intent.action.SEND");
        super.openIntentChooser();
    }

    protected void openInstagramIntentChooserForMedia(String str, String str2, Boolean bool, String str3) {
        ShareFile shareFile;
        Boolean boolValueOf = Boolean.valueOf(ShareIntent.hasValidKey("useInternalStorage", this.options) && this.options.getBoolean("useInternalStorage"));
        if (bool.booleanValue()) {
            shareFile = new ShareFile(str, "image/" + str3, MimeTypes.BASE_TYPE_IMAGE, boolValueOf, this.reactContext);
        } else {
            shareFile = new ShareFile(str, "video/" + str3, "video", boolValueOf, this.reactContext);
        }
        Uri uri = shareFile.getURI();
        Intent intent = new Intent("android.intent.action.SEND");
        if (bool.booleanValue()) {
            intent.setType("image/*");
        } else {
            intent.setType("video/*");
        }
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setPackage(PACKAGE);
        Intent intent2 = new Intent("com.instagram.share.ADD_TO_STORY");
        intent2.setDataAndType(uri, str3);
        intent2.addFlags(1);
        intent2.setPackage(PACKAGE);
        Intent intentCreateChooser = Intent.createChooser(intent, str2);
        intentCreateChooser.addFlags(268435456);
        intentCreateChooser.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[]{intent2});
        this.reactContext.getCurrentActivity().grantUriPermission(PACKAGE, uri, 1);
        this.reactContext.startActivity(intentCreateChooser);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean(FirebaseAnalytics.Param.SUCCESS, true);
        writableMapCreateMap.putString("message", getIntent().getPackage());
        TargetChosenReceiver.callbackResolve(writableMapCreateMap);
    }
}
