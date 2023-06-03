public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        coefficients = new double[0];
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
            smallPoly = polynomial;
            largePoly = this;
        } else {
            smallPoly = this;
            largePoly = polynomial;
        }

        Polynomial newPoly = new Polynomial;
        int[] newCoeff = new int[largePoly.coefficients.length]

        for (int i = 0; i < smallPoly.coefficients.length; i++) {
            newCoeff[i] = smallPoly.coefficients[i] + largePoly.coefficients[i]
        } 
        for (int i = smallPoly.coefficients.length; i < largePoly.coefficients.length; i++) {
            newCoeff[i] = newPoly.coefficients[i]
        } 
 
        return new Polynomial(newCoeff);
    }
}