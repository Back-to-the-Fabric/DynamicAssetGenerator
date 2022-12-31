package dev.lukebemish.dynamicassetgenerator;

import dev.lukebemish.dynamicassetgenerator.api.client.AssetResourceCache;
import dev.lukebemish.dynamicassetgenerator.impl.DynamicAssetGenerator;
import dev.lukebemish.dynamicassetgenerator.impl.client.DynamicAssetGeneratorClient;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class DynamicAssetGeneratorInitClient implements ClientModInitializer {
    public static RuntimeResourcePack RESOURCE_PACK;
    @Override
    public void onInitializeClient() {
        RRPCallback.AFTER_VANILLA.register(a -> {
            RESOURCE_PACK = RuntimeResourcePack.create(DynamicAssetGenerator.CLIENT_PACK);
            Map<ResourceLocation, Supplier<InputStream>> map = AssetResourceCache.INSTANCE.getResources();

            for (ResourceLocation rl : map.keySet()) {
                Supplier<InputStream> stream = map.get(rl);
                if (stream != null) {
                    RESOURCE_PACK.addLazyResource(PackType.CLIENT_RESOURCES, rl, (i,r) -> {
                        try (InputStream is = stream.get()) {
                            if (is == null) {
                                DynamicAssetGenerator.LOGGER.error(
                                        "No InputStream supplied for {}; attempting not to die terribly...",
                                        rl);
                            }
                            return is == null ? null : is.readAllBytes();
                        } catch (IOException e) {
                            DynamicAssetGenerator.LOGGER.error(e);
                        }
                        return null;
                    });
                }
            }
            a.add(RESOURCE_PACK);
        });
        DynamicAssetGeneratorClient.init();
    }
}
