package com.example.metetoobo;

import java.util.HashMap;

public class MeteoIndex {

    public static String getWeatherHashmap(int key) {

        HashMap<Integer, String> weather = new HashMap<>();
        weather.put(0, "Soleil");
        weather.put(1, "Peu nuageux");
        weather.put(2, "Ciel voilé");
        weather.put(3, "Nuageux");
        weather.put(4, "Très nuageux");
        weather.put(5, "Couvert");
        weather.put(6, "Brouillard");
        weather.put(7, "Brouillard givrant");
        weather.put(10, "Pluie faible");
        weather.put(11, "Pluie modérée");
        weather.put(12, "Pluie forte");
        weather.put(13, "Pluie faible verglaçante");
        weather.put(14, "Pluie modérée verglaçante");
        weather.put(15, "Pluie forte verglaçante");
        weather.put(16, "Bruine");
        weather.put(20, "Neige faible");
        weather.put(21, "Neige modérée");
        weather.put(22, "Neige forte");
        weather.put(30, "Pluie et neige mêlées faibles");
        weather.put(31, "Pluie et neige mêlées modérées");
        weather.put(32, "Pluie et neige mêlées fortes");
        weather.put(40, "Averses de pluie locales et faibles");
        weather.put(41, "Averses de pluie locales");
        weather.put(42, "Averses locales et fortes");
        weather.put(43, "Averses de pluie faibles");
        weather.put(44, "Averses de pluie");
        weather.put(45, "Averses de pluie fortes");
        weather.put(46, "Averses de pluie faibles et fréquentes");
        weather.put(47, "Averses de pluie fréquentes");
        weather.put(48, "Averses de pluie fortes et fréquentes");
        weather.put(60, "Averses de neige localisées et faibles");
        weather.put(61, "Averses de neige localisées");
        weather.put(62, "Averses de neige localisées et fortes");
        weather.put(63, "Averses de neige faibles");
        weather.put(64, "Averses de neige");
        weather.put(65, "Averses de neige fortes");
        weather.put(66, "Averses de neige faibles et fréquentes");
        weather.put(67, "Averses de neige fréquentes");
        weather.put(68, "Averses de neige fortes et fréquentes");
        weather.put(70, "Averses de pluie et neige mêlées localisées et faibles");
        weather.put(71, "Averses de pluie et neige mêlées localisées");
        weather.put(72, "Averses de pluie et neige mêlées localisées et fortes");
        weather.put(73, "Averses de pluie et neige mêlées faibles");
        weather.put(74, "Averses de pluie et neige mêlées");
        weather.put(75, "Averses de pluie et neige mêlées fortes");
        weather.put(76, "Averses de pluie et neige mêlées faibles et nombreuses");
        weather.put(77, "Averses de pluie et neige mêlées fréquentes");
        weather.put(78, "Averses de pluie et neige mêlées fortes et fréquentes");
        weather.put(100, "Orages faibles et locaux");
        weather.put(101, "Orages locaux");
        weather.put(102, "Orages fort et locaux");
        weather.put(103, "Orages faibles");
        weather.put(104, "Orages");
        weather.put(105, "Orages forts");
        weather.put(106, "Orages faibles et fréquents");
        weather.put(107, "Orages fréquents");
        weather.put(108, "Orages forts et fréquents");
        weather.put(120, "Orages faibles et locaux de neige ou grésil");
        weather.put(121, "Orages locaux de neige ou grésil");
        weather.put(122, "Orages locaux de neige ou grésil");
        weather.put(123, "Orages faibles de neige ou grésil");
        weather.put(124, "Orages de neige ou grésil");
        weather.put(125, "Orages de neige ou grésil");
        weather.put(126, "Orages faibles et fréquents de neige ou grésil");
        weather.put(127, "Orages fréquents de neige ou grésil");
        weather.put(128, "Orages fréquents de neige ou grésil");
        weather.put(130, "Orages faibles et locaux de pluie et neige mêlées ou grésil");
        weather.put(131, "Orages locaux de pluie et neige mêlées ou grésil");
        weather.put(132, "Orages fort et locaux de pluie et neige mêlées ou grésil");
        weather.put(133, "Orages faibles de pluie et neige mêlées ou grésil");
        weather.put(134, "Orages de pluie et neige mêlées ou grésil");
        weather.put(135, "Orages forts de pluie et neige mêlées ou grésil");
        weather.put(136, "Orages faibles et fréquents de pluie et neige mêlées ou grésil");
        weather.put(137, "Orages fréquents de pluie et neige mêlées ou grésil");
        weather.put(138, "Orages forts et fréquents de pluie et neige mêlées ou grésil");
        weather.put(140, "Pluies orageuses");
        weather.put(141, "Pluie et neige mêlées à caractère orageux");
        weather.put(142, "Neige à caractère orageux");
        weather.put(210, "Pluie faible intermittente");
        weather.put(211, "Pluie modérée intermittente");
        weather.put(212, "Pluie forte intermittente");
        weather.put(220, "Neige faible intermittente");
        weather.put(221, "Neige modérée intermittente");
        weather.put(222, "Neige forte intermittente");
        weather.put(230, "Pluie et neige mêlées");
        weather.put(231, "Pluie et neige mêlées");
        weather.put(232, "Pluie et neige mêlées");
        weather.put(235, "Averses de grêle");

        String message = weather.get(key);

        return message;
    }
}
