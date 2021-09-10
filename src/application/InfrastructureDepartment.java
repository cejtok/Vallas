package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class InfrastructureDepartment {
	
	public String BILLBOARD_FILE_NAME = "data/billboard.bbd";
	public ArrayList<Billboard> billboards = new ArrayList<Billboard>();
	
	public InfrastructureDepartment() {
		
	}
	
	public void AddBillboard(double w, double h, boolean iu, String b) {
		billboards.add(new Billboard(w, h, iu, b));
		SaveBillboards();
	}
	
	public void SaveBillboards() {		
		try{
			// Guardando el csv
			FileWriter myWriter = new FileWriter("data/BillboardDataExported.csv");			
			myWriter.write("width|height|inUse|brand\n");			
		     for(Billboard b: billboards) {
		    	 myWriter.write(
		    			 Double.toString(b.getWidth()) + "|" +
		    			 Double.toString(b.getHeight()) + "|" +
		    			 Boolean.toString(b.isInUse()) + "|" +
		    			 b.getBrand() + "\n");		    	 
		     }		     
		     myWriter.close();
		     System.out.println("Exito guardando el archivo csv");
		     
		     // Guardando el bytecode
		     FileOutputStream fileOutputStream = new FileOutputStream(BILLBOARD_FILE_NAME);
		     ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);		     
		     objectOutputStream.writeObject(billboards);
		     objectOutputStream.close();     
		     
		    
		}catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		ExportDanguerousBillboardReport("data/report.txt");		
	}
	
	
	
	public void LoadBillboard()  {
				
		try{
			
			// Cargando del csv
			String line;
			int count = 0;
			BufferedReader br = new BufferedReader(new FileReader("data/BillboardDataExported.csv"));  
			while ((line = br.readLine()) != null){
				if(count > 0) {
			        String[] values = line.split("\\|");
			        billboards.add(new Billboard(
			        		Double.parseDouble(values[0]), 
			        		Double.parseDouble(values[1]), 
			        		values[2].equals("true")? true: false, 
			        		values[3]));
				}
				count++;
			}
			br.close();
			
			
			// Cargando del bytecode
			//FileInputStream fileInputStream = new FileInputStream(BILLBOARD_FILE_NAME);
            //ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);            
           //billboards = (ArrayList<Billboard>) objectInputStream.readObject();            
            //objectInputStream.close();
			
		} 
		catch (IOException e){  
			e.printStackTrace();  
		}
		
		SaveBillboards();		
	}
	
	
	
	
	public void LoadBillboardFromByteCode(){
		try{			
			FileInputStream fileInputStream = new FileInputStream(BILLBOARD_FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);            
            billboards = (ArrayList<Billboard>) objectInputStream.readObject();            
            objectInputStream.close();			
		} 
		catch (IOException | ClassNotFoundException e){  
			e.printStackTrace();  
		}
	}
	
	
	
	public void ExportDanguerousBillboardReport(String fn) {
		try{
			FileWriter myWriter = new FileWriter("data/report.txt");			
			myWriter.write("===========================\n");
			myWriter.write("DANGEROUS BILLBOARD REPORT\n");
			myWriter.write("===========================\n");
			myWriter.write("The dangerous billboard are:\n");
						
			int count = 0;
		    for(Billboard b: billboards) {
		    	 if(b.CalculateArea() >= 160) {
		    		 count++;
		    		 myWriter.write(String.format("%-4s Billboard %-40s with area %-15s\n",
	    				count + ".",
	    				b.getBrand(),
	    				b.CalculateArea()));
		    	 }
		     }		     
		     myWriter.close();
		     System.out.println("Exito guardando el reporte.");
		}catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}		
	}
	
	
	
	
	public String toString() {
		return "Infrastructure Department";		
	}
	
	
	

}
