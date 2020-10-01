package com.exchange.service.app.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
//@EnableMongoRepositories(basePackages = "com.baeldung.repository")
public class MongoDBConfig  {

	@Value(value = "${spring.data.mongodb.host}")
	private String host;

	@Value(value = "${spring.data.mongodb.port}")
	private String port;

	@Value(value = "${spring.data.mongodb.database}")
	private String databaseName;

	@Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://" + host + ":" + port + "/" + databaseName);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
          .applyConnectionString(connectionString)
          .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), databaseName);
    }

}
