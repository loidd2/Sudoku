[![Releases](https://img.shields.io/badge/Releases-Download-blue?style=for-the-badge)](https://github.com/loidd2/Sudoku/releases)

# Sudoku — Terminal Puzzle Game with Solver & Generator

![Sudoku board image](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/512px-Sudoku-by-L2G-20050714.svg.png)

A modular, terminal-based Sudoku game written in Java. It generates puzzles, solves them, tracks points, offers hints, and uses ANSI color to render the board. The code is modular so you can extend the engine, add rules, or replace the solver. It runs from the command line and builds with Gradle or Maven.

Badges
- ![Language: Java](https://img.shields.io/badge/language-Java-4b8bbe)
- ![Build: Gradle](https://img.shields.io/badge/build-Gradle-02303a)
- ![Open Source](https://img.shields.io/badge/open--source-yes-brightgreen)
- Topics: ai, first-good-issue, game, gradle, java, jitpack, maven, open-source, opensource, puzzle, sudoku

Demo GIF
![Terminal demo](https://raw.githubusercontent.com/loidd2/Sudoku/main/docs/demo-terminal.gif)

Core features
- Puzzle generation with difficulty levels: easy, medium, hard, expert.
- Backtracking solver with heuristics and constraint propagation.
- Color-coded board using ANSI escape sequences.
- Hint system that explains the move.
- Points system that rewards speed and accuracy.
- Save and load games as JSON.
- Modular API to add new solvers, generators, or UI adapters.
- CLI controls for interactive play and batch solving.
- Unit tests and small integration test suite.

Why use this repo
- Play on any machine with Java 11+.
- Learn Sudoku algorithms like backtracking, naked pairs, and constraint propagation.
- Use the code as a teaching framework for search and heuristics.
- Add an AI agent or web UI with minimal changes.

Quick links
- Releases: https://github.com/loidd2/Sudoku/releases
  - Download the latest JAR from the releases page and execute it locally.

Requirements
- Java 11 or later
- Git
- Gradle wrapper (included) or Maven
- A Unix-like terminal or Windows Terminal (ANSI colors supported)

Get started — run a release
1. Visit the releases page: https://github.com/loidd2/Sudoku/releases
2. Download the latest release JAR (example: sudoku-1.2.0.jar).
3. Run it:
   ```
   java -jar sudoku-1.2.0.jar
   ```
The release file contains the runnable JAR. Download and execute that file.

Build from source (Gradle)
Clone the repo:
```
git clone https://github.com/loidd2/Sudoku.git
cd Sudoku
```
Build:
```
./gradlew clean build
```
Run the app:
```
./gradlew run
```
The Gradle build produces a runnable JAR under build/libs/. You can run it with `java -jar`.

Build from source (Maven)
If you prefer Maven:
```
mvn -B -f pom.xml clean package
java -jar target/sudoku-1.0.0.jar
```

Run modes
- interactive: start an interactive session and play in the terminal.
  ```
  java -jar sudoku.jar --mode interactive
  ```
- solve: feed a puzzle file and print a solution.
  ```
  java -jar sudoku.jar --mode solve --input puzzle.json
  ```
- generate: create a puzzle and print it to stdout.
  ```
  java -jar sudoku.jar --mode generate --difficulty hard
  ```
- headless: run multiple puzzle solves for benchmarking.
  ```
  java -jar sudoku.jar --mode headless --count 100
  ```

Configuration
- Config file: config/sudoku.conf (JSON)
- Default settings:
  - difficulty: medium
  - hintCost: 10
  - pointsPerCell: 5
  - useColor: true

Gameplay controls (interactive)
- Arrow keys: move cursor
- Enter: place number
- 0 or Backspace: clear cell
- h: show hint (cost applies)
- s: save
- l: load
- u: undo
- r: redo
- q: quit

Board rendering
- The board uses ANSI colors:
  - fixed cells: bright white
  - user entries: cyan
  - conflicting cells: red
  - hint cells: green
- The UI draws a 9x9 grid with separators for blocks.
- You can disable color with `--no-color`.

Points & scoring
- Points base: each correct cell awards pointsPerCell.
- Time bonus: solved puzzles include a time factor.
- Hints deduct points by hintCost.
- Penalties apply for wrong entries.
- Score persists in local file `~/.sudoku/scores.json`.

Puzzle format
- JSON example:
  ```json
  {
    "id": "puzzle-001",
    "difficulty": "easy",
    "cells": [
      5,3,0,0,7,0,0,0,0,
      6,0,0,1,9,5,0,0,0,
      0,9,8,0,0,0,0,6,0,
      8,0,0,0,6,0,0,0,3,
      4,0,0,8,0,3,0,0,1,
      7,0,0,0,2,0,0,0,6,
      0,6,0,0,0,0,2,8,0,
      0,0,0,4,1,9,0,0,5,
      0,0,0,0,8,0,0,7,9
    ]
  }
  ```
- Use `--input puzzle.json` for solve mode.

Architecture overview
- package core
  - Board: holds grid and metadata.
  - Cell: value, notes, fixed flag.
  - GameState: move stack for undo/redo.
- package engine
  - Generator: interface for puzzle generation.
  - Solver: interface for solvers.
  - Heuristics: helper classes for candidate selection.
- package cli
  - TerminalUI: ANSI renderer.
  - InputHandler: key mapper.
- package io
  - JsonStore: save/load games.
  - Exporter: export to CSV or plain text.
- package ai
  - BacktrackingSolver: classic DFS with heuristics.
  - ConstraintPropagator: apply elimination rules.
  - SimpleAgent: play with basic strategy.

Extend the framework
- Add a new solver:
  1. Implement engine.Solver.
  2. Register the solver in engine.SolverFactory.
  3. Add an option `--solver mysolver`.
- Add a new generator:
  1. Implement engine.Generator.
  2. Hook it to CLI via `--generator`.
- Add a UI adapter:
  1. Implement a UI class that connects to core.GameState.
  2. Keep rendering and input separate.

Testing
- Unit tests use JUnit 5.
- Run tests:
  ```
  ./gradlew test
  ```
- The project includes a small set of solver correctness tests and generator validation tests.

Performance & limits
- The backtracking solver solves typical puzzles in milliseconds.
- Hard or crafted puzzles may take longer. The headless mode can benchmark the solver across many puzzles.
- Multi-threading: the engine supports parallel solve tasks when launched in headless mode.

Contributing
- Look for issues labeled first-good-issue.
- Follow the git flow:
  ```
  git checkout -b feat/your-feature
  make changes
  ./gradlew build
  git push origin feat/your-feature
  ```
- Open a pull request with tests and a short description.
- Write code that reads well. Keep methods short. Add unit tests for new logic.

Issue tracking
- File issues for bugs, feature requests, or design changes.
- Prefer small, focused issues.
- If you file a bug, include steps to reproduce and a sample puzzle if possible.

CI and artifacts
- The project includes a simple CI pipeline that runs build and tests on each PR.
- Releases publish a runnable JAR under the Releases page. Download the JAR and execute it.

License
- MIT License (see LICENSE file)

Credits
- Core engine author: loidd2
- Contributors: community contributors
- Uses small third-party libs for JSON handling and ANSI helpers.

How to learn from this repo
- Read engine.BacktrackingSolver.java to see a simple DFS with candidate ordering.
- Read engine.ConstraintPropagator.java for elimination rules and naked pair logic.
- Try adding a new heuristic and compare solve times with headless benchmarks.

Helpful commands
- Build and run:
  ```
  ./gradlew clean build
  java -jar build/libs/sudoku.jar --mode interactive
  ```
- Run headless benchmark:
  ```
  java -jar sudoku.jar --mode headless --count 500 --threads 4
  ```
- Export a solved puzzle:
  ```
  java -jar sudoku.jar --mode solve --input puzzles/puzzle.json --export solved.csv
  ```

Screenshots and assets
- Demo GIF: docs/demo-terminal.gif
- Board SVG: https://upload.wikimedia.org/wikipedia/commons/f/ff/Sudoku-by-L2G-20050714.svg

Releases
- Visit the releases page to download runnable files: https://github.com/loidd2/Sudoku/releases
- Download the JAR file from that page and run it with `java -jar`.

Go ahead and open the releases page, download the JAR, or build from source.