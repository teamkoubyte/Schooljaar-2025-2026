package com.example.toetslocaltimelocaldate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;

public class BewerkingenOpDatums {
    public static String converteerDatumInString(LocalDate datum, FormatStyle formaat) {
        return (datum.format(DateTimeFormatter.ofLocalizedDate(formaat)));
    }

    public static String geefDatumVanVandaagGeformatteerd(FormatStyle formaat) {
        LocalDate datumVanVandaag;
        String resultaat;
        datumVanVandaag = LocalDate.now();
        resultaat = converteerDatumInString(datumVanVandaag, formaat);
        return (resultaat);
    }

    public static String geefDatumGeformatteerd(int jaar, int maand, int dag, FormatStyle formaat) {
        return (converteerDatumInString(LocalDate.of(jaar, maand, dag), formaat));
    }

    public static String geefDatumGeformatteerd(String datumAlsString, FormatStyle formaat) {
        return (converteerDatumInString(LocalDate.parse(datumAlsString), formaat));
    }

    public static LocalDate berekenBegindatumKerstvakantie(int jaarVanKerstdag) {
        LocalDate kerstdag;
        DayOfWeek dagVanKerstdag;

        kerstdag = LocalDate.of(jaarVanKerstdag, 12, 25);
        dagVanKerstdag = kerstdag.getDayOfWeek();

        if (dagVanKerstdag == DayOfWeek.SATURDAY || dagVanKerstdag == DayOfWeek.SUNDAY) {
            return (kerstdag.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        } else {
            return (kerstdag.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));
        }
    }

    public static LocalDate berekenEinddatumKerstvakantie(int jaarVanKerstdag) {
        LocalDate begindatumKerstvakantie;

        begindatumKerstvakantie = berekenBegindatumKerstvakantie(jaarVanKerstdag);
        return (begindatumKerstvakantie.plusWeeks(2).minusDays(1));
    }
}
