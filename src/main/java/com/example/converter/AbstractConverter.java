package com.example.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E,D> {
    public abstract D convert(E entity);

    public List<D> convert(List<E> entities) {
        if(entities == null) {
            return Collections.emptyList();
        } else {
            return entities.stream().map(this::convert).collect(Collectors.toList());
        }
    }
}
