package my_app_group;

// This library was installed as a dependency via the pom.xml file
import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        String myString = "";

        if ( StringUtils.isEmpty(myString) ) {
          System.out.println( "It was empty" );
        }
        else {
          System.out.println( "It was FULL" );
        }

        System.out.println( "Hello World!" );
    }
}
