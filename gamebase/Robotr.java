package gamebase;

import java.awt.Robot;
import java.io.File; // Import the File class
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.lang.model.util.ElementScanner14;

import javProjects.Game2048.Tile;

public class Robotr extends Thread{

    //Game2048 board = new Game2048();
    Game2048.Tile tileer = new Game2048.Tile();
    public String filename;
     
    
    public void robots(int no) throws InterruptedException {
        //Thread.sleep(3000);

        
            
        try {
            Thread.sleep(80);
            
            //Object KeyEvent;
            // rt.keyPress(38);
            Robot rt = new Robot();

            //int no = (int) (Math.random() * 4 + 1);

            switch(no){
                case 1: rt.keyPress(38);break;
                case 2: rt.keyPress(37);break;
                case 3: rt.keyPress(39);;break;
                case 4: rt.keyPress(40);;break;
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
    
  }


  //JSON writer

  public void createrecorder() throws Exception{

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
    LocalDateTime now = LocalDateTime.now();  
    filename =  dtf.format(now);  

    try {
        File myObj = new File("./records/"+filename+".txt");
        if (myObj.createNewFile()) {
          System.out.println("File created: " + myObj.getName());
        } else {
          System.out.println("File already exists.");
        }
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      

     
  }


  public void createRecord(int gen, int scr){
    try {
        FileWriter myWriter = new FileWriter("./records/"+filename + ".txt",true);
        myWriter.write("Generation : "+ gen + " Score : " + scr +"\n");
        
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
  }


  public void decision(gamebase.Game2048.Tile[] myTiles, int gen) throws InterruptedException {

    for(int x = 0; x<3; x++){
        for(int y = 0; y<3; y++){
           //Tile hehe = tileAt(x, y, tiles);
           int value1 = tileAt(x, y, myTiles).value;
           String moved;

           if(value1 == 0){continue;}
           else if(tileAt(x, y, myTiles).value == tileAt(x+1, y, myTiles).value && value1 != 0){
               robots(1);
               moved = "left ";
               break;
           }
           else if(tileAt(x, y, myTiles).value == tileAt(x, y+1, myTiles).value  && value1 != 0){
            robots(3);
            moved = "right";
            break;
           }
          else{
            int jabnatab = (int) (Math.random() * 4 + 1);
            if(jabnatab == 1){
              moved = "left ";
            }
            else if(jabnatab == 2){
              moved = "right";
            }
            else if(jabnatab == 3){
              moved = "up   ";
            }
            else if(jabnatab == 4){
                moved = "down ";
              }
            else{
              moved = "none ";
            }
            
            robots(jabnatab);
          }

          //Log Generator
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
          LocalDateTime now = LocalDateTime.now();  
          String lolaTime =  dtf.format(now);

            try {
                FileWriter myWriter = new FileWriter("./decisions/"+filename + ".txt",true);
                myWriter.write("Current Generation : "+gen+" Action Taken : "+moved+"  Time : "+lolaTime+"\n");
                
                myWriter.close();
                //System.out.println("Successfully wrote to the file.");
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        }
    }
    

    

      
  }

  private gamebase.Game2048.Tile tileAt(int x, int y, gamebase.Game2048.Tile[] myTiles) {
    return myTiles[x + y * 4];
  }



  }
