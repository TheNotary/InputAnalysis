package my_app_group;

// This library was installed as a dependency via the pom.xml file
import org.apache.commons.lang3.StringUtils;


// Keyboard/ mouse hooking library
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import java.util.Map.Entry;

/**
 * Hello world!
 *
 */
public class App
{
    private static boolean run = true;

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

        listenToInputs();
    }


    public static void listenToInputs()
    {
      // might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails
      GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true); // use false here to switch to hook instead of raw input

      System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
      for(Entry<Long,String> keyboard:GlobalKeyboardHook.listKeyboards().entrySet())
        System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());

      keyboardHook.addKeyListener(new GlobalKeyAdapter() {
        @Override public void keyPressed(GlobalKeyEvent event) {
          System.out.println(event);
          if(event.getVirtualKeyCode()==GlobalKeyEvent.VK_ESCAPE)
            run = false;
        }
        @Override public void keyReleased(GlobalKeyEvent event) {
          System.out.println(event); }
      });

      try {
        while(run) Thread.sleep(128);
      } catch(InterruptedException e) { /* nothing to do here */ }
      finally { keyboardHook.shutdownHook(); }

      System.out.println( "got here" );
    }
}
