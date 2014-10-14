
package greatgatsby;

import java.awt.Color;

import java.awt.Font;

import java.awt.Graphics;

import java.awt.Image;

import java.awt.event.ActionListener;

import sun.audio.*;

import java.applet.*;

import javax.swing.*;

import java.io.*;

import java.net.*;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import java.util.ArrayList;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.swing.ImageIcon;

import javax.swing.JFrame;

public class GatsbyWorld extends JFrame implements Runnable {

       private Image dbImage;

       private Graphics dbg;

       int xtest, ytest;
       
       int prechapter=1;
       
       int prevhorroom=1;
       
       int lifespan=1;
       
       boolean gatsbyalive=true;
       
       int gameTime;
       
       int fontsize=30;
       
       int elevatorshake=0;
       
       int i1=0;
       
       int m =1;
       
       int limit=50;
       
       int shake=0;
       
       boolean freeze = false;

       boolean atIntroPage = true;

      Character Gatsby = new Character();

      Character Tom = new Character();

      Character Nick = new Character();

      Character Daisy = new Character();
      
      Character Elevator = new Character();

      Character Room = new Character();
      
      Character Exclamation = new Character();
      
      Character Dialoguebox = new Character();

      
        Sound sound1;

        Sound sound2;
        
        Sound sound3;
        
       int XDirNick, YDirNick;
       
       int intromusic = 1;
       
       int backgroundmusic = 1;

       int chapter=1;
       //Represents the current chapter of the game

       int horizontalroom;
       //Represents the current horizontal room number

       int verticalroom;
       //Represents the current vertical room number

       public void run() {

       try {

           while (true) {
               
               if(atIntroPage&&intromusic==1)
               {
               sound1.playSound();
               intromusic--;
               }
               
               if(intromusic==0&&!atIntroPage)
               {
               sound1.stopSound();
               intromusic++;
               }
               
               if(backgroundmusic==1&&!atIntroPage)
               {
               sound2.playSound();
               backgroundmusic--;
               }
               
               if(prevhorroom!=horizontalroom)
               {
               sound3.playSoundOnce();
               prevhorroom=horizontalroom;
               if(chapter==8){
                   if(lifespan==0){gatsbyalive=false;}
                   else{lifespan=0;}}
               }
               
               if(chapter==8&&verticalroom==2)
               {
                 lifespan=1;
                 gatsbyalive=true;
               }
               
               prechapter=chapter;
               
               while(freeze)
               {
                   Thread.sleep(2);
               }
               
               if(prechapter!=chapter)
               {
                   elevatorshake=600+200*(Math.abs((prechapter-chapter)));
                   horizontalroom=1;
                   gatsbyalive=true;
                   lifespan=1;
               }
               
        while(elevatorshake>0)
        {
            
               Thread.sleep(1);
               
               if((elevatorshake/20)%2==0)
                   {
                   shake+=1;
                   Nick.shake1+=1;
                  }
               
               else
                   {
                   shake-=1;
                   Nick.shake1-=1;
                   }
               
               elevatorshake--;
               
               }
        
           shake=0;
        
           Nick.shake1=0;
 
           Nick.xCord+=XDirNick;

           Nick.steps+=i1;

           Nick.yCord+=YDirNick;

          roomchange();
          
          elevatorInteraction();
          
          if(verticalroom==1&&horizontalroom==3)
          {
              moveRandom(Tom);
          }

          if(verticalroom==1&&horizontalroom==4)
          {
              moveRandom(Daisy);
          }

          if(verticalroom==1&&horizontalroom==2)
          {
              moveRandom(Gatsby);
          }  

          

          

          gameTime+=3;

          boundaryCheck(Tom);

          boundaryCheck(Daisy);
          
          boundaryCheck(Gatsby);
          
          if(verticalroom == 1)
          {
              boundaryCheckmain(Nick);
          }
          
          if(verticalroom == 2)
          {
              elevatorBoundary(Nick);
          }

          Thread.sleep(4);

           }

       } 
       
       catch (Exception e) 
       {

           System.out.print("error");

       }

    }

