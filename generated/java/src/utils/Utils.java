package utils;

public class Utils {
    public static Integer[] processLine(String[] strings) {
        Integer[] intarray = new Integer[strings.length];
        int i=0;
        for(String str:strings){
            intarray[i]=Integer.parseInt(str.trim());
            i++;
        }

        return intarray;
    }
}
