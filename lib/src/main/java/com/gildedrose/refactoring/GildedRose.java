package com.gildedrose.refactoring;

class GildedRose {
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

                if (item.quality >= 50) {
                    continue;
                }

                if (item.sellIn < 0 && item.quality < 49) {
                    item.quality += 2;
                    continue;
                }

                item.quality++;
                continue;
            }

            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert") && item.quality < 50) {
                item.sellIn--;

                if (item.sellIn < 0) {
                    item.quality = 0;
                    continue;
                }

                item.quality++;

                if (item.sellIn < 5 && item.quality < 50) {
                    item.quality++;
                }
                if (item.sellIn < 10 && item.quality < 50) {
                    item.quality++;
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
}
