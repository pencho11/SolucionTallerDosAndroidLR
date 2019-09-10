package com.example.soluciontaller2.quizactivity;

public enum QuizActivityint {

    UNO(1),
    VENTITRES(23),
    CIENTOTREINTAYCINCO(135),
    CUARENTAYSEIS(46),
    TREINTAMIL(30000),
    DIEZMIL(1000),
    VEINTEMIL(2000),
    SESENTA(60)
    ;

    QuizActivityint(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }



    private int num;
}
