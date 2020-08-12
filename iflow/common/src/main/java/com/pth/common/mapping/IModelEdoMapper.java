package com.pth.common.mapping;

import java.util.List;


public interface IModelEdoMapper<M, E> {

  M fromEdo(E edo);

  E toEdo(M model);

  List<E> toEdoList(final List<M> modelList);

  List<M> fromEdoList(final List<E> edoList);

}
