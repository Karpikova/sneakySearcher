package com.example.sneakysearch;

import com.example.sneakysearch.excel.HeadersMy;
import com.example.sneakysearch.excel.WrittenToExcel;
import com.example.sneakysearch.excel.WrittenToFile;
import org.cactoos.text.Lowered;
import org.cactoos.text.TextOf;
import org.cactoos.text.Trimmed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class SearchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String doSearch(@RequestParam(value = "phrase") String wholePhrase, @RequestParam(value = "checkbox", required = false) boolean needEngReplace) {
        Arrays.stream(wholePhrase.split(",")).forEach
                (phrase -> new Thread(() -> {
                    try {
                        final WrittenToFile writtenToFile = new WrittenToExcel(
                                (new Trimmed(new Lowered(new TextOf(phrase)))).asString(),
                                new HeadersMy());
                        writtenToFile.writeToFile();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start());
        return "result";
    }
}
