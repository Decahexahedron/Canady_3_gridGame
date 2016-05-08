package gridgame;

import java.util.Scanner;

public class GridGame {

    public static int x, y, nx, ny, maxX, maxY, score;

    static String dinput;
    static Scanner sc = new Scanner(System.in);
    public static boolean game;

    public static void main(String[] args) {
        game = true;
        score = 0;
        maxX = 30;
        maxY = 30;
        String[][] map = new String[maxX][maxY];
        boolean[][] trap = new boolean[maxX][maxY];
        boolean[][] wall = new boolean[maxX][maxY];
        boolean[][] treasure = new boolean[maxX][maxY];
        x = 4;
        y = 4;

        if (trap[y][x] == true) {
            x = (int) Math.floor(Math.random() * maxX);
            y = (int) Math.floor(Math.random() * maxY);
        }
        for (int t = 0; t < 50; t++) {
            int trapx = (int) Math.floor(Math.random() * maxX);
            int trapy = (int) Math.floor(Math.random() * maxY);
            trap[trapx][trapy] = true;
        }
        for (int t = 0; t < 11; t++) {
            int treasurex = (int) Math.floor(Math.random() * maxX);
            int treasurey = (int) Math.floor(Math.random() * maxY);
            treasure[treasurex][treasurey] = true;
        }
        
        nx = x;
        ny = y;
        System.out.println("Traps: ##, Enemies: XK, Treasure: %%" + "\n" + "Collect 10 points to win.");
        while (game) {
            input(map, trap, wall, treasure);
            grid(map, trap, wall, treasure);
            if(score == 10){
                System.out.println("YOU WIN! gg wp");
                game = false;
            }
        }
        if(!game && score < 10){
            System.out.println("game over, your score was " + score);
        }
    } // end of main

    public static void grid(String[][] map, boolean[][] trap, boolean[][] wall, boolean[][] treasure) {

        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (trap[j][i]) {
                    map[i][j] = "##";
                }
                if (wall[j][i]) {
                    map[i][j] = "▒▒";
                }
                if (treasure[j][i]) {
                    map[i][j] = "%%";
                }
                if(trap[j][i] && treasure[j][i]){
                    trap[j][i] = false;
                }
                if (j < map[1].length - 1) {
                    if (map[i][j] != "██" && map[i][j] != "##" && map[i][j] != "▒▒" && map[i][j] != "%%" && map[i][j] != "XK") {
                        System.out.print("░░");
                    } else {
                        System.out.print(map[i][j]);
                    }

                } else {
                    if (map[i][j] != "██" && map[i][j] != "##") {
                        System.out.println("░░");
                    } else {
                        System.out.println(map[i][j]);
                    }

                }
            }
        }

    } //end of grid

    public static void move(String[][] map, int movex, int movey, boolean[][] trap, boolean[][] wall, boolean[][] treasure) {
        map[y][x] = "░░";
        nx += movex;
        ny += movey;
        if (trap[nx][ny]) {
            game = false;
        }
        if (wall[nx][ny]) {
            nx -= movex;
            ny -= movey;
            movex = 0;
            movey = 0;
        }
        if(treasure[nx][ny]){
            map[nx][ny] = "░░";
            score++;
            treasure[nx][ny] = false;
        }
        x += movex;
        y += movey;
    }

    public static void input(String[][] map, boolean[][] trap, boolean[][] wall, boolean[][] treasure) {
        System.out.print("Enter direction (W, A, S, D or diagonals), your score is " + score + ": ");
        dinput = sc.nextLine();

        if (dinput.equalsIgnoreCase("w") && y != maxX - 1) {
            move(map, 0, -1, trap, wall, treasure);
        } else if (dinput.equalsIgnoreCase("d") && x != maxX - 1) {
            move(map, 1, 0, trap, wall, treasure);
        } else if (dinput.equalsIgnoreCase("s") && y != maxX - 1) {
            move(map, 0, 1, trap, wall, treasure);
        } else if (dinput.equalsIgnoreCase("a") && x != 0) {
            move(map, -1, 0, trap, wall, treasure);
        } else if (dinput.equalsIgnoreCase("wd") && y != 0 && x != maxX - 1 || dinput.equalsIgnoreCase("dw") && y != 0 && x != maxX - 1) {
            move(map, 1, -1, trap, wall, treasure);
        } else if (dinput.equalsIgnoreCase("wa") && y != 0 && x != 0 || dinput.equalsIgnoreCase("aw") && y != 0 && x != 0) {
            move(map, -1, -1, trap, wall, treasure);
        } else if (dinput.equalsIgnoreCase("sd") && y != maxX - 1 && x != maxX - 1 || dinput.equalsIgnoreCase("ds") && y != maxX - 1 && x != maxX - 1) {
            move(map, 1, 1, trap, wall, treasure);
        } else if (dinput.equalsIgnoreCase("sa") && y != maxX - 1 && x != 0 || dinput.equalsIgnoreCase("as") && y != maxX - 1 && x != 0) {
            move(map, -1, 1, trap, wall, treasure);
        } else {
            System.out.println("Sorry, wrong input or reached map edge");
            input(map, trap, wall, treasure);
        }
        if (game) {
            map[y][x] = "██";
        } else {
            System.out.println("Game over");
        }
    } //end of input
}