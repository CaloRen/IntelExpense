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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HomeFragment extends Fragment {

    String itemDesc;
    double expAmt;
    String expCategory;
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    DatabaseHelper dbh;
    GlobalVariables gv;

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
        gv = new GlobalVariables();

        TextView txtTotalExpensesAmt = getView().findViewById(R.id.txtTotalExpenseAmt);
        txtTotalExpensesAmt.setText(String.valueOf(nf.format(gv.getTotalExp())));

        TextView txtRemainingBalanceAmt = getView().findViewById(R.id.txtRemainingBalanceAmt);
        txtRemainingBalanceAmt.setText(String.valueOf(nf.format(gv.getRemBalAmt())));

        TextView txtSavingsAmountAmt = getView().findViewById(R.id.txtSavingsAmountAmt);
        txtSavingsAmountAmt.setText(String.valueOf(nf.format(gv.getRemSavAmt())));

        TextView txtClothingAmt = getView().findViewById(R.id.txtClothingAmt);
        txtClothingAmt.setText(String.valueOf(nf.format(gv.getExpClothing())));

        TextView txtEntertainmentAmt = getView().findViewById(R.id.txtEntertainmentAmt);
        txtEntertainmentAmt.setText(String.valueOf(nf.format(gv.getExpEntertainment())));

        TextView txtFoodAmt = getView().findViewById(R.id.txtFoodAmt);
        txtFoodAmt.setText(String.valueOf(nf.format(gv.getExpFood())));

        TextView txtGasAmt = getView().findViewById(R.id.txtGasAmt);
        txtGasAmt.setText(String.valueOf(nf.format(gv.getExpGas())));

        TextView txtGroceryAmt = getView().findViewById(R.id.txtGroceryAmt);
        txtGroceryAmt.setText(String.valueOf(nf.format(gv.getExpGrocery())));

        TextView txtInsuranceAmt = getView().findViewById(R.id.txtInsuranceAmt);
        txtInsuranceAmt.setText(String.valueOf(nf.format(gv.getExpInsurance())));

        TextView txtTransportationAmt = getView().findViewById(R.id.txtTranspoAmt);
        txtTransportationAmt.setText(String.valueOf(nf.format(gv.getExpTranspo())));

        TextView txtUtilitiesAmt = getView().findViewById(R.id.txtUtilAmt);
        txtUtilitiesAmt.setText(String.valueOf(nf.format(gv.getExpUtil())));

        FloatingActionButton fab1 = getView().findViewById(R.id.fab_action1);

        //OnClickListener for the Homescreen Floating Action Button
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder expBuilder = new AlertDialog.Builder(getActivity());
                //Launches the Add Expense dialog box
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

                //Records the expense in the database after clicking the Save button.
                expBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        itemDesc = txtExpDescription.getText().toString();
                        expAmt = Double.parseDouble(txtExpAmt.getText().toString());
                        expCategory = expCategoriesSpinner.getSelectedItem().toString();


                        //TOAST TEST FOR EXPENSE AMOUNT
                        gv.setTotalExp(expAmt);
                        Toast.makeText(getActivity(), "Expense Amount: " + String.valueOf(expAmt), Toast.LENGTH_SHORT).show();

                        //TOAST TEST FOR CURRENT DATE/TIME
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = dateFormat.format(date);
                        Toast.makeText(getActivity(), "Date/Time" + formattedDate, Toast.LENGTH_SHORT).show();

                        //TOAST TEST FOR EXPENSE CATEGORY
                        Toast.makeText(getActivity(), "Expense Category: " + expCategory, Toast.LENGTH_SHORT).show();


                        //FOR TESTING PURPOSES ONLY
                        boolean isAdded = dbh.addTransaction("user1", 3, formattedDate, expAmt, itemDesc);

                        if(isAdded){
                            Toast.makeText(getActivity(),"Expense added.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Expense not added. Please retry.",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });

                //Cancels the Add Expense dialog screen.
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
