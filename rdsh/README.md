## Installation
 
### Configure the managed datasource
config:edit org.ops4j.datasource-ldsh
config:property-set user root
config:property-set password root
config:property-set url jdbc:mysql://localhost:3306/rdsh?autoReconnect=true&createDatabaseIfNotExist=true
config:property-set databaseName rdsh
config:property-set dataSourceName rdshDS
config:property-set osgi.jdbc.driver.name mysql
config:property-set osgi.jpa.properties.hibernate.dialect org.hibernate.dialect.MySQL5InnoDBDialect
config:property-set pool dbcp2
config:property-set xa true
config:property-set jdbc.pool.testOnBorrow true
config:property-set jdbc.factory.validationQuery 'select 1'
config:property-set jdbc.pool.testWhileIdle true
config:property-set jdbc.factory.validationQueryTimeout 15
config:update
 
### Configure Liquibase to use the RDSH datasource
config:edit com.eurodyn.qlack2.util.liquibase
config:property-set datasource rdshDS
config:update
 
### Install RDSH Karaf features repository
feature:repo-add mvn:openwis.pilot.rdsh/rdsh-karaf-features/LATEST/xml/features
 
### Install DB connectivity feature and automatic Liquibase update
feature:install rdsh-database
 
### Install dependencies
feature:install rdsh-deps

### Install the server feature
feature:install rdsh-server
