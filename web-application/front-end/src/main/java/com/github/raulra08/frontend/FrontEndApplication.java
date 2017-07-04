package com.github.raulra08.frontend;

import com.github.raulra08.frontend.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FrontEndApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new FrontEndApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {}

    @Override
    public void run(AppConfiguration conf, Environment env) {
        MainResource resource = new MainResource(conf.getTemplate(), conf.getDefaultName());
        TemplateHealthCheck healthCheck = new TemplateHealthCheck(conf.getTemplate());
        env.healthChecks().register("template", healthCheck);
        env.jersey().register(resource);
    }

}
