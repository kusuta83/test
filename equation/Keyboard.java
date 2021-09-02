package equation;

import java.util.Scanner;

public class Keyboard {
    /**
     * 文字列を読み込み、読み込まれなかったらもう一度聞いてくれる。
     * この関数を複数回読み込んだときにバグったため、scanner.close()をコメントアウトしている。たぶんあんまりよくない
     * @return 読み込まれた文字列
     */
    public String inputString() {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        if (str.isEmpty()) {
            System.out.println("もう一度入力してください。");
            str = inputString();
        }
        
        // scanner.close();
        return str;
    }
}
