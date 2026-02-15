CLI-Based Exam Revision Tracker

A terminal-based productivity tool built in Java using GitHub Copilot CLI.
This project was developed as part of the GitHub Copilot CLI Challenge.

Features
Add subjects
Add topics under each subject
Mark topics as completed
Track subject-wise progress percentage
Track overall progress
Maintain daily study streak
Persistent data storage using Java serialization
Clean, color-coded CLI interface

Architecture

The application follows clean OOP principles:
Topic → Represents a revision topic
Subject → Contains multiple topics
RevisionTracker → Manages subjects, streaks & persistence
Main → CLI interaction layer
Encapsulation, aggregation, and separation of concerns are applied throughout the design.

Data Persistence

The application uses Java object serialization to store application state in:
tracker.dat
This ensures progress is retained across sessions.

How to Run

Navigate to the src folder:
javac *.java
java Main

Built with GitHub Copilot CLI

Copilot CLI was used directly inside the terminal to:
Design class structure
Generate OOP templates
Implement progress logic
Implement serialization
Improve code organization

Prompts:

<gh copilot -i "design the class structure for a CLI based exam revision tracker in Java using OOP principles">
<gh copilot -p "how to implement Java object serialization to save application state">

Why This Project?
This is my first time participating in a developer challenge, and this project represents my journey into building structured CLI applications using AI-assisted development.