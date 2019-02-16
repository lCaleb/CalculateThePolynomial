package modelo;

public class Graeffe {
    public int polynomialDegree; 
    public double[] realRoots;
    public Complex[] complexRoot=new Complex[4]; 
    public int quantityOfReals; 
    public int quantityOfComplex;
    private double[][] squaresMatrix;
    private int pot2=1;
    private int m;
    private final int MAX_ITER=10; 
    private static final double ZERO=0.0001;
    private double[] rootsModule=new double[2];

    public Graeffe(double[] coef) {
    	polynomialDegree=coef.length-1; 
        realRoots=new double[polynomialDegree]; 
        squaresMatrix= new double[MAX_ITER][polynomialDegree+1];
//la primera fila de la tabla guarda los coeficientes del polinomio
        for(int j=0; j<polynomialDegree+1; j++){
        	squaresMatrix[0][j]=coef[j];
        }
        for(int m=1; m<MAX_ITER; m++){
            for(int j=0; j<polynomialDegree+1; j++){
            	squaresMatrix[m][j]=0.0;
            }
        }
        quantityOfReals=0;
        quantityOfComplex=0;
    }
    

    private void squaresTable(){
        int k,l, signo;
//divide el polinomio por el primer coeficiente, las raíces no cambian

        for(int i=1; i<polynomialDegree+1; i++){
        	squaresMatrix[0][i]/=squaresMatrix[0][0];
        }
        squaresMatrix[0][0]=1.0;
        m=1;
pajuera:// label que detiene el ciclo anidado B)
        do{
//cuadrados
            for(int i=0; i<polynomialDegree+1; i++){
            	squaresMatrix[m][i]=squaresMatrix[m-1][i]*squaresMatrix[m-1][i];
                if(Double.isInfinite(squaresMatrix[m][i])){
                    break pajuera;
                }
            }
//dobles productos
            for(int i=1; i<polynomialDegree; i++){
                for(int s=1; s<polynomialDegree/2+1; s++){
                    k=i-s;      l=i+s;
                    if((k<0)||(l>polynomialDegree))    break;   //términos simétricos
                    signo=(s%2==0)? +1: -1;
                    squaresMatrix[m][i]+=signo*2*squaresMatrix[m-1][k]*squaresMatrix[m-1][l];
                    if(Double.isInfinite(squaresMatrix[m][i])){
                        break pajuera;
                    }
                }
            }
            m++;
        }while(m<MAX_ITER);

        m--;
//potencia de m de 2
        pot2=1;
        for(int i=1; i<=m; i++){
            pot2*=2;
        }
    }
//valor de un polinomio para una variable real
    public double polynomialValue(double x){
        double y=0.0;
//sucesivas potencias de x, se puede utilizar también la funcion Math.pow
        double[] pot_x=new double[polynomialDegree+1];
        pot_x[0]=1.0;
        for(int i=1; i<polynomialDegree+1; i++){
            pot_x[i]=pot_x[i-1]*x;
        }
//valores de los sucesivos términos
        for(int i=0; i<polynomialDegree+1; i++){
            y+=squaresMatrix[0][i]*pot_x[polynomialDegree-i];
        }
        return y;
    }
    public Complex polynomialValue(Complex x){
        Complex y=new Complex();
        for(int i=0; i<polynomialDegree+1; i++){
            y=Complex.suma(y, Complex.producto(squaresMatrix[0][i], 
               Complex.potencia(x, (polynomialDegree-i))));
        }
        return y;
    }

    private void simpleRealRoot(int j){
//valor absoluto de la raíz
       // System.out.println("Raiz simple");
        double logaritmo=(Math.log(squaresMatrix[m][j])-Math.log(squaresMatrix[m][j-1]))/pot2;
        double raiz=Math.exp(logaritmo);
//determinación del signo, y1 o y2 tienen que ser casi cero

       realRoots[quantityOfReals]=(Math.abs(polynomialValue(raiz))<
              Math.abs(polynomialValue(-raiz)))? raiz : -raiz;
        quantityOfReals++;
    }

