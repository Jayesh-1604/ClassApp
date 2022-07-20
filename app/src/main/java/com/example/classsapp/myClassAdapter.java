package com.example.classsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class myClassAdapter extends FirebaseRecyclerAdapter<myClassModel,myClassAdapter.myviewholder>
{
    Context context;
    public myClassAdapter(@NonNull FirebaseRecyclerOptions<myClassModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull myClassModel  model) {


        holder.myClassName.setText(model.getClassName());
        holder.myClassPassCode.setText("Class Code : "+model.getClassPassCode());
        holder.myTeacherName.setText("__");

        holder.myClassName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,assignmentGiven.class);
                intent.putExtra("className",model.getClassName());
                intent.putExtra("classPassCode",model.ClassPassCode);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);


            }
        });

    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleclass,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView myClassName,myClassPassCode,myTeacherName;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            myClassName=(TextView)itemView.findViewById(R.id.myclasses_name);
            myClassPassCode=(TextView)itemView.findViewById(R.id.myclassRoom_pass_code);
            myTeacherName=(TextView)itemView.findViewById(R.id.myclass_teacher_name);
        }
    }
}

