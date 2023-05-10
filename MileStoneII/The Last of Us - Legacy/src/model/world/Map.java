package model.world;
import model.characters.Character;
public class Map extends Cell {
	private int numRows;
    private int numCols;
    private Cell[][] cells;

    public Map(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.cells = new Cell[numRows][numCols];

       // intilize le coloums and le epik rows 
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                cells[i][j] = new EmptyCell();
            }
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
    }
    public void updateMap(Character playCharacter) {
        for(int i=0; i<numRows; i++) {
            for(int j=0; j<numCols; j++) {
                if(cells[i][j] instanceof CharacterCell) {
                    CharacterCell characterCell = (CharacterCell) cells[i][j];
                    if(characterCell.getCharacter().equals(playCharacter)) {
                        cells[i][j] = new EmptyCell();
                    }
                }
            }
        }
    }
    public void SetPos(int row, int col, Cell cell) {
        cells[row][col] = cell;
    }
    public int getRow(Character character) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (cells[i][j] instanceof CharacterCell) {
                    CharacterCell characterCell = (CharacterCell) cells[i][j];
                    if (characterCell.getCharacter().equals(character)) {
                        return i;
                    }
                }
            }
        }
        return -1; 
    }
    public int GetCol(Character character) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (cells[i][j] instanceof CharacterCell) {
                    CharacterCell characterCell = (CharacterCell) cells[i][j];
                    if (characterCell.getCharacter().equals(character)) {
                        return j;
                    }
                }
            }
        }
        return -1; 
    } 
}
