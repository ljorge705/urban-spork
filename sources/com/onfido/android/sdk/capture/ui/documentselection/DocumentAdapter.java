package com.onfido.android.sdk.capture.ui.documentselection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.oblador.keychain.KeychainModule;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.databinding.OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding;
import com.onfido.android.sdk.capture.document.DocumentPages;
import com.onfido.android.sdk.capture.ui.documentselection.DocumentAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0016\u0017B7\u0012&\u0010\u0004\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0005j\u0002`\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0004\u001a\"\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0005j\u0002`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentItem;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentAdapter$DocumentViewHolder;", "onDocumentClick", "Lkotlin/Function3;", "Lcom/onfido/android/sdk/capture/DocumentType;", "", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentClickListener;", "itemFactory", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentDisplayItemFactory;", "(Lkotlin/jvm/functions/Function3;Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentDisplayItemFactory;)V", "onBindViewHolder", "holder", ViewProps.POSITION, "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "DocumentViewHolder", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentAdapter extends ListAdapter<DocumentItem, DocumentViewHolder> {
    private final DocumentDisplayItemFactory itemFactory;
    private final Function3<DocumentType, String, DocumentPages, Unit> onDocumentClick;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class DiffCallback extends DiffUtil.ItemCallback<DocumentItem> {
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(DocumentItem oldItem, DocumentItem newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            DocumentType documentType = oldItem.getDocumentType();
            DocumentType documentType2 = DocumentType.GENERIC;
            return documentType == documentType2 || newItem.getDocumentType() == documentType2 ? oldItem.getDocumentType() == newItem.getDocumentType() && Intrinsics.areEqual(((GenericDocumentItem) oldItem).getTitle(), ((GenericDocumentItem) newItem).getTitle()) : oldItem.getDocumentType() == newItem.getDocumentType();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(DocumentItem oldItem, DocumentItem newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getDocumentType().getId(), newItem.getDocumentType().getId());
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J2\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\"\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u000b0\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentAdapter$DocumentViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/onfido/android/sdk/capture/databinding/OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding;", "(Lcom/onfido/android/sdk/capture/databinding/OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding;)V", Constants.KEY_ICON, "Landroid/widget/ImageView;", KeychainModule.AuthPromptOptions.SUBTITLE, "Landroid/widget/TextView;", "title", "bind", "", com.clevertap.android.sdk.leanplum.Constants.IAP_ITEM_PARAM, "Lcom/onfido/android/sdk/capture/ui/documentselection/DocumentDisplayItem;", "onDocumentClick", "Lkotlin/Function3;", "Lcom/onfido/android/sdk/capture/DocumentType;", "", "Lcom/onfido/android/sdk/capture/document/DocumentPages;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class DocumentViewHolder extends RecyclerView.ViewHolder {
        private final ImageView icon;
        private final TextView subtitle;
        private final TextView title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DocumentViewHolder(OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            ImageView ivDocumentIcon = binding.ivDocumentIcon;
            Intrinsics.checkNotNullExpressionValue(ivDocumentIcon, "ivDocumentIcon");
            this.icon = ivDocumentIcon;
            TextView tvDocumentType = binding.tvDocumentType;
            Intrinsics.checkNotNullExpressionValue(tvDocumentType, "tvDocumentType");
            this.title = tvDocumentType;
            TextView tvDescription = binding.tvDescription;
            Intrinsics.checkNotNullExpressionValue(tvDescription, "tvDescription");
            this.subtitle = tvDescription;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2$lambda$1(Function3 onDocumentClick, DocumentDisplayItem item, String str, DocumentPages documentPages, View view) {
            Intrinsics.checkNotNullParameter(onDocumentClick, "$onDocumentClick");
            Intrinsics.checkNotNullParameter(item, "$item");
            onDocumentClick.invoke(item.getDocumentType(), str, documentPages);
        }

        public final void bind(final DocumentDisplayItem item, final Function3<? super DocumentType, ? super String, ? super DocumentPages, Unit> onDocumentClick) {
            String string;
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(onDocumentClick, "onDocumentClick");
            this.icon.setImageResource(item.getIconRes());
            boolean z = item instanceof GenericDocumentDisplayItem;
            String title = z ? ((GenericDocumentDisplayItem) item).getTitle() : item.getDocumentType().getId();
            TextView textView = this.title;
            if (item.getTitleId() != 0) {
                title = this.itemView.getContext().getString(item.getTitleId());
            }
            textView.setText(title);
            if (!z || (string = ((GenericDocumentDisplayItem) item).getSubtitle()) == null) {
                string = "";
            }
            TextView textView2 = this.subtitle;
            if (item.getSubtitleId() != 0) {
                string = this.itemView.getContext().getString(item.getSubtitleId());
            }
            textView2.setText(string);
            Unit unit = Unit.INSTANCE;
            final DocumentPages genericDocumentPages = z ? ((GenericDocumentDisplayItem) item).getGenericDocumentPages() : null;
            final String title2 = z ? ((GenericDocumentDisplayItem) item).getTitle() : null;
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.onfido.android.sdk.capture.ui.documentselection.DocumentAdapter$DocumentViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DocumentAdapter.DocumentViewHolder.bind$lambda$2$lambda$1(onDocumentClick, item, title2, genericDocumentPages, view);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DocumentAdapter(Function3<? super DocumentType, ? super String, ? super DocumentPages, Unit> onDocumentClick, DocumentDisplayItemFactory itemFactory) {
        super(new DiffCallback());
        Intrinsics.checkNotNullParameter(onDocumentClick, "onDocumentClick");
        Intrinsics.checkNotNullParameter(itemFactory, "itemFactory");
        this.onDocumentClick = onDocumentClick;
        this.itemFactory = itemFactory;
    }

    public /* synthetic */ DocumentAdapter(Function3 function3, DocumentDisplayItemFactory documentDisplayItemFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function3, (i & 2) != 0 ? new DocumentDisplayItemFactoryImpl() : documentDisplayItemFactory);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DocumentViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        DocumentItem item = getItem(position);
        DocumentDisplayItemFactory documentDisplayItemFactory = this.itemFactory;
        Intrinsics.checkNotNull(item);
        holder.bind(documentDisplayItemFactory.getFor(item), this.onDocumentClick);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DocumentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding onfidoItemRestrictedDocumentSelectionDocumentTypeBindingInflate = OnfidoItemRestrictedDocumentSelectionDocumentTypeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(onfidoItemRestrictedDocumentSelectionDocumentTypeBindingInflate, "inflate(...)");
        return new DocumentViewHolder(onfidoItemRestrictedDocumentSelectionDocumentTypeBindingInflate);
    }
}