       public void moveRandom (Character c1){

           if(c1.steps%200==0)
           {

               c1.direction=(int)(Math.random()*4);

               c1.steps++;

           }

           else{

               if(c1.steps%2==0)
               {

                   if(c1.direction==0)
                   {

                        c1.yCord--;

                        c1.steps++;
                   }

                   if(c1.direction==1)
                   {

                       c1.xCord++;

                       c1.steps++;
                   }

                   if(c1.direction==2)
                   {

                       c1.yCord++;

                       c1.steps++;
                   }

                    if(c1.direction==3)
                    {

                       c1.xCord--;

                       c1.steps++;
                    }

               }

               else
               {
                   c1.steps++;
               }
 
           }

       }
       
       public void boundaryCheckmain(Character c1)

       {

           if(c1.xCord < 150 && (c1.yCord <250 || c1.yCord>364) )
           {

             c1.xCord = 150;

           }

           if(c1.xCord > 1144 && (c1.yCord <250 || c1.yCord>364) )
           {

               c1.xCord  = 1144;

           }

           if(c1.yCord < 31)
           {

               c1.yCord = 31;

           }

           if(c1.yCord > 567)

           {

               c1.yCord = 567;

           }

           if(c1.xCord <149 && c1.yCord>250 &&c1.yCord<364)
           {

              

               if(c1.yCord < 251)
               {

                   c1.yCord = 250;

               }

               if(c1.yCord >363)
               {

                   c1.yCord = 363;

               }

           }

       }

       public void elevatorBoundary(Character c1)

       {

           if(c1.xCord<506)
           {

               c1.xCord = 506;

           }

           if(c1.xCord>782)
           {

               c1.xCord = 782;

           }

           if(c1.yCord < 349)
           {

               c1.yCord = 349;

           }

           if(c1.xCord>505 && c1.xCord<577 && c1.yCord>541)
           {

               c1.yCord = 541;

           }

           if(c1.xCord>714 && c1.xCord<783 && c1.yCord>541)
           {

               c1.yCord = 541;

           }

       }

      public void elevatorInteraction()
      {
          if(verticalroom==2
                  &&Nick.xCord>550
                  &&Nick.xCord<740
                  &&Nick.yCord<400)
          {
          Elevator.talking=true;
          }
          else
          {
              Elevator.talking=false;
          }
      }

       public void showInteraction(Character c1,Graphics g)
       {
           if(c1.characterInteraction(Nick)&&!c1.talking){
              g.drawImage(Exclamation.image, c1.xCord+20, c1.yCord-65, this);

      }
       }
       
     public void talking(Character c1)
     {
        if(c1.characterInteraction(Nick))
        { 
            c1.talking=true;
        }
      }

       public void boundaryCheck(Character c1)
       {

           if (c1.xCord>1000)
           {
               c1.xCord=1000;
           }

           if (c1.xCord<200)
           {
               c1.xCord=200;
           }

           if(c1.yCord<100)
           {
               c1.yCord=100;
           }

           if(c1.yCord>450)
           {
               c1.yCord=450;
           }

       }

public void roomchange()
{

    if (Nick.xCord<10&&Nick.yCord<450&&Nick.yCord>150)
    {

   

       Nick.xCord=1300;
           if(horizontalroom==1){
               if(chapter==1||chapter==5||chapter==6||chapter==7||chapter==9)
               {
                   horizontalroom=4;
               }
               
               if(chapter==2||chapter==4)
               {
                   horizontalroom=3;
               }
               
               if(chapter==3||chapter==8)
               {
                   horizontalroom=2;
               }
               
           }
      else if(horizontalroom==2)
      {
               if(chapter==3||chapter==5||chapter==6||chapter==7||chapter==8)
               {
                   horizontalroom=1;
               }
            
           }
      else if(horizontalroom==3)
      {
          if(chapter==1||chapter==2||chapter==4||chapter==9)
          {
              horizontalroom=1;
          }
          
          if(chapter==6||chapter==7)
          {
              horizontalroom=2;
          }
          
      }
      else if(horizontalroom==4)
      {
               if(chapter==5)
               {
                   horizontalroom=2;
               }
               
               if(chapter==1||chapter==6||chapter==7||chapter==9)
               {
                   horizontalroom=3;
               }
               
      }
       
       }

    if(Nick.xCord>1300&&Nick.yCord<450&&Nick.yCord>150)
    {

       Nick.xCord=40;

       if(horizontalroom==1)
       {
           
      if(chapter==1||chapter==2||chapter==4||chapter==9)
      {
          horizontalroom=3;
      }
      
      if(chapter==3||chapter==5||chapter==6||chapter==7||chapter==8)
      {
          horizontalroom=2;
      }
      
      }
      else if(horizontalroom==2)
      {
            if(chapter==3||chapter==8)
            {
                horizontalroom=1;
            }
            
            if(chapter==5)
            {
                horizontalroom=4;
            }
            
            if(chapter==6||chapter==7)
            {
                horizontalroom=3;
            }
            
           }
      else if(horizontalroom==3)
      {
          if(chapter==1||chapter==6||chapter==7||chapter==9)
          {
              horizontalroom=4;
          }
          
          if(chapter==2||chapter==4)
          {
              horizontalroom=1;
          }
          
      }
      else if(horizontalroom==4)
      {
      if(chapter==1||chapter==5||chapter==6||chapter==7||chapter==9)
      {
          horizontalroom=1;
      }
      
      }

    }

    if(Nick.yCord<40&&Nick.xCord>600&&Nick.xCord<840)
    {

     if(verticalroom==1)
     {

       Nick.yCord=700;

       verticalroom++;

     }

    }

   

     if(Nick.yCord>720&&Nick.xCord>600&&Nick.xCord<840)
     {

     if(verticalroom==2)
     {

       Nick.yCord=50;

       verticalroom--;

     }

    }

}

