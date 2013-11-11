package labyrinth;

import java.util.List;

/**
 * User: vadya
 * Date: 10.11.13
 * Time: 15:27
 */
public class Agent {
    //Координаты агента
    private int h, w;
    private boolean solvePart = false;
    private Maze maze;
    public Agent (int h, int w, int way, Maze maze) {
        this.h = h;
        this.w = w;
        this.maze = maze;
        maze.setChecked(h, w);
        createNeighbours();
//        if (solvePart)
//            this.path.add(way);
    }
    private void createNeighbours () {
        //Если это не выход
        if (!maze.ifExit(h, w)) {
            //Сосед справа
            createNeighbour(h, w + 1, 0);
            //Сосед снизу
            createNeighbour(h + 1, w, 1);
            //Сосед слева
            createNeighbour(h, w - 1, 2);
            //Сосед сверху
            createNeighbour(h - 1, w, 3);
        } else {
            System.out.println("Found the exit!");
            this.solvePart = true;
        }
    }
    private void createNeighbour (int h, int w, int way) {
        if (maze.ifAvailable(h, w)) {
            System.out.println("Creating neighbour at " + h + "x" + w);
            Agent agent = new Agent(h, w, way, this.maze);
            if (agent.solvePart) {
                this.solvePart = true;
                agent.maze.path.add(way);
                maze.setSolved(h, w);
            }
        }
    }



}
