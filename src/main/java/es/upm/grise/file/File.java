package es.upm.grise.file;

import java.util.ArrayList;

import es.upm.grise.file.exceptions.InvalidContentException;
import es.upm.grise.file.exceptions.WrongEncodingException;
import es.upm.grise.file.exceptions.WrongFileTypeException;

public class File {

    private ArrayList<Character> content;
    private FileType type;
    private Location location;

    /*
     * Method to code/test
     */
    public File(FileType type, Location location) {
    	
    }

    /*
     * Method to code/test
     */
    public void addProperty(char[] content) throws InvalidContentException, WrongFileTypeException {
    	

    }
    
    /*
     * Method to code/test
     */
    public void addImageBytes(char[] content) throws InvalidContentException, WrongFileTypeException, WrongEncodingException {
    	

    }			
    
    /*
     * Method to code/test
     */
    public void removeContent(int numberChars) {
    	

    }
    
    /*
     * getters
     */
    
    public ArrayList<Character> getContent() {
        return content;
    }

    public FileType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }
    
}