       GatsbyWorld()
       {

       addKeyListener(new GatsbyWorld.AL());

       setTitle("Gatsby World");

       setSize(1350, 700);

       setResizable(true);

       setVisible(true);

       setBackground(Color.GREEN);

       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       Gatsby.s="gatsby";

       Daisy.s="daisy";

       Tom.s="tom";

       Nick.s="nick";

       Nick.xCord=448;

       Nick.yCord=391;

       Tom.xCord=448;

       Tom.yCord=391;

       Daisy.xCord=448;

       Daisy.yCord=391;

       Gatsby.xCord=448;

       Gatsby.yCord=391;
       
        sound1 = new Sound("jazz1.wav");
        
        sound2 = new Sound("jazz2.wav");
        
        sound3 = new Sound("door.wav");
        
       gameTime=0;

      YDirNick=0; 
      
      XDirNick=0;

      horizontalroom=1;
      
      verticalroom=1;

      Daisy.dialogue[1]="Nick! You ought to come see my "
              + "daughter, she's two years old!";
      
       Daisy.dialogue[2]="";
       
       Daisy.dialogue[3]="";
       
       Daisy.dialogue[4]="";
       
       Daisy.dialogue[5]="You're inviting me over for tea?"
               + " Sure that sounds nice, and I will "
               + "remember to not bring Tom.";
       
       Daisy.dialogue[6]="What has gotten into Tom "
               + "lately? He has been acting quite strange.";
       
       Daisy.dialogue[7]="Tom and I have reconciled "
               + "our differences and are back together!";
       
       Daisy.dialogue[8]="";
       
       Daisy.dialogue[9]="You see, I never loved Gatsby, I only "
               + "saw him for what his image represented.";
       
       Tom.dialogue[1]="I was reading the book ‘The"
               + " Rise of the Coloured Empires’ by "
               + "this man Goddard the other day. I "
               + "keep losing faith in humanity.";
       
       Tom.dialogue[2]="We're heading over to see "
               + "Myrtle today. I swear, she better "
               + "not mention anything about Daisy.";
       
       Tom.dialogue[3]="";
       
       Tom.dialogue[4]="Where’ve you been? Daisy’s "
               + "furious because you haven’t called up."
               + " How’ve you been, anyhow? How’d you "
               + "happen to come up this far to eat?";
       
       Tom.dialogue[5]="";
       
       Tom.dialogue[6]="Who is this Gatsby anyhow?"
               + " Some big bootlegger?";
       
       Tom.dialogue[7]="I thought it was bad as it is"
               + " with all the commosion"
               + " Gatsby and Daisy has been causing,"
               + " but now I find out that"
               + " Myrtle is suddenly dead. Not to "
               + "mention that I have a feeling"
               + " that Gatsby and Daisy was in the car"
               + " that killed Myrtle."
               + " Everything is falling apart!";
       
       Tom.dialogue[8]="";
       
       Tom.dialogue[9]="Truth is, I was the one who"
               + " told Wilson that Gatsby"
               + "owned the car that killed Myrtle,"
               + " but he would have killed me"
               + "had I not told him. Anyway, he deserved to die for"
               + "what he did.";
       
       Gatsby.dialogue[1]="";
       
       Gatsby.dialogue[2]="";
       
       Gatsby.dialogue[3]="Your face is familiar, Weren’t you in the"
               + " Third Division during the war? I'm Jay Gatsby by"
               + " the way. I thought you knew, old sport. I’m afraid"
               + " I’m not a very good host.";
       
       Gatsby.dialogue[4]="";
       
       Gatsby.dialogue[5]="Hey, would you mind "
               + "talking to Daisy for me? You know,"
               + " if you have the time?";
       
       Gatsby.dialogue[6]="Oh no, I think Daisy "
               + "didn't enjoy the party thoroughly!";
       
       Gatsby.dialogue[7]="I know Daisy is in love"
               + " with me, not in love with Tom."
               + " She only married him because I "
               + "was poor and she was tired of"
               + " waiting for me. It was a terrible"
               + " mistake, but in her heart she " +
"never loved any one except me!";
       
       Gatsby.dialogue[8]="Where is the Daisy that"
               + " I know and love,"
               + "she must be out there somewhere. "
               + "I feel so lost and lonely, "
               + "I could just about die!";
       
       Gatsby.dialogue[9]="";
 
       }
       
