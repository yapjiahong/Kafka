import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class FileSinkConfig extends AbstractConfig {

    public FileSinkConfig(Map<?,?> props){
        super(CONFIG_DEF, props);
    }

    //public static final ConfigDef CONFIG_DEF = new ConfigDef();
    public static final String FILE_CONFIG = "file";
    public static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(FILE_CONFIG, ConfigDef.Type.STRING, null,
                    ConfigDef.Importance.HIGH,
                    "Destination filename. If not specified, the standard output will be used");
}
