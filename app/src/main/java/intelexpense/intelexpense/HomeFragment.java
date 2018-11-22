package intelexpense.intelexpense;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;


public class HomeFragment extends Fragment {

    //FOR TESTING PURPOSES ONLY
    double totalExp = 0;
    double remBalAmt = 100.00;
    double remSavAmt = 0;
    double expClothing = 0;
    double expEntertainment = 0;
    double expFood = 0;
    double expGas = 0;
    double expGrocery = 0;
    double expInsurance = 0;
    double expTranspo = 0;
    double expUtil = 0;
    String itemDesc;
    double expAmt;
    String expCategory;
    NumberFormat nm = NumberFormat.getCurrencyInstance();

    DatabaseHelper dbh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbh = new DatabaseHelper(getActivity());

        final TextView txtTotalExpensesAmt = (TextView)getView().findViewById(R.id.txtTotalExpenseAmt);
        TextView txtRemainingBalanceAmt = (TextView)getView().findViewById(R.id.txtRemainingBalanceAmt);
        TextView txtSavingsAmountAmt = (TextView)getView().findViewById(R.id.txtSavingsAmountAmt);

        TextView txtClothingAmt = (TextView)getView().findViewById(R.id.txtClothingAmt);
        TextView txtEntertainmentAmt = (TextView)getView().findViewById(R.id.txtEntertainmentAmt);
        TextView txtFoodAmt = (TextView)getView().findViewById(R.id.txtFoodAmt);
        TextView txtGasAmt = (TextView)getView().findViewById(R.id.txtGasAmt);
        TextView txtGroceryAmt = (TextView)getView().findViewById(R.id.txtGroceryAmt);
        TextView txtInsuranceAmt = (TextView)getView().findViewById(R.id.txtInsuranceAmt);
        TextView txtTransportationAmt = (TextView)getView().findViewById(R.id.txtTranspoAmt);
        TextView txtUtilitiesAmt = (TextView)getView().findViewById(R.id.txtUtilAmt);

        FloatingActionButton fab1 = getView().findViewById(R.id.fab_action1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder expBuilder = new AlertDialog.Builder(getActivity());
                View expView = getLayoutInflater().inflate(R.layout.addexpense_dialog, null);
                expBuilder.setTitle("Add Expense");
                expBuilder.setView(v);

                final EditText txtExpDescription = (EditText)expView.findViewById(R.id.txtExpDescription);
                final EditText txtExpAmt = (EditText)expView.findViewById(R.id.txtExpAmt);
                final Spinner expCategoriesSpinner = (Spinner)expView.findViewById(R.id.expCategoriesSpinner);
                ArrayAdapter<String> expCategoriesArray = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_dropdown_item,
                        getResources().getStringArray(R.array.expCategoriesArray));
                expCategoriesArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                expCategoriesSpinner.setAdapter(expCategoriesArray);

                expBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*Toast.makeText(getActivity(), txtExpDescription.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), txtExpAmt.getText(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), expCategoriesSpinner.getSelectedItem().toString(),
                                Toast.LENGTH_SHORT).show();*/

                        itemDesc = txtExpDescription.getText().toString();
                        expAmt = Double.parseDouble(txtExpAmt.getText().toString());
                        expCategory = expCategoriesSpinner.getSelectedItem().toString();

                        totalExp += expAmt;

                        //FOR TESTING PURPOSES ONLY
                        //dbh.updateTransaction(1, "user", Integer.parseInt(expCategory), "11/20/2018",expAmt, itemDesc);

                        //FOR TESTING PURPOSES ONLY
                        boolean isAdded = dbh.addTransaction("user1", 3, "11/20/2018", expAmt, itemDesc);

                        if(isAdded){
                            Toast.makeText(getActivity(),"Expense added.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Expense not added. Please retry.",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });

                expBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                expBuilder.setView(expView);
                AlertDialog dialog = expBuilder.create();
                dialog.show();
            }
        });
    }
}