    private void doubleRealRoot(int j){
//valor absoluto de la raíz
        double logaritmo=(Math.log(squaresMatrix[m][j+1])-Math.log(squaresMatrix[m][j-1]))/(2*pot2);
        double raiz=Math.exp(logaritmo);
        boolean bPositiva=false, bNegativa=false;
        if (Math.abs(polynomialValue(raiz))<ZERO){
           realRoots[quantityOfReals]=raiz;
           quantityOfReals++;
           bPositiva=true;
        }
        if (Math.abs(polynomialValue(-raiz))<ZERO){
           realRoots[quantityOfReals]=-raiz;
           quantityOfReals++;
           bNegativa=true;
        }
        if(bPositiva && !bNegativa){
          realRoots[quantityOfReals]=raiz;
          quantityOfReals++;
        }
        if(!bPositiva && bNegativa){
          realRoots[quantityOfReals]=-raiz;
          quantityOfReals++;
        }
    }

    private void oneComlexRoot(){
        double suma=0.0;
        for(int i=0; i<quantityOfReals; i++){
            suma+=realRoots[i];
        }
        double u, v;
        u=-(squaresMatrix[0][1]+suma)/2;
        v=Math.sqrt(rootsModule[0]*rootsModule[0]-u*u);
        complexRoot[0]=new Complex(u, v);
        complexRoot[1]=new Complex(u, -v);
    }
    private void twoComplexRoot(){
        double suma=0.0;
        double producto=1.0;
        double inversa=0.0;
        for(int i=0; i<quantityOfReals; i++){
            suma+=realRoots[i];
            producto*=realRoots[i];
            inversa+=1/realRoots[i];
        }
        double r1=rootsModule[0];
        double r2=rootsModule[1];
        double y=-(squaresMatrix[0][1]+suma)/2;
        int signo=((polynomialDegree-1)%2==0)? +1: -1;
        double z=signo*squaresMatrix[0][polynomialDegree-1]/(2*producto)-r1*r1*r2*r2*inversa/2;
        double u1=(y*r1*r1-z)/(r1*r1-r2*r2);
        double u2=(-y*r2*r2+z)/(r1*r1-r2*r2);
        double v1=Math.sqrt(r1*r1-u1*u1);
        double v2=Math.sqrt(r2*r2-u2*u2);
        complexRoot[0]=new Complex(u1, v1);
        complexRoot[1]=new Complex(u1, -v1);
        complexRoot[2]=new Complex(u2, v2);
        complexRoot[3]=new Complex(u2, -v2);
    }

    private boolean changeSign(int j){
        double logaritmo;
        for(int k=2; k<=m; k++){
            if(squaresMatrix[k][j]>0)   continue;
            quantityOfComplex++;
//máximo dos raíces complejas, 4 contando sus respectivas conjugadas
            if(quantityOfComplex<3){
                logaritmo=(Math.log(squaresMatrix[m][j+1])-Math.log(squaresMatrix[m][j-1]))/(2*pot2);
                rootsModule[quantityOfComplex-1]=Math.exp(logaritmo);
                return true;
            }
        }
        return false;
    }

    public void findRoots(){
        squaresTable();
//el pimer coeficiente a[m][0] es siempre 1
        for(int i=1; i<polynomialDegree+1; i++){      //i es la raíz
            if(changeSign(i)){
//raíz compleja y su correspondiente conjugada
                i++;
                continue;
            }
//raíces simple y dobles
            double logaritmo=Math.log(squaresMatrix[m][i])-2*Math.log(squaresMatrix[m-1][i]);
            if(Math.abs(logaritmo)<ZERO){
                simpleRealRoot(i);
            }else{
                doubleRealRoot(i);
                i++;
                continue;
            }
        }
        if(quantityOfComplex==1){
            oneComlexRoot();
        }
        if(quantityOfComplex==2){
            twoComplexRoot();
        }
     }


    public void showRoots(){
        findRoots();
//raíces reales
        System.out.println("Raíces reales");
        for(int i=0; i<quantityOfReals; i++){
            System.out.println((double)Math.round(realRoots[i]*100)/100+" --->"+polynomialValue(realRoots[i]));
        }
        System.out.println("");
//raíces complejas
        System.out.println("Raíces complejas");
        for(int i=0; i<quantityOfComplex; i++){
            System.out.println(complexRoot[2*i]+" --->  "+polynomialValue(complexRoot[2*i]));
            System.out.println(complexRoot[2*i+1]+" --->  "+polynomialValue(complexRoot[2*i]));
        }
        System.out.println("");
    }
}




class ExcepcionDivideCero extends Exception {

  public ExcepcionDivideCero() {
         super();
  }
  public ExcepcionDivideCero(String s) {
         super(s);
  }
}
