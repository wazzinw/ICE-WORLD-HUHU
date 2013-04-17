package Login;

public class NameCheck implements Runnable {
 String name;
 int cnt;
 int timer=5;
 
 NameCheck(){
  this.name = "";
  cnt =1;
 }
 NameCheck(String name){
  this.name = name;
  cnt =1;
 }
 @Override
 public void run() {
  if(cnt>=3){
   for(int i =0;i<5;i++){
    try {
     Thread.sleep(60000);
    } catch (InterruptedException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
    timer--;
   }
   cnt = 0;
   timer =5;
  }
  
 }
 
 
}
