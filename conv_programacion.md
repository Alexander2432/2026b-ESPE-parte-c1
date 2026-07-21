Jardel: Explicame paso a paso que debo realizar

Hola Jardel, aquí tienes el desglose paso a paso de lo que debes realizar para completar esta asignación de la ESPE.

Fase 1: Configuración del Repositorio Git
Realiza un fork del repositorio ubicado en [https://github.com/GRISE-UPM/2026b-ESPE-parte-c1](https://github.com/GRISE-UPM/2026b-ESPE-parte-c1).  
PDF

Clona el repositorio que acabas de forkear en tu entorno local.  
PDF

Crea o edita un archivo README.md y añade tu nombre y apellidos (Jardel Alexander Maza Flores).  
PDF

Realiza un commit en Git con un mensaje que sea significativo.  
PDF

Fase 2: Implementación de la Clase File
Utiliza la interfaz web de un LLM (como esta) para generar la implementación de la clase File basándote en la especificación.  
PDF

Asegúrate de que, al inicializar la clase, el content esté vacío pero no sea null.  
PDF

Asigna los valores de type y location directamente desde los parámetros del constructor.  
PDF

Implementa el método addProperty(char[] content) para agregar contenido al existente, lanzando InvalidContentException si es nulo o no tiene formato clave=valor, y WrongFileTypeException si el tipo de archivo es IMAGE.  
PDF

Implementa el método addImageBytes(char[] content) para agregar contenido, lanzando InvalidContentException si es nulo, WrongFileTypeException si es PROPERTY, y WrongEncodingException si algún carácter supera el valor de 255.  
PDF

Implementa el método removeContent(int numberChars) para eliminar la cantidad especificada de caracteres finales del contenido.  
PDF

Vacía el contenido por completo sin lanzar ninguna excepción si el número de caracteres a eliminar es mayor que la longitud actual.  
PDF

Guarda una copia de la conversación que utilizaste para generar este código en un archivo llamado conv_programacion.<ext> (por ejemplo, .txt o .md) en el directorio del proyecto.  
PDF

Añade este archivo al control de versiones de Git y realiza un commit con un mensaje significativo.  
PDF

Fase 3: Creación de Pruebas Unitarias
Utiliza la interfaz web del LLM para generar las pruebas de unidad correspondientes utilizando jUnit.  
PDF

Guarda la conversación de esta etapa en un archivo llamado conv_prueba.<ext> en el directorio del proyecto.  
PDF

Añade este archivo al control de versiones de Git y realiza un nuevo commit con un mensaje significativo.  
PDF

Fase 4: Entrega
Realiza un push con todos tus cambios hacia tu repositorio forkeado.  
PDF

Dirígete a GitHub y realiza un pull request hacia el repositorio original.  
PDF

Escribe tu nombre y apellidos explícitamente en el comentario de ese pull request.  

Jardel: Implementa la clase File.java

Aquí tienes la implementación completa de la clase File.java basándose en el código proporcionado y cumpliendo con todos los requisitos de la especificación.
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
