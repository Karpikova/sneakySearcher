package com.example.sneakysearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String doSearch(@RequestParam(value = "word") String word) throws SneakySearchException {
        WrittenToFile writtenToFile = new WrittenToFileMy(word);
        writtenToFile.writeToFile();
        return "result";
    }
}
