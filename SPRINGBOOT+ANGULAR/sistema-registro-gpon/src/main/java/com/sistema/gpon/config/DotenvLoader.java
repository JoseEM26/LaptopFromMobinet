package com.sistema.gpon.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

public class DotenvLoader {

    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                              .filename(".env")
                              .ignoreIfMalformed()
                              .ignoreIfMissing()
                              .load();

        dotenv.entries().forEach(entry -> {
            Map<String, String> env = System.getenv();
            if (!env.containsKey(entry.getKey())) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        });
    }
}
