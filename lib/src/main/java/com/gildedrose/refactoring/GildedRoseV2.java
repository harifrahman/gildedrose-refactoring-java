package com.gildedrose.refactoring;

class GildedRoseV2 {
    private static final int MAX_QUALITY = 50;

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

    private int degradeQuality(Item item, int deduction) {
        int finalDeduction = item.sellIn < 0 ? deduction * 2 : deduction;
        return item.quality > finalDeduction ? item.quality - finalDeduction : 0;
    }

    private int upgradeQuality(Item item, int addition) {
        int finalAddition = item.sellIn < 0 ? addition * 2 : addition;
        return item.quality + finalAddition < MAX_QUALITY ? item.quality + finalAddition : MAX_QUALITY;
    }

    private Item updateNormalItem(Item item) {
        item.sellIn--;
        item.quality = degradeQuality(item, 1);
        return item;
    }

    private Item updateConjured(Item item) {
        item.sellIn--;
        item.quality = degradeQuality(item, 2);
        return item;
    }

    private Item updateAgedBrie(Item item) {
        item.sellIn--;
        item.quality = upgradeQuality(item, 1);
        return item;
    }

    private Item updateBackstagePasses(Item item) {
        int sellIn = item.sellIn;
        item.sellIn--;

        if (sellIn < 1) {
            item.quality = 0;
            return item;
        }

        if (sellIn <= 5) {
            item.quality = upgradeQuality(item, 3);
            return item;
        }

        if (sellIn <= 10) {
            item.quality = upgradeQuality(item, 2);
            return item;
        }

        item.quality = upgradeQuality(item, 1);
        return item;
    }
}
