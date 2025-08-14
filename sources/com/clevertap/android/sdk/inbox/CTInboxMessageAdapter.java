package com.clevertap.android.sdk.inbox;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import java.util.ArrayList;

/* loaded from: classes5.dex */
class CTInboxMessageAdapter extends RecyclerView.Adapter {
    private static final int CAROUSEL = 2;
    private static final int ICON = 1;
    private static final int IMAGE_CAROUSEL = 3;
    private static final int SIMPLE = 0;
    private CTInboxListViewFragment fragment;
    private ArrayList<CTInboxMessage> inboxMessages;

    CTInboxMessageAdapter(ArrayList<CTInboxMessage> arrayList, CTInboxListViewFragment cTInboxListViewFragment) {
        Logger.v("CTInboxMessageAdapter: messages=" + arrayList);
        this.inboxMessages = arrayList;
        this.fragment = cTInboxListViewFragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.inboxMessages.size();
    }

    /* renamed from: com.clevertap.android.sdk.inbox.CTInboxMessageAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$clevertap$android$sdk$inbox$CTInboxMessageType;

        static {
            int[] iArr = new int[CTInboxMessageType.values().length];
            $SwitchMap$com$clevertap$android$sdk$inbox$CTInboxMessageType = iArr;
            try {
                iArr[CTInboxMessageType.SimpleMessage.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inbox$CTInboxMessageType[CTInboxMessageType.IconMessage.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inbox$CTInboxMessageType[CTInboxMessageType.CarouselMessage.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$clevertap$android$sdk$inbox$CTInboxMessageType[CTInboxMessageType.CarouselImageMessage.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$clevertap$android$sdk$inbox$CTInboxMessageType[this.inboxMessages.get(i).getType().ordinal()];
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 != 3) {
            return i2 != 4 ? -1 : 3;
        }
        return 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((CTInboxBaseMessageViewHolder) viewHolder).configureWithMessage(this.inboxMessages.get(i), this.fragment, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CTInboxBaseMessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new CTSimpleMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_simple_message_layout, viewGroup, false));
        }
        if (i == 1) {
            return new CTIconMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_icon_message_layout, viewGroup, false));
        }
        if (i == 2) {
            return new CTCarouselMessageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_carousel_text_layout, viewGroup, false));
        }
        if (i != 3) {
            return null;
        }
        return new CTCarouselImageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inbox_carousel_layout, viewGroup, false));
    }
}
