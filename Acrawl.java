
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Acrawl {
	
	public static void main(String arr[])
	{
		Document doc1;
		try {
doc1 = Jsoup.connect("https://www.amazon.in/OnePlus-Mirror-Black-64GB-Memory/dp/B0756Z43QS/ref=hsx_crw_1389401031_tl_1?pf_rd_p=5bce9f37-e1a0-471a-b819-40361ce65f3d&pf_rd_s=merchandised-search-20&pf_rd_t=101&pf_rd_i=1389401031&pf_rd_m=A1VBAL9TL5WCBF&pf_rd_r=NNTW9961MGEZFV37TWVS&pf_rd_r=NNTW9961MGEZFV37TWVS&pf_rd_p=5bce9f37-e1a0-471a-b819-40361ce65f3d").get();
			 Element contentDiv = doc1.select("div[id=feature-bullets]").first();
			 
			 
			    String text=contentDiv.getElementsByTag("div").text();

			    System.out.println(text); // The result
			    try (PrintWriter out = new PrintWriter("output/Aoutput.txt", "UTF-8")) {
		            out.write(text);
		        }
			    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//save in db
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/test","root","");  
			PreparedStatement ps=con.prepareStatement(  
					"insert into filetable2 (des) values (?)");  
					              
					File f=new File("../jsp&mysql/output/Aoutput.txt");  
					FileReader fr=new FileReader(f);  
					              
					//ps.setInt(1,101);  
					ps.setCharacterStream(1,fr,(int)f.length());  
					int i=ps.executeUpdate();  
					System.out.println(i+" records insert");  
					
//					Statement stmt=con.createStatement();  
//					ResultSet rs=stmt.executeQuery("select * from filetable2");  
//					while(rs.next())  
//					System.out.println(rs.getInt(1)+"  "+rs.getString(2));  


					con.close();  
		}catch (Exception e) {e.printStackTrace();}  
		
	}
}
