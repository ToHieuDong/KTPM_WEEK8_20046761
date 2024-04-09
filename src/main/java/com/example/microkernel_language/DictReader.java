package com.example.microkernel_language;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DictReader {
    public Map<String, String> readDictionary(String filePath) {
        Map<String, String> dictionary = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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

    public static void main(String[] args) {
        String filePath = "D:\\IUH_20046761\\HK8\\KTPM\\TH_WEEK8\\MicroKernel_Language\\src\\main\\resources\\star_vietanh.txt"; // Đường dẫn tới file .dict
        DictReader reader = new DictReader();
        Map<String, String> dictionary = reader.readDictionary(filePath);

        // In ra từ điển đã đọc
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("=======================================");
        }

    }
}
