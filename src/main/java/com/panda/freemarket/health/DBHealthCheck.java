package com.panda.freemarket.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by gzge. All Rights Reserved
 */
public class DBHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
