package com.example.sneakysearch.typos.wrongbutton;

import java.util.List;
import java.util.Map;

public interface Keyboard {
    Map<String, List<String>> lettersWithNeighbours();
    Map<Character, String> lettersWithEnglishAnalogues();
}
