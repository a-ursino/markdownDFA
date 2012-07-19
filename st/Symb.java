package st;
import java.util.*;
import java.lang.*;

public class Symb  {

	private  Type type;
	private  Object value;
        private  boolean IsArray;
        private  int DimensionArray;
          
        HashMap MultipleValue = new HashMap(); 
        
        //stampo il contenuto del symbolo
        public void stamp()
        {
            System.out.println("Type: "+display()+" valore: "+displayValue()+" IsArray: "+displayIsArray()+" DimensioneArray: "+displayDimension());
            Iterator it = MultipleValue.entrySet().iterator();
		while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
				Object s = (Object)pairs.getValue();
		        System.out.println("PositionInArray "+pairs.getKey() + " value: " + s.toString());
		    }
        }
        
	public Symb(Type type){
		this.type=type;
               
	}

        //Ritorna il campo Type sotto forma di stringa
	public String display(){
		return type.display();
	}
        
        //Inserisce i valore di inizializzazione corrispondente di una variabile semplice
        public void insert(Object val)
        {
            value=val;
        }
        
        //Incrementa la variabile value
        public void increment()
        {
           if(value==null)
           {
             System.out.println("La variabile non è stata inizializzata con alcuni valore - incremento impossible");
           }
           else
           {
             if(display().compareTo("int")==0)
              {
                System.out.print("Increment value: "+value);
                int val=Integer.parseInt(value.toString());
                val++;
                value=val;
                System.out.println(" in value: "+value);
                
                
              }
              if(display().compareTo("float")==0)
              {
                System.out.print("Increment value: "+value);
                Float val=Float.parseFloat(value.toString());
                val=val+(float)1.0;
                value=val;
                System.out.println(" in value: "+value);
                
              }
          }     
        }
        
        //Decrementa la variabile value
        public void decrement()
        {
           if(value==null)
           {
             System.out.println("La variabile non è stata inizializzata con alcuni valore - decremento impossible");
           }
           else
           {
            
              if(display().compareTo("int")==0)
              {
                System.out.print("Decrement value: "+value);
                int val=Integer.parseInt(value.toString());
                val--;
                value=val;
                System.out.println(" in value: "+value);
              }
              if(display().compareTo("float")==0)
              {
                System.out.print("Decrement value: "+value);
                Float val=Float.parseFloat(value.toString());
                val=val-(float)1.0;
                value=val;
                System.out.println(" in value: "+value);
              }    
            }
        } 
  
  
          public void insertDimension(int val)
        {
            DimensionArray=val;
        }
        
      //Da sistemare!!!  
        
        public void insertSingleValue(String val,String pos)
        {
          int position = Integer.parseInt(pos);
          if(position>DimensionArray)
            System.out.println("##Error to insert value in a locaton that not exist in array");
          else{
                if(MultipleValue.containsKey(position))
                {
                   MultipleValue.remove(position);
          //        System.out.println("***Elemento eliminato");
                 
                  
                  }
                  
                if(display().compareTo("String")==0)
                {
                  MultipleValue.put(position,val);
                  System.out.println("                          Stringa inserita: "+val+" in posizione: "+position);
                }
                if(display().compareTo("int")==0)
                {
                  int partInt=Integer.parseInt(val);
                  MultipleValue.put(position,partInt);
                  System.out.println("                          Int inserito: "+val+" in posizione: "+position);
                }
                if(display().compareTo("float")==0)
                {       
                  float partFloat=Float.parseFloat(val);
                  MultipleValue.put(position,partFloat);
                  System.out.println("                          Int inserito: "+val+" in posizione: "+position);
                }
              }
          }
           
      
        
        
        
        
        //Inserisce i valori di inizializzazione corrispondente di una variabile array
        public void insertMultipleValue(String val)
        {
          //Contatore di stringhe dell'array lette
          int i=0;
          //posizione della Stringa in ingresso analizzata analizzata
          int position =0;
          //Stringa,intero o float letti
          String part;
          int partInt;
          float partFloat;
          
          //Posizione dell'ultima virgola rilevata nella stringa
          int index =0;
          boolean flag=false;
         
          try
            {
              while(i<DimensionArray)
              {
              //recupero la posizione della prima virgola(index) a partire dalla posizione position
              index=val.indexOf(",",position);
          
              //recupero la stringa tra position e index
              part=val.substring(position,index);
            
              //Aggiorno position con la posizione dell'ultima virgola individuata
              position =index+1;
              i++;
              }
            }
            //Gestisco il caso in cui la dimensione dell'array è maggiore rispetto al numero di stringhe inserite (con cui l'array è inizializzato)
            catch(StringIndexOutOfBoundsException e)
            {
              System.out.println("###### Error:StringIndexOutBoundsException  ");
              flag=true;
            }
         
            if(flag==true)
              System.out.println("Impossible Inizializate the array in hashmap - dimension too big");
            else
            {
              i=0;
              index=0;
              position=0;
              
              while(i<DimensionArray)
              {
              //recupero la posizione della prima virgola(index) a partire dalla posizione position
              index=val.indexOf(",",position);
          
              //recupero la stringa tra position e index
              part=val.substring(position,index);
            
              //Inserisco il valore dell'hashmap
                if(display().compareTo("String")==0)
                {
                  MultipleValue.put(i,part);
                  System.out.println("                          Stringa inserita: "+part+" in posizione: "+i);
                }
                if(display().compareTo("int")==0)
                {
                  partInt=Integer.parseInt(part);
                  MultipleValue.put(i,partInt);
                  System.out.println("                          Int inserito: "+part+" in posizione: "+i);
                }
                if(display().compareTo("float")==0)
                {
                  partFloat=Float.parseFloat(part);
                  MultipleValue.put(i,partFloat);
                  System.out.println("                          Int inserito: "+part+" in posizione: "+i);
                }
                
                
                  
              //Aggiorno position con la posizione dell'ultima virgola individuata
              position =index+1;
              i++;
              }
              
            }
        }
  
        
        
        public Object displayValue()
        {
          return value;
        }
        
      
        public boolean displayIsArray()
        {
          return IsArray;
        }
        
        public int displayDimension()
        {
          return DimensionArray;
        }
        
        
        public void insertIsArray(boolean IsArr)
        {
            IsArray=IsArr;
        }


        
}