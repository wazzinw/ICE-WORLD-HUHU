import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileServerClient {
	
    File myfile;
    Frame myFrame = new Frame();
    
    public void receiveFileFromServer() throws ClassNotFoundException, IOException{
    	
        Socket sock = null;
        String host = "localhost";
        int port = 8990;
 
        try {
            sock = new Socket(host, port);
        } catch (IOException e1) {

            e1.printStackTrace();
        }
 
        System.out.println("Connection made (clientSide)");
 
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e1) {

            e1.printStackTrace();
        }

        myfile=(File)ois.readObject();
 

        System.out.println("AbsolutePath: " + myfile.getAbsolutePath()); 
        System.out.println("FileName:" + myfile.getName() );
        System.out.println("lenght"  + myfile.length()); 
 
 
        FileDialog choo = new FileDialog(myFrame,"Choose File Destination",FileDialog.SAVE);
        choo.setDirectory(null);
        choo.setFile("enter file name here");
        choo.setVisible(true);
 
 
        String targetFileName = choo.getDirectory()+choo.getFile( )+".txt" ;
 
        System.out.println("File will be saved to: " + targetFileName); 
 
        copyBytes(myfile, targetFileName);
 
 
    }
 
    private void copyBytes(File originalFile, String targetFileName) throws IOException {
 
        FileInputStream in = null;
        FileOutputStream out = null;
 
 
        in = new FileInputStream(originalFile);
        out = new FileOutputStream(targetFileName);
        int c;
        
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        
        out.close();
        in.close();
    }
    
    public static void main(String[] args) {
        FileServerClient client = new FileServerClient();  
        try {
            client.receiveFileFromServer();
 
        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}