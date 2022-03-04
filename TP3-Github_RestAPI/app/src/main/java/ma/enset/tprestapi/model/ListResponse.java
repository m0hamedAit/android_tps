package ma.enset.tprestapi.model;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListResponse {
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("items")
    private List<UserGithub> users;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<UserGithub> getUsers() {
        return users;
    }

    public void setUsers(List<UserGithub> users) {
        this.users = users;
    }
}
