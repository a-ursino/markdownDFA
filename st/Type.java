package st;
import java.util.*;

public class Type {
	public static final String CHARACTER = "char";
	public static final String INTEGER = "int";
	public static final String FLOATING = "float";
	public static final String BOOL = "boolean";
	public static final String ARRAY = "array";
        public static final String STRING = "String";
	
	static HashMap _types = new HashMap();

	String _tag;
	int _width = 0;

        //INSERISCO nell'hashmap l'accoppiata key=TIPO, value =OGGETTO type corrispondente
	public static void initTypes(){
		_types.put(INTEGER+"", new Type(INTEGER, 4));
		_types.put(FLOATING+"", new Type(FLOATING, 4));
		_types.put(CHARACTER+"", new Type(CHARACTER, 1));
		_types.put(BOOL+"", new Type(BOOL, 1));
                _types.put(STRING+"", new Type(STRING, 4));
                _types.put(ARRAY+"", new Type(ARRAY, 4));
                
        }

        //Costruttore Type
	protected Type(String tag, int width) {
		_tag = tag;
		_width = width;
	}

	public String display(){
		String str = new String(""+_tag);
		return str;
	}
	
        
        /* INTEGER HELPER - Accedo all'hash map e dalla chiave recupero l'oggetto TYPE corrispondente*/
	public static Type integer() {
		Type t=(Type)_types.get(""+INTEGER);
		return t;
	}

        
        /* BOOL HELPER*/
	public static Type bool() {
		return (Type)_types.get(""+BOOL);
	}
        
        //NUOVO
        public static Type string() {
		Type t=(Type)_types.get(""+STRING);
		return t;
	}
        //FINE NUOVO
        
	/* FLOATING HELPER*/
	public static Type floating() {
		return (Type)_types.get(""+FLOATING);
	}

	
	/* CHARACTER HELPER*/
	public static Type character() {
		return (Type)_types.get(""+CHARACTER);
	}
        
        
        public boolean isFloating() {
		if (_tag == FLOATING) return true;
		else return false;
	}

	public boolean isCharacter() {
		if (_tag == CHARACTER) return true;
		else return false;
	}
	

        public boolean isInteger() {
		if (_tag == INTEGER) return true;
		else return false;
	}
	
	public boolean isBool() {
		if (_tag == BOOL) return true;
		else return false;
	}
        public boolean isArray() {
		if (_tag == ARRAY) return true;
		else return false;
	}
        public boolean isString() {
		if (_tag == STRING) return true;
		else return false;
	}
        
        
}
