# FirstGame - Hex Strategy Prototype

A 2D hex-based strategy game prototype built with Java, LibGDX, Gradle, and JUnit.
This project was originally generated with gdx-liftoff and extended as a learning project to gain experience in game development, input handling, and rendering logic.

## Project Overview

This prototype explores the core systems behind a real-time strategy game:

- Hexagonal game map

- Planet-based nodes connected with lines

- Camera movement with custom mouse controls (panning & zooming)

- Basic UI screens (main menu, game screen)

- Early logic for entities and world interaction

- Unit test setup using JUnit

The project is not complete and some systems are experimental, but it demonstrates architectural structure, input handling, and rendering techniques in LibGDX.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests.
