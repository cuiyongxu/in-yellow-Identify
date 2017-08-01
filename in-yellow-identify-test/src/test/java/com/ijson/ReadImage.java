package com.ijson;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by cuiyongxu on 17/8/1.
 */
public class ReadImage {


    private String fileName = "black.jpg";

    @Test
    public void readImage() {
        System.out.println(Arrays.toString(image2Bytes()));
    }

    private byte[] image2Bytes() {
        byte[] bytes = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("images/" + fileName);
            //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
            bytes = new byte[inputStream.available()];
            //将文件内容写入字节数组，提供测试的case
            inputStream.read(bytes);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Test
    public void getFileMetadata() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("images/" + fileName);
        Metadata metadata = null;
        try {
            metadata = JpegMetadataReader.readMetadata(inputStream);
        } catch (JpegProcessingException | IOException e) {
            e.printStackTrace();
        }
        //输出所有附加属性数据
        for (Directory directory : metadata.getDirectories()) {
            System.out.println("******\t" + directory.getName() + "\t******");
            for (Tag tag : directory.getTags()) {
                System.out.println(tag.getTagName() + ":" + tag.getDescription());
            }
            System.out.println();
            System.out.println();
        }

    }


}
