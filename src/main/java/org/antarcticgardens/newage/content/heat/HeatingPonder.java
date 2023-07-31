package org.antarcticgardens.newage.content.heat;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

public class HeatingPonder {

    public static void ponder(SceneBuilder scene, SceneBuildingUtil util) {
        scene.configureBasePlate(0, 0, 5);
        scene.title("heating", "Using Heating");
        scene.showBasePlate();

        scene.world.showSection(util.select.fromTo(0, 0, 0, 5, 0, 5), Direction.UP);

        scene.idle(10);
        scene.world.showSection(util.select.position(1, 1, 1), Direction.DOWN);
        scene.overlay.showText(80).placeNearTarget()
                .text("").pointAt(new Vec3(1.5, 2, 1.5));

        scene.idle(90);
        scene.addKeyframe();
        scene.rotateCameraY(45);
        scene.idle(20);

        scene.world.showSection(util.select.position(2, 1, 1), Direction.DOWN);
        scene.world.modifyBlock(util.grid.at(2, 1, 1),
                blockState -> blockState.setValue(BlockStateProperties.SOUTH, false),
                false);
        scene.idle(10);
        scene.world.showSection(util.select.position(3, 1, 1), Direction.DOWN);
        scene.world.modifyBlock(util.grid.at(3, 1, 1),
                blockState -> blockState.setValue(BlockStateProperties.UP, false),
                false);
        scene.idle(10);

        scene.overlay.showText(80).placeNearTarget()
                .text("").pointAt(new Vec3(3.0, 1.8, 1.5));


        scene.idle(90);

        showTripleText(scene);

        scene.idle(20);
        showTripleText(scene);

        scene.idle(20);
        showTripleText(scene);

        scene.idle(40);

        scene.addKeyframe();
        scene.idleSeconds(20);

        scene.world.modifyBlock(util.grid.at(3, 1, 1),
                blockState -> blockState.setValue(BlockStateProperties.UP, true),
                false);

    }

    private static void showTripleText(SceneBuilder scene) {
        scene.overlay.showText(20).placeNearTarget()
                .text("").pointAt(new Vec3(1.5, 2, 1.5));

        scene.overlay.showText(20).placeNearTarget()
                .text("").pointAt(new Vec3(2.5, 1.8, 1.5));

        scene.overlay.showText(20).placeNearTarget()
                .text("").pointAt(new Vec3(3.5, 1.8, 1.5));
    }

}
