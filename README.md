# English Learning App

![GitHub](https://img.shields.io/badge/GitHub-Repository-blue.svg)
![Platform](https://img.shields.io/badge/Platform-Android-green.svg)
![Language](https://img.shields.io/badge/Language-Kotlin-orange.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

A comprehensive English learning Android app with **Listening**, **Reading** and **Word** modules, designed to help users improve their English proficiency efficiently.

---

## 🎯 Features

### 🎧 Listening Module
- **MP3 File Import**: Import audio files from local storage
- **AI-Powered Transcription**: Use LLMs to transcribe speech to text
- **Lyric-Style Subtitles**: Synchronized subtitles like karaoke/lyrics display
- **Playback Controls**: Play, pause, seek, speed adjustment (0.5x-2.0x)
- **Loop Modes**: Single track loop and repeat functionality
- **Audio Library**: Manage and organize your audio collection

### 📖 Reading Module
- **Camera OCR**: Take photos and recognize text using OCR
- **PDF Import**: Import and render PDF documents
- **Word Annotation**: Long-press to add custom annotations to words
- **Word Lookup**: Tap to view word definitions instantly
- **Reading Progress**: Track reading progress and resume later
- **Dark Mode**: Support for comfortable reading in low light
- **Font Adjustment**: Customizable text size for better readability

### 📝 Word Module
- **CET-4 Vocabulary**: Complete CET-4 word list (4500+ words)
- **Smart Review**: Spaced repetition algorithm for efficient learning
- **Word Association**: Automatically tracks words from listening/reading
- **Word Details**: Show definitions, phonetics, example sentences
- **Mastery Tracking**: Track your learning progress with mastery levels
- **Favorites**: Save and manage your favorite words
- **Learning Statistics**: View detailed learning statistics

---

## 🏗️ Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture with **Clean Architecture** principles:

```
EnglishLearningApp/
├── data/                  # Data layer
│   ├── dao/              # Data Access Objects (Room)
│   ├── database/         # Database configuration
│   ├── model/            # Data models
│   ├── repository/       # Data repositories
│   └── network/          # API service and network layer
├── domain/               # Domain layer (business logic)
├── ui/                   # UI layer
│   ├── components/       # Reusable Compose components
│   ├── screens/          # Screens and view components
│   └── theme/            # Theme and styling
├── viewmodel/            # ViewModels
└── utils/                # Utility classes
```

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Kotlin** | 1.9+ | Primary development language |
| **Jetpack Compose** | 1.5+ | Modern UI framework |
| **Room** | 2.6+ | Local database persistence |
| **Hilt** | 2.48 | Dependency injection |
| **ExoPlayer** | 2.19+ | Audio playback |
| **Google ML Kit** | - | OCR text recognition |
| **AndroidPdfViewer** | 3.2.0+ | PDF rendering |
| **Retrofit** | 2.9+ | Network requests |
| **Coroutines** | - | Asynchronous programming |

---

## 📱 Screenshots

### Home Screen
- Overview of learning progress
- Quick access to all modules
- Recent learning activities

### Listening Screen
- Audio library management
- Player with subtitle display
- Audio controls and settings

### Reading Screen
- Text and PDF display
- Annotation system
- Reading controls

### Word Screen
- CET-4 vocabulary browser
- Word detail views
- Learning statistics

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Hedgehog (2023.1.1) or later
- **Android SDK** API 26 (Android 8.0) or higher
- **JDK** 8 or higher
- **Gradle** 8.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/stongkid/english-learning-app.git
   cd english-learning-app
   ```

2. **Open the project**
   - Open Android Studio
   - Select "Open an existing project"
   - Choose the `english-learning-app` directory

3. **Sync Gradle**
   - Wait for Gradle sync to complete
   - Accept any SDK license agreements

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click "Run" (▶️) button or press `Shift+F10`

### API Configuration (Optional)

To use the AI transcription feature, you'll need to set up your API endpoint:

1. Open `data/network/ApiService.kt`
2. Configure your API base URL
3. Add your API key (if required)

---

## 📋 Project Structure

```
EnglishLearningApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/englishlearning/
│   │   │   ├── data/
│   │   │   │   ├── dao/             # Database access objects
│   │   │   │   ├── database/        # Database setup
│   │   │   │   ├── model/           # Data models
│   │   │   │   ├── repository/      # Data repositories
│   │   │   │   └── network/         # API services
│   │   │   ├── ui/
│   │   │   │   ├── components/      # UI components
│   │   │   │   ├── screens/         # Screen implementations
│   │   │   │   └── theme/           # Theme and styling
│   │   │   ├── viewmodel/           # ViewModels
│   │   │   ├── utils/               # Utility classes
│   │   │   ├── MainActivity.kt
│   │   │   └── EnglishLearningApp.kt
│   │   ├── assets/
│   │   │   └── cet4_words.json      # CET-4 vocabulary data
│   │   ├── res/                     # Resources
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── EnglishLearningApp_PRD.md        # Product requirements document
├── EnglishLearningApp_Design.md     # UI/UX design documentation
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🎨 Design Principles

- **Clean Interface**: Simple, intuitive user interface
- **Responsive Design**: Works on various screen sizes
- **Material Design**: Follows Material Design guidelines
- **Accessibility**: Supports accessibility features
- **Dark/Light Theme**: Theme support for comfortable use

---

## 📊 Database Schema

### Main Tables

| Table | Description |
|-------|-------------|
| `Audio` | Stored audio files and metadata |
| `Reading` | Imported reading materials |
| `Annotation` | Word annotations from reading |
| `Word` | CET-4 vocabulary list |
| `WordLearning` | User's learning progress |

---

## 🧪 Testing

This project follows best practices for testing:

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Run all tests
./gradlew test connectedAndroidTest
```

---

## 📄 Documentation

For more detailed information:

- **Product Requirements**: [EnglishLearningApp_PRD.md](./EnglishLearningApp_PRD.md)
- **UI/UX Design**: [EnglishLearningApp_Design.md](./EnglishLearningApp_Design.md)

---

## 🤝 Contributing

Contributions are welcome! Please feel free to:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Hilt](https://dagger.dev/hilt/)
- [ExoPlayer](https://exoplayer.dev/)
- [Google ML Kit](https://developers.google.com/ml-kit)

---

## 📞 Contact / 联系方式

If you have any questions, want to collaborate, or need help, feel free to reach out!

### WeChat / 微信

Scan the QR code below to add me on WeChat:

### Other Ways to Connect

- **GitHub Issues**: [Open an Issue](https://github.com/stongkid/english-learning-app/issues)
- **Repository**: [https://github.com/stongkid/english-learning-app](https://github.com/stongkid/english-learning-app)

---

## 🌟 Star History

Give this project a ⭐ if you find it helpful!

---

**Built with ❤️ for English learners everywhere**
