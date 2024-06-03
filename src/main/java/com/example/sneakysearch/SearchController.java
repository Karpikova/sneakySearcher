package com.example.sneakysearch;

import com.example.sneakysearch.excel.HeadersMy;
import com.example.sneakysearch.excel.WrittenToExcel;
import com.example.sneakysearch.excel.WrittenToFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String doSearch(@RequestParam(value = "word") String word) throws SneakySearchException {
        new Thread(() -> {
            WrittenToFile writtenToFile = new WrittenToExcel(word,
                    new HeadersMy()); //привести к нижнему регистру. а что?
            writtenToFile.writeToFile();
        }).start();
        return "result";
    }
}
