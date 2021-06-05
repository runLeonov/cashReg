package ua.training.dao.entity;

import java.sql.Timestamp;

public class Report {
    private Timestamp printTime;
    private int countChecks;
    private int countCancelChecks;
    private double totalA;
    private double totalB;
    private double totalC;
    private double sumTotal;
    private double ndsTotalA;
    private double ndsTotalB;
    private double ndsTotalC;
    private double sumNdsTotal;

    public Report() {
    }

    public Report(Timestamp printTime,
                  int countChecks,
                  int countCancelChecks,
                  double totalA,
                  double totalB,
                  double totalC,
                  double sumTotal,
                  double ndsTotalA,
                  double ndsTotalB,
                  double ndsTotalC,
                  double sumNdsTotal) {
        this.printTime = printTime;
        this.countChecks = countChecks;
        this.countCancelChecks = countCancelChecks;
        this.totalA = totalA;
        this.totalB = totalB;
        this.totalC = totalC;
        this.ndsTotalA = ndsTotalA;
        this.ndsTotalB = ndsTotalB;
        this.ndsTotalC = ndsTotalC;
        this.sumNdsTotal = sumNdsTotal;
        this.sumTotal = sumTotal;
    }

    @Override
    public String toString() {
        return "Report{" +
                "printTime=" + printTime +
                ", countChecks=" + countChecks +
                ", countCancelChecks=" + countCancelChecks +
                ", totalA=" + totalA +
                ", totalB=" + totalB +
                ", totalC=" + totalC +
                ", sumTotal=" + sumTotal +
                ", ndsTotalA=" + ndsTotalA +
                ", ndsTotalB=" + ndsTotalB +
                ", ndsTotalC=" + ndsTotalC +
                ", sumNdsTotal=" + sumNdsTotal +
                '}';
    }

    public Timestamp getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Timestamp printTime) {
        this.printTime = printTime;
    }

    public int getCountChecks() {
        return countChecks;
    }

    public void setCountChecks(int countChecks) {
        this.countChecks = countChecks;
    }

    public int getCountCancelChecks() {
        return countCancelChecks;
    }

    public void setCountCancelChecks(int countCancelChecks) {
        this.countCancelChecks = countCancelChecks;
    }

    public double getTotalA() {
        return totalA;
    }

    public void setTotalA(double totalA) {
        this.totalA = totalA;
    }

    public double getTotalB() {
        return totalB;
    }

    public void setTotalB(double totalB) {
        this.totalB = totalB;
    }

    public double getTotalC() {
        return totalC;
    }

    public void setTotalC(double totalC) {
        this.totalC = totalC;
    }

    public double getNdsTotalA() {
        return ndsTotalA;
    }

    public void setNdsTotalA(double ndsTotalA) {
        this.ndsTotalA = ndsTotalA;
    }

    public double getNdsTotalB() {
        return ndsTotalB;
    }

    public void setNdsTotalB(double ndsTotalB) {
        this.ndsTotalB = ndsTotalB;
    }

    public double getNdsTotalC() {
        return ndsTotalC;
    }

    public void setNdsTotalC(double ndsTotalC) {
        this.ndsTotalC = ndsTotalC;
    }

    public double getSumNdsTotal() {
        return sumNdsTotal;
    }

    public void setSumNdsTotal(double sumNdsTotal) {
        this.sumNdsTotal = sumNdsTotal;
    }

    public double getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(double sumTotal) {
        this.sumTotal = sumTotal;
    }

    public static class Builder {
        private Report report;

        public Builder() {
            report = new Report();
        }

        public Builder withPrintTime(Timestamp printTime) {
            report.printTime = printTime;
            return this;
        }

        public Builder withCountChecks(int countChecks) {
            report.countChecks = countChecks;
            return this;
        }

        public Builder withCountCancelChecks(int countCancelChecks) {
            report.countCancelChecks = countCancelChecks;
            return this;
        }

        public Builder withTotalA(double totalA) {
            report.totalA = totalA;
            return this;
        }

        public Builder withTotalB(double totalB) {
            report.totalB = totalB;
            return this;
        }

        public Builder withTotalC(double totalC) {
            report.totalC = totalC;
            return this;
        }

        public Builder withSumTotal(double sumTotal) {
            report.sumTotal = sumTotal;
            return this;
        }

        public Builder withNdsTotalA(double ndsTotalA) {
            report.ndsTotalA = ndsTotalA;
            return this;
        }

        public Builder withNdsTotalB(double ndsTotalB) {
            report.ndsTotalB = ndsTotalB;
            return this;
        }

        public Builder withNdsTotalC(double ndsTotalC) {
            report.ndsTotalC = ndsTotalC;
            return this;
        }

        public Builder withSumNdsTotal(double sumNdsTotal) {
            report.sumNdsTotal = sumNdsTotal;
            return this;
        }

        public Report build() {
            return report;
        }


    }

}










