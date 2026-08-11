package net.morthen.resource_backpacks.backpack.util;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BackpackBlockShapes {

    public static VoxelShape getVoxelShape(Direction direction, boolean onWall) {
        return switch(direction) {
            case NORTH -> north(onWall);
            case EAST -> east(onWall);
            case SOUTH -> south(onWall);
            case WEST -> west(onWall);
            default -> Shapes.block();
        };
    }

    private static VoxelShape north(boolean onWall) {
        VoxelShape shape = Shapes.empty();

        if (onWall) {
            shape = Shapes.join(shape, Shapes.box(0.7500625, 0.2500625, 0.7813125, 0.8124375, 0.5, 0.9686875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.25, 0.25, 0.75, 0.75, 0.8125, 1), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.1875, 0.75, 0.6875, 0.25, 1), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.1875625, 0.2500625, 0.7813125, 0.2499375, 0.5, 0.9686875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.25, 0.6875, 0.6875, 0.6875, 0.75), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.40625, 0.28125, 0.65625, 0.59375, 0.46875, 0.71875), BooleanOp.OR);
        } else {
            shape = Shapes.join(shape, Shapes.box(0.25, 0.0625, 0.375, 0.75, 0.625, 0.625), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.3125, 0.6875, 0.5, 0.375), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.375, 0.6875, 0.0625, 0.625), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.75, 0.0625, 0.40625, 0.8125, 0.3125, 0.59375), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.40625, 0.25, 0.3125, 0.59375), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.40625, 0.09375, 0.28125, 0.59375, 0.28125, 0.34375), BooleanOp.OR);
        }

        return shape;
    }

    private static VoxelShape east(boolean onWall) {
        VoxelShape shape = Shapes.empty();

        if (onWall) {
            shape = Shapes.join(shape, Shapes.box(0.0313125, 0.2500625, 0.7500625, 0.2186875, 0.5, 0.8125), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0, 0.25, 0.25, 0.25, 0.8125, 0.75), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0, 0.1875, 0.3125, 0.25, 0.25, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.0313125, 0.2500625, 0.1875625, 0.2186875, 0.5, 0.25), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.25, 0.25, 0.3125, 0.3125, 0.6875, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.28125, 0.28125, 0.40625, 0.34, 0.46875, 0.594), BooleanOp.OR);
        } else {
            shape = Shapes.join(shape, Shapes.box(0.375, 0.0625, 0.25, 0.625, 0.625, 0.75), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.625, 0.0625, 0.3125, 0.6875, 0.5, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.3125, 0.625, 0.0625, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.40625, 0.0625, 0.75, 0.59375, 0.3125, 0.8125), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.40625, 0.0625, 0.1875, 0.59375, 0.3125, 0.25), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.65625, 0.09375, 0.40625, 0.71875, 0.28125, 0.59375), BooleanOp.OR);
        }

        return shape;
    }

    private static VoxelShape south(boolean onWall) {
        VoxelShape shape = Shapes.empty();

        if (onWall) {
            shape = Shapes.join(shape, Shapes.box(0.1875625, 0.2500625, 0.0313125, 0.2499375, 0.5, 0.2186875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.25, 0.25, 0, 0.75, 0.8125, 0.25), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.1875, 0, 0.6875, 0.25, 0.25), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.7500625, 0.2500625, 0.0313125, 0.8124375, 0.5, 0.2186875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.25, 0.25, 0.6875, 0.6875, 0.3125), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.40625, 0.28125, 0.28125, 0.59375, 0.46875, 0.34375), BooleanOp.OR);
        } else {
            shape = Shapes.join(shape, Shapes.box(0.25, 0.0625, 0.375, 0.75, 0.625, 0.625), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.625, 0.6875, 0.5, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.375, 0.6875, 0.0625, 0.625), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.1875, 0.0625, 0.40625, 0.25, 0.3125, 0.59375), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.75, 0.0625, 0.40625, 0.8125, 0.3125, 0.59375), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.40625, 0.09375, 0.65625, 0.59375, 0.28125, 0.71875), BooleanOp.OR);
        }
        return shape;
    }

    private static VoxelShape west(boolean onWall) {
        VoxelShape shape = Shapes.empty();

        if (onWall) {
            shape = Shapes.join(shape, Shapes.box(0.7813125, 0.2500625, 0.1875625, 0.9686875, 0.5, 0.25), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.75, 0.25, 0.25, 1, 0.8125, 0.75), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.75, 0.1875, 0.3125, 1, 0.25, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.7813125, 0.2500625, 0.7500625, 0.9686875, 0.5, 0.8125), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.6875, 0.25, 0.3125, 0.75, 0.6875, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.65625, 0.28125, 0.40625, 0.71875, 0.46875, 0.59375), BooleanOp.OR);
        } else {
            shape = Shapes.join(shape, Shapes.box(0.375, 0.0625, 0.25, 0.625, 0.625, 0.75), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.3125, 0.0625, 0.3125, 0.375, 0.5, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.3125, 0.625, 0.0625, 0.6875), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.40625, 0.0625, 0.1875, 0.59375, 0.3125, 0.25), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.40625, 0.0625, 0.75, 0.59375, 0.3125, 0.8125), BooleanOp.OR);
            shape = Shapes.join(shape, Shapes.box(0.28125, 0.09375, 0.40625, 0.34375, 0.28125, 0.59375), BooleanOp.OR);
        }
        return shape;
    }
}