       public void chat(Character c1)
       {
           
       if(c1.talking&&freeze)
       {
           m++;
       }
       
       }
       



    public class Sound // Holds one audio file

    {

  private AudioClip song; // Sound player

  private URL songPath; // Sound path

  Sound(String filename)

  {

     try

     {

    songPath = this.getClass().getResource(filename);

    song = Applet.newAudioClip(songPath); // Load the Sound

     }

     catch(Exception e){} // Satisfy the catch

  }

  public void playSound()

  {

     song.loop(); // Play

  }

  public void stopSound()

  {

     song.stop(); // Stop

  }

  public void playSoundOnce()

  {

     song.play(); // Play only once

  }

    }

 
public ArrayList<String> stringBreaker(String s1, int i1)
{
    
   ArrayList<String> a1= new ArrayList<String>();
   
   a1.add("");
   
   int oldmax =0;
   
   int newmax=0;
   
   String s2 = s1;
   
   if(s2.contains(" "))
  
   {
      oldmax = s2.indexOf(" ");
      
      newmax = s2.indexOf(" ");
      
      while(s2.length()>i1)
      {
       
           while(newmax<i1)
           {
               oldmax=newmax;
               
               if(s2.substring(oldmax+1).contains(" "))
               {
                   newmax = s2.indexOf(" ", newmax+1);
               }
               
               else
               {
                   newmax=i1;
               }
           }
           
           a1.add(s2.substring(0, oldmax));
           
           s2=s2.substring(oldmax+1);
           
           oldmax=0;
           
           newmax=0;
  
   }
      
   }
   
   a1.add(s2);
    
    return a1;
    
}
           public void setXDirectionNick(int xDir)
    {

      if(YDirNick==0)
      { 
          XDirNick = xDir;
      }

    }

           public void setYDirectionNick(int yDir)
    {

       if(XDirNick==0)
       {
           YDirNick = yDir;
       }

    }

       public class AL extends KeyAdapter 
       {

        public void keyPressed(KeyEvent e){

            int keyCode = e.getKeyCode();
            
             if (keyCode == e.VK_LEFT) 
             {

                if(!freeze)
                {
                    Nick.direction=3;

                    setXDirectionNick(-1);

                    i1=1;
                }

           }

             

             if (keyCode == e.VK_RIGHT)
             {
                if(!freeze)
                {
                    Nick.direction=1;

                    setXDirectionNick(+1);

                    i1=1;
                }
             }

             

             if (keyCode == e.VK_UP) 
             {
                if(!freeze)
                {
                    Nick.direction=0;

                    setYDirectionNick(-1);

                    i1=1;
                }
             }

        if (keyCode == e.VK_DOWN) 
        {
            if(!freeze)
            {
                 Nick.direction=2;

                 setYDirectionNick(+1);

                 i1=1;

            }
        }

        }

