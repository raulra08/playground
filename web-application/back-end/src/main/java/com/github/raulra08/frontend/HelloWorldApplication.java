package com.github.raulra08.frontend;

import com.github.raulra08.frontend.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {}

    @Override
    public void run(HelloWorldConfiguration conf, Environment env) {
        HelloWorldResource resource = new HelloWorldResource(conf.getTemplate(), conf.getDefaultName());
        TemplateHealthCheck healthCheck = new TemplateHealthCheck(conf.getTemplate());
        env.healthChecks().register("template", healthCheck);
        env.jersey().register(resource);
    }

}
