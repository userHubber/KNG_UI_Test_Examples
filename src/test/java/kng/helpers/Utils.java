package kng.helpers;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.commons.collections4.ListUtils;

public class Utils {

    public final Date DATE = new Date();
//================================================================================

    public String getPresetTime(String dateFormat, int hourOffset) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, hourOffset);
        return sdf.format(c.getTime());
    }

    public String getPresetDate(String dateFormat, int dayOffset) {
        String[] russianMonat
                = {
                    "января",
                    "февраля",
                    "марта",
                    "апреля",
                    "мая",
                    "июня",
                    "июля",
                    "августа",
                    "сентября",
                    "октября",
                    "ноября",
                    "декабря"
                };
        Locale local = new Locale("ru", "RU");
        DateFormatSymbols russSymbol = new DateFormatSymbols(local);
        russSymbol.setMonths(russianMonat);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, russSymbol);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dayOffset);
        return sdf.format(cal.getTime());
    }

    public int getRandomNumeral(int end) {
        Random random = new Random();
        return random.nextInt(end);
    }

    public String getGeneratedMail() {
        String mail = "_t-t_email@yopmail.com";
        StringBuffer time_ = new StringBuffer(Long.toString(DATE.getTime()));
        StringBuffer _mail = new StringBuffer(time_.append(mail));
        return _mail.toString();
    }

    public String getGeneratedPhone() {
        StringBuffer time_ = new StringBuffer(Long.toString(DATE.getTime()));
        String phone = time_.delete(time_.length() - 1, time_.length()).substring(2);
        return phone;
    }

    public String getGeneratedPhoneBelarus() {
        StringBuffer time_ = new StringBuffer(Long.toString(DATE.getTime()));
        String phone = time_.delete(time_.length() - 1, time_.length()).substring(3);
        return phone;
    }

    public String getSavedFormatPhoneByAccount(String phone) {//space in phone after registration only  
        StringBuffer phone_ = new StringBuffer(phone);
        String savedPhone = phone_.insert(0, '+')
                .insert(1, '7')
                .insert(2, " ")
                .insert(3, '(')
                .insert(7, ')')
                .insert(8, " ")
                .insert(12, '-')
                .insert(15, '-').toString();
        return savedPhone;
    }

    public String getSavedFormatPhoneByCheckout(String phone) {
        StringBuffer phone_ = new StringBuffer(phone);
        String savedPhone = phone_.insert(0, '+')
                .insert(1, '7')
                .insert(2, '(')
                .insert(6, ')')
                .insert(10, '-')
                .insert(13, '-').toString();
        return savedPhone;
    }

    public String getSavedFormatPhoneByCheckoutBelarus(String phone) {
        StringBuffer phone_ = new StringBuffer(phone);
        String savedPhone = phone_.insert(0, '+')
                .insert(1, '3')
                .insert(2, '7')
                .insert(3, '5')
                .insert(4, '(')
                .insert(7, ')')
                .insert(11, '-')
                .insert(14, '-').toString();

        return savedPhone;
    }

    public ArrayList<String> getArrayListByString(String manyLinesString) {

        ArrayList<String> al = new ArrayList<>();

        new BufferedReader(new StringReader(manyLinesString)).lines().forEach(
                (line) -> al.add(line)
        );
        return al;
    }

    public ArrayList<String> getArrayListByFile(String filePath) {
        ArrayList<String> $lines = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            lines.forEach((line) -> {
                $lines.add(line);
            });

        } catch (IOException ignore) {

        }
        return $lines;
    }

    public ArrayList<String> getDistinctionByTwoListCompare(ArrayList<String> control, ArrayList<String> expected) {

        ArrayList<String> resultCompare = new ArrayList<>();

        if (!ListUtils.isEqualList(control, expected)) {

            ArrayList<String> $control = new ArrayList<>();
            ArrayList<String> $expected = new ArrayList<>();
            int con = control.size();
            int exp = expected.size();

            if (exp == con) {

                for (int i = 0; i < exp; i++) {
                    if (!control.get(i).equals(expected.get(i))) {
                        $control.add("index" + i + ": " + control.get(i));
                        $expected.add("index" + i + ": " + expected.get(i));
                    }
                }

            } else if (exp > con) {

                int lastIndex = exp - (exp - con) - 1;

                for (int i = 0; i < exp; i++) {
                    if (i <= lastIndex && !control.get(i).equals(expected.get(i))) {
                        $control.add("index" + i + ": " + control.get(i));
                        $expected.add("index" + i + ": " + expected.get(i));
                    } else if (i > lastIndex) {
                        $control.add("absent index" + i);
                        $expected.add("index" + i + ": " + expected.get(i));
                    }
                }

            } else {

                int lastIndex = con - (con - exp) - 1;

                for (int i = 0; i < con; i++) {
                    if (i <= lastIndex && !control.get(i).equals(expected.get(i))) {
                        $control.add("index" + i + ": " + control.get(i));
                        $expected.add("index" + i + ": " + expected.get(i));
                    } else if (i > lastIndex) {
                        $control.add("index" + i + ": " + control.get(i));
                        $expected.add("absent index: " + i);
                    }
                }
            }

            resultCompare.add("\nexpected:\n");
            $expected.forEach((s) -> {
                resultCompare.add(s);
            });
            resultCompare.add("\ncurrent:\n");
            $control.forEach((s) -> {
                resultCompare.add(s);
            });
            return resultCompare;

        }
        return resultCompare;
    }

    public static void writeToFile(String filePath, String valueToWrite, boolean appendLine) {

        try (FileWriter nFile = new FileWriter(filePath, appendLine)) {
            nFile.write(valueToWrite + "\n");
        } catch (IOException e) {
        }
    }

    public static String transliterate(String source) {
        String ru;
        String[] en;

        ru = " йцукенгшщзхъфывапролджэячсмитьбюё/+";

        en = new String[]{
            "-", "i", "ts", "u", "k", "e", "n", "g", "sh", "sch", "z", "h", "", "f", "y", "v", "a", "p", "r", "o",
            "l", "d", "zh", "e", "ya", "ch", "s", "m", "i", "t", "", "b", "yu", "e", "-", "-"
        };

        StringBuilder sb = new StringBuilder();
        String str = source.toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            int x = ru.indexOf(currentChar);

            if (x >= 0) {
                sb.append(en[x]);
            } else {
                sb.append(currentChar);
            }
        }

        str = sb.toString();
        str = str.replaceAll("[^\\_a-z0-9]", "_");
        str = str.replaceAll("_{2,}", "_");
        str = str.replaceAll("[_]+$", "");

        return str;
    }

    public static int toInt(String string) {
        int format = Integer.parseInt(string.replaceAll("\\s+", "").replaceAll("[^0-9]", ""));
        return format;
    }

    public static String amountStringFormat(int amount) {
        String $amount = String.valueOf(amount);
        int length = $amount.length();
        StringBuffer $$amount = new StringBuffer($amount);

        switch (length) {
            case 4:
                $$amount.insert(1, ' ');
                break;
            case 5:
                $$amount.insert(2, ' ');
                break;
            case 6:
                $$amount.insert(3, ' ');
                break;
            case 7:
                $$amount.insert(1, ' ').insert(5, ' ');
                break;
            default:
                break;
        }
        return $$amount.toString();
    }

    public String getTextByFilePDF(File file) {
        try (PDDocument pddDoc = PDDocument.load(file)) {
            PDFTextStripper reader = new PDFTextStripper();
            return reader.getText(pddDoc);
        } catch (IOException e) {
        }
        return null;
    }
}
