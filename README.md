# Quiz-App  <br>
Android app designed for students to take multiple-choice quizzes on various subjects. The app retrieves the quizzes from a server and displays them to the user with the date they were last updated. To use the app, users must register first. This app is currently under development and may be subject to change as new features are added and bugs are fixed.

<br><br>
# Features and Tools <br>
<a href="https://developer.android.com/jetpack/guide" target="_new">MVVM architecture</a></li><li>
The Model-View-ViewModel (MVVM) architecture is used in this app to help separate the concerns of the app's components. The View displays the UI, the ViewModel retrieves data from the Model and updates the View, and the Model holds the data and provides it to the ViewModel.
MVVM allows for easier testing and maintainability of the app's components, and it can also lead to better performance as the UI is updated only when necessary.<br>

<a href="https://developer.android.com/guide/navigation" target="_new">Navigation Component</a></li><li>
The Navigation Component is a tool provided by the Android Jetpack libraries that simplifies the process of navigating between screens in an Android app.<br>

<a href="https://square.github.io/retrofit/" target="_new">Retrofit</a></li><li>
Retrofit is used to retrieve quiz data from the server, making it easier to handle network requests and parse responses<br>

<a href="https://developer.android.com/training/basics/network-ops/managing" target="_new">ConnectivityObserver</a></li><li>
The ConnectivityObserver is a tool that helps developers monitor network status changes and provide feedback to the user. It uses a broadcast receiver to listen for network status changes and updates the UI accordingly.<br>

<a href="https://square.github.io/okhttp/" target="_new">Okhttp</a></li><li>
Okhttp is also used to support HTTP/2 and WebSocket protocols, making it a reliable HTTP client for Android and Java. <br>

<a href="https://kotlinlang.org/docs/sealed-classes.html" target="_new">Sealed class</a></li><li>
A sealed class is used to handle response errors, ensuring that the app remains stable and performs as expected even when unexpected errors occur. <br>

<a href="https://developer.android.com/training/data-storage/shared-preferences" target="_new">Shared preferences</a></li></li><li>
Shared preferences are used to store user data for easy access between app sessions. This provides a more personalized experience for users. 
</div></div></div><div class="flex justify-between">
