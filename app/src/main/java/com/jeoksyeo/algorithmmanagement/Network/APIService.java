package com.jeoksyeo.algorithmmanagement.Network;

import com.jeoksyeo.algorithmmanagement.Model.ProblemsOfClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("problem/class")
    Call<List<ProblemsOfClass>> getProblemsOfClass();

    @GET("site/stats")
    Call<Stats> getStats();


}
