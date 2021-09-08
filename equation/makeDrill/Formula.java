package makeDrill;

import java.util.ArrayList;
import java.util.Random;

/**
 * 計算式クラス 項を組み合わせて式を作る？
 */
public class Formula {
    String Formula;

    /**
     * 分数で構成された式を表示する 今回使用する分数は(ax+b)/c
     * 
     * @param termNumber 項の数
     */
    public void makeFractionFormula(int termNumber) {
        ArrayList<Fraction> fractions = new ArrayList<>();
        for (int i = 0; i < termNumber; i++) {
            Fraction fraction = new Fraction();
            fraction.makeRandomFraction();
            fractions.add(fraction);
        }

        Random random = new Random();
        ArrayList<String> signs = new ArrayList<>();
        for (int i = 1; i < termNumber; i++) {
            String sign;
            if (random.nextInt(2) == 0) {
                sign = "+";
            } else {
                sign = "-";
            }
            signs.add(sign);
        }

        Formula = fractions.get(0).toString();
        for (int i = 1; i < termNumber; i++) {
            Formula = Formula + " " + signs.get(i-1) + " " + fractions.get(i).toString();
        }

    }

    @Override
    public String toString() {
        return Formula;
    }
}
