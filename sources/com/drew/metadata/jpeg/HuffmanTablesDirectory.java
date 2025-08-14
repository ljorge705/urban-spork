package com.drew.metadata.jpeg;

import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.lds.CVCAFile;

/* loaded from: classes5.dex */
public class HuffmanTablesDirectory extends Directory {
    public static final int TAG_NUMBER_OF_TABLES = 1;
    protected static final HashMap<Integer, String> _tagNameMap;
    protected final List<HuffmanTable> tables = new ArrayList(4);
    protected static final byte[] TYPICAL_LUMINANCE_DC_LENGTHS = {0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
    protected static final byte[] TYPICAL_LUMINANCE_DC_VALUES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    protected static final byte[] TYPICAL_CHROMINANCE_DC_LENGTHS = {0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
    protected static final byte[] TYPICAL_CHROMINANCE_DC_VALUES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    protected static final byte[] TYPICAL_LUMINANCE_AC_LENGTHS = {0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125};
    protected static final byte[] TYPICAL_LUMINANCE_AC_VALUES = {1, 2, 3, 0, 4, 17, 5, Ascii.DC2, 33, 49, 65, 6, 19, 81, 97, 7, ISO7816.INS_MSE, 113, Ascii.DC4, ISO7816.INS_INCREASE, ISOFileInfo.DATA_BYTES2, -111, ISOFileInfo.A1, 8, 35, CVCAFile.CAR_TAG, ISO7816.INS_READ_BINARY2, -63, Ascii.NAK, 82, -47, -16, ISO7816.INS_CHANGE_CHV, 51, ISOFileInfo.FCP_BYTE, 114, -126, 9, 10, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, Ascii.SUB, 37, 38, 39, 40, 41, ISO7816.INS_PSO, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57, 58, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, ISOFileInfo.FMD_BYTE, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, ISOFileInfo.FILE_IDENTIFIER, -124, ISOFileInfo.PROP_INFO, -122, ISOFileInfo.FCI_EXT, -120, -119, ISOFileInfo.LCS_BYTE, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, ISOFileInfo.A5, -90, -89, -88, -87, -86, -78, ISO7816.INS_READ_RECORD2, ISO7816.INS_READ_BINARY_STAMPED, -75, ISO7816.INS_READ_RECORD_STAMPED, -73, -72, -71, -70, ISO7816.INS_ENVELOPE, -61, -60, -59, -58, -57, -56, -55, ISO7816.INS_GET_DATA, ISO7816.INS_WRITE_RECORD, -45, -44, -43, ISO7816.INS_UPDATE_BINARY, -41, ISO7816.INS_LOAD_KEY_FILE, -39, ISO7816.INS_PUT_DATA, -31, ISO7816.INS_APPEND_RECORD, -29, ISO7816.INS_DELETE_FILE, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6};
    protected static final byte[] TYPICAL_CHROMINANCE_AC_LENGTHS = {0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119};
    protected static final byte[] TYPICAL_CHROMINANCE_AC_VALUES = {0, 1, 2, 3, 17, 4, 5, 33, 49, 6, Ascii.DC2, 65, 81, 7, 97, 113, 19, ISO7816.INS_MSE, ISO7816.INS_INCREASE, ISOFileInfo.DATA_BYTES2, 8, Ascii.DC4, CVCAFile.CAR_TAG, -111, ISOFileInfo.A1, ISO7816.INS_READ_BINARY2, -63, 9, 35, 51, 82, -16, Ascii.NAK, ISOFileInfo.FCP_BYTE, 114, -47, 10, Ascii.SYN, ISO7816.INS_CHANGE_CHV, ISO7816.INS_DECREASE_STAMPED, -31, 37, -15, Ascii.ETB, Ascii.CAN, Ascii.EM, Ascii.SUB, 38, 39, 40, 41, ISO7816.INS_PSO, 53, 54, 55, 56, 57, 58, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, ISOFileInfo.FMD_BYTE, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, ISOFileInfo.FILE_IDENTIFIER, -124, ISOFileInfo.PROP_INFO, -122, ISOFileInfo.FCI_EXT, -120, -119, ISOFileInfo.LCS_BYTE, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, ISOFileInfo.A5, -90, -89, -88, -87, -86, -78, ISO7816.INS_READ_RECORD2, ISO7816.INS_READ_BINARY_STAMPED, -75, ISO7816.INS_READ_RECORD_STAMPED, -73, -72, -71, -70, ISO7816.INS_ENVELOPE, -61, -60, -59, -58, -57, -56, -55, ISO7816.INS_GET_DATA, ISO7816.INS_WRITE_RECORD, -45, -44, -43, ISO7816.INS_UPDATE_BINARY, -41, ISO7816.INS_LOAD_KEY_FILE, -39, ISO7816.INS_PUT_DATA, ISO7816.INS_APPEND_RECORD, -29, ISO7816.INS_DELETE_FILE, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6};

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "Huffman";
    }

    protected List<HuffmanTable> getTables() {
        return this.tables;
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(1, "Number of Tables");
    }

    public HuffmanTablesDirectory() {
        setDescriptor(new HuffmanTablesDescriptor(this));
    }

    public HuffmanTable getTable(int i) {
        return this.tables.get(i);
    }

    public int getNumberOfTables() throws MetadataException {
        return getInt(1);
    }

    public boolean isTypical() {
        if (this.tables.size() == 0) {
            return false;
        }
        Iterator<HuffmanTable> it = this.tables.iterator();
        while (it.hasNext()) {
            if (!it.next().isTypical()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOptimized() {
        return !isTypical();
    }

    public static class HuffmanTable {
        private final byte[] _lengthBytes;
        private final HuffmanTableClass _tableClass;
        private final int _tableDestinationId;
        private final int _tableLength;
        private final byte[] _valueBytes;

        public enum HuffmanTableClass {
            DC,
            AC,
            UNKNOWN;

            public static HuffmanTableClass typeOf(int i) {
                return i != 0 ? i != 1 ? UNKNOWN : AC : DC;
            }
        }

        public HuffmanTableClass getTableClass() {
            return this._tableClass;
        }

        public int getTableDestinationId() {
            return this._tableDestinationId;
        }

        public int getTableLength() {
            return this._tableLength;
        }

        public HuffmanTable(HuffmanTableClass huffmanTableClass, int i, byte[] bArr, byte[] bArr2) {
            if (bArr == null) {
                throw new IllegalArgumentException("lengthBytes cannot be null.");
            }
            if (bArr2 == null) {
                throw new IllegalArgumentException("valueBytes cannot be null.");
            }
            this._tableClass = huffmanTableClass;
            this._tableDestinationId = i;
            this._lengthBytes = bArr;
            this._valueBytes = bArr2;
            this._tableLength = bArr2.length + 17;
        }

        public byte[] getLengthBytes() {
            byte[] bArr = this._lengthBytes;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }

        public byte[] getValueBytes() {
            byte[] bArr = this._valueBytes;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }

        public boolean isTypical() {
            if (this._tableClass == HuffmanTableClass.DC) {
                if (Arrays.equals(this._lengthBytes, HuffmanTablesDirectory.TYPICAL_LUMINANCE_DC_LENGTHS) && Arrays.equals(this._valueBytes, HuffmanTablesDirectory.TYPICAL_LUMINANCE_DC_VALUES)) {
                    return true;
                }
                return Arrays.equals(this._lengthBytes, HuffmanTablesDirectory.TYPICAL_CHROMINANCE_DC_LENGTHS) && Arrays.equals(this._valueBytes, HuffmanTablesDirectory.TYPICAL_CHROMINANCE_DC_VALUES);
            }
            if (this._tableClass != HuffmanTableClass.AC) {
                return false;
            }
            if (Arrays.equals(this._lengthBytes, HuffmanTablesDirectory.TYPICAL_LUMINANCE_AC_LENGTHS) && Arrays.equals(this._valueBytes, HuffmanTablesDirectory.TYPICAL_LUMINANCE_AC_VALUES)) {
                return true;
            }
            return Arrays.equals(this._lengthBytes, HuffmanTablesDirectory.TYPICAL_CHROMINANCE_AC_LENGTHS) && Arrays.equals(this._valueBytes, HuffmanTablesDirectory.TYPICAL_CHROMINANCE_AC_VALUES);
        }

        public boolean isOptimized() {
            return !isTypical();
        }
    }
}
