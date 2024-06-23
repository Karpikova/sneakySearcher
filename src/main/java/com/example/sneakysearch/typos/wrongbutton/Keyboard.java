package com.example.sneakysearch.typos.wrongbutton;

import java.util.List;
import java.util.Map;

public interface Keyboard {
    Map<String, List<String>> lettersWithNeighbours();//TODO а может и его в char? для однообразности? или ну его?
    Map<Character, String> lettersWithEnglishAnalogues();
}
