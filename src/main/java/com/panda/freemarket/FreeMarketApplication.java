package com.panda.freemarket;

import com.panda.freemarket.health.DBHealthCheck;
import com.panda.freemarket.resources.BidResource;
import com.panda.freemarket.resources.BuyerResource;
import com.panda.freemarket.resources.ProjectResource;
import com.panda.freemarket.resources.SellerResource;
import com.panda.freemarket.services.StorageService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * Created by gzge. All Rights Reserved
 */
public class FreeMarketApplication extends Application<FreeMarketConfiguration> {

    @Override
    public void initialize(Bootstrap<FreeMarketConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<FreeMarketConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(FreeMarketConfiguration config) {
                return config.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(FreeMarketConfiguration config, Environment environment) throws Exception {
        // set up db
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "h2");
        final StorageService storageService = jdbi.onDemand(StorageService.class);

        // set up resources
        environment.jersey().register(new SellerResource(storageService));
        environment.jersey().register(new BuyerResource(storageService));
        environment.jersey().register(new BidResource(storageService));
        environment.jersey().register(new ProjectResource(storageService));

        // register health checks
        environment.healthChecks().register("db", new DBHealthCheck());
    }

    public static void main(String[] args) throws Exception {
        new FreeMarketApplication().run(args);
    }
}
