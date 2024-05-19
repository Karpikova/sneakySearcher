package com.example.sneakysearch;

import java.util.Set;

public final class WrittenToFileMy implements WrittenToFile {
    private final FoundFromWebByAllTypoVariants foundFromWebByAllTypoVariants;

    public WrittenToFileMy(FoundFromWebByAllTypoVariants foundFromWebByAllTypoVariants) {
        this.foundFromWebByAllTypoVariants = foundFromWebByAllTypoVariants;
    }

    public WrittenToFileMy(String word) {
        this(new FoundFromWebByAllTypoVariantsMy(word));
    }

    @Override
    public void writeToFile() {
        Set<ResultLink> result = foundFromWebByAllTypoVariants.foundFromWeb();
        System.out.println(result.size());
    }
}
