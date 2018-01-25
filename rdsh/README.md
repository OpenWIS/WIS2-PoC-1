## Installation
 
### Configure the managed datasource
```
config:edit org.ops4j.datasource-openwis
config:property-set user root
config:property-set password root
config:property-set url jdbc:mysql://localhost:3306/openwis?autoReconnect=true&createDatabaseIfNotExist=true
config:property-set databaseName openwis
config:property-set dataSourceName openwisDS
config:property-set osgi.jdbc.driver.name mysql
config:property-set osgi.jpa.properties.hibernate.dialect org.hibernate.dialect.MySQL5InnoDBDialect
config:property-set pool dbcp2
config:property-set xa true
config:property-set jdbc.pool.testOnBorrow true
config:property-set jdbc.factory.validationQuery 'select 1'
config:property-set jdbc.pool.testWhileIdle true
config:property-set jdbc.factory.validationQueryTimeout 15
config:update
```

### Configure Liquibase to use the RDSH datasource
```
config:edit com.eurodyn.qlack2.util.liquibase
config:property-set datasource openwisDS
config:update
```

### Configure Config Admin properties
```
config:edit openwis.rdsh
config:property-set mqtt_host tcp://localhost:1883
config:property-set mqtt_username admin
config:property-set mqtt_password admin
config:property-set jwt_secret 7fa6c11b-b8d4-1adb-c60d-1f6efbeec457
config:update
```

### Install RDSH Karaf features repository
    feature:repo-add mvn:openwis.pilot.rdsh/rdsh-karaf-features/LATEST/xml/features
 
### Install DB connectivity feature and automatic Liquibase update
    feature:install rdsh-database
 
### Install dependencies
    feature:install rdsh-deps

### Install the server feature
    feature:install rdsh-server
