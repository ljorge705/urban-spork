package com.clevertap.android.sdk.inbox;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class CTInboxTabAdapter extends FragmentPagerAdapter {
    private final Fragment[] fragmentList;
    private final List<String> fragmentTitleList;

    public CTInboxTabAdapter(FragmentManager fragmentManager, int i) {
        super(fragmentManager, 1);
        this.fragmentTitleList = new ArrayList();
        this.fragmentList = new Fragment[i];
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.fragmentList.length;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragmentList[i];
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.fragmentTitleList.get(i);
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Object objInstantiateItem = super.instantiateItem(viewGroup, i);
        this.fragmentList[i] = (Fragment) objInstantiateItem;
        return objInstantiateItem;
    }

    void addFragment(Fragment fragment, String str, int i) {
        this.fragmentList[i] = fragment;
        this.fragmentTitleList.add(str);
    }
}