        public void keyReleased(KeyEvent e)
        {

             int keyCode = e.getKeyCode();

             if (keyCode == e.VK_LEFT)
             {

               setXDirectionNick(0);

               Nick.steps=0;

               i1=0;
               
               if(Elevator.talking&&freeze)
               {
                 if(chapter!=1)
                 {
                     chapter--;
                 }

               }
           }

          if (keyCode == e.VK_RIGHT) 
            {

               setXDirectionNick(0);

                Nick.steps=0;

                i1=0;
                if(Elevator.talking&&freeze)
                {
                     if(chapter!=9)
                     {
                         chapter++;
                     }

                }
           }


           if (keyCode == e.VK_UP) {

               setYDirectionNick(0);

                Nick.steps=0;

                i1=0;

           }

            if (keyCode == e.VK_DOWN) 
            {

               setYDirectionNick(0);

                Nick.steps=0;

                i1=0;

           }

              if(keyCode == e.VK_ENTER)

          {

            if(atIntroPage==false)
            {
                   
             if(horizontalroom==4&&verticalroom==1)
             {
                 
             if(!Daisy.talking)
             {
                 talking(Daisy);
             }
             
             else
             {
                
                 if(stringBreaker(Daisy.dialogue[chapter],limit).size()==m+1)
                 
                 {
                     
                 m=1;
                 
                 Daisy.talking=false;
                 
                 freeze=false;
                 
                 }
                 
                 else
                 {
                     m++; 
                 }
                 
             }
             
             }
             
             if(horizontalroom==3&&verticalroom==1)
             {
                 
                if(!Tom.talking)
                {
                    talking(Tom);
                }
                
             else
                {
                
                 if(stringBreaker(Tom.dialogue[chapter],limit).size()==m+1)
                  {
                      
                 m=1;
                 
                 Tom.talking=false;
                 
                 freeze=false;
                 
                 }
                 
                 else
                 {
                     m++; 
                 }
                 
             }
                
             }
             
           if(horizontalroom==2&&verticalroom==1&&gatsbyalive)
           {
                if(!Gatsby.talking)
                {
                    talking(Gatsby);
                }
                
            else
                {
                
                if(stringBreaker(Gatsby.dialogue[chapter],limit).size()==m+1)
                 {
                 m=1;
                 Gatsby.talking=false;
                 freeze=false;
                 }
                
                 else
                {
                    m++; 
                }
                 
             }
           
           }
           
          if(Elevator.talking)
          {  
             if(!freeze)
             {
                 freeze=true;
             }
             
              else
             {
                 freeze=false;
                 
                 Elevator.talking=false;
           
             }
             
          }
          
           if((Daisy.talking&&verticalroom==1)
                   ||(Tom.talking&&verticalroom==1)
                   ||(Gatsby.talking&&verticalroom==1))
               
           {
               freeze=true;
           }
    
 }
            
  atIntroPage = false;
 
          }
              
        }

       }

       

       public void paint(Graphics g) 
       {

       dbImage = createImage(getWidth(), getHeight());

       dbg = dbImage.getGraphics();

       paintComponent(dbg);

      g.drawImage(dbImage, 0, 0, this);  

       }

       protected Image createImage(String path)

{

    java.net.URL imgURL = getClass().getResource(path);

    if (imgURL != null) 
    {

       return new ImageIcon(imgURL).getImage();

    } 
    
    else 
    {

       System.err.println("Couldn't find file: " + path);

       return null;

    }

}

