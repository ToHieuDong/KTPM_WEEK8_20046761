package com.example.microkernel_language.FrontEnd;

import com.example.core.Language;
import com.example.englishlanguage.EnglishLanguage;
import com.example.microkernel_language.PluginManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class ClientControllers {
    public static final String ENGLISH_LANGUAGE_PLUGIN_FILE_PATH = "EnglishLanguage/build/libs/EnglishLanguage-0.0.1-SNAPSHOT-plain.jar";

    public ClientControllers() throws Exception {
        PluginManager.loadPlugin(ENGLISH_LANGUAGE_PLUGIN_FILE_PATH);

    }

    @GetMapping("/")
    public String getBlog() {
        return "index";
    }

    @PostMapping("/find")
    public String getProducts(Model model, @RequestParam String inputWord) {
        String text = PluginManager.get(Language.class).find(inputWord);
        model.addAttribute("repText", text);
        model.addAttribute("valueText", inputWord);

        System.out.println(text);
        return "index";
    }
}
