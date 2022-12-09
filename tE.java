/*----README------
Name - Yagay Khatri
Roll No - 21101106065

The text editor is divided into 3 major panels and 1 menu bar.

Menubar contains File menu, Edit menu and Review menu. 
File menu contains 4 menu items - new, open, save and exit. 
Edit menu contains 3 menu items - cut, copy and paste.
Review menu contains 1 menu item - count. 
More menu contains about us ;)

Panel1_TextEditor contains subpanels which contain the JTextArea and all the elements.
Panel2_SketchPad contains the sketch area.
Panel3 is for displaying the word and character count.
------README----*/


import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;



class TextEditor extends JFrame implements ActionListener {
	
	int pos = 0;
	
	//-----menuBar-----
	JMenuBar menuBar;
	JMenu menuFile;
	JMenuItem newItem;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	JMenu menuEdit;
	JMenuItem cut;
	JMenuItem copy;
	JMenuItem paste;
	JMenu menuReview;
	JMenuItem count;
	JMenu about;
	JMenuItem aboutUs;

	
	//---PANEL1_TEXTEDITOR---
	JPanel panel_te;
	JTextArea textArea;
	JScrollPane scrollPane;
	//---sub_panel_1---
	JPanel sub_panel_1;
	//---sub_panel_1_1---
	JPanel sub_panel_1_1;
	JToggleButton bold;
	JToggleButton italic;
	JToggleButton bolic;
	//---sub_panel_1_2---
	JPanel sub_panel_1_2;
	JButton leftAlign;
	JButton centerAlign;
	JButton rightAlign;
	JButton justifyAlign;
	//---sub_panel_1_3---
	JPanel sub_panel_1_3;
	JComboBox <Integer> fontSize;
	JComboBox fontBox;
	//---sub_panel_2---
	JPanel sub_panel_2;
	JLabel findLabel;
	JTextField find;
	JLabel replaceLabel;
	JTextField replace;
	//---sub_panel_3---
	JPanel sub_panel_3;
	JButton findAllButton;
	JButton findNextButton;
	JButton replaceButton;
	JButton replaceAllButton;
	
	
	//---PANEL2_SKETCHPAD---
	JPanel panel_sp;
	JLabel spTitleLabel;
	JTextArea sketchHere;
	//---sub_panel_sp_1---
	JPanel sub_panel_sp_1;
	JButton rectangle;
	JButton oval;
	JButton line;
	JButton triangle;
	JButton pentagon;
	JButton clear;
	//---sub_panel_sp_2---
	JPanel sub_panel_sp_2;
	
