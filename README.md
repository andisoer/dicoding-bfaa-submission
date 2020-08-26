# dicoding-bfaa-submission
![Platform](https://img.shields.io/badge/platform-Android-green.svg) ![Kotlin](https://img.shields.io/badge/kotlin-1.3.72-blue.svg) ![Gradle](https://img.shields.io/badge/gradle-4.0.1-%2366DCB8.svg)

This repository containt my last and final submission for [Dicoding BFAA Course](https://www.dicoding.com/academies/14) which impelement Android Widget or Component such as Content Provider, Local Database, Localization, Notification, Alarm Manager, and MVVM Architecture. This repo also include 2 modules (app & consumerapp) which each module must comunicate each other using Content Provider.

## Screenshots
<h3 align="center"> Search Screen </h3>
<p align="center">
    <img src="assets/app-search-waiting.png"
        alt="Home (app Module)"    
        style="margin-right: 10px;"    
        width="200" />
    <img src="assets/app-search.png"
        alt="Detail Profile (app Module)"    
        style="margin-right: 10px;"    
        width="200" />
    <img src="assets/app-search-error.png"
        alt="Settings (app Module)"    
        style="margin-right: 10px;"    
        width="200" />
    <img src="assets/search.gif?raw=true"
        alt="Home (consumerapp Module)"    
        style="margin-right: 10px;"    
        width="200" />
</p>

## List of Features
- ##### app Module
  - Home (Search for Github Users)
  - Github Users Detail
  - Settings
    - Alarm Reminder
    - Localization (Change Language)
  - Add to favorite and Delete from favorite
- ##### consumerapp Module
  - Home (List of favorited users)
  - Github Favorited User Detail
  - Remove to favorite
  - Localization (Change Language)
  
## Tech Stacks 🛠
- Kotlin (Programming Language)
- Room (Local Database built on top of SQLite)
- Content Provider
- Alarm Manager
- Notification (for Alarm Manager)
- MVVM (Architecture)
- Jetpack Navigation Component
- Retrofit (Network Library)
- Glide (Image Loading Library)
