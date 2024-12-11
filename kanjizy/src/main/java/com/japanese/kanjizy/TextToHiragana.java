package com.japanese.kanjizy;

import java.util.HashMap;
import java.util.Map;

public class TextToHiragana {

    Map<String, String> allHiragana = new HashMap<String, String>();
    {
        allHiragana.put("a", "あ");
        allHiragana.put("i", "い");
        allHiragana.put("u", "う");
        allHiragana.put("e", "え");
        allHiragana.put("o", "お");
        allHiragana.put("ka", "か");
        allHiragana.put("ki", "き");
        allHiragana.put("ku", "く");
        allHiragana.put("ke", "け");
        allHiragana.put("ko", "こ");
        allHiragana.put("sa", "さ");
        allHiragana.put("shi", "し");
        allHiragana.put("su", "す");
        allHiragana.put("se", "せ");
        allHiragana.put("so", "そ");
        allHiragana.put("ta", "た");
        allHiragana.put("chi", "ち");
        allHiragana.put("tsu", "つ");
        allHiragana.put("te", "て");
        allHiragana.put("to", "と");
        allHiragana.put("na", "な");
        allHiragana.put("ni", "に");
        allHiragana.put("nu", "ぬ");
        allHiragana.put("ne", "ね");
        allHiragana.put("no", "の");
        allHiragana.put("ha", "は");
        allHiragana.put("hi", "ひ");
        allHiragana.put("fu", "ふ");
        allHiragana.put("he", "へ");
        allHiragana.put("ho", "ほ");
        allHiragana.put("ma", "ま");
        allHiragana.put("mi", "み");
        allHiragana.put("mu", "む");
        allHiragana.put("me", "め");
        allHiragana.put("mo", "も");
        allHiragana.put("ya", "や");
        allHiragana.put("yu", "ゆ");
        allHiragana.put("yo", "よ");
        allHiragana.put("ra", "ら");
        allHiragana.put("ri", "り");
        allHiragana.put("ru", "る");
        allHiragana.put("re", "れ");
        allHiragana.put("ro", "ろ");
        allHiragana.put("wa", "わ");
        allHiragana.put("wo", "を");
        allHiragana.put("n", "ん");
        allHiragana.put("ga", "が");
        allHiragana.put("gi", "ぎ");
        allHiragana.put("gu", "ぐ");
        allHiragana.put("ge", "げ");
        allHiragana.put("go", "ご");
        allHiragana.put("za", "ざ");
        allHiragana.put("ji", "じ");
        allHiragana.put("zu", "ず");
        allHiragana.put("ze", "ぜ");
        allHiragana.put("zo", "ぞ");
        allHiragana.put("da", "だ");
        allHiragana.put("de", "で");
        allHiragana.put("do", "ど");
        allHiragana.put("ba", "ば");
        allHiragana.put("bi", "び");
        allHiragana.put("bu", "ぶ");
        allHiragana.put("be", "べ");
        allHiragana.put("bo", "ぼ");
        allHiragana.put("pa", "ぱ");
        allHiragana.put("pi", "ぴ");
        allHiragana.put("pu", "ぷ");
        allHiragana.put("pe", "ぺ");
        allHiragana.put("po", "ぽ");
    };

    public String convert(String hira) {
        hira = hira.toLowerCase();
        StringBuilder sendHira = new StringBuilder();
        boolean skip = false;
        boolean skip2 = false;

        for (int i = 0; i < hira.length(); i++) {
            if (!skip2) {
                if (!skip) {
                    if (allHiragana.containsKey(String.valueOf(hira.charAt(i)))
                            && (i + 2 > hira.length())
                    ) {
                        sendHira.append(allHiragana.get(String.valueOf(hira.charAt(i))));
                    }

                    else if (allHiragana.containsKey(String.valueOf(hira.charAt(i)))
                            && !allHiragana.containsKey(hira.substring(i, i + 2))) {
                        sendHira.append(allHiragana.get(String.valueOf(hira.charAt(i))));

                    } else if (allHiragana.containsKey(hira.substring(i, i + 2))) {
                        skip = true;
                        sendHira.append(allHiragana.get(hira.substring(i, i + 2)));

                    } else if (allHiragana.containsKey(hira.substring(i, i + 3))) {
                        skip = true;
                        skip2 = true;
                        sendHira.append(allHiragana.get(hira.substring(i, i + 3)));
                    }
                } else {
                    skip = false;
                }
            } else {
                skip2 = false;
            }
        }

        return sendHira.toString();
    }
}
