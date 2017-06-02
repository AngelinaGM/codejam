package com.angelina.codejam.flipper;

/**
 * Created by Angelina on 02.06.2017.
 */
public final class Flipper {
    private int k;      // number of pancakes than can flip by flipper
    private String row; // the row of pancakes

    public Flipper() {
    }

    public Flipper(int k, String row) {
        this.k = k;
        this.row = row;
    }

    public static Flipper initFlipper(int k, String row) {
        return new Flipper(k,row);
    }

    public static Flipper initFlipper(Flipper flipper) {
        Flipper f = new Flipper(flipper.getK(), flipper.getRow());
        return f;
    }

    @Override
    public String toString() {
        return row + " " + k;
    }

    public int getK() {
        return k;
    }

    public String getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flipper flipper = (Flipper) o;

        if (k != flipper.k) return false;
        return row != null ? row.equals(flipper.row) : flipper.row == null;

    }

    @Override
    public int hashCode() {
        int result = k;
        result = 31 * result + (row != null ? row.hashCode() : 0);
        return result;
    }
}
