package com.example.pp.models;


import java.util.*;

public class UserInMemoryStore implements Store<Shop> {

    private Map<Long, Shop> users = new LinkedHashMap<>();

    @Override
    public Optional<Long> create(Shop user) {
        users.put(user.getId(), user);
        return Optional.of(user.getId());
    }

    @Override
    public List<Shop> readAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<Shop> read(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<Shop> update(Shop user) {
        return Optional.ofNullable(users.replace(user.getId(), user));
    }

    @Override
    public Optional<Shop> delete(long id) {
        return Optional.ofNullable(users.remove(id));
    }
}
