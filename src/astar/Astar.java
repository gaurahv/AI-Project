/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Gaurav Agarwal
 */
public class Astar {

    private State source, goal;
    long counter = 0;
    Maze m;
    
    Astar(Maze m, State source, State goal) {
        this.m = m;
        this.source = source;
        this.goal = goal;
    }

    ArrayList<State> getNextStates(State u) {
        ArrayList<State> list = new ArrayList<State>();

        int x = u.getX();
        int y = u.getY();

        if (m.n != y) {
            if (!m.north[x][y]) {
                list.add(new State(x, y + 1, u, goal));
            }
        }
        if (x != 0) {
            if (!m.west[x][y]) {
                list.add(new State(x - 1, y, u, goal));
            }
        }
        if (y != 0) {
            if (!m.south[x][y]) {
                list.add(new State(x, y - 1, u, goal));
            }
        }
        if (m.n != x) {
            if (!m.east[x][y]) {
                list.add(new State(x + 1, y, u, goal));
            }
        }

        return list;
    }

    static boolean relax(State u, State v) {
        if (v.getCn() > u.getCn() + State.cost(u, v)) {
            v.updateParent(u);
            return true;
        }
        return false;
    }

    public boolean isGoal(State g) {
        return goal.equals(g);
    }

    public State computePath() {
        int a,b,c,d;
        a = b = c = d = 0;
        long start = System.currentTimeMillis();
        PriorityQueue<State> current = new PriorityQueue<State>(new Comparator<State>() {
            @Override
            public int compare(State a, State b) {
                if (a.getFn() < b.getFn()) {
                    return -1;
                } else if (a.getFn() > b.getFn()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        HashSet<State> closed = new HashSet<State>();
        current.offer(source);

        while (!current.isEmpty()) {            
            State u = current.poll();
            if (isGoal(u)) {
                long end = System.currentTimeMillis();
                System.out.println();
                System.out.println("Summary of A* Search");
                System.out.println("Total states explored :: "  + closed.size());
                System.out.println("Total states in current list to be explored :: " + current.size());
                System.out.println("Total Time Taken     :: " + (end-start) + " milliseconds.");
                int pathLength = 0;
                while (u != null) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledCircle(u.getX() + 0.5, u.getY() + 0.5, 0.20);
                    StdDraw.show();
//                    StdDraw.pause(30);
                    u = u.getParent();
                    pathLength++;
                }
                System.out.println("Total Path Length  :: " + pathLength);
                return goal;
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.filledCircle(u.getX() + 0.5,u.getY() + 0.5, 0.20);
                StdDraw.show();
//                StdDraw.pause(20);
                
                if(closed.size()%(200)==0){
                    System.out.println("Explored " + closed.size() + " nodes.");
                }
                ArrayList<State> list = getNextStates(u);
                for (State v : list) {
                    if (closed.contains(v)) {
                        a++;
                        if (relax(u, v)) {
                            LinkedList<State> newlist = new LinkedList<State>();
                            newlist.offer(v);

                            while (!newlist.isEmpty()) {
                                State x = newlist.poll();
                                ArrayList<State> temp = getNextStates(x);
                                for (State y : temp) {
                                   b++;
                                    if (closed.contains(y)){
                                        newlist.offer(y);
                                        relax(x, y);
                                    }else if(current.contains(y)) {
                                        relax(x, y);
                                    }
                                }
                            }
                        }
                    } else if (current.contains(v)) {
                        c++;
                        relax(u, v);
                    } else {
                        d++;
                        current.offer(v);
                    }
                }
            }
            closed.add(u);
        }
        return null;
    }
}
