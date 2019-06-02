package org.lanaeus.braintrainning;

public class Question {
    public enum QStatus {
        Unanswered,
        Wrong,
        Right
    }
    int num1;
    int num2;
    int num3;
    int num4;
    int num5;
    int num6;
    char oper1;
    char oper2;
    char oper3;char oper4;char oper5;

    int ans;
    int count; //question number
    String input;
    QStatus status;

    public Question(int num1, int num2, int num3,int num4, int num5, int num6,
                    char oper1,char oper2,char oper3,char oper4,char oper5, int ans, int count) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.oper1 = oper1;
        this.oper2 = oper2;
        this.oper3 = oper3;
        this.oper4 = oper4;
        this.oper5 = oper5;
        this.ans = ans;
        this.count = count;
        this.input = "";
        this.status = QStatus.Unanswered;
    }

    public int getnum1() {
        return this.num1; }

    public int getNum2() {
        return this.num2;
    }

    public int getNum3() {
        return this.num3;
    }

    public int getNum4() {
        return this.num4; }

    public int getNum5() {
        return this.num5;
    }

    public int getNum6() {
        return this.num6;
    }

    public char getOper1() {
        return this.oper1;
    }

    public char getOper2() {
        return this.oper2;
    }

    public char getOper3() {
        return this.oper3;
    }

    public char getOper4() {
        return this.oper4;
    }

    public char getOper5() {
        return this.oper5;
    }

    public int getAns() {
        return this.ans;
    }

    public int getCount() {
        return this.count;
    }

    public String getInput() {
        return this.input;
    }

    public QStatus getStatus() {
        return this.status;
    }

    public String getNoviceLine() {

        return String.format("%d)  %d %c %d= ",count, num1,oper1,num2);
    }

    public String getEasyLine() {
        return String.format("%d)  %d %c %d %c %d= ",count, num1,oper1,num2,oper2,num3);
    }

    public String getMediumLine() {
        return String.format("%d)  %d %c %d %c %d %c %d= ",count, num1,oper1,num2,oper2,num3,oper3,num4);
    }

    public String getGuruLine() {
        return String.format("%d)  %d %c %d %c %d %c %d %c %d= ",count, num1,oper1,num2,oper2,num3,oper3,num4,oper4,num5);
    }


    public void setInput(String s) {
        this.input = s;
    }

    public void setStatus(QStatus s) {
        this.status = s;
    }

}