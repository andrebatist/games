package ru.job4j.chess.figures;

import ru.job4j.chess.exceptions.ImpossibleMoveException;

public abstract class Figure {
    private final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return position;
    }

    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public abstract Figure copy(Cell dest);

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }
}
