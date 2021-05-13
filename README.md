# Transact - Transaction Management Application

## Description

Whenever we lend money to some people, friends, colleagues, we need to keep track of it to return the money to them timely. The same goes the other way round when others lend money from us. In earlier times, people used to use a personal notebook for tracking these details. However, the notebook is very fragile and can be easily lost, corrupted, or damaged. 

Keeping transaction records in the notebook can prove to be very disadvantageous for the people who significantly remain dependent on the records. Few examples of this could be the small businesspersons, store owners. Customers buy products from them, and they tend to pay later. Businesspersons have to enter all the records in the notebook manually. The major problem comes when they need to query the whole manually and need to filter out all the records of a particular person from the whole list of data. This process was tiresome, and the store owner could run into a significant loss if he could not recollect the money back.

So we need an affordable application that can keep track of all the transactions that users make. The purpose of the application would be to automate all of these mentioned limitations. The user needs to filter records and based on the filter. All the records would be displayed to the user in a list. Thus it would save time and energy, and also, the user will not run at a loss.

One of the most popular applications in the current market related to Transaction Management Application is “KhataBook.” It currently features over 10 Million+ downloads by users around the Globe. However, the application as of now has one considerable disadvantage. It does not ensure verification of the transaction records.

Suppose there are two parties involved in the transaction. Let us name them Party A and the other one Party B. Suppose A lends money to B. Let it be amount X. In the KhataBook application, the user can enter any amount, and it goes into the recordings without any verification. Now let us assume there were any malpractices involved from Party A, then he could record it any number, suppose ten times of X. That where the present “KhataBook” application does not provide trusted recordings. 

So the primary motivation in building this transaction recording application is to build a verification layer over the recordings so that any of the transactions recorded in this application is adequately trusted and verified by both parties involved in that transaction.


## Code structure

<img src="images/cleancodearchitecture.png" width="450" height ="600">

This application is created mainly by keeping Clean Code MVVM Architecture into our point of vision. 

What are the Different Layers of the Project?

- **Data layer:** Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain.
  - **Remote Database:** 
  - **Local Database:** The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite. Defining the Room entity, setting up the DAO Interface and building the Database Builder is done in this layer.

- **Domain layer:** This will be the most generic layer of the three. It will connect the presentation layer with the data layer. This is the layer where app-related business logic will be executed. All the application use-cases and the repositories interfaces reside in the domain layer.
  - **Use Cases:** Use cases are the application logic executor. As the name depicts each functionality can have its separate use case. With more granularity of the use case creation, it can be reused more often.
  - **Repositories:** It specifies the functionalities required by the use cases which is implemented by the data layer. 

- **Presentation layer:** The presentation layer provides the UI implementation of the application. This layer internally implements MVVM (Model-View-ViewModel) architecture.
  - Why _MVVM Architecture_ over other patterns: MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables. Also View Model store and manage UI-related data in a lifecycle conscious way. It allows data to survive configuration changes such as screen rotations.

## Package Structure
    
    com.sayantanbanerjee.transactionmanagementapp    # Root Package
    .
    ├── data                         # For data handling.
    │   ├── db                       # Local Persistence Database. Room (SQLite) database.
    │   ├── model                    # Model Classes.
    |   ├── preference               # Shared Preference.
    │   └── repository               # Defining the AppRepository class which provides connection 
    │ 			               to LocalDataSource and RemoteDataSource classes.
    |
    ├── domain                       # Serves connection between Data layer to Presenter Layer.          
    │   ├── repository               # Defining the AppRepository interface.
    │   ├── usecases                 # Use Cases of the Project.      
    |
    ├── presenter                    # Activity/View layer.
    │   ├── di                       # Dependency Injection module classes.  
    │   ├── AddRecord                # Add Record Activity(to add a new record) related View Model and View Model Factory class.
    │   ├── ScanningActivity         # Scanning Activity(to verify other records) related View Model and View Model Factory class. 
    │   ├── TransactionActivity      # Transaction Activity(to view all the transactions) related View Model, Factory and Adapter class.
    |
    └── utils                        # Utility Classes / Kotlin extensions

## Technologies and Libraries

- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For working with asynchronous threading related task.
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps. Thus they help us to separate business logic apart from the UI logic and helps us in designing proper architecture.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on configuration changes. 
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. 
- [Firebase Realtime Database](https://firebase.google.com/docs/database/) - The Firebase Realtime Database is a cloud-hosted database. Data is stored as JSON and synchronized in realtime to every connected client.
- [Zxing](https://github.com/zxing/zxing) - ZXing ("zebra crossing") is an open-source, multi-format 1D/2D barcode image processing library implemented in Java, with ports to other languages.

## Built With

* Android Studio

## Author

* <a href="https://www.linkedin.com/in/sayantan-banerjee-iiitgwalior/">  **Sayantan Banerjee** </a>
