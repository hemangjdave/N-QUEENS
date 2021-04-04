package com.techrevolution.nqueens.logic;

import com.techrevolution.nqueens.model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BiPredicate;

public class QueensBoard {

    private final char[][] queens;
    private final Stack<Coordinates> stack = new Stack<>();
    private final List<Coordinates> list = new ArrayList<>();
    private int successCount;
    private Coordinates defaultCoordinates;

    public QueensBoard(int size) {
        this.queens = new char[size][size];
        defaultCoordinates = new Coordinates(0, 0);
    }

    private boolean isInitializedCordinates(Coordinates coordinates) {
        return coordinates.getXCoordinate() == 0 && coordinates.getYCoordinate() == 0;
    }

    private boolean isValidLeftCordinates(Coordinates coordinates) {
        if (isInitializedCordinates(coordinates)) {
            return true;
        }
        var xAxis = coordinates.getXCoordinate();
        var yAxis = coordinates.getYCoordinate();
        while (yAxis != 0) {
            yAxis--;
            if (ifCoordinatesHaveQueen(xAxis , yAxis)) {
                return false;
            }
        }
        return true;
    }

    private boolean ifCoordinatesHaveQueen(int xAxis, int yAxis) {
        BiPredicate<Integer , Integer> biPredicate
                = (x , y) -> queens[x][y] == 'Q';
        return biPredicate.test(xAxis, yAxis);
    }

    private boolean isValidUpperDiagonalCordinates(Coordinates coordinates) {
        if (isInitializedCordinates(coordinates)) {
            return true;
        }
        var xAxis = coordinates.getXCoordinate();
        var yAxis = coordinates.getYCoordinate();
        while (yAxis != 0 && xAxis != 0) {
            xAxis--;
            yAxis--;
            if (ifCoordinatesHaveQueen(xAxis , yAxis)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidLowerDiagonalCordinates(Coordinates coordinates) {
        if (isInitializedCordinates(coordinates)) {
            return true;
        }
        var xAxis = coordinates.getXCoordinate();
        var yAxis = coordinates.getYCoordinate();
        while (xAxis < queens.length - 1 && yAxis != 0) {
            xAxis++;
            yAxis--;
            if (ifCoordinatesHaveQueen(xAxis , yAxis)) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidCoordinates(Coordinates coordinates) {
        return isValidLeftCordinates(coordinates)
                && isValidUpperDiagonalCordinates(coordinates)
                && isValidLowerDiagonalCordinates(coordinates);
    }

    public boolean isQueenboardFull() {
        return successCount == queens.length;
    }

    public void putQueen() {
        performQueenOperation(defaultCoordinates);
    }

    private void performQueenOperation(Coordinates coordinates) {
        if (isValidCoordinates(coordinates)) {
            int yCoordinate = coordinates.getYCoordinate();
            queens[coordinates.getXCoordinate()][yCoordinate] = 'Q';
            successCount++;
            stack.push(coordinates);
            list.add(coordinates);
            defaultCoordinates = new Coordinates(0, yCoordinate + 1);
        } else {
            if (defaultCoordinates.getXCoordinate() == queens.length - 1) {
                backTrackQueen();
            } else {
                defaultCoordinates = new Coordinates(
                        defaultCoordinates.getXCoordinate() + 1,
                        defaultCoordinates.getYCoordinate()
                );
            }
        }
    }

    private void backTrackQueen() {
        if (stack.isEmpty()) {
            return;
        }
        Coordinates lastCoordinate = stack.pop();
        queens[lastCoordinate.getXCoordinate()][lastCoordinate.getYCoordinate()] = ' ';
        list.remove(lastCoordinate);
        successCount--;
        if (lastCoordinate.getXCoordinate() == queens.length - 1) {
            backTrackQueen();
        } else {
            defaultCoordinates = new Coordinates(lastCoordinate.getXCoordinate() + 1, lastCoordinate.getYCoordinate());
        }
    }

    public List<Coordinates> getCoordinates() {
        return list;
    }
}
