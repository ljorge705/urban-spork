package com.google.maps.android.data.kml;

import android.graphics.Color;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Style;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/* loaded from: classes2.dex */
public class KmlStyle extends Style {
    private static final int HSV_VALUES = 3;
    private static final int HUE_VALUE = 0;
    private static final int INITIAL_SCALE = 1;
    private String mIconUrl;
    private boolean mFill = true;
    private boolean mOutline = true;
    private String mStyleId = null;
    private final HashMap<String, String> mBalloonOptions = new HashMap<>();
    private final HashSet<String> mStylesSet = new HashSet<>();
    private double mScale = 1.0d;
    float mMarkerColor = 0.0f;
    private boolean mIconRandomColorMode = false;
    private boolean mLineRandomColorMode = false;
    private boolean mPolyRandomColorMode = false;

    public HashMap<String, String> getBalloonOptions() {
        return this.mBalloonOptions;
    }

    public double getIconScale() {
        return this.mScale;
    }

    public String getIconUrl() {
        return this.mIconUrl;
    }

    String getStyleId() {
        return this.mStyleId;
    }

    public boolean hasFill() {
        return this.mFill;
    }

    public boolean hasOutline() {
        return this.mOutline;
    }

    boolean isIconRandomColorMode() {
        return this.mIconRandomColorMode;
    }

    public boolean isLineRandomColorMode() {
        return this.mLineRandomColorMode;
    }

    public boolean isPolyRandomColorMode() {
        return this.mPolyRandomColorMode;
    }

    public void setFill(boolean z) {
        this.mFill = z;
    }

    void setStyleId(String str) {
        this.mStyleId = str;
    }

    KmlStyle() {
    }

    void setInfoWindowText(String str) {
        this.mBalloonOptions.put("text", str);
    }

    public boolean isStyleSet(String str) {
        return this.mStylesSet.contains(str);
    }

    void setIconScale(double d) {
        this.mScale = d;
        this.mStylesSet.add("iconScale");
    }

    public boolean hasBalloonStyle() {
        return this.mBalloonOptions.size() > 0;
    }

    void setOutline(boolean z) {
        this.mOutline = z;
        this.mStylesSet.add("outline");
    }

    void setIconUrl(String str) {
        this.mIconUrl = str;
        this.mStylesSet.add("iconUrl");
    }

    void setFillColor(String str) {
        setPolygonFillColor(Color.parseColor("#" + convertColor(str)));
        this.mStylesSet.add("fillColor");
    }

    void setMarkerColor(String str) {
        this.mMarkerColor = getHueValue(Color.parseColor("#" + convertColor(str)));
        this.mMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(this.mMarkerColor));
        this.mStylesSet.add("markerColor");
    }

    private static float getHueValue(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return fArr[0];
    }

    private static String convertColor(String str) {
        String strTrim = str.trim();
        if (strTrim.length() > 6) {
            return strTrim.substring(0, 2) + strTrim.substring(6, 8) + strTrim.substring(4, 6) + strTrim.substring(2, 4);
        }
        return strTrim.substring(4, 6) + strTrim.substring(2, 4) + strTrim.substring(0, 2);
    }

    void setHeading(float f) {
        setMarkerRotation(f);
        this.mStylesSet.add("heading");
    }

    void setHotSpot(float f, float f2, String str, String str2) {
        setMarkerHotSpot(f, f2, str, str2);
        this.mStylesSet.add("hotSpot");
    }

    void setIconColorMode(String str) {
        this.mIconRandomColorMode = str.equals("random");
        this.mStylesSet.add("iconColorMode");
    }

    void setLineColorMode(String str) {
        this.mLineRandomColorMode = str.equals("random");
        this.mStylesSet.add("lineColorMode");
    }

    void setPolyColorMode(String str) {
        this.mPolyRandomColorMode = str.equals("random");
        this.mStylesSet.add("polyColorMode");
    }

    void setOutlineColor(String str) {
        this.mPolylineOptions.color(Color.parseColor("#" + convertColor(str)));
        this.mPolygonOptions.strokeColor(Color.parseColor("#" + convertColor(str)));
        this.mStylesSet.add("outlineColor");
    }

    void setWidth(Float f) {
        setLineStringWidth(f.floatValue());
        setPolygonStrokeWidth(f.floatValue());
        this.mStylesSet.add("width");
    }

    private static MarkerOptions createMarkerOptions(MarkerOptions markerOptions, boolean z, float f) {
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.rotation(markerOptions.getRotation());
        markerOptions2.anchor(markerOptions.getAnchorU(), markerOptions.getAnchorV());
        if (z) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(getHueValue(computeRandomColor((int) f))));
        }
        markerOptions2.icon(markerOptions.getIcon());
        return markerOptions2;
    }

    private static PolylineOptions createPolylineOptions(PolylineOptions polylineOptions) {
        PolylineOptions polylineOptions2 = new PolylineOptions();
        polylineOptions2.color(polylineOptions.getColor());
        polylineOptions2.width(polylineOptions.getWidth());
        polylineOptions2.clickable(polylineOptions.isClickable());
        return polylineOptions2;
    }

    private static PolygonOptions createPolygonOptions(PolygonOptions polygonOptions, boolean z, boolean z2) {
        float strokeWidth;
        PolygonOptions polygonOptions2 = new PolygonOptions();
        if (z) {
            polygonOptions2.fillColor(polygonOptions.getFillColor());
        }
        if (z2) {
            polygonOptions2.strokeColor(polygonOptions.getStrokeColor());
            strokeWidth = polygonOptions.getStrokeWidth();
        } else {
            strokeWidth = 0.0f;
        }
        polygonOptions2.strokeWidth(strokeWidth);
        polygonOptions2.clickable(polygonOptions.isClickable());
        return polygonOptions2;
    }

    public MarkerOptions getMarkerOptions() {
        return createMarkerOptions(this.mMarkerOptions, isIconRandomColorMode(), this.mMarkerColor);
    }

    public PolylineOptions getPolylineOptions() {
        return createPolylineOptions(this.mPolylineOptions);
    }

    public PolygonOptions getPolygonOptions() {
        return createPolygonOptions(this.mPolygonOptions, this.mFill, this.mOutline);
    }

    public static int computeRandomColor(int i) {
        Random random = new Random();
        int iRed = Color.red(i);
        int iGreen = Color.green(i);
        int iBlue = Color.blue(i);
        if (iRed != 0) {
            iRed = random.nextInt(iRed);
        }
        if (iBlue != 0) {
            iBlue = random.nextInt(iBlue);
        }
        if (iGreen != 0) {
            iGreen = random.nextInt(iGreen);
        }
        return Color.rgb(iRed, iGreen, iBlue);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Style{\n balloon options=");
        sb.append(this.mBalloonOptions);
        sb.append(",\n fill=").append(this.mFill);
        sb.append(",\n outline=").append(this.mOutline);
        sb.append(",\n icon url=").append(this.mIconUrl);
        sb.append(",\n scale=").append(this.mScale);
        sb.append(",\n style id=").append(this.mStyleId);
        sb.append("\n}\n");
        return sb.toString();
    }
}
