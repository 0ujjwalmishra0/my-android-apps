package aqura.ujjwal.Table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String numTable;
    ArrayList<String> table= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView= findViewById(R.id.listView);
        EditText editText = findViewById(R.id.number);
        TextView textView = findViewById(R.id.textView);
        textView.setText("Table of 1");
        for(int i=0;i<10;i++){
            String text= (1+" X "+ (i+1)+ " = "+ (i+1));
            table.add(text);
        }


        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {

                //b is a boolean which gives if focus is present or not
                if(!b) {
//                    editText.setVisibility(View.VISIBLE);
//                    listView.setVisibility(View.VISIBLE);
                    numTable = editText.getText().toString();
                    Integer num = Integer.parseInt(numTable);
                    if(num>= Integer.MAX_VALUE/10){
                        Toast.makeText(MainActivity.this, "Number is too large !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        textView.setText("Table of " + numTable);
                        for (int i = 0; i < 10; i++) {
                            String text = (numTable + " X " + (i + 1) + " = " + (i + 1) * num);
                            table.set(i, text);
                        }
                    }
                }
//                else{
//                    for(int i=0;i<10;i++){
//                        String text= (1+" X "+ (i+1)+ " = "+ (i+1));
//                        table.set(i,text);
//
//                    }
//                }
            }
        });


        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,table);
        listView.setAdapter(arrayAdapter);
//        arrayAdapter.notifyDataSetChanged();

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, family.get(i), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}