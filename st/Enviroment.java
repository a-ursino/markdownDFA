package st;
import java.util.*;


//La classe Envitoment implementa la SymbolTable

public class Enviroment  
  {
    //Ogni istanza condivide l'hash table in modo statico
    static HashMap table = new HashMap();
    //Mi permette di aggiornare IsArray e Dimension solo se il simbolo è inserito
    static Boolean exist =false;
    
    //Usato per salvare il codice relativo al corpo del while
    static LinkedList whileList = new LinkedList();
    
    static LinkedList expressionValueList = new LinkedList();
    
    //La stringa in ingresso è il nome della variabile che si intende memorizzare.. 
    public static void put(String id_str, Symb s) 
      {
        //Se la chiave(nome variabile) non è presente nell'hash -->inserisco nomeVariabile e tipo nell'hash
	if(!table.containsKey(id_str)) 
          {
            table.put(id_str,s);
            System.out.println("\t\t\t[put method] Insert Element in SymbolTable:  "+id_str+" of type: "+s.display());
            exist=true;
          }
        //Se la variabile è presente  
        else
        {
           System.out.println("\t\t\t[put method] Element in already in SymbolTable:DUPLICATE NAME  "+id_str);
           exist=false;
        }
       
        
      }
      
      

    //La funzione ritorna il symbolo associato alla variabile key in ingresso
    public static Symb get(String name) 
      {
        Symb symbol=(Symb)table.get(name);
        return symbol;
      }

      
      //Inserico in fondo alla lista il valore booleano dell'espresssione
      public static void insertResultExpression(boolean value)
      {
        
        expressionValueList.add(value);
      }
      
      //Ritorno il valore dell'ultimo elemento salvato nella lista
      public static boolean getResultExpression()
      {
        boolean temp;
        
        //Ipotesi in cui nessun costrutto è stato eseguito, quindi la lista è vuota;
        if(expressionValueList.size()==0)
        {
            return true;
        }else
        {
          temp=Boolean.valueOf(expressionValueList.getLast().toString());
          return temp;
        }
        
      }
      
      public static void deleteResultExpression()
      {
        //Elimino l'ultimo elemento memorizzato nella lista
        if(expressionValueList.size()!=0)
           expressionValueList.removeLast(); 
      }
      
   //La funzione aggiorna il valore di IsAssay   
       public static void updateIsArray(String id_str,boolean val)
       {
         if(exist==true)
         {
           System.out.println("\t\t\t[update IsArray method] value: "+val);
           Symb symbol=(Symb)table.get(id_str);
           symbol.insertIsArray(val);
         }
      }
      
      
      public static void updateDimensionArray(String id_str,int val)
       {
         if(exist==true)
         {
           System.out.println("\t\t\t[update Dimension Array ] value: "+val);
           Symb symbol=(Symb)table.get(id_str);
           symbol.insertDimension(val);
         }
       }
      
              
 public static void generateRightbrace(int count)
 {
   ResultValue p=new ResultValue("}",count,false,"Rightbrace");
   addList(p,-1); 
 }   
     
      
  //La funzione in fase di assegnazione di un valore int ad una variabile, verifica la compatibilità ed eventualmente aggiorna il valore della variabile    
  public static void updateInt(String id_str,int val)
  {
    if(!table.containsKey(id_str))
    {
      System.out.println("\t\t\t[update SymbolTable] Element : " +id_str+" not exist  ");
      System.out.println("NOT POSSIBLE update");
    }
    else
    {
      //Recupero il Symb associato alla variabile in ingresso
      Symb symbol=(Symb)table.get(id_str);
      //Recupero da Symb il Type(sottoforma di stringa) e verifico che sia intero(in tal caso posso effettuare l'aggiornamento/inizializzazione)
      if(symbol.display().compareTo("int")==0 && symbol.displayIsArray()==false)
      {
         
          //Effettuo l'aggiornamento del valore
          symbol.insert(val);
          System.out.println("\t\t\t[update SymbolTable] Element : " +id_str+ " update - value: "+val);
      }
      else
        System.out.println("\t\t\t[update SymbolTable] ErrorToAssingValueAt "+id_str+ " [ different type "+symbol.display()+"-int]");
    }
    
  }    
  
  
  //La funzione in fase di assegnazione di un valore char ad una variabile, verifica la compatibilità ed eventualmente aggiorna il valore della variabile  
  public static void updateChar(String id_str,String val)
  {
    if(!table.containsKey(id_str))
    {
      System.out.println("\t\t\t[update SymbolTable] Element : " +id_str+" not exist  ");
      System.out.println("NOT POSSIBLE update");
    }
    else
    {
      Symb symbol=(Symb)table.get(id_str);
      if(symbol.display().compareTo("char")==0  && symbol.displayIsArray()==false )
      {
          symbol.insert(val);
          System.out.println("\t\t\t[update SymbolTable] Element : " +id_str+ " update  - value: "+val);
      }
      else
        System.out.println("\t\t\t[update SymbolTable] ErrorToAssingValueAt "+id_str+ " [different type "+symbol.display()+"-char] ");
    }
    
  } 
  
  
  
  //La funzione in fase di assegnazione di un valore float ad una variabile, verifica la compatibilità ed eventualmente aggiorna il valore della variabile  
  public static void updateFloat(String id_str,float val)
  {
    if(!table.containsKey(id_str))
    {
      System.out.println("\t\t\t[update method] Element : " +id_str+" not exist in SymbolTable ");
      System.out.println("NOT POSSIBLE update");
    }
    else
    {
      Symb symbol=(Symb)table.get(id_str);
      if(symbol.display().compareTo("float")==0 && symbol.displayIsArray()==false)
      {
          symbol.insert(val);
          System.out.println("\t\t\t[update method] Element : " +id_str+ " update in SymbolTable - value: "+val);
      }
      else
        System.out.println("\t\t\t[update method] ErrorToAssingValueAt "+id_str+ " [different type "+symbol.display()+"-float]");
    }
    
  } 
  
    //La funzione in fase di assegnazione di un valore boolean ad una variabile, verifica la compatibilità ed eventualmente aggiorna il valore della variabile  
  public static void updateBool(String id_str,boolean val)
  {
    if(!table.containsKey(id_str))
    {
      System.out.println("\t\t\t[update method] Element : " +id_str+" not exist in SymbolTable ");
      System.out.println("NOT POSSIBLE update");
    }
    else
    {
      Symb symbol=(Symb)table.get(id_str);
      if(symbol.display().compareTo("boolean")==0 && symbol.displayIsArray()==false)
      {
          symbol.insert(val);
          System.out.println("\t\t\t[update method] Element : " +id_str+ " update in SymbolTable - value: "+val);
      }
      else
        System.out.println("\t\t\t[update method] ErrorToAssingValueAt "+id_str+ " [different type "+symbol.display()+"-boolean]");
    }
    
  } 
  
    //La funzione in fase di assegnazione di un valore String ad una variabile Stringa o un valore char a un arrayDiChar, verifica la compatibilità ed eventualmente aggiorna il valore della variabile  
  public static void updateStringAndArrayChar(String id_str,String val)
  {
    
    if(!table.containsKey(id_str))
    {
      System.out.println("\t\t\t[update method] Element : " +id_str+" not exist in SymbolTable ");
      System.out.println("NOT POSSIBLE update");
    }
    else 
    {
      Symb symbol=(Symb)table.get(id_str);
      if(symbol.displayIsArray()==false)
      {
        if(symbol.display().compareTo("String")==0 )
        {
          System.out.println("                                IsArray of symbol'"+id_str+"' is "+ symbol.displayIsArray());
          symbol.insert(val);
          System.out.println("\t\t\t[update method] Element : " +id_str+ " update in SymbolTable - value: "+val);
        }
        else
        System.out.println("\t\t\t[update method] ErrorToAssingValueAt "+id_str+ " [different type "+symbol.display()+"-String]");
      }
      else
      {
        
        if(symbol.display().compareTo("char")==0 )
        {
          System.out.println("                      IsArray: " + symbol.displayIsArray());
          symbol.insert(val);
          System.out.println("\t\t\t[update method] Element Array: " +id_str+ " update in SymbolTable - value: "+val);
      
        }
        else
          System.out.println("\t\t\t[update method] ErrorToAssingValueAt "+id_str+ " [different type "+symbol.display()+"-char]");
    
      }
    
    } 
  }  

  
   public static void updateArrayString(String id_str,String val,String position)
     {
        if(!table.containsKey(id_str))
        {
          System.out.println("\t\t\t[update method] Element : " +id_str+" not exist in SymbolTable ");
          System.out.println("NOT POSSIBLE update");
         }
        else
        {       
          Symb symbol=(Symb)table.get(id_str);
          if(symbol.display().compareTo("String")==0 && symbol.displayIsArray()==true)
          {
            //inizializzazione multipla dell'array
             if(position.compareTo("not")==0)
              symbol.insertMultipleValue(val);
             //inizializzazine singola dell'array
              else{
        //          System.out.println("**Single insert array- val: "+val+" position: "+position);
                  symbol.insertSingleValue(val,position);
              }
          }
          else
          System.out.println("\t\t\t[update method] ErrorToAssingValueAt "+id_str+ " [differentType "+symbol.display()+"-String]");
        }
      }
      
      //Funzione incremanto es a++.
      public static void addone(String id_str)
      {
        if(!table.containsKey(id_str))
        {
          System.out.println("\t\t\t[update method] Element : " +id_str+" not exist in SymbolTable ");
          System.out.println("NOT POSSIBLE update");
         }
        else
        {       
          Symb symbol=(Symb)table.get(id_str);
          if((symbol.display().compareTo("int")==0 && symbol.displayIsArray()==false) || (symbol.display().compareTo("float")==0 && symbol.displayIsArray()==false))
            symbol.increment();
         else
          System.out.println("\t\t\t[update method] ErrorToIncrementAt: "+id_str+" Incompatible Type: "+symbol.display()); 
        }
      }
      
      //Funzione decremanto es a--.
      public static void subone(String id_str)
      {
        if(!table.containsKey(id_str))
        {
          System.out.println("\t\t\t[update method] Element : " +id_str+" not exist in SymbolTable ");
          System.out.println("NOT POSSIBLE update");
         }
        else
        {       
          Symb symbol=(Symb)table.get(id_str);
          if((symbol.display().compareTo("int")==0 && symbol.displayIsArray()==false) || (symbol.display().compareTo("float")==0 && symbol.displayIsArray()==false))
            symbol.decrement();
         else
          System.out.println("\t\t\t[update method] ErrorToDecrementAt: "+id_str+" Incompatible Type: "+symbol.display()); 
        }
      }
      
  
   public static void updateArrayInt(String id_str,String val,String position)
     {
        if(!table.containsKey(id_str))
        {
          System.out.println("\t\t\t[update method] Element : " +id_str+" not exist in SymbolTable ");
          System.out.println("NOT POSSIBLE update");
         }
        else
        {       
          Symb symbol=(Symb)table.get(id_str);
          if(symbol.display().compareTo("int")==0 && symbol.displayIsArray()==true)
          {
                //inizializzazione multipla dell'array
                if(position.compareTo("not")==0)
                    symbol.insertMultipleValue(val);
                //inizializzazine singola dell'array
              else{ 
             //   System.out.println("**Single insert array- val: "+val+" position: "+position);
                symbol.insertSingleValue(val,position);
              }
          }
          else
          System.out.println("\t\t\t[update method] ErrorToAssingValueAt: "+id_str+ " [differentType "+symbol.display()+"-int]");
        }
      }
  
      public static void  updateArrayFloat(String id_str,String val,String position)
     {
        if(!table.containsKey(id_str))
        {
          System.out.println("\t\t\t[update method] Element : " +id_str+" not exist in SymbolTable ");
          System.out.println("NOT POSSIBLE update");
         }
        else
        {       
          Symb symbol=(Symb)table.get(id_str);
          if(symbol.display().compareTo("float")==0 && symbol.displayIsArray()==true)
          {
            //inizializzazione multipla dell'array
            if(position.compareTo("not")==0)
              symbol.insertMultipleValue(val);
        
            else{
           //   System.out.println("**Single insert array- val: "+val+" position: "+position);
              symbol.insertSingleValue(val,position);
          }}
          else
          System.out.println("\t\t\t[update method] ErrorToAssingValueAt "+id_str+ " [differentType "+symbol.display()+"-float]");
        }
      }
 
      public static void printAll()
          {
		System.out.println("***************************************************************");                     
		Iterator it = table.entrySet().iterator();
		while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
				Symb s = (Symb)pairs.getValue();
		        System.out.println("                       nameVariable: "+pairs.getKey() + " of type " + s.display());
                        s.stamp();
                        System.out.println("***************************************************************");  
                  }
		
		
          }
        
 public static void generateInstruction(String name)
 {
   if(name.compareTo("else")==0)
   {
      ResultValue p=new ResultValue(name,null,false,"else");
      addList(p,-1); 
    }
    else{
   
   ResultValue p=new ResultValue(name,null,false,"instruction");
   addList(p,-1); 
    }
   }   
   
   public static void generateFunctionInstruction(String val1, String val2, String val3)
 {
   ResultValue p=new ResultValue(val1+"$"+val2+"$"+val3+"$",null,false,"function");
   addList(p,-1); 
   
   } 
          
          
