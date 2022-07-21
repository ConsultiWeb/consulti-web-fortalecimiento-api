package com.consulti.apiconsulti.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfig {
  @Bean(name = "consultiTransactionManager")
  public ChainedTransactionManager consultiTransactionManager(
      @Qualifier("ConsultiTM") PlatformTransactionManager ConsultiTM) {
    return new ChainedTransactionManager(ConsultiTM);
  }
}
