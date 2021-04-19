package com.javarush.games.minesweeper;

public class GameObject {
    public int x, y, countMineNeighbors ;
    public boolean isMine;

    //Конструктор
    GameObject (int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }
}
