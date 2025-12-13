package com.mayin_tarlasi;

public class Cell {
private boolean isMine;
private boolean isOpened;
private int adjacentMineCount;
private boolean isFlagged;
public Cell() {
    this.isMine = false;
    this.isOpened = false;
    this.adjacentMineCount = 0;
    this.isFlagged = false;
}
public void setOpened(boolean b) { this.isOpened = b; }
public void OpenCell(){
    if(!isFlagged && !isOpened){
        this.isOpened=true;
    }
}
public void toggleFlag() {
        if (!isOpened) {
            this.isFlagged = !this.isFlagged;
        }
    }

public boolean isMine() {
    return isMine;
}
public void setMine(boolean isMine) {
    this.isMine = isMine;
}
public boolean isOpened() {
    return isOpened;
}

public int getAdjacentMineCount() {
    return adjacentMineCount;
}
public void setAdjacentMineCount(int count) {
    this.adjacentMineCount = count;
}
public boolean isFlagged() {
    return isFlagged;
}




}
