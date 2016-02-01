/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author yreham.com dev.yreham
 *
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.yreham..web.docbase.repository"},
    entityManagerFactoryRef = "docEntityManagerFactory",
    transactionManagerRef = "docTransactionManager")
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "docAuditorProvider")
@PropertySource("classpath:docbase.properties")
public class DocbasePersistenceConfig {

  public static final String PERSISTENCE_UNIT = "kengine";

  private static final String HIBERNATE_C3P0_MAX_CONNECTION_AGE = "docbase.c3p0.maxConnectionAge";
  private static final String HIBERNATE_C3P0_PREFERRED_TEST_QUERY =
      "docbase.c3p0.preferredTestQuery";
  private static final String HIBERNATE_C3P0_TEST_CONNECTION_ON_CHECKOUT =
      "docbase.c3p0.testConnectionOnCheckout";
  private static final String HIBERNATE_C3P0_MAX_STATEMENTS = "docbase.c3p0.max_statements";
  private static final String HIBERNATE_C3P0_TIMEOUT = "docbase.c3p0.timeout";
  private static final String HIBERNATE_C3P0_MAX_SIZE = "docbase.c3p0.max_size";
  private static final String HIBERNATE_C3P0_MIN_SIZE = "docbase.c3p0.min_size";
  private static final String HIBERNATE_C3P0_IDLE_TEST_PERIOD = "docbase.c3p0.idle_test_period";
  private static final String PACKAGE_TO_SCAN = "com.yreham..web.docbase.model";
  protected static final String PROPERTY_NAME_DATABASE_DRIVER = "docbase.connection.driver_class";
  protected static final String PROPERTY_NAME_DATABASE_PASSWORD = "docbase.connection.password";
  protected static final String PROPERTY_NAME_DATABASE_URL = "docbase.connection.url";
  protected static final String PROPERTY_NAME_DATABASE_USERNAME = "docbase.connection.username";
  private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "docbase.dialect";
  private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "docbase.format_sql";
  private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "docbase.hbm2ddl.auto";
  private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "docbase.show_sql";
  private static final String PROPERTY_DEFAULT_SCHEMA = "docbase.default_schema";

  @Resource
  Environment env;

  @Bean(name = "docDataSource")
  public DataSource docDataSource() throws IllegalStateException, PropertyVetoException {
    // BasicDataSource ds = new org.apache.commons.dbcp.BasicDataSource();
    ComboPooledDataSource ds = new ComboPooledDataSource();

    ds.setDriverClass(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
    ds.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
    ds.setUser(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
    ds.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
    return ds;
  }


  @Bean(name = "docEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean docEntityManagerFactory()
      throws IllegalStateException, PropertyVetoException {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();

    entityManagerFactoryBean.setDataSource(docDataSource());
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan(PACKAGE_TO_SCAN);
    entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT);
    Properties jpaProperties = new Properties();
    /* driver */
    jpaProperties.put(replacePrefix(PROPERTY_NAME_DATABASE_DRIVER),
        env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));

    /* use default configuration */
    jpaProperties.put(replacePrefix(PROPERTY_NAME_DATABASE_USERNAME),
        env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
    jpaProperties.put(replacePrefix(PROPERTY_NAME_DATABASE_PASSWORD),
        env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
    jpaProperties.put(replacePrefix(PROPERTY_NAME_DATABASE_URL),
        env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
    jpaProperties.put(replacePrefix(PROPERTY_DEFAULT_SCHEMA),
        env.getRequiredProperty(PROPERTY_DEFAULT_SCHEMA));

    jpaProperties.put(replacePrefix(PROPERTY_NAME_HIBERNATE_DIALECT),
        env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
    jpaProperties.put(replacePrefix(PROPERTY_NAME_HIBERNATE_FORMAT_SQL),
        env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
    jpaProperties.put(replacePrefix(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO),
        env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
    jpaProperties.put(replacePrefix(PROPERTY_NAME_HIBERNATE_SHOW_SQL),
        env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

    /* c3p0 */
    jpaProperties.put(replacePrefix(HIBERNATE_C3P0_PREFERRED_TEST_QUERY),
        env.getRequiredProperty(HIBERNATE_C3P0_PREFERRED_TEST_QUERY));
    jpaProperties.put(replacePrefix(HIBERNATE_C3P0_TEST_CONNECTION_ON_CHECKOUT),
        env.getRequiredProperty(HIBERNATE_C3P0_TEST_CONNECTION_ON_CHECKOUT));
    jpaProperties.put(replacePrefix(HIBERNATE_C3P0_IDLE_TEST_PERIOD),
        env.getRequiredProperty(HIBERNATE_C3P0_IDLE_TEST_PERIOD));
    jpaProperties.put(HIBERNATE_C3P0_MIN_SIZE, env.getRequiredProperty(HIBERNATE_C3P0_MIN_SIZE));
    jpaProperties.put(replacePrefix(HIBERNATE_C3P0_MAX_SIZE),
        env.getRequiredProperty(HIBERNATE_C3P0_MAX_SIZE));
    jpaProperties.put(replacePrefix(HIBERNATE_C3P0_TIMEOUT),
        env.getRequiredProperty(HIBERNATE_C3P0_TIMEOUT));
    jpaProperties.put(replacePrefix(HIBERNATE_C3P0_MAX_STATEMENTS),
        env.getRequiredProperty(HIBERNATE_C3P0_MAX_STATEMENTS));
    jpaProperties.put(replacePrefix(HIBERNATE_C3P0_MAX_CONNECTION_AGE),
        env.getRequiredProperty(HIBERNATE_C3P0_MAX_CONNECTION_AGE));
    entityManagerFactoryBean.setJpaProperties(jpaProperties);
    entityManagerFactoryBean.afterPropertiesSet();

    return entityManagerFactoryBean;
  }

  @Bean(name = "docTransactionManager")
  public PlatformTransactionManager docTransactionManager()
      throws IllegalStateException, PropertyVetoException {
    JpaTransactionManager transactionManager = new JpaTransactionManager();

    transactionManager.setEntityManagerFactory(docEntityManagerFactory().getObject());
    transactionManager.setJpaDialect(new HibernateJpaDialect());
    return transactionManager;
  }

  @Bean(name = "docHibernateExceptionTranslator")
  public HibernateExceptionTranslator docHibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

  @Bean(name = "docExceptionTranslation")
  public PersistenceExceptionTranslationPostProcessor docExceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean(name = "docAuditorProvider")
  public AuditorAware<String> docAuditorProvider() {
    return new DocumentAuditorAware();
  }

  /**
   * @param envar
   * @return
   */
  private String replacePrefix(String envar) {
    return envar.replace("docbase.", "hibernate.");
  }
}
