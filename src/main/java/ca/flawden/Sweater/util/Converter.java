package ca.flawden.Sweater.util;

import org.springframework.stereotype.Component;

@Component
public class Converter {

    private static  char[] english = {'a','b','v','g','d','e','e','z','z','i','i','k','l','m','n','o','p', 'r', 's', 't', 'u', 'p','f', 'h', 'c', 's', 's', ' ', 'i', ' ', 'e', 'u', 'i'};

    private static char [] russian = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};

    public String convert(String filename) {

        char[] charFileName = filename.toLowerCase().toCharArray();

        for(int i=0;i<filename.length();i++) {
            System.out.println(i);
            for(int j=0; j<english.length;j++) {
                if(charFileName[i] == russian[j]) {
                    charFileName[i] = english[j];
                    break;
                }
                System.out.print(j);
            }

        }

        System.out.println("Готовая строка" + String.valueOf(charFileName));
        return String.valueOf(charFileName);
    }

}
