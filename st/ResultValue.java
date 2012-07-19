package st;
public class ResultValue  
  {
    String name;
    Object value;
    boolean controlValue;
    Object type;
    
    //Costruttore per variabile di tipo letterale, e valore assunto intero
    ResultValue(Object name, int value,boolean controlValue,String type)
    {
        this.name=(String)name;
        this.value=value;
        this.controlValue=controlValue;
        this.type=type;
    }
    
    //Costruttore per variabile di tipo letterale, e valore assunto float
    ResultValue(Object name, float value,boolean controlValue,String type)
    {
        this.name=(String)name;
        this.value=value;
        this.controlValue=controlValue;
        this.type=type;
    }
    
    //Costruttore per variabile di tipo letterale, e valore assunto boolean
     ResultValue(Object name, boolean value,boolean controlValue,String type)
    {
        this.name=(String)name;
        this.value=value;
        this.controlValue=controlValue;
        this.type=type;
    }
    
     //Costruttore per variabile di tipo letterale, e valore assunto String
     ResultValue(Object name, String value,boolean controlValue,String type)
    {
        this.name=(String)name;
        this.value=value;
        this.controlValue=controlValue;
        this.type=type;
    }
    
    
     //Costruttore per variabile di tipo letterale, e valore assunto char
     ResultValue(Object name,char value,boolean controlValue,String type)
    {
        this.name=(String)name;
        this.value=value;
        this.controlValue=controlValue;
        this.type=type;
    }
    
    
    //Costruttore di un ResultValue di una variabile dichiarata ma non inizializzata (value=null)
    ResultValue(Object name, Object value,boolean controlValue,String type)
    {
        this.name=(String)name;
        this.value=value;
        this.controlValue=controlValue;
        this.type=type;
    }
     
    //Costruttore di un ResultValue di una variabile non accettata nei costrutti. es.array
    ResultValue(Object name, Object value,boolean controlValue,Object type)
    {
        this.name=(String)name;
        this.value=value;
        this.controlValue=controlValue;
        this.type=type;
    }
    
    public void print()
    {
        String temp= "Object: nome: "+name+ " value: "+value+" controlValue: "+controlValue+" Type: "+type;
      System.out.println(""+temp);
    }
    
    public boolean getControlValue()
    {
        return controlValue;
    }
    
    public Object getValue()
    {
        return value;
    }
    
    public Object getType()
    {
        return type;
    }
    
    public String getName()
    {
        return name;
    }
    public void setControlValue()
    {
        controlValue=true;
    }
    public void setName(String str)
    {
        this.name=str+this.name;
    }
    public void setValue(int value)
    {
     this.value=value;  
    }
  }