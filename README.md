# Farm Management System

## Overview

The Farm Management System is designed to manage the agricultural resources of a farm. It includes functionalities for managing grown products, materials like fertilizers, suppliers, customers, customer orders, employees, employee attendance, users, and more. The system is built using Java, JavaFX, and MySQL.

## Features

- **Product Management**: Manage grown products and their details.
- **Material Management**: Manage materials like fertilizers required for growing products.
- **Supplier Management**: Manage supplier information and orders.
- **Customer Management**: Manage customer information and orders.
- **Order Management**: Handle customer orders and supplier orders.
- **Employee Management**: Manage employee details and attendance.
- **User Management**: Manage user accounts and authentication.

## Technologies Used

- **Java**: Core programming language.
- **JavaFX**: For building the graphical user interface.
- **MySQL**: Database management system.

## Project Structure

- `controller`: Contains the JavaFX controllers for handling UI interactions.
- `dao`: Data Access Objects for interacting with the database.
  - `custom`: Custom DAO interfaces.
  - `custom.impl`: Implementations of custom DAO interfaces.
- `dto`: Data Transfer Objects for transferring data between layers.
- `entity`: Entity classes representing the database tables.
- `util`: Utility classes for common functionalities like navigation, styling, and regex validation.

## Setup and Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/jlokitha/Alokagreen-Management-System-Using-Layered-Architecture.git
    ```

2. **Open the project in IntelliJ IDEA**:
    - Open IntelliJ IDEA.
    - Select `File > Open` and choose the cloned project directory.

3. **Configure the database**:
    - Create a MySQL database.
    - Import the provided SQL script to set up the database schema and initial data.

4. **Update database configurations**:
    - Update the database connection details in the `DbConnection` class.

5. **Run the project**:
    - Navigate to the main class and run the application.

## Usage

- **Login**: Users can log in using their credentials.
- **Dashboard**: Access different management modules from the dashboard.
- **Product Management**: Add, update, and delete product details.
- **Material Management**: Manage materials required for farming.
- **Supplier Management**: Add, update, and delete supplier information.
- **Customer Management**: Manage customer details.
- **Order Management**: Handle customer and supplier orders.
- **Employee Management**: Manage employee details and track attendance.
- **User Management**: Manage user accounts and permissions.

## License

This project is licensed under the MIT License. See the [MIT License](LICENSE) file for more details.

##
<div align="center">
<a href="https://github.com/jlokitha" target="_blank"><img src = "https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white"></a>
<a href="https://git-scm.com/" target="_blank"><img src = "https://img.shields.io/badge/Git-000000?style=for-the-badge&logo=git&logoColor=white"></a>
<a href="https://www.jetbrains.com/idea/download/?section=windows" target="_blank"><img src = "https://img.shields.io/badge/java-000000?style=for-the-badge&logo=openjdk&logoColor=white"></a>
<a href="https://www.mysql.com/downloads/" target="_blank"><img src = "https://img.shields.io/badge/MySQL-000000?style=for-the-badge&logo=mysql&logoColor=white"></a>
<a href="https://www.jetbrains.com/idea/download/?section=windows" target="_blank"><img src = "https://img.shields.io/badge/intellij-000000?style=for-the-badge&logo=intellijidea&logoColor=white"></a>
</div> <br>

<p align="center">
  &copy; 2024 Janindu Lokitha
</p>
