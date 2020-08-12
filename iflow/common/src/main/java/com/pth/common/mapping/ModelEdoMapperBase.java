package com.pth.common.mapping;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ModelEdoMapperBase <M, E>{

    public abstract  M fromEdo(E edo);

    public abstract E toEdo(M model);

    public List<E> toEdoList(List<M> modelList) {
        return modelList.stream().map(m -> toEdo(m)).collect(Collectors.toList());
    }


    public List<M> fromEdoList(List<E> edoList) {
        return edoList.stream().map(e -> fromEdo(e)).collect(Collectors.toList());
    }
}
