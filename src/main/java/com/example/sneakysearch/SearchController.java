package com.example.sneakysearch;

import com.example.sneakysearch.excel.HeadersMy;
import com.example.sneakysearch.excel.WrittenToExcel;
import com.example.sneakysearch.excel.WrittenToFile;
import com.example.sneakysearch.text.FirstWord;
import org.cactoos.text.Lowered;
import org.cactoos.text.TextOf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String doSearch(@RequestParam(value = "word") String word) {
        new Thread(() -> {
            try {
                final WrittenToFile writtenToFile = new WrittenToExcel(
                        new FirstWord(new Lowered(new TextOf(word))).asString(),
                        new HeadersMy());
                writtenToFile.writeToFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        return "result";
    }
}
