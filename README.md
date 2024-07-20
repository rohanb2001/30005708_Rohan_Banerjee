# Gym Management System 

This project is built with core Java, JDBC, MySql.

### Project Summary

This Gym Management System is a menu-based console application developed using Core Java, MySQL, and JDBC. The application allows users to manage gym members, trainers, and class schedules efficiently. It provides functionalities to register new members, add trainers, create class schedules, and perform various other management tasks

####Features:
Member Management:
- Register new members
- View member details
- Update member information
- Delete members

Trainer Management:
- Add new trainers
- View trainer details
- Update trainer information
- Delete trainers

Class Schedule Management:
- Create new class schedules
- View class schedules
- Update class information
- Cancel classes


## Prerequisites
- Java Development Kit (JDK) installed
- MySQL database server installed
- Eclipse IDE (or any other Java IDE)

## Project Setup in Eclipse
### Download MySQL Connector/J:

- Go to the official MySQL Connector/J download page.
- Download the platform-independent ZIP archive.
- Extract the ZIP file to a preferred location.

### Create a New Java Project:
- Go to File > New > Java Project.
- Enter a project name and click Finish.

### Add MySQL Connector/J to the Project:
- Right-click on your project in the Project Explorer and select Properties.
- Select Java Build Path from the left pane.
- Go to the Libraries tab.
- Click on Add External JARs....
- Navigate to the location where you extracted the MySQL Connector/J, select the mysql-connector-java-<version>.jar file, and click Open.
- Click Apply and Close.

### Running the project
#### Database Setup:

- Create a Java class DatabaseSetup.java to handle the database setup and table creation.
- Run the DatabaseSetup class to create the necessary tables in the database.
- Main Application:

- Create the main application class GymManagementSystem.java.
- Implement the necessary DAO (Data Access Object) classes for Member, Trainer, and ClassSchedule.
- Implement the menu-based console interface.

`Compile and run the GymManagementSystem class.`


### Database Schemas
`CREATE DATABASE gym_management;`

`CREATE TABLE IF NOT EXISTS Member (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact_number VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    membership_type VARCHAR(50)
);`

`CREATE TABLE IF NOT EXISTS Trainer (
    trainer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact_number VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    speciality VARCHAR(100)
);`

`CREATE TABLE IF NOT EXISTS ClassSchedule (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    trainer_id INT,
    day_of_week VARCHAR(10),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY (trainer_id) REFERENCES Trainer(trainer_id)
);`