       public void paintComponent(Graphics g) 
       {

             g.setColor(Color.black);

         g.fillRect(0, 0, 2000, 2000);

      Room.image = createImage("/greatgatsby/room.gif");

  if(verticalroom==1) 
  {
      g.drawImage(Room.image, 0,0, this);   
  }

          Elevator.image = createImage("/greatgatsby/Elevator.gif");
          
          Exclamation.image = createImage("/greatgatsby/Exclamation.gif");
          
          Dialoguebox.image = createImage("/greatgatsby/dialoguebox.gif");

               // g.drawString("xtest value:" + Nick.xCord 
               //         + "    ytest value:" + Nick.yCord + "        " 
                //        + Daisy.characterInteraction(Nick), 200, 200);
              
                if(verticalroom==1&&horizontalroom==1)
                {
                  //  g.drawString("Main Room", 600, 200);
                }

       if(verticalroom==2)
       {
           g.drawImage(Elevator.image, 484+shake, 246, this);

           g.drawString("elevator", 600, 200);
           
        if(Elevator.talking&&!freeze)
        {
            g.drawImage(Exclamation.image, 650, 250, this);
        }
        
       }

       if(verticalroom==1&&horizontalroom==2)
       {
          // g.drawString("Gatsbys Room", 600, 200);
       }

       if(verticalroom==1&&horizontalroom==3)
       {
          // g.drawString("Tom Room", 600, 200);
       }

       if(verticalroom==1&&horizontalroom==4)
       {
         //  g.drawString("Daisy Room", 600, 200);
       }
    
     if(verticalroom==1&&horizontalroom==2&&!gatsbyalive)
{

      g.drawImage(createImage("/greatgatsby/deadgatsby.gif"), 400, 400, this);

}

Font f1 = new Font("monospaced", Font.BOLD, fontsize);

g.setFont(f1);

          g.drawString("Nick", Nick.xCord, Nick.yCord-20);
          
Nick.changepics(Nick, Nick.s, g);
                 
      if(verticalroom==1&&horizontalroom==3)
      {
        Tom.changepics(Tom, Tom.s, g);

         g.drawString("Tom", Tom.xCord, Tom.yCord-20);
         
        showInteraction(Tom,g);
      }

      if(verticalroom==1&&horizontalroom==2&&gatsbyalive)
      {
   
          Gatsby.changepics(Gatsby, Gatsby.s, g);

         g.drawString("Gatsby", Gatsby.xCord, Gatsby.yCord-20);
         
        showInteraction(Gatsby,g);
      
      }
      


      if(verticalroom==1&&horizontalroom==4)
      {

          Daisy.changepics(Daisy, Daisy.s, g);

         g.drawString("Daisy", Daisy.xCord, Daisy.yCord-20);
         
        showInteraction(Daisy,g);
        
      }
   
     if(       
           ((Nick.yCord>Daisy.yCord)&&(horizontalroom==4))
             ||((Nick.yCord>Tom.yCord)&&(horizontalroom==3))
             ||((Nick.yCord>Gatsby.yCord)&&(horizontalroom==2))
       )
         
     {
     Nick.changepics(Nick, Nick.s, g);
     g.drawString("Nick", Nick.xCord, Nick.yCord-20);
     }
     
       //g.drawString(gameTime/1000+"       "
           //    + "chapter:" + chapter+"     m is "+m, 800, 200);

      if(Daisy.talking
              ||Tom.talking
              ||Nick.talking
              ||Gatsby.talking
              ||(Elevator.talking&&freeze))
      {
          g.drawImage(Dialoguebox.image, 125, 530, this);
      }
      
if(Daisy.talking&&verticalroom==1)
{
g.drawString(stringBreaker(Daisy.dialogue[chapter],limit).get(m), 170, 600);
}

if(Tom.talking&&verticalroom==1)
{
g.drawString(stringBreaker(Tom.dialogue[chapter],limit).get(m), 170, 600);
}

if(Gatsby.talking&&verticalroom==1)
{
g.drawString(stringBreaker(Gatsby.dialogue[chapter],limit).get(m), 170, 600);
}

if(Elevator.talking&&freeze)
{
    Font f2 = new Font("monospaced", Font.PLAIN, fontsize);
    
g.setFont(f2);

g.drawString("Chapter:  1  2  3  4  5  6  7  8  9", 170, 600);

g.drawRect(287+(chapter*54), 572, 35, 35);

}
            if(atIntroPage){ 
                g.drawRect(10, 10, 80, 30);

           g.setColor(Color.red);

           g.fillRect(0, 0, 1350, 700);

           g.setColor(Color.black);
           
if((gameTime/250)%2==0)
    
{
    g.drawImage(createImage("/greatgatsby/Intro0.gif"), 0, 0, this);
}

if((gameTime/250)%2==1)
{
    g.drawImage(createImage("/greatgatsby/Intro1.gif"), 0, 0, this);
}
         
            }

      repaint();

       }

}