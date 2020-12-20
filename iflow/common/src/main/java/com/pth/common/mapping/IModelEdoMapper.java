package com.pth.common.mapping;

import java.util.Collection;
import java.util.List;


public interface IModelEdoMapper<M, E> {

  M fromEdo(E edo);

  E toEdo(M model);

  List<E> toEdoList(final Collection<M> modelList);

  List<M> fromEdoList(final Collection<E> edoList);

}
