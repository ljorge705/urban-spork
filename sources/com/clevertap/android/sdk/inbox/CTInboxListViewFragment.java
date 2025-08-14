package com.clevertap.android.sdk.inbox;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DidClickForHardPermissionListener;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.customviews.MediaPlayerRecyclerView;
import com.clevertap.android.sdk.customviews.VerticalSpaceItemDecoration;
import com.clevertap.android.sdk.video.VideoLibChecker;
import com.facebook.react.uimanager.ViewProps;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CTInboxListViewFragment extends Fragment {
    CleverTapInstanceConfig config;
    private DidClickForHardPermissionListener didClickForHardPermissionListener;
    private CTInboxMessageAdapter inboxMessageAdapter;
    LinearLayout linearLayout;
    private WeakReference<InboxListener> listenerWeakReference;
    MediaPlayerRecyclerView mediaRecyclerView;
    RecyclerView recyclerView;
    CTInboxStyleConfig styleConfig;
    private int tabPosition;
    boolean haveVideoPlayerSupport = VideoLibChecker.haveVideoPlayerSupport;
    ArrayList<CTInboxMessage> inboxMessages = new ArrayList<>();
    private boolean firstTime = true;

    interface InboxListener {
        void messageDidClick(Context context, int i, CTInboxMessage cTInboxMessage, Bundle bundle, HashMap<String, String> map, int i2);

        void messageDidShow(Context context, CTInboxMessage cTInboxMessage, Bundle bundle);
    }

    private boolean shouldAutoPlayOnFirstLaunch() {
        return this.tabPosition <= 0;
    }

    MediaPlayerRecyclerView getMediaRecyclerView() {
        return this.mediaRecyclerView;
    }

    void setMediaRecyclerView(MediaPlayerRecyclerView mediaPlayerRecyclerView) {
        this.mediaRecyclerView = mediaPlayerRecyclerView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.config = (CleverTapInstanceConfig) arguments.getParcelable("config");
            this.styleConfig = (CTInboxStyleConfig) arguments.getParcelable("styleConfig");
            this.tabPosition = arguments.getInt(ViewProps.POSITION, -1);
            updateInboxMessages();
            if (context instanceof CTInboxActivity) {
                setListener((InboxListener) getActivity());
            }
            if (context instanceof DidClickForHardPermissionListener) {
                this.didClickForHardPermissionListener = (DidClickForHardPermissionListener) context;
            }
        }
    }

    private void updateInboxMessages() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        String string = arguments.getString("filter", null);
        CleverTapAPI cleverTapAPIInstanceWithConfig = CleverTapAPI.instanceWithConfig(getActivity(), this.config);
        if (cleverTapAPIInstanceWithConfig != null) {
            Logger.v("CTInboxListViewFragment:onAttach() called with: tabPosition = [" + this.tabPosition + "], filter = [" + string + "]");
            ArrayList<CTInboxMessage> allInboxMessages = cleverTapAPIInstanceWithConfig.getAllInboxMessages();
            if (string != null) {
                allInboxMessages = filterMessages(allInboxMessages, string);
            }
            this.inboxMessages = allInboxMessages;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.inbox_list_view, viewGroup, false);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R.id.list_view_linear_layout);
        this.linearLayout = linearLayout;
        linearLayout.setBackgroundColor(Color.parseColor(this.styleConfig.getInboxBackgroundColor()));
        TextView textView = (TextView) viewInflate.findViewById(R.id.list_view_no_message_view);
        if (this.inboxMessages.size() <= 0) {
            textView.setVisibility(0);
            textView.setText(this.styleConfig.getNoMessageViewText());
            textView.setTextColor(Color.parseColor(this.styleConfig.getNoMessageViewTextColor()));
            return viewInflate;
        }
        textView.setVisibility(8);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.inboxMessageAdapter = new CTInboxMessageAdapter(this.inboxMessages, this);
        if (this.haveVideoPlayerSupport) {
            MediaPlayerRecyclerView mediaPlayerRecyclerView = new MediaPlayerRecyclerView(getActivity());
            this.mediaRecyclerView = mediaPlayerRecyclerView;
            mediaPlayerRecyclerView.setVisibility(0);
            this.mediaRecyclerView.setLayoutManager(linearLayoutManager);
            this.mediaRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(18));
            this.mediaRecyclerView.setItemAnimator(new DefaultItemAnimator());
            this.mediaRecyclerView.setAdapter(this.inboxMessageAdapter);
            this.inboxMessageAdapter.notifyDataSetChanged();
            this.linearLayout.addView(this.mediaRecyclerView);
            if (this.firstTime && shouldAutoPlayOnFirstLaunch()) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.clevertap.android.sdk.inbox.CTInboxListViewFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CTInboxListViewFragment.this.mediaRecyclerView.playVideo();
                    }
                }, 1000L);
                this.firstTime = false;
            }
        } else {
            RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.list_view_recycler_view);
            this.recyclerView = recyclerView;
            recyclerView.setVisibility(0);
            this.recyclerView.setLayoutManager(linearLayoutManager);
            this.recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(18));
            this.recyclerView.setItemAnimator(new DefaultItemAnimator());
            this.recyclerView.setAdapter(this.inboxMessageAdapter);
            this.inboxMessageAdapter.notifyDataSetChanged();
        }
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("recyclerLayoutState");
            MediaPlayerRecyclerView mediaPlayerRecyclerView = this.mediaRecyclerView;
            if (mediaPlayerRecyclerView != null && mediaPlayerRecyclerView.getLayoutManager() != null) {
                this.mediaRecyclerView.getLayoutManager().onRestoreInstanceState(parcelable);
            }
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView == null || recyclerView.getLayoutManager() == null) {
                return;
            }
            this.recyclerView.getLayoutManager().onRestoreInstanceState(parcelable);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MediaPlayerRecyclerView mediaPlayerRecyclerView = this.mediaRecyclerView;
        if (mediaPlayerRecyclerView != null) {
            mediaPlayerRecyclerView.onRestartPlayer();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        MediaPlayerRecyclerView mediaPlayerRecyclerView = this.mediaRecyclerView;
        if (mediaPlayerRecyclerView != null) {
            mediaPlayerRecyclerView.onPausePlayer();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        MediaPlayerRecyclerView mediaPlayerRecyclerView = this.mediaRecyclerView;
        if (mediaPlayerRecyclerView != null && mediaPlayerRecyclerView.getLayoutManager() != null) {
            bundle.putParcelable("recyclerLayoutState", this.mediaRecyclerView.getLayoutManager().onSaveInstanceState());
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null || recyclerView.getLayoutManager() == null) {
            return;
        }
        bundle.putParcelable("recyclerLayoutState", this.recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        MediaPlayerRecyclerView mediaPlayerRecyclerView = this.mediaRecyclerView;
        if (mediaPlayerRecyclerView != null) {
            mediaPlayerRecyclerView.stop();
        }
    }

    void didClick(Bundle bundle, int i, int i2, HashMap<String, String> map, int i3) {
        InboxListener listener = getListener();
        if (listener != null) {
            listener.messageDidClick(getActivity().getBaseContext(), i2, this.inboxMessages.get(i), bundle, map, i3);
        }
    }

    void didShow(Bundle bundle, int i) {
        InboxListener listener = getListener();
        if (listener != null) {
            Logger.v("CTInboxListViewFragment:didShow() called with: data = [" + bundle + "], position = [" + i + "]");
            listener.messageDidShow(getActivity().getBaseContext(), this.inboxMessages.get(i), bundle);
        }
    }

    void fireUrlThroughIntent(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str.replace("\n", "").replace(StringUtils.CR, "")));
            if (getActivity() != null) {
                Utils.setPackageNameFromResolveInfoList(getActivity(), intent);
            }
            startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    InboxListener getListener() {
        InboxListener inboxListener;
        try {
            inboxListener = this.listenerWeakReference.get();
        } catch (Throwable unused) {
            inboxListener = null;
        }
        if (inboxListener == null) {
            Logger.v("InboxListener is null for messages");
        }
        return inboxListener;
    }

    void setListener(InboxListener inboxListener) {
        this.listenerWeakReference = new WeakReference<>(inboxListener);
    }

    void handleClick(int i, int i2, String str, JSONObject jSONObject, HashMap<String, String> map, int i3) {
        try {
            if (jSONObject != null) {
                String linktype = this.inboxMessages.get(i).getInboxMessageContents().get(0).getLinktype(jSONObject);
                if (linktype.equalsIgnoreCase("url")) {
                    String linkUrl = this.inboxMessages.get(i).getInboxMessageContents().get(0).getLinkUrl(jSONObject);
                    if (linkUrl != null) {
                        fireUrlThroughIntent(linkUrl);
                    }
                } else if (linktype.contains(Constants.KEY_REQUEST_FOR_NOTIFICATION_PERMISSION) && this.didClickForHardPermissionListener != null) {
                    this.didClickForHardPermissionListener.didClickForHardPermissionWithFallbackSettings(this.inboxMessages.get(i).getInboxMessageContents().get(0).isFallbackSettingsEnabled(jSONObject));
                }
            } else {
                String actionUrl = this.inboxMessages.get(i).getInboxMessageContents().get(0).getActionUrl();
                if (actionUrl != null) {
                    fireUrlThroughIntent(actionUrl);
                }
            }
            Bundle bundle = new Bundle();
            JSONObject wzrkParams = this.inboxMessages.get(i).getWzrkParams();
            Iterator<String> itKeys = wzrkParams.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next.startsWith(Constants.WZRK_PREFIX)) {
                    bundle.putString(next, wzrkParams.getString(next));
                }
            }
            if (str != null && !str.isEmpty()) {
                bundle.putString(Constants.KEY_C2A, str);
            }
            didClick(bundle, i, i2, map, i3);
        } catch (Throwable th) {
            Logger.d("Error handling notification button click: " + th.getCause());
        }
    }

    void handleViewPagerClick(int i, int i2) {
        try {
            Bundle bundle = new Bundle();
            JSONObject wzrkParams = this.inboxMessages.get(i).getWzrkParams();
            Iterator<String> itKeys = wzrkParams.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next.startsWith(Constants.WZRK_PREFIX)) {
                    bundle.putString(next, wzrkParams.getString(next));
                }
            }
            didClick(bundle, i, i2, null, -1);
            fireUrlThroughIntent(this.inboxMessages.get(i).getInboxMessageContents().get(i2).getActionUrl());
        } catch (Throwable th) {
            Logger.d("Error handling notification button click: " + th.getCause());
        }
    }

    private ArrayList<CTInboxMessage> filterMessages(ArrayList<CTInboxMessage> arrayList, String str) {
        ArrayList<CTInboxMessage> arrayList2 = new ArrayList<>();
        Iterator<CTInboxMessage> it = arrayList.iterator();
        while (it.hasNext()) {
            CTInboxMessage next = it.next();
            if (next.getTags() != null && next.getTags().size() > 0) {
                Iterator<String> it2 = next.getTags().iterator();
                while (it2.hasNext()) {
                    if (it2.next().equalsIgnoreCase(str)) {
                        arrayList2.add(next);
                    }
                }
            }
        }
        return arrayList2;
    }
}
