package dev.lukebemish.dynamicassetgenerator;

import dev.lukebemish.dynamicassetgenerator.impl.client.DynamicAssetGeneratorClient;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.packs.PackType;

@Environment(EnvType.CLIENT)
public class DynamicAssetGeneratorInitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DynamicAssetGeneratorClient.init();
        DynamicAssetGeneratorInit.registerForType(PackType.CLIENT_RESOURCES);

    }
}
