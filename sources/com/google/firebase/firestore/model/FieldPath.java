package com.google.firebase.firestore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class FieldPath extends BasePath<FieldPath> {
    public static final FieldPath KEY_PATH = fromSingleSegment(DocumentKey.KEY_FIELD_NAME);
    public static final FieldPath EMPTY_PATH = new FieldPath(Collections.emptyList());

    @Override // com.google.firebase.firestore.model.BasePath
    /* bridge */ /* synthetic */ BasePath createPathWithSegments(List list) {
        return createPathWithSegments((List<String>) list);
    }

    private FieldPath(List<String> list) {
        super(list);
    }

    public static FieldPath fromSingleSegment(String str) {
        return new FieldPath(Collections.singletonList(str));
    }

    public static FieldPath fromSegments(List<String> list) {
        return list.isEmpty() ? EMPTY_PATH : new FieldPath(list);
    }

    @Override // com.google.firebase.firestore.model.BasePath
    FieldPath createPathWithSegments(List<String> list) {
        return new FieldPath(list);
    }

    public static FieldPath fromServerFormat(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean z = false;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\') {
                i++;
                if (i == str.length()) {
                    throw new IllegalArgumentException("Trailing escape character is not allowed");
                }
                sb.append(str.charAt(i));
            } else if (cCharAt == '.') {
                if (!z) {
                    String string = sb.toString();
                    if (string.isEmpty()) {
                        throw new IllegalArgumentException("Invalid field path (" + str + "). Paths must not be empty, begin with '.', end with '.', or contain '..'");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    arrayList.add(string);
                    sb = sb2;
                } else {
                    sb.append(cCharAt);
                }
            } else if (cCharAt == '`') {
                z = !z;
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        String string2 = sb.toString();
        if (string2.isEmpty()) {
            throw new IllegalArgumentException("Invalid field path (" + str + "). Paths must not be empty, begin with '.', end with '.', or contain '..'");
        }
        arrayList.add(string2);
        return new FieldPath(arrayList);
    }

    private static boolean isValidIdentifier(String str) {
        if (str.isEmpty()) {
            return false;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt != '_' && ((cCharAt < 'a' || cCharAt > 'z') && (cCharAt < 'A' || cCharAt > 'Z'))) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 != '_' && ((cCharAt2 < 'a' || cCharAt2 > 'z') && ((cCharAt2 < 'A' || cCharAt2 > 'Z') && (cCharAt2 < '0' || cCharAt2 > '9')))) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.firebase.firestore.model.BasePath
    public String canonicalString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.segments.size(); i++) {
            if (i > 0) {
                sb.append(".");
            }
            String strReplace = this.segments.get(i).replace("\\", "\\\\").replace("`", "\\`");
            if (!isValidIdentifier(strReplace)) {
                strReplace = "`" + strReplace + '`';
            }
            sb.append(strReplace);
        }
        return sb.toString();
    }

    public boolean isKeyField() {
        return equals(KEY_PATH);
    }
}
