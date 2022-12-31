package dev.lukebemish.dynamicassetgenerator;

import dev.lukebemish.dynamicassetgenerator.impl.DynamicAssetGenerator;
import dev.lukebemish.dynamicassetgenerator.impl.GeneratedPackResources;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;

public class DynamicAssetGeneratorInit implements ModInitializer {
    @Override
    public void onInitialize() {
        DynamicAssetGenerator.init();
        registerForType(PackType.SERVER_DATA);
    }

    static void registerForType(PackType type) {
        RRPCallback.AFTER_VANILLA.register(a ->
            DynamicAssetGenerator.caches.forEach((location, info) -> {
                if (info.cache().getPackType() == type) {
                    PackMetadataSection metadata = DynamicAssetGenerator.fromCache(info.cache());
                    Pack pack = Pack.create(DynamicAssetGenerator.MOD_ID+':'+info.cache().getName().toString(),
                            Component.literal(info.cache().getName().toString()),
                            true,
                            s -> new GeneratedPackResources(info.cache()),
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(), FeatureFlagSet.of()),
                            type,
                            info.position(),
                            true,
                            PackSource.DEFAULT
                    );
                    a.add(pack.open());
                }
            })
        );
    }
}
