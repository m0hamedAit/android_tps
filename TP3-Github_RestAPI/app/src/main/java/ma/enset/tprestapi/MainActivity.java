package ma.enset.tprestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import ma.enset.tprestapi.model.ListResponse;
import ma.enset.tprestapi.model.UserGithub;
import ma.enset.tprestapi.model.UserItemAdapter;
import ma.enset.tprestapi.service.RestServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        SearchView searchview = (SearchView) findViewById(R.id.searchBar);


        List<UserGithub> list = new ArrayList<>();
        UserItemAdapter adapter = new UserItemAdapter(this,  R.layout.user_item, list);
        listView.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        RestServiceAPI restServiceAPI = retrofit.create(RestServiceAPI.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        list.clear();
        Call<List<UserGithub>>  usersCall = restServiceAPI.getUsers();
        usersCall.enqueue(new Callback<List<UserGithub>>() {
            @Override
            public void onResponse(Call<List<UserGithub>> call, Response<List<UserGithub>> response) {
                List<UserGithub> listUsers=response.body();
                for (UserGithub user:listUsers) {
                    list.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<UserGithub>> call, Throwable t) {
                Log.e("error","Erreur de réseau");
            }
        });

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                list.clear();
                String key = s;
                if(s.length()==0){
                    list.clear();
                    Call<List<UserGithub>>  usersCall = restServiceAPI.getUsers();
                    usersCall.enqueue(new Callback<List<UserGithub>>() {
                        @Override
                        public void onResponse(Call<List<UserGithub>> call, Response<List<UserGithub>> response) {
                            List<UserGithub> listUsers=response.body();
                            for (UserGithub user:listUsers) {
                                list.add(user);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<List<UserGithub>> call, Throwable t) {
                            Log.e("error","Erreur de réseau");
                        }
                    });
                }
                else{
                    Call<ListResponse> callUsers = restServiceAPI.getUsersbyKey(key);
                    callUsers.enqueue(new Callback<ListResponse>() {
                        @Override
                        public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                            try {
                                ListResponse listUsers = response.body();
                                for (UserGithub user : listUsers.getUsers()) {
                                    list.add(user);
                                }
                                adapter.notifyDataSetChanged();
                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onFailure(Call<ListResponse> call, Throwable t) {
                            Log.e("error", "Erreur de réseau");
                        }
                    });
                }

                return false;
            }
        });



    }
}