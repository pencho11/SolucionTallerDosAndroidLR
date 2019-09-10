package com.example.soluciontaller2.quizcontract;

public enum QuizContractstring {

    TABLE_NAME("quiz_questions"),
    COLUMN_QUESTION ("question"),
    COLUMN_OPTION1( "option1"),
    COLUMN_OPTION2( "option2"),
    COLUMN_OPTION3( "option3"),
    COLUMN_ANSWER_NR ( "answer_nr"),
    JUGADOR("jugador")
    ;

    public String getMensaje() {
        return mensaje;
    }

    QuizContractstring(String mensaje) {
        this.mensaje = mensaje;
    }

    private String mensaje ;
}
