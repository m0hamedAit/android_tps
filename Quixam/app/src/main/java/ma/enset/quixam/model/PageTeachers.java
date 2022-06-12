package ma.enset.quixam.model;

import java.util.List;

public class PageTeachers {
    private List<Teacher> data;
    private int page;
    private int coutPerPage;
    private int total;


    public List<Teacher> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getCoutPerPage() {
        return coutPerPage;
    }

    public int getTotal() {
        return total;
    }
}
