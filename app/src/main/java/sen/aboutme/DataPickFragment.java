package sen.aboutme;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataPickFragment extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        TextView dateSet = (TextView) getActivity().findViewById(R.id.txt_date);
        GregorianCalendar gl = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateSet.setText(dateFormat.format(gl.getTime()));
    }
}
