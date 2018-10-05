package io.jalaj.compression.application.service.impl;

import io.jalaj.compression.application.service.CompressService;
import io.jalaj.compression.application.service.dto.CompressDTO;
import io.jalaj.compression.application.service.dto.DeCompressDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Service
public class CompressServiceImpl implements CompressService {
    private String defaultDir = "/home/jalaj/";

    public static List<String> listFilesForFolder(File folder) {
        List<String> fileNameList = new ArrayList<>();
        if (folder != null) {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                } else {
                    fileNameList.add(fileEntry.getName());
                }
            }
        }
        return fileNameList;
    }

    @Override
    public void compress(CompressDTO compressDTO) throws IOException {
        String inputDir = defaultDir + compressDTO.getInputDir();
        String outputDir = defaultDir + compressDTO.getOutputDir();
        File folder = new File(inputDir);
        final int BUFFER = 104857600; // 100MB
        final long MAX_ZIP_SIZE = compressDTO.getMaxSize();
        long currentSize = 0;
        int zipSplitCount = 0;
        byte fileRAW[] = new byte[BUFFER];
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(outputDir + "ZipFileName"));
        ZipEntry zipEntry;
        FileInputStream entryFile;
        List<String> list = listFilesForFolder(folder);
        if (list != null) {
            for (String aFile : list) {
                zipEntry = new ZipEntry(aFile);
                if (currentSize >= MAX_ZIP_SIZE) {
                    zipSplitCount++;
                    //zipOut.closeEntry();
                    zipOut.close();
                    zipOut = new ZipOutputStream(new FileOutputStream(outputDir + "ZipFileName"));
                    currentSize = 0;
                }
                zipOut.putNextEntry(zipEntry);
                entryFile = new FileInputStream(inputDir + "/" + aFile);

                int count;
                while ((count = entryFile.read(fileRAW, 0, BUFFER)) != -1) {
                    zipOut.write(fileRAW, 0, count);
                }
                entryFile.close();
                zipOut.closeEntry();
                currentSize += zipEntry.getCompressedSize();
            }
        }
        zipOut.close();
    }

    @Override
    public void decompress(DeCompressDTO deCompressDTO) throws IOException {
        String inputDir = defaultDir + deCompressDTO.getInputDir() + "/";
        String outputDir = defaultDir + deCompressDTO.getOutputDir() + "/";
        File folder = new File(inputDir);
        List<String> list = listFilesForFolder(folder);
        if (list != null) {
            for (String aFile : list) {
                String fileZip = deCompressDTO.getInputDir() + aFile;
                byte[] buffer = new byte[1024];
                ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
                ZipEntry zipEntry = zis.getNextEntry();
                while (zipEntry != null) {
                    String fileName = zipEntry.getName();
                    File newFile = new File(outputDir + fileName);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    zipEntry = zis.getNextEntry();
                }
                zis.closeEntry();
                zis.close();
            }
        }
    }
}
