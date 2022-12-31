package dev.lukebemish.dynamicassetgenerator.api;

import dev.lukebemish.dynamicassetgenerator.api.sources.TagBakery;
import dev.lukebemish.dynamicassetgenerator.impl.DynamicAssetGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.jetbrains.annotations.NotNull;

public class DataResourceCache extends ResourceCache {
    private final TagBakery tagBakery = new TagBakery();

    public DataResourceCache(ResourceLocation name) {
        super(name);
        this.planSource(tagBakery);
    }

    @Override
    public boolean shouldCache() {
        return DynamicAssetGenerator.getConfig().cacheData();
    }

    @Override
    public @NotNull PackType getPackType() {
        return PackType.SERVER_DATA;
    }

    @SuppressWarnings("unused")
    public TagBakery tags() {
        return tagBakery;
    }
}
