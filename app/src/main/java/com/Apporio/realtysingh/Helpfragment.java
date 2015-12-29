package com.Apporio.realtysingh;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Apporio.realtysingh.parsing_files.parsingforfeedback;
import com.Apporio.realtysingh.singleton.VolleySingleton;

/**
 * Created by saifi45 on 12/24/2015.
 */
public class Helpfragment extends Fragment {
public static EditText username, contacts, emails, feedback;
    public static TextView submit;
    public static ProgressBar pb;
    SharedPreferences prefs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_help, null);
        username = (EditText)root.findViewById(R.id.name);
        emails = (EditText)root.findViewById(R.id.email);
        contacts = (EditText)root.findViewById(R.id.password);
        feedback = (EditText)root.findViewById(R.id.mob);
        submit = (TextView)root.findViewById(R.id.login34);
        pb = (ProgressBar)root.findViewById(R.id.progressBar3);
      prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsingforfeedback.parsing(getActivity(),username.getText().toString().trim(),
                        emails.getText().toString().trim(),
                        contacts.getText().toString().trim(),
                        feedback.getText().toString().trim(),prefs.getString("userid", null));
            }
        });





        return root;
    }

    public static Helpfragment newInstance(FragmentActivity activity ) {
        Helpfragment f = new Helpfragment();
        Bundle b = new Bundle();
        b.putString("msg22", "");
        //Log.e("possss",""+position);

        f.setArguments(b);

        return f;
    }
}
