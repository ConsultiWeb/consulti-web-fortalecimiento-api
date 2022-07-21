package com.consulti.apiconsulti.datasource;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Clase utilizada para configurar los datasources Oracle
 *
 * @author Leonardo Espinoza <mailto:eespinoza@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Configuration
public class DbConfig {
  @Value("${db.username}")
  private String username;

  @Value("${db.password}")
  private String password;

  @Value("${db.host}")
  private String host;

  @Value("${db.name}")
  private String name;

  @Value("${spring.datasource.hikari.idle-timeout}")
  private int idleTimeout;

  @Value("${spring.datasource.hikari.connection-timeout}")
  private int connectionTimeout;

  @Value("${spring.datasource.hikari.maximum-pool-size}")
  private int maxPoolSize;

  @Value("${spring.datasource.hikari.minimum-idle}")
  private int minPoolSize;

  @Value("${spring.datasource.hikari.max-lifetime}")
  private int maxLifetime;

  private final Map<String, Object> hibernateProperties() {
    Map<String, Object> hibernateProperties = new LinkedHashMap<String, Object>();
    hibernateProperties.put("hibernate.connection.release_mode", "auto");
    return hibernateProperties;
  }

  // INICIO CONFIGURACIÓN INFRAESTRUCTURA
  @Primary
  @Bean(name = "dsApiConsulti")
  public HikariDataSource dsApiConsulti(@Qualifier("dsApiConsultiProperties") HikariConfig dataSourceConfig) {
    return new HikariDataSource(dataSourceConfig);
  }

  @Primary
  @Bean(name = "dsApiConsultiProperties")
  public HikariConfig dsApiConsultiConfig() throws Exception {
    HikariConfig dataSourceConfig = new HikariConfig();
    dataSourceConfig.setPoolName("api-consulti");
    dataSourceConfig.setUsername(username);
    dataSourceConfig.setPassword(password);
    dataSourceConfig.setJdbcUrl("jdbc:postgresql://" + host + "/" + name);
    dataSourceConfig.setConnectionTimeout(connectionTimeout);
    dataSourceConfig.setIdleTimeout(idleTimeout);
    dataSourceConfig.setMaximumPoolSize(maxPoolSize);
    dataSourceConfig.setMinimumIdle(minPoolSize);
    dataSourceConfig.setMaxLifetime(maxLifetime);
    dataSourceConfig.setValidationTimeout(10000);
    // dataSourceConfig.setConnectionTestQuery("SELECT 1 FROM DUAL");
    // dataSourceConfig.setConnectionInitSql("SELECT 1 FROM DUAL");
    dataSourceConfig.addDataSourceProperty("oracle.jdbc.timezoneAsRegion", "false");
    return dataSourceConfig;
  }

  @Primary
  @Bean(name = "jdbcConsulti")
  @Autowired
  public JdbcTemplate jdbcConsultiTemplate(@Qualifier("dsApiConsulti") DataSource dsApiConsulti) {
    return new JdbcTemplate(dsApiConsulti);
  }

  @Primary
  @Bean(name = "ConsultiEMFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryConsulti(EntityManagerFactoryBuilder builder,
      @Qualifier("dsApiConsulti") DataSource dsApiConsulti) {
    return builder.dataSource(dsApiConsulti).properties(hibernateProperties())
        .packages("com.consulti.apiconsulti.entity").persistenceUnit("dbConsulti").build();
  }

  @Primary
  @Bean(name = "ConsultiTM")
  public PlatformTransactionManager transactionManagerConsulti(
      @Qualifier("ConsultiEMFactory") EntityManagerFactory ConsultiEMFactory) {
    return new JpaTransactionManager(ConsultiEMFactory);
  }
}