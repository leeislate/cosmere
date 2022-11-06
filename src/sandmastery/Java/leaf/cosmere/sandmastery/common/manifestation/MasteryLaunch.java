package leaf.cosmere.sandmastery.common.manifestation;

import leaf.cosmere.api.CosmereAPI;
import leaf.cosmere.api.Manifestations;
import leaf.cosmere.api.Roshar;
import leaf.cosmere.api.Taldain;
import leaf.cosmere.api.math.VectorHelper;
import leaf.cosmere.api.spiritweb.ISpiritweb;
import leaf.cosmere.common.cap.entity.SpiritwebCapability;
import leaf.cosmere.sandmastery.client.SandmasteryKeybindings;
import leaf.cosmere.sandmastery.common.capabilities.SandmasterySpiritwebSubmodule;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
public class MasteryLaunch extends SandmasteryManifestation{
    public MasteryLaunch(Taldain.Mastery mastery) {
        super(mastery);
    }

    @Override
    public void tick(ISpiritweb data)
    {
        SpiritwebCapability playerSpiritweb = (SpiritwebCapability) data;
        SandmasterySpiritwebSubmodule submodule = (SandmasterySpiritwebSubmodule) playerSpiritweb.spiritwebSubmodules.get(Manifestations.ManifestationTypes.SANDMASTERY);
        submodule.checkRibbons(data, this);

        int mode = getMode(data);
        if (mode > 0 && SandmasteryKeybindings.SANDMASTERY_USE.isDown()) {
            applyEffectTick(data);
        }
    }

    @Override
    public void applyEffectTick(ISpiritweb data)
    {
        performEffectServer(data);
    }

    private void performEffectServer(ISpiritweb data)
    {
        SpiritwebCapability playerSpiritweb = (SpiritwebCapability) data;
        SandmasterySpiritwebSubmodule submodule = (SandmasterySpiritwebSubmodule) playerSpiritweb.spiritwebSubmodules.get(Manifestations.ManifestationTypes.SANDMASTERY);

        if(!submodule.adjustHydration(-10, false)) return;

        LivingEntity living = data.getLiving();
        Vec3 direction = living.getForward();

        int scaleFactor = getMode(data);
        Vec3 add = living.getDeltaMovement().add(direction.multiply(scaleFactor, scaleFactor, scaleFactor));
        living.setDeltaMovement(VectorHelper.ClampMagnitude(add, 10));
        living.hurtMarked = true; // Allow the game to move the player

        data.setMode(this, getMode(data)-1);

        submodule.adjustHydration(-10, true);
    }
}
