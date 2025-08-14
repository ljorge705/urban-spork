package org.jmrtd;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.FileInfo;
import net.sf.scuba.smartcards.FileSystemStructured;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.util.Hex;
import org.jmrtd.io.FragmentBuffer;
import org.jmrtd.lds.LDSFileUtil;
import org.jmrtd.protocol.SecureMessagingWrapper;

/* loaded from: classes4.dex */
public class DefaultFileSystem implements FileSystemStructured {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int NO_SFI = -1;
    private static final int READ_AHEAD_LENGTH = 8;
    private Map<Short, Byte> fidToSFI;
    private Map<Short, DefaultFileInfo> fileInfos;
    private boolean isSFIEnabled;
    private boolean isSelected;
    private int maxReadBinaryLength;
    private APDUWrapper oldWrapper;
    private short selectedFID;
    private APDULevelReadBinaryCapable service;
    private APDUWrapper wrapper;

    public int getMaxReadBinaryLength() {
        return this.maxReadBinaryLength;
    }

    public APDUWrapper getWrapper() {
        return this.wrapper;
    }

    public void setWrapper(APDUWrapper aPDUWrapper) {
        this.oldWrapper = this.wrapper;
        this.wrapper = aPDUWrapper;
    }

    public DefaultFileSystem(APDULevelReadBinaryCapable aPDULevelReadBinaryCapable, boolean z) {
        this(aPDULevelReadBinaryCapable, z, LDSFileUtil.FID_TO_SFI);
    }

    public DefaultFileSystem(APDULevelReadBinaryCapable aPDULevelReadBinaryCapable, boolean z, Map<Short, Byte> map) {
        this.service = aPDULevelReadBinaryCapable;
        this.fileInfos = new HashMap();
        this.selectedFID = (short) 0;
        this.isSelected = false;
        this.isSFIEnabled = z;
        this.fidToSFI = map;
        this.maxReadBinaryLength = 65536;
    }

    @Override // net.sf.scuba.smartcards.FileSystemStructured
    public synchronized FileInfo[] getSelectedPath() throws CardServiceException {
        DefaultFileInfo fileInfo = getFileInfo();
        if (fileInfo == null) {
            return null;
        }
        return new DefaultFileInfo[]{fileInfo};
    }

    @Override // net.sf.scuba.smartcards.FileSystemStructured
    public synchronized void selectFile(short s) throws CardServiceException {
        if (this.selectedFID == s) {
            return;
        }
        this.selectedFID = s;
        this.isSelected = false;
    }

    @Override // net.sf.scuba.smartcards.FileSystemStructured
    public synchronized byte[] readBinary(int i, int i2) throws CardServiceException {
        byte[] bArr;
        byte[] bArrSendReadBinary;
        try {
            try {
                if (this.selectedFID <= 0) {
                    throw new CardServiceException("No file selected");
                }
                DefaultFileInfo fileInfo = getFileInfo();
                if (fileInfo == null) {
                    throw new IllegalStateException("Could not get file info");
                }
                int iMin = Math.min(i2, this.maxReadBinaryLength);
                FragmentBuffer.Fragment smallestUnbufferedFragment = fileInfo.getSmallestUnbufferedFragment(i, iMin);
                if (smallestUnbufferedFragment.getLength() > 0) {
                    boolean z = true;
                    if (this.isSFIEnabled && i < 256) {
                        Byte b = this.fidToSFI.get(Short.valueOf(this.selectedFID));
                        if (b == null) {
                            throw new NumberFormatException("Unknown FID " + Integer.toHexString(this.selectedFID));
                        }
                        bArrSendReadBinary = sendReadBinary((b.byteValue() & 255) | 128, smallestUnbufferedFragment.getOffset(), smallestUnbufferedFragment.getLength(), false);
                        this.isSelected = true;
                    } else {
                        if (!this.isSelected) {
                            sendSelectFile(this.selectedFID);
                            this.isSelected = true;
                        }
                        int offset = smallestUnbufferedFragment.getOffset();
                        int length = smallestUnbufferedFragment.getLength();
                        if (i <= 32767) {
                            z = false;
                        }
                        bArrSendReadBinary = sendReadBinary(offset, length, z);
                    }
                    if (bArrSendReadBinary == null) {
                        throw new IllegalStateException("Could not read bytes");
                    }
                    if (bArrSendReadBinary.length > 0) {
                        fileInfo.addFragment(smallestUnbufferedFragment.getOffset(), bArrSendReadBinary);
                    }
                    if (bArrSendReadBinary.length < smallestUnbufferedFragment.getLength()) {
                        iMin = bArrSendReadBinary.length;
                    }
                }
                bArr = new byte[iMin];
                System.arraycopy(fileInfo.getBuffer(), i, bArr, 0, iMin);
            } catch (Exception e) {
                throw new CardServiceException("Read binary failed on file " + ((Object) (0 == 0 ? Integer.toHexString(this.selectedFID) : null)), e);
            }
        } catch (CardServiceException e2) {
            if ((((short) e2.getSW()) & ISO7816.SW_WRONG_LENGTH) != 26368 || this.maxReadBinaryLength <= 223) {
                throw new CardServiceException("Read binary failed on file " + ((Object) (0 == 0 ? Integer.toHexString(this.selectedFID) : null)), e2);
            }
            this.wrapper = this.oldWrapper;
            this.maxReadBinaryLength = PassportService.DEFAULT_MAX_BLOCKSIZE;
            return new byte[0];
        }
        return bArr;
    }

