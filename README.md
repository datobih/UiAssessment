## Libraries Used



## Architecture

This project follows the **Model-View-ViewModel (MVVM)** architectural pattern, a well-established approach for building maintainable and testable user interfaces. MVVM promotes a clear separation of concerns, enhancing code organization and scalability.

**Key Architectural Components:**

*   **Model:**
    *   **Responsibility:** Represents the data and business logic of the application. In this project, the Model layer primarily consists of:
        *   **Data Classes:**  Kotlin data classes that define the structure of data objects (e.g., `FoodItem`, `Category`, `Tag`).
        *   **Data Sources:** Abstractions for data retrieval and persistence. This includes:
            *   **Remote Data Sources:** Implementations for fetching data from the backend API (e.g., using Retrofit). These data sources handle network requests, API communication, and data serialization/deserialization.
            *   **Local Data Sources (Optional):**  While not explicitly mentioned, future iterations might include local data sources (e.g., Room database, DataStore) for caching or offline capabilities.

*   **Composables:**
    *   **Responsibility:**  Represents the User Interface (UI) layer. In this project, the View layer is built using **Jetpack Compose**.
    *   **Implementation:** Composables are the building blocks of the View layer. They are responsible for:
        *   **UI Rendering:** Displaying data to the user and handling user interactions.
        *   **UI State Observation:** Observing `LiveData` exposed by the ViewModels to react to data changes and update the UI accordingly.
        *   **Limited Business Logic:** Composable States are kept as passive as possible, primarily focused on presentation and UI interaction handling. They delegate business logic and data manipulation to the ViewModel.

*   **ViewModel:**
    *   **Responsibility:** Acts as a bridge between the Model and the View. ViewModels are responsible for:
        *   **Preparing Data for the UI:** Retrieving data from the Repository (Model), transforming and formatting it into a UI-friendly format.
        *   **Holding UI State:** Managing UI-related state and exposing it to the View, typically using `LiveData`.
        *   **Handling User Input:** Receiving user actions from the View and coordinating with the Model (Repository) to perform business logic operations.
        *   **Lifecycle Awareness:** ViewModels are lifecycle-aware (provided by `ViewModel` class), surviving configuration changes (like screen rotations) and ensuring data persistence across these events. This prevents unnecessary data re-fetching and maintains UI state.

*   **Repository:**
    *   **Responsibility:**  Abstracts data access from the ViewModels. Acts as a single source of truth for data, encapsulating data retrieval logic.
    *   **Implementation:** Repositories mediate between different data sources (Remote and potentially Local). They are responsible for:
        *   **Data Source Orchestration:** Deciding which data source to use (e.g., fetching from remote API, retrieving from local cache).
        *   **Data Caching Strategies (Potential Future Enhancement):** Repositories can implement caching logic to improve performance and offline capabilities (e.g., storing frequently accessed data in a local database).
        *   **Data Transformation (Optional):**  Repositories can perform data transformations and aggregation from different data sources before providing it to the ViewModels.

**Data Flow:**

The data flow within the application generally follows this unidirectional pattern:

1.  **UI Interacts:** User interacts with the UI (e.g., button click, form input).
2.  **View Delegates to ViewModel:**  The View notifies the ViewModel about the user interaction (e.g., calling a function on the ViewModel).
3.  **ViewModel Interacts with Repository:** The ViewModel requests data or performs an action through the Repository.
4.  **Repository Retrieves Data:** The Repository fetches data from the appropriate data source (typically the Remote Data Source for API calls in this project).
5.  **Data is Flowed via LiveData:** The Repository (or ViewModel, depending on where LiveData is exposed) updates `LiveData` with the retrieved data.
6.  **View Observes LiveData and Updates UI:** The View observes the `LiveData` exposed by the ViewModel. When `LiveData` emits new data, the View is automatically notified and updates the UI to reflect the changes.



I used the following libraries when working on this project.

**Jetpack Compose UI & Material Design:**

*   `implementation("androidx.compose.material:material:1.7.8")`
    *   **Category:** UI Toolkit - Material Design Components for Jetpack Compose
    *   **Purpose:** Provides pre-built Material Design components (Buttons, TextFields, Cards, etc.) for building user interfaces in Jetpack Compose.
    *   **Benefits:** Enables rapid UI development with consistent Material Design styling, accessibility, and theming capabilities.  Forms the foundation for creating modern Android UIs in Compose.

