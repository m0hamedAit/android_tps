package ma.enset.quixam.services;

import java.util.List;

import ma.enset.quixam.model.PageStudents;
import ma.enset.quixam.model.Quiz;
import ma.enset.quixam.model.Student;
import ma.enset.quixam.model.Teacher;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentAPICall {

    @GET("students/{studentId}/quizes")
    Call<List<Quiz>> getStudentQuizes(@Path("studentId") String studentId);

    @GET("students")
    Call<PageStudents> getAllStudents();
}
