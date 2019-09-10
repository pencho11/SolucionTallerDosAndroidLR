package com.example.soluciontaller2.quizactivity;

public enum QuizActivitystring {

   SELECCIONA_UNA_RESPUESTA("¡Selecciona una respuesta !"),
   PREGUNTA("Pregunta: "),
   CONFIRMAR("Confirmar"),
   ESPACIO("/"),
   PUNTOS("Puntos: "),
   RESPUESTA_UNO("La Respuesta 1 es correcta "),
   RESPUESTA_DOS("Respuesta 2 es correcta"),
   RESPUESTA_TRES("Respuesta 3 es correcta"),
   SEGUIR("Seguir"),
   VOLVER_AL_INICIO("Volver al Inicio"),
   SALIR_PARA_INICIO("Precione atras para volver al inicio"),
   PUNTAJE("Puntaje Acumulado : "),
   EXTRASCORE("extraScore"),
   FORMATO("%02d:%02d"),
   SHARE_PRE("sharedPrefs"),
   KEYH("keyHighscore"),
    ;


    QuizActivitystring(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }



    private String mensaje;

}
