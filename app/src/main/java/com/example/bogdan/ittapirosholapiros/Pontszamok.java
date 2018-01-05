package com.example.bogdan.ittapirosholapiros;

/**
 * Created by Vadek on 2018.01.04..
 */

public class Pontszamok {

    private int _id;
    private String _eredmeny;

    public Pontszamok(String maxPt) {

    }

    public Pontszamok(int id, String _eredmeny) {
        this._id = id;
        this._eredmeny = _eredmeny;
    }

    public int get_id() {
        return _id;
    }

    public String get_eredmeny() {
        return _eredmeny;
    }

    public void set_id(int _id) {

        this._id = _id;
    }

    public void set_eredmeny(String _eredmeny) {
        this._eredmeny = _eredmeny;
    }
}
