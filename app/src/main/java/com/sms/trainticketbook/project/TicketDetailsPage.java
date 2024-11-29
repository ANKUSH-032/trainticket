package com.sms.trainticketbook.project;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sms.trainticketbook.R;
import com.sms.trainticketbook.project.DataWareHouse.RegisteratoinLab;
import com.sms.trainticketbook.project.DataWareHouse.SPManipulation;
import com.sms.trainticketbook.project.DataWareHouse.TicketDetails;
import com.sms.trainticketbook.project.DataWareHouse.UserDetails;
import com.sms.trainticketbook.project.DataWareHouse.bookingLab;

public class TicketDetailsPage extends AppCompatActivity {

    private EditText TKuserIdFld, TKtoFld, TKfromFld, TKtypeFld, TKdateFld;
    private Button TKClearBtn, QRBtn;
    private ImageView QR_code;
    TicketDetails viewDetails;
    TicketDetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details_page);


        TKuserIdFld = (EditText) findViewById(R.id.TKuserIdFld);
        TKtoFld = (EditText) findViewById(R.id.TKtoFld);
        TKfromFld = (EditText) findViewById(R.id.TKformFld);
        TKtypeFld = (EditText) findViewById(R.id.TKtypeFld);
        TKdateFld = (EditText) findViewById(R.id.TKdateFld);

        TKClearBtn = (Button) findViewById(R.id.TKclearBtn);
        QRBtn = (Button) findViewById(R.id.QRBtn);
        QR_code = (ImageView) findViewById(R.id.QR_code);

        details = (TicketDetails) getIntent().getSerializableExtra("ticketdetails");

        if (details != null) {
            TKuserIdFld.setText(details.getTicketId() + "");
            TKtoFld.setText(details.getToStation());
            TKfromFld.setText(details.getFromStation());
            TKtypeFld.setText(details.getType());
            TKdateFld.setText(details.getDate());
        }

        viewDetails = (TicketDetails) getIntent().getSerializableExtra("TicDeatils");

//        final int kId=viewDetails.getTicketId();

        if (viewDetails != null) {
            TKuserIdFld.setText(viewDetails.getTicketId() + "");
            TKtoFld.setText(viewDetails.getToStation());
            TKfromFld.setText(viewDetails.getFromStation());
            TKtypeFld.setText(viewDetails.getType());
            TKdateFld.setText(viewDetails.getDate());
        }

        TKClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingLab.get(TicketDetailsPage.this).deletTicket(Integer.parseInt(TKuserIdFld.getText().toString()));
                Toast.makeText(TicketDetailsPage.this, "Deleted Sucessfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        QRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeButton();
            }
        });

    }

    public void QRCodeButton() {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            SPManipulation manipulation = SPManipulation.getInstance(this);
            UserDetails userdetails = RegisteratoinLab.get(TicketDetailsPage.this).findUserDetails(Integer.parseInt(manipulation.getUSERID()));
            StringBuilder stringBuilder = new StringBuilder();
            if (viewDetails != null) {
                stringBuilder.append(viewDetails.getTicketId());
                stringBuilder.append("\n");
                stringBuilder.append(userdetails.getName());
                stringBuilder.append("\n");
                stringBuilder.append(viewDetails.getDate());
                stringBuilder.append("\n");
                stringBuilder.append(viewDetails.getFromStation() + " to  " + viewDetails.getToStation());
                stringBuilder.append("\n");
                stringBuilder.append("\n");
                stringBuilder.append(userdetails.getAddress());
            } else if (details != null) {
                stringBuilder.append(details.getTicketId());
                stringBuilder.append("\n");
                stringBuilder.append(userdetails.getName());
                stringBuilder.append("\n");
                stringBuilder.append(details.getDate());
                stringBuilder.append("\n");
                stringBuilder.append(details.getFromStation() + " to  " + details.getToStation());
                stringBuilder.append("\n");
                stringBuilder.append("\n");
                stringBuilder.append(userdetails.getAddress());
            }
            BitMatrix bitMatrix = qrCodeWriter.encode(stringBuilder.toString(), BarcodeFormat.QR_CODE, 200, 200);
            Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            QR_code.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
