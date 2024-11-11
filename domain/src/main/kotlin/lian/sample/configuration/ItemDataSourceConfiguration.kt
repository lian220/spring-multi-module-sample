package kr.co.mustit.configuration

import com.zaxxer.hikari.HikariDataSource
import kr.co.mustit.jpa.MasterSlaveRoutingDataSource
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = ["kr.co.mustit.repository.item", "kr.co.mustit.repository.dps"],
  entityManagerFactoryRef = "itemEntityManagerFactory",
  transactionManagerRef = "itemTransactionManager")
class ItemDataSourceConfiguration(private val jpaProperties: JpaProperties) {

  @Bean
  @ConfigurationProperties("datasource.item.master")
  fun itemMasterDatasource(): DataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

  @Bean
  @ConfigurationProperties("datasource.item.slave")
  fun itemSlaveDatasource(): DataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

  @Bean
  fun itemRoutingDataSource() = MasterSlaveRoutingDataSource().apply {
    setTargetDataSources(hashMapOf<Any, Any>(
      "master" to itemMasterDatasource(),
      "slave" to itemSlaveDatasource()))
    setDefaultTargetDataSource(itemMasterDatasource())
  }

  @Primary
  @Bean
  fun itemLazyDataSource() = LazyConnectionDataSourceProxy(itemRoutingDataSource())

  @Primary
  @Bean("itemEntityManagerFactory")
  fun itemEntityManagerFactory() = LocalContainerEntityManagerFactoryBean().apply {
    dataSource = itemLazyDataSource()
    setPackagesToScan("kr.co.mustit.repository.item", "kr.co.mustit.repository.dps.jpa")
    jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
      setShowSql(jpaProperties.isShowSql)
      setGenerateDdl(jpaProperties.isGenerateDdl)
      setJpaPropertyMap(jpaProperties.properties)
    }
    persistenceUnitName = "itemEntityManager"
    afterPropertiesSet()
  }

  @Primary
  @Bean("itemTransactionManager")
  fun itemTransactionManager() = JpaTransactionManager(itemEntityManagerFactory().`object`!!)
}

