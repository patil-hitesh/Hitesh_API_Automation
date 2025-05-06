## 📋 Project Objectives

- ✅ Automate all relevant Petstore API endpoints
- ✅ Cover both **positive** and **negative** test scenarios
- ✅ Use **Cucumber** (Gherkin) for writing behavior-driven tests
- ✅ Use **Serenity BDD** with **Maven** for test execution and reporting
- ✅ Configure **GitLab CI/CD** to run tests in pipeline
- ✅ Generate and attach **HTML reports** as artifacts
- ✅ Provide instructions and documentation for future maintenance
---

## 📦 Tech Stack

- Java 17
- Maven
- Serenity BDD
- Rest Assured
- Cucumber
- GitLab CI/CD

---

## 🚀 Getting Started

### ✅ Prerequisites
- Java 17 or above
- Maven 3.9 or above
- Git

### 🔧 Clone the Project
```bash
git clone https://gitlab.com/your-gitlab-repo-url.git
cd petstore-serenity-bdda

Install Dependencies:
	mvn clean install

Run All Tests Locally:
	mvn clean verify

View HTML Report:
	target/site/serenity/index.html

How to Write New Tests
1. Add Feature File:
	Feature: Retrieve a pet

  Scenario: Get pet by valid ID
    Given a pet with ID 123 exists
    When the user retrieves the pet by ID
    Then the response code should be 200
    And the response should contain the pet's name

2. Create Step Definitions:
	src/test/java/steps/

Refactoring Highlights
🧹 Cleaned unused imports and classes

📁 Modular structure with steps, tasks, models

♻️ Created reusable tasks

📊 Improved assertion handling for various HTTP codes

🛠 Centralized endpoint URLs and headers

🧪 Added negative test coverage (400, 404, 405 errors)


├── src
│ └── test
│ ├── java
│ │ ├── steps/ # Step definitions for Cucumber scenarios
│ │ ├── tests/ # Cucumber runner classes (test entry points)
│ │ ├── models/ # POJO classes for request/response payloads
│ │
│ └──| resources
│ 	 └── features/ # Gherkin feature files defining test scenarios
├── target/site/serenity/ # Serenity BDD HTML test reports (auto-generated)
├── pom.xml # Maven project configuration file
├── .gitlab-ci.yml # GitLab CI/CD pipeline configuration
└── README.md # Project overview and instructions

