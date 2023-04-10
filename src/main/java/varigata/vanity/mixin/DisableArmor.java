package varigata.vanity.mixin;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import varigata.vanity.PlayerVanityAccessor;

@Mixin(value = RenderPlayer.class, remap = false)
public class DisableArmor {

    public boolean hideArmor = false;

    @Inject(method = "setArmorModel", at = @At(value = "HEAD"), cancellable = true)
    void vanity_setArmorModel(EntityPlayer entityplayer, int i, float f, CallbackInfoReturnable<Boolean> cir) {
        if (((PlayerVanityAccessor)entityplayer).isArmorHidden()) {
            cir.setReturnValue(false);
            return;
        }

    }
}
