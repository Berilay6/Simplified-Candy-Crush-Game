# Introduction
This is a simplified candy crush game in Java. This game includes a grid of colored cells, where
players match three cells of the same color to clear them from the board.

## Game Loop
• Check for and clear any matches.
• If no moves are possible, reshuffle the board.
• Accept player input for cell selection and direction of swap.
• Process the swap and update the board.

## Scoring 

For mode 1, the player gains 15 scores per match.

For mode 2, the player gains 10 scores for the matching color per match.

Players can receive bonus points for moving upper cells to the lower positions on the board. Specifically, if
a player’s move causes cells to fall down and fill cleared spaces, they should
receive an additional 15 points for each such move. This adds an extra layer of
strategy to the game, encouraging players to plan moves that cause cascades of
matches.


## Game Modes
• Mode 1: Collect 200 points in 15 moves.
• Mode 2: Collect 100 points for each color in 20 moves.
The game ends when the move count reaches zero, and the program
prints the collected points and remaining moves after each move.

Have Fun!! :)



