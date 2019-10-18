package kng.filesService;

import java.io.File;

public class FilesPaths {

    private static final File DOWNLOADS_FOLDER = new File("src\\test\\resources\\temporaryfiles");
    private static final File CREATED_ORDER_INFO = new File("src\\test\\resources\\dataFiles\\orders.txt");

    public static final String getChromDriverPath() {
        return "src\\test\\resources\\chromedriver.exe";
    }

    public static final String getDownloadsFilesFolder() {
        return DOWNLOADS_FOLDER.getAbsolutePath();
    }

    public static final String getCreatedOrderInfoFile() {
        return CREATED_ORDER_INFO.getAbsolutePath();
    }

}