    private synchronized DefaultFileInfo getFileInfo() throws CardServiceException {
        byte[] bArrSendReadBinary;
        short s = this.selectedFID;
        if (s <= 0) {
            throw new CardServiceException("No file selected");
        }
        DefaultFileInfo defaultFileInfo = this.fileInfos.get(Short.valueOf(s));
        if (defaultFileInfo != null) {
            return defaultFileInfo;
        }
        try {
            if (this.isSFIEnabled) {
                Byte b = this.fidToSFI.get(Short.valueOf(this.selectedFID));
                if (b == null) {
                    throw new NumberFormatException("Unknown FID " + Integer.toHexString(this.selectedFID));
                }
                bArrSendReadBinary = sendReadBinary((b.byteValue() & 255) | 128, 0, 8, false);
                this.isSelected = true;
            } else {
                if (!this.isSelected) {
                    sendSelectFile(this.selectedFID);
                    this.isSelected = true;
                }
                bArrSendReadBinary = sendReadBinary(0, 8, false);
            }
            if (bArrSendReadBinary != null && bArrSendReadBinary.length != 0) {
                int fileLength = getFileLength(this.selectedFID, 8, bArrSendReadBinary);
                if (fileLength < bArrSendReadBinary.length) {
                    bArrSendReadBinary = Arrays.copyOf(bArrSendReadBinary, fileLength);
                }
                DefaultFileInfo defaultFileInfo2 = new DefaultFileInfo(this.selectedFID, fileLength);
                defaultFileInfo2.addFragment(0, bArrSendReadBinary);
                this.fileInfos.put(Short.valueOf(this.selectedFID), defaultFileInfo2);
                return defaultFileInfo2;
            }
            LOGGER.warning("Something is wrong with prefix, prefix = " + Hex.bytesToHexString(bArrSendReadBinary));
            return null;
        } catch (IOException e) {
            throw new CardServiceException("Error getting file info for " + Integer.toHexString(this.selectedFID), e);
        }
    }

    private static int getFileLength(short s, int i, byte[] bArr) throws IOException {
        if (bArr.length < i) {
            return bArr.length;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        TLVInputStream tLVInputStream = new TLVInputStream(byteArrayInputStream);
        try {
            if (tLVInputStream.readTag() != 66) {
                return (bArr.length - byteArrayInputStream.available()) + tLVInputStream.readLength();
            }
            try {
                tLVInputStream.close();
                return 36;
            } catch (IOException e) {
                LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e);
                return 36;
            }
        } finally {
            try {
                tLVInputStream.close();
            } catch (IOException e2) {
                LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e2);
            }
        }
    }

    public synchronized void sendSelectFile(short s) throws CardServiceException {
        this.service.sendSelectFile(this.wrapper, s);
    }

    public synchronized byte[] sendReadBinary(int i, int i2, boolean z) throws CardServiceException {
        APDUWrapper secureMessagingWrapper = this.wrapper;
        if (secureMessagingWrapper instanceof SecureMessagingWrapper) {
            secureMessagingWrapper = SecureMessagingWrapper.getInstance((SecureMessagingWrapper) secureMessagingWrapper);
        }
        this.oldWrapper = secureMessagingWrapper;
        return this.service.sendReadBinary(this.wrapper, -1, i, i2, false, z);
    }

    public synchronized byte[] sendReadBinary(int i, int i2, int i3, boolean z) throws CardServiceException {
        return this.service.sendReadBinary(this.wrapper, i, i2, i3, true, z);
    }

    private static class DefaultFileInfo extends FileInfo implements Serializable {
        private static final long serialVersionUID = 6727369753765119839L;
        private FragmentBuffer buffer;
        private short fid;

        @Override // net.sf.scuba.smartcards.FileInfo
        public short getFID() {
            return this.fid;
        }

        public DefaultFileInfo(short s, int i) {
            this.fid = s;
            this.buffer = new FragmentBuffer(i);
        }

        public byte[] getBuffer() {
            return this.buffer.getBuffer();
        }

        @Override // net.sf.scuba.smartcards.FileInfo
        public int getFileLength() {
            return this.buffer.getLength();
        }

        public String toString() {
            return Integer.toHexString(this.fid);
        }

        public FragmentBuffer.Fragment getSmallestUnbufferedFragment(int i, int i2) {
            return this.buffer.getSmallestUnbufferedFragment(i, i2);
        }

        public void addFragment(int i, byte[] bArr) {
            this.buffer.addFragment(i, bArr);
        }
    }
}
