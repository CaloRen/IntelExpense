package intelexpense.intelexpense;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;

public class GoalsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    DatabaseHelper dbh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_goals, container, false);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        Button btnDate = (Button)getView().findViewById(R.id.btnDate);
        btnDate.setText(currentDate);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab2 = getView().findViewById(R.id.fab_action2);

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder goalBuilder = new AlertDialog.Builder(getActivity());
                View goalsView = getLayoutInflater().inflate(R.layout.addgoal_dialog, null);
                goalBuilder.setTitle("Add Goal");
                goalBuilder.setView(v);

                Calendar calendar = Calendar.getInstance();
                final String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

                Button btnDate = goalsView.findViewById(R.id.btnDate);
                btnDate.setText(currentDate);

                /*btnDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment datePicker = new DatePickerFragment();
                        datePicker.show(getActivity().getSupportFragmentManager(), "date picker");
                    }
                });*/

                goalBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isAdded = dbh.addEventwiseSaving("user1", "XBox One X", "XBox One X is the best", 499.99, 200.00);

                        if(isAdded){
                            Toast.makeText(getActivity(),"Goal added.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Goal not added. Please retry.",Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });

                goalBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                goalBuilder.setView(goalsView);
                AlertDialog dialog = goalBuilder.create();
                dialog.show();
            }
        });
    }
}
