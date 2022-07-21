package com.consulti.apiconsulti.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Clase utilizada para configurar el datasource del esquema GPS
 *
 * @author Leonardo Espinoza <mailto:eespinoza@telconet.ec>
 * @version 1.0
 * @since 02/03/2020
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "ConsultiEMFactory", transactionManagerRef = "ConsultiTM", basePackages = {
    "com.consulti.apiconsulti.repository" })
@EntityScan(basePackages = { "com.consulti.apiconsulti.entity" })
public class DatasourceConfig {
}
