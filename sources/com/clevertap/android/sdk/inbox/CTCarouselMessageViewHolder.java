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
class CTCarouselMessageViewHolder extends CTInboxBaseMessageViewHolder {
    private TextView carouselTimestamp;
    private final RelativeLayout clickLayout;
    private final CTCarouselViewPager imageViewPager;
    private final TextView message;
    private final LinearLayout sliderDots;
    private final TextView timestamp;
    private final TextView title;

    class CarouselPageChangeListener implements ViewPager.OnPageChangeListener {
        private final Context context;
        private final ImageView[] dots;
        private final CTInboxMessage inboxMessage;
        private final CTCarouselMessageViewHolder viewHolder;

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        CarouselPageChangeListener(Context context, CTCarouselMessageViewHolder cTCarouselMessageViewHolder, ImageView[] imageViewArr, CTInboxMessage cTInboxMessage) {
            this.context = context;
            this.viewHolder = cTCarouselMessageViewHolder;
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
            this.viewHolder.title.setText(this.inboxMessage.getInboxMessageContents().get(i).getTitle());
            this.viewHolder.title.setTextColor(Color.parseColor(this.inboxMessage.getInboxMessageContents().get(i).getTitleColor()));
            this.viewHolder.message.setText(this.inboxMessage.getInboxMessageContents().get(i).getMessage());
            this.viewHolder.message.setTextColor(Color.parseColor(this.inboxMessage.getInboxMessageContents().get(i).getMessageColor()));
        }
    }

    CTCarouselMessageViewHolder(View view) {
        super(view);
        this.imageViewPager = (CTCarouselViewPager) view.findViewById(R.id.image_carousel_viewpager);
        this.sliderDots = (LinearLayout) view.findViewById(R.id.sliderDots);
        this.title = (TextView) view.findViewById(R.id.messageTitle);
        this.message = (TextView) view.findViewById(R.id.messageText);
        this.timestamp = (TextView) view.findViewById(R.id.timestamp);
        this.clickLayout = (RelativeLayout) view.findViewById(R.id.body_linear_layout);
    }

    @Override // com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder
    void configureWithMessage(CTInboxMessage cTInboxMessage, CTInboxListViewFragment cTInboxListViewFragment, int i) {
        super.configureWithMessage(cTInboxMessage, cTInboxListViewFragment, i);
        CTInboxListViewFragment parent = getParent();
        Context applicationContext = cTInboxListViewFragment.getActivity().getApplicationContext();
        CTInboxMessageContent cTInboxMessageContent = cTInboxMessage.getInboxMessageContents().get(0);
        this.title.setVisibility(0);
        this.message.setVisibility(0);
        this.title.setText(cTInboxMessageContent.getTitle());
        this.title.setTextColor(Color.parseColor(cTInboxMessageContent.getTitleColor()));
        this.message.setText(cTInboxMessageContent.getMessage());
        this.message.setTextColor(Color.parseColor(cTInboxMessageContent.getMessageColor()));
        if (cTInboxMessage.isRead()) {
            this.readDot.setVisibility(8);
        } else {
            this.readDot.setVisibility(0);
        }
        this.timestamp.setVisibility(0);
        this.timestamp.setText(calculateDisplayTimestamp(cTInboxMessage.getDate()));
        this.timestamp.setTextColor(Color.parseColor(cTInboxMessageContent.getTitleColor()));
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
