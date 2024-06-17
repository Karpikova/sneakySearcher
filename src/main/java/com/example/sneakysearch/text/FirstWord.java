package com.example.sneakysearch.text;

import org.cactoos.Text;
import org.cactoos.text.Mapped;
import org.cactoos.text.TextEnvelope;

public final class FirstWord extends TextEnvelope {

    public FirstWord(Text text) {
        super(new Mapped(str -> str.split(" ")[0], text));
    }
}