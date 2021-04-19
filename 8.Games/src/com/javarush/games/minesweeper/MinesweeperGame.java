package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private String mines;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        setCellValue(x, y, String.valueOf(gameField[y][x].countMineNeighbors));
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        if (gameField[y][x].isMine)
            setCellValueEx(x, y, Color.ORANGE,"mine", Color.BLACK, 20); //show mine
        else setCellValueEx(x, y, Color.ORANGE, "");
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                    setCellValueEx(x, y, Color.ORANGE,"mine", Color.BLACK, 20); //show mine
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countMineNeighbors();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();

        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                //if (gameField[y][x].isMine) {
                    for ( GameObject Field: getNeighbors(gameField[y][x]) ) {
                        if (Field.isMine) Field.countMineNeighbors++;
                    }

                    System.out.println("Cell " + y + "-" + x + " " + gameField[y][x].isMine + " " + gameField[y][x].countMineNeighbors);
                    //setCellValueEx(x, y, Color.BLUE, String.valueOf(gameField[y][x].countMineNeighbors), Color.ORANGE, 50);
                //}
            }
        }
    }

}