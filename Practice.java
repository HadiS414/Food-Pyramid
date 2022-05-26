import java.util.ArrayList;

//Practice
public class Practice {

public static void main(String[] args) {

    int[] random = new int[100];
    for(int i = 0; i < 30; i++) {
        random[i] = (int)(Math.random() * 365 + 1);
        System.out.print(random[i]);
        System.out.print(" ");

    }

}

}
