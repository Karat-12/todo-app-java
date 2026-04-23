# 📝 Task Management System (Java)

A full-stack **Task Management Web Application** built using:

* Java Servlets
* JDBC (MySQL)
* Apache Tomcat
* Swing (Admin Panel)

---

## 🚀 Features

### 👤 User Features

* User Login (Session-based authentication)
* Add Tasks with deadline
* View Tasks
* Mark task as Completed
* Delete Task
* Logout

### 🛠 Admin Features (Swing UI)

* View all users' tasks
* Displays:

  * Username
  * Task Title
  * Deadline

---

## 🏗 Tech Stack

| Layer        | Technology      |
| ------------ | --------------- |
| Frontend     | HTML            |
| Backend      | Java Servlets   |
| Database     | MySQL           |
| Connectivity | JDBC            |
| Server       | Apache Tomcat 9 |
| Desktop UI   | Java Swing      |

---

## 📂 Project Structure

```
todo_app/
│
├── src/
│   ├── servlet/
│   ├── db/
│   └── swing/
│
├── web/
│   └── login.html
│
├── WEB-INF/
│   ├── web.xml
│   └── classes/
│
├── lib/
│   └── mysql-connector-j.jar
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone Repository

```
git clone https://github.com/YOUR_USERNAME/todo-app-java.git
```

---

### 2️⃣ Database Setup (MySQL)

```sql
CREATE DATABASE todo_app;

USE todo_app;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    title VARCHAR(100),
    deadline DATE,
    status VARCHAR(20)
);
```

---

### 3️⃣ Configure DB Connection

Update:

```
src/db/DBConnection.java
```

```java
String user = "root";
String pass = "your_password";
```

---

### 4️⃣ Run on Tomcat

* Copy project to:

```
Tomcat/webapps/todo_app
```

* Start Tomcat

Open:

```
http://localhost:7070/todo_app/login.html
```

---

### 5️⃣ Run Swing Admin Panel

```bash
javac -cp ".;lib/*" -d bin src/db/DBConnection.java src/swing/AdminPanel.java
java -cp ".;bin;lib/*" swing.AdminPanel
```

---

## 📸 Screenshots

* Login Page
* Dashboard
* Task List
* Admin Panel (Swing)

---

## 🎯 Learning Outcome

This project demonstrates:

* Full-stack Java development
* Session handling using Servlets
* JDBC integration with MySQL
* MVC-like structure
* Desktop + Web hybrid system

---

## 💡 Future Improvements

* Add CSS UI styling
* Add user registration
* Add REST API version (Spring Boot)
* Deploy on cloud (AWS / Render)

---

## 👨‍💻 Author

Karthik Agrawal
CSE Student | Java Backend Developer

---
