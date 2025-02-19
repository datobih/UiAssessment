## Libraries Used

This project leverages the following libraries to enhance functionality and development efficiency.

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
