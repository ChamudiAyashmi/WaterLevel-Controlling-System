import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
interface Observer{
    void update(int slidderValue);
}
class MainWindow extends JFrame {
    private JLabel lblTitle;
    private JButton btnGenerate;
    private static JSlider slider;
    public MainWindow(){
        Observerble observerble=Observerble.getInstance();
        setSize(400,400);
        setTitle("Water Level");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(200,100);
        setLayout(null);

        lblTitle=new JLabel("Water Level Generator");
        lblTitle.setBounds(50,10,300,30);
        lblTitle.setFont(new Font("",1,25));
        add(lblTitle);

        slider=new JSlider(0,100);
        slider.setBounds(160,70,50,250);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        slider.setOrientation(SwingConstants.VERTICAL);
        add(slider);

        btnGenerate=new JButton("Generete");
        btnGenerate.setBounds(250,300,100,30);
        btnGenerate.addActionListener(e -> {
            observerble.setSlidderValue(slider.getValue());
        });
        add(btnGenerate);

        setVisible(true);
    }
}
class WaterLevel extends JFrame implements Observer{
    private JLabel titleLabel;
    private JLabel lblWaterLevel;
    public WaterLevel(){
        setSize(300,200);
        setTitle("Water Level");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,100);
        setLayout(null);

        titleLabel=new JLabel("Water Level");
        titleLabel.setBounds(70,10,200,30);
        titleLabel.setFont(new Font("",1,25));
        add(titleLabel);

        lblWaterLevel=new JLabel("50");
        lblWaterLevel.setBounds(120,70,300,30);
        lblWaterLevel.setFont(new Font("",1,25));
        add(lblWaterLevel);

        setVisible(true);
    }
    @Override
    public void update(int slidderValue) {
        lblWaterLevel.setText(""+slidderValue);
    }
}
class Alarm extends JFrame implements Observer{
    private JLabel titleLabel;
    private JLabel lblWaterLevel;
    public Alarm(){
        setSize(300,200);
        setTitle("Alarm");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,300);
        setLayout(null);

        titleLabel=new JLabel("Alarm");
        titleLabel.setBounds(100,10,200,30);
        titleLabel.setFont(new Font("",1,25));
        add(titleLabel);

        lblWaterLevel=new JLabel("OFF");
        lblWaterLevel.setBounds(100,70,300,30);
        lblWaterLevel.setFont(new Font("",1,25));
        add(lblWaterLevel);

        setVisible(true);
    }
    @Override
    public void update(int slidderValue) {
        if (slidderValue>50){
            lblWaterLevel.setText("ON");
        }else {
            lblWaterLevel.setText("OFF");
        }
    }
}
class Splitter extends JFrame implements Observer{
    private JLabel titleLabel;
    private JLabel lblWaterLevel;
    public Splitter(){
        setSize(300,200);
        setTitle("Splitter");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600,500);
        setLayout(null);

        titleLabel=new JLabel("Splitter");
        titleLabel.setBounds(100,10,200,30);
        titleLabel.setFont(new Font("",1,25));
        add(titleLabel);

        lblWaterLevel=new JLabel("OFF");
        lblWaterLevel.setBounds(100,70,300,30);
        lblWaterLevel.setFont(new Font("",1,25));
        add(lblWaterLevel);

        setVisible(true);
    }
    @Override
    public void update(int slidderValue) {
        if (slidderValue>75){
            lblWaterLevel.setText("ON");
        }else {
            lblWaterLevel.setText("OFF");
        }
    }
}
class Observerble{
    private Observer[] observerArray=new Observer[100];
    private int nextIndex;
    private int slidderValue;
    private static Observerble instance;
    private Observerble(){}
    public static Observerble getInstance(){
        if (instance==null){
            instance=new Observerble();
        }
        return instance;
    }
    public void addObjects(Observer ob){
        observerArray[nextIndex++]=ob;
    }
    public void notifyObjects(){
        for (int i=0; i<nextIndex; i++){
            observerArray[i].update(slidderValue);
        }
    }
    public void setSlidderValue(int slidderValue){
        this.slidderValue=slidderValue;
        notifyObjects();
    }
}
public class Main {
    public static void main(String[] args) {
        Observerble observerble=Observerble.getInstance();
        observerble.addObjects(new WaterLevel());
        observerble.addObjects(new Alarm());
        observerble.addObjects(new Splitter());
        new MainWindow();
    }
}