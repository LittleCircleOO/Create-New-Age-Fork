package org.antarcticgardens.newage;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;


import static org.antarcticgardens.newage.CreateNewAge.REGISTRATE;

public class NewAgeItems {
    public static final ItemEntry<Item> NUCLEAR_FUEL =
            REGISTRATE.item("nuclear_fuel", Item::new)
                    .register();

    public static final ItemEntry<Item> THORIUM =
            REGISTRATE.item("thorium", Item::new)
                    .register();

    public static final ItemEntry<Item> RADIOACTIVE_THORIUM =
            REGISTRATE.item("radioactive_thorium", Item::new)
                    .register();

    public static final ItemEntry<SequencedAssemblyItem> INCOMPLETE_FUEL =
            REGISTRATE.item("incomplete_fuel", SequencedAssemblyItem::new)
                    .removeTab(CreateNewAge.CREATIVE_TAB_KEY)
                    .register();



    public static void load() {  }
}