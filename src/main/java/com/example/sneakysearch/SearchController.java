package com.example.sneakysearch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileWriter;
import java.io.IOException;

@Controller
public class SearchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String doSearch(@RequestParam(value = "word") String word) throws SneakySearchException {
        WrittenToFile writtenToFile = new WrittenToFileMy(word); //привести к нижнему регистру
    //    writtenToFile.writeToFile();
     //   return "src\\main\\resources\\templates\\result.html";
        return "result";
    }

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/application.properties", true);
        fileWriter.append("lalaa");
        fileWriter.flush();
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String start() throws SneakySearchException {
//        return "/";
//    }
}
