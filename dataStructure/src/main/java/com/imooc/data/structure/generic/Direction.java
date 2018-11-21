package com.imooc.data.structure.generic;

public enum Direction {
    TOP(0),
    LEFT(1),
    DOWN(2),
    RIGHT(3),
    TL(4),
    LD(5),
    DR(6),
    RT(7);
    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
