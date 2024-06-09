package com.example.sneakysearch.typos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RussianKeyboard implements Keyboard {

    private final Map<String, List<String>> lettersWithNeighbours;

    public RussianKeyboard(Map<String, List<String>> lettersWithNeighbours) {
        this.lettersWithNeighbours = lettersWithNeighbours;
    }

    public RussianKeyboard() {
        this(new HashMap<>() {{ //TODO подумать, что будет при непопадании
            put("й", Arrays.asList("ц", "ы", "ф"));
            put("ц", Arrays.asList("у", "ы", "ф", "й"));
            put("у", Arrays.asList("к", "в", "ы", "ц"));
            put("к", Arrays.asList("е", "а", "в", "у"));
            put("е", Arrays.asList("н", "п", "а", "к"));
            put("н", Arrays.asList("г", "р", "п", "е"));
            put("г", Arrays.asList("ш", "о", "р", "н"));
            put("ш", Arrays.asList("щ", "л", "о", "г"));
            put("щ", Arrays.asList("з", "д", "л", "ш"));
            put("з", Arrays.asList("х", "ж", "д", "щ"));
            put("х", Arrays.asList("ъ", "э", "ж", "з"));
            put("ъ", Arrays.asList("э", "х"));

            put("ф", Arrays.asList("й", "ц", "ы", "я"));
            put("ы", Arrays.asList("ц", "у", "в", "ч", "я", "ф"));
            put("в", Arrays.asList("у", "к", "а", "с", "ч", "ы"));
            put("а", Arrays.asList("к", "е", "п", "м", "с", "в"));
            put("п", Arrays.asList("е", "н", "р", "и", "м", "а"));
            put("р", Arrays.asList("н", "г", "о", "т", "и", "п"));
            put("о", Arrays.asList("г", "ш", "л", "ь", "т", "р"));
            put("л", Arrays.asList("ш", "щ", "д", "б", "ь", "о"));
            put("д", Arrays.asList("щ", "з", "ж", "ю", "б", "л"));
            put("ж", Arrays.asList("з", "х", "э", "ю", "д"));
            put("э", Arrays.asList("х", "ъ", "ж"));

            put("я", Arrays.asList("ф", "ы", "ч"));
            put("ч", Arrays.asList("я", "ы", "в", "с"));
            put("с", Arrays.asList("ч", "в", "а", "м"));
            put("м", Arrays.asList("с", "а", "п", "и"));
            put("и", Arrays.asList("м", "п", "р", "т"));
            put("т", Arrays.asList("и", "р", "о", "ь"));
            put("ь", Arrays.asList("т", "о", "л", "б"));
            put("б", Arrays.asList("ь", "л", "д", "ю"));
            put("ю", Arrays.asList("б", "д", "ж"));

            put("1", Arrays.asList());
            put("2", Arrays.asList());
            put("3", Arrays.asList());
            put("4", Arrays.asList());
            put("5", Arrays.asList());
            put("6", Arrays.asList());
            put("7", Arrays.asList());
            put("8", Arrays.asList());
            put("9", Arrays.asList());
            put("0", Arrays.asList());
        }});
    }

    @Override
    public Map<String, List<String>> lettersWithNeighbours() {
        return lettersWithNeighbours;
    }
}
