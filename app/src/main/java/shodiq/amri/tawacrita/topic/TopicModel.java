package shodiq.amri.tawacrita.topic;

import java.util.ArrayList;
import java.util.List;

public class TopicModel {
    public String title;
    public List<KeyValue> items;

    public TopicModel(String title) {
        this.title = title;
        this.items = new ArrayList<>();
    }
}
