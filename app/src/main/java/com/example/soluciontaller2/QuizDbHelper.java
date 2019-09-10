package com.example.soluciontaller2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.soluciontaller2.QuizContract.*;
import com.example.soluciontaller2.quizdbhelper.QuizDbHelperstring;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CulturaGeneral.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + QuizDbHelperstring.TEXT.getMsj() +
                QuestionsTable.COLUMN_OPTION1 + QuizDbHelperstring.TEXT.getMsj() +
                QuestionsTable.COLUMN_OPTION2 + QuizDbHelperstring.TEXT.getMsj() +
                QuestionsTable.COLUMN_OPTION3 + QuizDbHelperstring.TEXT.getMsj() +
                QuestionsTable.COLUMN_ANSWER_NR + QuizDbHelperstring.INTEGER.getMsj() +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question(QuizDbHelperstring.QUIENFUEELPRESIDENTE.getMsj(), QuizDbHelperstring.QUIENFUEELPRESIDENTERESP1.getMsj(),
                QuizDbHelperstring.QUIENFUEELPRESIDENTERESP2.getMsj(), QuizDbHelperstring.QUIENFUEELPRESIDENTERESP3.getMsj(), 1);
        addQuestion(q1);
        Question q2 = new Question(QuizDbHelperstring.M19.getMsj(), QuizDbHelperstring.M19RESP1.getMsj(),
                QuizDbHelperstring.M19RESP2.getMsj(),  QuizDbHelperstring.M19RESP3.getMsj(), 2);
        addQuestion(q2);
        Question q3 = new Question(QuizDbHelperstring.ANAPO.getMsj(),  QuizDbHelperstring.ANAPORESP1.getMsj(),
                QuizDbHelperstring.ANAPORESP2.getMsj() ,
                QuizDbHelperstring.ANAPORESP3.getMsj(), 3);
        addQuestion(q3);
        Question q4 = new Question(QuizDbHelperstring.RALITO.getMsj(), QuizDbHelperstring.RALITORESP1.getMsj()
                , QuizDbHelperstring.RALITORESP2.getMsj(), QuizDbHelperstring.RALITORESP3.getMsj(), 1);
        addQuestion(q4);
        Question q5 = new Question(QuizDbHelperstring.REGIMEN.getMsj(), QuizDbHelperstring.REGIMENRESP1.getMsj(),
                QuizDbHelperstring.REGIMENRESP2.getMsj(), QuizDbHelperstring.REGIMENRESP3.getMsj(), 2);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
