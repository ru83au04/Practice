package stack;

/**
 * 老鼠走迷宮
 * 目前只能跑出單一路線
 * 需要再處理遇岔路回溯的狀況
 */

public class Rat {
    private int[][] path;       // 走過的路徑
    private int[][] maze;       // 迷宮的路線
    boolean[][] mark;           // 判斷迷宮中的指定位置是否走過
    private int[] entrance, exit;       // 迷宮出入口的位置
    private int[][] direction = {       // 老鼠移動方向的選擇，要以二為陣列的索引為位置改變的方向
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

    /**
     * 老鼠移動至下一個位置
     * @param position
     * @param dir
     * @return 移動後的位置以int[]回傳
     */
    public int[] move(int[] position, int dir){
        int row = position[0] + direction[dir][0];
        int column = position[1] + direction[dir][1];
        int[] newPosition = {row, column, dir};
        return newPosition;
    }

    /**
     *
     * @return
     */
    public int[][] getExit(){
        int d = 0;          // 用來設定行走方向
        path[0] = entrance;     // 老鼠路徑的第一個點為入口
        for(int i = 0; i < mark.length; i++){           // 初始化mark陣列，標示所有位置都未走過
            for(int j = 0; j < mark[i].length; j++){
                mark[i][j] = false;
            }
        }
        int[] temp;
        while(path != null){
            temp = move(path[front], d);                // 老鼠欲移動的方向，方向為從上開始順時針依序測試
//            System.out.printf("(%d), (%d, %d)\n", maze[temp[0]][temp[1]], temp[0], temp[1]);
            if(maze[temp[0]][temp[1]] == 0 && !mark[temp[0]][temp[1]]){         // 檢查欲移動的位置是否可走及是否走過
                path[++front] = temp;           // 若可以移動，則移動至該位置
//                System.out.printf("(%d),(%d, %d)\n".formatted(path[front][2], path[front][0], path[front][1]));
                d = 0;          // 初始化行走方向
            }
            else{           // 若欲行走方向不可行
                if(d < direction.length){           // 還未測試完所有方向則繼續測試
                    d++;
//                    System.out.println("test");
                }
                else if(front != 0){            // 如果所有方向都測試過皆無法移動，則退回前一個位置
                    temp = path[--front];
                    d = path[front][2];         // 退回前一個位置，接續測試還未走過的方向
//                    System.out.println("back");
                }
                else{                           // 已經測試完所有路徑及方向皆無法達到出口
//                    System.out.println("Can't out");
                    return path;
                }
            }
            if(path[front][0] == exit[0] && path[front][1] == exit[1]){         // 老鼠的位置與出口吻合
//                System.out.println("I'm out");
                break;
            }
        }
        return path;
    }
}
