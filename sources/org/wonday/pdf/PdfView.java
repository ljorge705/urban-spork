package org.wonday.pdf;

import android.graphics.Canvas;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import com.github.barteksc.pdfviewer.util.Constants;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.gson.Gson;
import com.shockwave.pdfium.util.SizeF;
import java.io.File;
import java.io.FileNotFoundException;

/* loaded from: classes7.dex */
public class PdfView extends PDFView implements OnPageChangeListener, OnLoadCompleteListener, OnErrorListener, OnTapListener, OnDrawListener, OnPageScrollListener, LinkHandler {
    private static PdfView instance;
    private String asset;
    private boolean autoSpacing;
    private ThemedReactContext context;
    private boolean enableAnnotationRendering;
    private boolean enableAntialiasing;
    private boolean enablePaging;
    private FitPolicy fitPolicy;
    private boolean horizontal;
    private float lastPageHeight;
    private float lastPageWidth;
    private float maxScale;
    private float minScale;
    private int oldH;
    private int oldW;
    private float originalWidth;
    private int page;
    private boolean pageFling;
    private boolean pageSnap;
    private String password;
    private String path;
    private float scale;
    private boolean singlePage;
    private int spacing;

    public void setEnableAnnotationRendering(boolean z) {
        this.enableAnnotationRendering = z;
    }

    public void setEnableAntialiasing(boolean z) {
        this.enableAntialiasing = z;
    }

    public void setEnablePaging(boolean z) {
        this.enablePaging = z;
        if (z) {
            this.autoSpacing = true;
            this.pageFling = true;
            this.pageSnap = true;
        } else {
            this.autoSpacing = false;
            this.pageFling = false;
            this.pageSnap = false;
        }
    }

    public void setHorizontal(boolean z) {
        this.horizontal = z;
    }

    public void setMaxScale(float f) {
        this.maxScale = f;
    }

    public void setMinScale(float f) {
        this.minScale = f;
    }

    public void setPage(int i) {
        if (i <= 1) {
            i = 1;
        }
        this.page = i;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setScale(float f) {
        this.scale = f;
    }

    public void setSinglePage(boolean z) {
        this.singlePage = z;
    }

    public void setSpacing(int i) {
        this.spacing = i;
    }

    public PdfView(ThemedReactContext themedReactContext, AttributeSet attributeSet) {
        super(themedReactContext, attributeSet);
        this.page = 1;
        this.horizontal = false;
        this.scale = 1.0f;
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.spacing = 10;
        this.password = "";
        this.enableAntialiasing = true;
        this.enableAnnotationRendering = true;
        this.enablePaging = false;
        this.autoSpacing = false;
        this.pageFling = false;
        this.pageSnap = false;
        this.fitPolicy = FitPolicy.WIDTH;
        this.singlePage = false;
        this.originalWidth = 0.0f;
        this.lastPageWidth = 0.0f;
        this.lastPageHeight = 0.0f;
        this.oldW = 0;
        this.oldH = 0;
        this.context = themedReactContext;
        instance = this;
    }

    @Override // com.github.barteksc.pdfviewer.listener.OnPageChangeListener
    public void onPageChanged(int i, int i2) {
        int i3 = i + 1;
        this.page = i3;
        showLog(String.format("%s %s / %s", this.path, Integer.valueOf(i3), Integer.valueOf(i2)));
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("message", "pageChanged|" + i3 + "|" + i2);
        ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "topChange", writableMapCreateMap);
    }

