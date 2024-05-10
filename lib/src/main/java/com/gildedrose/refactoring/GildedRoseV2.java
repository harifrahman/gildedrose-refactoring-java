package com.gildedrose.refactoring;

class GildedRoseV2 {
    private static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRoseV2(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item currentItem : items) {
            if (currentItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }

            if (currentItem.name.equals("Aged Brie")) {
                updateAgedBrie(currentItem);
                continue;
            }

            if (currentItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                updateBackstagePasses(currentItem);
                continue;
            }

            if (currentItem.name.equals("Conjured Mana Cake")) {
                updateConjured(currentItem);
                continue;
            }

            updateNormalItem(currentItem);
        }
    }

    private int degradeQuality(Item item, int deduction) {
        int finalDeduction = item.sellIn < 0 ? deduction * 2 : deduction;
        return item.quality > finalDeduction ? item.quality - finalDeduction : 0;
    }

    private int upgradeQuality(Item item, int addition) {
        int finalAddition = item.sellIn < 0 ? addition * 2 : addition;
        return Math.min(item.quality + finalAddition, MAX_QUALITY);
    }

    private void updateNormalItem(Item item) {
        item.sellIn--;
        item.quality = degradeQuality(item, 1);
    }

    private void updateConjured(Item item) {
        item.sellIn--;
        item.quality = degradeQuality(item, 2);
    }

    private void updateAgedBrie(Item item) {
        item.sellIn--;
        item.quality = upgradeQuality(item, 1);
    }

    private void updateBackstagePasses(Item item) {
        int sellIn = item.sellIn;
        item.sellIn--;

        if (sellIn < 1) {
            item.quality = 0;
            return;
        }

        if (sellIn <= 5) {
            item.quality = upgradeQuality(item, 3);
            return;
        }

        if (sellIn <= 10) {
            item.quality = upgradeQuality(item, 2);
            return;
        }

        item.quality = upgradeQuality(item, 1);
    }
}
