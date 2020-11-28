import com.google.gson.Gson;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class FileSinkTask  extends SinkTask {

    private String filename;
    private PrintStream outputStream;

    @Override
    public String version() {
        return null;
    }

    @Override
    public void start(Map<String, String> props) {
        filename = props.get(FileSinkConnector.FILE_CONFIG);
        if (filename == null) {
            outputStream = System.out;
        } else {
            try {
                outputStream = new PrintStream(new FileOutputStream(filename, true), false,
                        StandardCharsets.UTF_8.name());
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                throw new ConnectException("Couldn't find or create file for FileStreamSinkTask", e);
            }
        }
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        for(SinkRecord record : records){
            outputStream.println("neteased file connect: ");
            outputStream.println(record.value());

            outputStream.println("Done.");
        }
    }

    @Override
    public void stop() {
        if(outputStream != null && outputStream != System.out){
            outputStream.close();
        }
    }
}

