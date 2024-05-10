package com.gildedrose.refactoring;

class GildedRoseV2 {
    Item[] items;

    public GildedRoseV2(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }

            if (items[i].name.equals("Aged Brie")) {
                Item updatedItem = updateAgedBrie(items[i]);
                items[i] = updatedItem;
                continue;
            }

            if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                Item updatedItem = updateBackstagePasses(items[i]);
                items[i] = updatedItem;
                continue;
            }

            if (items[i].name.equals("Conjured Mana Cake")) {
                Item updatedItem = updateConjured(items[i]);
                items[i] = updatedItem;
                continue;
            }

            Item updatedItem = updateNormalItem(items[i]);
            items[i] = updatedItem;
        }
    }

    private int reduceQuality(int currentQuality, int deduction) {
        return currentQuality > deduction ? currentQuality - deduction : 0;
    }

    private Item updateNormalItem(Item item) {
        item.quality--;
        item.sellIn--;
        return item;
    }

    private Item updateConjured(Item item) {
        item.quality -= 2;
        item.sellIn--;
        return item;
    }

    private Item updateAgedBrie(Item item) {
        item.quality++;
        item.sellIn--;
        return item;
    }

    private Item updateBackstagePasses(Item item) {
        int sellIn = item.sellIn;
        if (sellIn == 0) {
            item.quality = 0;
            return item;
        }

        if (sellIn <= 5) {
            item.quality += 3;
        }

        if (sellIn <= 10) {
            item.quality += 2;
        }

        if (sellIn > 10) {
            item.quality++;
        }

        item.sellIn--;
        return item;
    }
}
