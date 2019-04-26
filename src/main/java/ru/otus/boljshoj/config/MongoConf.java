package ru.otus.boljshoj.config;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.boljshoj.changelog.DBChangeLog;

@Configuration
public class MongoConf {

    @Value("${mongo.db.name}")
    private String MONGO_DB;

    @Autowired
    private MongoClient mongoClient;

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient, MONGO_DB);
    }

    @Bean
    public Mongobee mongobee(Environment environment){
        Mongobee runner = new Mongobee(mongoClient);
        runner.setDbName(MONGO_DB);
        runner.setChangeLogsScanPackage(DBChangeLog.class.getPackage().getName());
        runner.setSpringEnvironment(environment);
        return runner;
    }
}