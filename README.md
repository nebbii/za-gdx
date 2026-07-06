# ZaGdx

A port of ZA (originally for the CD-i) written in Java with [libGDX](https://libgdx.com/).

## Setup
### Requirements
#### ...for running the game/building an executable
- [Java JDK 17](https://adoptium.net/temurin/releases?version=17&os=any&arch=any) (for libgdx)
#### ...for the initial setup
- [git](https://git-scm.com/) (for loading the submodule)
- [chdman](https://onionui.github.io/docs/advanced/chdman) (if using bin/cue)
  - On Windows: Download and extract from the link above and pass the exe
  - On Linux: Install `mame-tools` using your package manager of choice
- [python3](https://www.python.org/) (but not 3.14), with the following dependencies:
  - tqdm
  - pillow
  - Note: You can do the initial run in [venv](https://docs.python.org/3/library/venv.html) to avoid installing dependencies directly on your machine.
### Providing an asset source
You'll need to acquire a bin/cue or .CHD of the original game and place it in the project directory.
```
4e9a8db4358c4a6d6e1f3bea18caa881  za.bin
7ae5883df11136e6ed7b24549585f4bc  za.cue
597176968e14d0025e58b644d841606a  za.chd
```

### Running the game

On windows:
- Open powershell and execute the following command in the project folder:
  - `./gradlew.bat lwjgl3:run -Pchdman=/path/to/your/chdman.exe`
- Note: After the initial setup, you can drop the chdman parameter!

On Linux/Mac:
- Open a terminal window and execute the following command in the project folder:
  - `./gradlew lwjgl3:run`

### Running tests
Execute `./gradlew core:test --info --rerun-tasks`

### Building an executable
1. `./gradlew lwjgl3:build`
2. The executable can be found under lwjgl3/build/libs

### Controls
Controls can be bound in the settings menu for keyboard and gamepads.

Default controls:
- Arrows - Move
- Left Shift - Action
- P - Open inventory
- Mouse - Interact with inventory
- F2 - Toggle debug views

### Archipelago
This port has a built-in archipelago client that can be configured from the settings menu! The game automatically connects on the main menu screen after creating a config file.

### Contributing
Pull requests are welcome! Please maintain [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/) and [branch naming](https://conventional-branch.github.io/) if possible. Check out the [Issues page](https://github.com/nebbii/za-gdx/issues) for stuff to do.

[Here's a link to the Archipelago integration for the port!](https://github.com/nebbii/Archipelago-za-gdx)

## Credits
- nebbii - Coding, mapping
- Phlosioneer - [Game asset extracting script](https://github.com/Phlosioneer/ZeldasAdventureExtractor)
- buntata - [Asset ripping automation](https://github.com/potatonyan/ZA_AssetExtraction), mapping
- Krista Corkos - Archipelago assets
- Christopher Wilson - Archipelago assets
