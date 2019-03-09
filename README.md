# Animal-Game
## Introduction
This app implements a game similar to the [20 Questions](https://github.com/devansh287/20-questions-game) one, but on an Android platform instead of C++. Note: the questions will not load on the screen as I have not provided my username and password to the code. I am just showing this to demonstrate that I have experience working in Firebase. Moreover, I have just uploaded the code right now. The entire app 
was too heavy to upload.
## Objective (Taken from assignment instructions)
We will write a basic quiz game app named Animal Game, based on a similar game located on the web at animalgame.com. In this game, you, the human player, are supposed to think of any animal you like. The computer will try to guess what animal you are thinking of by asking you a series of Yes-or-No questions. Eventually the computer will have asked enough questions that it thinks it knows what object you are thinking of, and it will make a final guess about what your animal is. If this guess is correct, the computer wins; if not, you win.

## Data Storage
The data is stored in a Firebase database on my google account.

## Functions
Main activities and their functions:
1. [MainActivity](https://github.com/devansh287/Animal-Game/blob/master/MainActivity.kt): shows the app name, logo, and has buttons for the user to start playing or modify settings. When the user presses the Play button, it launches the PlayGameActivity.
2. [PlayGameActivity](https://github.com/devansh287/Animal-Game/blob/master/PlayGameActivity.kt): Lets the user play the animal game. The game asks the user Yes/No questions, and the user can press the large Y and N buttons to indicate his/her answer. This will cause the game to ask a follow-up question repeatedly until the game is ready to guess the player's animal.
3. [LoseGameActivity](https://github.com/devansh287/Animal-Game/blob/master/LoseGameActivity.kt): If the app loses, the player is directed to this activity. The user updates the database with the correcting question
and returns to main activity.
4. [WinGameActivity](https://github.com/devansh287/Animal-Game/blob/master/WinGame.kt): If the app wins, this is a victory page. Then the user can return to main activity.
