package com.gildedrose.refactoring.item;

import com.gildedrose.refactoring.Item;
import com.gildedrose.refactoring.ItemModifier;
import com.gildedrose.refactoring.QualityModifier;

public class BackstagesPassesModifier extends QualityModifier implements ItemModifier {
    @Override
    public void updateItem(Item item) {
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
}