	//---PANEL3_COUNT---
	JPanel panel_count;
	JLabel wordLabel;
	JLabel charLabel;
	JTextField wordCount;
	JTextField charCount;
	
	
	//---FRAME---
	JFrame frame;
	
	
	TextEditor() {
		
		//-----menuBar-----
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		newItem = new JMenuItem("New");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");
		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
		menuFile.add(newItem);
		menuFile.add(openItem);
		menuFile.add(saveItem);
		menuFile.add(exitItem);
		menuBar.add(menuFile);
		
		menuEdit = new JMenu("Edit");
		cut = new JMenuItem("Cut");
		cut.addActionListener(this);
		copy = new JMenuItem("Copy");
		copy.addActionListener(this);
		paste = new JMenuItem("Paste");
		paste.addActionListener(this);
		menuEdit.add(cut);
		menuEdit.add(copy);
		menuEdit.add(paste);
		menuBar.add(menuEdit);
		
		menuReview = new JMenu("Review");
		count = new JMenuItem("Count");
		count.addActionListener(this);
		menuReview.add(count);
		menuBar.add(menuReview);
		
		about = new JMenu("More");
		aboutUs = new JMenuItem("About Us");
		about.add(aboutUs);
		aboutUs.addActionListener(this);
		menuBar.add(about);
	
		//-----menuBar-----
		
		
		//---PANEL1_TEXTEDITOR---
		panel_te = new JPanel();
		Border blackBorder1 = BorderFactory.createLineBorder(Color.black, 2);
		panel_te.setBorder(blackBorder1);
		//panel_te.setLayout(new FlowLayout(5));
		panel_te.setBounds(0, 0, 800, 450);
		panel_te.setBackground(new Color(200,200,200));
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		
		//textArea.setPreferredSize(new Dimension(800, 300));
		//textArea.setLocation(1000, 100);
		//textArea.setBounds(0, 80, 700, 350);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		scrollPane.setPreferredSize(new Dimension(700, 300));
		//scrollPane.setBounds(0, 80, 700, 350);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		sub_panel_1 = new JPanel();
		sub_panel_1.setBackground(new Color(200, 200, 200));
		
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontBox = new JComboBox(fonts);
		fontBox.addActionListener(this);
		fontBox.setSelectedItem("Tahoma");
		//fontBox.setLocation(50, 5);
		
		Integer[] font_size = {9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 35, 40, 45, 50, 60, 70, 80, 90, 100};
		fontSize = new JComboBox <Integer> (font_size);
		fontSize.setPreferredSize(new Dimension(75, 25));
		fontSize.addActionListener(this);
		fontSize.setSelectedItem(16);
		fontSize.setVisible(true);
		
		sub_panel_1_1 = new JPanel();
		sub_panel_1_1.setBackground(new Color(200,200,200));
//		sub_panel_1_1.setPreferredSize(new Dimension(200,50));
//		sub_panel_1_1.setLayout(new FlowLayout());
		bold = new JToggleButton();
		bold.setText("B");
		bold.setPreferredSize(new Dimension(35,25));
		bold.addActionListener(this);
		italic = new JToggleButton();
		italic.setText("I");
		italic.setPreferredSize(new Dimension(35,25));
		italic.addActionListener(this);
		bolic = new JToggleButton();
		bolic.setText("B+I");
		bolic.setPreferredSize(new Dimension(35,25));
		bolic.addActionListener(this);
		
		sub_panel_1_2 = new JPanel();
		sub_panel_1_2.setBackground(new Color(200,200,200));
		leftAlign = new JButton();
		leftAlign.setText("Left");
		leftAlign.setPreferredSize(new Dimension(70,25));
		leftAlign.addActionListener(this);
		centerAlign = new JButton();
		centerAlign.setText("Center");
		centerAlign.setPreferredSize(new Dimension(70,25));
		centerAlign.addActionListener(this);
		rightAlign = new JButton();
		rightAlign.setText("Right");
		rightAlign.setPreferredSize(new Dimension(70,25));
		rightAlign.addActionListener(this);
		justifyAlign = new JButton();
		justifyAlign.setText("Justify");
		justifyAlign.setPreferredSize(new Dimension(70,25));
		justifyAlign.addActionListener(this);
		
		sub_panel_1_3 = new JPanel();
		sub_panel_1_3.setBackground(new Color(200,200,200));
		
		sub_panel_2 = new JPanel();
		sub_panel_2.setBackground(new Color(200, 200, 200));
		
		findLabel = new JLabel();
		findLabel.setText("Find: ");
		find = new JTextField();
		find.setPreferredSize(new Dimension(310, 25));
		//find.setSize(100, 50);
		find.setEditable(true);
		find.setHorizontalAlignment(JTextField.LEFT);
		
		replaceLabel = new JLabel();
		replaceLabel.setText("   Replace: ");
		replace = new JTextField();
		replace.setPreferredSize(new Dimension(310, 25));
		//replace.setSize(100, 50);
		replace.setEditable(true);
		find.setHorizontalAlignment(JTextField.LEFT);
		
		sub_panel_3 = new JPanel();
		sub_panel_3.setBackground(new Color(200, 200, 200));
		findAllButton = new JButton();
		findAllButton.setText("Find All");
		findAllButton.addActionListener(this);
		findNextButton = new JButton();
		findNextButton.setText("Find Next");
		findNextButton.addActionListener(this);
		replaceButton = new JButton();
		replaceButton.setText("Replace");
		replaceButton.addActionListener(this);
		replaceAllButton = new JButton();
		replaceAllButton.setText("Replace All");
		replaceAllButton.addActionListener(this);
		
		
		sub_panel_1_1.add(bold);
		sub_panel_1_1.add(italic);
		sub_panel_1_1.add(bolic);
		sub_panel_1.add(sub_panel_1_1);
		
		sub_panel_1_2.add(leftAlign);
		sub_panel_1_2.add(centerAlign);
		sub_panel_1_2.add(rightAlign);
		sub_panel_1_2.add(justifyAlign);
		sub_panel_1.add(sub_panel_1_2);
		
		sub_panel_1_3.add(fontBox);
		sub_panel_1_3.add(fontSize);
		sub_panel_1.add(sub_panel_1_3);
		
		sub_panel_2.add(findLabel);
		sub_panel_2.add(find);
		sub_panel_2.add(replaceLabel);
		sub_panel_2.add(replace);
		
		sub_panel_3.add(findNextButton);
		sub_panel_3.add(findAllButton);
		sub_panel_3.add(replaceButton);
		sub_panel_3.add(replaceAllButton);
	
		
	//	panel_te.add(textArea);
		panel_te.add(sub_panel_1);
		panel_te.add(sub_panel_2);
		panel_te.add(sub_panel_3);
		panel_te.add(scrollPane);
		//---PANEL1_TEXTEDITOR---
		
		//---PANEL2_SKETCHPAD---
		panel_sp = new JPanel();
		Border blackBorder2 = BorderFactory.createLineBorder(Color.black, 2);
		panel_sp.setBorder(blackBorder2);
		panel_sp.setBounds(800, 0, 500, 450);
		spTitleLabel = new JLabel();
		spTitleLabel.setText("Sketch Pad");
		
		sub_panel_sp_1 = new JPanel();
		rectangle = new JButton();
		rectangle.setText("Rectangle");
		rectangle.setPreferredSize(new Dimension(85,25));
		rectangle.addActionListener(this);
		oval = new JButton();
		oval.setText("Oval");
		oval.setPreferredSize(new Dimension(70,25));
		oval.addActionListener(this);
		line = new JButton();
		line.setText("Line");
		line.setPreferredSize(new Dimension(70,25));
		line.addActionListener(this);
		triangle = new JButton();
		triangle.setText("Triangle");
		triangle.setPreferredSize(new Dimension(80,25));
		triangle.addActionListener(this);
		pentagon = new JButton();
		pentagon.setText("Pentagon");
		pentagon.setPreferredSize(new Dimension(85,25));
		pentagon.addActionListener(this);
		clear = new JButton();
		clear.setText("CLEAR");
		clear.setPreferredSize(new Dimension(70,25));
		clear.addActionListener(this);
		
		sub_panel_sp_2 = new JPanel();
		sub_panel_sp_2.setBounds(300, 200, 350, 350);
		sketchHere = new JTextArea();
		sketchHere.setPreferredSize(new Dimension(300,300));
		sketchHere.setEditable(false);
		sketchHere.setBackground(new Color(200,200,200));
		
		sub_panel_sp_1.add(rectangle);
		sub_panel_sp_1.add(oval);
		sub_panel_sp_1.add(line);
		sub_panel_sp_1.add(triangle);
		sub_panel_sp_1.add(pentagon);
		sub_panel_sp_1.add(clear);
		
		sub_panel_sp_2.add(sketchHere);
		
		panel_sp.add(spTitleLabel);
		panel_sp.add(sub_panel_sp_1);
		panel_sp.add(sub_panel_sp_2);
		
		//---PANEL2_SKETCHPAD---
		
		
		//---PANEL3_COUNT---
		panel_count = new JPanel();
		panel_count.setBorder(new BevelBorder(BevelBorder.LOWERED));
		Border blackBorder3 = BorderFactory.createLineBorder(Color.black, 2);
		panel_count.setBorder(blackBorder3);
		panel_count.setBackground(Color.gray);
		wordLabel = new JLabel();
		wordLabel.setText("Word Count: ");
		wordCount = new JTextField();
		wordCount.setEditable(false);
		wordCount.setPreferredSize(new Dimension(50, 15));
		charLabel = new JLabel();
		charLabel.setText("  Character Count: ");
		charCount = new JTextField();
		charCount.setEditable(false);
		charCount.setPreferredSize(new Dimension(50, 15));
		
		panel_count.add(wordLabel);
		panel_count.add(wordCount);
		panel_count.add(charLabel);
		panel_count.add(charCount);
		panel_count.setBounds(0, 450, 800, 30);
		//---PANEL3_COUNT---
		
		
		//---FRAME---
		frame = new JFrame();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(800, 525);
		this.setTitle("Text Editor");
		//---FRAME---
				
				
		//---FRAME_ADDING_COMPONENTS---
		this.setJMenuBar(menuBar);
		this.add(panel_te);
		//this.add(panel_sp);
		this.add(panel_count);
		//---FRAME_ADDING_COMPONENTS---
	
	}
			
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==italic) {
			if (italic.isSelected()==false) textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
			if (italic.isSelected()==true) textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC));
		}
		
		if (e.getSource()==bold) {
			if (bold.isSelected()==false) textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
			if (bold.isSelected()==true) textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
		}
		
		if (e.getSource()==bolic) {
			if (bolic.isSelected()==false) textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
			if (bolic.isSelected()==true) textArea.setFont(textArea.getFont().deriveFont(Font.BOLD + Font.ITALIC));
		}
		
		if (e.getSource()==leftAlign) {
			textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		}
		
		if (e.getSource()==rightAlign) {
			textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

//		if (e.getSource()==centerAlign) {
//			textArea.setComponentOrientation(ComponentOrientation);
//		}
		
//		if (e.getSource()==justifyAlign) {
//			
//		}
		
		if (e.getSource()==newItem) {
			textArea.setText("");
		}
		
		if (e.getSource()==openItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "HTML files", "docx");
			fileChooser.setFileFilter(filter);
			int response = fileChooser.showOpenDialog(null);
			
			if (response==JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				Scanner fileIn = null;
				try {
					fileIn = new Scanner(file);
					if (file.isFile()) {
						while(fileIn.hasNextLine()) {
							String Line = fileIn.nextLine()+"\n";
							textArea.append(Line);
						}
					}
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				finally {
					fileIn.close();
				}
			}
		}
		
		if (e.getSource()==saveItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			int response = fileChooser.showSaveDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter fileOut = null;
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					fileOut = new PrintWriter(file);
					fileOut.println(textArea.getText());
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				finally {
					fileOut.close();
				}	
				JOptionPane.showMessageDialog(null, "Saved Successfully!");
			}
		}
		
		if (e.getSource()==exitItem) {
			System.exit(0);
		}
		
		if (e.getSource()==aboutUs) {
			JOptionPane.showMessageDialog(null, "Text Editor made by Yagay Khatri\nRoll No - 2110110605\nLinkedIn: https://www.linkedin.com/in/yagay-khatri-1531671b6/");
		}
		
		if (e.getSource()==fontBox) {
			textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
		}
		
		if (e.getSource()==fontSize) {
			textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSize.getSelectedItem()));
		}
		
		
		if (e.getSource()==cut) {
			textArea.cut();
		}
		
		else if (e.getSource()==copy) {
			textArea.copy();
		}
		
		else if (e.getSource()==paste) {
			textArea.paste();
		}
		
		if (e.getSource()==count) {
			String text = textArea.getText();
			charCount.setText("   " + text.length());
			String words[] = text.split("\\s");
			wordCount.setText("   " + words.length);
		}
		
		if (e.getSource()==findNextButton) {
            String textAreaText = textArea.getText().toLowerCase();
            String textFieldText = find.getText().toLowerCase();
            int index = textAreaText.indexOf(textFieldText,pos);
            int length = textFieldText.length();
            Highlighter highlight = textArea.getHighlighter();
            highlight.removeAllHighlights();
            try {
                highlight.addHighlight(index, index+length, new DefaultHighlighter.DefaultHighlightPainter(Color.PINK));
            }
            catch(BadLocationException ae) {
                ae.printStackTrace();
            }
            pos = index+length;
            if(pos >= textAreaText.length()) pos = 0;
            if(textAreaText.indexOf(textFieldText,pos) == -1) pos = 0;
            
        }
		
		if (e.getSource()==findAllButton) {
			String oldWordFind = find.getText();
			Highlighter highlight = textArea.getHighlighter();        
		    DefaultHighlighter.DefaultHighlightPainter paint = new DefaultHighlighter.DefaultHighlightPainter(Color.PINK);
		    highlight.removeAllHighlights();        
		    Pattern pattern = Pattern.compile(Pattern.quote(oldWordFind) );
		    Matcher matcher = pattern.matcher(textArea.getText());                    
		    while(matcher.find()) {
		        try {
					highlight.addHighlight(matcher.start(), matcher.end(), paint);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
		    }
		}
		
		if (e.getSource()==replaceButton) {
			String oldWord = find.getText();
			String newWord = replace.getText();
			String replacedWordString = textArea.getText().replaceFirst(oldWord, newWord);
			textArea.setText(replacedWordString);
		}
		
		if (e.getSource()==replaceAllButton) {
			String oldWord = find.getText();
			String newWord = replace.getText();
			String replacedWordString = textArea.getText().replaceAll(oldWord, newWord);
			textArea.setText(replacedWordString);
		}
	}
}

public class tE {
    public static void main(String[] args) {
        TextEditor te = new TextEditor();
    }
}
