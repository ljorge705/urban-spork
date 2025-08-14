package com.drew.metadata.exif;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.drew.imaging.PhotographicConversions;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.Rational;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.oblador.keychain.KeychainModule;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes5.dex */
public abstract class ExifDescriptorBase<T extends Directory> extends TagDescriptor<T> {
    private final boolean _allowDecimalRepresentationOfRationals;

    public ExifDescriptorBase(T t) {
        super(t);
        this._allowDecimalRepresentationOfRationals = true;
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 1) {
            return getInteropIndexDescription();
        }
        if (i == 2) {
            return getInteropVersionDescription();
        }
        if (i == 262) {
            return getPhotometricInterpretationDescription();
        }
        if (i != 263) {
            switch (i) {
                case 254:
                    return getNewSubfileTypeDescription();
                case 255:
                    return getSubfileTypeDescription();
                case 256:
                    return getImageWidthDescription();
                case 257:
                    return getImageHeightDescription();
                case 258:
                    return getBitsPerSampleDescription();
                case 259:
                    return getCompressionDescription();
                default:
                    switch (i) {
                        case 266:
                            return getFillOrderDescription();
                        case 274:
                            return getOrientationDescription();
                        case 296:
                            return getResolutionDescription();
                        case 512:
                            return getJpegProcDescription();
                        case ExifDirectoryBase.TAG_CFA_PATTERN_2 /* 33422 */:
                            return getCfaPattern2Description();
                        case ExifDirectoryBase.TAG_EXPOSURE_TIME /* 33434 */:
                            return getExposureTimeDescription();
                        case ExifDirectoryBase.TAG_FNUMBER /* 33437 */:
                            return getFNumberDescription();
                        case ExifDirectoryBase.TAG_EXPOSURE_PROGRAM /* 34850 */:
                            return getExposureProgramDescription();
                        case ExifDirectoryBase.TAG_ISO_EQUIVALENT /* 34855 */:
                            return getIsoEquivalentDescription();
                        case ExifDirectoryBase.TAG_SENSITIVITY_TYPE /* 34864 */:
                            return getSensitivityTypeRangeDescription();
                        case ExifDirectoryBase.TAG_EXIF_VERSION /* 36864 */:
                            return getExifVersionDescription();
                        case ExifDirectoryBase.TAG_SHUTTER_SPEED /* 37377 */:
                            return getShutterSpeedDescription();
                        case ExifDirectoryBase.TAG_APERTURE /* 37378 */:
                            return getApertureValueDescription();
                        case ExifDirectoryBase.TAG_BRIGHTNESS_VALUE /* 37379 */:
                            return getBrightnessValueDescription();
                        case ExifDirectoryBase.TAG_EXPOSURE_BIAS /* 37380 */:
                            return getExposureBiasDescription();
                        case ExifDirectoryBase.TAG_MAX_APERTURE /* 37381 */:
                            return getMaxApertureValueDescription();
                        case ExifDirectoryBase.TAG_SUBJECT_DISTANCE /* 37382 */:
                            return getSubjectDistanceDescription();
                        case ExifDirectoryBase.TAG_METERING_MODE /* 37383 */:
                            return getMeteringModeDescription();
                        case 37384:
                            return getWhiteBalanceDescription();
                        case ExifDirectoryBase.TAG_FLASH /* 37385 */:
                            return getFlashDescription();
                        case ExifDirectoryBase.TAG_FOCAL_LENGTH /* 37386 */:
                            return getFocalLengthDescription();
                        case ExifDirectoryBase.TAG_USER_COMMENT /* 37510 */:
                            return getUserCommentDescription();
                        case ExifDirectoryBase.TAG_FLASHPIX_VERSION /* 40960 */:
                            return getFlashPixVersionDescription();
                        case 40961:
                            return getColorSpaceDescription();
                        case ExifDirectoryBase.TAG_EXIF_IMAGE_WIDTH /* 40962 */:
                            return getExifImageWidthDescription();
                        case ExifDirectoryBase.TAG_EXIF_IMAGE_HEIGHT /* 40963 */:
                            return getExifImageHeightDescription();
                        case ExifDirectoryBase.TAG_FOCAL_PLANE_X_RESOLUTION /* 41486 */:
                            return getFocalPlaneXResolutionDescription();
                        case ExifDirectoryBase.TAG_FOCAL_PLANE_Y_RESOLUTION /* 41487 */:
                            return getFocalPlaneYResolutionDescription();
                        case ExifDirectoryBase.TAG_FOCAL_PLANE_RESOLUTION_UNIT /* 41488 */:
                            return getFocalPlaneResolutionUnitDescription();
                        case ExifDirectoryBase.TAG_SENSING_METHOD /* 41495 */:
                            return getSensingMethodDescription();
                        case ExifDirectoryBase.TAG_FILE_SOURCE /* 41728 */:
                            return getFileSourceDescription();
                        case ExifDirectoryBase.TAG_SCENE_TYPE /* 41729 */:
                            return getSceneTypeDescription();
                        case ExifDirectoryBase.TAG_CFA_PATTERN /* 41730 */:
                            return getCfaPatternDescription();
                        case ExifDirectoryBase.TAG_CUSTOM_RENDERED /* 41985 */:
                            return getCustomRenderedDescription();
                        case ExifDirectoryBase.TAG_EXPOSURE_MODE /* 41986 */:
                            return getExposureModeDescription();
                        case ExifDirectoryBase.TAG_WHITE_BALANCE_MODE /* 41987 */:
                            return getWhiteBalanceModeDescription();
                        case ExifDirectoryBase.TAG_DIGITAL_ZOOM_RATIO /* 41988 */:
                            return getDigitalZoomRatioDescription();
                        case ExifDirectoryBase.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH /* 41989 */:
                            return get35mmFilmEquivFocalLengthDescription();
                        case ExifDirectoryBase.TAG_SCENE_CAPTURE_TYPE /* 41990 */:
                            return getSceneCaptureTypeDescription();
                        case ExifDirectoryBase.TAG_GAIN_CONTROL /* 41991 */:
                            return getGainControlDescription();
                        case ExifDirectoryBase.TAG_CONTRAST /* 41992 */:
                            return getContrastDescription();
                        case ExifDirectoryBase.TAG_SATURATION /* 41993 */:
                            return getSaturationDescription();
                        case ExifDirectoryBase.TAG_SHARPNESS /* 41994 */:
                            return getSharpnessDescription();
                        case ExifDirectoryBase.TAG_SUBJECT_DISTANCE_RANGE /* 41996 */:
                            return getSubjectDistanceRangeDescription();
                        case ExifDirectoryBase.TAG_LENS_SPECIFICATION /* 42034 */:
                            return getLensSpecificationDescription();
                        default:
                            switch (i) {
                                case 277:
                                    return getSamplesPerPixelDescription();
                                case 278:
                                    return getRowsPerStripDescription();
                                case 279:
                                    return getStripByteCountsDescription();
                                default:
                                    switch (i) {
                                        case 282:
                                            return getXResolutionDescription();
                                        case 283:
                                            return getYResolutionDescription();
                                        case 284:
                                            return getPlanarConfigurationDescription();
                                        default:
                                            switch (i) {
                                                case 530:
                                                    return getYCbCrSubsamplingDescription();
                                                case 531:
                                                    return getYCbCrPositioningDescription();
                                                case 532:
                                                    return getReferenceBlackWhiteDescription();
                                                default:
                                                    switch (i) {
                                                        case ExifDirectoryBase.TAG_COMPONENTS_CONFIGURATION /* 37121 */:
                                                            return getComponentConfigurationDescription();
                                                        case ExifDirectoryBase.TAG_COMPRESSED_AVERAGE_BITS_PER_PIXEL /* 37122 */:
                                                            return getCompressedAverageBitsPerPixelDescription();
                                                        default:
                                                            switch (i) {
                                                                case ExifDirectoryBase.TAG_TEMPERATURE /* 37888 */:
                                                                    return getTemperatureDescription();
                                                                case ExifDirectoryBase.TAG_HUMIDITY /* 37889 */:
                                                                    return getHumidityDescription();
                                                                case ExifDirectoryBase.TAG_PRESSURE /* 37890 */:
                                                                    return getPressureDescription();
                                                                case ExifDirectoryBase.TAG_WATER_DEPTH /* 37891 */:
                                                                    return getWaterDepthDescription();
                                                                case ExifDirectoryBase.TAG_ACCELERATION /* 37892 */:
                                                                    return getAccelerationDescription();
                                                                case ExifDirectoryBase.TAG_CAMERA_ELEVATION_ANGLE /* 37893 */:
                                                                    return getCameraElevationAngleDescription();
                                                                default:
                                                                    switch (i) {
                                                                        case ExifDirectoryBase.TAG_WIN_TITLE /* 40091 */:
                                                                            return getWindowsTitleDescription();
                                                                        case ExifDirectoryBase.TAG_WIN_COMMENT /* 40092 */:
                                                                            return getWindowsCommentDescription();
                                                                        case ExifDirectoryBase.TAG_WIN_AUTHOR /* 40093 */:
                                                                            return getWindowsAuthorDescription();
                                                                        case ExifDirectoryBase.TAG_WIN_KEYWORDS /* 40094 */:
                                                                            return getWindowsKeywordsDescription();
                                                                        case ExifDirectoryBase.TAG_WIN_SUBJECT /* 40095 */:
                                                                            return getWindowsSubjectDescription();
                                                                        default:
                                                                            return super.getDescription(i);
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        return getThresholdingDescription();
    }

    public String getInteropIndexDescription() {
        String string = this._directory.getString(1);
        if (string == null) {
            return null;
        }
        return "R98".equalsIgnoreCase(string.trim()) ? "Recommended Exif Interoperability Rules (ExifR98)" : "Unknown (" + string + ")";
    }

    public String getInteropVersionDescription() {
        return getVersionBytesDescription(2, 2);
    }

    public String getNewSubfileTypeDescription() {
        return getIndexedDescription(254, 0, "Full-resolution image", "Reduced-resolution image", "Single page of multi-page image", "Single page of multi-page reduced-resolution image", "Transparency mask", "Transparency mask of reduced-resolution image", "Transparency mask of multi-page image", "Transparency mask of reduced-resolution multi-page image");
    }

    public String getSubfileTypeDescription() {
        return getIndexedDescription(255, 1, "Full-resolution image", "Reduced-resolution image", "Single page of multi-page image");
    }

    public String getImageWidthDescription() {
        String string = this._directory.getString(256);
        if (string == null) {
            return null;
        }
        return string + " pixels";
    }

    public String getImageHeightDescription() {
        String string = this._directory.getString(257);
        if (string == null) {
            return null;
        }
        return string + " pixels";
    }

    public String getBitsPerSampleDescription() {
        String string = this._directory.getString(258);
        if (string == null) {
            return null;
        }
        return string + " bits/component/pixel";
    }

    public String getCompressionDescription() {
        Integer integer = this._directory.getInteger(259);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 32766) {
            return "Next";
        }
        if (iIntValue == 32767) {
            return "Sony ARW Compressed";
        }
        switch (iIntValue) {
            case 1:
                return "Uncompressed";
            case 2:
                return "CCITT 1D";
            case 3:
                return "T4/Group 3 Fax";
            case 4:
                return "T6/Group 4 Fax";
            case 5:
                return "LZW";
            case 6:
                return "JPEG (old-style)";
            case 7:
                return "JPEG";
            case 8:
                return "Adobe Deflate";
            case 9:
                return "JBIG B&W";
            case 10:
                return "JBIG Color";
            default:
                switch (iIntValue) {
                    case 99:
                        return "JPEG";
                    case 262:
                        return "Kodak 262";
                    case 32809:
                        return "Thunderscan";
                    case 32867:
                        return "Kodak KDC Compressed";
                    case 34661:
                        return "JBIG";
                    case 34715:
                        return "JBIG2 TIFF FX";
                    case ExifInterface.DATA_LOSSY_JPEG /* 34892 */:
                        return "Lossy JPEG";
                    case 65000:
                        return "Kodak DCR Compressed";
                    case 65535:
                        return "Pentax PEF Compressed";
                    default:
                        switch (iIntValue) {
                            case PanasonicMakernoteDirectory.TAG_SCENE_MODE /* 32769 */:
                                return "Packed RAW";
                            case FujifilmMakernoteDirectory.TAG_ORDER_NUMBER /* 32770 */:
                                return "Samsung SRW Compressed";
                            case FujifilmMakernoteDirectory.TAG_FRAME_NUMBER /* 32771 */:
                                return "CCIRLEW";
                            case PanasonicMakernoteDirectory.TAG_WB_RED_LEVEL /* 32772 */:
                                return "Samsung SRW Compressed 2";
                            case 32773:
                                return "PackBits";
                            default:
                                switch (iIntValue) {
                                    case 32895:
                                        return "IT8CTPAD";
                                    case 32896:
                                        return "IT8LW";
                                    case 32897:
                                        return "IT8MP";
                                    case 32898:
                                        return "IT8BL";
                                    default:
                                        switch (iIntValue) {
                                            case 32908:
                                                return "PixarFilm";
                                            case 32909:
                                                return "PixarLog";
                                            default:
                                                switch (iIntValue) {
                                                    case 32946:
                                                        return "Deflate";
                                                    case 32947:
                                                        return "DCS";
                                                    default:
                                                        switch (iIntValue) {
                                                            case 34676:
                                                                return "SGILog";
                                                            case 34677:
                                                                return "SGILog24";
                                                            default:
                                                                switch (iIntValue) {
                                                                    case 34712:
                                                                        return "JPEG 2000";
                                                                    case 34713:
                                                                        return "Nikon NEF Compressed";
                                                                    default:
                                                                        switch (iIntValue) {
                                                                            case 34718:
                                                                                return "Microsoft Document Imaging (MDI) Binary Level Codec";
                                                                            case 34719:
                                                                                return "Microsoft Document Imaging (MDI) Progressive Transform Codec";
                                                                            case 34720:
                                                                                return "Microsoft Document Imaging (MDI) Vector";
                                                                            default:
                                                                                return "Unknown (" + integer + ")";
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public String getPhotometricInterpretationDescription() {
        Integer integer = this._directory.getInteger(262);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 32803) {
            return "Color Filter Array";
        }
        if (iIntValue == 32892) {
            return "Linear Raw";
        }
        switch (iIntValue) {
            case 0:
                return "WhiteIsZero";
            case 1:
                return "BlackIsZero";
            case 2:
                return "RGB";
            case 3:
                return "RGB Palette";
            case 4:
                return "Transparency Mask";
            case 5:
                return "CMYK";
            case 6:
                return "YCbCr";
            default:
                switch (iIntValue) {
                    case 8:
                        return "CIELab";
                    case 9:
                        return "ICCLab";
                    case 10:
                        return "ITULab";
                    default:
                        switch (iIntValue) {
                            case 32844:
                                return "Pixar LogL";
                            case 32845:
                                return "Pixar LogLuv";
                            default:
                                return "Unknown colour space";
                        }
                }
        }
    }

    public String getThresholdingDescription() {
        return getIndexedDescription(263, 1, "No dithering or halftoning", "Ordered dither or halftone", "Randomized dither");
    }

    public String getFillOrderDescription() {
        return getIndexedDescription(266, 1, "Normal", "Reversed");
    }

    public String getOrientationDescription() {
        return super.getOrientationDescription(274);
    }

    public String getSamplesPerPixelDescription() {
        String string = this._directory.getString(277);
        if (string == null) {
            return null;
        }
        return string + " samples/pixel";
    }

    public String getRowsPerStripDescription() {
        String string = this._directory.getString(278);
        if (string == null) {
            return null;
        }
        return string + " rows/strip";
    }

    public String getStripByteCountsDescription() {
        String string = this._directory.getString(279);
        if (string == null) {
            return null;
        }
        return string + " bytes";
    }

    public String getXResolutionDescription() {
        Rational rational = this._directory.getRational(282);
        if (rational == null) {
            return null;
        }
        String resolutionDescription = getResolutionDescription();
        Object[] objArr = new Object[2];
        objArr[0] = rational.toSimpleString(true);
        objArr[1] = resolutionDescription == null ? "unit" : resolutionDescription.toLowerCase();
        return String.format("%s dots per %s", objArr);
    }

    public String getYResolutionDescription() {
        Rational rational = this._directory.getRational(283);
        if (rational == null) {
            return null;
        }
        String resolutionDescription = getResolutionDescription();
        Object[] objArr = new Object[2];
        objArr[0] = rational.toSimpleString(true);
        objArr[1] = resolutionDescription == null ? "unit" : resolutionDescription.toLowerCase();
        return String.format("%s dots per %s", objArr);
    }

    public String getPlanarConfigurationDescription() {
        return getIndexedDescription(284, 1, "Chunky (contiguous for each subsampling pixel)", "Separate (Y-plane/Cb-plane/Cr-plane format)");
    }

    public String getResolutionDescription() {
        return getIndexedDescription(296, 1, "(No unit)", "Inch", "cm");
    }

    public String getJpegProcDescription() {
        Integer integer = this._directory.getInteger(512);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        return iIntValue != 1 ? iIntValue != 14 ? "Unknown (" + integer + ")" : "Lossless" : "Baseline";
    }

    public String getYCbCrSubsamplingDescription() {
        int[] intArray = this._directory.getIntArray(530);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        int i = intArray[0];
        return (i == 2 && intArray[1] == 1) ? "YCbCr4:2:2" : (i == 2 && intArray[1] == 2) ? "YCbCr4:2:0" : "(Unknown)";
    }

    public String getYCbCrPositioningDescription() {
        return getIndexedDescription(531, 1, "Center of pixel array", "Datum point");
    }

    public String getReferenceBlackWhiteDescription() {
        int[] intArray = this._directory.getIntArray(532);
        if (intArray == null || intArray.length < 6) {
            Object object = this._directory.getObject(532);
            if (object == null || !(object instanceof long[])) {
                return null;
            }
            long[] jArr = (long[]) object;
            if (jArr.length < 6) {
                return null;
            }
            int[] iArr = new int[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                iArr[i] = (int) jArr[i];
            }
            intArray = iArr;
        }
        int i2 = intArray[0];
        int i3 = intArray[1];
        return String.format("[%d,%d,%d] [%d,%d,%d]", Integer.valueOf(i2), Integer.valueOf(intArray[2]), Integer.valueOf(intArray[4]), Integer.valueOf(i3), Integer.valueOf(intArray[3]), Integer.valueOf(intArray[5]));
    }

    public String getCfaPattern2Description() {
        byte[] byteArray = this._directory.getByteArray(ExifDirectoryBase.TAG_CFA_PATTERN_2);
        if (byteArray == null) {
            return null;
        }
        int[] intArray = this._directory.getIntArray(ExifDirectoryBase.TAG_CFA_REPEAT_PATTERN_DIM);
        if (intArray == null) {
            return String.format("Repeat Pattern not found for CFAPattern (%s)", super.getDescription(ExifDirectoryBase.TAG_CFA_PATTERN_2));
        }
        if (intArray.length == 2) {
            int length = byteArray.length;
            int i = intArray[0];
            int i2 = intArray[1];
            if (length == i * i2) {
                int[] iArr = new int[byteArray.length + 2];
                iArr[0] = i;
                iArr[1] = i2;
                for (int i3 = 0; i3 < byteArray.length; i3++) {
                    iArr[i3 + 2] = byteArray[i3] & 255;
                }
                return formatCFAPattern(iArr);
            }
        }
        return String.format("Unknown Pattern (%s)", super.getDescription(ExifDirectoryBase.TAG_CFA_PATTERN_2));
    }

    private static String formatCFAPattern(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        if (iArr.length < 2) {
            return "<truncated data>";
        }
        int i = iArr[0];
        if (i == 0 && iArr[1] == 0) {
            return "<zero pattern size>";
        }
        int i2 = i * iArr[1];
        int i3 = i2 + 2;
        if (i3 > iArr.length) {
            return "<invalid pattern size>";
        }
        String[] strArr = {"Red", "Green", "Blue", "Cyan", "Magenta", "Yellow", "White"};
        StringBuilder sb = new StringBuilder("[");
        for (int i4 = 2; i4 < i3; i4++) {
            int i5 = iArr[i4];
            if (i5 <= 6) {
                sb.append(strArr[i5]);
            } else {
                sb.append("Unknown");
            }
            if ((i4 - 2) % iArr[1] == 0) {
                sb.append(Constants.SEPARATOR_COMMA);
            } else if (i4 != i2 + 1) {
                sb.append("][");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public String getExposureTimeDescription() {
        String string = this._directory.getString(ExifDirectoryBase.TAG_EXPOSURE_TIME);
        if (string == null) {
            return null;
        }
        return string + " sec";
    }

    public String getFNumberDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_FNUMBER);
        if (rational == null) {
            return null;
        }
        return getFStopDescription(rational.doubleValue());
    }

    public String getExposureProgramDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_EXPOSURE_PROGRAM, 1, "Manual control", "Program normal", "Aperture priority", "Shutter priority", "Program creative (slow program)", "Program action (high-speed program)", "Portrait mode", "Landscape mode");
    }

    public String getIsoEquivalentDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_ISO_EQUIVALENT);
        if (integer != null) {
            return Integer.toString(integer.intValue());
        }
        return null;
    }

    public String getSensitivityTypeRangeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SENSITIVITY_TYPE, "Unknown", "Standard Output Sensitivity", "Recommended Exposure Index", "ISO Speed", "Standard Output Sensitivity and Recommended Exposure Index", "Standard Output Sensitivity and ISO Speed", "Recommended Exposure Index and ISO Speed", "Standard Output Sensitivity, Recommended Exposure Index and ISO Speed");
    }

    public String getExifVersionDescription() {
        return getVersionBytesDescription(ExifDirectoryBase.TAG_EXIF_VERSION, 2);
    }

    public String getComponentConfigurationDescription() {
        int[] intArray = this._directory.getIntArray(ExifDirectoryBase.TAG_COMPONENTS_CONFIGURATION);
        if (intArray == null) {
            return null;
        }
        String[] strArr = {"", "Y", "Cb", "Cr", "R", "G", "B"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(4, intArray.length); i++) {
            int i2 = intArray[i];
            if (i2 > 0 && i2 < 7) {
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }

    public String getCompressedAverageBitsPerPixelDescription() {
        StringBuilder sbAppend;
        String str;
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_COMPRESSED_AVERAGE_BITS_PER_PIXEL);
        if (rational == null) {
            return null;
        }
        String simpleString = rational.toSimpleString(true);
        if (rational.isInteger() && rational.intValue() == 1) {
            sbAppend = new StringBuilder().append(simpleString);
            str = " bit/pixel";
        } else {
            sbAppend = new StringBuilder().append(simpleString);
            str = " bits/pixel";
        }
        return sbAppend.append(str).toString();
    }

    public String getShutterSpeedDescription() {
        return super.getShutterSpeedDescription(ExifDirectoryBase.TAG_SHUTTER_SPEED);
    }

    public String getApertureValueDescription() {
        Double doubleObject = this._directory.getDoubleObject(ExifDirectoryBase.TAG_APERTURE);
        if (doubleObject == null) {
            return null;
        }
        return getFStopDescription(PhotographicConversions.apertureToFStop(doubleObject.doubleValue()));
    }

    public String getBrightnessValueDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_BRIGHTNESS_VALUE);
        if (rational == null) {
            return null;
        }
        return rational.getNumerator() == 4294967295L ? "Unknown" : new DecimalFormat("0.0##").format(rational.doubleValue());
    }

    public String getExposureBiasDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_EXPOSURE_BIAS);
        if (rational == null) {
            return null;
        }
        return rational.toSimpleString(true) + " EV";
    }

    public String getMaxApertureValueDescription() {
        Double doubleObject = this._directory.getDoubleObject(ExifDirectoryBase.TAG_MAX_APERTURE);
        if (doubleObject == null) {
            return null;
        }
        return getFStopDescription(PhotographicConversions.apertureToFStop(doubleObject.doubleValue()));
    }

    public String getSubjectDistanceDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_SUBJECT_DISTANCE);
        if (rational == null) {
            return null;
        }
        if (rational.getNumerator() == 4294967295L) {
            return "Infinity";
        }
        if (rational.getNumerator() == 0) {
            return "Unknown";
        }
        return new DecimalFormat("0.0##").format(rational.doubleValue()) + " metres";
    }

    public String getMeteringModeDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_METERING_MODE);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 255) {
            return "(Other)";
        }
        switch (iIntValue) {
            case 0:
                return "Unknown";
            case 1:
                return "Average";
            case 2:
                return "Center weighted average";
            case 3:
                return "Spot";
            case 4:
                return "Multi-spot";
            case 5:
                return "Multi-segment";
            case 6:
                return "Partial";
            default:
                return "Unknown (" + integer + ")";
        }
    }

    public String getWhiteBalanceDescription() {
        Integer integer = this._directory.getInteger(37384);
        if (integer == null) {
            return null;
        }
        int iIntValue = integer.intValue();
        if (iIntValue == 0) {
            return "Unknown";
        }
        if (iIntValue == 1) {
            return "Daylight";
        }
        if (iIntValue == 2) {
            return "Florescent";
        }
        if (iIntValue == 3) {
            return "Tungsten";
        }
        if (iIntValue == 4) {
            return ExifInterface.TAG_FLASH;
        }
        if (iIntValue == 255) {
            return "(Other)";
        }
        switch (iIntValue) {
            case 9:
                return "Fine Weather";
            case 10:
                return "Cloudy";
            case 11:
                return "Shade";
            case 12:
                return "Daylight Fluorescent";
            case 13:
                return "Day White Fluorescent";
            case 14:
                return "Cool White Fluorescent";
            case 15:
                return "White Fluorescent";
            case 16:
                return "Warm White Fluorescent";
            case 17:
                return "Standard light";
            case 18:
                return "Standard light (B)";
            case 19:
                return "Standard light (C)";
            case 20:
                return "D55";
            case 21:
                return "D65";
            case 22:
                return "D75";
            case 23:
                return "D50";
            case 24:
                return "Studio Tungsten";
            default:
                return "Unknown (" + integer + ")";
        }
    }

    public String getFlashDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_FLASH);
        if (integer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if ((integer.intValue() & 1) != 0) {
            sb.append("Flash fired");
        } else {
            sb.append("Flash did not fire");
        }
        if ((integer.intValue() & 4) != 0) {
            if ((integer.intValue() & 2) != 0) {
                sb.append(", return detected");
            } else {
                sb.append(", return not detected");
            }
        }
        if ((integer.intValue() & 16) != 0 && (integer.intValue() & 15) != 0) {
            sb.append(", auto");
        }
        if ((integer.intValue() & 64) != 0) {
            sb.append(", red-eye reduction");
        }
        return sb.toString();
    }

    public String getFocalLengthDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_FOCAL_LENGTH);
        if (rational == null) {
            return null;
        }
        return getFocalLengthDescription(rational.doubleValue());
    }

