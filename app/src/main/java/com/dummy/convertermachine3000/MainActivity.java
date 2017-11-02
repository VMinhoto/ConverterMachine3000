package com.dummy.convertermachine3000;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.dummy.convertermachine3000.utilities.ConverterJsonUtils;

import com.dummy.convertermachine3000.utilities.ConverterJsonUtils;
import com.dummy.convertermachine3000.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

import static com.dummy.convertermachine3000.utilities.ConverterJsonUtils.getSimpleConvertingStringFromJson;

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

        //TODO Dar handle de excepsions tipo o gajo meter números estúpidos. Necessário implementar double?
        final Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                makeSearchQuery();
            }

        });
    }


    //TODO Estes valores não são fixos. Implementar esta cena a mudar conforme selec.
    String currencyOne = "EUR";
    String currencyTwo = "USD";

    void makeSearchQuery(){


        URL searchUrl = NetworkUtils.buildUrl(currencyOne,currencyTwo);
        mURLDisplayTextView.setText(searchUrl.toString());
        String searchResults = null;
        new querryTask().execute(searchUrl);
    }


    //TODO Arrendondar a duas casas
    //TODO meter simbolos das moedas
    void convert(String s){
        float numberInserted = Float.parseFloat(mValueInsertedEditText.getText().toString());
        float conversionRate = Float.parseFloat(s);
        mValueConvertedTextView.setText(Float.toString(numberInserted*conversionRate));
    }




    //TODO Implementar LOADER- AsyncTask cria atividades zombies se se rodar enq isto dá load
    public class querryTask extends AsyncTask<URL, Void, String> {

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
                s=getSimpleConvertingStringFromJson(s,currencyTwo);
                mSearchDisplayTextView.setText(s);
                convert(s);
            }
        }
    }
}
