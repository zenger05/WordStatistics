import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        // создаем поток для чтения файла
        try (FileInputStream docs = new FileInputStream("file.txt");) {
            int symbol;
            String magpie = null;

            // переносим все содержимое файла в переменную magpie, для дальнейшей обработки
            while ((symbol = docs.read()) != -1) {
                if (magpie != null) {
                    magpie += (char) symbol;
                } else {
                    magpie = Character.toString((char) symbol);
                }
            }

            //заполняем массив, словами из sting переменной, полученной из файла
            //чистим массив от пробелов
            String[] massiveMagpie = magpie.split("[\\ \\.\\,]");
            ArrayList<String> arrayMagpie = new ArrayList<>(Arrays.asList(massiveMagpie));
            for (int i = 0; i < arrayMagpie.size(); i++) {
                if (arrayMagpie.get(i) == "") {
                    arrayMagpie.remove(i);
                } else {
                    continue;
                }
            }

            //создаем мапу, где ключ будет слово, а значение количество повторений
            massiveMagpie = arrayMagpie.toArray(new String[arrayMagpie.size()]);
            HashMap<String, Integer> count = new HashMap<>();
            massiveMagpie = toLowerCase(massiveMagpie);
            for (int i = 0; i < massiveMagpie.length; i++) {
                if (count.containsKey(massiveMagpie[i])) {
                    count.put(massiveMagpie[i], count.get(massiveMagpie[i]) + 1);
                } else {
                    count.put(massiveMagpie[i], 1);
                }
            }
            // переносим ключ и значение в строковой массив, после чего вызываем метод, для преобразования в
            // в байт массив
            String[] str2 = new String[count.size()];
            int j = 0;
            for(Map.Entry<String, Integer> entry : count.entrySet()) {
                str2[j] = entry.getKey() + " " + entry.getValue() + ", " + "\n";
                j++;
            }
            byte[] bytes = stringToByte(str2);
            //открывваем поток вывода и передаем наш байт массив
            try (FileOutputStream outputStream = new FileOutputStream("output.txt")) {
                for (byte eachByte : bytes) {
                    outputStream.write(eachByte);
                }
            }
            } catch (IOException e) {
                System.out.println("Input error");
            }
        }

    public static String[] toLowerCase(String[] x) {
        for(int i = 0; i < x.length; i++) {
            x[i] = x[i].toLowerCase();
        }
        return x;
    }

    public static byte[] stringToByte(String[] x) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < x.length; i++) {
            builder.append(x[i]);
        }
        return builder.toString().getBytes();
    }




}

