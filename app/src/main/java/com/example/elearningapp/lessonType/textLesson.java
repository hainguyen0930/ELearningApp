package com.example.elearningapp.lessonType;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elearningapp.R;
import com.example.elearningapp.activity.CourseLessonsActivity;
import com.example.elearningapp.courseItem.LessonItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class textLesson extends AppCompatActivity {

    TextView title, content;

    Button nxt, pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_text);

        title = findViewById(R.id.lesson_title2);
        content = findViewById(R.id.lesson_content);

        nxt = findViewById(R.id.btn_next_lesson);
        pre = findViewById(R.id.btn_pre_lesson);

        setLesson();
    }

    void setLesson() {
        List<LessonItem> lessonItem = (List<LessonItem>) getIntent().getSerializableExtra("lesson");
        int position = getIntent().getIntExtra("position", 0);
        int maxPosition = getIntent().getIntExtra("maxPosition", 0);

        title.setText(lessonItem.get(position).getName());
        content.setText(lessonItem.get(position).getContent());
        if (position > 0) {
            pre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonOnClick(position - 1, maxPosition, lessonItem);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
            });
        }
        if (position != maxPosition - 1) {
            nxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonOnClick(position + 1, maxPosition, lessonItem);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
        }
    }

    void buttonOnClick(int position, int maxPosition, List<LessonItem> lessonItemList) {
        if (lessonItemList.get(position).getType().equals("video")) {
            Intent intent = new Intent(textLesson.this, videoLesson.class);
            intent.putExtra("lesson", (Serializable) lessonItemList);
            intent.putExtra("position", position);
            intent.putExtra("maxPosition", maxPosition);
            startActivity(intent);
        } else if (lessonItemList.get(position).getType().equals("text")) {
            Intent intent = new Intent(textLesson.this, textLesson.class);
            intent.putExtra("lesson", (Serializable) lessonItemList);
            intent.putExtra("position", position);
            intent.putExtra("maxPosition", maxPosition);
            startActivity(intent);
        } else if (lessonItemList.get(position).getType().equals("test")) {
            Intent intent = new Intent(textLesson.this, testLesson.class);
            intent.putExtra("lesson", (Serializable) lessonItemList);
            intent.putExtra("position", position);
            intent.putExtra("maxPosition", maxPosition);
            startActivity(intent);
        }
    }


}