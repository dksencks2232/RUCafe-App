package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class donut_order extends AppCompatActivity {

    public static Order donutOrder = new Order();
    public static final int INITIAL_QUANTITY = 1;

    private RecyclerView donutList;
    private RecyclerView donutOrderRecyclerView;
    private donutAdapter donutAdapter;
    private RecyclerView.LayoutManager donutLayout;

    private TextView donutOrderSubtotal;
    private TextView subTotalName;

    private ArrayList<Donut> donuts = new ArrayList<>();

    private int [] donutImages = {R.drawable.apple, R.drawable.banana, R.drawable.grapes,
            R.drawable.mango, R.drawable.orange, R.drawable.peach, R.drawable.pineapple,
            R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems();


        donutOrderSubtotal = findViewById(R.id.donutOrderSubtotal);
        subTotalName = findViewById(R.id.subTotalText);

        donutAdapter adapter = new donutAdapter(this,donuts);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));

        donutOrderSubtotal.setText(String.format("$"+"%.2f", donutOrder.orderPrice()));
        //donutOrderSubtotal.setText();

        //subTotalName.setText("Sub Total:");


    }

    private void updateBalance(){

        double sum = 0;
        for (MenuItem items: donutOrder.getOrder()){

            sum+= items.itemPrice();

        }
        donutOrderSubtotal.setText(String.format("$"+"%.2f", donutOrder.orderPrice()));
        //donutAdapter.notifyDataSetChanged();

    }



    private void setupMenuItems() {

        String [] donutFlavors = getResources().getStringArray(R.array.donut_names);
        String [] donutTypes = getResources().getStringArray(R.array.donut_types);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < donutFlavors.length; i++) {
            donuts.add(new Donut(donutTypes[i] ,donutFlavors[i]  ,donutImages[i] ));
            //
        }

        donutOrderRecyclerView = findViewById(R.id.donutListView);
        donutOrderRecyclerView.setHasFixedSize(true);
        donutLayout = new LinearLayoutManager(this);

    }



    public void removeItem(int position) {
        donutOrder.getOrder().remove(position);
        donutAdapter.notifyItemRemoved(position);
        updateBalance();
    }

    public void onDeleteClick(int position){removeItem(position);}


    public void  onItemSelected(AdapterView<?> parent, View view, int position, long id){

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();


    }

}