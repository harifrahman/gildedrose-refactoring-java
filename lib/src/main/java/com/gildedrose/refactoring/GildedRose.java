package com.gildedrose.refactoring;

import com.gildedrose.refactoring.item.AgedBrieModifier;
import com.gildedrose.refactoring.item.BackstagesPassesModifier;
import com.gildedrose.refactoring.item.ConjuredCakeModifier;
import com.gildedrose.refactoring.item.NormalItemModifier;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    Item[] items;
    private static final Map<String, ItemModifier> ITEM_MODIFIER_MAP = new HashMap<>() {
        {
            put("Aged Brie", new AgedBrieModifier());
            put("Conjured Mana Cake", new ConjuredCakeModifier());
            put("Backstage passes to a TAFKAL80ETC concert", new BackstagesPassesModifier());
        }
    };

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
        ItemModifier itemModifier = ITEM_MODIFIER_MAP.getOrDefault(item.name, new NormalItemModifier());
        itemModifier.updateItem(item);
    }
}
