package dev.lukebemish.dynamicassetgenerator.impl.util;

@FunctionalInterface
public interface SupplierWithException <T,E extends Throwable> {
    T get() throws E;
}
