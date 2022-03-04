package ma.enset.tprestapi.service;

import java.util.List;

import ma.enset.tprestapi.model.ListResponse;
import ma.enset.tprestapi.model.UserGithub;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestServiceAPI {
    @GET("search/users")
    Call<ListResponse> getUsersbyKey(@Query("q") String key);
    //@GET("users/")
    //Call<List<UserGithub>> getUsersPerPage(@Query("per_page") int pageSize,@Query("page") int currentPage);
    @GET("users")
    Call<List<UserGithub>> getUsers();
    @GET("users/{login}")
    Call<UserGithub> getUser(@Path(value="login") String login);
}
