package com.example.pp.models;

import java.util.List;
import java.util.Optional;

public interface StoreProduct<Entity> {

    Optional<Long> create(Product product);

    List<Entity> readAll();

    Optional<Entity> read(long id);

    Optional<Entity> update(Entity item);

    Optional<Entity> delete(long id);
}