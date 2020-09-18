package com.shangcg.service;

class Model{
    public static int a = 1;
    private final int NUMBER = 2;
    public int b;
    public int c = 3;

    public Model(int b){
        this.b = b;
    }

    public static void main(String[] args){
        int d = 10;
        Model modelA = new Model(2);
        Model modelB = new Model(3);
    }
}