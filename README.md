# 🧩 Sudoku — Interactive Java Console Game

A fully-featured **Sudoku game in Java** with puzzle generation, solver, points system, color-coded board, hints, and interactive gameplay — all running in your terminal.  
Designed to be **modular** so anyone can extend it like a real framework.

---

## ✨ Features
- 🎲 **Random Puzzle Generation** with multiple difficulty levels
- 🧠 **Backtracking Solver** for guaranteed solvable puzzles
- 🎨 **Color-coded Board**:
  - **Red** → Original fixed numbers
  - **Gold** → Hints
  - **Green** → Correct user input
  - **Gray** → Empty cells
- 💡 **Hints System** — Get a clue when stuck (points deducted)
- ⏱ **Real-time Checking** — Instantly know if your move is correct
- 🔄 **Restart / New Puzzle** anytime
- 🏆 **Points System** to make it competitive
- 🧩 **Framework-style design** — All logic in `Sudoku.java`, just call methods from `main`


---

## 🚀 Getting Started

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Sujal-Gupta-SG/sudoku.git
```
### 2️⃣ Compile & Run

## ⚙️ How It Works

### Puzzle Generation
Creates a solved Sudoku board, then removes cells based on the chosen difficulty.

### Solver
Uses backtracking to find the solution (guarantees valid solved board).

### Color Printing (ANSI escape codes)
- 🔴 Red → Original puzzle numbers (fixed, uneditable)
- 🟡 Gold → Hints revealed by the system
- 🟢 Green → Correct player inputs
- ⚪ White → Empty cells

### Points System
Start with a baseline score (configurable). Points are added for correct moves and deducted for mistakes or hints.

### Threading
Optional: run solver/checker in a background thread to provide instant verification without blocking the main UI loop.

## 📦 Installation (via JitPack)

You can easily use the **Sudoku** framework in your Java/Android project through [JitPack](https://jitpack.io).

---

### Step 1 — Add the JitPack repository

Add the following in your **root** `settings.gradle` (at the end of `repositories`):

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2 — Add the Dependency

Replace `Tag` with the latest version (see badge below):

```gradle
dependencies {
    implementation 'com.github.Sujal-Gupta-SG:Sudoku:Tag'
}
```

#### 📌 Maven Users

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Sujal-Gupta-SG</groupId>
        <artifactId>Sudoku</artifactId>
        <version>Tag</version>
    </dependency>
</dependencies>
```

#### Latest Version

✅ That’s it!  
The first time you build your project, JitPack will automatically fetch, compile, and provide the Sudoku library as a `.jar`.

## 📌 To-Do / Future Ideas
- GUI Version (JavaFX / Swing)
- Save & Load game
- Multiplayer / Online mode
- Mobile app port (Android)

## 📜 License
This project is licensed under the MIT License — you’re free to use, modify, and distribute. Include an actual LICENSE file in the repository (suggested: MIT).

## 🤝 Contributing
Pull requests are welcome! Please follow these suggestions:

- Open an issue for any bug you find or feature you propose.
- Keep changes focused (one feature/fix per PR).
- Write clear commit messages and add comments where needed.
- Add tests or manual reproduction steps when fixing bugs.

Areas you can help with:

- Fix bugs
- Add new features
- Improve puzzle generation (hardness, uniqueness)
- Add more difficulty modes or scoring variants

## license and badges
![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)
![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)
![JitPack](https://img.shields.io/jitpack/v/github/Sujal-Gupta-SG/Sudoku)

## 💬 Author
Sujal Gupta   
🌐 GitHub: Sujal-Gupta-SG
