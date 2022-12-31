package dev.lukebemish.dynamicassetgenerator;

import com.google.auto.service.AutoService;
import dev.lukebemish.dynamicassetgenerator.impl.DynamicAssetGenerator;
import dev.lukebemish.dynamicassetgenerator.impl.platform.services.IPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

@AutoService(IPlatform.class)
public class PlatformImpl implements IPlatform {
    @Override
    public Path getConfigFolder() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public Path getModDataFolder() {
        return FabricLoader.getInstance().getGameDir().resolve("mod_data/"+DynamicAssetGenerator.MOD_ID);
    }

    @Override
    public boolean isDev() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
