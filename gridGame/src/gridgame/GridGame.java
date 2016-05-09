package gridgame;

import java.util.Scanner;

public class GridGame {

    public static int x, y, nx, ny, maxX, maxY, score, counter;
    static String dinput;
    static String direction;
    static Scanner sc = new Scanner(System.in);
    public static boolean game, enemy1, enemy2, enemy3, stun, stun1, stun2, stun3;

    public static void main(String[] args) {
        game = true;
        enemy1 = true;
        enemy2 = true;
        enemy3 = true;
        score = 0;
        maxX = 30;
        maxY = 30;
        counter = 0;
        stun = true;

        String[][] map = new String[maxX][maxY];
        boolean[][] trap = new boolean[maxX][maxY];
        boolean[][] treasure = new boolean[maxX][maxY];
        int[][] enemy = new int[maxX][maxY];

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
        for (int e = 0; e < 2; e++) {
            int enemyx = (int) Math.floor(Math.random() * maxX);
            int enemyy = (int) Math.floor(Math.random() * maxY);
            enemy[e][1] = enemyx;
            enemy[e][0] = enemyy;
        }
        nx = x;
        ny = y;
        System.out.println("Traps: ##, Enemies: XK, Treasure: %%" + "\n" + "Collect 10 points to win.");
        while (game) {
            input(map, trap, treasure, enemy);
            enemies(map, trap, treasure, enemy);
            grid(map, trap, treasure, enemy);
            if (stun) {
                counter++;
            }
            clear(map);
            if (score == 10) {
                System.out.println("YOU WIN! gg wp");
                game = false;
            }
        }
        if (!game && score < 10) {
            System.out.println("game over, your score was " + score);
        }
    } // end of main

