package org.test.interfaces;

import org.test.entity.dto.JsonDTO;

public interface JsonReader {
    JsonDTO getJsonData(String path);
}
