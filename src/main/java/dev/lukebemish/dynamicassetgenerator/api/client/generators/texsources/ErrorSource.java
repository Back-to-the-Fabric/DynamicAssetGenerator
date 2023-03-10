package dev.lukebemish.dynamicassetgenerator.api.client.generators.texsources;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lukebemish.dynamicassetgenerator.api.client.generators.ITexSource;
import dev.lukebemish.dynamicassetgenerator.api.client.generators.TexSourceDataHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public record ErrorSource(String message) implements ITexSource {
    static final String nonExistentError = "Texture given was nonexistent...";
    static final String nonExistentErrorF = "Texture given was nonexistent...\n{}";
    public static final Codec<ErrorSource> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("message").forGetter(ErrorSource::message)
    ).apply(instance, ErrorSource::new));

    @Override
    public Codec<? extends ITexSource> codec() {
        return CODEC;
    }

    @Override
    public @NotNull Supplier<NativeImage> getSupplier(TexSourceDataHolder data) throws JsonSyntaxException {
        return () -> {
            data.getLogger().error(message());
            return null;
        };
    }
}
