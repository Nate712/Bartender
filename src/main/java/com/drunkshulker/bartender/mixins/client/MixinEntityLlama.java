package com.drunkshulker.bartender.mixins.client;

import com.drunkshulker.bartender.Bartender;

import com.drunkshulker.bartender.util.salhack.events.entity.EventSteerEntity;
import net.minecraft.entity.passive.EntityLlama;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLlama.class)
public class MixinEntityLlama
{
    @Inject(method = "canBeSteered", at = @At("HEAD"), cancellable = true)
    public void canBeSteered(CallbackInfoReturnable<Boolean> cir)
    {
        EventSteerEntity event = new EventSteerEntity();
        Bartender.EVENT_BUS.post(event);

        if (event.isCancelled())
        {
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
}
