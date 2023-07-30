package org.antarcticgardens.newage.content.generation.magnets;

import net.minecraft.world.level.block.Block;

public class MagnetiteBlock extends Block implements IMagneticBlock {
    public MagnetiteBlock(Properties properties) {
        super(properties.strength(5.0F, 7.0F));
    }

    @Override
    public float getStrength() {
        return 1.0f;
    }
}
