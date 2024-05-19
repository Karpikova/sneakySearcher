package com.example.sneakysearch;

import java.util.List;
import java.util.Map;

public interface Keyboard {
    Map<String, List<String>> lettersWithNeighbours();
}
