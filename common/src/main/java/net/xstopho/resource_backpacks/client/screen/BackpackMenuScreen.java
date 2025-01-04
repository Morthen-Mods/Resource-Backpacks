package net.xstopho.resource_backpacks.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.xstopho.resource_backpacks.BackpackConstants;
import net.xstopho.resource_backpacks.backpack.util.BackpackStyle;
import net.xstopho.resource_backpacks.config.ClientConfig;
import net.xstopho.resource_backpacks.registries.KeyMappingRegistry;


public class BackpackMenuScreen extends AbstractContainerScreen<BackpackMenu> {

    private final ResourceLocation SLOT = texture("slot");
    private final ResourceLocation CORNER = texture("corner");
    private final ResourceLocation SIDE_VERTICAL = texture("side_vertical");
    private final ResourceLocation SIDE_HORIZONTAL = texture("side_horizontal");
    private final ResourceLocation INVENTORY_NORMAL = texture("inventory_normal");
    private final ResourceLocation INVENTORY_EXTENDED = texture("inventory_extended");

    private final int rows, columns;

    public BackpackMenuScreen(BackpackMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        this.rows = menu.getBackpackLevel().getRows();
        this.columns = menu.getBackpackLevel().getColumns();

        imageWidth = getWidth();
        imageHeight = getHeight() + 107;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int mouseX, int mouseY) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int x = (this.width - imageWidth) / 2;
        int y = (this.height - imageHeight) / 2;

        renderBackpackMenu(guiGraphics, x, y);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
    }

    private void renderBackpackMenu(GuiGraphics guiGraphics, int xPos, int yPos) {
        // render corners
        renderCorner(guiGraphics, xPos, yPos, 0f, 0f); // Top Left
        renderCorner(guiGraphics, xPos, yPos + getHeight(), 0f, 11f); // Bottom Left
        renderCorner(guiGraphics, xPos + getWidth() - 11, yPos, 11f, 0f); // Top Right
        renderCorner(guiGraphics, xPos + getWidth() - 11, yPos + getHeight(), 11f, 11f); // Bottom Right

        renderHorizontalSide(guiGraphics, xPos + 11, yPos, 4f, 0f, this.getWidth() - 22, 18); // Top
        renderHorizontalSide(guiGraphics, xPos + 11, yPos + getHeight() - 7, 4f, 4f, this.getWidth() - 22, 18); // Bottom


        renderVerticalSide(guiGraphics, xPos, yPos + 11, 0f, 4f, 18, this.getHeight() - 11); // Left
        renderVerticalSide(guiGraphics, xPos + getWidth() - 18, yPos + 11 , 4f, 4f, 18, this.getHeight() - 11); // Right

        renderSlots(guiGraphics, xPos + 7, yPos + 17, this.columns * 18, this.rows * 18);

        renderPlayerInventory(guiGraphics, xPos, yPos);
    }

    private void renderCorner(GuiGraphics guiGraphics, int xPos, int yPos, float xOffset, float yOffset) {
        guiGraphics.blit(CORNER, xPos, yPos, xOffset, yOffset, 11, 11, 22, 22);
    }

    private void renderHorizontalSide(GuiGraphics guiGraphics, int xPos, int yPos, float xOffset, float yOffset, int width, int height) {
        guiGraphics.blit(SIDE_HORIZONTAL, xPos, yPos, xOffset, yOffset, width, height, 22, 22);
    }

    private void renderVerticalSide(GuiGraphics guiGraphics, int xPos, int yPos, float xOffset, float yOffset, int width, int height) {
        guiGraphics.blit(SIDE_VERTICAL, xPos, yPos, xOffset, yOffset, width, height, 22, 22);
    }

    private void renderSlots(GuiGraphics guiGraphics, int xPos, int yPos, int width, int height) {
        guiGraphics.blit(SLOT, xPos, yPos, 0f, 0f, width, height, 18, 18);
    }

    private void renderPlayerInventory(GuiGraphics guiGraphics, int xPos, int yPos) {
        ResourceLocation inventory = this.columns < 10 ? INVENTORY_NORMAL : INVENTORY_EXTENDED;
        guiGraphics.blit(inventory, xPos + ((getWidth() - 175) / 2), yPos + getHeight() + 7,
                0f, 0f, 176, 87, 176, 87);
    }

    private int getWidth() {
        return (this.columns * 18) + 14;
    }

    private int getHeight() {
        return (this.rows * 18) + 18;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (KeyMappingRegistry.OPEN_BACKPACK.matches(keyCode, scanCode)) {
            this.onClose();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private ResourceLocation texture(String name) {
        BackpackStyle style = ClientConfig.style;
        return BackpackConstants.of(String.format("textures/gui/container/%s/%s.png", style.name().toLowerCase(), name));
    }
}
