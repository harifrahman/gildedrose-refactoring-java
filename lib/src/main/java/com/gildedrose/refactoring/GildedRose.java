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
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }

            item.sellIn--;
            if (item.name.equals("Aged Brie")) {
                updateAgedBrie(item);
                continue;
            }

            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                updateBackstagePasses(item);
                continue;
            }

            item.quality = degradeQuality(item);
        }
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

    private int upgradeQuality(Item item) {
        int finalAddition = item.sellIn < 0 ? 2 : 1;
        return Math.min(item.quality + finalAddition, MAX_QUALITY);
    }

    private int degradeQuality(Item item) {
        int finalDeduction = item.sellIn < 0 ? 2 : 1;
        return Math.max(item.quality - finalDeduction, MIN_QUALITY);
    }
}
