package com.example.sneakysearch;

import java.util.Set;

public interface Typos {
    Set<String> value();
    final class Smart {
        public void resetStringBuilder(StringBuilder wordSb, String initialWord) {
            wordSb.setLength(0);
            wordSb.append(initialWord);
        }
    }
}
