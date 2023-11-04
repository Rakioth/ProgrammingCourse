package com.raks.room.db;

import androidx.room.*;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM students")
    Single<List<Student>> getAll();

    @Query("SELECT * FROM students WHERE sid = :id")
    Single<Student> find(int id);

    @Insert
    Completable insertStudent(Student student);

    @Update
    Completable updateStudent(Student student);

    @Delete
    Completable deleteStudent(Student student);

}