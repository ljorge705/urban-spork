package com.singular.sdk;

/* loaded from: classes6.dex */
public enum Events {
    sngRate("sng_rate"),
    sngSpentCredits("sng_spent_credits"),
    sngTutorialComplete("sng_tutorial_complete"),
    sngLogin("sng_login"),
    sngStartTrial("sng_start_trial"),
    sngSubscribe("sng_subscribe"),
    sngBook("sng_book"),
    sngContentViewList("sng_content_view_list"),
    sngInvite("sng_invite"),
    sngShare("sng_share"),
    sngSubmitApplication("sng_submit_application"),
    sngUpdate("sng_update"),
    sngEcommercePurchase("sng_ecommerce_purchase"),
    sngViewCart("sng_view_cart"),
    sngAchievementUnlocked("sng_achievement_unlocked"),
    sngAddPaymentInfo("sng_add_payment_info"),
    sngAddToCart("sng_add_to_cart"),
    sngAddToWishlist("sng_add_to_wishlist"),
    sngCheckoutInitiated("sng_checkout_initiated"),
    sngCompleteRegistration("sng_complete_registration"),
    sngContentView("sng_content_view"),
    sngLevelAchieved("sng_level_achieved"),
    sngSearch("sng_search");

    private final String name;

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }

    Events(String str) {
        this.name = str;
    }

    public boolean equalsName(String str) {
        return this.name.equals(str);
    }
}
