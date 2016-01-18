package com.Apporio.realtysingh;

import android.content.Context;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Apporio.realtysingh.parsing_files.parsingforfeedback;
import com.Apporio.realtysingh.singleton.VolleySingleton;

import views.ProgressWheel;

/**
 * Created by saifi45 on 12/24/2015.
 */
public class Helpfragment extends Fragment {
public static EditText username, contacts, emails, feedback;
    public static TextView submit;
    public static ProgressWheel pb;
    SharedPreferences prefs;
    public static LinearLayout ll22;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_help, null);
        username = (EditText) root.findViewById(R.id.name);
        emails = (EditText) root.findViewById(R.id.email);
        contacts = (EditText) root.findViewById(R.id.password);
        feedback = (EditText) root.findViewById(R.id.mob);
        submit = (TextView) root.findViewById(R.id.login34);
        pb = (ProgressWheel) root.findViewById(R.id.progressBar3);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        InputMethodManager inputMethodManager=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.toggleSoftInputFromWindow(ll22.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
//

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                parsingforfeedback.parsing(getActivity(), username.getText().toString().trim(),
                        emails.getText().toString().trim(),
                        contacts.getText().toString().trim(),
                        feedback.getText().toString().trim(), prefs.getString("userid", null));
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

    @Override
    public void onResume() {
        super.onResume();
        username.postDelayed(
                new Runnable() {
                    public void run() {
                        username.requestFocus();
                        InputMethodManager inputMethodManager =  (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(username,InputMethodManager.SHOW_IMPLICIT);
                    }
                },100);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
