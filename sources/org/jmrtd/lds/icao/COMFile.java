package org.jmrtd.lds.icao;

import com.google.android.material.timepicker.TimeModel;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVOutputStream;
import org.jmrtd.lds.AbstractTaggedLDSFile;
import org.jmrtd.lds.LDSFileUtil;

/* loaded from: classes4.dex */
public class COMFile extends AbstractTaggedLDSFile {
    private static final int TAG_LIST_TAG = 92;
    private static final int VERSION_LDS_TAG = 24321;
    private static final int VERSION_UNICODE_TAG = 24374;
    private static final long serialVersionUID = 2002455279067170063L;
    private String majorVersionUnicode;
    private String minorVersionUnicode;
    private String releaseLevelUnicode;
    private List<Integer> tagList;
    private String updateLevelLDS;
    private String versionLDS;

    public COMFile(String str, String str2, String str3, String str4, String str5, int[] iArr) {
        super(96);
        initialize(str, str2, str3, str4, str5, iArr);
    }

    public COMFile(String str, String str2, int[] iArr) {
        super(96);
        try {
            if (str == null) {
                throw new IllegalArgumentException("Null versionLDS");
            }
            if (str2 == null) {
                throw new IllegalArgumentException("Null versionUnicode");
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
            if (stringTokenizer.countTokens() != 2) {
                throw new IllegalArgumentException("Could not parse LDS version. Expecting 2 level version number x.y.");
            }
            Integer numValueOf = Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken().trim()));
            Integer numValueOf2 = Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken().trim()));
            StringTokenizer stringTokenizer2 = new StringTokenizer(str2, ".");
            if (stringTokenizer2.countTokens() != 3) {
                throw new IllegalArgumentException("Could not parse unicode version. Expecting 3 level version number x.y.z.");
            }
            initialize(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, numValueOf), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, numValueOf2), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Integer.parseInt(stringTokenizer2.nextToken().trim()))), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Integer.parseInt(stringTokenizer2.nextToken().trim()))), String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Integer.parseInt(stringTokenizer2.nextToken().trim()))), iArr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Could not parse version number", e);
        } catch (IllegalFormatConversionException e2) {
            throw new IllegalArgumentException("Could not parse version number", e2);
        }
    }

    public COMFile(InputStream inputStream) throws IOException {
        super(96, inputStream);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int tag = tLVInputStream.readTag();
        if (tag != VERSION_LDS_TAG) {
            throw new IllegalArgumentException("Excepected VERSION_LDS_TAG (" + Integer.toHexString(VERSION_LDS_TAG) + "), found " + Integer.toHexString(tag));
        }
        if (tLVInputStream.readLength() != 4) {
            throw new IllegalArgumentException("Wrong length of LDS version object");
        }
        byte[] value = tLVInputStream.readValue();
        this.versionLDS = new String(value, 0, 2);
        this.updateLevelLDS = new String(value, 2, 2);
        int tag2 = tLVInputStream.readTag();
        if (tag2 != VERSION_UNICODE_TAG) {
            throw new IllegalArgumentException("Expected VERSION_UNICODE_TAG (" + Integer.toHexString(VERSION_UNICODE_TAG) + "), found " + Integer.toHexString(tag2));
        }
        if (tLVInputStream.readLength() != 6) {
            throw new IllegalArgumentException("Wrong length of LDS version object");
        }
        byte[] value2 = tLVInputStream.readValue();
        this.majorVersionUnicode = new String(value2, 0, 2);
        this.minorVersionUnicode = new String(value2, 2, 2);
        this.releaseLevelUnicode = new String(value2, 4, 2);
        int tag3 = tLVInputStream.readTag();
        if (tag3 != 92) {
            throw new IllegalArgumentException("Expected TAG_LIST_TAG (" + Integer.toHexString(92) + "), found " + Integer.toHexString(tag3));
        }
        tLVInputStream.readLength();
        byte[] value3 = tLVInputStream.readValue();
        this.tagList = new ArrayList();
        for (byte b : value3) {
            this.tagList.add(Integer.valueOf(b & 255));
        }
    }

    public String getLDSVersion() throws NumberFormatException {
        String str = this.versionLDS + "." + this.updateLevelLDS;
        try {
            return Integer.parseInt(this.versionLDS) + "." + Integer.parseInt(this.updateLevelLDS);
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    public String getUnicodeVersion() throws NumberFormatException {
        String str = this.majorVersionUnicode + "." + this.minorVersionUnicode + "." + this.releaseLevelUnicode;
        try {
            return Integer.parseInt(this.majorVersionUnicode) + "." + Integer.parseInt(this.minorVersionUnicode) + "." + Integer.parseInt(this.releaseLevelUnicode);
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    public int[] getTagList() {
        int[] iArr = new int[this.tagList.size()];
        Iterator<Integer> it = this.tagList.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }

    public void insertTag(Integer num) {
        if (this.tagList.contains(num)) {
            return;
        }
        this.tagList.add(num);
        Collections.sort(this.tagList);
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    protected void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(VERSION_LDS_TAG);
        tLVOutputStream.writeValue((this.versionLDS + this.updateLevelLDS).getBytes());
        tLVOutputStream.writeTag(VERSION_UNICODE_TAG);
        tLVOutputStream.writeValue((this.majorVersionUnicode + this.minorVersionUnicode + this.releaseLevelUnicode).getBytes());
        tLVOutputStream.writeTag(92);
        tLVOutputStream.writeLength(this.tagList.size());
        Iterator<Integer> it = this.tagList.iterator();
        while (it.hasNext()) {
            tLVOutputStream.write((byte) it.next().intValue());
        }
    }

    @Override // org.jmrtd.lds.AbstractTaggedLDSFile
    public String toString() {
        StringBuilder sb = new StringBuilder("COMFile ");
        sb.append("LDS " + this.versionLDS + "." + this.updateLevelLDS);
        sb.append(", ");
        sb.append("Unicode " + this.majorVersionUnicode + "." + this.minorVersionUnicode + "." + this.releaseLevelUnicode);
        sb.append(", [");
        int size = this.tagList.size();
        Iterator<Integer> it = this.tagList.iterator();
        int i = 0;
        while (it.hasNext()) {
            sb.append("DG" + LDSFileUtil.lookupDataGroupNumberByTag(it.next().intValue()));
            if (i < size - 1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        COMFile cOMFile = (COMFile) obj;
        return this.versionLDS.equals(cOMFile.versionLDS) && this.updateLevelLDS.equals(cOMFile.updateLevelLDS) && this.majorVersionUnicode.equals(cOMFile.majorVersionUnicode) && this.minorVersionUnicode.equals(cOMFile.minorVersionUnicode) && this.releaseLevelUnicode.equals(cOMFile.releaseLevelUnicode) && this.tagList.equals(cOMFile.tagList);
    }

    public int hashCode() {
        return (this.versionLDS.hashCode() * 3) + (this.updateLevelLDS.hashCode() * 5) + (this.majorVersionUnicode.hashCode() * 7) + (this.minorVersionUnicode.hashCode() * 11) + (this.releaseLevelUnicode.hashCode() * 13) + (this.tagList.hashCode() * 17);
    }

    private void initialize(String str, String str2, String str3, String str4, String str5, int[] iArr) {
        if (iArr == null) {
            throw new IllegalArgumentException("Null tag list");
        }
        if (str == null || str.length() != 2 || str2 == null || str2.length() != 2 || str3 == null || str3.length() != 2 || str4 == null || str4.length() != 2 || str5 == null || str5.length() != 2 || iArr == null) {
            throw new IllegalArgumentException();
        }
        this.versionLDS = str;
        this.updateLevelLDS = str2;
        this.majorVersionUnicode = str3;
        this.minorVersionUnicode = str4;
        this.releaseLevelUnicode = str5;
        this.tagList = new ArrayList(iArr.length);
        for (int i : iArr) {
            this.tagList.add(Integer.valueOf(i));
        }
    }
}
