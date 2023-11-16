package darkSoulsLevelScaling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Main {

	public static void main(String[] args) {
		createInterface();
		}

	// - INTERFACE WORK
	
	public static void createInterface() {
		
		// - Basic JFrame Set up
		   JFrame frame = new JFrame("Number of Souls to Next Level");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




	        
	        
	        // - Setting up  images
	        ImageIcon sadBernie = new ImageIcon("bernard.png");
	        ImageIcon ds1 = new ImageIcon("darksouls1.png"); // swap
	        ImageIcon ds2 = new ImageIcon("ds2.png");
	        ImageIcon ds3 = new ImageIcon("ds3.jpg");
	        ImageIcon demonSouls = new ImageIcon("demonsoulspng.png");
	        ImageIcon BloodBourne = new ImageIcon("bb.png"); // - swap
	        ImageIcon Sekiro = new ImageIcon("skiro.png");
	        ImageIcon er = new ImageIcon("eldenring.png");
	        
	        
	        
	        
	        // Setting up Objects
	        String[] choices = {"Demon Souls" , "Dark Souls 1" , "Dark Souls 2", "Dark Souls 3", "BloodBourne", "Sekiro", "Elden Ring"};       
	        final JComboBox<String> dropDown = new JComboBox<>(choices);       
	        JTextField numEnt = new JTextField(10);
	        JButton submitButton = new JButton("Submit" );
	        JLabel label = new JLabel("Enter a number: "); 
	        JLabel info = new JLabel("<html>Number of Souls/Runes/Experience: </html> ");
	        JLabel image = new JLabel(standardizeImageIconSize(ds1, 300,300));
	        

	        /// Panel Set ups
	        JPanel topPanel = new JPanel();
	        JPanel bottomPanel= new JPanel();
	        		bottomPanel.setLayout(new GridLayout(0,1));       
	        JPanel westPanel = new JPanel();
	        JPanel eastPanel = new JPanel();
	        JPanel centerPanel = new JPanel();
	        
	        
	        frame.add(topPanel, BorderLayout.NORTH);
	        frame.add(westPanel, BorderLayout.WEST);
	        frame.add(eastPanel, BorderLayout.EAST);
	        frame.add(bottomPanel, BorderLayout.SOUTH);
	        frame.add(centerPanel, BorderLayout.CENTER);
	        

	        
	        
	        centerPanel.add(image);
	        bottomPanel.add(dropDown);
	        topPanel.add(label);
	        topPanel.add(numEnt);
	        topPanel.add(submitButton);
	        bottomPanel.add(info);
	        
	        // - Color set up
	        
	        centerPanel.setBackground(Color.black);
	        bottomPanel.setBackground(Color.black);
	        topPanel.setBackground(Color.black);
	        westPanel.setBackground(Color.black);
	        eastPanel.setBackground(Color.black);
	        
	        dropDown.setBackground(Color.black);
	        dropDown.setForeground(Color.white);
	        
	        label.setForeground(Color.white);
	        info.setForeground(Color.white);
	        
	        
	        
	        
	        

	        frame.pack();
	        frame.setVisible(true);
	        
	        submitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String val = numEnt.getText();
	            	Integer optionPicked = dropDown.getSelectedIndex();

	                if (isNumeric(val)) {
	                	 if (optionPicked == 0 || optionPicked == 1 || optionPicked ==  4) {
	 	                	Integer Lvl = levelScalingGeneric(Integer.parseInt(val));
	 	                	info.setText("Number of Souls needed to Level up to " + val + " is: " + Lvl);
	 	                }
	 					else if (optionPicked == 2) {
	 						Integer Lvl = levelScalingds2(Integer.parseInt(val));  	
	 						info.setText("Number of Souls needed to Level up to " + val + " is: " + Lvl);
	 					 }
	 					else if (optionPicked == 3) {
	 						Integer Lvl =levelScalingds3(Integer.parseInt(val));  	
	 						info.setText("Number of Souls needed to Level up to " + val + " is: " + Lvl);
	 					}
	 					else if (optionPicked == 5) {
	 						Integer Lvl =experienceScalingSekiro(Integer.parseInt(val));  	
	 						info.setText("Amount of exp needed to Level up to " + val + " is: " + Lvl);
	 					}
	 					else if (optionPicked == 6) {
	 						Integer Lvl =levelScalingElden(Integer.parseInt(val));
	 						info.setText("Number of Runes needed to Level up to " + val + " is: " + Lvl);
	 					}
	                }else {
	                	numEnt.setText("2");
	                	optionPicked= 7;
	                	frame.pack();
	                }
	                ImageIcon[] icons = {demonSouls,ds1, ds2, ds3, BloodBourne, Sekiro, er, sadBernie};
	                ImageIcon icon = (icons[optionPicked]);
	                image.setIcon(standardizeImageIconSize(icon, 300,300));
	                frame.pack();
	                

	                }

	        });
	        
	}
    private static ImageIcon standardizeImageIconSize(ImageIcon originalIcon, int targetWidth, int targetHeight) {
        // Get the original image from the ImageIcon
        Image originalImage = originalIcon.getImage();

        // Create a new ImageIcon with the desired dimensions
        Image standardizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(standardizedImage);
    }
    

	

	
	
	
	// -GAME CALCULATIONS
	
	//- Demon Souls/Ds1/BB - Standardizing. The Level given is the level you need to get to this VALUE, not the next one up
	public static Integer levelScalingGeneric(Integer curLevel){
		Integer  y = 0;
		if (curLevel <8) {
		    y = ((curLevel-2)* 17) + 673;
			System.out.println(y);
		}
		else if ((curLevel >= 8) &&(curLevel < 12)) {
			y = ((curLevel-8)* 18) + 775;
		System.out.println(y);
		}
		else {
			y = mainScalingEquation(curLevel);
			System.out.println(y);
		}
		return y;
	
	}

	
	// - Ds2 - DISGUSTIN
	
	public static Integer levelScalingds2(Integer curLevel) {
		ArrayList<Integer> levelupVals = new ArrayList<Integer>();
		try {
			File newFile = new File("ds2levels.txt");
			Scanner scanner = new Scanner(newFile);

			while (scanner.hasNextLine()) {
				// - Going through Lines
				String curLine = scanner.nextLine();
				String[] split = curLine.split(":");
				String cur = (split[1]);
				
				Integer curNum =Integer.parseInt(cur);
				levelupVals.add(curNum);
								
				}
			scanner.close();
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return (levelupVals.get(curLevel-1));
		
	}
	
	// - Ds3
	public static Integer levelScalingds3(Integer curLevel) {
		Integer  y = 0;
		if (curLevel < 3) {
			return 673;
		}
		else if (curLevel <8) {
		    y = ((curLevel-2)* 17) + 672;
		}
		else if ((curLevel >= 8) &&(curLevel < 12)) {
			y = ((curLevel-8)* 18) + 775;
		}
		else {
			y = mainScalingEquation(curLevel);
		}
		return y;
	}
	

	
	//-Sekiro
	public static Integer experienceScalingSekiro(Integer curLevel) {
		if (curLevel <= 24) {
			Double calc1 = Math.pow(curLevel+69, 2);
			Double test = .1*calc1 + 10;
			return ((int)Math.floor(test));
		}
		else {
			// 0.1*(x+69)^2+10+0.02*(x+69)^2*(x+69-94)
			Double calc1 = Math.pow((curLevel+69), 2);
			Double calc2 = Math.pow((curLevel + 69), 2);
			Double calc3 =  (double) (curLevel+69-94);
			
			Double test = (.1*calc1 + 10)+ (.02*calc2)* calc3;
			return ((int)Math.floor(test));
		}
	}
	
	
	
	// - Elden Ring
	public static Integer levelScalingElden(Integer curLevel) {
		// x = ((Lvl+81)-92)*0.02
		// ((x+0.1)*((Lvl+81)^2))+1
		double x =  (((curLevel + 81)-92)*.02);
		if (x < 0) {
			x =0;
		}
		
		Double  z = Math.pow((curLevel + 81),2);
		Double math =  (x +.1) *z +1;
		Integer math2 =  (int) Math.floor(math);
		return (math2);
	}
	
	
	// - Validation/Calculations 
	
	public static Integer mainScalingEquation(Integer curLevel) {
		Double calc1 = .02*Math.pow(curLevel, 3);
		Double calc2 = 3.06*Math.pow(curLevel, 2);
		Double calc3 =  (105.6*curLevel);
		
		Integer  y = (int) Math.round(calc1+ calc2 + calc3- 895);
		return y;
	}
	
	public static Boolean isNumeric(String strNum) {
		 if (strNum == null) {
		        return false;
		    }
		    try {
		        Integer  d = Integer.parseInt(strNum);
		    } catch (NumberFormatException nfe) {
		        return false;
		    }
		    return true;
	}

}
