package sen.aboutme;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimePickFragment extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener {
    Calendar calendar = Calendar.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        TextView timeSet = (TextView) getActivity().findViewById(R.id.txt_time);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        //DateFormat timeFormat = DateFormat.getTimeInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeSet.setText(timeFormat.format(calendar.getTime()));
    }
}
