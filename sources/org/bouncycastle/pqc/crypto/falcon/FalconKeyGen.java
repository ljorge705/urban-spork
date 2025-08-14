package org.bouncycastle.pqc.crypto.falcon;

import com.drew.metadata.exif.makernotes.CasioType2MakernoteDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import kotlin.jvm.internal.CharCompanionObject;
import org.jmrtd.PassportService;
import org.spongycastle.crypto.tls.AlertDescription;

/* loaded from: classes4.dex */
class FalconKeyGen {
    private short[] REV10 = {0, 512, 256, 768, 128, 640, 384, 896, 64, 576, 320, 832, 192, 704, 448, 960, 32, 544, 288, 800, 160, 672, 416, 928, 96, 608, 352, 864, 224, 736, 480, 992, 16, 528, PassportService.EF_DG16, 784, 144, 656, 400, 912, 80, 592, 336, 848, 208, 720, 464, 976, 48, 560, 304, 816, 176, 688, 432, 944, AlertDescription.unrecognized_name, 624, 368, 880, 240, 752, 496, 1008, 8, 520, PassportService.EF_DG8, 776, 136, 648, 392, 904, 72, 584, 328, 840, 200, 712, 456, 968, 40, 552, 296, 808, 168, 680, 424, 936, 104, 616, 360, 872, 232, 744, 488, 1000, 24, 536, 280, 792, 152, 664, 408, 920, 88, 600, 344, 856, 216, 728, 472, 984, 56, 568, 312, 824, 184, 696, 440, 952, 120, 632, 376, 888, 248, 760, 504, 1016, 4, 516, PassportService.EF_DG4, 772, 132, 644, 388, 900, 68, 580, 324, 836, 196, 708, 452, 964, 36, 548, 292, 804, 164, 676, 420, 932, 100, 612, 356, 868, 228, 740, 484, 996, 20, 532, 276, 788, 148, 660, 404, 916, 84, 596, 340, 852, 212, 724, 468, 980, 52, 564, 308, 820, 180, 692, 436, 948, 116, 628, 372, 884, 244, 756, 500, 1012, 12, 524, PassportService.EF_DG12, 780, 140, 652, 396, 908, 76, 588, 332, 844, 204, 716, 460, 972, 44, 556, 300, 812, 172, 684, 428, 940, 108, 620, 364, 876, 236, 748, 492, 1004, 28, 540, 284, 796, 156, 668, 412, 924, 92, 604, 348, 860, 220, 732, 476, 988, 60, 572, 316, 828, 188, 700, 444, 956, 124, 636, 380, 892, 252, 764, 508, 1020, 2, 514, PassportService.EF_DG2, 770, 130, 642, 386, 898, 66, 578, 322, 834, 194, 706, 450, 962, 34, 546, 290, 802, 162, 674, 418, 930, 98, 610, 354, 866, 226, 738, 482, 994, 18, 530, 274, 786, 146, 658, 402, 914, 82, 594, 338, 850, 210, 722, 466, 978, 50, 562, 306, 818, 178, 690, 434, 946, AlertDescription.bad_certificate_hash_value, 626, 370, 882, 242, 754, 498, 1010, 10, 522, PassportService.EF_DG10, 778, 138, 650, 394, 906, 74, 586, 330, 842, 202, 714, 458, 970, 42, 554, 298, 810, 170, 682, 426, 938, 106, 618, 362, 874, 234, 746, 490, 1002, 26, 538, 282, 794, 154, 666, 410, 922, 90, 602, 346, 858, 218, 730, 474, 986, 58, 570, 314, 826, 186, 698, 442, 954, 122, 634, 378, 890, 250, 762, 506, 1018, 6, 518, PassportService.EF_DG6, 774, 134, 646, 390, 902, 70, 582, 326, 838, 198, 710, 454, 966, 38, 550, 294, 806, 166, 678, 422, 934, 102, 614, 358, 870, 230, 742, 486, 998, 22, 534, 278, 790, 150, 662, 406, 918, 86, 598, 342, 854, 214, 726, 470, 982, 54, 566, 310, 822, 182, 694, 438, 950, 118, 630, 374, 886, 246, 758, 502, 1014, 14, 526, PassportService.EF_DG14, 782, 142, 654, 398, 910, 78, 590, 334, 846, 206, 718, 462, 974, 46, 558, 302, 814, 174, 686, 430, 942, AlertDescription.unsupported_extension, 622, 366, 878, 238, 750, 494, 1006, 30, 542, PassportService.EF_COM, 798, 158, 670, 414, 926, 94, 606, 350, 862, 222, 734, 478, 990, 62, 574, 318, 830, 190, 702, 446, 958, 126, 638, 382, 894, 254, 766, 510, 1022, 1, 513, PassportService.EF_DG1, 769, 129, 641, 385, 897, 65, 577, 321, 833, 193, 705, 449, 961, 33, 545, 289, 801, 161, 673, 417, 929, 97, 609, 353, 865, 225, 737, 481, 993, 17, 529, 273, 785, 145, 657, 401, 913, 81, 593, 337, 849, 209, 721, 465, 977, 49, 561, 305, 817, 177, 689, 433, 945, AlertDescription.bad_certificate_status_response, 625, 369, 881, 241, 753, 497, 1009, 9, 521, PassportService.EF_DG9, 777, 137, 649, 393, 905, 73, 585, 329, 841, 201, 713, 457, 969, 41, 553, 297, 809, 169, 681, 425, 937, 105, 617, 361, 873, 233, 745, 489, 1001, 25, 537, 281, 793, 153, 665, 409, 921, 89, 601, 345, 857, 217, 729, 473, 985, 57, 569, 313, 825, 185, 697, 441, 953, 121, 633, 377, 889, 249, 761, 505, 1017, 5, 517, PassportService.EF_DG5, 773, 133, 645, 389, 901, 69, 581, 325, 837, 197, 709, 453, 965, 37, 549, 293, 805, 165, 677, 421, 933, 101, 613, 357, 869, 229, 741, 485, 997, 21, 533, 277, 789, 149, 661, 405, 917, 85, 597, 341, 853, 213, 725, 469, 981, 53, 565, 309, 821, 181, 693, 437, 949, 117, 629, 373, 885, 245, 757, 501, 1013, 13, 525, PassportService.EF_DG13, 781, 141, 653, 397, 909, 77, 589, 333, 845, 205, 717, 461, 973, 45, 557, 301, 813, 173, 685, 429, 941, 109, 621, 365, 877, 237, 749, 493, 1005, 29, 541, 285, 797, 157, 669, 413, 925, 93, 605, 349, 861, 221, 733, 477, 989, 61, 573, 317, 829, 189, 701, 445, 957, 125, 637, 381, 893, 253, 765, 509, 1021, 3, 515, PassportService.EF_DG3, 771, 131, 643, 387, 899, 67, 579, 323, 835, 195, 707, 451, 963, 35, 547, 291, 803, 163, 675, 419, 931, 99, 611, 355, 867, 227, 739, 483, 995, 19, 531, 275, 787, 147, 659, 403, 915, 83, 595, 339, 851, 211, 723, 467, 979, 51, 563, 307, 819, 179, 691, 435, 947, AlertDescription.unknown_psk_identity, 627, 371, 883, 243, 755, 499, 1011, 11, 523, PassportService.EF_DG11, 779, 139, 651, 395, 907, 75, 587, 331, 843, 203, 715, 459, 971, 43, 555, 299, 811, 171, 683, 427, 939, 107, 619, 363, 875, 235, 747, 491, 1003, 27, 539, 283, 795, 155, 667, 411, 923, 91, 603, 347, 859, 219, 731, 475, 987, 59, 571, 315, 827, 187, 699, 443, 955, 123, 635, 379, 891, 251, 763, 507, 1019, 7, 519, PassportService.EF_DG7, 775, 135, 647, 391, 903, 71, 583, 327, 839, 199, 711, 455, 967, 39, 551, 295, 807, 167, 679, 423, 935, 103, 615, 359, 871, 231, 743, 487, 999, 23, 535, 279, 791, 151, 663, 407, 919, 87, 599, 343, 855, 215, 727, 471, 983, 55, 567, 311, 823, 183, 695, 439, 951, 119, 631, 375, 887, 247, 759, 503, 1015, 15, 527, PassportService.EF_DG15, 783, 143, 655, 399, 911, 79, 591, 335, 847, 207, 719, 463, 975, 47, 559, 303, 815, 175, 687, 431, 943, AlertDescription.certificate_unobtainable, 623, 367, 879, 239, 751, 495, 1007, 31, 543, 287, 799, 159, 671, 415, 927, 
    95, 607, 351, 863, 223, 735, 479, 991, 63, 575, 319, 831, 191, 703, 447, 959, 127, 639, 383, 895, 255, 767, 511, 1023};
    final long[] gauss_1024_12289 = {1283868770400643928L, 6416574995475331444L, 4078260278032692663L, 2353523259288686585L, 1227179971273316331L, 575931623374121527L, 242543240509105209L, 91437049221049666L, 30799446349977173L, 9255276791179340L, 2478152334826140L, 590642893610164L, 125206034929641L, 23590435911403L, 3948334035941L, 586753615614L, 77391054539L, 9056793210L, 940121950, 86539696, 7062824, 510971, 32764, 1862, 94, 4, 0};
    final int[] MAX_BL_SMALL = {1, 1, 2, 2, 4, 7, 14, 27, 53, 106, Mp4VideoDirectory.TAG_DEPTH};
    final int[] MAX_BL_LARGE = {2, 2, 5, 7, 12, 21, 40, 78, 157, 308};
    final int[] bitlength_avg = {4, 11, 24, 50, 102, 202, 401, 794, 1577, 3138, 6308};
    final int[] bitlength_std = {0, 1, 1, 1, 1, 2, 4, 5, 8, 13, 25};
    final int DEPTH_INT_FG = 4;
    FPREngine fpr = new FPREngine();
    FalconSmallPrimeList primes = new FalconSmallPrimeList();
    FalconFFT fft = new FalconFFT();
    FalconCodec codec = new FalconCodec();
    FalconVrfy vrfy = new FalconVrfy();

