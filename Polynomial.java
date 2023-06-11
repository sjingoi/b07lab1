import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Polynomial {
    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        coefficients = new double[1];
        exponents = new int[1];
        coefficients[0] = 0;
        exponents[0] = 0;
    }

    public Polynomial(double[] coeff, int[] exp) {
        coefficients = coeff;
        exponents = exp;
    }

    public Polynomial(File file) throws Exception {
        Scanner sc = new Scanner(file);
        String polyString = sc.nextLine();
        polyString = polyString.replace(" ", "");
        polyString = polyString.replaceAll("-", "+-");
        String[] terms = polyString.split("\\+");

        coefficients = new double[terms.length];
        exponents = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            String[] termStr = terms[i].split("x");
            coefficients[i] = Double.parseDouble(termStr[0]);
            if (termStr.length == 1) {
                exponents[i] = 0;
            } else {
                exponents[i] = Integer.parseInt(termStr[1]);
            }
        }
        sc.close();
    }

    public void saveToFile(String filename) throws Exception {
        File file = new File(filename);
        FileWriter output = new FileWriter(filename, false);
        output.write(this.toString());
        output.close();
    }

    public Polynomial add(Polynomial polynomial) {
        Polynomial smallPoly;
        Polynomial largePoly;

        if (polynomial.coefficients.length < this.coefficients.length) {
            smallPoly = polynomial;
            largePoly = this;
        } else {
            smallPoly = this;
            largePoly = polynomial;
        }

        int largestExponent = 0;
        int largeLength = largePoly.coefficients.length;
        int smallLength = smallPoly.coefficients.length;

        for (int i = 0; i < largeLength; i++) {
            if (largePoly.exponents[i] > largestExponent) {
                largestExponent = largePoly.exponents[i];
            }
        }
        for (int i = 0; i < smallLength; i++) {
            if (smallPoly.exponents[i] > largestExponent) {
                largestExponent = smallPoly.exponents[i];
            }
        }

        int[] tempExp = new int[largestExponent + 1];
        double[] tempCoeff = new double[largestExponent + 1];

        for (int i = 0; i < tempExp.length; i++) {
            tempExp[i] = i;
        }

        for (int i = 0; i < largeLength; i++) {
            tempCoeff[largePoly.exponents[i]] = largePoly.coefficients[i];
        }
        for (int i = 0; i < smallLength; i++) {
            tempCoeff[smallPoly.exponents[i]] += smallPoly.coefficients[i];
        }

        int newLength = 0;
        for (int i = 0; i < tempCoeff.length; i++) {
            if (tempCoeff[i] != 0) {
                newLength++;
            }
        }

        double[] newCoeff = new double[newLength];
        int[] newExp = new int[newLength];

        int index = 0;
        for (int i = 0; i < largestExponent + 1; i++) {
            if (tempCoeff[i] != 0) {
                newCoeff[index] = tempCoeff[i];
                newExp[index] = tempExp[i];
                index++;
            }
        }

        return new Polynomial(newCoeff, newExp);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double root) {
        return evaluate(root) == 0;
    }

    public Polynomial multiply(Polynomial polynomial) {
        Polynomial newPoly = new Polynomial();
        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < polynomial.coefficients.length; j++) {
                double[] newCoeff = {this.coefficients[i] * polynomial.coefficients[j]};
                int[] newExp = {this.exponents[i] + polynomial.exponents[j]};
                newPoly = newPoly.add(new Polynomial(newCoeff, newExp));
            }
        }
        return newPoly;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < coefficients.length; i++) {
            if (i != 0) {
                result = result + "+";
            }
            if (exponents[i] == 0) {
                result = result + coefficients[i];
            } else {
                result = result + coefficients[i] + "x" + exponents[i];
            }
        }
        result = result.replace("+-", "-");
        return result;
    }


}