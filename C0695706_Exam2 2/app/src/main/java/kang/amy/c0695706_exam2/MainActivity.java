package kang.amy.c0695706_exam2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    EditText employeId;
    EditText employeName;
    EditText employeDob;
    EditText employeSalary;
    Button saveButton;

    private Realm realmEmploye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        employeId=(EditText)findViewById(R.id.empId);
        employeName=(EditText)findViewById(R.id.empName);
        employeDob=(EditText)findViewById(R.id.empDob);
        employeSalary=(EditText)findViewById(R.id.empSalary);

        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                save();
            }
        });



        //RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        //Realm.deleteRealm(realmConfiguration);
        //realmUser = Realm.getInstance(realmConfiguration);
        realmEmploye = Realm.getDefaultInstance();

    }

    public void save() {
        Log.d(TAG, "Save");

        if (!validate()) {
            onSaveFailed();
            return;
        }

        saveButton.setEnabled(false);



        final String id = employeId.getText().toString();
        final String name = employeName.getText().toString();
        final String dob = employeDob.getText().toString();
        final String salary = employeSalary.getText().toString();


        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable()
                {
                    public void run()
                    {
                        onSignupSuccess(id,name, dob, salary);
                        // onSignupFailed();
                    }
                }, 3000);
    }

    public void onSignupSuccess(final String id, final String name, final String dob,final String salary)
    {
        //Add new user record
        final Employe mEmploye = new Employe();
        mEmploye.setEmployeId(id);
        mEmploye.setEmployeName(name);
        mEmploye.setEmployeDob(dob);
        mEmploye.setEmployeSalary(salary);


        //realmUser.createObject(User.class, mUser);

        realmEmploye.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {
                try
                {
                    // This will create a new object in Realm or throw an exception if the
                    // object already exists (same primary key)
                    realmEmploye.copyToRealm(mEmploye);

                    // This will update an existing object with the same primary key
                    // or create a new object if an object with no primary key
                    //realmUser.copyToRealmOrUpdate(mUser);
                } catch(RealmPrimaryKeyConstraintException | RealmException re)
                {
                    Toast.makeText(MainActivity.this, "Employe Already exist", Toast.LENGTH_LONG).show();
                }

            }
        });
        saveButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSaveFailed()
    {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        saveButton.setEnabled(true);
    }




}




