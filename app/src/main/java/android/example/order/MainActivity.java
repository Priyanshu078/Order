package android.example.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int numberofCoffee = 1;
    //ppc  price per cup

    public void submitOrder(View view){
        String name = getName();
        int price = displayPrice(numberofCoffee);
        Boolean checked = WhippedCream();
        String summary = "Name: "+ name +"\n"+
                "Add Whiped Cream? "+ checked+"\n"+
                "Add Chocolate? "+ Chocolate()+"\n"+
                "Number of Coffees ordered: " + numberofCoffee +"\n"+
                "Total: $" + price +"\n"+
                "Thanks for Ordering";
////        displayPrice(numberofCoffee);
////        String message = "Total: "+ numberofCoffee+"\n"+"Thank You!!!";
////        displayMessage(message);
//        displaySummary(summary,price);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // we write this if we only want to use email app in our phone
//        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TEXT,summary); // this is used to give the text which will be there in the bottom of the email.
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order by "+name);// this text will be there in the subject of the email
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);// this is to start the intent
        }
    }
    public void increment(View view){
        numberofCoffee += 1;
        display(numberofCoffee);
        displayPrice(numberofCoffee);
    }
    public void decrement(View view){
        numberofCoffee -= 1;
        if (numberofCoffee < 1){
            numberofCoffee = 1;
            Toast toast = Toast.makeText(this,"1 is the least number of Coffee you can Order", Toast.LENGTH_SHORT);
            toast.show();
        }
        display(numberofCoffee);
        displayPrice(numberofCoffee);
    }

    public void display(int number){
        TextView numberTextView = (TextView) findViewById(R.id.number_text_view);
        numberTextView.setText("" + number);
    }

    public int displayPrice(int numberofCoffee){
        TextView priceTextView = (TextView) findViewById(R.id.Summary);
        int ppc = 5;
        int price;
        Boolean WhippedCream = WhippedCream();
        Boolean Chocolate = Chocolate();
        if (WhippedCream == true && Chocolate == true){
            price = numberofCoffee * ppc + numberofCoffee*1 + numberofCoffee*2;
        }
        else if(WhippedCream == true && Chocolate == false){
            price = numberofCoffee*ppc + numberofCoffee*1;
        }
        else if (WhippedCream == false && Chocolate == true){
            price = numberofCoffee*ppc + numberofCoffee*2;
        }
        else{
            price = numberofCoffee*ppc;
        }
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
        return price;
    }

    public void displaySummary(String message,int price){
        TextView Summary = (TextView) findViewById(R.id.Summary);
        Summary.setText("" + message);
    }

    public boolean WhippedCream(){
        CheckBox check = (CheckBox) findViewById(R.id.WhippedCream);
        Boolean checked;
        if (check.isChecked()){
            checked = true;
        }
        else{
            checked = false;
        }
        return checked;
    }
    private boolean Chocolate(){
        CheckBox Chocolate = (CheckBox) findViewById(R.id.Chocolate);
        Boolean NeedChocolate;
        if (Chocolate.isChecked()){
            NeedChocolate = true;
        }
        else{
            NeedChocolate = false;
        }
        return NeedChocolate;
    }
    private String getName(){
        EditText text = (EditText) findViewById(R.id.Name);
        String Name = text.getText().toString();
        return Name;
    }

//    public void displayMessage(String message){
//        TextView Message = (TextView) findViewById(R.id.price_text_View);
//        Message.setText("" + message);
//    }

//    public void incrementCoffee(int numberofCoffee){
//        TextView increment = (TextView) findViewById(R.id.number_text_view);
//        increment.setText(""+numberofCoffee);
//    }
}