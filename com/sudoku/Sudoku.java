package com.sudoku;

import java.util.*;

public class Sudoku {

    // ===================== CONSTANTS =====================
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[37m";

    // ===================== GAME STATE VARIABLES =====================
    private int[][] originalBoard;
    private int[][] currentBoard;
    private int[][] solvedBoard;
    private boolean[][] hintCells;
    private int points;
    private int difficulty;

    private Scanner scanner = new Scanner(System.in);

    // ===================== GENERATION =====================
    public int[][] generateSudoku(int difficulty) {
        int[][] board = new int[9][9];
        fillBoardRandom(board);
        solvedBoard = new int[9][9];
        copyBoard(board, solvedBoard);
        removeCells(board, difficulty);
        originalBoard = new int[9][9];
        copyBoard(board, originalBoard);
        currentBoard = new int[9][9];
        copyBoard(board, currentBoard);
        hintCells = new boolean[9][9];
        return board;
    }

    private boolean fillBoardRandom(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    int[] nums = getShuffledNumbers();
                    for (int num : nums) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (fillBoardRandom(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private int[] getShuffledNumbers() {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<Integer> list = new ArrayList<>();
        for (int n : nums) list.add(n);
        Collections.shuffle(list);
        for (int i = 0; i < 9; i++) nums[i] = list.get(i);
        return nums;
    }

    public void removeCells(int[][] board, int difficulty) {
        int cellsToRemove;
        if (difficulty == 1) cellsToRemove = 35;
        else if (difficulty == 2) cellsToRemove = 45;
        else cellsToRemove = 55;

        Random rand = new Random();
        int removed = 0;
        while (removed < cellsToRemove) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                removed++;
            }
        }
    }

    // ===================== SOLVER =====================
    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }
        int startRow = row / 3 * 3, startCol = col / 3 * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }
        return true;
    }

    // ===================== BOARD PRINTING =====================
    public void printBoard(int[][] currentBoard, int[][] originalBoard, boolean[][] hintCells) {
        System.out.println("\n    1 2 3  4 5 6  7 8 9");
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0) System.out.println("  +-------+------+------+");
            System.out.print((row + 1) + " | ");
            for (int col = 0; col < 9; col++) {
                int num = currentBoard[row][col];
                String color = WHITE;
                if (originalBoard[row][col] != 0) color = RED;
                else if (hintCells[row][col]) color = YELLOW;
                else if (num != 0 && num == solvedBoard[row][col]) color = GREEN;
                String text = (num == 0) ? "." : String.valueOf(num);
                System.out.print(colorText(text, color) + " ");
                if ((col + 1) % 3 == 0) System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("  +-------+------+------+");
        System.out.println("Points: " + points);
    }

    // ===================== GAME CONTROL =====================
    public void startGame() {
        System.out.print("Select difficulty (1 = Easy, 2 = Medium, 3 = Hard): ");
        difficulty = scanner.nextInt();
        points = 0;
        generateSudoku(difficulty);
        solveSudoku(solvedBoard);
        handleUserInput();
    }

    public void handleUserInput() {
        while (true) {
            clearScreen();
            printBoard(currentBoard, originalBoard, hintCells);
            if (Arrays.deepEquals(currentBoard, solvedBoard)) {
                System.out.println(GREEN + "Congratulations! You solved it!" + RESET);
                break;
            }
            System.out.println("Enter row col num (0 for hint, -1 to quit): ");
            int row = scanner.nextInt();
            if (row == -1) break;
            if (row == 0) {
                showHint();
                continue;
            }
            int col = scanner.nextInt();
            int num = scanner.nextInt();
            if (checkMove(row - 1, col - 1, num)) {
                points += 10;
            } else {
                points -= 5;
            }
        }
    }

    public boolean checkMove(int row, int col, int num) {
        if (originalBoard[row][col] != 0) return false;
        if (solvedBoard[row][col] == num) {
            currentBoard[row][col] = num;
            return true;
        }
        return false;
    }

    public void showHint() {
        Random rand = new Random();
        while (true) {
            int r = rand.nextInt(9);
            int c = rand.nextInt(9);
            if (currentBoard[r][c] == 0) {
                currentBoard[r][c] = solvedBoard[r][c];
                hintCells[r][c] = true;
                points -= 15;
                break;
            }
        }
    }

    public void showSolution() {
        clearScreen();
        printBoard(solvedBoard, originalBoard, hintCells);
    }

    public void restartGame() {
        startGame();
    }

    // ===================== UTILITIES =====================
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void copyBoard(int[][] source, int[][] destination) {
        for (int i = 0; i < source.length; i++) {
            System.arraycopy(source[i], 0, destination[i], 0, source[i].length);
        }
    }

    public String colorText(String text, String colorCode) {
        return colorCode + text + RESET;
    }
}
