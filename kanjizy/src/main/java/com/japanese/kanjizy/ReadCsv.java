package com.japanese.kanjizy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCsv {

    public static List<List<String>> fetchAllData(String csvPath) {
        // "/csv/verben/present/present/all_present.csv"

        List<String> verben = new ArrayList<>();
        List<String> hira = new ArrayList<>();
        List<String> de = new ArrayList<>();
        List<String> en = new ArrayList<>();
        List<String> es = new ArrayList<>();


        List<List<String>> finalSend = new ArrayList<>();


        try {
            InputStream inputStream = ReadCsv.class.getResourceAsStream(csvPath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int a = 1;
                for (String value : data) {
                    switch (a) {
                        case 1 -> verben.add(value);
                        case 2 -> hira.add(value);
                        case 3 -> de.add(value);
                        case 4 -> en.add(value);
                        case 5 -> es.add(value);

                        default -> a = 1;
                    }
                    a++;
                }

            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        verben.remove(0);
        hira.remove(0);
        de.remove(0);
        en.remove(0);
        es.remove(0);


        finalSend.add(verben);
        finalSend.add(hira);
        finalSend.add(de);
        finalSend.add(en);
        finalSend.add(es);


        return finalSend;

    }
}
