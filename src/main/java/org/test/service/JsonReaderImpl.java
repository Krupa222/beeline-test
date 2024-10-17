package org.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.test.entity.dto.JsonDTO;
import org.test.interfaces.JsonReader;

import java.io.File;
import java.io.IOException;

public class JsonReaderImpl implements JsonReader {
    @Override
    public JsonDTO getJsonData(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonDTO jsonDTO = new JsonDTO();
        try {

            jsonDTO = objectMapper.readValue(new File(path), JsonDTO.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonDTO;
    }
}
