package com.raks.psp.image.child;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class ImageProcessor {

    private static final String IMAGE_MAGICK = Path.of("C:", "Program Files", "ImageMagick-7.1.0-Q16-HDRI").toString();

    public static void main(String[] args) throws IOException, InterruptedException, IM4JavaException {
        switch (args[0]) {
            case "-nm" -> getProcessor(args[1]);
            case "-ef" -> getLoopProcessor();
            default    -> System.out.printf("%sUnknown Option%s [-nm | Normal Mode] [-ef | Efficient Mode]%n", "\u001B[31m", "\u001B[0m");
        }
    }

    private static void getProcessor(String imagePath) throws IOException, InterruptedException, IM4JavaException {
        String grayImagePath = imagePath.substring(0, imagePath.lastIndexOf(".")).concat(".gray.jpg");

        ProcessStarter.setGlobalSearchPath(IMAGE_MAGICK);
        ConvertCmd  cmd = new ConvertCmd();
        IMOperation op  = new IMOperation();

        op.addImage(imagePath);
        op.colorspace("Gray");
        op.addImage(grayImagePath);
        cmd.run(op);

        System.out.println(grayImagePath);
    }

    private static void getLoopProcessor() throws IOException, InterruptedException, IM4JavaException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) getProcessor(br.readLine());
        }
    }

}