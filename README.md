#  Android Static File Management

**Course:** Software Development for Portable Devices (CS 10)  
**Institution:** BITS Pilani - Work Integrated Learning Programs (WILP)  
**Course Faculty:** JIBIN N

---

## 📖 Overview
This repository serves as the official demonstration for **Contact Session 10**, focusing on **Static Files as Resources**. It provides a practical look at how Android applications bundle non-code files and the architectural decisions behind choosing between Internal, External, or Shared storage.

## 🎓 Instructional Objectives
The primary goal of this demo is to illustrate:
* **Bundled Content:** How files like `config.json` and `user_guide.txt` are packaged inside the APK and available immediately after installation.
* **Resource Handling:** Utilizing `resources.openRawResource()` for ID-based access and `assets.open()` for flexible path-based access.
* **Storage Lifecycle:** Differentiating between **read-only** static files and the **read-write** capabilities of runtime files in the internal file system.

## 🏛️ Project Architecture
The project follows a modern, privacy-first storage model where apps do not freely browse the device but use structured methods.

### 1. Static Files (Packaged Resources)
* **`res/raw`**: Used for fixed resources like `config.json` (accessed via `R.raw.config`).
* **`assets`**: Used for organized offline content like `user_guide.txt`, supporting folder structures and path strings.

### 2. File Handling Logic
* **InputStream Management:** Demonstrates the use of `.use {}` blocks in Kotlin to ensure streams are automatically closed, preventing memory leaks.
* **Data Transformation:** Shows how to convert raw `InputStream` data into readable text using `bufferedReader`.

## 🛠️ Usage for Students
Students should clone this repository to observe:
1.  **Static vs. Runtime Files:** Note that bundled files are read-only. To make a bundled file editable, it must first be copied to **Internal Storage**.
2.  **Storage Selection:** Review the "Simple Rule"—use `res/raw` for fixed files and `assets` for flexible path-based resources.
3.  **Code Implementation:** Study the `MainActivity.kt` to understand how the UI interacts with the underlying Android File System APIs.

---

**Topic:** Working with the File System  
**Next Session:** CS 11 - Android Databases (SQLite)
