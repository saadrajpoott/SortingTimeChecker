package com.example.sortingtimechecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sortingtimechecker.Algorithms.SortingAlgorithms;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView execution_time;
    private EditText array_size_edittext;
    private Spinner spinner;
    private long[] numbersArray;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        execution_time = findViewById(R.id.executionTime);
        spinner = findViewById(R.id.spinner);
        array_size_edittext = findViewById(R.id.array_size_edittext);

        if( savedInstanceState != null ){
            execution_time.setText(savedInstanceState.getString("time"));
            array_size_edittext.setText(savedInstanceState.getString("array_size"));
            spinner.setSelection(savedInstanceState.getInt("selected_item"));
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        execution_time.setText(savedInstanceState.getString("time"));
        array_size_edittext.setText(savedInstanceState.getString("array_size"));
        spinner.setSelection(savedInstanceState.getInt("selected_item"));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("time", execution_time.getText().toString());
        outState.putString("array_size", array_size_edittext.getText().toString());
        outState.putInt("selected_item", spinner.getSelectedItemPosition());
    }


    public void populateArray(View view) {
        if(!array_size_edittext.getText().toString().equals("")){
            int arraySize = Integer.parseInt(array_size_edittext.getText().toString());
            numbersArray = new long[arraySize];
            for( int x=0; x<arraySize; x++ ){
                numbersArray[x] = (long)(Math.random()*500);
            }
            Toast.makeText(this, "Populated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Specify Array Size", Toast.LENGTH_SHORT).show();
        }

    }


    public void calculateExecutionTime(View view) {
        if(numbersArray!= null){
            new AsyncCalculation().execute();
        }
        else{
            Toast.makeText(this, "Please Populate Array", Toast.LENGTH_SHORT).show();
        }
    }

    public void resetData(View view) {
        numbersArray = null;
        execution_time.setText("0");
        array_size_edittext.setText("");
        spinner.setSelection(0);
        Toast.makeText(MainActivity.this, "Refreshed!", Toast.LENGTH_SHORT).show();
    }

    public void displayArray(View view) {
        String string_array = Arrays.toString(numbersArray);
        Intent intent = new Intent(MainActivity.this, DisplayArray.class);
        intent.putExtra("string_array", string_array);
        startActivity(intent);

    }

    private class AsyncCalculation extends AsyncTask<Void, Void, Void>{
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        String selectedSort;
        String executionTime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            array_size_edittext.setText("");
            selectedSort = spinner.getSelectedItem().toString();
            progressDialog.setMessage("Calculating...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(selectedSort.equals("Select Algorithm")){
                Toast.makeText(MainActivity.this, "Please Select a Sort", Toast.LENGTH_SHORT).show();
            }
            else if(selectedSort.equals("Bubble Sort")){
                executionTime = Double.toString(SortingAlgorithms.bubbleSort(numbersArray));
            }
            else if( selectedSort.equals("Selection Sort") ){
                executionTime = Double.toString(SortingAlgorithms.selectionSort(numbersArray));
            }
            else if(selectedSort.equals("Insertion Sort")){
                executionTime = Double.toString(SortingAlgorithms.insertionSort(numbersArray));
            }
            else if(selectedSort.equals("Merge Sort")){
                executionTime = Double.toString(SortingAlgorithms.mergeSort(numbersArray));
            }
            else if(selectedSort.equals("Quick Sort")){
                executionTime = Double.toString(SortingAlgorithms.quickSort(numbersArray));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            execution_time.setText(executionTime);
            progressDialog.dismiss();
        }
    }
}
