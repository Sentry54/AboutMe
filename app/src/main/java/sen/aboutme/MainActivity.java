package sen.aboutme;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    ImageView image;
    TextView fullName, email, phone;
    ArrayList<ArrayList<String>> skillKnow, privatInfo, education;
    ExpandableListView expSkillKnow, expPrivatInfo, expEducation;
    ClipboardManager mClipboardManager;
    ClipData mClipData;
    StringBuilder personalData;
    final String LOG = getString(R.string.txt_log);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    public void initUI() {

        image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.image);

        fullName = (TextView) findViewById(R.id.txt_full_name);
        personalData = new StringBuilder().
                append(getString(R.string.txt_surname) + " " + getString(R.string.txt_name) + " " +
                        getString(R.string.txt_patronymic));
        fullName.setText(personalData);

        email = (TextView) findViewById(R.id.txt_email);
        phone = (TextView) findViewById(R.id.txt_phone);

        education = new ArrayList<>();
        education.add(new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.education))));

        expEducation = (ExpandableListView) findViewById(R.id.exp_education);
        ExpListAdapter adapterE = new ExpListAdapter(getApplicationContext(), education);
        expEducation.setAdapter(adapterE);

        skillKnow = new ArrayList<>();
        skillKnow.add(new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.skill_knowledge))));

        expSkillKnow = (ExpandableListView) findViewById(R.id.exp_skill_know);
        ExpListAdapter adapterSK = new ExpListAdapter(getApplicationContext(), skillKnow);
        expSkillKnow.setAdapter(adapterSK);

        privatInfo = new ArrayList<>();
        privatInfo.add(new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.privat_info))));

        expPrivatInfo = (ExpandableListView) findViewById(R.id.exp_privat_info);
        ExpListAdapter adapterPI = new ExpListAdapter(getApplicationContext(), privatInfo);
        expPrivatInfo.setAdapter(adapterPI);

        //Toast.makeText(this, getString(R.string.txt_orientation_txt), Toast.LENGTH_SHORT).show();
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
                getCopyData(R.string.txt_menu_copy_all);
                break;
            case R.id.menu_copy_name:
                getCopyData(R.string.txt_menu_copy_name);
                break;
            case R.id.menu_copy_email:
                getCopyData(R.string.txt_menu_email);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getCopyData(int string){
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        Log.d(LOG, getString(string));
        mClipData = ClipData.newPlainText("\"" + getString(string)+ "\"",getString(string));
        mClipboardManager.setPrimaryClip(mClipData);
    }
}
