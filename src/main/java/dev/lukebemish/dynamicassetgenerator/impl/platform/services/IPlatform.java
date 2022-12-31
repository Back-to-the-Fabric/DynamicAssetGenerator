package dev.lukebemish.dynamicassetgenerator.impl.platform.services;

import java.nio.file.Path;

public interface IPlatform {
    Path getConfigFolder();
    Path getModDataFolder();
    boolean isDev();
}
