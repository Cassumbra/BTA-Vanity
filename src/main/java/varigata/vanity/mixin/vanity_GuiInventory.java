package varigata.vanity.mixin;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import varigata.vanity.PlayerVanityAccessor;

@Mixin(value = GuiInventory.class, remap = false)
public class vanity_GuiInventory extends GuiScreen {

    @Shadow protected int armourButtonFloatX;

    int hideArmorButtonFloatX = 61;

    GuiButton hideArmorButton;


    @Inject(method = "initGui", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/GuiInventory;updateOverlayButtons()V"))
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

    @Inject(method = "drawScreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", shift = At.Shift.AFTER))
    public void vanity_drawScreen(int x, int y, float renderPartialTicks, CallbackInfo ci) {
        if (this.hideArmorButton != null) {

            this.drawTexturedModalRect(this.hideArmorButton.xPosition, this.hideArmorButton.yPosition, 176, 0, this.hideArmorButton.getWidth(), this.hideArmorButton.getHeight());

            if (this.hideArmorButton.isHovered(x, y) ^ ((PlayerVanityAccessor)this.mc.thePlayer).isArmorHidden()) {
                this.drawTexturedModalRect(this.hideArmorButton.xPosition + 1, this.hideArmorButton.yPosition, 195, 0, this.hideArmorButton.getWidth(), this.hideArmorButton.getHeight());
            }
        }
    }

    @Inject(method = "actionPerformed", at = @At(value = "HEAD"))
    protected void actionPerformed(GuiButton guibutton, CallbackInfo ci) {
        //System.out.println("AA");
        if (guibutton == this.hideArmorButton) {
            ((PlayerVanityAccessor)this.mc.thePlayer).toggleArmorHidden();
            //this.mc.gameSettings.armorProtectionOverlay.toggle();
        }
    }
}
