# Chess Puzzle Game

Welcome to the Chess Puzzle Game! This game involves maneuvering chess pieces on a small board to achieve a specific goal configuration.

## Game Description

Consider a game board consisting of 2 rows and 3 columns, with the following set of white chess pieces: a king (K), two bishops (B), and two rooks (R). Initially, the pieces are arranged on the board as follows:

|   |   |   |
|---|---|---|
| K | B | B |
| R | R |   |

In a move, one of the pieces must be moved to the empty square. Each piece can move according to the standard rules of chess:

- **King (K)**: Moves one square in any direction.
- **Bishops (B)**: Move diagonally any number of squares.
- **Rooks (R)**: Move horizontally or vertically any number of squares.

The goal of the game is to transform the initial board configuration into the following configuration:

|   |   |   |
|---|---|---|
| B | B |   |
| R | R | K |


## Solution

The solution to the game involves a sequence of moves that will rearrange the pieces into the goal configuration. Here is the solution in terms of moves:

1. Move `B` from `(0, 1)` to `(1, 2)`
2. Move `K` from `(0, 0)` to `(0, 1)`
3. Move `R` from `(1, 0)` to `(0, 0)`
4. Move `R` from `(1, 1)` to `(1, 0)`
5. Move `B` from `(0, 2)` to `(1, 1)`
6. Move `K` from `(0, 1)` to `(0, 2)`
7. Move `R` from `(0, 0)` to `(0, 1)`
8. Move `B` from `(1, 1)` to `(0, 0)`
9. Move `R` from `(0, 1)` to `(1, 1)`
10. Move `B` from `(1, 2)` to `(0, 1)`
11. Move `K` from `(0, 2)` to `(1, 2)`

Congratulations! You have successfully solved the puzzle.

## Getting Started

To play the game, follow these steps:

1. Clone this repository.
2. Open the game in your preferred development environment.
3. Implement the game logic to perform the moves and achieve the goal configuration.

Enjoy the challenge and have fun solving the Chess Puzzle Game!

   

