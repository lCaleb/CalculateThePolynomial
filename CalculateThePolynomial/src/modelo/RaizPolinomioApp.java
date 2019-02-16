package modelo;

public class RaizPolinomioApp {
	
    public static void main(String[] args) {
//Ecuación
//a[n]x^n+a[n-1]·x^(n-1)+.....+a[3]·x^3+a[2]·x^2+a[1]·x+a[0]=0

        double[] coef={1, -4, 1, 6};
        Graeffe g=new Graeffe(coef);
        g.showRoots();
//de mayor a menor grado        
        double[] coef1={1, -7, 16, -12};
        g=new Graeffe(coef1);
        g.showRoots();
        double[] coef2={1, -7, 25, -39};
        g=new Graeffe(coef2);
        g.showRoots();
        double[] coef3={1, 0, -5, 0, 4, -10};
        g=new Graeffe(coef3);
        g.showRoots();

        double[] coef4={1, -6, 11, -7};
        g=new Graeffe(coef4);
        g.showRoots();
        double[] coef5={1, 2, 2, 2};
        g=new Graeffe(coef5);
        g.showRoots();

        double[] coef6={1, -1, -10, -1, 1};
        g=new Graeffe(coef6);
        g.showRoots();

        double[] coef7={4, 16, 25, 21, 9};
        g=new Graeffe(coef7);
        g.showRoots();
        double[] coef8={16, -16, -12, 12, 0, 1};
        g=new Graeffe(coef8);
        g.showRoots();
        double[] coef9={1, -8, 17, -10, 0, 1};
        g=new Graeffe(coef9);
        g.showRoots();
    }
}
