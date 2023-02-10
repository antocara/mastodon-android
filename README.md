Mastodon for Android
======================

[![Crowdin](https://badges.crowdin.net/mastodon-for-android/localized.svg)](https://crowdin.com/project/mastodon-for-android)

This is a fork of the repository for the official Android app for Mastodon. This is a port to Kotlin language. My idea is to modify the interfaces and use Jetpack Compose, add unit tests. Eventually turn it into a Kotlin Multiplatform project and create an IOS version and a desktop version

[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png"
     alt="Get it on F-Droid"
     height="80">](https://f-droid.org/packages/org.joinmastodon.android/)
[<img src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png"
     alt="Get it on Google Play"
     height="80">](https://play.google.com/store/apps/details?id=org.joinmastodon.android)

Or get the APK from the [The Releases Section](https://github.com/mastodon/mastodon-android/releases/latest).

## Contributing
If you want to contribute, you just have to open a PR and I will be happy to assist you. All feedback is welcome

## Building

At the moment as this app is using Java 17 features, you need JDK 17 or newer to build it. Other than that, everything is pretty standard. You can either import the project into Android Studio and build it from there, or run the following command in the project directory:

```
./gradlew assembleRelease
```

## License

This project is released under the [GPL-3 License](./LICENSE).

The Mastodon name and logo are trademarks of Mastodon gGmbH. If you intend to redistribute a modified version of this app, use a unique name and icon for your app that does not mistakenly imply any official connection with or endorsement by Mastodon gGmbH.
