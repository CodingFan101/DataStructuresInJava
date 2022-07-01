/**
 * A class that represents a rational number. 
 * 
 * @author Charles Hoot 
 * @version 5.0
*/

public class Rational
{
    // PUT PRIVATE DATA FIELDS HERE
	private int numerator;
	private int denominator;
    /**
     * The default constructor for objects of class Rational.  Creates the rational number 1.
     */
    public Rational()
    {       
        
    	numerator = 1;
    	denominator = 1;
    }

    /**
     * The alternate constructor for objects of class Rational.  Creates a rational number equivalent to n/d.
     * @param n The numerator of the rational number.
     * @param d The denominator of the rational number.
     */    
    public Rational(int n, int d)
    {
        
    	if (d == 0) {
    		throw new ZeroDenominatorException("Denominator cannot be zero");
    	}
    	numerator = n;
    	denominator = d;
    	normalize();
    }
    
    /**
     * Get the value of the Numerator
     *
     * @return     the value of the numerator
     */
    public int getNumerator()
    {
        
        return numerator;
    }
    
    /**
     * Get the value of the Denominator
     *
     * @return     the value of the denominator
     */
    public int getDenominator()
    {
        
        return denominator;
    }


    /**
     * Negate a rational number r
     * 
     * @return a new rational number that is negation of this number -r
     */    
    public Rational negate()
    {               
        
    	Rational negation = new Rational(numerator * -1, denominator);
        return negation;
    }


    /**
     * Invert a rational number r 
     * 
     * @return a new rational number that is 1/r.
     */    
    public Rational invert()
    {               
       
    	Rational inversion = new Rational(denominator, numerator);
        return inversion;
    }





    /**
     * Add two rational numbers
     *
     * @param other the second argument of the add
     * @return a new rational number that is the sum of this and the other rational
     */    
    public Rational add(Rational other)
    {       
        int n = numerator;
        int d = denominator;
        int x = other.numerator;
        int y = other.denominator;
        return new Rational(n*y + x*d, d*y);
    }
    
     /**
     * Subtract a rational number t from this one r
     *
     * @param other the second argument of subtract
     * @return a new rational number that is r-t
     */    
    public Rational subtract(Rational other)
    {               
        
        return add(other.negate());
    }

    /**
     * Multiply two rational numbers
     *
     * @param other the second argument of multiply
     * @return a new rational number that is the sum of this object and the other rational.
     */    
    public Rational multiply(Rational other)
    {       
    	int n = numerator;
        int d = denominator;
        int x = other.numerator;
        int y = other.denominator;
        return new Rational(n * x, d * y);
    }
        
 
     /**
     * Divide this rational number r by another one t
     *
     * @param other the second argument of divide
     * @return a new rational number that is r/t
     */    
    public Rational divide(Rational other)
    {               
        return multiply(other.invert());
    }
     
 
 
 /**
     * Put the rational number in normal form where the numerator
     * and the denominator share no common factors.  Guarantee that only the numerator
     * is negative.
     *
     */
    private void normalize()
    {
        // ADD CODE TO NORMALIZE THE RATIONAL NUMBER
    	if (denominator < 0) {
    		denominator = -denominator;
    		numerator = -numerator;
    	}
    	
    	int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
    	
    	numerator /= gcd;
    	denominator /= gcd;
    }
    
    /**
     * Recursively compute the greatest common divisor of two positive integers
     *
     * @param a the first argument of gcd
     * @param b the second argument of gcd
     * @return the gcd of the two arguments
     */
    private int gcd(int a, int b)
    {
        int result = 0;
        if(a<b)
            result = gcd(b,a);
        else if(b==0)
            result = a;
        else
        {
            int remainder = a % b;
            result = gcd(b, remainder);
        }
        return result;
    }
   
    
    
}
