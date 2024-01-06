# CineVault
CineVault simplifies your movie world! Organize watched films, plan to watch, and experience a secure login. Elevate your movie journey with CineVault—where user-friendly organization meets top-notch security, offering a personalized and protected movie database for all film enthusiasts.

# CineVault: Movie Database System

**Author:** Jeyadheep V <br>
**Date:** 6/1/2024

## Overview

CineVault is a Java-based Movie Database system allowing users to register, login, manage watched and plan-to-watch movies, and more. It features secure data storage with encryption for user passwords and CSV files.

## Features

1. **User Registration:**
   - Register with a unique username and a password meeting specific constraints.

2. **User Login:**
   - Log in using either email or username along with a password.

3. **Movie Management:**
   - Add watched movies with details like name, rating, completion date, and a small review.
   - Add movies to the plan-to-watch list.

4. **Display Movie Database:**
   - View lists of plan-to-watch and watched movies with relevant details.

5. **Update Movie Database:**
   - Move movies from the plan-to-watch list to the watched list.
   - Remove completed movies from the watched list.

6. **Save Data to CSV Files:**
   - User data is stored in `user_db.csv` (encrypted).
   - Movie data is stored in `movie_db.csv` with fields: sr.no, username, movie_name, status, rating, and review.


## File Structure

- `MovieDatabase.java`: Main Java source code file.
- `user_db.csv`: CSV file for encrypted user data.
- `movie_db.csv`: CSV file for movie data.

## Usage

1. **Clone the Repository**
   ```bash
     git clone https://github.com/BackBenchDreamer/CineVault.git

2. **Compile the Code**
   ```bash
     javac MovieDatabase.java
3. **Running the Code**
   ```bash
     java MovieDatabase

## Output

1. **Register and Login Page** <br>![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/863ad676-d684-4977-8ce0-6958bae747f6)


2. **Adding Movie to Watched List** <br> ![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/4bfa6c98-35c2-43ac-a59a-d61f41f2e3ec)

3. **Adding Movie to Plan to Watch List** <br> ![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/93688d73-574c-49a2-b4f7-3f1ebbfebcc8)

4. **Displaying the Movie List** <br> ![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/aed891f9-fa08-4d26-aaeb-84bd9e078668)

5. **Moving Movie from Plan to Watch List to Watched Movies List**<br> ![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/b71cfc89-9522-4f53-988d-a8902c6208f0)

6. **Deleting Movies from Watched List** <br> ![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/a8e6832a-bdea-4fdf-915b-86c4b92e811f)

7. **Exit** <br> ![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/d14f0454-42ff-4b3a-b975-cc4a463435e3)
 
8. **How the Data is Stored in user_db.csv** <br> ![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/11677496-aa0c-4ae1-808a-be1691178fbb)

9. **How the Data is Stored in movie_db.csv** <br> ![image](https://github.com/BackBenchDreamer/CineVault/assets/112080762/763e106e-d8e1-4602-9431-4230324703f5)
