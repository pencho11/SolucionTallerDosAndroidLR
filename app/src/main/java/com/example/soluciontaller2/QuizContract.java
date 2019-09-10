package com.example.soluciontaller2;

import android.provider.BaseColumns;

import com.example.soluciontaller2.quizcontract.QuizContractstring;

public final class QuizContract {

    private QuizContract() {


    }

    public static   class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = QuizContractstring.TABLE_NAME.getMensaje();
        public static final String COLUMN_QUESTION = QuizContractstring.COLUMN_QUESTION.getMensaje();
        public static final String COLUMN_OPTION1 = QuizContractstring.COLUMN_OPTION1.getMensaje();
        public static final String COLUMN_OPTION2 = QuizContractstring.COLUMN_OPTION2.getMensaje();
        public static final String COLUMN_OPTION3 = QuizContractstring.COLUMN_OPTION3.getMensaje();
        public static final String COLUMN_ANSWER_NR = QuizContractstring.COLUMN_ANSWER_NR.getMensaje();
    }


}
