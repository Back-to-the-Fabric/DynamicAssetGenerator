package dev.lukebemish.dynamicassetgenerator.api;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface IPathAwareInputStreamSource extends IInputStreamSource {

    @NotNull
    Set<ResourceLocation> getLocations();
}
