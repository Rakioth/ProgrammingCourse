package com.raks.room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.raks.room.db.AppDatabase;
import com.raks.room.db.Student;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Student> _students;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase appDatabase = ((RoomApplication) getApplication()).appDatabase;
        appDatabase.studentDao().getAll()
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(students -> {
                       _students = students;
                       RecyclerView recyclerView = findViewById(R.id.recyclerview);
                       recyclerView.setAdapter(new StudentAdapter(_students, new StudentAdapter.StudentClickListener() {
                           @Override
                           public void onStudentEdit(int position) {
                               Student student = _students.get(position);
                               Intent  intent  = new Intent();
                               intent.setClass(MainActivity.this, EditStudentActivity.class);
                               intent.putExtra(EditStudentActivity.STUDENT_ID_KEY, student.sid);
                               startActivity(intent);
                           }

                           @Override
                           public void onStudentDelete(int position) {
                               Student student = _students.get(position);
                               appDatabase.studentDao().deleteStudent(student)
                                          .subscribeOn(Schedulers.io())
                                          .observeOn(AndroidSchedulers.mainThread())
                                          .subscribe(() -> {
                                              _students.remove(position);
                                              recyclerView.getAdapter().notifyItemRemoved(position);
                                          });
                           }
                       }));
                       recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                   });


        findViewById(R.id.add_student).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, EditStudentActivity.class);
            intent.putExtra(EditStudentActivity.STUDENT_ID_KEY, 0);
            startActivity(intent);
        });
    }
}