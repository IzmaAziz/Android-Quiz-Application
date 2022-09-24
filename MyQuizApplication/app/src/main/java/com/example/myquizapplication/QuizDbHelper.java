package com.example.myquizapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myquizapplication.QuizDis.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyQuizApplication.db";
    private static final int DATABASE_VERSION = 2;
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
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER + " INTEGER" +
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
           Question q1 = new Question("Which method can be defined only once in a program?", "main method", "finalize method", "private method", 1);
           addQuestion(q1);
           Question q2 = new Question("Which of these is not a bitwise operator?", "&", "<=", "&=", 2);
           addQuestion(q2);
           Question q3 = new Question("Which keyword is used by method to refer to the object that invoked it?", "this", "catch", "import", 1);
           addQuestion(q3);
           Question q4 = new Question("Which of these keywords is used to define interfaces in Java?", "Interface", "intf", "interface", 3);
           addQuestion(q4);
           Question q5 = new Question("Which of these access specifiers can be used for an interface?", "private", "public", "protected", 2);
           addQuestion(q5);
           Question q6 = new Question("Which of the following is correct way of importing an entire package ‘pkg’?", "import pkg.*", "Import pkg.", "import pkg.", 1);
           addQuestion(q6);
           Question q7 = new Question("What is the return type of Constructors?", "float", "None of the mentioned", "int", 2);
           addQuestion(q7);
           Question q8 = new Question("Which of the following package stores all the standard java classes?", "util", "lang", "java", 3);
           addQuestion(q8);
           Question q9 = new Question("Which of these method of class String is used to compare two String objects for their equality?", "equals()", "Equals()", "isequal()", 1);
           addQuestion(q9);
           Question q10 = new Question("An expression involving byte, int, & literal numbers is promoted to which of these?", "long", "byte", "int", 3);
           addQuestion(q10);
        }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOptA());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOptB());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOptC());
        cv.put(QuestionsTable.COLUMN_ANSWER, question.getAnswer());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOptA(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOptB(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOptC(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}

