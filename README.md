# 🌱 Plant Manager

### **A Java-based Console Application for Tracking Indoor Plants**

## **📌 About the Project**

Plant Manager is a command-line application designed to help users keep track of their indoor plants, including their watering schedules, room placement, and light requirements. The program allows users to add, update, deactivate, and reactivate plants while keeping all data stored persistently in CSV files.

---

## **🚀 Features**

- **View Plant Database** – Displays all active plants, grouped by room and sorted by next watering date.
- **Add a New Plant** – Users can select a species, assign it to a room, and track watering schedules.
- **Add a New Species** – Users can add custom plant species with defined watering cadence and light requirements.
- **Add a New Room** – Users can define and add new rooms to categorize their plants.
- **Log Watering Event** – Easily update a plant's last watered date and recalculate the next watering date.
- **Deactivate & Reactivate Plants** – Remove plants from active tracking while maintaining their data.
- **Reset Database** – Restore default data while preserving the program’s functionality.

---

## **🛠️ Technologies Used**

- **Java 17** – Core language for application logic.
- **CSV Storage** – Data persistence without the need for a database.
- **Git & GitHub** – Version control and repository management.

---

## **📂 Project Structure**

```
📦 PlantManager
 ┣ 📂 Data              # CSV files storing plants, species, and rooms
 ┣ 📂 PlantManager.Java # Java source files
 ┃ ┣ 📜 MenuManager.java     # Handles user navigation and menu system
 ┃ ┣ 📜 DatabaseManager.java # Manages data operations
 ┃ ┣ 📜 PlantManager.java    # Handles plant-specific functions
 ┃ ┣ 📜 RoomManager.java     # Handles room-related operations
 ┃ ┣ 📜 SpeciesManager.java  # Handles species-related operations
 ┃ ┣ 📜 FileHandler.java     # Manages file read/write operations
 ┃ ┣ 📜 IDGenerator.java     # Generates unique IDs for plants, species, and rooms
 ┗ 📜 README.md         # Project documentation
```

---

## **📖 How to Run the Project**

1. **Clone the repository:**
   ```sh
   git clone https://github.com/iauger/PlantManager.git
   cd PlantManager
   ```
2. **Compile the project:**
   ```sh
   javac -d bin PlantManager/Java/*.java
   ```
3. **Run the application:**
   ```sh
   java -cp bin PlantManager.Java.MenuManager
   ```

---

## **🔧 Future Enhancements**

- Integrate with Google Calendar to create events for watering plants.
- Implement a **graphical user interface (GUI)** for easier navigation.
- Integrate with **external plant databases** for species information.

---

## **👥 Contributors**

- [**@iauger**](https://github.com/iauger) – Developer & Project Maintainer

---

## **📜 License**

This project is licensed under the **MIT License** – feel free to modify and improve!

---

### **🌿 Happy Plant Parenting!**

