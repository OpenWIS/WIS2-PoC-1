# AWISC - Authoritative WIS Catalogue
 
## Installation
 
### Configure the managed datasource
config:edit org.ops4j.datasource-awisc
config:property-set user root
config:property-set password jijikos
config:property-set url jdbc:mysql://localhost:3306/awisc?autoReconnect=true&createDatabaseIfNotExist=true
config:property-set dataSourceName awiscDS
config:property-set osgi.jdbc.driver.name mysql
config:property-set osgi.jpa.properties.hibernate.dialect org.hibernate.dialect.MySQL5InnoDBDialect
config:property-set pool dbcp2
config:property-set xa true
config:property-set jdbc.pool.testOnBorrow true
config:property-set jdbc.factory.validationQuery 'select 1'
config:property-set jdbc.pool.testWhileIdle true
config:property-set jdbc.factory.validationQueryTimeout 15
config:update
 
### Configure Liquibase to use the AWISC datasource
config:edit com.eurodyn.qlack2.util.liquibase
config:property-set datasource awiscDS
config:update
 
### Install AWISC Karaf features repository
feature:repo-add mvn:openwis.pilot.awisc/awisc-karaf-features/LATEST/xml/features
 
 
 
### Install DB connectivity feature and automatic Liquibase update
feature:install awisc-database
 
### Install dependencies
feature:install awisc-deps
 
 
### Install the server feature
feature:install awisc-server