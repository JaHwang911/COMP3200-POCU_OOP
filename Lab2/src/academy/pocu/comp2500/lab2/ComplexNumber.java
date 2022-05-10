package academy.pocu.comp2500.lab2;

public class ComplexNumber {
    public final double real;
    public final double imaginary;

    public ComplexNumber() {
        this(0.0, 0.0);
    }

    public ComplexNumber(double real) {
        this(real, 0.0);
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public boolean isReal() {
        return (this.imaginary == 0.0);
    }

    public boolean isImaginary() {
        return (this.real == 0.0);
    }

    public ComplexNumber getConjugate() {
        return new ComplexNumber(this.real, this.imaginary * -1);
    }

    public ComplexNumber add(ComplexNumber num) {
        double tmpReal = this.real + num.getReal();
        double tmpImaginary = this.imaginary + num.getImaginary();

        return new ComplexNumber(tmpReal, tmpImaginary);
    }

    public ComplexNumber subtract(ComplexNumber num) {
        double tmpReal = this.real - num.getReal();
        double tmpImaginary = this.imaginary - num.getImaginary();

        return new ComplexNumber(tmpReal, tmpImaginary);
    }

    public ComplexNumber multiply(ComplexNumber num) {
        double tmpReal = this.real * num.getReal() + this.imaginary * num.getImaginary() * -1;
        double tmpImaginary = this.real * num.getImaginary() + this.imaginary * num.getReal();

        return new ComplexNumber(tmpReal, tmpImaginary);
    }

    public ComplexNumber divide(ComplexNumber num) {
        ComplexNumber numerator = this.multiply(num.getConjugate());
        double denominator = num.getReal() * num.getReal() + num.getImaginary() * num.getImaginary();

        return new ComplexNumber(numerator.getReal() / denominator, numerator.getImaginary() / denominator);
    }
}
