package com.online.merchant.model.save;

@FunctionalInterface
public interface SaveTransaction<T, R> {

    R save(T transaction);
}
