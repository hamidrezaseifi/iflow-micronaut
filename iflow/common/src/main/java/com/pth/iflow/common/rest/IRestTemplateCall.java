package com.pth.iflow.common.rest;

import java.net.URI;

import com.pth.iflow.common.enums.EModule;

/**
 * @author rezasei
 *
 */
public interface IRestTemplateCall {

  <I, O> O callRestPost(final URI uri, final EModule service, final I edo, final Class<O> response,
      final String token,
      boolean throwError) throws RestTemplateException;

  <O> O callRestGet(final URI uri, final EModule service, final Class<O> responseClass, final String token,
      boolean throwError)
      throws RestTemplateException;

}
