package com.gildedrose.refactoring;

class GildedRose {
    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        item.sellIn--;
        if (item.name.equals("Aged Brie")) {
            updateAgedBrie(item);
            return;
        }

        if (item.name.equals("Conjured Mana Cake")) {
            updateConjuredCake(item);
            return;
        }

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateBackstagePasses(item);
            return;
        }

        item.quality = degradeQuality(item, 1);
    }

    private void updateBackstagePasses(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
            return;
        }

        item.quality = upgradeQuality(item);

        if (item.sellIn < 5) {
            item.quality = upgradeQuality(item);
        }
        if (item.sellIn < 10) {
            item.quality = upgradeQuality(item);
        }
    }

    private void updateAgedBrie(Item item) {
        item.quality = upgradeQuality(item);
    }

    private void updateConjuredCake(Item item) {
        item.quality = degradeQuality(item, 2);
    }

    private int upgradeQuality(Item item) {
        int finalAddition = item.sellIn < 0 ? 2 : 1;
        return Math.min(item.quality + finalAddition, MAX_QUALITY);
    }

    private int degradeQuality(Item item, int deduction) {
        int finalDeduction = item.sellIn < 0 ? 2 * deduction : deduction;
        return Math.max(item.quality - finalDeduction, MIN_QUALITY);
    }
}
