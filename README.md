[![Android CI](https://github.com/antocara/musktodon/actions/workflows/pr_merge.yml/badge.svg)](https://github.com/antocara/musktodon/actions/workflows/pr_merge.yml)

Musktodon for Android
======================


This is a fork of the repository for the official Android app for Mastodon. This is a port to Kotlin language. My idea is to modify the interfaces and use Jetpack Compose, add unit tests. Eventually turn it into a Kotlin Multiplatform project and create an IOS version and a desktop version


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
