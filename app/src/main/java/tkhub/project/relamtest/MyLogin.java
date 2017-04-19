package tkhub.project.relamtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Himanshu on 3/15/2017.
 */

public class MyLogin extends AppCompatActivity {

    private Realm mRealm;
    Button button;
    EditText editTextName,editTextAge,editText3;
    ListView listview,listview2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button =(Button)findViewById(R.id.button);
        editTextName =(EditText)findViewById(R.id.editText);
        editTextAge =(EditText)findViewById(R.id.editText2);
        editText3 =(EditText)findViewById(R.id.editText3);




        listview =(ListView)findViewById(R.id.listview);
        listview2 =(ListView)findViewById(R.id.listview2);



        Realm.init(this);
        RealmConfiguration config0 = new RealmConfiguration.Builder()
                .name("default0.realm")
                .schemaVersion(3)
                .build();
        try {
            Realm.migrateRealm(config0, new Migration());
        } catch (FileNotFoundException ignored) {
        }
        mRealm = Realm.getInstance(config0);


        setdata();
        setdata2();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        User myDog = realm.createObject(User.class);
                        myDog.setName(editTextName.getText().toString());
                        myDog.setAge(Integer.parseInt(editTextAge.getText().toString()));
                        myDog.setId(Integer.parseInt(editText3.getText().toString()));

                        setdata();
                        setdata2();
                    }
                });
            }
        });


    }
    private void setdata(){

        final ArrayList<String> list = new ArrayList<String>();
        for (User no : mRealm.where(User.class).findAll()) {
            list.add(no.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_view_row_item, list);

        listview.setAdapter(adapter);


    }

    private void setdata2(){

        final ArrayList<String> list = new ArrayList<String>();
        for (User no : mRealm.where(User.class).findAll()) {
            list.add(String.valueOf(no.getSex()));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_view_row_item, list);

        listview2.setAdapter(adapter);


    }
}
