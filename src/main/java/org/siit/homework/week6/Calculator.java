package org.siit.homework.week6;


import org.jetbrains.annotations.NotNull;

public class Calculator {

    private int mm;
    private int cm;
    private int dm;
    private int m;
    private int km;
    private String resultType;

    public String getResultType() {
        return resultType;
    }

    private void addValues(int value, String unitOfLength) {
        if (unitOfLength.equals("mm")) {
            this.mm += value;
        }
        if (unitOfLength.equals("cm")) {
            this.cm += value;
        }
        if (unitOfLength.equals("dm")) {
            this.dm += value;
        }
        if (unitOfLength.equals("m")) {
            this.m += value;
        }
        if (unitOfLength.equals("km")) {
            this.km += value;
        }
    }

    private void subtractValues(int value, @NotNull String measureUnit) {
        if (measureUnit.equals("mm")) {
            this.mm -= value;
        }
        if (measureUnit.equals("cm")) {
            this.cm -= value;
        }
        if (measureUnit.equals("dm")) {
            this.dm -= value;
        }
        if (measureUnit.equals("m")) {
            this.m -= value;
        }
        if (measureUnit.equals("km")) {
            this.km -= value;
        }
    }

    private void setVariablesToZero() {
        this.mm = 0;
        this.cm = 0;
        this.dm = 0;
        this.m = 0;
        this.km = 0;
    }

    private int calculateResult() {
        int result;
        switch (this.resultType) {
            case "mm":
                result = mm + (cm * 10) + (dm * 100) + (m * 1000) + (km * 1000000);
                break;
            case "cm":
                result = (mm / 10) + cm + (dm * 10) + (m * 100) + (km * 100000);
                break;
            case "dm":
                result = (mm / 100) + (cm / 10) + dm + (m * 10) + (km * 10000);
                break;
            case "m":
                result = (mm / 1000) + (cm / 100) + (dm / 10) + m + (km * 1000);
                break;
            case "km":
                result = (mm / 1000000) + (cm / 100000) + (dm / 10000) + (m / 1000) + km;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    public void computeString(String expression) {
        String[] splitExpression = expression.split(" ");

        addValues(Integer.parseInt(splitExpression[0]), splitExpression[1]);
        for (int i = 2; i < splitExpression.length; i += 3) {
            if (splitExpression[i].equals("+")) {
                addValues(Integer.parseInt(splitExpression[i + 1]), splitExpression[i + 2]);
            } else if (splitExpression[i].equals("-")) {
                subtractValues(Integer.parseInt(splitExpression[i + 1]), splitExpression[i + 2]);
            } else if (splitExpression[i].equals("=")) {
                this.resultType = splitExpression[i + 2];
            } else {
                this.resultType = "null";
                System.out.println("Invalid input!");
                break;
            }
        }
        System.out.println(this.calculateResult() + " " + this.resultType);
        setVariablesToZero();
    }



}
