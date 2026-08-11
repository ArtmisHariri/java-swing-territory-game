# Territory Builder (Java Swing)

A grid-based Java Swing game: move around the map, claim territory by
building a "house", and avoid two enemies while doing it. Includes a
selectable player skin (orange / melon / batman) and a themed
background.

## Project structure
```
territory-builder-game/
├── src/
│   └── Project.java     # game logic + Swing UI (entry point)
├── sources/              # image assets (player/house/slight sprites, background)
└── README.md
```

## Build & run
```bash
cd src
javac Project.java
java Project
```
> Run this from a location where the `sources/` folder is reachable at
> `sources/...` relative to the working directory (i.e. run the `java`
> command from the project root, or copy `sources/` next to the
> compiled classes).

## Controls
| Key | Action |
|---|---|
| ↑ / ↓ / ← / → | Move |
| Enter | (see `Project.java` for exact behavior) |
| Space | (see `Project.java` for exact behavior) |


