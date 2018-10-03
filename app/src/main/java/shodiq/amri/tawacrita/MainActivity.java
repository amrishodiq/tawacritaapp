package shodiq.amri.tawacrita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shodiq.amri.tawacrita.topic.KeyValue;
import shodiq.amri.tawacrita.topic.TopicModel;
import shodiq.amri.tawacrita.topic.TopicView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        TopicView topicView = findViewById(R.id.device);

        TopicModel model = new TopicModel("Device");
        model.items.add(new KeyValue("Model", "Redmi Node 4"));
        model.items.add(new KeyValue("OS Version", "8.0.0"));

        topicView.setModel(model);
    }
}
