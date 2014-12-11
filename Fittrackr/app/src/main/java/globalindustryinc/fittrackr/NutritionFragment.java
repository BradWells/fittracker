package globalindustryinc.fittrackr;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import globalindustryinc.fittrackr.FoodItem;


public class NutritionFragment extends android.support.v4.app.Fragment implements OnItemSelectedListener{

    ArrayList<FoodItem> foodItems;
    private Spinner foodSpinner;
    private Button btnAdd;
    int calorieTotal = 0;
    double fatTotal = 0.0;
    int carbTotal = 0;

    TextView t;
    TextView text1;
    TextView text2;
    TextView text3;

    private String sText1;
    private String sText2;
    private String sText3;
    
    View inflated;

    private Handler mHandler = new Handler();

    private Runnable mWaitRunnable = new Runnable() {
        public void run() {
            text1.setText(sText1);
            text2.setText(sText2);
            text3.setText(sText3);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodItems = new ArrayList<FoodItem>();
        
        inflated = inflater.inflate(R.layout.activity_nutrition, container, false);
        //setUpFoodSpinner();

        addItemsOnSpinner();
        addListenerOnButton();
        //addListenerOnSpinnerItemSelection();

        text1 = new TextView(getActivity());
        text2 = new TextView(getActivity());
        text3 = new TextView(getActivity());
        text1 = (TextView)inflated.findViewById(R.id.textView1);
        text2 = (TextView)inflated.findViewById(R.id.textView2);
        text3 = (TextView)inflated.findViewById(R.id.textView3);

        sText1 = "" + calorieTotal;
        text1.setText(sText1);

        sText2 = "" + fatTotal;
        text2.setText(sText2);

        sText3 = "" + carbTotal;
        text3.setText(sText3);

        return inflated;

    }

    public void addItemsOnSpinner() {

        foodSpinner = (Spinner) inflated.findViewById(R.id.spinner1);
        List<String> foodList = new ArrayList<String>();
        foodList.add("Apple");
        foodList.add("Slice of pizza");
        foodList.add("Glass of orange juice");
        foodList.add("Can of soda");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, foodList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {

    }

    public void addListenerOnButton() {

        foodSpinner = (Spinner) inflated.findViewById(R.id.spinner1);

        btnAdd = (Button) inflated.findViewById(R.id.button1);

        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(foodSpinner.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();

                calculateFoodItems();
            }
        });
    }

    private void setUpFoodSpinner() {
        Spinner foodSpinner = (Spinner) inflated.findViewById(R.id.spinner1);
        foodSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.food_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        foodSpinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void calculateFoodItems() {

        String currentFood = String.valueOf(foodSpinner.getSelectedItem());

        FoodItem currentFoodItem = null;

        for(int i = 0; i < foodItems.size(); i++) {
            if(foodItems.get(i).getName().equals(currentFood)){
                currentFoodItem = foodItems.get(i);
            }
        }
        calorieTotal = calorieTotal + currentFoodItem.getCalories();
        fatTotal = fatTotal + currentFoodItem.getFat();
        carbTotal = carbTotal + currentFoodItem.getCarbs();

    }

    private void populateFoodItems() {
        //String name, int calories (calories), int fat (grams), int carbs (grams)
        foodItems.add(new FoodItem("Apple", 95, 0.3, 25));
        foodItems.add(new FoodItem("Slice of pizza", 285, 10, 36));
        foodItems.add(new FoodItem("Glass of orange juice", 39, 0.2, 9));
        foodItems.add(new FoodItem("Can of soda", 138, 0.1, 35));
    }

}
