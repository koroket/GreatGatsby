
package greatgatsby;

import java.awt.Image;

import javax.swing.ImageIcon;

import java.awt.Graphics;

public class Character {
    
    public Character() {} 
    
        int xCord;
        
        int yCord;
        
        boolean right = false;
        
        boolean left = false;
        
        boolean down = false;
        
        boolean up = true;
        
        String dialogue[] = new String[13];
        
        //ArrayList<Dialogue> dialogue = new ArrayList<>(); 

        int steps=0;
        
        int shake1=0;
        
        int direction; 
        
        //0 for up, 1 for right, 2 for down, 3 for left
        
        boolean exists;
        
        boolean talking=false;
        
        Image image;
        
        Image imageup;
        
        Image imageupr;
        
        Image imageupl;
        
        String s;
        
        Image imagedown;
        
        Image imageright;
        
        Image imageleft;
        
protected Image createImage(String path) 
{
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL).getImage();
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}
   public boolean characterInteraction(Character c1)
   {
             double xdiff = (double)Math.abs(this.xCord-c1.xCord);
             double ydiff = (double)Math.abs(this.yCord-c1.yCord);
             double radius = Math.sqrt(xdiff*xdiff+ydiff*ydiff);
             if (radius<150){
           return true;}
             else{return false;}
       }
 public void changepics(Character c1, String sx,Graphics g)
 {
     String s[]= new String[12];
     
     for (int i =0;i<12;i++)
     {s[i]="/greatgatsby/"+sx+i+".gif";
             }   
   
     
if((c1.steps/40%3==0))
{
    c1.imageup = createImage(s[0+c1.direction*3]);
}

    else 
{
    if((c1.steps/40%3==1)) 
    {
        c1.imageup = createImage(s[1+c1.direction*3]);
    }
    
        else
    {
        if((c1.steps/40%3==2)) 
        {
            c1.imageup = createImage(s[2+c1.direction*3]);
        }
        
        }
    }
g.drawImage(imageup, xCord+shake1, yCord, null);
   
 }
}
