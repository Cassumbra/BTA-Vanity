package varigata.vanity.mixin;

import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiInventory.class, remap = false)
public class vanity_GuiInventory extends GuiScreen {

    @Shadow protected int armourButtonFloatX;

    int hideArmorButtonFloatX = 61;

    GuiButton hideArmorButton;


    @Inject(method = "initGui", at = @At(value = "HEAD"))
    void vanity_initGui(CallbackInfo ci) {
        this.hideArmorButton = new GuiButton(101, this.width / 2 - hideArmorButtonFloatX, this.height / 2 - 74, 9, 9, "");
        this.hideArmorButton.visible = false;
        //boolean enableArmorButton = false;

        //for(int i = 0; i < this.mc.thePlayer.inventory.getSizeInventory(); ++i) {
        //    ItemStack itemStack = this.mc.thePlayer.inventory.getStackInSlot(i);
        //    if (itemStack != null && itemStack.getItem() instanceof ItemArmor) {
        //        enableArmorButton = true;
        //        break;
        //    }
        //}

        //if (enableArmorButton) {
            //this.hideArmorButton = new GuiButton(101, this.width / 2 - this.armourButtonFloatX, this.height / 2 - 74, 9, 9, "");
            //this.hideArmorButton.visible = false;
            this.controlList.add(this.hideArmorButton);
        //} else {
        //    this.hideArmorButton = null;
        //}


    }

    @Inject(method = "drawScreen", at = @At(value = "TAIL"))
    public void vanity_drawScreen(int x, int y, float renderPartialTicks, CallbackInfo ci) {
        if (this.hideArmorButton != null) {

            this.drawTexturedModalRect(this.hideArmorButton.xPosition, this.hideArmorButton.yPosition, 176, 0, this.hideArmorButton.getWidth(), this.hideArmorButton.getHeight());

            // TODO: Draw an X over the symbol when visibility is off AND when visibility is on and hovering over the symbol + vice versa
            //if (this.hideArmorButton.isHovered(x, y)) {
            //    this.drawTexturedModalRect(this.hideArmorButton.xPosition, this.hideArmorButton.yPosition, 185, 0, this.hideArmorButton.getWidth(), this.hideArmorButton.getHeight());
            //} else {
            //    this.drawTexturedModalRect(this.hideArmorButton.xPosition, this.hideArmorButton.yPosition, 176, 0, this.hideArmorButton.getWidth(), this.hideArmorButton.getHeight());
            //}
        }
    }

    @Inject(method = "actionPerformed", at = @At(value = "HEAD"))
    protected void actionPerformed(GuiButton guibutton, CallbackInfo ci) {
        if (guibutton == this.hideArmorButton) {
            this.mc.gameSettings.armorProtectionOverlay.toggle();
        }
    }
}
