package sen.aboutme;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    String LOG, personalData;
    Resources res;
    ImageView image;
    TextView fullName, email, phone;
    ArrayList<String> titleArray;
    ArrayList<ArrayList<String>> myData;
    ExpandableListView expMyData;
    ClipboardManager mClipboardManager;
    ClipData mClipData;
    DataPickFragment dataFragment;
    TimePickFragment timeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    public void initUI() {

        res = getResources();
        LOG = res.getString(R.string.txt_log);

        image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.image);

        personalData = getString(R.string.txt_surname) + " " + getString(R.string.txt_name) + " " + getString(R.string.txt_patronymic);
        fullName = (TextView) findViewById(R.id.txt_full_name);
        fullName.setText(personalData);

        email = (TextView) findViewById(R.id.txt_email);
        phone = (TextView) findViewById(R.id.txt_phone);

        myData = new ArrayList<>();
        myData.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.education))));
        myData.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.skill_knowledge))));
        myData.add(new ArrayList<>(Arrays.asList(res.getStringArray(R.array.privat_info))));

        titleArray = new ArrayList<>();
        titleArray.add(getString(R.string.label_Education));
        titleArray.add(getString(R.string.label_Skill_Knowledge));
        titleArray.add(getString(R.string.label_Privat_Info));

        expMyData = (ExpandableListView) findViewById(R.id.exp_my_data);
        ExpListAdapter adapterData = new ExpListAdapter(titleArray, myData);
        expMyData.setAdapter(adapterData);

        dataFragment = new DataPickFragment();

        timeFragment = new TimePickFragment();

        //Toast.makeText(this, getString(R.string.txt_my_txt), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_copy_all:
                //TODO сделать копирование всего содержимого
                //getCopyData(personalData.toString());
                Log.d(LOG, getString(R.string.txt_menu_copy_all));
                Toast.makeText(getApplicationContext(), getString(R.string.txt_toast_menu_copy_all), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_copy_name:
                getCopyData(personalData);
                Log.d(LOG, getString(R.string.txt_menu_copy_name));
                Toast.makeText(getApplicationContext(), getString(R.string.txt_toast_menu_copy_name), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_copy_email:
                getCopyData(getString(R.string.txt_email));
                Log.d(LOG, getString(R.string.txt_menu_email));
                Toast.makeText(getApplicationContext(), getString(R.string.txt_toast_menu_email), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_date_time:
                break;
            case R.id.menu_date:
                dataFragment.show(getSupportFragmentManager(), "dataPicker");
                Log.d(LOG, getString(R.string.txt_menu_date));
                break;
            case R.id.menu_time:
                timeFragment.show(getSupportFragmentManager(), "timePicker");
                Log.d(LOG, getString(R.string.txt_menu_time));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getCopyData(String text) {
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mClipData = ClipData.newPlainText("\"" + text + "\"", text);
        mClipboardManager.setPrimaryClip(mClipData);
    }
}