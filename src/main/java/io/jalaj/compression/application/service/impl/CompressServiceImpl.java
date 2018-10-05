package io.jalaj.compression.application.service.impl;

import io.jalaj.compression.application.service.CompressService;
import io.jalaj.compression.application.service.dto.CompressDTO;
import io.jalaj.compression.application.service.dto.DeCompressDTO;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

@Service
public class CompressServiceImpl implements CompressService {
    private String defaultDir = "/home/ubuntu/";

    @Override
    public void compress(CompressDTO compressDTO) throws IOException {
        //Assign the original file : file to
        //FileInputStream for reading data
        FileInputStream fis = new FileInputStream(defaultDir + compressDTO.getInputDir());

        //Assign compressed file:file2 to FileOutputStream
        FileOutputStream fos = new FileOutputStream(defaultDir + compressDTO.getOutputDir());

        //Assign FileOutputStream to DeflaterOutputStream
        DeflaterOutputStream dos = new DeflaterOutputStream(fos);

        //read data from FileInputStream and write it into DeflaterOutputStream
        int data;
        while ((data = fis.read()) != -1) {
            dos.write(data);
        }

        //close the file
        fis.close();
        dos.close();
    }

    @Override
    public void decompress(DeCompressDTO deCompressDTO) throws IOException {
        //assign Input File : file2 to FileInputStream for reading data
        FileInputStream fis = new FileInputStream(defaultDir + deCompressDTO.getInputDir());

        //assign output file: file3 to FileOutputStream for reading the data
        FileOutputStream fos = new FileOutputStream(defaultDir + deCompressDTO.getOutputDir());

        //assign inflaterInputStream to FileInputStream for uncompressing the data
        InflaterInputStream iis = new InflaterInputStream(fis);

        //read data from inflaterInputStream and write it into FileOutputStream
        int data;
        while ((data = iis.read()) != -1) {
            fos.write(data);
        }

        //close the files
        fos.close();
        iis.close();
    }
}
