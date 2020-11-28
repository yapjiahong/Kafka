import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileSinkConnector extends SinkConnector {

    String filename;
    public static final String FILE_CONFIG = "file";

    private Map<String, String > configProps;

    @Override
    public void start(Map<String, String> props) {
        //filename = props.get(FILE_CONFIG);
        //filename = props;
        configProps = props;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return FileSinkTask.class;
        //return null;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        // TODO Auto-generated method stub
        ArrayList<Map<String, String>> configs = new ArrayList<Map<String, String>>();

//         for (int i = 0; i < maxTasks; i++) {
//             Map<String, String> config = new HashMap<String, String>();
//             if (filename != null)
//                 config.put(FILE_CONFIG, filename);
//             configs.add(config);
//         }

        for(int count = 0; count < maxTasks; ++count){
            configs.add(configProps);
        }
        return configs;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        //return null;
        return FileSinkConfig.CONFIG_DEF;
    }

    @Override
    public String version() {
        //return null;
        return AppInfoParser.getVersion();
    }
}
