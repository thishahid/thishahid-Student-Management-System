# Student Management System
[![My Skills](https://skillicons.dev/icons?i=java,mysql&theme=light)]
## Description

This Java-based **Student Management System** allows users to manage student data (Name, Roll No., Marks) using a graphical user interface (GUI). The project uses **Java Swing** for the frontend, **MySQL** for the database, and **Java Database Connectivity (JDBC)** for database interaction. The system allows for the following operations:
- **Add** a new student
- **Update** existing student information
- **Delete** a student record
- **Display** student data in a tabular format

### Features:
- Java Swing-based user interface.
- Operations: Add, Update, Delete student information.
- View student data in a table.
- Uses **DTO (Data Transfer Object)** for transferring data between the application and the database.
- **MySQL** database for storing student information.

## Project Structure

```bash
├── src/
│   ├── StudentForm.java           # Main form for adding, updating, deleting, and displaying data
│   ├── JDBCConnectionManager.java # Manages the database connection
│   ├── StudentDTO.java            # Data Transfer Object (DTO) for student details
│   ├── StudentTable.java          # Displays student data in a table
├── lib/
│   └── mysql-connector-java.jar   # MySQL JDBC driver
├── README.md                      # Project documentation
├── pom.xml                        # For Maven-based builds
└── student.sql                    # SQL script to create the students table
```

## Screenshots

### Main Window
![Screenshot_20240925_015731](https://github.com/user-attachments/assets/da4fe544-9b02-4337-afac-a7be4fd2598d)

### Display Table Window
![Screenshot_20240925_021513](https://github.com/user-attachments/assets/40e2c08c-2d31-4249-9770-ea921a9efca7)

## Prerequisites

- **Java Development Kit (JDK)** 8 or higher.
- **MySQL** database installed and running.
- **MySQL Connector/J** library (included in the `lib` folder).
- **Maven** (optional) for dependency management.

## Setup and Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/your-username/student-management-system.git
cd student-management-system
```

### Step 2: Set up the MySQL Database

1. **Create Database:**
   - Log in to your MySQL and create the `student_db` database.
   ```sql
   CREATE DATABASE student_db;
   ```

2. **Create the Students Table:**
   - Run the `student.sql` script to create the table.
   ```sql
   USE student_db;
   CREATE TABLE students (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(50),
       roll_no INT,
       marks INT
   );
   ```

### Step 3: Add MySQL Connector to Your Project

- If you're not using Maven, ensure that the **MySQL Connector/J** JAR (`mysql-connector-java.jar`) is added to your project's classpath.
- For **Maven** users, add the following dependency to your `pom.xml`:
   ```xml
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.30</version> <!-- Ensure it's the correct version -->
   </dependency>
   ```

### Step 4: Compile and Run the Application

1. **Using IDE:**
   - Open the project in your preferred Java IDE (IntelliJ IDEA, Eclipse, etc.).
   - Ensure that the MySQL JDBC driver is added to the project’s classpath.
   - Compile and run `StudentForm.java`.

2. **Using Command Line:**
   - Navigate to the project root folder:
   ```bash
   javac -cp lib/mysql-connector-java.jar src/*.java
   java -cp .:lib/mysql-connector-java.jar src/StudentForm
   ```

### Step 5: Configure Database Credentials

In the `JDBCConnectionManager.java` file, ensure that the **database URL, username, and password** are correctly set:

```java
String url = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
String username = "root"; // Your MySQL username
String password = "password"; // Your MySQL password
```

Replace `root` and `password` with your MySQL credentials.

### Step 6: Using the Application

1. **Add a Student:**
   - Input student details and click **Submit**.

2. **Update/Delete a Student:**
   - Use the same form to update or delete student records.

3. **Display Students:**
   - Click on the `Display` button to open a new window showing student records in a tabular format.

## Troubleshooting

1. **"No suitable driver found for jdbc:mysql://localhost" error:**
   - Ensure that the MySQL JDBC driver (`mysql-connector-java.jar`) is properly included in the classpath.

2. **"java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver":**
   - Make sure the MySQL JDBC driver is in the `lib` folder or added to your Maven `pom.xml`.

3. **SQL Exception:**
   - Ensure the MySQL database is running, and the credentials in `JDBCConnectionManager` are correct.

## Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to submit a pull request.

## License

This project is licensed under the MIT License.
