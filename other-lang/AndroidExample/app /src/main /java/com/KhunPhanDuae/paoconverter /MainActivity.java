package com.khunphanduae.paoconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText zgEdit,uniEdit;
    Button zgPaste,zgCopy,uniPaste,uniCopy;
    ClipboardManager clipboardManager;

    boolean isZawgyi = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("PaOh ZG <=> Uni Converter");
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        zgEdit = findViewById(R.id.edZawgyi);
        zgEdit.setTypeface(Typeface.createFromAsset(getAssets(),"zg.ttf"));

        uniEdit = findViewById(R.id.edUni);
        uniEdit.setTypeface(Typeface.createFromAsset(getAssets(), "uni.ttf"));

        zgEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                isZawgyi = true;
            }
        });
        zgEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isZawgyi) {
                    uniEdit.setText(Rabbit.zg2uni(zgEdit.getText().toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        uniEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                isZawgyi = false;
            }
        });
        uniEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!isZawgyi) {
                    zgEdit.setText(Rabbit.uni2zg(uniEdit.getText().toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        zgPaste = findViewById(R.id.zgPaste);
        zgPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isZawgyi = true;
                if (clipboardManager.hasText()) {
                    zgEdit.setText(clipboardManager.getText());
                }
            }
        });


        zgCopy = findViewById(R.id.zgCopy);
        zgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isZawgyi = true;
                if (zgEdit.getText().length()>0){
                    clipboardManager.setText(zgEdit.getText().toString());
                    if (clipboardManager.hasText()){
                        Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        uniPaste = findViewById(R.id.uniPaste);
        uniPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isZawgyi = false;
                if (clipboardManager.hasText()) {
                    uniEdit.setText(clipboardManager.getText());
                }
            }
        });

        uniCopy = findViewById(R.id.uniCopy);
        uniCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isZawgyi = false;
                if (uniCopy.getText().length()>0){
                    clipboardManager.setText(uniCopy.getText().toString());
                    if (clipboardManager.hasText()){
                        Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.clear:
                uniEdit.setText("");
                zgEdit.setText("");
                break;
            case R.id.about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("About")
                        .setMessage(Html.fromHtml("<p>PaOh Zawgyi Unicode converter source code<br /><a href=\"https://github.com/KhunHtetzNaing/PaOh-Zawgyi-Unicode-Converter\">https://github.com/KhunHtetzNaing/PaOh-Zawgyi-Unicode-Converter</a></p>\n" +
                                "<p>Web version<br /><a href=\"https://khunhtetznaing.github.io/PaOh-Zawgyi-Unicode-Converter/\">https://khunhtetznaing.github.io/PaOh-Zawgyi-Unicode-Converter/</a></p>\n" +
                                "<p>Developed by Khun Htetz Naing<br /><a href=\"https://web.facebook.com/KhunHtetzNaing0\">https://web.facebook.com/KhunHtetzNaing0</a></p>\n" +
                                "<p>Version 1.0</p>\n" +
                                "<p>This converter based on Rabbit converter.<br /><a href=\"https://github.com/Rabbit-Converter/Rabbit/\">https://github.com/Rabbit-Converter/Rabbit/</a></p>"))
                        .setPositiveButton("OK",null);
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Notice!")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("Do you want to exit ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No",null);
        builder.show();
    }
  }
