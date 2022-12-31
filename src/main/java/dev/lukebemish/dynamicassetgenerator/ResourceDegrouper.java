/*
 * Copyright (C) 2022 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.dynamicassetgenerator;

import com.google.auto.service.AutoService;
import dev.lukebemish.dynamicassetgenerator.impl.platform.services.IResourceDegrouper;
import dev.lukebemish.dynamicassetgenerator.mixin.IGroupResourcePackAccessor;
import net.fabricmc.fabric.impl.resource.loader.GroupResourcePack;
import net.minecraft.server.packs.PackResources;

import java.util.ArrayList;
import java.util.List;

@AutoService(IResourceDegrouper.class)
public class ResourceDegrouper implements IResourceDegrouper {
    public List<? extends PackResources> unpackPacks(List<? extends PackResources> packs) {
        ArrayList<PackResources> packsOut = new ArrayList<>();
        packs.forEach(pack -> {
            if (pack instanceof GroupResourcePack groupResourcePack) {
                packsOut.addAll(((IGroupResourcePackAccessor) groupResourcePack).getPacks());
            } else packsOut.add(pack);
        });
        return packsOut;
    }
}
