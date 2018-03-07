# AWISC - Authoritative WIS Catalogue
 
## Installation
 
### Configure the managed datasource
config:edit org.ops4j.datasource-openwis
config:property-set user root
config:property-set password jijikos
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

### Configure CXF
config:edit org.apache.cxf.osgi
config:property-set org.apache.cxf.servlet.context /awisc/cxf
config:update
 
### Configure Liquibase to use the AWISC datasource
config:edit com.eurodyn.qlack2.util.liquibase
config:property-set datasource openwisDS
config:update

### Configure Config Admin properties
config:edit openwis.awisc
config:property-set jwt_secret 7fa6c11b-b8d4-1adb-c60d-1f6efbeec457
config:update

#################################################################
# QLACK Fuse - Search configuration
# etc/com.eurodyn.qlack2.fuse.search.cfg
#################################################################
config:edit com.eurodyn.qlack2.fuse.search
# A comma-separated list of ES hosts in the form of protocol1:host1:port1,protocol2:host2:port2,etc. Use https if your elasticsearch installation is configured with searchguard. http otherwise.
config:property-set es.hosts http:localhost:9200

# The following properties are only relevant if your elasticsearch installation is configured with searchguard.
# Enable or disable hostname verification. Only applies when https is used to communicate with elasticsearch. Must be false to disable hostname verification.
config:property-set es.hostname.verification false
config:property-set es.username admin
config:property-set es.password admin
config:update
 
### Install AWISC Karaf features repository
feature:repo-add mvn:openwis.pilot.awisc/awisc-karaf-features/LATEST/xml/features
 
### Install DB connectivity feature and automatic Liquibase update
feature:install awisc-database
 
### Install dependencies
feature:install awisc-deps

### Install the server feature
feature:install awisc-server

### Workaround for entity class cast exception
#refresh hibernate-osgi
