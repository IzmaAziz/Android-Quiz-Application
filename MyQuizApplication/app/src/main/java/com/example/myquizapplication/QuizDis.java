package com.example.myquizapplication;

import android.provider.BaseColumns;

public final class QuizDis {
    private QuizDis() {
    }
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "optA";
        public static final String COLUMN_OPTION2 = "optB";
        public static final String COLUMN_OPTION3 = "optC";
        public static final String COLUMN_ANSWER = "answer";
    }
}


