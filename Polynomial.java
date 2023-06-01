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
        int lengthOfSmallest;
        int lengthOfLargest;

        Polynomial smallPoly;
        Polynomial largePoly;

        if (polynomial.coefficients.length < this.coefficients.length) {
            //lengthOfSmallest = polynomial.coefficients.length;
            smallPoly = polynomial;
            largePoly = this;
            // //lengthOfLargest = this.coefficients.length;
            // double[] newCoefficients = new double[lengthOfLargest];
        } else {
            //lengthOfSmallest = this.coefficients.length;
            //lengthOfLargest = polynomial.coefficients.length;
            smallPoly = this;
            largePoly = polynomial;
        }

        //double[] newCoefficients = new double[lengthOfLargest];

        Polynomial newPolynomial = new Polynomial(coefficients);

        return polynomial;
    }
}