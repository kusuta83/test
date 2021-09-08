package makeDrill;

import java.util.Random;

/**
 * 分数クラス
 * 分数を作成したり読み込んだり？
 */
public class Fraction {
    /** 分母 */
    private String denominator;

    /** 分子 */
    private String numerator;
    

    public Fraction() {
    }

    /**
     * 分母と分子が数値の場合
     * @param denominator
     * @param numerator
     */
    public Fraction (int denominator, int numerator) {
        this.denominator = Integer.toString(denominator);
        this.numerator = Integer.toString(numerator);
    }

    /**
     * 分母と分子が数値となる分数をランダムで作成する
     */
    public void makeRandomIntFraction() {
        Random random = new Random();
        numerator = Integer.toString(random.nextInt(10)+1);
        denominator = Integer.toString(random.nextInt(10)+1);
    }

    /**
     * 分子が"ax+b", "ax-b"となる分数をランダムで作成する
     * 
     */
    public void makeRandomFraction() {
        Random random = new Random();
        String a = Integer.toString(random.nextInt(10)+1);
        String b = Integer.toString(random.nextInt(10)+1);
        denominator = Integer.toString(random.nextInt(10)+2);
        String sign;
        if(random.nextInt(2)==0){
            sign = "+";
        } else {
            sign = "-";
        }

        numerator = a + "x" + sign + b;

    }

    @Override
    public String toString() {
        
        return "(" + numerator + ")/" + denominator;
    }
    
}
