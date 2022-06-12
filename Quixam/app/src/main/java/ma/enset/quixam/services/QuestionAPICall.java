package ma.enset.quixam.services;

import java.util.List;

import ma.enset.quixam.model.Answers;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuestionAPICall {

    @GET("questions/{questionId}/answers")
    Call<List<Answers>> getQuestionPropositions(@Path("questionId") String questionId);

}
