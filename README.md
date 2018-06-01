# Integrated Paper File Management System

[Documentation](https://docs.google.com/document/d/1Z7Cqh9w_Z1mIrdK_GDnlKrDEtlHih-Q98lKckrJNLHY/edit?usp=sharing)

Ensure you have:
+ jdk 1.8
+ maven 3.5.0

Install dependencies: mvn clean install

Run: mvn spring-boot:run

Build .war: mvn clean package

### Configuration
Environmental Variables:
+ ENV - set as 'dev' or 'prod' to toggle certain functionality. At the moment, the only change is switching the IdentityFilter.java functionality. If not present, defaults to dev functionality
+ CORS - set as the address of the frontend, if hosted at a separate location, to allow cross origin resource sharing.

See application.properties in the resources folder for the rest of the configuration options.

You can also hardcode allowed origins for CORS in the IpfmsConfiguration class
