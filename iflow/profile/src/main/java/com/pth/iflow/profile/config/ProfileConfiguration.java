package com.pth.iflow.profile.config;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * a class to collect gui configuration from property file
 *
 * @author rezasei
 *
 */
@Component
public class ProfileConfiguration {

  /**
   * configs regarding core
   */
  @Component
  public static class CoreAccessConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${iflow.profile.urls.core.base}")
    private String       coreBaseUrl;

    private URI          baseCoreBaseUri;

    @PostConstruct
    private void init() throws URISyntaxException {
      this.log.info("CORE Base URL: {}", this.coreBaseUrl);
      this.baseCoreBaseUri = new URI(this.coreBaseUrl);

    }

    public URI prepareCoreUrl(final URI subUrl) throws MalformedURLException {
      return this.baseCoreBaseUri.resolve(subUrl);
    }

  }

}
