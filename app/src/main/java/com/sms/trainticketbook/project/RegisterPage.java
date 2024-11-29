package com.sms.trainticketbook.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sms.trainticketbook.R;
import com.sms.trainticketbook.project.DataWareHouse.Registeration;
import com.sms.trainticketbook.project.DataWareHouse.RegisteratoinLab;
import com.sms.trainticketbook.project.DataWareHouse.SPManipulation;


public class RegisterPage extends AppCompatActivity {
    public static final String userDetails = "userDetails";
    private EditText RuserIdFld,
            RnameFld, RpasswordFld,
            RageFld, RadrssFld,
            RmobileNoFld, RcardNoFld;

    private Button RsubmitBtn;
    SPManipulation spManipulation;

    public boolean isValid(String s) {
        return (!s.trim().isEmpty());
    }

    public boolean userIdIsValid(String s) {
        return (s.length() >= 5 && isValid(s));
    }

    public boolean passIsValid(String s) {
        return (s.length() >= 5 && isValid(s));
    }


    public int C2I(String st) {
//        int number = Integer.parseInt(st);
        try {
            return Integer.parseInt(st);
        } catch (Exception e) {
            return 1234567898;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        spManipulation = SPManipulation.getInstance(this);
        RuserIdFld = (EditText) findViewById(R.id.RuserIdFld);
        RnameFld = (EditText) findViewById(R.id.RnameFld);
        RpasswordFld = (EditText) findViewById(R.id.RpasswordFld);
        RadrssFld = (EditText) findViewById(R.id.RaddressFld);
        RageFld = (EditText) findViewById(R.id.RageFld);
        RmobileNoFld = (EditText) findViewById(R.id.RmobileNoFld);
        RcardNoFld = (EditText) findViewById(R.id.RcardNoFld);
        RsubmitBtn = (Button) findViewById(R.id.RsubmitBtn);

        RsubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userIdIsValid(RuserIdFld.getText().toString())
                        && isValid(RnameFld.getText().toString())
                        && passIsValid(RpasswordFld.getText().toString())
                        && isValid(RadrssFld.getText().toString())
                        && isValid(RageFld.getText().toString())
                        && isValid(RmobileNoFld.getText().toString())
                        && isValid(RcardNoFld.getText().toString())) {

                    //geting te date and put it into model
                    Registeration register = new Registeration();
                    register.setUuid(C2I(RuserIdFld.getText().toString()));
                    register.setUserName(RnameFld.getText().toString());
                    register.setPassword(RpasswordFld.getText().toString());
                    register.setAddress(RadrssFld.getText().toString());
                    register.setAge(C2I(RageFld.getText().toString()));
                    register.setMobileNo(C2I(RmobileNoFld.getText().toString()));
                    register.setCardNo(C2I(RcardNoFld.getText().toString()));

                    Intent intent = new Intent(v.getContext(), SelectPage.class);
                    intent.putExtra(userDetails, register);
                    Toast.makeText(RegisterPage.this, register.getUserName(), Toast.LENGTH_SHORT).show();
                    boolean b = RegisteratoinLab.get(RegisterPage.this).insert(register);
                    if (b == true) {
                        Toast.makeText(RegisterPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        spManipulation.saveIsLogin(true);
                        spManipulation.saveUSERId("" + register.getUuid());
                        finish();
                    } else {
                        Toast.makeText(v.getContext(), "Data Already Registered \n try again!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "please Fill Fields Right", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}

