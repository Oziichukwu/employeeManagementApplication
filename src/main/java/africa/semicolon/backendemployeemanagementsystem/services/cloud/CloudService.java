package africa.semicolon.backendemployeemanagementsystem.services.cloud;

import java.io.IOException;
import java.util.Map;

public interface CloudService {

    Map<?,?> upload(byte[]bytes, Map<?,?> params) throws IOException;

}
