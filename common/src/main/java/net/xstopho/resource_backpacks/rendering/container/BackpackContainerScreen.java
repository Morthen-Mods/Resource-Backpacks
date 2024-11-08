package net.xstopho.resource_backpacks.rendering.container;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;

public class BackpackContainerScreen extends AbstractContainerScreen<BackpackContainer> {

    private final ResourceLocation BACKPACK_CONTAINER = ResourceLocation.fromNamespaceAndPath(BackpackConstants.MOD_ID, "textures/gui/container/backpack_container.png");

    private final int rows, columns;

    public BackpackContainerScreen(BackpackContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.rows = menu.getLevel().getRows();
        this.columns = menu.getLevel().getColumns();

        imageWidth = getWidth();
        imageHeight = getHeight(107);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int x = (this.width - imageWidth) / 2;
        int y = (this.height - imageHeight) / 2;

        renderBackpackContainer(guiGraphics, x, y);
        renderPlayerInventory(guiGraphics, x, y);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }

    private void renderBackpackContainer(GuiGraphics guiGraphics, int xPos, int yPos) {
        // render corners
        renderCorner(guiGraphics, Corner.TOP_LEFT, xPos, yPos);
        renderCorner(guiGraphics, Corner.TOP_RIGHT, xPos + getWidth() - 11, yPos);
        renderCorner(guiGraphics, Corner.BOTTOM_LEFT, xPos, yPos + getHeight(12));
        renderCorner(guiGraphics, Corner.BOTTOM_RIGHT, xPos + getWidth() - 11, yPos + getHeight(12));

        // render sides
        for (int i = 11; i < (getWidth() - 11); i++) {
            renderSide(guiGraphics, Side.TOP, xPos + i, yPos);
            renderSide(guiGraphics, Side.BOTTOM, xPos + i, yPos + getHeight(5));
        }
        for (int i = 11; i < (getHeight(12)); i++) {
            renderSide(guiGraphics, Side.LEFT, xPos, yPos + i);
            renderSide(guiGraphics, Side.RIGHT, xPos + getWidth() - 18, yPos + i);
        }

        // render slots
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                renderSlot(guiGraphics, xPos + 7 + (column * 18), yPos + 17 + (row * 18));
            }
        }
    }

    private void renderSlot(GuiGraphics guiGraphics, int xPos, int yPos) {
        guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 23, 1, 18, 18);
    }

    private void renderPlayerInventory(GuiGraphics guiGraphics, int xPos, int yPos) {
        int vOffset = this.columns <= 9 ? 109 : 22;
        int yOffset = this.columns <= 9 ? 19 : 20;
        guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos + ((getWidth() - 175) / 2), yPos + getHeight(yOffset), 0f, 0f, 0, vOffset, 176, 87);

    }

    private void renderCorner(GuiGraphics guiGraphics, Corner type, int xPos, int yPos) {
        switch (type) {
            case TOP_LEFT -> guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 0, 0, 11, 11);
            case BOTTOM_LEFT -> guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 0, 11, 11, 11);
            case TOP_RIGHT -> guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 11, 0, 11, 11);
            case BOTTOM_RIGHT -> guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 11, 11, 11, 11);
        }
    }

    private void renderSide(GuiGraphics guiGraphics, Side side, int xPos, int yPos) {
        switch (side) {
            case TOP -> guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 4, 0, 1, 18);
            case LEFT -> guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 0, 4, 18, 1);
            case BOTTOM -> guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 3, 4, 1, 18);
            case RIGHT -> guiGraphics.blit(RenderType::guiTextured, BACKPACK_CONTAINER, xPos, yPos, 0f, 0f, 4, 3, 18, 1);
        }
    }

    private int getWidth() {
        return 14 + (this.columns * 18);
    }

    // with player inv 107 only backpack 23
    private int getHeight(int yOffset) {
        return yOffset + (this.rows * 18);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (KeyMappingRegistry.OPEN_BACKPACK.matches(keyCode, scanCode)) {
            this.onClose();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private enum Corner {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
    }

    private enum Side {
        TOP, LEFT, BOTTOM, RIGHT;
    }
}
