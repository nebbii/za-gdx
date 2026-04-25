# ZaGdx

A port of ZA (originally for the CD-i) written in Java with [libGDX](https://libgdx.com/). 
Assets need to be ripped from a backup of the game, script for this is still under construction.

## Running the game
First you'll need to run the bin/cue rip script to get the assets for the game. 
Obtain a rip of the game and run the following line in a shell or terminal:
- `python build.py Zelda's\ Adventure.bin assets/export`
### Building an executable
1. `./gradlew lwjgl3:build`
2. The executable can be found under lwjgl3/build/libs
### Debugging the game
 `./gradlew lwjgl3:run`
## Credits
- nebbii - Coding, mapping
- Phlosioneer - [Game asset extracting script](https://github.com/Phlosioneer/ZeldasAdventureExtractor)
- buntata - [Asset ripping automation](https://github.com/potatonyan/ZA_AssetExtraction), mapping
