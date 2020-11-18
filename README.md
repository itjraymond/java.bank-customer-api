## Bank Customer API (microservice)

### About
The main purpose is _code practice_.  This bounded context is the management of the entity
_Customer_ which should express a bank customer (of the current running bank).  Also, on purpose, 
we keep the Customer model and business very simple.  Each Customer entity will simply represent 
the first and last name of the bank customer with their date of birth.  Each Customer entry will
have their own identifier (UUID) that other bounded context can save in their own data store.

### Initial development

The microservice will first be done in Java + Spring Boot (then will try Kotlin).  The 
data store used will be MongoDB.

##### MongoDB
We will leverage Docker to run an instance of MongoDB during development.  This mean that MongoDB 
will be exposed through docker container while our app will be running on a local JVM during 
development.  In other word, we want to be able to quickly test code by running the µservice
within IntelliJ IDEA.  

Getting MongoDB running in Docker requires the following:

    $ docker run -d -p 27017:27017 -v /Users/jraymond/data/mongodb:/data/db --name bank-customer-store mongo
    
To quickly verify if mongo is up, you can do the following:

    $ docker exec -it bank-customer-store bash
    root@187fb766287d:/#  mongo 
    > show dbs
    admin      0.000GB
    config     0.000GB
    customers  0.000GB
    local      0.000GB
    test       0.000GB
    > use customers
    switched to db customers
    > db.customer.find()
    { "_id" : ObjectId("5fb55e274a16407e9072fab4"), "firstname" : "Bob", "lastname" : "Cruz", "dob" : ISODate("1970-04-17T07:00:00Z"), "_class" : "ca.jent.bank.customer.data.Customer" }
    { "_id" : ObjectId("5fb55e274a16407e9072fab6"), "firstname" : "Santa", "lastname" : "Claus", "dob" : ISODate("1972-12-25T07:00:00Z"), "_class" : "ca.jent.bank.customer.data.Customer" }
    { "_id" : ObjectId("5fb55e274a16407e9072fab5"), "firstname" : "Tim", "lastname" : "Bazz", "dob" : ISODate("1971-12-30T07:00:00Z"), "_class" : "ca.jent.bank.customer.data.Customer" }

Note that the sample data output above was created after we ran the µservice within our IntelliJ IDEA.