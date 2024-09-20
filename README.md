# 2048-JavaGame
2048 Game in Java programming language

1. Introduction

   2048 is a sliding puzzle game where players combine numbered tiles on a grid to create larger numbers, aiming to reach the tile with the number 2048. Players can continue playing even after reaching 2048 to achieve higher scores. The game ends when no more legal moves are available.

   This implementation of 2048 is written in Java using Eclipse IDE and is structured into multiple packages, ensuring separation of game logic, user interfaces (both GUI and console), and data storage.

2. Tehnologies:
   
  Java

3. Project Structure

The project is organized as follows:

    src/: Contains all source code files, divided into three main packages:
        logika: Implements the core game logic, such as handling tile movements, merging, and checking for game completion.
        gui: Contains the graphical user interface (GUI) for interacting with the game.
        konzola: Contains the console-based user interface for playing the game in a terminal environment.

    bin/: Compiled .class files.

    doc/: JavaDoc documentation files, generated from the comments in the source code.

    2048_diagram_Armin_Vejzovic.drawio.svg: Diagram illustrating the game flow and structure.

    rezultati.txt: Stores the high scores of players.

    stanje_igre.ser: Serialized file used to save the current state of the game.

    LICENSE: The license file for this project (GPL-3.0).

    .classpath, .project, .settings/: Eclipse-specific project files.

    README.md: The file you are currently reading.

4. Setup Instructions

To run the project locally, follow these steps:

Clone the repository:

bash

    git clone https://github.com/your-username/2048-JavaGame.git

Import the project into Eclipse:

    Open Eclipse IDE.
    Select File -> Import... -> Existing Projects into Workspace.
    Browse to the folder where you cloned the project and select it.

Build and run the project:

    Right-click on the project in the Eclipse Project Explorer.
    Select Run As -> Java Application.
    You will be prompted to choose between two main files:
        Igra2048JavaGui (for GUI interface)
        Igra2048Konzola (for console interface)
  

5. How to Play

  The game can be played either through the GUI or console interface.
  
  5.1 GUI Interface:
  
        Arrow keys:
        (Up, Down, Left, Right) 
      are used to move tiles in the selected direction.
  
  Each time two tiles with the same number collide, they merge into one tile with double the value.
  
  A new tile (either 2 or 4) appears on the board after each move.
  
  The goal is to reach the 2048 tile, but players can continue playing after reaching 2048 to 
  achieve higher scores.
  
  5.2 Console Interface:
  
      Arrow keys:
          "W" - Move Up
          "A" - Move Left
          "S" - Move Down
          "D" - Move Right
          "Q" - End the game
          "B" - Show points
  
  After entering a move, press Enter to execute it.
  
  Each time two tiles with the same value collide, they merge into one tile with double the value (e.g., two 2 tiles become a 4).
  
  After every move, a new tile (either 2 or 4) appears randomly on an empty space on the board.
  
  The current state of the game is displayed in console after every move, showing the updated board and any tile merges.
  
  The goal is to create a tile with the value 2048, but you can continue playing.
  
  Points are calculated based on the merged tiles, and you can check your score anytime by pressing B.

6. Game Features

    4x4 Grid: Standard game grid where tiles are moved and combined.

    Tile Merging: Combine two tiles with the same value to create a larger tile.

    New Tile Generation: After every move, a new tile (either 2 or 4) is randomly generated on an empty tile.

    Save and Load Game State: If game was not ended, when we enter game next time player is asked to continue game or start new game

    Score Tracking: Track the player's score, which increases as tiles merge.

    Best Scores: Store and display the best scores in rezultati.txt. (best 10 scores are stored)

    Endgame Detection: Automatically checks if no more legal moves are possible.



