package com.clevertap.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class CTInboxStyleConfig implements Parcelable {
    public static final Parcelable.Creator<CTInboxStyleConfig> CREATOR = new Parcelable.Creator<CTInboxStyleConfig>() { // from class: com.clevertap.android.sdk.CTInboxStyleConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInboxStyleConfig createFromParcel(Parcel parcel) {
            return new CTInboxStyleConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CTInboxStyleConfig[] newArray(int i) {
            return new CTInboxStyleConfig[i];
        }
    };
    private static final int MAX_TABS = 2;
    private String backButtonColor;
    private String firstTabTitle;
    private String inboxBackgroundColor;
    private String navBarColor;
    private String navBarTitle;
    private String navBarTitleColor;
    private String noMessageViewText;
    private String noMessageViewTextColor;
    private String selectedTabColor;
    private String selectedTabIndicatorColor;
    private String tabBackgroundColor;
    private String[] tabs;
    private String unselectedTabColor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBackButtonColor() {
        return this.backButtonColor;
    }

    public String getFirstTabTitle() {
        return this.firstTabTitle;
    }

    public String getInboxBackgroundColor() {
        return this.inboxBackgroundColor;
    }

    public String getNavBarColor() {
        return this.navBarColor;
    }

    public String getNavBarTitle() {
        return this.navBarTitle;
    }

    public String getNavBarTitleColor() {
        return this.navBarTitleColor;
    }

    public String getNoMessageViewText() {
        return this.noMessageViewText;
    }

    public String getNoMessageViewTextColor() {
        return this.noMessageViewTextColor;
    }

    public String getSelectedTabColor() {
        return this.selectedTabColor;
    }

    public String getSelectedTabIndicatorColor() {
        return this.selectedTabIndicatorColor;
    }

    public String getTabBackgroundColor() {
        return this.tabBackgroundColor;
    }

    public String getUnselectedTabColor() {
        return this.unselectedTabColor;
    }

    public void setBackButtonColor(String str) {
        this.backButtonColor = str;
    }

    public void setFirstTabTitle(String str) {
        this.firstTabTitle = str;
    }

    public void setInboxBackgroundColor(String str) {
        this.inboxBackgroundColor = str;
    }

    public void setNavBarColor(String str) {
        this.navBarColor = str;
    }

    public void setNavBarTitle(String str) {
        this.navBarTitle = str;
    }

    public void setNavBarTitleColor(String str) {
        this.navBarTitleColor = str;
    }

    public void setNoMessageViewText(String str) {
        this.noMessageViewText = str;
    }

    public void setNoMessageViewTextColor(String str) {
        this.noMessageViewTextColor = str;
    }

    public void setSelectedTabColor(String str) {
        this.selectedTabColor = str;
    }

    public void setSelectedTabIndicatorColor(String str) {
        this.selectedTabIndicatorColor = str;
    }

    public void setTabBackgroundColor(String str) {
        this.tabBackgroundColor = str;
    }

    public void setUnselectedTabColor(String str) {
        this.unselectedTabColor = str;
    }

    public CTInboxStyleConfig() {
        this.navBarColor = Constants.WHITE;
        this.navBarTitle = "App Inbox";
        this.navBarTitleColor = "#333333";
        this.inboxBackgroundColor = "#D3D4DA";
        this.backButtonColor = "#333333";
        this.selectedTabColor = "#1C84FE";
        this.unselectedTabColor = "#808080";
        this.selectedTabIndicatorColor = "#1C84FE";
        this.tabBackgroundColor = Constants.WHITE;
        this.tabs = new String[0];
        this.noMessageViewText = "No Message(s) to show";
        this.noMessageViewTextColor = Constants.BLACK;
        this.firstTabTitle = "ALL";
    }

    CTInboxStyleConfig(CTInboxStyleConfig cTInboxStyleConfig) {
        this.navBarColor = cTInboxStyleConfig.navBarColor;
        this.navBarTitle = cTInboxStyleConfig.navBarTitle;
        this.navBarTitleColor = cTInboxStyleConfig.navBarTitleColor;
        this.inboxBackgroundColor = cTInboxStyleConfig.inboxBackgroundColor;
        this.backButtonColor = cTInboxStyleConfig.backButtonColor;
        this.selectedTabColor = cTInboxStyleConfig.selectedTabColor;
        this.unselectedTabColor = cTInboxStyleConfig.unselectedTabColor;
        this.selectedTabIndicatorColor = cTInboxStyleConfig.selectedTabIndicatorColor;
        this.tabBackgroundColor = cTInboxStyleConfig.tabBackgroundColor;
        String[] strArr = cTInboxStyleConfig.tabs;
        this.tabs = strArr == null ? new String[0] : (String[]) Arrays.copyOf(strArr, strArr.length);
        this.noMessageViewText = cTInboxStyleConfig.noMessageViewText;
        this.noMessageViewTextColor = cTInboxStyleConfig.noMessageViewTextColor;
        this.firstTabTitle = cTInboxStyleConfig.firstTabTitle;
    }

    protected CTInboxStyleConfig(Parcel parcel) {
        this.navBarColor = parcel.readString();
        this.navBarTitle = parcel.readString();
        this.navBarTitleColor = parcel.readString();
        this.inboxBackgroundColor = parcel.readString();
        this.tabs = parcel.createStringArray();
        this.backButtonColor = parcel.readString();
        this.selectedTabColor = parcel.readString();
        this.unselectedTabColor = parcel.readString();
        this.selectedTabIndicatorColor = parcel.readString();
        this.tabBackgroundColor = parcel.readString();
        this.noMessageViewText = parcel.readString();
        this.noMessageViewTextColor = parcel.readString();
        this.firstTabTitle = parcel.readString();
    }

    public ArrayList<String> getTabs() {
        return this.tabs == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(this.tabs));
    }

    public void setTabs(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        if (arrayList.size() > 2) {
            arrayList = new ArrayList<>(arrayList.subList(0, 2));
        }
        this.tabs = (String[]) arrayList.toArray(new String[0]);
    }

    public boolean isUsingTabs() {
        String[] strArr = this.tabs;
        return strArr != null && strArr.length > 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.navBarColor);
        parcel.writeString(this.navBarTitle);
        parcel.writeString(this.navBarTitleColor);
        parcel.writeString(this.inboxBackgroundColor);
        parcel.writeStringArray(this.tabs);
        parcel.writeString(this.backButtonColor);
        parcel.writeString(this.selectedTabColor);
        parcel.writeString(this.unselectedTabColor);
        parcel.writeString(this.selectedTabIndicatorColor);
        parcel.writeString(this.tabBackgroundColor);
        parcel.writeString(this.noMessageViewText);
        parcel.writeString(this.noMessageViewTextColor);
        parcel.writeString(this.firstTabTitle);
    }
}
