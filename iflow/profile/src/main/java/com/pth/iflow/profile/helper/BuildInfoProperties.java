package com.pth.iflow.profile.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:META-INF/build-info.properties")
public class BuildInfoProperties {

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.time}")
    private String buildTime;

    @Value("${build.name}")
    private String buildName;

    public String getBuildVersion() {
	return buildVersion;
    }

    public String getBuildTime() {
	return buildTime;
    }

    public String getBuildName() {
	return buildName;
    }

}
