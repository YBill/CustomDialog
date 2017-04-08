package com.bill.customdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void showAlertDialog(View view) {
        CopyCommentDialog.Builder builder = new CopyCommentDialog.Builder(this).setLeftClickListener(new CopyCommentDialog.OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "回复", Toast.LENGTH_SHORT).show();
            }
        }).setRightClickListener(new CopyCommentDialog.OnClickListener() {
            @Override
            public void onClick() {
                Utils.copy(editText.getText().toString());
                Toast.makeText(MainActivity.this, "复制", Toast.LENGTH_SHORT).show();
            }
        }).setLeftImage(R.mipmap.ic_launcher);
        CopyCommentDialog dialog = builder.create();
        dialog.showDialog();
    }

}
