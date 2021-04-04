package com.techrevolution.nqueens.service;

import com.techrevolution.nqueens.model.Coordinates;

import java.util.List;

public interface QueensService {
    List<Coordinates> getCoordinates(int queenNumbers);
}
