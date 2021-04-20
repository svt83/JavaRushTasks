package com.javarush.games.minesweeper;

public class GameObject {
    public int x, y, countMineNeighbors = 0;
    public boolean isMine, isOpen, isFlag;

    //Constructor of cell
    GameObject (int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }
}
