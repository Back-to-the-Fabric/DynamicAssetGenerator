package dev.lukebemish.dynamicassetgenerator.mixin;

import dev.lukebemish.dynamicassetgenerator.api.ServerPrePackRepository;
import dev.lukebemish.dynamicassetgenerator.api.client.ClientPrePackRepository;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.MultiPackResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(MultiPackResourceManager.class)
public class MultiPackResourceManagerMixin {
    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
    private static List<PackResources> dynamicAssetGeneratorLoadPacks(List<PackResources> packs, PackType type,
                                                                      List<PackResources> packsAgain) {
        if (type == PackType.CLIENT_RESOURCES) {
            ClientPrePackRepository.resetResources();
        }
        if (type == PackType.SERVER_DATA) {
            ServerPrePackRepository.loadResources(packs);
        }
        return packs;
    }
}
