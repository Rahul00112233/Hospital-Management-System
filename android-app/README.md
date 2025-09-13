# HMS Android App (Jetpack Compose)

This Android app consumes your Spring Boot HMS backend with JWT auth.

## Configure BASE_URL

- Emulator to localhost backend: `http://10.0.2.2:8080`
- Device on same network: `http://<your-machine-ip>:8080`

Change in `app/build.gradle.kts`:

```
defaultConfig {
    ...
    buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8080\"")
}
```

## Features
- Login and Register via `/api/auth/login` and `/api/auth/register`
- Saves JWT in DataStore
- Adds `Authorization: Bearer <token>` header for protected calls
- Sample protected call: `/api/patient/records`

## Run
```
./gradlew :app:assembleDebug
```
Open in Android Studio to run on emulator/device.

