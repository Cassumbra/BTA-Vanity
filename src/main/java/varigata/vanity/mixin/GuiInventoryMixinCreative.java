package varigata.vanity.mixin;


import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiInventoryCreative;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiInventoryCreative.class, remap = false)
public class GuiInventoryMixinCreative extends GuiInventoryMixin {

    //TODO: This sucks. There has to be a better way to do this.
    int hideArmorButtonFloatX = 117;

    @Override
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

}
