package dev.lukebemish.dynamicassetgenerator.mixin;

import dev.lukebemish.dynamicassetgenerator.api.client.ClientPrePackRepository;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(Minecraft.class)
public class MinecraftMixin {
    @ModifyVariable(
        method = "reloadResourcePacks(Z)Ljava/util/concurrent/CompletableFuture;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/packs/repository/PackRepository;openAllSelected()Ljava/util/List;",
            shift = At.Shift.AFTER
        ),
        ordinal = 0,
        require = 0,
        argsOnly = true
    )
    private Minecraft reloadResourcePacksDynamicAssetsModifyList(Minecraft value) {
        ClientPrePackRepository.resetResources();
        return value;
    }

    @ModifyVariable(
        method = "<init>",
        at = @At(
            value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/server/packs/repository/PackRepository;openAllSelected()Ljava/util/List;",
            shift = At.Shift.AFTER
        ),
        ordinal = 0,
        require = 0,
        argsOnly = true
    )
    private Minecraft initDynamicAssetsModifyList(Minecraft value) {
        ClientPrePackRepository.resetResources();
        return value;
    }

}
