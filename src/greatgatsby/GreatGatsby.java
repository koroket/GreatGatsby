/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package greatgatsby;



/**
 *
 * @author Mike
 */
public class GreatGatsby {

    /**
     * @param args the command line arguments
     */
 
    public static void main(String[] args) {
     

      GatsbyWorld GW = new GatsbyWorld();
        Thread T1 = new Thread(GW);
       T1.start();
    }
}
