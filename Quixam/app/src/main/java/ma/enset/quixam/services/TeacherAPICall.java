package ma.enset.quixam.services;

import java.util.List;

import ma.enset.quixam.model.PageTeachers;
import ma.enset.quixam.model.Question;
import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.model.Teacher;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TeacherAPICall {
    @GET("teachers")
    Call<PageTeachers> getAllTeachers();

    @GET("teachers/{teacherId}/quizes")
    Call<List<Quiz>> getTeacherQuizes(@Path("teacherId") String teacherId);


}