public static void addList(Object name1,int count)
{
  ResultValue s=(ResultValue)name1;
  String expr=s.getName();
  String temp1=expr.substring(0,1);
  //Nel caso di while
  if(temp1.compareTo("w")==0)
  {
    s.setValue(count);
  }
  
  whileList.add((ResultValue)s);
  System.out.println("--->In While List: ");
  String r;
   for(Iterator it=whileList.iterator();it.hasNext();){
            s=(ResultValue)it.next();
            s.print();
            
            }
  }
  
  
  public static String ExecutionWhileIn(ResultValue point)
  {
    String tot="";
      int count,position;
      String temp="";
      String temporany="";
      String expr=point.getName();
      ResultValue resultTot;
        //Mi serve per testare l'espressione del while e vedere se è ancora vera o meno
      boolean expressionValueWhile=true;
      
      //Verifico se l'espressione passata e vera o falsa e l'assegno a resultTot
      resultTot=controlCondition(expr.substring(1));
      boolean internalflag=true;    
      //Setto la varibile expressionValue perche il while deve continuare a essere eseguito solo se la condizione e true
      expressionValueWhile=resultTot.getControlValue();
      
      //Se il Controlvalore dell'espressione e  true!
      while(expressionValueWhile==true)
      {
       count=Integer.parseInt(point.getValue().toString());
        
       //Recupero la posizione dell'espressione nella lista delle istruzioni/espressioni while
         position=whileList.indexOf(point);
         position++;
          //Prendo l'istruzione dopo l'espressione 
         resultTot=(ResultValue)whileList.get(position);
       //  System.out.println("OIOIOIOI -resultTot.getName(): "+resultTot.getName()+ " --- "); 
         
       while(resultTot.getName().compareTo("}")!=0 /*&& Integer.parseInt(resultTot.getValue().toString())!=count*/)
      {
         
        if(resultTot.getType().toString().compareTo("else")==0)
        {
           System.out.println("--------------------------Zelse found: "+resultTot.getName());
              //Recupero il valore true/false dalla lista 
              internalflag=(getResultExpression());
              expressionValueList.add(!internalflag);
          }
        
        
        
        if(resultTot.getType().toString().compareTo("instruction")==0)
        {
              System.out.println("Instruction found: "+resultTot.getName());
              //Recupero il valore true/false dalla lista 
              internalflag=getResultExpression();
              if((internalflag!=false))
               // System.out.println("******NON ESEGRUO L'ISTRUZIONE: "+resultTot.getName()+" Flaginternal: "+internalflag );
             
            {
                  temp=resultTot.getName();
                    //Variabili in cui immagazzino i valori letti
                    int value;
                    int val;
                    
                   
                   //Mi serve per capire se il numero analizzato  si tratta di un float 
                   boolean flagFloat=false;
                   
                   int start=0;
                   int end=1;
                   //Memorizzo i caratteri letti uno alla volta
                   String part="";
                   //Memorizzo i caratteri appartenenti allo stesso idname
                   String sumPart="";
                   //Prendo inizialmente il prima carattere
                   part = temp.substring(start,end);
                  //Finche non arrivo alla fine della variabile
                   while(part.compareTo(";")!=0 && part.compareTo("+")!=0 && part.compareTo("-")!=0  && part.compareTo("[")!=0 && part.compareTo("=")!=0)
                   {
                     sumPart=sumPart+part;
                     start++;
                     end++;
                     part = temp.substring(start,end);
                   }
                   //Assegnazioni
                    if(part.compareTo("=")==0)
                    {
                      start++;
                      end++;
                      part = temp.substring(start,end);
                      if(part.compareTo(":")==0)
                      {
                        temporany="";
                        while(part.compareTo(";")!=0)
                        {
                          start++;
                          end++;
                          part = temp.substring(start,end);
                         
                          if(part.compareTo(";")!=0)
                            temporany=temporany+part;
                          if(part.compareTo(".")==0)
                            flagFloat=true;
                        }
                        if(flagFloat==false)
                         updateInt(sumPart,Integer.parseInt(temporany));
                        else 
                        {
                          updateFloat(sumPart,Float.parseFloat(temporany));
                          flagFloat=false;
                        }  
                      }
                      //Assegnazione di array di char o stringhe
                      else if(part.compareTo("\"")==0)
                      {
                        temporany="";
                        while(part.compareTo(";")!=0)
                        {
                          start++;
                          end++;
                          part = temp.substring(start,end);
                         
                          if(part.compareTo(";")!=0)
                            temporany=temporany+part;
                        }
                        updateStringAndArrayChar(sumPart,temporany);
                      }
                      //Assegnazine di array
                      else if(part.compareTo("{")==0)
                      {
                        temporany="";
                        start++;
                        end++;
                        part = temp.substring(start,end);
                        if(part.compareTo(":")!=0)
                        {
                          while(part.compareTo("}")!=0)
                          {
                            if(part.compareTo("}")!=0)
                              temporany=temporany+part;
                            start++;
                            end++;
                            part = temp.substring(start,end);
                          }
                          //Array di stringhe
                         updateArrayString(sumPart,temporany,"not"); 
                        }
                        else
                        {  
                            start++;
                            end++;
                            part = temp.substring(start,end);
                          while(part.compareTo("}")!=0)
                          {
                            if(part.compareTo("}")!=0)
                              temporany=temporany+part;
                            if(part.compareTo(".")==0)
                              flagFloat=true;
                            start++;
                            end++;
                            part = temp.substring(start,end);
                          }
                          //Array di interi
                          if(flagFloat==false)
                            updateArrayInt(sumPart,temporany,"not"); 
                           else
                          { 
                          //  System.out.println("OOOO");
                            updateArrayFloat(sumPart,temporany,"not"); 
                            flagFloat=false; 
                          }
                          
                       }
                      }
                      else
                      { 
                        temporany="";
                        while(part.compareTo(";")!=0)
                        {
                            temporany=temporany+part;
                            start++;
                            end++;
                            part = temp.substring(start,end);
                            count++;
                        }
                        //Assegnazione di caratteri
                        if(count==1)
                        {
                            updateChar(sumPart,temporany);
                          count=0;
                        }
                        //Assegnazione di booleani
                        else
                        {
                           count=0;
                           if(temporany.compareTo("true")==0 || temporany.compareTo("false")==0 || temporany.compareTo("TRUE")==0 || temporany.compareTo("FALSE")==0) 
                            updateBool(sumPart,Boolean.parseBoolean(temporany));                                
                        }
                        
                      }
                    }
                   
                   
                   //Incrementi
                   if(part.compareTo("+")==0)
                   {
                     addone(sumPart);
                   }
                   //Decrementi
                  if(part.compareTo("-")==0)
                   {
                     subone(sumPart);
                   }
                   //Assegnazioni o aggiornamenti di valori singoli di array
                  if(part.compareTo("[")==0)
                  {
                     temporany="";
                
                     while(part.compareTo("]")!=0)
                     { 
                       
                       start++;
                       end++;
                       part = temp.substring(start,end);
                       if(part.compareTo("]")!=0)
                         temporany=temporany+part;
                      
                     }
                    
                     //La posizione dell'array da aggiornale
                     value=Integer.parseInt(temporany);
                     
                     start=start+2;
                     end=end+2;
                     part = temp.substring(start,end);
                     temporany="";
                     //Si tratta di un numero
                     if(part.compareTo(":")==0)
                     {
                       
                       part = temp.substring(start,end);
                       while(part.compareTo(";")!=0)
                       {
                         start++;
                         end++;
                         part = temp.substring(start,end);
                         if(part.compareTo(";")!=0)
                           temporany=temporany+part;
                         if(part.compareTo(".")==0)
                            flagFloat=true;
                       } 
                       
                       if(flagFloat==false)
                       {
                          val=Integer.parseInt(temporany);
                          updateArrayInt((String)sumPart,Integer.toString(val),Integer.toString(value));  
                       }
                       else
                       {
                         updateArrayFloat((String)sumPart,temporany,Integer.toString(value));  
                         flagFloat=false;
                       }
                     }
                     else
                     {
                       temporany="";
                        while(part.compareTo(";")!=0)
                        {
                         
                         part = temp.substring(start,end);
                         if(part.compareTo(";")!=0)
                           temporany=temporany+part;
                         start++;
                         end++;
                       } 
                       updateArrayString((String)sumPart,(String)temporany,Integer.toString(value));
                       
                     }
                  }
                  resultTot=controlCondition(expr.substring(1));
                //Setto la varibile expressionValue perche il while deve continuare a essere eseguito solo se la condizione e true
                expressionValueWhile=resultTot.getControlValue();
                
                
            }
            
        }
        //Gestisco il caso in cui l'istruzione memorizzata e una funzione
        if(resultTot.getType().toString().compareTo("function")==0)
        {
             System.out.println("function found: "+resultTot.getName());
                  //Recupero il valore true/false dalla lista 
                  internalflag=getResultExpression();
                  if((internalflag!=false))
                  {
                    temp=resultTot.getName();
                    //Variabili in cui immagazzino i valori letti
                    int value;
                    int val;
                    String var1,var2,var3;
                   int correntpos=0;
                   int pos;
                   int start=0;
                   
                   String sumPart="";
                   //Prendo inizialmente il prima carattere
                   pos=temp.indexOf("$");
                     System.out.println("333333: "+sumPart+ " pos "+pos);
                   var1 = temp.substring(start,pos);
                  correntpos+=pos+1;
                   sumPart=temp.substring(pos+1);
                   pos = sumPart.indexOf("$");
                     System.out.println("333334: "+sumPart+ " pos "+pos);
                   var2 = sumPart.substring(start,pos);
                   correntpos+=pos;
                    
                   sumPart=temp.substring(correntpos+1);
                   pos = sumPart.indexOf("$");
                   System.out.println("333335: "+sumPart+ " pos "+correntpos);
                   var3 = sumPart.substring(start,pos);
                   System.out.println("OOOOOOOOOOOOOOIOIOOOOOOOOOOIOIOO var1 "+var1+" var2: "+var2+" var3: "+var3);
                    
                   tot+=ExecutionFunction((String)var1,(String)var2,(String)var3); 
                    
                    
              
                 }
        }
        
        
        
        
        position++;
        resultTot=(ResultValue)whileList.get(position);
      }
      //System.out.println("88888888888888888888888");
      printAll();
      //Ri aggiorno il valore della flag testando nuovamente l'espressione
      resultTot=controlCondition(expr.substring(1));
      expressionValueWhile=resultTot.getControlValue();
    }
     
    
    return tot;
    
  }
  
  
  public static String ExecutionWhile()
  {
      String tot="";
      ResultValue result=null;
      ResultValue resultTot=null;
      //Memorizza il valore, true o false, dell'espressione corrente
     
      //Per if e while annidati
      boolean internalflag=true;
    //Mi serve per testare l'espressione del while e vedere se è ancora vera o meno
      boolean expressionValueWhile=true;
    
      String temp="";
      String temporany="";
      //Lo uso per capire se si tratta di char o di stringa
      int count=0;
    //Se non ci sono espressioni memorizzate nella lista delle espressioni del while
    if(whileList.size()==0)
    {
      System.out.println("##La lista whileList e vuota");
    }
    else
    {   //Finche la condizione del while è vera!
        while(expressionValueWhile!=false)
        { 
    //      System.out.println("*****RIESEGUO IL TUTTO!!!");     
          for(Iterator it=whileList.iterator();it.hasNext();)
            {
              
                result=(ResultValue)it.next();
                if(expressionValueWhile!=false)
                {
                  
                   if(result.getType().toString().compareTo("else")==0)
        {
           System.out.println("--------------------->else found: "+resultTot.getName());
              //Recupero il valore true/false dalla lista 
              internalflag=(getResultExpression());
              expressionValueList.add(!internalflag);
          }
        
                if(result.getType().toString().compareTo("instruction")==0)
                {
                    System.out.println("Instruction found: "+result.getName());
                    //Recupero il valore true/false dalla lista 
                    internalflag=getResultExpression();
                    if((internalflag!=false))
      //                System.out.println("******NON ESEGRUO L'ISTRUZIONE: "+result.getName()+" Flaginternal: "+internalflag );
                   
                  {
                      
          //              System.out.println("*******************DENTRO - "+" Flaginternal: "+internalflag);    
                         //Aggiungo un punto di fine istruzine
                         temp=result.getName();
                          //Variabili in cui immagazzino i valori letti
                          int value;
                          int val;
                          
                         
                         //Mi serve per capire se il numero analizzato  si tratta di un float 
                         boolean flagFloat=false;
                         
                         int start=0;
                         int end=1;
                         //Memorizzo i caratteri letti uno alla volta
                         String part="";
                         //Memorizzo i caratteri appartenenti allo stesso idname
                         String sumPart="";
                         //Prendo inizialmente il prima carattere
                         part = temp.substring(start,end);
                        //Finche non arrivo alla fine della variabile
                         while(part.compareTo(";")!=0 && part.compareTo("+")!=0 && part.compareTo("-")!=0  && part.compareTo("[")!=0 && part.compareTo("=")!=0)
                         {
                           sumPart=sumPart+part;
                           start++;
                           end++;
                           part = temp.substring(start,end);
                         }
                         //Assegnazioni
                          if(part.compareTo("=")==0)
                          {
                            start++;
                            end++;
                            part = temp.substring(start,end);
                            if(part.compareTo(":")==0)
                            {
                              temporany="";
                              while(part.compareTo(";")!=0)
                              {
                                start++;
                                end++;
                                part = temp.substring(start,end);
                               
                                if(part.compareTo(";")!=0)
                                  temporany=temporany+part;
                                if(part.compareTo(".")==0)
                                  flagFloat=true;
                              }
                              if(flagFloat==false)
                               updateInt(sumPart,Integer.parseInt(temporany));
                              else 
                              {
                                updateFloat(sumPart,Float.parseFloat(temporany));
                                flagFloat=false;
                              }  
                            }
                            //Assegnazione di array di char o stringhe
                            else if(part.compareTo("\"")==0)
                            {
                              temporany="";
                              while(part.compareTo(";")!=0)
                              {
                                start++;
                                end++;
                                part = temp.substring(start,end);
                               
                                if(part.compareTo(";")!=0)
                                  temporany=temporany+part;
                              }
                              updateStringAndArrayChar(sumPart,temporany);
                            }
                            //Assegnazine di array
                            else if(part.compareTo("{")==0)
                            {
                              temporany="";
                              start++;
                              end++;
                              part = temp.substring(start,end);
                              if(part.compareTo(":")!=0)
                              {
                                while(part.compareTo("}")!=0)
                                {
                                  if(part.compareTo("}")!=0)
                                    temporany=temporany+part;
                                  start++;
                                  end++;
                                  part = temp.substring(start,end);
                                }
                                //Array di stringhe
                               updateArrayString(sumPart,temporany,"not"); 
                              }
                              else
                              {  
                                  start++;
                                  end++;
                                  part = temp.substring(start,end);
                                while(part.compareTo("}")!=0)
                                {
                                  if(part.compareTo("}")!=0)
                                    temporany=temporany+part;
                                  if(part.compareTo(".")==0)
                                    flagFloat=true;
                                  start++;
                                  end++;
                                  part = temp.substring(start,end);
                                }
                                //Array di interi
                                if(flagFloat==false)
                                  updateArrayInt(sumPart,temporany,"not"); 
                                 else
                                { 
                                //  System.out.println("OOOO");
                                  updateArrayFloat(sumPart,temporany,"not"); 
                                  flagFloat=false; 
                                }
                                
                             }
                            }
                            else
                            { 
                              temporany="";
                              while(part.compareTo(";")!=0)
                              {
                                  temporany=temporany+part;
                                  start++;
                                  end++;
                                  part = temp.substring(start,end);
                                  count++;
                              }
                              //Assegnazione di caratteri
                              if(count==1)
                              {
                                  updateChar(sumPart,temporany);
                                count=0;
                              }
                              //Assegnazione di booleani
                              else
                              {
                                 count=0;
                                 if(temporany.compareTo("true")==0 || temporany.compareTo("false")==0 || temporany.compareTo("TRUE")==0 || temporany.compareTo("FALSE")==0) 
                                  updateBool(sumPart,Boolean.parseBoolean(temporany));                                
                              }
                              
                            }
                          }
                         
                         
                         //Incrementi
                         if(part.compareTo("+")==0)
                         {
                           addone(sumPart);
                         }
                         //Decrementi
                        if(part.compareTo("-")==0)
                         {
                           subone(sumPart);
                         }
                         //Assegnazioni o aggiornamenti di valori singoli di array
                        if(part.compareTo("[")==0)
                        {
                           temporany="";
                      
                           while(part.compareTo("]")!=0)
                           { 
                             
                             start++;
                             end++;
                             part = temp.substring(start,end);
                             if(part.compareTo("]")!=0)
                               temporany=temporany+part;
                            
                           }
                          
                           //La posizione dell'array da aggiornale
                           value=Integer.parseInt(temporany);
                           
                           start=start+2;
                           end=end+2;
                           part = temp.substring(start,end);
                           temporany="";
                           //Si tratta di un numero
                           if(part.compareTo(":")==0)
                           {
                             
                             part = temp.substring(start,end);
                             while(part.compareTo(";")!=0)
                             {
                               start++;
                               end++;
                               part = temp.substring(start,end);
                               if(part.compareTo(";")!=0)
                                 temporany=temporany+part;
                               if(part.compareTo(".")==0)
                                  flagFloat=true;
                             } 
                             
                             if(flagFloat==false)
                             {
                                val=Integer.parseInt(temporany);
                                updateArrayInt((String)sumPart,Integer.toString(val),Integer.toString(value));  
                             }
                             else
                             {
                               updateArrayFloat((String)sumPart,temporany,Integer.toString(value));  
                               flagFloat=false;
                             }
                           }
                           else
                           {
                             temporany="";
                              while(part.compareTo(";")!=0)
                              {
                               
                               part = temp.substring(start,end);
                               if(part.compareTo(";")!=0)
                                 temporany=temporany+part;
                               start++;
                               end++;
                             } 
                             updateArrayString((String)sumPart,(String)temporany,Integer.toString(value));
                             
                           }
                        }
                      
                      
                  }
                  
                }
                else if(result.getType().toString().compareTo("Rightbrace")==0)
                {
                    
                  deleteResultExpression();
                }
                else if(result.getType().toString().compareTo("function")==0)
                {
                  System.out.println("function found: "+result.getName());
                  //Recupero il valore true/false dalla lista 
                  internalflag=getResultExpression();
                  if((internalflag!=false))
                  {
                    temp=result.getName();
                    //Variabili in cui immagazzino i valori letti
                    int value;
                    int val;
                    String var1,var2,var3;
                   int correntpos=0;
                   int pos;
                   int start=0;
                   
                   String sumPart="";
                   //Prendo inizialmente il prima carattere
                   pos=temp.indexOf("$");
                     System.out.println("333333: "+sumPart+ " pos "+pos);
                   var1 = temp.substring(start,pos);
                  correntpos+=pos+1;
                   sumPart=temp.substring(pos+1);
                   pos = sumPart.indexOf("$");
                     System.out.println("333334: "+sumPart+ " pos "+pos);
                   var2 = sumPart.substring(start,pos);
                   correntpos+=pos;
                    
                   sumPart=temp.substring(correntpos+1);
                   pos = sumPart.indexOf("$");
                   System.out.println("333335: "+sumPart+ " pos "+correntpos);
                   var3 = sumPart.substring(start,pos);
                   System.out.println("OOOOOOOOOOOOOOIOIOOOOOOOOOOIOIOO var1 "+var1+" var2: "+var2+" var3: "+var3);
                    
                   tot+=ExecutionFunction((String)var1,(String)var2,(String)var3); 
                    
                    
              
                 }
                }
                //Si tratta di una espressione di un while o un if
                else
              {
                  String expr=result.getName();
                  String temp1=expr.substring(0,1);
                  //Nel caso di while
                  if(temp1.compareTo("w")==0)
                  {
                    if(Integer.parseInt(result.getValue().toString())==1)
                    {
                      System.out.println("SONO DENTROOO");
                    //  System.out.println("expressionValueWhile PRIMA--->"+expressionValueWhile);
                      //Effettuo l'analisi del ResultValue associato all'espressione analizzata
                      resultTot=controlCondition(expr.substring(1));       
                      
                      
                      //Setto la varibile expressionValue perche il while deve continuare a essere eseguito solo se la condizione e true
                      expressionValueWhile=resultTot.getControlValue();
                     // System.out.println("expressionValueWhile DOPO--->"+expressionValueWhile);
                      //Inserisco nella lista dei valori delle espressioni, true o false
                      insertResultExpression(expressionValueWhile);
                    }
                    //qui devo fare qualcosa quando value dell'expression è maggiore di 1!!!!!
                    if(Integer.parseInt(result.getValue().toString())>1)
                    {
                       // System.out.println("OAOAAOAOAOAOAOAOOAOAOAOAOAOAOAOAOAOAOA: "+Integer.parseInt(result.getValue().toString()));
                      ExecutionWhileIn(result);
                      resultTot=controlCondition(result.getName());
                      insertResultExpression(resultTot.getControlValue());
   //                    insertResultExpression(expressionValueWhile);
                      //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOTONNAI: "+ resultTot.getControlValue());
                    }
                    
                  }
                  //Caso if
                  if(temp1.compareTo("f")==0)
                  {
                     //Effettuo l'analisi del ResultValue associato all'espressione analizzata
                     resultTot=controlCondition(expr.substring(1));  
                      //Inserisco nella lista dei valori delle espressioni, true o false
                      insertResultExpression(resultTot.getControlValue());
                  }
              
               }
            }   
        }
    }
  }   
      whileList.clear();
      if(expressionValueWhile==false)
      {
        deleteResultExpression();
      }
      return tot;
  }
  

  
  
