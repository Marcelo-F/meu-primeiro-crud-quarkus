package com.github.Marcelo.F;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

public class DatabaseLifeCycle implements QuarkusTestResourceLifecycleManager {

    private static PostgreSQLContainer<?> POSTGRESQL= new PostgreSQLContainer<>("dpage/pgadmin4:11");

    public Map<String, String> start() {
        POSTGRESQL.start();
    Map<String, String> propriedades = new HashMap<>();
        propriedades.put("quarkus.datasource.driver", POSTGRESQL.getJdbcUrl());
        propriedades.put("quarkus.datasource.username", POSTGRESQL.getUsername());
        propriedades.put("quarkus.datasource.password", POSTGRESQL.getPassword());

        return propriedades;
    }

    @Override
    public void stop() {
        if(POSTGRESQL != null){
            POSTGRESQL.stop();
        }

    }
}
