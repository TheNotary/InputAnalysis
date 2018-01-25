package thenotary;

// This library was installed as a dependency via the pom.xml file
import org.apache.commons.lang3.StringUtils;

// Keyboard/ mouse hooking library
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyListener;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import thenotary.Weapon;

/**
 * Hello world!
 *
 */
public class App
{
    private static boolean run = true;

    static GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(false); // use false here to switch to hook instead of raw input
    static Map<Integer, WeaponGroup> loadoutMappings;

    public static void main( String[] args )
    {
		Weapon wep1 = new Weapon();
		WeaponGroup weaponGroup1 = new WeaponGroup();
		weaponGroup1.add(wep1);

		loadoutMappings = new HashMap<Integer, WeaponGroup>();
		loadoutMappings.put(49, weaponGroup1);


        System.out.println( "Hello World!" );
        listenToInputs();
    }


    public static void listenToInputs()
    {
      System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
      // printInputDevices();

      keyboardHook.addKeyListener(new GlobalKeyListener() {

          @Override public void keyPressed(GlobalKeyEvent event) {
        	  WeaponGroup weaponGroup = loadoutMappings.get( event.getVirtualKeyCode() );

        	  if ( weaponGroup != null && weaponGroup.getTransitionState() != event.getTransitionState() ) {
        		  System.out.println(event);
        		  weaponGroup.setTransitionState(event.getTransitionState());
        	  }


              if(event.getVirtualKeyCode()==GlobalKeyEvent.VK_ESCAPE)
                  run = false;
          }

          @Override public void keyReleased(GlobalKeyEvent event) {
        	  WeaponGroup weaponGroup = loadoutMappings.get( event.getVirtualKeyCode() );

        	  if (weaponGroup != null && weaponGroup.getTransitionState() != event.getTransitionState()) {
        		  System.out.println(event);
        		  weaponGroup.setTransitionState(event.getTransitionState());
        	  }
          }
      });


      try {
          while(run) Thread.sleep(128);
      }
      catch(InterruptedException e) { /* nothing to do here */ }
      finally { keyboardHook.shutdownHook(); }
    }

    public static void printInputDevices()
    {
        for(Entry<Long,String> keyboard:GlobalKeyboardHook.listKeyboards().entrySet())
        {
            System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
        }
    }
}
