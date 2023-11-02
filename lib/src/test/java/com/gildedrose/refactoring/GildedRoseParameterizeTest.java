package com.gildedrose.refactoring;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class GildedRoseParameterizeTest {

    private static Stream<Arguments> getUpdateQuality() {
        return Stream.of(
                Arguments.of("+5 Dexterity Vest", "normal", "decrease sell in and quality", 9, 19, 8, 18),
                Arguments.of("+5 Dexterity Vest", "sell in passed", "double decrease sell in and quality", 0, 19, -1, 17),
                Arguments.of("+5 Dexterity Vest", "sell in passed and quality is zero", "update sell in and not decrease quality", -6, 0, -7, 0),
                Arguments.of("Aged Brie", "sell in decreased", "increase quality", 1, 6, 0, 7),
                Arguments.of("Aged Brie", "sell in passed", "double increase quality", 0, 7, -1, 9),
                Arguments.of("Aged Brie", "sell in passed and quality is 50", "not increase quality", -2, 50, -3, 50),
                Arguments.of("Aged Brie", "sell in decreased and quality is 50", "not increase quality", 1, 50, 0, 50),
                Arguments.of("Sulfuras, Hand of Ragnaros", "update quality", "not update sell in and quality", -1, 80, -1, 80),
                Arguments.of("Backstage passes to a TAFKAL80ETC concert", "sell in more than 10", "increase quality", 11, 20, 10, 21),
                Arguments.of("Backstage passes to a TAFKAL80ETC concert", "sell in less than 10", "increase quality by two", 10, 21, 9, 23),
                Arguments.of("Backstage passes to a TAFKAL80ETC concert", "sell in less than 5", "increase quality by three", 5, 23, 4, 26),
                Arguments.of("Backstage passes to a TAFKAL80ETC concert", "sell in passed", "drop quality to zero", 0, 26, -1, 0),
                Arguments.of("Backstage passes to a TAFKAL80ETC concert", "sell in passed", "drop quality to zero and not decrease below zero", -1, 0, -2, 0)
        );
    }

    private static Stream<Arguments> getUpdateQualityForConjuredManaCake() {
        return Stream.of(

                // TODO: Not implemented yet
                Arguments.of("Conjured Mana Cake", "quality update", "decrease quality double", 9, 12, 8, 10),
                Arguments.of("Conjured Mana Cake", "sell in passed", "decrease quality quadruple", -1, 12, -2, 8),
                Arguments.of("Conjured Mana Cake", "sell in passed", "decrease quality quadruple and not below zero", -1, 3, -2, 0)
        );
    }

    @ParameterizedTest(name = "Given item {0} when {1} update quality should return {2}")
    @MethodSource("com.gildedrose.refactoring.GildedRoseParameterizeTest#getUpdateQuality")
    void shouldReturnCorrectUpdateQuality(String itemName, String condition, String expectation,
                                          int itemSellIn, int itemQuality, int itemSellInNextDay, int itemQualityNextDay) {

        Item[] items = new Item[]{new Item(itemName, itemSellIn, itemQuality)};
        Item[] itemsNextDay = new Item[]{new Item(itemName, itemSellInNextDay, itemQualityNextDay)};

        GildedRose app = getGildedRose(items);

        assertArrayEquals(itemsNextDay, app.items);
    }

    private static GildedRose getGildedRose(Item[] items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        return app;
    }
}