    FalconKeyGen() {
    }

    private static int mkn(int i) {
        return 1 << i;
    }

    private long toUnsignedLong(int i) {
        return i & 4294967295L;
    }

    long get_rng_u64(SHAKE256 shake256) {
        shake256.inner_shake256_extract(new byte[8], 0, 8);
        return ((r1[7] & 255) << 56) | (r1[0] & 255) | ((r1[1] & 255) << 8) | ((r1[2] & 255) << 16) | ((r1[3] & 255) << 24) | ((r1[4] & 255) << 32) | ((r1[5] & 255) << 40) | ((r1[6] & 255) << 48);
    }

    void keygen(SHAKE256 shake256, byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, byte[] bArr4, int i4, short[] sArr, int i5, int i6) {
        int i7;
        int i8;
        byte b;
        int i9 = i;
        byte[] bArr5 = bArr2;
        int i10 = i2;
        int i11 = i6;
        int iMkn = mkn(i6);
        short[] sArr2 = sArr;
        while (true) {
            FalconFPR[] falconFPRArr = new FalconFPR[iMkn * 3];
            poly_small_mkgauss(shake256, bArr, i9, i11);
            poly_small_mkgauss(shake256, bArr5, i10, i11);
            int i12 = 1 << (this.codec.max_fg_bits[i11] - 1);
            int i13 = 0;
            int i14 = 0;
            while (true) {
                if (i14 < iMkn) {
                    byte b2 = bArr[i9 + i14];
                    if (b2 >= i12 || b2 <= (i8 = -i12) || (b = bArr5[i10 + i14]) >= i12 || b <= i8) {
                        break;
                    } else {
                        i14++;
                    }
                } else if (i12 >= 0) {
                    int iPoly_small_sqnorm = poly_small_sqnorm(bArr, i9, i11);
                    int iPoly_small_sqnorm2 = poly_small_sqnorm(bArr5, i10, i11);
                    if ((((-((iPoly_small_sqnorm | iPoly_small_sqnorm2) >>> 31)) | (iPoly_small_sqnorm + iPoly_small_sqnorm2)) & 4294967295L) < 16823) {
                        int i15 = iMkn + iMkn;
                        poly_small_to_fp(falconFPRArr, 0, bArr, i, i6);
                        poly_small_to_fp(falconFPRArr, iMkn, bArr2, i2, i6);
                        this.fft.FFT(falconFPRArr, 0, i11);
                        this.fft.FFT(falconFPRArr, iMkn, i11);
                        this.fft.poly_invnorm2_fft(falconFPRArr, i15, falconFPRArr, 0, falconFPRArr, iMkn, i6);
                        this.fft.poly_adj_fft(falconFPRArr, 0, i11);
                        this.fft.poly_adj_fft(falconFPRArr, iMkn, i11);
                        this.fft.poly_mulconst(falconFPRArr, 0, this.fpr.fpr_q, i11);
                        this.fft.poly_mulconst(falconFPRArr, iMkn, this.fpr.fpr_q, i11);
                        this.fft.poly_mul_autoadj_fft(falconFPRArr, 0, falconFPRArr, i15, i6);
                        this.fft.poly_mul_autoadj_fft(falconFPRArr, iMkn, falconFPRArr, i15, i6);
                        this.fft.iFFT(falconFPRArr, 0, i11);
                        this.fft.iFFT(falconFPRArr, iMkn, i11);
                        FalconFPR falconFPRFpr_add = this.fpr.fpr_zero;
                        for (int i16 = 0; i16 < iMkn; i16++) {
                            FPREngine fPREngine = this.fpr;
                            FalconFPR falconFPRFpr_add2 = fPREngine.fpr_add(falconFPRFpr_add, fPREngine.fpr_sqr(falconFPRArr[i16]));
                            FPREngine fPREngine2 = this.fpr;
                            falconFPRFpr_add = fPREngine2.fpr_add(falconFPRFpr_add2, fPREngine2.fpr_sqr(falconFPRArr[iMkn + i16]));
                        }
                        FPREngine fPREngine3 = this.fpr;
                        if (fPREngine3.fpr_lt(falconFPRFpr_add, fPREngine3.fpr_bnorm_max)) {
                            short[] sArr3 = new short[iMkn * 2];
                            if (sArr2 == null) {
                                sArr2 = sArr3;
                                i7 = 0;
                                i13 = iMkn;
                            } else {
                                i7 = i5;
                            }
                            int i17 = iMkn;
                            int i18 = i11;
                            if (this.vrfy.compute_public(sArr2, i7, bArr, i, bArr2, i2, i6, sArr3, i13) != 0) {
                                if (solve_NTRU(i6, bArr3, i3, bArr4, i4, bArr, i, bArr2, i2, (1 << (this.codec.max_FG_bits[i18] - 1)) - 1, new int[i18 > 2 ? i17 * 28 : i17 * 84], 0) != 0) {
                                    return;
                                }
                            }
                            i9 = i;
                            bArr5 = bArr2;
                            i10 = i2;
                            i11 = i6;
                            iMkn = i17;
                        }
                    }
                }
            }
            i10 = i2;
        }
    }

    void make_fg(int[] iArr, int i, byte[] bArr, int i2, byte[] bArr2, int i3, int i4, int i5, int i6) {
        int iMkn = mkn(i4);
        int i7 = i + iMkn;
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i8 = falconSmallPrimeArr[0].p;
        for (int i9 = 0; i9 < iMkn; i9++) {
            iArr[i + i9] = modp_set(bArr[i2 + i9], i8);
            iArr[i7 + i9] = modp_set(bArr2[i3 + i9], i8);
        }
        if (i5 != 0 || i6 == 0) {
            int i10 = 0;
            while (i10 < i5) {
                int i11 = i10 + 1;
                make_fg_step(iArr, i, i4 - i10, i10, i10 != 0 ? 1 : 0, (i11 < i5 || i6 != 0) ? 1 : 0);
                i10 = i11;
            }
            return;
        }
        int i12 = falconSmallPrimeArr[0].p;
        int iModp_ninv31 = modp_ninv31(i12);
        int i13 = i7 + iMkn;
        modp_mkgm2(iArr, i13, iArr, i13 + iMkn, i4, falconSmallPrimeArr[0].g, i12, iModp_ninv31);
        modp_NTT2(iArr, i, iArr, i13, i4, i12, iModp_ninv31);
        modp_NTT2(iArr, i7, iArr, i13, i4, i12, iModp_ninv31);
    }

