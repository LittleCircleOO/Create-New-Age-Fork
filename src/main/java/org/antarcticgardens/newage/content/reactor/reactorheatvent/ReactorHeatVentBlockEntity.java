package org.antarcticgardens.newage.content.reactor.reactorheatvent;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.antarcticgardens.newage.content.heat.HeatBlockEntity;
import org.antarcticgardens.newage.content.reactor.RodFindingReactorBlockEntity;
import org.antarcticgardens.newage.content.reactor.reactorrod.ReactorRodBlockEntity;
import org.antarcticgardens.newage.tools.StringFormattingTool;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class ReactorHeatVentBlockEntity extends RodFindingReactorBlockEntity implements HeatBlockEntity, IHaveGoggleInformation {

    private float extract;

    public ReactorHeatVentBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public float heat = 0;

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        heat = tag.getFloat("heat");
        extract = tag.getFloat("extract");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putFloat("heat", heat);
        tag.putFloat("extract", extract);
    }


    @Override
    public float getHeat() {
        return heat;
    }

    @Override
    public void addHeat(float amount) {
        heat += amount;
        setChanged();
    }

    @Override
    public void setHeat(float amount) {
        heat = amount;
        setChanged();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    int tick = 0;
    public void tick(BlockPos pos, Level world, BlockState state) {
        tick++;
        if (tick >= 20) {
            if (heat > 16000) {
                world.setBlock(pos, Blocks.LAVA.defaultBlockState(), 3);
            }
            tick = 0;
            extract = 0;

            List<ReactorRodBlockEntity> rods = new LinkedList<>();
            for (Direction dir : Direction.values()) {
                findRods(rods, dir);
            }
            for (ReactorRodBlockEntity rod : rods) {
                float total = Math.min(rod.heat, 9000 - heat);
                rod.heat -= total;
                if (total > 0) {
                    setChanged();
                }
                extract += total;
                heat+=total;
            }
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        Lang.translate("tooltip.create_new_age.temperature", StringFormattingTool.formatFloat(heat))
                .style(ChatFormatting.AQUA).forGoggles(tooltip, 1);
        Lang.translate("tooltip.create_new_age.extracting")
                .style(ChatFormatting.GRAY).forGoggles(tooltip, 1);
        Lang.translate("tooltip.create_new_age.temperature", StringFormattingTool.formatFloat(extract))
                .style(ChatFormatting.AQUA).forGoggles(tooltip, 2);
        return true;
    }

}
