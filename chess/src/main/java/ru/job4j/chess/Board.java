package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;

public class Board {
    private Figure[] figures = new Figure[32];

    private int count = 0;

    public void add(Figure figure) {
        this.figures[this.count++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException,
            OccupiedWayException, FigureNotFoundException {
        boolean found = false;
        Figure figure = null;
        for (Figure fig : figures) {
            if ((fig != null) && (fig.getPosition().x == source.x) && (fig.getPosition().y == source.y)) {
                found = true;
                figure = fig;
                break;
            }
        }
        if (!found) {
            throw new FigureNotFoundException("На данной клетке нет фигуры");
        }
        Cell[] cells = figure.way(source, dest);
        checkIsOccupied(cells);
        figure.copy(dest);
        return true;
    }

    private void checkIsOccupied(Cell[] cells) {
        for (Cell cell : cells) {
            if (cell != null) {
                for (Figure fig : figures) {
                    if ((fig != null) && fig.getPosition().equals(cell)) {
                        throw new OccupiedWayException("Недопустимый ход. Путь фигуры занят.");
                    }
                }
            }
        }
    }
}
