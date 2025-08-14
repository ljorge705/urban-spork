package com.google.maps.android.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.R;
import com.google.maps.android.collections.GroundOverlayManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.collections.PolygonManager;
import com.google.maps.android.collections.PolylineManager;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.geojson.BiMultiMap;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonGeometryCollection;
import com.google.maps.android.data.geojson.GeoJsonLineString;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonMultiLineString;
import com.google.maps.android.data.geojson.GeoJsonMultiPoint;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlMultiGeometry;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPolygon;
import com.google.maps.android.data.kml.KmlStyle;
import com.google.maps.android.data.kml.KmlUtil;
import io.sentry.protocol.ViewHierarchyNode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class Renderer {
    private static final Object FEATURE_NOT_ON_MAP = null;
    private static final int MARKER_ICON_SIZE = 32;
    private static final DecimalFormat sScaleFormat = new DecimalFormat("#.####");
    private final BiMultiMap<Feature> mContainerFeatures;
    private ArrayList<KmlContainer> mContainers;
    private Context mContext;
    private final GeoJsonLineStringStyle mDefaultLineStringStyle;
    private final GeoJsonPointStyle mDefaultPointStyle;
    private final GeoJsonPolygonStyle mDefaultPolygonStyle;
    private final BiMultiMap<Feature> mFeatures;
    private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlayMap;
    private final GroundOverlayManager.Collection mGroundOverlays;
    private ImagesCache mImagesCache;
    private boolean mLayerOnMap;
    private GoogleMap mMap;
    private final Set<String> mMarkerIconUrls;
    private final MarkerManager.Collection mMarkers;
    private int mNumActiveDownloads;
    private final PolygonManager.Collection mPolygons;
    private final PolylineManager.Collection mPolylines;
    private HashMap<String, String> mStyleMaps;
    private HashMap<String, KmlStyle> mStyles;
    private HashMap<String, KmlStyle> mStylesRenderer;

    public static final class ImagesCache {
        final Map<String, Map<String, BitmapDescriptor>> markerImagesCache = new HashMap();
        final Map<String, BitmapDescriptor> groundOverlayImagesCache = new HashMap();
        final Map<String, Bitmap> bitmapCache = new HashMap();
    }

    protected void downloadStarted() {
        this.mNumActiveDownloads++;
    }

    protected HashMap<? extends Feature, Object> getAllFeatures() {
        return this.mFeatures;
    }

    protected ArrayList<KmlContainer> getContainerList() {
        return this.mContainers;
    }

    GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mDefaultLineStringStyle;
    }

    GeoJsonPointStyle getDefaultPointStyle() {
        return this.mDefaultPointStyle;
    }

    GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mDefaultPolygonStyle;
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayMap() {
        return this.mGroundOverlayMap;
    }

    public GoogleMap getMap() {
        return this.mMap;
    }

    protected Set<String> getMarkerIconUrls() {
        return this.mMarkerIconUrls;
    }

    protected HashMap<String, String> getStyleMaps() {
        return this.mStyleMaps;
    }

    protected HashMap<String, KmlStyle> getStylesRenderer() {
        return this.mStylesRenderer;
    }

    public boolean isLayerOnMap() {
        return this.mLayerOnMap;
    }

    protected void setLayerVisibility(boolean z) {
        this.mLayerOnMap = z;
    }

    public void setMap(GoogleMap googleMap) {
        this.mMap = googleMap;
    }

    public Renderer(GoogleMap googleMap, Context context, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager, ImagesCache imagesCache) {
        this(googleMap, new HashSet(), null, null, null, new BiMultiMap(), markerManager, polygonManager, polylineManager, groundOverlayManager);
        this.mContext = context;
        this.mStylesRenderer = new HashMap<>();
        this.mImagesCache = imagesCache == null ? new ImagesCache() : imagesCache;
    }

    public Renderer(GoogleMap googleMap, HashMap<? extends Feature, Object> map, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        this(googleMap, null, new GeoJsonPointStyle(), new GeoJsonLineStringStyle(), new GeoJsonPolygonStyle(), null, markerManager, polygonManager, polylineManager, groundOverlayManager);
        this.mFeatures.putAll(map);
        this.mImagesCache = null;
    }

    private Renderer(GoogleMap googleMap, Set<String> set, GeoJsonPointStyle geoJsonPointStyle, GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonPolygonStyle geoJsonPolygonStyle, BiMultiMap<Feature> biMultiMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        this.mFeatures = new BiMultiMap<>();
        this.mNumActiveDownloads = 0;
        this.mMap = googleMap;
        this.mLayerOnMap = false;
        this.mMarkerIconUrls = set;
        this.mDefaultPointStyle = geoJsonPointStyle;
        this.mDefaultLineStringStyle = geoJsonLineStringStyle;
        this.mDefaultPolygonStyle = geoJsonPolygonStyle;
        this.mContainerFeatures = biMultiMap;
        if (googleMap != null) {
            this.mMarkers = (markerManager == null ? new MarkerManager(googleMap) : markerManager).newCollection();
            this.mPolygons = (polygonManager == null ? new PolygonManager(googleMap) : polygonManager).newCollection();
            this.mPolylines = (polylineManager == null ? new PolylineManager(googleMap) : polylineManager).newCollection();
            this.mGroundOverlays = (groundOverlayManager == null ? new GroundOverlayManager(googleMap) : groundOverlayManager).newCollection();
            return;
        }
        this.mMarkers = null;
        this.mPolygons = null;
        this.mPolylines = null;
        this.mGroundOverlays = null;
    }

    protected void putContainerFeature(Object obj, Feature feature) {
        this.mContainerFeatures.put((BiMultiMap<Feature>) feature, obj);
    }

    public Set<Feature> getFeatures() {
        return this.mFeatures.keySet();
    }

    Feature getFeature(Object obj) {
        return this.mFeatures.getKey(obj);
    }

    Feature getContainerFeature(Object obj) {
        BiMultiMap<Feature> biMultiMap = this.mContainerFeatures;
        if (biMultiMap != null) {
            return biMultiMap.getKey(obj);
        }
        return null;
    }

    public Collection<Object> getValues() {
        return this.mFeatures.values();
    }

    protected BitmapDescriptor getCachedMarkerImage(String str, double d) {
        Bitmap bitmap;
        String str2 = sScaleFormat.format(d);
        Map<String, BitmapDescriptor> map = this.mImagesCache.markerImagesCache.get(str);
        BitmapDescriptor bitmapDescriptor = map != null ? map.get(str2) : null;
        if (bitmapDescriptor != null || (bitmap = this.mImagesCache.bitmapCache.get(str)) == null) {
            return bitmapDescriptor;
        }
        BitmapDescriptor bitmapDescriptorScaleIcon = scaleIcon(bitmap, d);
        putMarkerImagesCache(str, str2, bitmapDescriptorScaleIcon);
        return bitmapDescriptorScaleIcon;
    }

    private BitmapDescriptor scaleIcon(Bitmap bitmap, double d) {
        int i;
        int i2 = (int) (this.mContext.getResources().getDisplayMetrics().density * 32.0f * d);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < height) {
            i = (int) ((height * i2) / width);
        } else if (width > height) {
            int i3 = (int) ((width * i2) / height);
            i = i2;
            i2 = i3;
        } else {
            i = i2;
        }
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, i2, i, false));
    }

    protected BitmapDescriptor getCachedGroundOverlayImage(String str) {
        Bitmap bitmap;
        BitmapDescriptor bitmapDescriptor = this.mImagesCache.groundOverlayImagesCache.get(str);
        if (bitmapDescriptor != null || (bitmap = this.mImagesCache.bitmapCache.get(str)) == null) {
            return bitmapDescriptor;
        }
        BitmapDescriptor bitmapDescriptorFromBitmap = BitmapDescriptorFactory.fromBitmap(bitmap);
        this.mImagesCache.groundOverlayImagesCache.put(str, bitmapDescriptorFromBitmap);
        return bitmapDescriptorFromBitmap;
    }

    protected KmlStyle getPlacemarkStyle(String str) {
        return this.mStylesRenderer.get(str) != null ? this.mStylesRenderer.get(str) : this.mStylesRenderer.get(null);
    }

    protected void putFeatures(Feature feature, Object obj) {
        this.mFeatures.put((BiMultiMap<Feature>) feature, obj);
    }

    protected void putStyles() {
        this.mStylesRenderer.putAll(this.mStyles);
    }

    protected void putStyles(HashMap<String, KmlStyle> map) {
        this.mStylesRenderer.putAll(map);
    }

    private void putMarkerImagesCache(String str, String str2, BitmapDescriptor bitmapDescriptor) {
        Map<String, BitmapDescriptor> map = this.mImagesCache.markerImagesCache.get(str);
        if (map == null) {
            map = new HashMap<>();
            this.mImagesCache.markerImagesCache.put(str, map);
        }
        map.put(str2, bitmapDescriptor);
    }

    protected void cacheBitmap(String str, Bitmap bitmap) {
        this.mImagesCache.bitmapCache.put(str, bitmap);
    }

    protected void downloadFinished() {
        this.mNumActiveDownloads--;
        checkClearBitmapCache();
    }

    protected void checkClearBitmapCache() {
        ImagesCache imagesCache;
        if (this.mNumActiveDownloads != 0 || (imagesCache = this.mImagesCache) == null || imagesCache.bitmapCache.isEmpty()) {
            return;
        }
        this.mImagesCache.bitmapCache.clear();
    }

    protected boolean hasFeatures() {
        return this.mFeatures.size() > 0;
    }

    protected void removeFeatures(HashMap<? extends Feature, Object> map) {
        removeFeatures(map.values());
    }

    private void removeFeatures(Collection collection) {
        for (Object obj : collection) {
            if (obj instanceof Collection) {
                removeFeatures((Collection) obj);
            } else if (obj instanceof Marker) {
                this.mMarkers.remove((Marker) obj);
            } else if (obj instanceof Polyline) {
                this.mPolylines.remove((Polyline) obj);
            } else if (obj instanceof Polygon) {
                this.mPolygons.remove((Polygon) obj);
            }
        }
    }

    protected void removeGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> map) {
        for (GroundOverlay groundOverlay : map.values()) {
            if (groundOverlay != null) {
                this.mGroundOverlays.remove(groundOverlay);
            }
        }
    }

    protected void removeFeature(Feature feature) {
        if (this.mFeatures.containsKey(feature)) {
            removeFromMap(this.mFeatures.remove(feature));
        }
    }

    private void setFeatureDefaultStyles(GeoJsonFeature geoJsonFeature) {
        if (geoJsonFeature.getPointStyle() == null) {
            geoJsonFeature.setPointStyle(this.mDefaultPointStyle);
        }
        if (geoJsonFeature.getLineStringStyle() == null) {
            geoJsonFeature.setLineStringStyle(this.mDefaultLineStringStyle);
        }
        if (geoJsonFeature.getPolygonStyle() == null) {
            geoJsonFeature.setPolygonStyle(this.mDefaultPolygonStyle);
        }
    }

    protected void clearStylesRenderer() {
        this.mStylesRenderer.clear();
    }

    protected void storeData(HashMap<String, KmlStyle> map, HashMap<String, String> map2, HashMap<KmlPlacemark, Object> map3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> map4) {
        this.mStyles = map;
        this.mStyleMaps = map2;
        this.mFeatures.putAll(map3);
        this.mContainers = arrayList;
        this.mGroundOverlayMap = map4;
    }

    protected void addFeature(Feature feature) {
        Object objAddGeoJsonFeatureToMap = FEATURE_NOT_ON_MAP;
        if (feature instanceof GeoJsonFeature) {
            setFeatureDefaultStyles((GeoJsonFeature) feature);
        }
        if (this.mLayerOnMap) {
            if (this.mFeatures.containsKey(feature)) {
                removeFromMap(this.mFeatures.get(feature));
            }
            if (feature.hasGeometry()) {
                if (feature instanceof KmlPlacemark) {
                    KmlPlacemark kmlPlacemark = (KmlPlacemark) feature;
                    objAddGeoJsonFeatureToMap = addKmlPlacemarkToMap(kmlPlacemark, feature.getGeometry(), getPlacemarkStyle(feature.getId()), kmlPlacemark.getInlineStyle(), getPlacemarkVisibility(feature));
                } else {
                    objAddGeoJsonFeatureToMap = addGeoJsonFeatureToMap(feature, feature.getGeometry());
                }
            }
        }
        this.mFeatures.put((BiMultiMap<Feature>) feature, objAddGeoJsonFeatureToMap);
    }

    protected void removeFromMap(Object obj) {
        if (obj instanceof Marker) {
            this.mMarkers.remove((Marker) obj);
            return;
        }
        if (obj instanceof Polyline) {
            this.mPolylines.remove((Polyline) obj);
            return;
        }
        if (obj instanceof Polygon) {
            this.mPolygons.remove((Polygon) obj);
            return;
        }
        if (obj instanceof GroundOverlay) {
            this.mGroundOverlays.remove((GroundOverlay) obj);
        } else if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                removeFromMap(it.next());
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    protected Object addGeoJsonFeatureToMap(Feature feature, Geometry geometry) {
        String geometryType = geometry.getGeometryType();
        geometryType.hashCode();
        char c = 65535;
        switch (geometryType.hashCode()) {
            case -2116761119:
                if (geometryType.equals("MultiPolygon")) {
                    c = 0;
                    break;
                }
                break;
            case -1065891849:
                if (geometryType.equals("MultiPoint")) {
                    c = 1;
                    break;
                }
                break;
            case -627102946:
                if (geometryType.equals("MultiLineString")) {
                    c = 2;
                    break;
                }
                break;
            case 77292912:
                if (geometryType.equals("Point")) {
                    c = 3;
                    break;
                }
                break;
            case 1267133722:
                if (geometryType.equals(KmlPolygon.GEOMETRY_TYPE)) {
                    c = 4;
                    break;
                }
                break;
            case 1806700869:
                if (geometryType.equals("LineString")) {
                    c = 5;
                    break;
                }
                break;
            case 1950410960:
                if (geometryType.equals("GeometryCollection")) {
                    c = 6;
                    break;
                }
                break;
        }
        MarkerOptions markerOptions = null;
        PolylineOptions polylineOptions = null;
        PolygonOptions polygonOptions = null;
        switch (c) {
            case 0:
                return addMultiPolygonToMap(((GeoJsonFeature) feature).getPolygonStyle(), (GeoJsonMultiPolygon) geometry);
            case 1:
                return addMultiPointToMap(((GeoJsonFeature) feature).getPointStyle(), (GeoJsonMultiPoint) geometry);
            case 2:
                return addMultiLineStringToMap(((GeoJsonFeature) feature).getLineStringStyle(), (GeoJsonMultiLineString) geometry);
            case 3:
                if (feature instanceof GeoJsonFeature) {
                    markerOptions = ((GeoJsonFeature) feature).getMarkerOptions();
                } else if (feature instanceof KmlPlacemark) {
                    markerOptions = ((KmlPlacemark) feature).getMarkerOptions();
                }
                return addPointToMap(markerOptions, (GeoJsonPoint) geometry);
            case 4:
                if (feature instanceof GeoJsonFeature) {
                    polygonOptions = ((GeoJsonFeature) feature).getPolygonOptions();
                } else if (feature instanceof KmlPlacemark) {
                    polygonOptions = ((KmlPlacemark) feature).getPolygonOptions();
                }
                return addPolygonToMap(polygonOptions, (DataPolygon) geometry);
            case 5:
                if (feature instanceof GeoJsonFeature) {
                    polylineOptions = ((GeoJsonFeature) feature).getPolylineOptions();
                } else if (feature instanceof KmlPlacemark) {
                    polylineOptions = ((KmlPlacemark) feature).getPolylineOptions();
                }
                return addLineStringToMap(polylineOptions, (GeoJsonLineString) geometry);
            case 6:
                return addGeometryCollectionToMap((GeoJsonFeature) feature, ((GeoJsonGeometryCollection) geometry).getGeometries());
            default:
                return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.Object addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark r13, com.google.maps.android.data.Geometry r14, com.google.maps.android.data.kml.KmlStyle r15, com.google.maps.android.data.kml.KmlStyle r16, boolean r17) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.Renderer.addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark, com.google.maps.android.data.Geometry, com.google.maps.android.data.kml.KmlStyle, com.google.maps.android.data.kml.KmlStyle, boolean):java.lang.Object");
    }

    private Marker addPointToMap(MarkerOptions markerOptions, Point point) {
        markerOptions.position(point.getGeometryObject());
        return this.mMarkers.addMarker(markerOptions);
    }

    private void setInlinePointStyle(MarkerOptions markerOptions, KmlStyle kmlStyle, KmlStyle kmlStyle2) {
        double iconScale;
        MarkerOptions markerOptions2 = kmlStyle.getMarkerOptions();
        if (kmlStyle.isStyleSet("heading")) {
            markerOptions.rotation(markerOptions2.getRotation());
        }
        if (kmlStyle.isStyleSet("hotSpot")) {
            markerOptions.anchor(markerOptions2.getAnchorU(), markerOptions2.getAnchorV());
        }
        if (kmlStyle.isStyleSet("markerColor")) {
            markerOptions.icon(markerOptions2.getIcon());
        }
        if (kmlStyle.isStyleSet("iconScale")) {
            iconScale = kmlStyle.getIconScale();
        } else {
            iconScale = kmlStyle2.isStyleSet("iconScale") ? kmlStyle2.getIconScale() : 1.0d;
        }
        if (kmlStyle.isStyleSet("iconUrl")) {
            addMarkerIcons(kmlStyle.getIconUrl(), iconScale, markerOptions);
        } else if (kmlStyle2.getIconUrl() != null) {
            addMarkerIcons(kmlStyle2.getIconUrl(), iconScale, markerOptions);
        }
    }

    private Polyline addLineStringToMap(PolylineOptions polylineOptions, LineString lineString) {
        polylineOptions.addAll(lineString.getGeometryObject());
        Polyline polylineAddPolyline = this.mPolylines.addPolyline(polylineOptions);
        polylineAddPolyline.setClickable(polylineOptions.isClickable());
        return polylineAddPolyline;
    }

    private void setInlineLineStringStyle(PolylineOptions polylineOptions, KmlStyle kmlStyle) {
        PolylineOptions polylineOptions2 = kmlStyle.getPolylineOptions();
        if (kmlStyle.isStyleSet("outlineColor")) {
            polylineOptions.color(polylineOptions2.getColor());
        }
        if (kmlStyle.isStyleSet("width")) {
            polylineOptions.width(polylineOptions2.getWidth());
        }
        if (kmlStyle.isLineRandomColorMode()) {
            polylineOptions.color(KmlStyle.computeRandomColor(polylineOptions2.getColor()));
        }
    }

    private Polygon addPolygonToMap(PolygonOptions polygonOptions, DataPolygon dataPolygon) {
        polygonOptions.addAll(dataPolygon.getOuterBoundaryCoordinates());
        Iterator<List<LatLng>> it = dataPolygon.getInnerBoundaryCoordinates().iterator();
        while (it.hasNext()) {
            polygonOptions.addHole(it.next());
        }
        Polygon polygonAddPolygon = this.mPolygons.addPolygon(polygonOptions);
        polygonAddPolygon.setClickable(polygonOptions.isClickable());
        return polygonAddPolygon;
    }

    private void setInlinePolygonStyle(PolygonOptions polygonOptions, KmlStyle kmlStyle) {
        PolygonOptions polygonOptions2 = kmlStyle.getPolygonOptions();
        if (kmlStyle.hasFill() && kmlStyle.isStyleSet("fillColor")) {
            polygonOptions.fillColor(polygonOptions2.getFillColor());
        }
        if (kmlStyle.hasOutline()) {
            if (kmlStyle.isStyleSet("outlineColor")) {
                polygonOptions.strokeColor(polygonOptions2.getStrokeColor());
            }
            if (kmlStyle.isStyleSet("width")) {
                polygonOptions.strokeWidth(polygonOptions2.getStrokeWidth());
            }
        }
        if (kmlStyle.isPolyRandomColorMode()) {
            polygonOptions.fillColor(KmlStyle.computeRandomColor(polygonOptions2.getFillColor()));
        }
    }

    private ArrayList<Object> addGeometryCollectionToMap(GeoJsonFeature geoJsonFeature, List<Geometry> list) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Geometry> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(addGeoJsonFeatureToMap(geoJsonFeature, it.next()));
        }
        return arrayList;
    }

    protected static boolean getPlacemarkVisibility(Feature feature) {
        return (feature.hasProperty(ViewHierarchyNode.JsonKeys.VISIBILITY) && Integer.parseInt(feature.getProperty(ViewHierarchyNode.JsonKeys.VISIBILITY)) == 0) ? false : true;
    }

    public void assignStyleMap(HashMap<String, String> map, HashMap<String, KmlStyle> map2) {
        for (String str : map.keySet()) {
            String str2 = map.get(str);
            if (map2.containsKey(str2)) {
                map2.put(str, map2.get(str2));
            }
        }
    }

    private ArrayList<Object> addMultiGeometryToMap(KmlPlacemark kmlPlacemark, KmlMultiGeometry kmlMultiGeometry, KmlStyle kmlStyle, KmlStyle kmlStyle2, boolean z) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Geometry> it = kmlMultiGeometry.getGeometryObject().iterator();
        while (it.hasNext()) {
            arrayList.add(addKmlPlacemarkToMap(kmlPlacemark, it.next(), kmlStyle, kmlStyle2, z));
        }
        return arrayList;
    }

    private ArrayList<Marker> addMultiPointToMap(GeoJsonPointStyle geoJsonPointStyle, GeoJsonMultiPoint geoJsonMultiPoint) {
        ArrayList<Marker> arrayList = new ArrayList<>();
        Iterator<GeoJsonPoint> it = geoJsonMultiPoint.getPoints().iterator();
        while (it.hasNext()) {
            arrayList.add(addPointToMap(geoJsonPointStyle.toMarkerOptions(), it.next()));
        }
        return arrayList;
    }

    private ArrayList<Polyline> addMultiLineStringToMap(GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonMultiLineString geoJsonMultiLineString) {
        ArrayList<Polyline> arrayList = new ArrayList<>();
        Iterator<GeoJsonLineString> it = geoJsonMultiLineString.getLineStrings().iterator();
        while (it.hasNext()) {
            arrayList.add(addLineStringToMap(geoJsonLineStringStyle.toPolylineOptions(), it.next()));
        }
        return arrayList;
    }

    private ArrayList<Polygon> addMultiPolygonToMap(GeoJsonPolygonStyle geoJsonPolygonStyle, GeoJsonMultiPolygon geoJsonMultiPolygon) {
        ArrayList<Polygon> arrayList = new ArrayList<>();
        Iterator<GeoJsonPolygon> it = geoJsonMultiPolygon.getPolygons().iterator();
        while (it.hasNext()) {
            arrayList.add(addPolygonToMap(geoJsonPolygonStyle.toPolygonOptions(), it.next()));
        }
        return arrayList;
    }

    private void addMarkerIcons(String str, double d, MarkerOptions markerOptions) {
        BitmapDescriptor cachedMarkerImage = getCachedMarkerImage(str, d);
        if (cachedMarkerImage != null) {
            markerOptions.icon(cachedMarkerImage);
        } else {
            this.mMarkerIconUrls.add(str);
        }
    }

    protected GroundOverlay attachGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        return this.mGroundOverlays.addGroundOverlay(groundOverlayOptions);
    }

    private void setMarkerInfoWindow(KmlStyle kmlStyle, Marker marker, KmlPlacemark kmlPlacemark) {
        boolean zHasProperty = kmlPlacemark.hasProperty("name");
        boolean zHasProperty2 = kmlPlacemark.hasProperty("description");
        boolean zHasBalloonStyle = kmlStyle.hasBalloonStyle();
        boolean zContainsKey = kmlStyle.getBalloonOptions().containsKey("text");
        if (zHasBalloonStyle && zContainsKey) {
            marker.setTitle(KmlUtil.substituteProperties(kmlStyle.getBalloonOptions().get("text"), kmlPlacemark));
            createInfoWindow();
            return;
        }
        if (zHasBalloonStyle && zHasProperty) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            createInfoWindow();
            return;
        }
        if (zHasProperty && zHasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            marker.setSnippet(kmlPlacemark.getProperty("description"));
            createInfoWindow();
        } else if (zHasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty("description"));
            createInfoWindow();
        } else if (zHasProperty) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            createInfoWindow();
        }
    }

    private void createInfoWindow() {
        this.mMarkers.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() { // from class: com.google.maps.android.data.Renderer.1
            @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
            public View getInfoContents(Marker marker) {
                View viewInflate = LayoutInflater.from(Renderer.this.mContext).inflate(R.layout.amu_info_window, (ViewGroup) null);
                TextView textView = (TextView) viewInflate.findViewById(R.id.window);
                if (marker.getSnippet() != null) {
                    textView.setText(Html.fromHtml(marker.getTitle() + "<br>" + marker.getSnippet()));
                } else {
                    textView.setText(Html.fromHtml(marker.getTitle()));
                }
                return viewInflate;
            }
        });
    }

    void setOnFeatureClickListener(final Layer.OnFeatureClickListener onFeatureClickListener) {
        this.mPolygons.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() { // from class: com.google.maps.android.data.Renderer$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
            public final void onPolygonClick(Polygon polygon) {
                this.f$0.m5414x2cc520ac(onFeatureClickListener, polygon);
            }
        });
        this.mMarkers.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: com.google.maps.android.data.Renderer$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public final boolean onMarkerClick(Marker marker) {
                return this.f$0.m5415xf5c617ed(onFeatureClickListener, marker);
            }
        });
        this.mPolylines.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() { // from class: com.google.maps.android.data.Renderer$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
            public final void onPolylineClick(Polyline polyline) {
                this.f$0.m5416xbec70f2e(onFeatureClickListener, polyline);
            }
        });
    }

    /* renamed from: lambda$setOnFeatureClickListener$0$com-google-maps-android-data-Renderer, reason: not valid java name */
    /* synthetic */ void m5414x2cc520ac(Layer.OnFeatureClickListener onFeatureClickListener, Polygon polygon) {
        if (getFeature(polygon) != null) {
            onFeatureClickListener.onFeatureClick(getFeature(polygon));
        } else if (getContainerFeature(polygon) != null) {
            onFeatureClickListener.onFeatureClick(getContainerFeature(polygon));
        } else {
            onFeatureClickListener.onFeatureClick(getFeature(multiObjectHandler(polygon)));
        }
    }

    /* renamed from: lambda$setOnFeatureClickListener$1$com-google-maps-android-data-Renderer, reason: not valid java name */
    /* synthetic */ boolean m5415xf5c617ed(Layer.OnFeatureClickListener onFeatureClickListener, Marker marker) {
        if (getFeature(marker) != null) {
            onFeatureClickListener.onFeatureClick(getFeature(marker));
            return false;
        }
        if (getContainerFeature(marker) != null) {
            onFeatureClickListener.onFeatureClick(getContainerFeature(marker));
            return false;
        }
        onFeatureClickListener.onFeatureClick(getFeature(multiObjectHandler(marker)));
        return false;
    }

    /* renamed from: lambda$setOnFeatureClickListener$2$com-google-maps-android-data-Renderer, reason: not valid java name */
    /* synthetic */ void m5416xbec70f2e(Layer.OnFeatureClickListener onFeatureClickListener, Polyline polyline) {
        if (getFeature(polyline) != null) {
            onFeatureClickListener.onFeatureClick(getFeature(polyline));
        } else if (getContainerFeature(polyline) != null) {
            onFeatureClickListener.onFeatureClick(getContainerFeature(polyline));
        } else {
            onFeatureClickListener.onFeatureClick(getFeature(multiObjectHandler(polyline)));
        }
    }

    private ArrayList<?> multiObjectHandler(Object obj) {
        for (Object obj2 : getValues()) {
            if (obj2.getClass().getSimpleName().equals("ArrayList")) {
                ArrayList<?> arrayList = (ArrayList) obj2;
                if (arrayList.contains(obj)) {
                    return arrayList;
                }
            }
        }
        return null;
    }
}
