package com.gildedrose.refactoring.item;

import com.gildedrose.refactoring.Item;
import com.gildedrose.refactoring.ItemModifier;
import com.gildedrose.refactoring.QualityModifier;

public class ConjuredCakeModifier extends QualityModifier implements ItemModifier {
    @Override
    public void updateItem(Item item) {
        item.quality = degradeQuality(item, 2);
    }
}