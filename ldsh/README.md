## Installation
 
### Configure the managed datasource
config:edit org.ops4j.datasource-ldsh
config:property-set user root
config:property-set password ____PASSWORD____
config:property-set url jdbc:mysql://localhost:3306/LDSH-DB
config:property-set databaseName  LDSH-DB 
config:property-set dataSourceName ldshDataSource 
config:property-set osgi.jdbc.driver.name mysql
config:property-set osgi.jpa.properties.hibernate.dialect org.hibernate.dialect.MySQL5InnoDBDialect
config:property-set pool dbcp2
config:property-set xa true
config:property-set jdbc.pool.testOnBorrow true
config:property-set jdbc.factory.validationQuery 'select 1'
config:property-set jdbc.pool.testWhileIdle true
config:property-set jdbc.factory.validationQueryTimeout 15
config:update
 
### Configure Liquibase to use the Ldsh datasource
config:edit com.eurodyn.qlack2.util.liquibase
config:property-set datasource ldshDataSource
config:update
 
### Install LDSH Karaf features repository
feature:repo-add mvn:openwis.pilot.ldsh/ldsh-karaf-features/LATEST/xml/features
  
 
### Install DB connectivity feature and automatic Liquibase update
feature:install ldsh-database
 
### Install dependencies
feature:install ldsh-deps

### Install camel
feature:repo-add mvn:org.apache.camel.karaf/apache-camel/2.18.2/xml/features

feature:install ldsh-camel
 
 
### Install the server feature
feature:install ldsh-server