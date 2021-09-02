
/**
 * 一次方程式クラス 左辺、右辺はコンマ区切りで入力されることを想定
 * ()の記述には不対応。例えば"(2x+3)/2"は、"2x/2,3/2"と入力するように。
 * 分母にxがある場合もダメ。まずは分母を払わなきゃダメだったわ……。アルゴリズムの欠陥
 * 
 */
public class LinearEquation {
    // field
    /** 左辺 */
    private String leftSide;

    private double leftDouble = 0.0;

    /** 右辺 */
    private String rightSide;

    private double rightDouble = 0.0;

    // constructor
    public LinearEquation() {

    }

    /**
     * 一次方程式を解く。 x以外の文字には対応していない。正規表現を使えばどんな文字にも対応できるように改造できるはず。
     * 
     * @return 一次方程式の答え
     */
    public String solve() {
        System.out.println("左辺を入力してください。");
        System.out.println("各項はコンマで区切ってください。");
        Keyboard keyboard = new Keyboard();
        leftSide = keyboard.inputString();
        String leftLine[] = leftSide.split(","); // 項に分割

        System.out.println("右辺を入力してください。");
        System.out.println("各項はコンマで区切ってください。");
        rightSide = keyboard.inputString();
        String rightLine[] = rightSide.split(","); // 項に分割

        // 移項
        double coefficient;
        for (String string : leftLine) { // TODO (3x+2)/2が来たときにバグるでしょ
            if (string.contains("x")) {
                if (string.equals("x")) {
                    coefficient = 1.0;
                } else if(string.equals("-x")){
                    coefficient = -1.0;
                } else {
                    if (string.contains("/")) {
                        String[] line = string.split("/");
                        if (line[0].equals("x")) {
                            string = "1/" + line[1];
                        } else{
                            string = line[0].split("x")[0] + "/" + line[1];
                        }
                        coefficient = toFractionDouble(string);
                    } else { // xが入っていて、かつ分数でない ex)5x, 0.5x
                        coefficient = Double.valueOf(string.split("x")[0]);
                    }
                }
                leftDouble += coefficient;
            } else { // xが入っていないとき
                if (string.contains("/")) {
                    rightDouble -= toFractionDouble(string);
                } else {
                    rightDouble -= Double.valueOf(string);
                }
            }
        }

        for (String string : rightLine) {
            if (string.contains("x")) {
                if (string.equals("x")) {
                    coefficient = 1.0;
                } else if(string.equals("-x")){
                    coefficient = -1.0;
                } else {
                    if (string.contains("/")) {
                        String[] line = string.split("/");
                        if (line[0].equals("x")) {
                            string = "1/" + line[1];
                        } else{
                            string = line[0].split("x")[0] + "/" + line[1];
                        }
                        coefficient = toFractionDouble(string);
                    } else { // xが入っていて、かつ分数でない ex)5x, 0.5x
                        coefficient = Double.valueOf(string.split("x")[0]);
                    }
                }
                leftDouble -= coefficient;
            } else {
                if (string.contains("/")) {
                    rightDouble += toFractionDouble(string);
                } else {
                    rightDouble += Double.valueOf(string);
                }
            }
        }

        if (leftDouble == 0.0) {
            System.err.println("xの係数が0になったため、解けません。");
            return "Error";
        }

        // // 答えを既約分数にする
        // int[] fraction = toIntegerNumeratorAndDenominator(rightDouble, leftDouble); // まず分母と分子を整数に
        // // TODO 既約分数にする関数

        // String answer = fraction[0] + " / " + fraction[1];
        // return answer;

        double answer = rightDouble / leftDouble;
        String answerString = Double.toString(answer);

        // String fractionalPart;
        if(answerString.contains("999999")){
            // fractionalPart = answerString.split(".")[1];
            if (answer > 0) { // +だったら
                answer = Math.ceil(answer);
            } else if(answer < 0){ // -だったら
                answer = Math.floor(answer);
            }
        }



        return Double.toString(answer);

    }

    /**
     * 入力の中に分数が含まれていた時に、それをdouble型に変換する
     * 
     * @return 入力された分数
     */
    public double toFractionDouble(String fractionString) {
        String[] line = fractionString.split("/");
        double numerator = Double.parseDouble(line[0]);
        double denominator = Double.parseDouble(line[1]);

        return numerator / denominator;

    }

    /**
     * 分母と分子を整数に直す関数
     * 
     * @param numerator   元の分子
     * @param denominator 元の分母
     * @return 分母と分子が整数である分数([0]: 分子, [1]:分母)
     */
    public int[] toIntegerNumeratorAndDenominator(double numerator, double denominator) {
        double eps = 0.00001;
        while (numerator - Math.floor(numerator) < eps && denominator - Math.floor(denominator) < eps) {
            numerator = numerator * 10;
            denominator = denominator * 10;
            double sample = Math.floor(numerator);
            double sample2 = Math.floor(denominator);
            double sample3 = numerator - Math.floor(numerator);
        }

        int[] fraction = new int[2];
        fraction[0] = (int) numerator;
        fraction[1] = (int) denominator;
        return fraction;
    }
}
