package com.techrevolution.nqueens.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@JsonPropertyOrder(
        {
                "xCoordinate",
                "yCoordinate"
        }
)
public class Coordinates {
    private int xCoordinate;
    private int yCoordinate;

    @Override
    public String toString() {
        return "Coordinates{" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}
