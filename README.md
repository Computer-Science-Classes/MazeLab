# Maze Generator and Solver

This program generates a maze using a Depth-First Search (DFS) algorithm and solves the maze using the A* algorithm with the Manhattan distance heuristic.

## Table of Contents
- [Introduction](#introduction)
- [Classes](#classes)
  - [Maze](#maze)
  - [Cell](#cell)
    - [EdgeCell](#edgecell)
    - [BlockCell](#blockcell)
    - [EntryCell](#entrycell)
    - [ExitCell](#exitcell)
- [Generation Algorithm](#generation-algorithm)
- [A* Algorithm with Manhattan Distance Heuristic](#a-algorithm-with-manhattan-distance-heuristic)
- [License](#license)

## Introduction

This program allows you to generate a maze using the Depth-First Search (DFS) algorithm and then solve the generated maze using the A* algorithm with the Manhattan distance heuristic.

The program provides a graphical representation of the maze and offers options to visualize the maze generation and solving processes.

## Classes

### Maze

The `Maze` class represents the main structure of the maze and manages the grid of cells. It is responsible for initializing the maze, generating the maze using DFS, solving the maze using A*, and providing methods for accessing and manipulating cells within the maze.

### Cell

The `Cell` class represents a single cell within the maze. It holds information about its position, walls, and other properties. The `Cell` class is the base class for different types of cells within the maze.

#### EdgeCell

The `EdgeCell` class represents cells that are located on the edge of the maze. These cells are used to create the boundary of the maze and are colored differently.

#### BlockCell

The `BlockCell` class represents cells that are blocked or inaccessible within the maze. These cells act as obstacles that the A* algorithm must navigate around to find a path.

#### EntryCell

The `EntryCell` class represents the entry point or start cell of the maze. It is colored differently to indicate the starting position.

#### ExitCell

The `ExitCell` class represents the exit point or goal cell of the maze. It is colored differently to indicate the goal position.

## Generation Algorithm

The maze generation algorithm used in this program is Depth-First Search (DFS). The DFS algorithm starts from a random cell and explores the maze by recursively visiting neighboring cells. It marks visited cells and creates paths between them, gradually forming a maze structure. The algorithm continues until all cells have been visited, resulting in a fully generated maze.

## A* Algorithm with Manhattan Distance Heuristic

The A* algorithm is used to solve the generated maze and find the shortest path from the start cell to the goal cell. The algorithm uses the Manhattan distance heuristic to estimate the cost of reaching the goal from each cell. It maintains a priority queue to explore cells in the order of their estimated total cost, which is the sum of the cost to reach the cell from the start and the estimated cost to reach the goal from the cell. The A* algorithm terminates when the goal cell is reached or there are no more cells to explore.

## License

This program is licensed under the [MIT License](LICENSE).
