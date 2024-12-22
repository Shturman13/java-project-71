package formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Json {
    public static String json(Map<String, Map<String, Object>> allParametersMap) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var jsonView = mapper.writeValueAsString(allParametersMap);
        return jsonView;
    }
}
