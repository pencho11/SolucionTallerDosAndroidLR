package com.example.soluciontaller2.quizdbhelper;

public enum QuizDbHelperstring {

    TEXT(" TEXT, ") ,
    INTEGER(" INTEGER") ,
    QUIENFUEELPRESIDENTE("¿Quien fue el presidente de colombia para el año 1953?"),
    QUIENFUEELPRESIDENTERESP1("Gustavo Rojas Pinilla"),
    QUIENFUEELPRESIDENTERESP2("Mariano Ospina"),
    QUIENFUEELPRESIDENTERESP3("Guillermo León Valencia"),
    M19("¿En que año el M-19 se toma el palacio de Justicia?"),
    M19RESP1("1984"),
    M19RESP2("1985"),
    M19RESP3("1986"),
    ANAPO("¿Que fue la Anapo?"),
    ANAPORESP1("Una pantilla"),
    ANAPORESP2("Un movimiento politico de izquierda colombiano creado por Guadalupe Salcedo en santander para convatir a los chulavitas " ),
    ANAPORESP3("Un movimiento politico Nacionalista creado por" +
            " el General Gustavo Rojas Pinilla en 1961 para frenar violencia del frente nacional\""),
    RALITO("¿Que fue el Pacto de ralito?"),
    RALITORESP1("Proceso de Paz adelantado en el gobierno" +
            "del Ex-Presidente Alvaro Uribe con grupos Paramilitares"),
    RALITORESP2("Proceso de Paz en el gobierno Gaviria con" +
            "el fin de negociar la paz con  narcotraficantes "),
    RALITORESP3("Proceso de ayuda humanitaria en el Caguan"),
    REGIMEN("¿La palabra 'Regimen' en Ciencia Politica  es ?.." ),
    REGIMENRESP1("Una dictadura Militar "),
    REGIMENRESP2("El marco Legal y Normativo de un Estado"),
    REGIMENRESP3("Una Democracia  ")



    ;

    public String getMsj() {
        return msj;
    }

    QuizDbHelperstring(String msj) {
        this.msj = msj;
    }

    private String msj;


}
