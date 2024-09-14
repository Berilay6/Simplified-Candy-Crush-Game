# Introduction
This is a simplified candy crush game in Java. This game includes a grid of colored cells, where
players match three cells of the same color to clear them from the board.

##Game Loop
• Check for and clear any matches.
• If no moves are possible, reshuffle the board.
• Accept player input for cell selection and direction of swap.
• Process the swap and update the board.

##Scoring 

For mode 1, the player gains 15 scores per match.

For mode 2, the player gains 10 scores for the matching color per match.

Players can receive bonus points for moving upper cells to the lower positions on the board. Specifically, if
a player’s move causes cells to fall down and fill cleared spaces, they should
receive an additional 15 points for each such move. This adds an extra layer of
strategy to the game, encouraging players to plan moves that cause cascades of
matches.


##Game Modes
• Mode 1: Collect 200 points in 15 moves.
• Mode 2: Collect 100 points for each color in 20 moves.
The game ends when the move count reaches zero, and the program
prints the collected points and remaining moves after each move.

#Play Example

##Mode 1:
Welcome to the game!
Enter ’s’ to start the game or ’q’ to quit:
s
You have to collect 200 points in 15 moves!
Good luck!
Enter the size of the matrix:
4 5
You have 15 moves to reach the goal!
|B|R|G|B|B|
|R|G|R|R|B|
|B|B|G|G|R|
|B|R|B|G|R|
Enter the cell:
1 1
Enter the direction:
down
Invalid move!
|B|R|G|B|B|
|R|G|R|R|B|
|B|B|G|G|R|
|B|R|B|G|R|
Enter the cell:
2 4
Enter the direction:
right
Clearing Board:
|B|R|G|B|B|
|R|G|R|B|R|
|B|B|G|G|R|
|B|R|B|G|R|
--------------
|B|R|G|B|G|
|R|G|R|B|R|
|B|B|G|G|G| // seen another match
|B|R|B|G|B|
--------------
|B|R|B|R|G| // new generated colors
|R|G|G|B|G|
|B|B|R|B|R|
|B|R|B|G|B|
You have collected 20 points and you have 14 moves left!

##Mode 2:
Welcome to the game!
Enter ’s’ to start the game or ’q’ to quit:
s
You have to collect 100 points for each color in 20 moves!
Good luck!
Enter the size of the matrix:
4 5
You have 20 moves to reach the goal!
|B|R|G|B|B|
|R|G|R|R|B|
|B|B|G|G|R|
|B|R|B|G|R|
Enter the cell:
1 1
Enter the direction:
down
Invalid move!
|B|R|G|B|B|
|R|G|R|R|B|
|B|B|G|G|R|
|B|R|B|G|R|
Enter the cell:
2 4
Enter the direction:
right
Clearing Board:
|B|R|G|B|B|
|R|G|R|B|R|
|B|B|G|G|R|
|B|R|B|G|R|
--------------
|B|R|G|B|G|
|R|G|R|B|R|
|B|B|G|G|G| // seen another match
|B|R|B|G|B|
--------------
|B|R|B|R|G| // new generated colors
|R|G|G|B|G|
|B|B|R|B|R|
|B|R|B|G|B|
You have collected 10 points for red, 10 points for green, 0 points for blue and
you have 19 moves left!
This process continues until all the moves are played or the player completes
the mission. At the end, the program should announce whether the player won
the game or not.