    public String getUserCommentDescription() {
        return getEncodedTextDescription(ExifDirectoryBase.TAG_USER_COMMENT);
    }

    public String getTemperatureDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_TEMPERATURE);
        if (rational == null) {
            return null;
        }
        if (rational.getDenominator() == 4294967295L) {
            return "Unknown";
        }
        return new DecimalFormat("0.0").format(rational.doubleValue()) + " °C";
    }

    public String getHumidityDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_HUMIDITY);
        if (rational == null) {
            return null;
        }
        if (rational.getDenominator() == 4294967295L) {
            return "Unknown";
        }
        return new DecimalFormat("0.0").format(rational.doubleValue()) + " %";
    }

    public String getPressureDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_PRESSURE);
        if (rational == null) {
            return null;
        }
        if (rational.getDenominator() == 4294967295L) {
            return "Unknown";
        }
        return new DecimalFormat("0.0").format(rational.doubleValue()) + " hPa";
    }

    public String getWaterDepthDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_WATER_DEPTH);
        if (rational == null) {
            return null;
        }
        if (rational.getDenominator() == 4294967295L) {
            return "Unknown";
        }
        return new DecimalFormat("0.0##").format(rational.doubleValue()) + " metres";
    }

    public String getAccelerationDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_ACCELERATION);
        if (rational == null) {
            return null;
        }
        if (rational.getDenominator() == 4294967295L) {
            return "Unknown";
        }
        return new DecimalFormat("0.0##").format(rational.doubleValue()) + " mGal";
    }

    public String getCameraElevationAngleDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_CAMERA_ELEVATION_ANGLE);
        if (rational == null) {
            return null;
        }
        if (rational.getDenominator() == 4294967295L) {
            return "Unknown";
        }
        return new DecimalFormat("0.##").format(rational.doubleValue()) + " degrees";
    }

    private String getUnicodeDescription(int i) {
        byte[] byteArray = this._directory.getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        try {
            return new String(byteArray, "UTF-16LE").trim();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public String getWindowsTitleDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_TITLE);
    }

    public String getWindowsCommentDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_COMMENT);
    }

    public String getWindowsAuthorDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_AUTHOR);
    }

    public String getWindowsKeywordsDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_KEYWORDS);
    }

    public String getWindowsSubjectDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_SUBJECT);
    }

    public String getFlashPixVersionDescription() {
        return getVersionBytesDescription(ExifDirectoryBase.TAG_FLASHPIX_VERSION, 2);
    }

    public String getColorSpaceDescription() {
        Integer integer = this._directory.getInteger(40961);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 1 ? "sRGB" : integer.intValue() == 65535 ? "Undefined" : "Unknown (" + integer + ")";
    }

    public String getExifImageWidthDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_EXIF_IMAGE_WIDTH);
        if (integer == null) {
            return null;
        }
        return integer + " pixels";
    }

    public String getExifImageHeightDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_EXIF_IMAGE_HEIGHT);
        if (integer == null) {
            return null;
        }
        return integer + " pixels";
    }

    public String getFocalPlaneXResolutionDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_FOCAL_PLANE_X_RESOLUTION);
        if (rational == null) {
            return null;
        }
        String focalPlaneResolutionUnitDescription = getFocalPlaneResolutionUnitDescription();
        return rational.getReciprocal().toSimpleString(true) + (focalPlaneResolutionUnitDescription == null ? "" : StringUtils.SPACE + focalPlaneResolutionUnitDescription.toLowerCase());
    }

    public String getFocalPlaneYResolutionDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_FOCAL_PLANE_Y_RESOLUTION);
        if (rational == null) {
            return null;
        }
        String focalPlaneResolutionUnitDescription = getFocalPlaneResolutionUnitDescription();
        return rational.getReciprocal().toSimpleString(true) + (focalPlaneResolutionUnitDescription == null ? "" : StringUtils.SPACE + focalPlaneResolutionUnitDescription.toLowerCase());
    }

    public String getFocalPlaneResolutionUnitDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_FOCAL_PLANE_RESOLUTION_UNIT, 1, "(No unit)", "Inches", "cm");
    }

    public String getSensingMethodDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SENSING_METHOD, 1, "(Not defined)", "One-chip color area sensor", "Two-chip color area sensor", "Three-chip color area sensor", "Color sequential area sensor", null, "Trilinear sensor", "Color sequential linear sensor");
    }

    public String getFileSourceDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_FILE_SOURCE, 1, "Film Scanner", "Reflection Print Scanner", "Digital Still Camera (DSC)");
    }

    public String getSceneTypeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SCENE_TYPE, 1, "Directly photographed image");
    }

    public String getCfaPatternDescription() {
        return formatCFAPattern(decodeCfaPattern(ExifDirectoryBase.TAG_CFA_PATTERN));
    }

    private int[] decodeCfaPattern(int i) {
        byte[] byteArray = this._directory.getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        if (byteArray.length < 4) {
            int[] iArr = new int[byteArray.length];
            for (int i2 = 0; i2 < byteArray.length; i2++) {
                iArr[i2] = byteArray[i2];
            }
            return iArr;
        }
        int[] iArr2 = new int[byteArray.length - 2];
        try {
            ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
            short int16 = byteArrayReader.getInt16(0);
            short int162 = byteArrayReader.getInt16(2);
            Boolean bool = false;
            if ((int16 * int162) + 2 > byteArray.length) {
                byteArrayReader.setMotorolaByteOrder(!byteArrayReader.isMotorolaByteOrder());
                int16 = byteArrayReader.getInt16(0);
                int162 = byteArrayReader.getInt16(2);
                if (byteArray.length >= (int16 * int162) + 2) {
                    bool = true;
                }
            } else {
                bool = true;
            }
            if (bool.booleanValue()) {
                iArr2[0] = int16;
                iArr2[1] = int162;
                for (int i3 = 4; i3 < byteArray.length; i3++) {
                    iArr2[i3 - 2] = byteArrayReader.getInt8(i3);
                }
            }
        } catch (IOException e) {
            this._directory.addError("IO exception processing data: " + e.getMessage());
        }
        return iArr2;
    }

    public String getCustomRenderedDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_CUSTOM_RENDERED, "Normal process", "Custom process");
    }

    public String getExposureModeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_EXPOSURE_MODE, "Auto exposure", "Manual exposure", "Auto bracket");
    }

    public String getWhiteBalanceModeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_WHITE_BALANCE_MODE, "Auto white balance", "Manual white balance");
    }

    public String getDigitalZoomRatioDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_DIGITAL_ZOOM_RATIO);
        if (rational == null) {
            return null;
        }
        return rational.getNumerator() == 0 ? "Digital zoom not used" : new DecimalFormat("0.#").format(rational.doubleValue());
    }

    public String get35mmFilmEquivFocalLengthDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 0 ? "Unknown" : getFocalLengthDescription(integer.intValue());
    }

    public String getSceneCaptureTypeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SCENE_CAPTURE_TYPE, "Standard", "Landscape", "Portrait", "Night scene");
    }

    public String getGainControlDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_GAIN_CONTROL, KeychainModule.AccessControl.NONE, "Low gain up", "Low gain down", "High gain up", "High gain down");
    }

    public String getContrastDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_CONTRAST, KeychainModule.AccessControl.NONE, "Soft", "Hard");
    }

    public String getSaturationDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SATURATION, KeychainModule.AccessControl.NONE, "Low saturation", "High saturation");
    }

    public String getSharpnessDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SHARPNESS, KeychainModule.AccessControl.NONE, "Low", "Hard");
    }

    public String getSubjectDistanceRangeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SUBJECT_DISTANCE_RANGE, "Unknown", "Macro", "Close view", "Distant view");
    }

    public String getLensSpecificationDescription() {
        return getLensSpecificationDescription(ExifDirectoryBase.TAG_LENS_SPECIFICATION);
    }
}
