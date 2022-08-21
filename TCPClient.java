
// L� uma linha do teclado
// Envia o pacote (linha digitada) ao servidor

import java.io.*;
import java.net.*;

class TCPClient {
   public static void main(String args[]) throws Exception
   {      
      //le entrada do teclado
      //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

      // le arquivo
      String inFromUser = "";
      BufferedReader buffRead = new BufferedReader(new FileReader("./Arquivo1.txt")); //Path do arquivo
		String linha = "";
		while (true) {
			if (linha != null) {
            inFromUser += linha;
				//System.out.println(linha);
			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
      //System.out.println(inFromUser);
      
      try{
         //conexao cliente servidor
         Socket client = new Socket("localhost", 9876);

         //streams de output e input
         ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
         ObjectInputStream input = new ObjectInputStream(client.getInputStream());

         //le msg teclado
         //String msg = inFromUser.readLine();
         //output.writeUTF(msg);

         //le msg do arquivo
         String msg = inFromUser;
         output.writeUTF(msg);

         //limpeza de buffer
         output.flush();

         //recebe msg do server
         msg = input.readUTF();
         System.out.println(msg);

         //closes
         output.close();
         input.close();
         client.close();
      }catch(IOException e){
         //Trata excecao
         System.out.println("IOException: client.");
      }      
   }
}
