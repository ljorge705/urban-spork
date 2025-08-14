package com.facebook.imagepipeline.bitmaps;

import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.google.common.base.Ascii;
import java.io.IOException;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import okio.Utf8;
import org.jmrtd.lds.CVCAFile;

/* loaded from: classes5.dex */
public class EmptyJpegGenerator {
    private static final byte[] EMPTY_JPEG_PREFIX = {-1, ISO7816.INS_LOAD_KEY_FILE, -1, -37, 0, 67, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, ISO7816.INS_GET_RESPONSE, 0, 17, 8};
    private static final byte[] EMPTY_JPEG_SUFFIX = {3, 1, ISO7816.INS_MSE, 0, 2, 17, 0, 3, 17, 0, -1, -60, 0, Ascii.US, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, Ascii.DC2, 33, 49, 65, 6, 19, 81, 97, 7, ISO7816.INS_MSE, 113, Ascii.DC4, ISO7816.INS_INCREASE, ISOFileInfo.DATA_BYTES2, -111, ISOFileInfo.A1, 8, 35, CVCAFile.CAR_TAG, ISO7816.INS_READ_BINARY2, -63, Ascii.NAK, 82, -47, -16, ISO7816.INS_CHANGE_CHV, 51, ISOFileInfo.FCP_BYTE, 114, -126, 9, 10, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, Ascii.SUB, 37, 38, 39, 40, 41, ISO7816.INS_PSO, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57, 58, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, ISOFileInfo.FMD_BYTE, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, ISOFileInfo.FILE_IDENTIFIER, -124, ISOFileInfo.PROP_INFO, -122, ISOFileInfo.FCI_EXT, -120, -119, ISOFileInfo.LCS_BYTE, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, ISOFileInfo.A5, -90, -89, -88, -87, -86, -78, ISO7816.INS_READ_RECORD2, ISO7816.INS_READ_BINARY_STAMPED, -75, ISO7816.INS_READ_RECORD_STAMPED, -73, -72, -71, -70, ISO7816.INS_ENVELOPE, -61, -60, -59, -58, -57, -56, -55, ISO7816.INS_GET_DATA, ISO7816.INS_WRITE_RECORD, -45, -44, -43, ISO7816.INS_UPDATE_BINARY, -41, ISO7816.INS_LOAD_KEY_FILE, -39, ISO7816.INS_PUT_DATA, -31, ISO7816.INS_APPEND_RECORD, -29, ISO7816.INS_DELETE_FILE, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -60, 0, Ascii.US, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, Ascii.DC2, 65, 81, 7, 97, 113, 19, ISO7816.INS_MSE, ISO7816.INS_INCREASE, ISOFileInfo.DATA_BYTES2, 8, Ascii.DC4, CVCAFile.CAR_TAG, -111, ISOFileInfo.A1, ISO7816.INS_READ_BINARY2, -63, 9, 35, 51, 82, -16, Ascii.NAK, ISOFileInfo.FCP_BYTE, 114, -47, 10, Ascii.SYN, ISO7816.INS_CHANGE_CHV, ISO7816.INS_DECREASE_STAMPED, -31, 37, -15, Ascii.ETB, Ascii.CAN, Ascii.EM, Ascii.SUB, 38, 39, 40, 41, ISO7816.INS_PSO, 53, 54, 55, 56, 57, 58, 67, ISO7816.INS_REHABILITATE_CHV, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, ISOFileInfo.FMD_BYTE, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, ISOFileInfo.FILE_IDENTIFIER, -124, ISOFileInfo.PROP_INFO, -122, ISOFileInfo.FCI_EXT, -120, -119, ISOFileInfo.LCS_BYTE, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, ISOFileInfo.A5, -90, -89, -88, -87, -86, -78, ISO7816.INS_READ_RECORD2, ISO7816.INS_READ_BINARY_STAMPED, -75, ISO7816.INS_READ_RECORD_STAMPED, -73, -72, -71, -70, ISO7816.INS_ENVELOPE, -61, -60, -59, -58, -57, -56, -55, ISO7816.INS_GET_DATA, ISO7816.INS_WRITE_RECORD, -45, -44, -43, ISO7816.INS_UPDATE_BINARY, -41, ISO7816.INS_LOAD_KEY_FILE, -39, ISO7816.INS_PUT_DATA, ISO7816.INS_APPEND_RECORD, -29, ISO7816.INS_DELETE_FILE, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, ISO7816.INS_PUT_DATA, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, Utf8.REPLACEMENT_BYTE, 0, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.LCS_BYTE, 40, ISOFileInfo.A0, 15, -1, -39};
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public EmptyJpegGenerator(PooledByteBufferFactory pooledByteBufferFactory) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    public CloseableReference<PooledByteBuffer> generate(short s, short s2) throws Throwable {
        PooledByteBufferOutputStream pooledByteBufferOutputStreamNewOutputStream = null;
        try {
            try {
                PooledByteBufferFactory pooledByteBufferFactory = this.mPooledByteBufferFactory;
                byte[] bArr = EMPTY_JPEG_PREFIX;
                int length = bArr.length;
                byte[] bArr2 = EMPTY_JPEG_SUFFIX;
                pooledByteBufferOutputStreamNewOutputStream = pooledByteBufferFactory.newOutputStream(length + bArr2.length + 4);
                pooledByteBufferOutputStreamNewOutputStream.write(bArr);
                pooledByteBufferOutputStreamNewOutputStream.write((byte) (s2 >> 8));
                pooledByteBufferOutputStreamNewOutputStream.write((byte) (s2 & 255));
                pooledByteBufferOutputStreamNewOutputStream.write((byte) (s >> 8));
                pooledByteBufferOutputStreamNewOutputStream.write((byte) (s & 255));
                pooledByteBufferOutputStreamNewOutputStream.write(bArr2);
                return CloseableReference.of(pooledByteBufferOutputStreamNewOutputStream.toByteBuffer());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } finally {
            if (pooledByteBufferOutputStreamNewOutputStream != null) {
                pooledByteBufferOutputStreamNewOutputStream.close();
            }
        }
    }
}