    @Override // com.github.barteksc.pdfviewer.PDFView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if ((i <= 0 || i2 <= 0) && this.oldW <= 0 && this.oldH <= 0) {
            return;
        }
        super.onSizeChanged(i, i2, this.oldW, this.oldH);
        this.oldW = i;
        this.oldH = i2;
    }

    @Override // com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
    public void loadComplete(int i) {
        SizeF pageSize = getPageSize(0);
        float width = pageSize.getWidth();
        float height = pageSize.getHeight();
        zoomTo(this.scale);
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("message", "loadComplete|" + i + "|" + width + "|" + height + "|" + new Gson().toJson(getTableOfContents()));
        ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "topChange", writableMapCreateMap);
    }

    @Override // com.github.barteksc.pdfviewer.listener.OnErrorListener
    public void onError(Throwable th) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (th.getMessage().contains("Password required or incorrect password")) {
            writableMapCreateMap.putString("message", "error|Password required or incorrect password.");
        } else {
            writableMapCreateMap.putString("message", "error|" + th.getMessage());
        }
        ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "topChange", writableMapCreateMap);
    }

    @Override // com.github.barteksc.pdfviewer.listener.OnPageScrollListener
    public void onPageScrolled(int i, float f) {
        Constants.Pinch.MINIMUM_ZOOM = this.minScale;
        Constants.Pinch.MAXIMUM_ZOOM = this.maxScale;
    }

    @Override // com.github.barteksc.pdfviewer.listener.OnTapListener
    public boolean onTap(MotionEvent motionEvent) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("message", "pageSingleTap|" + this.page + "|" + motionEvent.getX() + "|" + motionEvent.getY());
        ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "topChange", writableMapCreateMap);
        return true;
    }

    @Override // com.github.barteksc.pdfviewer.listener.OnDrawListener
    public void onLayerDrawn(Canvas canvas, float f, float f2, int i) {
        if (this.originalWidth == 0.0f) {
            this.originalWidth = f;
        }
        float f3 = this.lastPageWidth;
        if (f3 > 0.0f) {
            float f4 = this.lastPageHeight;
            if (f4 > 0.0f && (f != f3 || f2 != f4)) {
                Constants.Pinch.MINIMUM_ZOOM = this.minScale;
                Constants.Pinch.MAXIMUM_ZOOM = this.maxScale;
                WritableMap writableMapCreateMap = Arguments.createMap();
                writableMapCreateMap.putString("message", "scaleChanged|" + (f / this.originalWidth));
                ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "topChange", writableMapCreateMap);
            }
        }
        this.lastPageWidth = f;
        this.lastPageHeight = f2;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isRecycled()) {
            drawPdf();
        }
    }

    public void drawPdf() {
        PDFView.Configurator configuratorFromUri;
        showLog(String.format("drawPdf path:%s %s", this.path, Integer.valueOf(this.page)));
        if (this.path != null) {
            setMinZoom(this.minScale);
            setMaxZoom(this.maxScale);
            setMidZoom((this.maxScale + this.minScale) / 2.0f);
            Constants.Pinch.MINIMUM_ZOOM = this.minScale;
            Constants.Pinch.MAXIMUM_ZOOM = this.maxScale;
            if (this.path.startsWith(ReactNativeBlobUtilConst.FILE_PREFIX_CONTENT)) {
                try {
                    configuratorFromUri = fromStream(getContext().getContentResolver().openInputStream(Uri.parse(this.path)));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e.getMessage());
                }
            } else {
                configuratorFromUri = fromUri(getURI(this.path));
            }
            configuratorFromUri.defaultPage(this.page - 1).swipeHorizontal(this.horizontal).onPageChange(this).onLoad(this).onError(this).onDraw(this).onPageScroll(this).spacing(this.spacing).password(this.password).enableAntialiasing(this.enableAntialiasing).pageFitPolicy(this.fitPolicy).pageSnap(this.pageSnap).autoSpacing(this.autoSpacing).pageFling(this.pageFling).enableSwipe(!this.singlePage).enableDoubletap(!this.singlePage).enableAnnotationRendering(this.enableAnnotationRendering).linkHandler(this);
            if (this.singlePage) {
                configuratorFromUri.pages(this.page - 1);
                setTouchesEnabled(false);
            } else {
                configuratorFromUri.onTap(this);
            }
            configuratorFromUri.load();
        }
    }

    public void setFitPolicy(int i) {
        if (i == 0) {
            this.fitPolicy = FitPolicy.WIDTH;
        } else if (i == 1) {
            this.fitPolicy = FitPolicy.HEIGHT;
        } else {
            this.fitPolicy = FitPolicy.BOTH;
        }
    }

    @Override // com.github.barteksc.pdfviewer.link.LinkHandler
    public void handleLinkEvent(LinkTapEvent linkTapEvent) {
        String uri = linkTapEvent.getLink().getUri();
        Integer destPageIdx = linkTapEvent.getLink().getDestPageIdx();
        if (uri != null && !uri.isEmpty()) {
            handleUri(uri);
        } else if (destPageIdx != null) {
            handlePage(destPageIdx.intValue());
        }
    }

    private void handleUri(String str) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("message", "linkPressed|" + str);
        ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "topChange", writableMapCreateMap);
    }

    private void handlePage(int i) {
        jumpTo(i);
    }

    private void showLog(String str) {
        Log.d("PdfView", str);
    }

    private Uri getURI(String str) {
        Uri uri = Uri.parse(str);
        return (uri.getScheme() == null || uri.getScheme().isEmpty()) ? Uri.fromFile(new File(str)) : uri;
    }

    private void setTouchesEnabled(boolean z) {
        setTouchesEnabled(this, z);
    }

    private static void setTouchesEnabled(View view, boolean z) {
        if (z) {
            view.setOnTouchListener(null);
        } else {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: org.wonday.pdf.PdfView.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return true;
                }
            });
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setTouchesEnabled(viewGroup.getChildAt(i), z);
            }
        }
    }
}
