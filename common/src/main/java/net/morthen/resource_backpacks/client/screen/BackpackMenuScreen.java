package net.morthen.resource_backpacks.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.morthen.resource_backpacks.BackpackConstants;
import net.morthen.resource_backpacks.client.metadata.BackpackColorMetadata;
import net.morthen.resource_backpacks.client.util.ResourcePackUtils;
import net.morthen.resource_backpacks.registries.KeyMappingRegistry;

import java.util.LinkedList;


public class BackpackMenuScreen extends AbstractContainerScreen<BackpackMenu> {

    private final Identifier SLOT = texture("slot");
    private final Identifier CORNER = texture("corner");
    private final Identifier SIDE_VERTICAL = texture("side_vertical");
    private final Identifier SIDE_HORIZONTAL = texture("side_horizontal");
    private final Identifier INVENTORY_NORMAL = texture("inventory_normal");
    private final Identifier INVENTORY_EXTENDED = texture("inventory_extended");

    private final int rows, columns;

    public BackpackMenuScreen(BackpackMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title,
                (menu.getBackpackLevel().getColumns() * 18) + 14,
                (menu.getBackpackLevel().getRows() * 18) + 125);

        this.rows = menu.getBackpackLevel().getRows();
        this.columns = menu.getBackpackLevel().getColumns();
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        int x = (this.width - imageWidth) / 2;
        int y = (this.height - imageHeight) / 2;

        renderBackpackMenu(graphics, x, y);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        LinkedList<BackpackColorMetadata> data = ResourcePackUtils.readAllMetaData(BackpackColorMetadata.TYPE);
        int titleColor = data.isEmpty() ? 0xff404040 : data.getLast().getColor();
        graphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, titleColor, false);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        this.extractBlurredBackground(graphics);
        this.extractBackground(graphics, mouseX, mouseY, partialTick);
        super.extractContents(graphics, mouseX, mouseY, partialTick);
        this.extractTooltip(graphics, mouseX, mouseY);
    }


    private void renderBackpackMenu(GuiGraphicsExtractor guiGraphics, int xPos, int yPos) {
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

    private void renderCorner(GuiGraphicsExtractor guiGraphics, int xPos, int yPos, float xOffset, float yOffset) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, CORNER, xPos, yPos, xOffset, yOffset, 11, 11, 22, 22);
    }

    private void renderHorizontalSide(GuiGraphicsExtractor guiGraphics, int xPos, int yPos, float xOffset, float yOffset, int width, int height) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SIDE_HORIZONTAL, xPos, yPos, xOffset, yOffset, width, height, 22, 22);
    }

    private void renderVerticalSide(GuiGraphicsExtractor guiGraphics, int xPos, int yPos, float xOffset, float yOffset, int width, int height) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SIDE_VERTICAL, xPos, yPos, xOffset, yOffset, width, height, 22, 22);
    }

    private void renderSlots(GuiGraphicsExtractor guiGraphics, int xPos, int yPos, int width, int height) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, SLOT, xPos, yPos, 0f, 0f, width, height, 18, 18);
    }

    private void renderPlayerInventory(GuiGraphicsExtractor guiGraphics, int xPos, int yPos) {
        Identifier inventory = this.columns < 10 ? INVENTORY_NORMAL : INVENTORY_EXTENDED;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, inventory, xPos + ((getWidth() - 175) / 2), yPos + getHeight() + 7,
                0f, 0f, 176, 87, 176, 87);
    }

    private int getWidth() {
        return (this.columns * 18) + 14;
    }

    private int getHeight() {
        return (this.rows * 18) + 18;
    }

    @Override
    public boolean keyPressed(KeyEvent keyEvent) {
        if (KeyMappingRegistry.OPEN_BACKPACK.matches(keyEvent)) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyEvent);
    }

    private Identifier texture(String texture) {
        return BackpackConstants.of(String.format("textures/gui/container/backpack/%s.png", texture));
    }
}
