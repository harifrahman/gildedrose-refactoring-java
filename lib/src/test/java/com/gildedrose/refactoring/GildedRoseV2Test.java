package com.gildedrose.refactoring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseV2Test {

    private static GildedRoseV2 gildedRose;

    @BeforeAll
    static void setUp() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6)};

        gildedRose = new GildedRoseV2(items);
    }

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRoseV2 app = getGildedRose(items);
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void givenListOfItemWhenUpdateQualityThenShouldReturnNextDayQualityForEachItemCorrectly() {
        Item[] qualityExpected = new Item[]{
                new Item("+5 Dexterity Vest", 9, 19),
                new Item("Aged Brie", 1, 1),
                new Item("Elixir of the Mongoose", 4, 6),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 14, 21),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50),
                new Item("Conjured Mana Cake", 2, 4)
        };

        gildedRose.updateQuality();
        assertArrayEquals(qualityExpected, gildedRose.items);
    }

    @Nested
    class TestNormalItem {

        @Test
        void givenDexterityVestWhenUpdateQualityThenShouldReturnDecreaseSellInAndQuality() {
            Item[] items = new Item[]{new Item("+5 Dexterity Vest", 9, 19)};
            Item[] itemsNextDay = new Item[]{new Item("+5 Dexterity Vest", 8, 18)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenDexterityVestWhenSellInPassedThenShouldReturnDoubleDecreaseQuality() {
            Item[] items = new Item[]{new Item("+5 Dexterity Vest", 0, 19)};
            Item[] itemsNextDay = new Item[]{new Item("+5 Dexterity Vest", -1, 17)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenDexterityVestWhenSellInPassedAndQualityIsZeroThenShouldUpdateSellInNotDecreaseQuality() {
            Item[] items = new Item[]{new Item("+5 Dexterity Vest", -6, 0)};
            Item[] itemsNextDay = new Item[]{new Item("+5 Dexterity Vest", -7, 0)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

    }

    @Nested
    class TestAgedBrie {

        @Test
        void givenAgedBrieWhenSellInDecreasedThenShouldReturnIncreaseQuality() {
            Item[] items = new Item[]{new Item("Aged Brie", 1, 6)};
            Item[] itemsNextDay = new Item[]{new Item("Aged Brie", 0, 7)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenAgedBrieWhenSellInPassedThenShouldReturnDoubleIncreaseQuality() {
            Item[] items = new Item[]{new Item("Aged Brie", 0, 7)};
            Item[] itemsNextDay = new Item[]{new Item("Aged Brie", -1, 9)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenAgedBrieWhenSellInPassedAndQualityIs50ThenShouldNotIncreaseQuality() {
            Item[] items = new Item[]{new Item("Aged Brie", -2, 50)};
            Item[] itemsNextDay = new Item[]{new Item("Aged Brie", -3, 50)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenAgedBrieWhenSellInDecreasedAndQualityIs50ThenShouldNotIncreaseQuality() {
            Item[] items = new Item[]{new Item("Aged Brie", 1, 50)};
            Item[] itemsNextDay = new Item[]{new Item("Aged Brie", 0, 50)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }
    }

    @Nested
    class TestSulfuras {

        @Test
        void givenSulfurasWhenUpdateQualityThenShouldNotUpdateSellInAndQualityValue() {
            Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 80)};

            Item[] itemsNextDay = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 80)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
            assertEquals(80, app.items[0].quality);
        }
    }

    @Nested
    class TestBackstagePasses {
        @Test
        void givenBackstagePassesWhenSellInMoreThan10ThenShouldIncreaseQuality() {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};
            Item[] itemsNextDay = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 21)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenBackstagePassesWhenSellInLessThan10ThenShouldIncreaseQualityByTwo() {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 21)};
            Item[] itemsNextDay = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 9, 23)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenBackstagePassesWhenSellInLessThan5ThenShouldIncreaseQualityByThree() {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 23)};
            Item[] itemsNextDay = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 4, 26)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenBackstagePassesWhenSellInPassedThenShouldDropQualityToZero() {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 26)};
            Item[] itemsNextDay = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenBackstagePassesWhenSellInPassedThenShouldDropQualityToZeroAndShouldNotDecreaseBelowZero() {
            Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0)};
            Item[] itemsNextDay = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", -2, 0)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }
    }

    @Nested
    class TestConjured {
        @Test
        void givenConjuredWhenQualityUpdateThenShouldDecreaseQualityDouble() {
            Item[] items = new Item[]{new Item("Conjured Mana Cake", 9, 12)};
            Item[] itemsNextDay = new Item[]{new Item("Conjured Mana Cake", 8, 10)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenConjuredWhenSellInPassedThenShouldDecreaseQualityQuadruple() {
            Item[] items = new Item[]{new Item("Conjured Mana Cake", -1, 12)};
            Item[] itemsNextDay = new Item[]{new Item("Conjured Mana Cake", -2, 8)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }

        @Test
        void givenConjuredWhenSellInPassedThenShouldDecreaseQualityQuadrupleAndNotBelowZero() {
            Item[] items = new Item[]{new Item("Conjured Mana Cake", -1, 3)};
            Item[] itemsNextDay = new Item[]{new Item("Conjured Mana Cake", -2, 0)};

            GildedRoseV2 app = getGildedRose(items);

            assertArrayEquals(itemsNextDay, app.items);
        }
    }

    private static GildedRoseV2 getGildedRose(Item[] items) {
        GildedRoseV2 app = new GildedRoseV2(items);
        app.updateQuality();
        return app;
    }
}
