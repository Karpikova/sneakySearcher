package com.example.sneakysearch;

import com.example.sneakysearch.excel.HeadersDefault;
import com.example.sneakysearch.excel.WrittenToExcel;
import com.example.sneakysearch.found.FoundFromWebByAllTypoVariantsOfPhrase;
import com.example.sneakysearch.typos.AllTypos;
import com.example.sneakysearch.typos.doublebutton.DoubleButtonTypos;
import com.example.sneakysearch.typos.langreplacement.EnglishReplacementTypos;
import com.example.sneakysearch.typos.missedbutton.MissedInnerButtonTypos;
import com.example.sneakysearch.typos.mixedbuttons.MixedButtonsTypos;
import com.example.sneakysearch.typos.wrongbutton.AddedWrongButtonTypos;
import com.example.sneakysearch.typos.wrongbutton.RussianKeyboard;
import org.cactoos.text.Lowered;
import org.cactoos.text.TextOf;
import org.cactoos.text.Trimmed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public final class SearchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String doSearch(@RequestParam(value = "wholePhrase") String wholePhrase,
                           @RequestParam(value = "filterDate") LocalDate filterDate,
                           @RequestParam(value = "checkbox", required = false) boolean needEngReplace) {
        Arrays.stream(wholePhrase.split(",")).forEach
                (phrase -> new Thread(() -> {
                    try {
                        final String readyPhrase = (new Trimmed(new Lowered(new TextOf(phrase)))).asString();
                        final AllTypos allTypos = needEngReplace ? allTyposWithEngAnalogues(readyPhrase) :
                                allTyposWithoutEngAnalogues(readyPhrase);
                        new WrittenToExcel
                                (new FoundFromWebByAllTypoVariantsOfPhrase(filterDate, allTypos),
                                        new HeadersDefault().value(),
                                        readyPhrase)
                                .writeToFile();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start());
        return "result";
    }

    private AllTypos allTyposWithoutEngAnalogues(String readyPhrase) {
        return new AllTypos(List.of(
                () -> Set.of(readyPhrase),
                new AddedWrongButtonTypos(readyPhrase, new RussianKeyboard()),
                new MixedButtonsTypos(readyPhrase),
                new DoubleButtonTypos(readyPhrase),
                new MissedInnerButtonTypos(readyPhrase)));
    }


    private AllTypos allTyposWithEngAnalogues(String readyPhrase) {
        RussianKeyboard russianKeyboard = new RussianKeyboard();
        return new AllTypos(List.of(
                () -> Set.of(readyPhrase),
                new AddedWrongButtonTypos(readyPhrase, russianKeyboard),
                new MixedButtonsTypos(readyPhrase),
                new DoubleButtonTypos(readyPhrase),
                new MissedInnerButtonTypos(readyPhrase),
                new EnglishReplacementTypos(readyPhrase, russianKeyboard)));
    }
}
