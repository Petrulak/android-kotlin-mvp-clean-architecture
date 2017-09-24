# Kotlin MVP Clean Architecture
Tired of searching for simple, readable and easy to start with template for your Android application?

If your answer is yes, i have a solution for you in this repository! 

Sample app contains implementation of Clean architecture for Android using Kotlin, RxJava2, Dagger2 and MVP pattern.
## Features
* **Clean architecture** - App is divided into 3 modules. Data -> Domain -> Presentation
* **MVP** - Presentation layer is using model view presenter (MVP) design pattern.
* **EventBus** - Implementation of event bus using [RxRelay](https://github.com/JakeWharton/RxRelay).
* **DataBus** - Keep data in sync across various part of application.
* **Networking layer** - Network calls done via [Retrofit](http://square.github.io/retrofit/).
* **Persistence layer** - Store data using [Room](https://developer.android.com/topic/libraries/architecture/room.html).
* **Debugging** 
  * **Stetho** - Debug API calls, explore view hierarchy & storage. 
  * **LeakCanary** - Discover memory leaks.  
  * **Timber** - Log errors and pass them into your crash reporting tool (Instabug, Crashlytics etc..).  
* **Analytics Manager** - Basic interface to track events you are interested in. Sample usage with Mixpanel.
## Work in progress
* **Unit Tests**
* **UI Tests**
## Feel free to contribute :)
