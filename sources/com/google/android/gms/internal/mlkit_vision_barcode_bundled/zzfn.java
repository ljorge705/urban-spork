package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
final class zzfn {
    static String zza(zzfl zzflVar, String str) throws SecurityException {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzflVar, sb, 0);
        return sb.toString();
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) throws SecurityException {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzgn.zza(zzdb.zzs((String) obj)));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzdb) {
            sb.append(": \"");
            sb.append(zzgn.zza((zzdb) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzec) {
            sb.append(" {");
            zzd((zzec) obj, sb, i + 2);
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj.toString());
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i4 = i + 2;
        zzb(sb, i4, Constants.KEY_KEY, entry.getKey());
        zzb(sb, i4, "value", entry.getValue());
        sb.append("\n");
        while (i2 < i) {
            sb.append(' ');
            i2++;
        }
        sb.append("}");
    }

    private static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }

    private static void zzd(zzfl zzflVar, StringBuilder sb, int i) throws SecurityException {
        boolean zEquals;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzflVar.getClass().getDeclaredMethods()) {
            map2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String strSubstring = str.startsWith("get") ? str.substring(3) : str;
            if (strSubstring.endsWith("List") && !strSubstring.endsWith("OrBuilderList") && !strSubstring.equals("List")) {
                String strValueOf = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf2 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 4));
                String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                Method method2 = (Method) map.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzc(strConcat), zzec.zzQ(method2, zzflVar, new Object[0]));
                }
            }
            if (strSubstring.endsWith("Map") && !strSubstring.equals("Map")) {
                String strValueOf3 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf4 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 3));
                String strConcat2 = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
                Method method3 = (Method) map.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzc(strConcat2), zzec.zzQ(method3, zzflVar, new Object[0]));
                }
            }
            String strValueOf5 = String.valueOf(strSubstring);
            if (((Method) map2.get(strValueOf5.length() != 0 ? "set".concat(strValueOf5) : new String("set"))) != null) {
                if (strSubstring.endsWith("Bytes")) {
                    String strValueOf6 = String.valueOf(strSubstring.substring(0, strSubstring.length() - 5));
                    if (!map.containsKey(strValueOf6.length() != 0 ? "get".concat(strValueOf6) : new String("get"))) {
                    }
                }
                String strValueOf7 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf8 = String.valueOf(strSubstring.substring(1));
                String strConcat3 = strValueOf8.length() != 0 ? strValueOf7.concat(strValueOf8) : new String(strValueOf7);
                String strValueOf9 = String.valueOf(strSubstring);
                Method method4 = (Method) map.get(strValueOf9.length() != 0 ? "get".concat(strValueOf9) : new String("get"));
                String strValueOf10 = String.valueOf(strSubstring);
                Method method5 = (Method) map.get(strValueOf10.length() != 0 ? "has".concat(strValueOf10) : new String("has"));
                if (method4 != null) {
                    Object objZzQ = zzec.zzQ(method4, zzflVar, new Object[0]);
                    if (method5 == null) {
                        if (objZzQ instanceof Boolean) {
                            if (((Boolean) objZzQ).booleanValue()) {
                                zzb(sb, i, zzc(strConcat3), objZzQ);
                            }
                        } else if (objZzQ instanceof Integer) {
                            if (((Integer) objZzQ).intValue() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzQ);
                            }
                        } else if (objZzQ instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) objZzQ).floatValue()) != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzQ);
                            }
                        } else if (!(objZzQ instanceof Double)) {
                            if (objZzQ instanceof String) {
                                zEquals = objZzQ.equals("");
                            } else if (objZzQ instanceof zzdb) {
                                zEquals = objZzQ.equals(zzdb.zzb);
                            } else if (objZzQ instanceof zzfl) {
                                if (objZzQ != ((zzfl) objZzQ).zzX()) {
                                    zzb(sb, i, zzc(strConcat3), objZzQ);
                                }
                            } else if (!(objZzQ instanceof Enum) || ((Enum) objZzQ).ordinal() != 0) {
                                zzb(sb, i, zzc(strConcat3), objZzQ);
                            }
                            if (!zEquals) {
                                zzb(sb, i, zzc(strConcat3), objZzQ);
                            }
                        } else if (Double.doubleToRawLongBits(((Double) objZzQ).doubleValue()) != 0) {
                            zzb(sb, i, zzc(strConcat3), objZzQ);
                        }
                    } else if (((Boolean) zzec.zzQ(method5, zzflVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, zzc(strConcat3), objZzQ);
                    }
                }
            }
        }
        if (zzflVar instanceof zzdy) {
            Iterator itZzf = ((zzdy) zzflVar).zza.zzf();
            while (itZzf.hasNext()) {
                Map.Entry entry = (Map.Entry) itZzf.next();
                int i2 = ((zzdz) entry.getKey()).zza;
                StringBuilder sb2 = new StringBuilder(13);
                sb2.append("[");
                sb2.append(i2);
                sb2.append("]");
                zzb(sb, i, sb2.toString(), entry.getValue());
            }
        }
        zzgq zzgqVar = ((zzec) zzflVar).zzc;
        if (zzgqVar != null) {
            zzgqVar.zzg(sb, i);
        }
    }
}
