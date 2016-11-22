/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Gaurav Agarwal
 */
public class State {

    private int x, y;
    private State parent;
    private double fn, cn, hn;
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public double getFn() {
        return fn;
    }

    public void setFn(double fn) {
        this.fn = fn;
    }

    public double getCn() {
        return cn;
    }

    public void setCn(double cn) {
        this.cn = cn;
    }

    public double getHn() {
        return hn;
    }

    public void setHn(double hn) {
        this.hn = hn;
    }

    State(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    State(int x, int y, State goal) {
        this.x = x;
        this.y = y;
       
        cn = 0;
        hn = cost(goal, this);
        fn = cn + hn;        
    }

    State(int x, int y, State parent, State goal) {
        this.x = x;
        this.y = y;
        
        this.parent = parent;
        cn = parent.getCn() + cost(parent, this);
        hn = cost(goal, this);
        fn = cn + hn;
    }

    public void updateParent(State parent) {
        cn = parent.getCn() + cost(parent, this);
        fn = cn + hn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final State other = (State) obj;
        return ((x==other.x) && (y==other.y));
    }

    @Override
    public int hashCode() {
        return (x+y); //To change body of generated methods, choose Tools | Templates.
    }

    public static double cost(State a, State b) {
//        if(ch==1) return (Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2)));
//        if(ch==2) 
            return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
//        return 0.0;
    }

    @Override
    public String toString() {
        return (x + " " + y + " " + fn); //To change body of generated methods, choose Tools | Templates.
    }
   
}
