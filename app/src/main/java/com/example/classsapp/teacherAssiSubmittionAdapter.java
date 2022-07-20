package com.example.classsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class teacherAssiSubmittionAdapter extends FirebaseRecyclerAdapter<teacherAssiSubmittionModel,teacherAssiSubmittionAdapter.myviewholder>
{
    Context context;
    public teacherAssiSubmittionAdapter(@NonNull FirebaseRecyclerOptions<teacherAssiSubmittionModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull teacherAssiSubmittionModel  model) {
        holder.assignStudentName.setText(model.getStudentName());
        holder.assignSubName.setText("Title : "+model.getAssiName());
        holder.assignStudentEmail.setText(model.getStudentEmail());
        holder.assignStudentName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,studentAssiDashboard.class);
                intent.putExtra("pdfUrl",model.getPdfUrl());
                intent.putExtra("student_name",model.getStudentName());
                intent.putExtra("student_email",model.getStudentEmail());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowforassignview,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView assignSubName,assignStudentName,assignStudentEmail;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            assignSubName=(TextView)itemView.findViewById(R.id.teacher_your_assign_sub_Name);
            assignStudentName=(TextView)itemView.findViewById(R.id.teacher_your_assign_sub_student_Name);
            assignStudentEmail=(TextView)itemView.findViewById(R.id.teacher_your_assign_student_email);
        }
    }
}
