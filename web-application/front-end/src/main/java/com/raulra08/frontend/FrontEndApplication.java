package com.raulra08.frontend;

import com.raulra08.frontend.health.TemplateHealthCheck;
import com.raulra08.frontend.resources.IndexResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class FrontEndApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new FrontEndApplication().run(args);
    }

    @Override
    public String getName() {
        return "front-end";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<AppConfiguration>());
    }

    @Override
    public void run(AppConfiguration conf, Environment environment) {

        environment.healthChecks().register("template", new TemplateHealthCheck());
        environment.jersey().register(new IndexResource());
    }

}
