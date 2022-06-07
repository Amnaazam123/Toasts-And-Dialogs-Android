package com.example.toast_and_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button b0,b,b2,b3,checkbox,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b0=findViewById(R.id.toast_simple);
        b=findViewById(R.id.toast_test);
        b2=findViewById(R.id.dialog_alert);
        b3=findViewById(R.id.dialog_list);
        checkbox=findViewById(R.id.checkbox_alert);
        login=findViewById(R.id.login_alert);

        int c1=getResources().getColor(R.color.teal_200);


        //performing simple toast against this b0 button
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Simple Toast",Toast.LENGTH_LONG).show();
                Log.d("abc","simpl toast click");
            }
        });
        //performing customized toast against b button
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater lf=getLayoutInflater();
                View mylayout=lf.inflate(R.layout.customized_toast,null);
                ImageView img=mylayout.findViewById(R.id.imageView);
                img.setImageResource(R.drawable.images);
                TextView tv=mylayout.findViewById(R.id.toast);
                tv.setText("Customized Toast");

                Toast toast=new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(mylayout);
                toast.setGravity(Gravity.CENTER|Gravity.RIGHT,100,200);
                toast.show();
            }
        });
        //performing alert dialog against b2 button
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Title here");
                builder.setMessage("Meessage here");
                builder.setCancelable(false);
                //positive btn = leave
                builder.setPositiveButton("leave btn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                //negative btn = stay
                builder.setNegativeButton("stay btn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                //what-up btn = guidance(about what to do)
                builder.setNeutralButton("what up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Click leave to close and stay to cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        // showing alert dailog with list
        b3.setOnClickListener(new View.OnClickListener() {
            String []colos={"green","blue","red","yellow"};

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Available Colors").setItems(colos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //using customized view
                        LayoutInflater lf=getLayoutInflater();
                        View mylayout=lf.inflate(R.layout.customized_toast,null);
                        ImageView img=mylayout.findViewById(R.id.imageView);
                        TextView tv=mylayout.findViewById(R.id.toast);
                        tv.setText(colos[i]);


                        Toast toast=new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(mylayout);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }
                });
                builder.setCancelable(false);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        //check boxes alert
        checkbox.setOnClickListener(new View.OnClickListener() {
            String []clrs={"green","blue","red"};
            @Override
            public void onClick(View view) {
                ArrayList<Integer> selectedItems=new ArrayList(); //when we track the selected items

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select options")
                        .setMultiChoiceItems(clrs, null,new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    selectedItems.add(which);
                                } else if (selectedItems.contains(which)) {
                                    selectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        });
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "";
                        for (int i = 0; i < selectedItems.size(); i++) {
                            msg = msg + "\n" + (i + 1) + " : " + clrs[ selectedItems.get(i)];
                        }
                        Toast.makeText(getApplicationContext(), "Total " + selectedItems.size() + " Items Selected.\n" + msg, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"No Option Selected",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog  = builder.create();
                dialog.show();
            }
        });
        //login alert using customized toast
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater=getLayoutInflater();
                View signin=inflater.inflate(R.layout.sign_in,null);
                builder.setView(signin);    //set view
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();  //stay there
                    }
                });
                builder.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //sign in user
                        Toast.makeText(MainActivity.this,"Successful login",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog  = builder.create();
                dialog.show();
            }
        });
    }
}