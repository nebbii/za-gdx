# ZaGdx

A port of ZA (originally for the CD-i) written in Java with [libGDX](https://libgdx.com/). 

## Setup
### Requirements
- Java JDK 17 or 21 (for libgdx)
- git (for loading the submodule)
- python3, with the following dependencies:
  - tqdm
  - pillow
- chdman (if using bin/cue)

### Providing an asset source
You'll need to acquire a bin/cue or .CHD of the original game and place it in the project directory.
```
4e9a8db4358c4a6d6e1f3bea18caa881  za.bin
7ae5883df11136e6ed7b24549585f4bc  za.cue
597176968e14d0025e58b644d841606a  za.chd
```

### Running the game
Execute `./gradlew lwjgl3:run` in the project folder. On initial setup this will create an assets export from your disc image.

### Building an executable
1. `./gradlew lwjgl3:build`
2. The executable can be found under lwjgl3/build/libs

### Controls
Arrows - Move
Ctrl - Action
P - Open inventory
Mouse - Interact with inventory
Space - Toggle debug data visibility

## Credits
- nebbii - Coding, mapping
- Phlosioneer - [Game asset extracting script](https://github.com/Phlosioneer/ZeldasAdventureExtractor)
- buntata - [Asset ripping automation](https://github.com/potatonyan/ZA_AssetExtraction), mapping
