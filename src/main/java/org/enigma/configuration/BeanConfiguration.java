package org.enigma.configuration;

import org.enigma.repository.*;
import org.enigma.service.*;
import org.enigma.utils.RandomUuid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Profile("!local")
@Configuration
@PropertySource("classpath:application.properties")
public class BeanConfiguration {
    @Value("${dbdriver}")
    private String dbDriver;
    @Value("${url}")
    private String url;
    @Value("${dbuser}")
    private String dbUser;
    @Value("${dbpassword}")
    private String dbPassword;

    @Bean
    public IStoreService getStoreService(){return new StoreService((StoreRepository) getStoreRepo());}

    @Bean
    public ITransactionService getTransactionService(){return new TransactionService((TransactionRepository) getTransactionRepo(),(StoreService) getStoreService());}
    @Bean
    public ReportService getReportService(){return new ReportService(getReportRepo());}
    @Bean
    public ITransactionRepository getTransactionRepo(){return new TransactionRepository(dataSource());
    }
    @Bean
    public IStoreRepository getStoreRepo(){return new StoreRepository(dataSource());}
    @Bean
    public ReportRepository getReportRepo(){return new ReportRepository(dataSource());}

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        driverManagerDataSource.setDriverClassName(dbDriver);

        return driverManagerDataSource;
    }
    @Bean
    public RandomUuid getRandomUUID(){return new RandomUuid();}
}

