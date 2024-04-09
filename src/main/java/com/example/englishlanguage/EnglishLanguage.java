package com.example.englishlanguage;


import com.example.core.Language;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class EnglishLanguage implements Language {

    @Override
    public String name() {
        return "EnglishLanguage";
    }

    @Override
    public String find(String name) {
        String filePath = "D:\\IUH_20046761\\HK8\\KTPM\\TH_WEEK8\\MicroKernel_Language\\src\\main\\resources\\star_vietanh.txt"; // Đường dẫn tới file .dict
        EnglishLanguage language = new EnglishLanguage();
        Map<String, String> dictionary = language.readDictionary(filePath);
        TreeMap<String, String> data = (TreeMap<String, String>) dictionary;
        if (data.floorKey(name)!=null){
            return dictionary.get(data.floorKey(name));
        }

        return "Không tìm thấy " + name + " trong từ điển.";
    }

    public Map<String, String> readDictionary(String filePath) {
        Map<String, String> dictionary = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String key = line.trim();
                StringBuilder valueBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    if (line.endsWith("xem") || line.endsWith("Xem")) {
                        valueBuilder.append(line);
                        break;
                    }
                    valueBuilder.append(line);
                }

                dictionary.put(key, valueBuilder.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }

        return dictionary;
    }
}