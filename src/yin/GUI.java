package yin;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 String filePath;
	 String type;
	 final JComboBox comboBox;
	 JButton button;
	public GUI(){  
	        this.setTitle("文件类型转换");  
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        this.setBounds(300,300,500,500);  
	        JPanel contentPane=new JPanel();  
	        contentPane.setBorder(new EmptyBorder(5,5,5,5));  
	        this.setContentPane(contentPane);  
	        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
	        JLabel label2 = new JLabel("这是一个文件转化的小程序：");
	        JLabel label3 = new JLabel("1.选择文件的类型并选择文件的路径");
	        JLabel label4 = new JLabel("2.点击转化");
	        label2.setFont(new Font("宋体",Font.BOLD,20));
	        label3.setFont(new Font("宋体",Font.BOLD,20));
	        label4.setFont(new Font("宋体",Font.BOLD,20));
	        contentPane.add(label2);
	        contentPane.add(label3);
	        contentPane.add(label4);
	        JLabel label=new JLabel("文件类型:");  
	        contentPane.add(label);  
	        comboBox=new JComboBox();  
	        comboBox.addItem(".java");  
	        comboBox.addItem(".jsp");  
	        comboBox.addItem(".txt");  
	        contentPane.add(comboBox);
	        
	        JButton chooseJButton = new JButton("选择文件");
	        chooseJButton.addActionListener(this);
	        contentPane.add(chooseJButton);
	        button = new JButton("转化");
	        button.setFont(new Font("宋体",Font.BOLD,15));
	        button.addActionListener(this);
	        contentPane.add(button);
	        this.setVisible(true);  
	    }  
	   
		public void actionPerformed(ActionEvent arg0) {
			type = (String) comboBox.getSelectedItem();
			JButton button = (JButton) arg0.getSource();
			if(button.getText().equals("转化")){
				System.out.println("type:"+type+"\nfilepath:"+filePath);
				AnsiToUtf8 ant8 =new  AnsiToUtf8();
				ant8.run(filePath, type);
				System.out.println("转换成功");
			}else if(button.getText().equals("选择文件")){
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
					filePath = jfc.getSelectedFile().getAbsolutePath();
					filePath = filePath.replace("\\", "/");
					System.out.println("filepath:"+filePath);
				}
			}
			
		}
		 public static void main(String[] args) {
				new GUI();
			}
}
