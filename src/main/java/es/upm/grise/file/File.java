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
        this.content = new ArrayList<>();
        this.type = type;
        this.location = location;
    }

    /*
     * Method to code/test
     */
    public void addProperty(char[] content) throws InvalidContentException, WrongFileTypeException {
    	if (content == null) {
            throw new InvalidContentException();
        }
        if (this.type == FileType.IMAGE) {
            throw new WrongFileTypeException();
        }
        
        boolean containsEquals = false;
        for (char c : content) {
            if (c == '=') {
                containsEquals = true;
                break;
            }
        }
        
        if (!containsEquals) {
            throw new InvalidContentException();
        }

        for (char c : content) {
            this.content.add(c);
        }

    }
    
    /*
     * Method to code/test
     */
    public void addImageBytes(char[] content) throws InvalidContentException, WrongFileTypeException, WrongEncodingException {
    	if (content == null) {
            throw new InvalidContentException();
        }
        if (this.type == FileType.PROPERTY) {
            throw new WrongFileTypeException();
        }
        
        for (char c : content) {
            if (c > 255) {
                throw new WrongEncodingException();
            }
        }

        for (char c : content) {
            this.content.add(c);
        }

    }			
    
    /*
     * Method to code/test
     */
    public void removeContent(int numberChars) {
    	if (numberChars >= this.content.size()) {
            this.content.clear();
        } else {
            for (int i = 0; i < numberChars; i++) {
                this.content.remove(this.content.size() - 1);
            }
        }

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
