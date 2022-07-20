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


public class studentsAssignmentAdapter extends FirebaseRecyclerAdapter<studentAssignmentModel,studentsAssignmentAdapter.myviewholder>
{
    Context context;
    public studentsAssignmentAdapter(@NonNull FirebaseRecyclerOptions<studentAssignmentModel> options, Context context) {
        super(options);
        this.context = context;
        //Toast.makeText(context,"Jayesh",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull studentAssignmentModel  model) {
        holder.assignDesc.setText(model.getAssiDesc());
        holder.assignName.setText("Title : "+model.getAssiName());
        holder.teacherph.setText("Teacher Ph : "+model.getAssiTeacherPh());
        holder.assignClassCodee.setText("Class Code : "+model.getAssiClassCode());

        holder.assignDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,studentAssignmentSubmittion.class);
                intent.putExtra("AssignmentName",model.getAssiName());
                intent.putExtra("AssignmentDescription",model.getAssiDesc());
                intent.putExtra("teacher_ph",model.getAssiTeacherPh());
                intent.putExtra("ClassCodee",model.getAssiClassCode());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowforassign,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView assignName,assignDesc,teacherph,assignClassCodee;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            assignName=(TextView)itemView.findViewById(R.id.assign_name);
            assignDesc=(TextView)itemView.findViewById(R.id.assign_desc);
            teacherph=(TextView)itemView.findViewById(R.id.classTeacherPhoneNumber);
            assignClassCodee=(TextView)itemView.findViewById(R.id.AssignmentClassCode);

        }
    }
}
