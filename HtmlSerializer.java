import java.io.*;



class HtmlSerializer {
   

	public static void main(String args[]){

	}

	public void HtmlSerializer(){
      		
	}
	
	public void write(StringBuffer output,String fileName){
		try{

				FileWriter fstream = new FileWriter(fileName);
				BufferedWriter out = new BufferedWriter(fstream);

	    		out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n ");
				out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n ");
	    		out.write("<head>\n");
	    		out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n ");
				out.write("\t<title>untitled</title>\n");
				out.write("</head>\n");
				out.write("<body>\n");
				out.write(output.toString());
				out.write("</body>\n");
				out.write("</html>\n"); 

	    		out.close();
	    	}catch (Exception e){//Catch exception if any
	      		System.err.println("Error: " + e.getMessage());
	    	}
		
	}

}