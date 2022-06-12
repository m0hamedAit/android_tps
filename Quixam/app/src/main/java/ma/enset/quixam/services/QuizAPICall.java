package ma.enset.quixam.services;

import java.util.List;

import ma.enset.quixam.model.Question;
import ma.enset.quixam.model.Quiz;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface QuizAPICall {
    @GET("quizes/{quizId}")
    Call<Quiz> getQuizInfo(@Path("quizId") String quizId);

    @GET("quizes/{quizId}/questions")
    Call<List<Question>> getQuizQuestions(@Path("quizId") String quizId);

    @POST("quizes")
    void addQuiz(@Body Quiz quiz);

    @PUT("quizes/{quizId}")
    void updateQuiz(@Path("quizId") String quizId, @Body Quiz quiz);




}
