/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        Stack<MazeCell> s = new Stack<MazeCell>();
        MazeCell finish = maze.getEndCell();
        MazeCell start = maze.getStartCell();
        s.add(finish);
        MazeCell position = finish.getParent();
        while(position != null && position != start)
        {
            s.push(position);
            position = position.getParent();
        }
        s.push(start);
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        while(!s.empty())
        {
            solution.add(s.pop());
        }
        return solution;

        //start at the end and go back to beggining because one parent
        // Should be from start to end cells
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze

        //create the stack
        Stack<MazeCell> visit =  new Stack<MazeCell>();

        MazeCell position = maze.getStartCell();
        visit.push(position);

        while(!visit.isEmpty())
        {
            position.setExplored(true);
            position = visit.pop();

            if(position == maze.getEndCell())
            {
                return getSolution();
            }
            int x = position.getRow();
            int y = position.getCol();
            MazeCell child;

            if(maze.isValidCell(x -1, y))
            {
                child = maze.getCell(x-1, y);
                child.setParent(position);
                position.setExplored(true);
                visit.push(child);
            }
            if(maze.isValidCell(x, y + 1))
            {
                child = maze.getCell(x, y +1);
                child.setParent(position);
                position.setExplored(true);
                visit.push(child);
            }
            if(maze.isValidCell(x, y-1))
            {
                child = maze.getCell(x, y -1);
                child.setParent(position);
                position.setExplored(true);
                visit.push(child);
            }

        }
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        //create a queue
        Queue<MazeCell> visit = new LinkedList<>();

        MazeCell position = maze.getStartCell();
        visit.add(position);
        while(!visit.isEmpty())
        {
            position.setExplored(true);
            position = visit.remove();

            if(position == maze.getEndCell())
            {
                return getSolution();
            }
            int x = position.getRow();
            int y = position.getCol();
            MazeCell child;

            if(maze.isValidCell(x - 1, y))
            {
                child = maze.getCell(x-1, y);
                child.setParent(position);
                position.setExplored(true);
                visit.add(child);
            }
            if(maze.isValidCell(x, y +1))
            {
                child = maze.getCell(x, y + 1);
                child.setParent(position);
                position.setExplored(true);
                visit.add(child);
            }
            if(maze.isValidCell(x - 1, y))
            {
                child = maze.getCell(x+1, y);
                child.setParent(position);
                position.setExplored(true);
                visit.add(child);
            }
            if(maze.isValidCell(x, y - 1))
            {
                child = maze.getCell(x, y - 1);
                child.setParent(position);
                position.setExplored(true);
                visit.add(child);
            }

        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
