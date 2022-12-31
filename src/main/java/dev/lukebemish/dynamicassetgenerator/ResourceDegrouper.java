package dev.lukebemish.dynamicassetgenerator;

import com.google.auto.service.AutoService;
import dev.lukebemish.dynamicassetgenerator.impl.platform.services.IResourceDegrouper;
import dev.lukebemish.dynamicassetgenerator.mixin.IGroupResourcePackMixin;
import net.fabricmc.fabric.impl.resource.loader.GroupResourcePack;
import net.minecraft.server.packs.PackResources;

import java.util.ArrayList;
import java.util.List;

@AutoService(IResourceDegrouper.class)
public class ResourceDegrouper implements IResourceDegrouper {
    public List<? extends PackResources> unpackPacks(List<? extends PackResources> packs) {
        if (packs.stream().noneMatch(GroupResourcePack.class::isInstance)) {
            return packs;
        }
        List<PackResources> outPacks = new ArrayList<>();
        for (PackResources pack : packs) {
            if (pack instanceof GroupResourcePack groupPack) {
                outPacks.addAll(unpackPacks(((IGroupResourcePackMixin)groupPack).getPacks()));
            } else {
                outPacks.add(pack);
            }
        }
        return outPacks;
    }
}
