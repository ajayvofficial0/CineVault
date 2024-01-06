# CineVault
CineVault simplifies your movie world! Organize watched films, plan to watch, and experience a secure login with advanced encryption. Elevate your movie journey with CineVault—where user-friendly organization meets top-notch security, offering a personalized and protected movie database for all film enthusiasts.

# CineVault: Movie Database System

**Author:** Jeyadheep V  
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

7. **Encryption:**
   - User passwords in `user_db.csv` are encrypted using Blowfish encryption.

## File Structure

- `CineVault.java`: Main Java source code file.
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

1. **Register and Login Page** <br>![image](https://github.com/BackBenchDreamer/LearninLanguages/assets/112080762/7404c290-3b63-4d2c-be5d-1640f385fab5)

2. **Adding Movie to Watched List** <br>![image](https://github.com/BackBenchDreamer/LearninLanguages/assets/112080762/d801248c-9871-415e-be3c-ee5b82880dff)

3. **Adding Movie to Plan to Watch List** <br>![image](https://github.com/BackBenchDreamer/LearninLanguages/assets/112080762/1f8e0691-9181-4a44-8985-8cdffa212827)

4. **Displaying the Movie List** <br>![image](https://github.com/BackBenchDreamer/LearninLanguages/assets/112080762/fdfcbd10-0dc2-496d-a598-59f7f5ccfd59)

5. **Moving Movie from Plan to Watch List to Watched Movies List**<br>![image](https://github.com/BackBenchDreamer/LearninLanguages/assets/112080762/3e2e26ce-c690-4915-a0df-8e378e52539e)

6. **Deleting Movies from Watched List** <br> ![image](https://github.com/BackBenchDreamer/LearninLanguages/assets/112080762/2d8297f5-729e-4eb5-88ae-d921766a64b3)

7. **Exit** <br>![image](https://github.com/BackBenchDreamer/LearninLanguages/assets/112080762/9a1475c0-3454-4741-9f4a-baa4db721156)
 
8. **How the Data is Stored in Database** <br> ![image](https://github.com/BackBenchDreamer/LearninLanguages/assets/112080762/64d2cf07-b740-4cc0-bb60-3e45354c7655)
