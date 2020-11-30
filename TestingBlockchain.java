import java.util.Date;
import java.util.ArrayList;

public class TestingBlockchain {

	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp; 
   public static ArrayList <TestingBlockchain> blockchain = new ArrayList <TestingBlockchain>();
   

	public TestingBlockchain(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
      this.hash = calculateHash();
	}
   
   public String calculateHash() {
	String calculatedhash = StringUtil.applySha256(previousHash + Long.toString(timeStamp)+data);
	return calculatedhash;
}
public static void main(String[] args) {
	   
      blockchain.add(new TestingBlockchain("im the first one", "0"));
      blockchain.add(new TestingBlockchain("im the second one", blockchain.get(blockchain.size()-1).hash));
      blockchain.add(new TestingBlockchain("im the third one", blockchain.get(blockchain.size()-1).hash));
      
      System.out.println(blockchain);			
	}

public String toString ()
   {
      String result = " ";
      for (int i = 0; i<blockchain.size(); i++)
         {
            result = i + blockchain.get(i).hash + " ";
         }
         
      return result;
   }
   
public static boolean isChainValid ()
   {
      TestingBlockchain currTestingBlockchain;
      TestingBlockchain prevTestingBlockchain;
      
      for (int i=0; i<blockchain.size(); i++)
         {
            currTestingBlockchain = blockchain.get(i);
            prevTestingBlockchain = blockchain.get(i-1);
                  if(!currTestingBlockchain.hash.equals(prevTestingBlockchain.hash))
                     {
                        return false;
                     }
                     
                  if(!prevTestingBlockchain.hash.equals(currTestingBlockchain.hash))
                     {
                        return false;
                     
                     }
               
          }
          
           return true;
   }
}