package io.jalaj.compression.application.web.rest;

import io.jalaj.compression.application.service.CompressService;
import io.jalaj.compression.application.service.dto.CompressDTO;
import io.jalaj.compression.application.service.dto.DeCompressDTO;
import io.jalaj.compression.application.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class CompressResource {

    private final Logger log = LoggerFactory.getLogger(CompressResource.class);
    @Autowired
    private CompressService compressService;

    @PostMapping("/compress")
    public ResponseEntity<Void> compress(@Valid @RequestBody CompressDTO compressDTO) throws URISyntaxException {
        try {
            compressService.compress(compressDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.created(new URI("/api/compress/"))
            .headers(HeaderUtil.createAlert("All files are compressed successfully " + null, null))
            .body(null);
    }

    @PostMapping("/decompress")
    public ResponseEntity<Void> decompress(@Valid @RequestBody DeCompressDTO deCompressDTO) throws URISyntaxException {
        try {
            compressService.decompress(deCompressDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.created(new URI("/api/decompress/"))
            .headers(HeaderUtil.createAlert("All files are decompressed successfully " + null, null))
            .body(null);
    }
}
