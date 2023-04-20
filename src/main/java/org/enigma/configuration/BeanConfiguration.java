package org.enigma.configuration;

import org.enigma.repository.*;
import org.enigma.service.*;
import org.enigma.utils.RandomUuid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

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
    public IStoreService getProductService(){return new ProductService((ProductRepository) getProductRepo());}
    @Bean
    public ITransactionService getTransactionService(){return new TransactionService((TransactionRepository) getTransactionRepo(),(StockService) getStockService(),(PriceService) getPriceService(),(ProductService) getProductService());}
    @Bean
    public ReportService getReportService(){return new ReportService(getReportRepo());}
    @Bean
    public IStockService getStockService(){return new StockService(getStockRepo());}
    @Bean
    public IPriceService getPriceService(){return new PriceService(getPriceRepo());}
    @Bean
    public ICategoryService getCategoryService(){return new CategoryService(getCategoryRepo());}
    @Bean
    public ITransactionRepository getTransactionRepo(){return new TransactionRepository(dataSource());
    }
    @Bean
    public IProductRepository getProductRepo(){return new ProductRepository(dataSource());}
    @Bean
    public ReportRepository getReportRepo(){return new ReportRepository(dataSource());}
    @Bean
    public IStockRepository getStockRepo(){return new StockRepository(dataSource());}
    @Bean
    public ICategoryRepository getCategoryRepo(){return new CategoryRepository(dataSource());}
    @Bean
    public IPriceRepository getPriceRepo(){return new PriceRepository(dataSource());}

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

