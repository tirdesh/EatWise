# EatWise Diet Tracker - README

## Project Overview

Eat-Wise is a comprehensive dietary management system designed to help users monitor and manage their nutritional intake effectively. The application provides tools for logging daily food and water consumption, setting personalized dietary goals, tracking progress, and gaining insights through visual data representations. Key features include Nutri Match for finding nutritionally similar foods and Nutri Sort for organizing food items by nutritional value.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Tech Stack](#tech-stack)
4. [System Design](#system-design)
5. [Installation](#installation)
6. [Usage](#usage)
7. [Future Enhancements](#future-enhancements)
8. [Contributors](#contributors)

## Introduction

In today's health-conscious world, managing dietary habits is crucial. Eat-Wise aims to address the common challenges faced by individuals in tracking and analyzing their nutrition. By providing an easy-to-use interface and powerful tracking tools, Eat-Wise helps users stay on top of their dietary goals and make informed decisions about their eating habits.

## Features

- **Food Logging**: Log daily food intake, including portion sizes and meal times.
- **Nutritional Tracking**: Display nutritional information such as calories, macronutrients, vitamins, and minerals.
- **Goal Setting**: Set personalized dietary goals based on nutritional needs and preferences.
- **Progress Monitoring**: Track progress towards dietary goals over time.
- **Water Intake Tracker**: Monitor daily water consumption.
- **Nutri Match**: Discover foods with similar nutritional profiles.
- **Nutri Sort**: Organize and prioritize food items based on nutritional value.
- **User Management**: Secure user authentication, registration, and profile management.

## Tech Stack

- **Programming Language**: Java
- **User Interface**: JavaFX, FXML, CSS
- **Data Structures**: Lists, HashMaps, Binary Search Trees (BST), Graphs
- **Algorithms**: Merge Sort, Euclidean Distance for similarity matching

## System Design

### Architecture Overview

The system is organized into high-level modules, each responsible for specific functionalities:

1. **Presentation Layer**: Handles user interactions, built using JavaFX and FXML.
2. **Application Logic Layer**: Implements core functionalities such as session management and dietary tracking.
3. **Data Access Layer**: Manages data storage and retrieval using efficient data structures.
4. **Database Layer**: Stores persistent data like user profiles and meal logs.

### Packages

- **model.implementation**: Core data models and business logic.
- **model.io**: Data persistence utilities.
- **model.manager**: Manages data operations and business logic.
- **model.util**: Utility classes for data manipulation and algorithmic operations.
- **view_controller.fx**: User interface components and controllers.

## Installation

1. **Prerequisites**:
   - Java Development Kit (JDK)
   - Integrated Development Environment (IDE) like Eclipse or IntelliJ IDEA

2. **Steps**:
   - Clone the repository: `git clone <repository-url>`
   - Open the project in your preferred IDE.
   - Ensure JavaFX is set up correctly in your IDE.
   - Build and run the project.

## Usage

1. **Login**: Authenticate using your credentials.
2. **Dashboard**: View an overview of daily food consumption and progress.
3. **Log Food**: Add details of food items consumed.
4. **Set Goals**: Define and adjust dietary goals.
5. **Track Water Intake**: Log and monitor daily water consumption.
6. **Nutri Match**: Find and compare nutritionally similar foods.
7. **Nutri Sort**: Sort and prioritize food items based on nutritional value.

## Future Enhancements

- Integrate machine learning algorithms for personalized dietary recommendations.
- Support for external APIs such as nutrition databases and recipe databases.
- Add social features for user interaction and community building.
- Extend platform support to web, iOS, and Android applications.

## Contributors

- Abhishek Sagar Sanda (sanda.a@northeastern.edu)
- Tirdesh Pettugani (pettugani.t@northeastern.edu)
- Abhishek Chintapalli (chintapalli.a@northeastern.edu)

---

Thank you for choosing Eat-Wise. Let's be wise about our health together!