    public static void grid(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {

        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (trap[j][i]
                        && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                        && map[i][j] != "\\" && map[i][j] != "//") {
                    map[i][j] = "##";
                }
//                if (enemy[0][0] == j && enemy[0][1] == i
//                        || enemy[1][0] == j && enemy[1][1] == i
//                        || enemy[2][0] == j && enemy[2][1] == i) {
//                    map[i][j] = "XK";
//                }
                if (treasure[j][i]) {
                    map[i][j] = "%%";
                }
                if (trap[j][i] && treasure[j][i]) {
                    trap[j][i] = false;
                }

                if (j < map[1].length - 1) {
                    if (map[i][j] != "██" && map[i][j] != "##" && map[i][j] != "%%" && map[i][j] != "XK"
                            && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                            && map[i][j] != "\\" && map[i][j] != "//" && map[i][j] != "**") {
                        System.out.print("░░");
                    } else {
                        System.out.print(map[i][j]);
                    }

                } else if (map[i][j] != "██" && map[i][j] != "##" && map[i][j] != "%%" && map[i][j] != "XK"
                        && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                        && map[i][j] != "\\" && map[i][j] != "//" && map[i][j] != "**") {
                    System.out.println("░░");
                } else {
                    System.out.println(map[i][j]);
                }
            }
        }

    } //end of grid

    public static void enemies(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        if (enemy[0][0] > x && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "░░";
            enemy[0][0]--;
        }
        if (enemy[0][0] < x && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "░░";
            enemy[0][0]++;
        }
        if (enemy[0][1] > y && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "░░";
            enemy[0][1]--;
        }
        if (enemy[0][1] < y && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "░░";
            enemy[0][1]++;
        }
        if (enemy[1][0] > x && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "░░";
            enemy[1][0]--;
        }
        if (enemy[1][0] < x && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "░░";
            enemy[1][0]++;
        }
        if (enemy[1][1] > y && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "░░";
            enemy[1][1]--;
        }
        if (enemy[1][1] < y && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "░░";
            enemy[1][1]++;
        }
        if (enemy[2][0] > x && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "░░";
            enemy[2][0]--;
        }
        if (enemy[2][0] < x && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "░░";
            enemy[2][0]++;
        }
        if (enemy[2][1] > y && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "░░";
            enemy[2][1]--;
        }
        if (enemy[2][1] < y && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "░░";
            enemy[2][1]++;
        }
        if (map[enemy[0][1]][enemy[0][0]] == "**" && stun) {
            stun1 = true;
        }
        if (map[enemy[1][1]][enemy[1][0]] == "**" && stun) {
            stun2 = true;
        }
        if (map[enemy[2][1]][enemy[2][0]] == "**" && stun) {
            stun3 = true;
        }
        if (enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "XK";
        } else if (!enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "░░";
        }
        if (enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "XK";
        } else if (!enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "░░";
        }
        if (enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "XK";
        } else if (!enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "░░";
        }

        if (enemy[0][1] == y && enemy[0][0] == x && enemy1
                || enemy[1][1] == y && enemy[1][0] == x && enemy2
                || enemy[2][1] == y && enemy[2][0] == x && enemy2) {
            game = false;
        }

        if (trap[enemy[0][1]][enemy[0][0]]) {
            enemy1 = false;
        }
        if (trap[enemy[1][1]][enemy[1][0]]) {
            enemy1 = false;
        }
        if (trap[enemy[2][1]][enemy[2][0]]) {
            enemy1 = false;
        }

    }

    public static void move(String[][] map, int movex, int movey, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        map[y][x] = "░░";
        nx += movex;
        ny += movey;
        if (trap[nx][ny]) {
            game = false;
        }
        if (treasure[nx][ny]) {
            map[nx][ny] = "░░";
            score++;
            treasure[nx][ny] = false;
        }
        x += movex;
        y += movey;
    }

    public static void clear(String[][] map) {
        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (map[i][j] == "^^" || map[i][j] == "vv" || map[i][j] == ">>" || map[i][j] == "<<"
                        || map[i][j] == "\\" || map[i][j] == "//") {
                    map[i][j] = "░░";
                }
                if (map[i][j] == "**" && counter == 3) {
                    map[i][j] = "░░";
                    stun = false;
                    stun1 = false;
                    stun2 = false;
                    stun3 = false;
                }
            }
        }
    }

    public static void input(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        System.out.print("Enter direction (W, A, S, D or diagonals), F to attack, G to stun, your score is " + score + ": ");
        dinput = sc.nextLine();

        if (dinput.equalsIgnoreCase("w") && y != maxX - 1) {
            move(map, 0, -1, trap, treasure, enemy);
            direction = "w";
        } else if (dinput.equalsIgnoreCase("d") && x != maxX - 1) {
            move(map, 1, 0, trap, treasure, enemy);
            direction = "d";
        } else if (dinput.equalsIgnoreCase("s") && y != maxX - 1) {
            move(map, 0, 1, trap, treasure, enemy);
            direction = "s";
        } else if (dinput.equalsIgnoreCase("a") && x != 0) {
            move(map, -1, 0, trap, treasure, enemy);
            direction = "a";
        } else if (dinput.equalsIgnoreCase("wd") && y != 0 && x != maxX - 1 || dinput.equalsIgnoreCase("dw") && y != 0 && x != maxX - 1) {
            move(map, 1, -1, trap, treasure, enemy);
            direction = "wd";
        } else if (dinput.equalsIgnoreCase("wa") && y != 0 && x != 0 || dinput.equalsIgnoreCase("aw") && y != 0 && x != 0) {
            move(map, -1, -1, trap, treasure, enemy);
            direction = "wa";
        } else if (dinput.equalsIgnoreCase("sd") && y != maxX - 1 && x != maxX - 1 || dinput.equalsIgnoreCase("ds") && y != maxX - 1 && x != maxX - 1) {
            move(map, 1, 1, trap, treasure, enemy);
            direction = "sd";
        } else if (dinput.equalsIgnoreCase("sa") && y != maxX - 1 && x != 0 || dinput.equalsIgnoreCase("as") && y != maxX - 1 && x != 0) {
            move(map, -1, 1, trap, treasure, enemy);
            direction = "sa";
        } else if (dinput.equalsIgnoreCase("f") && direction == "w") {
            map[y - 1][x] = "^^";
            trap[y - 1][x] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "s") {
            map[y + 1][x] = "vv";
            trap[y + 1][x] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "d") {
            map[y][x + 1] = ">>";
            trap[y][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "a") {
            map[y][x - 1] = "<<";
            trap[y][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wd") {
            map[y - 1][x + 1] = "//";
            trap[y - 1][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wa") {
            map[y - 1][x - 1] = "\\";
            trap[y - 1][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sd") {
            map[y + 1][x + 1] = "\\";
            trap[y + 1][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sa") {
            map[y + 1][x - 1] = "//";
            trap[y + 1][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("g")) {
//            stun = true;
            counter = 0;
            map[y - 1][x] = "**";
            map[y + 1][x] = "**";
            map[y][x + 1] = "**";
            map[y][x - 1] = "**";
            map[y - 1][x + 1] = "**";
            map[y - 1][x - 1] = "**";
            map[y + 1][x + 1] = "**";
            map[y + 1][x - 1] = "**";
        } else {
            System.out.println("Sorry, wrong input or reached map edge");
            input(map, trap, treasure, enemy);
        }
        if (game) {
            map[y][x] = "██";
        } else {
            System.out.println("Game over");
        }
    } //end of input
}
