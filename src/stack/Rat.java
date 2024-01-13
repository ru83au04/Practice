package stack;

import java.util.Arrays;

public class Rat {
    private int[][] path;
    private int[][] maze;
    boolean[][] mark;
    private int[] entrance, exit;
    private int[][] direction = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };
    private int front = 0;

    public Rat(int[][] maze, int[] entrance, int[] exit){
        this.maze = maze;
        this.mark = new boolean[maze.length][maze[0].length];
        this.entrance = entrance;
        this.exit = exit;
        this.path = new int[1000][3];
    }

    public int getFront(){
        return front;
    }

    public int[] move(int[] position, int dir){
        int row = position[0] + direction[dir][0];
        int column = position[1] + direction[dir][1];
        int[] newPosition = {row, column, dir};
        return newPosition;
    }

    public int[][] getExit(){
        int d = 0;
        path[0] = entrance;
        for(int i = 0; i < mark.length; i++){
            for(int j = 0; j < mark[i].length; j++){
                mark[i][j] = false;
            }
        }
        int[] temp;
        while(path != null){
            temp = move(path[front], d);
            System.out.printf("(%d), (%d, %d)\n", maze[temp[0]][temp[1]], temp[0], temp[1]);
            if(maze[temp[0]][temp[1]] == 0 && !mark[temp[0]][temp[1]]){
                path[++front] = temp;
                System.out.printf("(%d),(%d, %d)\n".formatted(path[front][2], path[front][0], path[front][1]));
                d = 0;
            }
            else{
                if(d < direction.length){
                    d++;
                    System.out.println("test");
                }
                else if(front != 0){
                    temp = path[--front];
                    d = path[front][2];
                    System.out.println("back");
                }
                else{
                    System.out.println("Can't out");
                    return path;
                }
            }
            if(path[front][0] == exit[0] && path[front][1] == exit[1]){
                System.out.println("I'm out");
                break;
            }
        }
        return path;
    }
}
