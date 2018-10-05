package io.jalaj.compression.application.service;

import io.jalaj.compression.application.service.dto.CompressDTO;
import io.jalaj.compression.application.service.dto.DeCompressDTO;

public interface CompressService {
    void compress(CompressDTO compressService);

    void decompress(DeCompressDTO compressDTO);
}
