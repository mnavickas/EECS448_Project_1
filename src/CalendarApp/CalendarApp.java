package CalendarApp;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import DayView.DayView;
import MonthView.MonthView;
import Views.Views;
import WeekView.WeekView;
import YearView.YearView;
import constants.ViewTypes;
/**
 * 
 * Main entry point. The JFrame that everything is set in.
 *
 */

public class CalendarApp extends JFrame {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 142874753849595415L;
	
    public static final int FRAME_WIDTH 		= 700;
    public static final int FRAME_HEIGHT 		= 700;
	public static final int DAY_OF_MONTH_WIDTH 	= 100;
	public static final int DAY_OF_MONTH_HEIGHT = 100;
    
	/**
	 * This Instance of CalendarApp 
	 */
    public static CalendarApp app;
    
    private JComponent activePanel;
	private Views activeView;
	private ViewTypes activeViewType;
	
    private CalendarApp(){
		initFrame();
		
		activePanel = MonthView.getInstance();
		activeView = MonthView.getInstance();
		activeViewType = ViewTypes.MONTH;
		
		add(new MenuBar(this));
		add(activePanel);
		
		display();
	}
	
    private void initFrame(){
		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS) );
	}
	
    private void display(){
		pack();
		invalidate();
		validate();
		setVisible(true);
	}
    
    /**
     * Refresh active view in the JFrame
     */
    public void updateCurrentView(){
		activeView.update();
	}
    
    /**
     * Set the current view to the given view parameter and update the frame
     * @param View to change to
     */
	public void setActiveView(ViewTypes view){
		/*
		 * User pressed the current button, no change.
		 */
		if(view == activeViewType){
			return;
		}
		
		invalidate();
		remove(activePanel);
		switch(view)
		{
			case YEAR:
				activePanel = YearView.getInstance();
				activeView = YearView.getInstance();
				break;
			case MONTH:
				activePanel = MonthView.getInstance();
				activeView = MonthView.getInstance();
				break;
			case WEEK:
				activePanel = WeekView.getInstance();
				activeView = WeekView.getInstance();
				break;
			case DAY:
				activePanel = DayView.getInstance();
				activeView = DayView.getInstance();
				break;
			default:
				break;
			
		}
		activeViewType = view;
		add(activePanel);

	
		activeView.update();
		validate();
		repaint();
	}
	
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            app = new CalendarApp();
        });
        
	}
}