//Nel momento in cui si riduce il while( sono GIA state eseguite tutte le istruzioni del corpo del while per la prima volta)      
public static ResultValue controlCondition(Object expr)
{
   String temp="";
  //La uso per capire se la variabile è una lettera o un numero
  boolean flag=false;
  ResultValue point;
  ResultValue result=null;
  ResultValue result2=null;
  ResultValue resultTot=null;
  ResultValue result1=null;
  boolean iteralflag=true;
 
      //La whileList è vuota se la condizione del while è gia non verificata in partenza     
      if(whileList.size()!=0)
      {
           //DA ELIMINARE condizione expr = null .. non piu usata!!
          //Expr è null quando la funzione ControlCondition è chiamata a seguito della riduzione della regola associata al while
          if(expr==null)
           {
              
              point=(ResultValue)whileList.getFirst();
             //Aggiungo un "." alla fine dell'expression, come simbolo di fine stringa.
             temp=point.getName()+".";
           }
           else
               temp=expr.toString()+".";
           System.out.println("\nExpression analized: "+temp);
            
           int start=0;
           int end=1;
           //Memorizzo i caratteri letti uno alla volta
           String part="";
           //Memorizzo i caratteri appartenenti allo stesso idname
           String sumPart="";
           //Mi serve per memorizzare l'operatore +,*,/*-
           String operatorIter="";
          
          
           //Prendo inizialmente il prima carattere
           part = temp.substring(start,end);
           //Finche non arrivo alla fine dell'espressione
           while(part.compareTo(".")!=0 && iteralflag==true)
           {
             
             String tempVar="";
             //entro nell'if solo il carattere letto e una lettera
             if(part.compareTo("+")!=0 && part.compareTo("-")!=0 && part.compareTo("*")!=0 && part.compareTo("/")!=0 && part.compareTo(">")!=0 && part.compareTo("<")!=0 && part.compareTo("=")!=0 && part.compareTo("!")!=0 && part.compareTo("&")!=0 && part.compareTo("|")!=0)
              { 
                 //Il carattere letto è un numero
                 if(part.compareTo(":")==0)
                {
                   System.out.println("Sono arrivato ai due punti-->"+part);
                   
                    while(part.compareTo(".")!=0 && part.compareTo("&")!=0 && part.compareTo("|")!=0)
                    {
                      start++;
                      end++;
                      part = temp.substring(start,end);
                      tempVar=tempVar+part;
                      flag=true;
                    }
                    start--;
                    end--;
                    tempVar=tempVar.substring(0,start);
                    
                    
                    System.out.println("  tempVar------->>"+tempVar+"  part ------>>"+part);
                } 
                 
                    //Aggiungo il carattere letto a costruire il nome della variabile         
                    sumPart=sumPart+part;
                    start++;
                    end++;
                    part = temp.substring(start,end);
                  
                   System.out.println("  sumPart------->>"+sumPart+"  part2 ------>>"+part);
                   if(flag==false)
                   {
                      System.out.println("Chiamo resultValue per: "+sumPart);
                      resultTot=GenerateResultValue((String)sumPart);
                    
                   }
                    else
                    {
                      try{                  System.out.println("E stato rilevato un valore intero-->"+Integer.parseInt(tempVar));
                      resultTot=GenerateResultValue(Integer.parseInt(tempVar));  
                      iteralflag=false;
                        break;
                      }catch(Exception e)
                      {
                        System.out.println("E stato rilevato un valore booleano-->"+Boolean.parseBoolean(tempVar));
                        resultTot=GenerateResultValue(Boolean.parseBoolean(tempVar));
                        iteralflag=false;
                        break;
                        
                      }
                    }
                  
               }
               else
                {
                   System.out.println("Espressione analizzata nell'else @part--> "+part+ " @temp:-->"+temp.substring(end-1));
                   if(part.compareTo("+")==0 || part.compareTo("-")==0 || part.compareTo("*")==0 || part.compareTo("/")==0 )
                    {
                        flag=false;
                        //Passo a result il ResultValue relativo alla prima variabile (e.pippo) 
                        result=resultTot;
                        String firstVar="";
                        String secondVar="";
                        String actualOperator="";
                        int count=0;
                      if(result==null)
                          System.out.println("result null");
                         
                         if(part.compareTo("+")==0 || part.compareTo("-")==0)
                      {  
                           System.out.println("Trovato un operatore + o -");
                           //Salvo l'operatore + o -
                           operatorIter=part;
                           
                           
                            //Verifico se ci sono operatori * e / prima di una uguaglianza, differenza o fine espressione
                            while(part.compareTo("*")!=0 && part.compareTo("/")!=0 && part.compareTo("=")!=0 && part.compareTo("!")!=0 && part.compareTo(".")!=0)
                            {
                               start++;
                               end++;
                               part = temp.substring(start,end);
                               //Costruisco l'eventuale prima variabile del prodotto/differenza
                               firstVar+=part;
                               count++;
                            }
                          
                            count--;
                            firstVar=firstVar.substring(0,count);
                            System.out.println("Il valore di @part "+ part+ " @firstVar "+firstVar);
                            
                            //Salvo l'operatore * o /
                            if(part.compareTo("*")==0 || part.compareTo("/")==0)
                              actualOperator=part;
                            
                            if(part.compareTo("*")==0 || part.compareTo("/")==0)
                            {
                              System.out.println("E' stato individuato un operatore "+part+" che ha la precedenza sull'operatore "+operatorIter);
                              //Procedo in avanti per ricavare la seconda variabile del prodotto/differenza
                              
                              start++;
                              end++;
                               part = temp.substring(start,end);
                              while(part.compareTo(".")!=0 && part.compareTo(">")!=0 && part.compareTo("<")!=0 && part.compareTo("=")!=0 && part.compareTo("!")!=0 
                              && part.compareTo("+")!=0 && part.compareTo("-")!=0 && part.compareTo("*")!=0 && part.compareTo("/")!=0  && part.compareTo("|")!=0 && part.compareTo("&")!=0)
                              {
                                secondVar+=part;  
                                start++;
                                end++;
                                part = temp.substring(start,end);
                               
                              }
                              System.out.println("@Part "+part+" @SecondVar: "+secondVar);
                              
                              //A questo punto ricavo il resultValue della prima variabile della moltiplicazione o divisione
                              
                              //Se firstValue non contiene :
                              if(firstVar.indexOf(":")==-1)
                              {
                                System.out.println("Genero il resultValue della variabile: "+ firstVar);
                                result1=GenerateResultValue((String)firstVar);
                              }
                              else
                              {
                                try
                                  {                 
                                    System.out.println("E stato rilevato un valore intero-->"+Integer.parseInt(firstVar.substring(1)));
                                    result1=GenerateResultValue(Integer.parseInt(firstVar.substring(1)));  
                                  }
                                  catch(Exception e)
                                  {
                                      System.out.println("E stato rilevato un valore booleano-->"+Boolean.parseBoolean(firstVar.substring(1)));
                                      result1=GenerateResultValue(Boolean.parseBoolean(firstVar.substring(1)));
                                  }
                              }
                              
                              
                              //A questo punto ricavo il resultValue della seconda variabile della moltiplicazione o divisione
                              
                              //Se firstValue non contiene :
                              if(secondVar.indexOf(":")==-1)
                              {
                                System.out.println("Genero il resultValue della variabile: "+ secondVar);
                                result2=GenerateResultValue((String)firstVar);
                              }
                              else
                              {
                                try
                                  {                 
                                    System.out.println("E stato rilevato un valore intero-->"+Integer.parseInt(secondVar.substring(1)));
                                    result2=GenerateResultValue(Integer.parseInt(secondVar.substring(1)));  
                                  }
                                  catch(Exception e)
                                  {
                                      System.out.println("E stato rilevato un valore booleano-->"+Boolean.parseBoolean(secondVar.substring(1)));
                                      result2=GenerateResultValue(Boolean.parseBoolean(secondVar.substring(1)));
                                  }
                              }
                              
                              if(actualOperator.compareTo("*")==0)
                                  resultTot=GenerateResultValue(result1,"*",result2);
                              if(actualOperator.compareTo("/")==0)
                                 resultTot=GenerateResultValue(result1,"/",result2);
                              
                              System.out.println(" Ho ricavato in resultTot il ResultValue: ");
                              resultTot.print();
                             
                              if(operatorIter.compareTo("+")==0 &&  result!=null )
                              {
                                  resultTot=GenerateResultValue(result,"+",resultTot);
                              }
                              if(operatorIter.compareTo("+")==0 &&  result==null )
                              {
                                  resultTot=GenerateResultValue("+",resultTot);
                              }
                              if(operatorIter.compareTo("-")==0 && result!=null )
                             {
                               resultTot=GenerateResultValue(result,"+",resultTot);
                             }
                             if(operatorIter.compareTo("-")==0 && result==null )
                             {
                               resultTot=GenerateResultValue("-",resultTot);
                             }
                              
                             System.out.println("********* Ho risolto la precedenza!!!");
                             resultTot.print();
                              
                          }
                          //Ovvero considero il caso in cui l'operatore + o - non è seguito da un operatore * oppure /
                          if(part.compareTo("=")!=0 && part.compareTo("!")!=0 && part.compareTo(".")!=0 )
                          {
                            if(firstVar.indexOf(":")==-1)
                              {
                                System.out.println("Genero il resultValue della variabile: "+ firstVar);
                                result2=GenerateResultValue((String)firstVar);
                              }
                              else
                              {
                                try
                                  {                 
                                    System.out.println("E stato rilevato un valore intero-->"+Integer.parseInt(firstVar.substring(1)));
                                    result2=GenerateResultValue(Integer.parseInt(firstVar.substring(1)));  
                                  }
                                  catch(Exception e)
                                  {
                                      System.out.println("E stato rilevato un valore booleano-->"+Boolean.parseBoolean(firstVar.substring(1)));
                                      result2=GenerateResultValue(Boolean.parseBoolean(firstVar.substring(1)));
                                  }
                              } 
                            
                         
                          
                            
                           
                           /* 
                            start++;
                            end++;
                            //Leggo la lettera successiva all'operatore
                            part = temp.substring(start,end);
                           //Nel caso in cui il carattere letto dopo l'operatore +,-,.. sia un numero
                           if(part.compareTo(":")==0)
                           {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                              flag=true;
                           }                            
                            //finche non compare un altro operatore o la fine inserisco tutte le lettere o i numeri nella stessa variabile
                            while(part.compareTo(".")!=0 && part.compareTo(">")!=0 && part.compareTo("<")!=0 && part.compareTo("=")!=0 && part.compareTo("!")!=0 
                              && part.compareTo("+")!=0 && part.compareTo("-")!=0 && part.compareTo("*")!=0 && part.compareTo("/")!=0  && part.compareTo("|")!=0 && part.compareTo("&")!=0)
                            {
                              sumPart="";
                              sumPart=sumPart+part;
                              start++;
                              end++;
                              part = temp.substring(start,end);
                              
                            }//Quando esco in part c'è l'operatore oppure la fine stringa
                           //Se la variabile contiene lettere
                           if(flag==false)
                           {
                             result2=GenerateResultValue((String)sumPart);
                            
                           }
                           //Se la variabile contiene numeri
                           else
                          {
                            result2=GenerateResultValue(Integer.parseInt(sumPart));  
                           
                          }
                           */
                         if(operatorIter.compareTo("+")==0 &&  result!=null )
                                {
                                  resultTot=GenerateResultValue(result,"+",result2);
                               }
                          if(operatorIter.compareTo("+")==0 &&  result==null )
                                {
                                  resultTot=GenerateResultValue("+",result2);
                                }
                           if(operatorIter.compareTo("-")==0 && result!=null )
                           {
                             resultTot=GenerateResultValue(result,"+",result2);
                           }
                           if(operatorIter.compareTo("-")==0 && result==null )
                           {
                             resultTot=GenerateResultValue("-",result2);
                           }
                          
                             
                        }
                          
                      }
                      if(part.compareTo("*")==0 || part.compareTo("/")==0)
                      {
                        if(firstVar.indexOf(":")==-1)
                              {
                                System.out.println("Genero il resultValue della variabile: "+ firstVar);
                                result2=GenerateResultValue((String)firstVar);
                              }
                              else
                              {
                                try
                                  {                 
                                    System.out.println("E stato rilevato un valore intero-->"+Integer.parseInt(firstVar.substring(1)));
                                    result2=GenerateResultValue(Integer.parseInt(firstVar.substring(1)));  
                                  }
                                  catch(Exception e)
                                  {
                                      System.out.println("E stato rilevato un valore booleano-->"+Boolean.parseBoolean(firstVar.substring(1)));
                                      result2=GenerateResultValue(Boolean.parseBoolean(firstVar.substring(1)));
                                  }
                              }

                         if(operatorIter.compareTo("*")==0)
                              resultTot=GenerateResultValue(result,"*",result2);
                          if(operatorIter.compareTo("/")==0)
                              resultTot=GenerateResultValue(result,"/",result2);       
                        
                      }
                      
                    }
                    if(part.compareTo(">")==0 || part.compareTo("<")==0 || part.compareTo("=")==0 || part.compareTo("!")==0 || part.compareTo("|")==0 || part.compareTo("&")==0)
                    {
                        
                        result=resultTot; 
                        if(part.compareTo("&")==0)
                        {
                          System.out.println("Ho rilevato la presenza di un & - part-->"+part);
                           
                           start=1;
                           end=2;
                           part = temp.substring(start,end);
                          System.out.println(" part-->"+part);
                           if(part.compareTo("&")==0)
                           {
                             
                             System.out.println("Ho rilevato la presenza di un secondo & - part-->"+part);
                             System.out.println("ControlCondition di--->"+temp.substring(end));
                             result2=controlCondition(temp.substring(end));
                             resultTot=GenerateResultValue(result,"&&",result2);     
                             System.out.println("Sono tornato!-->"+temp.substring(end+1));
                             temp = temp.substring(end+1);
                             start=0;
                             end=1;
                             part = temp.substring(start,end);
                             //Tolgo dalla stringa la parte gia analizzata.
                             while( part.compareTo(".")!=0 &&  part.compareTo("&")!=0 &&  part.compareTo("|")!=0) 
                             {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                             }
                             temp=temp.substring(end-1);
                             System.out.println("Parte ancora da analizzare2:-->"+temp+ "part-->"+part); 
                             
                           }
                        }
                        if(part.compareTo("|")==0)
                        {
                           //Leggo il secondo carattere che puo essere =,oppure >
                           start=1;
                           end=2;
                           part = temp.substring(start,end);
                           if(part.compareTo("|")==0)
                           {
                             System.out.println("Ho rilevato la presenza di un secondo | - part-->"+part);
                             System.out.println("ControlCondition di--->"+temp.substring(end));
                             result2=controlCondition(temp.substring(end));
                             resultTot=GenerateResultValue(result,"||",result2);     
                             System.out.println("Sono tornato!-->"+temp.substring(end+1));
                             temp = temp.substring(end+1);
                             start=0;
                             end=1;
                             part = temp.substring(start,end);
                             //Tolgo dalla stringa la parte gia analizzata.
                             while( part.compareTo(".")!=0 &&  part.compareTo("&")!=0 &&  part.compareTo("|")!=0) 
                             {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                             }
                             temp=temp.substring(end-1);
                             System.out.println("Parte ancora da analizzare2:-->"+temp+ "part-->"+part); 
                             
                           }
                        }
                      
                        if(part.compareTo(">")==0)
                        {
                         System.out.println("ControlCondition di--->"+temp.substring(end));
                         result2=controlCondition(temp.substring(end));
                         resultTot=GenerateResultValue(result,">",result2);     
                         
                          System.out.println("Sono tornato!-->"+temp.substring(end+1));
                             temp = temp.substring(end+1);
                             start=0;
                             end=1;
                             part = temp.substring(start,end);
                             //Tolgo dalla stringa la parte gia analizzata.
                             while( part.compareTo(".")!=0 &&  part.compareTo("&")!=0 &&  part.compareTo("|")!=0) 
                             {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                             }
                             temp=temp.substring(end-1);
                             System.out.println("Parte ancora da analizzare2:-->"+temp+ "part-->"+part);       
                          
                        }
                         if(part.compareTo("<")==0)
                        {
                           start++;
                           end++;
                           part = temp.substring(start,end);
                           if(part.compareTo("=")==0)
                           {
                             System.out.println("ControlCondition di--->"+temp.substring(end));
                             result2=controlCondition(temp.substring(end));
                             resultTot=GenerateResultValue(result,"<=",result2);     
                              
                             System.out.println("Sono tornato!-->"+temp.substring(end+1));
                             temp = temp.substring(end+1);
                             start=0;
                             end=1;
                             part = temp.substring(start,end);
                             //Tolgo dalla stringa la parte gia analizzata.
                             while( part.compareTo(".")!=0 &&  part.compareTo("&")!=0 &&  part.compareTo("|")!=0) 
                             {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                             }
                             temp=temp.substring(end-1);
                             System.out.println("Parte ancora da analizzare2:-->"+temp+ "part-->"+part);
                           }
                           else
                           {
                             start--;
                             end--;   
                             System.out.println("ControlCondition di--->"+temp.substring(end));
                             result2=controlCondition(temp.substring(end));
                             resultTot=GenerateResultValue(result,"<",result2);     
                             
                             System.out.println("Sono tornato!-->"+temp.substring(end+1));
                             temp = temp.substring(end+1);
                             start=0;
                             end=1;
                             part = temp.substring(start,end);
                             //Tolgo dalla stringa la parte gia analizzata.
                             while( part.compareTo(".")!=0 &&  part.compareTo("&")!=0 &&  part.compareTo("|")!=0) 
                             {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                             }
                             temp=temp.substring(end-1);
                             System.out.println("Parte ancora da analizzare2:-->"+temp+ "part-->"+part);
                           }
                        }
                        //Caso !=
                         if(part.compareTo("!")==0)
                        {
                          if(result==null)
                          {
                           
                            start++;
                            end++;
                            part = temp.substring(start,end);
                            while(part.compareTo(".")!=0 && part.compareTo(">")!=0 && part.compareTo("<")!=0 && part.compareTo("=")!=0 && part.compareTo("!")!=0 
                              && part.compareTo("+")!=0 && part.compareTo("-")!=0 && part.compareTo("*")!=0 && part.compareTo("/")!=0 &&  part.compareTo("|")!=0  && part.compareTo("&")!=0  )
                              {
                                sumPart="";
                                sumPart=sumPart+part;
                                start++;
                                end++;
                                part = temp.substring(start,end);
                              }
                              
                            result=GenerateResultValue((String)sumPart);
                            resultTot=GenerateResultValue("!",result);
                            result=resultTot;                              
                          }
                          else
                          {
                          
                          //Passo davanti di una posizione l'end cosi da leggere il carattere =
                           end++;
                           System.out.println("ControlCondition di--->"+temp.substring(end));
                           result2=controlCondition(temp.substring(end));
                           resultTot=GenerateResultValue(result,"!=",result2);     
                           
                           System.out.println("Sono tornato!-->"+temp.substring(end+1));
                             temp = temp.substring(end+1);
                             start=0;
                             end=1;
                             part = temp.substring(start,end);
                             //Tolgo dalla stringa la parte gia analizzata.
                             while( part.compareTo(".")!=0 &&  part.compareTo("&")!=0 &&  part.compareTo("|")!=0) 
                             {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                             }
                             temp=temp.substring(end-1);
                             System.out.println("Parte ancora da analizzare2:-->"+temp+ "part-->"+part); 
                          }
                        }
                        
                        if(part.compareTo("=")==0)
                        {
                           //Leggo il secondo carattere che puo essere =,oppure >
                           start++;
                           end++;
                           part = temp.substring(start,end);
                           if(part.compareTo("=")==0)
                          {
                             System.out.println("E stata rilevata la presenza di un =");
                             System.out.println("ControlCondition di--->"+temp.substring(end));
                             result2=controlCondition(temp.substring(end));
                             
                            System.out.println("o********** stampo result");
                            result.print();
                             resultTot=GenerateResultValue(result,"==",result2);
                            
                             System.out.println("Sono tornato!-->"+temp.substring(end+1));
                             temp = temp.substring(end+1);
                             start=0;
                             end=1;
                             part = temp.substring(start,end);
                             //Tolgo dalla stringa la parte gia analizzata.
                             while( part.compareTo(".")!=0 &&  part.compareTo("&")!=0 &&  part.compareTo("|")!=0) 
                             {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                             }
                             temp=temp.substring(end-1);
                             System.out.println("Parte ancora da analizzare2:-->"+temp+ "part-->"+part);
                             
                          }
                           if(part.compareTo(">")==0)
                           {
                             System.out.println("ControlCondition di--->"+temp.substring(end));
                             
                             result2=controlCondition(temp.substring(end));
                             resultTot=GenerateResultValue(result,"=>",result2);     
                             
                             System.out.println("Sono tornato!-->"+temp.substring(end+1));
                             temp = temp.substring(end+1);
                             start=0;
                             end=1;
                             part = temp.substring(start,end);
                             //Tolgo dalla stringa la parte gia analizzata.
                             while( part.compareTo(".")!=0 &&  part.compareTo("&")!=0 &&  part.compareTo("|")!=0) 
                             {
                              start++;
                              end++;
                              part = temp.substring(start,end);
                             }
                             temp=temp.substring(end-1);
                             System.out.println("Parte ancora da analizzare2:-->"+temp+ "part-->"+part);
                             
                           }
                        }
                       
                    }
                }
               
          }
          System.out.println("part=="+part+" InternalFlag "+iteralflag+"   ->ControlCondition Close; ResultValueRitornato: ");
          resultTot.print();
          System.out.println("\n");
            return resultTot;
         
      } 
  //La condizione è gia falsa alla prima analisi
  else return null;
}
   
 
    //La funzione crea un oggetto Result Value gestendo le situazioni del tipo while(a) o if(a), dunque un confronto con 0.
    public static ResultValue GenerateResultValue(Object name)
    {
        ResultValue point=null;
      try{
      
      
        //La variabile analizzata è letterale, es. a
        if(!table.containsKey((String)name))
         {
           //Se non è contenuta nella symbol table, value e type =null;
           point=new ResultValue(name,null,false,null);
           System.out.println("##Error: La variabile: "+name.toString()+" non e' stata dichiarata.. ");
         }
       else 
       {
          Symb symbol=(Symb)table.get((String)name);
          //Se nè contenuta nella symbol table ma non è inizializzata oppure si tratta di un array--> value=null
          if(symbol.displayValue()==null)
             {
                point=new ResultValue(name,null,false,symbol.display());
                System.out.println("##Error: La variabile: "+name.toString()+" non e' stata assegnata.. ");
             }
          else
          {
               //Recupero da Symb il Type(sottoforma di stringa) e verifico che sia un tipo accettato e meno nella struttura di tipo while(a) o if(a)
               if(symbol.display().compareTo("int")==0 && symbol.displayIsArray()==false)
               {
                  int value= Integer.parseInt(symbol.displayValue().toString()); 
                  if(value==0)
                  {
                     point=new ResultValue(name,value,true,symbol.display());
                  }
                  else
                  {
                     point=new ResultValue(name,value,false,symbol.display());
                  }
               }   
              if(symbol.display().compareTo("float")==0 && symbol.displayIsArray()==false)
              {
                  float value=Float.valueOf(symbol.displayValue().toString());
                  if(value==0.0)
                  {
                      point=new ResultValue(name,value,true,symbol.display());
                  }
                  else
                  {
                     point=new ResultValue(name,value,false,symbol.display());
                  }
              }
             if(symbol.display().compareTo("boolean")==0) 
             {
                  boolean value=Boolean.parseBoolean(symbol.displayValue().toString());
                  if(value==true)
                  {
                      point=new ResultValue(name,value,true,symbol.display());
                  }
                  else
                  {
                     point=new ResultValue(name,value,false,symbol.display());
                  }
            }
            //Se la variabile di tipo chat non è accettata nella struttura di tipo while(a) o if(a)
            if(symbol.display().compareTo("char")==0) 
             {
                  char value=symbol.displayValue().toString().charAt(0);
                  point=new ResultValue(name,value,false,symbol.display());
             }
            
            if(symbol.display().compareTo("String")==0 && symbol.displayIsArray()==false) 
             {
                  String value=symbol.displayValue().toString();
                  point=new ResultValue(name,value,false,symbol.display());
             }
            
          }
       }
       
         if(point!=null)
         {
         //  System.out.println("###### ResultValue is not null!! lo stampo:");
           point.print();
           
         }
        else System.out.println("###### Point is null!!");
     }
     //Gestisce il caso in cui la variabile e un numero es. 5
      catch(java.lang.ClassCastException e)
      {
        try
          {
            System.out.println("Insert in expression a value: " + name.toString());
            if((Integer.parseInt(name.toString()))==0)
              point=new ResultValue(":"+Integer.parseInt(name.toString()),Integer.parseInt(name.toString()),true,"int");
            else
              point=new ResultValue(":"+Integer.parseInt(name.toString()),Integer.parseInt(name.toString()),false,"int");
        
           if(point!=null)
             point.print();
           else System.out.println("###### Point is null!!");
            return point;
         }catch(Exception ev)
         {
           if((Boolean.parseBoolean(name.toString()))==true)
               point=new ResultValue(":"+Boolean.parseBoolean(name.toString()),Boolean.parseBoolean(name.toString()),true,"boolean");
           else
               point=new ResultValue(":"+Boolean.parseBoolean(name.toString()),Boolean.parseBoolean(name.toString()),false,"boolean");
               
           point.print();
           }
      }
      
      
       return point;
    }   
 
    //La funzione effettua un controllo in ingressi del tipo (!a), (-a), (+a)
 public static ResultValue GenerateResultValue(String operation,Object name1)
 {
   
   ResultValue point=null;
   ResultValue operand1=(ResultValue)name1;   
   if(operand1==null || operand1.getValue()==null || operand1.getType()==null)
   {
       point=new ResultValue(operation+operand1.getName(),null,false,null);
    
   }
   else if(operand1!=null && operand1.getValue()!=null && operand1.getType()!=null)
  {
    //OPERATORE !
     if(operation=="!")
     {
       if(operand1.getType().toString().compareTo("boolean")==0)
       {
         if( Boolean.parseBoolean(operand1.getValue().toString())==false )
         {
            point=new ResultValue(operation+operand1.getName(),!Boolean.parseBoolean(operand1.getValue().toString()),true,operand1.getType().toString());
         }
          else
          {
            point=new ResultValue(operation+operand1.getName(),null,false,operand1.getType().toString());
          }
       }
       else
       {
         point=new ResultValue(operation+operand1.getName(),null,false,operand1.getType().toString());
        System.out.println("Impossible apply opetator ! at "+ operand1.getType().toString());
        }
     }
     //OPERATORE -
     if(operation=="-")
     {
       if(operand1.getType().toString().compareTo("int")==0)
       {
         if( ((Integer.parseInt(operand1.getValue().toString()))*(-1))==0 )
         {
            point=new ResultValue(operation+operand1.getName(),(Integer.parseInt(operand1.getValue().toString()))*(-1),true,operand1.getType().toString());
         }
          else
          {
            point=new ResultValue(operation+operand1.getName(),null,false,operand1.getType().toString());
          }
       }
       if(operand1.getType().toString().compareTo("float")==0)
       {
         if( ((Float.parseFloat(operand1.getValue().toString()))*(-1.0))==0.0 )
         {
            point=new ResultValue(operation+operand1.getName(),(Float.parseFloat(operand1.getValue().toString()))*(-1.0),true,operand1.getType().toString());
         }
          else
          {
            point=new ResultValue(operation+operand1.getName(),null,false,operand1.getType().toString());
          }
       }
       
       else if(operand1.getType().toString().compareTo("int")!=0 && operand1.getType().toString().compareTo("float")!=0)
       {
         point=new ResultValue(operation+operand1.getName(),null,false,operand1.getType().toString());
        System.out.println("Impossible apply opetator - at "+ operand1.getType().toString());
        }
     }
     
     //OPERATORE +
     if(operation=="+")
     {
       if(operand1.getType().toString().compareTo("int")==0)
       {
         if( ((Integer.parseInt(operand1.getValue().toString())))==0 )
         {
            point=new ResultValue(operation+operand1.getName(),(Integer.parseInt(operand1.getValue().toString())),true,operand1.getType().toString());
         }
          else
          {
            point=new ResultValue(operation+operand1.getName(),null,false,operand1.getType().toString());
          }
       }
       if(operand1.getType().toString().compareTo("float")==0)
       {
         if( ((Float.parseFloat(operand1.getValue().toString())))==0.0 )
         {
            point=new ResultValue(operation+operand1.getName(),(Float.parseFloat(operand1.getValue().toString())),true,operand1.getType().toString());
         }
          else
          {
            point=new ResultValue(operation+operand1.getName(),null,false,operand1.getType().toString());
          }
       }
       
       else if(operand1.getType().toString().compareTo("int")!=0 && operand1.getType().toString().compareTo("float")!=0)
       {
         point=new ResultValue(operation+operand1.getName(),null,false,operand1.getType().toString());
        System.out.println("Impossible apply opetator + at "+ operand1.getType().toString());
        }
     }
     
     
  }
    
   if(point!=null)
    point.print();
  else System.out.println("###### Point is null!!");
    return point;
}
      
    

    
//La funzione effettua un controllo tra due variabili letterali 
public static ResultValue GenerateResultValue(Object name1,String operation,Object name2)
{
   
  

   ResultValue point=null;
   ResultValue operand1=(ResultValue)name1;
   ResultValue operand2=(ResultValue)name2;
  
   //La utilizzo per verificare che i tipi delle due variabili siano uguali
   boolean flag = false;
   
   //Se il primo operatore non è stato dichiarato, o non è stato inzializzato o non è contenuto la seconda variabile nella symbol table
   if(operand1==null || operand1.getValue()==null || operand1.getType()==null || operand2==null || operand2.getValue()==null || operand2.getType()==null  )
   {
       point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,null);
    
   }
   else if(operand1!=null && operand1.getValue()!=null && operand1.getType()!=null && operand2!=null && operand2.getValue()!=null && operand2.getType()!=null)
  {
      //Se le due variabili sono intere entrambe e non array
      if( ((operand1.getType().toString()).compareTo(operand2.getType().toString())==0 && operand1.getType().toString().compareTo("int")==0) || 
          ((operand1.getType().toString()).compareTo(operand2.getType().toString())==0 && operand1.getType().toString().compareTo("float")==0) ||
          ((operand1.getType().toString()).compareTo(operand2.getType().toString())==0 && operand1.getType().toString().compareTo("String")==0) ||
          ((operand1.getType().toString()).compareTo(operand2.getType().toString())==0 && operand1.getType().toString().compareTo("char")==0) ||
          ((operand1.getType().toString()).compareTo(operand2.getType().toString())==0 && operand1.getType().toString().compareTo("boolean")==0) )
        {
               flag=true;
          
        }
        
     else
     {
        point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,null);
       System.out.println("##Error(valid only for operation different to && and ||) incompatible type: "+ operand1.getType() +" - "+  operand2.getType() );
     }
          
  }
  
  //Lavoro sotto l'ipotesi che entrambi gli operatori siano dello stesso tipo
   if(flag==true)
   {
   
     //OPERATORE ==
     if(operation=="==")
    {    //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
         if(operand1.getType().toString().compareTo("int")==0)
         {
            if((Integer.parseInt(operand1.getValue().toString())==Integer.parseInt(operand2.getValue().toString())))
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi interi e sono uguali, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         //Definisco il comportamento nel caso di due String
        if(operand1.getType().toString().compareTo("String")==0 || operand1.getType().toString().compareTo("char")==0)
         {
            if((operand1.getValue().toString()).compareTo(operand2.getValue().toString())==0)
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),operand1.getValue().toString(),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi Stringhe o char e sono uguali, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         
          }
         //Definisco il comportamento nel caso di due boolean
         if(operand1.getType().toString().compareTo("boolean")==0)
         {
            if((Boolean.parseBoolean(operand1.getValue().toString())==Boolean.parseBoolean(operand2.getValue().toString())))
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Boolean.parseBoolean(operand1.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi boolean e sono uguali, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         
         //Definisco il comportamento nel caso di due flaot
          if(operand1.getType().toString().compareTo("float")==0)
          {
             if((Float.parseFloat(operand1.getValue().toString())==Float.parseFloat(operand2.getValue().toString())))
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi float e sono uguali, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
    }  
       //OPERATORE  
    if(operation=="!=")
    {    //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
         if(operand1.getType().toString().compareTo("int")==0)
         {
            if((Integer.parseInt(operand1.getValue().toString())!=Integer.parseInt(operand2.getValue().toString())))
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi interi e sono diversi, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         //Definisco il comportamento nel caso di due String
        if(operand1.getType().toString().compareTo("String")==0 || (operand2.getType().toString()).compareTo("char")==0)
         {
            if((operand1.getValue().toString()).compareTo(operand2.getValue().toString())!=0)
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),operand1.getValue().toString(),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi Stringhe o char e sono uguali, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         
         }
         //Definisco il comportamento nel caso di due boolean
         if(operand1.getType().toString().compareTo("boolean")==0)
         {
            if((Boolean.parseBoolean(operand1.getValue().toString())!=Boolean.parseBoolean(operand2.getValue().toString())))
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Boolean.parseBoolean(operand1.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi boolean e sono diversi, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         
         //Definisco il comportamento nel caso di due flaot
          if(operand1.getType().toString().compareTo("float")==0)
          {
             if((Float.parseFloat(operand1.getValue().toString())!=Float.parseFloat(operand2.getValue().toString())))
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi float e sono diversi, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         
    }
    //OPERATORE + (Accetta solo interi e float)
    if(operation=="+")
    {   
         
          //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
         if(operand1.getType().toString().compareTo("int")==0)
         {
            
            if((Integer.parseInt(operand1.getValue().toString())+Integer.parseInt(operand2.getValue().toString()))==0)
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString())+Integer.parseInt(operand2.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi interi somma ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString())+Integer.parseInt(operand2.getValue().toString()),false,operand1.getType().toString());
            }
         }
           //Definisco il comportamento nel caso di due flaot
          if(operand1.getType().toString().compareTo("float")==0)
          {
             if((Float.parseFloat(operand1.getValue().toString())+Float.parseFloat(operand2.getValue().toString()))==0)
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString())+Float.parseFloat(operand2.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi boolean e sono diversi, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString())+Float.parseFloat(operand2.getValue().toString()),false,operand1.getType().toString());
            }
         }
         if(operand1.getType().toString().compareTo("float")!=0 && operand1.getType().toString().compareTo("int")!=0)
         {
          
           point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
           System.out.println("##Error: Operator not definited for: "+operand1.getType().toString());
         }
         
    }
    //OPERATORE - (Accetto solo interi e float)
    if(operation=="-")
    {   
         
          //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
         if(operand1.getType().toString().compareTo("int")==0)
         {
            
            if((Integer.parseInt(operand1.getValue().toString())-Integer.parseInt(operand2.getValue().toString()))==0)
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString())-Integer.parseInt(operand2.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi interi , ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString())-Integer.parseInt(operand2.getValue().toString()),false,operand1.getType().toString());
            }
         }
           //Definisco il comportamento nel caso di due flaot
          if(operand1.getType().toString().compareTo("float")==0)
          {
             if((Float.parseFloat(operand1.getValue().toString())-Float.parseFloat(operand2.getValue().toString()))==0)
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString())-Float.parseFloat(operand2.getValue().toString()),true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi, ok");
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString())-Float.parseFloat(operand2.getValue().toString()),false,operand1.getType().toString());
            }
         }
         if(operand1.getType().toString().compareTo("float")!=0 && operand1.getType().toString().compareTo("int")!=0)
         {
          
           point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
           System.out.println("##Error: Operator not definited for: "+operand1.getType().toString());
         }
         
    }
    //OPERATORE /
    if(operation=="/")
    {   
         try
           {
              //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
             if(operand1.getType().toString().compareTo("int")==0)
             {
               if((Integer.parseInt(operand1.getValue().toString())/Integer.parseInt(operand2.getValue().toString()))==0)
              {
                point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString())/Integer.parseInt(operand2.getValue().toString()),true,operand1.getType().toString());
                System.out.println("I due operandi sono entrambi interi  ok");
              }
              else
              {   
                point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString())/Integer.parseInt(operand2.getValue().toString()),false,operand1.getType().toString());
              }
           }
             //Definisco il comportamento nel caso di due flaot
            if(operand1.getType().toString().compareTo("float")==0)
            {
               if((Float.parseFloat(operand1.getValue().toString())/Float.parseFloat(operand2.getValue().toString()))==0)
              {
                point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString())/Float.parseFloat(operand2.getValue().toString()),true,operand1.getType().toString());
                System.out.println("I due operandi sono entrambi float ok");
              }
              else
              {
                point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString())/Float.parseFloat(operand2.getValue().toString()),false,operand1.getType().toString());
              }
           }
           if(operand1.getType().toString().compareTo("float")!=0 && operand1.getType().toString().compareTo("int")!=0)
           {
             point=new ResultValue(operand1.getName()+operation+operand2.getName(),(Float.parseFloat(operand1.getValue().toString()))/(Float.parseFloat(operand2.getValue().toString())),false,operand1.getType().toString());
             System.out.println("##Error: Operator not definited for: "+operand1.getType().toString());
           }
         }catch(Exception e)
           {
              System.out.println("##Error: Impossible divide- exception");
               point=new ResultValue(operand1.getName()+operation+operand2.getName(),9999999999.99999,false,operand1.getType().toString());
            }
         
    }
    //OPERATORE *
    if(operation=="*")
    {   
         try
           {
              //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
             if(operand1.getType().toString().compareTo("int")==0)
             {
               if((Integer.parseInt(operand1.getValue().toString())*Integer.parseInt(operand2.getValue().toString()))==0)
              {
                point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString())*Integer.parseInt(operand2.getValue().toString()),true,operand1.getType().toString());
                System.out.println("I due operandi sono entrambi interi  ok");
              }
              else
              {   
                point=new ResultValue(operand1.getName()+operation+operand2.getName(),Integer.parseInt(operand1.getValue().toString())*Integer.parseInt(operand2.getValue().toString()),false,operand1.getType().toString());
              }
           }
             //Definisco il comportamento nel caso di due flaot
            if(operand1.getType().toString().compareTo("float")==0)
            {
               if((Float.parseFloat(operand1.getValue().toString())*Float.parseFloat(operand2.getValue().toString()))==0)
              {
                point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString())*Float.parseFloat(operand2.getValue().toString()),true,operand1.getType().toString());
                System.out.println("I due operandi sono entrambi float ok");
              }
              else
              {
                point=new ResultValue(operand1.getName()+operation+operand2.getName(),Float.parseFloat(operand1.getValue().toString())*Float.parseFloat(operand2.getValue().toString()),false,operand1.getType().toString());
              }
           }
           if(operand1.getType().toString().compareTo("float")!=0 && operand1.getType().toString().compareTo("int")!=0)
           {
             point=new ResultValue(operand1.getName()+operation+operand2.getName(),(Float.parseFloat(operand1.getValue().toString()))*(Float.parseFloat(operand2.getValue().toString())),false,operand1.getType().toString());
             System.out.println("##Error: Operator not definited for: "+operand1.getType().toString());
           }
         }catch(Exception e)
           {
              System.out.println("##Error: Impossible multipler- exception");
               point=new ResultValue(operand1.getName()+operation+operand2.getName(),9999999999.99999,false,operand1.getType().toString());
            }
         
    }
    //OPERATORE <
    if(operation=="<")
    {    //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
         if(operand1.getType().toString().compareTo("int")==0)
         {
           
            if((Integer.parseInt(operand1.getValue().toString())<Integer.parseInt(operand2.getValue().toString())))
            {
              //Come valore salvo null, non avrebbe senzo salvare uno dei due valori
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi interi e sono: "+operand1.getName()+operation+operand2.getName());
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         
         if(operand1.getType().toString().compareTo("float")==0)
          {
             if((Float.parseFloat(operand1.getValue().toString())<Float.parseFloat(operand2.getValue().toString())))
            {
              //Come valore salvo null, non avrebbe senzo salvare uno dei due valori
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi float e sono: "+operand1.getName()+operation+operand2.getName());
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }  
    }
    //OPERATORE >
    if(operation==">")
    {    //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
         if(operand1.getType().toString().compareTo("int")==0)
         {
            if((Integer.parseInt(operand1.getValue().toString())>Integer.parseInt(operand2.getValue().toString())))
            {
              //Come valore salvo null, non avrebbe senzo salvare uno dei due valori
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi interi e sono: "+operand1.getName()+operation+operand2.getName());
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         
         if(operand1.getType().toString().compareTo("float")==0)
          {
             if((Float.parseFloat(operand1.getValue().toString())>Float.parseFloat(operand2.getValue().toString())))
            {
              //Come valore salvo null, non avrebbe senzo salvare uno dei due valori
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi float e sono: "+operand1.getName()+operation+operand2.getName());
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }  
    
    }
    //OPERATORE <=
    if(operation=="<=")
    {    //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
         if(operand1.getType().toString().compareTo("int")==0)
         {
           
            if((Integer.parseInt(operand1.getValue().toString())<Integer.parseInt(operand2.getValue().toString())) || (Integer.parseInt(operand1.getValue().toString())==Integer.parseInt(operand2.getValue().toString())))
            {
              //Come valore salvo null, non avrebbe senzo salvare uno dei due valori
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi interi e sono: "+operand1.getName()+operation+operand2.getName());
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         
         if(operand1.getType().toString().compareTo("float")==0)
          {
             if((Float.parseFloat(operand1.getValue().toString())<Float.parseFloat(operand2.getValue().toString())) || (Float.parseFloat(operand1.getValue().toString())<Float.parseFloat(operand2.getValue().toString())))
            {
              //Come valore salvo null, non avrebbe senzo salvare uno dei due valori
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi float e sono: "+operand1.getName()+operation+operand2.getName());
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }  
    }
    
    //OPERATORE =>
    if(operation=="=>")
    {    //Definisco il comportamento nel caso di due interi( potevo mettere pure symbol al posto di operand nell'if sottostante, in ogni caso hanno lo stesso tipo)
         if(operand1.getType().toString().compareTo("int")==0)
         {
            if((Integer.parseInt(operand1.getValue().toString())>Integer.parseInt(operand2.getValue().toString())) || (Integer.parseInt(operand1.getValue().toString())==Integer.parseInt(operand2.getValue().toString())))
            {
              //Come valore salvo null, non avrebbe senzo salvare uno dei due valori
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi interi e sono: "+operand1.getName()+operation+operand2.getName());
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }
         
         if(operand1.getType().toString().compareTo("float")==0)
          {
             if((Float.parseFloat(operand1.getValue().toString())>Float.parseFloat(operand2.getValue().toString())) || (Float.parseFloat(operand1.getValue().toString())==Float.parseFloat(operand2.getValue().toString())))
            {
              //Come valore salvo null, non avrebbe senzo salvare uno dei due valori
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,true,operand1.getType().toString());
              System.out.println("I due operandi sono entrambi float e sono: "+operand1.getName()+operation+operand2.getName());
            }
            else
            {
              point=new ResultValue(operand1.getName()+operation+operand2.getName(),null,false,operand1.getType().toString());
            }
         }  
    
    }
  }
  
 //OPERATORE &&  -> Fuori da Flag con operand.value che puo assumere anche il valore null
    if(operation=="&&" && operand1!=null && operand1.getType()!=null && operand2!=null && operand2.getType()!=null)
    {
      if(operand1.getControlValue()==true && operand2.getControlValue()==true)
      {
        point=new ResultValue(operand1.getName()+operation+operand2.getName(),true,true,"boolean");
        System.out.println(" AND  ok");
      }
      else
      {
        point=new ResultValue(operand1.getName()+operation+operand2.getName(),false,false,"boolean");
      }
    }
     
    //OPERATORE ||  -> Fuori da Flag con operand.value che puo assumere anche il valore null
    if(operation=="||" && operand1!=null && operand1.getType()!=null && operand2!=null && operand2.getType()!=null)
    {
      if((operand1.getControlValue()==true && operand2.getControlValue()==true) 
        || (operand1.getControlValue()==true && operand2.getControlValue()==false) 
        || (operand1.getControlValue()==false && operand2.getControlValue()==true) )
      {
        point=new ResultValue(operand1.getName()+operation+operand2.getName(),true,true,"boolean");
        System.out.println("OR ok");
      }
      else
      {
        point=new ResultValue(operand1.getName()+operation+operand2.getName(),false,false,"boolean");
      }
    }     
   if(point!=null)
       point.print();
  else System.out.println("###### Point is null!!");
   
  
  return point;
}
     
  
public static String ExecutionFunction(String input1,String input2,String input3)
{
  String output="";
  //Ipotizzo variabile semplice o array intero

  if(input2.indexOf("[")==-1)
  {
    System.out.println("Function-Execution: - Html: "+input1+" Variable: "+input2+ " Style: "+input3);
    if(!table.containsKey(input2))
       System.out.println("\t\t\tFunction-Execution Element : " +input2+" not exist  ");
    else
    {
        //Recupero il Symb associato alla variabile in ingresso
        Symb symbol=(Symb)table.get(input2);
        //Ipotizzo che si tratti di una variabile semplice e non di un array
        if(symbol.displayIsArray()==false)
        {
          if(symbol.displayValue()==null)
            System.out.println("\t\t\tFunction-Execution Element : " +input2+" not inizializzate  ");
          else
          {
            if(input3.compareTo("p")==0)
            {
                output="<p>"+input1+" "+symbol.displayValue().toString()+"</p>";
            }
            if(input3.compareTo("em")==0)
            {
                output="<p><em>"+input1+" "+symbol.displayValue().toString()+"</em></p>";
            }
            if(input3.compareTo("bold")==0)
            {
                output="<p><bold>"+input1+" "+symbol.displayValue().toString()+" </bold></p>";
            }
			/* ADDED*/	
            if(input3.compareTo("span")==0){
                output="<span>"+input1+" "+symbol.displayValue().toString()+" </span>";
            }
            if(input3.compareTo("span2")==0){
                output="<span>"+input1+" </span>";
            }
			if(input3.compareTo("p2")==0){
                output="<p>"+input1+" </p>";
            }
			if(input3.compareTo("strong")==0){
                output="<strong>"+input1+" "+symbol.displayValue().toString()+" </strong>";
            }
			if(input3.compareTo("strong2")==0){
                output="<strong>"+input1+" </strong>";
            }
			if(input3.compareTo("br")==0){
                output="<br />";
            }
			/* /ADDED*/
           if(input3.compareTo("p")!=0 && input3.compareTo("em")!=0 && input3.compareTo("bold")!=0 && input3.compareTo("span")!=0
			&& 	input3.compareTo("p2")!=0 &&input3.compareTo("span2")!=0 && input3.compareTo("strong")!=0 && input3.compareTo("strong2")!=0 && input3.compareTo("br")!=0)
            {
                System.out.println("##Error in Input3 ");
            }
          
          }
        }
       //Si tratta di un array
       else
       {
         System.out.println("Variable: "+input2+" is an array");
         
         if(input3.compareTo("oli")==0)
            {
                output="<ol>";
                int i=0;
                while(i<symbol.MultipleValue.size())
                {
                  Object value= symbol.MultipleValue.get(i);
                  output+="\n"+"<li>"+input1+" "+value.toString()+"</li>";
                  i++;
                }
                 output+="\n"+"</ol>";
            }
           else if(input3.compareTo("uoli")==0)
            {
                output="<ul>";
                int i=0;
                while(i<symbol.MultipleValue.size())
                {
                  Object value= symbol.MultipleValue.get(i);
                  output+="\n"+"<li>"+input1+" "+value.toString()+"</li>";
                  i++;
                }
                 output+="\n"+"</ul>";
              
            }
            else
            {
                System.out.println("##Error in Input3 ");
            }
            
       }
    }
  } //end if if(input2.indexOf("[")==-1)
  else
  {
    System.out.println("Input2 is an Array Value ");
    int start=0;
    int end=0;
    start=input2.indexOf("[");
    end=input2.indexOf("]");
    String part=input2.substring(0,start);
    int position= Integer.parseInt(input2.substring(start+1,end));
    System.out.println("Function-Execution: - Html:**  "+input1+" **"+" Variable: "+part+" Position: "+position+ " Style: "+input3);
    if(!table.containsKey(part))
       System.out.println("\t\t\tFunction-Execution Element : " +part+" not exist  ");
    else
      {
        Symb symbol=(Symb)table.get(part);
        if(symbol.displayIsArray()==false)
        {
          System.out.println("Error, variable: "+part+" is not an array!");
        }
        else if(symbol.MultipleValue.size()==0)
          System.out.println("Array "+part+" not inizializzated");
        else
        {
          Object value= symbol.MultipleValue.get(position);
          if(value==null)
              System.out.println("Error in array position!");
          else
            {
              if(input3.compareTo("p")==0)
              {
                  output="<p>"+input1+" "+value.toString()+"</p>";
              }
              if(input3.compareTo("em")==0)
              {
                  output="<p><em>"+input1+" "+value.toString()+"</em></p>";
              }
              if(input3.compareTo("bold")==0)
              {
                  output="<p><bold>"+input1+" "+value.toString()+"</bold></p>";
              }
              
              if(input3.compareTo("p")!=0 && input3.compareTo("em")!=0 && input3.compareTo("bold")!=0)
              {
                  System.out.println("##Error in Input3 ");
              }
            }
        }
        
      }

  }
  System.out.println("OUTPUT Funzione: "+output);
  return output;
  
  
}
        

  
  
  
  
}