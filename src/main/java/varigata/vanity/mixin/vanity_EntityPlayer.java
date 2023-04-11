package varigata.vanity.mixin;

import net.minecraft.src.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import varigata.vanity.PlayerVanityAccessor;

@Mixin(value = EntityPlayer.class, remap = false)
public class vanity_EntityPlayer implements PlayerVanityAccessor {
    boolean armorHidden = false;
    @Override
    public boolean isArmorHidden() {
        return armorHidden;
    }

    @Override
    public void toggleArmorHidden() {
        armorHidden = !armorHidden;
    }
}
