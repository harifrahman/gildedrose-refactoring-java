package com.gildedrose.refactoring;

public class QualityModifier {
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    public int upgradeQuality(Item item) {
        int finalAddition = item.sellIn < 0 ? 2 : 1;
        return Math.min(item.quality + finalAddition, MAX_QUALITY);
    }

    public int degradeQuality(Item item, int deduction) {
        int finalDeduction = item.sellIn < 0 ? 2 * deduction : deduction;
        return Math.max(item.quality - finalDeduction, MIN_QUALITY);
    }
}
