package com.pth.profile.services.base;

import com.pth.common.exceptions.IFlowMessageConversionFailureException;

import java.util.List;


public interface ICoreModelEdoMapperService<M, E> {

  M fromEdo(E edo) throws IFlowMessageConversionFailureException;

  E toEdo(M model);

  List<E> toEdoList(final List<M> modelList);

  List<M> fromEdoList(final List<E> edoList) throws IFlowMessageConversionFailureException;

}
