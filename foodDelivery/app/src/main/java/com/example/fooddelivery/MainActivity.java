package com.example.fooddelivery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.fooddelivery.EXTRA_NAME";
    public static final String EXTRA_ADDRESS = "com.example.fooddelivery.EXTRA_ADDRESS";
    public static final String EXTRA_EMAIL = "com.example.fooddelivery.EXTRA_EMAIL";
    public static final String EXTRA_NUMBER = "com.example.fooddelivery.EXTRA_NUMBER";
    public static final String EXTRA_TOTAL = "com.example.fooddelivery.EXTRA_TOTAL";

    String nameInp, addrInp, emailInp;
    int phoneInp;

    RadioGroup radioGroup;
    TextView totalValue;
    Button submitBtn;
    Button toppings;
    TextView itemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> orderItems = new ArrayList<>();
    double total;
    EditText nameInput;
    EditText addrInput;
    EditText emailInput;
    EditText phoneInput;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        submitBtn = (Button) findViewById(R.id.submit);
        toppings = (Button) findViewById(R.id.toppings);
        itemSelected = (TextView) findViewById(R.id.toppings_selected);
        totalValue = (TextView) findViewById(R.id.total);

//        submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int radioId = radioGroup.getCheckedRadioButtonId();
//                radioButton = findViewById(radioId);
//                textView.setText("Your option: " + radioButton.getText());
//
//            }
//        });

        listItems = getResources().getStringArray(R.array.topping_list);
        checkedItems = new boolean[listItems.length];

        toppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Toppings Available");
                builder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if (!orderItems.contains(position)){
                                orderItems.add(position);
                                if (position == 0 || position == 1 || position == 5 || position == 6 || position == 7){
                                    total = total + 5.00;
                                } else if (position == 2){total = total + 7.00;}
                                else if (position == 3 || position == 8 || position == 9 ){
                                    total = total + 8.00;
                                }
                                else if(position == 4){total = total + 10.00;}
                            }
                        }else {
                            orderItems.remove(position);
                            if (position == 0 || position == 1 || position == 5 || position == 6 || position == 7){
                                total = total - 5.00;
                            } else if (position == 2){total = total - 7.00;}
                            else if (position == 3 || position == 8 || position == 9 ){
                                total = total - 8.00;
                            }
                            else if(position == 4){total = total - 10.00;}
                        }
                    }
                });
                builder.setCancelable(false);
                builder.setPositiveButton(getString(R.string.ok_label), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";
                        for (int i = 0; i<orderItems.size(); i++){
                            item = item + listItems[orderItems.get(i)];
                            if(i != orderItems.size() - 1){
                                item = item + ", ";
                            }
                        }
                        itemSelected.setText(item);
                    }
                });

                builder.setNegativeButton(getString(R.string.dismiss_label), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton(getString(R.string.clear_all_label), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i < checkedItems.length; i++){
                            checkedItems[i] = false;
                            orderItems.clear();
                            itemSelected.setText("");
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        nameInput = (EditText) findViewById(R.id.name);
        addrInput = (EditText) findViewById(R.id.addr);
        phoneInput = (EditText) findViewById(R.id.phoneNum);
        emailInput = (EditText) findViewById(R.id.email);

        nameInp = nameInput.getText().toString();
        addrInp = addrInput.getText().toString();
        emailInp = emailInput.getText().toString();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmation();
            }
        });

    }

    public void pizzaSize(View v){

        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()) {
            case R.id.radio_1:
                if (checked)
                    total = 5.50;
                    Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radio_2:
                if (checked)
                    total = 7.99;
                    Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radio_3:
                if (checked)
                    total = 9.50;
                Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_4:
                if (checked)
                    total = 11.38;
                Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
        }
    }

    public void cheese(View v){
        CheckBox cheese = (CheckBox) v;
        if (cheese.isChecked()){
            total = total + 5.00;
            Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
        }
        else{
            total = total - 5.00;
            Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
        }
    }

    public void deliver(View v){
        CheckBox deliver = (CheckBox) v;
        if (deliver.isChecked()){
            total = total + 5.00;
            Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
        }
        else{
            total = total - 5.00;
            Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
        }
    }

    public void openConfirmation(){
//        Toast.makeText(this, "Total is: " + total, Toast.LENGTH_SHORT).show();
        nameInput = (EditText) findViewById(R.id.name);
        addrInput = (EditText) findViewById(R.id.addr);
        phoneInput = (EditText) findViewById(R.id.phoneNum);
        emailInput = (EditText) findViewById(R.id.email);

        nameInp = nameInput.getText().toString();
        addrInp = addrInput.getText().toString();
        emailInp = emailInput.getText().toString();
        phoneInp = Integer.parseInt(phoneInput.getText().toString());


        Intent intent = new Intent(this, Confirmation.class);
        intent.putExtra(EXTRA_NAME, nameInp);
        intent.putExtra(EXTRA_ADDRESS, addrInp);
        intent.putExtra(EXTRA_EMAIL, emailInp);
        intent.putExtra(EXTRA_NUMBER, phoneInp);
        intent.putExtra(EXTRA_TOTAL, total);
        startActivity(intent);
    }

}
