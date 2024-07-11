package com.raks.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.raks.room.db.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    public interface StudentClickListener {
        void onStudentEdit(int position);
        void onStudentDelete(int position);
    }

    private final List<Student>        _students;
    private final StudentClickListener _studentClickListener;

    public StudentAdapter(List<Student> students, StudentClickListener studentClickListener) {
        _students             = students;
        _studentClickListener = studentClickListener;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View studentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_student_item, parent, false);
        return new StudentViewHolder(studentView, _studentClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bind(_students.get(position));
    }

    @Override
    public int getItemCount() {
        return _students.size();
    }

}