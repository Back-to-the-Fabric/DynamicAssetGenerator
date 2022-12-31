package dev.lukebemish.dynamicassetgenerator.mixin;

import net.fabricmc.fabric.api.resource.ModResourcePack;
import net.fabricmc.fabric.impl.resource.loader.GroupResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(value = GroupResourcePack.class, remap = false)
public interface IGroupResourcePackMixin {
    @Accessor
    List<ModResourcePack> getPacks();
}
