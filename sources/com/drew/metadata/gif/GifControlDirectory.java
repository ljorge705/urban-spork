package com.drew.metadata.gif;

import com.drew.metadata.Directory;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class GifControlDirectory extends Directory {
    public static final int TAG_DELAY = 1;
    public static final int TAG_DISPOSAL_METHOD = 2;
    public static final int TAG_TRANSPARENT_COLOR_FLAG = 4;
    public static final int TAG_TRANSPARENT_COLOR_INDEX = 5;
    public static final int TAG_USER_INPUT_FLAG = 3;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "GIF Control";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Delay");
        map.put(2, "Disposal Method");
        map.put(3, "User Input Flag");
        map.put(4, "Transparent Color Flag");
        map.put(5, "Transparent Color Index");
    }

    public GifControlDirectory() {
        setDescriptor(new GifControlDescriptor(this));
    }

    public DisposalMethod getDisposalMethod() {
        return (DisposalMethod) getObject(2);
    }

    public boolean isTransparent() {
        Boolean booleanObject = getBooleanObject(4);
        return booleanObject != null && booleanObject.booleanValue();
    }

    /* renamed from: com.drew.metadata.gif.GifControlDirectory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod;

        static {
            int[] iArr = new int[DisposalMethod.values().length];
            $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod = iArr;
            try {
                iArr[DisposalMethod.DO_NOT_DISPOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.INVALID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.NOT_SPECIFIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.RESTORE_TO_BACKGROUND_COLOR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.RESTORE_TO_PREVIOUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[DisposalMethod.TO_BE_DEFINED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum DisposalMethod {
        NOT_SPECIFIED,
        DO_NOT_DISPOSE,
        RESTORE_TO_BACKGROUND_COLOR,
        RESTORE_TO_PREVIOUS,
        TO_BE_DEFINED,
        INVALID;

        public static DisposalMethod typeOf(int i) {
            switch (i) {
                case 0:
                    return NOT_SPECIFIED;
                case 1:
                    return DO_NOT_DISPOSE;
                case 2:
                    return RESTORE_TO_BACKGROUND_COLOR;
                case 3:
                    return RESTORE_TO_PREVIOUS;
                case 4:
                case 5:
                case 6:
                case 7:
                    return TO_BE_DEFINED;
                default:
                    return INVALID;
            }
        }

        @Override // java.lang.Enum
        public String toString() {
            switch (AnonymousClass1.$SwitchMap$com$drew$metadata$gif$GifControlDirectory$DisposalMethod[ordinal()]) {
                case 1:
                    return "Don't Dispose";
                case 2:
                    return "Invalid value";
                case 3:
                    return "Not Specified";
                case 4:
                    return "Restore to Background Color";
                case 5:
                    return "Restore to Previous";
                case 6:
                    return "To Be Defined";
                default:
                    return super.toString();
            }
        }
    }
}
