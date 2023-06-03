import java.io.Console;

public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0;
    }

    public Polynomial(double[] coeff) {
        coefficients = coeff;
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

        double[] newCoeff = new double[largePoly.coefficients.length];

        for (int i = 0; i < smallPoly.coefficients.length; i++) {
            newCoeff[i] = smallPoly.coefficients[i] + largePoly.coefficients[i];
        } 
        for (int i = smallPoly.coefficients.length; i < largePoly.coefficients.length; i++) {
            newCoeff[i] = largePoly.coefficients[i];
        } 
 
        return new Polynomial(newCoeff);
    }

    public double evaluate(double x) {
        double y = 0;
        for (int deg = 0; deg < coefficients.length; deg++) {
            double term = coefficients[deg];
            for (int i = 0; i < deg; i++) {
                term = term * x;
            }
            y += term;
        }
        return y;
    }

    public boolean hasRoot(double root) {
        return evaluate(root) == 0;
    }
}