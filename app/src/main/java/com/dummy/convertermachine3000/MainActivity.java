package com.dummy.convertermachine3000;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dummy.convertermachine3000.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText mValueInsertedEditText;

    TextView mValueConvertedTextView;

    TextView mURLDisplayTextView;

    TextView mSearchDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mValueInsertedEditText = (EditText) findViewById(R.id.amount_edit_text);
        mValueConvertedTextView = (TextView) findViewById(R.id.value_of_first_experience_text_view);
        mURLDisplayTextView = (TextView) findViewById(R.id.made_URL_text_view);
        mSearchDisplayTextView = (TextView) findViewById(R.id.search_result_text_view);

        final Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                makeSearchQuery();
            }

        });
    }

    void makeSearchQuery(){
        //TODO Estes valores não são fixos. Implementar esta cena a mudar conforme selec.
        String currencyOne = "EUR";
        String currencyTwo = "CNY";
        URL searchUrl = NetworkUtils.buildUrl(currencyOne,currencyTwo);
        mURLDisplayTextView.setText(searchUrl.toString());
        String searchResults = null;
        new querryTask().execute(searchUrl);

    }

    //TODO Implementar LOADER- AsyncTask cria atividades zombies se se rodar enq isto dá load
    public class querryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String searchResults = null;
            try{
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            }catch (IOException e){
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")){
                mSearchDisplayTextView.setText(s);
            }
        }
    }
}
