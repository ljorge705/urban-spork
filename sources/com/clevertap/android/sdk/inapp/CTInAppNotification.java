package com.clevertap.android.sdk.inapp;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplateInAppData;
import com.clevertap.android.sdk.inapp.images.FileResourceProvider;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTInAppNotification implements Parcelable {
    public static final Parcelable.Creator<CTInAppNotification> CREATOR = new Parcelable.Creator<CTInAppNotification>() { // from class: com.clevertap.android.sdk.inapp.CTInAppNotification.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInAppNotification createFromParcel(Parcel parcel) {
            return new CTInAppNotification(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInAppNotification[] newArray(int i) {
            return new CTInAppNotification[i];
        }
    };
    private String _landscapeImageCacheKey;
    private JSONObject actionExtras;
    private String backgroundColor;
    private int buttonCount;
    private ArrayList<CTInAppNotificationButton> buttons;
    private String campaignId;
    private JSONObject customExtras;
    private String customInAppUrl;
    private CustomTemplateInAppData customTemplateData;
    private boolean darkenScreen;
    private String error;
    private boolean excludeFromCaps;
    private boolean fallBackToNotificationSettings;
    private int height;
    private int heightPercentage;
    private boolean hideCloseButton;
    private String html;
    private String id;
    private CTInAppType inAppType;
    private boolean isLandscape;
    private boolean isLocalInApp;
    private boolean isPortrait;
    private boolean isTablet;
    private boolean jsEnabled;
    private JSONObject jsonDescription;
    private String landscapeImageUrl;
    private int maxPerSession;
    private ArrayList<CTInAppNotificationMedia> mediaList;
    private String message;
    private String messageColor;
    private char position;
    private boolean showClose;
    private long timeToLive;
    private String title;
    private String titleColor;
    private int totalDailyCount;
    private int totalLifetimeCount;
    private String type;
    private boolean videoSupported;
    private int width;
    private int widthPercentage;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    void didDismiss(FileResourceProvider fileResourceProvider) {
    }

    public boolean fallBackToNotificationSettings() {
        return this.fallBackToNotificationSettings;
    }

    public JSONObject getActionExtras() {
        return this.actionExtras;
    }

    String getBackgroundColor() {
        return this.backgroundColor;
    }

    int getButtonCount() {
        return this.buttonCount;
    }

    public ArrayList<CTInAppNotificationButton> getButtons() {
        return this.buttons;
    }

    public String getCampaignId() {
        return this.campaignId;
    }

    JSONObject getCustomExtras() {
        return this.customExtras;
    }

    String getCustomInAppUrl() {
        return this.customInAppUrl;
    }

    public CustomTemplateInAppData getCustomTemplateData() {
        return this.customTemplateData;
    }

    String getError() {
        return this.error;
    }

    int getHeight() {
        return this.height;
    }

    int getHeightPercentage() {
        return this.heightPercentage;
    }

    String getHtml() {
        return this.html;
    }

    public String getId() {
        return this.id;
    }

    public CTInAppType getInAppType() {
        return this.inAppType;
    }

    public JSONObject getJsonDescription() {
        return this.jsonDescription;
    }

    public int getMaxPerSession() {
        return this.maxPerSession;
    }

    ArrayList<CTInAppNotificationMedia> getMediaList() {
        return this.mediaList;
    }

    public String getMessage() {
        return this.message;
    }

    String getMessageColor() {
        return this.messageColor;
    }

    char getPosition() {
        return this.position;
    }

    public long getTimeToLive() {
        return this.timeToLive;
    }

    public String getTitle() {
        return this.title;
    }

    String getTitleColor() {
        return this.titleColor;
    }

    public int getTotalDailyCount() {
        return this.totalDailyCount;
    }

    public int getTotalLifetimeCount() {
        return this.totalLifetimeCount;
    }

    String getType() {
        return this.type;
    }

    int getWidth() {
        return this.width;
    }

    int getWidthPercentage() {
        return this.widthPercentage;
    }

    boolean isDarkenScreen() {
        return this.darkenScreen;
    }

    public boolean isExcludeFromCaps() {
        return this.excludeFromCaps;
    }

    boolean isHideCloseButton() {
        return this.hideCloseButton;
    }

    boolean isJsEnabled() {
        return this.jsEnabled;
    }

    public boolean isLandscape() {
        return this.isLandscape;
    }

    public boolean isLocalInApp() {
        return this.isLocalInApp;
    }

    public boolean isPortrait() {
        return this.isPortrait;
    }

    boolean isShowClose() {
        return this.showClose;
    }

    boolean isTablet() {
        return this.isTablet;
    }

    boolean isVideoSupported() {
        return this.videoSupported;
    }

    void setError(String str) {
        this.error = str;
    }

    CTInAppNotification() {
        this.buttons = new ArrayList<>();
        this.mediaList = new ArrayList<>();
        this.isLocalInApp = false;
        this.fallBackToNotificationSettings = false;
    }

    private CTInAppNotification(Parcel parcel) {
        this.buttons = new ArrayList<>();
        this.mediaList = new ArrayList<>();
        this.isLocalInApp = false;
        this.fallBackToNotificationSettings = false;
        try {
            this.id = parcel.readString();
            this.campaignId = parcel.readString();
            this.inAppType = (CTInAppType) parcel.readValue(CTInAppType.class.getClassLoader());
            this.html = parcel.readString();
            this.excludeFromCaps = parcel.readByte() != 0;
            this.showClose = parcel.readByte() != 0;
            this.darkenScreen = parcel.readByte() != 0;
            this.maxPerSession = parcel.readInt();
            this.totalLifetimeCount = parcel.readInt();
            this.totalDailyCount = parcel.readInt();
            this.position = ((Character) parcel.readValue(Character.TYPE.getClassLoader())).charValue();
            this.height = parcel.readInt();
            this.heightPercentage = parcel.readInt();
            this.width = parcel.readInt();
            this.widthPercentage = parcel.readInt();
            JSONObject jSONObject = null;
            this.jsonDescription = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
            this.error = parcel.readString();
            this.customExtras = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
            if (parcel.readByte() != 0) {
                jSONObject = new JSONObject(parcel.readString());
            }
            this.actionExtras = jSONObject;
            this.type = parcel.readString();
            this.title = parcel.readString();
            this.titleColor = parcel.readString();
            this.backgroundColor = parcel.readString();
            this.message = parcel.readString();
            this.messageColor = parcel.readString();
            try {
                this.buttons = parcel.createTypedArrayList(CTInAppNotificationButton.CREATOR);
            } catch (Throwable unused) {
            }
            try {
                this.mediaList = parcel.createTypedArrayList(CTInAppNotificationMedia.CREATOR);
            } catch (Throwable unused2) {
            }
            this.hideCloseButton = parcel.readByte() != 0;
            this.buttonCount = parcel.readInt();
            this.isTablet = parcel.readByte() != 0;
            this.customInAppUrl = parcel.readString();
            this.jsEnabled = parcel.readByte() != 0;
            this.isPortrait = parcel.readByte() != 0;
            this.isLandscape = parcel.readByte() != 0;
            this.isLocalInApp = parcel.readByte() != 0;
            this.fallBackToNotificationSettings = parcel.readByte() != 0;
            this.landscapeImageUrl = parcel.readString();
            this._landscapeImageCacheKey = parcel.readString();
            this.timeToLive = parcel.readLong();
            this.customTemplateData = (CustomTemplateInAppData) parcel.readParcelable(CustomTemplateInAppData.class.getClassLoader());
        } catch (JSONException unused3) {
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.campaignId);
        parcel.writeValue(this.inAppType);
        parcel.writeString(this.html);
        parcel.writeByte(this.excludeFromCaps ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.showClose ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.darkenScreen ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.maxPerSession);
        parcel.writeInt(this.totalLifetimeCount);
        parcel.writeInt(this.totalDailyCount);
        parcel.writeValue(Character.valueOf(this.position));
        parcel.writeInt(this.height);
        parcel.writeInt(this.heightPercentage);
        parcel.writeInt(this.width);
        parcel.writeInt(this.widthPercentage);
        if (this.jsonDescription == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.jsonDescription.toString());
        }
        parcel.writeString(this.error);
        if (this.customExtras == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.customExtras.toString());
        }
        if (this.actionExtras == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.actionExtras.toString());
        }
        parcel.writeString(this.type);
        parcel.writeString(this.title);
        parcel.writeString(this.titleColor);
        parcel.writeString(this.backgroundColor);
        parcel.writeString(this.message);
        parcel.writeString(this.messageColor);
        parcel.writeTypedList(this.buttons);
        parcel.writeTypedList(this.mediaList);
        parcel.writeByte(this.hideCloseButton ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.buttonCount);
        parcel.writeByte(this.isTablet ? (byte) 1 : (byte) 0);
        parcel.writeString(this.customInAppUrl);
        parcel.writeByte(this.jsEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isPortrait ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isLandscape ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isLocalInApp ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.fallBackToNotificationSettings ? (byte) 1 : (byte) 0);
        parcel.writeString(this.landscapeImageUrl);
        parcel.writeString(this._landscapeImageCacheKey);
        parcel.writeLong(this.timeToLive);
        parcel.writeParcelable(this.customTemplateData, i);
    }

    CTInAppNotificationMedia getInAppMediaForOrientation(int i) {
        Iterator<CTInAppNotificationMedia> it = this.mediaList.iterator();
        while (it.hasNext()) {
            CTInAppNotificationMedia next = it.next();
            if (i == next.getOrientation()) {
                return next;
            }
        }
        return null;
    }

    CTInAppNotification initWithJSON(JSONObject jSONObject, boolean z) {
        this.videoSupported = z;
        this.jsonDescription = jSONObject;
        try {
            String string = jSONObject.has("type") ? jSONObject.getString("type") : null;
            this.type = string;
            if (string == null || string.equals(Constants.KEY_CUSTOM_HTML)) {
                legacyConfigureWithJson(jSONObject);
            } else {
                configureWithJson(jSONObject);
            }
        } catch (JSONException e) {
            this.error = "Invalid JSON : " + e.getLocalizedMessage();
        }
        return this;
    }

    CTInAppNotification createNotificationForAction(CustomTemplateInAppData customTemplateInAppData) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constants.INAPP_ID_IN_PAYLOAD, this.id);
            jSONObject.put(Constants.NOTIFICATION_ID_TAG, this.campaignId);
            jSONObject.put("type", InAppActionType.CUSTOM_CODE.toString());
            jSONObject.put(Constants.KEY_EFC, 1);
            jSONObject.put(Constants.KEY_EXCLUDE_GLOBAL_CAPS, 1);
            jSONObject.put("wzrk_ttl", this.timeToLive);
            if (this.jsonDescription.has(Constants.INAPP_WZRK_PIVOT)) {
                jSONObject.put(Constants.INAPP_WZRK_PIVOT, this.jsonDescription.optString(Constants.INAPP_WZRK_PIVOT));
            }
            if (this.jsonDescription.has(Constants.INAPP_WZRK_CGID)) {
                jSONObject.put(Constants.INAPP_WZRK_CGID, this.jsonDescription.optString(Constants.INAPP_WZRK_CGID));
            }
            CTInAppNotification cTInAppNotificationInitWithJSON = new CTInAppNotification().initWithJSON(jSONObject, this.videoSupported);
            cTInAppNotificationInitWithJSON.setCustomTemplateData(customTemplateInAppData);
            return cTInAppNotificationInitWithJSON;
        } catch (JSONException unused) {
            return null;
        }
    }

    void setCustomTemplateData(CustomTemplateInAppData customTemplateInAppData) throws JSONException {
        this.customTemplateData = customTemplateInAppData;
        customTemplateInAppData.writeFieldsToJson(this.jsonDescription);
    }

    public boolean hasStreamMedia() {
        return !getMediaList().isEmpty() && getMediaList().get(0).isMediaStreamable();
    }

    private void configureWithJson(JSONObject jSONObject) {
        CTInAppNotificationMedia cTInAppNotificationMediaInitWithJSON;
        CTInAppNotificationMedia cTInAppNotificationMediaInitWithJSON2;
        try {
            this.id = jSONObject.has(Constants.INAPP_ID_IN_PAYLOAD) ? jSONObject.getString(Constants.INAPP_ID_IN_PAYLOAD) : "";
            this.campaignId = jSONObject.has(Constants.NOTIFICATION_ID_TAG) ? jSONObject.getString(Constants.NOTIFICATION_ID_TAG) : "";
            this.type = jSONObject.getString("type");
            this.isLocalInApp = jSONObject.has(CTLocalInApp.IS_LOCAL_INAPP) && jSONObject.getBoolean(CTLocalInApp.IS_LOCAL_INAPP);
            this.fallBackToNotificationSettings = jSONObject.has(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS) && jSONObject.getBoolean(CTLocalInApp.FALLBACK_TO_NOTIFICATION_SETTINGS);
            this.excludeFromCaps = jSONObject.optInt(Constants.KEY_EFC, -1) == 1 || jSONObject.optInt(Constants.KEY_EXCLUDE_GLOBAL_CAPS, -1) == 1;
            this.totalLifetimeCount = jSONObject.has(Constants.KEY_TLC) ? jSONObject.getInt(Constants.KEY_TLC) : -1;
            this.totalDailyCount = jSONObject.has(Constants.KEY_TDC) ? jSONObject.getInt(Constants.KEY_TDC) : -1;
            this.maxPerSession = jSONObject.has(Constants.INAPP_MAX_DISPLAY_COUNT) ? jSONObject.getInt(Constants.INAPP_MAX_DISPLAY_COUNT) : -1;
            this.inAppType = CTInAppType.fromString(this.type);
            this.isTablet = jSONObject.has(Constants.KEY_IS_TABLET) && jSONObject.getBoolean(Constants.KEY_IS_TABLET);
            this.backgroundColor = jSONObject.has(Constants.KEY_BG) ? jSONObject.getString(Constants.KEY_BG) : Constants.WHITE;
            this.isPortrait = !jSONObject.has(Constants.KEY_PORTRAIT) || jSONObject.getBoolean(Constants.KEY_PORTRAIT);
            this.isLandscape = jSONObject.has(Constants.KEY_LANDSCAPE) && jSONObject.getBoolean(Constants.KEY_LANDSCAPE);
            this.timeToLive = jSONObject.has("wzrk_ttl") ? jSONObject.getLong("wzrk_ttl") : System.currentTimeMillis() + 172800000;
            JSONObject jSONObject2 = jSONObject.has("title") ? jSONObject.getJSONObject("title") : null;
            if (jSONObject2 != null) {
                this.title = jSONObject2.has("text") ? jSONObject2.getString("text") : "";
                this.titleColor = jSONObject2.has("color") ? jSONObject2.getString("color") : Constants.BLACK;
            }
            JSONObject jSONObject3 = jSONObject.has("message") ? jSONObject.getJSONObject("message") : null;
            if (jSONObject3 != null) {
                this.message = jSONObject3.has("text") ? jSONObject3.getString("text") : "";
                this.messageColor = jSONObject3.has("color") ? jSONObject3.getString("color") : Constants.BLACK;
            }
            this.hideCloseButton = jSONObject.has(Constants.KEY_HIDE_CLOSE) && jSONObject.getBoolean(Constants.KEY_HIDE_CLOSE);
            JSONObject jSONObject4 = jSONObject.has(Constants.KEY_MEDIA) ? jSONObject.getJSONObject(Constants.KEY_MEDIA) : null;
            if (jSONObject4 != null && (cTInAppNotificationMediaInitWithJSON2 = new CTInAppNotificationMedia().initWithJSON(jSONObject4, 1)) != null) {
                this.mediaList.add(cTInAppNotificationMediaInitWithJSON2);
            }
            JSONObject jSONObject5 = jSONObject.has(Constants.KEY_MEDIA_LANDSCAPE) ? jSONObject.getJSONObject(Constants.KEY_MEDIA_LANDSCAPE) : null;
            if (jSONObject5 != null && (cTInAppNotificationMediaInitWithJSON = new CTInAppNotificationMedia().initWithJSON(jSONObject5, 2)) != null) {
                this.mediaList.add(cTInAppNotificationMediaInitWithJSON);
            }
            JSONArray jSONArray = jSONObject.has(Constants.KEY_BUTTONS) ? jSONObject.getJSONArray(Constants.KEY_BUTTONS) : null;
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    CTInAppNotificationButton cTInAppNotificationButtonInitWithJSON = new CTInAppNotificationButton().initWithJSON(jSONArray.getJSONObject(i));
                    if (cTInAppNotificationButtonInitWithJSON != null && cTInAppNotificationButtonInitWithJSON.getError() == null) {
                        this.buttons.add(cTInAppNotificationButtonInitWithJSON);
                        this.buttonCount++;
                    }
                }
            }
            this.customTemplateData = CustomTemplateInAppData.createFromJson(jSONObject);
            switch (AnonymousClass2.$SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[this.inAppType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    Iterator<CTInAppNotificationMedia> it = this.mediaList.iterator();
                    while (it.hasNext()) {
                        CTInAppNotificationMedia next = it.next();
                        if (next.isGIF() || next.isAudio() || next.isVideo()) {
                            next.setMediaUrl(null);
                            Logger.d("Unable to download to media. Wrong media type for template");
                        }
                    }
                    break;
                case 5:
                case 6:
                case 7:
                    if (this.mediaList.isEmpty()) {
                        this.error = "No media type for template";
                        break;
                    } else {
                        Iterator<CTInAppNotificationMedia> it2 = this.mediaList.iterator();
                        while (it2.hasNext()) {
                            CTInAppNotificationMedia next2 = it2.next();
                            if (next2.isGIF() || next2.isAudio() || next2.isVideo() || !next2.isImage()) {
                                this.error = "Wrong media type for template";
                            }
                        }
                        break;
                    }
                    break;
            }
        } catch (JSONException e) {
            this.error = "Invalid JSON" + e.getLocalizedMessage();
        }
    }

    /* renamed from: com.clevertap.android.sdk.inapp.CTInAppNotification$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType;

        static {
            int[] iArr = new int[CTInAppType.values().length];
            $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType = iArr;
            try {
                iArr[CTInAppType.CTInAppTypeFooter.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHeader.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeCover.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHalfInterstitial.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeCoverImageOnly.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeHalfInterstitialImageOnly.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inapp$CTInAppType[CTInAppType.CTInAppTypeInterstitialImageOnly.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private boolean isKeyValid(Bundle bundle, String str, Class<?> cls) {
        return bundle.containsKey(str) && bundle.get(str).getClass().equals(cls);
    }

    /* JADX WARN: Removed duplicated region for block: B:116:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0074 A[Catch: JSONException -> 0x01c5, TryCatch #0 {JSONException -> 0x01c5, blocks: (B:6:0x0034, B:9:0x003c, B:11:0x0043, B:13:0x004b, B:15:0x0052, B:17:0x005e, B:23:0x006c, B:25:0x0074, B:27:0x007a, B:29:0x0082, B:31:0x0088, B:33:0x0090, B:37:0x0098, B:39:0x00a0, B:41:0x00b0, B:43:0x00b9, B:46:0x00c1, B:48:0x00cf, B:49:0x00d3, B:51:0x00dd, B:52:0x00e1, B:54:0x00e5, B:55:0x00ec, B:57:0x00f5, B:59:0x011a, B:61:0x0120, B:63:0x0128, B:65:0x012e, B:67:0x0136, B:69:0x013c, B:71:0x0147, B:73:0x0150, B:75:0x015b, B:76:0x0162, B:77:0x0164, B:79:0x0168, B:81:0x0172, B:83:0x0176, B:85:0x017a, B:88:0x0183, B:90:0x0187, B:92:0x018b, B:95:0x0196, B:97:0x019a, B:99:0x01a0, B:101:0x01a7, B:103:0x01ab, B:105:0x01af, B:107:0x01b6, B:109:0x01ba, B:111:0x01c0, B:40:0x00a5), top: B:115:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0082 A[Catch: JSONException -> 0x01c5, TryCatch #0 {JSONException -> 0x01c5, blocks: (B:6:0x0034, B:9:0x003c, B:11:0x0043, B:13:0x004b, B:15:0x0052, B:17:0x005e, B:23:0x006c, B:25:0x0074, B:27:0x007a, B:29:0x0082, B:31:0x0088, B:33:0x0090, B:37:0x0098, B:39:0x00a0, B:41:0x00b0, B:43:0x00b9, B:46:0x00c1, B:48:0x00cf, B:49:0x00d3, B:51:0x00dd, B:52:0x00e1, B:54:0x00e5, B:55:0x00ec, B:57:0x00f5, B:59:0x011a, B:61:0x0120, B:63:0x0128, B:65:0x012e, B:67:0x0136, B:69:0x013c, B:71:0x0147, B:73:0x0150, B:75:0x015b, B:76:0x0162, B:77:0x0164, B:79:0x0168, B:81:0x0172, B:83:0x0176, B:85:0x017a, B:88:0x0183, B:90:0x0187, B:92:0x018b, B:95:0x0196, B:97:0x019a, B:99:0x01a0, B:101:0x01a7, B:103:0x01ab, B:105:0x01af, B:107:0x01b6, B:109:0x01ba, B:111:0x01c0, B:40:0x00a5), top: B:115:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0 A[Catch: JSONException -> 0x01c5, TryCatch #0 {JSONException -> 0x01c5, blocks: (B:6:0x0034, B:9:0x003c, B:11:0x0043, B:13:0x004b, B:15:0x0052, B:17:0x005e, B:23:0x006c, B:25:0x0074, B:27:0x007a, B:29:0x0082, B:31:0x0088, B:33:0x0090, B:37:0x0098, B:39:0x00a0, B:41:0x00b0, B:43:0x00b9, B:46:0x00c1, B:48:0x00cf, B:49:0x00d3, B:51:0x00dd, B:52:0x00e1, B:54:0x00e5, B:55:0x00ec, B:57:0x00f5, B:59:0x011a, B:61:0x0120, B:63:0x0128, B:65:0x012e, B:67:0x0136, B:69:0x013c, B:71:0x0147, B:73:0x0150, B:75:0x015b, B:76:0x0162, B:77:0x0164, B:79:0x0168, B:81:0x0172, B:83:0x0176, B:85:0x017a, B:88:0x0183, B:90:0x0187, B:92:0x018b, B:95:0x0196, B:97:0x019a, B:99:0x01a0, B:101:0x01a7, B:103:0x01ab, B:105:0x01af, B:107:0x01b6, B:109:0x01ba, B:111:0x01c0, B:40:0x00a5), top: B:115:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a5 A[Catch: JSONException -> 0x01c5, TryCatch #0 {JSONException -> 0x01c5, blocks: (B:6:0x0034, B:9:0x003c, B:11:0x0043, B:13:0x004b, B:15:0x0052, B:17:0x005e, B:23:0x006c, B:25:0x0074, B:27:0x007a, B:29:0x0082, B:31:0x0088, B:33:0x0090, B:37:0x0098, B:39:0x00a0, B:41:0x00b0, B:43:0x00b9, B:46:0x00c1, B:48:0x00cf, B:49:0x00d3, B:51:0x00dd, B:52:0x00e1, B:54:0x00e5, B:55:0x00ec, B:57:0x00f5, B:59:0x011a, B:61:0x0120, B:63:0x0128, B:65:0x012e, B:67:0x0136, B:69:0x013c, B:71:0x0147, B:73:0x0150, B:75:0x015b, B:76:0x0162, B:77:0x0164, B:79:0x0168, B:81:0x0172, B:83:0x0176, B:85:0x017a, B:88:0x0183, B:90:0x0187, B:92:0x018b, B:95:0x0196, B:97:0x019a, B:99:0x01a0, B:101:0x01a7, B:103:0x01ab, B:105:0x01af, B:107:0x01b6, B:109:0x01ba, B:111:0x01c0, B:40:0x00a5), top: B:115:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b9 A[Catch: JSONException -> 0x01c5, TryCatch #0 {JSONException -> 0x01c5, blocks: (B:6:0x0034, B:9:0x003c, B:11:0x0043, B:13:0x004b, B:15:0x0052, B:17:0x005e, B:23:0x006c, B:25:0x0074, B:27:0x007a, B:29:0x0082, B:31:0x0088, B:33:0x0090, B:37:0x0098, B:39:0x00a0, B:41:0x00b0, B:43:0x00b9, B:46:0x00c1, B:48:0x00cf, B:49:0x00d3, B:51:0x00dd, B:52:0x00e1, B:54:0x00e5, B:55:0x00ec, B:57:0x00f5, B:59:0x011a, B:61:0x0120, B:63:0x0128, B:65:0x012e, B:67:0x0136, B:69:0x013c, B:71:0x0147, B:73:0x0150, B:75:0x015b, B:76:0x0162, B:77:0x0164, B:79:0x0168, B:81:0x0172, B:83:0x0176, B:85:0x017a, B:88:0x0183, B:90:0x0187, B:92:0x018b, B:95:0x0196, B:97:0x019a, B:99:0x01a0, B:101:0x01a7, B:103:0x01ab, B:105:0x01af, B:107:0x01b6, B:109:0x01ba, B:111:0x01c0, B:40:0x00a5), top: B:115:0x0034 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c1 A[Catch: JSONException -> 0x01c5, TryCatch #0 {JSONException -> 0x01c5, blocks: (B:6:0x0034, B:9:0x003c, B:11:0x0043, B:13:0x004b, B:15:0x0052, B:17:0x005e, B:23:0x006c, B:25:0x0074, B:27:0x007a, B:29:0x0082, B:31:0x0088, B:33:0x0090, B:37:0x0098, B:39:0x00a0, B:41:0x00b0, B:43:0x00b9, B:46:0x00c1, B:48:0x00cf, B:49:0x00d3, B:51:0x00dd, B:52:0x00e1, B:54:0x00e5, B:55:0x00ec, B:57:0x00f5, B:59:0x011a, B:61:0x0120, B:63:0x0128, B:65:0x012e, B:67:0x0136, B:69:0x013c, B:71:0x0147, B:73:0x0150, B:75:0x015b, B:76:0x0162, B:77:0x0164, B:79:0x0168, B:81:0x0172, B:83:0x0176, B:85:0x017a, B:88:0x0183, B:90:0x0187, B:92:0x018b, B:95:0x0196, B:97:0x019a, B:99:0x01a0, B:101:0x01a7, B:103:0x01ab, B:105:0x01af, B:107:0x01b6, B:109:0x01ba, B:111:0x01c0, B:40:0x00a5), top: B:115:0x0034 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void legacyConfigureWithJson(org.json.JSONObject r18) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 456
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inapp.CTInAppNotification.legacyConfigureWithJson(org.json.JSONObject):void");
    }

    private boolean validateNotifBundle(Bundle bundle) {
        try {
            Bundle bundle2 = bundle.getBundle(Constants.INAPP_WINDOW);
            Bundle bundle3 = bundle.getBundle("d");
            if (bundle2 == null || bundle3 == null || !(isKeyValid(bundle2, Constants.INAPP_X_DP, Integer.class) || isKeyValid(bundle2, Constants.INAPP_X_PERCENT, Integer.class))) {
                return false;
            }
            if ((isKeyValid(bundle2, Constants.INAPP_Y_DP, Integer.class) || isKeyValid(bundle2, Constants.INAPP_Y_PERCENT, Integer.class)) && isKeyValid(bundle2, Constants.INAPP_NOTIF_DARKEN_SCREEN, Boolean.class) && isKeyValid(bundle2, Constants.INAPP_NOTIF_SHOW_CLOSE, Boolean.class) && isKeyValid(bundle3, Constants.INAPP_HTML_TAG, String.class) && isKeyValid(bundle2, Constants.INAPP_POSITION, String.class)) {
                char cCharAt = bundle2.getString(Constants.INAPP_POSITION).charAt(0);
                return cCharAt == 'b' || cCharAt == 'c' || cCharAt == 'l' || cCharAt == 'r' || cCharAt == 't';
            }
            return false;
        } catch (Throwable th) {
            Logger.v("Failed to parse in-app notification!", th);
            return false;
        }
    }

    private static Bundle getBundleFromJsonObject(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof String) {
                    bundle.putString(next, (String) obj);
                } else if (obj instanceof Character) {
                    bundle.putChar(next, ((Character) obj).charValue());
                } else if (obj instanceof Integer) {
                    bundle.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(next, ((Float) obj).floatValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(next, ((Long) obj).longValue());
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof JSONObject) {
                    bundle.putBundle(next, getBundleFromJsonObject((JSONObject) obj));
                }
            } catch (JSONException unused) {
                Logger.v("Key had unknown object. Discarding");
            }
        }
        return bundle;
    }
}
