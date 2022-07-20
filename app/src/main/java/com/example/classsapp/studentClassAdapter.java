package com.example.classsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class studentClassAdapter extends FirebaseRecyclerAdapter<studentModel,studentClassAdapter.myviewholder>
{
    Context context;
    public studentClassAdapter(@NonNull FirebaseRecyclerOptions<studentModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull studentModel  model) {


        holder.classname.setText(model.getClassName());
        holder.classpasscode.setText("Class Code:"+model.getPasscode());
        holder.teacherph.setText("Teacher Mob"+model.getTeacherPhoneNumber());

        holder.classname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Assignments.class);
                intent.putExtra("className",model.getClassName());
                intent.putExtra("classPassCode",model.getPasscode());
                intent.putExtra("classTeacherPh",model.getTeacherPhoneNumber());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);


            }
        });

    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView classname,classpasscode,teacherph;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            classname=(TextView)itemView.findViewById(R.id.class_name);
            classpasscode=(TextView)itemView.findViewById(R.id.class_pass_code);
            teacherph=(TextView)itemView.findViewById(R.id.class_teacher_ph);
        }
    }
}
