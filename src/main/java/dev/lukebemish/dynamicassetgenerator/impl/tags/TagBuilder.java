package dev.lukebemish.dynamicassetgenerator.impl.tags;

import com.mojang.datafixers.util.Pair;
import dev.lukebemish.dynamicassetgenerator.api.IPathAwareInputStreamSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.function.Supplier;

public class TagBuilder implements IPathAwareInputStreamSource {
    private final List<Supplier<Set<ResourceLocation>>> paths = new ArrayList<>();
    private final ResourceLocation location;
    private final ResourceLocation tagLocation;
    private final List<Supplier<Map<ResourceLocation,Set<ResourceLocation>>>> queue;

    public TagBuilder(ResourceLocation location, ResourceLocation tagLocation, List<Supplier<Map<ResourceLocation,Set<ResourceLocation>>>> queue) {
        this.location = location;
        this.tagLocation = tagLocation;
        this.queue = queue;
    }

    public void add(Pair<ResourceLocation,Supplier<Boolean>> p) {
        paths.add(() -> Boolean.TRUE.equals(p.getSecond().get())?Set.of(p.getFirst()):Set.of());
    }

    public void add(Supplier<Set<ResourceLocation>> rls) {
        paths.add(rls);
    }

    @Override
    public @NotNull Supplier<InputStream> get(ResourceLocation outRl) {
        return this::build;
    }

    private InputStream build() {
        StringBuilder internal = new StringBuilder();
        Set<ResourceLocation> toAdd = new HashSet<>();
        for (Supplier<Set<ResourceLocation>> p : paths) {
            toAdd.addAll(p.get());
        }
        for (var queuePart : queue) {
            toAdd.addAll(queuePart.get().getOrDefault(tagLocation, Set.of()));
        }
        toAdd.forEach(rl -> {
            if (internal.length() >= 1) {
                internal.append(",\n");
            }
            internal.append("    \"").append(rl.getNamespace()).append(":").append(rl.getPath()).append("\"");
        });
        String json = "{\n  \"replace\":false,\n  \"values\":["+internal+"\n]}";
        return new ByteArrayInputStream(json.getBytes());
    }

    @Override
    @NotNull
    public Set<ResourceLocation> getLocations() {
        return Set.of(location);
    }
}
