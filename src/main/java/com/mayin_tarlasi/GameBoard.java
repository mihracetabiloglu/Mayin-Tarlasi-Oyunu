package com.mayin_tarlasi;

import java.util.Random;


public class GameBoard {
private Cell [][] grid;
private int numRows;
private int numCols;
private int numMines;
public boolean isGameOver;
public GameBoard(DifficultyLevel level){
    this.numRows=level.getRows();
    this.numCols=level.getCols();
    this.numMines=level.getMines();
    this.isGameOver=false;
    this.grid = new Cell[numRows][numCols]; // Depoyu (Rafı) kuruyoruz
    // Hücreleri oluşturuyoruz
    for(int i=0; i<numRows;i++){
        // Her satır için
        for(int j=0; j<numCols;j++){
            // Her sütun için
            this.grid[i][j]=new Cell();
            // Hücre oluşturuldu
        }
}
plantMines(); // Mayınları yerleştir
calculeteAdjacentNumber(); // Komşu mayın sayılarını hesapla
}

public void plantMines(){
    Random random =new Random();
   int minesPlacedCount = 0;
// Hedef sayıya (örn: 10) ulaşana kadar dön
   while(minesPlacedCount<this.numMines){
   int randomRow = random.nextInt(numRows);
   int randomCol = random.nextInt(numCols);

   Cell square = this.grid[randomRow][randomCol];
   // Eğer orada mayın YOKSA koy. (Varsa pas geç, döngü tekrar döner)
   if(!square.isMine()){
      square.setMine(true);
      minesPlacedCount++; // Mayın sayısını artır
   }
   }
}

private void calculeteAdjacentNumber(){
    // Tüm kareleri gez
  for(int i=0; i<this.numRows;i++){
    for(int j=0; j<this.numCols;j++){
     Cell square = this.grid[i][j];

     if(!square.isMine()){ // Sadece mayın olmayanlar için
        int mineCount = 0;
        for(int dr=-1; dr<=1 ; dr++){ // Etrafındaki 3x3 alanı tara
         //dr (Delta Row - Satır Değişimi) ve dc (Delta Column - Sütun Değişimi) demektir.   
            for(int dc=-1; dc<=1;dc++){ 
                if(dr==0 && dc==0){// Kendini sayma
                    continue;
                }
                int nr = i +dr;
                int nc = j +dc;
                // Kendini sayma ve sınır kontrolü
                if(nr >= 0 && nr < this.numRows && nc >= 0 && nc < this.numCols){
                    // Komşu kare mayınlı mı?
                    if(this.grid[nr][nc].isMine()){
                        mineCount++; // Mayın sayısını artır
                    }
                }
            }
        }
        square.setAdjacentMineCount(mineCount); // Sonucu kaydet
     }
    }
  }
 }

public void OpenSquaze(int rows,int cols){
  if (rows < 0 || rows >= this.numRows || cols < 0 || cols >= this.numCols) {
        return;
    }
    Cell chosenSquare = grid[rows][cols];
    if(chosenSquare.isOpened()){
        return;
    }
    if(chosenSquare.isFlagged()){
       return ; 
    }
  chosenSquare.OpenCell();
 if (chosenSquare.isMine()) {
    isGameOver = true;        // OYUN BİTTİ
    chosenSquare.OpenCell(); 
    revealMines();    // mayını göster
    return;
}
  // Eğer '0' ise Domino Etkisini başlat
  if(chosenSquare.getAdjacentMineCount()==0){
    OpenNeighboringBlocks(rows,cols);
  }
 
 }
public void toggleFlag(int rows, int cols) {
if (rows >= 0 && rows < this.numRows && cols >= 0 && cols < this.numCols) {
  this.grid[rows][cols].toggleFlag();
  }
}
private void OpenNeighboringBlocks(int rows,int cols){
    // Yine 8 komşuyu geziyoruz
for(int dr=-1; dr<=1 ; dr++){
  for(int dc=-1; dc<=1;dc++){
    if(dr==0 && dc==0){
      continue;
      }
      
     int nr = rows +dr;
     int nc = cols +dc;
     // Sınır kontrolü
     if(nr >= 0 && nr < this.numRows && nc >= 0 && nc < this.numCols){
     Cell neighborSquare = grid[nr][nc];  
    
    if(!neighborSquare.isOpened() && !neighborSquare.isFlagged()){
     neighborSquare.OpenCell();
     // RECURSION (Özyineleme):
     // Eğer bu komşu da '0' ise, metodu KENDİSİ için tekrar çağır.
      if(neighborSquare.getAdjacentMineCount()==0){
        OpenNeighboringBlocks(nr, nc);
      }
      }
      }
    }
  }
}

public boolean checkWin() {
    for (int i = 0; i < this.numRows; i++) {
        for (int j = 0; j < this.numCols; j++) {
            Cell cell = this.grid[i][j];
            if (!cell.isMine() && !cell.isOpened()) {
                return false; 
            }
        }
    }
    return true;
}

public void revealMines() {
    for (int i = 0; i < this.numRows; i++) {
        for (int j = 0; j < this.numCols; j++) {
            Cell cell = this.grid[i][j];
            if (cell.isMine()) {
                cell.setOpened(true);
            }

        }
    }
}

public Cell getCell(int r, int c) {
    return this.grid[r][c];
}
}

