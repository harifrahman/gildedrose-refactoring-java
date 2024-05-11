package com.gildedrose.refactoring;

class GildedRose {
    public static final int MAX_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }

            if (item.name.equals("Aged Brie")) {
                item.sellIn--;
                item.quality = upgradeQuality(item);
                continue;
            }

            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.sellIn--;

                if (item.sellIn < 0) {
                    item.quality = 0;
                    continue;
                }

                item.quality = upgradeQuality(item);

                if (item.sellIn < 5) {
                    item.quality = upgradeQuality(item);
                }
                if (item.sellIn < 10) {
                    item.quality = upgradeQuality(item);
                }

                continue;
            }

            item.sellIn--;
            if (item.quality > 0) {
                item.quality--;
            }

            if (item.sellIn < 0 && item.quality > 0) {
                item.quality--;
            }
        }
    }

    private int upgradeQuality(Item item) {
        int finalAddition = item.sellIn < 0 ? 2 : 1;
        return Math.min(item.quality + finalAddition, MAX_QUALITY);
    }
}