    void make_fg_step(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13 = 1 << i2;
        int i14 = i13 >> 1;
        int[] iArr2 = this.MAX_BL_SMALL;
        int i15 = iArr2[i3];
        int i16 = iArr2[i3 + 1];
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i17 = i14 * i16;
        int i18 = i + i17;
        int i19 = i18 + i17;
        int i20 = i13 * i15;
        int i21 = i19 + i20;
        int i22 = i21 + i20;
        int i23 = i22 + i13;
        int i24 = i23 + i13;
        System.arraycopy(iArr, i, iArr, i19, i13 * 2 * i15);
        int i25 = 0;
        while (i25 < i15) {
            int i26 = falconSmallPrimeArr[i25].p;
            int iModp_ninv31 = modp_ninv31(i26);
            int iModp_R2 = modp_R2(i26, iModp_ninv31);
            int i27 = i25;
            int i28 = i19;
            int i29 = i16;
            modp_mkgm2(iArr, i22, iArr, i23, i2, falconSmallPrimeArr[i25].g, i26, iModp_ninv31);
            int i30 = i28 + i27;
            int i31 = i30;
            int i32 = 0;
            while (i32 < i13) {
                iArr[i24 + i32] = iArr[i31];
                i32++;
                i31 += i15;
            }
            if (i4 == 0) {
                modp_NTT2(iArr, i24, iArr, i22, i2, i26, iModp_ninv31);
            }
            int i33 = i + i27;
            int i34 = 0;
            int i35 = i33;
            while (i34 < i14) {
                int i36 = i24 + (i34 << 1);
                iArr[i35] = modp_montymul(modp_montymul(iArr[i36], iArr[i36 + 1], i26, iModp_ninv31), iModp_R2, i26, iModp_ninv31);
                i34++;
                i35 += i29;
            }
            if (i4 != 0) {
                i7 = iModp_R2;
                i8 = iModp_ninv31;
                i9 = i26;
                i6 = i29;
                modp_iNTT2_ext(iArr, i30, i15, iArr, i23, i2, i26, i8);
            } else {
                i6 = i29;
                i7 = iModp_R2;
                i8 = iModp_ninv31;
                i9 = i26;
            }
            int i37 = i21 + i27;
            int i38 = i37;
            int i39 = 0;
            while (i39 < i13) {
                iArr[i24 + i39] = iArr[i38];
                i39++;
                i38 += i15;
            }
            if (i4 == 0) {
                modp_NTT2(iArr, i24, iArr, i22, i2, i9, i8);
            }
            int i40 = i18 + i27;
            int i41 = 0;
            int i42 = i40;
            while (i41 < i14) {
                int i43 = i24 + (i41 << 1);
                int i44 = i8;
                int i45 = i9;
                iArr[i42] = modp_montymul(modp_montymul(iArr[i43], iArr[i43 + 1], i45, i44), i7, i45, i44);
                i41++;
                i42 += i6;
            }
            int i46 = i8;
            int i47 = i9;
            int i48 = i6;
            if (i4 != 0) {
                i11 = i46;
                i12 = i47;
                i10 = i48;
                modp_iNTT2_ext(iArr, i37, i15, iArr, i23, i2, i47, i11);
            } else {
                i10 = i48;
                i11 = i46;
                i12 = i47;
            }
            if (i5 == 0) {
                int i49 = i2 - 1;
                int i50 = i10;
                int i51 = i12;
                int i52 = i11;
                modp_iNTT2_ext(iArr, i33, i50, iArr, i23, i49, i51, i52);
                modp_iNTT2_ext(iArr, i40, i50, iArr, i23, i49, i51, i52);
            }
            i25 = i27 + 1;
            i19 = i28;
            i16 = i10;
        }
        int i53 = i19;
        int i54 = i15;
        zint_rebuild_CRT(iArr, i53, i15, i15, i13, falconSmallPrimeArr, 1, iArr, i22);
        zint_rebuild_CRT(iArr, i21, i54, i54, i13, falconSmallPrimeArr, 1, iArr, i22);
        int i55 = i54;
        int i56 = i16;
        while (i55 < i56) {
            int i57 = falconSmallPrimeArr[i55].p;
            int iModp_ninv312 = modp_ninv31(i57);
            int iModp_R22 = modp_R2(i57, iModp_ninv312);
            int i58 = i54;
            int iModp_Rx = modp_Rx(i58, i57, iModp_ninv312, iModp_R22);
            int i59 = i56;
            modp_mkgm2(iArr, i22, iArr, i23, i2, falconSmallPrimeArr[i55].g, i57, iModp_ninv312);
            int i60 = 0;
            int i61 = i53;
            while (i60 < i13) {
                iArr[i24 + i60] = zint_mod_small_signed(iArr, i61, i58, i57, iModp_ninv312, iModp_R22, iModp_Rx);
                i60++;
                i61 += i58;
            }
            modp_NTT2(iArr, i24, iArr, i22, i2, i57, iModp_ninv312);
            int i62 = i + i55;
            int i63 = i62;
            int i64 = 0;
            while (i64 < i14) {
                int i65 = i24 + (i64 << 1);
                iArr[i63] = modp_montymul(modp_montymul(iArr[i65], iArr[i65 + 1], i57, iModp_ninv312), iModp_R22, i57, iModp_ninv312);
                i64++;
                i63 += i59;
            }
            int i66 = iModp_R22;
            int i67 = iModp_ninv312;
            int i68 = i57;
            int i69 = i21;
            int i70 = 0;
            while (i70 < i13) {
                int i71 = i66;
                iArr[i24 + i70] = zint_mod_small_signed(iArr, i69, i58, i68, i67, i71, iModp_Rx);
                i70++;
                i69 += i58;
                i66 = i71;
                i67 = i67;
                i68 = i68;
            }
            int i72 = i66;
            int i73 = i67;
            int i74 = i68;
            modp_NTT2(iArr, i24, iArr, i22, i2, i74, i73);
            int i75 = i18 + i55;
            int i76 = 0;
            int i77 = i75;
            while (i76 < i14) {
                int i78 = i24 + (i76 << 1);
                iArr[i77] = modp_montymul(modp_montymul(iArr[i78], iArr[i78 + 1], i74, i73), i72, i74, i73);
                i76++;
                i77 += i59;
            }
            if (i5 == 0) {
                int i79 = i2 - 1;
                modp_iNTT2_ext(iArr, i62, i59, iArr, i23, i79, i74, i73);
                modp_iNTT2_ext(iArr, i75, i59, iArr, i23, i79, i74, i73);
            }
            i55++;
            i54 = i58;
            i56 = i59;
        }
    }

