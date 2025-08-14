package com.google.mlkit.vision.text;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbw;
import com.google.android.gms.internal.mlkit_vision_text_common.zznr;
import com.google.android.gms.internal.mlkit_vision_text_common.zznt;
import com.google.android.gms.internal.mlkit_vision_text_common.zznv;
import com.google.android.gms.internal.mlkit_vision_text_common.zznx;
import com.google.android.gms.internal.mlkit_vision_text_common.zzu;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.text.Text;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public class Text {
    private final List zza;
    private final String zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
    public static class TextBase {
        private final String zza;
        private final Rect zzb;
        private final Point[] zzc;
        private final String zzd;

        TextBase(String str, Rect rect, List list, String str2, Matrix matrix) {
            this.zza = str;
            Rect rect2 = new Rect(rect);
            if (matrix != null) {
                CommonConvertUtils.transformRect(rect2, matrix);
            }
            this.zzb = rect2;
            Point[] pointArr = new Point[list.size()];
            for (int i = 0; i < list.size(); i++) {
                pointArr[i] = new Point((Point) list.get(i));
            }
            if (matrix != null) {
                CommonConvertUtils.transformPointArray(pointArr, matrix);
            }
            this.zzc = pointArr;
            this.zzd = str2;
        }

        public Rect getBoundingBox() {
            return this.zzb;
        }

        public Point[] getCornerPoints() {
            return this.zzc;
        }

        public String getRecognizedLanguage() {
            return this.zzd;
        }

        protected final String zza() {
            String str = this.zza;
            return str == null ? "" : str;
        }
    }

    public Text(zznx zznxVar, final Matrix matrix) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        this.zzb = zznxVar.zza();
        arrayList.addAll(zzbw.zza(zznxVar.zzb(), new zzu() { // from class: com.google.mlkit.vision.text.zza
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
            public final Object zza(Object obj) {
                return new Text.TextBlock((zznr) obj, matrix);
            }
        }));
    }

    public String getText() {
        return this.zzb;
    }

    public List<TextBlock> getTextBlocks() {
        return Collections.unmodifiableList(this.zza);
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
    public static class Element extends TextBase {
        Element(zznt zzntVar, Matrix matrix) {
            super(zzntVar.zzc(), zzntVar.zza(), zzntVar.zzd(), zzntVar.zzb(), matrix);
        }

        public String getText() {
            return zza();
        }

        public Element(String str, Rect rect, List list, String str2, Matrix matrix) {
            super(str, rect, list, str2, matrix);
        }
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
    public static class TextBlock extends TextBase {
        private final List zza;

        TextBlock(zznr zznrVar, final Matrix matrix) {
            super(zznrVar.zzc(), zznrVar.zza(), zznrVar.zzd(), zznrVar.zzb(), matrix);
            this.zza = zzbw.zza(zznrVar.zze(), new zzu() { // from class: com.google.mlkit.vision.text.zzc
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    return new Text.Line((zznv) obj, matrix);
                }
            });
        }

        public synchronized List<Line> getLines() {
            return this.zza;
        }

        public String getText() {
            return zza();
        }

        public TextBlock(String str, Rect rect, List list, String str2, Matrix matrix, List list2) {
            super(str, rect, list, str2, matrix);
            this.zza = list2;
        }
    }

    public Text(String str, List list) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        arrayList.addAll(list);
        this.zzb = str;
    }

    /* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
    public static class Line extends TextBase {
        private final List zza;

        Line(zznv zznvVar, final Matrix matrix) {
            super(zznvVar.zzc(), zznvVar.zza(), zznvVar.zzd(), zznvVar.zzb(), matrix);
            this.zza = zzbw.zza(zznvVar.zze(), new zzu() { // from class: com.google.mlkit.vision.text.zzb
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object zza(Object obj) {
                    return new Text.Element((zznt) obj, matrix);
                }
            });
        }

        public synchronized List<Element> getElements() {
            return this.zza;
        }

        public String getText() {
            return zza();
        }

        public Line(String str, Rect rect, List list, String str2, Matrix matrix, List list2) {
            super(str, rect, list, str2, matrix);
            this.zza = list2;
        }
    }
}
