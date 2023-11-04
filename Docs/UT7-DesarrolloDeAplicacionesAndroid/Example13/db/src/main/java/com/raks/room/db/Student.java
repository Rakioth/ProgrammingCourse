package com.raks.room.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int    sid;
    @ColumnInfo
    public String name;
    @ColumnInfo(name = "first_surname")
    public String firstSurname;
    @ColumnInfo(name = "second_surname")
    public String secondSurname;

}