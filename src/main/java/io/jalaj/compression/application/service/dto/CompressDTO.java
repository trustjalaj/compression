package io.jalaj.compression.application.service.dto;

public class CompressDTO extends DeCompressDTO {
    private Long maxSize;

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }
}
