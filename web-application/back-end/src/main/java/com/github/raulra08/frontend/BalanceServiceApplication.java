package com.github.raulra08.frontend;

import com.github.raulra08.frontend.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BalanceServiceApplication extends Application<BalanceServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new BalanceServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<BalanceServiceConfiguration> bootstrap) {}

    @Override
    public void run(BalanceServiceConfiguration conf, Environment env) {

        env.healthChecks().register("template", new TemplateHealthCheck());
        env.jersey().register(new BalanceResource());
    }

}
