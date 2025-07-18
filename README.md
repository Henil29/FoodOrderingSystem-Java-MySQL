# Food Ordering System

A simple Java-based food ordering system that allows users to register, browse restaurants, manage menus, and place orders. The system uses a MySQL database for persistent storage.

## Features

- **User Management:** Register, update, and delete users; view user details.
- **Restaurant Management:** Add, update, and delete restaurants; view restaurant details.
- **Menu Management:** Add, update, and delete dishes for each restaurant.
- **Order Management:** Place orders, view menus, and process payments (UPI, cash, card).
- **Database Integration:** Uses MySQL for storing users, restaurants, and menu data.

## Folder Structure 

```
demo/
  ├── bin/         # Compiled Java classes
  ├── lib/         # External libraries (e.g., MySQL connector)
  ├── src/         # Java source code
  │   ├── test/    # Core classes (user, restaurant, order, dish)
  │   └── zomato.java  # Main entry point
  └── README.md    # Project documentation
```

## Prerequisites

- Java JDK 8 or higher
- MySQL Server
- MySQL Connector/J (JAR file, included in `lib/` or as `mysql-connector-j-9.0.0.jar`)
- (Optional) Visual Studio Code with Java extensions

## Database Setup

1. **Create the Database:**
   - Use the provided SQL script `zomato (1).sql` in the project root to set up the database schema and initial data.
   - Example (from terminal or MySQL Workbench):
     ```sql
     CREATE DATABASE zomato;
     USE zomato;
     SOURCE path/to/zomato (1).sql;
     ```

2. **Database Connection:**
   - The application connects to MySQL at `jdbc:mysql://localhost:3306/zomato` with user `root` and an empty password by default.
   - Update credentials in `src/zomato.java` if your setup differs.

## How to Run

1. **Compile the Project:**
   - Ensure the MySQL connector JAR is in the `lib/` directory.
   - From the `demo/` directory, compile with:
     ```sh
     javac -cp "lib/mysql-connector-j-9.0.0.jar;src" -d bin src/zomato.java src/test/*.java
     ```

2. **Run the Application:**
   - From the `demo/` directory, run:
     ```sh
     java -cp "bin;lib/mysql-connector-j-9.0.0.jar" zomato
     ```

   - On Unix/Mac, replace `;` with `:` in the classpath.

## Usage

- On running, you’ll see a menu for:
  1. User Management
  2. Restaurant Management
  3. Menu Management
  4. Order Management
  5. Exit

- Follow the prompts to manage users, restaurants, menus, and place orders.

## Notes

- Each restaurant has its own table for dishes.
- User and restaurant passwords are stored as integers (for demo purposes).
- Orders are written to a text file named after the user.
- The system uses stored procedures for some database operations (see SQL file).

## Contributing

Pull requests and suggestions are welcome!

## License

This project is for educational purposes.
