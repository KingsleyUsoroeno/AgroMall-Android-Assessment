# AgroMall-Android-Assessment
### Getting Started 

The App makes use of the Google Place and Google Maps API , in order to use the Google Place API , please ensure to get a billed API Key from your 
google console [here](https://developers.google.com/places/android-sdk/get-api-key), once that is done , create a file called `apikey.properties`
in the root directory of your Android Studio Project with the key being `GOOGLE_MAP_API_KEY` and the value being your apikey and then build your project.

#### Modules
The App is very much modlarised into different packages to ensure a clean design code all of which performs different roles in the MVVM Structure of the App.
Those packages includes.
* authentication
* local
* dataSource
* UI 
etc

