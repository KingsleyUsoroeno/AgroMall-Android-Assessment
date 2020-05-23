# AgroMall-Android-Assessment
### Getting Started 

## Installation
AgroMall requires a minimum API level of 16. Clone the repository. The App makes use of the Google Place and Google Maps API, You will need to get a billed Google Maps API key from your Google Console
[here](https://developers.google.com/places/android-sdk/get-api-key) in order to enable the Google Place Functionality, If you don’t already have an account, you will need to create one in order to get an API Key.
In your project's root directory, create a file `apikey.properties` and include the following lines:

`GOOGLE_MAP_API_KEY = ""`


* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to handle data in a lifecycle-aware fashion.
* [Navigation Component](https://developer.android.com/guide/navigation) to handle all navigations and also passing of data between destinations.
* [Material Design](https://material.io/develop/android/docs/getting-started/)
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) used to manage the local storage i.e. `writing to and reading from the database`. Coroutines help in managing background threads and reduces the need for callbacks.
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) to declaratively bind UI components in layouts to data sources.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [Android KTX](https://developer.android.com/kotlin/ktx) which helps to write more concise, idiomatic Kotlin code.

## Architecture
The architecture of this application relies and complies with the following points below:
* A single-activity architecture, using the [Navigation Components](https://developer.android.com/guide/navigation) to manage fragment operations.
* Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)(MVVM) which facilitates a separation of development of the graphical user interface.
* [Android architecture components](https://developer.android.com/topic/libraries/architecture/) which help to keep the application robust, testable, and maintainable

#### Modules
The App is very much modularised into different packages to ensure a clean design code all of which performs different roles in the MVVM Structure of the App.
Those packages includes.
* authentication
* local
* dataSource
* UI 
etc

