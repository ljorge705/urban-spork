package com.clevertap.android.sdk.inbox;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import com.clevertap.android.sdk.R;

/* loaded from: classes5.dex */
class CTCarouselImageViewHolder extends CTInboxBaseMessageViewHolder {
    private final TextView carouselTimestamp;
    private final RelativeLayout clickLayout;
    private final CTCarouselViewPager imageViewPager;
    private final LinearLayout sliderDots;

    class CarouselPageChangeListener implements ViewPager.OnPageChangeListener {
        private final Context context;
        private final ImageView[] dots;
        private final CTInboxMessage inboxMessage;
        private final CTCarouselImageViewHolder viewHolder;

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        CarouselPageChangeListener(Context context, CTCarouselImageViewHolder cTCarouselImageViewHolder, ImageView[] imageViewArr, CTInboxMessage cTInboxMessage) {
            this.context = context;
            this.viewHolder = cTCarouselImageViewHolder;
            this.dots = imageViewArr;
            this.inboxMessage = cTInboxMessage;
            imageViewArr[0].setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ct_selected_dot, null));
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            for (ImageView imageView : this.dots) {
                imageView.setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.ct_unselected_dot, null));
            }
            this.dots[i].setImageDrawable(ResourcesCompat.getDrawable(this.context.getResources(), R.drawable.ct_selected_dot, null));
        }
    }

    CTCarouselImageViewHolder(View view) {
        super(view);
        this.imageViewPager = (CTCarouselViewPager) view.findViewById(R.id.image_carousel_viewpager);
        this.sliderDots = (LinearLayout) view.findViewById(R.id.sliderDots);
        this.carouselTimestamp = (TextView) view.findViewById(R.id.carousel_timestamp);
        this.clickLayout = (RelativeLayout) view.findViewById(R.id.body_linear_layout);
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder
    void configureWithMessage(CTInboxMessage cTInboxMessage, CTInboxListViewFragment cTInboxListViewFragment, int i) {
        super.configureWithMessage(cTInboxMessage, cTInboxListViewFragment, i);
        CTInboxListViewFragment parent = getParent();
        Context applicationContext = cTInboxListViewFragment.getActivity().getApplicationContext();
        CTInboxMessageContent cTInboxMessageContent = cTInboxMessage.getInboxMessageContents().get(0);
        this.carouselTimestamp.setVisibility(0);
        if (cTInboxMessage.isRead()) {
            this.readDot.setVisibility(8);
        } else {
            this.readDot.setVisibility(0);
        }
        this.carouselTimestamp.setText(calculateDisplayTimestamp(cTInboxMessage.getDate()));
        this.carouselTimestamp.setTextColor(Color.parseColor(cTInboxMessageContent.getTitleColor()));
        this.clickLayout.setBackgroundColor(Color.parseColor(cTInboxMessage.getBgColor()));
        this.imageViewPager.setAdapter(new CTCarouselViewPagerAdapter(applicationContext, cTInboxListViewFragment, cTInboxMessage, (LinearLayout.LayoutParams) this.imageViewPager.getLayoutParams(), i));
        int size = cTInboxMessage.getInboxMessageContents().size();
        if (this.sliderDots.getChildCount() > 0) {
            this.sliderDots.removeAllViews();
        }
        ImageView[] imageViewArr = new ImageView[size];
        setDots(imageViewArr, size, applicationContext, this.sliderDots);
        imageViewArr[0].setImageDrawable(ResourcesCompat.getDrawable(applicationContext.getResources(), R.drawable.ct_selected_dot, null));
        this.imageViewPager.addOnPageChangeListener(new CarouselPageChangeListener(cTInboxListViewFragment.getActivity().getApplicationContext(), this, imageViewArr, cTInboxMessage));
        this.clickLayout.setOnClickListener(new CTInboxButtonClickListener(i, cTInboxMessage, (String) null, parent, (ViewPager) this.imageViewPager, true, -1));
        markItemAsRead(cTInboxMessage, i);
    }
}
