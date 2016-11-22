/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;
/**
 *
 * @author Gaurav Agarwal
 */
public class Test {

    public static void main(String[] args) {

        int n = 140;
        Maze maze = new Maze(n);
        StdDraw.enableDoubleBuffering();
        maze.draw();
        
        State goal = new State((int) (Math.random()*n), (int) (Math.random()*n));
        State source = new State(1, 1, goal);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(goal.getX() + 0.5, goal.getY() + 0.5, 0.25);
        StdDraw.show();
        Astar a = new Astar(maze, source, goal);
        State s = a.computePath();
        if(s==null){
            System.out.println("Sorry Goal cannot be reached");
        }
    }
    
}
