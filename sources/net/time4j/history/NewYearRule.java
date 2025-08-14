package net.time4j.history;

/* loaded from: classes4.dex */
public enum NewYearRule {
    BEGIN_OF_JANUARY { // from class: net.time4j.history.NewYearRule.1
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            return HistoricDate.of(historicEra, i, 1, 1);
        }

        @Override // net.time4j.history.NewYearRule
        int displayedYear(NewYearStrategy newYearStrategy, HistoricDate historicDate) {
            return historicDate.getYearOfEra();
        }
    },
    BEGIN_OF_MARCH { // from class: net.time4j.history.NewYearRule.2
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            return HistoricDate.of(historicEra, i, 3, 1);
        }
    },
    BEGIN_OF_SEPTEMBER { // from class: net.time4j.history.NewYearRule.3
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            return HistoricDate.of(historicEra, i - 1, 9, 1);
        }

        @Override // net.time4j.history.NewYearRule
        int displayedYear(NewYearStrategy newYearStrategy, HistoricDate historicDate) {
            HistoricEra era = historicDate.getEra();
            int yearOfEra = historicDate.getYearOfEra();
            int i = yearOfEra + 1;
            return historicDate.compareTo(newYearStrategy.newYear(era, i)) >= 0 ? i : yearOfEra;
        }
    },
    CHRISTMAS_STYLE { // from class: net.time4j.history.NewYearRule.4
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            return HistoricDate.of(historicEra, i - 1, 12, 25);
        }

        @Override // net.time4j.history.NewYearRule
        int displayedYear(NewYearStrategy newYearStrategy, HistoricDate historicDate) {
            int yearOfEra = historicDate.getYearOfEra();
            int i = yearOfEra + 1;
            return historicDate.compareTo(newYearStrategy.newYear(historicDate.getEra(), i)) >= 0 ? i : yearOfEra;
        }
    },
    EASTER_STYLE { // from class: net.time4j.history.NewYearRule.5
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            int i2;
            int iMarchDay = Computus.EASTERN.marchDay(historicEra.annoDomini(i));
            int i3 = iMarchDay - 1;
            if (i3 > 31) {
                i3 = iMarchDay - 32;
                i2 = 4;
            } else {
                i2 = 3;
            }
            return HistoricDate.of(historicEra, i, i2, i3);
        }
    },
    GOOD_FRIDAY { // from class: net.time4j.history.NewYearRule.6
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            int i2;
            int iMarchDay = Computus.EASTERN.marchDay(historicEra.annoDomini(i));
            int i3 = iMarchDay - 2;
            if (i3 > 31) {
                i3 = iMarchDay - 33;
                i2 = 4;
            } else {
                i2 = 3;
            }
            return HistoricDate.of(historicEra, i, i2, i3);
        }
    },
    MARIA_ANUNCIATA { // from class: net.time4j.history.NewYearRule.7
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            return HistoricDate.of(historicEra, i, 3, 25);
        }
    },
    CALCULUS_PISANUS { // from class: net.time4j.history.NewYearRule.8
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            return MARIA_ANUNCIATA.newYear(historicEra, i + 1);
        }

        @Override // net.time4j.history.NewYearRule
        int displayedYear(NewYearStrategy newYearStrategy, HistoricDate historicDate) {
            int yearOfEra = historicDate.getYearOfEra();
            int i = yearOfEra - 1;
            return historicDate.compareTo(newYear(historicDate.getEra(), i)) < 0 ? yearOfEra - 2 : i;
        }

        @Override // net.time4j.history.NewYearRule
        int standardYear(boolean z, NewYearStrategy newYearStrategy, HistoricEra historicEra, int i, int i2, int i3) {
            return MARIA_ANUNCIATA.standardYear(z, newYearStrategy, historicEra, i + 1, i2, i3);
        }
    },
    EPIPHANY { // from class: net.time4j.history.NewYearRule.9
        @Override // net.time4j.history.NewYearRule
        HistoricDate newYear(HistoricEra historicEra, int i) {
            return HistoricDate.of(historicEra, i, 1, 6);
        }
    };

    private static final int COUNCIL_OF_TOURS = 567;

    abstract HistoricDate newYear(HistoricEra historicEra, int i);

    public NewYearStrategy until(int i) {
        if (i <= 567) {
            throw new IllegalArgumentException("Defining New-Year-strategy is not supported before Council of Tours in AD 567.");
        }
        NewYearStrategy newYearStrategy = new NewYearStrategy(this, i);
        NewYearRule newYearRule = BEGIN_OF_JANUARY;
        return this != newYearRule ? new NewYearStrategy(newYearRule, 567).and(newYearStrategy) : newYearStrategy;
    }

    int displayedYear(NewYearStrategy newYearStrategy, HistoricDate historicDate) {
        int yearOfEra = historicDate.getYearOfEra();
        return historicDate.compareTo(newYear(historicDate.getEra(), yearOfEra)) < 0 ? yearOfEra - 1 : yearOfEra;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002d A[PHI: r1 r9
      0x002d: PHI (r1v2 int) = (r1v1 int), (r1v0 int) binds: [B:22:0x0042, B:14:0x002a] A[DONT_GENERATE, DONT_INLINE]
      0x002d: PHI (r9v5 int) = (r9v2 int), (r9v8 int) binds: [B:22:0x0042, B:14:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    int standardYear(boolean r5, net.time4j.history.NewYearStrategy r6, net.time4j.history.HistoricEra r7, int r8, int r9, int r10) {
        /*
            r4 = this;
            r0 = 5
            if (r9 < r0) goto L8
            r0 = 8
            if (r9 > r0) goto L8
            return r8
        L8:
            net.time4j.history.HistoricDate r0 = r4.newYear(r7, r8)
            int r1 = r8 + 1
            net.time4j.history.HistoricDate r6 = r6.newYear(r7, r1)
            net.time4j.history.HistoricDate r2 = net.time4j.history.HistoricDate.of(r7, r8, r9, r10)
            r3 = 4
            if (r9 > r3) goto L2f
            net.time4j.history.HistoricDate r7 = net.time4j.history.HistoricDate.of(r7, r1, r9, r10)
            int r9 = r2.compareTo(r0)
            if (r9 < 0) goto L25
            r9 = r8
            goto L26
        L25:
            r9 = r1
        L26:
            int r6 = r7.compareTo(r6)
            if (r6 < 0) goto L2d
            goto L45
        L2d:
            r8 = r1
            goto L45
        L2f:
            int r1 = r8 + (-1)
            net.time4j.history.HistoricDate r7 = net.time4j.history.HistoricDate.of(r7, r1, r9, r10)
            int r7 = r7.compareTo(r0)
            if (r7 < 0) goto L3d
            r9 = r1
            goto L3e
        L3d:
            r9 = r8
        L3e:
            int r6 = r2.compareTo(r6)
            if (r6 < 0) goto L45
            goto L2d
        L45:
            if (r9 > r8) goto L4b
            if (r5 == 0) goto L4a
            r8 = r9
        L4a:
            return r8
        L4b:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Invalid date due to changing new year rule (year too short to cover month and day-of-month): "
            r6.<init>(r7)
            java.lang.StringBuilder r6 = r6.append(r2)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: net.time4j.history.NewYearRule.standardYear(boolean, net.time4j.history.NewYearStrategy, net.time4j.history.HistoricEra, int, int, int):int");
    }
}
