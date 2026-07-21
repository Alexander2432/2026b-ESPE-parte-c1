Jardel: Confecciona las pruebas usando JUnit

Aquí tienes la batería de pruebas de unidad utilizando JUnit 5, tomando como base la estructura del paquete y las importaciones proporcionadas.  
JAVA

Estas pruebas cubren todas las validaciones de excepciones y comportamientos lógicos detallados en la especificación de la clase File y programados en el código. Puedes guardar este código en un archivo llamado FileTest.java.
package es.upm.grise.file;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.upm.grise.file.exceptions.InvalidContentException;
import es.upm.grise.file.exceptions.WrongEncodingException;
import es.upm.grise.file.exceptions.WrongFileTypeException;

public class FileTest {

    private Location dummyLocation;

    @BeforeEach
    public void setUp() {
        dummyLocation = null; 
    }

    @Test
    public void testConstructor_InitializesEmptyContent() {
        File file = new File(FileType.PROPERTY, dummyLocation);
        assertNotNull(file.getContent(), "El contenido no debe ser null");
        assertTrue(file.getContent().isEmpty(), "El contenido debe estar vacío al inicializar");
        assertEquals(FileType.PROPERTY, file.getType());
        assertEquals(dummyLocation, file.getLocation());
    }

    @Test
    public void testAddProperty_ValidContent() throws Exception {
        File file = new File(FileType.PROPERTY, dummyLocation);
        char[] property = "CLAVE=VALOR".toCharArray();
        file.addProperty(property);
        assertEquals(11, file.getContent().size());
        assertEquals('C', file.getContent().get(0));
    }

    @Test
    public void testAddProperty_NullContent_ThrowsInvalidContentException() {
        File file = new File(FileType.PROPERTY, dummyLocation);
        assertThrows(InvalidContentException.class, () -> file.addProperty(null));
    }

    @Test
    public void testAddProperty_WrongFileType_ThrowsWrongFileTypeException() {
        File file = new File(FileType.IMAGE, dummyLocation);
        char[] property = "CLAVE=VALOR".toCharArray();
        assertThrows(WrongFileTypeException.class, () -> file.addProperty(property));
    }

    @Test
    public void testAddProperty_NoEqualsSign_ThrowsInvalidContentException() {
        File file = new File(FileType.PROPERTY, dummyLocation);
        char[] property = "CLAVEVALOR".toCharArray(); 
        assertThrows(InvalidContentException.class, () -> file.addProperty(property));
    }

    @Test
    public void testAddImageBytes_ValidContent() throws Exception {
        File file = new File(FileType.IMAGE, dummyLocation);
        char[] bytes = {(char) 100, (char) 200}; 
        file.addImageBytes(bytes);
        assertEquals(2, file.getContent().size());
        assertEquals((char) 100, file.getContent().get(0));
    }

    @Test
    public void testAddImageBytes_NullContent_ThrowsInvalidContentException() {
        File file = new File(FileType.IMAGE, dummyLocation);
        assertThrows(InvalidContentException.class, () -> file.addImageBytes(null));
    }

    @Test
    public void testAddImageBytes_WrongFileType_ThrowsWrongFileTypeException() {
        File file = new File(FileType.PROPERTY, dummyLocation);
        char[] bytes = {(char) 100, (char) 200};
        assertThrows(WrongFileTypeException.class, () -> file.addImageBytes(bytes));
    }

    @Test
    public void testAddImageBytes_WrongEncoding_ThrowsWrongEncodingException() {
        File file = new File(FileType.IMAGE, dummyLocation);
        char[] bytes = {(char) 256}; 
        assertThrows(WrongEncodingException.class, () -> file.addImageBytes(bytes));
    }

    @Test
    public void testRemoveContent_LessThanTotalSize() throws Exception {
        File file = new File(FileType.PROPERTY, dummyLocation);
        file.addProperty("K=V".toCharArray()); 
        file.removeContent(1); 
        assertEquals(2, file.getContent().size());
        assertEquals('=', file.getContent().get(1)); 
    }

    @Test
    public void testRemoveContent_GreaterThanTotalSize_ClearsContent() throws Exception {
        File file = new File(FileType.PROPERTY, dummyLocation);
        file.addProperty("K=V".toCharArray()); 
        file.removeContent(10); 
        assertTrue(file.getContent().isEmpty(), "El contenido debe estar vacío y no lanzar excepciones");
    }
}
