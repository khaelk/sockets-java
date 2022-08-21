// Recebe um pacote de algum cliente
// Separa o dado, o endere�o IP e a porta deste cliente
// Imprime a mensagem e confirma

import java.io.*;
import java.net.*;

class TCPServer {
   //Socket do servidor
   private static ServerSocket serverSocket;
   //Cria socket do servidor
   private static void createServerSocket(int port) throws IOException{
      serverSocket = new ServerSocket(port);
   }

   //Metodo para aceitar conexao do cliente
   private static Socket waitConnect() throws IOException{
      Socket socket = serverSocket.accept();
      return socket;
   }

   //Stream TCP
   private static void streamIO(Socket socket) throws IOException{
      //Streams de output e input de comunicacao com o cliente
      ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

      //Leitura da msg
      String msg = input.readUTF();
      System.out.println("Mensagem: "+msg);
      //Envio de confirmacao
      output.writeUTF("Mensagem confirmada: "+msg);
      output.flush();      
      //Fecha IO
      input.close();
      output.close();
   }

   public static void main(String args[])  throws Exception{   
      //Estabelecimento de conexao
      try{                         
         System.out.println("Conectando...");
         createServerSocket(9876);            
         while(true){                     
            Socket client = waitConnect();
            System.out.println("Conectado.");
            //Stream
            try{            
               streamIO(client);
            }catch(IOException e){
               System.out.println("IOException: stream");
            }finally{
               System.out.println("Fim da conexao.");
               client.close();
            }
         }
      }catch(IOException e){
         System.out.println("IOException: connect");
      }            
   }
}
