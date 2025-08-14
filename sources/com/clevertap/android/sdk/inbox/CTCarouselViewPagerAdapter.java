package com.clevertap.android.sdk.inbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CTCarouselViewPagerAdapter extends PagerAdapter {
    private final ArrayList<String> carouselImages;
    private final Context context;
    private final CTInboxMessage inboxMessage;
    private LayoutInflater layoutInflater;
    private final LinearLayout.LayoutParams layoutParams;
    private final WeakReference<CTInboxListViewFragment> parentWeakReference;
    private final int row;
    private View view;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    CTCarouselViewPagerAdapter(Context context, CTInboxListViewFragment cTInboxListViewFragment, CTInboxMessage cTInboxMessage, LinearLayout.LayoutParams layoutParams, int i) {
        this.context = context;
        this.parentWeakReference = new WeakReference<>(cTInboxListViewFragment);
        this.carouselImages = cTInboxMessage.getCarouselImages();
        this.layoutParams = layoutParams;
        this.inboxMessage = cTInboxMessage;
        this.row = i;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.carouselImages.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        this.layoutInflater = layoutInflater;
        this.view = layoutInflater.inflate(R.layout.inbox_carousel_image_layout, viewGroup, false);
        try {
            if (this.inboxMessage.getOrientation().equalsIgnoreCase("l")) {
                addImageAndSetClick((ImageView) this.view.findViewById(R.id.imageView), this.view, i, viewGroup);
            } else if (this.inboxMessage.getOrientation().equalsIgnoreCase("p")) {
                addImageAndSetClick((ImageView) this.view.findViewById(R.id.squareImageView), this.view, i, viewGroup);
            }
        } catch (NoClassDefFoundError unused) {
            Logger.d("CleverTap SDK requires Glide dependency. Please refer CleverTap Documentation for more info");
        }
        return this.view;
    }

    void addImageAndSetClick(ImageView imageView, View view, final int i, ViewGroup viewGroup) {
        imageView.setVisibility(0);
        try {
            Glide.with(imageView.getContext()).load(this.carouselImages.get(i)).apply(new RequestOptions().placeholder(Utils.getThumbnailImage(this.context, Constants.IMAGE_PLACEHOLDER)).error(Utils.getThumbnailImage(this.context, Constants.IMAGE_PLACEHOLDER))).into(imageView);
        } catch (NoSuchMethodError unused) {
            Logger.d("CleverTap SDK requires Glide v4.9.0 or above. Please refer CleverTap Documentation for more info");
            Glide.with(imageView.getContext()).load(this.carouselImages.get(i)).into(imageView);
        }
        viewGroup.addView(view, this.layoutParams);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.clevertap.android.sdk.inbox.CTCarouselViewPagerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                CTInboxListViewFragment parent = CTCarouselViewPagerAdapter.this.getParent();
                if (parent != null) {
                    parent.handleViewPagerClick(CTCarouselViewPagerAdapter.this.row, i);
                }
            }
        });
    }

    CTInboxListViewFragment getParent() {
        return this.parentWeakReference.get();
    }
}
