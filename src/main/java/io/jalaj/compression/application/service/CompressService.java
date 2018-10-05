package io.jalaj.compression.application.service;

import io.jalaj.compression.application.service.dto.CompressDTO;
import io.jalaj.compression.application.service.dto.DeCompressDTO;

import java.io.IOException;

public interface CompressService {
    void compress(CompressDTO compressService) throws IOException;

    void decompress(DeCompressDTO compressDTO) throws IOException;
}
