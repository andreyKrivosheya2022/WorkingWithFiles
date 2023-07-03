package fileInterfeces;

import java.io.IOException;

public interface FileSerializeDeserialize {

    public void serializeObjectToJson(String linkToFile, Object obj) throws IOException;
        Object deserializeJsonToObject(String linkToFile) throws IOException, ClassNotFoundException;
}
