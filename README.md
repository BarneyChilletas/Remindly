# Remindly

**Remindly** is a simple and modern Java desktop application that helps you keep track of your homework, assignments, and personal tasks. Built with **JavaFX** and **PostgreSQL**, Remindly lets you add, view, and complete tasks

---

## 🚀 Features

- Add, view, and delete tasks
- Set due dates and reminders
- Mark tasks as done
- Data persistence with PostgreSQL
- Clean and modern JavaFX UI

---

## 🛠 Tech Stack

| Component        | Technology          |
|------------------|---------------------|
| Language         | Java 17+            |
| GUI              | JavaFX              |
| Database         | PostgreSQL          |
| Build Tool       | Maven               |
| IDE (suggested)  | IntelliJ IDEA       |

---

## 📦 Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/BarneyChilletas/Remindly.git
   cd Remindly
   ```

2. **Configure PostgreSQL:**
   - Make sure you have a running PostgreSQL server.
   - Create a database for Remindly.
   - Update your database credentials in the project's configuration (typically in `src/main/resources` or via environment variables).

3. **Build the project:**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application:**
   ```bash
   ./mvnw javafx:run
   ```

---

## 💻 Usage

- Launch the app and start adding your tasks.
- Set due dates to receive reminders.
- Mark tasks as done to keep your list organized.

---

## 📂 Project Structure

```
src/
└── main/
    ├── java/com/taskbuddy/
    │   ├── MainApp.java
    │   ├── controller/
    │   ├── model/
    │   ├── dao/
    │   └── util/
    └── resources/
        ├── fxml/
        └── css/
```

---

## 🤝 Contributing

Contributions are welcome! Please open issues or submit pull requests for improvements or bug fixes.

---

## 📄 License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for more information.

---

## 🙌 Acknowledgements

- JavaFX for UI
- PostgreSQL for database
- All contributors and users