    int mkgauss(SHAKE256 shake256, int i) {
        int i2 = 1 << (10 - i);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            long j = get_rng_u64(shake256);
            int i5 = (int) (j >>> 63);
            int i6 = (int) (((j & Long.MAX_VALUE) - this.gauss_1024_12289[0]) >>> 63);
            long j2 = Long.MAX_VALUE & get_rng_u64(shake256);
            int i7 = 1;
            int i8 = 0;
            while (true) {
                long[] jArr = this.gauss_1024_12289;
                if (i7 < jArr.length) {
                    int i9 = ((int) ((j2 - jArr[i7]) >>> 63)) ^ 1;
                    i8 |= (-((i6 ^ 1) & i9)) & i7;
                    i6 |= i9;
                    i7++;
                }
            }
            i3 += ((-i5) ^ i8) + i5;
        }
        return i3;
    }

    void modp_NTT2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5) {
        modp_NTT2_ext(iArr, i, 1, iArr2, i2, i3, i4, i5);
    }

    void modp_NTT2_ext(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        if (i4 == 0) {
            return;
        }
        int iMkn = mkn(i4);
        int i7 = 1;
        int i8 = iMkn;
        while (i7 < iMkn) {
            int i9 = i8 >> 1;
            int i10 = 0;
            int i11 = 0;
            while (i10 < i7) {
                int i12 = iArr2[i3 + i7 + i10];
                int i13 = i + (i11 * i2);
                int i14 = (i9 * i2) + i13;
                int i15 = 0;
                while (i15 < i9) {
                    int i16 = iArr[i13];
                    int iModp_montymul = modp_montymul(iArr[i14], i12, i5, i6);
                    iArr[i13] = modp_add(i16, iModp_montymul, i5);
                    iArr[i14] = modp_sub(i16, iModp_montymul, i5);
                    i15++;
                    i13 += i2;
                    i14 += i2;
                }
                i10++;
                i11 += i8;
            }
            i7 <<= 1;
            i8 = i9;
        }
    }

    int modp_R(int i) {
        return Integer.MIN_VALUE - i;
    }

    int modp_R2(int i, int i2) {
        int iModp_R = modp_R(i);
        int iModp_add = modp_add(iModp_R, iModp_R, i);
        int iModp_montymul = modp_montymul(iModp_add, iModp_add, i, i2);
        int iModp_montymul2 = modp_montymul(iModp_montymul, iModp_montymul, i, i2);
        int iModp_montymul3 = modp_montymul(iModp_montymul2, iModp_montymul2, i, i2);
        int iModp_montymul4 = modp_montymul(iModp_montymul3, iModp_montymul3, i, i2);
        int iModp_montymul5 = modp_montymul(iModp_montymul4, iModp_montymul4, i, i2);
        return (iModp_montymul5 + (i & (-(iModp_montymul5 & 1)))) >>> 1;
    }

    int modp_Rx(int i, int i2, int i3, int i4) {
        int i5 = i - 1;
        int iModp_R = modp_R(i2);
        int i6 = 0;
        while (true) {
            int i7 = 1 << i6;
            if (i7 > i5) {
                return iModp_R;
            }
            if ((i7 & i5) != 0) {
                iModp_R = modp_montymul(iModp_R, i4, i2, i3);
            }
            i4 = modp_montymul(i4, i4, i2, i3);
            i6++;
        }
    }

    int modp_add(int i, int i2, int i3) {
        int i4 = (i + i2) - i3;
        return i4 + ((-(i4 >>> 31)) & i3);
    }

    int modp_div(int i, int i2, int i3, int i4, int i5) {
        int i6 = i3 - 2;
        for (int i7 = 30; i7 >= 0; i7--) {
            int iModp_montymul = modp_montymul(i5, i5, i3, i4);
            i5 = iModp_montymul ^ ((-(1 & (i6 >>> i7))) & (modp_montymul(iModp_montymul, i2, i3, i4) ^ iModp_montymul));
        }
        return modp_montymul(i, modp_montymul(i5, 1, i3, i4), i3, i4);
    }

    void modp_iNTT2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5) {
        modp_iNTT2_ext(iArr, i, 1, iArr2, i2, i3, i4, i5);
    }

    void modp_iNTT2_ext(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        if (i4 == 0) {
            return;
        }
        int iMkn = mkn(i4);
        int i7 = iMkn;
        int i8 = 1;
        while (i7 > 1) {
            i7 >>= 1;
            int i9 = i8 << 1;
            int i10 = 0;
            int i11 = 0;
            while (i10 < i7) {
                int i12 = iArr2[i3 + i7 + i10];
                int i13 = i + (i11 * i2);
                int i14 = (i8 * i2) + i13;
                int i15 = 0;
                while (i15 < i8) {
                    int i16 = iArr[i13];
                    int i17 = iArr[i14];
                    iArr[i13] = modp_add(i16, i17, i5);
                    iArr[i14] = modp_montymul(modp_sub(i16, i17, i5), i12, i5, i6);
                    i15++;
                    i13 += i2;
                    i14 += i2;
                }
                i10++;
                i11 += i9;
            }
            i8 = i9;
        }
        int i18 = 1 << (31 - i4);
        int i19 = i;
        int i20 = 0;
        while (i20 < iMkn) {
            iArr[i19] = modp_montymul(iArr[i19], i18, i5, i6);
            i20++;
            i19 += i2;
        }
    }

    void modp_mkgm2(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4, int i5, int i6) {
        int iMkn = mkn(i3);
        int iModp_R2 = modp_R2(i5, i6);
        int iModp_montymul = modp_montymul(i4, iModp_R2, i5, i6);
        for (int i7 = i3; i7 < 10; i7++) {
            iModp_montymul = modp_montymul(iModp_montymul, iModp_montymul, i5, i6);
        }
        int iModp_div = modp_div(iModp_R2, iModp_montymul, i5, i6, modp_R(i5));
        int i8 = 10 - i3;
        int iModp_R = modp_R(i5);
        int iModp_montymul2 = iModp_R;
        for (int i9 = 0; i9 < iMkn; i9++) {
            short s = this.REV10[i9 << i8];
            iArr[i + s] = iModp_R;
            iArr2[i2 + s] = iModp_montymul2;
            iModp_R = modp_montymul(iModp_R, iModp_montymul, i5, i6);
            iModp_montymul2 = modp_montymul(iModp_montymul2, iModp_div, i5, i6);
        }
    }

    int modp_montymul(int i, int i2, int i3, int i4) {
        long unsignedLong = toUnsignedLong(i) * toUnsignedLong(i2);
        int unsignedLong2 = ((int) ((unsignedLong + (((i4 * unsignedLong) & toUnsignedLong(Integer.MAX_VALUE)) * i3)) >>> 31)) - i3;
        return unsignedLong2 + ((-(unsignedLong2 >>> 31)) & i3);
    }

    int modp_ninv31(int i) {
        int i2 = 2 - i;
        int i3 = i2 * (2 - (i * i2));
        int i4 = i3 * (2 - (i * i3));
        int i5 = i4 * (2 - (i * i4));
        return Integer.MAX_VALUE & (-(i5 * (2 - (i * i5))));
    }

    int modp_norm(int i, int i2) {
        return i - (i2 & (((i - ((i2 + 1) >>> 1)) >>> 31) - 1));
    }

    void modp_poly_rec_res(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int i6 = 1 << (i2 - 1);
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = (i7 << 1) + i;
            iArr[i + i7] = modp_montymul(modp_montymul(iArr[i8], iArr[i8 + 1], i3, i4), i5, i3, i4);
        }
    }

    int modp_set(int i, int i2) {
        return i + (i2 & (-(i >>> 31)));
    }

    int modp_sub(int i, int i2, int i3) {
        int i4 = i - i2;
        return i4 + ((-(i4 >>> 31)) & i3);
    }

    void poly_big_to_fp(FalconFPR[] falconFPRArr, int i, int[] iArr, int i2, int i3, int i4, int i5) {
        int iMkn = mkn(i5);
        if (i3 == 0) {
            for (int i6 = 0; i6 < iMkn; i6++) {
                falconFPRArr[i + i6] = this.fpr.fpr_zero;
            }
            return;
        }
        int i7 = i2;
        int i8 = 0;
        while (i8 < iMkn) {
            int i9 = -(iArr[(i7 + i3) - 1] >>> 30);
            int i10 = i9 >>> 1;
            int i11 = i9 & 1;
            FalconFPR falconFPRFpr_add = this.fpr.fpr_zero;
            FalconFPR falconFPRFpr_mul = this.fpr.fpr_one;
            int i12 = 0;
            while (i12 < i3) {
                int i13 = (iArr[i7 + i12] ^ i10) + i11;
                i11 = i13 >>> 31;
                int i14 = i13 & Integer.MAX_VALUE;
                FPREngine fPREngine = this.fpr;
                falconFPRFpr_add = fPREngine.fpr_add(falconFPRFpr_add, fPREngine.fpr_mul(fPREngine.fpr_of(i14 - ((i14 << 1) & i9)), falconFPRFpr_mul));
                i12++;
                FPREngine fPREngine2 = this.fpr;
                falconFPRFpr_mul = fPREngine2.fpr_mul(falconFPRFpr_mul, fPREngine2.fpr_ptwo31);
            }
            falconFPRArr[i + i8] = falconFPRFpr_add;
            i8++;
            i7 += i4;
        }
    }

    int poly_big_to_small(byte[] bArr, int i, int[] iArr, int i2, int i3, int i4) {
        int iMkn = mkn(i4);
        for (int i5 = 0; i5 < iMkn; i5++) {
            int iZint_one_to_plain = zint_one_to_plain(iArr, i2 + i5);
            if (iZint_one_to_plain < (-i3) || iZint_one_to_plain > i3) {
                return 0;
            }
            bArr[i + i5] = (byte) iZint_one_to_plain;
        }
        return 1;
    }

    void poly_small_mkgauss(SHAKE256 shake256, byte[] bArr, int i, int i2) {
        int iMkgauss;
        int iMkn = mkn(i2);
        int i3 = 0;
        for (int i4 = 0; i4 < iMkn; i4++) {
            while (true) {
                iMkgauss = mkgauss(shake256, i2);
                if (iMkgauss >= -127 && iMkgauss <= 127) {
                    if (i4 != iMkn - 1) {
                        i3 ^= iMkgauss & 1;
                        break;
                    } else if (((iMkgauss & 1) ^ i3) == 0) {
                    }
                }
            }
            bArr[i + i4] = (byte) iMkgauss;
        }
    }

    int poly_small_sqnorm(byte[] bArr, int i, int i2) {
        int iMkn = mkn(i2);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < iMkn; i5++) {
            byte b = bArr[i + i5];
            i3 += b * b;
            i4 |= i3;
        }
        return (-(i4 >>> 31)) | i3;
    }

    void poly_small_to_fp(FalconFPR[] falconFPRArr, int i, byte[] bArr, int i2, int i3) {
        int iMkn = mkn(i3);
        for (int i4 = 0; i4 < iMkn; i4++) {
            falconFPRArr[i + i4] = this.fpr.fpr_of(bArr[i2 + i4]);
        }
    }

    void poly_sub_scaled(int[] iArr, int i, int i2, int i3, int[] iArr2, int i4, int i5, int i6, int[] iArr3, int i7, int i8, int i9, int i10) {
        int iMkn = mkn(i10);
        for (int i11 = 0; i11 < iMkn; i11++) {
            int i12 = -iArr3[i7 + i11];
            int i13 = i + (i11 * i3);
            int i14 = i4;
            for (int i15 = 0; i15 < iMkn; i15++) {
                zint_add_scaled_mul_small(iArr, i13, i2, iArr2, i14, i5, i12, i8, i9);
                if (i11 + i15 == iMkn - 1) {
                    i12 = -i12;
                    i13 = i;
                } else {
                    i13 += i3;
                }
                i14 += i6;
            }
        }
    }

    void poly_sub_scaled_ntt(int[] iArr, int i, int i2, int i3, int[] iArr2, int i4, int i5, int i6, int[] iArr3, int i7, int i8, int i9, int i10, int[] iArr4, int i11) {
        int i12 = i5;
        int iMkn = mkn(i10);
        int i13 = i12 + 1;
        int iMkn2 = i11 + mkn(i10);
        int iMkn3 = iMkn2 + mkn(i10);
        int i14 = iMkn3 + (iMkn * i13);
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i15 = 0;
        while (i15 < i13) {
            int i16 = falconSmallPrimeArr[i15].p;
            int iModp_ninv31 = modp_ninv31(i16);
            int iModp_R2 = modp_R2(i16, iModp_ninv31);
            int iModp_Rx = modp_Rx(i12, i16, iModp_ninv31, iModp_R2);
            int i17 = i15;
            modp_mkgm2(iArr4, i11, iArr4, iMkn2, i10, falconSmallPrimeArr[i15].g, i16, iModp_ninv31);
            for (int i18 = 0; i18 < iMkn; i18++) {
                iArr4[i14 + i18] = modp_set(iArr3[i7 + i18], i16);
            }
            modp_NTT2(iArr4, i14, iArr4, i11, i10, i16, iModp_ninv31);
            int i19 = iMkn3 + i17;
            int i20 = i4;
            int i21 = 0;
            int i22 = i19;
            while (i21 < iMkn) {
                iArr4[i22] = zint_mod_small_signed(iArr2, i20, i5, i16, iModp_ninv31, iModp_R2, iModp_Rx);
                i21++;
                i20 += i6;
                i22 += i13;
            }
            modp_NTT2_ext(iArr4, i19, i13, iArr4, i11, i10, i16, iModp_ninv31);
            int i23 = 0;
            int i24 = i19;
            while (i23 < iMkn) {
                iArr4[i24] = modp_montymul(modp_montymul(iArr4[i14 + i23], iArr4[i24], i16, iModp_ninv31), iModp_R2, i16, iModp_ninv31);
                i23++;
                i24 += i13;
            }
            modp_iNTT2_ext(iArr4, i19, i13, iArr4, iMkn2, i10, i16, iModp_ninv31);
            i15 = i17 + 1;
            i12 = i5;
        }
        zint_rebuild_CRT(iArr4, iMkn3, i13, i13, iMkn, falconSmallPrimeArr, 1, iArr4, i14);
        int i25 = i;
        int i26 = 0;
        while (i26 < iMkn) {
            zint_sub_scaled(iArr, i25, i2, iArr4, iMkn3, i13, i8, i9);
            i26++;
            i25 += i3;
            iMkn3 += i13;
        }
    }

    int solve_NTRU(int i, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4, byte[] bArr4, int i5, int i6, int[] iArr, int i7) {
        byte[] bArr5;
        int i8;
        int iMkn = mkn(i);
        if (solve_NTRU_deepest(i, bArr3, i4, bArr4, i5, iArr, i7) == 0) {
            return 0;
        }
        int i9 = i;
        if (i <= 2) {
            while (true) {
                int i10 = i9 - 1;
                if (i9 <= 0) {
                    break;
                }
                if (solve_NTRU_intermediate(i, bArr3, i4, bArr4, i5, i10, iArr, i7) == 0) {
                    return 0;
                }
                i9 = i10;
            }
        } else {
            while (true) {
                int i11 = i9 - 1;
                if (i9 > 2) {
                    if (solve_NTRU_intermediate(i, bArr3, i4, bArr4, i5, i11, iArr, i7) == 0) {
                        return 0;
                    }
                    i9 = i11;
                } else if (solve_NTRU_binary_depth1(i, bArr3, i4, bArr4, i5, iArr, i7) == 0 || solve_NTRU_binary_depth0(i, bArr3, i4, bArr4, i5, iArr, i7) == 0) {
                    return 0;
                }
            }
        }
        if (bArr2 == null) {
            bArr5 = new byte[iMkn];
            i8 = 0;
        } else {
            bArr5 = bArr2;
            i8 = i3;
        }
        if (poly_big_to_small(bArr, i2, iArr, i7, i6, i) != 0) {
            int i12 = i7 + iMkn;
            if (poly_big_to_small(bArr5, i8, iArr, i12, i6, i) != 0) {
                int i13 = i12 + iMkn;
                int i14 = i13 + iMkn;
                int i15 = i14 + iMkn;
                FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
                int i16 = falconSmallPrimeArr[0].p;
                int iModp_ninv31 = modp_ninv31(i16);
                modp_mkgm2(iArr, i15, iArr, i7, i, falconSmallPrimeArr[0].g, i16, iModp_ninv31);
                for (int i17 = 0; i17 < iMkn; i17++) {
                    iArr[i7 + i17] = modp_set(bArr5[i8 + i17], i16);
                }
                for (int i18 = 0; i18 < iMkn; i18++) {
                    iArr[i12 + i18] = modp_set(bArr3[i4 + i18], i16);
                    iArr[i13 + i18] = modp_set(bArr4[i5 + i18], i16);
                    iArr[i14 + i18] = modp_set(bArr[i2 + i18], i16);
                }
                modp_NTT2(iArr, i12, iArr, i15, i, i16, iModp_ninv31);
                modp_NTT2(iArr, i13, iArr, i15, i, i16, iModp_ninv31);
                modp_NTT2(iArr, i14, iArr, i15, i, i16, iModp_ninv31);
                modp_NTT2(iArr, i7, iArr, i15, i, i16, iModp_ninv31);
                int iModp_montymul = modp_montymul(CasioType2MakernoteDirectory.TAG_SELF_TIMER, 1, i16, iModp_ninv31);
                for (int i19 = 0; i19 < iMkn; i19++) {
                    if (modp_sub(modp_montymul(iArr[i12 + i19], iArr[i7 + i19], i16, iModp_ninv31), modp_montymul(iArr[i13 + i19], iArr[i14 + i19], i16, iModp_ninv31), i16) != iModp_montymul) {
                        return 0;
                    }
                }
                return 1;
            }
        }
        return 0;
    }

    int solve_NTRU_binary_depth0(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int i5 = i4;
        int i6 = 1 << i;
        int i7 = i6 >> 1;
        int i8 = FalconSmallPrimeList.PRIMES[0].p;
        int iModp_ninv31 = modp_ninv31(i8);
        int iModp_R2 = modp_R2(i8, iModp_ninv31);
        int i9 = i5 + i7;
        int i10 = i9 + i7;
        int i11 = i10 + i6;
        int i12 = i11 + i6;
        int i13 = i12 + i6;
        int i14 = iModp_ninv31;
        modp_mkgm2(iArr, i12, iArr, i13, i, FalconSmallPrimeList.PRIMES[0].g, i8, i14);
        for (int i15 = 0; i15 < i7; i15++) {
            int i16 = i5 + i15;
            iArr[i16] = modp_set(zint_one_to_plain(iArr, i16), i8);
            int i17 = i9 + i15;
            iArr[i17] = modp_set(zint_one_to_plain(iArr, i17), i8);
        }
        int i18 = i - 1;
        modp_NTT2(iArr, i4, iArr, i12, i18, i8, i14);
        modp_NTT2(iArr, i9, iArr, i12, i18, i8, i14);
        for (int i19 = 0; i19 < i6; i19++) {
            iArr[i10 + i19] = modp_set(bArr[i2 + i19], i8);
            iArr[i11 + i19] = modp_set(bArr2[i3 + i19], i8);
        }
        modp_NTT2(iArr, i10, iArr, i12, i, i8, i14);
        modp_NTT2(iArr, i11, iArr, i12, i, i8, i14);
        int i20 = 0;
        while (i20 < i6) {
            int i21 = i10 + i20;
            int i22 = iArr[i21];
            int i23 = i21 + 1;
            int i24 = iArr[i23];
            int i25 = i11 + i20;
            int i26 = iArr[i25];
            int i27 = i25 + 1;
            int i28 = iArr[i27];
            int i29 = i20 >> 1;
            int i30 = i7;
            int i31 = i14;
            int iModp_montymul = modp_montymul(iArr[i5 + i29], iModp_R2, i8, i31);
            int i32 = i6;
            int iModp_montymul2 = modp_montymul(iArr[i9 + i29], iModp_R2, i8, i31);
            iArr[i21] = modp_montymul(i28, iModp_montymul, i8, i31);
            iArr[i23] = modp_montymul(i26, iModp_montymul, i8, i31);
            iArr[i25] = modp_montymul(i24, iModp_montymul2, i8, i31);
            iArr[i27] = modp_montymul(i22, iModp_montymul2, i8, i31);
            i20 += 2;
            i6 = i32;
            i7 = i30;
            i5 = i4;
            i14 = i31;
        }
        int i33 = i7;
        int i34 = i14;
        int i35 = i6;
        modp_iNTT2(iArr, i10, iArr, i13, i, i8, i34);
        modp_iNTT2(iArr, i11, iArr, i13, i, i8, i34);
        int i36 = i4 + i35;
        int i37 = i36 + i35;
        System.arraycopy(iArr, i10, iArr, i4, i35 * 2);
        int i38 = i37 + i35;
        int i39 = i38 + i35;
        int i40 = i39 + i35;
        int i41 = i40 + i35;
        modp_mkgm2(iArr, i37, iArr, i38, i, FalconSmallPrimeList.PRIMES[0].g, i8, i34);
        modp_NTT2(iArr, i4, iArr, i37, i, i8, i34);
        modp_NTT2(iArr, i36, iArr, i37, i, i8, i34);
        int iModp_set = modp_set(bArr[i2], i8);
        iArr[i41] = iModp_set;
        iArr[i40] = iModp_set;
        for (int i42 = 1; i42 < i35; i42++) {
            int i43 = i2 + i42;
            iArr[i40 + i42] = modp_set(bArr[i43], i8);
            iArr[(i41 + i35) - i42] = modp_set(-bArr[i43], i8);
        }
        modp_NTT2(iArr, i40, iArr, i37, i, i8, i34);
        modp_NTT2(iArr, i41, iArr, i37, i, i8, i34);
        for (int i44 = 0; i44 < i35; i44++) {
            int iModp_montymul3 = modp_montymul(iArr[i41 + i44], iModp_R2, i8, i34);
            iArr[i38 + i44] = modp_montymul(iModp_montymul3, iArr[i4 + i44], i8, i34);
            iArr[i39 + i44] = modp_montymul(iModp_montymul3, iArr[i40 + i44], i8, i34);
        }
        int iModp_set2 = modp_set(bArr2[i3], i8);
        iArr[i41] = iModp_set2;
        iArr[i40] = iModp_set2;
        for (int i45 = 1; i45 < i35; i45++) {
            int i46 = i3 + i45;
            iArr[i40 + i45] = modp_set(bArr2[i46], i8);
            iArr[(i41 + i35) - i45] = modp_set(-bArr2[i46], i8);
        }
        modp_NTT2(iArr, i40, iArr, i37, i, i8, i34);
        modp_NTT2(iArr, i41, iArr, i37, i, i8, i34);
        for (int i47 = 0; i47 < i35; i47++) {
            int iModp_montymul4 = modp_montymul(iArr[i41 + i47], iModp_R2, i8, i34);
            int i48 = i38 + i47;
            iArr[i48] = modp_add(iArr[i48], modp_montymul(iModp_montymul4, iArr[i36 + i47], i8, i34), i8);
            int i49 = i39 + i47;
            iArr[i49] = modp_add(iArr[i49], modp_montymul(iModp_montymul4, iArr[i40 + i47], i8, i34), i8);
        }
        modp_mkgm2(iArr, i37, iArr, i40, i, FalconSmallPrimeList.PRIMES[0].g, i8, i34);
        modp_iNTT2(iArr, i38, iArr, i40, i, i8, i34);
        modp_iNTT2(iArr, i39, iArr, i40, i, i8, i34);
        for (int i50 = 0; i50 < i35; i50++) {
            int i51 = i38 + i50;
            iArr[i37 + i50] = modp_norm(iArr[i51], i8);
            iArr[i51] = modp_norm(iArr[i39 + i50], i8);
        }
        FalconFPR[] falconFPRArr = new FalconFPR[i35 * 3];
        int i52 = i35 + i35;
        for (int i53 = 0; i53 < i35; i53++) {
            falconFPRArr[i52 + i53] = this.fpr.fpr_of(iArr[i38 + i53]);
        }
        this.fft.FFT(falconFPRArr, i52, i);
        System.arraycopy(falconFPRArr, i52, falconFPRArr, i35, i33);
        int i54 = i35 + i33;
        for (int i55 = 0; i55 < i35; i55++) {
            falconFPRArr[i54 + i55] = this.fpr.fpr_of(iArr[i37 + i55]);
        }
        this.fft.FFT(falconFPRArr, i54, i);
        this.fft.poly_div_autoadj_fft(falconFPRArr, i54, falconFPRArr, i35, i);
        this.fft.iFFT(falconFPRArr, i54, i);
        for (int i56 = 0; i56 < i35; i56++) {
            iArr[i37 + i56] = modp_set((int) this.fpr.fpr_rint(falconFPRArr[i54 + i56]), i8);
        }
        modp_mkgm2(iArr, i38, iArr, i39, i, FalconSmallPrimeList.PRIMES[0].g, i8, i34);
        for (int i57 = 0; i57 < i35; i57++) {
            iArr[i40 + i57] = modp_set(bArr[i2 + i57], i8);
            iArr[i41 + i57] = modp_set(bArr2[i3 + i57], i8);
        }
        modp_NTT2(iArr, i37, iArr, i38, i, i8, i34);
        modp_NTT2(iArr, i40, iArr, i38, i, i8, i34);
        modp_NTT2(iArr, i41, iArr, i38, i, i8, i34);
        for (int i58 = 0; i58 < i35; i58++) {
            int iModp_montymul5 = modp_montymul(iArr[i37 + i58], iModp_R2, i8, i34);
            int i59 = i4 + i58;
            iArr[i59] = modp_sub(iArr[i59], modp_montymul(iModp_montymul5, iArr[i40 + i58], i8, i34), i8);
            int i60 = i36 + i58;
            iArr[i60] = modp_sub(iArr[i60], modp_montymul(iModp_montymul5, iArr[i41 + i58], i8, i34), i8);
        }
        modp_iNTT2(iArr, i4, iArr, i39, i, i8, i34);
        modp_iNTT2(iArr, i36, iArr, i39, i, i8, i34);
        for (int i61 = 0; i61 < i35; i61++) {
            int i62 = i4 + i61;
            iArr[i62] = modp_norm(iArr[i62], i8);
            int i63 = i36 + i61;
            iArr[i63] = modp_norm(iArr[i63], i8);
        }
        return 1;
    }

    int solve_NTRU_binary_depth1(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int i5;
        int i6;
        FalconKeyGen falconKeyGen = this;
        int i7 = i4;
        int i8 = 1 << i;
        int i9 = i - 1;
        int i10 = 1 << i9;
        int i11 = i10 >> 1;
        int[] iArr2 = falconKeyGen.MAX_BL_SMALL;
        int i12 = iArr2[1];
        int i13 = iArr2[2];
        int i14 = falconKeyGen.MAX_BL_LARGE[1];
        int i15 = i13 * i11;
        int i16 = i7 + i15;
        int i17 = i16 + i15;
        int i18 = i14 * i10;
        int i19 = i17 + i18;
        int i20 = 0;
        while (i20 < i14) {
            int i21 = FalconSmallPrimeList.PRIMES[i20].p;
            int iModp_ninv31 = falconKeyGen.modp_ninv31(i21);
            int i22 = i9;
            int iModp_R2 = falconKeyGen.modp_R2(i21, iModp_ninv31);
            int iModp_Rx = falconKeyGen.modp_Rx(i13, i21, iModp_ninv31, iModp_R2);
            int i23 = i17 + i20;
            int i24 = i19 + i20;
            int i25 = i8;
            int i26 = i7;
            int i27 = i16;
            int i28 = 0;
            while (true) {
                i6 = i20;
                if (i28 < i11) {
                    int i29 = i21;
                    int i30 = i11;
                    int i31 = i14;
                    int i32 = i13;
                    iArr[i23] = zint_mod_small_signed(iArr, i26, i13, i29, iModp_ninv31, iModp_R2, iModp_Rx);
                    iArr[i24] = zint_mod_small_signed(iArr, i27, i32, i29, iModp_ninv31, iModp_R2, iModp_Rx);
                    i28++;
                    i26 += i32;
                    i27 += i32;
                    i23 += i31;
                    i24 += i31;
                    i14 = i31;
                    i18 = i18;
                    i17 = i17;
                    i21 = i29;
                    i20 = i6;
                    i11 = i30;
                    i10 = i10;
                    i19 = i19;
                    i13 = i32;
                    i12 = i12;
                }
            }
            i20 = i6 + 1;
            i9 = i22;
            i8 = i25;
            i11 = i11;
            i10 = i10;
            falconKeyGen = this;
        }
        int i33 = i18;
        int i34 = i11;
        int i35 = i8;
        int i36 = i10;
        int i37 = i9;
        int i38 = i14;
        System.arraycopy(iArr, i17, iArr, i7, i33);
        int i39 = i7 + i33;
        System.arraycopy(iArr, i19, iArr, i39, i33);
        int i40 = i33 + i39;
        int i41 = i12;
        int i42 = i41 * i36;
        int i43 = i40 + i42;
        int i44 = i43 + i42;
        int i45 = 0;
        while (i45 < i38) {
            int i46 = FalconSmallPrimeList.PRIMES[i45].p;
            int iModp_ninv312 = modp_ninv31(i46);
            int i47 = i44 + i35;
            int i48 = i47 + i36;
            int i49 = i48 + i35;
            int i50 = i42;
            int i51 = i43;
            int iModp_R22 = modp_R2(i46, iModp_ninv312);
            int i52 = iModp_ninv312;
            int i53 = i40;
            int i54 = i46;
            int i55 = i41;
            int i56 = i45;
            int i57 = i39;
            int i58 = i34;
            int i59 = i38;
            modp_mkgm2(iArr, i44, iArr, i47, i, FalconSmallPrimeList.PRIMES[i45].g, i54, i52);
            int i60 = i35;
            for (int i61 = 0; i61 < i60; i61++) {
                iArr[i48 + i61] = modp_set(bArr[i2 + i61], i54);
                iArr[i49 + i61] = modp_set(bArr2[i3 + i61], i54);
            }
            modp_NTT2(iArr, i48, iArr, i44, i, i54, i52);
            modp_NTT2(iArr, i49, iArr, i44, i, i54, i52);
            int i62 = i;
            int i63 = i37;
            while (i62 > i63) {
                int i64 = i62;
                modp_poly_rec_res(iArr, i48, i62, i54, i52, iModp_R22);
                modp_poly_rec_res(iArr, i49, i64, i54, i52, iModp_R22);
                i62 = i64 - 1;
            }
            int i65 = i44 + i36;
            int i66 = i36;
            System.arraycopy(iArr, i47, iArr, i65, i66);
            int i67 = i65 + i66;
            System.arraycopy(iArr, i48, iArr, i67, i66);
            int i68 = i67 + i66;
            System.arraycopy(iArr, i49, iArr, i68, i66);
            int i69 = i68 + i66;
            int i70 = i69 + i58;
            int i71 = i7 + i56;
            int i72 = i57 + i56;
            int i73 = i71;
            int i74 = i72;
            int i75 = 0;
            while (i75 < i58) {
                iArr[i69 + i75] = iArr[i73];
                iArr[i70 + i75] = iArr[i74];
                i75++;
                i73 += i59;
                i74 += i59;
            }
            int i76 = i - 2;
            int i77 = i66;
            modp_NTT2(iArr, i69, iArr, i44, i76, i54, i52);
            modp_NTT2(iArr, i70, iArr, i44, i76, i54, i52);
            int i78 = i71;
            int i79 = i72;
            int i80 = 0;
            while (i80 < i58) {
                int i81 = i80 << 1;
                int i82 = i67 + i81;
                int i83 = iArr[i82];
                int i84 = iArr[i82 + 1];
                int i85 = i81 + i68;
                int i86 = iArr[i85];
                int i87 = iArr[i85 + 1];
                int i88 = iArr[i69 + i80];
                int i89 = i69;
                int i90 = i54;
                int i91 = i52;
                int i92 = i60;
                int i93 = iModp_R22;
                int iModp_montymul = modp_montymul(i88, i93, i90, i91);
                int i94 = i77;
                int iModp_montymul2 = modp_montymul(iArr[i70 + i80], i93, i90, i91);
                iArr[i78] = modp_montymul(i87, iModp_montymul, i90, i91);
                iArr[i78 + i59] = modp_montymul(i86, iModp_montymul, i90, i91);
                iArr[i79] = modp_montymul(i84, iModp_montymul2, i90, i91);
                iArr[i79 + i59] = modp_montymul(i83, iModp_montymul2, i90, i91);
                i80++;
                int i95 = i59 << 1;
                i78 += i95;
                i79 += i95;
                i54 = i90;
                i69 = i89;
                i77 = i94;
                iModp_R22 = i93;
                i60 = i92;
                i52 = i91;
                i58 = i58;
            }
            int i96 = i77;
            int i97 = i58;
            int i98 = i52;
            int i99 = i54;
            int i100 = i60;
            modp_iNTT2_ext(iArr, i71, i59, iArr, i65, i63, i99, i98);
            modp_iNTT2_ext(iArr, i72, i59, iArr, i65, i63, i99, i98);
            if (i56 < i55) {
                modp_iNTT2(iArr, i67, iArr, i65, i63, i99, i98);
                modp_iNTT2(iArr, i68, iArr, i65, i63, i99, i98);
                int i101 = i53 + i56;
                int i102 = i51 + i56;
                i5 = i96;
                int i103 = 0;
                while (i103 < i5) {
                    iArr[i101] = iArr[i67 + i103];
                    iArr[i102] = iArr[i68 + i103];
                    i103++;
                    i101 += i55;
                    i102 += i55;
                }
            } else {
                i5 = i96;
            }
            i45 = i56 + 1;
            i41 = i55;
            i35 = i100;
            i36 = i5;
            i42 = i50;
            i39 = i57;
            i43 = i51;
            i40 = i53;
            i38 = i59;
            i37 = i63;
            i7 = i4;
            i34 = i97;
        }
        int i104 = i40;
        int i105 = i39;
        int i106 = i37;
        int i107 = i34;
        int i108 = i36;
        int i109 = i38;
        int i110 = i108 << 1;
        zint_rebuild_CRT(iArr, i4, i109, i109, i110, FalconSmallPrimeList.PRIMES, 1, iArr, i44);
        zint_rebuild_CRT(iArr, i104, i41, i41, i110, FalconSmallPrimeList.PRIMES, 1, iArr, i44);
        FalconFPR[] falconFPRArr = new FalconFPR[i108];
        FalconFPR[] falconFPRArr2 = new FalconFPR[i108];
        poly_big_to_fp(falconFPRArr, 0, iArr, i4, i109, i109, i106);
        poly_big_to_fp(falconFPRArr2, 0, iArr, i105, i109, i109, i106);
        System.arraycopy(iArr, i104, iArr, i4, i41 * 2 * i108);
        FalconFPR[] falconFPRArr3 = new FalconFPR[i108];
        FalconFPR[] falconFPRArr4 = new FalconFPR[i108];
        int i111 = i41;
        int i112 = i41;
        poly_big_to_fp(falconFPRArr3, 0, iArr, i4, i111, i112, i106);
        poly_big_to_fp(falconFPRArr4, 0, iArr, i4 + i42, i111, i112, i106);
        this.fft.FFT(falconFPRArr, 0, i106);
        this.fft.FFT(falconFPRArr2, 0, i106);
        this.fft.FFT(falconFPRArr3, 0, i106);
        this.fft.FFT(falconFPRArr4, 0, i106);
        FalconFPR[] falconFPRArr5 = new FalconFPR[i108];
        FalconFPR[] falconFPRArr6 = new FalconFPR[i107];
        this.fft.poly_add_muladj_fft(falconFPRArr5, 0, falconFPRArr, 0, falconFPRArr2, 0, falconFPRArr3, 0, falconFPRArr4, 0, i106);
        this.fft.poly_invnorm2_fft(falconFPRArr6, 0, falconFPRArr3, 0, falconFPRArr4, 0, i106);
        this.fft.poly_mul_autoadj_fft(falconFPRArr5, 0, falconFPRArr6, 0, i106);
        this.fft.iFFT(falconFPRArr5, 0, i106);
        for (int i113 = 0; i113 < i108; i113++) {
            FalconFPR falconFPR = falconFPRArr5[i113];
            FPREngine fPREngine = this.fpr;
            if (fPREngine.fpr_lt(falconFPR, fPREngine.fpr_ptwo63m1)) {
                FPREngine fPREngine2 = this.fpr;
                if (fPREngine2.fpr_lt(fPREngine2.fpr_mtwo63m1, falconFPR)) {
                    FPREngine fPREngine3 = this.fpr;
                    falconFPRArr5[i113] = fPREngine3.fpr_of(fPREngine3.fpr_rint(falconFPR));
                }
            }
            return 0;
        }
        this.fft.FFT(falconFPRArr5, 0, i106);
        this.fft.poly_mul_fft(falconFPRArr3, 0, falconFPRArr5, 0, i106);
        this.fft.poly_mul_fft(falconFPRArr4, 0, falconFPRArr5, 0, i106);
        this.fft.poly_sub(falconFPRArr, 0, falconFPRArr3, 0, i106);
        this.fft.poly_sub(falconFPRArr2, 0, falconFPRArr4, 0, i106);
        this.fft.iFFT(falconFPRArr, 0, i106);
        this.fft.iFFT(falconFPRArr2, 0, i106);
        int i114 = i4 + i108;
        for (int i115 = 0; i115 < i108; i115++) {
            iArr[i4 + i115] = (int) this.fpr.fpr_rint(falconFPRArr[i115]);
            iArr[i114 + i115] = (int) this.fpr.fpr_rint(falconFPRArr2[i115]);
        }
        return 1;
    }

    int solve_NTRU_deepest(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int[] iArr, int i4) {
        int i5 = this.MAX_BL_SMALL[i];
        FalconSmallPrime[] falconSmallPrimeArr = FalconSmallPrimeList.PRIMES;
        int i6 = i4 + i5;
        int i7 = i6 + i5;
        int i8 = i7 + i5;
        int i9 = i8 + i5;
        make_fg(iArr, i7, bArr, i2, bArr2, i3, i, i, 0);
        zint_rebuild_CRT(iArr, i7, i5, i5, 2, falconSmallPrimeArr, 0, iArr, i9);
        return (zint_bezout(iArr, i6, iArr, i4, iArr, i7, iArr, i8, i5, iArr, i9) != 0 && zint_mul_small(iArr, i4, i5, CasioType2MakernoteDirectory.TAG_SELF_TIMER) == 0 && zint_mul_small(iArr, i6, i5, CasioType2MakernoteDirectory.TAG_SELF_TIMER) == 0) ? 1 : 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x048b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x04e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    int solve_NTRU_intermediate(int r40, byte[] r41, int r42, byte[] r43, int r44, int r45, int[] r46, int r47) {
        /*
            Method dump skipped, instructions count: 1288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.falcon.FalconKeyGen.solve_NTRU_intermediate(int, byte[], int, byte[], int, int, int[], int):int");
    }

    void zint_add_mul_small(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = i + i6;
            long unsignedLong = (toUnsignedLong(iArr2[i2 + i6]) * toUnsignedLong(i4)) + toUnsignedLong(iArr[i7]) + toUnsignedLong(i5);
            iArr[i7] = ((int) unsignedLong) & Integer.MAX_VALUE;
            i5 = (int) (unsignedLong >>> 31);
        }
        iArr[i + i3] = i5;
    }

    void zint_add_scaled_mul_small(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6, int i7) {
        if (i4 == 0) {
            return;
        }
        int i8 = (-(iArr2[(i3 + i4) - 1] >>> 30)) >>> 1;
        int i9 = 0;
        int i10 = i6;
        int i11 = 0;
        while (i10 < i2) {
            int i12 = i10 - i6;
            int i13 = i12 < i4 ? iArr2[i3 + i12] : i8;
            int i14 = i + i10;
            long unsignedLong = (toUnsignedLong(i9 | ((i13 << i7) & Integer.MAX_VALUE)) * i5) + toUnsignedLong(iArr[i14]) + i11;
            iArr[i14] = ((int) unsignedLong) & Integer.MAX_VALUE;
            i11 = (int) (unsignedLong >>> 31);
            i10++;
            i9 = i13 >>> (31 - i7);
        }
    }

    int zint_bezout(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int[] iArr4, int i4, int i5, int[] iArr5, int i6) {
        FalconKeyGen falconKeyGen = this;
        int i7 = i5;
        int[] iArr6 = iArr5;
        if (i7 == 0) {
            return 0;
        }
        int i8 = i6 + i7;
        int i9 = i8 + i7;
        int i10 = i9 + i7;
        int iModp_ninv31 = falconKeyGen.modp_ninv31(iArr3[i3]);
        int iModp_ninv312 = falconKeyGen.modp_ninv31(iArr4[i4]);
        System.arraycopy(iArr3, i3, iArr6, i9, i7);
        System.arraycopy(iArr4, i4, iArr6, i10, i7);
        iArr[i] = 1;
        iArr2[i2] = 0;
        for (int i11 = 1; i11 < i7; i11++) {
            iArr[i + i11] = 0;
            iArr2[i2 + i11] = 0;
        }
        System.arraycopy(iArr4, i4, iArr6, i6, i7);
        System.arraycopy(iArr3, i3, iArr6, i8, i7);
        iArr6[i8] = iArr6[i8] - 1;
        int i12 = 30;
        int i13 = (i7 * 62) + 30;
        while (i13 >= i12) {
            int i14 = -1;
            int i15 = -1;
            int i16 = i7;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            while (true) {
                int i21 = i16 - 1;
                if (i16 <= 0) {
                    break;
                }
                int i22 = iArr6[i9 + i21];
                int i23 = iArr6[i10 + i21];
                i18 ^= (i18 ^ i22) & i15;
                i17 ^= (i17 ^ i22) & i14;
                i20 ^= (i20 ^ i23) & i15;
                i19 ^= (i19 ^ i23) & i14;
                int i24 = ((((i22 | i23) + Integer.MAX_VALUE) >>> 31) - 1) & i15;
                i16 = i21;
                int i25 = i15;
                i15 = i24;
                i14 = i25;
            }
            int i26 = i17 | (i18 & i14);
            int i27 = ~i14;
            long unsignedLong = (falconKeyGen.toUnsignedLong(i18 & i27) << 31) + falconKeyGen.toUnsignedLong(i26);
            long unsignedLong2 = (falconKeyGen.toUnsignedLong(i20 & i27) << 31) + falconKeyGen.toUnsignedLong(i19 | (i20 & i14));
            int i28 = iArr6[i9];
            int i29 = iArr6[i10];
            long j = 0;
            long j2 = 0;
            long j3 = 1;
            long j4 = 1;
            int i30 = 0;
            long unsignedLong3 = unsignedLong2;
            long j5 = unsignedLong;
            for (int i31 = 31; i30 < i31; i31 = 31) {
                long j6 = unsignedLong3 - j5;
                int i32 = (int) ((j6 ^ ((j5 ^ unsignedLong3) & (j5 ^ j6))) >>> 63);
                int i33 = (i28 >> i30) & 1;
                int i34 = (i29 >> i30) & 1 & i33;
                int i35 = i13;
                int i36 = i34 & i32;
                int i37 = (~i32) & i34;
                int i38 = (i33 ^ 1) | i36;
                int i39 = i28 - ((-i36) & i29);
                long j7 = j5 - (unsignedLong3 & (-falconKeyGen.toUnsignedLong(i36)));
                long j8 = -i36;
                long j9 = j3 - (j2 & j8);
                long j10 = j - (j4 & j8);
                int i40 = i29 - ((-i37) & i39);
                long j11 = unsignedLong3 - (j7 & (-falconKeyGen.toUnsignedLong(i37)));
                long j12 = -i37;
                long j13 = j2 - (j9 & j12);
                long j14 = j4 - (j10 & j12);
                long j15 = i38;
                long j16 = j15 - 1;
                j3 = j9 + (j9 & j16);
                j = j10 + (j10 & j16);
                j5 = j7 ^ ((j7 ^ (j7 >> 1)) & (-falconKeyGen.toUnsignedLong(i38)));
                i29 = i40 + ((-i38) & i40);
                long j17 = -j15;
                j2 = j13 + (j13 & j17);
                j4 = j14 + (j14 & j17);
                unsignedLong3 = j11 ^ ((j11 ^ (j11 >> 1)) & (falconKeyGen.toUnsignedLong(i38) - 1));
                i30++;
                i28 = i39 + ((i38 - 1) & i39);
                i13 = i35;
            }
            int i41 = i13;
            int i42 = i8;
            int iZint_co_reduce = zint_co_reduce(iArr5, i9, iArr5, i10, i5, j3, j, j2, j4);
            long j18 = -(iZint_co_reduce & 1);
            long j19 = j3 - ((j3 + j3) & j18);
            long j20 = j - ((j + j) & j18);
            long j21 = -(iZint_co_reduce >>> 1);
            long j22 = j2 - ((j2 + j2) & j21);
            long j23 = j4 - ((j4 + j4) & j21);
            zint_co_reduce_mod(iArr, i, iArr5, i6, iArr4, i4, i5, iModp_ninv312, j19, j20, j22, j23);
            zint_co_reduce_mod(iArr2, i2, iArr5, i42, iArr3, i3, i5, iModp_ninv31, j19, j20, j22, j23);
            i13 = i41 - 30;
            falconKeyGen = this;
            i7 = i5;
            iArr6 = iArr5;
            i12 = 30;
            i10 = i10;
            i8 = i42;
            i9 = i9;
        }
        int i43 = i9;
        int i44 = iArr5[i43] ^ 1;
        for (int i45 = 1; i45 < i5; i45++) {
            i44 |= iArr5[i43 + i45];
        }
        return (1 - ((i44 | (-i44)) >>> 31)) & iArr3[i3] & iArr4[i4];
    }

    int zint_co_reduce(int[] iArr, int i, int[] iArr2, int i2, int i3, long j, long j2, long j3, long j4) {
        int i4 = i;
        int[] iArr3 = iArr2;
        int i5 = i2;
        int i6 = i3;
        long j5 = 0;
        int i7 = 0;
        long j6 = 0;
        while (i7 < i6) {
            int i8 = i4 + i7;
            int i9 = i5 + i7;
            long j7 = iArr[i8];
            long j8 = iArr3[i9];
            long j9 = j5 + (j7 * j) + (j8 * j2);
            long j10 = (j7 * j3) + (j8 * j4) + j6;
            if (i7 > 0) {
                iArr[i8 - 1] = ((int) j9) & Integer.MAX_VALUE;
                iArr3 = iArr2;
                iArr3[i9 - 1] = ((int) j10) & Integer.MAX_VALUE;
            } else {
                iArr3 = iArr2;
            }
            j5 = j9 >> 31;
            j6 = j10 >> 31;
            i7++;
            i4 = i;
            i5 = i2;
            i6 = i3;
        }
        int i10 = i6;
        iArr[(i4 + i10) - 1] = (int) j5;
        iArr3[(i2 + i10) - 1] = (int) j6;
        int i11 = (int) (j5 >>> 63);
        int i12 = (int) (j6 >>> 63);
        zint_negate(iArr, i4, i10, i11);
        zint_negate(iArr3, i2, i10, i12);
        return (i12 << 1) | i11;
    }

    void zint_co_reduce_mod(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3, int i4, int i5, long j, long j2, long j3, long j4) {
        char c;
        int i6 = i4;
        long j5 = j;
        long j6 = j2;
        int i7 = iArr[i];
        int i8 = iArr2[i2];
        int i9 = (((((int) j5) * i7) + (((int) j6) * i8)) * i5) & Integer.MAX_VALUE;
        int i10 = (((i7 * ((int) j3)) + (i8 * ((int) j4))) * i5) & Integer.MAX_VALUE;
        long j7 = 0;
        int i11 = 0;
        long j8 = 0;
        while (i11 < i6) {
            int i12 = i + i11;
            int i13 = i2 + i11;
            long j9 = iArr[i12];
            long j10 = iArr2[i13];
            int i14 = i3 + i11;
            long unsignedLong = (j9 * j5) + (j10 * j6) + (iArr3[i14] * toUnsignedLong(i9)) + j7;
            long unsignedLong2 = (j9 * j3) + (j10 * j4) + (iArr3[i14] * toUnsignedLong(i10)) + j8;
            if (i11 > 0) {
                c = CharCompanionObject.MAX_VALUE;
                iArr[i12 - 1] = ((int) unsignedLong) & Integer.MAX_VALUE;
                iArr2[i13 - 1] = ((int) unsignedLong2) & Integer.MAX_VALUE;
            } else {
                c = CharCompanionObject.MAX_VALUE;
            }
            j7 = unsignedLong >> 31;
            j8 = unsignedLong2 >> 31;
            i11++;
            i6 = i4;
            j6 = j2;
            j5 = j;
        }
        long j11 = j8;
        iArr[(i + i4) - 1] = (int) j7;
        iArr2[(i2 + i4) - 1] = (int) j11;
        zint_finish_mod(iArr, i, i4, iArr3, i3, (int) (j7 >>> 63));
        zint_finish_mod(iArr2, i2, i4, iArr3, i3, (int) (j11 >>> 63));
    }

    void zint_finish_mod(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 = ((iArr[i + i6] - iArr2[i3 + i6]) - i5) >>> 31;
        }
        int i7 = (-i4) >>> 1;
        int i8 = -((1 - i5) | i4);
        for (int i9 = 0; i9 < i2; i9++) {
            int i10 = i + i9;
            int i11 = (iArr[i10] - ((iArr2[i3 + i9] ^ i7) & i8)) - i4;
            iArr[i10] = Integer.MAX_VALUE & i11;
            i4 = i11 >>> 31;
        }
    }

    int zint_mod_small_signed(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        if (i2 == 0) {
            return 0;
        }
        return modp_sub(zint_mod_small_unsigned(iArr, i, i2, i3, i4, i5), (-(iArr[(i + i2) - 1] >>> 30)) & i6, i3);
    }

    int zint_mod_small_unsigned(int[] iArr, int i, int i2, int i3, int i4, int i5) {
        int iModp_add = 0;
        while (true) {
            int i6 = i2 - 1;
            if (i2 <= 0) {
                return iModp_add;
            }
            int iModp_montymul = modp_montymul(iModp_add, i5, i3, i4);
            int i7 = iArr[i + i6] - i3;
            iModp_add = modp_add(iModp_montymul, i7 + ((-(i7 >>> 31)) & i3), i3);
            i2 = i6;
        }
    }

    int zint_mul_small(int[] iArr, int i, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i + i5;
            long unsignedLong = (toUnsignedLong(iArr[i6]) * toUnsignedLong(i3)) + i4;
            iArr[i6] = ((int) unsignedLong) & Integer.MAX_VALUE;
            i4 = (int) (unsignedLong >> 31);
        }
        return i4;
    }

    void zint_negate(int[] iArr, int i, int i2, int i3) {
        int i4 = (-i3) >>> 1;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i + i5;
            int i7 = (iArr[i6] ^ i4) + i3;
            iArr[i6] = Integer.MAX_VALUE & i7;
            i3 = i7 >>> 31;
        }
    }

    void zint_norm_zero(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = 0;
        int i5 = i3;
        int i6 = 0;
        while (true) {
            int i7 = i5 - 1;
            if (i5 <= 0) {
                zint_sub(iArr, i, iArr2, i2, i3, i4 >>> 31);
                return;
            }
            int i8 = iArr[i + i7];
            int i9 = iArr2[i2 + i7];
            int i10 = ((i6 << 30) | (i9 >>> 1)) - i8;
            i4 |= ((-(i10 >>> 31)) | ((-i10) >>> 31)) & ((i4 & 1) - 1);
            i5 = i7;
            i6 = i9 & 1;
        }
    }

    int zint_one_to_plain(int[] iArr, int i) {
        int i2 = iArr[i];
        return i2 | ((1073741824 & i2) << 1);
    }

    void zint_rebuild_CRT(int[] iArr, int i, int i2, int i3, int i4, FalconSmallPrime[] falconSmallPrimeArr, int i5, int[] iArr2, int i6) {
        int i7 = 0;
        iArr2[i6] = falconSmallPrimeArr[0].p;
        int i8 = i2;
        int i9 = 1;
        while (i9 < i8) {
            int i10 = falconSmallPrimeArr[i9].p;
            int i11 = falconSmallPrimeArr[i9].s;
            int iModp_ninv31 = modp_ninv31(i10);
            int iModp_R2 = modp_R2(i10, iModp_ninv31);
            int i12 = i;
            int i13 = i7;
            while (i13 < i4) {
                int i14 = i12;
                int i15 = iModp_ninv31;
                zint_add_mul_small(iArr, i14, iArr2, i6, i9, modp_montymul(i11, modp_sub(iArr[i12 + i9], zint_mod_small_unsigned(iArr, i14, i9, i10, iModp_ninv31, iModp_R2), i10), i10, i15));
                i13++;
                i12 += i3;
                iModp_ninv31 = i15;
            }
            iArr2[i6 + i9] = zint_mul_small(iArr2, i6, i9, i10);
            i9++;
            i8 = i2;
            i7 = 0;
        }
        if (i5 != 0) {
            int i16 = i;
            int i17 = 0;
            while (i17 < i4) {
                zint_norm_zero(iArr, i16, iArr2, i6, i2);
                i17++;
                i16 += i3;
            }
        }
    }

    int zint_sub(int[] iArr, int i, int[] iArr2, int i2, int i3, int i4) {
        int i5 = -i4;
        int i6 = 0;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = i + i7;
            int i9 = iArr[i8];
            int i10 = (i9 - iArr2[i2 + i7]) - i6;
            i6 = i10 >>> 31;
            iArr[i8] = i9 ^ (((i10 & Integer.MAX_VALUE) ^ i9) & i5);
        }
        return i6;
    }

    void zint_sub_scaled(int[] iArr, int i, int i2, int[] iArr2, int i3, int i4, int i5, int i6) {
        if (i4 == 0) {
            return;
        }
        int i7 = (-(iArr2[(i3 + i4) - 1] >>> 30)) >>> 1;
        int i8 = 0;
        int i9 = i5;
        int i10 = 0;
        while (i9 < i2) {
            int i11 = i9 - i5;
            int i12 = i11 < i4 ? iArr2[i11 + i3] : i7;
            int i13 = i + i9;
            int i14 = (iArr[i13] - (i8 | ((i12 << i6) & Integer.MAX_VALUE))) - i10;
            iArr[i13] = i14 & Integer.MAX_VALUE;
            i10 = i14 >>> 31;
            i9++;
            i8 = i12 >>> (31 - i6);
        }
    }
}
