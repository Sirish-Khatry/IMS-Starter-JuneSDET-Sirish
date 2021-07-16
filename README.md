Coverage: 74%
# Project Title

IMS(Inventory Management System) IMS is used to keep track of customers, items and orders. IMS allows add/delete/update/view of customers, orders and items.

## Getting Started

To get the IMS working clone the repository and open a command line on the main folder. Then write java -jar "ims-0.0.1-jar-with-dependencies.jar" to run the jar file.

### Prerequisites

No software installtion required.


### How to use the IMS system

-When the jar file is run, you will be greeted into the system.
-There will be 4 entities provided to chose from, just type the entities title to use that entity.
-After chosing an entity, there will be different options available to chose from regarding what action you want to perfrom within that entity.E.g. CREATE, READ, DELETE
- After chosing a action, you will be asked to enter the required input for that entity and action to function, for example CUSTOMER->CREATE->Enter First_Name->Enter Lastname.
- If you need to chose a different entity enter RETURN to return to entity selection.
- To stop the application enter STOP.


## Running the tests

The tests can be run by opening the source file in Eclipse IDE and selecting run as JUNIT test.

### Unit Tests 

Unit test for Controllers test the methods in the controller class and their return values.
Unit test for Domains test the attributes of the domain and how they are set and accessed.
Unit test for DAO test the queries and their return, making sure each query is ran sucessfully.
Unit test for exception assure that if any method or query fails they are caught and the user is made aware of that exception.
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use GIT for versioning.

## Testing

Unit testing: JUNIT

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