*   `implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")`
    *   **Category:** Layout Management - ConstraintLayout for Jetpack Compose
    *   **Purpose:** Offers the flexible and powerful `ConstraintLayout` system within Jetpack Compose.
    *   **Benefits:**  Facilitates the creation of complex and adaptive layouts, especially for responsive designs that adapt to different screen sizes and orientations. Allows for precise control over UI element positioning and relationships using constraints.

**Dependency Injection:**

*   `implementation("com.google.dagger:hilt-android:2.51.1")`
*   `kapt("com.google.dagger:hilt-android-compiler:2.51.1")`
    *   **Category:** Dependency Injection (DI) - Hilt for Android
    *   **Purpose:** Simplifies dependency injection in Android applications built with Kotlin and Jetpack Compose. Hilt is built on top of Dagger and integrates seamlessly with Android's lifecycle.
    *   **Benefits:**  Improves code testability, maintainability, and modularity by managing dependencies effectively. Reduces boilerplate code associated with manual dependency injection. Provides compile-time correctness of dependency graphs.

**Navigation:**

*   `val nav_version = "2.8.7"`
*   `implementation("androidx.navigation:navigation-compose:$nav_version")`
    *   **Category:** Navigation - Jetpack Navigation Component for Compose
    *   **Purpose:** Implements navigation between Composables within the application, managing the back stack, handling deep links, and providing a structured approach to UI navigation.
    *   **Benefits:**  Encourages single-activity architecture, simplifies navigation logic, and integrates smoothly with Jetpack Compose's declarative UI paradigm.  Enhances user experience through consistent and predictable navigation patterns.

**Data Handling & Serialization:**

*   `implementation(libs.kotlinx.serialization.json)`
    *   **Category:** Serialization - Kotlinx Serialization JSON
    *   **Purpose:** Provides Kotlin serialization for JSON format. Enables easy conversion between Kotlin objects and JSON strings.
    *   **Benefits:**  Kotlin-first serialization library with type safety and performance optimizations. Streamlines data serialization and deserialization for network communication or data persistence.

*   `implementation("androidx.compose.runtime:runtime-livedata:1.7.5")`
    *   **Category:** Reactive Data - LiveData Integration for Compose Runtime
    *   **Purpose:** Allows seamless observation of `LiveData` objects within Jetpack Compose Composables.
    *   **Benefits:** Enables the use of `LiveData` (from Android Architecture Components) as a state source in Compose UIs.  Facilitates reactive data flow from ViewModels to Composable UI elements, automatically triggering UI updates upon data changes.

**Image Loading:**

*   `implementation("io.coil-kt.coil3:coil-compose:3.0.3")`
*   `implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.3")`
    *   **Category:** Image Loading - Coil (Coroutine Image Loader)
    *   **Purpose:**  A modern, lightweight, and asynchronous image loading library for Android, built for Kotlin and Compose. Handles efficient image fetching, caching (memory and disk), and display.
    *   **Benefits:** Simplifies image loading from network URLs, local files, and resources into Compose `Image` composables. Optimized for performance and memory efficiency, with coroutine-based asynchronous operations and automatic caching.  `coil-network-okhttp` module integrates with OkHttp for network requests, leveraging OkHttp's features like connection pooling and HTTP/2 support.

**Networking:**

*   `implementation ("com.squareup.retrofit2:retrofit:2.11.0")`
*   `implementation ("com.squareup.retrofit2:converter-gson:2.11.0")`
*   `implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")`
    *   **Category:** Networking - Retrofit & OkHttp
    *   **Purpose:**
        *   **Retrofit:** A type-safe HTTP client for Android and Java. Simplifies the process of making network requests by defining API interfaces and handling request/response serialization and deserialization.
        *   **OkHttp (via Retrofit):** An efficient HTTP client that Retrofit uses under the hood. Handles network communication, connection pooling, caching, and more.
        *   **`converter-gson`:** Retrofit converter to handle JSON serialization and deserialization using Gson library.
        *   **`logging-interceptor`:** OkHttp interceptor for logging HTTP request and response information.
    *   **Benefits:**  Streamlines API communication by abstracting away low-level networking details.  Reduces boilerplate code for network requests.  `converter-gson` simplifies working with JSON data from APIs. `logging-interceptor` is invaluable for debugging network interactions during development.
