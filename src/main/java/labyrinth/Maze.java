package labyrinth;

import java.util.ArrayList;
import java.util.List;

/**
 * User: vadya
 * Date: 10.11.13
 * Time: 16:25
 */
public class Maze {
    //Высота и ширина лабиринта
    private int h;
    private int w;
    //Массив лабиринта. True - проход, false - стена
    private boolean [][] maze;
    //Матрица проверенных/недопустимых элементов (false)
    private boolean [][] checkedMaze;
    private boolean [][] solveMatrix;
    private int startH = 1;
    private int startW = 0;
    public List<Integer> path = new ArrayList<Integer>();



    private Agent agent;

    private static boolean[][] intMazeToBool(int[][] maze) {
        boolean[][] boolMaze = new boolean[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 0) {
                    boolMaze[i][j] = false;
                } else
                    boolMaze[i][j] = true;
            }
        }
        return boolMaze;
    }

    public Maze(boolean [][] maze) {
        this.maze = maze;
        this.checkedMaze = maze.clone();
//        checkedMaze = new boolean[maze.length][maze[0].length];
//        System.arraycopy(maze, 0, checkedMaze, 0, maze.length);
        this.h = maze.length;
        this.w = maze[0].length;
        System.out.println("Created maze:");
        printMaze(this.maze);
        solveMatrix = new boolean[h][w];
        setSolved(startH, startW);
        agent = new Agent(startH, startW, 0, this);
        path.add(0);
        printMaze(maze);
    }

    public Maze (int [][] maze) {
        this(intMazeToBool(maze));
    }
    public void setChecked (int h, int w) {
        checkedMaze[h][w] = false;
        System.out.println("Set checked at " + h + "x" + w);
//        printMaze(checkedMaze);
        printMaze(maze);
    }

    public void setSolved (int h, int w) {
        solveMatrix[h][w] = true;
    }

    public boolean ifSolved (int h, int w) {
        return solveMatrix[h][w];
    }

    //True если выходит за рамки матрицы лабиринта
    private boolean ifOutside (int h, int w) {
        if (h > this.h - 1 || w > this.w - 1 || h < 0 || w < 0)
            return true; else
            return false;
    }

    private boolean ifChecked (int h, int w) {
        return !checkedMaze[h][w];
    }

    public boolean mazeValue (int h, int w) {
        return maze[h][w];
    }

    public boolean ifAvailable (int h, int w) {
        if (ifOutside(h, w))
            return false; else
        return (!ifChecked(h, w));
    }

    public boolean ifExit (int h, int w) {
        return (h == (this.h - 1) || w == (this.w - 1));

    }

    private List<Integer> getSolvePath () {
        return this.path;
    }

    private void printMaze (boolean[][] maze) {
        for (int i = 0; i < this.h; i++) {
            for (int j = 0; j < this.w; j++) {
                if (maze[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }




}

